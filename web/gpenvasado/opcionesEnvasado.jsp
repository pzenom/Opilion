<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../includes/head.jsp" flush="true" />
<body>
<div class="container" id="container">
	<s:i18n name="ApplicationResources">
	<jsp:include page="../includes/header.jsp" flush="true" />
	<div id="content" >
	  <div id="top_menu" class="clearfix">
			<jsp:include page="../includes/menu.jsp" flush="true" />
			<a href="pagEscritorio.jsp" id="visit" class="right"><s:text name="general.home"/></a>
	  </div><!-- end top menu -->
		<div id="content_main" class="clearfix">
			<div id="main_panel_container" class="left">
			
			<div id="shortcuts" class="clearfix">
				<h2 class="ico_settings"><s:text name="opcionesEnvasado.titulo"/></h2>
				<ul>
				  <li><a href="ConsProdFina.action"><img src="img/ico_proceso.png" height="48" width="48" alt="Nuevo proceso de envasado" title="Nuevo proceso de envasado" /><span><s:text name="opcionesEnvasado.alta"/></span></a></li>
					<li><a href="ListaEnvasar.action"><img src="img/icon_envasado.png" height="48" width="48" alt="Ir a proceso de envasado" title="Ir a proceso de envasado" /><span><s:text name="opcionesEnvasado.envasar"/></span></a></li>
					<li><a href="FiltGPEnva.action"><img src="img/icon_albaran.png" height="48" width="48" alt="Listar todos los procesos de envasado" title="Listar todos los procesos de envasado" /><span><s:text name="opcionesEnvasado.listar"/></span></a></li>
				</ul>
			</div><!-- end #shortcuts -->
			
			<jsp:include page="../includes/menuenvasado.jsp" flush="true" />
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	<jsp:include page="../includes/pie.jsp" flush="true" />
	</s:i18n>	
</div><!-- end container -->
</body>
</html>