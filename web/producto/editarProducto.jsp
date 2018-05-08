<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ page contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" language="java"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="producto.editarProducto"/><span class="screenCode"><s:text name="producto.editarProducto.codigoPantalla"/></span></h3>
	<s:form id="formulario" name="formulario" action="ActualizaProducto" validate="true" method="post" enctype="multipart/form-data">
		<s:iterator id="producto" value="%{#session.producto}">
			<s:hidden id="idGrupo" name="idGrupo" key="idGrupo"/>
			<s:hidden id="mostrar" name="mostrar" key="mostrar"/>
			<s:hidden id="idMateriaPrima" name="idMateriaPrima" key="idMateriaPrima" value="0"/>
			<div class="widget" id="widget_tabs"> <!-- CONTIENE LAS TABS -->
				<div class="tab">
					<ul>
						<li><a href="#tab_datosGenerales">Datos generales</a></li>
						<li id="pestaniaImagen" style="display: none;"><a href="#tab_imagen">Imagen</a></li>
						<li id="pestaniaCategorizacion" style="display: none;"><a href="#tab_categorizacion">Grupo</a></li>
						<li id="pestaniaIngredientes" style="display: none;"><a href="#tab_ingredientes">Ingredientes</a></li>
						<li id="pestaniaEANS13" style="display: none;"><a href="#tab_items" >EANs13</a></li>
						<li id="pestaniaEnvases" style="display: none;"><a href="#tab_envases" >Envases</a></li>
					</ul>
					<div id="tab_datosGenerales"> <!-- TAB DATOS GENERALES -->
						<fieldset>
							<section class="sectionTercio">
								<label for="text_id">ID</label>
								<div>
									<s:textfield id="text_id" key="idProducto" label="ID" readonly="true"/>
								</div>
							</section>
							<section class="sectionTercio">
								<label>Tipo EAN</label>
								<div>
									<input type="radio" id="radio_ean13" name="radio" value="EAN13" onchange="javascript:tipoEan('13');"><label id="label13" for="radio_ean13">EAN13</label>
									<input type="radio" id="radio_ean14" name="radio" value="EAN14" onchange="javascript:tipoEan('14');"><label id="label14" for="radio_ean14">EAN14</label>
								</div>
							</section>
							<section class="sectionTercio">
								<label>Granel</label>
								<div>
									<input type="checkbox" id="check_granel" name="check_granel" value="Granel">
								</div>
							</section>
						</fieldset>
						<fieldset style="margin-top: 0px;">
							<section class="sectionTercio">
								<label for="text_ean">EAN</label>
								<div>
									<s:textfield id="text_ean" key="codigoEan" label="Codigo EAN"/>
								</div>
							</section>
							<section class="sectionTercio">
								<label for="text_stock">Stock</label>
								<div>
									<s:textfield id="text_stock" key="stock" label="Stock" readonly="true"/>
								</div>
							</section>
							<section class="sectionTercio">
								<label for="text_stockAgrupado">Stock agrupado</label>
								<div>
									<s:textfield id="text_stockAgrupado" key="stockAgrupado" label="Stock agrupado" readonly="true"/>
								</div>
							</section>
							<section class="sectionMitad">
								<label for="text_nombre"><s:text name="registroCliente.nombre"/></label>
								<div>
									<s:textfield id="text_nombre" key="nombre" key="nombre" label="%{getText('registro.label.nombre')}" onkeypress="return validarSinComas(event);" />
								</div>
							</section>
							<section class="sectionMitad">
								<label for="text_descripcion">Descripci&oacute;n</label>
								<div>
									<s:textfield id="text_descripcion" key="descripcion" label="Descripcion" onkeypress="return validarSinComas(event);" />
								</div>
							</section>
							<section class="sectionCuarto">
								<label for="text_stockMinimo">Stock m&iacute;n</label>
								<div>
									<s:textfield id="text_stockMinimo" key="stockMinimo" label="Stock minimo" onkeypress="return validarNumerosDecimales('text_stockMinimo', event);" />
								</div>
							</section>
							<section class="sectionCuarto">
								<label for="text_peso">Peso</label>
								<div>
									<s:textfield id="text_peso" key="peso" label="Peso" onkeypress="return validarNumerosDecimales('text_peso', event);" />
								</div>
							</section>
							<section class="sectionCuarto">
								<label for="text_precio">Precio</label>
								<div>
									<s:textfield id="text_precio" key="precio" label="Precio" onkeypress="return validarNumerosDecimales('text_precio', event);" />
								</div>
							</section>
							<section class="sectionCuarto">
								<label for="text_caducidad">Caducidad</label>
								<div>
									<s:textfield id="text_caducidad" key="caducidad" label="Caducidad" onkeypress="return validarSinComas(event);" />
								</div>
							</section>
							<section class="sectionMitad">
								<label for="dropdown_categorias">Categoria</label>
								<div>
									<s:select id="dropdown_categorias" key="idCategoria" onchange="javascript:categoriaSeleccionada();" label="Categoria" list="%{#session.listacategorias}" listKey="idCategoria" listValue="nombreCategoria" headerKey="0" headerValue="-- Categoria --" cssStyle="width:195px;"/>
								</div>
							</section>
							<section class="sectionMitad">
								<label for="dropdown_impuestos">Impuesto</label>
								<div>
									<s:select id="dropdown_impuestos" key="idImpuesto" onchange="javascript:impuestoSeleccionado();" label="Impuesto" list="%{#session.listaimpuestos}" listKey="idImpuesto" listValue="nombreImpuesto" headerKey="0" headerValue="-- Impuesto --" cssStyle="width:195px;"/>
								</div>
							</section>
						</fieldset>
					</div> <!-- end tab datosGenearles -->
					<div id="tab_imagen">
						<fieldset>
							<section>
								<label for="file_upload">Imagen<br>
								<span>Seleccione una imagen para el producto</span></label>
								<div><input type="file" id="file_upload" name="file_upload"></div>
								<div id="imagen_producto" style="float: right; margin-top: -180px; width: 50%;">
									<h2><s:text name="producto.label.imagenProducto"/></h2>
									<div style="max-width: 220px; max-height: 220px; border: 1px solid black;">
										<img alt="Imagen seleccionada para el producto" src="<s:property value="%{#session.userImageFileName}"/>"/>
									</div>
								</div>
							</section>
						</fieldset>
					</div>
					<div id="tab_categorizacion">
						<fieldset>
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
												<li id="<s:property value="idGrupo" />" class="closed treeViewNOSeleccion" onclick="javascript:grupoCategorizacionSeleccionado('<s:property value="idGrupo" />');" >
													<span id="span_<s:property value="idGrupo" />" class="folder"><s:property value="nombreGrupo" /></span>
													<ul id="lista_<s:property value="idGrupo" />">
														<s:iterator id="categorizaciones2" value="%{#session.listaCategorizaciones}">
															<s:if test="%{#categorizaciones2.idPadre==#categorizaciones.idGrupo}" >
																<li id="<s:property value="idGrupo" />" class="closed treeViewNOSeleccion" onclick="javascript:grupoCategorizacionSeleccionado('<s:property value="idGrupo" />');">
																	<span id="span_<s:property value="idGrupo" />" class="file"><s:property value="nombreGrupo" /></span>
																	<ul id="lista_<s:property value="idGrupo" />">
																		<s:iterator id="categorizaciones3" value="%{#session.listaCategorizaciones}">
																			<s:if test="%{#categorizaciones3.idPadre==#categorizaciones2.idGrupo}" >
																				<li id="<s:property value="idGrupo" />" onclick="javascript:grupoCategorizacionSeleccionado('<s:property value="idGrupo" />');" class="file treeViewNOSeleccion">
																					<span id="span_<s:property value="idGrupo" />" class="file"><s:property value="nombreGrupo" /></span>
																				</li>
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
													</ul>
												</li>
											</s:if>
										</s:iterator>
									</ul>
								</div>
							</section>
						</fieldset>
					</div><!-- end tab_categorias -->
					<div id="tab_ingredientes"> <!-- TAB ingredientes -->
						<s:label name="%{getText('producto.label.seleccionMP')}" value="%{getText('producto.label.seleccionMP')}"/>
						<select name="dropdown_productos" id="dropdown_productos" onchange="javascript:seleccionProducto();">
							<option selected value="0">Materias primas y EANs13</option>
							<optgroup id="optMPs" label="Seleccione una materia prima">
								<s:iterator id="mps" value="%{#session.listamateriaprima}">
									<option value="<s:property value="idMateriaCategoria" />_MPs">
										<s:property value="nombre" /> - <s:property value="nombreCategoria" />
									</option>
								</s:iterator>
							</optgroup>
							<optgroup id="optEAN13" label="Seleccione un EAN13">
								<s:iterator id="eans13" value="%{#session.productosCompuesto}">
									<option value="<s:property value="idProducto" />_EAN13">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
						<table id="tabla_MP" class="flat" style="width: 100% !important;" cellpadding="2" cellspacing="2" border="1" style="display: none;">
							<thead>
								<tr id="cabeceraMPs">
									<th><s:text name="producto.id"/></th>
									<th><s:text name="producto.nombre"/></th>
									<th style="width: 100px;"><s:text name="producto.cantidadMPs"/></th>
								</tr>
							</thead>
							<tbody id="tabla_MP_body">
								<s:iterator id="infomp" value="%{#session.listamateriaprima_Asociado}">
									<tr id="seleccionMP_<s:property value='idMateriaCategoria'/>">
										<td style="width: 25px;">
											<input type="hidden" name="MP_<s:property value='idMateriaCategoria'/>" value="<s:property value='idMateriaCategoria'/>"/><s:property value="idMateriaCategoria" />
										</td>
										<td><s:property value="nombre + ' - ' + nombreCategoria" /></td>
										<td nowrap="nowrap">
											<s:textfield value="%{#infomp.cantidad}" cssClass="inputCantidadIngrediente" name="CANTIDAD_MP_%{#infomp.idMateriaCategoria}" id="CANTIDAD_MP_%{#infomp.idMateriaCategoria}" label="%{getText('producto.label.cantidad')}" key="cantidad" onkeypress="return validarNumerosDecimales('CANTIDAD_MP_%{#infomp.idMateriaCategoria}', event);" onblur="javascript:ajustarDecimal('CANTIDAD_MP_%{#infomp.idMateriaCategoria}');" cssStyle="text-align: right;" />
										</td>
										<td style="width: 25px;">
											<a id="elimina" href="javascript:eliminaMateria('<s:property value='idMateriaCategoria' />')" title="Eliminar esta materia">
												<img src="img/cancel.png" alt="Eliminar" title="Eliminar materia"/>
											</a>
										</td>
									</tr>
								</s:iterator>
								<s:iterator id="infoEAN13" value="%{#session.listaEANs13_Asociado}">
									<tr id="seleccionEAN13_<s:property value='idProducto'/>">
										<td style="width: 25px;">
											<input type="hidden" name="MP_<s:property value='idProducto'/>" value="<s:property value='idProducto'/>"/><s:property value="idProducto" />
										</td>
										<td><s:property value="nombre" /></td>
										<td nowrap="nowrap">
											<s:textfield value="%{#infoEAN13.cantidad}" cssClass="inputCantidadIngrediente" name="CANTIDAD_EAN13_%{#infoEAN13.idProducto}" id="CANTIDAD_EAN13_%{#infoEAN13.idProducto}" label="%{getText('producto.label.cantidad')}" key="cantidad" onkeypress="return validarNumerosDecimales('CANTIDAD_EAN13_%{#infoEAN13.idProducto}', event);" onblur="javascript:ajustarDecimal('CANTIDAD_EAN13_%{#infoEAN13.idProducto}');" cssStyle="text-align: right;" />
										</td>
										<td style="width: 25px;">
											<a id="elimina" href="javascript:eliminaEAN13('<s:property value='idProducto' />')" title="Eliminar este producto">
												<img src="img/cancel.png" alt="Eliminar" title="Eliminar producto"/>
											</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div> <!-- end ingredientes -->
					<div id="tab_items">
						<s:label name="%{getText('producto.label.seleccionMP')}" value="Seleccionar EANs13" />
						<s:select id="items" value="idProducto" key="idProducto" cssStyle="width:180px; text-align:left;" onchange="cargaItem();" label="%{getText('producto.label.seleccionMP')}" list="%{#session.productosCompuesto}" listKey="idProducto" listValue="nombre" headerKey="0" headerValue="-- Seleccione un producto --"/>
						<table id="tabla_Items" class="flat" style="width: 100% !important;" cellpadding="2" cellspacing="2" border="1" style="display: none;">
							<thead>
								<tr id="cabeceraItems">
									<th><s:text name="producto.id"/></th>
									<th><s:text name="producto.nombre"/></th>
									<th style="width: 100px;"><s:text name="producto.cantidadCompuesto"/></th>
								</tr>
							</thead>
							<tbody id="tbody_items">
								<s:iterator id="infoCompuesto" value="%{#session.productosCompuesto_Asociado}">
									<tr id="seleccionItem_<s:property value='idProducto'/>">
										<td style="width: 25px;">
											<input type="hidden" name="Item_<s:property value='idProducto'/>" value="<s:property value='idProducto'/>"/><s:property value="idProducto" />
										</td>
										<td><s:property value="nombre" /></td>
										<td nowrap="nowrap">
											<s:textfield label="%{getText('producto.label.cantidad')}" cssClass="inputCantidadEAN13" value="%{#infoCompuesto.disponible}" name="CANTIDAD_Item_%{#infoCompuesto.idProducto}" id="CANTIDAD_Item_%{#infoCompuesto.idProducto}" key="cantidad" onkeypress="return validarSoloNumeros(event);" onblur="javascript:ajustarDecimal('CANTIDAD_Item_%{#infoCompuesto.idProducto}');" cssStyle="text-align: right;" />
										</td>
										<td style="width: 25px;">
											<a id="elimina" href="javascript:eliminaItem('<s:property value='idProducto' />')" title="Eliminar este producto">
												<img src="img/cancel.png" alt="Eliminar" title="Eliminar item"/>
											</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					<div id="tab_envases"> <!-- TAB envases -->
						<s:label name="%{getText('producto.label.seleccionEN')}" value="%{getText('producto.label.seleccionEN')}"/>
						<s:select id="envases" key="envases" cssStyle="width:180px; text-align:left;" onchange="cargaEnvase();" label="%{getText('producto.label.seleccionEN')}" list="%{#session.listaenvases}" listKey="idEnvase" listValue="nombre" headerKey="0" headerValue="-- Envase --" />
						<table id="tabla_EN" class="flat" style="width: 100% !important;" cellpadding="2" cellspacing="2" border="1" style="display: none;">
							<thead>
								<tr id="cabeceraENs">
									<th><s:text name="producto.id"/></th>
									<th><s:text name="producto.nombre"/></th>
									<th style="width: 100px;"><s:text name="producto.cantidadENs"/></th>
								</tr>
							</thead>
							<tbody id="tabla_EN_body">
								<s:iterator id="infoen" value="%{#session.listaenvases_Asociado}">
									<tr id="seleccionEN_<s:property value='idEnvase'/>">
										<td style="width: 25px;">
											<input type="hidden" name="EN_<s:property value='idEnvase'/>" value="<s:property value='idEnvase'/>"/><s:property value="idEnvase" />
										</td>
										<td><s:property value="nombre" /></td>
										<td nowrap="nowrap">
											<s:textfield value="%{#infoen.cantidad}" id="CANTIDAD_EN_%{#infoen.idEnvase}" name="CANTIDAD_EN_%{#infoen.idEnvase}" key="cantidad" onkeypress="return validarSoloNumeros(event);" onblur="javascript:ajustarDecimal('CANTIDAD_EN_%{#infoen.idEnvase}');" cssStyle="text-align: right;" cssClass="inputCantidadEnvase" />
										</td>
										<td style="width: 25px;">
											<a id="elimina" href="javascript:eliminaEnvase('<s:property value='idEnvase' />')" title="Eliminar este envase">
												<img src="img/cancel.png" alt="Eliminar" title="Eliminar envase"/>
											</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div> <!-- end envases -->
				</div> <!-- END CLASS TAB -->
			</div> <!-- END widget_tabs -->
		</s:iterator>
	</s:form>
</s:i18n>