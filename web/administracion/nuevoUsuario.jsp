<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<div class="g12 ui-sortable">
		<div id="widget_consTraza" class="widget">
			<h3 class="handle">Nuevo usuario<span class="screenCode">ADD_USER</span></h3>
			<s:form id="formulario" name="formulario" action="IngresarUsuario" method="post" validate="true">
				<div>
					<fieldset>
						<section class="">
							<label for="texto_nombre">Nombre</label>
							<div>
								<input type="text" id="texto_nombre" name="texto_nombre" value="" />
							</div>
						</section>
						<section class="sectionMitad">
							<label for="texto_contrasenia">Contrase&ntilde;a</label>
							<div>
								<input type="password" id="texto_contrasenia" name="texto_contrasenia" value="" />
							</div>
						</section>
						<section class="sectionMitad">
							<label for="texto_confirma_contrasenia">Confirmar</label>
							<div>
								<input type="password" id="texto_confirma_contrasenia" name="texto_confirma_contrasenia" value="" />
							</div>
						</section>
					</fieldset>
				</div>
			</s:form>
		</div>
	</div>
</s:i18n>