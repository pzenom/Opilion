<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consulta albaranes<span class="screenCode">CONS_ALBA</span></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form id="formulario" name="formulario" action="ConsAlba" validate="true">
				<fieldset>
					<section style="width: 50%;">
						<label for="dropdown_cuantos_mostrar">Filtrar</label>
						<div>
							<select name="dropdown_cuantos_mostrar" id="dropdown_cuantos_mostrar" onchange="javascript:filtraAlbaranes();">
								
								<optgroup label="Mostrar...">
									<option value="0">Todos los albaranes</option>
									<option value="1" selected>&Uacute;ltimos 20</option>
									<option value="2">&Uacute;ltimos 50</option>
									<option value="3">&Uacute;ltima semana</option>
									<option value="4">&Uacute;ltimo mes</option>
									<option value="5">&Uacute;ltimo a&ntilde;o</option>
								</optgroup>
							</select>
						</div>
					</section>
					<section style="width: 50%;">
						<label for="dropdown_clientes">Cliente</label>
						<div>
							<select name="dropdown_clientes" id="dropdown_clientes" onchange="javascript:filtraAlbaranes();">
								<option value="0">Seleccione un cliente</option>
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
				</fieldset>
			</s:form>
		</div> <!-- end consulta -->
		<p>&nbsp;</p>
		<div id="demo">
			<table id="tablaAlbaranes" cellpadding="0" cellspacing="0" border="0" class="display" >
				<thead>
					<tr>
						<th>Codigo</th>
						<th>Fecha</th>
						<th>Cliente</th>
						<th>Estado</th>
						<th>IMPORTE</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="albaran" value="%{#session.listaalbaranes}">
						<tr>
							<td><s:property value="codigoAlbaran" /></td>
							<td><s:property value="fecha" /></td>
							<td><s:property value="cliente.nombre" /></td>
							<td id="celdasEstados_<s:property value="codigoAlbaran" />" onmouseover="javascript:muestraInfoEstadoAlbaran('<s:property value="estado" />', '<s:property value="codigoAlbaran" />');" class="celdasEstadosAlbaranes">
								<s:property value="descripcionEstado" />
							</td>
							<td style="text-align:right;">
								<span id="total_<s:property value="codigoAlbaran" />" class="numeroMilesDecimal"><s:property value="importeTotal" /> &euro;</span>
							</td>
							<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle;" border="0">
								<a id="bt_editarAlbaran_<s:property value="codigoAlbaran" />" href="javascript:editarAlbaran('<s:property value="codigoAlbaran" />');">
									<img id="img_bt_editarAlbaran_<s:property value="codigoAlbaran" />" src="img/edit.png" alt="Editar albar&aacute;n" title="Editar albar&aacute;n"/>
								</a>
							</td>
							<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle;" border="0">
							  <a href="javascript:reporte('<s:property value="codigoAlbaran" />');"><img src="img/pdf_icon_16.gif" alt="Reporte" title="Reporte"/></a>
							</td>
							<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle;" border="0">
								<a id="bt_entregarAlbaran_<s:property value="codigoAlbaran" />" href="javascript:entregarAlbaran('<s:property value="codigoAlbaran" />');">
									<img id="img_bt_entregarAlbaran_<s:property value="codigoAlbaran" />" src="img/lorry.png" alt="Entregar albar&aacute;n" title="Entregar albar&aacute;n"/>
								</a>
							</td>
							<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle;" border="0">
								<a id="facturar_<s:property value="codigoAlbaran" />" href="javascript:albaranAFactura('<s:property value="codigoAlbaran" />','<s:property value="tipoAlbaran" />','<s:property value="estado" />');">
									<img id="img_facturar_<s:property value="codigoAlbaran" />" src="img/pill_go.png" alt="A factura" title="A factura"/>
								</a>
							</td>
							<td style="display: none;"><s:hidden id="facturado_%{#albaran.codigoAlbaran}" name="facturado" value="%{#albaran.facturado}"/></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div> <!-- end demo -->
		<s:hidden id="bgmNum" name="bgmNum" value="0" />
		<s:hidden id="estado" name="estado" value="0" />
		<p>&nbsp;</p>
	</div> <!-- end divNecesarioWidget -->
	<div id="dialogo" title="Reporte del albar&aacute;n" style="display: none;">
		<!-- <input id="checkEncabezado" name="checkEncabezado" type="checkbox">A&ntilde;adir encabezado</input><br />
		<br/> -->
		<input id="checkPrecios" name="checkPrecios" type="checkbox">A&ntilde;adir precios</input><br /><br />
		<input id="checkLineaCarrefour" name="checkLineaCarrefour" type="checkbox">A&ntilde;adir n&uacute;mero de pedido, fecha de entrega y horario</input><br /><br />
		<!-- <button id="bt_imprimir" class="i_pdf_document icon verdeOpilion" onclick="javascript:preparar('viejo');">Imprimir viejo</button> -->
		<button id="bt_imprimir_nuevo" class="i_pdf_document icon verdeOpilion" onclick="javascript:preparar('nuevo');">Imprimir</button>
	</div>
	<div id="ajaxEstados" name="ajaxEstados" style="display: none;"></div>
	<div id="ajaxFacturas" name="ajaxFacturas" style="display: none;"></div>
</s:i18n>