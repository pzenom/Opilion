<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="gestionProcesoEnvasado.bienvenida" />: <s:property value="#session.orden" /><span class="screenCode">GESENVA01</span></h3>
	<div id="divNecesarioWidget">
		<s:hidden id="visualizando" name="visualizando" key="visualizando" value="true" />
		<s:hidden id="idProd" name="idProd" key="idProd" />
		<s:hidden id="tipoEan" name="tipoEan" key="tipoEan" />
		<s:hidden id="idProductoSelectValor" name="idProductoSelectValor" key="idProductoSelectValor" value="%{#session.idProductoSelect}"/>
		<s:hidden id="agrupar" name="agrupar" key="agrupar" value="%{#session.agrupar}" />
		<div id="botonesEnvasar" style="margin:0 auto 0 auto; width: 125px;">
			<div id="boton_etiqueta_envasado" class="bt_envasar">
				<button id="botEtiqueta" class="botonGestionEnvasado" onClick="javascript:parent.get_ventana_emergente('EN','EtiqENJR.action?orden=<s:property value="%{#session.orden}" />','no','no','590','420','','');" type="button" name="botonEtiqueta">
					<h2><s:text name="gestionProcesoEnvasado.botonEtiqueta" /></h2>
				</button>
			</div>
		</div><!-- end #botonesEnvasar -->
		<fieldset><!-- Filtro Campos-->	
			<div id="infoProceso">
				<div style="position:relative; float:left;" >
					<table class="tabla" >
						<thead>
							<tr>
								<th style="font-size: 17px; width: 550px; max-width: 550px;" >Producto</th>
								<th style="font-size: 17px;">Lote</th>
								<th style="font-size: 17px;"><s:text name="gestionProcesoEnvasado.unidadesPorEnvasar" /></th>
								<th style="font-size: 17px;"><s:text name="gestionProcesoEnvasado.unidadesEnvasadas" /></th>
								<th id="th_eans14Elaborados" style="font-size: 17px; width: 78px; text-align: center; display: none;" >EANs14 elaborados</th>
								<th style="font-size: 17px;">Estado</th>
							</tr>
						</thead>
						<tbody>
							<tr style="height: 28px;">
								<td style="font-size: 20px; padding:18px;"><s:property value="%{#session.nombreProducto}" /></td>
								<td style="font-size: 20px;"><s:property value="#session.lote" /></td>
								<td id="cantidadTotal" style="font-size: 20px;"><s:property value="#session.envasar" /></td>
								<td style="font-size: 20px;"><s:property value="%{#session.envasado}" /></td>
								<td id="td_cantidadEnvasadaAgrupar" style=" padding:18px; font-size: 20px; display: none;"><s:property value="#session.agrupacionesEnvasadas" /></td>
								<td style="font-size: 20px;"><s:property value="#session.estado" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<s:form id="gestionaEnvasado" name="gestionaEnvasado" action="" validate="true">
					<table id="electedIngredientsTable" class="tabla" border="1" width="100%">
						<thead>
							<tr>
								<th class="destaca" rowspan="2" style="width: 100px;">INGREDIENTE</th>
								<th class="destaca" rowspan="2" style="width: 100px;">UBICACION</th>
								<th class="destaca" colspan="3">CANTIDAD UTILIZADA (KG.)</th>
								<th class="destaca" rowspan="2" style="width: 100px;">PROVEEDOR</th>
								<th class="destaca" rowspan="2" style="width: 120px;">LOTE/ALBARAN</th>
								<th id="encabezadoDevolverMP" class="destaca" rowspan="2" style="width: 170px; visibility: hidden;">UBICACION DEVOLVER</th>
							</tr>
							<tr>
								<th style="width: 65px;">Real</th>
								<th style="width: 65px;">Mermas</th>
								<th style="width: 65px;">Devolver</th>
							</tr>
						</thead>
						<tbody id="electedIngredientsBody">
							<s:iterator id="infomp" value="%{#session.ingredientes}">
								<s:if test="%{#infomp.idLinea == #infomp.idAnterior}">
								</s:if>
								<s:else>
									<tr id="escogerIngredientesNuevo<s:property value='idLinea' />">
										<td class="verde" colspan="9" style="text-align:center; background-color: #CEF6CE !important;" >INGREDIENTE: <s:property value="nombre" /> - <s:property value="nombreCategoria" /></td>
									</tr>
								</s:else>
								<tr id="<s:property value='registroEntrada' />_<s:property value="idUbicacion" />_<s:property value="idPalet" />" class="ingrediente">
									<td class="entradaMateria"><s:property value="registroEntrada" /></td>
									<td id="ubicaMP_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />"><s:property value="refUbicacion" /></td>
									<td id="cantidadMP_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />" style="text-align: right; display: none;">
										<s:property value="cantidadUbicacion" />
									</td>
									<td id="realMP<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />" style="width: 100 px;" onkeyup="javascript:checkCantidadIngrediente('<s:property value="registroEntrada" />')">
										<s:textfield cssStyle="text-align:right;font-size:20px;width:60px;" value="%{real}" cssClass="inputRellenar" name="real_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}" disabled="true" />
									</td>
									<td id="mermasMP_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />">
										<s:textfield cssStyle="text-align:right; font-size:20px;width:60px;" cssClass="inputRellenar" value="%{mermas}" name="mermas_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}" disabled="true" />
									</td>
									<td id="sobra_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />">
										<s:textfield cssStyle="text-align:right; font-size:20px;width:60px;" cssClass="inputRellenar" name="sobra_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}" id="sobra_mp_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}" disabled="true" />
									</td>
									<td id="proveedorMP_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />">
										<s:property value="proveedor" />
									</td>
									<td id="loteMP_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />">
										<s:property value="lote" />
									</td>
									<td id="fechaMP_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />" style="display:none;">
										<s:property value="fecha" />
									</td>	
									<td id="fechaCaducidadMP_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />" style="display:none;">
										<s:property value="fechaCaducidad" />
									</td>
									<td id="nombreMP_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />" style="display:none;">
										<s:property value="nombre" />
									</td>
									<td id="idMP_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />" style="display:none;">
										<s:property value="idProveedor" />
									</td>
									<td class="ubicacionesDevolver" id="ubicacionDevolver_<s:property value="registroEntrada"/>_<s:property value="idUbicacion" />_<s:property value="idPalet" />" style="visibility: hidden;">
										<s:textfield cssClass="inputReubicarMP" cssStyle="text-align:right; font-size:20px; width: 94%;" onclick = "javascript:reubicar('%{#infomp.registroEntrada}', 'MP', '%{#infomp.idPalet}', '%{#infomp.idUbicacion}');" value="%{#infomp.refUbicacionNueva}" name="reubicar_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}" id="reubicar_%{#infomp.registroEntrada}_%{#infomp.idUbicacion}_%{#infomp.idPalet}"/>
									</td>
									<s:hidden id="teoricaMP_%{#infomp.registroEntrada}" name="teoricaMP_%{#infomp.teorica}" value="%{#infomp.teorica}"/>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<table id="electedEnvasesTable" class="tabla" border="1" width="100%">
						<thead>
							<tr>
								<th class="destaca" rowspan="2" style="width: 100px;">INGREDIENTE</th>
								<th class="destaca" rowspan="2" style="width: 100px;">UBICACION</th>
								<th class="destaca" colspan="3">CANTIDAD UTILIZADA (Uds.)</th>
								<th class="destaca" rowspan="2" style="width: 100px;">PROVEEDOR</th>
								<th class="destaca" rowspan="2" style="width: 120px;">LOTE/ALBARAN</th>
								<th id="encabezadoDevolverEN" class="destaca" rowspan="2" style="width: 170px; visibility: hidden;">UBICACION DEVOLVER</th>
							</tr>
							<tr>
								<th style="width: 65px;">Real</th>
								<th style="width: 65px;">Mermas</th>
								<th style="width: 65px;">Devolver</th>
							</tr>
						</thead>
						<tbody id="electedEnvasesBody" >
							<s:iterator id="infoen" value="%{#session.envases}">
								<s:if test="%{#infoen.idLinea == #infoen.idAnterior}">
								</s:if>
								<s:else>
									<tr id="escogerIngredientesNuevo<s:property value='idLinea' />">
										<td colspan="9" style="text-align:center; background-color: #CEF6CE !important;" >ENVASE: <s:property value="nombre" /></td>
								</tr>
								</s:else>
								<tr id="<s:property value='registroEntrada' />" class="envase">
									<td><s:property value="registroEntrada" /></td>
									<td id="ubicaEN_<s:property value="registroEntrada" />"><s:property value="refUbicacion" /></td>
									<td id="cantidadEN_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />" style="text-align: right; display: none;"><s:property value="cantidadUbicacion" /></td>
									<td id="realEN<s:property value="registroEntrada" />" onkeyup="javascript:checkCantidadIngrediente('<s:property value="registroEntrada" />')"><s:textfield cssStyle="text-align:right;font-size:20px;width:60px;" value="%{real}" cssClass="inputRellenar" name="real_en_%{#infoen.registroEntrada}_%{#infoen.idUbicacion}_%{#infoen.idPalet}" disabled="true" /></td>
									<td id="mermasEN_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />"><s:textfield cssStyle="text-align:right; font-size:20px;width:60px;" cssClass="inputRellenar" value="%{mermas}" name="mermas_en_%{#infoen.registroEntrada}_%{#infoen.idUbicacion}_%{#infoen.idPalet}" disabled="true" /></td>
									<td id="sobra_<s:property value="registroEntrada" />_<s:property value="idUbicacion" />_<s:property value="idPalet" />">
										<s:textfield cssStyle="text-align:right; font-size:20px;width:60px;" cssClass="inputRellenar" value="0" name="sobra_en_%{#infoen.registroEntrada}_%{#infoen.idUbicacion}_%{#infoen.idPalet}" id="sobra_en_%{#infoen.registroEntrada}_%{#infoen.idUbicacion}_%{#infoen.idPalet}" disabled="true" />
									</td>
									<td id="proveedorEN_<s:property value="registroEntrada" />"><s:property value="proveedor" /></td>
									<td id="loteEN_<s:property value="registroEntrada" />"><s:property value="lote" /></td>
									<td id="fechaEN_<s:property value="registroEntrada" />" style="display:none;"><s:property value="fecha" /></td>	
									<td id="fechaCaducidadEN_<s:property value="registroEntrada" />" style="display:none;"><s:property value="fechaCaducidad" /></td>
									<td id="nombreEN_<s:property value="registroEntrada" />" style="display:none;"><s:property value="nombre" /></td>
									<td id="idEN_<s:property value="registroEntrada" />" style="display:none;"><s:property value="idProveedor" /></td>
									<td class="ubicacionesDevolver" id="ubicacionDevolver_<s:property value="registroEntrada"/>" style="visibility: hidden;">
										<s:textfield cssClass="inputReubicarEN" cssStyle="text-align:right; font-size:20px; width: 94%;" onclick = "javascript:reubicar('%{#infoen.registroEntrada}', 'EN', '%{#infoen.idPalet}', '%{#infoen.idUbicacion}');" value="%{#infoen.refUbicacionNueva}" name="reubicar_%{#infoen.registroEntrada}_%{#infoen.idUbicacion}_%{#infoen.idPalet}" id="reubicar_%{#infoen.registroEntrada}_%{#infoen.idUbicacion}_%{#infoen.idPalet}"/>
									</td>
									<s:hidden id="teoricaEN_%{#infoen.registroEntrada}" name="teoricaEN_%{#infoen.teorica}" value="%{#infoen.teorica}"/>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div id="div_observaciones">
						<s:hidden key="orden" id="orden" name="orden" value="%{#session.orden}"/>
						<s:hidden id="estado" name="estado" value="%{#session.estado}"/>
						<s:hidden id="lote" name="lote" value="%{#session.lote}"/>
						<s:hidden id="observaciones" name="observaciones" value=""/>
						<s:hidden id="idEnvasado" name="idEnvasado" value="%{#session.idEnvasado}"/>
						<s:hidden id="editar" name="editar" value="%{#session.editar}"/>
					</div>
				</s:form>
				<div id="observaciones">
					<p><s:text name="envasado.observaciones" /></p>
					<textarea id="explica" type="text" style="height: 70px; width: 60%;" disabled="disabled" name="explica"><s:property value="#session.observaciones" /></textarea><br>
					&nbsp;
				</div>
			</div> <!-- end infoproceso -->
		</fieldset>
	</div> <!-- end necesarioWidget -->
</s:i18n>