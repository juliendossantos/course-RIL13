<?php
ini_set("soap.wsdl_cache_enabled",0);
header("Content-type: text/html");
$wsdl = "service.wsdl";
$parameters = array(
	"soap_version" => SOAP_1_2,
	"encoding" => "utf-8",
	"trace" => true
);

try {
	
	$client = new SoapClient($wsdl, $parameters);
	// var_dump($client->__getFunctions());
	// var_dump($client->login("CI2567","caP2089"));
	// var_dump($client->listBiens("c3427ef63762a5b853be42b5e00692b8"));
	// var_dump($client->searchBiens("c3427ef63762a5b853be42b5e00692b8", array("ref"=>"CI78754")));
	// var_dump($client->searchRefBiens("c3427ef63762a5b853be42b5e00692b8", "CI78754"));
	// var_dump($client->searchBiens("c3427ef63762a5b853be42b5e00692b8", array("type"=>"maison")));
	// var_dump($client->insertOrUpdate("c3427ef63762a5b853be42b5e00692b8", array("nb_pieces"=>7,"id"=>10)));
	// var_dump($client->vente("c3427ef63762a5b853be42b5e00692b8",10,"2013-12-12"));
	// var_dump($client->location("c3427ef63762a5b853be42b5e00692b8",8,"2013-12-12 10:00:00","2013-12-19 10:00:00"));
	// var_dump($client->statistique("c3427ef63762a5b853be42b5e00692b8","2013-12-01"));

} catch(SoapFault $e) {
	
	var_dump($e);

}