<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consultar devoluciones<span class="screenCode">CONS_DEVOL</span></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
		</div> <!-- end consulta -->
		<p>&nbsp;</p>
		<div id="demo">
			<table id="tablaDevoluciones" cellpadding="0" cellspacing="0" border="0" class="display" style="display: none;">
				<thead>
					<tr>
						<th style="width: 60px;">C&oacute;digo</th>
						<th style="width: 60px;">Cliente</th>
						<th style="width: 60px;">Fecha</th>
						<th style="width: 60px;">Lote</th>
						<th style="width: 60px;">Cantidad</th>
						<th style="width: 60px;">Responsable</th>
						<th style="width: 60px;">Causa</th>
						<th style="width: 60px;">Acci&oacute;n correctora</th>
						<th style="width: 60px;">Notas</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="orden" value="%{#session.listaDevoluciones}">
						<tr>
							<td id="entrada_<s:property value="codigoEntrada" />" class="entrada"><s:property value="codigoEntrada" /></td>
							<td><s:property value="nombreClienteDevolucion" /></td>
							<td><s:property value="fechaLlegada" /></td>
							<td><s:property value="lote" /></td>
							<td><s:property value="cantidad" /></td>
							<td><s:property value="descResponsable" /></td>
							<td><s:property value="incidencia" /></td>
							<td id="incidenciaEntrada_<s:property value="codigoEntrada" />" style="display: none;"><s:property value="idIncidencias" /></td>
							<td id="reaprovechar_<s:property value="codigoEntrada" />" style="display: none;"><s:property value="reaprovechar" /></td>
							<td><s:property value="accionCorrectora" /></td>
							<td><s:property value="notasIncidencias" /></td>
							<td id="botonesEntrada_<s:property value="codigoEntrada" />" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; width: 30px; display: none;">
								<a id="destripa" href="javascript:destriparDevolucion('<s:property value="lote" />')" title="Reaprovechar">
									<img src="img/pill_go.png" alt="Pildora con flecha para reaprovechar" title="Reaprovechar"/>
								</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<p>&nbsp;</p>
		</div>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>