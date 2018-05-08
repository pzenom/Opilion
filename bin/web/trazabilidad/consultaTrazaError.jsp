<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<div class="g12 ui-sortable">
		<div id="widget_consTraza" class="widget">
			<h3 class="handle">Trazabilidad. Error en al consulta<span class="screenCode">TRAZA_ERROR</span></h3>
			<div id="divNecesarioWidget">
				<s:form name="formulario" action="ConsultaTrazaProducto" validate="true">
					<fieldset><!-- Informacion Basica-->
						<section>
							<label for="codigoEntrada">ERROR</label>
							<div>
								<br />
								<h2><s:property value='%{#session.error}' /></h2>
								<br />
							</div>
						</section>
					</fieldset><!--end Informacion Basica-->
				</s:form>
			</div><!-- end #divNecesarioWidget -->
		</div> <!-- end widget_consProv -->
	</div> <!-- end g12 widgets ui-sortable -->
</s:i18n>