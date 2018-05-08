<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<div class="g12 ui-sortable">
		<div id="widget_consUbica" class="widget">
		</div> <!-- end widget_consProv -->
	</div> <!-- end g12 widgets ui-sortable -->
	<div class="g12 ui-sortable">
		<div id="widget_menuProv" class="widget" data-collapsed="true">
			<h3 class="handle">Men&uacute; ubicaciones</h3>
			<div>
				<button class="i_plus icon yellow" onclick="javascript:nuevoProdu();"><s:text name="menulateral.nuevo"/></button>
				<button class="i_address_book icon blue" onclick="javascript:consProdu();"><s:text name="menulateral.listar"/></button>
			</div>
		</div>
	</div>
</s:i18n>