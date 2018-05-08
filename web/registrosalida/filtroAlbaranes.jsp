<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<table id="tablaAlbaranes" cellpadding="0" cellspacing="0" border="0" class="display" >
		<thead>
			<tr>
				<th>Codigo</th>
				<th>Fecha</th>
				<th>Cliente</th>
				<th>Importe total</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="albaran" value="%{#session.listaalbaranes}">
				<tr>
					<td><s:property value="codigoAlbaran" /></td>
					<td><s:property value="fecha" /></td>
					<td><s:property value="cliente.nombre" /></td>
					<td style="text-align:right;"><s:property value="importeTotal" /></td>
					<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle;" border="0">
						<a id="bt_editarAlbaran_<s:property value="codigoAlbaran" />" href="javascript:editarAlbaran('<s:property value="codigoAlbaran" />');">
							<img id="img_bt_editarAlbaran_<s:property value="codigoAlbaran" />" src="img/edit.png" alt="Editar albar&aacute;n" title="Editar albar&aacute;n"/>
						</a>
					</td>
					<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle;" border="0">
					  <a href="javascript:reporte('<s:property value="codigoAlbaran" />');"><img src="img/report_go.png" alt="Reporte" title="Reporte"/></a>
					</td>
					<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle;" border="0">
					  <a href="javascript:albaranAFactura('<s:property value="codigoAlbaran" />','<s:property value="tipoAlbaran" />');"><img src="img/pill_go.png" alt="A factura" title="A factura"/></a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
</s:i18n>