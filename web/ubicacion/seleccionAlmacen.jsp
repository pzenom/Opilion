<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Selecci&oacute;n de almac&eacute;n<span class="screenCode">SELEC_ALMACEN</span></h3>
	<div id="divNecesarioWidget">
		<s:form id="almac" name="almac" action="UbicarPalet.action" validate="true" method="post">
			<s:hidden id="idTipoUbicacion" key="idTipoUbicacion" name="idTipoUbicacion"/>
			<fieldset><!-- Informacion Basica -->
				<section>
					<label for="dropdown_almacenes">Almacenes</label>
					<div>
						<select name="dropdown_almacenes" id="dropdown_almacenes" onchange="javascript:almacenSeleccionado();">
							<option selected value="0">Selecciona un almacen</option>
							<optgroup id="optgroup_almacenes" label="Almacen">
								<s:iterator id="tipos" value="%{#session.almacenes}">
									<option value="<s:property value="idTipoUbicacion" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</div>
				</section>
				<!-- <button class="i_file_cabinet icon small" onclick="javascript:nuevoAlmacen('false');">Almac&eacute;n nuevo</button>
				<button class="i_car icon small" onclick="javascript:nuevoAlmacen('true');">Veh&iacute;culo nuevo</button> -->
			</fieldset>
		</s:form>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>