<?php

class Entity {

	protected $con;
	protected $table;

	public function __construct($t, $c) {
		$this->table = $t;
		$this->con = $c;
	}

	public function get($id) {
		$result = array();
		$qry = "SELECT * FROM " . $this->table;

		if($id > 0)
			$qry .= " WHERE id=".$id;

		$rs = $this->con->query($qry);

		if($id > 0){
			if($rs->rowCount() == 1)
				$result['result'] = $rs->fetch(PDO::FETCH_ASSOC);
			else
				$result['error'] = 'No ' . $this->table . ' for ID: ' .$id;

		} else {
			$result['result'] = array();
			while($rw = $rs->fetch(PDO::FETCH_ASSOC))
				$result['result'][] = $rw;
		}
		return $result;
	}

	public function delete($id) {
		$result = array();
		
		$qry = "DELETE FROM " . $this->table . " WHERE id= :id";
		$stg = $this->con->prepare($qry);
		
		if(!$stg->execute(array(":id"=>$id)))
			$result["error"] = "Suppression impossible";
		
		return $result;
	}

	public function post($params) {
		$this->insertOrUpdate($params);
	}

	public function put($params) {
		$this->insertOrUpdate($params);
	}

	private function insertOrUpdate($params) {
		$result = array();
		
		if(isset($params["id"])) {
			$id = $paramas["id"];
			unset($paramas["id"]);
		}
		
		$put = $id > 0;

		$qry = ($put ? "UPDATE " : "INSERT INTO ") . $this->table . " SET ";

		foreach($params as $key => $value) {
			$values[] = "`" . $key . "` = :" . $key;
			unset($params[$key]);
			$params[":".$key] = $value;
		}

		$qry .= implode(", ", $values);

		if($put)
			$qry .= " WHERE id=".$id;

		$stg = $this->con->prepare($qry);
		
		if($stg->execute($params))
			$result["id"] = ($put ? $id : $this->con->lastInsertId());
		else
			$result["error"] = "Immposible " . ($put ? "de modifier" : "d'ajouter") . " l'élément";

		return $result;
	
	}

}