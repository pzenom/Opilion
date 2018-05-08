<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<table id="tablaFacturas" cellpadding="0" cellspacing="0" border="0" class="display">
		<thead>
			<tr>
				<th style="max-width: 40px !important; width: 40px !important;">C&oacute;digo</th>
				<th style="width: 200px;">Cliente</th>
				<th>Fecha vencimiento</th>
				<th>Estado</th>
				<th style="width: 100px;">Importe</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="salida" value="%{#session.listasalidas}">
				<tr>
					<s:hidden id="estado_%{#salida.codigoFactura}" value="%{#salida.estado}" />
					<s:hidden id="cuotas_%{#salida.codigoFactura}" value="%{#salida.enCuotas}" />
					<td><s:property value="codigoFactura" /></td>
					<td><s:property value="nombreCliente" /></td>
					<td><s:property value="fechaVencimiento" /></td>
					<td id="celdasEstados_<s:property value="codigoFactura" />" class="estados" onmouseover="javascript:muestraEstadoFactura('<s:property value="estado" />', '<s:property value="codigoFactura" />');"><s:property value="descripcionEstado" /></td>
					<td style="text-align: right;"><span id="total_<s:property value="idFactura" />" class="numeroMilesDecimal"><s:property value="importeTotal" /> &euro;</span></td>
					<td id="celdaEditar_<s:property value="codigoFactura" />" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none;" border="0">
						<a href="javascript:editarFactura('<s:property value="codigoFactura" />');">
							<img src="img/edit.png" alt="Editar" title="Editar" />
						</a>
					</td>
					<td id="celdaControlCuotas_<s:property value="codigoFactura" />" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none;" border="0">
						<a href="javascript:controlCuotas('<s:property value="codigoFactura" />');">
							<img src="img/zoom.png" alt="Visualizar cuotas" title="Visualizar cuotas" />
						</a>
					</td>
					<td id="celdaImprimir_<s:property value="codigoFactura" />" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none;" border="0">
						<a href="javascript:reporteFac('<s:property value="codigoFactura" />');">
							<img src="img/pdf_icon_16.gif" alt="Imprimir" title="Imprimir" />
						</a>
					</td>
					<td id="celdaEmitir_<s:property value="codigoFactura" />" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none;" border="0">
						<a href="javascript:emitirFactura('<s:property value="codigoFactura" />');">
							<img src="img/note_go.png" alt="Emitir factura" title="Emitir factura" />
						</a>
					</td>
					<td id="celdaCuotas_<s:property value="codigoFactura" />" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none;" border="0">
						<a href="javascript:cuotasFactura('<s:property value="codigoFactura" />');">
							<img src="img/arrow_divide.png" alt="Preparar cuotas" title="Preparar cuotas" />
						</a>
					</td>
					<td id="celdaCobrar_<s:property value="codigoFactura" />" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none;" border="0">
						<a href="javascript:cobrarFactura('<s:property value="codigoFactura" />');">
							<img src="img/money.png" alt="Cobrar factura" title="Cobrar factura" />
						</a>
					</td>
					<td id="celdaCobradaNoCobrar_<s:property value="codigoFactura" />" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none;" border="0">
						<a href="javascript:$.msg('No es posible cobrar una factura dos veces.');">
							<img src="img/accept.png" alt="Factura cobrada" title="Factura cobrada" />
						</a>
					</td>
					<td id="celdaCobradaNoAnular_<s:property value="codigoFactura" />" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none;" border="0">
						<a href="javascript:$.msg('No se puede anular una factura ya cobrada.');">
							<img src="img/accept.png" alt="Factura cobrada." title="Factura cobrada." />
						</a>
					</td>
					<td id="celdaAnular_<s:property value="codigoFactura" />" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none;" border="0">
						<a href="javascript:anularFactura('<s:property value="codigoFactura" />');">
							<img src="img/cancel.png" alt="Anular factura" title="Anular factura" />
						</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<p>&nbsp;</p>				
</s:i18n>