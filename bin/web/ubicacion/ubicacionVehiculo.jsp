<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Selecci&oacute;n de ubicacion<span class="screenCode">PLANO_VEHICULO</span></h3>
	<div id="divNecesarioWidget">
		<s:iterator id="ubicacion" value="%{#session.ubicacion}">
			<s:hidden id="hueco" key="hueco" name="hueco" value="%{#ubicacion.idHueco}" />
			<fieldset>
				<section class="sectionMitad">
					<label for="text_nombreAlmacen">Nombre del veh&iacute;culo/almac&eacute;n</label>
					<div>
						<s:textfield id="text_nombreAlmacen" disabled="true" key="almacen" label="vehiculo" cssStyle="width:180px;" value="%{#ubicacion.nombreAlmacen}"/>
					</div>
				</section>
			</fieldset>
			<hr />
		</s:iterator>
		<fieldset style="display:none;">
			<!-- <legend><span>Datos del registro a almacenar</span></legend> -->
			<table width="100%" cellpadding="2" cellspacing="2" border="0">
				<tr>
					<td class="label"><s:label name="%{getText('entry.entrada')}" value="%{getText('entry.entrada')}"/></td>
					<td nowrap="nowrap">
						<s:textfield key="codigoEntrada" value="%{#session.codigoEntrada}"cssStyle="width:180px;" label="%{getText('entry.entrada')}" disabled="true" cssStyle="text-align:right;"/>
					</td>
					<td class="label"><s:label name="%{getText('registro.label.responsable')}" value="%{getText('registro.label.responsable')}"/></td>
					<td>
						<s:textfield key="idOperario" cssStyle="width:180px;" label="%{getText('registro.label.responsable')}" value="%{#session.usuario.login}" disabled="true"/>
					</td>
				</tr>
			</table>
		</fieldset>
		<div id="registrosActuales" style="display: none;">
			<fieldset>
				<legend><span>Registros que se encuentran actualmente en el hueco</span></legend>
					<table id="tablaActual" class="display" border=0 cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th>Producto</th>
								<th style="width: 13%; ">Registro</th>
								<th>Proveedor</th>
								<th>Fecha entrada</th>
								<th>Id palet</th>
								<th>Cantidad</th>
							</tr>
						<thead>
						<tbody>
							<s:iterator id="ubicaciones" value="%{#session.ubicaciones}">
								<tr>
									<td><s:property value='nombreProducto'/></td>
									<td><s:property value='registro'/> (<s:property value='orden'/>)</td>
									<td><s:property value='proveedor'/></td>
									<td class="celdaFecha"><s:property value='fecha'/></td>
									<td><s:property value='idPalet'/></td>
									<td><span id="span_cantidad_lote_<s:property value='registro' />"><s:property value='cantidad' /></span></td>
									<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; width: 2%;">
										<a id="trazar" href="javascript:trazar2('<s:property value='registro'/>');" title="Mostrar trazabilidad">
											<img src="img/pill_go.png" alt="Pildora con flecha para ir" title="Mostrar la trazabilidad"/>
										</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
			</fieldset>
		</div>
		<div id="contenedorSacar" style="display: none;">
			<fieldset>
				<legend><span>Introduzca las cantidades que va a a sacar</span></legend>
					<table id="tablasacar" class="display" border=0 cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th>Producto</th>
								<th>Registro</th>
								<th>Proveedor</th>
								<th>Fecha entrada</th>
								<th>Id palet</th>
								<th>Cantidad</th>
								<th>Cantidad sacar</th>
							</tr>
						<thead>
						<tbody>
							<s:iterator id="ubicaciones" value="%{#session.ubicaciones}">
								<tr>
									<td><s:property value='nombreProducto'/></td>
									<td><s:property value='registro'/> (<s:property value='orden'/>)</td>
									<td><s:property value='proveedor'/></td>
									<td><s:property value='fecha'/></td>
									<td id="idPalet_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>" style="text-align: right;"><s:property value='idPalet'/></td>
									<td style="text-align: right;"><span id="span_cantidad_lote_<s:property value='registro' />"><s:property value='cantidad' /></span></td>
									<td>
										<input class="sacando" id="sacarRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>" key="sacarRegistro_<s:property value='registro'/>" name="sacarRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>" value="0" style="text-align: right; width: 88%;" onblur="javascript:ajustarDecimal('sacarRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>');" onkeypress="return validarNumerosDecimales('sacarRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>',event);" ></input>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
			</fieldset>
		</div>
		<div id="contenedorIncidencia" style="display:none;">
			<fieldset>
				<legend><span>Introduzca las cantidades sobre las que se ha producido la incidencia</span></legend>
					<table id="tablasacarIncidencia" class="display" border=0 cellpadding="2" cellspacing="2">
						<thead>
							<tr>
								<th>Producto</th>
								<th>Registro</th>
								<th>Proveedor</th>
								<th>Fecha entrada</th>
								<th>Id palet</th>
								<th>Cantidad</th>
								<th>Cantidad sacar</th>
							</tr>
						<thead>
						<tbody>
							<s:iterator id="ubicaciones" value="%{#session.ubicaciones}">
								<tr>
									<td><s:property value='nombreProducto'/></td>
									<td><s:property value='registro'/> (<s:property value='orden'/>)</td>
									<td><s:property value='proveedor'/></td>
									<td><s:property value='fecha'/></td>
									<td style="text-align: right;"><s:property value='idPalet'/></td>
									<td style="text-align: right;"><span id="span_cantidad_lote_<s:property value='registro' />"><s:property value='cantidad' /></span></td>
									<td>
										<input class="mermando" id="mermaRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>" key="mermaRegistro_<s:property value='registro'/>" name="mermaRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>" value="0" style="text-align: right; width: 88%;" onblur="javascript:ajustarDecimal('mermaRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>');" onkeypress="return validarNumerosDecimales('mermaRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>',event);" ></input>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<textarea cols="112" rows="4" id="observacionIncidencia" name="observacionIncidencia"></textarea>
			</fieldset>
		</div>
		<br />
	</div><!-- end #content_main -->
</s:i18n>