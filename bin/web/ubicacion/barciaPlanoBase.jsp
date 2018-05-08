<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../includes/head.jsp" flush="true" />
<body>
<div class="container" id="container">
	<s:i18n name="ApplicationResources">
	<jsp:include page="../includes/header.jsp" flush="true" />
	<div id="content" >
		<div id="top_menu" class="clearfix">
			<jsp:include page="../includes/menu.jsp" flush="true" />
			<a href="pagEscritorio.jsp" id="visit" class="right">Home</a>
		</div><!-- end top menu -->
		<div id="content_main" class="clearfix">
			<div id="main_panel_container" class="left">
				<div id="dashboard">
					<div class="section">
						<p class="idPantalla">PLANO-BB</p>
						<h3 class="ico_ubi">Escoge una Ubicacion Para este producto</h3>
						<fieldset>
						<legend><span>Datos Generales</span></legend>
							<table width="100%" cellpadding="2" cellspacing="2" border="0">
								<tr>
									<td class="label"><s:label name="%{getText('entry.entrada')}" value="%{getText('entry.entrada')}"/></td>
									<td nowrap="nowrap"><s:property value="%{#session.codigoEntrada}"/>
										<s:textfield key="codigoOrden" cssStyle="width:180px;" value="%{#session.codigoentrada}" disabled="true" cssStyle="text-align:right;"/>
										<s:textfield key="codigoEntrada" value="%{#session.codigoEntrada}"cssStyle="width:180px;" label="%{getText('entry.entrada')}" disabled="true" cssStyle="text-align:right;"/>
									</td>
									<td class="label"><s:label name="%{getText('registro.label.responsable')}" value="%{getText('registro.label.responsable')}"/></td>
									<td>
										<s:textfield key="idOperario" cssStyle="width:180px;" label="%{getText('registro.label.responsable')}" value="%{#session.usuario.login}" disabled="true"/>
									</td>
								</tr>
							</table>
						</fieldset>
						<fieldset>
							<legend><span>Mapa Nave Barcia</span></legend>
							<img src="img/mapaNave.png" width=847 height=368 border=1 alt="Un mapaa" usemap="#mapa1"> 
							<MAP NAME="mapa1">
								<area shape=POLY border="1" coords="270,10, 440,10 ,440,45, 270,45, 270,10" href="InseRUE.action?zona=1&codigoEntrada=<s:property value="codigoEntrada" />" onclick="return alert('Ubicado en: Conservacion 1 arriba');return window.close()" alt="Conservacion 1 arriba" title="Conservacion 1 arriba">
								<area shape=POLY border="1" coords="270,80, 440,80, 440,115, 270,115, 270,80" href="InseRUE.action?zona=1&codigoEntrada=<s:property value="codigoEntrada" />" onclick="return alert('Ubicado en: Conservacion 1 abajo');return window.close()" alt="Conservacion 1 abajo" title="Conservacion 1 abajo">
								<area shape=POLY border="1" coords="450,10, 620,10, 620,45, 450,45, 450,10" href="InseRUE.action?zona=2&codigoEntrada=<s:property value="codigoEntrada" />" onclick="return alert('Ubicado en: Conservacion 2 arriba');return window.close()" alt="Conservacion 2 arriba" title="Conservacion 2 arriba">
								<area shape=POLY border="1" coords="450,80, 620,80, 620,115, 450,115, 450,80"		href="InseRUE.action?zona=2&codigoEntrada=<s:property value="codigoEntrada" />" onclick="return alert('Ubicado en: Conservacion 2 abajo');return window.close()" alt="Conservacion 2 abajo" title="Conservacion 2 abajo">
								<area shape=POLY border="1" coords="160,10, 255,10, 255,155, 160,155, 160,10" href="InseRUE.action?zona=9&codigoEntrada=<s:property value="codigoEntrada" />" onclick="return alert('Ubicado en: Entrada');return window.close();" alt="Entrada" title="Entrada">
								<area shape=POLY border="1" coords="265,120, 365,120, 365,155, 265,155, 265,120" 	href="InseRUE.action?zona=3&codigoEntrada=<s:property value="codigoEntrada" />" onclick="return alert('Ubicado en: Cartonaje');return window.close()" alt="Cartonaje" title="Cartonaje">
								<area shape=POLY border="1" coords="535,120, 620,120, 620,185, 535,185, 535,120" 	href="InseRUE.action?zona=7&codigoEntrada=<s:property value="codigoEntrada" />" onclick="return alert('Ubicado en: Congelador 1');return window.close()" alt="Congelador 1" title="Congelador 1">
								<area shape=POLY border="1" coords="200,235, 365,235, 365,270, 200,270, 200,235" 	href="InseRUE.action?zona=4&codigoEntrada=<s:property value="codigoEntrada" />" onclick="return alert('Ubicado en: Congelador 1');return window.close()" alt="Almacen 1" title="Almacen 1">
								<area shape=POLY border="1" coords="200,325, 365,325, 365,360, 200,360, 200,325" href="InseRUE.action?zona=5&codigoEntrada=<s:property value="codigoEntrada" />" onclick="return alert('Ubicado en: Congelador 1');return window.close()" alt="Almacen 2" title="Almacen 2">
								<area shape=POLY border="1" coords="380,120, 525,120, 525,190, 620,190, 620,360, 380,360, 380,120 " href="InseRUE.action?zona=6&codigoEntrada=<s:property value="codigoEntrada" />" onclick="return alert('Ubicado en: Congelador 1');return window.close()" alt="Envasado" title="Envasado">
								<area shape=POLY border="1" coords="630,10, 840,10, 840,360, 630,360, 630,10 " href="InseRUE.action?zona=8&codigoEntrada=<s:property value="codigoEntrada" />" onclick="return alert('Ubicado en: Congelador 1');return window.close()" alt="Fuera" title="Fuera">
							</MAP>
						</fieldset>
					</div><!-- end #section -->	
				</div><!-- end #dashboard -->
			</div><!-- end #main_panel_container-->
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	<jsp:include page="../includes/pie.jsp" flush="true" />
	</s:i18n>
</div><!-- end container -->
</body>
</html>