<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../includes/head.jsp" flush="true" />
<body>
<div class="container" id="container">
	<s:i18n name="ApplicationResources">
	<div id="content">
	<div id="content_main" class="clearfix">
			<div id="main_panel_container" class="left">
			<div id="dashboard">
			<div id="tabledata" class="section">
				<h3 class="ico_mug">Eliminar Registro Salida: <s:property value="codigoSalida" /></h3>
				 <!-- Parámetros de consulta-->
				 <s:form action="EliminarRegistroSalida" validate="true" onsubmit="javascript:window.opener.location.reload();javascript:window.close();">
				<!-- Detalle de c/registro a eliminar -->
					<fieldset>
							<s:iterator id="salida" value="%{#session.listarsdel}">
								<table width="100%" cellpadding="2" cellspacing="2" border="0">	
									<tr>	
										<td class="label"><s:label name="Orden" value="Orden" cssStyle="text-align:right;"/></td>
										<td>
											<s:textfield key="orden" label="orden" disabled="true"/>
										</td>
									</tr>
									<tr>	
										<td class="label"><s:label name="Lote" value="Lote" cssStyle="text-align:right;"/></td>
										<td>
											<s:textfield key="loteAsignado" label="loteAsignado" disabled="true"/>
										</td>
									</tr>						
									<tr>	
										<td class="label"><s:label name="Fecha" value="Fecha" cssStyle="text-align:right;"/></td>
										<td>
											<s:textfield key="horaProceso" label="horaProceso" disabled="true"/>
										</td>
									</tr>
									<tr>														
										<td class="label"><s:label name="Cantidad(unidades)" value="Cantidad(unidades)" cssStyle="text-align:right;"/></td>
										<td nowrap="nowrap">
											<s:textfield key="cantidadUnitaria" label="%{getText('exit.cantidadUnitaria')}" />
										</td>
									</tr>
									<tr>														
										<td class="label"><s:label name="Peso Neto" value="Peso Neto" cssStyle="text-align:right;"/></td>
										<td nowrap="nowrap">
											<s:textfield key="pesoNeto" label="%{getText('exit.pesoNeto')}" />
										</td>
									</tr>	
									<tr>														
										<td class="label"><s:label name="Numero Bultos" value="Numero Bultos" cssStyle="text-align:right;"/></td>
										<td nowrap="nowrap">
											<s:textfield key="numeroBultos" label="%{getText('exit.numeroBultos')}" />
										</td>
									</tr>	
									<tr>														
										<td class="label"><s:label name="Precio Unitario" value="Precio Unitario" cssStyle="text-align:right;"/></td>
										<td nowrap="nowrap">
											<s:textfield key="precioUnitario" label="precioUnitario" />
										</td>
									</tr>						
									<tr>	
										<td nowrap="nowrap">
											<s:submit value="Eliminar"/> 
										</td>
									</tr>								
								</table>

						</s:iterator>					
					</fieldset>						 
				</s:form>	
		</div><!-- end #dashboard -->
		</div><!-- end #main_panel_container-->
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	</s:i18n>
</div><!-- end container -->
</body>
</html>