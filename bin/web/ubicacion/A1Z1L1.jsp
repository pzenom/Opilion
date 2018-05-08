<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:div name="ajax" id="ajaxDiv">
		<s:form id="formulario" name="formulario" action="SeleccionHueco" validate="true" method="get">
			<fieldset><!-- Filtro Campos-->
				<legend><span>Seleccione el piso</span></legend>
				<s:iterator id="ocupados" value="%{#session.ocupados}" status="itStatus">
					<s:hidden cssClass="ocupado" id="ocupado_%{#ocupados.idHueco}" value="%{#ocupados.idHueco}" />
				</s:iterator>
				<s:iterator id="ubicacion" value="%{#session.ubicacion}">
					<br />
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
									<td id="3" class="hueco" onmouseover="javascript: muestraAlmacenados(3);" onclick="javascript:seleccionHueco(1,1,1,1,3,3,1,3,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E1:P3:H1" title="A1:Z1:L1:E1:P3:H1">
									</td>
									<td rowspan="3" style="background: black; width: 10px;">
									</td>
									<td id="12" class="hueco" onmouseover="javascript: muestraAlmacenados(12);" onclick="javascript:seleccionHueco(1,1,2,2,3,6,1,12,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E2:P3:H1" title="A1:Z1:L1:E2:P3:H1">
									</td>
									<td id="13" class="hueco" onmouseover="javascript: muestraAlmacenados(13);" onclick="javascript:seleccionHueco(1,1,2,2,3,6,2,13,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E2:P3:H2" title="A1:Z1:L1:E2:P3:H2">
									</td>
									<td id="14" class="hueco" onmouseover="javascript: muestraAlmacenados(14);" onclick="javascript:seleccionHueco(1,1,2,2,3,6,3,14,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E2:P3:H3" title="A1:Z1:L1:E2:P3:H3">
									</td>
									<td id="15" class="hueco" onmouseover="javascript: muestraAlmacenados(15);" onclick="javascript:seleccionHueco(1,1,2,2,3,6,4,15,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E2:P3:H4" title="A1:Z1:L1:E2:P3:H4">
									</td>
									<td rowspan="3" style="background: black; width: 30px;" >
									</td>
									<td id="24" class="hueco" onmouseover="javascript: muestraAlmacenados(24);" onclick="javascript:seleccionHueco(1,1,3,3,3,9,1,24,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E3:P3:H1" title="A1:Z1:L1:E3:P3:H1">
									</td>
									<td id="25" class="hueco" onmouseover="javascript: muestraAlmacenados(25);" onclick="javascript:seleccionHueco(1,1,3,3,3,9,2,25,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E3:P3:H2" title="A1:Z1:L1:E3:P3:H2">
									</td>
									<td id="26" class="hueco" onmouseover="javascript: muestraAlmacenados(26);" onclick="javascript:seleccionHueco(1,1,3,3,3,9,3,26,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E3:P3:H3" title="A1:Z1:L1:E3:P3:H3">
									</td>
									<td id="27" class="hueco" onmouseover="javascript: muestraAlmacenados(27);" onclick="javascript:seleccionHueco(1,1,3,3,3,9,4,27,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E3:P3:H4" title="A1:Z1:L1:E3:P3:H4">
									</td>
								</tr>
								<tr>
									<td id="2" class="hueco" onmouseover="javascript: muestraAlmacenados(2);" onclick="javascript:seleccionHueco(1,1,1,1,2,2,1,2,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E1:P2:H1" title="A1:Z1:L1:E1:P2:H1">
									</td>
									<td id="8" class="hueco" onmouseover="javascript: muestraAlmacenados(8);" onclick="javascript:seleccionHueco(1,1,2,2,2,5,1,8,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E2:P2:H1" title="A1:Z1:L1:E2:P2:H1">
									</td>
									<td id="9" class="hueco" onmouseover="javascript: muestraAlmacenados(9);" onclick="javascript:seleccionHueco(1,1,2,2,2,5,2,9,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E2:P2:H2" title="A1:Z1:L1:E2:P2:H2">
									</td>
									<td id="10" class="hueco" onmouseover="javascript: muestraAlmacenados(10);" onclick="javascript:seleccionHueco(1,1,2,2,2,5,3,10,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E2:P2:H3" title="A1:Z1:L1:E2:P2:H3">
									</td>
									<td id="11" class="hueco" onmouseover="javascript: muestraAlmacenados(11);" onclick="javascript:seleccionHueco(1,1,2,2,2,5,4,11,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E2:P2:H4" title="A1:Z1:L1:E2:P2:H4">
									</td>
									<td id="20" class="hueco" onmouseover="javascript: muestraAlmacenados(20);" onclick="javascript:seleccionHueco(1,1,3,3,2,8,1,20,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E3:P2:H1" title="A1:Z1:L1:E3:P2:H1">
									</td>
									<td id="21" class="hueco" onmouseover="javascript: muestraAlmacenados(21);" onclick="javascript:seleccionHueco(1,1,3,3,2,8,2,21,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E3:P2:H2" title="A1:Z1:L1:E3:P2:H2">
									</td>
									<td id="22" class="hueco" onmouseover="javascript: muestraAlmacenados(22);" onclick="javascript:seleccionHueco(1,1,3,3,2,8,3,22,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E3:P2:H3" title="A1:Z1:L1:E3:P2:H3">
									</td>
									<td id="23" class="hueco" onmouseover="javascript: muestraAlmacenados(23);" onclick="javascript:seleccionHueco(1,1,3,3,2,8,4,23,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E3:P2:H4" title="A1:Z1:L1:E3:P2:H4">
									</td>
								</tr>
								<tr>
									<td id="1" class="hueco" onmouseover="javascript: muestraAlmacenados(1);" onclick="javascript:seleccionHueco(1,1,1,1,1,1,1,1,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E1:P1:H1" title="A1:Z1:L1:E1:P1:H1">
									</td>
									<td id="4" class="hueco" onmouseover="javascript: muestraAlmacenados(4);" onclick="javascript:seleccionHueco(1,1,2,2,1,4,1,4,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E2:P1:H1" title="A1:Z1:L1:E2:P1:H1">
									</td>
									<td id="5" class="hueco" onmouseover="javascript: muestraAlmacenados(5);" onclick="javascript:seleccionHueco(1,1,2,2,1,4,2,5,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E2:P1:H2" title="A1:Z1:L1:E2:P1:H2">
									</td>
									<td id="6" class="hueco" onmouseover="javascript: muestraAlmacenados(6);" onclick="javascript:seleccionHueco(1,1,2,2,1,4,3,6,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E2:P1:H3" title="A1:Z1:L1:E2:P1:H3">
									</td>
									<td id="7" class="hueco" onmouseover="javascript: muestraAlmacenados(7);" onclick="javascript:seleccionHueco(1,1,2,2,1,4,4,7,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E2:P1:H4" title="A1:Z1:L1:E2:P1:H4">
									</td>
									<td id="16" class="hueco" onmouseover="javascript: muestraAlmacenados(16);" onclick="javascript:seleccionHueco(1,1,3,3,1,7,1,16,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E3:P1:H1" title="A1:Z1:L1:E3:P1:H1">
									</td>
									<td id="17" class="hueco" onmouseover="javascript: muestraAlmacenados(17);" onclick="javascript:seleccionHueco(1,1,3,3,1,7,2,17,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E3:P1:H2" title="A1:Z1:L1:E3:P1:H2">
									</td>
									<td id="18" class="hueco" onmouseover="javascript: muestraAlmacenados(18);" onclick="javascript:seleccionHueco(1,1,2,2,1,7,3,18,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E3:P1:H3" title="A1:Z1:L1:E3:P1:H3">
									</td>
									<td id="19" class="hueco" onmouseover="javascript: muestraAlmacenados(19);" onclick="javascript:seleccionHueco(1,1,3,3,1,7,4,19,'<s:property value='%{#session.registro}'/>');" alt="A1:Z1:L1:E3:P1:H4" title="A1:Z1:L1:E3:P1:H4">
									</td>
								</tr>
							</table>
						</div>
					</div>
					<img src="img/planos/palet.png" style="display: none;" onload="javascript:dibujarOcupados('A1Z1L1');">
				</s:iterator>
			</fieldset>
		</s:form>
	</s:div>
	<s:div name="ajaxMuestra" id="ajaxMuestra" cssStyle="display: none;">
	</s:div>
</s:i18n>