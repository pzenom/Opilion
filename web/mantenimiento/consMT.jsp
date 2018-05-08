<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name='mantenimiento.consulta' /></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form name="formulario" action="ConsMT" validate="true">
						<fieldset><!-- Filtro Campos-->
							<legend><span><s:text name='mantenimiento.criterios' /></span></legend>
							<table width="100%" cellpadding="2" cellspacing="2" border="0">
								<tr>
									<td class="label"><s:label name="Tipo maquinas" value="Tipo maquinas" cssStyle="text-align:right; width:130px;"/></td>
									<td nowrap="nowrap">
									<s:select key="idTipoMaquinas" cssStyle="width:180px;" label="%{getText('entry.producto')}" list="%{#session.listaTiposMaquinas}" listKey="idTipo" listValue="descripcion" headerKey="0" headerValue="--Tipo--" cssStyle="text-align:left;" onchange="seleccionarTipoMaquinas()"/>
									<s:hidden id="idTipo" key="idTipo" value="0"/>
									</td>
									<!-- <td class="label"><s:label name="%{getText('crenv.fecha')}" value="%{getText('crenv.fecha')}" cssStyle="text-align:right;"/></td>
									<td nowrap="nowrap">
									<s:datetimepicker name="fecha" key="fecha" label="Format (yyyy-MM-dd)" displayFormat="yyyy-MM-dd"/>
									</td> -->
								</tr>
							</table>
						</fieldset><!--end product info-->
			</s:form>
			<div style="clear: left; float: left; position: relative;">
				<button class="i_magnifying_glass icon verdeOpilion" onclick="javascript:filtraAlbaranes();">Consultar</button>
				<button class="i_pdf_document icon verdeOpilion" onclick="javascript:reporteAlbaranes();">Ver en PDF</button>
			</div>
		</div> <!-- end consulta -->
		<button id="bot_consulta" class="icon i_arrow_down blue ocultando" onClick="javascript:muestraConsulta();">Mostrar consulta</button>
		<p>&nbsp;</p>
		<div id="demo">
						<table id="tablaMantenimientos" cellpadding="0" cellspacing="0" border="0" class="display">
							<thead>
								<tr>
									<th>id</th>
									<th>Tipo maquina</th>
									<th>Nombre</th>
									<th>Descripcion</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator id="mant" value="%{#session.listaMantenimientos}">
									<tr>
										<td><s:property value="idMantenimiento" /></td>
										<td><s:property value="tipoMaquina" /></td>
										<td><s:property value="nombre" /></td>
										<td><s:property value="descripcion" /></td>
										<td style="text-align: center;">
											<a title="Programar mantenimiento" href="ProgramarMT.action?idTipo=<s:property value="idTipo" />"><img src="img/anadir_01.gif" alt="Programar mantenimiento" title="Programar mantenimiento"/></a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
		</div> <!-- end demo -->
		<button class="i_arrow_left icon verdeOpilion" onclick="javascript:volverAlEscritorio();">Volver</button>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>