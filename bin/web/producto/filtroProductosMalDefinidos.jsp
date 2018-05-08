<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>EAN</th>
				<th>Nombre</th>
				<th>Descripcion</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="entry" value="%{#session.listaproductosMalDefinidos}">
				<tr>
					<td><s:property value="idProducto" /></td>
					<td><s:property value="codigoEan" /></td>
					<td><s:property value="nombre" /></td>
					<td><s:property value="descripcion" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:i18n>