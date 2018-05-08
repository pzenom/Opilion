<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Lotes del ingrediente<span class="screenCode">LOTES_INGRE</span></h3>
	<div id="divNecesarioWidget">
		<div id="lotesIngrediente">
			<table id="tablaLotesIngredientes" cellpadding="0" cellspacing="0" border="0" class="display" style="display: none;">
				<thead>
					<tr>
						<th><s:text name="general.nombre"/></th>
						<th>Lote</th>
						<th><s:text name="general.stock"/></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="lotes" value="%{#session.lotes}">
						<tr>
							<td style="width: 80%;" id="celdaIngrediente_<s:property value="lote" />" >
								<s:property value="descProducto" />
							</td>
							<td style="width: 80%;" id="celdaIngrediente_<s:property value="lote" />" >
								<s:property value="lote" />
							</td>
							<td style="width: 10%;">
								<span id="saldo_<s:property value="lote" />" class="numeroMilesDecimal"><s:property value="cantidad" /></span>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<p>&nbsp;</p>
		</div> <!-- end lotesIngrediente -->
	</div> <!-- end divNecesarioWidget -->
	<div id="ajaxCategorias" name="ajaxCategorias" style="display: none;"></div>
</s:i18n>