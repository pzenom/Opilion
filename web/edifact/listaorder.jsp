<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consulta pedidos<span class="screenCode">CONS_PEDIDOS</span></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form id="formulario" name="formulario" action="ConsOrders" validate="true">	
				<fieldset><!-- Informacion Basica -->
					<section style="width: 50%;">
						<label for="dropdown_cuantos_mostrar">Filtrar</label>
						<div>
							<select name="dropdown_cuantos_mostrar" id="dropdown_cuantos_mostrar" onchange="javascript:filtraPedidos();">
								
								<optgroup label="Mostrar...">
									<option value="0">Todos los pedidos</option>
									<option value="1" selected>&Uacute;ltimos 20</option>
									<option value="2">&Uacute;ltimos 50</option>
									<option value="3">&Uacute;ltima semana</option>
									<option value="4">&Uacute;ltimo mes</option>
									<option value="5">&Uacute;ltimo a&ntilde;o</option>
								</optgroup>
							</select>
						</div>
					</section>
					<!-- <section class="sectionMitad">
						<label for="dropdown_estadoPedido">Estado</label>
						<div>
							<select name="dropdown_estadoPedido" id="dropdown_estadoPedido" onchange="javascript:filtraPedidos();">
								<option value="0">Todos</option>
								<optgroup label="Estado del pedido">
									<option value="A">Aceptado</option>
									<option value="X">Eliminado</option>
									<option value="L">Listo</option>
									<option value="N">Rechazado</option>
									<option value="R">Recibido</option>
								</optgroup>
							</select>
						</div>
					</section> -->
				</fieldset><!--end Informacion Basica-->		
			</s:form>
		</div> <!-- end consulta -->
		<p>&nbsp;</p>
		<div id="demo">
			<table id="tablaOrders" cellpadding="0" cellspacing="0" border="0" class="display" style="display: none;">
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
		</div> <!-- end demo -->
		<s:hidden id="bgmNum" name="bgmNum" value="0" />
		<s:hidden id="estado" name="estado" value="0" />
		<p>&nbsp;</p>
		<div id="ajaxProductos" name="ajaxProductos" style="display: none;"></div>
		<div id="ajaxEstados" name="ajaxEstados" style="display: none;"></div>
		<div id="ajaxProductosMalDefinidos" name="ajaxProductosMalDefinidos" style="display: none;"></div>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>