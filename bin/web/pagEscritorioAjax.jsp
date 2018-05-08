<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
					<!-- Aqui van las miguitas de pan -->
					<!-- <div id="g12" class="g12 nodrop">
						<div id="contenedorEscritorio">
							<h1 class="handle">Escritorio</h1>
							<div>
								<p>A vista de p&aacute;jaro, lo que puedes hacer. Revisa tus tareas pendientes.</p>
								<ol>
									<s:iterator id="mensajes" value="%{#session.mensajes}" >
										<li id="message_delay"><s:property value="mensaje" /></li>
									</s:iterator>
								</ol>
								<hr />
							</div>
						</div>
					</div> -->
					<div class="g6">
						<div id="calendar_widget" class="widget" data-icon="calendar" style="display: none;">
							<div>
								<div id="calendario" class="" data-header="small"></div>
								<!-- <div class="alert info">Es posible arrastrar las facturas no emitidas (Facturas de color verde) para modificar la fecha de vencimiento.</div> -->
							</div>
							<div id="divFiltrarFacturasEscritorio" class="rol3 rol4" style="display: none; position: relative; float: left; width: 47%;">
								<label>FILTRAR FACTURAS</label>
								<fieldset><!-- Informacion Basica-->
									<section>
										<label for="checkOcultaFacturas">Ocultar</label>
										<div>
											<input type="checkbox" id="checkOcultaFacturas" onchange="javascript:checkOcultaFacturasModificado(false);">
										</div>
									</section>
									<br /><br />
									<section id="sectionDropdownClientes">
										<label for="dropdown_clientes">Cliente</label>
										<div>
											<select name="dropdown_clientes" id="dropdown_clientes" onchange="javascript:filtroFacturas();">
												<option value="0">Todos los clientes</option>
												<optgroup label="Cliente">
													<s:iterator id="clientes" value="%{#session.listaclientes}">
														<option value="<s:property value="idUsuario" />">
															<s:property value="nombre" />
														</option>
													</s:iterator>
												</optgroup>
											</select>
										</div>
									</section>
									<section id="sectionDropdownEstados">
										<label for="dropdown_estados">Estado</label>
										<div>
											<select name="dropdown_estados" id="dropdown_estados" onchange="javascript:filtroFacturas();">
												<option value="-1">Todos los estados</option>
												<optgroup label="Estado">
													<s:iterator id="estados" value="%{#session.listaestados}">
														<option value="<s:property value="idEstadoPedido" />">
															<s:property value="nombre" />
														</option>
													</s:iterator>
												</optgroup>
											</select>
										</div>
									</section>
								</fieldset><!--end Informacion Basica-->
							</div>
							<div id="divFiltrarEnvasadosEscritorio" style="float: right; width: 47%; display: none;">
								<label>FILTRAR ENVASADOS</label>
								<fieldset><!-- Informacion Basica-->
									<section>
										<label for="checkOcultaEnvasados">Ocultar</label>
										<div>
											<input type="checkbox" id="checkOcultaEnvasados" onchange="javascript:checkOcultaEnvasadosModificado(false);">
										</div>
									</section>
									<br /><br />
									<section id="sectionDropdownProductos">
										<label for="dropdown_productos">Producto</label>
										<div>
											<select name="dropdown_productos" id="dropdown_productos" onchange="javascript:filtroEnvasados();">
												<option value="0">Todos los productos</option>
												<optgroup label="Producto">
													<s:iterator id="productos" value="%{#session.listaproductos}">
														<option value="<s:property value="idProducto" />">
															<s:property value="nombre" />
														</option>
													</s:iterator>
												</optgroup>
											</select>
										</div>
									</section>
									<section id="sectionDropdownEstadosEnvasados">
										<label for="dropdown_estadosEnvasados">Estado</label>
										<div>
											<select name="dropdown_estadosEnvasados" id="dropdown_estadosEnvasados" onchange="javascript:filtroEnvasados();">
												<option value="-1">Todos los estados</option>
												<optgroup label="Estado">
													<option value="I">Iniciado</option>
													<option value="S">Parado</option>
													<option value="P">Pendiente</option>
												</optgroup>
											</select>
										</div>
									</section>
								</fieldset><!--end Informacion Basica-->
							</div>
						</div>
					</div>
					<div class="g6">
						<div id="widget_fast_access" style="display: none;" class="widget">
							<h3 class="handle">Acceso r&aacute;pido</h3>
							<div>
								<p>
									<a title="Gesti&oacute;n de clientes" class="btn" href="javascript:menuClientes();"><img src="img/icon_cliente.png" height="48" width="48" alt="Clientes" title="Clientes" /><span>Clientes</span></a>
									<a title="" class="btn" href="javascript:proveedores();"><img src="img/icon_cliente.png" height="48" width="48" alt="Proveedores" title="Proveedores" /><span>Proveedores</span></a>
									<a title="" class="btn" href="javascript:gestionProductos();"><img src="img/ico_proceso.png" height="48" width="48" alt="Productos" title="Productos" /><span>Productos</span></a>
								</p>
								<p>
									<a title="" class="btn" href="javascript:ordenesEntrada();"><img src="img/ico_entrada.png" height="48" width="48" alt="Registros de Entrada" title="Registros de Entrada" /><span>Entradas</span></a>
									<a title="" class="btn" href="javascript:albaranes();"><img src="img/icon_albaran.png" height="48" width="48" alt="Albaranes" title="Albaranes" /><span>Albaranes</span></a>
									<a title="" class="btn" href="javascript:consultarTrazabilidad();"><img src="img/icon_trazabilidad.png" height="48" width="48" alt="Manzana con c&oacute;digo de barras" title="Trazabilidad inversa" /><span>Trazabilidad</span></a>
								</p>
							</div>
						</div>
						<div id="widget_fast_access2" class="widget" style="display: none;">
							<h3 class="handle"><s:text name="opcionesEnvasado.titulo"/></h3>
							<div>
								<p>
									<a title="" class="btn" href="javascript:nuevoEnva();"><img src="img/ico_proceso.png" height="48" width="48" alt="Nuevo proceso de envasado" title="Nuevo proceso de envasado" /><span><s:text name="opcionesEnvasado.alta"/></span></a>
									<a title="" class="btn" href="javascript:envasar();"><img src="img/icon_envasado.png" height="48" width="48" alt="Ir a proceso de envasado" title="Ir a proceso de envasado" /><span><s:text name="opcionesEnvasado.envasar"/></span></a>
									<a title="" class="btn" href="javascript:listarEnvasados();"><img src="img/icon_albaran.png" height="48" width="48" alt="Listar todos los procesos de envasado" title="Listar todos los procesos de envasado" /><span><s:text name="opcionesEnvasado.listar"/></span></a>
								</p>
							</div>
						</div>
						<div id="widget_fast_access4" class="widget" style="display: none;">
							<h3 class="handle">Otros</h3>
							<div>
								<p>
									<a title="" class="btn" href="javascript:gestionRutas();"><img src="img/ruta.png" height="48" width="48" alt="Rutas" title="Rutas" /><span>Rutas</span></a>
									<a title="" class="btn" href="http://192.168.1.105/codigos_2.pdf" target="_blank"><img src="img/barcodereader.png" height="48" width="48" alt="Hoja de códigos" title="Hoja de códigos" /><span>Hoja de códigos</span></a>
									<a title="" class="btn" href="javascript:lanzaderaComercialSeleccion();"><img src="img/icon_albaran.png" height="48" width="48" alt="Lanzadera comercial" title="Lanzadera comercial" /><span>Lanzadera Comercial</span></a>
									<a title="" class="btn" href="javascript:modificarStock();"><img src="img/warnings.png" height="48" width="48" alt="Modificar el stock de un lote/registro" title="Modificar el stock de un lote/registro" /><span>Modificar stock</span></a>
								</p>
							</div>
						</div>
						<div class="widget" id="widget_fast_access3" style="display: none;">
							<h3 class="handle"><s:text name="lectorDeCodigos"/></h3>
							<div>
								<p>
									<a title="" class="btn" href="javascript:flujoCodigos();"><img src="img/barcodereader.png" height="48" width="48" alt="Funcionamiento inal&aacute;mbrico" title="Funcionamiento inal&aacute;mbrico" /><span><s:text name="Modo_libre"/></span></a>
								</p>
							</div>
						</div>
					</div>
					<div id="contenedorOcultos" style="display: none;">
						<s:iterator id="facturas" value="%{#session.facturas}" >
							<s:hidden id="codigoFactura_%{#facturas.codigoFactura}" cssClass="factura" />
							<s:hidden id="fecha_%{#facturas.codigoFactura}" value="%{#facturas.fechaVencimiento}" />
							<s:hidden id="cliente_%{#facturas.codigoFactura}" value="%{#facturas.nombreCliente}" />
							<s:hidden id="idCliente_%{#facturas.codigoFactura}" cssClass="clientes" value="%{#facturas.idCliente}" />
							<s:hidden id="estado_%{#facturas.codigoFactura}" cssClass="estados" value="%{#facturas.estado}" />
							<s:hidden id="descripcionEstado_%{#facturas.codigoFactura}" value="%{#facturas.descripcionEstado}" />
							<s:hidden id="cuotas_%{#facturas.codigoFactura}" value="%{#facturas.enCuotas}" />
							<s:hidden id="importe_%{#facturas.codigoFactura}" value="%{#facturas.importeTotal}" />
						</s:iterator>
						<s:iterator id="procesosEnvasado" value="%{#session.listaregienvasados}" >
							<s:hidden id="orden_%{#procesosEnvasado.orden}" cssClass="envasado" />
							<s:hidden id="fecha_%{#procesosEnvasado.orden}" value="%{#procesosEnvasado.fecha}" />
							<s:hidden id="estado_%{#procesosEnvasado.orden}" cssClass="estadosProcesos" value="%{#procesosEnvasado.estadoProceso}" />
							<s:hidden id="idProducto_%{#procesosEnvasado.orden}" cssClass="productos" value="%{#procesosEnvasado.idProducto}" />
							<s:hidden id="descProducto_%{#procesosEnvasado.orden}" value="%{#procesosEnvasado.descProducto}" />
							<s:hidden id="cantidad_%{#procesosEnvasado.orden}" value="%{#procesosEnvasado.cantidadProducto}" />
						</s:iterator>
						<span id="eventos"></span>
					</div>
					<div id="vistaFactura" style="display: none;">
					</div>
					<div id="dialogo" title="Reporte de factura" style="display: none;">
						<input id="checkEncabezado" name="checkEncabezado" type="checkbox">A&ntilde;adir encabezado</input><br />
						<br/>
						<button id="bt_imprimir_nuevo" class="i_pdf_document icon verdeOpilion" onclick="javascript:preparar();">Imprimir</button>
					</div>
</s:i18n>