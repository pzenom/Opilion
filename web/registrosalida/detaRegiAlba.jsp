<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<s:iterator id="albaran" value="%{#session.albaran}" >
		<h3 class="handle">Editar albar&aacute;n: <span id="spanCodigoAlbaran"><s:property value="%{#albaran.codigoAlbaran}"/></span><span class="screenCode">EDIT_ALBA</span></h3>
		<s:form id="formulario" name="formulario" action="#" validate="true">
			<s:hidden id="idTransportista" value="%{#albaran.idTransportista}" />
			<s:hidden id="idTelefono" value="%{#albaran.idTelefono}" />
			<s:hidden id="idDireccionCliente" value="%{#albaran.idDireccionCliente}" />
			<s:hidden id="idDireccionEntrega" value="%{#albaran.destino}" />
			<s:hidden id="idDatoBancario" value="%{#albaran.idFormaPago}" />
			<s:hidden id="idPortes" value="%{#albaran.idPortesTransporte}" />
			<s:hidden id="idTemperatura" value="%{#albaran.idTemperaturaTransporte}" />
			<div id="contenedorAlbaran">
				<div id="contenedorInfoCliente" style="position: relative; width: 48%; float: left;">
					<fieldset>
						<label>Datos cliente</label>
						<section onclick="javascript:modificaNombreCliente();" style="height: 50px;">
							<label for="text_nombreCliente">Nombre</label>
							<div>
								<s:textfield id="text_nombreCliente" key="text_nombreCliente" label="Nombre cliente" value="%{#albaran.nombreCliente}" cssStyle="display: none;" onblur="javascript:nombreClienteModificado();"/>
								<span id="spanNombreCliente"><s:property value="%{#albaran.nombreCliente}"/></span>
							</div>
						</section>
						<section onclick="javascript:modificaIdCliente();" style="width: 50%; height: 50px;">
							<label for="text_idCliente">Id</label>
							<div style="width: 52%;">
								<s:textfield id="text_idCliente" key="text_idCliente" label="%{getText('exit.albaran')}" value="%{#albaran.idCliente}" cssStyle="display: none;" onblur="javascript:idClienteModificado();"/>
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
						<section style="height: 50px;">
							<label for="dropdown_telefono" onclick="javascript:modificaTelefono();">Tel&eacute;fono</label>
							<div>
								<div id="divDropdownTelefono" style="display: none;">
									<select id="dropdown_telefono" label="Numero de telefono" value="<s:property value="%{#albaran.idTelefono}"/>" onchange="javascript:telefonoModificado();" onblur="javascript:telefonoModificado();">
										<option value="0">Seleccione un tel&eacute;fono</option>
										<optgroup label="Tel&eacute;fonos">
											<s:iterator id="tfnos" value="%{#session.telefonos}">
												<option value="<s:property value="idTelefono" />">
													<s:property value="%{#tfnos.numero}" />
												</option>
											</s:iterator>
										</optgroup>
									</select>
								</div>
								<span id="spanTelefono" onclick="javascript:modificaTelefono();"></span>
							</div>
						</section>
						<section style="height: 50px;">
							<label for="dropdown_direcciones" onclick="javascript:modificaDireccionCliente();">Direcci&oacute;n</label>
							<div>
								<div id="divDropdownDirecciones" style="display: none;">
									<select id="dropdown_direcciones" label="Direccion" value="<s:property value="%{#albaran.idDireccionCliente}"/>" onchange="javascript:direccionClienteModificada();" onblur="javascript:direccionClienteModificada();">
										<option value="0">Seleccione una direcci&oacute;n</option>
										<optgroup label="Direcciones">
											<s:iterator id="dirs" value="%{#session.direccionesFacturacion}">
												<option value="<s:property value="idDireccion" />">
													<s:property value="%{#dirs.nombreCalle}" />. <s:property value="%{#dirs.localidad}" />
												</option>
											</s:iterator>
										</optgroup>
									</select>
								</div>
								<span id="spanDireccionCliente" onclick="javascript:modificaDireccionCliente();"></span>
							</div>
						</section>
						<section id="sectionFormaPago" style="height: 50px;">
							<label for="dropdown_formasPago" onclick="javascript:modificaFormaPago();">Forma de pago</label>
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
								<span id="spanFormaPago" onclick="javascript:modificaFormaPago();"></span>
								<s:textfield id="textoFormaPago" cssStyle="display: none; text-align: left;" onblur="javascript:textoFormaPagoModificado2();" />
							</div>
						</section>
						<section style="height: 50px !important;" onclick="javascript:modificaFechaVencimiento();">
							<label for="date_fechaVencimiento">Fecha vencimiento</label>
							<div>
								<s:textfield id="date_fechaVencimiento" key="date_fechaVencimiento" value="%{#albaran.fechaVencimiento}" cssStyle="display: none;" cssClass="date" onchange="javascript:fechaVencimientoModificada();" onblur="javascript:fechaVencimientoModificada();"/>
								<span id="spanFechaVencimiento"><s:property value="%{#albaran.fechaVencimiento}"/></span>
							</div>
						</section>
					</fieldset>
				</div> <!-- END CONTENEDOR INFO ALBARAN -->
				<div id="contenedorInfoDirEntregaFormaPago" style="width: 50%; position: relative; float: right;">
					<fieldset>
						<label>Datos entrega</label>
						<section tyle="height: 50px;">
							<label for="text_nombreCliente">Nombre</label>
							<div>
								<span id="spanNombreCliente"><s:property value="%{#albaran.descripcionNombreEntrega}"/></span>
							</div>
						</section>
						<section style="height: 50px;">
							<label for="dropdown_direccionesEntrega" onclick="javascript:modificaDireccionEntrega();">Direcci&oacute;n</label>
							<div>
								<div id="divDropdownDireccionesEntrega" style="display: none;">
									<select id="dropdown_direccionesEntrega" label="Direccion" value="<s:property value="%{#albaran.destino}"/>" onchange="javascript:direccionEntregaModificada();" onblur="javascript:direccionEntregaModificada();">
										<option value="0">Seleccione una direcci&oacute;n</option>
										<optgroup label="Direcciones">
											<s:iterator id="dirs" value="%{#session.direccionesEntrega}">
												<option value="<s:property value="idDireccion" />" class="<s:property value="horario" />">
													<s:property value="%{#dirs.nombreCalle}" />. <s:property value="%{#dirs.localidad}" />
												</option>
											</s:iterator>
										</optgroup>
									</select>
								</div>
								<span id="spanDireccionEntrega" onclick="javascript:modificaDireccionEntrega();"></span>
							</div>
						</section>
						<section style="height: 50px;">
							<label for="dropdown_transportistas" onclick="javascript:modificaTransportista();">Transportista</label>
							<div>
								<div id="divDropdownTransportista" style="display: none;">
									<select id="dropdown_transportistas" label="Transportista" value="<s:property value="%{#albaran.idTransportista}"/>" onchange="javascript:transportistaModificado();" onblur="javascript:transportistaModificado();">
										<option value="0">Seleccione un transportista</option>
										<optgroup label="Transportistas">
											<s:iterator id="transportistas" value="%{#session.listatransportistas}">
												<option value="<s:property value="idUsuario" />">
													<s:property value="%{#transportistas.nombre}" />
												</option>
											</s:iterator>
										</optgroup>
									</select>
								</div>
								<span id="spanNombreTransportista" onclick="javascript:modificaTransportista();"></span>
							</div>
						</section>
						<section style="height: 50px;">
							<label for="dropdown_temperaturas" onclick="javascript:modificaTemperatura();">Temperatura</label>
							<div>
								<div id="divDropdownTemperatura" style="display: none;">
									<select id="dropdown_temperaturas" label="Temperatura" value="<s:property value="%{#albaran.idTemperaturaTransporte}"/>" onchange="javascript:temperaturaModificada();" onblur="javascript:temperaturaModificada();">
										<option value="0">Seleccione la temperatura</option>
										<optgroup label="Temperaturas">
											<s:iterator id="temperaturas" value="%{#session.listaTemperaturas}">
												<option value="<s:property value="idTemperatura" />">
													<s:property value="%{#temperaturas.descripcion}" />
												</option>
											</s:iterator>
										</optgroup>
									</select>
								</div>
								<span id="spanTemperatura" onclick="javascript:modificaTemperatura();"></span>
							</div>
						</section>
						<section style="height: 50px;">
							<label for="dropdown_portes" onclick="javascript:modificaPortes();">Portes</label>
							<div>
								<div id="divDropdownPortes" style="display: none;">
									<select id="dropdown_portes" label="Portes" value="<s:property value="%{#albaran.idPortesTransporte}"/>" onchange="javascript:portesModificados();" onblur="javascript:portesModificados();">
										<option value="0">Seleccione los portes</option>
										<optgroup label="Portes">
											<s:iterator id="portes" value="%{#session.listaPortes}">
												<option value="<s:property value="idPorte" />">
													<s:property value="%{#portes.descripcion}" />
												</option>
											</s:iterator>
										</optgroup>
									</select>
								</div>
								<span id="spanPortes" onclick="javascript:modificaPortes();"></span>
							</div>
						</section>
						<section style="height: 50px !important;" class="sectionMitad" onclick="javascript:modificaFechaEntrega();">
							<label for="date_fechaEntrega">Fecha</label>
							<div>
								<s:textfield id="date_fechaEntrega" key="date_fechaEntrega" value="%{#albaran.fechaEntrega}" cssStyle="display: none;" cssClass="date" onchange="javascript:fechaEntregaModificada();" onblur="javascript:fechaEntregaModificada();"/>
								<span id="spanFechaEntrega"><s:property value="%{#albaran.fechaEntrega}"/></span>
							</div>
						</section>
						<section class="sectionMitad" onclick="javascript:modificaHorarioEntrega();" style="height: 50px;">
							<label for="text_horario">Horario</label>
							<div>
								<s:textfield id="text_horario" key="text_horario" value="%{#albaran.horarioEntrega}" cssStyle="display: none;" onchange="javascript:horarioEntregaModificado();" onblur="javascript:horarioEntregaModificado();" />
								<span id="spanHorario"><s:property value="%{#albaran.horarioEntrega}" /></span>
							</div>
						</section>
						<section onclick="javascript:modificaNumeroPedido();" style="height: 50px;">
							<label for="text_nPedido">Pedido</label>
							<div id="div_text_nPedido">
								<s:textfield id="text_nPedido" key="text_nPedido" label="%{getText('exit.albaran')}" value="%{#albaran.codigoOrden}" cssStyle="display: none;" onblur="javascript:numeroPedidoModificado();"/>
								<span id="spanNPedido"><s:property value="%{#albaran.codigoOrden}"/></span>
							</div>
						</section>
					</fieldset>
				</div> <!-- END CONTENEDOR INFO ALBARAN -->
				<div id="contenedorLineasAlbaran" style=" clear: left; padding-top: 1%;">
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
							<s:iterator id="lineas" value="%{#albaran.graneles}" >
								<tr style="height: 45px;">
									<s:hidden id="%{#lineas.numLinea}_%{#lineas.idBulto}" cssClass="linea" value="%{#lineas.numLinea}_%{#lineas.idBulto}" />
									<td style="vertical-align: middle;">
										<s:property value="%{#lineas.cantidad}"/>
									</td>
									<td style="vertical-align: middle;">
										<s:property value="%{#lineas.codigoEan}"/>
									</td>
									<!--<td style="background-color: #EFF8FB; vertical-align: middle;" onclick="javascript:celdaDescripcion(<s:property value="%{#lineas.numLinea}"/>, <s:property value="%{#lineas.idBulto}"/>);"> -->
									<td style="vertical-align: middle;">
										<span id="spanDescripcion_<s:property value="%{#lineas.numLinea}"/>_<s:property value="%{#lineas.idBulto}"/>"><s:property value="%{#lineas.descripcion}"/></span>
										<s:textfield id="textDescripcion_%{#lineas.numLinea}_%{#lineas.idBulto}" value="%{#lineas.descripcion}" cssStyle="display: none;" onblur="javascript:descripcionModificada(%{#lineas.numLinea}, %{#lineas.idBulto});"/>
									</td>
									<td style="vertical-align: middle;">
										<s:property value="%{#lineas.lote}"/>
									</td>
									<td style="vertical-align: middle;">
										<span id="spanPesoLinea_<s:property value="%{#lineas.numLinea}"/>_<s:property value="%{#lineas.idBulto}"/>"><s:property value="%{#lineas.pesoNeto}"/></span>
									</td>
									<td style="background-color: #EFF8FB; vertical-align: middle;" onclick="javascript:celdaPrecio(<s:property value="%{#lineas.numLinea}"/>, <s:property value="%{#lineas.idBulto}"/>);">
										<span id="spanPrecio_<s:property value="%{#lineas.numLinea}"/>_<s:property value="%{#lineas.idBulto}"/>"><s:property value="%{#lineas.precioUnitario}"/></span>
										<s:textfield id="textPrecio_%{#lineas.numLinea}_%{#lineas.idBulto}" value="%{#lineas.precioUnitario}" cssStyle="display: none;" onblur="javascript:precioModificado(%{#lineas.numLinea}, %{#lineas.idBulto});" onkeypress="return validarNumerosDecimales('textPrecio_%{#lineas.numLinea}_%{#lineas.idBulto}', event);" />
									</td>
									<td style="vertical-align: middle;">
										<span id="spanPrecioLinea_<s:property value="%{#lineas.numLinea}"/>_<s:property value="%{#lineas.idBulto}"/>" class="totalesLinea"><s:property value="%{#lineas.precioTotal}"/></span>
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
							<s:iterator id="lineas" value="%{#albaran.itemsAgrupaciones}" >
								<tr style="height: 45px;">
									<s:hidden id="%{#lineas.numLinea}_%{#lineas.idBulto}" cssClass="linea" value="%{#lineas.numLinea}_%{#lineas.idBulto}" />
									<td style="vertical-align: middle;">
										<s:property value="%{#lineas.cantidad}"/>
									</td>
									<td style="vertical-align: middle;">
										<s:property value="%{#lineas.codigoEan}"/>
									</td>
									<!--<td style="background-color: #EFF8FB; vertical-align: middle;" onclick="javascript:celdaDescripcion(<s:property value="%{#lineas.numLinea}"/>, <s:property value="%{#lineas.idBulto}"/>);"> -->
									<td style="vertical-align: middle;">
										<span id="spanDescripcion_<s:property value="%{#lineas.numLinea}"/>_<s:property value="%{#lineas.idBulto}"/>"><s:property value="%{#lineas.descripcion}"/></span>
										<s:textfield id="textDescripcion_%{#lineas.numLinea}_%{#lineas.idBulto}" value="%{#lineas.descripcion}" cssStyle="display: none;" onblur="javascript:descripcionModificada(%{#lineas.numLinea}, %{#lineas.idBulto});"/>
									</td>
									<td style="vertical-align: middle;">
										<s:property value="%{#lineas.lote}"/>
									</td>
									<td style="vertical-align: middle;">
										<span id="spanPesoLinea_<s:property value="%{#lineas.numLinea}"/>_<s:property value="%{#lineas.idBulto}"/>"><s:property value="%{#lineas.pesoNeto}"/></span>
									</td>
									<td style="background-color: #EFF8FB; vertical-align: middle;" onclick="javascript:celdaPrecio(<s:property value="%{#lineas.numLinea}"/>, <s:property value="%{#lineas.idBulto}"/>);">
										<span id="spanPrecio_<s:property value="%{#lineas.numLinea}"/>_<s:property value="%{#lineas.idBulto}"/>"><s:property value="%{#lineas.precioUnitario}"/></span>
										<s:textfield id="textPrecio_%{#lineas.numLinea}_%{#lineas.idBulto}" value="%{#lineas.precioUnitario}" cssStyle="display: none;" onblur="javascript:precioModificado(%{#lineas.numLinea}, %{#lineas.idBulto});" onkeypress="return validarNumerosDecimales('textPrecio_%{#lineas.numLinea}_%{#lineas.idBulto}', event);" />
									</td>
									<td style="vertical-align: middle;">
										<span id="spanPrecioLinea_<s:property value="%{#lineas.numLinea}"/>_<s:property value="%{#lineas.idBulto}"/>" class="totalesLinea"><s:property value="%{#lineas.precioTotal}"/></span>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div id="contenedorTotalesAlbaran" style="width: 50%; position: relative; float: right;">
					<table>
						<thead>
							<tr>
								<th style="width: 80px;">Peso total</th>
								<th style="width: 80px;">Unidades total</th>
								<th style="width: 80px;">Importe total</th>
							</tr>
						</thead>
						<tbody>
							<tr style="height:45px;">
								<td style="vertical-align: middle; ">
									<span id="spanPesoTotal"><s:property value="%{#albaran.pesoNetoTotal}"/></span>
								</td>
								<td style="vertical-align: middle; ">
									<span id="spanUnidadesTotal"><s:property value="%{#albaran.cantidadTotal}"/></span>
								</td>
								<td style="vertical-align: middle; ">
									<span id="spanImporteTotal"><s:property value="%{#albaran.importeTotal}"/></span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="contenedorIvaAplicable" style="height: 50px; margin-top: 40px; text-align: center;" onclick="javascript:modificarIva();">
					<div id="divIva"><span>I.V.A. aplicable: </span><span id="spanIvaAplicable"><s:property value="%{#albaran.ivaAplicable}"/></span><s:textfield id="nuevoIva" value="%{#albaran.ivaAplicable}" cssStyle="display: block; float: left; margin-left: 325px; margin-top: -24px; position: absolute; width: 35px;" onblur="javascript:ivaModificado();" onkeypress="return validarSoloNumeros(event);"/><span>%</span></div>
				</div>
				<span>Observaciones:</span><br />
				<textarea id="observaciones" name="observaciones"><s:property value="%{#albaran.observaciones}"/></textarea>
			</div> <!-- END CONTENEDOR ALBARAN -->
		</s:form>
	</s:iterator>
	<div id="ocultos" style="display: none;" />
</s:i18n>