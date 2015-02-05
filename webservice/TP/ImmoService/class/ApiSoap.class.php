<?php
require_once "../class/Agence.class.php";

class ApiSoap {

	private $con;
	private $agence;

	public function __construct($wsdl) {
		$this->connectToDB();
		$this->agence = new Agence($this->con);
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

	public function login($ref, $mdp) {
		$token = $this->agence->login($ref, $mdp);
		if($token)
			return $token;
		else
			return "Référence ou mot de passe non fournis";
	}

	public function validToken($token) {
		return $this->agence->validToken($token);
	}

	public function listBiens($token) {
		if($this->validToken($token) && $this->agence->listBiens()) {
			foreach($this->agence->biens as $bien)
				$biens[] = get_object_vars($bien);
			return $biens;
		}

		return false;
	}
	
	public function searchRefBiens($token, $ref) {
		if($this->validToken($token) && $this->agence->listBiens(array("ref"=>$ref)))
			return get_object_vars($this->agence->biens[0]);
		
		return false;
	}

	public function searchBiens($token, $search) {
		if($this->validToken($token) && $this->agence->listBiens($search)) {
			if(count($this->agence->biens) > 1) {
				foreach($this->agence->biens as $bien)
					$biens[] = get_object_vars($bien);
				return $biens;
			} else { 
				return get_object_vars($this->agence->biens[0]);
			}
		}
		
		return false;
	}

	public function insertOrUpdate($token, $params) {
		if($this->validToken($token) && $this->agence->insertOrUpdateBiens($params))
			return true;

		return false;
	}

	public function vente($token, $id, $date_vente) {
		if($this->validToken($token) && $this->agence->venteBien($id, $date_vente))
			return true;

		return false;
	}

	public function location($token, $id, $date_debut, $date_fin) {
		if($this->validToken($token) && $this->agence->locationBien($id, $date_debut, $date_fin))
			return true;

		return false;
	}

	public function statistique($token, $date) {
		if($this->validToken($token))
			return $this->agence->statistique($date);

		return false;
	}

}