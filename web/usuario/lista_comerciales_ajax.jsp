<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:iterator id="comercial" value="%{#session.vectorComerciales}" status="status">
		<s:hidden id="comercial_%{#comercial.idUsuario}" cssClass="comercialAjax" value="%{#comercial.idUsuario}" />
		<s:hidden id="login_%{#comercial.idUsuario}" value="%{#comercial.login}" />
	</s:iterator>
</s:i18n>