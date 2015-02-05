<?php
require_once "class/Gestion.class.php";
header("Content-type: application/json");
$gestion = new Gestion();
$gestion->process();