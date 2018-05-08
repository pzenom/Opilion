<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consulta procesos de envasado<span class="screenCode">CONS_GP_ENVA</span></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form id="listado" name="listado" action="ConsGPEnva" validate="true" method="get">
				<s:hidden id="idEnvasado" name="idEnvasado" value="0" />
				<s:hidden id="orden" name="orden" value="0"/>
				<s:hidden id="estado" name="estado" value="0"/>
				<s:hidden id="id" name="id" value="0"/>
				<s:hidden id="lote" name="lote" value="0"/>
				<fieldset><!-- Informacion Basica -->
					<section style="width: 50%;">
						<label for="dropdown_cuantos_mostrar">Filtrar</label>
						<div>
							<select name="dropdown_cuantos_mostrar" id="dropdown_cuantos_mostrar" onchange="javascript:filtraEnvasados();">
								
								<optgroup label="Mostrar...">
									<option value="0">Todos los procesos</option>
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
						<label for="dropdown_productos">Productos</label>
						<div>
							<select name="dropdown_productos" id="dropdown_productos" onchange="javascript:filtraEnvasados();">
								<option value="0">Seleccione el producto final a envasar</option>
								<optgroup label="Producto">
									<s:iterator id="tipos" value="%{#session.listaproductos}">
										<option value="<s:property value="idProducto" />">
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
		<s:form id="oculto" name="oculto" action="CancelaPendiente" validate="true" method="get" cssStyle="display: none;">
			<!-- <ss:hidden id="idEnvasado" name="idGestion" value="0" /> -->
			<s:hidden id="loteAsignado" name="loteAsignado" value="0" />
			<s:hidden id="visualizar" name="visualizar" value="0" />
		</s:form>
		<div id="demo">
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
						<th style="min-width: 80px;"></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="gprod" value="%{#session.listaregienvasados}">
						<s:hidden cssClass="listaEnvasados" value="0"/>
						<s:hidden id="idProducto_%{#gprod.orden}" name="idProducto_%{#gprod.orden}" value="%{#gprod.idProducto}"/>
						<s:hidden id="idEnvasado_%{#gprod.orden}" name="idEnvasado_%{#gprod.orden}" value="%{#gprod.idGestion}"/>
						<tr>						
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
								<a href="#" onClick="javascript:parent.get_ventana_emergente('EN','EtiqENJR.action?orden=<s:property value="orden" />','no','no','330','190','','');">
									<img src="img/etiqueta.png" alt="Etiqueta" title="Etiqueta" />
								</a>
								<a href="#" onClick="javascript:parent.get_ventana_emergente('REP','ConsDetaGPENJR.action?orden=<s:property value="orden" />','no','no','590','420','','');">
									<img src="img/report_go.png" alt="Reporte" title="Reporte" />
								</a>
								<a href="javascript:controlTiempos('<s:property value="orden" />');">
									<img src="img/icon_clock.png" alt="Control de tiempos" title="Control de tiempos" />
								</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<br />
		</div>
		<br />
	</div> <!-- end divNecesarioWidget -->
	<p>&nbsp;</p>
</s:i18n>