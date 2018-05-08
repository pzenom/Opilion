<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consultar incidencias<span class="screenCode">CONS_INCI</span></h3>
	<div id="divNecesarioWidget">
		<table id="tablaIncidencias" cellpadding="0" cellspacing="0" border="0" class="display">
			<thead>
				<tr>
					<th style="width: 12px;">ID</th>
					<th style="width: 50px;">Valor</th>
					<th style="width: 70px;">Ubicacion</th>
					<th style="width: 70px;">Lote</th>
					<th style="width: 70px;">Cantidad</th>
					<th style="width: 70px;">Fecha</th>
					<th>Descripcion</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="incidencia" value="%{#session.listaIncidencias}">
					<tr>
						<td style="width: 12px;"><s:property value="idIncidencia" /></td>
						<td style="width: 50px;"><s:property value="valor" /></td>
						<td style="width: 70px;"><s:property value="descripcionUbicacion" /></td>
						<td style="width: 70px;"><s:property value="loteProducto" /></td>
						<td style="width: 70px;"><s:property value="cantidad" /></td>
						<td style="width: 70px;"><s:property value="fecha" /></td>
						<td><s:property value="descripcion" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<br />
		<br />
		<br />
	</div>
</s:i18n>