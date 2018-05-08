<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="rcon.paso2" /> - <s:text name="insenv.envasesdos" /></h3>
	<div id="divNecesarioWidget">
		<s:form name="formulario" action="UpdaRECongCant" validate="true" >
			<div id="demo">
				<table id="tablaCong" cellpadding="0" cellspacing="0" border="0" class="display">
					<thead>
						<tr>
							<th style="display: none;">Seleccione</th>
							<th>Codigo Entrada</th>
							<th>Cantidad Disponible Kg.</th>
							<th>Cantidad</th>
							<th>Saldo</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator id="ingre" value="%{#session.listadocong}">
							<tr>
								<td style="display: none;"><input type="checkbox" checked="true" name="listProcSele" value=<s:property value="proceso" />/></td>
								<td><s:property value="proceso" /></td>
								<td id="<s:property value="proceso" />" class="seleccionado"><s:property value="cantidadProducto" /></td>
								<td>
									<s:textfield id="cantidad_%{#ingre.proceso}" cssClass="cantidad_%{#ingre.proceso}" name="cantidad" key="cantidad" size="10" onkeyup="doCalcAndSubmit(this.value, '%{#ingre.proceso}')" value="0"/>
								</td>
								<td>
									<s:textfield id="resta_%{#ingre.proceso}" name="resta" key="resta" size="10" value="" readonly="true"/>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div> <!-- end demo -->
			<fieldset>
				<legend><span>Seleccionar ubicacion registros de entrada</span></legend>
				<table id="electedIngredientsTable" class="tabla" border="1" width="100%">
					<thead>
						<tr>
							<th class="destaca" rowspan=2 style="width: 80px;">REG. ENTRADA</th>
							<th class="destaca">CANTIDAD (Kilos)</th>
						</tr>
					</thead>
					<tbody id="electedIngredientsBody">
						<s:iterator id="infomp" value="%{#session.ingredientes}">
							<s:if test="%{#infomp.idLinea == #infomp.idAnterior}">
							</s:if>
							<s:else>
								<tr id="escogerIngredientesNuevo<s:property value='idLinea' />">
									<td colspan="2" style="text-align:center; background-color: #CEF6CE !important;" >INGREDIENTE: <s:property value="nombre" /> - <s:property value="nombreCategoria" /></td>
									</tr>
							</s:else>
							<tr id="<s:property value='registroEntrada' />" class="ingrediente" style="font-size:14px;">
								<td class="entradaMateria"><s:property value="registroEntrada"/></td>
								<td id="cantidadMP_<s:property value="registroEntrada" />"><s:property value="teorica" /></td>
							</tr>
							<tr>
								<td><b>UBICACIONES</b></td>
								<td>Referencia del hueco</td>
								<td>Zona</td>
								<td>Linea</td>
								<td style="text-align: right;">Almacenado</td>
								<td style="text-align: right;">Cantidad a sacar</td>
							</tr>
							<s:iterator id="ubicacionesMp" value="%{#infomp.ubicaciones}" status="status">
								<tr style="font-size:14px;" id="<s:property value="registroEntrada" />_<s:property value="%{#status.count}" />" class="<s:property value="registroEntrada" />">
									<td style="background: transparent;"></td>
									<td style="width: 160px;"><s:property value="nombreHueco" /></td>
									<td style="width: 180px;"><s:property value="nombreZona" /></td>
									<td style="width: 200px;"><s:property value="nombreLinea" /></td>
									<td id="enHueco_<s:property value="registroEntrada" />_<s:property value="%{#status.count}" />" class="enHueco_<s:property value="registroEntrada" />" style="text-align: right; width: 90px;"><s:property value="cantidad" /></td>
									<td id="sacar_<s:property value="registroEntrada" />_<s:property value="%{#status.count}" />" style="text-align: right;">
										<s:textfield id="sacar_%{#ubicacionesMp.idUbicacion}_%{#infomp.registroEntrada}" cssClass="sacar_%{#infomp.registroEntrada}" key="sacar_%{#ubicacionesMp.idUbicacion}_%{#infomp.registroEntrada}" value="0" cssStyle="width: 90px; text-align: right;"/>
									</td>
								</tr>
							</s:iterator>
						</s:iterator>
					</tbody>
				</table>
			</fieldset>
		</s:form>
	</div> <!-- end divNecesarioWidget -->
	<p>&nbsp;</p>
</s:i18n>