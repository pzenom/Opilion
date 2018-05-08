<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="includes/head.jsp" flush="true" />
<body>
	<div class="blocker" style="display: none;">
		<div id="div_reloj" style="filter:alpha(opacity=60);">
			<img src="img/reloj.gif"></img>
		</div>
	</div>
	<jsp:include page="includes/controles.jsp" flush="true" />
	<header>
		<div id="logo">
			<a href="Inicio.action" title="Opilion - Sistema de Gesti&oacute;n Agroalimentaria"><span>Opilion</span></a>
		</div>
		<jsp:include page="includes/botones_principal.jsp" flush="true" />
	</header>
	<jsp:include page="includes/general_nav.jsp" flush="true" />
	<s:i18n name="ApplicationResources">
		<jsp:include page="includes/variablesOcultasGenerales.jsp" flush="true" />
		<div id="myDiv" class="blocker">
			<div id="divTrabajando" style="width: 120px;">
				<div style="margin: auto; width: 66px;">
					<img src="img/ajax-loader.gif"/>
				</div>
			</div>
		</div> 
		<!-- DIV AJAX -->
		<s:div id="ajax" name="ajax">
			<section id="content">
				<jsp:include page="/includes/botonera.jsp" flush="true" />
				<div id="contenido" style="display: none;">
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
						<div id="widget_fast_access5" class="widget" style="display: none;">
							<h3 class="handle">Reportes</h3>
							<div>
								<h4>Albaranes</h4>
								<p>
									<a title="" class="btn" href="javascript:reporteVentasMesPasado();"><img src="img/icon_albaran.png" height="48" width="48" alt="Mes pasado" title="Mes pasado" /><span>Mes pasado</span></a>
								</p>
								 <hr />
								<h4>Productos</h4>
								<p>
									<a title="" class="btn" href="javascript:alert('Sin implementar');"><img src="img/icon_albaran.png" height="48" width="48" alt="Productos" title="Productos" /><span>Productos</span></a>
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
				</div><!-- END CONTENIDO -->
				<div id="ocultoGestionRoles">
					<s:iterator id="accion" value="%{#session.gestionRoles.acciones}" >
						<s:iterator id="rol" value="%{#accion.rolesPermitidos}" >
							<s:hidden id="accionRol_%{#accion.idAccion}_%{#rol.idRol}" cssClass="accionRol" />
						</s:iterator>
						<s:iterator id="idRelacionado" value="%{#accion.idsRelacionados}" status="status">
							<s:hidden id="accionIdRelacionado_%{#accion.idAccion}_%{#status.count}" cssClass="accionIdRelacionado_%{#accion.idAccion}" value="%{#idRelacionado}" />
						</s:iterator>
						<s:iterator id="claseRelacionada" value="%{#accion.clasesRelacionadas}" status="status">
							<s:hidden id="accionClaseRelacionada_%{#accion.idAccion}_%{#status.count}" cssClass="accionClaseRelacionada_%{#accion.idAccion}" value="%{#claseRelacionada}" />
						</s:iterator>
					</s:iterator>
				</div>
				<!-- <div id="ocultoSeleccionVehiculoExportar" title="Exportar datos" style="display: none;">
					<br />
					<p>
						<select name="dropdown_vehiculos" id="dropdown_vehiculos" title="Vehiculo">
							<option value="-1">Seleccione el veh&iacute;culo</option>
							<optgroup label="Veh&iacute;culos">
								<s:iterator id="vehiculos" value="%{#session.listavehiculos}">
									<option value="<s:property value="idVehiculo" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</p>
					<p>
						<select name="dropdown_comerciales" id="dropdown_comerciales" title="Vehiculo">
							<option value="-1">Seleccione el comercial</option>
							<optgroup label="Comerciales">
								<s:iterator id="comerciales" value="%{#session.comerciales}">
									<option value="<s:property value="idUsuario" />">
										<s:property value="login" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</p>
					<p>
						<select name="dropdown_rutas" id="dropdown_rutas" title="Ruta">
							<option value="-1">Seleccione la ruta</option>
							<optgroup label="Rutas">
								<s:iterator id="rutas" value="%{#session.rutas}">
									<option value="<s:property value="idRuta" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</p>
					<button id="bt_exportar" class="i_tick icon verdeOpilion" onclick="javascript:exportar();" style="float: right;">Aceptar</button>
				</div> -->
				<div id="ocultoSeleccionComercialLanzadera" title="Lanzadera comercial" style="display: none;">
					<br />
					<p>
						<select name="dropdown_comerciales" id="dropdown_comerciales" title="Vehiculo">
							<option value="-1">Seleccione el comercial</option>
							<optgroup label="Comerciales">
								<s:iterator id="comerciales" value="%{#session.comerciales}">
									<option value="<s:property value="idUsuario" />">
										<s:property value="login" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</p>
					<button id="bt_exportar" class="i_tick icon verdeOpilion" onclick="javascript:lanzaderaComercial();" style="float: right;">Aceptar</button>
				</div>
			</section> <!-- sectionPrincipal -->
		</s:div>
	</s:i18n>
	<footer>
		<jsp:include page="includes/footer.jsp" flush="true" />
	</footer>
	<script type="text/javascript" src="pagEscritorio.js"></script>
	<script type="text/javascript" src="gestionCalendarioEscritorio.js"></script>
</body>
</html>