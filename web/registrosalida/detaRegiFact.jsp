<%@ page contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Generar factura<span class="screenCode">FACT_01</span></h3>
	<s:form id="formulario" name="formulario" action="#" validate="true">
		<s:iterator id="albaran" value="%{#session.albaran}" >
			<div id="contenedorAlbaran">
				<div style="margin-left: 100px; margin-top: 20px; width: 200px; position: relative; float: left;">
					<img src="img/logo_tierrina_2.gif" style="width: 200px; height: 100px;"/>
				</div>
				<s:iterator id="factura" value="%{#session.factura}" >
					<s:hidden id="idDestinoFactura" value="%{#factura.idDestino}" />
					<s:hidden id="idFormaPago" value="%{#albaran.idFormaPago}" />
					<div id="contenedorInfoAlbaran" style="position: relative; margin-left: 460px; width: 50%;">
						<fieldset>
							<section>
								<label for="text_numeroAlbaran">N&uacute;mero</label>
								<div>
									<s:property value="%{#factura.idFactura}"/>
								</div>
							</section>
							<section>
								<label for="text_codigoFactura">C&oacute;digo</label>
								<div>
									<span id="spanCodigoFactura"><s:property value="%{#factura.codigoFactura}"/></span>
								</div>
							</section>
							<section onclick="javascript:modificaFechaVencimiento();" style="height: 45px;">
								<label for="date_fecha">Fecha</label>
								<div>
									<span id="spanFechaVencimiento"><s:property value="%{#albaran.fechaVencimiento}"/></span>
									<input id="date_fecha" key="date_fecha" label="Fecha de vencimiento" type="text" class="date" data-format="dd-mm-yyyy" onchange="javascript:fechaVencimientoModificada();" onblur="javascript:fechaVencimientoModificada();" style="display: none;" value="<s:property value="%{#albaran.fechaVencimiento}"/>"/>
								</div>
							</section>
						</fieldset>
					</div> <!-- END CONTENEDOR INFO ALBARAN -->
				</s:iterator>
				<s:hidden id="codigoAlbaran" value="%{#albaran.codigoAlbaran}"/>
				<div id="contenedorInfoCliente" style="position: relative; width: 59%;">
					<fieldset>
						<label>Cliente</label>
						<section onclick="javascript:modificaNombreCliente();" style="height: 50px;">
							<label for="text_nombreCliente">Nombre</label>
							<div>
								<s:textfield id="text_nombreCliente" key="text_nombreCliente" label="Nombre cliente" value="%{#albaran.nombreCliente}" cssStyle="display: none;" onblur="javascript:nombreClienteModificado();"/>
								<span id="spanNombreCliente"><s:property value="%{#albaran.nombreCliente}"/></span>
							</div>
						</section>
						<section style="width: 50%; height: 50px;">
							<label for="text_idCliente">Id</label>
							<div style="width: 52%;">
								<span id="spanIdCliente"><s:property value="%{#albaran.idCliente}"/></span>
							</div>
						</section>
						<section onclick="javascript:modificaNifCliente();" style="width: 50%; height: 50px;">
							<label for="text_nifCliente">NIF</label>
							<div style="width: 50%;">
								<s:textfield id="text_nifCliente" key="text_nifCliente" label="%{getText('exit.albaran')}" value="%{#albaran.nifCliente}" cssStyle="display: none;" onblur="javascript:nifClienteModificado();"/>
								<span id="spanNifCliente"><s:property value="%{#albaran.nifCliente}"/></span>
							</div>
						</section>
						<section onclick="javascript:modificaTelefono();" style="height: 50px;">
							<label for="text_telefono">Tel&eacute;fono</label>
							<div>
								<s:textfield id="text_telefono" key="text_telefono" label="Numero de telefono" value="%{#albaran.numeroTelefono}" cssStyle="display: none;" onblur="javascript:telefonoModificado();" />
								<span id="spanTelefono"><s:property value="%{#albaran.numeroTelefono}"/></span>
							</div>
						</section>
					</fieldset>
				</div> <!-- END CONTENEDOR INFO ALBARAN -->
				<div id="contenedorInfoDirEntregaFormaPago" style="float: right; margin-right: 23px; margin-top: -160px; position: relative; width: 39%;">
					<fieldset>
						<section>
							<label for="dropdown_direccionFacturacion" onclick="javascript:modificaDireccionFacturacion();">Direcci&oacute;n de facturaci&oacute;n</label>
							<div>
								<div id="divDropdownDireccionFacturacion" style="display: block;">
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
							<label for="text_formaPago">Forma de pago</label>
							<div>
								<!--<s:textfield id="text_formaPago" key="text_formaPago" label="Forma de pago" value="%{#albaran.descripcionFormaPago}" cssStyle="display: none;" onblur="javascript:formaPagoModificada();"/>-->
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
								<span id="spanFormaPago"><s:property value="%{#albaran.descripcionFormaPago}"/></span>
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
							<s:iterator id="lineas" value="%{#albaran.graneles}">
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
							<s:iterator id="lineas" value="%{#albaran.itemsAgrupaciones}">
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
									<s:textfield id="cargoTran" name="cargoTran" cssClass="inputCargos" key="cargoTran" value="0" size="4" onkeyup="calcularCargos();" onkeypress="return validarNumerosDecimales('cargoTran', event);" />
								</td>
								<td>
									<s:textfield id="ivact" name="ivact" key="ivacargo1" cssClass="inputCargos" size="4" value="0" onkeyup="calcularCargos();" onkeypress="return validarNumerosDecimales('ivact', event);" />
								</td>
								<td>
									<s:textfield id="totalCargoTran" name="totalCargoTran" key="totalCargoTran" cssClass="inputCargos" value="0" size="4" readonly="true"/>
								</td>
							</tr>
							<tr>
								<td>Bancos</td>
								<td>
									<s:textfield id="cargoBanc" name="cargoBanc" key="cargoBanc" value="0" cssClass="inputCargos" size="4" onkeyup="calcularCargos();" onkeypress="return validarNumerosDecimales('cargoBanc', event);" />
								</td>
								<td>
									<s:textfield id="ivacb" name="ivacb" key="ivacargo2" size="4" value="0" cssClass="inputCargos" onkeyup="calcularCargos();" onkeypress="return validarNumerosDecimales('ivacb', event);" />
								</td>
								<td>
									<s:textfield id="totalCargoBanc" name="totalCargoBanc" key="totalCargoBanc" cssClass="inputCargos" value="0" size="4" readonly="true"/>
								</td>
							</tr>
							<tr>
								<td>Devoluciones</td>
								<td>
									<s:textfield id="cargoDevo" name="cargoDevo" key="cargoDevo" value="0" size="4" cssClass="inputCargos" onkeyup="calcularCargos();" onkeypress="return validarNumerosDecimales('cargoDevo', event);" />
								</td>
								<td>
									<s:textfield id="ivacd" name="ivacd" key="ivacargo3" size="4" value="0" cssClass="inputCargos" onkeyup="calcularCargos();" onkeypress="return validarNumerosDecimales('ivacd', event);" />
								</td>
								<td>
									<s:textfield id="totalCargoDevo" name="totalCargoDevo" key="totalCargoDevo" cssClass="inputCargos" value="0" size="4" readonly="true"/>
								</td>
							</tr>
						</tbody>
					</table>
					<button id="bot_insertarCargos" class="i_tick icon verdeOpilion" onClick="javascript:aceptarCargos();">Aceptar</button>
				</div> <!-- end div cargos -->
				<div id="contenedorTotalesAlbaran" style="width: 70%; position: relative; float: right;">
					<table>
						<thead>
							<tr>
								<!--<th style="width: 80px;">Peso total</th>
								<th style="width: 80px;">Unidades total</th>-->
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
								<!-- <td style="vertical-align: middle; text-align: right;">
									<span id="spanPesoTotal"><s:property value="%{#albaran.pesoNetoTotal}"/></span>
								</td>
								<td style="vertical-align: middle; text-align: right;">
									<span id="spanUnidadesTotal"><s:property value="%{#albaran.cantidadTotal}"/></span>
								</td>-->
								<td style="vertical-align: middle; text-align: right;">
									<span id="spanSubtotal"><s:property value="%{#albaran.importeTotal}"/></span>
								</td>
								<td style="vertical-align: middle; text-align: right;">
									<span id="spanValorIva">0</span>
								</td>
								<td style="vertical-align: middle; text-align: right;">
									<span id="spanValorCargos">0</span>
								</td>
								<td onclick="javascript:descuento();" style="background-color: #EFF8FB; vertical-align: middle; text-align: right;">
									<span id="spanDescuento">0</span>
									<s:textfield id="textDescuento" value="0" cssStyle="display: none; vertical-align: middle; text-align: right;" onblur="javascript:descuentoModificado();" onkeypress="return validarNumerosDecimales('textDescuento', event);"/>
								</td>
								<td style="vertical-align: middle; text-align: right;">
									<span id="spanValorDescuento">0</span>
								</td>
								<td style="vertical-align: middle; text-align: right; background-color: yellow; width: 130px;">
									<span id="spanImporteTotal"><s:property value="%{#albaran.importeTotal}"/></span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="contenedorIvaAplicable" style="height: 50px; margin-top: 40px; text-align: center;" onclick="javascript:modificarIva();">
					<div id="divIva"><span>I.V.A. aplicable: </span><span id="spanIvaAplicable"><s:property value="%{#albaran.ivaAplicable}"/></span><s:textfield id="nuevoIva" value="%{#albaran.ivaAplicable}" cssStyle="display: block;  float: left; margin-left: 300px; margin-top: -24px; position: absolute; width: 35px;" onblur="javascript:ivaModificado();" onkeypress="return validarSoloNumeros(event);"/><span>%</span></div>
				</div>
				<div id="contenedorCuotas" style="display: none;">
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
							<tr id="filaTotalesCuotas">
								<td colspan="2" style="background-color: yellow;">Totales cuotas</td>
								<td><span id="totalPorcentajeCuotas">0</span></td>
								<td><span id="totalImporteCuotas">0</span></td>
								<td colspan="2" style="display: none;"></td>
							</tr>
						</tbody>
					</table>
				</div>
			<textarea id="observaciones" name="observaciones"><s:property value="%{#albaran.observaciones}"/></textarea>
		</div> <!-- END CONTENEDOR ALBARAN -->
		</s:iterator>
	</s:form>
	<div id="ocultos" style="display: none;">
		<input id="fechaGenerico" label="Fecha" style="display: none;" />
	</div>
</s:i18n>