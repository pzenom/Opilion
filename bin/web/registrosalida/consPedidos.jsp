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
			<a href="pagEscritorio.jsp" id="visit" class="right">Home</a>
		</div><!-- end top menu -->
	<div id="content_main" class="clearfix">
	<div id="main_panel_container" class="left">
	<div id="dashboard">
		<h3 class="ico_posts">Consulta Albaranes</h3>
					<s:form action="ConsAlba" validate="true">
						<fieldset>
							<legend><span>Criterios de b&uacute;squeda</span></legend>
							<table width="100%" cellpadding="2" cellspacing="2" border="0">
								<tr>
									<th><s:label name="%{getText('exit.albaran')}" value="%{getText('exit.albaran')}" cssStyle="text-align:right;"/></th>
									<td>
										<s:textfield key="albaran" label="%{getText('exit.albaran')}" value="" />
									</td>
									<th><s:label name="Estado" value="Estado" cssStyle="text-align:right;"/></th>
									<td>
										<s:select key="idFactura" list="#{'S':'Facturado','N':'No Facturado'}" headerKey="0" headerValue="--Ver todos--"/>
									</td>
								</tr>
								<tr>
									<th><s:label name="Fechas entre" value="Fechas entre" cssStyle="text-align:right;"/></th>
									<td colspan="3">
										<s:datetimepicker name="fechaInicio" key="fechaInicio" label="Format (yyyy-MM-dd)" displayFormat="yyyy-MM-dd"/>
										y :
										<s:datetimepicker name="fechaFin" key="fechaFin" label="Format (yyyy-MM-dd)" displayFormat="yyyy-MM-dd"/>
									</td>
								</tr>
							</table>
							<s:submit value="Consultar" />
						</fieldset>
				  </s:form>
			</div> <!-- end #tabledata -->
		</div><!-- end #dashboard -->
			<jsp:include page="../includes/menupedidos.jsp" flush="true" />
		</div><!-- end #main_panel_container-->
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	<jsp:include page="../includes/pie.jsp" flush="true" />
	</s:i18n>
</div><!-- end container -->
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="registrosalida/consRegiAlba.js"></script>
</body>
</html>