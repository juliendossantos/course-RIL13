<?php
require_once "../class/ApiRest.class.php";
header("Content-type: application/json");
$service = new ApiRest();
$service->process();