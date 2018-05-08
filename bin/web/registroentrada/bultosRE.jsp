<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Gesti&oacute;n de los bultos del registro de entrada <s:label id="codigoEntrada" value="%{#session.codigoEntrada}"/><span class="screenCode">BULTOS_RE</span></h3>
	<s:form id="formulario" name="formulario" action="#" method="get">
		<s:hidden id="modificados" value="%{#session.modificados}"/>
		<s:hidden id="update" value="%{#session.updateBulto}"/>
		<s:hidden id="primero" value="%{#session.primero}"/>
		<s:iterator id="entrada" value="%{#session.listaregistros}">
			<s:hidden id="tipo" key="idTipoRegistro"/>
			<s:hidden id="codigo" key="codigoEntrada"/>
			<fieldset>
				<legend><span>Totales del registro de entrada</span></legend>
				<table id="distibucionTotal" class="tabla" border=0 cellpadding="2" cellspacing="2">
				<thead>
					<tr>
						<th class="noProductosFinales">Pallets</th>
						<th class="noProductosFinales">Bultos</th>
						<th id="th_kilosUnidades1"></th>
					</tr>
				<thead>
				<tbody>
					<tr>
						<td class="noProductosFinales"><span id="totalPallet"><s:property value="numeroPallets" /></span></td>
						<td class="noProductosFinales"><span id="totalBultos"><s:property value="numeroBultos" /></span></td>
						<td><span id="totalPeso"><s:property value="cantidad" /></span></td>
					</tr>
				</tbody>
				</table>
			</fieldset>
			<fieldset class="noProductosFinales">
				<legend><span>Autoajuste del n&uacute;mero de bultos</span></legend>
				<p style="font-size: 12;">Realiza el ajuste autom&aacute;tico del n&uacute;mero de bultos para todos los palets</p>
				<div style="position: relative; float: left;">
					<table id="distribucionPeso" class="tabla" border=0 cellpadding="2" cellspacing="2">
						<thead>
							<tr>
								<th rowspan=2 >Bultos por Palet</th>
							</tr>
						</thead>
						<tbody>
							<tr id="filaPalet_0">
								<td>
									<s:textfield id="numeroBultosAjustar" value="1" cssStyle="text-align:right; width: 50px"/>
								</td>
								<td>
									&nbsp;
									<a id="enlace<s:property value='numBulto' />" href="javascript:ajustarBultos();"><img id="guarda<s:property value='numBulto' />" src="img/pill_go.png" alt="Ajustar" title="Ajustar"/></a>
									&nbsp;
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</fieldset>
			<fieldset class="noProductosFinales">
				<legend><span id="spanAutoajuste"></span></legend>
				<p id="textAutoajuste" style="font-size: 12;"></p>
				<div id="autoajusteM" style="position: relative; float: left; display: none;">
					<table id="distribucionPeso" class="tabla" border=0 cellpadding="2" cellspacing="2">
					<thead>
						<tr>
							<th colspan=2 >Kgs. por palet</th>
						</tr>
						<tr>
							<th>Bruto</th>
							<th>Neto</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator id="lista" value="%{#session.paletUno}" status="status">
							<tr id="filaPalet_0">
								<td><s:textfield id="pesoBruto" value="%{#lista.pBruto}" cssStyle="text-align:right; width: 50px"/></td>
								<td><s:textfield id="pesoNeto" value="%{#lista.pNeto}" cssStyle="text-align:right; width: 50px"/></td>
								<td>
									&nbsp;
									<a href="javascript:ajustarPesos();"><img id="guarda0" src="img/pill_go.png" alt="Ajustar" title="Ajustar"/></a>
									&nbsp;
								</td>
							</tr>
						</s:iterator>
					</tbody>
					</table>
				</div>
				<div id="autoajusteE" style="position: relative; float: left; display: none;">
					<table id="distribucionPeso" class="tabla" border=0 cellpadding="2" cellspacing="2">
					<thead>
						<tr>
							<th>Unidades por palet</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator id="lista" value="%{#session.paletUno}" status="status">
							<tr id="filaPalet_0">
								<td><s:textfield id="uds" value="%{#lista.pBruto}" cssStyle="text-align:right; width: 50px"/></td>
								<td>
									&nbsp;
									<a href="javascript:ajustarPesos();"><img id="guarda0" src="img/pill_go.png" alt="Ajustar" title="Ajustar"/></a>
									&nbsp;
								</td>
							</tr>
						</s:iterator>
					</tbody>
					</table>
				</div>
			</fieldset>
			<fieldset>
				<legend><span id="spanDistribucion"></span></legend>
				<table id="distribucionPeso" class="tabla" border=0 cellpadding="2" cellspacing="2">
				<thead>
					<tr>
						<th rowspan="2"><span class="thPalet">Palet</span></th>
						<th class="noProductosFinales" rowspan="2">Bultos por Palet</th>
						<th class="noProductosFinales" colspan="2" id="thPesoUdsPalet"></th>
						<th rowspan="2">Ubicaci&oacute;n</th>
					</tr>
					<tr>
						<th class="celdaPBruto noProductosFinales">Bruto</th>
						<th class="noProductosFinales">Neto</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="listadepalets" value="%{#session.listabultos}" status="status">
						<tr id="filaPalet_<s:property value="#status.count" />" class="filaPalet">
							<td><span class="thPalet">Palet</span> <s:property value="numBulto" /> de <s:property value="numeroPallets" /></td>
							<td class="noProductosFinales"><s:textfield id="numBulto%{#listadepalets.numBulto}" name="numBulto%{#listadepalets.numBulto}" key="numBulto%{#listadepalets.numBulto}" value="%{#listadepalets.numBultosPalet}" cssStyle="text-align:right; width: 50px" onkeyup="creaUrl(%{#listadepalets.numBulto}, false, false)" cssClass="numBultos"/></td>
							<td class="celdaPBruto noProductosFinales"><s:textfield id="pBruto%{#listadepalets.numBulto}" name="pBruto%{#listadepalets.numBulto}" value="%{#listadepalets.pBruto}" cssStyle="text-align:right; width: 50px" onkeyup="creaUrl(%{#listadepalets.numBulto},false,false)" cssClass="brutos"/></td>
							<td class="noProductosFinales"><s:textfield id="pNeto%{#listadepalets.numBulto}" name="pNeto%{#listadepalets.numBulto}" value="%{#listadepalets.pNeto}" cssStyle="text-align:right; width: 50px" onkeyup="creaUrl(%{#listadepalets.numBulto}, false, false)" cssClass="netos"/></td>
							<td><s:textfield id="ubicacion%{#listadepalets.numBulto}" name="v%{#listadepalets.numBulto}" value="%{#listadepalets.descripcionUbicacion}" cssClass="ubicacion" cssStyle="text-align:right; width: 170px" disabled="true"/></td>
							<td style="display: none;"><s:textfield id="idUbicacion%{#listadepalets.numBulto}" value="%{#listadepalets.idUbicacion}" cssStyle="text-align:right; width: 1px" disabled="true"/></td>
							<td>
								<a id="enlace<s:property value='numBulto' />" ><img id="guarda<s:property value='numBulto' />" src="img/disk.png" alt="Insertar" title="Insertar"/></a>
								&nbsp;
								<a id="etiqueta<s:property value='numBulto' />" href="#" onClick="javascript:parent.get_ventana_emergente('ERE','EtiquetaBultoRE.action?idEntrada=<s:property value='%{#session.codigoEntrada}' />&numBulto=<s:property value='numBulto' />&pBruto=<s:property value='pBruto' />&pNeto=<s:property value='pNeto' />&numBultosPalet=<s:property value='numBultosPalet' />','no','no','590','420','','');">
									<img src="img/report.png" alt="Etiqueta" title="Etiqueta"/>
								</a>
								&nbsp;
								<a id="ubicar<s:property value='numBulto' />" class="ubicaciones" style="display: none;" href="#" onClick="javascript:ubicar(<s:property value='numBulto' />);">
									<img id="img_ubica_<s:property value='numBulto' />" src="img/ico_box.png" alt="Ubicar" title="Ubicar"/>
								</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
				</table>
				<br />
			</fieldset>
		</s:iterator>
	</s:form>
</s:i18n>