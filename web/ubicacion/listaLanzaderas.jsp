<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consulta lanzaderas<span class="screenCode">LISTA_LANZA</span></h3>
	<div id="divNecesarioWidget">
		<div id="demo">
			<table id="tablaLanzaderas" cellpadding="0" cellspacing="0" border="0" class="display" style="display: none;">
				<thead>
					<tr>
						<th><s:label name="%{getText('cons.label.ID')}" value="%{getText('cons.label.ID')}" /></th>
						<th>Fecha</th>
						<th>Fecha</th>
						<th>Responsable</th>
						<th>N&uacute;mero</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="lanzadera" value="%{#session.vectorLanzaderas}">
						<tr>
							<td><s:property value="idLanzadera" /></td>
							<td><s:property value="fecha" /></td>
							<td><s:property value="fechaFormateada" /></td>
							<td><s:property value="usuarioResponsable" /></td>
							<td><s:property value="lanzaderaNumero" />/<s:property value="totalLanzaderas" /></td>
							<td>
								<a id="pdfLanzadera_<s:property value="idLanzadera" />" href="javascript:muestraReporteLanzadera('<s:property value="nombreReporte" />');">
									<img src="img/pdf_icon_16.gif" alt="Reporte" title="Reporte"/>
								</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
		<div id="ajax_div_consultaProductosLanzadera">
		</div>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>