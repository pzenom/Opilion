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
			<a href="pagEscritorio.jsp" id="visit" class="right"><s:text name="general.home" /></a>
		</div><!-- end top menu -->
	<div id="content_main" class="clearfix">
	<div id="main_panel_container" class="left">
	<div id="dashboard">
	<div class="section">
		<h2 class="ico_mug"><s:text name="renv.bienvenida" /></h2>
		  <s:form action="IngresarRegistroEnvasado" validate="true">
					<fieldset><!-- Informacion Basica-->
					<legend><span><s:text name="renv.infoBasica" /></span></legend>
		       <table width="100%" cellpadding="2" cellspacing="2" border="0">
						<tr>
							<tr rowspan="2">
								<td class="label"><s:label name="%{getText('renv.orden')}" value="%{getText('renv.orden')}" cssStyle="text-align:right;"/></td>
								<td nowrap><s:textfield key="orden" disabled="true"/></td>
							</tr>				
							<tr>
							  <td class="label"><s:label name="%{getText('renv.fecha')}" value="%{getText('renv.fecha')}" cssStyle="text-align:right;"/></td>
								<td><s:textfield key="fecha" value="%{#session.fecharegistro}" disabled="true"/></td>
								<td class="label"><s:label name="%{getText('renv.fechacad')}" value="%{getText('renv.fechacad')}" cssStyle="text-align:right;"/></td>
								<td><s:textfield key="fechaCaducidad" value="%{#session.fechacaducidad}" /></td>
							</tr>			
							<tr>
							  <td class="label"><s:label name="%{getText('renv.mermaingredientes')}" value="%{getText('renv.mermaingredientes')}" cssStyle="text-align:right;"/></td>
								<td><s:textfield key="mermaIngredientes" value="0" /></td>
								<td class="label"><s:label name="%{getText('renv.mermaenvases')}" value="%{getText('renv.mermaenvases')}" cssStyle="text-align:right;"/></td>
								<td><s:textfield key="mermaEnvases" value="0" /></td>												
							</tr>				
						</tr>
					  </table>
				  </fieldset><!--end product info-->
							
					<table id="table" border="1">
						<thead>
							<tr>
							  <th><b><s:text name="renv.materia" /></b></th>
								<th><b><s:text name="renv.cantidadMP" /></b></th>
								<th><b><s:text name="renv.usarMP" /></b></th>
								<th><b><s:text name="renv.mermaMP" /></b></th>
								<th><b><s:text name="renv.envase" /></b></th>
								<th><b><s:text name="renv.cantidadEN" /></b></th>
								<th><b><s:text name="renv.usarEN" /></b></th>
								<th><b><s:text name="renv.mermaEN" /></b></th>
						  </tr>
						</thead>
						<tbody>
						<s:iterator id="ingred" value="%{#session.selingredientesenvases}">
									<tr>
									  <td><s:property value="descProducto" /></td>
										<td><s:property value="cantidadProducto" /></td>
									  <td><s:property value="cantidadIngredientesFin" /></td>
									  <td><s:property value="mermaIngredientes" /></td>
										<td><s:property value="descEnvase" /></td>
										<td><s:property value="cantidadUnidadesEnvases" /></td>
										<td><s:property value="cantidadEnvasesFin" /></td>
										<td><s:property value="mermaEnvases" /></td>
									</tr>
								</s:iterator>
						</tbody>
					</table>
					<s:submit value="Registrar"/> 	
			</s:form>	
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