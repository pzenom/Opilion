<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Nuevo pedido<span class="screenCode">NUEVO_PEDIDO</span></h3>
	<s:form id="formulario" name="formulario" action="InseDetaAlba" validate="true">
		<s:hidden id="idDireccionDefecto" value="0" />
		<fieldset>
			<section class="sectionTercio">
				<label for="text_pedido">ID</label>
				<div>
					<s:textfield id="text_pedido" key="text_pedido" label="%{getText('exit.pedido')}" value="%{#session.pedido}" readonly="true"/>
				</div>
			</section>
			<section class="sectionTercio">
				<label for="date_fechaPedido">Fecha pedido</label>
				<div>
					<s:textfield id="date_fechaPedido" key="date_fechaPedido" label="%{getText('exit.fechaPedido')}" value="%{#session.fecharegistro}" readonly="true"/>
				</div>
			</section>
			<section class="sectionTercio">
				<label for="date_fechaEntrega">Fecha entrega</label>
				<div>
					<input id="date_fechaEntrega" name="date_fechaEntrega" type="text" class="date" data-format="yyyy-mm-dd">
				</div>
			</section>
			<section class="sectionUnoDeTres">
				<label for="dropdown_divisas">Divisa</label>
				<div>
					<select name="dropdown_divisas" id="dropdown_divisas">
						<option selected value="0">Seleccione una divisa</option>
						<optgroup label="Divisas disponibles">
							<option value="EUR" selected>EUR</option>
						</optgroup>
					</select>
				</div>
			</section>
			<section class="sectionDosDeTres">
				<label for="dropdown_clientes">Cliente</label>
				<div>
					<select name="dropdown_clientes" id="dropdown_clientes" onchange="javascript:seleccionCliente();">
						<option selected value="0">Seleccione un cliente</option>
						<optgroup label="Clientes">
							<s:iterator id="clientes" value="%{#session.listaclientes}">
								<option value="<s:property value="idUsuario" />">
									<s:property value="nombre" />
								</option>
							</s:iterator>
						</optgroup>
					</select>
				</div>
			</section>
			<section>
				<label for="dropdown_formasDePago">Forma de pago</label>
				<div id="contenedorSelect" style="display: none;">
					<div>
						<select name="dropdown_formasDePago" id="dropdown_formasDePago">
							<option selected value="-1">Seleccione una forma de pago</option>
							<optgroup label="Formas de pago" id="optgroupFormasPago">
							</optgroup>
						</select>
					</div>
				</div>
				<div id="contenedorTexto">
					<p id="textoSeleccioneCliente" style="color: red;">Seleccione un cliente</p>
				</div>
			</section>
			<!-- <label>A&ntilde;adir productos al pedido</label> -->
			<hr />
			<div style="display: none;">
				<select name="dropdown_productos" id="dropdown_productos" onChange="javascript:addProducto();">
					<option selected value="0">Seleccione un producto para a&ntilde;adir al pedido</option>
					<optgroup label="Productos">
						<s:iterator id="productos" value="%{#session.listaproductos}">
							<option id="prod_<s:property value="idProducto" />" value="<s:property value="idProducto" />_<s:property value="codigoEan" />_<s:property value="peso" />_<s:property value="mostrar" />_<s:property value="EANs13" />">
								<s:property value="nombre" />
							</option>
						</s:iterator>
					</optgroup>
				</select>
			</div>
			<!-- Listado productos aniadidos -->
			<div id="divProductos" style="display: none;">
				<table id="tablaProductos" cellpadding="0" cellspacing="0" border="0" class="display" >
					<thead>
						<tr>
							<th>Linea</th>
							<th>Producto</th>
							<th>Cantidad</th>
							<th>Precio</th>
							<th>Agrupaciones EDI</th>
							<th>Direccion de entrega</th>
						</tr>
					</thead>
					<tbody id="tablaProductosBody">
					</tbody>
				</table>
			</div> <!-- end demo: Listado productos disponible-->
		</fieldset>
		<fieldset>
			<div id="totales">
				<table id="tablaTotales" cellpadding="0" cellspacing="0" border="0" >
					<thead>
						<tr>
							<th>Kilos totales</th>
							<th>Unidades totales</th>
							<th>Agrupaciones EDI totales</th>
							<th>IMPORTE TOTAL</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td id="kilosTotal"><s:property value="0" /></td>
							<td id="unidadesTotal"><s:property value="0" /></td>
							<td id="agrupacionesTotal"><s:property value="0" /></td>
							<td id="importeTotal"><s:property value="0.0" /></td>
						</tr>
					</tbody>
				</table>
			</div> <!-- end totales -->
			<section>
				<label for="dropdown_formasDePago">Observaciones del pedido</label>
				<div>
					<textarea id="observacionesPedido" name="observacionesPedido" style="width:96%;" rows=2></textarea>
				</div>
			</section>
		</fieldset>
		<div id="hiddens" style="display: none;">
			<s:hidden id="moa79" name="moa79"/>
			<s:hidden id="nadBy" name="nadBy"/>
			<s:hidden id="nadMs" name="nadMs"/>
			<s:hidden id="bgmNum" name="bgmNum"/>
			<s:hidden id="fechaPedido" name="fechaPedido"/>
			<s:hidden id="cux" name="cux"/>
			<s:hidden id="cnt" name="cnt"/>
			<s:hidden id="idFormaPago" name="idFormaPago"/>
			<select style="display: none;" id="entrega_-1"></select>
			<div id="formasPagoOcultas" style="display: none;"></div>
			<div id="direccionesOcultas" style="display: none;"></div>
			<div id="contenedorProductos" style="display: none;">
				<div id="divGruposProductos" >
					<fieldset id="fieldsetGruposProductos">
						<section>
							<label for="tree_grupos">Grupo</label>
							<div id="breadcrumb_grupo">
								<ul id="ul_breadcrum_grupo" class="breadcrumb" data-connect="breadcrumbcontent">
									<li id="breadcrum_grupo_seleccionar">
										<a id="breadcrumb_seleccionar" onclick="javascript:muestraSeleccionarGrupo();">Seleccionar grupo</a>
									</li>
								</ul>
							</div>
							<div id="treeView_grupo" style="display: none;">
								<ul id="categorizacion" class="filetree">
									<s:iterator id="categorizaciones" value="%{#session.listaCategorizaciones}">
										<s:if test="%{#categorizaciones.idPadre==0}" >
											<li id="<s:property value="idGrupo" />" class="closed treeViewNOSeleccion">
												<span id="span_<s:property value="idGrupo" />" class="folder"><s:property value="nombreGrupo" /></span>
												<ul id="lista_<s:property value="idGrupo" />">
													<s:iterator id="categorizaciones2" value="%{#session.listaCategorizaciones}">
														<s:if test="%{#categorizaciones2.idPadre==#categorizaciones.idGrupo}" >
															<li id="<s:property value="idGrupo" />" class="closed treeViewNOSeleccion">
																<span id="span_<s:property value="idGrupo" />" class="file"><s:property value="nombreGrupo" /></span>
																<ul id="lista_<s:property value="idGrupo" />">
																	<s:iterator id="categorizaciones3" value="%{#session.listaCategorizaciones}">
																		<s:if test="%{#categorizaciones3.idPadre==#categorizaciones2.idGrupo}" >
																			<li id="<s:property value="idGrupo" />" class="file treeViewNOSeleccion closed">
																				<span id="span_<s:property value="idGrupo" />" class="file"><s:property value="nombreGrupo" /></span>
																				<ul id="lista_<s:property value="idGrupo" />">
																					<s:iterator id="productos" value="%{#session.listaproductos}">
																						<s:if test="%{#productos.idGrupo==#categorizaciones3.idGrupo}" >
																							<li id="producto_<s:property value="idProducto" />" onclick="javascript:productoSeleccionado('<s:property value="idProducto" />');" class="file treeViewNOSeleccion closed"><span class="file"><s:property value="nombre" /></span></li>
																						</s:if> 
																						<s:else>
																						</s:else>
																					</s:iterator>
																				</ul>
																			</li>
																		</s:if>
																		<s:else>
																		</s:else>
																	</s:iterator>
																	<s:iterator id="productos" value="%{#session.listaproductos}">
																		<s:if test="%{#productos.idGrupo==#categorizaciones2.idGrupo}" >
																			<li id="producto_<s:property value="idProducto" />" onclick="javascript:productoSeleccionado('<s:property value="idProducto" />');" class="file treeViewNOSeleccion closed"><span class="file"><s:property value="nombre" /></span></li>
																		</s:if> 
																		<s:else>
																		</s:else>
																	</s:iterator>
																</ul>
															</li>
														</s:if>
														<s:else>
														</s:else>
													</s:iterator>
													<s:iterator id="productos" value="%{#session.listaproductos}">
														<s:if test="%{#productos.idGrupo==#categorizaciones.idGrupo}" >
															<li id="producto_<s:property value="idProducto" />" onclick="javascript:productoSeleccionado('<s:property value="idProducto" />');" class="file treeViewNOSeleccion closed"><span class="file"><s:property value="nombre" /></span></li>
														</s:if> 
														<s:else>
														</s:else>
													</s:iterator>
												</ul>
											</li>
										</s:if>
									</s:iterator>
								</ul>
							</div>
						</section>
					</fieldset>
				</div> <!-- END divGruposProductos -->
				<button id="bot_aceptarProducto" class="i_tick icon verdeOpilion" style="display: none;">Aceptar</button>
			</div>
		</div>
	</s:form>
</s:i18n>