<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:div name="ajax" id="ajaxDiv" >
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
										<td id="126" class="hueco" onmouseover="javascript: muestraAlmacenados(126);" onclick="javascript:seleccionHueco(1,6,1,15,4,48,1,126,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E1:P4:H1" title="A1:Z4:L1:E1:P4:H1">
										</td>
										<td id="127" class="hueco" onmouseover="javascript: muestraAlmacenados(127);" onclick="javascript:seleccionHueco(1,6,1,15,4,48,2,127,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E1:P4:H2" title="A1:Z4:L1:E1:P4:H2">
										</td>
										<td rowspan="4" style="background: black; width: 30px;" ></td>
										<td id="134" class="hueco" onmouseover="javascript: muestraAlmacenados(134);" onclick="javascript:seleccionHueco(1,6,2,16,4,52,1,134,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E2:P4:H1" title="A1:Z4:L1:E2:P4:H1">
										</td>
										<td id="135" class="hueco" onmouseover="javascript: muestraAlmacenados(135);" onclick="javascript:seleccionHueco(1,6,2,16,4,52,2,135,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E2:P4:H2" title="A1:Z4:L1:E2:P4:H2">
										</td>
										<td rowspan="4" style="background: black; width: 30px;" ></td>
										<td id="145" class="hueco" onmouseover="javascript: muestraAlmacenados(145);" onclick="javascript:seleccionHueco(1,6,3,17,4,56,1,145,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E3:P4:H1" title="A1:Z4:L1:E3:P4:H1">
										</td>
										<td id="146" class="hueco" onmouseover="javascript: muestraAlmacenados(146);" onclick="javascript:seleccionHueco(1,6,3,17,4,56,2,146,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E3:P4:H2" title="A1:Z4:L1:E3:P4:H2">
										</td>
										<td id="147" class="hueco" onmouseover="javascript: muestraAlmacenados(147);" onclick="javascript:seleccionHueco(1,6,3,17,4,56,3,147,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E3:P4:H3" title="A1:Z4:L1:E3:P4:H3">
										</td>
										<td rowspan="4" style="background: black; width: 30px;" ></td>
										<td id="157" class="hueco" onmouseover="javascript: muestraAlmacenados(157);" onclick="javascript:seleccionHueco(2,2,4,18,4,60,1,157,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E4:P4:H1" title="A1:Z4:L1:E4:P4:H1">
										</td>
										<td id="158" class="hueco" onmouseover="javascript: muestraAlmacenados(158);" onclick="javascript:seleccionHueco(2,2,4,18,4,60,2,158,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E4:P4:H2" title="A1:Z4:L1:E4:P4:H2">
										</td>
										<td id="159" class="hueco" onmouseover="javascript: muestraAlmacenados(159);" onclick="javascript:seleccionHueco(2,2,4,18,4,60,3,159,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E4:P4:H3" title="A1:Z4:L1:E4:P4:H3">
										</td>
									</tr>
									<tr>
										<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										<td id="124" class="hueco" onmouseover="javascript: muestraAlmacenados(124);" onclick="javascript:seleccionHueco(1,6,1,15,3,47,1,124,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E1:P3:H1" title="A1:Z4:L1:E1:P3:H1">
										</td>
										<td id="125" class="hueco" onmouseover="javascript: muestraAlmacenados(125);" onclick="javascript:seleccionHueco(1,6,1,15,3,47,2,125,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E1:P3:H2" title="A1:Z4:L1:E1:P3:H2">
										</td>
										<td id="132" class="hueco" onmouseover="javascript: muestraAlmacenados(132);" onclick="javascript:seleccionHueco(1,6,2,16,3,51,1,132,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E2:P3:H1" title="A1:Z4:L1:E2:P3:H1">
										</td>
										<td id="133" class="hueco" onmouseover="javascript: muestraAlmacenados(133);" onclick="javascript:seleccionHueco(1,6,2,16,3,51,2,133,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E2:P3:H2" title="A1:Z4:L1:E2:P3:H2">
										</td>
										<td id="142" class="hueco" onmouseover="javascript: muestraAlmacenados(142);" onclick="javascript:seleccionHueco(1,6,3,17,3,55,1,142,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E3:P3:H1" title="A1:Z4:L1:E3:P3:H1">
										</td>
										<td id="143" class="hueco" onmouseover="javascript: muestraAlmacenados(143);" onclick="javascript:seleccionHueco(1,6,3,17,3,55,2,143,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E3:P3:H2" title="A1:Z4:L1:E3:P3:H2">
										</td>
										<td id="144" class="hueco" onmouseover="javascript: muestraAlmacenados(144);" onclick="javascript:seleccionHueco(1,6,3,17,3,55,3,144,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E3:P3:H3" title="A1:Z4:L1:E3:P3:H3">
										</td>
										<td id="154" class="hueco" onmouseover="javascript: muestraAlmacenados(154);" onclick="javascript:seleccionHueco(2,2,4,18,3,59,1,154,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E4:P3:H1" title="A1:Z4:L1:E4:P3:H1">
										</td>
										<td id="155" class="hueco" onmouseover="javascript: muestraAlmacenados(155);" onclick="javascript:seleccionHueco(2,2,4,18,3,59,2,155,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E4:P3:H2" title="A1:Z4:L1:E4:P3:H2">
										</td>
										<td id="156" class="hueco" onmouseover="javascript: muestraAlmacenados(156);" onclick="javascript:seleccionHueco(2,2,4,18,3,59,3,159,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E4:P4:H3" title="A1:Z4:L1:E4:P3:H3">
										</td>
									</tr>
									<tr>
										<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										<td id="122" class="hueco" onmouseover="javascript: muestraAlmacenados(122);" onclick="javascript:seleccionHueco(1,6,1,15,2,46,1,122,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E1:P2:H1" title="A1:Z4:L1:E1:P2:H1">
										</td>
										<td id="123" class="hueco" onmouseover="javascript: muestraAlmacenados(123);" onclick="javascript:seleccionHueco(1,6,1,15,2,46,2,123,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E1:P2:H2" title="A1:Z4:L1:E1:P2:H2">
										</td>
										<td id="130" class="hueco" onmouseover="javascript: muestraAlmacenados(130);" onclick="javascript:seleccionHueco(1,6,2,16,2,50,1,130,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E2:P2:H1" title="A1:Z4:L1:E2:P2:H1">
										</td>
										<td id="131" class="hueco" onmouseover="javascript: muestraAlmacenados(131);" onclick="javascript:seleccionHueco(1,6,2,16,2,50,2,131,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E2:P2:H2" title="A1:Z4:L1:E2:P2:H2">
										</td>
										<td id="139" class="hueco" onmouseover="javascript: muestraAlmacenados(139);" onclick="javascript:seleccionHueco(1,6,3,17,2,54,1,139,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E3:P2:H1" title="A1:Z4:L1:E3:P2:H1">
										</td>
										<td id="140" class="hueco" onmouseover="javascript: muestraAlmacenados(140);" onclick="javascript:seleccionHueco(1,6,3,17,2,54,2,140,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E3:P2:H2" title="A1:Z4:L1:E3:P2:H2">
										</td>
										<td id="141" class="hueco" onmouseover="javascript: muestraAlmacenados(141);" onclick="javascript:seleccionHueco(1,6,3,17,2,54,3,141,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E3:P2:H3" title="A1:Z4:L1:E3:P2:H3">
										</td>
										<td id="151" class="hueco" onmouseover="javascript: muestraAlmacenados(151);" onclick="javascript:seleccionHueco(1,6,4,18,2,58,1,151,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E4:P2:H1" title="A1:Z4:L1:E4:P2:H1">
										</td>
										<td id="152" class="hueco" onmouseover="javascript: muestraAlmacenados(152);" onclick="javascript:seleccionHueco(1,6,4,18,2,58,2,152,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E4:P2:H2" title="A1:Z4:L1:E4:P2:H2">
										</td>
										<td id="153" class="hueco" onmouseover="javascript: muestraAlmacenados(153);" onclick="javascript:seleccionHueco(1,6,4,18,2,58,3,153,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E4:P2:H3" title="A1:Z4:L1:E4:P2:H3">
										</td>
									</tr>
									<tr>
										<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										<td id="120" class="hueco" onmouseover="javascript: muestraAlmacenados(120);" onclick="javascript:seleccionHueco(1,6,1,15,1,45,1,120,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E1:P1:H1" title="A1:Z4:L1:E1:P1:H1">
										</td>
										<td id="121" class="hueco" onmouseover="javascript: muestraAlmacenados(121);" onclick="javascript:seleccionHueco(1,6,1,15,1,45,2,121,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E1:P1:H2" title="A1:Z4:L1:E1:P1:H2">
										</td>
										<td id="128" class="hueco" onmouseover="javascript: muestraAlmacenados(128);" onclick="javascript:seleccionHueco(1,6,2,16,1,49,1,128,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E2:P1:H1" title="A1:Z4:L1:E2:P1:H1">
										</td>
										<td id="129" class="hueco" onmouseover="javascript: muestraAlmacenados(129);" onclick="javascript:seleccionHueco(1,6,2,16,1,49,2,129,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E2:P1:H2" title="A1:Z4:L1:E2:P1:H2">
										</td>
										<td id="136" class="hueco" onmouseover="javascript: muestraAlmacenados(136);" onclick="javascript:seleccionHueco(1,6,3,17,1,53,1,136,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E3:P1:H1" title="A1:Z4:L1:E3:P1:H1">
										</td>
										<td id="137" class="hueco" onmouseover="javascript: muestraAlmacenados(137);" onclick="javascript:seleccionHueco(1,6,3,17,1,53,2,137,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E3:P1:H2" title="A1:Z4:L1:E3:P1:H2">
										</td>
										<td id="138" class="hueco" onmouseover="javascript: muestraAlmacenados(138);" onclick="javascript:seleccionHueco(1,6,3,17,1,53,3,138,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E3:P1:H3" title="A1:Z4:L1:E3:P1:H3">
										</td>
										<td id="148" class="hueco" onmouseover="javascript: muestraAlmacenados(148);" onclick="javascript:seleccionHueco(1,6,4,18,1,57,1,148,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E4:P1:H1" title="A1:Z4:L1:E4:P1:H1">
										</td>
										<td id="149" class="hueco" onmouseover="javascript: muestraAlmacenados(149);" onclick="javascript:seleccionHueco(1,6,4,18,1,57,2,149,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E4:P1:H2" title="A1:Z4:L1:E4:P1:H2">
										</td>
										<td id="150" class="hueco" onmouseover="javascript: muestraAlmacenados(150);" onclick="javascript:seleccionHueco(1,6,4,18,1,57,3,150,'<s:property value='%{#session.registro}'/>');" alt="A1:Z4:L1:E4:P1:H3" title="A1:Z4:L1:E4:P1:H3">
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
	<s:div name="ajaxMuestra" id="ajaxMuestra"  cssStyle="display: none;">
	</s:div>
	<div style="display: none;">
		<s:form id="formu" name="formu" action="MuestraAlmacenadosAjax" validate="true" method="get">
			<s:hidden id="idHuecoMuestra" key="idHuecoMuestra" name="idHuecoMuestra"/>
			<s:submit id="submitMuestra"  targets="ajaxMuestra" value="MuestraAlmacenadosAjax" cssStyle="display:none;"/>
		</s:form>
	</div>
</s:i18n>