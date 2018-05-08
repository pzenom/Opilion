<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<s:iterator id="lotes" value="%{#session.lotesProducto}" status="status">
		<s:textfield id="lote_%{#status.count}" cssClass="loteProducto" cssStyle="width:180px;" value="%{#lotes.lote}. Hueco: %{#lotes.ubicacion}. Cantidad: %{#lotes.cantidad}" />
	</s:iterator>
</s:i18n>