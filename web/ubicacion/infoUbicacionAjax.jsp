<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Informaci&oacute;n hueco<span class="screenCode">INFO_HUECO</span></h3>
	<div id="divNecesarioWidget">
		<s:form target="ajaxDiv" id="formulario" name="formulario" action="CargaMapaAlmacen" validate="true" method="get">
			<s:iterator id="ubicacion" value="%{#session.ubicacion}">
				<div class="widget" id="widget_tabs"> <!-- CONTIENE LAS TABS -->
					<fieldset>
						<section class="sectionMitad">
							<label for="text_almacen">Almacen</label>
							<div>
								<input type="text" id="text_almacen" name="text_almacen" readonly="true" value="<s:property value='nombreAlmacen'/>"/>
							</div>
						</section>
						<section class="sectionMitad">
							<label for="text_zona">Zona</label>
							<div>
								<input type="text" id="text_zona" name="text_zona" readonly="true" value="<s:property value='nombreZona'/>"/>
							</div>
						</section>
						<section class="sectionMitad">
							<label for="text_linea">Linea</label>
							<div>
								<input type="text" id="text_linea" name="text_linea" readonly="true" value="<s:property value='nombreLinea'/>"/>
							</div>
						</section>
						<section class="sectionMitad">
							<label for="text_estanteria">Estanter&iacute;a</label>
							<div>
								<input type="text" id="text_estanteria" name="text_estanteria" readonly="true" value="<s:property value='nombreEstanteria'/>"/>
							</div>
						</section>
						<section class="sectionMitad">
							<label for="text_piso">Piso</label>
							<div>
								<input type="text" id="text_piso" name="text_piso" readonly="true" value="<s:property value='nombrePiso'/>"/>
							</div>
						</section>
						<section class="sectionMitad">
							<label for="text_hueco">Hueco</label>
							<div>
								<input type="text" id="text_hueco" name="text_hueco" readonly="true" value="<s:property value='nombreHueco'/>"/>
							</div>
						</section>
						<section>
							<label for="text_ref">Referencia del hueco</label>
							<div>
								<input type="text" id="text_ref" name="text_ref" readonly="true" value="<s:property value='nombreHueco'/>"/>
							</div>
						</section>
					</fieldset>
					</div>
			</s:iterator>
		</s:form>
		<div id="registrosActuales" style="display: none;">
			<fieldset>
				<legend><span>Registros que se encuentran actualmente en el hueco</span></legend>
					<table id="tablaActual" class="display" border=0 cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th>Producto</th>
								<th>Registro</th>
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
									<td><s:property value='registro'/></td>
									<td><s:property value='proveedor'/></td>
									<td><s:property value='fecha'/></td>
									<td><s:property value='idPalet'/></td>
									<td><s:property value='cantidad'/></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
			</fieldset>
		</div>
		<!-- <ss:hidden id="idHueco" key="idHueco" name="idHueco" value="%{#ubicacion.idHueco}"/>
		<ss:hidden id="mover" name="mover" key="mover" value="%{#session.mover}"/>
		<ss:hidden id="incidencia" name="incidencia" key="incidencia" value="%{#session.incidencia}"/> -->
		<div id="contenedorSacar" style="display: none;">
			<fieldset>
				<legend><span>Introduzca las cantidades que va a a sacar</span></legend>
					<table id="tablasacar" class="display" border=0 cellpadding="2" cellspacing="2">
						<thead>
							<tr>
								<th>Producto</th>
								<th>Registro</th>
								<th>Proveedor</th>
								<th>Fecha entrada</th>
								<th >Id palet</th>
								<th>Cantidad</th>
								<th>Cantidad sacar</th>
							</tr>
						<thead>
						<tbody>
							<s:iterator id="ubicaciones" value="%{#session.ubicaciones}">
								<tr>
									<td><s:property value='nombreProducto'/></td>
									<td><s:property value='registro'/></td>
									<td><s:property value='proveedor'/></td>
									<td><s:property value='fecha'/></td>
									<td id="idPalet_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>" style="text-align: right;"><s:property value='idPalet'/></td>
									<td style="text-align: right;"><s:property value='cantidad'/></td>
									<td>
										<input class="sacando" id="sacarRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>" key="sacarRegistro_<s:property value='registro'/>" name="sacarRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>" value="0" style="text-align: right; width: 88%;" onblur="javascript:ajustarDecimal('sacarRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>');" onkeypress="return validarNumerosDecimales('sacarRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>',event);"></input>
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
									<td><s:property value='registro'/></td>
									<td><s:property value='proveedor'/></td>
									<td><s:property value='fecha'/></td>
									<td style="text-align: right;"><s:property value='idPalet'/></td>
									<td style="text-align: right;"><s:property value='cantidad'/></td>
									<td>
										<input class="mermando" id="mermaRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>" key="mermaRegistro_<s:property value='registro'/>" name="mermaRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>" value="0" style="text-align: right; width: 88%;" onblur="javascript:ajustarDecimal('mermaRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>');" onkeypress="return validarNumerosDecimales('mermaRegistro_<s:property value='registro'/>__<s:property value='idUbicacion'/>__<s:property value='idPalet'/>',event);"></input>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<textarea cols="112" rows="4" id="observacionIncidencia" name="observacionIncidencia"></textarea>
			</fieldset>
		</div>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>