<%@page contentType="text/html; charset=iso-8859-15" pageEncoding="iso-8859-15"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consultar &oacute;rdenes de entrada<span class="screenCode">CONS_OE</span></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form id="formulario" action="ConsOE" validate="true">
				<s:hidden id="idTipo" name="idTipo" key="idTipo" value="%{#session.idTipo}" />
				<fieldset><!-- Informacion Basica -->
					<section style="width: 50%;">
						<label for="dropdown_cuantos_mostrar">Filtrar</label>
						<div>
							<select name="dropdown_cuantos_mostrar" id="dropdown_cuantos_mostrar" onchange="javascript:filtraOE();">
								
								<optgroup label="Mostrar...">
									<option value="0">Todas las entradas</option>
									<option value="1" selected>&Uacute;ltimas 20</option>
									<option value="2">&Uacute;ltimas 50</option>
									<option value="3">&Uacute;ltima semana</option>
									<option value="4">&Uacute;ltimo mes</option>
									<option value="5">&Uacute;ltimo a&ntilde;o</option>
								</optgroup>
							</select>
						</div>
					</section>
					<section style="width: 50%;">
						<label for="dropdown_proveedores">Proveedor</label>
						<div>
							<select name="dropdown_proveedores" id="dropdown_proveedores" onchange="javascript:filtraOE();">
								<option value="0">Todos</option>
								<optgroup label="Proveedor">
									<s:iterator id="tipos" value="%{#session.listaproveedores}">
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
			<table id="tablaOE" cellpadding="0" cellspacing="0" border="0" class="display" style="display: none;">
				<thead>
					<tr>
						<th>Orden</th>
						<th>Proveedor</th>
						<th>Fecha</th>
						<th>Albaran</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="orden" value="%{#session.listaordenes}">
						<s:hidden cssClass="listaRegis" value="0" />
						<tr>
							<td><s:property value="codigoOrden" /></td>
							<td><s:property value="proveedor.nombre" /></td>
							<td><s:property value="fecha" /></td>
							<td><s:property value="albaran" /></td>
							<td>
								<a href="javascript:verOrden('<s:property value="codigoOrden" />');">
									<img id="orden-<s:property value="codigoOrden" />" src="img/zoom.png" alt="Ver Orden" title="Ver Orden"/>
								</a>
								<a href="javascript:editarOE('<s:property value="codigoOrden" />');">
									<img id="ordenEditar-<s:property value="codigoOrden" />" src="img/edit.png" alt="Editar orden" title="Editar orden"/>
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