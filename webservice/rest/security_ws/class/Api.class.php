<?php
require_once "Rest.class.php";

class Api extends Rest {
	
	const HEADER = "X-API-Auth";
	private $con;

	public function __construct() {
		$this->inputs();
		$this->connectToDB();
	}

	private function inputs(){
		
		$isJson = (isset($_SERVER["CONTENT_TYPE"]) && strtolower($_SERVER["CONTENT_TYPE"]) == $this->_content_type);
		$input = file_get_contents("php://input"); // PUT
		
		switch($this->get_request_method()){
			case "POST":
				if($isJson)
					$this->_request = json_decode($input, TRUE);
				else
					$this->_request = $this->cleanInputs($_POST);
				break;
			case "GET":
			case "DELETE":
				$this->_request = $this->cleanInputs($_GET);
				break;
			case "PUT":
				if($isJson) {
					$this->_request = json_decode($input, TRUE);
				} else {
					parse_str($input,$this->_request);
					$this->_request = $this->cleanInputs($this->_request);
				}
				break;
			default:
				$this->response('',406);
				break;
		}
	
	}


	private function connectToDB() {

		try{

			$this->con = new PDO("mysql:host=localhost;dbname=cesi_rest_sample", "root", "");
			$this->con->setAttribute(PDO::ATTR_CASE, PDO::CASE_LOWER);
			$this->con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$this->con->exec("SET NAMES utf8");

		} catch (PDOException $e) {

			die($e->getMessage());

		}		

	}

	public function process() {
		
		$request = trim($_GET["request"], "/");		
		
		if(method_exists($this, $request))
				$this->$request();
		else
			$this->response("404 la méthode n'existe pas", 404);

	}

	private function login() {

		if($this->get_request_method() != "POST")
			$this->response("Seul la méthode post est autorisée",406);
		
		$email  = $this->_request["email"];
		$pwd 		= $this->_request["pwd"];
		$res = array("success" => false);

		if(!empty($email) && !empty($pwd)) {
			if(filter_var($email, FILTER_VALIDATE_EMAIL)) {
				$qry = "SELECT id, token, token_expire FROM users WHERE email='" . $email . "' AND pwd='" . $pwd . "'";
				$rs = $this->con->query($qry);

				if($rs->rowCount() == 1){
					$rw = $rs->fetch(PDO::FETCH_ASSOC);
					$token = md5(time());
					$qry = "UPDATE users SET token='" . $token . "', token_expire=DATE_ADD(NOW(), INTERVAL 1 DAY) WHERE id=" . $rw['id'];
					if($this->con->exec($qry)) {
						$res["success"] = true;
						$res["token"] = $token;
						$this->response(json_encode($res),200);
					}
				}
			}				
		}

		$res["msg"] = "Email ou mot de passe non fournis";
		$this->response(json_encode($res), 401);

	}
	
	private function validToken() {

		$headers = getallheaders();
		
		if(!isset($headers[self::HEADER])) {
			$res["success"] = FALSE;
			$res["msg"] = "Veuillez-vous identifier !";
			$this->response(json_encode($res), 401);
		}

		$token = $headers[self::HEADER];

		$qry = "SELECT id FROM users WHERE token='" . $token . "' AND token_expire > NOW()";

		$rs = $this->con->query($qry);
		
		return $rs->rowCount() > 0;

	}
	
	private function listUsers() {

		if($this->get_request_method() != "GET")
			$this->response(json_encode(array("msg"=>"Seul la méthode get est autorisée")),406);
		
		$res = array("success" => false);
		if(!$this->validToken()){
			$res["msg"] = "Token invalide";
			$this->response(json_encode($res),401);
		}
		
		$res["success"] = true;
		$res["users"] = array();
		$qry = "SELECT id, email FROM users";
		$rs = $this->con->query($qry);
		
		if($rs->rowCount() > 0) {
			while($rw = $rs->fetch(PDO::FETCH_ASSOC)) {
				$res["users"][] = $rw;
			}
			
			$this->response(json_encode($res),200);
		}
		
		$res["msg"] = "Aucun utilisateur";
		$this->response(json_encode($res),404);

	}

	private function addUser() {

		if($this->get_request_method() != "POST")
			$this->response("Seul la méthode post est autorisée",406);
		
		$res = array("success" => false);
		
		if(!$this->validToken()){
			$res["msg"] = "Token invalide";
			$this->response(json_encode($res),401);
		}
		
		$qry = "INSERT INTO users SET email='" . $this->_request["email"] . "', pwd='" . $this->_request["pwd"] . "'";

		try {

			$res["success"] = (bool)$this->con->exec($qry);
			
			if($res["success"])
				$res["id"] = $success->lastInsertId();
			
			$this->response(json_encode($res),200);
		
		} catch (PDOException $e) {
			
			if($e->errorInfo[1] == 1062) {
				$res["msg"] = "Un utilisateur est déjà enregistrer avec l'adresse email : " . $this->_request["email"];
				$this->response(json_encode($res),409);
			}
			
			throw($e);
		
		}

	}

	private function deleteUser() {

		if($this->get_request_method() != "DELETE")
			$this->response("Seul la méthode delete est autorisée",406);
		if(!$this->validToken()){
			$res["msg"] = "Token invalide";
			$this->response(json_encode($res),401);
		}
		if(!isset($this->_request["id"]) || $this->_request["id"] > 0) {
			$res["msg"] = "Aucun identification de suppression fourni !";
			$this->response(json_encode($res),400);
		}

		$res["success"] = false;

		$qry = "DELETE FROM users WHERE id='" . $this->_request['id'] . "'";
	
		$res["success"] = (bool)$this->con->exec($qry);
		
		$this->response(json_encode($res),200);

	}

}