<?php
try{

	$con = new PDO("mysql:host=localhost;dbname=cesi_rest_sample", "root", "");
	$con->setAttribute(PDO::ATTR_CASE, PDO::CASE_LOWER);
	$con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	$con->exec("SET NAMES utf8");

} catch (PDOException $e) {

	die($e->getMessage());

}

$headers = getallheaders();
$accept  = $headers["Accept"];
$uri 		 = str_replace("/RIL_13/webservice/rest/products_ws/", "", $_SERVER["REQUEST_URI"]);
$method  = $_SERVER["REQUEST_METHOD"];
$params  = explode("/", $uri);
$entity  = array_shift($params);
$id 		 = (int)array_shift($params);

$result = array();
$result["success"] = false;

try {

	switch($entity) {
		case "products":
		case "news":

			if($entity == "products") {
				require_once "class/Products.class.php";
				$instance = new Products($con);
			} /*else if ($entity == "news") {
				require_once "class/News.class.php";
				$instance = new News($con);				
			}*/

			if($method == "GET") {
				$result = array_merge($result, $instance->get($id));
			} else if ($method == "POST" && $id == 0) {
				$result = array_merge($result, $instance->post($_POST));
			} else if($method == "PUT" && $id > 0) {
				parse_str(file_get_contents("php://input"), $put);
				$put["id"] = $id;
				$result = array_merge($result, $instance->post($put));				
			} else if($method == "DELETE" && $id > 0) {
				$result = array_merge($result, $instance->delete($id));				
			}
			break;
			default:
				$result["succes"] = false;
				$result["error"] = "Unknow entity";
				break;
	}

} catch(PDOException $e) {
	$result["error"] = $e->getMessage();
}

$result["success"] = !isset($result["error"]) && empty($result["error"]);

header("Content-type: application/json; charset: utf-8");
echo json_encode($result, JSON_UNESCAPED_UNICODE);