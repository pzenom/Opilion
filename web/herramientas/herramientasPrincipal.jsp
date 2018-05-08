<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<div class="g12 ui-sortable">
		<div id="widget_consHerra" class="widget">
		</div> <!-- end widget_consHerra -->
	</div> <!-- end g12 widgets ui-sortable -->
	<div class="g12 ui-sortable">
		<div id="widget_menuHerra" class="widget" data-collapsed="true">
			<h3 class="handle">Men&uacute; herramientas</h3>
			<div>
				<button class="i_plus icon yellow" onclick="javascript:configurarDB();">Configurar BD</button>
			</div>
		</div>
	</div>
</s:i18n>