<?php
require_once "Bien.class.php";

class Agence {

	public $id;
	public $name;
	public $biens = array();
	private $con;

	public function __construct($con) {
		$this->con = $con;
	}

	public function login($ref, $mdp) {

		if(!empty($ref) && !empty($mdp)) {
			$qry = "SELECT id, token, token_expire FROM agences WHERE ref='" . $ref . "' AND mdp='" . $mdp . "'";
			$rs = $this->con->query($qry);

			if($rs->rowCount() == 1){
				$rw = $rs->fetch(PDO::FETCH_ASSOC);
				$token = md5(time());
				$qry = "UPDATE agences SET token='" . $token . "', token_expire=DATE_ADD(NOW(), INTERVAL 1 DAY) WHERE id=" . $rw['id'];
				if($this->con->exec($qry)) {
					return $token;
				}
			}
		}

		return false;	
	
	}

	public function validToken($token) {

		$qry = "SELECT id, nom FROM agences WHERE token='" . $token . "' AND token_expire > NOW()";

		$rs = $this->con->query($qry);
		
		if($rs->rowCount() > 0) {
			$rw = $rs->fetch(PDO::FETCH_ASSOC);
			$this->id = $rw["id"];
			$this->name = $rw["nom"];
			return true;
		} else {
			return false;
		}

	}

	public function listBiens($search = false) {

		$qry = "SELECT b.id
			FROM biens as b
			LEFT JOIN type_bien as tb
				ON b.type_bien_id=tb.id
			WHERE b.agences_id =" . $this->id;

		if ($search) { // si des paramètre de recherche sont spécifier, construit une requéte de recherche
			foreach($search as $key => $value) {
				if($key == "type")
					$clause[] = "tb.". $key . "='" . $value . "'"; 
				else
					$clause[] = "b.". $key . "='" . $value . "'"; 
			}
			
			$qry .= " AND " . implode(" AND ", $clause);
		}

		$rs = $this->con->query($qry);
		
		if($rs->rowCount() > 0) {

			while($rw = $rs->fetch(PDO::FETCH_ASSOC)) {
				$bien = new Bien($rw["id"], $this->con);
				$bien->setDatas();
				$this->biens[] = $bien;
			}

			return true;
		
		} else {
			
			return false;
		
		}
	
	}

	public function insertOrUpdateBiens($params) {

		if(isset($params["id"])) {
			$id = $params["id"];
			unset($params["id"]);
		} else {
			$id = 0;
		}

		$put = $id > 0;
		$bien = new Bien($id, $this->con);

		if($put)
			$bien->setDatas();
		else 
			$bien->agences_id = $this->id;

		if($bien->agences_id == $this->id && !empty($params))
			return $bien->insertOrUpdateBiens($params);
		else
			return false;
	
	}

	public function insertOrUpdatePhotos($params) {
		
		if(!isset($params["id"]))
			return false; 
		
		$bien = new Bien($params["id"], $this->con);
		$bien->setDatas();
		$bien->insertOrUpdatePhotos($_FILES["photos"]);
		return true;

	}

	public function deletePhoto($params) {

		if(!isset($params["bien_id"]) && !isset($params["photo_id"]))
			return false;
		
		$bien = new Bien($params["bien_id"], $this->con);
		$bien->setDatas();
		return $bien->deletePhoto($params["photo_id"]);
	
	}

	public function venteBien($id, $date_vente) {
		
		$bien = new Bien($id, $this->con);
		$bien->setDatas();
		return $bien->vente($date_vente);	
	
	}

	public function locationBien($id, $date_debut, $date_fin) {

		$bien = new Bien($id, $this->con);
		$bien->setDatas();
		return $bien->location($date_debut, $date_fin);	
	
	}

	public function visiteBien($id, $date) { // Méthod ajouter nécessaire pour calculer le taux de convertion
		$bien = new Bien($id, $this->con);
		$bien->setDatas();
		return $bien->visite($date);	
	}

	public function statistique($date) {

		// $statistique = array("month"=>$month);

		$statistique["ventes_mois"] = $this->statistiqueVente($date);

		$statistique["locations_mois"] = $this->statistiqueLocation($date);

		// $statistique["location_disponible_debut"] = $this->locationDisponibleDebut($date);

		// $statistique["ventes_disponible_debut"] = $this->venteDisponibleDebut($date);

		// $statistique["location_disponible_fin"] = $this->locationDisponibleFin($date);

		// $statistique["ventes_disponible_fin"] = $this->venteDisponibleFin($date);

		$statistique["biens_disponible_debut_mois"] = $this->locationDisponibleDebut($date) + $this->venteDisponibleDebut($date);

		$statistique["biens_disponible_fin_mois"] = $this->locationDisponibleFin($date) + $this->venteDisponibleFin($date);

		$statistique["visites_mois"] = $this->statistiqueVisites($date);

		if($statistique["ventes_mois"] + $statistique["locations_mois"] > 0)
			$statistique["taux_transformation_mois"] = ($statistique["ventes_mois"] + $statistique["locations_mois"]) / $statistique["visites_mois"];
		else
			$statistique["taux_transformation_mois"] = 0;

		return $statistique;
	
	}

	public function statistiqueVente($date) {
		$qry = "SELECT v.id
						FROM ventes as v
						LEFT JOIN biens as b
							ON v.bien_id=b.id
						WHERE b.agences_id =".$this->id."
						AND MONTH(date_vente) = MONTH('".$date."')
						AND YEAR(date_vente) = YEAR('".$date."')";

		$rs = $this->con->query($qry);
		return $rs->rowCount();
	}

	public function statistiqueLocation($date) {
		$qry = "SELECT l.id
						FROM locations as l
						LEFT JOIN biens as b
							ON l.bien_id=b.id
						WHERE b.agences_id =".$this->id."
						AND MONTH(date_debut) = MONTH('".$date."')
						AND YEAR(date_debut) = YEAR('".$date."')";

		$rs = $this->con->query($qry);
		return $rs->rowCount();
	}

	public function venteDisponibleDebut($date) {
		$qry = "SELECT b.id
						FROM biens as b
						LEFT JOIN ventes as v
							ON v.bien_id = b.id
						WHERE b.agences_id =1 
						AND type_action_id=1
						AND (
							v.date_vente IS NULL OR
							v.date_vente > DATE_FORMAT('".$date."' ,'%Y-%m-01')
						)";
		
		$rs = $this->con->query($qry);
		return $rs->rowCount();

	}

	public function venteDisponibleFin($date) {
		$qry = "SELECT b.id
						FROM biens as b
						LEFT JOIN ventes as v
							ON v.bien_id = b.id
						WHERE b.agences_id =1 
						AND type_action_id=1
						AND (
							v.date_vente IS NULL OR
							v.date_vente > DATE_FORMAT('".$date."' ,CONCAT('%Y-%m-',DAY(LAST_DAY('".$date."'))))
						)";
		
		$rs = $this->con->query($qry);
		return $rs->rowCount();

	}

	public function locationDisponibleDebut($date) {
		$qry = "SELECT b.id as count
						FROM biens as b
						LEFT JOIN locations as l
							ON l.bien_id = b.id
						WHERE b.agences_id =".$this->id."
						AND b.type_action_id=2
						AND (
							l.date_debut IS NULL OR
							DATE_FORMAT('".$date."' ,'%Y-%m-01') 
							NOT BETWEEN 
								l.date_debut AND 
								l.date_fin
						) GROUP BY b.id";
		
		$rs = $this->con->query($qry);
		return $rs->rowCount();
	
	}

	public function locationDisponibleFin($date) {
		$qry = "SELECT b.id as count
						FROM biens as b
						LEFT JOIN locations as l
							ON l.bien_id = b.id
						WHERE b.agences_id =".$this->id."
						AND b.type_action_id=2
						AND (
							l.date_debut IS NULL OR
							DATE_FORMAT(
								'".$date."' ,
								CONCAT(
									'%Y-%m-',
									DAY(
										LAST_DAY('".$date."')
									)
								)
							) 
							NOT BETWEEN 
								l.date_debut AND 
								l.date_fin
						) GROUP BY b.id";

		$rs = $this->con->query($qry);
		return $rs->rowCount();
	
	}

	public function statistiqueVisites($date) {
		$qry = "SELECT v.id
						FROM biens as b
						LEFT JOIN visites as v
							ON v.bien_id=b.id
						WHERE b.agences_id =".$this->id."
						AND MONTH(v.date) = MONTH('".$date."')
						AND YEAR(v.date) = YEAR('".$date."')";

		$rs = $this->con->query($qry);
		return $rs->rowCount();
	}

}