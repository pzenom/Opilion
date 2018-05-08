<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consultar productos<span class="screenCode">CONS_PROD</span></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form id="formulario" action="ConsultaProducto" validate="true">
				<s:hidden id="idGrupo" name="idGrupo" key="idGrupo" value="0" />
				<s:hidden id="idFamilia" name="idFamilia" key="idFamilia" value="0" />
				<s:hidden id="idCategoria" name="idCategoria" key="idCategoria" value="0" />
				<s:hidden id="idEstado" name="idEstado" key="idEstado" value="0" />
				<s:hidden id="idProducto" name="idProducto" value="0"/>
				<fieldset><!-- Informacion Basica-->
					<section class="sectionTercio">
						<label for="dropdown_familias">Familia</label>
						<div>
							<select name="dropdown_familias" id="dropdown_familias" onchange="javascript:filtraProductos();">
								<option value="0">Todas</option>
								<optgroup label="Familia">
									<s:iterator id="familias" value="%{#session.listafamilias}">
										<option value="<s:property value="idFamilia" />">
											<s:property value="descripcionFamilia" />
										</option>
									</s:iterator>
								</optgroup>
							</select>
						</div>
					</section>
					<section class="sectionTercio">
						<label for="dropdown_grupos">Grupo</label>
						<div>
							<select name="dropdown_grupos" id="dropdown_grupos" onchange="javascript:filtraProductos();">
								<option value="0">Todos</option>
								<optgroup label="Grupo">
									<s:iterator id="grupos" value="%{#session.listagrupos}">
										<option value="<s:property value="idGrupo" />">
											<s:property value="nombreGrupo" />
										</option>
									</s:iterator>
								</optgroup>
							</select>
						</div>
					</section>
					<section class="sectionTercio">
						<label for="dropdown_categorias">Categoria</label>
						<div>
							<select name="dropdown_categorias" id="dropdown_categorias" onchange="javascript:filtraProductos();">
								<option value="0">Todas</option>
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
				</fieldset><!--end Informacion Basica-->
			</s:form>
		</div> <!-- end consulta -->
		<p>&nbsp;</p>
		<div id="productos">
			<table id="tablaProductos" cellpadding="0" cellspacing="0" border="0" class="display" style="display: none;">
				<thead>
					<tr>
						<th><s:label name="%{getText('cons.label.ID')}" value="%{getText('cons.label.ID')}" /></th>
						<th><s:label name="%{getText('cons.label.codigoEAN')}" value="%{getText('cons.label.codigoEAN')}" /></th>
						<th><s:label name="%{getText('cons.label.nombre')}" value="%{getText('cons.label.nombre')}" /></th>
						<th><s:label name="%{getText('cons.label.descripcion')}" value="%{getText('cons.label.descripcion')}" /></th>
						<th><s:label name="%{getText('cons.label.stock')}" value="%{getText('cons.label.stock')}" /></th>
						<th><s:label name="%{getText('cons.label.stockAgrupado')}" value="%{getText('cons.label.stockAgrupado')}" /></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="entry" value="%{#session.listaproductos}">
						<tr>
							<td style="width: 4%"><s:property value="idProducto" /></td>
							<td style="width: 12%"><s:property value="codigoEan" /></td>
							<td style="width: 33%"><s:property value="nombre" /></td>
							<td style="width: 33%"><s:property value="descripcion" /></td>
							<td style="width: 7%">
								<span id="stock_<s:property value="idProducto" />" class="numeroMilesDecimal"><s:property value="stock" /></span>
							</td>
							<td style="width: 7%">
								<span id="stockAgrupado_<s:property value="idProducto" />" class="numeroMilesDecimal"><s:property value="stockAgrupado" /></span>
							</td>
							<td id="celdaCargarProducto_<s:property value="idProducto" />" class="celdaCargarProducto" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none; width: 3%;">
								<a href="javascript:cargarProducto('<s:property value="idProducto" />');"><img src="img/edit.png" alt="Editar" title="Editar"/></a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<p>&nbsp;</p>
		</div> <!-- end productos -->
	</div> <!-- end divNecesarioWidget -->
</s:i18n>