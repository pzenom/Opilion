<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="divCargandoMenu">
	<div style="margin: auto; width: 40px;">
		<img src="img/ajax-loader.gif" style="width: 60px;" />
	</div>
</div>
<div id="header" style="display: none;">
	<ul id="headernav">
		<li>
			<ul class="botonera_descrita">
				<li id="liMenuContactos" class="liMenu" style="display: none;"><span class="titulo_seccion">Contactos</span>
					<button id="btMenuClientes" class="i_admin_user icon corto" title="Gesti&oacute;n de clientes" onclick="javascript:menuClientes();" style="display: none;">&nbsp;</button>
					<button id="btMenuProveedores" class="i_user icon corto" title="Gesti&oacute;n de proveedores" onclick="javascript:proveedores();" style="display: none;">&nbsp;</button>
				</li>
				<li id="liMenuAlmacen" class="liMenu" style="display: none;"><span class="titulo_seccion">Almac&eacute;n</span>
					<button id="btMenuAlmacen" class="i_t-shirt icon corto" title="Art&iacute;culos" style="display: none;">&nbsp;</button>
					<ul>
						<li id="liMateriasPrimas" style="display: none;"><a href="javascript:ingredientes();" title="Materias primas">Materias primas</a></li>
						<li id="liEnvases" style="display: none;"><a href="javascript:gestionEnvases();" title="Envases">Envases</a></li>
						<li id="liProductos" style="display: none;"><a href="javascript:gestionProductos();" title="Productos">Productos</a></li>
					</ul>
					<button id="btMenuAlmacenes2" class="i_house_2 icon corto" title="Mostar almacenes y veh&iacute;culos" onclick="javascript:muestraAlmacen();" style="display: none;">&nbsp;</button>
					<button id="btMenuAlmacenes3" class="i_refresh_2 icon corto" title="Movimiento de mercanc&iacute;as" style="display: none;">&nbsp;</button>
					<ul>
						<li id="liMovimientoAlmacenes" style="display: none"><a href="javascript:movimientoAlmacenes();" title="">Movimiento entre almacenes</a></li>
						<li id="liGestionMermas" style="display: none"><a href="javascript:gestionMermas();" title="">Gesti&oacute;n de incidencias/mermas</a></li>
					</ul>
				</li>
				<li id="liMenuEntradas" class="liMenu" style="display: none;"><span class="titulo_seccion">Entradas</span>
					<button id="btMenuOE" class="i_incomming icon corto" style="display: none;" title="&Oacute;rdenes" onclick="javascript:ordenesEntrada();">&nbsp;</button>
					<button id="btMenuRE" class="i_create_write icon corto" style="display: none;" title="Registros de Entrada" onclick="javascript:registrosEntrada();">&nbsp;</button>
					<button id="btMenuDevoluciones" class="i_facebook_not_like icon corto" style="display: none;" title="Devoluciones" onclick="javascript:devoluciones();">&nbsp;</button>
				</li>
				<li id="liMenuProcesos" class="liMenu" style="text-align: center; display: none;"><span class="titulo_seccion">Procesos</span>
					<button id="btMenuProcesos" class="i_breadcrumb icon corto" title="Procesos del sistema">&nbsp;</button>
					<ul>
						<li id="liMenuEnvasados"><a href="javascript:btMenuEnvasados(true);" title="">Envasado</a></li>
						
						<li id="liMenuSeleccion"><a href="javascript:selecciones();" title="">Selecci&oacute;n</a></li>
						<li id="liMenuCribado"><a href="javascript:cribados(true);" title="">Cribado</a></li>
						<li id="liMenuDesgranado"><a href="javascript:desgranados(true);" title="">Desgranado</a></li>
						<li id="liMenuFumigado"><a href="javascript:fumigados(true);" title="">Fumigado</a></li>
						<li id="liMenuCongelado"><a href="javascript:congelados(true);" title="">Congelado</a></li>
					</ul>
				</li>
				<li id="liMenuSalidas" class="liMenu" style="display: none;"><span class="titulo_seccion">Salidas</span>
					<button id="btMenuPedidos" class="i_user_comment icon corto" title="Pedidos" onclick="javascript:pedidos();" style="display: none;">&nbsp;</button>
					<button id="btMenuAlbaranes" class="i_outgoing icon corto" title="Albaranes" onclick="javascript:gestionAlbaranes();" style="display: none;">&nbsp;</button>
					<button id="btMenuFacturas" class="i_money icon corto" title="Facturas" onclick="javascript:functionFacturasMenu(true);" style="display: none;">&nbsp;</button>
				</li>
				<li id="liMenuAdministracion" class="liMenu" style="display: none; text-align: center;"><span class="titulo_seccion">Administraci&oacute;n</span>
					<button id="btMenuAdministracion" class="i_admin_user icon corto" title="Administraci&oacute;n del sistema" onclick="javascript:administrarSistema();">&nbsp;</button>
				</li>
			</ul>
		</li>
	</ul>
	<div id="searchbox">
		<form id="searchform">
			<input type="search" name="query" id="search" placeholder="Buscar">
		</form>
	</div>
</div>