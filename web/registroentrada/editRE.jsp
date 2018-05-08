<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Editar registro de entrada<span class="screenCode">UPDA_RE</span></h3>
	<s:form action="InseRE" validate="true" onsubmit="javascript:window.opener.location.reload();">
		<s:iterator id="registro" value="%{#session.listaregistros}">
			<s:hidden id="idTipoRegistro" key="idTipoRegistro" name="idTipoRegistro" value="%{#session.idTipoRegistro}"/>
			<div>
				<fieldset>
					<section class="sectionMitad">
						<label for="text_orden">Orden</label>
						<div>
							<s:textfield id="text_orden" key="codigoOrden" cssStyle="width:180px; text-align:left;" disabled="true" />
							<!-- codigoOrden -->
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_responsable">Responsable</label>
						<div>
							<s:textfield id="text_responsable" key="descResponsable" cssStyle="width:180px; text-align:left;" disabled="true" />
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_proveedor">Proveedor</label>
						<div>
							<s:textfield id="text_proveedor" key="descProveedor" cssStyle="width:180px; text-align:left;" disabled="true" />
						</div>
						<!-- proveedor -->
					</section>
					<section class="sectionMitad">
						<label for="dropdown_tipoRegistro">Tipo de registro</label>
						<div>
							<!-- <select name="dropdown_tipoRegistro" id="dropdown_tipoRegistro" onchange="javascript:tipoRegistroSeleccionado();">
								<option value="0">Tipo de registro</option>
								<optgroup label="Tipo de registros">
									<option id="opcionMateria" value="M" style="display: none;">Materia prima</option>
									<option id="opcionEnvase" value="E" style="display: none;">Envase</option>
									<option id="opcionProducto" value="P" style="display: none;">Producto venta</option>
								</optgroup>
							</select> -->
							<s:textfield id="dropdown_tipoRegistro" key="idTipoRegistro" cssStyle="width:180px; text-align:left;" disabled="true" />
							<!-- idTipoRegistro -->
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_lote">Lote</label>
						<div>
							<s:textfield id="text_lote" key="lote" cssStyle="width:180px; text-align:left;"/>
						</div>
						<!-- lote -->
					</section>
					<section class="sectionMitad">
						<label for="text_cantidad">Uds/Kilos</label>
						<div>
							<s:textfield id="text_cantidad" key="cantidad" cssStyle="width:180px; text-align:left;" onkeyup="javascript:multiplica();" onkeypress="return validarNumerosDecimales('text_cantidad', event);" onblur="javascript:ajustarDecimal('text_cantidad');" disabled="true" />
						</div>
						<!-- cantidad -->
					</section>
					<section class="sectionMitad">
						<label for="text_costoUnitario">Costo unitario</label>
						<div>
							<s:textfield id="text_costoUnitario" key="costoUnitario" cssStyle="width:180px; text-align:left;" onkeyup="javascript:multiplica();" onkeypress="return validarNumerosDecimales('text_costoUnitario', event);" onblur="javascript:ajustarDecimal('text_costoUnitario');" />
						</div>
						<!-- costoUnitario -->
					</section>
					<section class="sectionMitad">
						<label for="text_costoTotal">Costo total</label>
						<div>
							<s:textfield id="text_costoTotal" key="costoTotal" cssStyle="width:180px; text-align:left;" disabled="true" />
						</div>
						<!-- costoTotal -->
					</section>
					<section class="sectionMitad">
						<label for="date_fechaRegistro">Fecha de registro</label>
						<div>
							<input id="date_fechaRegistro" name="date_fechaRegistro" type="text" class="date" value="<s:property value="%{#session.fecharegistro}" />">
							<!-- fechaLlegada -->
						</div>
					</section>
					<section class="sectionMitad">
						<label for="date_fechaCaducidad">Fecha de caducidad</label>
						<div>
							<input id="date_fechaCaducidad" name="date_fechaCaducidad" type="text" class="date" value="<s:property value="%{#session.fechacaducidad}" />">
							<!-- fechaCaducidad -->
						</div>
					</section>
					<section class="sectionMitad" id="materias" style="display: none;">
						<label for="dropdown_productos">Producto</label>
						<div>
							<s:select id="dropdown_productos" key="idProducto" list="%{#session.listaproductos}" listKey="idProducto" listValue="nombre" headerKey="0" cssStyle="text-align:left; width:140px;" disabled="true" />
						</div>
					</section>
					<section class="sectionMitad" id="envases" style="display: none;">
						<label for="dropdown_envases">Envase</label>
						<div>
							<s:select id="dropdown_envases" key="idEnvase" list="%{#session.listaenvases}" listKey="idEnvase" listValue="nombre" headerKey="0" cssStyle="text-align:left; width:140px;" value="%{#registro.idEnvase}" disabled="true" />
							<!-- idEnvase -->
						</div>
					</section>
					<section class="sectionMitad" id="productosFinales" style="display: none;">
						<label for="dropdown_productosFinales">Producto</label>
						<div>
							<s:select id="dropdown_productosFinales" key="idEnvase" list="%{#session.listaProductosFinales}" listKey="idProducto" listValue="nombre" headerKey="0" cssStyle="text-align:left; width:140px;" value="%{#registro.idProducto}" disabled="true" />
							<!-- idProducto -->
						</div>
					</section>
				</fieldset>
				<hr />
				<fieldset>
					<section class="sectionMitad" id="categoriasEntrada">
						<label for="dropdown_categoriasEntrada">Categoria de entrada</label>
						<div>
							<s:select id="dropdown_categoriasEntrada" key="idCategoriaEntrada" list="%{#session.listacategorias}" listKey="idCategoria" listValue="nombreCategoria" headerKey="0" cssStyle="text-align:left; width:140px;" value="%{#registro.idCategoriaEntrada}" disabled="true" />
							<!-- idCategoriaEntrada -->
						</div>
					</section>
					<section class="sectionMitad" id="categoriasSalida">
						<label for="dropdown_categoriasSalida">Categoria de salida</label>
						<div>
							<s:select id="dropdown_categoriasSalida" key="idCategoria" list="%{#session.listacategorias}" listKey="idCategoria" listValue="nombreCategoria" headerKey="0" cssStyle="text-align:left; width:140px;" value="%{#registro.idCategoria}" disabled="true" />
							<!-- idCategoria -->
						</div>
					</section>
					<section class="sectionMitad" id="cosechas">
						<label for="dropdown_cosechas">Cosecha</label>
						<div>
							<s:select id="dropdown_cosechas" key="idCosecha" list="%{#session.listacosechas}" listKey="idCosecha" listValue="nombre" headerKey="0" headerValue="-- Cosechas --" cssStyle="text-align:left; width:140px;" value="%{#registro.idCosecha}"/>
							<!-- idCosecha -->
						</div>
					</section>
					<section class="sectionMitad">
						<label for="dropdown_formatosEntrega">Formato de entrega</label>
						<div>
							<s:select id="dropdown_formatosEntrega" key="idFormatoEntrega" list="%{#session.listaformatos}" listKey="idFormatoEntrega" listValue="descripcion" headerKey="0" headerValue="-- Formatos --" cssStyle="text-align:left; width:140px;" value="%{#registro.idFormatoEntrega}"/>
							<!-- idFormatoEntrega -->
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_numeroBultos">N&uacute;mero de bultos</label>
						<div>
							<s:textfield id="text_numeroBultos" key="numeroBultos" cssStyle="width:180px; text-align:left;" disabled="true" readonly="true"/>
						</div>
						<!-- numeroBultos -->
					</section>
					<section class="sectionMitad">
						<label for="text_numeroPalets">N&uacute;mero de palets</label>
						<div>
							<s:textfield id="text_numeroPalets" key="numeroPallets" cssStyle="width:180px;text-align:left;" disabled="true" readonly="true"/>
						</div>
						<!-- numeroPallets -->
					</section>
					<section class="sectionMitad" id="temperatura">
						<label for="text_temperatura">Temperatura</label>
						<div>
							<s:textfield id="text_temperatura" key="temperatura" cssStyle="width:180px;text-align:left;" />
						</div>
						<!-- temperatura -->
					</section>
					<section class="sectionMitad" id="humedad">
						<label for="text_humedad">Humedad</label>
						<div>
							<s:textfield id="text_humedad" key="humedad" cssStyle="width:180px;text-align:left;" />
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
								<s:select id="dropdown_estadosFabas" key="idEstadoFabas" list="%{#session.listaestadosfabas}" listKey="idEstadoFabas" listValue="descripcion" headerKey="0" headerValue="-- Incidencias --" cssStyle="text-align:left; width:140px;" onchange="estadoSelec();"/>
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
								<s:iterator id="estados" value="%{#session.listaestadostmp}">
									<tr id="est<s:property value="idEstadoFabas" />" class="estados">
										<td><s:property value="descripcion" /></td>
										<td>
											<a href="#"><img id="fallo<s:property value="idEstadoFabas" />" src="img/cancel.png" alt="Eliminar" title="Eliminar" onClick="borrarEstado('<s:property value="idEstadoFabas" />')"/></a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					<div style="width: 50%; position: relative; float: left;">
						<section>
							<label for="dropdown_incidencias">Medidas correctoras</label>
							<div>
								<s:select id="dropdown_incidencias" key="idIncidencia" list="%{#session.listaincidencias}" listKey="idIncidencia" listValue="nombre" headerKey="0" headerValue="-- Medidas correctoras --" cssStyle="text-align:left; width:140px;" onchange="incidenciaSelec();"/>
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
								<s:iterator id="incidencias" value="%{#session.listaincidenciastmp}">
									<tr id="inc<s:property value="idIncidencia" />" class="incidencias">
										<td><s:property value="nombre" /></td>
										<td>
											<a href="#"><img id="incid<s:property value="idIncidencia" />" src="img/cancel.png" alt="Eliminar" title="Eliminar" onClick="borrarIncidencia('<s:property value="idIncidencia" />')"/></a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					<section id="observacionesInci">
						<label for="text_notasIncidencias">Observaciones</label>
						<div>
							<textarea cols="30" rows="4" id="text_notasIncidencias" value="<s:property value="notasIncidencias" />"><s:property value="notasIncidencias" /></textarea> 
						</div>
						<!-- notasIncidencias -->
					</section>
				</fieldset>
			</div>
		</s:iterator>
	</s:form>
</s:i18n>