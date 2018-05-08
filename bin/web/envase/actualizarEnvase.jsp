<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Actualiza envase<span class="screenCode">ACTUALIZA_EN</span></h3>
	<div id="divNecesarioWidget">
		<s:form name="formulario" action="ActualizaEnvase" method="post" validate="true">
			<s:iterator id="entry" value="%{#session.listaregistros}">
				<s:hidden id="idMaterial" name="idMaterial" key="idMaterial" />
				<s:hidden id="idEnvase" name="idEnvase" key="idEnvase"/>
				<fieldset>
					<section class="sectionMitad">
						<label for="text_nombre"><s:text name="registroCliente.nombre"/></label>
						<div>
							<input type="text" id="text_nombre" name="text_nombre" value="<s:property value="nombre" />"/>
							<span>Introducir nombre del envase</code></span>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_descripcion">Descripci&oacute;n</label>
						<div>
							<input type="text" id="text_descripcion" name="text_descripcion" value="<s:property value="descripcion" />"/>
							<span>Introducir descripci&oacute;n del envase</code></span>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_peso">Peso</label>
						<div>
							<input type="text" id="text_peso" name="text_peso" value="<s:property value="peso" />"/>
							<span>Introducir peso del envase</code></span>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_dimensiones">Dimensiones</label>
						<div>
							<input type="text" id="text_dimensiones" name="text_dimensiones" value="<s:property value="dimensiones" />"/>
							<span>Introducir dimensiones del envase</code></span>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="dropdown_tiposMaterial">Material</label>
						<div>
							<select name="dropdown_tiposMaterial" id="dropdown_tiposMaterial" onchange="javascript:materialSeleccionado();" >
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
			</s:iterator>
		</s:form>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>