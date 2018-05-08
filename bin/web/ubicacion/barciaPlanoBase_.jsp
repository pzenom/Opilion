<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Selecci&oacute;n de ubicacion<span class="screenCode">PLANO_BARCIA</span></h3>
	<div id="divNecesarioWidget">
		<fieldset style="display:none;">
			<legend><span>Datos del registro a almacenar</span></legend>
			<table width="100%" cellpadding="2" cellspacing="2" border="0">
				<tr>
					<td class="label"><s:label name="%{getText('entry.entrada')}" value="%{getText('entry.entrada')}"/></td>
					<td nowrap="nowrap">
						<s:textfield key="codigoEntrada" value="%{#session.codigoEntrada}"cssStyle="width:180px;" label="%{getText('entry.entrada')}" disabled="true" cssStyle="text-align:right;"/>
					</td>
					<td class="label"><s:label name="%{getText('registro.label.responsable')}" value="%{getText('registro.label.responsable')}"/></td>
					<td>
						<s:textfield key="idOperario" cssStyle="width:180px;" label="%{getText('registro.label.responsable')}" value="%{#session.usuario.login}" disabled="true"/>
					</td>
				</tr>
			</table>
		</fieldset>
		<!-- AQUI EL DIV DE AJAX -->
		<s:div id="ajax" name="ajax" id="ajaxDiv">
			<fieldset>
				<img src="img/planos/naveBarcia.png" width=847 height=368 border=1 alt="Un mapaa" usemap="#mapa1"/>
				<map name="mapa1">
				<!-- seleccionLinea(zona, linea, lineaAbsoluta, nombreLinea) -->
					<area shape=POLY border="1" coords="270,10, 440,10,440,45, 270,45, 270,10" href="#" onclick="javascript:seleccionLinea(1,1,1,'Conservacion 1 arriba');" alt="Conservacion 1 arriba" title="Conservacion 1 arriba"/>
					<area shape=RECT border="1" coords="270,81, 440,111" href="#" onclick="javascript:seleccionLinea(1,2,2,'Conservacion 1 abajo');" alt="Conservacion 1 abajo" title="Conservacion 1 abajo"/>
					<area shape=RECT border="1" coords="453,9, 620,40" href="#" onclick="javascript:seleccionLinea(2,1,3,'Conservacion 2 arriba');" alt="Conservacion 2 arriba" title="Conservacion 2 arriba"/>
					<area shape=RECT border="1" coords="453,79, 619,111" href="#" onclick="javascript:seleccionLinea(2,2,4,'Conservacion 2 abajo');" alt="Conservacion 2 abajo" title="Conservacion 2 abajo"/>
					<area shape=RECT border="1" coords="263,122, 368,152" href="#" onclick="javascript:seleccionLinea(3,1,5,'Cartonaje');" alt="Cartonaje" title="Cartonaje"/>
					<area shape=RECT border="1" coords="199,237, 367,264" href="#" onclick="javascript:seleccionLinea(4,1,6,'Almacen 1');" alt="Almacen 1" title="Almacen 1"/>
					<area shape=RECT border="1" coords="198,327, 367,356" href="#" onclick="javascript:seleccionLinea(5,2,7,'Almacen 2');" alt="Almacen 2" title="Almacen 2"/>
					<area shape=POLY border="1" coords="382,124, 518,124, 518,193, 622,194, 622,358 , 382,359, 381,124" href="#" onclick="javascript:seleccionLinea(6,1,11,'Zona de envasado');" alt="Zona de envasado" title="Zona de envasado"/>
				</map>
			</fieldset>
		</s:div>
	</div>
</s:i18n>