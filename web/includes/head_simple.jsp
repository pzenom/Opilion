<%@ page contentType="text/html; charset=utf-8"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<!doctype html>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html lang="es-ES">
<head>
	<%@ taglib uri="/struts-tags" prefix="s"%>
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
	<!-- include the skins (change to dark if you like) -->
	<link rel="stylesheet" href="css/opilion/theme.css" id="themestyle">
	
	<!-- JavaScript -->
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-migrate-1.1.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.10.2.custom.js"></script>
	
	<!-- Struts-labels -->
	<s:i18n name="ApplicationResources">
		<title><s:text name="index.login"/></title>
	</s:i18n>
	<sx:head/>
</head>