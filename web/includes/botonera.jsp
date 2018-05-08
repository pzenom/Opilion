<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="widget_menu" class="menusPrincipales" style="display: none; z-index: 0;">
	<button id="bot_vuelve" class="i_arrow_left icon naranjaOpilion botonBotonera" onClick="javascript:volverAlEscritorio();" style="display:none;"><s:text name="general.volver"/></button>
	<!-- Botones factura libre -->
	<button id="bot_addFacturaLibre" class="i_plus icon yellow botonBotonera" onClick="javascript:addProductoLinea();" style="display:none;">A&ntilde;adir linea</button>
	<button id="bot_editarCargosFactura" class="i_plus icon yellow botonBotonera" onClick="javascript:editarCargosFactura();" style="display:none;">Cargos</button>
	<button id="bot_gestionarCuotas" class="i_plus icon yellow botonBotonera" onClick="javascript:addCuota();" style="display:none;">Cuotas</button>
	<!-- END botones factura libre -->
	<button id="bot_addProductoPedido" class="i_plus icon yellow botonBotonera" onClick="javascript:addProductoPedido();" style="display:none;">A&ntilde;adir producto</button>
	<button id="bot_cargarEntidad" class="i_arrow_right icon yellow botonBotonera" style="display:none;">Cargar entidad</button>
	<button id="bot_procesarPedido" class="i_arrow_right icon yellow botonBotonera" style="display:none;">Procesar pedido</button>
	<button id="bot_generarAlbaran" class="i_tick icon verdeOpilion botonBotonera" onClick="javascript:generarAlbaran();" style="display: none;" >Generar albar&aacute;n</button>
	<button id="bot_generarFacturaLibre" class="i_plus icon yellow botonBotonera" onClick="javascript:generarFacturaLibre();" style="display: none;" >Factura libre</button>
	<button id="bot_generarFactura" class="i_tick icon verdeOpilion botonBotonera" onClick="javascript:generarFactura();" style="display: none;" >Generar factura</button>
	<button id="bot_siguienteLote" class="i_scan_label icon verdeOpilion botonBotonera" onclick="javascript:siguienteLote(false);" style="display:none;">Leer lotes</button>
	<button id="bot_nuevo" class="i_plus icon yellow botonBotonera" onClick="javascript:nuevaEntidad();" style="display:none;"><s:text name="menulateral.nuevo"/></button>
	<button id="bot_nuevoVehiculo" class="i_plus icon yellow botonBotonera" onClick="javascript:nuevoAlmacen(true);" style="display:none;">Nuevo veh&iacute;culo</button>
	<button id="bot_pendientes" class="i_breadcrumb icon green botonBotonera" onclick="javascript:consEnvasadosActivos();" style="display: none;">Pendientes</button>
	<button id="bot_nuevo_EDI" class="i_plus icon yellow botonBotonera" onClick="javascript:nuevoEDI();" style="display:none;">Nuevo EDI</button>
	<button id="bot_insertar" class="i_tick icon verdeOpilion botonBotonera" onClick="javascript:registraEntidad();" style="display: none;" >Insertar</button>
	<button id="bot_salvarSacar" class="i_tick icon verdeOpilion botonBotonera" onclick="javascript:salvarSacar();" style="display:none;">Sacar registros</button>
	<button id="bot_actualizar" class="i_tick icon verdeOpilion botonBotonera botonBotonera" onClick="javascript:actualizaEntidad();" style="display: none;" ><s:text name="general.actualizar"/></button>
	<button id="bot_consulta" class="icon i_arrow_down blue ocultando botonBotonera" onClick="javascript:muestraConsulta();" style="display:none;"><s:text name="general.filtros"/></button>
	<button id="bot_listar" class="i_address_book icon blue botonBotonera" onClick="javascript:consCli();" style="display:none;"><s:text name="menulateral.listar"/></button>
	<button id="bot_reporte" class="i_pdf_document icon blue botonBotonera" onClick="javascript:reporteClientes();" style="display:none;"><s:text name="general.pdf"/></button>
	<button id="bot_lanzadera" class="i_list icon blue botonBotonera" onClick="javascript:gestionLanzaderas();" style="display:none;">Lanzaderas</button>
	<button id="bot_imprimir" class="i_pdf_document icon blue botonBotonera" onClick="javascript:reporte();" style="display:none;"><s:text name="general.imprimir"/></button>
	<!-- Botones ubicaciones (Clase botonUbi) -->
	<button id="btvolver" class="i_arrow_left icon naranjaOpilion botonUbi botonBotonera" onclick="javascript:volverSeleccionAlmacen();" style="display: none;">Seleccionar almacen</button>
	<button id="btreubicar" class="i_arrow_left icon naranjaOpilion botonUbi botonBotonera" onclick="javascript:almacenSeleccionado();" style="display: none;">Reubicarse en la nave</button>
	<button id="btConfirmarUbicacion" class="i_tick icon verdeOpilion botonUbi botonBotonera" onclick="javascript:confirmar();" style="display: none;">Confirmar</button>
	<button id="btConfirmarSacar" class="i_tick icon verdeOpilion botonUbi botonBotonera" onclick="javascript:sacarRegistros();" style="display: none;">Sacar</button>
	<button id="btConfirmarIncidencia" class="i_tick icon verdeOpilion botonUbi botonBotonera" onclick="javascript:sacarRegistros();" style="display: none;">Confirmar incidencia</button>
	<!-- END Botones ubicaciones (Clase botonUbi) -->
	<!-- Botones ordenes de entrada -->
	<button id="btAniadirRE" class="i_plus icon verdeOpilion botonBotonera" onclick="javascript:otroRE();">A&ntilde;adir otro registro</button>
	<button id="btGuardaOrden" class="i_tick icon verdeOpilion botonBotonera" onclick="javascript:salvarOE();">Guardar orden</button>
	<!-- END Botones ordenes de entrada -->
	<!-- Botones gestion de bultos -->
	<button id="bot_salvarBultos" class="i_tick icon verdeOpilion botonBotonera" onclick="javascript:salvarBultos();" style="display: none;">Salvar bultos</button>
	<button id="bot_todasEtiquetas" class="i_plus icon verdeOpilion botonBotonera" onclick="javascript:imprimirTodasEtiquetas();" style="display: none;">TODAS ETIQUETAS</button>
	<!-- END Botones gestion de bultos -->
	<!-- Botones trazabilidad -->
	<button id="bot_trazabilidad" class="i_tick icon verdeOpilion botonBotonera" onclick="javascript:trazabilidad();">Consultar trazabilidad</button>
	<!-- END Botones trazabilidad -->
	<!-- Botones ayuda -->
	<button id="bot_ayuda" class="i_tick icon blue botonBotonera" onclick="javascript:ayuda();">Ayuda</button>
	<!-- END Botones ayuda -->
	<button id="bot_modificar_stock" class="i_tick icon verdeOpilion botonBotonera" onClick="javascript:modificaStockRegistro();" style="display: none;" >Modificar stock</button>
</div>