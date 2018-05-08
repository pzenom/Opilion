<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<div class="g12 ui-sortable">
		<div id="widget_consMaqui" class="widget">
		</div> <!-- end widget_consMaqui -->
	</div> <!-- end g12 widgets ui-sortable -->
	<div class="g12 ui-sortable">
		<div id="widget_menuMaquinas" class="widget" data-collapsed="true">
			<h3 class="handle">Men&uacute; m&aacute;quinas</h3>
			<div>
				<button class="i_address_book icon blue" onclick="javascript:listaMaquinas();"><s:text name="menulateral.listar"/></button>
			</div>
		</div>
	</div>
</s:i18n>