<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<div id="divNecesarioWidget">
		<s:hidden id="stockEnvaseAgrupar" name="stockEnvaseAgrupar" key="stockEnvaseAgrupar" value="%{#session.stockEnvaseAgrupar}"/>
		<s:hidden id="idProd" name="idProd" key="idProd" />
		<s:hidden id="tipoEan" name="tipoEan" key="tipoEan" />
		<s:hidden id="idProductoSelectValor" name="idProductoSelectValor" key="idProductoSelectValor" value="%{#session.idProductoSelect}"/>
		<fieldset class="fieldsetEnvasado" style="display: none;"><!-- Filtro Campos-->
			<label>Materias primas<button id="bot_mostrarMaterias" class="small blue" onClick="javascript:mostrarIngredientes();">Ocultar</button></label>
			<div id="todoIngredientes" style="display: block;">
				<div id="electedIngredients">
					<table id="electedIngredientsTable" class="tabla" border="3" width="100%">
						<thead>
							<tr>
								<th style="width: 300px;" class="destaca" rowspan="2"><s:text name="envasado.ingredientes" /></th>
								<th class="destaca" rowspan="2"><s:text name="envasado.codigoEntrada" /></th>
								<th class="destaca" colspan="2">Kilos</th>
								<th class="destaca" rowspan="2"><s:text name="envasado.proveedor" /></th>
								<th class="destaca" rowspan="2"><s:text name="envasado.loteAlbaran" /></th>
							</tr>
							<tr>
								<th style="width: 80px;"><s:text name="envasado.disponible" /></th>
								<th style="width: 80px;"><s:text name="envasado.teorica" /></th>
							</tr>
						</thead>
						<tbody id="electedIngredientsBody" >
							<tr id="filaTotalesIngredientes">
								<td colspan="2" style="visibility: hidden;"></td>
								<td style="background-color: yellow;"><b>TOTAL</b></td>
								<td id="totalIngredientes" style="background-color: yellow;">0</td>
							</tr>
						</tbody>
					</table>
					<div style="text-align:right">
						<button id="bot_ingrediente" class="small blue" onClick="javascript:insertaIngrediente();">M&aacute;s ingredientes</button>
					</div>
				</div><!-- electedIngredients-->
				<div id="escogerIngredientes">
					<fieldset><!-- Filtro Campos-->
						<legend><span><s:text name="envasado.ingredientesDisponibles" /></span></legend>
						<table id="disponibleMP" class="tabla" border="1" width="70%">
							<thead>
								<tr>
									<th><s:text name="envasado.codigoEntrada" /></th>
									<th><s:text name="envasado.fechaEntrada" /></th>
									<th><s:text name="envasado.fechaCaducidad" /></th>
									<th><s:text name="envasado.lote" /></th>
									<th><s:text name="envasado.saldo" /></th>
									<th><s:text name="envasado.proveedor" /></th>
								</tr>
							</thead>
							<tbody id="escogerIngredientesBody">
							<s:iterator id="ingres" value="%{#session.listaregistroingredientes}">
								<s:hidden id="cantidad_%{#ingres.entrada}" name="cantidad_%{#ingres.entrada}" value="%{#ingres.disponible}" />
								<s:hidden id="nombre_%{#ingres.entrada}" value="%{#ingres.nombre} - %{#ingres.nombreCategoria}" />
								<s:if test="%{#ingres.idLinea==#ingres.idAnterior}">
								</s:if>
								<s:else>
									<tr id="escogerIngredientesNuevo<s:property value='idLinea' />">
										<td colspan="6" style="text-align:center; background-color: #CEF6CE !important;" ><s:text name="envasado.ingredienteEscoger"/><s:property value="nombre" /> - <s:property value="nombreCategoria" /></td>
										<td style="display:none;"><s:hidden id="cantidad_%{#ingres.nombre}_%{#ingres.nombreCategoria}" value="%{#ingres.teorica}" /></td>
									</tr>
								</s:else>
								<tr id="escogerIngredientes<s:property value='entrada' />" class="filaIngre">
									<td><s:property value="entrada" /></td>
									<td><s:property value="fecha" /></td>
									<td><s:property value="fechaCaducidad" /></td>
									<td id="loteMP<s:property value='entrada'/>"><s:property value="lote" /></td>
									<td id="saldo_<s:property value='entrada'/>"><s:property value="disponible" /></td>
									<td id="proveedorMP<s:property value='entrada'/>"><s:property value="proveedor" /></td>
									<td>
										<a class="bot_ingredientes" href="javascript:insertaMe('<s:property value="entrada" />')" title="Envasar estas unidades">
											<img src="img/anadir_01.gif" alt="Unidades a envasar " title="Unidades a envasar"/>
										</a>
									</td>
								</tr>
							</s:iterator>
							<s:iterator id="eans13" value="%{#session.listaeans13}">
								<s:hidden id="cantidad_%{#eans13.lote}" name="cantidad_%{#eans13.lote}" value="%{#eans13.disponible}" />
								<s:hidden id="nombre_%{#eans13.lote}" value="%{#eans13.nombre}" />
								<s:if test="%{#eans13.idLinea==#eans13.idAnterior}">
								</s:if>
								<s:else>
									<tr id="escogerProductosNuevo<s:property value='lote' />">
										<td colspan="6" style="text-align:center; background-color: #CEF6CE !important;" ><s:text name="envasado.productoEscoger"/><s:property value="nombre" /></td>
										<td style="display:none;"><s:hidden id="cantidad_%{#eans13.nombre}_%{#eans13.nombreCategoria}" value="%{#eans13.teorica}" /></td>
									</tr>
									<s:hidden id="cantidadCompone_%{#eans13.idLinea}" name="cantidadCompone_%{#eans13.idLinea}" value="%{#eans13.cantidadUbicacion}" />
								</s:else>
								<tr id="escogerProductos<s:property value='lote' />">
									<td><s:property value="nombre" /></td>
									<td id="loteMP<s:property value='lote'/>"><s:property value="lote" /></td>
									<td id="fechaEnvasado<s:property value='lote'/>"><s:property value="fecha" /></td>
									<td id="fechaCaducidad<s:property value='lote'/>"><s:property value="fechaCaducidad" /></td>
									<td id="saldo_<s:property value='lote'/>"><s:property value="disponible" /></td>
									<td>
										<a class="bot_productos" href="javascript:insertaEAN13('<s:property value="lote" />', '<s:property value="idLinea" />');" title="Envasar estas unidades">
											<img src="img/anadir_01.gif" alt="Unidades a envasar " title="Unidades a envasar"/>
										</a>
									</td>
								</tr>
							</s:iterator>
							</tbody>
						</table>
						<div style="text-align:right">
							<button id="botonHideEscogerMP" class="small blue" onClick="javascript:ocultaEscogerIngredientes();">Ocultar ingredientes disponibles</button>
						</div>
					</fieldset>
				</div><!--escoger ingredientes-->
			</div> <!-- end div todo ingredientes -->
		</fieldset>
		<fieldset id="fieldsetEnvases" class="fieldsetEnvasado" style="display: none;">
			<label>Envases<button id="bot_mostrarEnvases" class="small blue" onClick="javascript:mostrarEnvases();">Ocultar</button></label>
			<div id="todoEnvases" style="display: block;">
				<div id="electedPackages">
					<table id="electedEnvasesTable" class="tabla" border="1" width="100%">
						<thead>
							<tr>
								<th style="width: 300px;" class="destaca" rowspan=2><s:text name="envasado.envases" /></th>
								<th class="destaca" rowspan="2"><s:text name="envasado.codigoEntrada" /></th>
								<th class="destaca" colspan="2">Unidades</th>
								<th class="destaca" rowspan=2><s:text name="envasado.proveedor" /></th>
								<th class="destaca" rowspan=2><s:text name="envasado.loteAlbaran" /></th>
							</tr>
							<tr id="">
								<th style="width: 80px;"><s:text name="envasado.disponible" /></th>
								<th style="width: 80px;"><s:text name="envasado.teorica" /></th>
							</tr>
						</thead>
						<tbody id="electedEnvasesBody">
							<tr id="filaTotalesEnvases">
								<td colspan="2" style="visibility: hidden;"></td>
								<td style="background-color: yellow;"><b>TOTAL</b></td>
								<td id="totalEnvases" style="background-color: yellow;">0</td>
							</tr>
						</tbody>
					</table>
					<div style="text-align:right">
						<button id="bot_envase" class="small blue" style="display: none;" onClick="javascript:insertaEnvase();">M&aacute;s envases</button>
					</div>
				</div><!-- electedPackages -->
				<div id="escogerEnvases">
					<fieldset><!-- Filtro Campos-->
						<legend><span><s:text name="envasado.envasesDisponibles" /></span></legend>
						<table id="disponibleENV" class="tabla" border="1" width="70%">
							<thead>
								<tr>
									<th><s:text name="envasado.codigoEntrada" /></th>
									<th><s:text name="envasado.fechaEntrada" /></th>
									<th><s:text name="envasado.fechaCaducidad" /></th>
									<th><s:text name="envasado.lote" /></th>
									<th><s:text name="envasado.saldo" /></th>
									<th><s:text name="envasado.proveedor" /></th>
								</tr>
							</thead>
							<tbody id="escogerEnvasesBody">
								<s:iterator id="envax" value="%{#session.listaregistroenvases}">
									<s:hidden id="cantidad_%{#envax.entrada}_%{#envax.idProducto}" name="cantidad_%{#envax.entrada}_%{#envax.idProducto}" value="%{#envax.teorica}" />
									<s:hidden id="nombre_%{#envax.entrada}_%{#envax.idProducto}" value="%{#envax.nombre}" />
									<s:if test="%{#envax.idProducto==#envax.idAnterior}">
									</s:if>
									<s:else>
										<s:if test="%{#envax.idProducto==#envax.idAnteriorAgrupar}">
										</s:if>
										<s:else>
											<tr id="escogerEnvasesNuevo<s:property value='idLinea' />" class="encabezadoEnvase_<s:property value='idProducto' /> encabezadosEnvases" style="display: none;">
												<td colspan="6" style="text-align:center; background-color: #CEF6CE !important;" ><s:text name="envasado.envaseEscoger" /> <s:property value="nombre" /></td>
											</tr>
										</s:else>
									</s:else>
									<tr id="escogerEnvases<s:property value='entrada' />_<s:property value="idProducto" />" class="filaEnvase">
										<td><s:property value="entrada" /></td>
										<td><s:property value="fecha" /></td>
										<td><s:property value="fechaCaducidad" /></td>
										<td id="loteEN<s:property value='entrada'/>_<s:property value="idProducto" />"><s:property value="lote" /></td>
										<td id="saldo_<s:property value='entrada'/>_<s:property value="idProducto" />"><s:property value="disponible" /></td>
										<td id="proveedorEN<s:property value='entrada'/>_<s:property value="idProducto" />"><s:property value="proveedor" /></td>
										<td>
											<a id="bt_envase_<s:property value='entrada' />_<s:property value="idProducto" />" class="bot_envases" href="javascript:insertaENV('<s:property value="entrada" />', '<s:property value="idProducto" />')" title="Envasar estas unidades">
												<img src="img/anadir_01.gif" alt="Unidades a envasar " title="Unidades a envasar"/>
											</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div style="text-align:right">
							<button id="botonHideEscogerENV" class="small blue" onClick="javascript:ocultaEscogerEnvases();">Ocultar envases disponibles</button>
						</div>
					</fieldset>
				</div><!--escoger envases-->
			</div><!-- end div todo envases -->
		</fieldset>
		<s:iterator id="agrupacionesPosibles" value="%{#session.agrupacionesPosibles}">
			<s:hidden id="idAgrupacion_%{#agrupacionesPosibles.idProducto}" value="%{#agrupacionesPosibles.nombre}" cssClass="agrupacionPosibleOculta" />
		</s:iterator>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>