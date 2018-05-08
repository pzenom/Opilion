<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<table id="tablaOrders" cellpadding="0" cellspacing="0" border="0" class="display" >
		<thead>
			<tr>
				<th class="label"><s:label name="Pedido" value="Pedido" cssStyle="text-align:left;" /></th>
				<th class="label"><s:label name="Productos" value="Productos" cssStyle="text-align:left;" /></th>
				<th class="label"><s:label name="Fecha" value="Fecha" cssStyle="text-align:left;" /></th>
				<th class="label"><s:label name="Cliente" value="Cliente" cssStyle="text-align:left;" /></th>
				<th class="label"><s:label name="Estado" value="Estado" cssStyle="text-align:left;" /></th>
				<th class="label"><s:label name="Importe" value="Importe" cssStyle="text-align:left;" /></th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="pedido" value="%{#session.pedido}" >
				<tr id="linea_<s:property value="bgmNum" />">
					<s:hidden cssClass="filaPedidos" value="0" />
					<s:hidden id="numeroDirecciones_%{#pedido.bgmNum}" cssClass="direccionesPedidos" value="%{#pedido.numeroDireccionesDistintas}" />
					<td id="celda_<s:property value="bgmNum" />"><s:property value="bgmNum" /></td>
					<td id="celdasProductos_<s:property value="bgmNum" />" onmouseover="javascript:muestraProductos('<s:property value="bgmNum" />');" style="width: 15px;">
						<img src="img/eye.png" alt="Ver los productos pedidos" title="Ver los productos pedidos" />
					</td>
					<td>
						<s:iterator id="dtm" value="%{dtm}" >
							<s:if test="%{dtmFunc == 137}">
								<s:property value="%{dtmFech}" />
							</s:if>
						</s:iterator>
					</td>
					<td style="width: 25em !important;">
						<s:property value="nombreCliente" />
					</td>
					<td id="celdasEstados_<s:property value="bgmNum" />" onmouseover="javascript:muestraEstadoPedido('<s:property value="estado" />', '<s:property value="bgmNum" />');" style="width: 7em !important;">
						<span class="estado_<s:property value="estado" />" id="estado_<s:property value="bgmNum" />"><s:property value="descripcionEstado" /></span>
					</td>
					<td style="text-align: right; width: 4em !important;">
						<span id="moa79_<s:property value="bgmNum" />" class="numeroMilesDecimal"><s:property value="moa79" /> &euro;</span>
					</td>
					<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; width: 15px;" border="0">
						<a href="javascript:mostrar('<s:property value="bgmNum" />');">
							<img src="img/zoom.png" alt="Ver pedido" title="Ver pedido" />
						</a>
					</td>
					<td id="editarPedido_<s:property value="bgmNum" />" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; width: 15px;" border="0">
						<a href="javascript:cargarEditar('<s:property value="bgmNum" />');">
							<img src="img/edit.png" alt="Editar pedido" title="Editar pedido" />
						</a>
					</td>
					<td id="procesarPedido_<s:property value="bgmNum" />" style="width: 15px; background: none repeat scroll 0 0 #FFF; vertical-align: middle;" border="0">
						<a id="enlaceProcesarPedido_<s:property value="bgmNum" />" href="javascript:procesarPedido('<s:property value="bgmNum" />')">
							<img src="img/pill_go.png" alt="Procesar pedido" title="Procesar pedido" />
						</a>
						<img id="imgNoProcesarPedido_<s:property value="bgmNum" />" src="img/ico_error.png" onclick="javascript:$.msg('No se puede generar un albar&aacute;n con m&aacute;s de una direcci&oacute;n de entrega');" alt="No se puede generar un albar&aacute;n con m&aacute;s de una direcci&oacute;n de entrega" title="No se puede generar un albar&aacute;n con m&aacute;s de una direcci&oacute;n de entrega" style="display: none;" />
					</td>
					<td id="eliminarPedido_<s:property value="bgmNum" />" style="width: 15px; background: none repeat scroll 0 0 #FFF; vertical-align: middle;" border="0">
						<a href="javascript:eliminar('<s:property value="bgmNum" />');">
							<img src="img/cancel.png" alt="Eliminar pedido" title="Eliminar pedido" />
						</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:i18n>