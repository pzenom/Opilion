<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../includes/head.jsp" flush="true" />
<body>
	<s:i18n name="ApplicationResources">
	<div class="container" id="container">		
		<jsp:include page="../includes/header.jsp" flush="true" />
		<div id="content" >
			<div id="top_menu" class="clearfix">
				<jsp:include page="../includes/menu.jsp" flush="true" />
				<a href="pagEscritorio.jsp" id="visit" class="right">Home</a>
			</div><!-- end top menu -->
			<div id="content_main" class="clearfix">
				<div id="main_panel_container" class="left">
					<div id="dashboard">
						<p class="idPantalla">CALIRE</p>
						<h3 class="ico_posts">Análisis de Calidad</h3>
						<s:form id="formulario" action="CalcCaliRE" validate="true">
							<fieldset><!-- Historico-->
								<legend><span>Historico de Calidad</span></legend>
								<table id="tabla">
									<thead>
										<tr>
											<th><b><s:label name="%{getText('analisis.calidad.fecha')}" value="%{getText('analisis.calidad.fecha')}" cssStyle="text-align:left;"/></b></th>
											<th><b><s:label name="%{getText('analisis.calidad.varIG')}" value="%{getText('analisis.calidad.varIG')}" cssStyle="text-align:left;"/></b></th>
											<th><b><s:label name="%{getText('analisis.calidad.varSP')}" value="%{getText('analisis.calidad.varSP')}" cssStyle="text-align:left;"/></b></th>
											<th><b><s:label name="%{getText('analisis.calidad.varDP')}" value="%{getText('analisis.calidad.varDP')}" cssStyle="text-align:left;"/></b></th>
											<th><b><s:label name="%{getText('analisis.calidad.varDA')}" value="%{getText('analisis.calidad.varDA')}" cssStyle="text-align:left;"/></b></th>
											<th><b><s:label name="%{getText('analisis.calidad.varM')}" value="%{getText('analisis.calidad.varM')}" cssStyle="text-align:left;"/></b></th>
											<th><b><s:label name="%{getText('analisis.calidad.calidad')}" value="%{getText('analisis.calidad.calidad')}" cssStyle="text-align:left;"/></b></th>
										</tr>	
									</thead>																		
									<tbody>
										<s:iterator id="entrada" value="%{#session.historico}">
										<tr>
											<td><s:property value="fecha" /></td>
											<td><s:property value="varIG" /></td>
											<td><s:property value="varSP" /></td>
											<td><s:property value="varDP" /></td>
											<td><s:property value="varDA" /></td>
											<td><s:property value="varM" /></td>
											<td><s:property value="calidad" /></td>
										</tr>
										</s:iterator>
									</tbody>					
								</table>								
							</fieldset><!--end Historico-->
							
							<fieldset><!-- Informacion Basica-->
								<legend><span>Escoja los parámetros de calidad</span></legend>
								<table id="table">
									<thead>
										<tr>
											<th><b><s:label name="%{getText('analisis.calidad.varIG')}" value="%{getText('analisis.calidad.varIG')}" cssStyle="text-align:left;"/></b></th>
											<th><b><s:label name="%{getText('analisis.calidad.varSP')}" value="%{getText('analisis.calidad.varSP')}" cssStyle="text-align:left;"/></b></th>
											<th><b><s:label name="%{getText('analisis.calidad.varDP')}" value="%{getText('analisis.calidad.varDP')}" cssStyle="text-align:left;"/></b></th>
											<th><b><s:label name="%{getText('analisis.calidad.varDA')}" value="%{getText('analisis.calidad.varDA')}" cssStyle="text-align:left;"/></b></th>
											<th><b><s:label name="%{getText('analisis.calidad.varM')}" value="%{getText('analisis.calidad.varM')}" cssStyle="text-align:left;"/></b></th>
										</tr>
									</thead>																		
									<tbody>
										<tr>
											<td class="radio_label"><s:radio label="%{getText('analisis.calidad.varIG')}" name="varIG" list="#{'1':'Roto','2':'Semi roto','3':'Normal','4':'Semi Integro','5':'Integro'}"/></td>									
											<td class="radio_label"><s:radio label="%{getText('analisis.calidad.varSP')}" name="varSP" list="#{'1':'Lisa','2':'Semi lisa','3':'Normal','4':'Semi Rugosa','5':'Rugosa'}"/></td>									
											<td class="radio_label"><s:radio label="%{getText('analisis.calidad.varDP')}" name="varDP" list="#{'1':'Blanda','2':'Semi blanda','3':'Normal','4':'Semi Dura','5':'Dura'}"/></td>									
											<td class="radio_label"><s:radio label="%{getText('analisis.calidad.varDA')}" name="varDA" list="#{'1':'Blando','2':'Semi blando','3':'Normal','4':'Semi Duro','5':'Duro'}"/></td>
											<td class="radio_label"><s:radio label="%{getText('analisis.calidad.varM')}" name="varM" list="#{'1':'Nada Mantecoso','2':'Semi Mantecoso','3':'Normal','4':'Mantecoso','5':'Muy Mantecoso'}"/></td>
										</tr>	
									</tbody>					
								</table>
								<p>
									<!--script type="text/javascript">function submitform() { document.forms["formulario"].submit(); }</script-->
									<!--a class="botonInserta" id="actualiza" href="javascript:submitform()"-->
									<a class="botonInserta" id="actualiza" href="javascript:detector()">
										<img src="img/j_button_busca.png" alt="Calcular" title="Calcular"/>
										Calcular
									</a>&nbsp;
									<!--<s:set name="salida" value="%{#session.listareestado}">
										<s:if test="%{#session.listareestado=='si'}">
									<a class="botonInserta" id="pdf" href="ConsREJR.action">
										<img src="img/j_button_pdf.png" alt="Ver en PDF" title="Ver en PDF"/>
										Ver en PDF
									</a>&nbsp;
										</s:if>
									</s:set>-->
									<a class="botonInserta" id="pdf" href="javascript:print()">
										<img src="img/j_button_print.png" alt="Imprimir" title="Imprimir"/>
										Imprimir
									</a>&nbsp;
									<a class="botonInserta" id="volver" href="FiltRE.action">
										<img src="img/j_button_back.png" alt="Volver" title="Volver"/>
										Volver
									</a>&nbsp;
								</p>
								<!--s:submit value="Calcular"/-->
							</fieldset><!--end Informacion Basica-->

							<fieldset><!-- Informacion Basica-->
								<legend><span>Valoración</span></legend>
								<table width="100%" cellpadding="2" cellspacing="2" border="0">
									<tr>
										<td>Entre +23,5 y +13,5 puntos</td>
										<td>Mala Calidad</td>
									</tr>
									<tr>
										<td>Entre +12,50 y +3,5 puntos</td>
										<td>Calidad Aceptable</td>
									</tr>
									<tr>
										<td>Entre +2,5 y -6,5 puntos</td>
										<td>Muy Buena Calidad</td>
									</tr>
									<tr>
										<td>Entre -7,5 y -16,5 puntos</td>
										<td>Excelente Calidad</td>
									</tr>
								</table>
							</fieldset><!--end Valoración-->					
						</s:form>			
						
						<div id="tabledata" class="section">											
							Valor de Análisis :<s:property value="%{#session.valorCalidad}" />
							Resultado de Baremo :<s:property value="%{#session.baremoCalidad}" />							
						</div>
					</div> <!-- end #tabledata -->
				</div><!-- end #dashboard -->
			</div><!-- end #main_panel_container-->
		</div><!-- end #content_main -->
	</div><!-- end #content -->
	<jsp:include page="../includes/pie.jsp" flush="true" />
	</s:i18n>
</div><!-- end container -->
<script type="text/javascript" src="registroentrada/caliRE.js"></script>
</body>
</html>