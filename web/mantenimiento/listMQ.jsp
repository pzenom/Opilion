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
					<div id="tabledata" class="section">
						<h3 class="ico_posts">Consulta Listado de M&aacute;quinas</h3>
						<s:form name="formulario" action="ConsMQ" validate="true">
							<fieldset><!-- Filtro Campos-->
								<legend><span><s:text name='mantenimiento.criterios' /></span></legend>
								<table width="100%" cellpadding="2" cellspacing="2" border="0">
									<tr>
										<td class="label"><s:label name="Tipo maquinas" value="Tipo maquinas" cssStyle="text-align:right; width:130px;"/></td>
										<td nowrap="nowrap">
											<s:select key="idTipoMaquinas" cssStyle="width:180px;" label="%{getText('entry.producto')}" list="%{#session.listaTiposMaquinas}" listKey="idTipo" listValue="descripcion" headerKey="0" headerValue="--Tipo--" cssStyle="text-align:left;" onchange="seleccionarTipoMaquinas()"/>
										<s:hidden id="idTipo" key="idTipo" value="0"/>
										</td>
										<td class="label"><s:label name="%{getText('crenv.fecha')}" value="%{getText('crenv.fecha')}" cssStyle="text-align:right;"/></td>
										<td nowrap="nowrap">
										<s:datetimepicker name="fechaConsulta" key="fechaConsulta" label="Format (yyyy-MM-dd)" displayFormat="yyyy-MM-dd"/>
										</td>
									</tr>
								</table>
								<p>
									<a class="botonInserta" id="actualiza" href="javascript:filtrar()">
										<img src="img/j_button_busca.png" alt="Consultar" title="Consultar"/>
										Consultar
									</a>&nbsp;
									<!-- <a class="botonInserta" id="pdf" href="ConsMTJR.action">
										<img src="img/j_button_pdf.png" alt="Ver en PDF" title="Ver en PDF"/>
										Ver en PDF
									</a>&nbsp; -->
									<a class="botonInserta" id="pdf" href="javascript:print()">
										<img src="img/j_button_print.png" alt="Imprimir" title="Imprimir"/>
										Imprimir
									</a>
								</p>
							</fieldset><!--end product info-->
						</s:form>
						<div id="demo">
							<table id="tablaMaquinas" cellpadding="0" cellspacing="0" border="0" class="display">
								<thead>
									<tr>
										<th>Id</th>
										<th>Nombre</th>
										<th>Tipo de maquina</th>
										<th>Fecha</th>
										<th>Descripcion</th>
										<!-- <th>Acciones</th> -->
									</tr>
								</thead>
								<tbody>
									<s:iterator id="mant" value="%{#session.listaMaquinas}">
									<tr>
										<td><s:property value="idMaquina" /></td>
										<td><s:property value="nombre" /></td>
										<td><s:property value="descripcionTipo" /></td>
										<td><s:property value="fecha" /></td>
										<td><s:property value="descripcion" /></td>
										<td style="text-align: center;">
											<a title="Ver mantenimientos asociados a la m&aacute;quina" href="FiltMTMaquina.action?idMaquina=<s:property value="idMaquina" />"><img src="img/bombilla_01.gif" alt="Ver mantenimientos asociados a la m&aacute;quina" title="Ver mantenimientos asociados a la m&aacute;quina"/></a>
										</td>
										<!-- <td style="text-align: center;">
											<a title="Asociar mantenimiento a la m&aacute;quina" href="EditMT.action?idMaquina=<s:property value="idMaquina" />"><img src="img/ico_settings.png" alt="Asociar mantenimiento a la m&aacute;quina" title="Asociar mantenimiento a la m&aacute;quina"/></a>
										</td> -->
										<td style="text-align: center;">
											<a title="Asociar mantenimiento a la m&aacute;quina" href="ProgramarMT.action?idMaquina=<s:property value="idMaquina" />"><img src="img/ico_settings.png" alt="Asociar mantenimiento a la m&aacute;quina" title="Asociar mantenimiento a la m&aacute;quina"/></a>
										</td>
									</tr>
									</s:iterator>
								</tbody>
							</table>
						</div> <!-- demo -->
					</div> <!-- end #tabledata -->
				</div><!-- end #dashboard -->
			</div><!-- end #main_panel_container-->
			<jsp:include page="../includes/menumaquinaria.jsp" flush="true" />
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	<jsp:include page="../includes/pie.jsp" flush="true" />
	</s:i18n>
</div><!-- end container -->
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="mantenimiento/listMQ.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.dateSorting.js"></script>
</body>
</html>