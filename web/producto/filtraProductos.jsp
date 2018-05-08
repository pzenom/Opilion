<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<table id="tablaProductos" cellpadding="0" cellspacing="0" border="0" class="display">
		<thead>
			<tr>
				<th><s:label name="%{getText('cons.label.ID')}" value="%{getText('cons.label.ID')}" /></th>
				<th><s:label name="%{getText('cons.label.codigoEAN')}" value="%{getText('cons.label.codigoEAN')}" /></th>
				<th><s:label name="%{getText('cons.label.nombre')}" value="%{getText('cons.label.nombre')}" /></th>
				<th><s:label name="%{getText('cons.label.descripcion')}" value="%{getText('cons.label.descripcion')}" /></th>
				<th><s:label name="%{getText('cons.label.stock')}" value="%{getText('cons.label.stock')}" /></th>
				<th><s:label name="%{getText('cons.label.stockAgrupado')}" value="%{getText('cons.label.stockAgrupado')}" /></th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="entry" value="%{#session.listaproductos}">
				<tr>
					<td style="width: 4%"><s:property value="idProducto" /></td>
					<td style="width: 12%"><s:property value="codigoEan" /></td>
					<td style="width: 33%"><s:property value="nombre" /></td>
					<td style="width: 33%"><s:property value="descripcion" /></td>
					<td style="width: 7%">
						<span id="stock_<s:property value="idProducto" />" class="numeroMilesDecimal"><s:property value="stock" /></span>
					</td>
					<td style="width: 7%">
						<span id="stockAgrupado_<s:property value="idProducto" />" class="numeroMilesDecimal"><s:property value="stockAgrupado" /></span>
					</td>
					<td id="celdaCargarProducto_<s:property value="idProducto" />" class="rol3 rol4" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none; width: 3%;">
						<a href="javascript:cargarProducto('<s:property value="idProducto" />');"><img src="img/edit.png" alt="Editar" title="Editar"/></a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<p>&nbsp;</p>
</s:i18n>