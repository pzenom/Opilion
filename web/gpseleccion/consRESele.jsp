<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="rsel.paso1" /></h3>
	<div id="divNecesarioWidget">
		<p>&nbsp;</p>
		<div id="demo">
			<table id="tablaRegistros" cellpadding="0" cellspacing="0" border="0" class="display" style="display: none;">
				<thead>
					<tr>
						<th>Codigo proceso</th>
						<th>Fecha ingreso</th>
						<th>Descripci&oacute;n</th>
						<th>Cantidad producto</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="ingred" value="%{#session.listaregistrosseleccion}">
						<tr>
							<td><s:property value="orden" /></td>
							<td><s:property value="fecha" /></td>
							<td><s:property value="descProducto" /></td>
							<td><s:property value="cantidadProducto" /></td>
							<td style="text-align:center;">
								<a href="javascript:regiGPSele('<s:property value="orden" />');"><img src="img/note_go.png" alt="Selección" title="Selecci&oacute;n" /></a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div> <!-- end divNecesarioWidget -->
	<p>&nbsp;</p>
</s:i18n>