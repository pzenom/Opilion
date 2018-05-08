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
						<fieldset>
							<legend><span>Datos generales</span></legend>
							<br />
							<fieldset>
								<legend><span>Maquina</span></legend>
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
									<s:hidden id="idMaquina" key="idMaquina" name="idMaquina"/>
								</s:iterator>
							</fieldset>
							<br />
							<s:form name="formulario" action="InseRegMT" method="post" validate="true">
								<s:iterator id="mant" value="%{#session.mantenimiento}">
									<table width="100%" cellpadding="2" cellspacing="2" border="0">
										<tr>
											<td class="label"><s:label name="Nombre" value="Nombre" cssStyle="text-align:right; width:130px;"/></td>
											<td>
												<s:textfield key="nombre" name="nombre" cssStyle="text-align:right; width:130px;" disabled="true"/>
											</td>
										</tr>
										<tr>
											<td class="label"><s:label name="Descripcion" value="Descripcion" cssStyle="text-align:right;"/></td>
											<td colspan="3">
												<s:textarea key="descripcion" rows="3" cols="50" disabled="true"/>
											</td>
										</tr>
										<tr>
											<td class="label"><s:label name="Observaciones" value="Observaciones" cssStyle="text-align:right;"/></td>
											<td colspan="3">
												<s:textarea id="observaciones" key="observaciones" rows="3" cols="50"/>
											</td>
										</tr>
										<tr>
											<td class="label"><s:label name="Fecha programada" value="Fecha programada" cssStyle="text-align:right; width:130px;"/></td>
											<td>
												<s:textfield key="fechaProgramada" name="fechaProgramada" cssStyle="text-align:right; width:130px;" disabled="true"/>
											</td>
										</tr>
										
										<tr>
											<td class="label"><s:label name="Fecha realizada" value="Fecha realizada" cssStyle="text-align:right; width:130px;"/></td>
											<td>
												<s:datetimepicker id="fechaRealizadaI" key="fechaRealizada" label="Format (yyyy-MM-dd)" displayFormat="yyyy-MM-dd" disabled="true"/>
												<s:textfield id="fechaRealizadaII" key="fechaRealizada" name="fechaRealizada" cssStyle="text-align:right; width:130px;" disabled="true"/>
											</td>
										</tr>
										<tr>
											<td class="label"><s:label name="Estado" value="Estado" cssStyle="text-align:right; width:130px;"/></td>
											<td>
												<s:textfield id="estado" key="estado" cssStyle="text-align:right; width:130px;" disabled="true"/>
											</td>
										</tr>
										<tr>
											<td class="label"><s:label name="Ciclo" value="Ciclo" cssStyle="text-align:right; width:130px;"/></td>
											<td>
												<s:textfield key="ciclo" cssStyle="text-align:right; width:130px;" disabled="true"/>
											</td>
										</tr>
										<tr>
											<td class="label"><s:label name="Responsable" value="Responsable" cssStyle="text-align:right; width:130px;"/></td>
											<td>
												<s:textfield key="responsable" cssStyle="text-align:right; width:130px;" disabled="true"/>
											</td>
										</tr>
									</table>
										<!-- Si es un calibrado... -->
										<s:if test="%{#mant.calibrado=='Si'}">
											<br />
											<fieldset>
												<table>
													<legend><span>Registro de calibrado</span></legend>
													<tr>
														<td class="label"><s:label name="Patron" value="Patron" cssStyle="text-align:right; width:130px;"/></td>
														<td>
															<s:textfield key="patron" name="patron" cssStyle="text-align:right; width:130px;"/>
														</td>
													</tr>
													<tr>
														<td class="label"><s:label name="Medida patron" value="Medida patron" cssStyle="text-align:right;"/></td>
														<td colspan="3">
															<s:textfield key="medidaPatron" cssStyle="text-align:right; width:130px;"/>
														</td>
													</tr>
													<tr>
														<td class="label"><s:label name="Medida equipo" value="Medida equipo" cssStyle="text-align:right;"/></td>
														<td colspan="3">
															<s:textfield key="medidaEquipo" cssStyle="text-align:right; width:130px;"/>
														</td>
													</tr>
													<tr>
														<td class="label"><s:label name="Desviacion" value="Desviacion" cssStyle="text-align:right;"/></td>
														<td colspan="3">
															<s:textfield key="desviacion" cssStyle="text-align:right; width:130px;"/>
														</td>
													</tr>
													<tr>
														<td class="label"><s:label name="Verificado" value="Verificado" cssStyle="text-align:right;"/></td>
														<td colspan="3">
															<s:checkbox label="checkbox test" id="verifica" name="verifica" key="verifica" value="aBoolean" fieldValue="true" onchange="verificadoCambio()"/>
															<s:hidden id="verificado" name="verificado" key="verificado"/>
														</td>
													</tr>
												</table>
											</fieldset>
										</s:if>
									<p>&nbsp;</p>
									<s:hidden id="idMantenimientoProgramacion" name="idMantenimientoProgramacion" key="idMantenimientoProgramacion"/>
									<s:hidden id="idMaquina" name="idMaquina" key="idMaquina"/>
									<s:hidden id="idMantenimiento" name="idMantenimiento" key="idMantenimiento"/>
									<s:hidden id="idCiclo" name="idCiclo" key="idCiclo"/>
									<s:hidden id="idCalibrado" name="idCalibrado" key="idCalibrado"/>
								</s:iterator>
							</s:form>
							<script type="text/javascript">function submitform() { document.forms["formulario"].submit(); }</script>
							<div id="boton" style="position: relative; float: left;">
								<a id="bot_inserta" class="botonInserta" href="javascript:submitform();" title="Insertar">
									<img src="img/j_button1_add.png" alt="Insertar " title="Insertar"/>Insertar
								</a>
							</div>
							&nbsp;
							<a class="botonInserta" id="bot_back" href="#" title="Regresar">
								<img src="img/j_button_back.png" alt="Boton con una flecha indicando el regreso a la p?gina anterior" title="Regresar"/>Regresar
							</a>
						</fieldset>
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
<script type="text/javascript" src="mantenimiento/registrarMT.js"></script>
</body>
</html>