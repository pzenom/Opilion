<%@ page contentType="text/html; charset=UTF-8"%>
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
					<div id="tabledata" class="section">
						<h3 class="ico_ubi">Consultar ubicaciones</h3>
						<s:form name="formulario" action="ConsultaUbicacion" validate="true">
							<fieldset><!-- Informacion Basica-->
								<legend><span><s:label name="%{getText('registro.label.datosconsulta')}" value="%{getText('registro.label.datosconsulta')}"/></span></legend>
								<table width="40%" cellpadding="2" cellspacing="2" border="0">
									<tr>
										<td class="label"><s:label name="%{getText('ubicacion.busqueda.zona')}" value="%{getText('ubicacion.busqueda.zona')}"/></td>
										<td nowrap><s:select key="idZona" label="%{getText('ubicacion.busqueda.zona')}" list="%{#session.listaubicacionzonas}" listKey="idZona" listValue="nombre" headerKey="0" headerValue="Seleccione una Zona"/></td>
									</tr>
								</table>
								<script type="text/javascript">function submitform() { document.forms["formulario"].submit(); }</script>
									<a id="bot_inserta" class="botonInserta" href="javascript:submitform();" title="Actualizar">
										<img src="img/j_button1_add.png" alt="Actualizar " title="Actualizar"/>Actualizar
									</a>
							</fieldset><!--end Informacion Basica-->
						</s:form>
						<div id="demo">
							<table id="tablaUbicaciones" cellpadding="0" cellspacing="0" border="0" class="display">
								<thead>
									<tr>
										<th>ID</th>
										<th>Nombre Zona</th>
										<th>Pasillo</th>
										<th>Estanter&iacute;a</th>
										<th>Altura</th>
									</tr>
								</thead>
								<tbody>
										<s:iterator id="ubicacion" value="%{#session.listaubicaciones}">
											<tr>
												<td><s:property value="idUbicacion" /></td>
												<td><s:property value="nombre" /></td>
												<td><s:property value="pasillo" /></td>
												<td><s:property value="estanteria" /></td>
												<td><s:property value="altura" /></td>
												<td>
													<a href="CargarUbicacion.action?idUbicacion=<s:property value="idUbicacion" />"><img src="img/edit.png" alt="edit"/></a>
												</td>
											</tr>
										</s:iterator>
								</tbody>
							</table>
						</div> <!-- demo -->
					</div> <!-- end #tabledata -->
				</div><!-- end #dashboard -->
			</div><!-- end #main_panel_container-->
			<jsp:include page="../includes/menuubicacion.jsp" flush="true" />
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	<jsp:include page="../includes/pie.jsp" flush="true" />
	</s:i18n>
</div><!-- end container -->
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="ubicacion/consultaUbicacion.js"></script>
</body>
</html>