<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Nuevo registro de entrada<span class="screenCode">ADD_RE</span></h3>
	<s:form action="InseRE" validate="true" onsubmit="javascript:window.opener.location.reload();">
		<s:hidden id="idTipoRegistro" key="idTipoRegistro" name="idTipoRegistro" value="%{#session.idTipoRegistro}" />
		<s:hidden id="idProductoFinal" />
		<s:hidden id="pesoProductoFinal" />
		<s:iterator id="tiposRegistro" value="%{#session.tiposProveedor}">
			<s:hidden id="tipoReg_%{#tiposRegistro.idTipo}" cssClass="tiposReg" value="%{#tiposRegistro.idTipo}" />
		</s:iterator>
		<div>
			<fieldset>
				<section class="sectionMitad">
					<label for="text_orden">Orden</label>
					<div>
						<input type="text" id="text_orden" name="text_orden" readonly="true" value="<s:property value="%{#session.codigoorden}" />"/>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_proveedor">Proveedor</label>
					<div>
						<input type="text" id="text_proveedor" name="text_proveedor" readonly="true" value="<s:property value="%{#session.proveedor}" />" />
					</div>
					<!-- proveedor -->
				</section>
				<section class="sectionMitad">
					<label for="dropdown_tipoRegistro">Tipo de registro</label>
					<div>
						<select name="dropdown_tipoRegistro" id="dropdown_tipoRegistro" onchange="javascript:tipoRegistroSeleccionado();">
							<option value="0">Tipo de registro</option>
							<optgroup label="Tipo de registros">
								<option id="opcionMateria" value="M" style="display: none;">Materia prima</option>
								<option id="opcionEnvase" value="E" style="display: none;">Envase</option>
								<option id="opcionProducto" value="P" style="display: none;">Producto venta</option>
							</optgroup>
						</select>
						<!-- idTipoRegistro -->
					</div>
				</section>
				<section class="sectionMitad" id="contenedorAvisoTipoRegistro">
					<div id="">
						<p style="color: red;">Seleccione el tipo de registro para poder continuar</p>
					</div>
				</section>
				<section class="sectionMitad" id="materias" style="display: none;">
					<label for="dropdown_productos">Producto</label>
					<div>
						<select name="dropdown_productos" id="dropdown_productos">
							<option value="0">Seleccione un producto</option>
							<optgroup label="Producto">
								<s:iterator id="productos" value="%{#session.listaproductos}">
									<option value="<s:property value="idProducto" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
						<!-- idProducto -->
					</div>
				</section>
				<section class="sectionMitad" id="envases" style="display: none;">
					<label for="dropdown_envases">Producto</label>
					<div>
						<select name="dropdown_envases" id="dropdown_envases">
							<option value="0">Seleccione un envase</option>
							<optgroup label="Producto">
								<s:iterator id="envases" value="%{#session.listaenvases}">
									<option value="<s:property value="idEnvase" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
						<!-- idEnvase -->
					</div>
				</section>
				<section class="sectionMitad" id="productosFinales" style="display: none;">
					<label for="dropdown_productosFinales">Producto</label>
					<div>
						<select name="dropdown_productosFinales" id="dropdown_productosFinales" onChange="javascript:productoFinalSeleccion();">
							<option value="0">Seleccione un producto</option>
							<optgroup label="Producto">
								<s:iterator id="productos" value="%{#session.listaProductosFinales}">
									<option value="<s:property value="idProducto" />_<s:property value="peso" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
						<!-- idProducto -->
					</div>
				</section>
			</fieldset>
			<div id="contenedorSecundario" style="display: none;">
				<fieldset>
					<section class="sectionMitad">
						<label for="text_lote">Lote</label>
						<div>
							<input type="text" id="text_lote" name="text_lote"/>
						</div>
						<!-- lote -->
					</section>
					<section class="sectionMitad">
						<label for="text_cantidad"><span id="labelCantidad" style="font-size: 13px;"></span></label>
						<div>
							<input type="text" id="text_cantidad" name="text_cantidad" onkeyup="javascript:multiplica();" onkeypress="return validarNumerosDecimales('text_cantidad', event);" onblur="javascript:ajustarDecimal('text_cantidad');" value="0" />
						</div>
						<!-- cantidad -->
					</section>
					<section class="sectionMitad" id="sectionPrecioKilo">
						<label for="text_precioKilo">Precio kilo</label>
						<div>
							<input type="text" id="text_precioKilo" value="0" name="text_precioKilo" onkeyup="javascript:ajustaCostoUnitario();" onkeypress="return validarNumerosDecimales('text_precioKilo', event);" onblur="javascript:ajustarDecimal('text_precioKilo');" />
						</div>
						<!-- costoUnitario -->
					</section>
					<section class="sectionMitad">
						<label for="text_costoUnitario">Costo unitario</label>
						<div>
							<input type="text" id="text_costoUnitario" value="0" name="text_costoUnitario" onkeyup="javascript:multiplica();" onkeypress="return validarNumerosDecimales('text_costoUnitario', event);" onblur="javascript:ajustarDecimal('text_costoUnitario');" disabled="disabled" />
						</div>
						<!-- costoUnitario -->
					</section>
					<section class="sectionMitad">
						<label for="text_costoTotal">Costo total</label>
						<div>
							<input type="text" value="0" id="text_costoTotal" name="text_costoTotal" disabled="disabled" />
						</div>
						<!-- costoTotal -->
					</section>
					<section class="sectionMitad">
						<label for="date_fechaRegistro">Fecha de registro</label>
						<div><input id="date_fechaRegistro" name="date_fechaRegistro" type="text" class="date" value="<s:property value="%{#session.fecharegistro}" />" />
						<!-- fechaLlegada -->
						</div>
					</section>
					<section class="sectionMitad">
						<label for="date_fechaCaducidad">Fecha de caducidad</label>
						<div><input id="date_fechaCaducidad" name="date_fechaCaducidad" type="text" class="date" value="<s:property value="%{#session.fechacaducidad}" />" />
						<!-- fechaCaducidad -->
						</div>
					</section>
					<section class="sectionMitad" id="categoriasEntrada">
						<label for="dropdown_categoriasEntrada">Categoria de entrada</label>
						<div>
							<select name="dropdown_categoriasEntrada" id="dropdown_categoriasEntrada">
								<option value="0">Seleccione una categoria de entrada</option>
								<optgroup label="Categoria de entrada">
									<s:iterator id="categoriasE" value="%{#session.listacategorias}">
										<option value="<s:property value="idCategoria" />">
											<s:property value="nombreCategoria" />
										</option>
									</s:iterator>
								</optgroup>
							</select>
							<!-- idCategoriaEntrada -->
						</div>
					</section>
					<section class="sectionMitad" id="categoriasSalida">
						<label for="dropdown_categoriasSalida">Categoria de salida</label>
						<div>
							<select name="dropdown_categoriasSalida" id="dropdown_categoriasSalida">
								<option value="0">Seleccione una categoria de salida</option>
								<optgroup label="Categoria de salida">
									<s:iterator id="categoriasS" value="%{#session.listacategorias}">
										<option value="<s:property value="idCategoria" />">
											<s:property value="nombreCategoria" />
										</option>
									</s:iterator>
								</optgroup>
							</select>
							<!-- idCategoriaSalida -->
						</div>
					</section>
					<section class="sectionMitad" id="cosechas">
						<label for="dropdown_cosechas">Cosecha</label>
						<div>
							<select name="dropdown_cosechas" id="dropdown_cosechas">
								<optgroup label="Cosecha">
									<s:iterator id="cosechas" value="%{#session.listacosechas}">
										<option value="<s:property value="idCosecha" />">
											<s:property value="nombre" />
										</option>
									</s:iterator>
								</optgroup>
							</select>
							<!-- idCosecha -->
						</div>
					</section>
					<section class="sectionMitad" id="formatoEntrega">
						<label for="dropdown_formatosEntrega">Formato de entrega</label>
						<div>
							<select name="dropdown_formatosEntrega" id="dropdown_formatosEntrega">
								<optgroup label="Formato de entrega">
									<s:iterator id="formatos" value="%{#session.listaformatos}">
										<option value="<s:property value="idFormatoEntrega" />">
											<s:property value="descripcion" />
										</option>
									</s:iterator>
								</optgroup>
							</select>
							<!-- idFormatoEntrega -->
						</div>
					</section>
					<section class="sectionMitad" id="numBultos">
						<label for="text_numeroBultos">N&uacute;mero de bultos</label>
						<div>
							<input type="text" id="text_numeroBultos" name="text_numeroBultos" onKeyPress="return validarSoloNumeros(event);" />
						</div>
						<!-- numeroBultos -->
					</section>
					<section class="sectionMitad" id="numPalets">
						<label for="text_numeroPalets">N&uacute;mero de palets</label>
						<div>
							<input type="text" id="text_numeroPalets" name="text_numeroPalets" onKeyPress="return validarSoloNumeros(event);" />
						</div>
						<!-- numeroPalets -->
					</section>
					<section class="sectionMitad" id="temperatura">
						<label for="text_temperatura">Temperatura</label>
						<div>
							<input type="text" id="text_temperatura" name="text_temperatura" onKeyPress="return validarNumerosDecimales('text_temperatura', event);" value="0" onblur="javascript:ajustarDecimal('text_temperatura');" />
						</div>
						<!-- temperatura -->
					</section>
					<section class="sectionMitad" id="humedad">
						<label for="text_humedad">Humedad</label>
						<div>
							<input type="text" id="text_humedad" name="text_humedad" onKeyPress="return validarNumerosDecimales('text_humedad', event);" value="0" onblur="javascript:ajustarDecimal('text_humedad');" />
						</div>
						<!-- humedad -->
					</section>
				</fieldset>
				<br />
				<fieldset id="soloMaterias">
					<div style="width: 50%; position: relative; float: left;">
						<section>
							<label for="dropdown_estadosFabas">Incidencias</label>
							<div>
								<select name="dropdown_estadosFabas" id="dropdown_estadosFabas" onchange="estadoSelec();">
									<optgroup label="Estados fabas">
										<s:iterator id="estados" value="%{#session.listaestadosfabas}">
											<option value="<s:property value="idEstadoFabas" />">
												<s:property value="descripcion" />
											</option>
										</s:iterator>
									</optgroup>
								</select>
								<!-- idEstadoFabas -->
							</div>
						</section>
						<table id="tablaEstados" class="flat" style="width: auto !important;" cellpadding="2" cellspacing="2" border="1" style="display: none;">
							<caption>Incidencias</caption>
							<thead>
								<tr>
									<th style="width: 200px;">Nombre</th>
								</tr>
							</thead>
							<tbody id="tbodyEstados">
							</tbody>
						</table>
					</div>
					<div style="width: 50%; position: relative; float: left;">
						<section>
							<label for="dropdown_incidencias">Medidas correctoras</label>
							<div>
								<select name="dropdown_incidencias" id="dropdown_incidencias" onchange="incidenciaSelec();">
									<option value="0">Seleccione medidas correctoras</option>
									<optgroup label="Incidencias">
										<s:iterator id="incidencias" value="%{#session.listaincidencias}">
											<option value="<s:property value="idIncidencia" />">
												<s:property value="nombre" />
											</option>
										</s:iterator>
									</optgroup>
								</select>
								<!-- idIncidencia -->
							</div>
						</section>
						<table id="tablaIncidencias" class="flat" style="width: auto !important;" cellpadding="2" cellspacing="2" border="1" style="display: none;">
							<caption>Medidas correctoras</caption>
							<thead>
								<tr>
									<th style="width: 200px;">Nombre</th>
								</tr>
							</thead>
							<tbody id="tbdoyIncidencias">
							</tbody>
						</table>
					</div>
					<section id="observacionesInci">
						<label for="text_notasIncidencias">Observaciones</label>
						<div>
							<textarea cols="30" rows="4" id="text_notasIncidencias"></textarea> 
						</div>
						<!-- notasIncidencias -->
					</section>
				</fieldset>
			</div> <!-- end div secundario -->
		</div>
	</s:form>
</s:i18n>