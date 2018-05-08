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
			<div id="tabledata" class="section">
				<h3 class="ico_posts"><s:text name="crenvase.bienvenida" /></h3>
				<s:text name="insenv.envasesuno" /><br/>
				<s:text name="insenv.envasesdos" /><br/>
				<s:text name="insenv.envasestres" /><br/>
				La cantidad total a envasar es: <s:property value="%{#session.qtytotal}"/>
				 <s:form action="InseCantREEN" validate="true">	
				 
				 <table class="tabla" border="1">
						<thead>
							<tr>
							  <th><b>Seleccione</b></th>
							  <th><b>Código Entrada</b></th>
								<th><b>Fecha</b></th>
								<th><b>Envase</b></th>
								<th><b>Cantidad Disponible</b></th>
								<th><b>Cantidad</b></th>
								<th><b>Saldo</b></th>								
						  </tr>
						</thead>
						<tbody>
						<!-- LISTA RE DE ENVASES ASOCIADOS A PP -->
						<s:iterator id="ingred" value="%{#session.listaregistroenvases}">
									<tr>
									  <td><input type="checkbox" name="listaEnvases" value=<s:property value="orden" />/></td>
									  <td><s:property value="orden" /></td>
									  <td><s:property value="fecha" /></td>
										<td><s:property value="descProducto" /></td>
										<td><s:property value="cantidadProducto" /></td>
										<td>
											<s:textfield key="cantidad" size="10" onchange="doCalcAndSubmit2(this.value)"/>
										</td>	
										<td>
											<s:textfield name="resta2" key="resta2" size="10" value="" disabled="true"/>
										</td>																		
									</tr>
									
									<script>
										var cantoriginal2 = new Array();
										cantoriginal2.push('<s:property value="cantidadProducto" />');
									</script>									
									<script type="text/javascript">
									function doCalcAndSubmit2(cadena2) {
									// obtener valores original e insertado
									val1 = cantoriginal2;
									val2 = cadena2;

									valresta = parseFloat(val1) - parseFloat(val2);

									if(parseFloat(val1)<parseFloat(val2))
										alert("Cantidad sobrepasa el saldo actual");
									//alert(document.forms[0].resta2.value);
									// asigna el valor al campo restado
									document.forms[0].resta2.value = valresta;
									}
									</script>									

									
								</s:iterator>
						</tbody>
					</table>
  	        <table width="100%" cellpadding="2" cellspacing="2" border="0">
						<tr>
							<tr>
							  <td class="label"><s:label name="%{getText('rcri.estadoProceso')}" value="%{getText('rcri.estadoProceso')}" cssStyle="text-align:right;"/></td>
								<td nowrap="nowrap">
									<s:select key="estadoProceso" cssStyle="width:180px;" label="%{getText('rcri.estadoProceso')}" list="#{'P':'Posponer','F':'Finalizar'}" headerKey="0" cssStyle="text-align:left;"/>								
								</td>		
								<td>
									<s:submit value="Insertar"/>								
								</td>												
							</tr>							
						</tr>
					  </table>
					</s:form>		
			</div> <!-- end #tabledata -->
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