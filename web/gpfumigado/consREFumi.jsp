<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="rfum.paso2" /><span class="screenCode">CONS_GP_FUMI_II</span></h3>
	<div id="divNecesarioWidget">
		<s:form action="InseREFumi" validate="true">
			<fieldset><!-- Filtro Campos-->
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
							<s:iterator id="ingred" value="%{#session.listaregistrosfumigados}">
								<tr>
									<td><s:property value="orden" /></td>
									<td><s:property value="fecha" /></td>
									<td><s:property value="descProducto" /></td>
									<td><s:property value="cantidadProducto" /></td>
									<td>
										<a href="javascript:fumigar('<s:property value="orden" />');"><img src="img/note_go.png" alt="Fumigar" title="Fumigar" /></a>
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