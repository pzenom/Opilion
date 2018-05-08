<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="Consulta procesos de congelados" /> - CONS_GP_CONG</h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form name="listado" action="ConsGPCong" validate="true">
				<fieldset><!-- Filtro Campos-->
					<label>Criterios de seleccion</label>
					<section class="sectionMitad">
						<label for="ordenConsulta">Orden</label>
						<div>
							<input type="text" id="ordenConsulta" name="ordenConsulta" title="Orden">
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
				</fieldset><!--end product info-->
			</s:form>
		</div> <!-- end consulta -->
		<p>&nbsp;</p>
		<div id="demo">
			<table id="tablaRegistros" cellpadding="0" cellspacing="0" border="0" class="display" style="display: none;">
				<thead>
					<tr>
						<th>Orden</th>
						<th>Fecha</th>
						<th>Producto final</th>
						<th>Lote</th>
						<th>Hora inicio</th>
						<th>Hora fin</th>
						<th>Cantidad</th>
						<th>Merma MP</th>
						<th>Merma Envases</th>
						<th>Responsable</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="entidad" value="%{#session.listacongelados}">
						<tr>
							<td><s:property value="orden" /></td>
							<td><s:property value="fecha" /></td>
							<td><s:property value="descIngrediente" /></td>
							<td><s:property value="loteAsignado" /></td>
							<td><s:property value="horaInicioProceso" /></td>
							<td><s:property value="horaFinProceso" /></td>
							<td><s:property value="cantidadProducto" /></td>
							<td><s:property value="mermaIngredientes" /></td>
							<td><s:property value="mermaEnvases" /></td>
							<td><s:property value="idOperario" /></td>
							<td style="text-align:center;">
								<a href="javascript:ubicarSalidaCongelado('<s:property value="orden" />');"><img src="img/package.png" alt="Ubicar" title="Ubicar" /></a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div> <!-- end demo -->	
	</div> <!-- end divNecesarioWidget -->
	<p>&nbsp;</p>
</s:i18n>