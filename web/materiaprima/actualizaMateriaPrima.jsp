<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Actualizar materia prima<span class="screenCode">ACTUALIZA_MP</span></h3>
	<div id="divNecesarioWidget">
		<s:form id="formu" key="formu" action="ActualizarMateriaPrima" method="post" validate="true">
			<s:iterator id="entry" value="%{#session.listaregistros}">
				<s:hidden id="idGrupo" name="idGrupo" key="idGrupo" />
				<s:hidden id="idMateriaPrima" name="idMateriaPrima" key="idMateriaPrima"/>
				<fieldset>
					<section>
						<label for="text_nombre"><s:text name="registroCliente.nombre"/></label>
						<div>
							<input type="text" id="text_nombre" name="text_nombre" value="<s:property value="nombre" />"/>
						</div>
					</section>
				</fieldset>
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
				<fieldset>
					<label>Categorias</label>
					<section>
						<label for="dropdown_categorias">Anadir categor&iacute;a</label>
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
					<table id="tablaCategorias" class="flat" style="width: 100% !important;" cellpadding="2" cellspacing="2" border="1">
						<thead>
							<th width="5%">Id</th>
							<th width="95%">Nombre</th>
						</thead>
						<tbody id="bodyCategorias">
							<s:iterator id="categorias" value="%{#session.categorias}">
								<tr id="categoria_<s:property value='idCategoria' />">
									<input id="<s:property value='idCategoria' />" name="<s:property value='idCategoria' />" value="<s:property value='idCategoria' />" style="display:none;" class="categoriasSeleccion"></input>
									<td id="<s:property value='idCategoria' />" name="<s:property value='idCategoria' />"><s:property value='idCategoria' /></td><td><s:property value='nombreCategoria' /></td>
									<td><a href="javascript:eliminaCategoria('<s:property value='idCategoria' />')" title="Eliminar este ingrediente"><img src="img/cancel.png" alt="Eliminar" title="Eliminar ingrediente"/></a>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</fieldset>
			</s:iterator>
		</s:form>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>