<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="gestionProcesoEnvasado.bienvenida" />: <s:property value="%{#session.orden}" /><span class="screenCode">GPENVA01</span></h3>
	<div id="divNecesarioWidget" style="text-align: center;">
		<s:hidden id="idProd" name="idProd" key="idProd" />
		<s:hidden id="tipoEan" name="tipoEan" key="tipoEan" />
		<s:hidden id="idProductoSelectValor" name="idProductoSelectValor" key="idProductoSelectValor" value="%{#session.idProductoSelect}" />
		<s:hidden id="agrupar" name="agrupar" key="agrupar" value="%{#session.agrupar}" />
		<div id="botonesEnvasar" style="margin:0 auto 0 auto; width: 500px;">
			<div id="boton_etiqueta_envasado" class="bt_envasar">
				<button id="botEtiqueta" class="botonGestionEnvasado" onClick="javascript:parent.get_ventana_emergente('EN','EtiqENJR.action?orden=<s:property value="%{#session.orden}" />','no','no','590','420','','');" type="button" name="botonEtiqueta">
					<h2><s:text name="gestionProcesoEnvasado.botonEtiqueta" /></h2>
				</button>
			</div>
			<div id="boton_continuar_envasado" class="bt_envasar">
				<button id="botContinuar" class="botonGestionEnvasado" onclick="javascript:continuarEnvasado('<s:property value="%{#session.orden}" />', '<s:property value="%{#session.estado}" />');" type="button" name="botonContinuar">
					<h2><s:text name="gestionProcesoEnvasado.botonContinuar" /></h2>
				</button>
			</div>
			<div id="boton_pausar_envasado" class="bt_envasar">
				<button id="botPausar" class="botonGestionEnvasado" onclick="javascript:observacion('P', '<s:property value="%{#session.orden}" />')" type="button" name="botonPausar">
					<h2><s:text name="gestionProcesoEnvasado.botonParar" /></h2>
				</button>
			</div>
			<div id="boton_finalizar_envasado" class="bt_envasar">
				<button id="botFinalizar" class="botonGestionEnvasado" onclick="javascript:observacion('F', '<s:property value="%{#session.orden}" />')" type="button" name="botonFinalizar">
					<h2><s:text name="gestionProcesoEnvasado.botonFinalizar" /></h2>
				</button>
			</div>
			<div id="boton_anular_envasado" class="bt_envasar">
				<button id="botAnular" class="botonGestionEnvasado" onclick="javascript:anularEnvasado('<s:property value="%{#session.orden}" />')" type="button" name="botonAnular">
					<h2>Anular</h2>
				</button>
			</div>
		</div><!-- end #botonesEnvasar -->
		<fieldset><!-- Filtro Campos-->
			<div id="infoProceso">
				<div style="position:relative; float:left;" >
					<table class="tabla" border="1" width="100%">
						<thead>
							<tr>
								<th style="font-size: 17px; width: 550px; max-width: 550px;" >Producto</th>
								<th style="font-size: 17px;" >Lote</th>
								<th style="font-size: 17px; width: 78px; text-align: center;" >Envasar</th>
								<th style="font-size: 17px; width: 78px; text-align: center;" >Envasado</th>
								<th id="th_eans14Elaborados" style="font-size: 17px; width: 78px; text-align: center; display: none;" >Agrupaciones</th>
								<th style="font-size: 17px; text-align: right;" >Estado</th>
							</tr>
						</thead>
						<tbody>
							<tr style="height: 28px;">
								<td style="font-size: 20px; padding:18px;"><s:property value="%{#session.nombreProducto}" /></td>
								<td id="loteProceso" style="font-size: 20px; width: 130px;"><s:property value="%{#session.lote}" /></td>
								<td id="cantidadTotal" style=" padding:18px; font-size: 20px;"><s:property value="#session.envasar" /></td>
								<td id="cantidadEnvasada" style=" padding:18px; font-size: 20px;"><s:property value="#session.envasado" /></td>
								<td id="td_cantidadEnvasadaAgrupar" style=" padding:18px; font-size: 20px; display: none;"><s:property value="#session.agrupacionesEnvasadas" /></td>
								<td style="font-size: 20px; text-align: right;"><s:property value="%{#session.estado}" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<s:form id="gestionaEnvasado" name="gestionaEnvasado" action="" validate="true" method="get">
					<s:hidden id="idProducto" name="idProducto" key="idProducto" value="%{#session.idProducto}" />
					<table id="electedIngredientsTable" class="tabla" border="1" width="100%">
						<thead>
							<tr>
								<th class="destaca" rowspan="2" style="width: 100px;">INGREDIENTE</th>
								<th class="destaca" rowspan="2" style="width: 100px;">UBICACION</th>
								<th class="destaca" colspan="4">CANTIDAD UTILIZADA (KG.)</th>
								<th class="destaca" rowspan="2" style="width: 100px;">PROVEEDOR</th>
								<th class="destaca" rowspan="2" style="width: 120px;">LOTE/ALBARAN</th>
							</tr>
							<tr>
								<th style="width: 65px;">Disponible</th>
								<th style="width: 65px;">Real</th>
								<th style="width: 65px;">Sobrante</th>
								<th style="width: 65px;">Mermas</th>
							</tr>
						</thead>
						<tbody id="electedIngredientsBody">
							<s:iterator id="infomp" value="%{#session.ingredientes}">
								<s:if test="%{#infomp.idLinea == #infomp.idAnterior}">
								</s:if>
								<s:else>
									<tr id="escogerIngredientesNuevo<s:property value='idLinea' />">
										<td class="verde" colspan="8" style="text-align:center; background-color: #CEF6CE !important;" >INGREDIENTE: <s:property value="nombre" /> - <s:property value="nombreCategoria" /></td>
										</tr>
								</s:else>
								<tr id="<s:property value='registroEntrada' />" class="ingrediente">
									<td class="entradaMateria"><s:property value="registroEntrada" /></td>
									<td id="ubicaMP_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />"><s:property value="refUbicacion" /></td>
									<td id="cantidadMP_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />" style="text-align: right;">
										<s:property value="cantidadUbicacion" />
									</td>
									<td id="realMP<s:property value="registroEntrada" />_<s:property value="idPalet" />">
										<s:textfield id="gestionaEnvasado_real_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}" cssStyle="text-align:right;font-size:20px;width:60px;" value="0" cssClass="inputRellenarMP" name="real_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}" onblur="javascript:ajustarDecimal('gestionaEnvasado_real_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}');" onkeypress="return validarNumerosDecimales('gestionaEnvasado_real_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}',event);" onkeyup="javascript:sobranteModificado('%{#infomp.registroEntrada}', '%{#infomp.idUbicacion}','%{#infomp.idPalet}','mp', '0');" />
									</td>
									<td id="sobranteMP_<s:property value="registroEntrada" />">
										<s:textfield id="gestionaEnvasado_sobrante_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}" cssStyle="text-align:right; font-size:20px;width:60px;" value="0" name="sobrante_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}" onblur="javascript:ajustarDecimal('gestionaEnvasado_sobrante_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}');" onkeypress="return validarNumerosDecimales('gestionaEnvasado_sobrante_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}',event);" onkeyup="javascript:sobranteModificado('%{#infomp.registroEntrada}', '%{#infomp.idUbicacion}','%{#infomp.idPalet}','mp','0');" cssClass="inputRellenarMP"/>
									</td>
									<td id="mermasMP_<s:property value="registroEntrada" />">
										<s:textfield id="gestionaEnvasado_mermas_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}" cssStyle="text-align:right; font-size:20px;width:60px;" cssClass="inputRellenarMP" value="0" name="mermas_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}" onblur="javascript:ajustarDecimal('gestionaEnvasado_mermas_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}');" onkeypress="return validarNumerosDecimales('gestionaEnvasado_mermas_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}',event);" onkeyup="javascript:sobranteModificado('%{#infomp.registroEntrada}', '%{#infomp.idUbicacion}','%{#infomp.idPalet}','mp', '0');"/>
									</td>
									<td id="proveedorMP_<s:property value="registroEntrada" />"><s:property value="proveedor" /></td>
									<td id="loteMP_<s:property value="registroEntrada" />"><s:property value="lote" /></td>
									<td id="fechaMP_<s:property value="registroEntrada" />" style="display:none;"><s:property value="fecha" /></td>	
									<td id="fechaCaducidadMP_<s:property value="registroEntrada" />" style="display:none;"><s:property value="fechaCaducidad" /></td>
									<td id="nombreMP_<s:property value="registroEntrada" />" style="display:none;"><s:property value="nombre" /></td>
									<td id="idMP_<s:property value="registroEntrada" />" style="display:none;"><s:property value="idProveedor" /></td>
									<s:hidden id="teoricaMP_%{#infomp.registroEntrada}" name="teoricaMP_%{#infomp.teorica}" value="%{#infomp.teorica}"/>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<table id="electedEnvasesTable" class="tabla" border="1" width="100%">
						<thead>					
							<tr>
								<th class="destaca" rowspan="2" style="width: 100px;">ENVASE</th>
								<th class="destaca" rowspan="2" style="width: 100px;">UBICACION</th>
								<th class="destaca" colspan="4">CANTIDAD UTILIZADA (Uds.)</th>
								<th class="destaca" rowspan="2" style="width: 100px;">PROVEEDOR</th>
								<th class="destaca" rowspan="2" style="width: 120px;">LOTE/ALBARAN</th>
							</tr>
							<tr>
								<th style="width: 65px;">Disponible</th>
								<th style="width: 65px;">Real</th>
								<th style="width: 65px;">Sobrante</th>
								<th style="width: 65px;">Mermas</th>
							</tr>
						</thead>
						<tbody id="electedEnvasesBody" >
							<s:iterator id="infoen" value="%{#session.envases}">
								<s:if test="%{#infoen.idLinea == #infoen.idAnterior}">
								</s:if>
								<s:else>
									<tr id="escogerEnvasesNuevo<s:property value='idLinea' />">
										<td colspan="8" style="text-align:center; background-color: #CEF6CE !important;" >ENVASE: <s:property value="nombre" /></td>
									</tr>
								</s:else>
								<tr id="<s:property value='registroEntrada' />" class="envase">
									<td class="entradaEnvase"><s:property value="registroEntrada"/></td>
									<td id="ubicaEN_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />"><s:property value="refUbicacion" /></td>
									<td id="cantidadEN_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />" style="text-align:right;">
										<s:property value="teorica" />
									</td>
									<td id="realEN<s:property value="registroEntrada" />">
										<s:textfield id="gestionaEnvasado_real_en_%{#infoen.registroEntrada}_%{#infoen.idUbicacion}_%{#infoen.idPalet}" cssStyle="text-align:right; font-size:20px;width:60px;" value="0" cssClass="inputRellenarEN" name="real_en_%{#infoen.registroEntrada}_%{#infoen.idUbicacion}_%{#infoen.idPalet}" onkeypress="return validarSoloNumeros(event);" onkeyup="javascript:sobranteModificado('%{#infoen.registroEntrada}', '%{#infoen.idUbicacion}','%{#infoen.idPalet}','en','0');" />
									</td>
									<td id="sobranteEN_<s:property value="registroEntrada" />">
										<s:textfield id="gestionaEnvasado_sobrante_en_%{#infoen.registroEntrada}_%{#infoen.idUbicacion}_%{#infoen.idPalet}" cssStyle="text-align:right; font-size:20px;width:60px;" value="0" name="sobrante_en_%{#infoen.registroEntrada}_%{#infoen.idUbicacion}_%{#infoen.idPalet}" onkeypress="return validarSoloNumeros(event);" cssClass="inputRellenarEN" onkeyup="javascript:sobranteModificado('%{#infoen.registroEntrada}', '%{#infoen.idUbicacion}','%{#infoen.idPalet}','en','0');" />
									</td>
									<td id="mermasEN_<s:property value="registroEntrada" />">
										<s:textfield id="gestionaEnvasado_mermas_en_%{#infoen.registroEntrada}_%{#infoen.idUbicacion}_%{#infoen.idPalet}" cssStyle="text-align:right; font-size:20px;width:60px;" cssClass="inputRellenarEN" value="0" name="mermas_en_%{#infoen.registroEntrada}_%{#infoen.idUbicacion}_%{#infoen.idPalet}" onkeypress="return validarSoloNumeros(event);" onkeyup="javascript:sobranteModificado('%{#infoen.registroEntrada}', '%{#infoen.idUbicacion}','%{#infoen.idPalet}','en','0');" />
									</td>
									<td id="proveedorEN_<s:property value="registroEntrada" />"><s:property value="proveedor" /></td>
									<td id="loteEN_<s:property value="registroEntrada" />"><s:property value="lote" /></td>
									<td id="fechaEN_<s:property value="registroEntrada" />" style="display:none;"><s:property value="fecha" /></td>	
									<td id="fechaCaducidadEN_<s:property value="registroEntrada" />" style="display:none;"><s:property value="fechaCaducidad" /></td>
									<td id="nombreEN_<s:property value="registroEntrada" />" style="display:none;"><s:property value="nombre" /></td>
									<td id="idEN_<s:property value="registroEntrada" />" style="display:none;"><s:property value="idProveedor" /></td>
									<s:hidden id="teoricaEN_%{#infoen.registroEntrada}" name="teoricaEN_%{#infoen.teorica}" value="%{#infoen.teorica}"/>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div id="div_observaciones">
						<s:hidden key="orden" id="orden" name="orden" value="%{#session.orden}"/>
						<s:hidden id="estado" name="estado" value="%{#session.estado}"/>
						<s:hidden id="observaciones" name="observaciones" value=""/>
						<s:hidden id="idEnvasado" name="idEnvasado" value="%{#session.idEnvasado}"/>
						<s:hidden id="editar" name="editar" value="%{#session.editar}"/>
						<s:hidden id="envasado" name="envasado" value="%{#session.envasado}"/>
						<s:hidden id="envasadoAgrupar" name="envasadoAgrupar" value="%{#session.agrupacionesEnvasadas}"/>
						<s:hidden id="elaborado" name="elaborado" value="0"/>
						<s:hidden id="elaboradoAgrupar" name="elaborado" value="0"/>
						<s:hidden id="estadoViejo" name="estadoViejo" value="%{#session.estadoViejo}"/>
						<s:hidden id="loteAsignado" name="loteAsignado" value="%{#session.lote}"/>
					</div>
				</s:form>
				<div id="explicacion">
					<p><s:text name="envasado.observaciones" /></p>
					<textarea id="observa" name="observa" type="text" style="font-size:19px; height: 53px; width: 99%;"><s:property value="#session.observaciones" /></textarea>
					&nbsp;
				</div>
			</div><!-- end #dashboard -->
		</fieldset>
	</div>
</s:i18n>