<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="UTF-8" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../includes/head.jsp" flush="true" />
<body>
	<div class="container" id="container">
		<s:i18n name="ApplicationResources">
		<jsp:include page="../includes/header.jsp" flush="true" />
		<div id="content" >
			<div id="top_menu" class="clearfix">
				<jsp:include page="../includes/menu.jsp" flush="true" />
				<a href="pagEscritorio.jsp" id="visit" class="right"><s:text name="general.home"/></a>
			</div><!-- end top menu -->
			<div id="content_main" class="clearfix">
				<div id="main_panel_container" class="left">
					<div id="dashboard">
						<div id="tabledata" class="section">
							<p class="idPantalla"><s:text name="faltaMaterias.idPantalla" /></p>
							<h3 class="ico_posts"><s:text name="faltaMaterias.titulo" /></h3>
							<p>&nbsp;</p>
							<h4><s:text name="faltaMaterias.texto1" /></h4>
							<p>&nbsp;</p>
							<s:form id="formulario" name="formulario" action="ProgramarEnvasado" validate="true">
								<div id="demo">
									<table id="tablaMaterias" cellpadding="0" cellspacing="0" border="0" class="display" >
										<thead>
											<tr>
												<th><s:text name="faltaMaterias.idMateria" /></th>
												<th><s:text name="faltaMaterias.nombre" /></th>
												<th><s:text name="faltaMaterias.cantidadPedir" /></th>
											</tr>
										</thead>
										<tbody>
											<s:iterator id="faltanMaterias" value="%{#session.listamaterias}">
												<tr>
													<td><p style="background: transparent;"><s:property value="idMateriaCategoria" /></td>
													<td><s:property value="nombre" /> - <s:property value="nombreCategoria" /></td>
													<td><p style="background: transparent;"><s:property value="cantidad" /> kilos</td>
													
												</tr>
											</s:iterator>
										</tbody>
									</table>
									<p>&nbsp;</p>
									<h4><s:text name="faltaMaterias.texto2" /></h4>
									<p>&nbsp;</p>
									<table id="tablaEnvases" cellpadding="0" cellspacing="0" border="0" class="display" >
										<thead>
											<tr>
												<th><s:text name="faltaMaterias.idMateria" /></th>
												<th><s:text name="faltaMaterias.nombre" /></th>
												<th><s:text name="faltaMaterias.cantidadPedir" /></th>
											</tr>
										</thead>
										<tbody>
											<s:iterator id="faltanEnvases" value="%{#session.listaenvases}">
												<tr>
													<td><p style="background: transparent;"><s:property value="idEnvase" /></td>
													<td><s:property value="nombre" /></td>
													<td><p style="background: transparent;"><s:property value="cantidad" /> unidades</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div><!-- end demo -->
							</s:form>
							<p>&nbsp;</p>
						</div><!-- end #tabledata -->
					</div> <!-- end #dashboard -->
				</div><!-- end #main_panel_container-->
				<jsp:include page="../includes/menupedidos.jsp" flush="true"/>
				<!--<div id="botones" class="right">
					<div id="boton_etiqueta_envasado" class="bt_envasar" style="height: 57px; width: 136px; padding: 5px; margin-right: 35px;">
						<button id="programarEnvasado" class="programarEnvasado" onClick="javascript:programarEnvasado('<s:property value="%{#session.pedido}" />')" type="button" name="programarEnvasado">
							<h2><s:text name="faltaStock.botonProgramarEnvasado" /></h2>
						</button>
					</div>
				</div>
				-->
			</div><!-- end #content_main -->
		</div><!-- end #content -->
		<jsp:include page="../includes/pie.jsp" flush="true" />
		</s:i18n>
	</div><!-- end container -->
	<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="registrosalida/muestraFalta.js"></script>
</body>
</html>