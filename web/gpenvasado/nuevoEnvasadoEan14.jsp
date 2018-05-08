<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<div id="divNecesarioWidget">
		<s:hidden id="idProd" name="idProd" key="idProd" />
		<s:hidden id="tipoEan" name="tipoEan" key="tipoEan" />
		<s:hidden id="idProductoSelectValor" name="idProductoSelectValor" key="idProductoSelectValor" value="%{#session.idProductoSelect}"/>
		<fieldset><!-- Filtro Campos-->
			<label>Productos<button id="bot_mostrarProductos" class="small blue" onClick="javascript:mostrarProductos();">Mostrar</button></label>
			<div id="todoProductos" style="display: none;">
				<div id="electedProductos">
					<table id="electedProductosTable" class="tabla" border="1" width="100%">
						<thead>
							<tr>
								
								<th style="width: 300px;" class="destaca" rowspan="2"><s:text name="envasado.producto" /></th>
								<th class="destaca" rowspan="2"><s:text name="envasado.lote" /></th>
								<th class="destaca" colspan="2"><s:text name="envasado.unidades" /></th>
								<th class="destaca" rowspan="2"><s:text name="envasado.fechaEnvasado" /></th>
								<th class="destaca" rowspan="2"><s:text name="envasado.fechaCaducidad" /></th>
							</tr>
							<tr>
								<th style="width: 80px;"><s:text name="envasado.disponible" /></th>
								<th style="width: 80px;"><s:text name="envasado.teorica" /></th>
							</tr>
						</thead>
						<tbody id="electedProductosBody" >
							<tr id="filaTotalesIngredientes">
								<td colspan="2" style="visibility: hidden;"></td>
								<td style="background-color: yellow;"><b>TOTAL</b></td>
								<td id="totalIngredientes" style="background-color: yellow;">0</td>
							</tr>
						</tbody>
					</table>
					<div style="text-align:right">
						<button id="btMasProducto" class="small blue" onClick="javascript:masProducto();">Mostrar productos</button>
					</div>
				</div><!-- electedProductos-->
				<div id="escogerProductos">
					<fieldset><!-- Filtro Campos-->
						<legend><span>Productos disponibles</span></legend>
						<table id="disponibleMP" class="tabla" border="1" width="70%">
							<thead>
								<tr>
									<th><s:text name="envasado.producto" /></th>
									<th><s:text name="envasado.lote" /></th>
									<th><s:text name="envasado.fechaEnvasado" /></th>
									<th><s:text name="envasado.fechaCaducidad" /></th>
									<th><s:text name="envasado.saldo" /></th>
								</tr>
							</thead>
							<tbody id="escogerProductosBody">
								<s:iterator id="ingres" value="%{#session.listaItemsComponen}">
									<s:hidden id="cantidad_%{#ingres.lote}" name="cantidad_%{#ingres.lote}" value="%{#ingres.disponible}" />
									<s:hidden id="nombre_%{#ingres.lote}" value="%{#ingres.nombre}" />
									<s:if test="%{#ingres.idLinea==#ingres.idAnterior}">
									</s:if>
									<s:else>
										<tr id="escogerProductosNuevo<s:property value='lote' />">
											<td colspan="6" style="text-align:center; background-color: #CEF6CE !important;" ><s:text name="envasado.productoEscoger"/><s:property value="nombre" /></td>
											<td style="display:none;"><s:hidden id="cantidad_%{#ingres.nombre}_%{#ingres.nombreCategoria}" value="%{#ingres.teorica}" /></td>
										</tr>
										<s:hidden id="cantidadCompone_%{#ingres.idLinea}" name="cantidadCompone_%{#ingres.idLinea}" value="%{#ingres.cantidadUbicacion}" />
									</s:else>
									<tr id="escogerProductos<s:property value='lote' />">
										<td><s:property value="nombre" /></td>
										<td id="loteMP<s:property value='lote'/>"><s:property value="lote" /></td>
										<td id="fechaEnvasado<s:property value='lote'/>"><s:property value="fecha" /></td>
										<td id="fechaCaducidad<s:property value='lote'/>"><s:property value="fechaCaducidad" /></td>
										<td id="saldo_<s:property value='lote'/>"><s:property value="disponible" /></td>
										<td>
											<a id="bot_productos" href="javascript:insertaMe('<s:property value="lote" />', '<s:property value="idLinea" />');" title="Envasar estas unidades">
												<img src="img/anadir_01.gif" alt="Unidades a envasar " title="Unidades a envasar"/>
											</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div style="text-align:right">
							<button id="botonHideEscogerMP" class="small blue" onClick="javascript:ocultaEscogerProductos();">Ocultar productos disponibles</button>
						</div>
					</fieldset>
				</div><!--escoger productos-->
			</div><!-- end todo productos -->
		</fieldset>
		<fieldset>
			<label>Envases<button id="bot_mostrarEnvases" class="small blue" onClick="javascript:mostrarEnvases();">Mostrar</button></label>
			<div id="todoEnvases" style="display: none;">
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
						<button id="bot_envase" class="small blue" onClick="javascript:insertaEnvase();">M&aacute;s envases</button>
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
									<s:hidden id="cantidad_%{#envax.entrada}" name="cantidad_%{#envax.entrada}" value="%{#envax.teorica}" />
									<s:hidden id="nombre_%{#envax.entrada}" value="%{#envax.nombre}" />
									<s:if test="%{#envax.idLinea==#envax.idAnterior}">
									</s:if>
									<s:else>
										<tr id="escogerEnvasesNuevo<s:property value='idLinea' />">
											<td colspan="6" style="text-align:center; background-color: #CEF6CE !important;" ><s:text name="envasado.envaseEscoger" /> <s:property value="nombre" /></td>
										</tr>
									</s:else>
									<tr id="escogerEnvases<s:property value='entrada' />" class="filaEnvase">
										<td><s:property value="entrada" /></td>
										<td><s:property value="fecha" /></td>
										<td><s:property value="fechaCaducidad" /></td>
										<td id="loteEN<s:property value='entrada'/>"><s:property value="lote" /></td>
										<td id="saldo_<s:property value='entrada'/>"><s:property value="disponible" /></td>
										<td id="proveedorEN<s:property value='entrada'/>"><s:property value="proveedor" /></td>
										<td>
											<a id="bot_envases" href="javascript:insertaENV('<s:property value="entrada" />')" title="Envasar estas unidades">
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
			</div><!-- end todo envases -->
		</fieldset>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>