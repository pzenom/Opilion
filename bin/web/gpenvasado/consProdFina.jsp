<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Nuevo proceso de envasado<span class="screenCode">NUEVO_GP_ENVA</span></h3>
	<div id="divNecesarioWidget">
		<s:form id="formulario" name="formulario" action="ListREMP" validate="true" method="get">
			<s:hidden id="idProd" name="idProd" key="idProd" />
			<s:hidden id="tipoEan" name="tipoEan" key="tipoEan" />
			<s:hidden id="agrupar" name="agrupar" key="agrupar" value="false" />
			<fieldset><!-- Informacion Basica -->
				<section class="sectionTercio">
					<label for="text_cantidad">Cantidad de EAN13</label>
					<div>
						<input type="text" id="text_cantidad" name="text_cantidad" title="Cantidad" onkeypress="return validarSoloNumeros(event);" onblur="javascript:ajustarDecimal('text_cantidad');" value="0" >
						<span>Introduzca la cantidad a envasar</code></span>
					</div>
				</section>
				<section class="sectionTercio">
					<label for="dropdown_productos">Productos</label>
					<div>
						<select name="dropdown_productos" id="dropdown_productos" onchange="javascript:seleccionProducto();">
							<option selected value="0">Seleccione el producto que quiere envasar</option>
							<optgroup id="optGroupEAN13" label="Seleccione el item (EAN13) a envasar">
								<s:iterator id="items" value="%{#session.eans13}">
									<option value="<s:property value="idProducto" />_13">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
							<optgroup id="optGroupEAN14" label="Seleccione la agrupaci&oacute;n (EAN14) a envasar">
								<s:iterator id="agrupados" value="%{#session.eans14}">
									<option value="<s:property value="idProducto" />_14">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</div>
				</section>
				<section class="sectionTercio">
					<label for="date_fecha">Fecha de envasado</label>
					<div><input id="date_fecha" name="date_fecha" type="text" class="date" data-format="yyyy-mm-dd" value="<s:property value="#session.fechaRegistro" />">
						<span>Introduzca la fecha de envasado</span>
					</div>
				</section>
				<section id="sectionAgrupaciones" style="display: none;" class="sectionMitad">
					<label for="dropdown_agrupaciones">Agrupacion</label>
					<div id="contenedorSelect" style="display: block;">
						<div>
							<select name="dropdown_agrupaciones" id="dropdown_agrupaciones" onchange="javascript:agrupacionSeleccionada();">
								<option selected value="-1">Seleccione el EAN14 en el que agrupar&aacute;</option>
								<optgroup label="Agrupaciones" id="optgroupAgrupaciones">
								</optgroup>
							</select>
						</div>
					</div>
				</section>
				<section id="sectionAvisoSeleccionAgrupacion" class="sectionMitad" style="display: none;">
					<label for="textoAviso" style="color: red;">Aviso</label>
					<div>
						<p id="textoAviso" style="color: red;">Seleccione una agrupaci&oacute;n para poder continuar</p>
					</div>
				</section>
			</fieldset><!--end Informacion Basica-->
			<div id="colgarNuevoEnvasado">
			</div>
			<div style="clear:left;">
				<p><s:text name="envasado.observaciones" /></p>
				<textarea id="observaciones" type="text" style="height: 70px; width: 100%;" name="observaciones"></textarea><br>
				&nbsp;
			</div>
		</s:form>
		<div id="agrupacionesOcultas" style="display: none;"></div>
		<!-- BOTON INSERTAR -->
	</div> <!-- end divNecesarioWidget -->
</s:i18n>