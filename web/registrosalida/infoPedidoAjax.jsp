<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:iterator id="pedidos" value="%{#session.materiaCategorias}" status="status">
		<s:hidden id="idCategoria_%{#status.count}" cssClass="categoria" value="%{#categorias.idCategoria}" />
		<s:hidden id="nombreCategoria_%{#categorias.idCategoria}" cssClass="nombresCategorias" value="%{#categorias.nombreCategoria}" />
		<s:hidden id="stockCategoria_%{#categorias.idCategoria}" value="%{#categorias.stock}" />
	</s:iterator>
</s:i18n>