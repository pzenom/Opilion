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
						<p class="idPantalla">PLANO-BBx</p>
						<h3 class="ico_ubi">Escoge una Ubicacion Para este producto</h3>
						<!-- AQUI EL DIV DE AJAX -->
						<s:div id="ajax" name="ajax" id="ajaxDiv">
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
									<map name="mapa1">
										<area shape=POLY border="1" coords="270,10, 440,10,440,45, 270,45, 270,10" href="#" onclick="javascript:seleccionLinea(1,1,1,'Conservacion 1 arriba');" alt="Conservacion 1 arriba" title="Conservacion 1 arriba">
										<!--
										
										<area shape=POLY border="1" coords="270,10, 440,10,440,45, 270,45, 270,10" href="#" onclick="javascript:seleccionLinea(3,1,3,'Conservacion 2 arriba','<s:property value='%{#session.codigoEntrada}'/>');" alt="Conservacion 2 arriba" title="Conservacion 2 arriba">
										
										-->
									</map>
									<s:submit targets="ajaxDiv" value="Seleccionar producto" cssStyle="visibility: hidden;"/>
								</s:form>
							</fieldset>
						</s:div>
						<a id="reubicar" class="botonInserta" href="javascript:document.location.reload()">
							<img title="Reubicar" alt="Reubicar" src="img/j_button_back.png">
							Reubicar
						</a>
						<a id="confirmarUbicacion" class="botonInserta" href="#" onclick="confirmar()">
							<img title="Confirmar ubicacion" alt="Confirmar ubicacion" src="img/j_button1_add.png">
							Confirmar ubicaci&oacute;n
						</a>
					</div><!-- end #section -->	
					
				</div><!-- end #dashboard -->
			</div><!-- end #main_panel_container-->
			<jsp:include page="../includes/menuubicacion.jsp" flush="true" />
			<s:form id="selecciones" name="selecciones" action="SalvaDatosUbicacion" method="get">
				<s:hidden id="seleccionado" key="seleccionado"/>
				<s:hidden id="idPalet" name="idPalet" key="idPalet" value="%{#session.idPalet}"/>
				<s:hidden cssClass="idHueco"id="idHueco" name="idHueco" key="idHueco"/>
				<s:hidden cssClass="registro" id="registro" name="registro" key="registro"/>
				<s:hidden cssClass="nombreHueco" id="nombreHueco" name="nombreHueco" key="nombreHueco"/>
			</s:form>
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	<jsp:include page="../includes/pie.jsp" flush="true" />
	</s:i18n>
</div><!-- end container -->
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="ubicacion/sacarBarciaPlanoBase_.js"></script>
</body>
</html>