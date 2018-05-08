<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<table id="tablaProveedores" cellpadding="0" cellspacing="0" border="0" class="display">
		<thead>
			<tr>
				<th><s:label name="%{getText('cons.label.ID')}" value="%{getText('cons.label.ID')}" /></th>
				<th><s:label name="%{getText('cons.label.razonsocial')}" value="%{getText('cons.label.razonsocial')}" /></th>
				<th><s:label name="%{getText('cons.label.cifnif')}" value="%{getText('cons.label.cifnif')}" /></th>
				<th><s:label name="%{getText('cons.label.fregistro')}" value="%{getText('cons.label.fregistro')}" /></th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="entidad" value="%{#session.listaproveedores}">
				<tr>
					<td><s:property value="idUsuario" /></td>
					<td><s:property value="nombre" /> <s:property value="apellidos" /></td>
					<td><s:property value="nif" /></td>
					<td><s:property value="fecha" /></td>
					<td>
						<a href="javascript:cargarProveedor(<s:property value="idUsuario" />);">
							<img src="img/edit.png" alt="Editar" title="Editar"/>
						</a>
					</td>
					<td>
						<a href="javascript:eliminarProveedor(<s:property value="idUsuario" />);">
							<img src="img/cancel.jpg" alt="Deshabilitar" title="Deshabilitar"/>
						</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
</s:i18n>