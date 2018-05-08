<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:div name="ajax" id="ajaxDiv">
		<!-- <p>&nbsp;</p> -->
		<s:form id="formulario" name="formulario" action="EnvasadoAjax" validate="true" method="get">
			
			<!-- <ss:iterator id="productos" value="%{#session.listaproductosenvasar}" status="status"> -->
				<fieldset><!-- Filtro Campos-->
					<legend><span>AQUI LAS LINEAS DE LA ZONA: <s:property value='%{#session.zona}'/></span></legend>
		</s:form>
	</s:div>
</s:i18n>