<?php

class DB_Functions {

    private $db;

    //put your code here
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->db->connect();
    }

    // destructor
    function __destruct() {
    }

	public function getComerciales(){
		$sth = mysql_query("SELECT u.idUsuario, u.login, u.password FROM usuario u, usuario_rol ur WHERE u.idUsuario = ur.idUsuario AND ur.idRol = 5 ORDER BY login asc; ");
		$rows = array();
		while($r = mysql_fetch_assoc($sth)) {
			$rows[] = $r;
		}
		return $rows;
	}
	
	public function getVehiculos(){
		$sth = mysql_query(" SELECT uh.idHueco, ua.descripcion FROM ubicacion_hueco uh, ubicacion_piso up, ubicacion_estanteria ue, ubicacion_linea ul, ubicacion_zona uz, ubicacion_almacen ua WHERE ua.esVehiculo = 'S' AND ua.idAlmacen = uz.idAlmacen AND uz.idZona = ul.idZona AND ul.idLinea = ue.idLinea AND ue.idEstanteria = up.idEstanteria AND up.idPiso = uh.idPiso; ");
		$rows = array();
		while($r = mysql_fetch_assoc($sth)) {
			$rows[] = $r;
		}
		return $rows;
	}
}
?>