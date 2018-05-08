<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:div name="ajax" id="ajaxDiv">
		<!-- <p>&nbsp;</p> -->
		<s:form id="formulario" name="formulario" action="SeleccionHueco" validate="true" method="get">
				<fieldset><!-- Filtro Campos-->
					<legend><span>Seleccione el piso</span></legend>
					<s:iterator id="ocupados" value="%{#session.ocupados}" status="itStatus">
						<s:hidden cssClass="ocupado" id="ocupado_%{#ocupados.idHueco}" value="%{#ocupados.idHueco}" />
					</s:iterator>
					<s:iterator id="ubicacion" value="%{#session.ubicacion}">
						<p style="font-size: 20px;">Linea '<s:property value="nombreLinea"/>', de la zona '<s:property value="nombreZona"/>', en el almacen '<s:property value="nombreAlmacen"/>'</p>
						<s:hidden id="idZona" key="idZona" name="idZona" value="%{#ubicacion.idZona}"/>
						<s:hidden id="idAlmacen" key="idAlmacen" name="idAlmacen" value="%{#ubicacion.idAlmacen}"/>
						<s:hidden id="idLinea" key="idLinea" name="idLinea" value="%{#ubicacion.idLinea}"/>
						<s:hidden id="registro" key="registro" name="registro" cssClass="registro" value="%{#ubicacion.registro}"/>
						<s:hidden id="idEstanteria" key="idEstanteria" name="idEstanteria"/>
						<s:hidden id="idPiso" key="idPiso" name="idPiso"/>
						<s:hidden id="idHueco" key="idHueco" name="idHueco" cssClass="idHueco"/>
						<s:hidden id="idHuecoPiso" key="idHuecoPiso" name="idHuecoPiso"/>
						<div id="conjunto">
							<div id="tablaLinea">
								<table class="tablasLineas">
									<tr>
										<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										<td id="166" class="hueco" onmouseover="javascript: muestraAlmacenados(166);" onclick="javascript:seleccionHueco(1,7,1,19,4,64,1,166,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E1:P4:H1" title="A1:Z5:L1:E1:P4:H1">
										</td>
										<td id="167" class="hueco" onmouseover="javascript: muestraAlmacenados(167);" onclick="javascript:seleccionHueco(1,7,1,19,4,64,2,167,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E1:P4:H2" title="A1:Z5:L1:E1:P4:H2">
										</td>
										<td rowspan="4" style="background: black; width: 30px;" ></td>
										<td id="174" class="hueco" onmouseover="javascript: muestraAlmacenados(174);" onclick="javascript:seleccionHueco(1,7,2,20,4,68,1,174,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E2:P4:H1" title="A1:Z5:L1:E2:P4:H1">
										</td>
										<td id="175" class="hueco" onmouseover="javascript: muestraAlmacenados(175);" onclick="javascript:seleccionHueco(1,7,2,20,4,68,2,175,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E2:P4:H2" title="A1:Z5:L1:E2:P4:H2">
										</td>
										<td rowspan="4" style="background: black; width: 30px;" ></td>
										<td id="185" class="hueco" onmouseover="javascript: muestraAlmacenados(185);" onclick="javascript:seleccionHueco(1,7,3,21,4,72,1,185,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E3:P4:H1" title="A1:Z5:L1:E3:P4:H1">
										</td>
										<td id="186" class="hueco" onmouseover="javascript: muestraAlmacenados(186);" onclick="javascript:seleccionHueco(1,7,3,21,4,72,2,186,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E3:P4:H2" title="A1:Z5:L1:E3:P4:H2">
										</td>
										<td id="187" class="hueco" onmouseover="javascript: muestraAlmacenados(187);" onclick="javascript:seleccionHueco(1,7,3,21,4,72,3,187,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E3:P4:H3" title="A1:Z5:L1:E3:P4:H3">
										</td>
										<td rowspan="4" style="background: black; width: 30px;" ></td>
										<td id="197" class="hueco" onmouseover="javascript: muestraAlmacenados(197);" onclick="javascript:seleccionHueco(1,7,4,22,4,76,1,197,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E4:P4:H1" title="A1:Z5:L1:E4:P4:H1">
										</td>
										<td id="198" class="hueco" onmouseover="javascript: muestraAlmacenados(198);" onclick="javascript:seleccionHueco(1,7,4,22,4,76,2,198,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E4:P4:H2" title="A1:Z5:L1:E4:P4:H2">
										</td>
										<td id="199" class="hueco" onmouseover="javascript: muestraAlmacenados(199);" onclick="javascript:seleccionHueco(1,7,4,22,4,76,3,199,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E4:P4:H3" title="A1:Z5:L1:E4:P4:H3">
										</td>
									</tr>
									<tr>
										<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										<td id="164" class="hueco" onmouseover="javascript: muestraAlmacenados(164);" onclick="javascript:seleccionHueco(1,7,1,19,3,63,1,164,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E1:P3:H1" title="A1:Z5:L1:E1:P3:H1">
										</td>
										<td id="165" class="hueco" onmouseover="javascript: muestraAlmacenados(165);" onclick="javascript:seleccionHueco(1,7,1,19,3,63,2,165,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E1:P3:H2" title="A1:Z5:L1:E1:P3:H2">
										</td>
										<td id="172" class="hueco" onmouseover="javascript: muestraAlmacenados(172);" onclick="javascript:seleccionHueco(1,7,2,20,3,67,1,172,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E2:P3:H1" title="A1:Z5:L1:E2:P3:H1">
										</td>
										<td id="173" class="hueco" onmouseover="javascript: muestraAlmacenados(173);" onclick="javascript:seleccionHueco(1,7,2,20,3,67,2,173,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E2:P3:H2" title="A1:Z5:L1:E2:P3:H2">
										</td>
										<td id="182" class="hueco" onmouseover="javascript: muestraAlmacenados(182);" onclick="javascript:seleccionHueco(1,7,3,21,3,71,1,182,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E3:P3:H1" title="A1:Z5:L1:E3:P3:H1">
										</td>
										<td id="183" class="hueco" onmouseover="javascript: muestraAlmacenados(183);" onclick="javascript:seleccionHueco(1,7,3,21,3,71,2,183,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E3:P3:H2" title="A1:Z5:L1:E3:P3:H2">
										</td>
										<td id="184" class="hueco" onmouseover="javascript: muestraAlmacenados(184);" onclick="javascript:seleccionHueco(1,7,3,21,3,71,3,184,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E3:P3:H3" title="A1:Z5:L1:E3:P3:H3">
										</td>
										<td id="194" class="hueco" onmouseover="javascript: muestraAlmacenados(194);" onclick="javascript:seleccionHueco(1,7,4,22,3,75,1,194,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E4:P3:H1" title="A1:Z5:L1:E4:P3:H1">
										</td>
										<td id="195" class="hueco" onmouseover="javascript: muestraAlmacenados(195);" onclick="javascript:seleccionHueco(1,7,4,22,3,75,2,195,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E4:P3:H2" title="A1:Z5:L1:E4:P3:H2">
										</td>
										<td id="196" class="hueco" onmouseover="javascript: muestraAlmacenados(196);" onclick="javascript:seleccionHueco(1,7,4,22,3,75,3,196,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E4:P3:H3" title="A1:Z5:L1:E4:P3:H3">
										</td>
									</tr>
									<tr>
										<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										<td id="162" class="hueco" onmouseover="javascript: muestraAlmacenados(162);" onclick="javascript:seleccionHueco(1,7,1,19,2,62,1,162,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E1:P2:H1" title="A1:Z5:L1:E1:P2:H1">
										</td>
										<td id="163" class="hueco" onmouseover="javascript: muestraAlmacenados(163);" onclick="javascript:seleccionHueco(1,7,1,19,2,62,2,163,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E1:P2:H2" title="A1:Z5:L1:E1:P2:H2">
										</td>
										<td id="170" class="hueco" onmouseover="javascript: muestraAlmacenados(170);" onclick="javascript:seleccionHueco(1,7,2,20,2,66,1,170,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E2:P2:H1" title="A1:Z5:L1:E2:P2:H1">
										</td>
										<td id="171" class="hueco" onmouseover="javascript: muestraAlmacenados(171);" onclick="javascript:seleccionHueco(1,7,2,20,2,66,2,171,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E2:P2:H2" title="A1:Z5:L1:E2:P2:H2">
										</td>
										<td id="179" class="hueco" onmouseover="javascript: muestraAlmacenados(179);" onclick="javascript:seleccionHueco(1,7,3,21,2,70,1,179,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E3:P2:H1" title="A1:Z5:L1:E3:P2:H1">
										</td>
										<td id="180" class="hueco" onmouseover="javascript: muestraAlmacenados(180);" onclick="javascript:seleccionHueco(1,7,3,21,2,70,2,180,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E3:P2:H2" title="A1:Z5:L1:E3:P2:H2">
										</td>
										<td id="181" class="hueco" onmouseover="javascript: muestraAlmacenados(181);" onclick="javascript:seleccionHueco(1,7,3,21,2,70,3,181,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E3:P2:H3" title="A1:Z5:L1:E3:P2:H3">
										</td>
										<td id="191" class="hueco" onmouseover="javascript: muestraAlmacenados(191);" onclick="javascript:seleccionHueco(1,7,4,22,2,74,1,191,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E4:P2:H1" title="A1:Z5:L1:E4:P2:H1">
										</td>
										<td id="192" class="hueco" onmouseover="javascript: muestraAlmacenados(192);" onclick="javascript:seleccionHueco(1,7,4,22,2,74,2,192,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E4:P2:H2" title="A1:Z5:L1:E4:P2:H2">
										</td>
										<td id="193" class="hueco" onmouseover="javascript: muestraAlmacenados(193);" onclick="javascript:seleccionHueco(1,7,4,22,2,74,3,193,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E4:P3:H3" title="A1:Z5:L1:E4:P3:H3">
										</td>
									</tr>
									<tr>
										<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										<td id="160" class="hueco" onmouseover="javascript: muestraAlmacenados(160);" onclick="javascript:seleccionHueco(1,6,1,19,1,61,1,160,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E1:P1:H1" title="A1:Z5:L1:E1:P1:H1">
										</td>
										<td id="161" class="hueco" onmouseover="javascript: muestraAlmacenados(161);" onclick="javascript:seleccionHueco(1,7,1,19,1,61,2,161,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E1:P1:H2" title="A1:Z5:L1:E1:P1:H2">
										</td>
										<td id="168" class="hueco" onmouseover="javascript: muestraAlmacenados(168);" onclick="javascript:seleccionHueco(1,7,2,20,1,65,1,168,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E2:P1:H1" title="A1:Z5:L1:E2:P1:H1">
										</td>
										<td id="169" class="hueco" onmouseover="javascript: muestraAlmacenados(169);" onclick="javascript:seleccionHueco(1,7,2,20,1,65,2,169,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E2:P1:H2" title="A1:Z5:L1:E2:P1:H2">
										</td>
										<td id="176" class="hueco" onmouseover="javascript: muestraAlmacenados(176);" onclick="javascript:seleccionHueco(1,7,3,21,1,69,1,176,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E3:P1:H1" title="A1:Z5:L1:E3:P1:H1">
										</td>
										<td id="177" class="hueco" onmouseover="javascript: muestraAlmacenados(177);" onclick="javascript:seleccionHueco(1,7,3,21,1,69,2,177,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E3:P1:H2" title="A1:Z5:L1:E3:P1:H2">
										</td>
										<td id="178" class="hueco" onmouseover="javascript: muestraAlmacenados(178);" onclick="javascript:seleccionHueco(1,7,3,21,1,69,3,178,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E3:P1:H3" title="A1:Z5:L1:E3:P1:H3">
										</td>
										<td id="188" class="hueco" onmouseover="javascript: muestraAlmacenados(188);" onclick="javascript:seleccionHueco(1,7,4,22,1,73,1,188,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E4:P1:H1" title="A1:Z5:L1:E4:P1:H1">
										</td>
										<td id="189" class="hueco" onmouseover="javascript: muestraAlmacenados(189);" onclick="javascript:seleccionHueco(1,7,4,22,1,73,2,189,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E4:P1:H2" title="A1:Z5:L1:E4:P1:H2">
										</td>
										<td id="190" class="hueco" onmouseover="javascript: muestraAlmacenados(190);" onclick="javascript:seleccionHueco(1,7,4,22,1,73,3,190,'<s:property value='%{#session.registro}'/>');" alt="A1:Z5:L1:E4:P1:H3" title="A1:Z5:L1:E4:P1:H3">
										</td>
									</tr>
								</table>
							</div>
						</div>
						<img src="img/planos/palet.png" style="display: none;" onload="javascript:dibujarOcupados('A1Z1L1');">
					</s:iterator>
				</fieldset>
			<s:submit  targets="ajaxDiv" value="Seleccionar producto" cssStyle="display:none;" />
		</s:form>
	</s:div>
	<s:div name="ajaxMuestra" id="ajaxMuestra" cssStyle="display: none;">
	</s:div>
	<div style="display: none;">
		<s:form id="formu" name="formu" action="MuestraAlmacenadosAjax" validate="true" method="get">
			<s:hidden id="idHuecoMuestra" key="idHuecoMuestra" name="idHuecoMuestra"/>
			<s:submit id="submitMuestra"  targets="ajaxMuestra" value="MuestraAlmacenadosAjax" cssStyle="display:none;"/>
		</s:form>
	</div>
</s:i18n>