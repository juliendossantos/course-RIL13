<?php
require_once "Photo.class.php";

class Bien {

	public $id, $ref, $desc, $adresse, $ville, $cp, $superficie, $nb_pieces, $prix, $action, $type, $agences_id;

	public $photos = array();
	private $con;

	public function __construct($id = 0, $con) {
		$this->id = $id;
		$this->con = $con;
	}

	public function setDatas() {
		
		if($this->id == 0)
			return false;

		$qry = "SELECT b.id, b.ref, b.desc, b.adresse, b.ville, b.cp, b.superficie, b.nb_pieces, b.prix, ta.action, tb.type, b.agences_id
			FROM biens as b
			LEFT JOIN type_bien as tb
				ON b.type_bien_id=tb.id
			LEFT JOIN type_action as ta
				ON b.type_action_id=ta.id
			WHERE b.id =" . $this->id;

		$rs = $this->con->query($qry);
		
		if($rs->rowCount() == 0)
			return false;

		$rw = $rs->fetch(PDO::FETCH_ASSOC);
		
		foreach($rw as $attr => $value)
			$this->$attr = $value;

	}

	public function insertOrUpdateBiens($params) {

		$put = $this->id > 0;
		
		$qry = ($put ? "UPDATE " : "INSERT INTO ") . " biens SET ";

		foreach($params as $key => $value) {
			$values[] = "`" . $key . "` = :" . $key;
			unset($params[$key]);
			$params[":".$key] = $value;
		}


		$qry .= implode(", ", $values);

		if($put) {
			$qry .= " WHERE id=". $this->id;
		} else {
			$params[":agences_id"] = $this->agences_id;
			$qry .= ", agences_id = :agences_id";
		}

		$stg = $this->con->prepare($qry);
		
		if($stg->execute($params)) {
			if(!$put) {
				$this->id = $this->con->lastInsertId();
				$this->setDatas();
			}
			return true;
		} else {
			return false;
		}

	}

	public function vente($date_vente) {

		if($this->action != "vente")
			return false;

		$params = array(
			":date_vente"=>$date_vente
		);

		$qry = "INSERT INTO ventes SET bien_id =".$this->id.", date_vente=:date_vente";
		
		$stg = $this->con->prepare($qry);
		
		if($stg->execute($params))
			return true;
		else
			return false;

	}

	public function location($date_debut, $date_fin) {

		if($this->action != "location")
			return false;
		
		$params = array(
			":date_debut"=>$date_debut,
			":date_fin"=>$date_fin
		);
		
		$qry = "INSERT INTO locations SET bien_id=".$this->id.", date_debut=:date_debut, date_fin=:date_fin";
		
		$stg = $this->con->prepare($qry);
		
		if($stg->execute($params))
			return true;
		else
			return false;
	
	}

	public function visite($date) {
		
		$params = array(
			":date"=>$date
		);
		
		$qry = "INSERT INTO visites SET bien_id=".$this->id.", date=:date";
		
		$stg = $this->con->prepare($qry);
		
		if($stg->execute($params))
			return true;
		else
			return false;
	
	}

	public function insertOrUpdatePhotos($photos) {

		for($i=0, $count=count($photos["name"]); $i < $count;$i++) {
			$path = "../photos/".$photos["name"][$i];
			if(move_uploaded_file($photos["tmp_name"][$i], $path))
				$this->addPhotos($path);
		}

		return true;

	}

	public function addPhotos($path) {
		
		$photo = new Photo($this->con);
		$photo->path = $path;
		if(!$photo->insertOrUpdate())
			return false;

		$params = array(
			":photo_id" => $photo->id,
			":bien_id" => $this->id
		);

		$qry = "INSERT INTO bien_photo SET photo_id=:photo_id, bien_id=:bien_id";
		
		$stg = $this->con->prepare($qry);
		
		if($stg->execute($params)) {
			array_push($this->photos, $photo);
			return true;
		} else {
			return false;
		}
	
	}

	public function deletePhoto($id) {
		
		$photo = new Photo($this->con,$id);
		if(!$photo->delete())
			return false;
		
		$params = array(
			":photo_id" => $photo->id,
			":bien_id" => $this->id
		);

		$qry = "DELETE FROM bien_photo WHERE bien_id=:bien_id AND photo_id=:photo_id";
		
		$stg = $this->con->prepare($qry);
		
		if($stg->execute($params)) {
			return true;
		} else {
			return false;
		}
	
	}

}