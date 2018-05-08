<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="includes/head.jsp" flush="true" />
<body>
<div class="container" id="container">
	<s:i18n name="ApplicationResources">
	<jsp:include page="includes/header.jsp" flush="true" />
	<div id="content" >
		<div id="top_menu" class="clearfix">
			<jsp:include page="includes/menu.jsp" flush="true" />
			<a href="pagEscritorio.jsp" id="visit" class="right">Home</a>
		</div><!-- end top menu -->
	<div id="content_main" class="clearfix">
			<div id="main_panel_container" class="left">
			<div id="dashboard">
			<div id="tabledata" class="section">
				<h2 class="ico_mug"><s:text name="confexit.bienvenida" /></h2> 
				  <h2><b>Operacion Realizada con Exito</b></h2>
				  <br>
				  <a href="ConsultaRegistroSalida.action?fecha=<s:property value="%{#session.fecharegistro}" />"><img src="img/accept.jpg" alt="Consulta Registro" />Listado Registro Salida</a>
			</div> <!-- end #tabledata -->
		</div><!-- end #dashboard -->
		</div><!-- end #main_panel_container-->
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	<jsp:include page="includes/pie.jsp" flush="true" />
	</s:i18n>
</div><!-- end container -->
</body>
</html>