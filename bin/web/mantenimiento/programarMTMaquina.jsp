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
						<h3 class="ico_posts">Registro de mantenimiento</h3>
							<!-- onsubmit="javascript:window.opener.location.reload();">-->
								<fieldset>
								<s:form name="formulario" action="InseMT" method="post" validate="true">
								<legend><span>Datos Generales</span></legend>
								<s:iterator id="mant" value="%{#session.maquina}">
									<table width="100%" cellpadding="2" cellspacing="2" border="0">
										<tr>
											<td class="label"><s:label name="Id maquina" value="Id maquina" cssStyle="text-align:right; width:130px;"/></td>
											<td>
												<s:textfield key="idMaquina" name="idMaquina" cssStyle="text-align:right; width:130px;" disabled="true"/>
											</td>
										</tr>
										<tr>
											<td class="label"><s:label name="Nombre" value="Nombre" cssStyle="text-align:right; width:130px;"/></td>
											<td>
												<s:textfield key="nombre" cssStyle="text-align:right; width:130px;" disabled="true"/>
											</td>
										</tr>
										<tr>
											<td class="label"><s:label name="Tipo de maquina" value="Tipo de maquina" cssStyle="text-align:right; width:130px;"/></td>
											<td>
												<s:textfield key="descripcionTipo" cssStyle="text-align:right; width:130px;" disabled="true"/>
												<s:hidden id="idTipo" name="idTipo" key="idTipo"/>
											</td>
										</tr>
										<tr>
											<td class="label"><s:label name="Descripcion" value="Descripcion" cssStyle="text-align:right;"/></td>
											<td colspan="3">
												<s:textarea key="descripcion" rows="3" cols="50" disabled="true"/>
											</td>
										</tr>
										<tr>
											<td class="label"><s:label name="Fecha de registro" value="Fecha de registro" cssStyle="text-align:right; width:130px;"/></td>
											<td>
												<s:textfield key="fecha" cssStyle="text-align:right; width:130px;" disabled="true"/>
											</td>
										</tr>
									</table>
									<p>&nbsp;</p>
									<!--<ss:form theme="ajax" target="ajaxDiv" id="formuAjax" name="formuAjax" action="MantenimientoMaquinaAjax" validate="true" method="get">-->
										<s:label name="Tipo de mantenimiento" value="Tipo de mantenimiento"/>
										<s:select key="idMantenimientos" cssStyle="width:180px;" label="Mantenimientos" list="%{#session.listaMantenimientos}" listKey="idMantenimiento" listValue="nombre+': '+descripcion" headerKey="0" headerValue="--Mantenimiento--" cssStyle="text-align:left;" onchange="seleccionarMantenimiento();" />
										<s:hidden id="idMantenimiento" name="idMantenimiento" key="idMantenimiento" value=""/>
										<s:hidden id="idMaquina" name="idMaquina" key="idMaquina"/>
										<s:submit theme="ajax" targets="ajaxDiv" value="Seleccionar mantenimiento" cssStyle="visibility:hidden;"/>
									<!--</ss:form>-->
								</s:iterator>
								<!-- AQUI EL DIV DE AJAX -->
								<!--<ss:div name="ajax" id="ajaxDiv" theme="ajax">
								</ss:div>-->
								<div id="tablaCiclo">
									<table width="100%" cellpadding="2" cellspacing="2" border="0">
										<p>&nbsp;</p>
										<tr>
											<td class="label"><s:label name="Fecha programada" value="Fecha programada" cssStyle="text-align:right;"/></td>
											<td nowrap="nowrap">
												<s:datetimepicker id="fechaProgramada" key="fechaProgramada" label="Format (yyyy-MM-dd)" displayFormat="yyyy-MM-dd"/>
											</td>
										</tr>
										<tr>
											<td class="label"><s:label name="Ciclo" value="Ciclo" cssStyle="text-align:right;"/></td>
											<td nowrap="nowrap">
												<s:select key="idCiclos" cssStyle="width:180px;" label="Ciclos" list="%{#session.listaCiclos}" listKey="idCiclo" listValue="descripcion" headerKey="0" headerValue="--Ciclo--" cssStyle="text-align:left;" onchange="seleccionarCiclo();" />
												<s:hidden id="idCiclo" name="idCiclo" key="idCiclo"/>
											</td>
										</tr>
										<tr>
											<td class="label"><s:label name="Calibrado" value="Calibrado" cssStyle="text-align:right; width:130px;"/></td>
											<td>
												<s:checkbox label="checkbox test" id="calibrado" name="calibrado" key="calibrado" value="aBoolean" fieldValue="true" onchange="calibradoCambio()"/>
												<s:hidden id="idCalibrado" name="idCalibrado" key="idCalibrado"/>
											</td>
										</tr>
										<tr>
											<td class="label"><s:label name="Observaciones" value="Observaciones" cssStyle="text-align:right;"/></td>
											<td colspan="3">
												<s:textarea key="observaciones" rows="3" cols="50"/>
											</td>
										</tr>
									</table>
								</div> <!-- TABLA CICLO -->
								<br />
								<script type="text/javascript">function submitform() { document.forms["formulario"].submit(); }</script>
								<a id="bot_inserta" class="botonInserta" href="javascript:submitform();" title="Insertar">
									<img src="img/j_button1_add.png" alt="Insertar " title="Insertar"/>Insertar
								</a>
								</fieldset>							
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
<script type="text/javascript" src="mantenimiento/programarMTMaquina.js"></script>
</body>
</html>