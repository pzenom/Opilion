<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ page contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" language="java"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="producto.crearProducto"/><span class="screenCode">REGISTRO_PRODU</span></h3>
		<s:form id="formulario_datosProducto" name="formulario_datosProducto" action="#" method="post" enctype="multipart/form-data">
			<s:hidden id="idGrupo" name="idGrupo" value="0"/>
			<s:hidden id="idMateriaPrima" name="idMateriaPrima" key="idMateriaPrima" value="0"/>
			<div class="widget" id="widget_tabs" style="display: none;"> <!-- CONTIENE LAS TABS -->
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
							<section class="sectionMitad">
								<label>Tipo EAN</label>
								<div>
									<input type="radio" id="radio_ean13" name="radio" value="EAN13" onchange="javascript:tipoEan('13');"><label id="label13" for="radio_ean13">EAN13</label>
									<input type="radio" id="radio_ean14" name="radio" value="EAN14" onchange="javascript:tipoEan('14');"><label id="label14" for="radio_ean14">EAN14</label>
								</div>
							</section>
							<section class="sectionMitad">
								<label>Granel</label>
								<div>
									<input type="checkbox" id="check_granel" name="check_granel" value="Granel">
								</div>
							</section>
						</fieldset>
						<fieldset style="margin-top: 0px;">
							<section class="sectionMitad">
								<label for="text_nombre"><s:text name="registroCliente.nombre"/></label>
								<div>
									<input type="text" id="text_nombre" name="text_nombre" onKeyPress="return validarSinComas(event);" />
									<span>Introducir nombre del producto</code></span>
								</div>
							</section>
							<section class="sectionMitad">
								<label for="text_descripcion">Descripci&oacute;n</label>
								<div>
									<input type="text" id="text_descripcion" name="text_descripcion" onKeyPress="return validarSinComas(event);" />
									<span>Introducir descripci&oacute;n del producto</code></span>
								</div>
							</section>
							<section class="sectionMitad">
								<label for="text_ean">EAN</label>
								<div>
									<input type="text" id="text_ean" name="text_ean" />
									<span>Introducir el codigo EAN del producto</code></span>
								</div>
							</section>
							<section class="sectionMitad">
								<label for="text_stockMinimo">Stock m&iacute;nimo</label>
								<div>
									<input type="text" id="text_stockMinimo" name="text_stockMinimo" onKeyPress="return validarNumerosDecimales('text_stockMinimo', event);" />
									<span>Introducir el stock m&iacute;nimo del producto</code></span>
								</div>
							</section>
							<section class="sectionMitad">
								<label for="text_peso">Peso</label>
								<div>
									<input type="text" id="text_peso" name="text_peso" onKeyPress="return validarNumerosDecimales('text_peso', event);" />
									<span>Introducir el peso del producto</code></span>
								</div>
							</section>
							<section class="sectionMitad">
								<label for="text_precio">Precio</label>
								<div>
									<input type="text" id="text_precio" name="text_precio" onKeyPress="return validarNumerosDecimales('text_precio', event);" />
									<span>Introducir el precio del producto</code></span>
								</div>
							</section>
							<section class="sectionMitad">
								<label for="text_stock">Stock</label>
								<div>
									<input type="text" id="text_stock" name="text_stock" readonly="true"/>
								</div>
							</section>
							<section class="sectionMitad">
								<label for="dropdown_impuestos">Impuesto</label>
								<div>
									<select name="dropdown_impuestos" id="dropdown_impuestos" onchange="javascript:impuestoSeleccionado();">
										<optgroup label="Impuesto">
											<s:iterator id="impuestos" value="%{#session.listaimpuestos}">
												<option value="<s:property value="idImpuesto" />">
													<s:property value="nombreImpuesto" />
												</option>
											</s:iterator>
										</optgroup>
									</select>
								</div>
							</section>
							<section class="sectionMitad">
								<label for="dropdown_categorias">Categoria</label>
								<div>
									<select name="dropdown_categorias" id="dropdown_categorias" onchange="javascript:categoriaSeleccionada();">
										<optgroup label="Categoria">
											<s:iterator id="categorias" value="%{#session.listacategorias}">
												<option value="<s:property value="idCategoria" />">
													<s:property value="nombreCategoria" />
												</option>
											</s:iterator>
										</optgroup>
									</select>
								</div>
							</section>
							<section class="sectionMitad">
								<label for="text_caducidad">Caducidad</label>
								<div>
									<input type="text" id="text_caducidad" name="text_caducidad" onKeyPress="return validarSinComas(event);" />
									<span>Meses</code></span>
								</div>
							</section>
						</fieldset>
					</div> <!-- end tab datosGenearles -->
					<div id="tab_imagen">
						<fieldset>
							<section>
								<input id="upload" type="file" size="45" name="upload" class="input">
								<p>Seleccione una imagen, y haga click en el bot&oacute;n INSERTAR</p>
								<button id="bot_subirImg" class="i_tick icon verdeOpilion" onClick="javascript:subeImagen();">Subir imagen</button>
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
									<th width="5%"><s:text name="producto.id"/></th>
									<th><s:text name="producto.nombre"/></th>
									<th width="15%"><s:text name="producto.cantidadMPs"/></th>
								</tr>
							</thead>
							<tbody id="tabla_MP_body">
							</tbody>
						</table>
					</div> <!-- end ingredientes -->
					<div id="tab_items"> <!-- TAB items -->
						<s:label name="%{getText('producto.label.seleccionMP')}" value="Seleccionar EANs13" />
						<s:select id="items" value="idProducto" key="idProducto" cssStyle="width:180px; text-align:left;" onchange="cargaItem();" label="%{getText('producto.label.seleccionMP')}" list="%{#session.productosCompuesto}" listKey="idProducto" listValue="nombre" headerKey="0" headerValue="-- Seleccione un producto --"/>
						<table id="tabla_Items" class="flat" style="width: 100% !important;" cellpadding="2" cellspacing="2" border="1" style="display: none;">
							<thead>
								<tr id="cabeceraItems">
									<th width="5%"><s:text name="producto.id"/></th>
									<th><s:text name="producto.nombre"/></th>
									<th width="15%"><s:text name="producto.cantidadENs"/></th>
								</tr>
							</thead>
							<tbody id="tabla_Items_body">
							</tbody>
						</table>
					</div> <!-- end items -->
					<div id="tab_envases"> <!-- TAB envases -->
						<s:label name="%{getText('producto.label.seleccionEN')}" value="%{getText('producto.label.seleccionEN')}"/>
						<s:select id="envases" key="envases" cssStyle="width:180px;" onchange="cargaEnvase();" label="%{getText('producto.label.seleccionEN')}" list="%{#session.listaenvases}" listKey="idEnvase" listValue="nombre" headerKey="0" headerValue="-- Seleccione un envase --" cssStyle="text-align:left;"/>
						 <table id="tabla_EN" class="flat" style="width: 100% !important;" cellpadding="2" cellspacing="2" border="1" style="display: none;">
							<thead>
								<tr id="cabeceraENs">
									<th width="5%"><s:text name="producto.id"/></th>
									<th><s:text name="producto.nombre"/></th>
									<th width="15%"><s:text name="producto.cantidadENs"/></th>
								</tr>
							</thead>
							<tbody id="tabla_EN_body">
							</tbody>
						</table>
					</div> <!-- end envases -->
				</div> <!-- END CLASS TAB -->
			</div> <!-- END widget_tabs -->
		</s:form>
	<!-- end divNecesarioWidget -->
</s:i18n>