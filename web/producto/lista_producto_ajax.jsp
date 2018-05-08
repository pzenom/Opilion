<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
<s:iterator id="producto" value="%{#session.producto}" status="status">
	<s:hidden id="nombreProducto" value="%{#producto.nombre}" />
</s:iterator>
</s:i18n>