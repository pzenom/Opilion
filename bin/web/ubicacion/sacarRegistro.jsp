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
				<div id="dashboard">
					<div class="section">
						<p class="idPantalla">SELEC_ALMA</p>
						<h3 class="ico_ubi">Seleccion de almacen</h3>
						<fieldset>
							<legend><span>Almacen</span></legend>
							<!--<table class="tabla" width="100%" cellpadding="2" cellspacing="2" border="0">
								<thead>
									<tr>
										<th>Id</th>
										<th>Descripcion</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>-->
							<s:form id="almac" name="almac" action="UbicarPalet.action" validate="true" method="post">
								<s:select key="almacenes" id="almacenes" list="%{#session.almacenes}" onchange="seleccionaAlmacen();" listKey="idTipoUbicacion" listValue="nombre" headerKey="0" headerValue="--Ubicación--" cssStyle="text-align:left; width:140px;"/>
								<!-- <s:hidden id="idTipoUbicacion" key="idTipoUbicacion" name="idTipoUbicacion"/>
								<s:hidden id="sacar" key="sacar" name="sacar" value="true"/>
								<s:hidden id="mover" key="mover" name="mover" value="%{#session.mover}"/> -->
							</s:form>
						</fieldset>
						<a id="salvarDatos" class="botonInserta" href="#" onclick="almacenSeleccionado()">
							<img title="Confirmar ubicacion" alt="Confirmar ubicacion" src="img/j_button1_add.png">
							Seleccionar almacen
						</a>
					</div><!-- end #section -->	
				</div><!-- end #dashboard -->
			</div><!-- end #main_panel_container-->
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	<jsp:include page="../includes/pie.jsp" flush="true" />
	</s:i18n>
</div><!-- end container -->
<script type="text/javascript" src="ubicacion/sacarRegistro.js"></script>
</body>
</html>