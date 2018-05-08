<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<table id="tablaIngredientes" cellpadding="0" cellspacing="0" border="0" class="display">
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
					<td class="rol3 rol4" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none; width: 3%;">
						<a href="javascript:cargarIngrediente('<s:property value="idMateriaPrima" />');"><img src="img/edit.png" alt="Editar" title="Editar"/></a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<p>&nbsp;</p>
</s:i18n>