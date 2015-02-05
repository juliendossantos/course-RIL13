<?php
ini_set("soap.wsdl_cache_enabled",0);
$wsdl = "http://localhost/RIL_13/webservice/soap/operation_ws/operation.wsdl";

class Math {

	function add($value1, $value2) {
		return $value1 + $value2;
	}

	function subtract($value1, $value2) {
		return $value1 - $value2;
	}

	function multiply($value1, $value2) {
		return $value1 * $value2;
	}

	function divide($value1, $value2) {
		return $value1 / $value2;
	}

}

$server = new SoapServer($wsdl);

$server->setClass("Math");

// var_dump($server);
// Démarre le serveur SOAP et attend une requête
$server->handle();