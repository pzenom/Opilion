<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<table id="tablaEnvases" cellpadding="0" cellspacing="0" border="0" class="display">
		<thead>
			<tr>
				<th><s:text name="envase.id"/></th>
				<th><s:text name="envase.nombre"/></th>
				<th><s:text name="envase.peso"/></th>
				<th><s:text name="envase.stock"/></th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="entry" value="%{#session.listaenvases}">
				<tr>
					<td style="width: 4%;"><s:property value="idEnvase" /></td>
					<td style="width: 70%;"><s:property value="nombre" /></td>
					<td style="width: 8%;"><s:property value="peso" /></td>
					<td style="width: 8%;">
						<span id="stock_<s:property value="idEnvase" />" class="numeroMilesDecimal"><s:property value="stock" /></span>
					</td>
					<td id="celdaCargarEnvase_<s:property value="idEnvase" />" class="rol3 rol4" style="width: 4%; background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none;">
						<a href="javascript:cargarEnvase('<s:property value="idEnvase" />');"><img src="img/edit.png" alt="Editar" title="Editar"/></a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<p>&nbsp;</p>
</s:i18n>