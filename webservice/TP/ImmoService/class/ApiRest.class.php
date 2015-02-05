<?php
require_once "../class/Rest.class.php";
require_once "../class/Agence.class.php";

class ApiRest extends Rest {
	
	const HEADER = "X-API-Auth";
	private $con;
	private $agence;

	public function __construct() {
		$this->inputs();
		$this->connectToDB();
		$this->agence = new Agence($this->con);
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

			$this->con = new PDO("mysql:host=localhost;dbname=immo_service", "root", "");
			$this->con->setAttribute(PDO::ATTR_CASE, PDO::CASE_LOWER);
			$this->con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$this->con->exec("SET NAMES utf8");

		} catch (PDOException $e) {

			die($e->getMessage());

		}		

	}

	public function process() {

		$request = trim($_GET["request"], "/");

		if(isset($this->_request["request"]))
			unset($this->_request["request"]);

		if(method_exists($this, $request))
				$this->$request();
		else
			$this->response("404 la méthode n'existe pas", 404);

	}

	private function login() {

		if($this->get_request_method() != "POST")
			$this->response("Seul la méthode post est autorisée",406);
		
		$ref  = $this->_request["ref"];
		$mdp 	= $this->_request["mdp"];
		$res 	= array("success" => false);

		if(!empty($ref) && !empty($mdp)) {

			$token = $this->agence->login($ref, $mdp);

			if($token){
				$res["success"] = true;
				$res["token"] = $token;
				$this->response(json_encode($res),200);
			}
		
		}

		$res["msg"] = "Référence ou mot de passe non fournis";
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
		
		return $this->agence->validToken($token);

	}

	private function listBiens() {
		if($this->get_request_method() != "GET")
			$this->response(json_encode(array("msg"=>"Seul la méthode get est autorisée")),406);
		
		$res = array("success" => false);
		if(!$this->validToken()){
			$res["msg"] = "Token invalide";
			$this->response(json_encode($res),401);
		}

		if($this->agence->listBiens($this->_request)) {

			$res["success"] = true;
			if(count($this->agence->biens) > 1) { // si un plusieur biens
				foreach($this->agence->biens as $bien)
					$res["biens"][] = get_object_vars($bien); // renvoie un tableau de biens
			} else {
				$res["biens"] = get_object_vars($this->agence->biens[0]); // sinon renvoie un seul bien
			}
			
			$this->response(json_encode($res),200);
		
		}
	
		$res["msg"] = "Aucun biens";
		$this->response(json_encode($res),404);
	
	}
	
	private function searchBiensRef() {
		$this->listBiens();
	}
	
	private function searchBiens() {
		$this->listBiens();	
	}

	private function insertOrUpdate() { // Méthode pour ajouter ou modifier un bien

		if($this->get_request_method() != "POST" && $this->get_request_method() != "PUT")
			$this->response(json_encode(array("msg"=>"Seul la méthode post ou put sont autorisées")),406);
		
		$res = array("success" => false);
		if(!$this->validToken()){
			$res["msg"] = "Token invalide";
			$this->response(json_encode($res),401);
		}
		
		if(!empty($_FILES)) { // Si des fichiers sont poster les ajouters
			if($this->agence->insertOrUpdatePhotos($this->_request)) {
				$res["upload_file"] = true;
			}
		}
		
		if(!empty($this->_request) && $this->agence->insertOrUpdateBiens($this->_request)) {
			$res["success"] = true;
			$this->response(json_encode($res),200);
		} 	
		
		$this->response(json_encode($res),409);
	
	}

	private function deletePhoto(){
		if($this->get_request_method() != "DELETE")
			$this->response(json_encode(array("msg"=>"Seul la méthode Delete est autorisée")),406);
		
		$res = array("success" => false);
		if(!$this->validToken()){
			$res["msg"] = "Token invalide";
			$this->response(json_encode($res),401);
		}

		if(!empty($this->_request) && $this->agence->deletePhoto($this->_request)) {
			$res["success"] = true;
			$this->response(json_encode($res),200);
		} 	
		
		$this->response(json_encode($res),409);
	}

	private function vente() {
		
		if($this->get_request_method() != "POST")
			$this->response(json_encode(array("msg"=>"Seul la méthode post sont autorisées")),406);

		$res = array("success" => false);
		if(!$this->validToken()){
			$res["msg"] = "Token invalide";
			$this->response(json_encode($res),401);
		}

		if($this->agence->venteBien($this->_request["id"], $this->_request["date_vente"])) {
			$res["success"] = true;
			$this->response(json_encode($res),200);
		} 	
		
		$res["message"] = "impossible de vendre ce bien";
		$this->response(json_encode($res),409);

	}

	private function location() {
		
		if($this->get_request_method() != "POST")
			$this->response(json_encode(array("msg"=>"Seul la méthode post sont autorisées")),406);

		$res = array("success" => false);
		if(!$this->validToken()){
			$res["msg"] = "Token invalide";
			$this->response(json_encode($res),401);
		}

		if($this->agence->locationBien($this->_request["id"], $this->_request["date_debut"], $this->_request["date_fin"])) {
			$res["success"] = true;
			$this->response(json_encode($res),200);
		} 	
		
		$res["message"] = "impossible de louer ce bien";
		$this->response(json_encode($res),409);

	}

	private function visite() {
		
		if($this->get_request_method() != "POST")
			$this->response(json_encode(array("msg"=>"Seul la méthode post sont autorisées")),406);

		$res = array("success" => false);
		if(!$this->validToken()){
			$res["msg"] = "Token invalide";
			$this->response(json_encode($res),401);
		}

		if($this->agence->visiteBien($this->_request["id"], $this->_request["date"])) {
			$res["success"] = true;
			$this->response(json_encode($res),200);
		} 	
		
		$res["message"] = "impossible de louer ce bien";
		$this->response(json_encode($res),409);

	}

	private function statistique() {
		
		if($this->get_request_method() != "GET")
			$this->response(json_encode(array("msg"=>"Seul la méthode get sont autorisées")),406);

		$res = array("success" => false);
		if(!$this->validToken()){
			$res["msg"] = "Token invalide";
			$this->response(json_encode($res),401);
		}

		$res["success"] = true;
		$res["statistique"] = $this->agence->statistique($this->_request["date"]);
		$this->response(json_encode($res),200);
	
	}

}