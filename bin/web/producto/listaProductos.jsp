<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consultar productos</h3>
	<div id="divNecesarioWidget">
		<p class="idPantalla"><span class="screenCode">CONS_PRODU</span></p>
		<s:form id="formulario" action="ConsultaProducto" validate="true">
			<fieldset><!-- Informacion Basica-->
				<label><s:text name="registro.label.datosconsulta"/></label>
				<section>
					<label for="dropdown_familias">Familia</label>
					<div>
						<select name="dropdown_familias" id="dropdown_familias" onchange="javascript:familiaSeleccionado();">
							<optgroup label="Familia">
								<s:iterator id="tipos" value="%{#session.listafamilias}">
									<option value="<s:property value="idFamilia" />">
										<s:property value="descripcionFamilia" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</div>
				</section>
				<section>
					<label for="dropdown_grupos">Grupo</label>
					<div>
						<select name="dropdown_grupos" id="dropdown_grupos" onchange="javascript:grupoSeleccionado();">
							<optgroup label="Grupo">
								<s:iterator id="tipos" value="%{#session.listagrupos}">
									<option value="<s:property value="idGrupo" />">
										<s:property value="nombreGrupo" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</div>
				</section>
				<section>
					<label for="dropdown_categorias">Categoria</label>
					<div>
						<select name="dropdown_categorias" id="dropdown_categorias" onchange="javascript:categoriaSeleccionado();">
							<optgroup label="Categoria">
								<s:iterator id="tipos" value="%{#session.listacategorias}">
									<option value="<s:property value="idCategoria" />">
										<s:property value="nombreCategoria" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</div>
				</section>
				<section>
					<label for="dropdown_estados">Estado</label>
					<div>
						<select name="dropdown_estados" id="dropdown_estados" onchange="javascript:estadoSeleccionado();">
							<optgroup label="Estado">
								<s:iterator id="tipos" value="%{#session.listaestados}">
									<option value="<s:property value="idEstado" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</div>
				</section>
				<br />
				<br />
				<button class="i_magnifying_glass icon verdeOpilion" onclick="javascript:filtraProveedores();">Consultar</button>
				<button class="i_pdf_document icon verdeOpilion" onclick="javascript:reporteProveedores();">Ver en PDF</button>
			</fieldset><!--end Informacion Basica-->
		</s:form>
		<div id="demo">
			<table id="tablaProductos" cellpadding="0" cellspacing="0" border="0" class="display">
			<thead>
				<tr>
					<th><s:label name="%{getText('cons.label.ID')}" value="%{getText('cons.label.ID')}" /></th>
					<th><s:label name="%{getText('cons.label.codigoEAN')}" value="%{getText('cons.label.codigoEAN')}" /></th>
					<th><s:label name="%{getText('cons.label.nombre')}" value="%{getText('cons.label.nombre')}" /></th>
					<th><s:label name="%{getText('cons.label.descripcion')}" value="%{getText('cons.label.descripcion')}" /></th>
					<th><s:label name="Stock minimo" value="Stock minimo" /></th>
					<th><s:label name="Stock minimo" value="Stock" /></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="entry" value="%{#session.listaproductos}">
					<tr>
						<td><s:property value="idProducto" /></td>
						<td><s:property value="codigoEan" /></td>
						<td><s:property value="nombre" /></td>
						<td><s:property value="descripcion" /></td>
						<td><s:property value="stockMinimo" /></td>
						<td><s:property value="stock" /></td>
						<td>
							<a href="MostrarProducto.action?idProducto=<s:property value="idProducto" />"><img src="img/edit.png" alt="Editar" title="Editar"/></a>
						</td>
						<td>
							<a href="EliminarProducto.action?idProducto=<s:property value="idProducto"/>"><img src="img/cancel.png" alt="Deshabilitar" title="Deshabilitar"/></a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
			</table>
			<p>&nbsp;</p>
		</div>
	<button class="i_arrow_left icon verdeOpilion" onclick="javascript:volverAlEscritorio();">Volver</button>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>