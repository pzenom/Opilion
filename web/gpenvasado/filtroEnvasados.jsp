<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<table id="tablaProcesos" cellpadding="0" cellspacing="0" border="0" class="display">
		<thead>
			<tr>
				<th><s:text name="crenv.orden" /></th>
				<th><s:text name="crenv.fechaProgramada" /></th>
				<th><s:text name="crenv.productoFinal" /></th>
				<th><s:text name="crenv.lote" /></th>
				<th><s:text name="crenv.horaInicio" /></th>
				<th><s:text name="crenv.horaFin" /></th>
				<th><s:text name="crenv.cantidad" /></th>
				<th><s:text name="crenv.responsable" /></th>
				<th><s:text name="crenv.estado" /></th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="gprod" value="%{#session.listaregienvasados}">
				<tr>
					<s:hidden cssClass="listaEnvasados" value="0"/>
					<s:hidden id="idProducto_%{#gprod.orden}" name="idProducto_%{#gprod.orden}" value="%{#gprod.idProducto}"/>
					<s:hidden id="idEnvasado_%{#gprod.orden}" name="idEnvasado_%{#gprod.orden}" value="%{#gprod.idGestion}"/>
					<td><s:property value="orden" /></td>
					<td><s:property value="fecha" /></td>
					<td><s:property value="descProducto" /></td>
					<td id="lote_<s:property value='orden'/>"><s:property value="loteAsignado" /></td>
					<td><s:property value="horaInicioProceso" /></td>
					<td><s:property value="horaFinProceso" /></td>
					<td><s:property value="cantidadProducto" /></td>
					<td><s:property value="usuarioResponsable" /></td>
					<td class="estado_<s:property value="estadoProceso" />" id="estado_<s:property value='orden'/>"><s:property value="estadoProceso" /></td>
					<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle;">
						<a id="actualiza" href="javascript:irAlProceso('<s:property value="orden" />')" title="Ir al proceso de envasado">
							<img src="img/zoom.png" alt="Pildora con flecha para seguir" title="Ir al proceso de envasado"/>
						</a>
					</td>
					<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle;" border="0">
						<a href="#" onClick="javascript:parent.get_ventana_emergente('EN','EtiqENJR.action?orden=<s:property value="orden" />','no','no','330','190','','');"><img src="img/etiqueta.png" alt="Etiqueta" title="Etiqueta" /></a>
					</td>
					<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle;" border="0">
						<a href="#" onClick="javascript:parent.get_ventana_emergente('REP','ConsDetaGPENJR.action?orden=<s:property value="orden" />','no','no','590','420','','');"><img src="img/report_go.png" alt="Reporte" title="Reporte" /></a>
					</td>
					<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle;" border="0">
						<a href="javascript:controlTiempos('<s:property value="orden" />');"><img src="img/icon_clock.png" alt="Control de tiempos" title="Control de tiempos" /></a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<p>&nbsp;</p>
</s:i18n>