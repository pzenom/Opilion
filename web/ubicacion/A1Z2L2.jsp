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
										<td id="81" class="hueco" onmouseover="javascript: muestraAlmacenados(81);" onclick="javascript:seleccionHueco(2,4,1,10,3,30,1,81,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E1:P3:H1" title="A1:Z2:L2:E1:P3:H1">
										</td>
										<td id="82" class="hueco" onmouseover="javascript: muestraAlmacenados(82);" onclick="javascript:seleccionHueco(2,4,1,10,3,30,2,82,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E1:P3:H2" title="A1:Z2:L2:E1:P3:H2">
										</td>
										<td id="83" class="hueco" onmouseover="javascript: muestraAlmacenados(83);" onclick="javascript:seleccionHueco(2,4,1,10,3,30,3,83,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E1:P3:H3" title="A1:Z2:L2:E1:P3:H3">
										</td>
										<td id="84" class="hueco" onmouseover="javascript: muestraAlmacenados(84);" onclick="javascript:seleccionHueco(2,4,1,10,3,30,4,84,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E1:P3:H4" title="A1:Z2:L2:E1:P3:H4">
										</td>
										<td rowspan="3" style="background: black; width: 30px;" ></td>
										<td id="93" class="hueco" onmouseover="javascript: muestraAlmacenados(93);" onclick="javascript:seleccionHueco(2,4,2,11,3,33,1,93,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E2:P3:H1" title="A1:Z2:L2:E2:P3:H1">
										</td>
										<td id="94" class="hueco" onmouseover="javascript: muestraAlmacenados(94);" onclick="javascript:seleccionHueco(2,4,2,11,3,33,2,94,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E2:P3:H2" title="A1:Z2:L2:E2:P3:H2">
										</td>
										<td id="95" class="hueco" onmouseover="javascript: muestraAlmacenados(95);" onclick="javascript:seleccionHueco(2,4,2,11,3,33,3,95,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E2:P3:H3" title="A1:Z2:L2:E2:P3:H3">
										</td>
										<td id="96" class="hueco" onmouseover="javascript: muestraAlmacenados(96);" onclick="javascript:seleccionHueco(2,4,2,11,3,33,4,96,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E2:P3:H4" title="A1:Z2:L2:E2:P3:H4">
										</td>
										<td rowspan="3" style="background: black; width: 30px;" ></td>
										<td id="99" class="hueco" onmouseover="javascript: muestraAlmacenados(99);" onclick="javascript:seleccionHueco(2,4,3,12,3,36,1,95,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E3:P3:H1" title="A1:Z2:L2:E3:P3:H1">
										</td>
									</tr>
									<tr>
									<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										<td id="77" class="hueco" onmouseover="javascript: muestraAlmacenados(77);" onclick="javascript:seleccionHueco(2,4,1,10,2,29,1,77,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E1:P2:H1" title="A1:Z2:L2:E1:P2:H1">
										</td>
										<td id="78" class="hueco" onmouseover="javascript: muestraAlmacenados(78);" onclick="javascript:seleccionHueco(2,4,1,10,2,29,2,78,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E1:P2:H2" title="A1:Z2:L2:E1:P2:H2">
										</td>
										<td id="79" class="hueco" onmouseover="javascript: muestraAlmacenados(79);" onclick="javascript:seleccionHueco(2,4,1,10,2,29,3,79,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E1:P2:H3" title="A1:Z2:L2:E1:P2:H3">
										</td>
										<td id="80" class="hueco" onmouseover="javascript: muestraAlmacenados(80);" onclick="javascript:seleccionHueco(2,4,1,10,2,29,4,80,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E1:P2:H4" title="A1:Z2:L2:E1:P2:H4">
										</td>
										<td id="89" class="hueco" onmouseover="javascript: muestraAlmacenados(89);" onclick="javascript:seleccionHueco(2,4,2,11,2,32,1,89,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E2:P2:H1" title="A1:Z2:L2:E2:P2:H1">
										</td>
										<td id="90" class="hueco" onmouseover="javascript: muestraAlmacenados(90);" onclick="javascript:seleccionHueco(2,4,2,11,2,32,2,90,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E2:P2:H2" title="A1:Z2:L2:E2:P2:H2">
										</td>
										<td id="91" class="hueco" onmouseover="javascript: muestraAlmacenados(91);" onclick="javascript:seleccionHueco(2,4,2,11,2,32,3,91,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E2:P2:H3" title="A1:Z2:L2:E2:P2:H3">
										</td>
										<td id="92" class="hueco" onmouseover="javascript: muestraAlmacenados(92);" onclick="javascript:seleccionHueco(2,4,2,11,2,32,4,92,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E2:P2:H4" title="A1:Z2:L2:E2:P2:H4">
										</td>
										<td id="98" class="hueco" onmouseover="javascript: muestraAlmacenados(98);" onclick="javascript:seleccionHueco(2,4,3,12,3,35,1,98,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E3:P2:H1" title="A1:Z2:L2:E3:P2:H1">
										</td>
									</tr>
									<tr>
									<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										<td id="73" class="hueco" onmouseover="javascript: muestraAlmacenados(73);" onclick="javascript:seleccionHueco(2,4,1,10,1,28,1,73,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E1:P1:H1" title="A1:Z2:L2:E1:P1:H1">
										</td>
										<td id="74" class="hueco" onmouseover="javascript: muestraAlmacenados(74);" onclick="javascript:seleccionHueco(2,4,1,10,1,28,2,74,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E1:P1:H2" title="A1:Z2:L2:E1:P1:H2">
										</td>
										<td id="75" class="hueco" onmouseover="javascript: muestraAlmacenados(75);" onclick="javascript:seleccionHueco(2,4,1,10,1,28,3,75,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E1:P1:H3" title="A1:Z2:L2:E1:P1:H3">
										</td>
										<td id="76" class="hueco" onmouseover="javascript: muestraAlmacenados(76);" onclick="javascript:seleccionHueco(2,4,1,10,1,28,4,76,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E1:P1:H4" title="A1:Z2:L2:E1:P1:H4">
										</td>
										<td id="85" class="hueco" onmouseover="javascript: muestraAlmacenados(85);" onclick="javascript:seleccionHueco(2,4,2,11,1,31,1,85,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E2:P1:H1" title="A1:Z2:L2:E2:P1:H1">
										</td>
										<td id="86" class="hueco" onmouseover="javascript: muestraAlmacenados(86);" onclick="javascript:seleccionHueco(2,4,2,11,1,31,2,86,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E2:P1:H2" title="A1:Z2:L2:E2:P1:H2">
										</td>
										<td id="87" class="hueco" onmouseover="javascript: muestraAlmacenados(87);" onclick="javascript:seleccionHueco(2,4,2,11,1,31,3,87,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E2:P1:H3" title="A1:Z2:L2:E2:P1:H3">
										</td>
										<td id="88" class="hueco" onmouseover="javascript: muestraAlmacenados(88);" onclick="javascript:seleccionHueco(2,4,2,11,1,31,4,88,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E2:P1:H4" title="A1:Z2:L2:E2:P1:H4">
										</td>
										<td id="97" class="hueco" onmouseover="javascript: muestraAlmacenados(97);" onclick="javascript:seleccionHueco(2,4,3,12,1,34,1,97,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L2:E3:P1:H1" title="A1:Z2:L2:E3:P1:H1">
										</td>
									</tr>
								</table>
							</div>
						</div>
						<img src="img/planos/palet.png" style="display: none;" onload="javascript:dibujarOcupados('A1Z1L1');">
					</s:iterator>
				</fieldset>
			<s:submit targets="ajaxDiv" value="Seleccionar producto" cssStyle="display:none;" />
		</s:form>
	</s:div>
	<s:div name="ajaxMuestra" id="ajaxMuestra" cssStyle="display: none;">
	</s:div>
	<div style="display: none;">
		<s:form id="formu" name="formu" action="MuestraAlmacenadosAjax" validate="true" method="get">
			<s:hidden id="idHuecoMuestra" key="idHuecoMuestra" name="idHuecoMuestra"/>
			<s:submit id="submitMuestra" targets="ajaxMuestra" value="MuestraAlmacenadosAjax" cssStyle="display:none;"/>
		</s:form>
	</div>
</s:i18n>