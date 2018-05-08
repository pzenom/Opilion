<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Edici&oacute;n del pedido<span class="screenCode">VER_PEDIDO</span></h3>
	<s:form id="formulario" name="formulario" action="InseDetaAlba" validate="true">
		<fieldset>
			<section class="sectionTercio">
				<label for="text_pedido">ID</label>
				<div>
					<s:textfield id="text_pedido" key="text_pedido" label="%{getText('exit.pedido')}" value="%{#session.codigoPedido}" disabled="true" />
				</div>
			</section>
			<section class="sectionTercio">
				<label for="date_fechaPedido">Fecha pedido</label>
				<div>
					<s:textfield id="date_fechaPedido" key="date_fechaPedido" label="%{getText('exit.fechaPedido')}" value="%{#session.fechaPedido}" disabled="true" />
				</div>
			</section>
			<section class="sectionTercio">
				<label for="date_fechaEntrega">Fecha entrega</label>
				<div>
					<s:textfield id="date_fechaEntrega" cssClass="input_editable" key="date_fechaEntrega" label="%{getText('exit.fechaEntrega')}" value="%{#session.fechaEntrega}" disabled="true" />
				</div>
			</section>
			<section class="sectionUnoDeTres">
				<label for="dropdown_divisas">Divisa</label>
				<div>
					<select name="dropdown_divisas" id="dropdown_divisas" disabled="disabled">
						<option selected value="0">Seleccione una divisa</option>
						<optgroup label="Divisas disponibles">
							<option value="EUR">EUR</option>
						</optgroup>
					</select>
				</div>
			</section>
			<section class="sectionDosDeTres">
				<label for="dropdown_clientes">Cliente</label>
				<div>
					<select name="dropdown_clientes" id="dropdown_clientes" onchange="javascript:seleccionCliente(true);" disabled="disabled">
						<option selected value="0">Seleccione un cliente</option>
						<optgroup label="Clientes">
							<s:iterator id="clientes" value="%{#session.listaclientes}">
								<option value="<s:property value="idUsuario" />">
									<s:property value="nombre" />
								</option>
							</s:iterator>
						</optgroup>
					</select>
				</div>
			</section>
			<section>
				<label for="dropdown_formasDePago">Forma de pago</label>
				<div id="contenedorSelect" style="display: none;">
					<div>
						<select name="dropdown_formasDePago" id="dropdown_formasDePago" disabled="disabled">
							<option value="-1">Seleccione una forma de pago</option>
							<optgroup label="Formas de pago" id="optgroupFormasPago">
							</optgroup>
						</select>
					</div>
				</div>
				<div id="contenedorTexto">
					<p id="textoSeleccioneCliente" style="color: red;">Seleccione un cliente</p>
				</div>
			</section>
			<hr />
			<div id="divTablaProductos">
				<table id="tablaProductos" cellpadding="0" cellspacing="0" border="0" class="display" >
					<thead>
						<tr>
							<th>Linea</th>
							<th>Producto</th>
							<th>Cantidad</th>
							<th>Precio</th>
							<th>Agrupaciones EDI</th>
							<th>Direccion de entrega</th>
						</tr>
					</thead>
					<tbody id="tablaProductosBody">
						<s:iterator id="pedido" value="%{#session.lineasPedido}">
							<tr id="lineaPedido_<s:property value="linNum" />" >
								<input id="linea_<s:property value="linNum" />" name="linea_<s:property value="linNum" />" class="linea" type="hidden" value="<s:property value="linNum" />"></input>
								<input id="lineaProcesada_<s:property value="linNum" />" name="lineaProcesada_<s:property value="linNum" />" class="lineaProcesada" type="hidden" value="<s:property value="lineaProcesada" />"></input>
								<input id="agrupaciones_<s:property value="linNum" />" name="agrupaciones_<s:property value="linNum" />" class="agrupaciones" type="hidden" value="0"></input>
								<input id="codEan_<s:property value="linNum" />" name="codEan_<s:property value="linNum" />" value="<s:property value="idLin" />__<s:property value="idPedido" />" type="hidden" class="codsEan"/>
								<input id="pesoProducto_<s:property value="linNum" />" name="pesoProducto_<s:property value="linNum" />" value="<s:property value="peso" />" type="hidden" class="pesos"/>
								<input id="idProductoLinea_<s:property value="linNum" />" name="idProductoLinea_<s:property value="linNum" />" class="ids" value="<s:property value="piaNumSa" />" type="hidden" />
								<td style="vertical-align: middle; width: 30px !important;" rowspan="2">
									<p id="indiceLinea_<s:property value="linNum" />" style="background: transparent !important;">
										<s:property value="linNum" />
										<a id="imgLineaProcesada_<s:property value="linNum" />" style="display: none;" title="La linea ya ha sido procesada">
											<img id="imgProcesada_<s:property value="linNum" />" title="La linea ya ha sido procesada" alt="La linea ya ha sido procesada" src="img/ico_error.png">
										</a>
									</p>
								</td>
								<td style="width: 180px !important; vertical-align: middle;" class="linea<s:property value="linNum" />">
									<s:property value="nombreProducto" /><br /> EAN: <s:property value="idLin" />
									<fieldset>
										<br /><br />Precio linea
										<input id="precioTotalLinea_<s:property value="linNum" />" class="preciosLineas" value="<s:property value="moa203" />" style="width:50px; background-color: #E6E6E6;" type="text" disabled="true" onchange="javascript:precioLineaModificado();" /> &euro;
										<br /><br />Agrupaciones EDI linea
										<input id="agrupacionesLinea_<s:property value="linNum" />" class="agrupacionesLineas" style="width:50px; background-color: #E6E6E6;" type="text" disabled="true" />
									</ fieldset>
								</td>
								<td style="width: 180px !important; vertical-align: middle; display: none;" id="ocultaLinea<s:property value="linNum" />" colspan="5" rowspan="2">
									<b>PRODUCTO LINEA</b> <s:property value="linNum" />: <s:property value="nombreProducto" />
								</td>
								<td style="width: 90px !important; vertical-align: middle;" class="linea<s:property value="linNum" />">
									<!--<div style="padding-bottom: 4px;">
										Autoajustar
										<input type="checkbox" id="checkAutoCantidad_<s:property value="linNum" />" onchange="javascript:checkAutoCantidadLinea(<s:property value="linNum" />);" checked="true" readonly="true" class="editables_<s:property value="linNum" />" />
									</div>
									<br />Kilos
									<input id="cantidadPedida_<s:property value="linNum" />" name="cantidadPedida_<s:property value="linNum" />" class="kilosPedidos editables_<s:property value="linNum" />" style="width:50px;" type="text" onKeyPress="return validarNumerosDecimales('cantidadPedida_<s:property value="linNum" />', event);" onkeyup="javascript:kilosModificados(<s:property value="linNum" />);" onblur="javascript:ajustarDecimal('cantidadPedida_<s:property value="linNum" />');" value="<s:property value="kilos" />" readonly="true" />-->
									<br />Unidades<br />
									<input id="unidadesPedidas_<s:property value="linNum" />" name="unidadesPedidas_<s:property value="linNum" />" class="unidadesPedidas editables_<s:property value="linNum" />" onkeyup="unidadesModificadas(<s:property value="linNum" />)" style="width:50px;" onKeyPress="return validarSoloNumeros(event);" value="<s:property value="qty21Cant" />" readonly="true" />
								</td>
								<td style="width: 90px !important; vertical-align: middle;" class="linea<s:property value="linNum" />">
									<!--<div style="padding-bottom: 4px;">
										Autoajustar
										<input type="checkbox" id="checkAutoPrecios_<s:property value="linNum" />" onchange="javascript:checkAutoPreciosLinea(<s:property value="linNum" />);" checked="true" readonly="true" class="editables_<s:property value="linNum" />"/>
									</div>
									<br />&euro;/Kg
									<input id="precioKilo_<s:property value="linNum" />" name="precioKilo_<s:property value="linNum" />" class="preciosKilo editables_<s:property value="linNum" />" onKeyPress="return validarNumerosDecimales('precioKilo_<s:property value="linNum" />', event);" onkeyup="javascript:precioKiloModificado(<s:property value="linNum" />);" onblur="javascript:ajustarDecimal( 'precioKilo_<s:property value="linNum" />');" style="width:50px;" type="text" readonly="true" />-->
									<br />&euro;/unidad de venta<br />
									<input id="precioUnidad_<s:property value="linNum" />" name="precioUnidad_<s:property value="linNum" />" class="preciosUnidades editables_<s:property value="linNum" />" onKeyPress="return validarNumerosDecimales('precioUnidad_<s:property value="linNum" />',event);" style="width:50px;" type="text" onblur="javascript:ajustarDecimal('precioUnidad_<s:property value="linNum" />');" onkeyup="javascript:precioUnidadModificado(<s:property value="linNum" />);" value="<s:property value="priAaa" />" readonly="true" />
								</td>
								<td style="width: 80px !important; vertical-align: middle;" class="linea<s:property value="linNum" />">
									Cada agrupacion EDI contiene<br />
									<input id="unidadesAgrupaciones_<s:property value="linNum" />" name="unidadesAgrupaciones_<s:property value="linNum" />" onKeyPress="return validarSoloNumeros(event);" class="unidadesAgrupacion editables_<s:property value="linNum" />" onkeyup="javascript:agrupacionesDePaquetesModificado(<s:property value="linNum" />);" style="width:50px;" type="text" value="<s:property value="qty59Cant" />" readonly="true" />
									<br />unidades
								</td>
								<td style="width: 350px !important; max-width: 350px !important;" class="linea<s:property value="linNum" />">
									<select id="dropdown_direcciones_<s:property value="linNum" />" onchange="javascript:aniadirDireccion(<s:property value="linNum" />);" disabled="disabled" class="editables_<s:property value="linNum" />" >
										<option selected value="0">Seleccione una direccion</option>
										<optgroup id="entrega_<s:property value="linNum" />" class="direccionesCliente" label="Direcciones"></optgroup>
									</select>
									<p style="background: transparent !important;">&nbsp;</p>
									<table>
										<thead><tr><th>Direccion</th><th>Agrupaciones EDI</th></tr></thead>
										<tbody id="direcciones_<s:property value="%{linNum}" />">
											<s:iterator id="dirs" value="%{#pedido.loc}" status="status">
												<s:hidden id="localizacionProcesada_%{#pedido.linNum}_%{#dirs.loc}" name="localizacionProcesada_%{#pedido.linNum}_%{#dirs.loc}" cssClass="localizacionProcesada localizacionProcesada_%{#pedido.linNum}" value="%{#dirs.localizacionProcesada}" />
												<tr id="direccionUnidades_<s:property value="%{linNum}" />_<s:property value="%{#status.count}" />" class="direccionesUnidades">
													<td>
														<p id="direccion_<s:property value="%{linNum}" />_<s:property value="%{#status.count}" />" name="direccion_<s:property value="%{linNum}" />_<s:property value="%{#status.count}" />" class="direccionesLinea" style="background: transparent !important" value="<s:property value="%{loc}" />">
															<a id="imgLocalizacionProcesada_<s:property value="linNum" />_<s:property value="loc" />" style="display: none;" title="Esta direccion ya ha sido procesada">
																<img title="Esta direccion ya ha sido procesada" alt="Esta direccion ya ha sido procesada" src="img/ico_error.png">
															</a>
															<s:property value="%{descripcion}" />
														</p>
														<s:hidden id="inputDireccion_%{#pedido.linNum}_%{#status.count}" cssClass="direccionesLinea" value="%{#dirs.loc}"/>
													</td>
													<td style="width: 25px;">
														<input id="unidades_<s:property value="%{linNum}" />_<s:property value="%{#status.count}" />" name="unidades_<s:property value="%{linNum}" />_<s:property value="%{#status.count}" />" style="width: 28px;" class="unidadesDireccion editables_<s:property value="linNum" />" onchange="javascript:compruebaBultosUnicaDireccion(<s:property value="%{linNum}" />);" value="<s:property value="%{qty}" />" readonly="true" >
														</input>
													</td>
													<td>
														<a id="procesar_<s:property value="%{linNum}" />_<s:property value="%{#status.count}" />" href="javascript:procesarLocalizacion(<s:property value="%{linNum}" />,<s:property value="%{loc}" />)" title="Procesar esta direccion" style="vertical-align: middle;" class="img_<s:property value="linNum" />_<s:property value="loc" />">
															<img src="img/pill_go.png" alt="Procesar esta direccion" title="Procesar esta direccion">
														</a>
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</td>
								<td style="vertical-align: middle; width: 20px !important;" rowspan="2">
									<a id="minimiza_<s:property value="linNum" />" title="Minimizar" href="javascript:minimizaLinea(<s:property value="linNum" />, true)">
										<img id="imgMinimiza_<s:property value="linNum" />" title="Minimiza esta linea" alt="Minimiza esta linea" src="img/zoom.png">
									</a>
								</td>
							</tr>
							<tr id="lineaObservaciones_<s:property value="linNum" />" >
								<td colspan="5" class="linea<s:property value="linNum" />">
									Observaciones:
										<textarea id="observaciones_<s:property value="linNum" />" name="observaciones_<s:property value="linNum" />" style="width:96%;" rows="2" readonly="true" ><s:property value="%{observaciones}" /></textarea>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div> <!-- end demo: Listado productos disponible-->
		</fieldset>
		<fieldset>
			<div id="totales">
				<table id="tablaTotales" cellpadding="0" cellspacing="0" border="0" class="display" >
					<thead>
						<tr>
							<th>Kilos</th>
							<th>Unidades</th>
							<th>Agrupaciones EDI</th>
							<th>IMPORTE</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td id="pesoNetoTotal"><s:property value="%{#session.pesoTotal}" /></td>
							<td id="cantidadTotal"><s:property value="%{#session.cantidadTotal}" /></td>
							<td id="numBultosTotal"><s:property value="%{#session.bultosTotal}" /></td>
							<td id="importeTotal"><s:property value="%{#session.importeTotal}" /></td>
						</tr>
					</tbody>
				</table>
			</div> <!-- end totales -->
			<section>
				<label for="dropdown_formasDePago">Observaciones del pedido</label>
				<div>
					<textarea id="observacionesPedido" name="observacionesPedido" style="width:96%;" rows="2" value="<s:property value="%{#session.observacionesPedido}" />" readonly="true"><s:property value="%{#session.observacionesPedido}" /></textarea>
				</div>
			</section>
		</fieldset>
		<div id="hiddens" style="display: none;">
			<s:hidden id="divisa_hidden" value="%{#session.divisa}"/>
			<s:hidden id="cliente_hidden" value="%{#session.cliente}"/>
			<s:hidden id="formaPago_hidden" value="%{#session.idFormaPago}"/>
			<s:hidden id="moa79" name="moa79"/>
			<s:hidden id="nadBy" name="nadBy"/>
			<s:hidden id="nadMs" name="nadMs"/>
			<s:hidden id="bgmNum" name="bgmNum"/>
			<s:hidden id="estado" name="estado" value="%{#session.estado}"/>
			<s:hidden id="fechaPedido" name="fechaPedido"/>
			<s:hidden id="cux" name="cux"/>
			<s:hidden id="cnt" name="cnt"/>
			<s:hidden id="idFormaPago" name="idFormaPago"/>
			<select style="display: none;" id="entrega_-1"></select>
			<div id="formasPagoOcultas" style="display: none;"></div>
			<div id="direccionesOcultas" style="display: none;"></div>
		</div>
	</s:form>
</s:i18n>