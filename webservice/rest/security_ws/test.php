<?php
$datas = $HTTP_RAW_POST_DATA; // POST
// $datas = file_get_contents("php://input"); // PUT
print_r(json_decode($datas, TRUE));