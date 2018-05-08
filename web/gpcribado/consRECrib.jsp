<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="rcrib.paso1" /><span class="screenCode">RCRIB_1</span></h3>
	<div id="divNecesarioWidget">
		<p>&nbsp;</p>
		<div id="demo">
			<table id="tablaRegistros" cellpadding="0" cellspacing="0" border="0" class="display">
				<thead>
					<tr>
						<th>Codigo proceso</th>
						<th>Fecha Ingreso</th>
						<th>Descripci&oacute;n</th>
						<th>Cantidad Producto</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="ingred" value="%{#session.listaregistroscribado}">
						<tr>
							<td><s:property value="orden" /></td>
							<td><s:property value="fecha" /></td>
							<td><s:property value="descProducto" /></td>
							<td><s:property value="cantidadProducto" /></td>
							<td style="text-align:center;">
								<a href="javascript:regiGPCrib('<s:property value="orden" />');"><img src="img/note_go.png" alt="Cribcción" title="Cribcci&oacute;n" /></a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div> <!-- end divNecesarioWidget -->
	<p>&nbsp;</p>
</s:i18n>