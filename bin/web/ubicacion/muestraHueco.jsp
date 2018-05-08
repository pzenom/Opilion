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
			<a href="pagEscritorio.jsp" id="visit" class="right">Home</a>
		</div><!-- end top menu -->	
		<div id="content_main" class="clearfix">
			<div id="main_panel_container" class="left">
				<div id="dashboard" style="height: auto;">
					<div id="tabledata" class="section">
						<p class="idPantalla">UBICA</p>
						<h3 class="ico_posts">Ubicacion del hueco seleccionado</h3>
					</div> <!-- end #tabledata -->
				</div><!-- end #dashboard -->
			</div><!-- end #main_panel_container-->
			<jsp:include page="../includes/menuenvasado.jsp" flush="true" />
		</div><!-- end #content_main -->
	</div><!-- end #content -->
  <div id="footer" class="clearfix">
		<p>
			<a href=""><img src="img/connect.png" alt="statistics"/>&nbsp;Conexion con el servidor</a>&nbsp;
			<a href=""><img src="img/note.png" alt="statistics"/>&nbsp;7</a>&nbsp;
			<a href=""><img src="img/clock.png" alt="statistics"/>&nbsp;17:32</a>
		</p>
	</div><!-- end #footer -->
	</s:i18n>	
</div><!-- end container -->
<script type="text/javascript" src="ubicacion/seleccionUbicacionesEnvasado.js"></script>
</body>
</html>