<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<div class="g12 ui-sortable">
		<div id="widget_consAdmin" class="widget">
			<h3 class="handle">Administraci&oacute;n de rutas<span class="screenCode">ADMIN_RUTAS</span></h3>
			<div id="content" >
				<div id="divAdministracion">
					<s:form id="formulario" name="formulario" action="IngresarRegistroCliente" method="post" validate="true">
						<div class="widget" id="widget_tabs"> <!-- CONTIENE LAS TABS -->
							<table id="tablaRutas" class="display" style="width: 100% !important; margin-left: auto; margin-right: auto;" cellpadding="2" cellspacing="2" border="1" style="display: none;">
								<caption>Rutas</caption>
								<thead>
									<tr>
										<th>Nombre</th>
										<th>Comercial (Defecto)</th>
									</tr>
								</thead>
								<tbody id="tbodyRutas">
									<s:iterator id="rutas" value="%{#session.rutas}" status="itStatus">
										<tr id="ruta_<s:property value="#itStatus.count"/>" class="rutas" onClick="javascript:rutaSeleccionada(<s:property value="idRuta"/>);">
											<input type="hidden" id="idRuta_<s:property value="#itStatus.count"/>" name="idRuta_<s:property value="#itStatus.count"/>" value="<s:property value="idRuta"/>" />
											<td>
												<s:property value="nombre"/>
											</td>
											<td>
												<s:property value="comercialDefecto"/>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div> <!-- END widget_tabs -->
					</s:form>
				</div> <!-- END divAdministracion -->
			</div> <!-- END content -->
		</div> <!-- end widget_consProv -->
	</div> <!-- end g12 widgets ui-sortable -->
</s:i18n>