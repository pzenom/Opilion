<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="consu_clientes.bienvenida" /><span class="screenCode">CONS_CLIE</span></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form target="ajaxDiv" id="formuAjax" name="formuAjax" action="ConsultaClientes" >
				<fieldset>
					<section class="sectionMitad">
						<label for="dropdown_sectores">Sector del cliente</label>
						<div>
							<select name="dropdown_sectores" id="dropdown_sectores" onchange="javascript:filtraClientes();">
								<option value="0">Todos</option>
								<optgroup label="Sector">
									<s:iterator id="sectores" value="%{#session.listasectores}">
										<option value="<s:property value="idSector" />">
											<s:property value="nombreSector" />
										</option>
									</s:iterator>
								</optgroup>
							</select>
						</div>
					</section>
				</fieldset>
			</s:form>
		</div> <!-- end consulta -->
		<br />
		<div id="demo">
			<table id="tablaClientes" cellpadding="0" cellspacing="0" border="0" class="display" style="display: none;">
				<thead>
					<tr>
						<th><s:label name="%{getText('cons.label.ID')}" value="%{getText('cons.label.ID')}" /></th>
						<th><s:label name="%{getText('cons.label.nombre')}" value="%{getText('cons.label.cliente')}" /></th>
						<th><s:label name="%{getText('cons.label.cifnif')}" value="%{getText('cons.label.cifnif')}" /></th>
						<th><s:label name="%{getText('cons.label.fregistro')}" value="%{getText('cons.label.fregistro')}" /></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="entidad" value="%{#session.listaclientes}">
						<s:hidden id="%{#entidad.idUsuario}" cssClass="clientes" />
						<s:hidden id="deudas_%{#entidad.idUsuario}" value="%{#entidad.deudas}" />
						<tr>
							<td><s:property value="idUsuario" /></td>
							<td><s:property value="nombre" /> <s:property value="apellidos" /></td>
							<td><s:property value="nif" /></td>
							<td><s:property value="fecha" /></td>
							<td id="celdaCargarEntidad_<s:property value="idUsuario" />" class="celdaCargarEntidad" style="text-align: center; display: none;">
								<a href="javascript:cargarEntidad(<s:property value="idUsuario" />);">
									<img src="img/edit.png" alt="<s:text name='consu_clientes.editar' />" title="<s:text name='consu_clientes.editar' />"/>
								</a>
							</td>
							<td id="celdaGestionarSalidas_<s:property value="idUsuario" />" class="celdaGestionarSalidas" style="display: none;">
								<a href="javascript:gestionarSalidas(<s:property value="idUsuario" />);">
									<img src="img/lorry_go.png" alt="Gestion de pedidos/albaranes/facturas" title="Gestion de pedidos/albaranes/facturas"/>
								</a>
							</td>
							<td>
								<a id="enlaceDeudas_<s:property value="idUsuario" />" href="javascript:$.msg('Cliente con facturas por pagar');" style="display: none;">
									<img src="img/ico_error.png" alt="Deuda" title="Deuda"/>
								</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>