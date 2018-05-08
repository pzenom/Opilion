<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="UTF-8" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../includes/head.jsp" flush="true" />
<body>
<div class="container" id="container">
	<s:i18n name="ApplicationResources">
	<div id="content" >
	<div id="content_main" class="clearfix">
	<div id="main_panel_container" class="left">
	<div id="dashboard">
		<h3 class="ico_posts">Cargos</h3>
		<s:form action="InseCargFact" validate="true" onsubmit="javascript:window.opener.location.reload();javascript:window.close();">
			<div="tabla">
				<table id="tabla">
							<tr>
								<th>Transporte</th>
								<th>IVA %</th>
								<th>Total</th>
							</tr>
							<tr>
								<td><s:textfield name="cargoTran" key="cargoTran" value="0" size="4"/></td>
								<td><s:textfield name="ivact" key="ivacargo1" size="4" value="0" onchange="doCalcCargoIndividual(cargoTran.value,this.value,'T');"/></td>
								<td><s:textfield name="totalCargoTran" key="totalCargoTran" value="0" size="4"/></td>
							</tr>
							<tr>
								<th>Bancos</th>
								<th>IVA %</th>
								<th>Total</th>
							</tr>
							<tr>
								<td><s:textfield name="cargoBanc" key="cargoBanc" value="0" size="4"/></td>
								<td><s:textfield name="ivacb" key="ivacargo2" size="4" value="0" onchange="doCalcCargoIndividual(cargoBanc.value,this.value,'B');"/></td>
								<td><s:textfield name="totalCargoBanc" key="totalCargoBanc" value="0" size="4"/></td>
							</tr>
							<tr>
								<th>Devoluci&oacute;n</th>
								<th>IVA %</th>
								<th>Total</th>
							</tr>
							<tr>
								<td><s:textfield name="cargoDevo" key="cargoDevo" value="0" size="4"/></td>
								<td><s:textfield name="ivacd" key="ivacargo3" size="4" value="0" onchange="doCalcCargoIndividual(cargoDevo.value,this.value,'D');"/></td>
								<td><s:textfield name="totalCargoDevo" key="totalCargoDevo" value="0" size="4"/></td>
							</tr>
							<tr>
								<th>Calcular Total Cargos</th>
							</tr>
							<tr>
								<td><s:textfield name="cargosTotal" key="cargosTotal" value="0" onclick="doCalcCargoTotal();" size="4"/></td>
							</tr>
					</tbody>
				</table>
			</div>	
			<s:submit value="Insertar" />
		</s:form>
	</div><!-- end #dashboard -->
	</div><!-- end #main_panel_container-->
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	</s:i18n>
</div><!-- end container -->
</body>
</html>