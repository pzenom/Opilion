<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Generar factura libre<span class="screenCode">FACT_LIBRE</span></h3>
	<s:form id="formulario" name="formulario" action="#" validate="true">
		<fieldset>
			<section class="sectionMitad">
				<label for="dropdown_clientes">Cliente</label>
				<div>
					<select name="dropdown_clientes" id="dropdown_clientes" onchange="javascript:seleccionCliente();">
						<option selected value="0">Seleccione un cliente</option>
						<optgroup label="Clientes">
							<s:iterator id="clientes" value="%{#session.listaclientes}">
								<option value="<s:property value="idUsuario" />_<s:property value="nif" />">
									<s:property value="nombre" />
								</option>
							</s:iterator>
						</optgroup>
					</select>
				</div>
			</section>
			<section class="sectionMitad">
				<label for="dropdown_formasDePago">Forma de pago</label>
				<div id="contenedorSelect" style="display: none;">
					<div>
						<select name="dropdown_formasDePago" id="dropdown_formasDePago" onchange="javascript:formaPagoSeleccionada();">
							<option selected value="-1">Seleccione una forma de pago</option>
							<optgroup label="Formas de pago" id="optgroupFormasPago">
							</optgroup>
						</select>
					</div>
				</div>
				<div id="contenedorTexto">
					<p id="textoSeleccioneCliente" style="color: red;">Seleccione un cliente</p>
				</div>
			</section>
			<section class="sectionMitad">
				<label for="dropdown_direcciones">Direcciones</label>
				<div id="contenedorSelectDirecciones" style="display: none;">
					<div>
						<select name="dropdown_direcciones" id="dropdown_direcciones" onchange="javascript:direccionSeleccionada();">
							<option selected value="-1">Seleccione una direcci&oacute;n de entrega</option>
							<optgroup label="Direcciones" id="optgroupDirecciones" class="direccionesCliente">
							</optgroup>
						</select>
					</div>
				</div>
				<div id="contenedorTextoDirecciones">
					<p id="textoSeleccioneClienteDirecciones" style="color: red;">Seleccione un cliente</p>
				</div>
			</section>			
		</fieldset>
		<div id="contenedorAlbaran">
			<div style="margin-left: 100px; margin-top: 20px; width: 200px; position: relative; float: left;">
				<img src="img/logo_tierrina_2.gif" style="width: 200px; height: 100px;"/>
			</div>
			<div id="contenedorInfoAlbaran" style="position: relative; margin-left: 460px; width: 50%;">
				<fieldset>
					<section>
						<label for="spanNumeroFactura">N&uacute;mero</label>
						<div>
							<span id="spanNumeroFactura"><s:property value="%{#session.numeroFactura}"/></span>
						</div>
					</section>
					<section>
						<label for="spanCodigoFactura">C&oacute;digo</label>
						<div>
							<span id="spanCodigoFactura"><s:property value="%{#session.codigoFactura}"/></span>
						</div>
					</section>
					<section onclick="javascript:modificaFechaVencimiento();">
						<label for="date_fecha">Fecha</label>
						<div>
							<input id="date_fecha" name="date_fecha" type="text" class="date" data-format="yyyy-mm-dd" style="display: none;" onchange="javascript:fechaVencimientoModificada();">
							<span id="spanFechaVencimiento"></span>
						</div>
					</section>
				</fieldset>
			</div> <!-- END CONTENEDOR INFO ALBARAN -->
			<div id="contenedorInfoCliente" style="position: relative; width: 40%;">
				<fieldset>
					<label>Cliente</label>
					<section onclick="javascript:modificaNombreCliente();" style="height: 50px;">
						<label for="text_nombreCliente">Nombre</label>
						<div>
							<s:textfield id="text_nombreCliente" key="text_nombreCliente" label="Nombre cliente" value="" cssStyle="display: none;" onblur="javascript:nombreClienteModificado();"/>
							<span id="spanNombreCliente"></span>
						</div>
					</section>
					<section onclick="javascript:modificaIdCliente();" style="width: 50%; height: 50px;">
						<label for="text_idCliente">Id</label>
						<div style="width: 52%;">
							<s:textfield id="text_idCliente" key="text_idCliente" label="%{getText('exit.albaran')}" value="" cssStyle="display: none;" onblur="javascript:idClienteModificado();"/>
							<span id="spanIdCliente"></span>
						</div>
					</section>
					<section onclick="javascript:modificaNifCliente();" style="width: 50%; height: 50px;">
						<label for="text_nifCliente">NIF</label>
						<div style="width: 50%;">
							<s:textfield id="text_nifCliente" key="text_nifCliente" label="%{getText('exit.albaran')}" value="" cssStyle="display: none;" onblur="javascript:nifClienteModificado();"/>
							<span id="spanNifCliente"></span>
						</div>
					</section>
					<section onclick="javascript:modificaTelefono();" style="height: 50px;">
						<label for="text_telefono">Tel&eacute;fono</label>
						<div>
							<s:textfield id="text_telefono" key="text_telefono" label="Numero de telefono" value="" cssStyle="display: none;" onblur="javascript:telefonoModificado();" />
							<span id="spanTelefono"></span>
						</div>
					</section>
				</fieldset>
			</div> <!-- END CONTENEDOR INFO ALBARAN -->
			<div id="contenedorInfoDirEntregaFormaPago" style="float: right; margin-right: 23px; margin-top: -160px; position: relative; width: 50%;">
				<fieldset>
					<section onclick="javascript:modificaDireccionEntrega();">
						<label for="text_direccionEntrega">Direcci&oacute;n de entrega</label>
						<div>
							<s:textfield id="text_direccionEntrega" key="text_direccionEntrega" label="Direcci&oacute;n de entrega" value="" cssStyle="display: none;" onblur="javascript:direccionEntregaModificada();"/>
							<span id="spanDireccionEntrega"></span>
						</div>
					</section>
					<section onclick="javascript:modificaFormaPago();">
						<label for="text_formaPago">Forma de pago</label>
						<div>
							<s:textfield id="text_formaPago" key="text_formaPago" label="Forma de pago" value="" cssStyle="display: none;" onblur="javascript:formaPagoModificada();"/>
							<span id="spanFormaPago"></span>
						</div>
					</section>
				</fieldset>
			</div> <!-- END CONTENEDOR INFO ALBARAN -->
			<div id="contenedorLineasFactura" style="margin-top: 20px;">
				<table>
					<thead>
						<tr>
							<th style="width: 30px;">Uds.</th>
							<th style="width: 100px;">GTIN/EAN</th>
							<th style="">Descripci&oacute;n</th>
							<th style="width: 80px;">Lote</th>
							<th style="width: 80px;">Kilos</th>
							<th style="width: 80px;" >&euro;/Kg.</th>
							<th style="width: 80px;">Importe</th>
						</tr>
					</thead>
					<tbody id="tbodyRegistrosFactura">
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
								<s:textfield id="totalCargoTran" name="totalCargoTran" key="totalCargoTran" cssClass="inputCargos" value="0" size="4" readonly="true" />
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
								<s:textfield id="totalCargoBanc" name="totalCargoBanc" key="totalCargoBanc" cssClass="inputCargos" value="0" size="4" readonly="true" />
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
				<button id="bot_insertarCargos" class="i_tick icon verdeOpilion botonBotonera" onClick="javascript:aceptarCargos();">Aceptar</button>
				<button id="bot_limpiaCargos" class="i_arrow_left icon naranjaOpilion botonBotonera" onClick="javascript:cerrar(true);">Limpiar</button>
			</div> <!-- end div cargos -->
			<div id="contenedorTotalesFactura" style="width: 50%; position: relative; float: right;">
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
								<span id="spanSubtotal">0</span>
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
								<span id="spanImporteTotal">0</span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="contenedorIvaAplicable" style="height: 50px; margin-top: 40px; text-align: center;" onclick="javascript:modificarIva();">
				<div id="divIva"><span>I.V.A. aplicable: </span><span id="spanIvaAplicable">0</span><s:textfield id="nuevoIva" value="0" cssStyle="display: block;  float: left; margin-left: 275px; margin-top: -24px; position: absolute; width: 35px;" onblur="javascript:ivaModificado();" onkeypress="return validarSoloNumeros(event);"/><span>%</span></div>
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
			<textarea id="observaciones" name="observaciones" />
		</div> <!-- END CONTENEDOR ALBARAN -->
	</s:form>
	<div id="ocultos" style="display: none;">
		<div id="formasPagoOcultas" style="display: none;"></div>
		<div id="direccionesOcultas" style="display: none;"></div>
		<select style="display: none;" id="entrega_-1"></select>
		<input id="fechaGenerico" label="Fecha" style="display: none;" />
	</div>
</s:i18n>