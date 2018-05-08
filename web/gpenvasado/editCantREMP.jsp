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
						<p class="idPantalla">EDITCANTREMP</p>
						<h3 class="ico_posts"><s:text name="cdcenvase.bienvenida" /></h3>
						<s:text name="insenv.envasesdos" /><br/>

						La cantidad total a envasar es: <span id="maximo"><s:property value="%{#session.qtytotal}"/></span>
						<s:form name="formulario" id="formulario" action="InseCantREMP" validate="true" >
						
						<table class="tabla" border="1">
										<thead>
											<tr>
												<th>Seleccione</th>
												<th>Codigo Entrada</th>
												<th>Cantidad Disponible Kg.</th>
												<th>Cantidad</th>
												<th>Remanente</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator id="ingre" value="%{#session.listadomp}">
											<tr>
												<td><input type="checkbox" checked="true" name="listaMateriaPrima" value=<s:property value="proceso" />/></td>
												<td><s:property value="proceso" /></td>
												<td class="cantidadexistente"><s:property value="cantidadProducto" /></td>
												<td class="cantidadasignada">
													<!--s:textfield name="cantidad" key="cantidad" size="10" onchange="doCalcAndSubmit(this.value,this.parentNode.parentNode.rowIndex);doEvalCant(this.value)"/-->
													<s:textfield name="cantidad" key="cantidad" size="10" onchange="doCalcAndSubmit(this.value,this.parentNode.parentNode.rowIndex);"/>													
												</td>
												<td class="remantente">
													<s:textfield name="resta" key="resta" size="10" value="" disabled="true"/>
												</td>
											</tr>											
											</s:iterator>
										</tbody>
									</table>
									<p>
										<a class="botonInserta" id="actualiza" href="javascript:inserta()" title="Envasar estas unidades">
											<img src="img/j_button_pill.png" alt="Unidades a Envasar " title="Unidades a envasar"/>
											Envasar
										</a>&nbsp;
									</p>
									<!--s:submit value="Insertar"/--> 	
							</s:form>	
					</div><!-- end #section -->	
				</div><!-- end #dashboard -->
			</div><!-- end #main_panel_container-->
			<jsp:include page="../includes/menuenvasado.jsp" flush="true" />
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	<jsp:include page="../includes/pie.jsp" flush="true" />
	</s:i18n>	
</div><!-- end container -->
<script type="text/javascript" src="gpenvasado/editCantREMP.js"></script>
</body>
</html>