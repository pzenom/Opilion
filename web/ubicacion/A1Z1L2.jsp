<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:div name="ajax" id="ajaxDiv" >
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
										<td id="34" class="hueco" onmouseover="javascript: muestraAlmacenados(34);" onclick="javascript:seleccionHueco(2,2,1,4,3,12,1,34,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E1:P3:H1" title="A1:Z1:L2:E1:P3:H1">
										</td>
										<td id="35" class="hueco" onmouseover="javascript: muestraAlmacenados(35);" onclick="javascript:seleccionHueco(2,2,1,4,3,12,2,35,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E1:P3:H2" title="A1:Z1:L2:E1:P3:H2">
										</td>
										<td id="36" class="hueco" onmouseover="javascript: muestraAlmacenados(36);" onclick="javascript:seleccionHueco(2,2,1,4,3,12,3,36,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E1:P3:H3" title="A1:Z1:L2:E1:P3:H3">
										</td>
										<td rowspan="3" style="background: black; width: 30px;" ></td>
										<td id="43" class="hueco" onmouseover="javascript: muestraAlmacenados(43);" onclick="javascript:seleccionHueco(2,2,2,5,3,15,1,43,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E2:P3:H1" title="A1:Z1:L2:E2:P3:H1">
										</td>
										<td id="44" class="hueco" onmouseover="javascript: muestraAlmacenados(44);" onclick="javascript:seleccionHueco(2,2,2,5,3,15,2,44,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E2:P3:H2" title="A1:Z1:L2:E2:P3:H2">
										</td>
										<td id="45" class="hueco" onmouseover="javascript: muestraAlmacenados(45);" onclick="javascript:seleccionHueco(2,2,2,5,3,15,3,45,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E2:P3:H3" title="A1:Z1:L2:E2:P3:H3">
										</td>
										<td rowspan="3" style="background: black; width: 30px;" ></td>
										<td id="50" class="hueco" onmouseover="javascript: muestraAlmacenados(50);" onclick="javascript:seleccionHueco(2,2,3,6,3,19,1,50,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E3:P3:H1" title="A1:Z1:L2:E3:P3:H1">
										</td>
										<td id="51" class="hueco" onmouseover="javascript: muestraAlmacenados(51);" onclick="javascript:seleccionHueco(2,2,3,6,3,19,2,51,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E3:P3:H2" title="A1:Z1:L2:E3:P3:H2">
										</td>
									</tr>
									<tr>
										<td id="31" class="hueco" onmouseover="javascript: muestraAlmacenados(31);" onclick="javascript:seleccionHueco(2,2,1,4,2,11,1,31,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E1:P2:H1" title="A1:Z1:L2:E1:P2:H1">
										</td>
										<td id="32" class="hueco" onmouseover="javascript: muestraAlmacenados(32);" onclick="javascript:seleccionHueco(2,2,1,4,2,11,2,32,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E1:P2:H2" title="A1:Z1:L2:E1:P2:H2">
										</td>
										<td id="33" class="hueco" onmouseover="javascript: muestraAlmacenados(33);" onclick="javascript:seleccionHueco(2,2,1,4,2,11,3,33,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E1:P2:H3" title="A1:Z1:L2:E1:P2:H3">
										</td>
										<td id="40" class="hueco" onmouseover="javascript: muestraAlmacenados(40);" onclick="javascript:seleccionHueco(2,2,2,5,2,14,1,40,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E2:P2:H1" title="A1:Z1:L2:E2:P2:H1">
										</td>
										<td id="41" class="hueco" onmouseover="javascript: muestraAlmacenados(41);" onclick="javascript:seleccionHueco(2,2,2,5,2,14,2,41,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E2:P3:H2" title="A1:Z1:L2:E2:P2:H2">
										</td>
										<td id="42" class="hueco" onmouseover="javascript: muestraAlmacenados(42);" onclick="javascript:seleccionHueco(2,2,2,5,2,14,3,42,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E2:P3:H3" title="A1:Z1:L2:E2:P2:H3">
										</td>
										<td id="48" class="hueco" onmouseover="javascript: muestraAlmacenados(48);" onclick="javascript:seleccionHueco(2,2,3,6,2,14,1,48,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E3:P2:H1" title="A1:Z1:L2:E3:P2:H1">
										</td>
										<td id="49" class="hueco" onmouseover="javascript: muestraAlmacenados(49);" onclick="javascript:seleccionHueco(2,2,3,6,2,14,2,49,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E3:P2:H2" title="A1:Z1:L2:E3:P2:H2">
										</td>
									</tr>
									<tr>
									<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										<td id="28" class="hueco" onmouseover="javascript: muestraAlmacenados(28);" onclick="javascript:seleccionHueco(2,2,1,4,1,10,1,28,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E1:P1:H1" title="A1:Z1:L2:E1:P1:H1">
										</td>
										<td id="29" class="hueco" onmouseover="javascript: muestraAlmacenados(29);" onclick="javascript:seleccionHueco(2,2,1,4,1,10,2,29,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E1:P1:H2" title="A1:Z1:L2:E1:P1:H2">
										</td>
										<td id="30" class="hueco" onmouseover="javascript: muestraAlmacenados(30);" onclick="javascript:seleccionHueco(2,2,1,4,1,10,3,30,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E1:P1:H3" title="A1:Z1:L2:E1:P1:H3">
										</td>
										<td id="37" class="hueco" onmouseover="javascript: muestraAlmacenados(37);" onclick="javascript:seleccionHueco(2,2,2,5,1,13,1,37,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E2:P1:H1" title="A1:Z1:L2:E2:P1:H1">
										</td>
										<td id="38" class="hueco" onmouseover="javascript: muestraAlmacenados(38);" onclick="javascript:seleccionHueco(2,2,2,5,1,13,2,38,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E2:P1:H2" title="A1:Z1:L2:E2:P1:H2">
										</td>
										<td id="39" class="hueco" onmouseover="javascript: muestraAlmacenados(39);" onclick="javascript:seleccionHueco(2,2,2,5,1,13,3,39,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E2:P1:H3" title="A1:Z1:L2:E2:P1:H3">
										</td>
										<td id="46" class="hueco" onmouseover="javascript: muestraAlmacenados(46);" onclick="javascript:seleccionHueco(2,2,3,6,1,16,1,46,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E3:P1:H1" title="A1:Z1:L2:E3:P1:H1">
										</td>
										<td id="47" class="hueco" onmouseover="javascript: muestraAlmacenados(47);" onclick="javascript:seleccionHueco(2,2,3,6,1,16,2,47,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L2:E3:P1:H2" title="A1:Z1:L2:E3:P1:H2">
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