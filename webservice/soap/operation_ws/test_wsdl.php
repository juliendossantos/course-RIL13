<?php
ini_set("soap.wsdl_cache_enabled",0);
header("Content-type: text/html");
$wsdl = "http://localhost/RIL_13/webservice/soap/operation_ws/operation.wsdl";
$parameters = array(
	"soap_version" => SOAP_1_2,
	"encoding" => "utf-8",
	"trace" => true
);

try {
	
	$client = new SoapClient($wsdl, $parameters);
	// var_dump($client->__getFunctions());
	var_dump($client->add(1,1));
	var_dump($client->multiply(1,1));
	var_dump($client->subtract(1,1));
	var_dump($client->divide(1,1));

} catch(SoapFault $e) {
	
	var_dump($e);

}