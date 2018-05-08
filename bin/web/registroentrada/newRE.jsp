<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Nuevo registro de entrada<span class="screenCode">ADD_RE</span></h3>
	<s:form action="InseRE" validate="true" onsubmit="javascript:window.opener.location.reload();">
		<div>
			<fieldset>
				<section class="sectionMitad">
					<label for="text_orden">Codigo orden</label>
					<div>
						<input type="text" id="text_orden" name="text_orden" readonly="true" value="<s:property value="%{#session.codigoorden}" />"/>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_responsable">Responsable</label>
					<div>
						<input type="text" id="text_responsable" name="text_responsable" readonly="true" value="<s:property value="%{#session.usuario.login}" />"/>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_lote">Lote</label>
					<div>
						<input type="text" id="text_lote" name="text_lote"/>
					</div>
					<!-- lote -->
				</section>
				<section class="sectionMitad">
					<label for="text_lote">Lote</label>
					<div>
						<input type="text" id="text_lote" name="text_lote"/>
					</div>
					<!-- cantidad -->
				</section>
				<!-- costoUnitario
				costoTotal -->
				<section class="sectionMitad">
					<label for="date_fechaRegistro">Fecha de registro</label>
					<div><input id="date_fechaRegistro" name="date_fechaRegistro" type="text" class="date" value="<s:property value="%{#session.fecharegistro}" />">
					<!-- fechaLlegada -->
					</div>
				</section>
				<section class="sectionMitad">
					<label for="date_fechaCaducidad">Fecha de caducidad</label>
					<div><input id="date_fechaCaducidad" name="date_fechaCaducidad" type="text" class="date" value="<s:property value="%{#session.fechacaducidad}" />">
					<!-- fechaCaducidad -->
					</div>
				</section>
				<section class="sectionMitad">
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
				<section class="sectionMitad">
					<label for="dropdown_envases">Envase</label>
					<div>
						<select name="dropdown_envases" id="dropdown_envases">
							<option value="0">Seleccione un envase</option>
							<optgroup label="Envase">
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
				<section class="sectionMitad">
					<label for="dropdown_distribuir">&iquest;Listo para distribuir?</label>
					<div>
						<select name="dropdown_distribuir" id="dropdown_distribuir">
							<option value="0">&iquest;Listo para distribuir?</option>
							<option value="S">S&iacute;</option>
							<option value="N">No</option>
						</select>
						<!-- idEnvase -->
					</div>
				</section>
			</fieldset>
			<hr />
			<fieldset>
				<section class="sectionMitad">
					<label for="dropdown_categorias">Categoria</label>
					<div>
						<select name="dropdown_categorias" id="dropdown_categorias">
							<optgroup label="Categoria">
								<s:iterator id="categorias" value="%{#session.listacategorias}">
									<option value="<s:property value="idCategoria" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
						<!-- idCategoriaEntrada -->
					</div>
				</section>
				<section class="sectionMitad">
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
				<section class="sectionMitad">
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
				<section class="sectionMitad">
					<label for="text_numeroBultos">N&uacute;mero de bultos</label>
					<div>
						<input type="text" id="text_numeroBultos" name="text_numeroBultos"/>
					</div>
					<!-- numeroBultos -->
				</section>
				<section class="sectionMitad">
					<label for="text_numeroPalets">N&uacute;mero de palets</label>
					<div>
						<input type="text" id="text_numeroPalets" name="text_numeroPalets"/>
					</div>
					<!-- numeroPallets -->
				</section>
				<section class="sectionMitad">
					<label for="text_temperatura">Temperatura</label>
					<div>
						<input type="text" id="text_temperatura" name="text_temperatura"/>
					</div>
					<!-- temperatura -->
				</section>
				<section class="sectionMitad">
					<label for="text_humedad">Humedad</label>
					<div>
						<input type="text" id="text_humedad" name="text_humedad"/>
					</div>
					<!-- humedad -->
				</section>
			</fieldset>
		</div>
	</s:form>
	<button class="i_arrow_left icon verdeOpilion" onclick="javascript:consProdu();">Volver</button>
	<button class="i_plus icon verdeOpilion" onclick="javascript:registraProducto();">Insertar</button>
</s:i18n>