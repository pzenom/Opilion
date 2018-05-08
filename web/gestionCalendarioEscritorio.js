var fondo = false;
var mensaje = false;
var primeraCarga = true;
var velocidad = 1500;
var my_tooltip = null;

$(document).ready(function(){
	$('#calendario').fullCalendar({
		weekends: true,
		/*buttonText: {
			prev: 'Anterior',
			next: 'Siguiente'
		},*/
		weekMode: 'fixed',
		disableDragging : false,
		eventDragStart: function( event, jsEvent, ui, view ) {
			//alert(event.title);
		},
		eventAfterRender : function(event, element, view ) {
			if (!primeraCarga){
				modificarNombreEventos();
				//alert(1);
				filtroFacturas();
				filtroEnvasados();
				checkOcultaFacturasModificado(false);
				checkOcultaEnvasadosModificado(false);
			}
			filtroFacturas();
		},
		eventDrop: function(factura,dayDelta,minuteDelta,allDay,revertFunc) {
			//alert(dayDelta);//Dias desplazada la factura
			diasDesplazamiento = parseFloat(dayDelta);
			//alert(diasDesplazamiento);
			$.ajax({
				url: "ActualizarFechaEvento.action?codigoEvento=" + factura.title + "&diasDesplazamiento=" + diasDesplazamiento,
				cache: false,
				async: false,
				success: function(html){
					//$.msg("Fecha de vencimiento de la factura " + factura + " modificada.");
				}
			});
		},
		eventClick: function(event, jsEvent, view ) {
			if (event.title.charAt(0) == 'F'){
				if (!$("#msgMuestraFactura").is(": visible")){
					//$("#mydiv").show();
$.blockUI({ message: '<h1>Cargando...</h1>' });
					$url = "EditFact.action?codigoFactura=" + event.title;
					$.ajax({
						url: $url,
						cache: false,
						async:false,
						success: function(html){
							$("#vistaFactura").empty();
							$("#vistaFactura").append(html);
							fondo = document.createElement('div');
							mensaje = document.createElement('div');
							fondo.setAttribute('id','fondo');
							fondo.setAttribute('onclick','javascript:cerrar();');
							fondo.setAttribute('onmouseover','javascript:cerrar();');
							mensaje.setAttribute('id','msgMuestraFactura');
							$('body').append(fondo);
							document.getElementsByTagName('body')[0].appendChild(mensaje);
							mensaje.innerHTML = 
								'<div id="botoneraFactura" class="botonesFacturaEscritorio">' +
									'<button id="bot_editarFacturaBotonera" class="i_arrow_right icon yellow botonBotoneraFactura bot_editarFacturaBotonera" onclick="javascript:editarFacturaDesdeEscritorio(' + "'" + event.title + "'" + ');">Editar</button>' +
									'<button id="bot_emitirFacturaBotonera" class="i_arrow_right icon yellow botonBotoneraFactura bot_emitirFacturaBotonera" onclick="javascript:emitir(' + "'" + event.title + "'" + ');">Emitir</button>' +
									'<button id="bot_cuotasFacturaBotonera" class="i_arrow_right icon yellow botonBotoneraFactura bot_cuotasFacturaBotonera" onclick="javascript:cuotas(' + "'" + event.title + "'" + ');">Dividir cuotas</button>' +
									'<button id="bot_controlCuotasFacturaBotonera" class="i_arrow_right icon yellow botonBotoneraFactura bot_controlCuotasFacturaBotonera" onclick="javascript:gestionCuotasDesdeEscritorio(' + "'" + event.title + "'" + ');">Control cuotas</button>' +
									'<button id="bot_imprimirFacturaBotonera" class="i_arrow_right icon yellow botonBotoneraFactura bot_imprimirFacturaBotonera" onclick="javascript:reporteFac(' + "'" + event.title + "'" + ');">Imprimir</button>' +
									'<button id="bot_cobrarFacturaBotonera" class="i_arrow_right icon yellow botonBotoneraFactura bot_cobrarFacturaBotonera" onclick="javascript:cobrar(' + "'" + event.title + "'" + ');">Cobrar</button>' +
									'<button id="bot_anularFacturaBotonera" class="i_arrow_right icon yellow botonBotoneraFactura bot_anularFacturaBotonera" onclick="javascript:anular(' + "'" + event.title + "'" + ')">Anular</button>' +
								'</div>';
							mensaje.innerHTML += $("#vistaFactura").html();
							$("#fondo").height($(document).height());
							$('#contenedorCuotas').show();
							$('.celdaEliminarCuota').hide();
							estado = $('#estado_' + event.title).val();
							cuotas = $('#cuotas_' + event.title).val();
							$(".botonBotoneraFactura").hide();
							if (estado == '3'){
								if (cuotas == "si"){
									$(".bot_cuotasFacturaBotonera").show();
								}else{
									$(".bot_emitirFacturaBotonera").show();
								}
								$(".bot_editarFacturaBotonera").show();
								$(".bot_anularFacturaBotonera").show();
							}else{
								if (estado == '1' || estado == '2'){
									$(".bot_imprimirFacturaBotonera").show();
									$(".bot_cobrarFacturaBotonera").show();
									$(".bot_anularFacturaBotonera").show();
								}else{
									if (estado == '0'){
										$(".bot_imprimirFacturaBotonera").show();
									}else{
										if (estado == '4'){	
											$(".bot_controlCuotasFacturaBotonera").show();
										}
									}
								}
							}
							$("#formulario").css("background", "none");
						}
					});
					//$("#mydiv").hide();
$.unblockUI();
				}
			}else{
				if (event.title.charAt(0) == 'E' && event.title.charAt(1) == 'N'){
					my_tooltip.remove();	
					gestionarProceso(event.title);
				}
			}
		}
	});
	//setTimeout("cargarEventos();", 500);
	cargarEventos();
	$.ajax({
		type: "GET",
		url: "registrosalida/accionesFacturas.js",
		dataType: "script"
	});
	$.ajax({
		type: "GET",
		url: "gpenvasado/accionesEnvasados.js",
		dataType: "script"
	});
	primeraCarga = false;
});

function cambiaEstado(){
	idSeleccionado = $("#dropdown_estados").val();
	//alert(idSeleccionado);
	estados = $('.estados').get();
	//alert(estados.length);
	for (i = 0; parseFloat(i) < parseFloat(estados.length); i++){
		//alert(i);
		id = estados[i].id;
		//alert(id);
		idEstado = $("#" + id).val();
		//alert(idEstado);
		frag = id.split("_");
		facturaOcultar = frag[1];
		//alert(facturaOcultar);
		//alert(idSeleccionado);
		if (idSeleccionado == -1){
			//$("#ocultar_" + facturaOcultar).show();
		}else{
			if (idEstado != idSeleccionado){
				$('#ocultar_' + facturaOcultar).hide();
			}else{
				//$('#ocultar_' + facturaOcultar).show();
			}
		}
	}
}

function cambiaEstadoEnvasado(){
	idSeleccionado = $("#dropdown_estadosEnvasados").val();
	nombreSeleccionado = $("#dropdown_estadosEnvasados option[value=" + idSeleccionado + "]").text();
	//alert(nombreSeleccionado);
	//alert(idSeleccionado);
	estados = $('.estadosProcesos').get();
	//alert(estados.length);
	for (i = 0; parseFloat(i) < parseFloat(estados.length); i++){
		//alert(i);
		id = estados[i].id;
		//alert("cambia estado: " + id);
		idEstado = $("#" + id).val();
		//alert(idEstado);
		frag = id.split("_");
		facturaOcultar = frag[1];
		//alert(facturaOcultar);
		//alert(idSeleccionado);
		if (idSeleccionado == -1){
			//$("#ocultar_" + facturaOcultar).show();
		}else{
			if (idEstado != nombreSeleccionado){
				$('#ocultar_' + facturaOcultar).hide();
			}else{
				//$('#ocultar_' + facturaOcultar).show();
			}
		}
	}
}

function filtroFacturas(){
	idClienteSeleccionado = $("#dropdown_clientes").val();
	//alert(idClienteSeleccionado);
	clientes = $('.clientes').get();
	//alert(clientes.length);
	for (i = 0; parseFloat(i) < parseFloat(clientes.length); i++){
		id = clientes[i].id;
		//alert(id);
		idCliente = $("#" + id).val();
		//alert(idCliente);
		frag = id.split("_");
		facturaOcultar = frag[1];
		//alert(facturaOcultar);
		padre = $("#" + facturaOcultar).parent(0);
		padre.attr('id', 'oculta1_' + facturaOcultar);
		padre = $("#oculta1_" + facturaOcultar).parent(0);
		padre.attr('id', 'ocultar_' + facturaOcultar);
		if (!$("#ocultar_" + facturaOcultar).hasClass('facturaCalendario')){
			$("#ocultar_" + facturaOcultar).addClass('facturaCalendario');
		}
		if (idClienteSeleccionado == 0){
			//Mostramos todos los clientes
			//alert("seleccionado el cero");
			//alert($("#ocultar_" + facturaOcultar).is(' :visible'));
			if (!$("#ocultar_" + facturaOcultar).is(' :visible')){
				//alert(1);
				$("#ocultar_" + facturaOcultar).show();
			}
		}else{
			if (idCliente != idClienteSeleccionado){
				$('#ocultar_' + facturaOcultar).hide();
			}else{
				$('#ocultar_' + facturaOcultar).show();
			}
		}
	}
	//Hasta aqui tenemos los clientes que mostraremos. Ahora solo tenemos que ocultar los estados que no queremos
	//alert(2);
	cambiaEstado();
}

function filtroEnvasados(){
	idProductoSeleccionado = $("#dropdown_productos").val();
	productos = $('.productos').get();
	for (i = 0; parseFloat(i) < parseFloat(productos.length); i++){
		id = productos[i].id;
		//alert(id);
		idProducto = $("#" + id).val();
		//alert(idProducto);
		frag = id.split("_");
		envasadoOcultar = frag[1];
		//alert(envasadoOcultar);
		padre = $("#" + envasadoOcultar).parent(0);
		padre.attr('id', 'oculta1_' + envasadoOcultar);
		padre = $("#oculta1_" + envasadoOcultar).parent(0);
		padre.attr('id', 'ocultar_' + envasadoOcultar);
		if (!$("#ocultar_" + envasadoOcultar).hasClass('envasadoCalendario')){
			$("#ocultar_" + envasadoOcultar).addClass('envasadoCalendario');
		}
		//alert(idProductoSeleccionado);
		if (idProductoSeleccionado == 0){
			//Mostramos todos los productos
			$("#ocultar_" + envasadoOcultar).show();
		}else{
			if (idProducto != idProductoSeleccionado){
				$('#ocultar_' + envasadoOcultar).hide();
			}else{
				$('#ocultar_' + envasadoOcultar).show();
			}
		}
	}
	//Hasta aqui tenemos los productos que mostraremos. Ahora solo tenemos que ocultar los estados que no queremos
	cambiaEstadoEnvasado();
}

function cargarEventos(){
	//alert($('#divFiltrarFacturasEscritorio').is(' :visible'));
	//alert($('#divFiltrarEnvasadosEscritorio').is(' :visible'));
	cargarFacturas();
	cargarEnvasados();
	modificarNombreEventos();
}

function cargarFacturas(){
	facturas = $('.factura').get();
	for (i = 0; i < facturas.length; i++){
		factura = facturas[i].id;
		frag = factura.split('_');
		factura = frag[1];
		fecha = $('#fecha_' + factura).val();
		estado = $('#estado_' + factura).val();
		//alert(estado);
		if (estado == '3'){
			$("#calendario").fullCalendar('addEventSource', [{title: factura , start: fecha, editable: true, backgroundColor: 'yellow', id: factura}]);
		}else{
			$("#calendario").fullCalendar('addEventSource', [{title: factura , start: fecha, backgroundColor: 'yellowgreen'}]);
		}
	}
}

function cargarEnvasados(){
	envasados = $('.envasado').get();
	for (i = 0; i < envasados.length; i++){
		envasado = envasados[i].id;
		frag = envasado.split('_');
		envasado = frag[1];
		fecha = $('#fecha_' + envasado).val();
		//alert(fecha);
		//fecha = "2012-03-02";
		estado = $('#estado_' + envasado).val();
		//alert(estado);
		if (estado != 'Finalizado'){
			$("#calendario").fullCalendar('addEventSource', [{title: envasado , start: fecha, editable: true, backgroundColor: '#00FFFF', id: envasado}]);
		}else{
			$("#calendario").fullCalendar('addEventSource', [{title: envasado , start: fecha, backgroundColor: '#66FFFF'}]);
		}
	}
}

function modificarNombreEventos(){
	eventos = $('.fc-event-title').get();
	//alert(eventos.length);
	for (i = 0; i < eventos.length; i++){
		evento = eventos[i];
		if (evento.id != ""){
			//alert(evento.id);
		}else{
			nombreEvento = evento.innerHTML.trim();
			if (nombreEvento.charAt(0) == 'F'){
				factura = nombreEvento;
				//alert(factura);
				evento.setAttribute("id", factura);
				cliente = $('#cliente_' + factura).val();
				original = cliente;
				//alert(cliente);
				cliente = cliente.substring(0, 13);
				cliente = cliente.toLowerCase();
				if (original.length > 12)
					cliente += '...';
				$("#" + factura).text(cliente);
				padre = $("#" + factura).parent(0);
				padre.attr("id", "factura1_" + factura);
				padre = $("#factura1_" + factura).parent(0);
				padre.attr("onMouseOver", "javascript:simple_tooltip('" + factura + "', 'tooltip', 'factura');");
			}else{
				if (nombreEvento.charAt(0) == 'E' && nombreEvento.charAt(1) == 'N'){
					envasado = nombreEvento;
					//alert(envasado);
					evento.setAttribute("id", envasado);
					evento.setAttribute("onMouseOver", "javascript:simple_tooltip('" + envasado + "', 'tooltip', 'envasado');");
				}
			}
		}
	}
}

function simple_tooltip(target_items, name, tipo){
	//alert(tipo);
	if (tipo == 'factura'){
		$('#' + target_items).each(function(i){
			if ($("#" + name + i).length > 0)
				$("#" + name + i).remove();
			html = "<div style='font-size:14px' class='" + name + "' id='" + name + i + "'>" +
				"<span><b>Factura " + $("#" + target_items).attr('id') + "</b></span><table><thead><tr><th>Cliente</th><th>Estado</th><th>Importe</th></tr></thead><tbody>";
			html +=
				'<tr>' +
					'<td>' + $('#cliente_' + target_items).val() + '</td>' +
					'<td>' + $('#descripcionEstado_' + target_items).val() + '</td>' +
					'<td>' + formatearNumeroMilesDecimales("" +  $('#importe_' + target_items).val()) + " " + String.fromCharCode(8364) + '</td>' +
				'</tr>';
			html += '</tbody></div>';
			$('body').append(html);
			my_tooltip = $("#" + name + i);
			$(this).removeAttr("title").mouseover(function(){
					my_tooltip.css({opacity:0.8, display:"none"}).fadeIn(100);
			}).mousemove(function(kmouse){
					my_tooltip.css({left:kmouse.pageX + 15, top:kmouse.pageY + 15});
			}).mouseout(function(){
					my_tooltip.remove();
			});
		});
	}else{
		if(tipo == 'envasado'){
			$('#' + target_items).each(function(i){
				if ($("#" + name + i).length > 0)
					$("#" + name + i).remove();
				html = "<div style='font-size:14px' class='" + name + "' id='" + name + i + "'>" +
					"<span><b>Envasado " + $("#" + target_items).attr('id') + "</b></span><table><thead><tr><th>Producto</th><th>Cantidad</th><th>Estado</th></tr></thead><tbody>";
				html +=
					'<tr>' +
						'<td>' + $('#descProducto_' + target_items).val() + '</td>' +
						'<td>' + $('#cantidad_' + target_items).val() + '</td>' +
						'<td>' + $('#estado_' + target_items).val() + '</td>' +
					'</tr>';
				html += '</tbody></div>';
				$('body').append(html);
				my_tooltip = $("#" + name + i);
				$(this).removeAttr("title").mouseover(function(){
						my_tooltip.css({opacity:0.8, display:"none"}).fadeIn(100);
				}).mousemove(function(kmouse){
						my_tooltip.css({left:kmouse.pageX + 15, top:kmouse.pageY + 15});
				}).mouseout(function(){
						my_tooltip.remove();
				});
			});
		}
	}
}

function cobrar(codigoFactura){
	cerrar();
	cobrarFactura(codigoFactura);
	//volverAlEscritorio();
}

function anular(codigoFactura){
	cerrar();
	anularFactura(codigoFactura);
	//volverAlEscritorio();
}

function emitir(codigoFactura){
	cerrar();
	emitirFactura(codigoFactura);
	//volverAlEscritorio();
}

function cuotas(codigoFactura){
	cerrar();
	cuotasFactura(codigoFactura);
	//volverAlEscritorio();
}

function gestionCuotasDesdeEscritorio(codigoFactura){
	cerrar();
	controlCuotas(codigoFactura);
}

function editarFacturaDesdeEscritorio(codigoFactura){
	cerrar();
	editarFactura(codigoFactura);
}

function cerrar(){
	//alert(1);
	$('#fondo').remove();
	document.getElementsByTagName('body')[0].removeChild(mensaje);
	fondo = false;
	mensaje = false;
}

function checkOcultaFacturasModificado(ocultar){
	//alert(ocultar);	
	if (ocultar == false){
		//alert(1);
		ocultarFacturas = false;
		//alert($('#checkOcultaFacturas').is(":checked"));
		ocultarFacturas = $('#checkOcultaFacturas').is(":checked");
	}else{
		//alert(2);
		ocultarFacturas = true;
	}
	if (ocultarFacturas){
		//Deshabilitar dropdowns
		/*$('#dropdown_clientes').attr('disabled', true);
		$('#dropdown_estados').attr('disabled', true);*/
		$('#sectionDropdownClientes').hide(velocidad);
		$('#sectionDropdownEstados').hide(velocidad);
	}else{
		/*$('#dropdown_clientes').removeAttr('disabled');
		$('#dropdown_estados').removeAttr('disabled');*/
		$('#sectionDropdownClientes').show(velocidad);
		$('#sectionDropdownEstados').show(velocidad);
	}
	facturas = $('.factura').get();
	//alert(facturas.length);
	for (i = 0; parseFloat(i) < parseFloat(facturas.length); i++){
		id = facturas[i].id;
		//alert(id);
		frag = id.split("_");
		facturaOcultar = frag[1];
		padre = $("#" + facturaOcultar).parent(0);
		padre.attr('id', 'oculta1_' + facturaOcultar);
		padre = $("#oculta1_" + facturaOcultar).parent(0);
		padre.attr('id', 'ocultar_' + facturaOcultar);
		if (ocultarFacturas){
			//alert("oculta");
			//Ocultar las facturas
			$('#ocultar_' + facturaOcultar).hide();
		}else{
			//Mostrar las facturas
			$('#ocultar_' + facturaOcultar).show();
		}
	}
	if (!ocultarFacturas){
		filtroFacturas();
	}
}

function checkOcultaEnvasadosModificado(ocultar){
	//alert(ocultar);	
	if (ocultar == false){
		//alert(1);
		ocultarEnvasados = false;
		ocultarEnvasados = $('#checkOcultaEnvasados').is(":checked");
	}else{
		//alert(2);
		ocultarEnvasados = true;
	}
	//alert(ocultarEnvasados);
	if (ocultarEnvasados){
		//Deshabilitar dropdowns
		$('#sectionDropdownEstadosEnvasados').hide(velocidad);
		$('#sectionDropdownProductos').hide(velocidad);
	}else{
		$('#sectionDropdownEstadosEnvasados').show(velocidad);
		$('#sectionDropdownProductos').show(velocidad);
	}
	envasados = $('.envasado').get();
	for (i = 0; parseFloat(i) < parseFloat(envasados.length); i++){
		id = envasados[i].id;
		frag = id.split("_");
		envasadoOcultar = frag[1];
		padre = $("#" + envasadoOcultar).parent(0);
		padre.attr('id', 'oculta1_' + envasadoOcultar);
		padre = $("#oculta1_" + envasadoOcultar).parent(0);
		padre.attr('id', 'ocultar_' + envasadoOcultar);
		//alert(ocultarEnvasados);
		if (ocultarEnvasados){
		//Ocultar las envasados
			$('#ocultar_' + envasadoOcultar).hide();
		}else{
			//Mostrar las facturas
			$('#ocultar_' + envasadoOcultar).show();
		}
	}
	if (!ocultarEnvasados){
		filtroEnvasados();
	}
}