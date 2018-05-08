<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Backup</h3>
	<div id="divNecesarioWidget">
		<fieldset>
			<legend><span>Error realizando backup</span></legend>
			<p style="font-size: 14px;"><s:property value="%{#session.mensajeError}" /></p>
		</fieldset>
		<button class="i_arrow_left icon verdeOpilion" onclick="javascript:volverAlEscritorio();">Volver</button>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>