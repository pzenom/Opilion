<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.edifact.data.*,es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Error registrando la &oacute;rden<span class="screenCode">EDIFACT_ERROR</span></h3>
	<s:hidden id="errorSalida" value="1" />
	<s:iterator id="mensaje" value="%{#session.mensaje}" >
		<fieldset>
			<legend><span><s:text name="registro.edifact.orders.cabecera" /></span></legend>
			<s:property value="mensaje" />
		</fieldset>
	</s:iterator>
</s:i18n>