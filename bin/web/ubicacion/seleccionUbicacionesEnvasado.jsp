<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Sacar materias primas y envases para el proceso de envasado<span class="screenCode">SELE_UBICA_ENVA_EAN13</span></h3>
	<div id="divNecesarioWidget">
		<s:hidden id="idProd" name="idProd" key="idProd" />
		<s:hidden id="tipoEan" name="tipoEan" key="tipoEan" />
		<s:hidden id="idProductoSelectValor" name="idProductoSelectValor" key="idProductoSelectValor" value="%{#session.idProductoSelect}"/>
		<fieldset><!-- Filtro Campos-->
			<div id="infoProceso">
				<div style="position:relative; float:left;" >
					<table class="tabla" >
						<thead>
							<tr>
								<th style="font-size: 17px; text-align: left;" >Orden</th>
								<th style="font-size: 17px; width: 380px;" >Producto</th>
								<th style="font-size: 17px;" >Lote</th>
								<th style="font-size: 17px; width: 78px; text-align: center;" >Unidades</th>
								<th style="font-size: 17px; text-align: right;" >Estado</th>
							</tr>
						</thead>
						<tbody>
							<tr style="height: 28px;">
								<td style="font-size: 20px; padding:18px; text-align: left;"><s:property value="%{#session.orden}" /></td>
								<td style="font-size: 20px; padding:18px;"><s:property value="%{#session.nombreProducto}" /></td>
								<td style="font-size: 20px; width: 130px;"><s:property value="%{#session.lote}" /></td>
								<td id="cantidadTotal" style=" padding:18px; font-size: 20px;"><s:property value="%{#session.envasar}" /></td>
								<td style="font-size: 20px; text-align: right;"><s:property value="%{#session.estado}" /></td>
							</tr>
						</tbody>
					</table>
					<s:hidden id="loteAsignado" value="%{#session.lote}" />
				</div>
				<hr style="float: left; width: 100%;"/>
				<table id="electedIngredientsTable" class="tabla" border="1" width="100%">
					<caption>Materias primas</caption>
					<thead>
						<tr>
							<th class="destaca" style="width: 80px;">REG. ENTRADA</th>
							<th class="destaca">CANTIDAD (Kilos)</th>
							<th class="destaca">PROVEEDOR</th>
							<th class="destaca">FECHA ENTRADA</th>
						</tr>
					</thead>
					<tbody id="electedIngredientsBody">
						<s:iterator id="infomp" value="%{#session.ingredientes}">
							<s:if test="%{#infomp.idLinea == #infomp.idAnterior}">
							</s:if>
							<s:else>
								<tr id="escogerIngredientesNuevo<s:property value='idLinea' />">
									<td colspan="4" style="text-align:center; background-color: #CEF6CE !important;" >INGREDIENTE: <s:property value="nombre" /> - <s:property value="nombreCategoria" /></td>
									</tr>
							</s:else>
							<tr id="<s:property value='registroEntrada' />" class="ingrediente" style="font-size:14px;">
								<td class="entradaMateria" style="background-color: yellow;"><s:property value="registroEntrada"/></td>
								<td id="cantidadMP_<s:property value="registroEntrada" />" style="color: red;">
									<s:property value="teorica" />
								</td>
								<td><s:property value="proveedor"/></td>
								<td><s:property value="fecha"/></td>
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
								<s:if test="%{#status.count == 1}">
									<tr style="font-size:14px;" id="<s:property value="registroEntrada" />_<s:property value="idPalet" />_<s:property value="idHueco" />" class="<s:property value="registroEntrada" /> ubicacion_1" >
								</s:if>
								<s:else>
									<tr style="font-size:14px; display: none;" id="<s:property value="registroEntrada" />_<s:property value="idPalet" />_<s:property value="idHueco" />" class="<s:property value="registroEntrada" /> ubicacion_<s:property value="idPalet" />_<s:property value="idHueco" />">
								</s:else>
									<s:if test="%{#status.count == 1}">
										<td id="botonIngrediente_<s:property value="registroEntrada" />" style="text-align: center; background: transparent;">
											<button id="botonMasUbicaciones_<s:property value="registroEntrada" />" class="small blue" onClick="javascript:muestraMasUbicaciones('<s:property value="registroEntrada" />');" style="width: 100%;">M&aacute;s ubicaciones</button>
										</td>
									</s:if>
									<s:else>
										<td style="background: transparent;"></td>
									</s:else>
									<td style="width: 160px;"><s:property value="nombreHueco" /></td>
									<td style="width: 180px;"><s:property value="nombreZona" /></td>
									<td style="width: 200px;"><s:property value="nombreLinea" /></td>
									<td id="enHueco_<s:property value="registroEntrada" />_<s:property value="idPalet" />_<s:property value="idHueco" />" class="hueco" style="text-align: right; width: 90px;">
										<s:property value="cantidad" />
									</td>
									<td id="sacar_<s:property value="registroEntrada" />_<s:property value="idPalet" />_<s:property value="idHueco" />" style="text-align: right;">
										<s:if test="%{#status.count == 1}">
											<s:textfield id="aSacar_%{#infomp.registroEntrada}_%{#ubicacionesMp.idPalet}_%{#ubicacionesMp.idHueco}" cssClass="sacar sacar_%{#infomp.registroEntrada}" key="sacar_%{#ubicacionesMp.idHueco}_%{#ubicacionesMp.idUbicacion}_%{#infomp.registroEntrada}_%{#ubicacionesMp.idPalet}_%{#ubicacionesMp.idHueco}" value="%{#infomp.teorica}" cssStyle="width: 90px; text-align: right;" onblur="javascript:ajustarDecimal('aSacar_%{#infomp.registroEntrada}_%{#ubicacionesMp.idPalet}_%{#ubicacionesMp.idHueco}');" onkeypress="return validarNumerosDecimales('%{#infomp.registroEntrada}_%{#ubicacionesMp.idPalet}_%{#ubicacionesMp.idHueco}', event);" onchange="javascript:cantidadSacarActualiza('%{#infomp.registroEntrada}_%{#ubicacionesMp.idPalet}_%{#ubicacionesMp.idHueco}', true);" />
										</s:if>
										<s:else>
											<s:textfield id="aSacar_%{#infomp.registroEntrada}_%{#ubicacionesMp.idPalet}_%{#ubicacionesMp.idHueco}" cssClass="sacar sacar_%{#infomp.registroEntrada}" key="sacar_%{#ubicacionesMp.idHueco}_%{#ubicacionesMp.idUbicacion}_%{#infomp.registroEntrada}_%{#ubicacionesMp.idPalet}_%{#ubicacionesMp.idHueco}" value="0" cssStyle="width: 90px; text-align: right;" onblur="javascript:ajustarDecimal('aSacar_%{#infomp.registroEntrada}_%{#ubicacionesMp.idPalet}_%{#ubicacionesMp.idHueco}');" onkeypress="return validarNumerosDecimales('%{#infomp.registroEntrada}_%{#ubicacionesMp.idPalet}_%{#ubicacionesMp.idHueco}', event);" onchange="javascript:cantidadSacarActualiza('%{#infomp.registroEntrada}_%{#ubicacionesMp.idPalet}_%{#ubicacionesMp.idHueco}', true);" />
										</s:else>
									</td>
								</tr>
							</s:iterator>
						</s:iterator>
					</tbody>
				</table>
				<hr />
				<table id="electedEnvasesTable" class="tabla" border="1" width="100%">
					<caption>Envases</caption>
					<thead>
						<tr>
							<th class="destaca" style="width: 80px;">REG. ENTRADA</th>
							<th class="destaca">CANTIDAD (Unidades)</th>
							<th class="destaca">PROVEEDOR</th>
							<th class="destaca">FECHA ENTRADA</th>
						</tr>
					</thead>
					<tbody id="electedEnvasesBody" >
						<s:iterator id="infoen" value="%{#session.envases}">
							<s:if test="%{#infoen.idLinea == #infoen.idAnterior}">
							</s:if>
							<s:else>
								<tr id="escogerEnvasesNuevo<s:property value='idLinea' />">
									<td colspan="4" style="text-align:center; background-color: #CEF6CE !important;" >ENVASE: <s:property value="nombre" /></td>
								</tr>
							</s:else>
							<tr id="<s:property value='registroEntrada' />" class="envase" style="font-size:14px;">
								<td id="entradaEnvase_<s:property value='registroEntrada' />" class="entradaEnvase" style="background-color: yellow;"><s:property value="registroEntrada"/></td>
								<td id="cantidadEN_<s:property value="registroEntrada" />" style="color: red;">
									<s:property value="teorica" />
								</td>
								<td><s:property value="proveedor"/></td>
								<td><s:property value="fecha"/></td>
							</tr>
							<tr>
								<td><b>UBICACIONES</b></td>
								<td>Referencia del hueco</td>
								<td>Zona</td>
								<td>Linea</td>
								<td style="text-align: right;">Almacenado</td>
								<td style="text-align: right;">Cantidad a sacar</td>
							</tr>
							<s:iterator id="ubicacionesEn" value="%{#infoen.ubicaciones}" status="status">
								<s:if test="%{#status.count == 1}">
									<tr style="font-size:14px;" id="<s:property value="registroEntrada" />_<s:property value="idPalet" />_<s:property value="idHueco" />" class="<s:property value="registroEntrada" /> ubicacion_1">
								</s:if>
								<s:else>
									<tr style="font-size:14px; display: none;" id="<s:property value="registroEntrada" />_<s:property value="idPalet" />_<s:property value="idHueco" />" class="<s:property value="registroEntrada"/> ubicacion_<s:property value="idPalet" />_<s:property value="idHueco" />">
								</s:else>
									<s:if test="%{#status.count == 1}">
										<td id="botonEnvase_<s:property value="registroEntrada" />" style="text-align: center; background: transparent;">
											<button id="botonMasUbicaciones_<s:property value="registroEntrada" />" class="small blue" onClick="javascript:muestraMasUbicaciones('<s:property value="registroEntrada" />');" style="width: 100%;">M&aacute;s ubicaciones</button>
										</td>
									</s:if>
									<s:else>
										<td style="background: transparent;"></td>
									</s:else>
									<td style="width: 160px;"><s:property value="nombreHueco" /></td>
									<td style="width: 180px;"><s:property value="nombreZona" /></td>
									<td style="width: 200px;"><s:property value="nombreLinea" /></td>
									<td id="enHueco_<s:property value="registroEntrada" />_<s:property value="idPalet" />_<s:property value="idHueco" />" class="hueco" style="text-align: right; width: 90px;"><s:property value="cantidad" /></td>
									<td id="sacar_<s:property value="registroEntrada" />_<s:property value="idPalet" />_<s:property value="idHueco" />" class="ubicacion" style="text-align: right;">
										<s:if test="%{#status.count ==1}">
											<s:textfield id="aSacar_%{#infoen.registroEntrada}_%{#ubicacionesEn.idPalet}_%{#ubicacionesEn.idHueco}" cssClass="sacar sacar_%{#infoen.registroEntrada}" key="sacar_%{#ubicacionesEn.idHueco}_%{#ubicacionesEn.idUbicacion}_%{#infoen.registroEntrada}_%{#ubicacionesEn.idPalet}_%{#ubicacionesEn.idHueco}" value="%{#infoen.teorica}" cssStyle="width: 90px; text-align: right;" onblur="javascript:ajustarDecimal('aSacar_%{#infoen.registroEntrada}_%{#ubicacionesEn.idPalet}_%{#ubicacionesEn.idHueco}');" onkeypress="return validarSoloNumeros(event);" onchange="javascript:cantidadSacarActualiza('%{#infoen.registroEntrada}_%{#ubicacionesEn.idPalet}_%{#ubicacionesEn.idHueco}', true);" />
										</s:if>
										<s:else>
											<s:textfield id="aSacar_%{#infoen.registroEntrada}_%{#ubicacionesEn.idPalet}_%{#ubicacionesEn.idHueco}" cssClass="sacar sacar_%{#infoen.registroEntrada}" key="sacar_%{#ubicacionesEn.idHueco}_%{#ubicacionesEn.idUbicacion}_%{#infoen.registroEntrada}_%{#ubicacionesEn.idPalet}_%{#ubicacionesEn.idHueco}" value="0" cssStyle="width: 90px; text-align: right;" onblur="javascript:ajustarDecimal('aSacar_%{#infoen.registroEntrada}_%{#ubicacionesEn.idPalet}_%{#ubicacionesEn.idHueco}');" onkeypress="return validarSoloNumeros(event);" onchange="javascript:cantidadSacarActualiza('%{#infoen.registroEntrada}_%{#ubicacionesEn.idPalet}_%{#ubicacionesEn.idHueco}', true);" />
										</s:else>
									</td>
								</tr>
							</s:iterator>
						</s:iterator>
					</tbody>
				</table>
				<s:hidden key="orden" id="orden" name="orden" value="%{#session.orden}"/>
			</div> <!-- info proceso -->
			<br />
			<br />
		</fieldset>
	</div><!-- end #dashboard -->
</s:i18n>