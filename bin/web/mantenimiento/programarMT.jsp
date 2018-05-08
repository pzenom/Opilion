<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
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
				<div id="dashboard">
					<div class="section">
						<h3 class="ico_posts">Registro de mantenimiento</h3>
							<!-- onsubmit="javascript:window.opener.location.reload();">-->
								<fieldset>
								<s:form target="ajaxDiv" id="formuAjax" name="formuAjax" action="MantenimientoAjax" validate="true" method="get">
									<s:select key="idMaquinas" cssStyle="width:180px;" label="Maquina" list="%{#session.listaMaquinas}" listKey="idMaquina" listValue="nombre" headerKey="0" headerValue="--Maquina--" cssStyle="text-align:left;" onchange="seleccionarMaquina();" />
									<s:hidden id="idMaquina" name="idMaquina" key="idMaquina" value=""/>
									<s:submit theme="ajax" targets="ajaxDiv" value="Cargar maquina"/>
								</s:form>
								<legend><span>Datos Generales</span></legend>
									<!-- AQUI EL DIV DE AJAX -->
									<sx:div name="ajax" id="ajaxDiv" theme="ajax">
									</sx:div>
									<br />
									<script type="text/javascript">function submitform() { document.forms["formulario"].submit(); }</script>
									<a id="bot_inserta" class="botonInserta" href="javascript:submitform();" title="Insertar">
										<img src="img/j_button1_add.png" alt="Insertar " title="Insertar"/>Insertar
									</a>
								</fieldset>
								<s:form name="formulario" action="InseMQ" validate="true">
								</s:form>
					</div><!-- end #section -->	
				</div><!-- end #dashboard -->
			</div><!-- end #main_panel_container-->
			<jsp:include page="../includes/menumantenimientos.jsp" flush="true" />
		</div><!-- end #content_main -->
		<jsp:include page="../includes/pie.jsp" flush="true" />
	</div><!-- end #content -->
	</s:i18n>
</div><!-- end container -->
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="mantenimiento/programarMT.js"></script>
</body>
</html>