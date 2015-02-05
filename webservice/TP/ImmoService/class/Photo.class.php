<?php

class Photo {
	public $id, $path;
	private $con;

	public function __construct($con, $id= 0) {
		$this->id = $id;
		$this->con = $con;
		
		if($id == 0)
			return false;

		$qry = "SELECT chemin FROM photos WHERE id=".$id;
		
		if($rs = $this->con->query($qry)) {
			$rw = $rs->fetch(PDO::FETCH_ASSOC);
			$this->path = $rw["chemin"];
		}

	}

	public function insertOrUpdate() {
		
		$put = $this->id > 0;
		if(!file_exists($this->path))
			return false;

		$params = array(
			":chemin"=>$this->path
		);

		$qry = ($put ? "UPDATE " : "INSERT INTO ") . " photos SET chemin=:chemin";


		if($put)
			$qry .= " WHERE id=". $this->id;

		$stg = $this->con->prepare($qry);
		
		if($stg->execute($params)) {
			if(!$put) {
				$this->id = $this->con->lastInsertId();
			}
			return true;
		} else {
			return false;
		}
	
	}

	public function delete() {
		if($this->id == 0)
			return false;

		if(!unlink($this->path))
			return false;

		$qry = "DELETE FROM photos WHERE id=".$this->id;
		
		if($rs = $this->con->query($qry))
			return true;
		else
			return false;
	
	}

}