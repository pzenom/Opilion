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
				 <!-- Parámetros de consulta-->
				 <s:form action="ConsRegiEN" validate="true">
		      <fieldset>
				       <table width="100%" cellpadding="2" cellspacing="2" border="0">
								<tr>
								<td class="label"><s:label name="%{getText('registro.label.grupoproducto')}" value="%{getText('registro.label.grupoproducto')}" cssStyle="text-align:right;"/></td>
								<td nowrap><s:select key="idGrupo"	list="%{#session.listagrupos}" 	listKey="idGrupo" listValue="nombre" headerKey="0" headerValue="Seleccione un Grupo"/></td>
								<td class="label"><s:label name="%{getText('centry.producto')}" value="%{getText('centry.producto')}" cssStyle="text-align:right;"/></td>
								<td nowrap><s:select key="idProducto" list="%{#session.listaproductos}" listKey="idProducto" listValue="nombre" headerKey="0" headerValue="Seleccione un Producto"/></td>
								<td nowrap><s:submit value="Consultar"/></td>
								</tr>
							  </table>
					</fieldset><!--end Parámetros de consulta-->
				 </s:form>	
					<!-- Listado MP disponible-->
					<div="tabla">
					<table class="tabla" border=0 cellpadding="2" cellspacing="2" width="100%" >
						<thead>
							<tr>
								<th>Orden</th>
								<th>Lote</th>
								<th>Hora</th>
								<th>Cantidad</th>
							</tr>
						</thead>
						<tbody>
								<s:iterator id="gprod" value="%{#session.listaregienvasados}">
									<tr>
										<td><b><a href="#" onClick="javascript:parent.get_ventana_emergente('regProv','EditCantEN.action?orden=<s:property value="orden" />&albaran=<s:property value="%{#session.albaran}" />','no','no','450','350','','');javascript:window.close();"><s:property value="orden" /></a></b></td>
									  <td><s:property value="loteAsignado" /></td>
										<td><s:property value="horaProceso" /></td>
										<td><s:property value="cantidadProducto" /></td>
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