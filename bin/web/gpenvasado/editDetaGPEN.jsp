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
		<h3 class="ico_mug"><s:text name="confrenv.bienvenida" /></h3>
		  <s:form action="InseDetaGPEN" validate="true">
					<fieldset><!-- Información Básica-->
					<legend><span>Informacion Basica</span></legend>
		       <table width="100%" cellpadding="2" cellspacing="2" border="0">
						<tr>
							<tr rowspan="2">
								<td class="label"><s:label name="%{getText('rcri.orden')}" value="%{getText('rcri.orden')}" cssStyle="text-align:right;"/></td>
								<td nowrap><s:textfield key="orden" disabled="true"/></td>
							  <td class="label"><s:label name="%{getText('renv.fecha')}" value="%{getText('renv.fecha')}" cssStyle="text-align:right;"/></td>
								<td><s:textfield key="fecha" value="%{#session.fecharegistro}" disabled="true"/></td>
							  <td class="label"><s:label name="%{getText('rcri.hienv')}" value="%{getText('rcri.hienv')}" cssStyle="text-align:right;"/></td>
								<td><s:textfield key="horaInicioProceso" value="%{#session.horaInicioProceso}" disabled="true"/></td>
							</tr>	
							<tr>
							  <td class="label"><s:label name="%{getText('rcri.estadoProceso')}" value="%{getText('rcri.estadoProceso')}" cssStyle="text-align:right;"/></td>
								<td nowrap="nowrap">
									<s:select key="estadoProceso" cssStyle="width:180px;" label="%{getText('rcri.estadoProceso')}" list="#{'F':'Finalizar','P':'Postponer'}" headerKey="0" headerValue="Seleccione Estado" cssStyle="text-align:left;"/>								
								</td>						
							</tr>							
						</tr>
					  </table>
				  </fieldset><!--end product info-->
							
					<table id="table" border="1">
						<thead>
							<tr>
							  <th><b>Código de Entrada</b></th>							  
								<th><b>Descripción</b></th>
								<th><b>Cantidad a Usar (Kg.)</b></th>
								<th><b>Cantidad Mermas (Kg.)</b></th>
								<th><b>Modificar</b></th>								
						  </tr>
						</thead>
						<tbody>
						<s:iterator id="ingred" value="%{#session.subentradasenvasado}">
									<tr>
									  <td><s:property value="proceso" /></td>
									  <td><s:property value="descProducto" /></td>
									  <td><s:property value="cantidadProducto" /></td>
									  <td><s:property value="mermaIngredientes" /></td>
									  <td>
											<a href="CargarSubRegistro.action?codigoEntrada=<s:property value="codigoEntrada" />"><img src="img/note_edit.png" alt="Ver Registro" title="Ver Registro"/></a>
										</td>
									</tr>
								</s:iterator>
						</tbody>
					</table>
					<s:submit value="Guardar"/> 	
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