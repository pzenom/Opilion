<?php

/**
 * File to handle all API requests
 * Accepts GET and POST
 * 
 * Each request will be identified by TAG
 * Response will be JSON data

  /**
 * check for POST request 
 */
if (isset($_POST['tag']) && $_POST['tag'] != '') {
    // get tag
    $tag = $_POST['tag'];
	
    // include db handler
    require_once 'include/DB_Functions.php';
    $db = new DB_Functions();

    // response Array
    $response = array("tag" => $tag, "success" => 0, "error" => 0);

	if ($tag == 'comerciales') {
		$comerciales = $db->getComerciales();
		$response["success"] = 1;
		$vector = $comerciales;
		for($i=0;$i<count($vector);$i++){
			$response["comercial_".$i]["idUsuario"] = $vector[$i]["idUsuario"];
			$response["comercial_".$i]["login"] = $vector[$i]["login"];
			$response["comercial_".$i]["password"] = $vector[$i]["password"];
		}
		$response["numero_comerciales"] = count($vector);
		echo json_encode($response);
	}else{
		if ($tag == 'vehiculos') {
			$vehiculos = $db->getVehiculos();
			$response["success"] = 1;
			$vector = $vehiculos;
			for($i=0;$i<count($vector);$i++){
				$response["vehiculo_".$i]["idHueco"] = $vector[$i]["idHueco"];
				$response["vehiculo_".$i]["descripcion"] = $vector[$i]["descripcion"];
			}
			$response["numero_vehiculos"] = count($vector);
			echo json_encode($response);
		}
    }
} else {
    echo "Access Denied";
	echo $_POST['tag'];
}
?>
<hr/>