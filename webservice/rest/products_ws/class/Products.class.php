<?php
require_once "class/Entity.class.php";

class Products extends Entity {

	public function __construct($c) {
		parent::__construct("products", $c);
	}

}