<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>Tierrina Vaqueira ERP</title>

  <!-- ** CSS ** -->
  <!-- base library -->
  <link rel="stylesheet" type="text/css" href="js/css/ext-all.css" />

  <!-- page specific -->
	<link rel="stylesheet" type="text/css" media="all" href="css/dashboard.css" />
	
  <!-- ** Javascript ** -->
  <!-- ExtJS library: base/adapter -->
  <script type="text/javascript" src="js/ext-base.js"></script>

  <!-- ExtJS library: all widgets -->
  <script type="text/javascript" src="js/ext-all.js"></script>

	<!-- dashboard specific -->
	<script type="text/javascript" src="js/dashboard/functions.js"></script>
	<script type="text/javascript" src="js/dashboard/layout-browser.js"></script>
	<script type="text/javascript" src="js/dashboard/base.js"></script>

	
</head>
<body>
	<jsp:include page="js/includes/header.jsp" flush="true" />
  <div id="content" <!--style="display:none;"-->
    <!-- Start page content -->
    <div id="inicio-div">
      <div style="float:left;" ><img src="images/logo_tierrina.png" /></div>
      <div style="margin-left:100px;">
        <h2>Bienvenido</h2>
        <p>Este es el ERP empresarial de Tierrina Vaqueira para la gesti&oacute;n integral de la empresa</p>
        <p>Selecciona una opci&oacute;n de la izquierda para comenzar a trabajar</p>
      </div>
    </div><!-- inicio-div -->
  </div><!-- content -->
</body>
</html>
