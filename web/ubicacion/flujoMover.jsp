<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Flujo mover</h3>
	<div id="divNecesarioWidget">
		<s:form id="formuFlujoMovimiento" name="formuFlujoMovimiento" action="RealizarFlujoMover"><!-- -->
			<s:iterator id="flujo" value="%{#session.flujo}" status="status">
				<fieldset>
					<legend><span>Ejecuci&oacute;n</span></legend>
						<s:textfield id="descripcionAccion" name="descripcionAccion" cssStyle="font-size: 40px; width: 450px; " value="Mover entre almacenes" disabled="true" onkeyup="keyup(1);"/>
					<!-- <s:form name="formu" action="ComenzarFlujo">
					</s:form> -->
				</fieldset>
				<fieldset>
					<legend><span>Origen</span></legend>
						<s:textfield id="origen" name="origen" key="origen" cssStyle="font-size: 40px; width: 450px;" onchange="javascript:enviar();" onkeyup="javascript:correcionEnies();" value="%{#flujo.origen}" onkeyup="keyup(2);"/>
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
					<legend><span>Destino</span></legend>
						<s:textfield id="destino" name="destino" cssClass="inputs" key="destino" disabled="true" cssStyle="font-size: 40px; width: 450px;" onchange="javascript:enviar();" value="%{#flujo.destino}" onkeyup="keyup(5);"/>
					<!-- <s:form name="formu" action="ComenzarFlujo">
					</s:form> -->
				</fieldset>
				<fieldset>
					<legend><span>C&oacute;digo de escape</span></legend>
					<s:textfield id="codigoEscape" name="codigoEscape" cssClass="inputs" disabled="true" cssStyle="font-size: 40px; width: 450px;" onchange="enviar();" onkeyup="keyup(6);"/>
				</fieldset>
			</s:iterator>
		</s:form>
		<!-- <button class="i_arrow_left icon verdeOpilion" onclick="javascript:nuevoGPSele();">Volver</button> -->
	</div><!-- end #divNecesarioWidget -->
</s:i18n>