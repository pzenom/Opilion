<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<div class="g12 ui-sortable">
		<div id="widget_consTraza" class="widget">
			<h3 class="handle">Modificar el stock de un lote/registro<span class="screenCode">STOCK_I</span></h3>
			<div id="divNecesarioWidget">
				<s:form name="formulario" >
					<s:hidden id="loteOK" value="N" />
					<fieldset><!-- Informacion Basica-->
						<section class="sectionTercio">
							<label for="loteRegistro">Lote/Registro</label>
							<div>
								<s:textfield id="loteRegistro" key="lote" label="Lote/Registro" onchange="javascript:loteRegistroModificado();" />
							</div>
						</section>
						<section class="sectionTercio">
							<label for="producto">Producto</label>
							<div>
								<s:textfield id="producto" key="producto" label="Producto" disabled="true" />
							</div>
						</section>
						<section class="sectionTercio">
							<label for="sel_ubicacion">Ubicacion</label>
							<div>
								<select name="sel_ubicacion" id="sel_ubicacion" onchange="javascript:ubicacionSeleccionada();" style="float: left;" >
									<option value="223">Salida</option>
								</select>
								<s:textfield id="cantidadUbicacion" key="cantidadActual" label="Cantidad actual" disabled="true" cssStyle="width: 50%; text-align: right;" />
							</div>
						</section>
						<!-- <section class="sectionTercio">
							<label for="cantidadUbicacion">Cantidad actual</label>
							<div>
								<s:textfield id="cantidadUbicacion" key="cantidadActual" label="Cantidad actual" disabled="true" />
							</div>
						</section> -->
						<section class="sectionTercio">
							<label for="cantidadModificar">Incremento</label>
							<div>
								<s:textfield id="cantidadModificar" key="cantidad" label="Incremento" />
							</div>
						</section>
						<section class="sectionTercio">
							<label for="fecha">Fecha</label>
							<div>
								<s:textfield id="fecha" key="fecha" label="Fecha" disabled="true" />
							</div>
						</section>
						<section class="sectionTercio">
							<label for="usuarioResponsable">Responsable</label>
							<div>
								<s:textfield id="usuarioResponsable" key="usuario" label="Responsable" disabled="true" value="%{#session.usuario.login}" />
							</div>
						</section>
						<section>
							<label for="causas">Causas</label>
							<textarea id="causas" name="causas" type="text" style="font-size:2em; width: 99%;"></textarea>
						</section>
					</fieldset><!--end Informacion Basica-->
				</s:form>
			</div><!-- end #divNecesarioWidget -->
		</div> <!-- end widget_consProv -->
	</div> <!-- end g12 widgets ui-sortable -->
</s:i18n>