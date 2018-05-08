<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:iterator id="direcciones" value="%{#session.direccionesCliente}" status="status">
		<s:hidden id="idDireccion_%{#status.count}" cssClass="direccionesClienteCargadas" value="%{#direcciones.idDireccion}" />
		<s:hidden id="calleDireccion_%{#direcciones.idDireccion}" value="%{#direcciones.nombreCalle}" />
		<s:hidden id="localidadDireccion_%{#direcciones.idDireccion}" value="%{#direcciones.localidad}" />
	</s:iterator>
</s:i18n>