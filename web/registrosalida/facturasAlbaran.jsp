<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:iterator id="facturas" value="%{#session.facturas}" status="status">
		<s:hidden id="codigoFactura_%{#status.count}" cssClass="codigoFactura" value="%{#facturas.codigoFactura}" />
		<s:hidden id="fecha_%{#facturas.codigoFactura}" cssClass="" value="%{#facturas.fecha}" />
		<s:hidden id="fechaVencimiento_%{#facturas.codigoFactura}" cssClass="" value="%{#facturas.fechaVencimiento}" />
		<s:hidden id="importeTotal_%{#facturas.codigoFactura}" cssClass="" value="%{#facturas.importeTotal}" />
	</s:iterator>
</s:i18n>