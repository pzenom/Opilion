<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">	
	<s:div name="ajaxMuestra" id="ajaxMuestra">
		<fieldset>
			<legend><span>Registros almacenados en este hueco</span></legend>
			<table id="almacenados" class="tabla" width="100%" cellpadding="2" cellspacing="2" border="0">
				<s:iterator id="almacenados" value="%{#session.almacenados}" status="status">
					<tr>
						<td>
							<s:textfield id="almacenado_%{#status.count}" cssClass="almacenado" disabled="true" key="almacen" label="Almacen" cssStyle="width:180px;" value="%{#almacenados.registro}"/>
						</td>
					</tr>
				</s:iterator>
			</table>
			<img src="img/planos/palet.png" style="display: none;" onload="javascript: simple_tooltip('<s:property value='%{#session.idHueco}'/>','tooltip');">
		</fieldset>
	</s:div>
	<s:form id="formu" name="formu" action="MuestraAlmacenadosAjax" validate="true" method="post">
		<s:hidden id="idHuecoMuestra" key="idHuecoMuestra" name="idHuecoMuestra"/>
		<s:hidden id="descripcionHueco" key="descripcionHueco" name="descripcionHueco" value="%{#session.descripcionHueco}"/>
		<s:submit id="submitMuestra" targets="ajaxMuestra" value="MuestraAlmacenadosAjax" cssStyle="visibility:hidden;"/>
	</s:form>
</s:i18n>