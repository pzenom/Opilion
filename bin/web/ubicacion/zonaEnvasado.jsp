<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Selecci&oacute;n de big bag<span class="screenCode">SELEC_BIG_BAG</span></h3>
	<div id="divNecesarioWidget">
		<s:form id="formulario" name="formulario" action="SeleccionHueco" validate="true" method="get">
			<fieldset><!-- Filtro Campos-->
				<label>NAVE BARCIA - Zona de envasado</label>
				<section>
					<label for="dropdown_bigbags">Almacenes</label>
					<div>
						<select name="dropdown_bigbags" id="dropdown_bigbags">
							<option selected value="0">Seleccione un big-bag</option>
							<optgroup id="optgroup_bigbags" label="Big bags">
								<s:iterator id="bigbags" value="%{#session.bigBags}">
									<option value="<s:property value="idHueco" />">
										<s:property value="descripcion" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</div>
				</section>
				<!--<s:hidden id="idZona" key="idZona" name="idZona" value="%{#ubicacion.idZona}"/>
				<s:hidden id="idAlmacen" key="idAlmacen" name="idAlmacen" value="%{#ubicacion.idAlmacen}"/>
				<s:hidden id="idLinea" key="idLinea" name="idLinea" value="%{#ubicacion.idLinea}"/>
				<s:hidden id="registro" key="registro" name="registro" cssClass="registro" value="%{#ubicacion.registro}"/>
				<s:hidden id="idEstanteria" key="idEstanteria" name="idEstanteria"/>
				<s:hidden id="idPiso" key="idPiso" name="idPiso"/>
				<s:hidden id="idHueco" key="idHueco" name="idHueco" cssClass="idHueco"/>
				<s:hidden id="idHuecoPiso" key="idHuecoPiso" name="idHuecoPiso"/>-->
			</fieldset>
			<s:submit targets="ajaxDiv" value="Seleccionar producto" cssStyle="display:none;" />
		</s:form>
		<button class="i_bended_arrow_right icon verdeOpilion" onclick="javascript:seleccionBigBag();">Siguiente
	</div>
</s:i18n>