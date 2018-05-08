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
										<td id="109" class="hueco" onmouseover="javascript: muestraAlmacenados(109);" onclick="javascript:seleccionHueco(1,5,1,13,4,40,1,109,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E1:P4:H1" title="A1:Z3:L1:E1:P4:H1">
										</td>
										<td id="110" class="hueco" onmouseover="javascript: muestraAlmacenados(110);" onclick="javascript:seleccionHueco(1,5,1,13,4,40,2,110,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E1:P4:H2" title="A1:Z3:L1:E1:P4:H2">
										</td>
										<td id="111" class="hueco" onmouseover="javascript: muestraAlmacenados(111);" onclick="javascript:seleccionHueco(1,5,1,13,4,40,3,111,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E1:P4:H3" title="A1:Z3:L1:E1:P4:H3">
										</td>
										<td rowspan="4" style="background: black; width: 30px;" ></td>
										<td id="118" class="hueco" onmouseover="javascript: muestraAlmacenados(118);" onclick="javascript:seleccionHueco(1,5,2,14,4,44,1,118,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E2:P4:H1" title="A1:Z3:L1:E2:P4:H1">
										</td>
										<td id="119" class="hueco" onmouseover="javascript: muestraAlmacenados(119);" onclick="javascript:seleccionHueco(1,5,2,14,4,44,2,119,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E2:P4:H2" title="A1:Z3:L1:E2:P4:H2">
										</td>
									</tr>
									<tr>
										<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										<td id="106" class="hueco" onmouseover="javascript: muestraAlmacenados(106);" onclick="javascript:seleccionHueco(1,5,1,13,3,39,1,106,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E1:P3:H1" title="A1:Z3:L1:E1:P3:H1">
										</td>
										<td id="107" class="hueco" onmouseover="javascript: muestraAlmacenados(107);" onclick="javascript:seleccionHueco(1,5,1,13,3,39,2,107,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E1:P3:H2" title="A1:Z3:L1:E1:P3:H2">
										</td>
										<td id="108" class="hueco" onmouseover="javascript: muestraAlmacenados(108);" onclick="javascript:seleccionHueco(1,5,1,13,3,39,3,108,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E1:P3:H3" title="A1:Z3:L1:E1:P3:H3">
										</td>
										<td id="116" class="hueco" onmouseover="javascript: muestraAlmacenados(116);" onclick="javascript:seleccionHueco(1,5,2,14,3,43,1,116,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E2:P3:H1" title="A1:Z3:L1:E2:P3:H1">
										</td>
										<td id="117" class="hueco" onmouseover="javascript: muestraAlmacenados(117);" onclick="javascript:seleccionHueco(1,5,2,14,3,43,2,117,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E2:P3:H2" title="A1:Z3:L1:E2:P3:H2">
										</td>
									</tr>
									<tr>
										<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										<td id="103" class="hueco" onmouseover="javascript: muestraAlmacenados(103);" onclick="javascript:seleccionHueco(1,5,1,13,2,38,1,103,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E1:P2:H1" title="A1:Z3:L1:E1:P2:H1">
										</td>
										<td id="104" class="hueco" onmouseover="javascript: muestraAlmacenados(104);" onclick="javascript:seleccionHueco(1,5,1,13,2,38,2,104,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E1:P2:H2" title="A1:Z3:L1:E1:P2:H2">
										</td>
										<td id="105" class="hueco" onmouseover="javascript: muestraAlmacenados(105);" onclick="javascript:seleccionHueco(1,5,1,13,2,38,3,105,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E1:P2:H3" title="A1:Z3:L1:E1:P2:H3">
										</td>
										<td id="114" class="hueco" onmouseover="javascript: muestraAlmacenados(114);" onclick="javascript:seleccionHueco(1,5,2,14,2,42,1,114,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E2:P2:H1" title="A1:Z3:L1:E2:P2:H1">
										</td>
										<td id="115" class="hueco" onmouseover="javascript: muestraAlmacenados(115);" onclick="javascript:seleccionHueco(1,5,2,14,2,42,2,115,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E2:P2:H2" title="A1:Z3:L1:E2:P2:H2">
										</td>
									</tr>
									<tr>
										<!-- seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro) -->
										<td id="100" class="hueco" onmouseover="javascript: muestraAlmacenados(100);" onclick="javascript:seleccionHueco(1,5,1,13,1,37,1,100,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E1:P1:H1" title="A1:Z3:L1:E1:P1:H1">
										</td>
										<td id="101" class="hueco" onmouseover="javascript: muestraAlmacenados(101);" onclick="javascript:seleccionHueco(1,5,1,13,1,37,2,101,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E1:P1:H2" title="A1:Z3:L1:E1:P1:H2">
										</td>
										<td id="102" class="hueco" onmouseover="javascript: muestraAlmacenados(102);" onclick="javascript:seleccionHueco(1,5,1,13,1,37,3,102,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E1:P1:H3" title="A1:Z3:L1:E1:P1:H3">
										</td>
										<td id="112" class="hueco" onmouseover="javascript: muestraAlmacenados(112);" onclick="javascript:seleccionHueco(1,5,2,14,1,41,1,112,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E2:P1:H1" title="A1:Z3:L1:E2:P1:H1">
										</td>
										<td id="113" class="hueco" onmouseover="javascript: muestraAlmacenados(113);" onclick="javascript:seleccionHueco(1,5,2,14,1,41,2,113,'<s:property value='%{#session.registro}'/>');" alt="A1:Z3:L1:E2:P1:H2" title="A1:Z3:L1:E2:P1:H2">
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