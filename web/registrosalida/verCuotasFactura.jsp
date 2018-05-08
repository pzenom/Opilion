<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Cuotas de la factura <s:property value="%{#session.codigoFactura}" /><span class="screenCode">CUOTAS_FACT</span></h3>
	<s:form id="formulario" name="formulario" action="#" validate="true">
		<fieldset>
			<div id="divTablaCuotasFacturas">
				<table id="tablaCuotasFacturas" cellpadding="0" cellspacing="0" border="0" class="display" >
					<thead>
						<tr>
							<th>Factura</th>
							<th>Descripci&oacute;n</th>
							<th>Fecha</th>
							<th>Estado</th>
							<th>Importe</th>
						</tr>
					</thead>
					<tbody id="tablaCuotasFacturasBody">
						<s:iterator id="cuota" value="%{#session.cuotas}">
							<tr id="cuotaFactura_<s:property value="codigoNuevaFactura" />" >
								<td style="width: 180px !important; vertical-align: middle;">
									<s:property value="codigoNuevaFactura" />
								</td>
								<td style="width: 180px !important; vertical-align: middle;">
									<s:property value="observaciones" />
								</td>
								<td style="width: 180px !important; vertical-align: middle;">
									<s:property value="fecha" />
								</td>
								
								<!-- <td style="width: 180px !important; vertical-align: middle;">
									<s:property value="estado" />
								</td> -->
								<td id="celdasEstados_<s:property value="codigoNuevaFactura" />" class="estados" onmouseover="javascript:muestraEstadoFactura('<s:property value="idEstado" />', '<s:property value="codigoNuevaFactura" />');"><s:property value="estado" /></td>
								<td style="width: 180px !important; vertical-align: middle;">
									<span id="spanImporte_<s:property value="codigoNuevaFactura" />" class="numeroMilesDecimal"><s:property value="importe" /></span>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</fieldset>
	</s:form>
	<div id="ajaxEstados" name="ajaxEstados" style="display: none;"></div>
</s:i18n>