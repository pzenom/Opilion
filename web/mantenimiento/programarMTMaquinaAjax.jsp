<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
<!-- AQUI EL DIV DE AJAX -->
<sx:div name="ajax" id="ajaxDiv" theme="ajax">
		<table width="100%" cellpadding="2" cellspacing="2" border="0">
			<p>&nbsp;</p>
			<tr>
				<td class="label"><s:label name="Ciclo" value="Ciclo" cssStyle="text-align:right;"/></td>
				<td nowrap="nowrap">
					<s:select key="idCiclos" cssStyle="width:180px;" label="Ciclos" list="%{#session.listaCiclos}" listKey="idCiclo" listValue="descripcion" headerKey="0" headerValue="--Ciclo--" cssStyle="text-align:left;" onchange="seleccionarCiclo();" />
				</td>
			</tr>
			<tr>
				<td class="label"><s:label name="Fecha programada" value="Fecha programada" cssStyle="text-align:right;"/></td>
				<td nowrap="nowrap">
					<s:datetimepicker name="fechaProgramada" key="fechaProgramada" label="Format (yyyy-MM-dd)" displayFormat="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td class="label"><s:label name="%{getText('crenv.fecha')}" value="%{getText('crenv.fecha')}" cssStyle="text-align:right;"/></td>
				<td nowrap="nowrap">
					<s:datetimepicker name="fechaConsulta" key="fechaConsulta" label="Format (yyyy-MM-dd)" displayFormat="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td class="label"><s:label name="Tipo Mantenimiento" value="Tipo Mantenimiento" cssStyle="text-align:right; width:130px;"/></td>
				<td>
					<s2:checkboxlist name="listaTM" list="%{#session.listatm}" listKey="idTipoMant" listValue="nombre" theme="simple" cssStyle="text-align:right; width:130px;"/>
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
</sx:div>
</s:i18n>