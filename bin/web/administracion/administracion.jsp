<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<div class="g12 ui-sortable">
		<div id="widget_consAdmin" class="widget">
			<h3 class="handle"><s:label name="%{getText('administracion.titulo')}" value="%{getText('administracion.titulo')}" /><span class="screenCode"><s:label name="%{getText('administracion.idPantalla')}" value="%{getText('administracion.idPantalla')}" /></span></h3>
			<div id="content" >
				<div id="divAdministracion">
					<s:form id="formulario" name="formulario" action="IngresarRegistroCliente" method="post" validate="true">
						<div class="widget" id="widget_tabs"> <!-- CONTIENE LAS TABS -->
							<div class="tab">
								<ul>
									<li>
										<a href="#tab_configuracion" onclick="javascript:otraPestania();"><s:text name="administracion.configuracion" /></a>
									</li>
									<li>
										<a href="#tab_roles" onclick="javascript:otraPestania();"><s:text name="administracion.gestionRoles" /></a>
									</li>
									<li>
										<a href="#tab_usuarios" onclick="javascript:pestaniaUsuarios();"><s:text name="administracion.gestionUsuarios" /></a>
									</li>
								</ul>
								<div id="tab_configuracion"> <!-- TAB CONFIGURACION -->
									<s:iterator id="empresa" value="%{#session.empresa}">
										<fieldset>
											<div style="text-align: center;">
												<div style="margin-top: 20px; width: 100%;">
													<img src="img/logo_tierrina_2.gif" style="width: 400px; height: 220px;" />
												</div>
											</div>
											<section>
												<label id="labelRegistroSanitario" for="text_registroSanitario">Registro sanitario</label>
												<div>
													<input type="text" id="text_registroSanitario" name="text_registroSanitario" title="Registro sanitario" style="width: 99%;" value="<s:property value="%{#empresa.registroSanitario}" />" />
												</div>
											</section>
											<section>
												<label id="labelLOPDFactura" for="text_LOPDFactura">LOPD facturas</label>
												<div>
													<textarea id="text_LOPDFactura" name="text_LOPDFactura" style="width: 99%;"><s:property value="%{#empresa.lopdFactura}" /></textarea>
												</div>
											</section>
										</fieldset>
									</s:iterator>
								</div> <!-- END TAB CONFIGURACION -->
								<div id="tab_roles"> <!-- TAB ROLES -->
									<fieldset>
										<table summary="Posibles acciones a realizar en el sistema y relaci&oacute;n de roles que las pueden ejecutar." class="display">
											<caption>Permisos de roles sobre las acciones del sistema</caption>
											<thead>
												<tr>
													<th>Acci&oacute;n</th>
													<s:iterator id="roles" value="%{#session.gestionRoles.roles}">
														<th><s:property value="%{#roles.nombre}" /></th>
													</s:iterator>
												</tr>
											</thead>
											<tbody>
												<s:iterator id="acciones" value="%{#session.gestionRoles.acciones}">
													<s:if test="%{#acciones.idPadre==0}" >
														<tr id="filaAccion_<s:property value="%{#acciones.idAccion}" />" style="height: 50px;" onclick="expanderFila(<s:property value="%{#acciones.idAccion}" />);" class="filaAccionOcultando">
															<td id="celdaAccionPadre_<s:property value="%{#acciones.idAccion}" />" class="celdaAccionPadre" colspan="<s:property value="%{#session.gestionRoles.numeroRoles}" />" style="vertical-align: middle; background-color: Bisque;"><span style="text-transform: uppercase; font-weight: bold;"><s:property value="%{#acciones.nombreAccion}" /></span> <img id="imagenCeldaAccion_<s:property value="%{#acciones.idAccion}" />" src="./img/j_arrow_down.png" /></td>
														</tr>
													</s:if> 
													<s:else>
														<tr id="filaAccion_<s:property value="%{#acciones.idAccion}" />" class="filaAccion filaAccionPadre_<s:property value="%{#acciones.idPadre}" />" style="height: 50px; display: none;">
															<th style="width: 35%; vertical-align: middle;"><s:property value="%{#acciones.nombreAccion}" /></th>
															<s:iterator id="roles" value="%{#session.gestionRoles.roles}">
																<td id="celdaAccionRol_<s:property value="%{#acciones.idAccion}" />_<s:property value="%{#roles.idRol}" />" style="width: 10%;" onclick="javascript:cambiaPermisos('<s:property value="%{#acciones.idAccion}" />', '<s:property value="%{#roles.idRol}" />');">
																	<img id="imgAccionRol_<s:property value="%{#acciones.idAccion}" />_<s:property value="%{#roles.idRol}" />" src="./img/no.png" style="width: 40px;"></img>
																	<s:hidden id="ocultoAccionRol_%{#acciones.idAccion}_%{#roles.idRol}" value="no" />
																</td>
															</s:iterator>
														</tr>
													</s:else>
												</s:iterator>
											</tbody>
										</table>
									</fieldset>
								</div> <!-- END TAB ROLES -->
								<div id="tab_usuarios"> <!-- TAB USUARIOS -->
									<fieldset>
										<table summary="Gesti&oacute;n de los usuarios del sistema y sus roles." class="display">
											<caption>Gesti&oacute;n de los usuarios del sistema y sus roles.</caption>
											<thead>
												<tr>
													<th>Usuario</th>
													<s:iterator id="roles" value="%{#session.gestionRoles.roles}">
														<th><s:property value="%{#roles.nombre}" /></th>
													</s:iterator>
												</tr>
											</thead>
											<tbody>
												<s:iterator id="usuarios" value="%{#session.usuarios}">
													<tr id="filaUsuario_<s:property value="%{#usuarios.idUsuario}" />" class="filaUsuario" style="height: 50px;">
														<th style="width: 35%; vertical-align: middle;"><s:property value="%{#usuarios.login}" /></th>
														<s:iterator id="roles" value="%{#session.gestionRoles.roles}">
															<td id="celdaUsuarioRol_<s:property value="%{#usuarios.idUsuario}" />_<s:property value="%{#roles.idRol}" />" style="width: 10%;" onclick="javascript:cambiaRol('<s:property value="%{#usuarios.idUsuario}" />', '<s:property value="%{#roles.idRol}" />');">
																<img id="imgUsuarioRol_<s:property value="%{#usuarios.idUsuario}" />_<s:property value="%{#roles.idRol}" />" src="./img/transparente.png" style="width: 40px;"></img>
																<s:hidden id="ocultoUsuarioRol_%{#usuarios.idUsuario}_%{#roles.idRol}" value="no" />
															</td>
														</s:iterator>
													</tr>
												</s:iterator>
											</tbody>
										</table>
									</fieldset>
								</div> <!-- END TAB USUARIOS -->
							</div> <!-- END CLASS TAB -->
						</div> <!-- END widget_tabs -->
					</s:form>
					<div id="ocultosAdministracion" style="display: none;">
						<s:iterator id="acciones" value="%{#session.gestionRoles.acciones}">
							<s:iterator id="rolesPermitidos" value="%{#acciones.rolesPermitidos}">
								<s:hidden id="%{#acciones.idAccion}_%{#rolesPermitidos.idRol}" cssClass="rolAccede_%{#acciones.idAccion}" />
							</s:iterator>
						</s:iterator>
						<s:iterator id="usuarios" value="%{#session.usuarios}">
							<s:iterator id="rolesUsuarios" value="%{#usuarios.roles}">
								<s:hidden id="%{#usuarios.idUsuario}_%{#rolesUsuarios.idRol}" cssClass="rolUsuario_%{#usuarios.idUsuario}" />
							</s:iterator>
						</s:iterator>
					</div>
				</div> <!-- END divAdministracion -->
			</div> <!-- END content -->
		</div> <!-- end widget_consProv -->
	</div> <!-- end g12 widgets ui-sortable -->
</s:i18n>