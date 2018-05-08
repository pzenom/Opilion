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
										<td id="54" class="hueco" onmouseover="javascript: muestraAlmacenados(54);" onclick="javascript:seleccionHueco(1,3,1,7,3,21,1,54,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E1:P3:H1" title="A1:Z2:L1:E1:P3:H1">
										</td>
										<td rowspan="3" style="background: black; width: 30px;" ></td>
										<td id="59" class="hueco" onmouseover="javascript: muestraAlmacenados(59);" onclick="javascript:seleccionHueco(1,3,2,8,3,24,1,59,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E2:P3:H1" title="A1:Z2:L1:E2:P3:H1">
										</td>
										<td id="60" class="hueco" onmouseover="javascript: muestraAlmacenados(60);" onclick="javascript:seleccionHueco(1,3,2,8,3,24,2,60,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E2:P3:H2" title="A1:Z2:L1:E2:P3:H2">
										</td>
										<td rowspan="3" style="background: black; width: 30px;" ></td>
										<td id="69" class="hueco" onmouseover="javascript: muestraAlmacenados(69);" onclick="javascript:seleccionHueco(1,3,3,9,3,27,1,69,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E3:P3:H1" title="A1:Z2:L1:E3:P3:H1">
										</td>
										<td id="70" class="hueco" onmouseover="javascript: muestraAlmacenados(70);" onclick="javascript:seleccionHueco(1,3,3,9,3,27,2,70,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E3:P3:H2" title="A1:Z2:L1:E3:P3:H2">
										</td>
										<td id="71" class="hueco" onmouseover="javascript: muestraAlmacenados(71);" onclick="javascript:seleccionHueco(1,3,3,9,3,27,3,71,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E3:P3:H3" title="A1:Z2:L1:E3:P3:H3">
										</td>
										<td id="72" class="hueco" onmouseover="javascript: muestraAlmacenados(72);" onclick="javascript:seleccionHueco(1,3,3,9,3,27,4,72,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E3:P3:H4" title="A1:Z2:L1:E3:P3:H4">
										</td>
									</tr>
									<tr>
										<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										
										<td id="53" class="hueco" onmouseover="javascript: muestraAlmacenados(53);" onclick="javascript:seleccionHueco(1,3,1,7,2,20,1,53,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E1:P2:H1" title="A1:Z2:L1:E1:P2:H1">
										</td>
										<td id="57" class="hueco" onmouseover="javascript: muestraAlmacenados(57);" onclick="javascript:seleccionHueco(1,3,2,8,2,23,1,57,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E2:P2:H1" title="A1:Z2:L1:E2:P2:H1">
										</td>
										<td id="58" class="hueco" onmouseover="javascript: muestraAlmacenados(58);" onclick="javascript:seleccionHueco(1,3,2,8,2,23,2,58,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E2:P2:H2" title="A1:Z2:L1:E2:P2:H2">
										</td>
										<td id="65" class="hueco" onmouseover="javascript: muestraAlmacenados(65);" onclick="javascript:seleccionHueco(1,3,3,9,2,26,1,65,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E3:P2:H1" title="A1:Z2:L1:E3:P2:H1">
										</td>
										<td id="66" class="hueco" onmouseover="javascript: muestraAlmacenados(66);" onclick="javascript:seleccionHueco(1,3,3,9,2,26,2,66,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E3:P2:H2" title="A1:Z2:L1:E3:P2:H2">
										</td>
										<td id="67" class="hueco" onmouseover="javascript: muestraAlmacenados(67);" onclick="javascript:seleccionHueco(1,3,3,9,2,26,3,67,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E3:P2:H3" title="A1:Z2:L1:E3:P2:H3">
										</td>
										<td id="68" class="hueco" onmouseover="javascript: muestraAlmacenados(68);" onclick="javascript:seleccionHueco(1,3,3,9,2,26,4,68,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E3:P2:H4" title="A1:Z2:L1:E3:P2:H4">
										</td>
									</tr>
									<tr>
									<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										<td id="52" class="hueco" onmouseover="javascript: muestraAlmacenados(52);" onclick="javascript:seleccionHueco(1,3,1,7,1,19,1,52,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E1:P1:H1" title="A1:Z2:L1:E1:P1:H1">
										</td>
										<td id="55" class="hueco" onmouseover="javascript: muestraAlmacenados(55);" onclick="javascript:seleccionHueco(1,3,2,8,1,22,1,55,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E2:P1:H1" title="A1:Z2:L1:E2:P1:H1">
										</td>
										<td id="56" class="hueco" onmouseover="javascript: muestraAlmacenados(56);" onclick="javascript:seleccionHueco(1,3,2,8,1,22,2,56,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E2:P1:H2" title="A1:Z2:L1:E2:P1:H2">
										</td>
										<td id="61" class="hueco" onmouseover="javascript: muestraAlmacenados(61);" onclick="javascript:seleccionHueco(1,3,3,9,1,25,1,61,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E3:P1:H1" title="A1:Z2:L1:E3:P1:H1">
										</td>
										<td id="62" class="hueco" onmouseover="javascript: muestraAlmacenados(62);" onclick="javascript:seleccionHueco(1,3,3,9,1,25,2,62,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E3:P1:H2" title="A1:Z2:L1:E3:P1:H2">
										</td>
										<td id="63" class="hueco" onmouseover="javascript: muestraAlmacenados(63);" onclick="javascript:seleccionHueco(1,3,3,9,1,25,3,63,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E3:P1:H3" title="A1:Z2:L1:E3:P1:H3">
										</td>
										<td id="64" class="hueco" onmouseover="javascript: muestraAlmacenados(64);" onclick="javascript:seleccionHueco(1,3,3,9,1,25,4,64,'<s:property value='%{#session.registro}'/>');" alt="A1:Z2:L1:E3:P1:H4" title="A1:Z2:L1:E3:P1:H4">
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