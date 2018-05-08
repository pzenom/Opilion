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
	<div class="section">
		<h3 class="ico_ubi">Crear zona de ubicaci&oacute;n</h3>
		  <s:form name="formulario" action="IngresarUbicacion" validate="true">
				<fieldset>
				<legend><span>Datos Generales</span></legend>
					<table width="100%" cellpadding="2" cellspacing="2" border="0">
						<tr>
							<td class="label"><s:label name="%{getText('registro.label.responsable')}" value="%{getText('registro.label.responsable')}"/></td>
							<td nowrap="nowrap">
								<s:textfield key="responsable" label="%{getText('registro.label.responsable')}" value="%{#session.usuario.login}" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td class="label"><s:label name="%{getText('ubicacion.label.nave')}" value="%{getText('ubicacion.label.nave')}"/></td>
							<td nowrap="nowrap">
								<s:select key="idTipoUbicacion" cssStyle="width:180px;" list="%{#session.listatipoubicacion}" listKey="idTipoUbicacion" listValue="idTipoUbicacion+' '+nombre" headerKey="0" headerValue="Seleccione tipo ubicacion" cssStyle="width:180px;text-align:left;"/>
							</td>
						</tr>
						<tr>
							<td class="label"><s:label name="%{getText('ubicacion.label.zona')}" value="%{getText('ubicacion.label.zona')}"/></td>
							<td nowrap="nowrap">
								<s:select key="idZona" label="%{getText('ubicacion.label.zona')}" list="%{#session.listaubicacionzonas}" listKey="idZona" listValue="nombre" headerKey="0" headerValue="Seleccione una zona" cssStyle="width:180px;text-align:left;"/>
							</td>
						</tr>
						<tr>
							<td class="label"><s:label name="%{getText('ubicacion.label.pasillo')}" value="%{getText('ubicacion.label.pasillo')}"/></td>
							<td nowrap="nowrap">
								<s:select key="pasillo" label="%{getText('ubicacion.label.pasillo')}" list="%{#session.listapasillos}" listKey="pasillo" listValue="pasillo" headerKey="0" headerValue="Seleccione un pasillo" cssStyle="width:180px;text-align:left;"/>
							</td>
						</tr>
						<tr>
							<td class="label"><s:label name="%{getText('ubicacion.label.estanteria')}" value="%{getText('ubicacion.label.estanteria')}"/></td>
							<td nowrap="nowrap">
								<s:select key="estanteria" label="%{getText('ubicacion.label.estanteria')}" list="%{#session.listaestanterias}" listKey="estanteria" listValue="estanteria" headerKey="0" headerValue="Seleccione una estanteria" cssStyle="width:180px;text-align:left;"/>
							</td>
						</tr>
						<tr>
							<td class="label"><s:label name="%{getText('ubicacion.label.altura')}" value="%{getText('ubicacion.label.altura')}"/></td>
							<td nowrap="nowrap">
								<s:select key="altura" label="%{getText('ubicacion.label.altura')}" list="%{#session.listaalturas}" listKey="altura" listValue="altura" headerKey="0" headerValue="Seleccione una altura" cssStyle="width:180px;text-align:left;"/>
							</td>
						</tr>
						<tr>
							<td class="label"><s:label name="%{getText('ubicacion.label.nombre')}" value="%{getText('ubicacion.label.nombre')}"/></td>
							<td nowrap="nowrap">
								<s:textfield key="nombre" label="%{getText('ubicacion.label.nombre')}"/>
							</td>
						</tr>
						<tr>
							<td class="label"><s:label name="%{getText('ubicacion.label.dimensiones')}" value="%{getText('ubicacion.label.dimensiones')}"/></td>
							<td nowrap="nowrap">
								<s:textfield key="dimensiones" label="%{getText('ubicacion.label.dimensiones')}"/>
							</td>
						</tr>
						<tr>
							<td class="label"><s:label name="%{getText('ubicacion.label.descripcion')}" value="%{getText('ubicacion.label.descripcion')}"/></td>
							<td nowrap="nowrap" colspan="3">
								<s:textarea key="descripcion" label="%{getText('ubicacion.label.descripcion')}"rows="3" cols="40" />
							</td>
						</tr>
					</table>
				</fieldset>
				<script type="text/javascript">function submitform() { document.forms["formulario"].submit(); }</script>
				<a id="bot_inserta" class="botonInserta" href="javascript:submitform();" title="Insertar">
					<img src="img/j_button1_add.png" alt="Insertar " title="Insertar"/>Insertar
				</a>
			</s:form>	
	</div><!-- end #section -->	
	</div><!-- end #dashboard -->
	</div><!-- end #main_panel_container-->
			<jsp:include page="../includes/menuubicacion.jsp" flush="true" />
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	<jsp:include page="../includes/pie.jsp" flush="true" />
	</s:i18n>
</div><!-- end container -->
</body>
</html>