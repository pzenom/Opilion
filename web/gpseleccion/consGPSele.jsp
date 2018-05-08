<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consulta procesos de seleccion<span class="screenCode">CONS_GP_SELE</span></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form action="ConsGPSele" id="listado" name="listado" validate="true">
				<fieldset><!-- Filtro Campos-->
					<label>Criterios de seleccion</label>
					<section class="sectionMitad">
						<label for="orden">Orden</label>
						<div>
							<input type="text" id="ordenSeleccion" name="orden" title="Orden">
							<span>Introduzca la orden</span>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="fechaConsulta">Fecha del proceso</label>
						<div>
							<input id="fechaConsulta" name="fechaConsulta" type="text" class="date">
							<span>Introduzca la fecha del proceso</span>
						</div>
					</section>
					<!-- <section class="sectionMitad">
						<label for="loteAsignado">Lote</label>
						<div>
							<input type="text" id="loteAsignado" name="loteAsignado" title="Lote">
							<span>Introduzca el lote</code></span>
						</div>
					</section> -->
				</fieldset><!--end product info-->
			</s:form>
		</div> <!-- end consulta -->
		<p>&nbsp;</p>
		<div id="demo">
			<table id="tablaSelecciones" cellpadding="0" cellspacing="0" border="0" class="display" style="display: none;">
				<thead>
					<tr>
						<th>Codigo proceso</th>
						<th>Fecha</th>
						<th>Producto</th>
						<th>Hora inicio</th>
						<th>Observaciones</th>
						<th>Responsable</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="entidad" value="%{#session.listaseleccion}">
						<tr>
							<td><s:property value="orden" /></td>
							<td><s:property value="fecha" /></td>
							<td><s:property value="descProducto" /></td>
							<td><s:property value="horaInicioProceso" /></td>
							<td><s:property value="observaciones" /></td>
							<td><s:property value="idOperario" /></td>
							<td style="text-align:center;">
								<a href="javascript:ubicarProceso('<s:property value="orden" />');"><img src="img/package.png" alt="Ubicar" title="Ubicar" /></a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div> <!-- end demo -->
	</div> <!-- end divNecesarioWidget -->
	<p>&nbsp;</p>
</s:i18n>