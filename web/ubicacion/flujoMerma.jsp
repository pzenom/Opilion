<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Flujo merma</h3>
	<div id="divNecesarioWidget">							
		<s:form id="formuFlujoMovimiento" name="formuFlujoMovimiento" action="RealizarFlujoMerma"><!-- -->
			<s:iterator id="flujo" value="%{#session.flujo}" status="status">
				<fieldset>
					<legend><span>Ejecuci&oacute;n</span></legend>
						<s:textfield id="descripcionAccion" name="descripcionAccion" cssStyle="font-size: 40px; width: 450px; " value="Merma en almacen" disabled="true"/>
					<!-- <s:form name="formu" action="ComenzarFlujo">
					</s:form> -->
				</fieldset>
				<fieldset>
					<legend><span>Origen</span></legend>
						<s:textfield id="origen" name="origen" key="origen" cssStyle="font-size: 40px; width: 450px;" onchange="javascript:enviar();" value="%{#flujo.origen}" onkeyup="keyup(2);"/>
					<!-- <s:form name="formu" action="ComenzarFlujo">
					</s:form> -->
				</fieldset>
				<fieldset>
					<legend><span>Producto</span></legend>
						<s:textfield id="producto" cssClass="inputs" name="producto" cssStyle="font-size: 40px; width: 450px;" onchange="javascript:enviar();" value="%{#flujo.producto}" disabled="true" onkeyup="keyup(3);"/>
					<!-- <s:form name="formu" action="ComenzarFlujo">
					</s:form> -->
				</fieldset>
				<fieldset>
					<legend><span>Unidades</span></legend>
						<s:textfield id="cantidad" name="cantidad" cssClass="inputs" cssStyle="font-size: 40px; width: 450px;" onchange="enviar();" value="%{#flujo.cantidad}" disabled="true" onkeyup="keyup(4);"/>
					<!-- <s:form name="formu" action="ComenzarFlujo">
					</s:form> -->
				</fieldset>
				<fieldset>
					<legend><span>C&oacute;digo de escape</span></legend>
					<s:textfield id="codigoEscape" name="codigoEscape" cssClass="inputs" disabled="true" cssStyle="font-size: 40px; width: 450px;" onchange="enviar();" onkeyup="keyup(5);"/>
				</fieldset>
			</s:iterator>
		</s:form>
		<!-- <button class="i_arrow_left icon verdeOpilion" onclick="javascript:nuevoGPSele();">Volver</button> -->
	</div><!-- end #divNecesarioWidget -->
</s:i18n>