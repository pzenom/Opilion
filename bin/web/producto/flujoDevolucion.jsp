<%@ page contentType="text/html; charset=utf-8"
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
							<h3 class="ico_posts">Flujo mover</h3>
							<s:hidden id="msg" name="msg" key="msg" value="%{#session.msg}"/>
							<s:form name="formuFlujoMovimiento" action="RealizarFlujoDevolucion"><!-- -->
								<s:iterator id="flujo" value="%{#session.flujo}" status="status">
									<fieldset>
										<legend><span>Ejecuci&oacute;n</span></legend>
											<s:textfield id="descripcionAccion" name="descripcionAccion" cssStyle="font-size: 40px; width: 450px; " value="Devolucion" disabled="true"/>
										<!-- <s:form name="formu" action="ComenzarFlujo">
										</s:form> -->
									</fieldset>
									<fieldset>
										<legend><span>Lote</span></legend>
											<s:textfield id="lote" name="lote" key="lote" cssStyle="font-size: 40px; width: 450px;" onchange="javascript:enviar();" value="%{#flujo.lote}"/>
										<!-- <s:form name="formu" action="ComenzarFlujo">
										</s:form> -->
									</fieldset>
									<fieldset>
										<legend><span>Unidades</span></legend>
											<s:textfield id="cantidad" name="cantidad" cssClass="inputs" cssStyle="font-size: 40px; width: 450px;" onchange="enviar();" value="%{#flujo.cantidad}" disabled="true" />
										<!-- <s:form name="formu" action="ComenzarFlujo">
										</s:form> -->
									</fieldset>
									<fieldset>
										<legend><span>¿Reubicar?</span></legend>
											<s:textfield id="devolverStock" name="devolverStock" cssClass="inputs" key="devolverStock" disabled="true" cssStyle="font-size: 40px; width: 450px;" onchange="javascript:enviar();" value="%{#flujo.devolverStock}"/>
										<!-- <s:form name="formu" action="ComenzarFlujo">
										</s:form> -->
									</fieldset>
									<fieldset id="fieldsetDestino" style="display: none;">
										<legend><span>Destino</span></legend>
											<s:textfield id="destino" name="destino" cssClass="inputs" key="destino" disabled="true" cssStyle="font-size: 40px; width: 450px;" onchange="javascript:enviar();" value="%{#flujo.destino}"/>
										<!-- <s:form name="formu" action="ComenzarFlujo">
										</s:form> -->
									</fieldset>
									<fieldset>
										<legend><span>C&oacute;digo de escape</span></legend>
										<s:textfield id="codigoEscape" name="codigoEscape" cssClass="inputs" disabled="true" cssStyle="font-size: 40px; width: 450px;" onchange="enviar();"/>
									</fieldset>
								</s:iterator>
							</s:form>
						</div><!-- end #dashboard -->
					</div><!-- end #main_panel_container-->
				</div><!-- end #content_main -->
			</div><!-- end #content -->
			<jsp:include page="../includes/pie.jsp" flush="true" />
		</s:i18n>
	</div><!-- end container -->
	<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="producto/flujoDevolucion.js"></script>
</body>