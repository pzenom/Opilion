<%@ page contentType="text/html; charset=utf-8"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,
	es.induserco.opilion.data.entidades.*"%>
<!doctype html>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html lang="es-ES">
<head>

	<!-- <@ page import="org.ajaxanywhere.AAUtils"%>
	<@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa" %> -->
	
	<!-- <script src="js/aa.js"></script> -->
	
	<!-- Metadatos -->
	<meta charset="utf-8">
	<meta name="description" content="Sistema de Administracion"/>
	<meta name="keywords" content="Opilion,trazabilidad,agroalimentaria" />
	<meta name="author" content="Induserco" />
	<link rel="shortcut icon" href="img/favicon.ico" />
	<!-- Hojas de estilo -->
	<!-- Google Font and style definitions -->
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=PT+Sans:regular,bold">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/opilion/theme.css" id="themestyle">
	<link rel="stylesheet" href="http://jquery.bassistance.de/treeview/jquery.treeview.css" type="text/css" />
	
	<!-- JavaScript -->
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-migrate-1.1.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.10.2.custom.js"></script>
	<script type="text/javascript" src="js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="js/md5-min.js"></script>
	<script type="text/javascript" src="js/funciones.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
		google.load('visualization', '1.0', {'packages':['corechart']});
	</script>	

	<script type="text/javascript" src="js/jquery.treeview.js"></script>
	<!-- some basic functions -->
	<script type="text/javascript" src="js/menu_slider.js"></script>
	<script src="js/functions.js"></script>
	<!-- all Third Party Plugins -->
	<script src="js/plugins.js"></script>
	<script src="js/editor.js"></script>
	<script src="js/fullcalendar.js"></script>
	<script src="js/flot.js"></script>
	<script src="js/elfinder.js"></script>
	<script src="js/datatables.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.dateSorting.js"></script>
	<!-- all Whitelabel Plugins -->
	<script src="js/wl_Alert.js"></script>
	<script src="js/wl_Autocomplete.js"></script>
	<script src="js/wl_Breadcrumb.js"></script>
	<script src="js/wl_Calendar.js"></script>
	<script src="js/wl_Chart.js"></script>
	<script src="js/wl_Color.js"></script>
	<script src="js/wl_Date.js"></script>
	<script src="js/wl_Editor.js"></script>
	<script src="js/wl_File.js"></script>
	<script src="js/wl_Fileexplorer.js"></script>
	<script src="js/wl_Form.js"></script>
	<script src="js/wl_Gallery.js"></script>
	<script src="js/wl_Multiselect.js"></script>
	<script src="js/wl_Number.js"></script>
	<script src="js/wl_Password.js"></script>
	<script src="js/wl_Slider.js"></script>
	<script src="js/wl_Store.js"></script>
	<script src="js/wl_Time.js"></script>
	<script src="js/wl_Widget.js"></script>
	<script src="js/wl_Valid.js"></script>
	<script src="js/wl_Dialog.js"></script>
	
	<!-- configuration to overwrite settings -->
	<script src="js/config.js"></script>
	<!-- the script which handles all the access to plugins etc... -->
	<script src="js/script.js"></script>
	<!-- plugin para subir ficheros mediante ajax -->
	<script src="js/ajaxfileupload.js"></script>
	<!-- TinyMCE -->
	<script type="text/javascript" src="js/plugins/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
	
	<!-- Struts-labels -->		
	<s:i18n name="ApplicationResources">
		<title><s:text name="index.web"/></title>
	</s:i18n>
</head>