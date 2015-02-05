<?php
header("Content-type: application/xml");
$wsdl = "http://www.webservicex.net/globalweather.asmx?WSDL";
$parameters = array(
	"soap_version" => SOAP_1_2,
	"encoding" => "utf-8",
	"trace" => true
);

$client = new SoapClient($wsdl, $parameters);


$result = $client->GetWeather(array("CityName"=>"Paris", "CountryName"=>"France"));
// var_dump($result);

echo $result->GetWeatherResult;