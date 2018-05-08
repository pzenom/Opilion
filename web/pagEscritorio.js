var fondo = false;
var vehiculo = '';

$(document).ready(function(){
	//ajustarRol();
	setTimeout('$("#header").show();', 1000);
	setTimeout('$("#divCargandoMenu").hide();', 1000);
	$("#widget_menu").hide();
	limpiarMenuSuperior();
	$("#irAlEscritorio").addClass("active");
	setTimeout('$("#contenido").show();', 500);
	setTimeout('$("#calendar_widget").show();', 500);
	setTimeout("$('#calendario').fullCalendar('today');", 500);
	//alert(1);
	/*setTimeout("if (!$('#divFiltrarFacturasEscritorio').is(' :visible')){ checkOcultaFacturasModificado(true); $('#divFiltrarEnvasadosEscritorio').css({'float' : '', 'width' : '97.5%'});}", 500);
	setTimeout("if (!$('#divFiltrarEnvasadosEscritorio').is(' :visible')){ checkOcultaEnvasadosModificado(true); }", 600);*/
	setTimeout("if (!$('#divFiltrarFacturasEscritorio').is(' :visible')){ $('#divFiltrarEnvasadosEscritorio').css({'float' : '', 'width' : '97.5%'});}", 600);
	setTimeout('ajustarRol();', 500);
});

function ampliarCalendario(){
	$("#g12").append($("#calendar_widget"));
	$("#contenedorEscritorio").remove();
}

function exportarDatos(){
	fondo = document.createElement('div');
	fondo.setAttribute('id','fondo');
	$('body').append(fondo);
	//action = "ConsAlbaOrdenJR.action?codigoAlbaran=" + codigoAlbaran;
	$("#ocultoSeleccionVehiculoExportar").dialog({
		close: function(event, ui) {
			if ( event.originalEvent && $(event.originalEvent.target).closest(".ui-dialog-titlebar-close").length ) {
				$.msg("Cancelada la exportaci&oacute;n");
				$('#fondo').remove();
				fondo = false;
			}
		}}).find("button").click(function(){
			//Cerramos el dialogo
			$(this).closest(".ui-dialog-contenido").dialog("close");
			$('#fondo').remove();
			fondo = false;
		});
}

function gestionRutas(){
	$.ajax({
		 url: "rutas/rutasPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "rutas/rutasPrincipal.js",
		dataType: "script"
	});
}

function importarDatos(){
	$.ajax({
		 url: "ImportarDatos.action",
		 cache: false,
		 async: true,
		 success: function(html){
			$.msg("Datos importados correctamente");
		 }
	});
}

function modificarStock(){
	$.ajax({
		url: "producto/modificarStock.jsp",
		cache: false,
		async: true,
		success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
			//$.msg("Introduzca un lote", {live:7000});
		}
	});
	$.ajax({
		type: "GET",
		url: "producto/modificarStock.js",
		dataType: "script"
	});
	/*$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});*/
}

function exportar(){
	idVehiculo = $("#dropdown_vehiculos").val();
	idComercial = $("#dropdown_comerciales").val();
	idRuta = $("#dropdown_rutas").val();
	if (idVehiculo > 0){
		$url = "ExportarDatos.action?vehiculo=" + idVehiculo + "&idComercial=" + idComercial + "&idRuta=" + idRuta;
		$.ajax({
			 url: $url,
			 cache: false,
			 async: true,
			 success: function(html){
				$.msg("Datos exportados correctamente");
			 }
		});
	}else{
		$.msg('No ha seleccionado ning&uacute;n veh&iacute;culo. Los datos no se han exportado.');
	}
}

function menuClientes(){
	limpiarMenuSuperior();
	$.ajax({
		 url: "cliente/clientePrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "cliente/clientePrincipal.js",
		dataType: "script"
	});
	$("#btMenuClientes").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function proveedores(){
	limpiarMenuSuperior();
	$.ajax({
		 url: "proveedor/proveedorPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "proveedor/proveedorPrincipal.js",
		dataType: "script"
	});
	$("#btMenuProveedores").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function ingredientes(){
	limpiarMenuSuperior();
	$.ajax({
		 url: "materiaprima/ingredientesPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "materiaprima/ingredientesPrincipal.js",
		dataType: "script"
	});
	$("#btMenuAlmacen").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function gestionEnvases(){
	limpiarMenuSuperior();
	$.ajax({
		 url: "envase/envasesPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "envase/envasesPrincipal.js",
		dataType: "script"
	});
	$("#btMenuAlmacen").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function gestionProductos(){
	limpiarMenuSuperior();
	$.ajax({
		 url: "producto/productosPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "producto/productosPrincipal.js",
		dataType: "script"
	});
	$("#btMenuAlmacen").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function ordenesEntrada(){
	limpiarMenuSuperior();
	$.ajax({
		url: "registroentrada/OEPrincipal.jsp",
		cache: false,
		async: true,
		success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
			$.ajax({
				type: "GET",
				url: "registroentrada/OEPrincipal.js",
				dataType: "script"
			});
			$.ajax({
				type: "GET",
				url: "registroentrada/comunOERE.js",
				dataType: "script"
			});
			$("#btMenuOE").addClass("active");
			$("#irAlEscritorio").removeClass("active");
		}
	});
}

function registrosEntrada(){
	limpiarMenuSuperior();
	$.ajax({
		 url: "registroentrada/REPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "registroentrada/REPrincipal.js",
		dataType: "script"
	});
	$.ajax({
		type: "GET",
		url: "registroentrada/comunOERE.js",
		dataType: "script"
	});
	$("#btMenuRE").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function devoluciones(){
	limpiarMenuSuperior();
	$.ajax({
		 url: "devoluciones/devolucionesPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "devoluciones/devolucionesPrincipal.js",
		dataType: "script"
	});
	$("#btMenuDevoluciones").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function btMenuEnvasados(principal){
	$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
	limpiarMenuSuperior();
	$.ajax({
		 url: "gpenvasado/envasadosPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
			//alert(principal);
			if (principal){
				//alert('vav llaamar al js');
				$.ajax({
					type: "GET",
					cache: true,
					url: "gpenvasado/envasadosPrincipal.js",
					dataType: "script",
					async: true,
					success: function(html){
						cargar();
					}
				});
			}
		 }
	});
	$("#tipoProceso").val("Envasado");
	$("#btMenuProcesos").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function selecciones(){
	limpiarMenuSuperior();
	$.ajax({
		 url: "gpseleccion/seleccionPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "gpseleccion/seleccionPrincipal.js",
		dataType: "script"
	});
	$("#tipoProceso").val("Seleccion");
	$("#btMenuProcesos").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function cribados(){
	limpiarMenuSuperior();
	$.ajax({
		 url: "gpcribado/cribadoPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "gpcribado/cribadoPrincipal.js",
		dataType: "script"
	});
	$("#tipoProceso").val("Cribado");
	$("#btMenuProcesos").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function desgranados(){
	limpiarMenuSuperior();
	$.ajax({
		 url: "gpdesgranado/desgranadoPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "gpdesgranado/desgranadoPrincipal.js",
		dataType: "script"
	});
	$("#tipoProceso").val("Desgranado");
	$("#btMenuProcesos").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function fumigados(){
	limpiarMenuSuperior();
	$.ajax({
		 url: "gpfumigado/fumigadoPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "gpfumigado/fumigadoPrincipal.js",
		dataType: "script"
	});
	$("#tipoProceso").val("Fumigado");
	$("#btMenuProcesos").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function congelados(){
	limpiarMenuSuperior();
	$.ajax({
		 url: "gpcongelado/congeladoPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "gpcongelado/congeladoPrincipal.js",
		dataType: "script"
	});
	$("#tipoProceso").val("Congelado");
	$("#btMenuProcesos").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function pedidos(){
	//alert(4);
	$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
	limpiarMenuSuperior();
	$.ajax({
		 url: "registrosalida/pedidosPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
			$.ajax({
				type: "GET",
				url: "registrosalida/pedidosPrincipal.js",
				dataType: "script"
			});
			$("#btMenuPedidos").addClass("active");
			$("#irAlEscritorio").removeClass("active");
		 }
	});
}

function gestionAlbaranes(){
	$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
	limpiarMenuSuperior();
	$.ajax({
		 url: "registrosalida/albaranesPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
			$.ajax({
				type: "GET",
				url: "registrosalida/albaranesPrincipal.js",
				dataType: "script"
			});
			$("#btMenuAlbaranes").addClass("active");
			$("#irAlEscritorio").removeClass("active");
		 }
	});	
}

function functionFacturasMenu(principal){
	limpiarMenuSuperior();
	$.ajax({
		 url: "registrosalida/facturasPrincipal.jsp",
		 cache: false,
		 async: false,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	//alert(principal);
	if (principal){
		$.ajax({
			type: "GET",
			url: "registrosalida/facturasPrincipal.js",
			dataType: "script"
		});
	}
	$("#btMenuFacturas").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function movimientoAlmacenes(){
	limpiarMenuSuperior();
	$("#incidencia").val("false");
	//Modificar variables necesarias (mover=true&reubicar=false)
	$.ajax({
	 url: "ubicacion/almacenesPrincipal.jsp",
	 cache: false,
	 async: true,
	 success: function(html){
		$("#contenido").empty();
		$("#contenido").append(html);
		$("#mover").val("true");
		$("#sacar").val("true");
		//$.msg($("#mover").val());
		$("#reubicar").val("false");
		$.msg("Seleccione el almacen DESDE el que quiere mover",{live:7000});
	 }
	});
	$.ajax({
		type: "GET",
		url: "ubicacion/almacenesPrincipal.js",
		dataType: "script"
	});
	$("#btMenuAlmacenes3").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function consultarTrazabilidad(){
	$.ajax({
	 url: "trazabilidad/consultaTrazaInversa.jsp",
	 cache: false,
	 async:true,
	 success: function(html){
		$("#contenido").empty();
		$("#contenido").append(html);
		$.msg("Introduzca un lote", {live:7000});
	 }
	});
	$.ajax({
		type: "GET",
		url: "trazabilidad/consultaTrazaInversa.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function muestraAlmacen(){
	$('mydiv').show();
	limpiarMenuSuperior();
	$("#incidencia").val("false");
	//SeleccionAlmacen
	$.ajax({
	 url: "ubicacion/almacenesPrincipal.jsp",
	 cache: false,
	 async:true,
	 success: function(html){
		$("#contenido").empty();
		$("#contenido").append(html);
		$("#mover").val("false");
		$("#sacar").val("false");
		$("#meter").val("false");
		$("#reubicar").val("false");
		$("#ubicar").val("false");
		$.msg("Seleccione el almacen que quiere inspeccionar",{live:7000});
	 }
	});
	$.ajax({
		type: "GET",
		url: "ubicacion/almacenesPrincipal.js",
		dataType: "script"
	});
	$("#btMenuAlmacenes2").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function gestionMermas(){
	limpiarMenuSuperior();
	//SeleccionAlmacen
	$.ajax({
		url: "ubicacion/incidenciasPrincipal.jsp",
		cache: false,
		async:true,
		success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		}
	});
	$.ajax({
		type: "GET",
		url: "ubicacion/incidenciasPrincipal.js",
		dataType: "script"
	});
	$("#btMenuAlmacenes3").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function limpiarMenuSuperior(){
	lis = $(".liMenu");
	for (i = 0; i < lis.length; i++){
		li = lis[i];
		hijos = li.children;
		for (j = 0; j < hijos.length; j++){
			if (hijos[j].id != "")
				$("#" + hijos[j].id).removeClass("active");
		}
	}
	$("#sacar").val(false);
	$("#meter").val(false);
	$("#mover").val(false);
}

function listarEnvasados(){
	//alert(1);
	$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
	var $url = "";
	$url = "FiltGPEnva.action";
	$.ajax({
		url: $url,
		cache: false,
		async: true,
		success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
			$.ajax({
				type: "POST",
				url: "gpenvasado/consRegiGPEN.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "js/script.js",
				dataType: "script"
			});
		}
	});
}

function nuevoEnva(){
	limpiarMenuSuperior();
	$.ajax({
		url: "gpenvasado/envasadosPrincipal.jsp",
		cache: false,
		async: true,
		success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		}
	});
	/*$.ajax({
		url: "ConsProdFina.action",
		cache: false,
		async: true,
		success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		}
	});*/
	$.ajax({
		type: "GET",
		url: "gpenvasado/envasadosPrincipal.js",
		dataType: "script",
		success: function(html){
			nuevoGPEnva();
			$("#bot_vuelve").attr("onclick" , "javascript:volverAlEscritorio();");
			$("#bot_vuelve").show();
		}
	});
	$("#btMenuProveedores").addClass("active");
	$("#irAlEscritorio").removeClass("active");
	
	/*
		limpiarMenuSuperior();
	$.ajax({
		 url: "gpenvasado/envasadosPrincipal.jsp",
		 cache: false,
		 async: true,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	if (principal){
		$.ajax({
			type: "GET",
			url: "gpenvasado/envasadosPrincipal.js",
			dataType: "script"
		});
	}
	$("#tipoProceso").val("Envasado");
	$("#btMenuProcesos").addClass("active");
	$("#irAlEscritorio").removeClass("active");
	*/
}

function envasar(){
	var $url = "";
	$url = "ListaEnvasar.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async: true,
	 success: function(html){
		$("#contenido").empty();
		$("#contenido").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpenvasado/listaEnvasar.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function flujoCodigos(){
	var $url = "FlujoCodigos.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async: true,
	 success: function(html){
		$("#contenido").empty();
		$("#contenido").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "flujoCodigos.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function herramientas(){
	limpiarMenuSuperior();
	$.ajax({
	 url: "herramientas/herramientasPrincipal.jsp",
	 cache: false,
	 async: true,
	 success: function(html){
		$("#contenido").empty();
		$("#contenido").append(html);
	 }
	});
	$.ajax({
		type: "GET",
		url: "herramientas/herramientasPrincipal.js",
		dataType: "script"
	});
	$("#btHerramientas").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function administrarSistema(){
	limpiarMenuSuperior();
	$.ajax({
		url: "AdministracionSistema.action",
		cache: false,
		async: true,
		success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		}
	});
	$.ajax({
		type: "GET",
		url: "administracion/administracion.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
	$("#btMenuAdministracion").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function lanzaderaComercialSeleccion(){
	/*$url = 'ListaComercialesAjax.action';
	$.ajax({
		url: $url,
		cache: false,
		async: true,
		dataType : "html",
		success: function(html){
			//alert(html);
			comerciales = $(html).filter('.comercialAjax');
			//alert(comerciales.length);
			var i;
			for (i = 0; i < comerciales.length; i++){
				comercial = comerciales.get(i);
				//alert(comercial);
				idLinea = comercial.id;//comercial_idUsuario
				aux = idLinea.split('_');
				//alert(comercial.id);
				idComercial = aux[1];
				
				login = $(html).filter('#login_' + idComercial).val();
			}*/
			
			fondo = document.createElement('div');
			fondo.setAttribute('id','fondo');
			$('body').append(fondo);
			//action = "ConsAlbaOrdenJR.action?codigoAlbaran=" + codigoAlbaran;
			$("#ocultoSeleccionComercialLanzadera").dialog({
				close: function(event, ui) {
					if ( event.originalEvent && $(event.originalEvent.target).closest(".ui-dialog-titlebar-close").length ) {
						$.msg("Cancelada la lanzadera");
						$('#fondo').remove();
						fondo = false;
					}
				}}).find("button").click(function(){
					//Cerramos el dialogo
					$(this).closest(".ui-dialog-contenido").dialog("close");
					$('#fondo').remove();
					fondo = false;
				});
		/*}
	});*/
}

function lanzaderaComercial(){
	idComercial = $("#dropdown_comerciales").val();
	if (idComercial > 0){
		$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
		$url = 'ReporteLanzaderaComercial.action?idComercial=' + idComercial;
		$.ajax({
			url: $url,
			cache: false,
			async: true,
			dataType : "html",
			success: function(html){
				nombrePDF = $(html).filter('#nombrePDF').val();
				//alert(nombrePDF);//timestamp
				parent.get_ventana_emergente('LANZADERA', 'https://localhost:8443/opilion/reportes_generados/lanzadera_comercial/' + nombrePDF + '.pdf', 'no', 'no', '800', '640','','');
				$.unblockUI();
			}
		});
	}else{
		$.msg("Selecciona un comercial");
	}
}

function reporteVentasMesPasado(){
	actionReporte = 'ReporteAlbaranesMesPasado.action';
	parent.get_ventana_emergente('Reporte', actionReporte, 'no', 'no', '800', '640', '', '');
}