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
						<h3 class="ico_posts">ALBA_ERR</h3>
						<div id="historicoCopias">
							<fieldset>
								<legend><span>Errores creando albarán</span></legend>
								<table class="tabla">
									<thead>
										<tr>
											<th style="font-size: 17px;">Errores</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator id="problemas" value="%{#session.errores}">
											<tr class="copiaSeguridad">
												<td style="font-size: 15px; padding: 8px;"><s:property value="error" /></td>
											</tr>
										</s:iterator>
									</tbody>
									
								</table>
							</fieldset>
							<a id="bot_inserta" class="botonInserta" title="Volver" href="javascript::window.history.back();">
										<img title="Volver" alt="Volver" src="img/j_button1_prev.png">
										Volver
									</a>
						</div> <!-- end historicoCopias -->
					</div><!-- end #dashboard -->
				</div><!-- end #main_panel_container-->
			</div><!-- end #content_main -->
		</div><!-- end #content -->
	<jsp:include page="../includes/pie.jsp" flush="true" />
		</s:i18n>
	</div><!-- end container -->
	<!--
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="registrosalida/consRegiAlba.js"></script>
-->
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	copias = $(".copiaSeguridad");
	cuantasCopias = copias.length;
	//alert(cuantasCopias);
	if (cuantasCopias <= 0){
		$("#historicoCopias").hide();
	}
});

function show(){
	$("#newBackup").hide();
	$("#copiaNueva").show();
	$("#noBackup").show();
}

function hide(){
	$("#noBackup").hide();
	$("#copiaNueva").hide();
	$("#newBackup").show();
}
</script>
</body>
</html>