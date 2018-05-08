<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:iterator id="ruta" value="%{#session.rutaCargada}">
		<h3 class="handle">Mostrar ruta<span class="screenCode">MOSTRAR_RUTA</span></h3>
		<div id="content" >
			<s:hidden id="idRuta" key="idRuta" name="idRuta"/>
			<s:form target="ajaxDiv" id="formuAjax" name="formuAjax" action="ConsultaClientes" >
				<div>
					<fieldset>
						<section class="sectionMitad">
							<label for="texto_nombre">Nombre</label>
							<div>
								<input type="text" id="texto_nombre" name="texto_nombre" value="<s:property value="nombre" />"/>
							</div>
						</section>
						<section class="sectionMitad">
							<label for="dropdown_comerciales">Comercial por defecto</label>
							<div>
								<s:select id="dropdown_comerciales" key="idUsuario" label="Comercial por defecto" list="%{#session.comerciales}" listKey="idUsuario" listValue="login" headerKey="0" headerValue="-- Seleccione un comercial --" cssStyle="width:195px;"/>
							</div>
						</section>
						<br />
						<table id="tablaClientes" class="display" style="width: auto !important; margin-left: auto; margin-right: auto;" cellpadding="2" cellspacing="2" border="1" style="display: none;">
							<caption>Clientes en la ruta</caption>
							<thead>
								<tr>
									<th>Nombre</th>
								</tr>
							</thead>
							<tbody id="tbodyClientes">
								<s:iterator id="clientes" value="%{#ruta.clientesEnRuta}" status="itStatus">
									<tr id="ruta_<s:property value="#itStatus.count"/>" class="clientes">
										<td>
											<s:property value="nombre"/>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</fieldset>
				</div>
			</s:form>
		</div> <!-- END content -->
	</s:iterator>
</s:i18n>