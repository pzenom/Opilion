<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<div class="g12 ui-sortable">
		<div id="widget_consTraza" class="widget">
			<h3 class="handle">Nueva ruta<span class="screenCode">ADD_RUTA</span></h3>
			<s:form id="formulario" name="formulario" action="IngresarUsuario" method="post" validate="true">
				<div>
					<fieldset>
						<section class="sectionMitad">
							<label for="texto_nombre">Nombre</label>
							<div>
								<input type="text" id="texto_nombre" name="texto_nombre" value="" />
							</div>
						</section>
						<section class="sectionMitad">
							<label for="dropdown_comerciales">Comercial por defecto</label>
							<div>
								<s:select id="dropdown_comerciales" key="idUsuario" label="Comercial por defecto" list="%{#session.comerciales}" listKey="idUsuario" listValue="login" headerKey="0" headerValue="-- Seleccione un comercial --" cssStyle="width:195px;"/>
							</div>
						</section>
					</fieldset>
				</div>
			</s:form>
		</div>
	</div>
</s:i18n>