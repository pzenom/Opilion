<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Nuevo envase<span class="screenCode">REGISTRO_EN</span></h3>
	<div id="divNecesarioWidget">
		<s:form name="formulario" action="IngresarRegistroEnvase" method="post" validate="true">
			<s:hidden id="idMaterial" name="idMaterial" key="idMaterial" value="0"/>
			<s:hidden id="idEnvase" name="idEnvase" key="idEnvase" value="0"/>
			<fieldset>
				<section class="sectionMitad">
					<label for="text_nombre"><s:text name="registroCliente.nombre"/></label>
					<div>
						<input type="text" id="text_nombre" name="text_nombre" />
						<span>Introducir nombre del envase</code></span>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_descripcion">Descripci&oacute;n</label>
					<div>
						<input type="text" id="text_descripcion" name="text_descripcion" />
						<span>Introducir descripci&oacute;n del envase</code></span>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_peso">Peso</label>
					<div>
						<input type="text" id="text_peso" name="text_peso" />
						<span>Introducir peso del envase</code></span>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_dimensiones">Dimensiones</label>
					<div>
						<input type="text" id="text_dimensiones" name="text_dimensiones" />
						<span>Introducir dimensiones del envase</code></span>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="dropdown_tiposMaterial">Material</label>
					<div>
						<select name="dropdown_tiposMaterial" id="dropdown_tiposMaterial" onchange="javascript:materialSeleccionado();">
							<option value="-1">Seleccione el material del envase</option>
							<optgroup label="Material">
								<s:iterator id="materiales" value="%{#session.listamateriales}">
									<option value="<s:property value="idMaterial" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</div>
				</section>
			</fieldset>
		</s:form>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>