<?php
header("Content-type: text/html");
$wsdl = "http://www.webservicex.net/geoipservice.asmx?WSDL";
$parameters = array(
	"soap_version" => SOAP_1_2,
	"encoding" => "utf-8",
	"trace" => true
);

$client = new SoapClient($wsdl, $parameters);

// var_dump($client->__getFunctions());
// var_dump($client->__getTypes());

if(isset($_POST["ip"])) {

	$result = $client->GetGeoIP(array("IPAddress"=>$_POST["ip"]));
	// var_dump($result);
	echo $result->GetGeoIPResult->CountryName;

} else {

	?><form method="post" >
	<label> Saisir IP :
	<input type="text" name="ip"/></label>
	<input type="submit" value="envoyer" />
	</form><?php

}