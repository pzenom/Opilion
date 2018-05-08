<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consulta facturas</h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form name="formulario" action="ConsFact" validate="true">
				<fieldset><!-- Informacion Basica-->
					<section>
						<label for="dropdown_clientes">Cliente</label>
						<div>
							<select name="dropdown_clientes" id="dropdown_clientes" onchange="javascript:filtraFacturas();">
								<option value="0">Todos los clientes</option>
								<optgroup label="Cliente">
									<s:iterator id="tipos" value="%{#session.listaclientes}">
										<option value="<s:property value="idUsuario" />">
											<s:property value="nombre" />
										</option>
									</s:iterator>
								</optgroup>
							</select>
						</div>
					</section>
				</fieldset><!--end Informacion Basica-->
			</s:form>
		</div> <!-- end consulta -->
		<p>&nbsp;</p>
		<div id="demo">
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
		</div><!-- demo -->
	</div> <!-- end divNecesarioWidget -->
	<div id="dialogo" title="Reporte de factura" style="display: none;">
		<input id="checkEncabezado" name="checkEncabezado" type="checkbox">A&ntilde;adir encabezado</input><br />
		<br/>
		<button id="bt_imprimir_nuevo" class="i_pdf_document icon verdeOpilion" onclick="javascript:preparar();">Imprimir</button>
	</div>
	<div id="ajaxEstados" name="ajaxEstados" style="display: none;"></div>
</s:i18n>