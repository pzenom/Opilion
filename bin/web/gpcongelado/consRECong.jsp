<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="rcon.paso2" /> - CONS_GP_CONG_II</h3>
	<div id="divNecesarioWidget">
		<s:form action="InseRECong" validate="true">
			<fieldset><!-- Filtro Campos-->
				<label>Entradas</label>
				<div id="demo">
					<table id="tablaRegistros" cellpadding="0" cellspacing="0" border="0" class="display">
						<thead>
							<tr>
								<th>C&oacute;digo entrada</th>
								<th>Fecha</th>
								<th>Nombre Producto</th>
								<th>Stock Producto</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator id="ingred" value="%{#session.listaregistroscongelados}">
								<tr>
									<td><s:property value="orden" /></td>
									<td><s:property value="fecha" /></td>
									<td><s:property value="descProducto" /></td>
									<td><s:property value="cantidadProducto" /></td>
									<td>
										<a href="javascript:congelar('<s:property value="orden" />');"><img src="img/note_go.png" alt="Congelar" title="Congelar" /></a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div> <!-- end demo -->
			</fieldset>
		</s:form>
	</div> <!-- end divNecesarioWidget -->
	<p>&nbsp;</p>
</s:i18n>