<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<s:iterator id="productos" value="%{#session.productosPedido}" status="status">
		<s:textfield id="producto_%{#status.count}" cssClass="producto" disabled="true" cssStyle="width:180px;" value="%{#productos.nombre}"/>
		<s:textfield id="cantidadProducto_%{#status.count}" disabled="true" cssStyle="width:180px;" value="%{#productos.peso}"/>
	</s:iterator>
	<s:hidden id="idPedido" key="idPedido" name="idPedido" value="%{#session.idPedido}"/>
</s:i18n>