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
						<h3 class="ico_posts">Registro de Maquinaria</h3>
							<s:form name="formulario" action="InseMQ" method="post" validate="true"><!-- onsubmit="javascript:window.opener.location.reload();">-->
								<fieldset>
								<legend><span>Datos Generales</span></legend>
									<table width="100%" cellpadding="2" cellspacing="2" border="0">
										<tr>
											
											<td class="label"><s:label name="Nombre" value="Nombre" cssStyle="text-align:right; width:130px;"/></td>
											<td>
												<s:textfield key="nombre" cssStyle="text-align:right; width:130px;"/>
											</td>
										</tr>
										
										<!-- <tr>
											<td class="label"><s:label name="Tipo Mantenimiento" value="Tipo Mantenimiento" cssStyle="text-align:right; width:130px;"/></td>
											<td>
												<s:checkboxlist name="listaTM" list="%{#session.listatm}" listKey="idTipoMant" listValue="nombre" theme="simple" cssStyle="text-align:right; width:130px;"/>
											</td>
										</tr> -->
										<tr>
											<td class="label"><s:label name="Descripcion" value="Descripcion" cssStyle="text-align:right;"/></td>
											<td colspan="3">
												<s:textarea key="descripcion" rows="3" cols="50" />
											</td>
										</tr>
										<tr>
											<!-- <td class="label"><s:label name="%{getText('crenv.fecha')}" value="%{getText('crenv.fecha')}" cssStyle="text-align:right;"/></td>
											<td nowrap="nowrap">
												<s:datetimepicker name="fechaConsulta" key="fechaConsulta" label="Format (yyyy-MM-dd)" displayFormat="yyyy-MM-dd"/>
											</td> -->
											<td class="label"><s:label name="Tipo maquina" value="Tipo maquina" cssStyle="text-align:right; width:130px;"/></td>
											<td nowrap="nowrap">
												<s:select key="idTipoMaquinas" cssStyle="width:180px;" label="%{getText('entry.producto')}" list="%{#session.listaTiposMaquinas}" listKey="idTipo" listValue="descripcion" headerKey="0" headerValue="--Tipo--" cssStyle="text-align:left;" onchange="seleccionarTipoMaquinas()"/>
												<s:hidden id="idTipo" key="idTipo" value="0"/>
											</td>
										</tr>
									</table>
									<br />
									<a id="bot_inserta" class="botonInserta" href="javascript:submitform();" title="Insertar">
										<img src="img/j_button1_add.png" alt="Insertar " title="Insertar"/>Insertar
									</a>
								</fieldset>
							</s:form>
					</div><!-- end #section -->	
				</div><!-- end #dashboard -->
			</div><!-- end #main_panel_container-->
			<jsp:include page="../includes/menumaquinaria.jsp" flush="true" />
		</div><!-- end #content_main -->
		<jsp:include page="../includes/pie.jsp" flush="true" />
	</div><!-- end #content -->
	</s:i18n>
</div><!-- end container -->
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="mantenimiento/editMQ.js"></script>
</body>
</html>