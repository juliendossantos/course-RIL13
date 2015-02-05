<?php
require_once "../class/ApiSoap.class.php";
ini_set("soap.wsdl_cache_enabled",0);
$wsdl = "service.wsdl";

$service = new SoapServer($wsdl);
$service->setClass("ApiSoap");
$service->handle();
