<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consulta procesos de envasado activos<span class="screenCode">CONS_GP_ENVA_ACTIV</span></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form id="listado" name="listado" action="FiltraListarEnvasar" validate="true">
				<s:hidden id="orden" name="orden" value="0"/>
				<s:hidden id="estadoProceso" name="estadoProceso" value="0"/>
				<s:hidden id="idProducto" name="idProducto" value="0"/>
				<s:hidden id="idEnvasado" name="idEnvasado" value="0"/>
				<s:hidden id="loteAsignado" name="loteAsignado" value="0"/>
				<fieldset>
					<section>
						<label for="dropdown_productos">Productos</label>
						<div>
							<select name="dropdown_productos" id="dropdown_productos" onchange="javascript:filtraEnvasadosActivos();">
								<option value="0">Seleccione el producto final</option>
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
				</fieldset>
			</s:form>
		</div> <!-- end consulta -->
		<p>&nbsp;</p>
		<div id="demo">
			<table id="tablaEnvasar" cellpadding="0" cellspacing="0" border="0" class="display" style="display: none;">
				<thead>
					<tr>
						<th><s:text name="envasa.orden" /></th>
						<th><s:text name="envasa.fechaProgramada" /></th>
						<th><s:text name="envasa.productoFinal" /></th>
						<!-- <th><s:text name="envasa.lote" /></th> -->
						<th><s:text name="envasa.horaInicio" /></th>
						<th><s:text name="envasa.cantidad" /></th>
						<th><s:text name="envasa.responsable" /></th>
						<th><s:text name="envasa.estado" /></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="gprod" value="%{#session.listaregienvasados}">
						<tr>
							<s:hidden cssClass="listaEnvasados" value="0"/>
							<s:hidden id="idProducto_%{#gprod.orden}" name="idProducto_%{#gprod.orden}" value="%{#gprod.idProducto}"/>
							<s:hidden id="idEnvasado_%{#gprod.orden}" name="idEnvasado_%{#gprod.orden}" value="%{#gprod.idGestion}"/>
							<s:hidden id="lote_%{#gprod.orden}" name="lote_%{#gprod.orden}" value="%{#gprod.loteAsignado}"/>
							<s:hidden id="tipoEan_%{#gprod.orden}" name="tipoEan_%{#gprod.orden}" value="%{#gprod.tipoEan}"/>
							<td><s:property value="orden" /></td>
							<td><s:property value="fecha" /></td>
							<td><s:property value="descProducto" /></td>
							<!-- <td><s:property value="loteAsignado" /></td> -->
							<td><s:property value="horaInicioProceso" /></td>
							<td><s:property value="cantidadProducto" /></td>
							<td><s:property value="usuarioResponsable" /></td>
							<td class="estado_<s:property value="estadoProceso" />" name="estado_<s:property value="orden" />" id="estado_<s:property value="orden" />"><s:property value="estadoProceso" /></td>
							<s:if test="%{#gprod.ubicado == #gprod.estaUbicado}">
								<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; width: 2%;">
									<a id="irAlProceso" href="javascript:gestionarProceso('<s:property value="orden" />')" title="Ir al proceso de envasado">
										<img src="img/pill_go.png" alt="Pildora con flecha para ir" title="Ir al proceso de envasado"/>
									</a>
								</td>
							</s:if>
							<s:else>
								<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; text-align:center; width: 2%;">
									<a id="sacar" href="javascript:sacarEnvasado('<s:property value="orden" />')" title="Sacar materias y envases del almacen">
										<img src="img/package.png" alt="Sacar materias y envases del almacen" title="Sacar materias y envases del almacen"/>
									</a>
								</td>
								<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; text-align:center; width: 2%;">
									<a id="eliminar" href="javascript:eliminarEnvasadoPendiente('<s:property value="orden" />')" title="Eliminar proceso de envasado">
										<img src="img/cancel.png" alt="Eliminar proceso de envasado" title="Eliminar proceso de envasado"/>
									</a>
								</td>
							</s:else>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<p>&nbsp;</p>
		</div>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>