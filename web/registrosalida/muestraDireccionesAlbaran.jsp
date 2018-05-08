<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:iterator id="direcciones" value="%{#session.direcciones}">
		<s:hidden id="direccion_%{#direcciones.idDireccion}" value="%{#direcciones.nombreCalle} %{#direcciones.localidad}" cssClass="direccionAlbaran"/>
	</s:iterator>
</s:i18n>