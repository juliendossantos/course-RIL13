<?php
require_once "class/Api.class.php";
header("Content-type: application/json");
$api = new Api();
$api->process();