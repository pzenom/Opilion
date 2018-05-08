<%@ page contentType="text/html; charset=ISO-8859-15"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 id="tituloPaginaFactura" class="handle" style="display: none;">Editar factura<span class="screenCode">FACT_02</span></h3>
	<s:form id="formulario" name="formulario" action="#" validate="true">
		<s:iterator id="factura" value="%{#session.factura}" >
			<s:hidden id="esCuota" value="%{#factura.esCuota}" />
			<s:hidden id="idDestinoFactura" value="%{#factura.idDestino}" />
			<s:hidden id="idFormaPago" value="%{#factura.idFormaPago}" />
			<div id="contenedorAlbaran">
				<div style="margin-left: 100px; margin-top: 20px; width: 200px; position: relative; float: left;">
					<img src="img/logo_tierrina_2.gif" style="width: 200px; height: 100px;" />
				</div>
				<div id="contenedorInfoAlbaran" style="position: relative; margin-left: 460px; width: 50%;">
					<fieldset>
						<section>
							<label for="text_numeroAlbaran">N&uacute;mero</label>
							<div>
								<span id="spanIdFactura"><s:property value="%{#factura.idFactura}" /></span>
							</div>
						</section>
						<section>
							<label for="text_codigoFactura">C&oacute;digo</label>
							<div>
								<span id="spanCodigoFactura"><s:property value="%{#factura.codigoFactura}" /></span>
							</div>
						</section>
						<section onclick="javascript:modificaFechaVencimiento();">
							<label for="date_fecha">Fecha</label>
							<div>
								<s:textfield id="date_fecha" key="date_fecha" label="Fecha de vencimiento" value="%{#factura.fechaVencimiento}"  cssStyle="display: none;" onchange="javascript:fechaVencimientoModificada();"  onblur="javascript:fechaVencimientoModificada();" cssClass="date" />
								<span id="spanFechaVencimiento"><s:property value="%{#factura.fechaVencimiento}" /></span>
							</div>
						</section>
					</fieldset>
				</div> <!-- END CONTENEDOR INFO ALBARAN -->
				<s:hidden id="codigoAlbaran" value="%{#factura.codigoAlbaran}" />
				<div id="contenedorInfoCliente" style="position: relative; width: 40%;">
					<fieldset>
						<label>Cliente</label>
						<section onclick="javascript:modificaNombreCliente();" style="height: 50px;">
							<label for="text_nombreCliente">Nombre</label>
							<div>
								<s:textfield id="text_nombreCliente" key="text_nombreCliente" label="Nombre cliente" value="%{#factura.nombreCliente}" cssStyle="display: none;" onblur="javascript:nombreClienteModificado();" />
								<span id="spanNombreCliente"><s:property value="%{#factura.nombreCliente}" /></span>
							</div>
						</section>
						<section onclick="javascript:modificaIdCliente();" style="width: 50%; height: 50px;">
							<label for="text_idCliente">Id</label>
							<div style="width: 52%;">
								<s:textfield id="text_idCliente" key="text_idCliente" label="%{getText('exit.albaran')}" value="%{#factura.idCliente}" cssStyle="display: none;" onblur="javascript:idClienteModificado();" />
								<span id="spanIdCliente"><s:property value="%{#factura.idCliente}" /></span>
							</div>
						</section>
						<section onclick="javascript:modificaNifCliente();" style="width: 50%; height: 50px;">
							<label for="text_nifCliente">NIF</label>
							<div style="width: 50%;">
								<s:textfield id="text_nifCliente" key="text_nifCliente" label="%{getText('exit.albaran')}" value="%{#factura.nifCliente}" cssStyle="display: none;" onblur="javascript:nifClienteModificado();" />
								<span id="spanNifCliente"><s:property value="%{#factura.nifCliente}" /></span>
							</div>
						</section>
						<section onclick="javascript:modificaTelefono();" style="height: 50px;">
							<label for="text_telefono">Tel&eacute;fono</label>
							<div>
								<s:textfield id="text_telefono" key="text_telefono" label="Numero de telefono" value="%{#factura.telefonoCliente}" cssStyle="display: none;" onblur="javascript:telefonoModificado();" />
								<span id="spanTelefono"><s:property value="%{#factura.telefonoCliente}" /></span>
							</div>
						</section>
					</fieldset>
				</div> <!-- END CONTENEDOR INFO ALBARAN -->
				<div id="contenedorInfoDirEntregaFormaPago" style="float: right; margin-right: 23px; margin-top: -160px; position: relative; width: 50%;">
					<fieldset>
						<!--section onclick="javascript:modificaDireccionEntrega();">
							<label for="text_direccionEntrega">Direcci&oacute;n de facturaci&oacute;n</label>
							<div>
								<s:textfield id="text_direccionEntrega" key="text_direccionEntrega" label="Direcci&oacute;n de entrega" value="%{#factura.descripcionDestino}" cssStyle="display: none;" onblur="javascript:direccionEntregaModificada();" />
								<span id="spanDireccionEntrega"><s:property value="%{#factura.descripcionDestino}" /></span>
							</div>
						</section>-->
						<section>
							<label for="dropdown_direccionFacturacion" onclick="javascript:modificaDireccionFacturacion();">Direcci&oacute;n de facturaci&oacute;n</label>
							<div>
								<div id="divDropdownDireccionFacturacion" style="display: none;">
									<select id="dropdown_direccionFacturacion" label="Direcci&oacute;n de facturaci&oacute;n" value="<s:property value="%{#albaran.idDireccionCliente}"/>" onchange="javascript:direccionFacturacionModificada();" onblur="javascript:direccionFacturacionModificada();">
										<option value="0">Seleccione una direcci&oacute;n</option>
										<optgroup label="Direcciones">
											<s:iterator id="dirs" value="%{#session.listaDireccionesFacturacion}">
												<option value="<s:property value="idDireccion" />">
													<s:property value="%{#dirs.nombreCalle}" />. <s:property value="%{#dirs.localidad}" />
												</option>
											</s:iterator>
										</optgroup>
									</select>
								</div>
								<span id="spanDireccionFacturacion" onclick="javascript:modificaDireccionFacturacion();"></span>
							</div>
						</section>
						<section onclick="javascript:modificaFormaPago();">
							<label for="dropdown_formasPago">Forma de pago</label>
							<div>
								<div id="divDropdownFormasPago" style="display: none;">
									<select id="dropdown_formasPago" label="Forma de pago" value="<s:property value="%{#albaran.idDatoBancario}"/>" onchange="javascript:formaPagoModificada();" onblur="javascript:formaPagoModificada();">
										<option value="0">Seleccione una forma de pago</option>
										<optgroup label="Formas de pago">
											<s:iterator id="formas" value="%{#session.formasDePago}">
												<option value="<s:property value="idDatoBancario" />">
													<s:property value="%{#formas.descripcion}" />
												</option>
											</s:iterator>
										</optgroup>
									</select>
								</div>
								<!-- <s:textfield id="text_formaPago" key="text_formaPago" label="Forma de pago" value="%{#factura.descripcionFormaPago}" cssStyle="display: none;" onblur="javascript:formaPagoModificada();" /> -->
								<span id="spanFormaPago"><s:property value="%{#factura.descripcionFormaPago}" /></span>
							</div>
						</section>
					</fieldset>
				</div> <!-- END CONTENEDOR INFO ALBARAN -->
				<div id="contenedorLineasAlbaran">
					<table>
						<thead>
							<tr>
								<th style="width: 30px;">Bultos</th>
								<th style="width: 100px;">GTIN/EAN</th>
								<th style="">Granel</th>
								<th style="width: 80px;">Lote</th>
								<th style="width: 80px;">Kilos</th>
								<th style="width: 80px;" >&euro;/Kg.</th>
								<th style="width: 80px;">Importe</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator id="lineas" value="%{#factura.graneles}">
								<s:hidden id="%{#lineas.numLinea}" cssStyle="lineas" />
								<s:hidden id="idProducto_%{#lineas.numLinea}" value="%{#lineas.idProducto}" />
								<s:hidden id="codigoItem_%{#lineas.numLinea}" value="%{#lineas.lote}" />
								<tr style="height: 45px;">
									<s:hidden id="%{#lineas.numLinea}" cssClass="linea" value="%{#lineas.numLinea}" />
									<td style="vertical-align: middle;">
										<span id="cantidad_<s:property value="%{#lineas.numLinea}"/>"><s:property value="%{#lineas.cantidad}"/></span>
									</td>
									<td style="vertical-align: middle;">
										<s:property value="%{#lineas.codigoEan}"/>
									</td>
									<td style="background-color: #EFF8FB; vertical-align: middle;" onclick="javascript:celdaDescripcion(<s:property value="%{#lineas.numLinea}"/>);">
										<span id="spanDescripcion_<s:property value="%{#lineas.numLinea}"/>"><s:property value="%{#lineas.descripcion}"/></span>
										<s:textfield id="textDescripcion_%{#lineas.numLinea}" value="%{#lineas.descripcion}" cssStyle="display: none;" onblur="javascript:descripcionModificada(%{#lineas.numLinea});"/>
									</td>
									<td style="vertical-align: middle;">
										<s:property value="%{#lineas.lote}"/>
									</td>
									<td style="vertical-align: middle;">
										<span id="spanPesoLinea_<s:property value="%{#lineas.numLinea}"/>"><s:property value="%{#lineas.peso}"/></span>
									</td>
									<td style="background-color: #EFF8FB; vertical-align: middle;" onclick="javascript:celdaPrecio(<s:property value="%{#lineas.numLinea}"/>);">
										<span id="spanPrecio_<s:property value="%{#lineas.numLinea}"/>"><s:property value="%{#lineas.precioKilo}"/></span>
										<s:textfield id="textPrecio_%{#lineas.numLinea}" value="%{#lineas.precioKilo}" cssStyle="display: none;" onblur="javascript:precioModificado(%{#lineas.numLinea});" onkeypress="return validarNumerosDecimales('textPrecio_%{#lineas.numLinea}', event);" />
									</td>
									<td style="vertical-align: middle;">
										<span id="spanPrecioLinea_<s:property value="%{#lineas.numLinea}"/>" class="totalesLinea"><s:property value="%{#lineas.precioTotal}"/></span>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<table>
						<thead>
							<tr>
								<th style="width: 30px;">Bultos</th>
								<th style="width: 100px;">GTIN/EAN</th>
								<th style="">Producto</th>
								<th style="width: 80px;">Lote</th>
								<th style="width: 80px;">Uds.</th>
								<th style="width: 80px;" >&euro;/ud.</th>
								<th style="width: 80px;">Importe</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator id="lineas" value="%{#factura.itemsAgrupaciones}">
								<s:hidden id="%{#lineas.numLinea}" cssStyle="lineas" />
								<s:hidden id="idProducto_%{#lineas.numLinea}" value="%{#lineas.idProducto}" />
								<s:hidden id="codigoItem_%{#lineas.numLinea}" value="%{#lineas.lote}" />
								<tr style="height: 45px;">
									<s:hidden id="%{#lineas.numLinea}" cssClass="linea" value="%{#lineas.numLinea}" />
									<td style="vertical-align: middle;">
										<span id="cantidad_<s:property value="%{#lineas.numLinea}"/>"><s:property value="%{#lineas.cantidad}"/></span>
									</td>
									<td style="vertical-align: middle;">
										<s:property value="%{#lineas.codigoEan}"/>
									</td>
									<td style="background-color: #EFF8FB; vertical-align: middle;" onclick="javascript:celdaDescripcion(<s:property value="%{#lineas.numLinea}"/>);">
										<span id="spanDescripcion_<s:property value="%{#lineas.numLinea}"/>"><s:property value="%{#lineas.descripcion}"/></span>
										<s:textfield id="textDescripcion_%{#lineas.numLinea}" value="%{#lineas.descripcion}" cssStyle="display: none;" onblur="javascript:descripcionModificada(%{#lineas.numLinea});"/>
									</td>
									<td style="vertical-align: middle;">
										<s:property value="%{#lineas.lote}"/>
									</td>
									<td style="vertical-align: middle;">
										<span id="spanPesoLinea_<s:property value="%{#lineas.numLinea}"/>"><s:property value="%{#lineas.pesoNeto}"/></span>
									</td>
									<td style="background-color: #EFF8FB; vertical-align: middle;" onclick="javascript:celdaPrecio(<s:property value="%{#lineas.numLinea}"/>);">
										<span id="spanPrecio_<s:property value="%{#lineas.numLinea}"/>"><s:property value="%{#lineas.precioUnitario}"/></span>
										<s:textfield id="textPrecio_%{#lineas.numLinea}" value="%{#lineas.precioUnitario}" cssStyle="display: none;" onblur="javascript:precioModificado(%{#lineas.numLinea});" onkeypress="return validarNumerosDecimales('textPrecio_%{#lineas.numLinea}', event);" />
									</td>
									<td style="vertical-align: middle;">
										<span id="spanPrecioLinea_<s:property value="%{#lineas.numLinea}"/>" class="totalesLinea"><s:property value="%{#lineas.precioTotal}"/></span>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>		
				<div id="divCargos" style="display: none;">
					<table id="tablaCargosI" style="font-size:14px;" class="display">
						<thead>
							<tr>
								<th style="width: 100px;">Tipo de cargo</th>
								<th style="width: 100px;">Cargo</th>
								<th style="width: 100px;">IVA %</th>
								<th style="width: 100px;">Total</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Transporte</td>
								<td>
									<s:textfield id="cargoTran" name="cargoTran" cssClass="inputCargos" key="cargoTran" value="%{#factura.cargoTran}" size="4" onkeyup="calcularCargos();" onkeypress="return validarNumerosDecimales('cargoTran', event);" />
								</td>
								<td>
									<s:textfield id="ivact" name="ivact" key="ivacargo1" cssClass="inputCargos" size="4" value="%{#factura.ivaCargoTran}" onkeyup="calcularCargos();" onkeypress="return validarNumerosDecimales('ivact', event);" />
								</td>
								<td>
									<s:textfield id="totalCargoTran" name="totalCargoTran" key="totalCargoTran" cssClass="inputCargos" value="%{#factura.totalCargoTran}" size="4" readonly="true" />
								</td>
							</tr>
							<tr>
								<td>Bancos</td>
								<td>
									<s:textfield id="cargoBanc" name="cargoBanc" key="cargoBanc" value="%{#factura.cargoBanc}" cssClass="inputCargos" size="4" onkeyup="calcularCargos();" onkeypress="return validarNumerosDecimales('cargoBanc', event);" />
								</td>
								<td>
									<s:textfield id="ivacb" name="ivacb" key="ivacargo2" size="4" value="%{#factura.ivaCargoBanc}" cssClass="inputCargos" onkeyup="calcularCargos();" onkeypress="return validarNumerosDecimales('ivacb', event);" />
								</td>
								<td>
									<s:textfield id="totalCargoBanc" name="totalCargoBanc" key="totalCargoBanc" cssClass="inputCargos" value="%{#factura.totalCargoBanc}" size="4" readonly="true" />
								</td>
							</tr>
							<tr>
								<td>Devoluciones</td>
								<td>
									<s:textfield id="cargoDevo" name="cargoDevo" key="cargoDevo" value="%{#factura.cargoDevo}" size="4" cssClass="inputCargos" onkeyup="calcularCargos();" onkeypress="return validarNumerosDecimales('cargoDevo', event);" />
								</td>
								<td>
									<s:textfield id="ivacd" name="ivacd" key="ivacargo3" size="4" value="%{#factura.ivaCargoDevo}" cssClass="inputCargos" onkeyup="calcularCargos();" onkeypress="return validarNumerosDecimales('ivacd', event);" />
								</td>
								<td>
									<s:textfield id="totalCargoDevo" name="totalCargoDevo" key="totalCargoDevo" cssClass="inputCargos" value="%{#factura.totalCargoDevo}" size="4" readonly="true" />
								</td>
							</tr>
						</tbody>
					</table>
					<button id="bot_insertarCargos" class="i_tick icon verdeOpilion botonBotonera" onClick="javascript:aceptarCargos();">Aceptar</button>
				</div> <!-- end div cargos -->
				<div id="contenedorTotalesFactura" style="width: 70%; position: relative; float: right;">
					<table>
						<thead>
							<tr>
								<th style="width: 80px;">SUBTOTAL</th>
								<th style="width: 120px;">Valor IVA</th>
								<th style="width: 120px;">Valor cargos</th>
								<th style="width: 120px;">Descuento (%)</th>
								<th style="width: 120px;">Valor descuento</th>
								<th style="width: 80px;">TOTAL</th>
							</tr>
						</thead>
						<tbody>
							<tr style="height: 45px;">
								<td style="vertical-align: middle; text-align: right;">
									<span id="spanSubtotal"><s:property value="%{#factura.subtotal}" /></span>
								</td>
								<td style="vertical-align: middle; text-align: right;">
									<span id="spanValorIva"><s:property value="%{#factura.valorIva}" /></span>
								</td>
								<td style="vertical-align: middle; text-align: right;">
									<span id="spanValorCargos"><s:property value="%{#factura.cargosTotal}" /></span>
								</td>
								<td onclick="javascript:descuento();" style="background-color: #EFF8FB; vertical-align: middle; text-align: right;">
									<span id="spanDescuento"><s:property value="%{#factura.descuento}" /></span>
									<s:textfield id="textDescuento" value="%{#factura.descuento}" cssStyle="display: none; vertical-align: middle; text-align: right;" onblur="javascript:descuentoModificado();" onkeypress="return validarNumerosDecimales('textDescuento', event);" />
								</td>
								<td style="vertical-align: middle; text-align: right;">
									<span id="spanValorDescuento"><s:property value="%{#factura.valorDescuento}" /></span>
								</td>
								<td style="vertical-align: middle; text-align: right; background-color: yellow; width: 130px;">
									<span id="spanImporteTotal"><s:property value="%{#factura.importeTotal}" /></span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="contenedorIvaAplicable" style="height: 50px; margin-top: 40px; text-align: center;" onclick="javascript:modificarIva();">
					<div id="divIva"><span>I.V.A. aplicable: </span><span id="spanIvaAplicable"><s:property value="%{#factura.ivaAplicable}" /></span><s:textfield id="nuevoIva" value="%{#factura.ivaAplicable}" cssStyle="display: none;  float: left; margin-left: 175px; margin-top: -24px; position: absolute; width: 35px;" onblur="javascript:ivaModificado();" onkeypress="return validarSoloNumeros(event);" /><span>%</span></div>
				</div>
				<div id="contenedorCuotas" style="display: block;">
					<table id="tablaCuotas">
						<thead>
							<tr>
								<th style="width: 40px;">N&uacute;mero</th>
								<th style="width: 120px;">Fecha cobro</th>
								<th style="width: 40px;">%</th>
								<th style="width: 50px;">Importe</th>
								<th style="width: 400px;">Observaciones</th>
							</tr>
						</thead>
						<tbody id="tbodyCuotas">
							<s:iterator id="cuotas" value="%{#factura.cuotas}">
								<tr id="lineaCuota_<s:property value="%{#cuotas.numeroCuota}" />" class="lineaCuota" style="height: 45px;">
									<td style="vertical-align: middle;">
										<span id="indiceCuota_<s:property value="%{#cuotas.numeroCuota}" />"><s:property value="%{#cuotas.numeroCuota}" /></span>
									</td>
									<td id="celdaFechaCuota_<s:property value="%{#cuotas.numeroCuota}" />" onclick="javascript:celdaFechaCuota('<s:property value="%{#cuotas.numeroCuota}" />');" style="vertical-align: middle; background-color: #EFF8FB;">
										<input id="textFechaCuota_<s:property value="%{#cuotas.numeroCuota}" />" key="textFechaCuota_<s:property value="%{#cuotas.numeroCuota}" />" class="date" label="Fecha" style="display: none;" data-format="dd-mm-yyyy" onchange="javascript:fechaCuotaModificada('<s:property value="%{#cuotas.numeroCuota}" />');" onblur="javascript:fechaCuotaModificada('<s:property value="%{#cuotas.numeroCuota}" />');" value="<s:property value="%{#cuotas.fecha}" />" />
										<span id="spanFechaCuota_<s:property value="%{#cuotas.numeroCuota}" />"><s:property value="%{#cuotas.fecha}" /></span>
									</td>
									<td id="tdPorcentajeCuota_<s:property value="%{#cuotas.numeroCuota}" />" onclick="javascript:celdaPorcentajeCuota('<s:property value="%{#cuotas.numeroCuota}" />');" style="vertical-align: middle; background-color: #EFF8FB;">
										<span id="spanPorcentajeCuota_<s:property value="%{#cuotas.numeroCuota}" />" class="porcentajesCuotas"><s:property value="%{#cuotas.porcentaje}" /></span>
										<input id="textPorcentajeCuota_<s:property value="%{#cuotas.numeroCuota}" />" style="display: none; vertical-align: middle; width: 92%;" onblur="javascript:porcentajeCuotaModificado('<s:property value="%{#cuotas.numeroCuota}" />');" onkeypress="return validarNumerosDecimales('textPorcentajeCuota_<s:property value="%{#cuotas.numeroCuota}" />', event);" value="<s:property value="%{#cuotas.porcentaje}" />" />
									</td>
									<td id="tdImporteCuota_<s:property value="%{#cuotas.numeroCuota}" />" onclick="javascript:celdaImporteCuota('<s:property value="%{#cuotas.numeroCuota}" />');" style="vertical-align: middle; background-color: #EFF8FB;">
										<span id="spanImporteCuota_<s:property value="%{#cuotas.numeroCuota}" />" class="importesCuotas"><s:property value="%{#cuotas.importe}" /></span>
										<input id="textImporteCuota_<s:property value="%{#cuotas.numeroCuota}" />" style="display: none; vertical-align: middle; width: 92%;" onblur="javascript:importeCuotaModificado('<s:property value="%{#cuotas.numeroCuota}" />');" onkeypress="return validarNumerosDecimales('textImporteCuota_<s:property value="%{#cuotas.numeroCuota}" />', event);" value="<s:property value="%{#cuotas.importe}" />" />
									</td>
									<td id="tdObservacionesCuota_<s:property value="%{#cuotas.numeroCuota}" />" onclick="javascript:celdaObservacionesCuota('<s:property value="%{#cuotas.numeroCuota}" />');" style="vertical-align: middle; background-color: #EFF8FB; max-width: 400px;">
										<span id="spanObservacionesCuota_<s:property value="%{#cuotas.numeroCuota}" />" class="unidadesLinea"><s:property value="%{#cuotas.observaciones}" /></span>
										<input id="textObservacionesCuota_<s:property value="%{#cuotas.numeroCuota}" />" style="display: none; vertical-align: middle; width: 92%;" onblur="javascript:observacionesModificadasCuota('<s:property value="%{#cuotas.numeroCuota}" />');" value="<s:property value="%{#cuotas.observaciones}" />" />
									</td>
									<td style="vertical-align: middle; width: 15px;" class="celdaEliminarCuota">
										<a id="elimina_<s:property value="%{#cuotas.numeroCuota}" />" title="Eliminar esta cuota" href="javascript:eliminaCuota('<s:property value="%{#cuotas.numeroCuota}" />')">
											<img title="Eliminar esta cuota" alt="Eliminar esta cuota" src="img/cancel.png">
										</a>
									</td>
								</tr>
							</s:iterator>
							<tr id="filaTotalesCuotas">
								<td colspan="2" style="background-color: yellow;">Totales cuotas</td>
								<td><span id="totalPorcentajeCuotas">100</span></td>
								<td><span id="totalImporteCuotas"><s:property value="%{#factura.importeTotal}" /></span></td>
								<td colspan="2" style="display: none;"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<span>Observaciones:</span><br />
				<textarea id="observaciones" name="observaciones" readonly="true"><s:property value="%{#factura.observaciones}" /></textarea>
			</s:iterator>
		</div> <!-- END CONTENEDOR ALBARAN -->
	</s:form>
	<div id="ocultos" style="display: none;">
		<input id="fechaGenerico" label="Fecha" style="display: none;" />
	</div>
</s:i18n>