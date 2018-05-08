<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Registro de devoluci&oacute;n<span class="screenCode">DEV01</span></h3>
	<div id="divNecesarioWidget">
		<s:form id="formulario" name="formulario" action="DevolverLote" validate="true">
			<fieldset><!-- Informacion Basica -->
				<section class="sectionMitad">
					<label for="dropdown_clientes">Cliente</label>
					<div>
						<select name="dropdown_clientes" id="dropdown_clientes" onchange="javascript:seleccionCliente();">
							<option selected value="0">Seleccione un cliente</option>
							<optgroup label="Clientes">
								<s:iterator id="clientes" value="%{#session.listaclientes}">
									<option value="<s:property value="idUsuario" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_lote">Lote</label>
					<div>
						<input type="text" id="text_lote" name="text_lote" title="Lote" />
						<span>Introduzca el lote que se devuelve</code></span>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_cantidad">Unidades</label>
					<div>
						<input type="text" id="text_cantidad" name="text_cantidad" title="Unidades" />
						<span>Introduzca las unidades que se devuelve</code></span>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="date_fecha">Fecha</label>
					<div>
						<input id="date_fecha" name="date_fecha" type="text" class="date" />
						<span>Introduzca la fecha de devoluci&oacute;n</span>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="checkbox_devolver">Acci&oacute;n correctora</label>
					<div>
							<input type="radio" name="medida" id="checkbox_devolver" />
							<label for="checkbox_devolver">Devolver al stock</label>
							<input type="radio" name="medida" id="checkbox_reaprovechar" />
							<label for="checkbox_reaprovechar">Reaprovechar</label>
							<input type="radio" name="medida" id="checkbox_tirar" checked/>
							<label for="checkbox_tirar">Enviar a destr&iacute;o</label>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="dropdown_estadosFabas">Incidencias</label>
					<div>
						<select name="dropdown_estadosFabas" id="dropdown_estadosFabas" onchange="estadoSelec();">
							<optgroup label="Incidencias">
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
				<section>
					<label for="text_observa">Observaciones</label>
					<div>
						<s:textarea id="text_observa" name="descProducto" key="descProducto" rows="4" cols="50"/>
					</div>
				</section>
			</fieldset>
		</s:form>
		<div id="ocultos" style="display: none;">
			<div id="resultadoComprobacion">
			</div>
		</div>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>