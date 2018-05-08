<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Generaci&oacute;n del albar&aacute;n<span class="screenCode">GEN_ALBA</span></h3>
	<s:form id="formulario" name="formulario" action="InseDetaAlbaOrden" validate="true">
		<s:iterator id="albaranEncabezado" value="%{#session.albaranEncabezado}" >
			<fieldset>
				<section class="sectionMitad">
					<label for="text_albaran">Albar&aacute;n</label>
					<div>
						<s:textfield id="text_albaran" key="text_albaran" label="%{getText('exit.albaran')}" value="%{#session.albaran}" readonly="true"/>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_cliente">Cliente</label>
					<div>
						<s:textfield id="text_cliente" key="text_cliente" label="%{getText('exit.cliente')}" value="%{#session.nombreCliente}" readonly="true"/>
					</div>
				</section>
				<section class="sectionTercio">
					<label for="text_responsable">Responsable</label>
					<div>
						<s:textfield id="text_responsable" key="text_responsable" label="%{getText('registro.label.responsable')}" value="%{#session.usuario.login}" readonly="true"/>
					</div>
				</section>
				<section class="sectionTercio">
					<label for="text_fecha">Fecha</label>
					<div>
						<s:textfield id="text_fecha" key="text_fecha" label="%{getText('exit.fecha')}" value="%{#session.fecharegistro}" readonly="true"/>
					</div>
				</section>
				<section class="sectionTercio">
					<label for="date_fechaVencimiento">Fecha vencimiento</label>
					<div>
						<input id="date_fechaVencimiento" name="date_fechaVencimiento" type="text" class="date" data-format="yyyy-mm-dd">
					</div>
				</section>
				<section class="sectionTercio">
					<label for="dropdown_transportistas">Transportista</label>
					<div>
						<select id="dropdown_transportistas" label="Transportista">
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
				</section>
				<section class="sectionTercio">
					<label for="dropdown_temperaturaTransporte">Temperatura transporte</label>
					<div>
						<select id="dropdown_temperaturaTransporte" label="Temperatura transporte">
							<option value="0">Seleccione la temperatura</option>
							<optgroup label="Temperatura del transporte">
								<s:iterator id="temperaturas" value="%{#session.listaTemperaturas}">
									<option value="<s:property value="idTemperatura" />">
										<s:property value="%{#temperaturas.descripcion}" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</div>
				</section>
				<section class="sectionTercio">
					<label for="dropdown_portesTransporte">Portes</label>
					<div>
						<select id="dropdown_portesTransporte" label="Portes">
							<option value="0">Seleccione los portes</option>
							<optgroup label="Portes de transporte">
								<s:iterator id="portes" value="%{#session.listaPortes}">
									<option value="<s:property value="idPorte" />">
										<s:property value="%{#portes.descripcion}" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</div>
				</section>
				<s:hidden id="cli" name="cli" value="%{#session.cliente}"/>
				<s:hidden id="idAlbaran" name="idAlbaran" value="%{#session.idAlbaran}"/>
				<s:hidden id="codigoPedido" name="codigoPedido" value="%{#session.codigoPedido}"/>
				<s:hidden id="auto" name="auto" value="true"/>
				<s:hidden id="codigoAlbaran" name="codigoAlbaran" value="%{#session.albaran}"/>
				<s:hidden id="numeroAgrupacionesTotal" name="numeroAgrupacionesTotal" />
			</fieldset>
		</s:iterator>
		<s:iterator id="pedido" value="%{#session.pedido}" >
			<s:hidden id="idFormaPago" name="idFormaPago" value="%{idFormaPago}"/>
			<fieldset>
				<div id="demo">
					<table id="tablaLineas" cellpadding="0" cellspacing="0" border="0" class="display" >
						<thead>
							<tr>
								<!--<th style="width: 30px;">L&iacute;nea</th>-->
								<th style="width: 30px;">ID</th>
								<th style="width: 25em;">Identificaci&oacute;n</th>
								<th id="thCantidades" style="width: 120px;">Cantidades solicitadas</th>
								<th>Detalle salidas</th>
								<th style="width: 100px;">Precios</th>
							</tr>
						</thead>
						<tbody id="tablaLineasBody">
							<s:iterator id="lin" value="%{lin}" >
								<tr>
									<td id="<s:property value="%{linNum}" />" class="lineas" style="display: none;"><s:property value="%{linNum}" /></td>
									<td><s:property value="%{idProducto}" /></td>
									<td id="celdaMuestraLotesProducto_<s:property value="%{linNum}" />" onmouseover="javascript:muestraLotesProducto('<s:property value="%{idProducto}" />','<s:property value="%{linNum}" />');">
										<p id="nombreProducto_<s:property value="%{linNum}" />"><s:property value="%{nombreProducto}" /></p>
										<p id="eanProducto_<s:property value="%{linNum}" />" style="background: transparent !important;"><s:property value="%{idLin}" /></p>
										<p id="idProducto_<s:property value="%{linNum}" />" style="display: none;"><s:property value="%{idProducto}" /></p>
									</td>
									<td style="width: 15em;">
										<p style="background: transparent !important;">Peso linea: <span id="kilosPedidos_<s:property value="%{linNum}" />" class="kilosPedidos"><s:property value="%{pesoLinea}" /></span></p>
										<p style="background: transparent !important;">Unidades pedidas: <span id="cantidadUnitaria_<s:property value="%{linNum}" />" class="cantidadPedida"><s:property value="%{qty21Cant}" /></span></p>
										<s:hidden id="cantidadUnitaria_%{linNum}" name="cantidadUnitaria_%{linNum}" value="%{qty21Cant}"/>
										<p style="background: transparent !important; display: none;">Agrupaciones: <span id="numBultos_<s:property value="%{linNum}" />" class="agrupacionesPedidas"><s:property value="%{numeroAgrupaciones}" /></span></p>
										<s:hidden id="bultos_%{idProducto}_%{linNum}" name="bultos_%{idProducto}_%{linNum}" cssClass="bultos" value="%{numeroAgrupaciones}"/>
									</td>
									<td id="detallesSalidas_<s:property value="%{idProducto}"/>_<s:property value="%{linNum}"/>" style="background: transparent !important; width: 30em;" class="detallesSalidas">
									</td>
									<td>
										<p style="background: transparent !important;">Neto Unitario: <span id="netoUnitario_<s:property value="%{linNum}" />"><s:property value="%{priAaa}" /></span>&euro;</p>
										<s:hidden id="netoUnitario_%{linNum}" name="netoUnitario_%{linNum}" value="%{priAaa}"/>
										<s:hidden id="brutoUnitario_%{linNum}" name="brutoUnitario_%{linNum}" value="%{priAab}"/>
										<p style="background: transparent !important;">Total neto: <span id="netoLinea_<s:property value="%{linNum}" />" class="netoLinea"><s:property value="%{moa203}" /></span>&euro;</p>
										<s:hidden id="netoLinea_%{linNum}" name="netoLinea_%{linNum}" value="%{moa203}"/>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</fieldset>
			<table class="display" border="0" width="95%">
				<tr>
					<th>Peso Neto total</th>
					<th>Numero de agrupaciones total</th>
					<th>Cantidad total</th>
					<th>Importe total</th>
				</tr>
				<s:iterator id="albaranTotales" value="%{#session.albaranTotales}" >
					<tr>
						<td id="pesoNetoTotal"></td>
						<td id="resumenNumeroAgrupacionesTotal"></td>
						<td id="cantidadTotal"></td>
						<td id="importeTotal"></td>
					</tr>
				</s:iterator>
			</table>
			<span>Observaciones:</span><br />
			<textarea id="observaciones" name="observaciones"></textarea>
			<s:hidden key="moa79" id="moa79" />
			<s:hidden key="cnt" id="cnt" />
		</s:iterator>
	</s:form>
	<div id="ocultos" style="display: none;">
		<s:iterator id="lins" value="%{#session.listaDirecciones}">
			<s:iterator id="dirs" value="%{direcciones}">
				<p style="background: transparent;">
					<b>ID: <span class="direcciones_<s:property value="%{numeroLinea}"/>"><s:property value="%{idDireccion}"/></span></b></br>
					<span id="nombreDireccion_<s:property value="%{idDireccion}"/>"><s:property value="%{calle}"/>, <s:property value="%{localidad}"/></span></br>
				</p>
				<ul id="bultosDireccion_<s:property value="%{linNum}"/>_<s:property value="%{idDireccion}"/>" style="margin-top: -8px; margin-left:35px;">
				</ul>
				<s:hidden id="bultos_%{numeroLinea}_%{idDireccion}" value="%{numeroBultos}"/>
				</br>
			</s:iterator>
		</s:iterator>
	</div>
	<div id="ajaxEstados" style="display: none;" />
</s:i18n>