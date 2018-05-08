<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
<s:iterator id="ubicacion" value="%{#session.listaUbicaciones}" status="status">
	<s:hidden id="ubicacion_%{#ubicacion.idUbicacion}" cssClass="ubicacionAjax" value="%{#ubicacion.idUbicacion}" />
	<s:hidden id="descUbicacion_%{#ubicacion.idUbicacion}" value="%{#ubicacion.descripcion}" />
	<s:hidden id="idHueco_%{#ubicacion.idUbicacion}" value="%{#ubicacion.idHueco}" />
</s:iterator>
</s:i18n>