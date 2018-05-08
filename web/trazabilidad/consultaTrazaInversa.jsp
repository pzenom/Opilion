<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<div class="g12 ui-sortable">
		<div id="widget_consTraza" class="widget">
			<h3 class="handle">Consultar la trazabilidad de un producto<span class="screenCode">TRAZA_I</span></h3>
			<div id="divNecesarioWidget">
				<s:form name="formulario" >
					<fieldset><!-- Informacion Basica-->
						<section class="sectionTercio">
							<label for="codigoEntrada">Lote del producto</label>
							<div>
								<s:textfield id="loteTraza" key="lote" label="%{getText('rcri.lote')}" />
							</div>
						</section>
					</fieldset><!--end Informacion Basica-->
				</s:form>
			</div><!-- end #divNecesarioWidget -->
		</div> <!-- end widget_consProv -->
	</div> <!-- end g12 widgets ui-sortable -->
</s:i18n>