<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:iterator id="cliente" value="%{#session.cliente}">
		<h3 class="handle">Gesti&oacute;n de salidas. CLIENTE: <s:property value="nombre" /><span class="screenCode">SALIDAS_CLIENTE</span></h3>
		<s:hidden id="idCliente" key="idUsuario" />
	</s:iterator>
	<div id="content" >
		<div id="cargaCliente">
			<s:form id="formulario" name="formulario" action="IngresarRegistroCliente" method="post" validate="true">
				<div class="widget" id="widget_tabs"> <!-- CONTIENE LAS TABS -->
					<div id="contenedorPagosCobros" class="tab">
						<ul>
							<li id="pestaniaPedidos">
								<a href="#tab_pedidos">Pedidos</a>
							</li>
							<li id="pestaniaPedidosDireccion">
								<a href="#tab_pedidosDireccion">Lineas de pedido por direcci&oacute;n</a>
							</li>
							<li id="pestaniaAlbaranes">
								<a href="#tab_albaranes">Albaranes</a>
							</li>
							<li id="pestaniaFacturas">
								<a href="#tab_facturas">Facturas</a>
							</li>
							<li id="pestaniaEstadisticas">
								<a href="#tab_estadisticas">Estad&iacute;sticas</a>
							</li>
						</ul>
						<div id="tab_pedidos"> <!-- TAB PEDIDOS -->
							<br />
							<table id="tablaPedidosCliente">
								<thead>
									<tr>
										<th>Pedido</th>
										<th>Estado</th>
										<th>Forma de pago</th>
										<th>Importe total</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator id="pedido" value="%{#session.listaPedidos}">
										<tr id="filaPedido_<s:property value="bgmNum" />" onmouseover="javascript:muestraProductosPedido('<s:property value="bgmNum" />');">
											<td id="codigoPedido_<s:property value="bgmNum" />" class="codigosPedidos">
												<s:property value="bgmNum" />
											</td>
											<td><s:property value="descripcionEstado" /></td>
											<td><s:property value="descripcionFormaPago" /></td>
											<td><span id="moa79_<s:property value="bgmNum" />" class="numeroMilesDecimal"><s:property value="moa79" /> &euro;</span></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div> <!-- END TAB PEDIDOS -->
						<div id="tab_pedidosDireccion"> <!-- TAB PEDIDOS DIRECCION -->
							<div id="divPedidosCliente">
								<p style="font-size: 15px;">Seleccione una direcci&oacute;n de entrega. Despu&eacute;s marque las lineas de pedido que quiera pasar a albar&aacute;n</p>
								<section class="sectionMitad">
									<!-- <label for="dropdown_direcciones">Direcciones</label> -->
									<div>
										<select name="dropdown_direcciones" id="dropdown_direcciones" title="Direcciones" onchange="javascript:direccionClienteSeleccionada();">
											<option value="0">Seleccione una direcci&oacute;n</p>
											<optgroup label="Direcciones">
												<s:iterator id="tipos" value="%{#session.direcciones}">
													<option value="<s:property value="idDireccion" />">
														<s:property value="nombreCalle" /> - <s:property value="localidad" />
													</option>
												</s:iterator>
											</optgroup>
										</select>
									</div>
								</section>
								<hr />
								<div id="contenedorPedidosDireccion">
								</div>
							</div>
						</div> <!-- END BREADCRUMB PEDIDOS DIRECCION -->
						<div id="tab_albaranes"> <!-- TAB ALBARANES -->
							<br />
							<table id="tablaAlbaranesCliente">
								<thead>
									<tr>
										<th>Albar&aacute;n</th>
										<th>Fecha</th>
										<th>Destino</th>
										<th>Estado</th>
										<th>Forma de pago</th>
										<th>Importe total</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator id="albaran" value="%{#session.listaAlbaranes}">
										<tr id="filaAlbaran_<s:property value="codigoAlbaran" />" >
											<s:hidden id="idFormaPagoAlbaran_%{#albaran.codigoAlbaran}" value="%{#albaran.idFormaPago}" />
											<td onmouseover="javascript:muestraProductosAlbaran('<s:property value="codigoAlbaran" />');">
												<s:property value="codigoAlbaran" />
											</td>
											<td><s:property value="fecha" /></td>
											<td><s:property value="destino" /></td>
											<td><s:property value="descripcionEstado" /></td>
											<td><s:property value="descripcionFormaPago" /></td>
											<td><s:property value="importeTotal" /></td>
											<td style="width: 15px; vertical-align: middle;">
												<img id="img_mostrar_albaran_<s:property value="codigoAlbaran" />" onclick="javascript:mostrarAlbaran('<s:property value="codigoAlbaran" />');" src="img/zoom.png" alt="Ver albar&aacute;n" title="Ver albar&aacute;n" style="cursor:pointer;" />
												<input id="check_albaran_<s:property value="codigoAlbaran" />" type="checkbox" class="checkAlbaranes" onchange="javascript:albaranSeleccionado();" value="<s:property value="codigoAlbaran" />" title="Marcar albar&aacute;n" />
												<img id="img_albaran_<s:property value="codigoAlbaran" />" src="img/enabled.gif" alt="Albar&aacute;n ya facturado" title="Albar&aacute;n ya facturado" style="display: none;" />
											</td>
											<td style="display: none;">
												<s:hidden id="facturado_%{#albaran.codigoAlbaran}" name="facturado" value="%{#albaran.facturado}"/>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div> <!-- END TAB ALBARANES -->
						<div id="tab_facturas"> <!-- TAB FACTURAS -->
							<br />
							<table id="tablaFacturasCliente">
								<thead>
									<tr>
										<th>Factura</th>
										<th>Fecha</th>
										<th>Estado</th>
										<th>Importe total</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator id="factura" value="%{#session.listaFacturas}">
										<tr id="filaFactura_<s:property value="codigoFactura" />" onmouseover="javascript:muestraProductosFactura('<s:property value="codigoFactura" />');">
											<s:hidden id="idFormaPagoFactura_%{#factura.codigoFactura}" value="%{#factura.idFormaPago}" />
											<td>
												<s:property value="codigoFactura" />
											</td>
											<td><s:property value="fecha" /></td>
											<td><s:property value="descripcionEstado" /></td>
											<td><s:property value="importeTotal" /></td>
											<td style="width: 15px; vertical-align: middle;">
												<!-- <input id="check_factura_<s:property value="codigoFactura" />" type="checkbox" class="checkFacturas" onchange="javascript:facturaSeleccionada();" value="<s:property value="codigoFactura" />" title="Marcar factura" /> -->
												<img id="img_factura_<s:property value="codigoFactura" />" src="img/enabled.gif" alt="Factura ya facturado" title="Factura ya facturado" style="display: none;" />
											</td>
											<td style="display: none;"><s:hidden id="facturado_%{#factura.codigoFactura}" name="facturado" value="%{#factura.facturado}"/></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div> <!-- END TAB FACTURAS -->
						<div id="tab_estadisticas"> <!-- TAB ESTADISTICAS -->
							<table id="tablaEstadisticosProductosPedidos" style="display: none;">
								<thead>
									<tr>
										<th></th>
										<s:iterator id="productosPedidos" value="%{#session.productosPedidos}">
											<s:hidden id="%{#productosPedidos.idProducto}" cssClass="productoPedido" />
											<s:hidden id="nombreProducto_%{#productosPedidos.idProducto}" value="%{#productosPedidos.nombreProducto}" />
											<th><s:property value="nombreProducto" /></th>
										</s:iterator>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Kilos</td>
										<s:iterator id="kilosPedidos" value="%{#session.productosPedidos}">
											<s:hidden id="kilos_%{#kilosPedidos.idProducto}" value="%{#kilosPedidos.kilos}" />
											<td><s:property value="kilos" /></td>
										</s:iterator>
									</tr>
								</tbody>
							</table>
							<div id="chart_div" style="text-align: center;">
							</div>
						</div> <!-- END TAB ESTADISTICAS -->
					</div>
				</div> <!-- END widget_tabs -->
			</s:form>
			<div id="divPreguntaFechaEntrega" style="display: none;">
				<p>
					<div style="height: 50px; margin: auto; width: 50%;">
						<fieldset id="fieldsetPreguntaFecha">
							<section>
								<label for="date_fechaEntrega">Fecha de facturaci&oacute;n</label>
								<div>
									<input id="date_fechaEntrega" name="date_fechaEntrega" type="text" class="date" data-format="yyyy-mm-dd" />
								</div>
							</section>
						</fieldset>
					</div>
				</p>
				<p style="text-align: center;">
					<button id="bot_aceptarFechaEntrega" class="i_tick icon verdeOpilion" onclick="javascript:generarNuevoPedidoUnion();">Aceptar</button>
				</p>
			</div>
		</div> <!-- END carga cliente -->
	</div> <!-- END content -->
	<div id="ajaxDirecciones" name="ajaxDirecciones" style="display: none;"></div>
	<div id="ajaxProductos" name="ajaxProductos" style="display: none;"></div>
</s:i18n>