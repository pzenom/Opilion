<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">C&oacute;digo leido</h3>
	<div id="divNecesarioWidget">								
		<s:form name="formu" action="ComenzarFlujo">
			<s:textfield id="codigo" name="codigo" cssStyle="font-size: 40px;"/>
		</s:form>
		<button class="i_arrow_left icon verdeOpilion" onclick="pagEscritorio.jsp">Volver</button>
	</div><!-- end #divNecesarioWidget -->
</s:i18n>