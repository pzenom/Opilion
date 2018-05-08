<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<div class="g12 ui-sortable">
		<div id="widget_consMant" class="widget">
		</div> <!-- end widget_consMant -->
	</div> <!-- end g12 widgets ui-sortable -->
	<div class="g12 ui-sortable">
		<div id="widget_menuMant" class="widget" data-collapsed="true">
			<h3 class="handle">Men&uacute; mantenimientos</h3>
			<div>
				<button class="i_address_book icon blue" onclick="javascript:listaMantenimientos();"><s:text name="menulateral.listar"/></button>
			</div>
		</div>
	</div>
</s:i18n>