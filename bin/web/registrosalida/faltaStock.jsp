<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="faltaStock.titulo" /> - <s:text name="faltaStock.texto" /></h3>							
	<br />
	<s:form id="formulario" name="formulario" action="ProgramarEnvasado" validate="true">
		<div id="demo">
			<table id="tablaAlbaranesFalta" cellpadding="0" cellspacing="0" border="0" class="tabla" >
				<thead>
					<tr>
						<th><s:text name="faltaStock.ean" /></th>
						<th><s:text name="faltaStock.descripcion" /></th>
						<th><s:text name="faltaStock.cantidadExistente" /></th>
						<th><s:text name="faltaStock.cantidadNecesaria" /></th>
						<!-- <th>Falta</th> -->
					</tr>
				</thead>
				<tbody>
					<s:iterator id="faltan" value="%{#session.listaFaltaStock}">
						<tr>
							<s:hidden id="ean_%{codigoEan}" value="%{codigoEan}" name="ean_%{codigoEan}" cssClass="eans"/>
							<s:hidden id="envasar_%{codigoEan}" name="envasar_%{codigoEan}" />
							<td><p id="ean_<s:property value="codigoEan"/>" name="ean_<s:property value="codigoEan"/>" class="ean" style="background: transparent;" value="hola"><s:property value="codigoEan" /></td>
							<td><s:property value="nombre" /></td>
							<td><p id="stock_<s:property value="codigoEan"/>" class="stocks" style="background: transparent;"><s:property value="stock" /></p></td>
							<td><p id="necesitan_<s:property value="codigoEan"/>" class="necesitan" style="background: transparent;"><s:property value="cantidad" /></td>
							<!-- <td><p id="falta_<s:property value="codigoEan"/>" name="falta_<s:property value="codigoEan"/>" class="falta" style="background: transparent;"><s:property value="stock" /></td> -->
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div><!-- end demo -->
	</s:form>
	<!-- <button class="i_plus icon verdeOpilion" onclick="javascript:volver();">Volver</button> -->
</s:i18n>