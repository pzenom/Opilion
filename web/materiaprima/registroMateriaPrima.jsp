<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Nueva materia prima<span class="screenCode">REGISTRO_MP</span></h3>
	<div id="divNecesarioWidget">
		<s:form id="formu" key="formu" name="formu" action="IngresarRegistroMateriaPrima" method="post" validate="true">
			<s:hidden id="idGrupo" name="idGrupo" value="0"/>
			<s:hidden id="idMateriaPrima" name="idMateriaPrima" key="idMateriaPrima" value="0"/>
			<fieldset>
				<section class="sectionMitad">
					<label for="text_nombre"><s:text name="registroCliente.nombre"/></label>
					<div>
						<input type="text" id="text_nombre" name="text_nombre" />
					</div>
				</section>
				<section class="sectionMitad">
					<label for="dropdown_categorias">Categor&iacute;a</label>
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
			</fieldset>
			<fieldset>
				<table id="tablaCategorias" class="flat" style="width: 100% !important;" cellpadding="2" cellspacing="2" border="1">
					<thead>
						<th width="5%">Id</th>
						<th width="95%">Nombre</th>
					</thead>
					<tbody id="bodyCategorias">
					</tbody>
				</table>
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
		</s:form>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>