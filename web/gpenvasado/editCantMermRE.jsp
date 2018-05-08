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
		<h3 class="ico_mug"><s:text name="cdmenvase.bienvenida" /></h3>
		<s:text name="insenv.cantidadesuno" /><br/>
		<s:form action="InseCantMermRE" validate="true" >
		<table id="table" border="1">
						<thead>
							<tr>
							  <th><b>Seleccione</b></th>
							  <th><b>Codigo Entrada</b></th>							  
								<th><b>Descripcion</b></th>
								<th><b>Cantidad Disponible (Unidades)</b></th>
								<th><b>Indicar Merma</b></th>
						  </tr>
						</thead>
						<tbody>
						<s:iterator id="ingre" value="%{#session.datosenv}">
									<tr>
									  <td><input type="checkbox" checked="true" name="listaEnvases" value=<s:property value="proceso" />/></td>
									  <td><s:property value="proceso" /></td>
									  <td><s:property value="descProducto" /></td>
										<td><s:property value="cantidadProducto" /></td>
										<td>
											<s:textfield key="cantidad" size="10"/>
										</td>
									</tr>
						</s:iterator>
						</tbody>
					</table>
					<s:submit value="Insertar"/> 	
			</s:form>	
	</div><!-- end #section -->	
	</div><!-- end #dashboard -->
	</div><!-- end #main_panel_container-->
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