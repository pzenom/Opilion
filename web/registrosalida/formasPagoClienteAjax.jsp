<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:iterator id="formasPago" value="%{#session.formasPagoCliente}" status="status">
		<s:hidden id="idFormaPago_%{#status.count}" cssClass="formasPagoCliente" value="%{#formasPago.idDatoBancario}" />
		<s:hidden id="descripcionFormaPago_%{#formasPago.idDatoBancario}" value="%{#formasPago.descripcion}" />
		<s:hidden id="diasFormaPago_%{#formasPago.idDatoBancario}" value="%{#formasPago.diasFormaPago}" />
		<s:hidden id="diaPago_%{#formasPago.idDatoBancario}" value="%{#formasPago.diaPago}" />
		<s:hidden id="cuentaAsociada_%{#formasPago.idDatoBancario}" value="%{#formasPago.cuentaAsociada}" />
		<s:hidden id="numCuenta_%{#formasPago.idDatoBancario}" value="%{#formasPago.numCuenta}" />
		<s:hidden id="descripcionFormaPagoDesde_%{#formasPago.idDatoBancario}" value="%{#formasPago.descripcionFormaPagoDesde}" />
	</s:iterator>
</s:i18n>