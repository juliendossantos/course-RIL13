<?php
header("Content-type: text/html");
$wsdl = "http://w3schools.com/webservices/tempconvert.asmx?WSDL";
$parameters = array(
	"soap_version" => SOAP_1_2,
	"encoding" => "utf-8",
	"trace" => true
);

$client = new SoapClient($wsdl, $parameters);

// var_dump($client->__getFunctions());
// var_dump($client->__getTypes());

$cel = 30;

try {
	$result = $client->CelsiusToFahrenheit(array("Celsius" => $cel));
	$fah = $result->CelsiusToFahrenheitResult;
	echo $cel . " Celcius font " . $fah . " Fahrenheit\n";

	$result = $client->FahrenheitToCelsius(array("Fahrenheit" => $fah));
	echo $fah . " Fahrenheit font " . $cel . " Celcius\n";

} catch(SoapFault $e) {
	var_dump($e);
}