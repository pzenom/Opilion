<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<!-- AQUI EL DIV DE AJAX -->
	<s:div name="ajax" id="ajaxDiv">
		<fieldset>
			<legend><span>Mapa Nave Barcia</span></legend>
			<s:form target="ajaxDiv" id="formuAjax" name="formuAjax" action="SeleccionLineaAjax" validate="true" method="get">
				<s:hidden id="zona" key="idZona" name="idZona"/>
				<s:hidden id="idLinea" key="idLinea" name="idLinea"/>
				<s:hidden id="idLineaZona" key="idLineaZona" name="idLineaZona"/>
				<s:hidden id="idAlmacen" key="idAlmacen" name="idAlmacen" value="%{#session.idAlmacen}"/>
				<s:hidden id="registro" key="registro" name="registro" value="%{#session.registro}"/>
				<s:hidden id="cuantosPalets" key="cuantosPalets" name="cuantosPalets" value="%{#session.numeroPalets}"/>
				<img src="img/planos/naveBarcia.png" width=847 height=368 border=1 alt="Un mapaa" usemap="#mapa1"/>
				<MAP NAME="mapa1">
					<area shape=POLY border="1" coords="270,10, 440,10,440,45, 270,45, 270,10" href="#" onclick="javascript:seleccionLinea(2,1,1,'Conservacion 1 arriba');" alt="Conservacion 1 arriba" title="Conservacion 1 arriba">
				</MAP>
				<s:submit targets="ajaxDiv" value="Seleccionar producto" cssStyle="visibility: hidden;"/>
			</s:form>
		</fieldset>
	</s:div>
</s:i18n>