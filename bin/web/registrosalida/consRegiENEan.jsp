<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../includes/head.jsp" flush="true" />
<body>
<div class="container" id="container">
	<s:i18n name="ApplicationResources">
	<div id="content" >
	<div id="content_main" class="clearfix">
			<div id="main_panel_container" class="left">
			<div id="dashboard">
				<h3 class="ico_posts"><s:text name="exitprod.h3"/></h3>
				<h4>La cantidad solicitada es: <s:property value="%{#session.qty21Cant}" /></h4>
					<!-- Listado MP disponible-->
					<div="tabla">
					<table class="tabla" border=0 cellpadding="2" cellspacing="2" width="50%" >
						<thead>
							<tr>
								<th>Orden</th>
								<th>Lote</th>
								<th>Fecha envasado</th>
								<th>Cantidad Disponible</th>							
							</tr>
						</thead>
						<tbody>
								<s:iterator id="gprod" value="%{#session.listaregienvasados}">
									<tr>
										<td><b><a href="EditCantEN.action?tipo=O&orden=<s:property value="orden" />&albaran=<s:property value="%{#session.albaran}" />"><s:property value="orden" /></a></b></td>									
									  <td><s:property value="loteAsignado" /></td>
										<td class="table_date"><s:property value="horaProceso" /></td>
										<td class="table_qty"><s:property value="cantidadProducto" /></td>
									</tr>
								</s:iterator>
						</tbody>
					</table>
					</div>

		</div><!-- end #dashboard -->
	
		</div><!-- end #main_panel_container-->
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	</s:i18n>
</div><!-- end container -->
</body>
</html>