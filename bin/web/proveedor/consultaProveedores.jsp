<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consultar Proveedores<span class="screenCode">CONS_PROVE</span></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form target="ajaxDiv" id="formuAjax" name="formuAjax" action="ConsultaProveedores" method="get" >
				<fieldset><!-- Informacion Basica -->
					<section>
						<label for="dropdown_tipos">Tipo de proveedor</label>
						<div>
							<select name="dropdown_tipos" id="dropdown_tipos" onchange="javascript:filtraProveedores();">
								<option value="0">Todos</option>
								<optgroup label="Tipo">
									<s:iterator id="tipos" value="%{#session.listatipos}">
										<option value="<s:property value="idTipo" />">
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
		<br />
		<div id="demo">
			<table id="tablaProveedores" cellpadding="0" cellspacing="0" border="0" class="display" style="display:none;">
				<thead>
					<tr>
						<th><s:label name="%{getText('cons.label.ID')}" value="%{getText('cons.label.ID')}" /></th>
						<th>Proveedor</th>
						<th><s:label name="%{getText('cons.label.cifnif')}" value="%{getText('cons.label.cifnif')}" /></th>
						<th><s:label name="%{getText('cons.label.fregistro')}" value="%{getText('cons.label.fregistro')}" /></th>
						<th style="max-width: 40px;">Acciones</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="entidad" value="%{#session.listaproveedores}">
						<tr>
							<td style="width: 5%;"><s:property value="idUsuario" /></td>
							<td><s:property value="nombre" /> <s:property value="apellidos" /></td>
							<td><s:property value="nif" /></td>
							<td><s:property value="fecha" /></td>
							<td id="celdaCargarProveedor_<s:property value="idUsuario" />" class="celdaCargarProveedor" style="text-align: center; max-width: 40px;">
								<a href="javascript:cargarEntidad(<s:property value="idUsuario" />);">
									<img src="img/edit.png" alt="Editar" title="Editar"/>
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