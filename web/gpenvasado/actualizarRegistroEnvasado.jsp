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
						<h2 class="ico_mug"><s:text name="uprenv.bienvenida" /></h2>
						<s:iterator id="entrada" value="%{#session.listaregistrosupd}">
							<s:form action="ActualizarRegistroEnvasado" validate="true">
								<s:select key="horaProceso" label="%{getText('uprenv.henv')}" list="%{#session.listahoras}" listKey="horaProceso" listValue="horaProceso" headerKey="0" headerValue="Seleccione una hora"/>
								
								<s:textfield key="fechaCaducidad" label="%{getText('uprenv.fechacad')}" />
								
								<s:textfield key="loteAsignado" label="%{getText('uprenv.lote')}" />
			
								<s:textfield key="temperatura" label="%{getText('uprenv.temperatura')}" />
								
								<s:select key="horaInicioProceso" label="%{getText('uprenv.hienv')}" list="%{#session.listahoras}" listKey="horaProceso" listValue="horaProceso" headerKey="0" headerValue="Seleccione una hora"/>
								
								<s:select key="horaFinProceso" label="%{getText('uprenv.hfenv')}" list="%{#session.listahoras}" listKey="horaProceso" listValue="horaProceso" headerKey="0" headerValue="Seleccione una hora"/>
								
								<s:select key="idProducto" label="%{getText('uprenv.producto')}" list="%{#session.listaproductos}" listKey="idProducto" listValue="idProducto+' '+nombre" headerKey="0" headerValue="Seleccione un Producto"/>
								
								<s:select key="idIngredientes" label="%{getText('uprenv.ingrediente')}" list="%{#session.listaproductos}" listKey="idProducto" listValue="idProducto+' '+nombre" headerKey="0" headerValue="Seleccione un Ingrediente"/>
								
								<s:textfield key="cantidadIngredientesIni" label="%{getText('uprenv.icantidadini')}" />
								
								<s:textfield key="cantidadIngredientesFin" label="%{getText('uprenv.icantidadfin')}" />
								
								<s:textfield key="loteIngredientes" label="%{getText('uprenv.loteingredientes')}" />
								
								<s:select key="idEnvase" label="%{getText('uprenv.envases')}" list="%{#session.listaenvases}" listKey="idEnvase" listValue="idEnvase+' '+modelo+' '+tamano" headerKey="0" headerValue="Seleccione un Envase"/>
								
								<s:textfield key="cantidadEnvasesIni" label="%{getText('uprenv.ecantidadini')}" />
								
								<s:textfield key="cantidadEnvasesFin" label="%{getText('uprenv.ecantidadfin')}" />
								
								<s:textfield key="loteEnvases" label="%{getText('uprenv.loteenvases')}" />
								
								<s:textarea key="notasIncidencias" label="%{getText('uprenv.notasincidencia')}" rows="3" cols="40" />
								
								<s:textarea key="notasInstrucciones" label="%{getText('uprenv.notasinstrucciones')}" rows="3" cols="40" />
								
								<s:textarea key="observaciones" label="%{getText('uprenv.notasobservaciones')}" rows="3" cols="40" />
								
								<s:submit value="Actualizar"/> 	
						</s:form>	
					</s:iterator>
			</div><!-- end #section -->	
			</div><!-- end #dashboard -->
	</div><!-- end #main_panel_container-->
			<jsp:include page="../includes/menuenvasado.jsp" flush="true" />
	</div><!-- end #content_main -->
	</div><!-- end #content -->
	<div id="footer" class="clearfix">
		<p>
			<a href=""><img src="img/connect.png" alt="statistics"/>&nbsp;Conexion con el servidor</a>&nbsp;
			<a href=""><img src="img/note.png" alt="statistics"/>&nbsp;7</a>&nbsp;
			<a href=""><img src="img/clock.png" alt="statistics"/>&nbsp;17:32</a>
		</p>
	</div><!-- end #footer -->
	</s:i18n>	
</div><!-- end container -->
</body>
</html>