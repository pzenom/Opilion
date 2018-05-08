<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<table id="tablaPedidosDireccionClientes" cellpadding="0" cellspacing="0" border="0" class="display">
		<thead>
			<tr>
				<th>Pedido</th>
				<th>Linea</th>
				<th>Producto</th>
				<th>Forma pago</th>
				<!-- <th>Agrupaciones</th> -->
				<th>Unidades</th>
				<!-- <th>Kilos</th> -->
				<th>Precio</th>
				<th>Marcar</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="direccion" value="%{#session.lineasDireccion}">
				<tr>
					<!--<s:hidden id="idFormaPago_%{#direccion.bgmNum}_%{#direccion.linNum}" value="%{#direccion.idFormaPago}"/>-->
					<td><s:property value="bgmNum" /></td>
					<td><s:property value="linNum" /></td>
					<td><s:property value="descripcionProducto" /></td>
					<td><s:property value="descripcionFormaPago" /></td>
					<!-- <td><s:property value="qty21Cant" /></td> -->
					<td><s:property value="qty59Cant" /></td>
					<!-- <td><s:property value="kilos" /></td> -->
					<td><s:property value="moa203" /></td>
					<td>
						<input type="checkbox" class="checkPedidosDireccion" id="check_pedido_direccion_<s:property value="bgmNum" />_<s:property value="linNum" />" onchange="javascript:pedidoDireccionSeleccionado('<s:property value="bgmNum" />','<s:property value="linNum" />');" name="check_pedido_direccion_<s:property value="bgmNum" />_<s:property value="linNum" />" value="<s:property value="bgmNum" />_<s:property value="linNum" />">
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:i18n>