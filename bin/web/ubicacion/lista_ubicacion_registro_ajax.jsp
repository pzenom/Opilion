<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
<s:iterator id="ubicacion" value="%{#session.ubicacion}" status="status">
	<s:hidden id="cantidad" value="%{#ubicacion.cantidad}" />
</s:iterator>
</s:i18n>