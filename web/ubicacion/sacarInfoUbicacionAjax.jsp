<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:div name="ajax" id="ajaxDiv">
		<s:form target="ajaxDiv" id="formulario" name="formulario" action="CargaMapaAlmacen" validate="true" method="get">
			<s:iterator id="ubicacion" value="%{#session.ubicacion}">
				<s:hidden id="idZona" key="idZona" name="idZona" value="%{#ubicacion.idZona}"/>
				<s:hidden id="idAlmacen" key="idAlmacen" name="idAlmacen" value="%{#ubicacion.idAlmacen}"/>
				<s:hidden id="idLinea" key="idLinea" name="idLinea" value="%{#ubicacion.idLinea}"/>
				<s:hidden id="idEstanteria" key="idEstanteria" name="idEstanteria" value="%{#ubicacion.idEstanteria}"/>
				<s:hidden id="idPiso" key="idPiso" name="idPiso" value="%{#ubicacion.idPiso}"/>
				<!-- <s:hidden id="idHueco" key="idHueco" name="idHueco" value="%{#ubicacion.idHueco}"/> -->
				<!-- <s:hidden id="registro" key="registro" name="registro" value="%{#ubicacion.registro}"/> -->
				<s:hidden id="idTipoRegistro" key="idTipoRegistro" name="idTipoRegistro" value="E"/>
				<table class="tabla" width="100%" cellpadding="2" cellspacing="2" border="0"> 
					<tr>
						<td class="nowrap">
							<s:textfield id="almacen" disabled="true" key="almacen" label="Almacen" cssStyle="width:180px;" value="%{#ubicacion.nombreAlmacen}"/>
						</td>
					</tr>
					<tr>
						<td class="nowrap">
							<s:textfield id="zona" disabled="true" key="zona" label="Zona" cssStyle="width:180px;" value="%{#ubicacion.nombreZona}"/>
						</td>
					</tr>
					<tr>
						<td class="nowrap">
							<s:textfield id="linea" disabled="true" key="linea" label="Linea" cssStyle="width:180px; background-color: #f2f2f2;" value="%{#ubicacion.nombreLinea}"/>
						</td>
					</tr>
					<tr>
						<td class="nowrap">
							<s:textfield id="estanteria" key="estanteria" disabled="true" label="Estanteria" cssStyle="width:180px;" value="%{#ubicacion.nombreEstanteria}"/>
						</td>
					</tr>
					<tr>
						<td class="nowrap">
							<s:textfield id="piso" key="piso" label="Piso" disabled="true" cssStyle="width:180px;" value="%{#ubicacion.nombrePiso}"/>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap">
							<s:textfield id="hueco" key="hueco" disabled="true" cssStyle="width:180px;" label="Hueco" value="%{#ubicacion.nombreHueco}"/>
						</td>
					</tr>
					<tr>
						<td class="nowrap">
							<s:textfield id="ref" key="ref" name="ref" label="Referencia ubicacion" disabled="true" cssStyle="width:180px;" 	value="%{#ubicacion.nombreHueco}"/>
						</td>
					</tr>
				</table>
			</s:iterator>	
			<s:submit id="submitAjax" targets="ajaxDiv" value="Seleccionar producto" cssStyle="visibility: hidden;"/>
		</s:form>
	</s:div>
</s:i18n>