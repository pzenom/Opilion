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
			<a href="pagEscritorio.jsp" id="visit" class="right"><s:text name="general.Home" /></a>
		</div><!-- end top menu -->
		<div id="content_main" class="clearfix">
			<div id="main_panel_container" class="left">
		<div id="dashboard">
			<div id="tabledata" class="section">
				<h3 class="ico_posts"><s:text name='mantenimiento.consulta' /></h3>
				<br />
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
					</s:iterator>
					<div id="demo">
						<table id="tablaMantenimientos" cellpadding="0" cellspacing="0" border="0" class="display">
							<thead>
								<tr>
									<th>Id</th>
									<th>Nombre</th>
									<th>Descripcion</th>
									<th>Observaciones</th>
									<th>Fecha programada</th>
									<th>Estado</th>
									<th>Calibracion</th>
									<th>Ciclo</th>
									<th>Responsable</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator id="mant" value="%{#session.listaMantenimientos}" status="itStatus">
									<tr>
										<td><s:property value="#itStatus.count" /></td>
										<td><s:property value="nombre" /></td>
										<td><s:property value="descripcion" /></td>
										<td><s:property value="observaciones" /></td>
										<td><s:property value="fechaProgramada" /></td>
										<td><s:property value="estado" /></td>
										<td><s:property value="calibrado" /></td>
										<td><s:property value="ciclo" /></td>
										<td><s:property value="responsable" /></td>
										<!-- Si es un calibrado... -->
										<s:if test="%{#mant.estado=='P'}">
											<td style="text-align: center;">
												<a title="Programar mantenimiento" href="RegistrarMT.action?idMantenimientoProgramacion=<s:property value="idMantenimientoProgramacion" />&idMaquina=<s:property value="idMaquina" />"><img src="img/pill_go.png" alt="Programar mantenimiento" title="Programar mantenimiento"/></a>
											</td>
										</s:if>
										<s:else>
											<td style="text-align: center;">
												<a title="Visualizar mantenimiento" href="RegistrarMT.action?idMantenimientoProgramacion=<s:property value="idMantenimientoProgramacion" />&idMaquina=<s:property value="idMaquina" />"><img src="img/zoom.png" alt="Visualizar mantenimiento" title="Visualizar mantenimiento"/></a>
											</td>
										</s:else>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div> <!-- demo -->
			</div> <!-- end #tabledata -->
		</div><!-- end #dashboard -->
		</div><!-- end #main_panel_container-->
		<jsp:include page="../includes/menumantenimientos.jsp" flush="true" />
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	<jsp:include page="../includes/pie.jsp" flush="true" />
	</s:i18n>
</div><!-- end container -->
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="mantenimiento/consMTMaquina.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.dateSorting.js"></script>
</body>
</html>