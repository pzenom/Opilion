<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="materiaprima.consultar.titulo"/><span class="screenCode"><s:text name="materiaprima.consultar.codigoPantalla"/></span></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form id="formulario" name="formulario" action="ConsultaIngrediente" validate="true">
				<s:hidden id="idGrupo" name="idGrupo" key="idGrupo" value="%{#session.idGrupo}" />
				<s:hidden id="idMateriaPrima" name="idMateriaPrima" value="0"/>
				<fieldset><!-- Informacion Basica-->
					<section>
						<label for="dropdown_tipos"><s:text name="materiaprima.tipoMateriaPrima"/></label>
						<select name="dropdown_tipos" id="dropdown_tipos" onchange="javascript:filtraIngredientes();">
							<option value="0"><s:text name="general.todos"/></option>
							<optgroup label="Tipo de materia">
								<s:iterator id="tipos" value="%{#session.listatipos}">
									<option value="<s:property value="idGrupo" />">
										<s:property value="nombreGrupo" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</section>
				</fieldset><!--end Informacion Basica-->
			</s:form>
		</div> <!-- end consulta -->
		<p>&nbsp;</p>
		<div id="ingredientes">
			<table id="tablaIngredientes" cellpadding="0" cellspacing="0" border="0" class="display" style="display: none;">
				<thead>
					<tr>
						<th><s:text name="general.id"/></th>
						<th><s:text name="general.nombre"/></th>
						<th><s:text name="general.stock"/></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="entry" value="%{#session.listaingredientes}">
						<tr>
							<td style="width: 4%;"><s:property value="idMateriaPrima" /></td>
							<td style="width: 80%;" id="celdaIngrediente_<s:property value="idMateriaPrima" />" onmouseover="javascript:muestraCategorias('<s:property value="idMateriaPrima" />', '<s:property value="nombre" />');">
								<s:property value="nombre" />
							</td>
							<td style="width: 10%;">
								<span id="stock_<s:property value="idMateriaPrima" />" class="numeroMilesDecimal"><s:property value="stock" /></span>
							</td>
							<td id="celdaCargarIngrediente_<s:property value="idMateriaPrima" />" class="celdaCargarIngrediente" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none; width: 3%;">
								<a href="javascript:cargarIngrediente('<s:property value="idMateriaPrima" />');"><img src="img/edit.png" alt="Editar" title="Editar"/></a>
							</td>
							<td id="celdaVerLotesIngrediente_<s:property value="idMateriaPrima" />" class="celdaVerLotesIngrediente" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; width: 3%;">
								<a href="javascript:verLotesIngrediente('<s:property value="idMateriaPrima" />');"><img src="img/zoom.png" alt="Ver lotes" title="Ver lotes"/></a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<p>&nbsp;</p>
		</div> <!-- end ingredientes -->
	</div> <!-- end divNecesarioWidget -->
	<div id="ajaxCategorias" name="ajaxCategorias" style="display: none;"></div>
</s:i18n>