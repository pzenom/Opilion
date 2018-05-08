<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Nueva entidad<span class="screenCode">REG_ENTI</span></h3>
	<div id="content" >
		<div id="registroCliente">
			<s:form id="formulario" name="formulario" action="IngresarRegistroCliente" method="post" validate="true">
				<div class="widget" id="widget_tabs" style="display: none;"> <!-- CONTIENE LAS TABS -->
					<div id="contenedorRegistrarEntidad">
						<ul class="breadcrumb" data-connect="breadcrumbcontent">
							<li>
								<a id="breadcrumb_entidad" onclick="javascript:seleccionBreadcrumbEntidad();">Entidad</a>
							</li>
							<li id="liDatosGenerales" style="display: none;">
								<a id="breadDatosGenerales" onclick="javascript:seleccionDatosGenerales();">Datos b&aacute;sicos</a>
							</li>
							<li id="liContactos" style="display: none;">
								<a onclick="javascript:seleccionContactos();"><s:text name="registroCliente.contactos"/></a>
							</li>
							<li id="liDirecciones" style="display: block;">
								<a onclick="javascript:seleccionDirecciones();"><s:text name="registroCliente.direcciones"/></a>
							</li>
							<li id="liDatosBancarios" style="display: block;">
								<a onclick="javascript:seleccionBancarios();"><s:text name="registroCliente.datosBancarios"/></a>
							</li>
							<li id="liOtros" style="display: block;">
								<a onclick="javascript:ocultarTablas();">Otros</a>
							</li>
						</ul>
						<div class="widget" id="widget_1">
							<div id="breadcrumbcontent" style="padding-bottom: 50px;">
								<div> <!-- PRIMER BREADCRUM -->
									<div id="divClienteProveedor">
										<h3>La nueva entidad, &iquest;es un cliente, un proveedor, o ambos a la vez?</h3>
										<p>
											Selecciona el bot&oacute;n que corresponda
										</p>
										<div>
											<div style=" text-align: center;">
												<button id="bot_cliente" onclick="javascript:entidadCliente();" title="La nueva entidad es un cliente" alt="La nueva entidad es un cliente"></button>
												<button id="bot_proveedor" onclick="javascript:entidadProveedor();" title="La nueva entidad es un proveedor" alt="La nueva entidad es un proveedor"></button>
											</div>
										</div>
									</div> <!-- end divClienteProveedor -->
									<div id="divAutonomoEmpresa" style="display: none;">
										<h3>La nueva entidad, &iquest;es un aut&oacute;nomo o una empresa?</h3>
										<p>
											Selecciona el bot&oacute;n que corresponda
										</p>
										<div>
											<div style=" text-align: center;">
												<button id="bot_autonomo" onclick="javascript:entidadAutonomo();" title="La nueva entidad es un aut&oacute;nomo" alt="La nueva entidad es un aut&oacute;nomo"></button>
												<button id="bot_empresa" onclick="javascript:entidadEmpresa();" title="La nueva entidad es una empresa" alt="La nueva entidad es una empresa"></button>
											</div>
										</div>
									</div> <!-- end divAutonomoEmpresa -->
									<div id="botonesPrevNext1">
										<button id="btPrev1" onclick="javascript:prevPrimerBreadcrumb();" style="float: left; display: none;" title="Ir a la pantalla anterior" alt="Ir a la pantalla anterior">Anterior</button>
										<button id="prev1" class="prev" style="display: none;">Anterior</button>
										<button onclick="javascript:nextPrimerBreadcrumb();" style="float: right;" title="Ir a la siguiente pantalla" alt="Ir a la siguiente pantalla">Siguiente</button>
										<button id="next1" class="next" style="display: none;">next</button>
									</div>
								</div> <!-- END PRIMER BREADCRUMB -->
								<div> <!-- TERCER BREADCRUM -->
									<h3>Datos b&aacute;sicos</h3>
									<p>
										Introduzca la informaci&oacute;n general de la entidad
										<fieldset>
											<section class="sectionMitad">
												<label id="labelNombre" for="text_nombre" style="color:red;">Seleccione aut&oacute;nomo o empresa</label>
												<div>
													<input type="text" id="text_nombre" name="text_nombre" readonly="true" title="Seleccione aut&oacute;nomo o empresa">
													<span id="spanNombre" style="color:red;">Seleccione aut&oacute;nomo o empresa</code></span>
												</div>
											</section>
											<section class="sectionMitad">
												<label id="labelApellidos" for="text_apellidos" style="color:red;">Seleccione aut&oacute;nomo o empresa</label>
												<div>
													<input type="text" id="text_apellidos" name="text_apellidos" readonly="true" title="Seleccione aut&oacute;nomo o empresa">
													<span id="spanApellidos" style="color:red;">Seleccione aut&oacute;nomo o empresa</code></span>
												</div>
											</section>
											<section class="sectionTercio">
												<label for="text_DNI"><s:text name="registroCliente.dniCif"/></label>
												<div>
													<input type="text" id="text_DNI" name="text_DNI" title="<s:text name="registroCliente.introducirDNI"/>" />
													<span><s:text name="registroCliente.introducirDNI"/></code></span>
												</div>
											</section>
											<section class="sectionTercio">
												<label for="text_EAN"><s:text name="registroCliente.ean"/></label>
												<div>
													<input type="text" id="text_EAN" name="text_EAN" title="<s:text name="registroCliente.introducirEan"/>" onKeyPress="return validarSoloNumeros(event);" />
													<span><s:text name="registroCliente.introducirEan"/></code></span>
												</div>
											</section>
											<section class="sectionTercio">
												<label for="text_web"><s:text name="registroCliente.web"/></label>
												<div>
													<input type="text" id="text_web" name="text_web" title="<s:text name="registroCliente.introducirWeb"/>" type="url" data-instant="true">
													<span><s:text name="registroCliente.introducirWeb"/></code></span>
												</div>
											</section>
											<section id="sectionSectorCliente" style="display: none;">
												<label for="dropdown_sectores">Sector del cliente</label>
												<div>
													<select name="dropdown_sectores" id="dropdown_sectores" title="Sector del cliente">
														<option value="0">Seleccione el sector del cliente</option>
														<optgroup label="Sectores">
															<s:iterator id="tipos" value="%{#session.listasectores}">
																<option value="<s:property value="idSector" />">
																	<s:property value="nombreSector" />
																</option>
															</s:iterator>
														</optgroup>
													</select>
												</div>
											</section>
											<section id="sectionTipoProveedor" style="display: none;">
												<label for="dropdown_tipos">Tipo de proveedor</label>
												<div>
													<select name="dropdown_tipos" id="dropdown_tipos" onchange="javascript:proveedorSeleccionado();" title="Tipo de proveedor">
														<option value="0">Seleccione el tipo de proveedor</option>
														<optgroup label="Tipos">
															<s:iterator id="tipos" value="%{#session.listatipos}">
																<option value="<s:property value="idTipo" />">
																	<s:property value="nombre" />
																</option>
															</s:iterator>
														</optgroup>
													</select>
												</div>
												<div id="divTablaTiposProveedor">
													<table id="tablaTiposProveedor" class="flat" style="width: auto !important; margin-left: auto; margin-right: auto;">
														<caption>Tipos del proveedor</caption>
														<thead>
															<tr>
																<th style="width: 200px;" >Tipo</th>
															</tr>
														</thead>
														<tbody id="tbodyTiposProveedor">
														</tbody>
													</table>
												</div>
											</section>
										</fieldset>
										<!-- <button class="next">Siguiente</button> -->
									</p>
									<div id="botonesPrevNext2">
										<button id="btPrev2" onclick="javascript:prevSegundoBreadcrumb();" style="float: left;" title="Ir a la pantalla anterior" alt="Ir a la pantalla anterior">Anterior</button>
										<button id="prev2" class="prev" style="display: none;">Anterior</button>
										<button onclick="javascript:nextSegundoBreadcrumb();" style="float: right;" title="Ir a la siguiente pantalla" alt="Ir a la siguiente pantalla">Siguiente</button>
										<button id="next2" class="next" style="display: none;">next</button>
									</div>
								</div> <!-- END TERCER BREADCRUMB -->
								<div> <!-- CUARTO BREADCRUMB -->
									<h3>Contactos</h3>
									<p>
										Introduzca los contactos de la entidad
										<div id="tfnos" class="botonera" style="clear: left;">
											<button id="addTfno" class="i_plus icon verdeOpilion" onclick="javascript:nuevoTfno();">Nuevo tel&eacute;fono</button>
										</div>
										<fieldset id="nuevo_tfno" style="display: none;">
											<label>Nuevo telefono</label>
											<section>
												<label for="tipo_tfno">Tipo</label>
												<div>
													<select id="tipo_tfno" name="tipo_tfno">
														<option value="0">Seleccione el tipo de tfno.</option>
														<option value="Casa">Casa</option>
														<option value="Movil">Movil</option>
														<option value="Trabajo">Trabajo</option>
														<option value="Telefono de la empresa">Telefono de la empresa</option>
														<option value="Fax de casa">Fax de casa</option>
														<option value="Fax del trabajo">Fax del trabajo</option>
														<option value="Buscapersonas">Buscapersonas</option>
														<option value="Otro">Otro</option>
													</select>
												</div>
											</section>
											<section class="sectionMitad"><label for="text_numero">N&uacute;mero</label>
												<div>
													<input type="text" id="text_numero" name="text_numero" title="Numero">
													<span>Introduzca el n&uacute;mero</span>
												</div>
											</section>
											<section class="sectionMitad"><label for="text_notasTfno">Notas</label>
												<div>
													<input type="text" id="text_notasTfno" name="text_notasTfno" title="Notas">
													<span>Introduzca las notas</code></span>
												</div>
											</section>
											<div class="botonera" style="clear: left;">
												<button id="btAddTfno" class="i_tick icon verdeOpilion" onClick="javascript:addTelefono();">Guardar tel&eacute;fono</button>
												<button id="btEditTfno" class="i_tick icon verdeOpilion" onClick="editaTfno();">Aceptar cambios</button>
												<button id="btCancelTfno" class="i_cross icon rojoOpilion" onClick="hideFormularioTfno();">Descartar</button>
											</div> <!-- END BOTONERA -->
										</fieldset>
										<hr />
										<div id="mails" class="botonera" style="clear: left;">
											<button id="addEmail" class="i_plus icon verdeOpilion" onclick="javascript:addMail();">Nuevo email</button>
										</div>
										<fieldset id="nuevo_email" style="display: none;">
											<label>Nuevo email</label>
											<section>
												<label for="dropdown_tipoEmail">Tipo</label>
												<div>
													<select id="dropdown_tipoEmail" name="dropdown_tipoEmail">
														<option value="0">Seleccione el tipo de email</option>
														<option value="Personal">Personal</option>
														<option value="Trabajo">Trabajo</option>
														<option value="Otro">Otro</option>
													</select>
												</div>
											</section>
											<section class="sectionMitad"><label for="text_mail">Email</label>
												<div>
													<input type="text" id="text_mail" name="text_mail" title="Email">
													<span>Introduzca el email</span>
												</div>
											</section>
											<section class="sectionMitad"><label for="text_notasEmail">Notas</label>
												<div>
													<input type="text" id="text_notasEmail" name="text_notasEmail" title="Notas">
													<span>Introduzca las notas</code></span>
												</div>
											</section>
											<div class="botonera" style="clear: left;">
												<button id="btAddEmail" class="i_tick icon verdeOpilion" onClick="javascript:addNuevoEmail();">Guardar email</button>
												<button id="btEditEmail" class="i_tick icon verdeOpilion" onClick="editarEmail();">Aceptar cambios</button>
												<button id="btCancelEmail" class="i_cross icon rojoOpilion" onClick="hideFormularioEmail();">Descartar</button>
											</div> <!-- END BOTONERA -->
										</fieldset>
										<!-- <button class="next">Siguiente</button> -->
									</p>
									<div id="botonesPrevNext3">
										<button id="btPrev3" onclick="javascript:prevTercerBreadcrumb();" style="float: left;" title="Ir a la pantalla anterior" alt="Ir a la pantalla anterior">Anterior</button>
										<button id="prev3" class="prev" style="display: none;">Anterior</button>
										<button onclick="javascript:nextTercerBreadcrumb();" style="float: right;" title="Ir a la siguiente pantalla" alt="Ir a la siguiente pantalla">Siguiente</button>
										<button id="next3" class="next" style="display: none;">next</button>
									</div>
								</div> <!-- END CUARTO BREADCRUMB -->
								<div> <!-- QUINTO BREADCRUMB -->
									<h3>Direcciones</h3>
									<p>
										Introduzca las direcciones de la entidad
										<div id="dirs" class="botonera" style="clear: left;">
											<button id="addDirContacto" class="i_plus icon verdeOpilion" onclick="javascript:addDireccionContacto();"><s:text name="registroCliente.nuevaDireccion"/></button>
										</div>
										<fieldset id="nueva_direccion" style="display: none;">
											<label><s:text name="registroCliente.nuevaDireccion"/></label>
											<section class="sectionMitad">
												<label for="dropdown_tiposDire"><s:text name="registroCliente.tipoDireccion"/></label>
												<div>
													<select name="dropdown_tiposDire" id="dropdown_tiposDire" title="Tipo de direccion">
														<option value="0">Seleccione el tipo de direcci&oacute;n</option>
														<optgroup label="Tipo">
															<option value="E">Env&iacute;o</option>
															<option value="F">Facturaci&oacute;n</option>
															<option value="A">Env&iacute;o y facturaci&oacute;n</option>
														</optgroup>
													</select>
												</div>
											</section>
											<section class="sectionMitad">
												<label for="dropdown_provincias">Provincia</label>
												<div>
													<select name="dropdown_provincias" id="dropdown_provincias" title="Provincias">
														<optgroup label="Provincia">
															<s:iterator id="tipos" value="%{#session.listaprovincias}">
																<option value="<s:property value="idProvincia" />">
																	<s:property value="nombre" />
																</option>
															</s:iterator>
														</optgroup>
													</select>
												</div>
											</section>
											<section class="sectionMitad"><label for="text_eanDireccion">EAN direcci&oacute;n</label>
												<div>
													<input type="text" id="text_eanDireccion" name="text_eanDireccion" title="EAN" onKeyPress="return validarSoloNumeros(event);">
													<span>Introduzca el c&oacute;digo EAN de la direcci&oacute;n</span>
												</div>
											</section>
											<section class="sectionMitad"><label for="text_horario">Horario</label>
												<div>
													<input type="text" id="text_horario" name="text_horario" title="Horario">
													<span>Introduzca el horario</code></span>
												</div>
											</section>
											<section><label for="text_calle">Calle</label>
												<div>
													<input type="text" id="text_calle" name="text_calle" title="Calle">
													<span>Introduzca la calle</code></span>
												</div>
											</section>
											<section class="sectionTercio">
												<label for="text_localidad">Localidad</label>
												<div>
													<input type="text" id="text_localidad" name="text_localidad" title="Localidad">
													<span>Introduzca la localidad</code></span>
												</div>
											</section>
											<section class="sectionTercio">
												<label for="text_municipio">Municipio</label>
												<div>
													<input type="text" id="text_municipio" name="text_municipio" title="Municipio">
													<span>Introduzca el municipio</code></span>
												</div>
											</section>
											<section class="sectionTercio">
												<label for="text_codigoPostal">C&oacute;digo postal</label>
												<div>
													<input type="text" id="text_codigoPostal" name="text_codigoPostal" title="C&oacute;digo postal">
													<span>Introduzca el c&oacute;digo postal</code></span>
												</div>
											</section>
											<p>&nbsp;</p>
											<div class="botonera" style="clear: left;">
												<button id="btAddDirCon" class="i_tick icon verdeOpilion" onClick="javascript:guardarDireccion();">Guardar direcci&oacute;n</button>
												<button id="btEditDirCon" class="i_tick icon verdeOpilion" onClick="editDirContacto();">Aceptar cambios</button>
												<button id="btCancelDirCon" class="i_cross icon rojoOpilion" onClick="hideFormularioDireccion();">Descartar</button>
											</div>
										</fieldset>
										<!-- <button class="next">Siguiente</button> -->
									</p>
									<div id="botonesPrevNext4">
										<button id="btPrev4" onclick="javascript:prevCuartoBreadcrumb();" style="float: left;" title="Ir a la pantalla anterior" alt="Ir a la pantalla anterior">Anterior</button>
										<button id="prev4" class="prev" style="display: none;">Anterior</button>
										<button onclick="javascript:nextCuartoBreadcrumb();" style="float: right;" title="Ir a la siguiente pantalla" alt="Ir a la siguiente pantalla">Siguiente</button>
										<button id="next4" class="next" style="display: none;">next</button>
									</div>
								</div> <!-- END QUINTO BREADCRUMB -->
								<div id="divDatosBancarios">
									<h3>Datos bancarios</h3>
									<p>
										Introduzca las formas de pago de la entidad
										<div id="formasPago" class="botonera" style="clear: left;">
											<button id="btNuevaFormaPago" class="i_plus icon verdeOpilion" onclick="javascript:nuevaFormaPago();">Nueva forma de pago</button>
										</div>
										<fieldset id="nueva_formaPago" style="display: none;">
											<label>Nueva forma de pago</label>
											<section>
												<label for="dropdown_formasPago">Forma pago</label>
												<div>
													<div style="position: relative;">
														<select name="dropdown_formasPago" id="dropdown_formasPago" title="Formas de pago">
															<option value="-1">Seleccione una forma de pago</option>
															<optgroup label="Forma de pago" id="optgroup_dropdown_formasPago">
																<s:iterator id="tipos" value="%{#session.listaformaspago}">
																	<option value="<s:property value="idFormaPago" />">
																		<s:property value="descripcion" />
																	</option>
																</s:iterator>
															</optgroup>
														</select>
													</div>
													<div id="contenedorFormaPagoDias" style="display: block; margin-top:-3em; position: relative;">
														<div style="margin-left:17em; position:absolute;">
															<span style="margin-top: 8px;"> a </span>
															<input type="number" id="text_diasFormaPago" name="text_diasFormaPago" title="Dias" style="width: 40px;" class="integer" value="0" data-min="0"/>
															<span style="margin-top: 8px;">d&iacute;as</span>
														</div>
														<div style="position: absolute; margin-left:25em;">
															<div style="position: relative; float: left; margin-top: 8px;">
																<span>desde</span>
															</div>
															<div style="position: relative; float: left;">
															<select name="dropdown_formaPagoDesde" id="dropdown_formaPagoDesde" title="Desde">
																<option value="0">Seleccione desde cuando se cuentan los d&iacute;as</option>
																<optgroup label="Desde" id="optgroup_dropdown_formasPagoDesde">
																	<s:iterator id="pagosDesde" value="%{#session.listaformaspagodesde}" status="itStatus">
																		<option class="opcionesFormaPagoDesde" id="idFormaPagoDesde_<s:property value="#itStatus.count"/>" value="<s:property value="idFormaPagoDesde" />">
																			<s:property value="descripcion" />
																		</option>
																	</s:iterator>
																</optgroup>
															</select>
															</div>
														</div>
													</div>
													<div style="margin-top: 45px; margin-bottom: -20px;">
														<div style="position: relative; float: left;">
															<span>Marque la casilla si la forma de pago tiene una cuenta bancaria asociada</span>
														</div>
														<div style="position:relative; float: left;">
															<input type="checkbox" id="cuentaAsociada" onchange="javascript:cuentaBancariaAsociada();"/>
														</div>
													</div>
												</div>
											</section>
											<section style="display: none;">
												<label for="dropdown_banco">Banco</label>
												<div>
													<div>
														<select name="dropdown_banco" id="dropdown_banco" title="Banco">
															<option value="0">Seleccione un banco</option>
															<optgroup id="optgroup_dropdown_banco" label="Banco">
																<s:iterator id="tipos" value="%{#session.listabancos}">
																	<option value="<s:property value="idBanco" />">
																		<s:property value="nombre" />
																	</option>
																</s:iterator>
															</optgroup>
														</select>
													</div>
												</div>
											</section>
											<section style="display: none;" id="sectionCuentaBancaria">
												<label for="text_cuenta">Cuenta</label>
												<div>
													<span>IBAN:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>Banco:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>Sucursal:</span>&nbsp;&nbsp;<span>DC:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>Num. Cuenta:</span><br/>
													<input type="text" id="cuenta_iban" name="cuenta_iban" maxlength="4" size="4" class="w_5 numerosCuenta" value="0000"/>
													<input type="text" id="cuenta_banco" name="cuenta_banco" required maxlength="4" size="4" class="w_5 numerosCuenta" />
													<input type="text" id="cuenta_ofici" name="cuenta_ofici" required maxlength="4" size="4" class="w_5 numerosCuenta" />
													<input type="text" id="cuenta_contr" name="cuenta_contr" required maxlength="2" size="2" class="w_3 numerosCuenta" style="width:3%" />
													<input type="text" id="cuenta_numer" name="cuenta_numer" required data-regex="^OK$" maxlength="10" size="10" class="w_10 numerosCuenta" />
													<a class="btn small" id="form_cuenta_validador" onClick="validar(this.form)">Validar</a>
													<br/><span id="spanBanco"></span>
												</div>
											</section>
											<section>
												<label for="diaPago">D&iacute;a de pago</label>
												<div>
													<input id="diaPago" name="diaPago" type="number" class="integer" data-min="0" data-max="28" value="0"/>
													<br><span>Introduzca el d&iacute;a de pago</span>
												</div>
											</section>
											<div class="botonera" style="clear: left;">
												<button id="btAddFormaPago" class="i_tick icon verdeOpilion" onClick="javascript:addFormaPagoEntidad();">Guardar forma de pago</button>
												<button id="btEditFormaPago" class="i_tick icon verdeOpilion" onClick="javascript:guardaCambiosFormaPago();">Aceptar cambios</button>
												<button id="btCancelFormaPago" class="i_cross icon rojoOpilion" onClick="hideFormularioFormaPago();">Descartar</button>
											</div> <!-- END BOTONERA -->
										</fieldset>
									</p>
									<div id="botonesPrevNext5">
										<button id="btPrev5" onclick="javascript:prevQuintoBreadcrumb();" style="float: left;" title="Ir a la pantalla anterior" alt="Ir a la pantalla anterior">Anterior</button>
										<button id="prev5" class="prev" style="display: none;">Anterior</button>
										<button onclick="javascript:nextQuintoBreadcrumb();" style="float: right;" title="Ir a la siguiente pantalla" alt="Ir a la siguiente pantalla">Siguiente</button>
										<button id="next5" class="next" style="display: none;">next</button>
									</div>
								</div> <!-- end breadcrumb divdatosbancarios -->
								<div id="divOtros">
									<fieldset>
										<section class="sectionTercio">
											<label for="text_limiteCredito">L&iacute;mite cr&eacute;dito</label>
											<div>
												<input type="text" id="text_limiteCredito" name="text_limiteCredito" title="Limite credito" onChange="javascript:ajustarDecimal('text_limiteCredito');" onKeyPress="return validarNumerosDecimales('text_limiteCredito', event);" />
												<span>Introduzca el l&iacute;mite del cr&eacute;dito</code></span>
											</div>
										</section>
										<section class="sectionTercio"><label for="text_dsctoMercancia">Descuento mercanc&iacute;a</label>
											<div>
												<input type="text" id="text_dsctoMercancia" name="text_dsctoMercancia" title="Descuento mercancia" onKeyPress="return validarNumerosDecimales('text_dsctoMercancia', event);" onChange="javascript:ajustarDecimal('text_dsctoMercancia');" />
												<span>Introduzca el descuento</code></span>
											</div>
										</section>
										<section class="sectionTercio"><label for="text_dsctoValor">Descuento valor</label>
											<div>
												<input type="text" id="text_dsctoValor" name="text_dsctoValor" title="Descuento valor" onKeyPress="return validarNumerosDecimales('text_dsctoValor', event);" onChange="javascript:ajustarDecimal('text_dsctoValor');" />
												<span>Introduzca el descuento</code></span>
											</div>
										</section>
										<section id="sectionExportableAutoventa" class="sectionTercio">
											<label for="checkExportableAutoventa">Exportable</label>
											<div>
												<input type="checkbox" id="checkExportableAutoventa" />
												<label for="checkExportableAutoventa">Exportable para autoventa</label>
											</div>
										</section>
										<section id="sectionComercialCliente">
											<label for="dropdown_comerciales">Comercial</label>
											<div>
												<select name="dropdown_comerciales" id="dropdown_comerciales" title="Comercial">
													<option value="0">Seleccione el comercial asociado</option>
													<optgroup label="Comerciales">
														<s:iterator id="comerciales" value="%{#session.comerciales}">
															<option value="<s:property value="idUsuario" />">
																<s:property value="login" />
															</option>
														</s:iterator>
													</optgroup>
												</select>
											</div>
										</section>
										<section id="sectionRutaCliente">
											<label for="dropdown_rutas">Ruta</label>
											<div>
												<select name="dropdown_rutas" id="dropdown_rutas" title="Ruta">
													<option value="0">Seleccione la ruta asociada</option>
													<optgroup label="Rutas">
														<s:iterator id="rutas" value="%{#session.rutas}">
															<option value="<s:property value="idRuta" />">
																<s:property value="nombre" />
															</option>
														</s:iterator>
													</optgroup>
												</select>
											</div>
										</section>
										<section id="sectionRecarEqui" style="display: none;">
											<label for="text_recargoEqui">Recargo por equivalencia</label>
											<div>
												<input type="text" id="text_recargoEqui" name="text_recargoEqui" title="Recargo por equivalencia" onKeyPress="return validarNumerosDecimales('text_recargoEqui', event);" onChange="javascript:ajustarDecimal('text_recargoEqui');" />
												<span>Introduzca el recargo por equivalencia</code></span>
											</div>
										</section>
										<section class="">
											<label for="txt_altaEntidad_observaciones"><s:text name="registroCliente.observaciones"/></label>
											<div>
												<textarea id="txt_altaEntidad_observaciones" name="txt_altaEntidad_observaciones" rows="10" cols="">
													<s:property value="observaciones" />
												</textarea>
											</div>
										</section>
									</fieldset>
									<div id="botonesPrevNext6">
										<button id="btPrev6" onclick="javascript:prevSextoBreadcrumb();" style="float: left;" title="Ir a la pantalla anterior" alt="Ir a la pantalla anterior">Anterior</button>
										<button id="prev6" class="prev" style="display: none;">Anterior</button>
										<button id="bot_insertar_II" class="i_tick icon verdeOpilion" onclick="javascript:registraEntidad();" style="float: right;" >Insertar</button>
									</div>
								</div>
							</div> <!-- END BREADCRUMBS-->
						</div> <!-- END WIDGET -->
					</div>
					<table id="tablaDirecciones" class="tabla" style="display: none; margin-left: auto; margin-right: auto;">
						<caption>Direcciones almacenadas</caption>
						<thead>
							<tr>
								<th>Tipo</th>
								<th><s:text name="registro.label.codigoEan" /></th>
								<th><s:text name="registro.label.localidad" /></th>
								<th style="min-width:100px"><s:text name="registro.label.calle" /></th>
								<th><s:text name="registro.label.municipio" /></th>
								<th><s:text name="registro.label.provincia" /></th>
								<th><s:text name="registro.label.codigoPostal" /></th>
								<th><s:text name="registro.label.horario" /></th>
							</tr>
						</thead>
						<tbody id="tbodyDirsContacto">
						</tbody>
					</table>
					<table id="tablaTelefonos" class="tabla" style="width: auto !important; margin-left: auto; margin-right: auto;" cellpadding="2" cellspacing="2" border="1" style="display: none;">
						<caption>Tel&eacute;fonos</caption>
						<thead>
							<tr>
								<th style="width: 200px;">Tipo</th>
								<th style="width: 150px;"><s:text name="registro.label.numero" /></th>
								<th style="width: 150px;"><s:text name="registro.label.notas" /></th>
							</tr>
						</thead>
						<tbody id="tbodyTfnos">
						</tbody>
					</table>
					<table id="tablaEmails" class="tabla" style="width: auto !important; margin-left: auto; margin-right: auto;" cellpadding="2" cellspacing="2" border="1" style="display: none;">
						<caption>Emails</caption>
						<thead>
							<tr>
								<th style="width: 200px;">Tipo</th>
								<th style="width: 150px;"><s:text name="registro.label.direccion" /></th>
								<th style="width: 150px;"><s:text name="registro.label.notas" /></th>
							</tr>
						</thead>
						<tbody id="tbodyEmails">
						</tbody>
					</table>
					<table id="tablaCuentasBanco" class="tabla" style="width: auto !important; margin-left: auto; margin-right: auto;" cellpadding="2" cellspacing="2" border="1" style="display: none;">
						<caption>Cuentas bancarias</caption>
						<thead>
							<tr>
								<th style="width: 200px;" rowspan="2">Forma de pago</th>
								<th style="width: 400px;" colspan="5">Cuenta asociada</th>
								<th style="width: 150px;" rowspan="2">D&iacute;a de pago/cobro</th>
							</tr>
							<tr>
								<th>IBAN</th>
								<th>Banco</th>
								<th>Sucursal</th>
								<th>DC</th>
								<th>Numero cuenta</th>
							</tr>
						</thead>
						<tbody id="tbodyCuentasBanco">
						</tbody>
					</table>
				</div> <!-- END widget_tabs -->
			</s:form>
		</div> <!-- END carga cliente -->
	</div> <!-- END content -->
</s:i18n>
<script type="text/javascript">
	tinyMCE.init({
		// General options
		mode : "exact",
        elements : "txt_altaEntidad_observaciones", 
		theme : "advanced",
		/*plugins : "autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,wordcount,advlist,autosave,visualblocks",*/

		// Theme options
		theme_advanced_buttons1 : "newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
		/*theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",*/
		theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,|,forecolor,backcolor",
		theme_advanced_buttons3 : "",/*"tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",*/
		theme_advanced_buttons4 : "",
		/*
		theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
		theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
		theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak,restoredraft,visualblocks",*/
		theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		theme_advanced_statusbar_location : "bottom",
		theme_advanced_resizing : true,

		// Example content CSS (should be your site CSS)
		//content_css : "css/content.css",

		// Drop lists for link/image/media/template dialogs
		/*template_external_list_url : "lists/template_list.js",
		external_link_list_url : "lists/link_list.js",
		external_image_list_url : "lists/image_list.js",
		media_external_list_url : "lists/media_list.js",*/

		// Style formats
		style_formats : [
			{title : 'Bold text', inline : 'b'},
			{title : 'Red text', inline : 'span', styles : {color : '#ff0000'}},
			{title : 'Red header', block : 'h1', styles : {color : '#ff0000'}},
			{title : 'Example 1', inline : 'span', classes : 'example1'},
			{title : 'Example 2', inline : 'span', classes : 'example2'},
			{title : 'Table styles'},
			{title : 'Table row 1', selector : 'tr', classes : 'tablerow1'}
		]
	});
</script>