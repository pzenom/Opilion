var fondo = false;
var mensaje = false;

$(document).ready(function() {
	numeros = $(".numeroMilesDecimal");
	for (h = 0; h < numeros.length; h++){
		id = numeros[h].id;
		frag = $("#" + id).text().split(" ");
		valor = frag[0];
		$("#" + id).text(formatearNumeroMilesDecimales("" + valor) + " " + String.fromCharCode(8364));
	}
	$('#tablaPedidosCliente').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'desc' ]],
		"aoColumns": [
			null,
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	$('#tablaAlbaranesCliente').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'desc' ]],
		"aoColumns": [
			null,
			null,
			null,
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	$('#tablaFacturasCliente').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'desc' ]],
		"aoColumns": [
			null,
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick", "javascript:consCli();");
	$("#bot_vuelve").show();
	$("#bot_cargarEntidad").show();
	$("#bot_cargarEntidad").attr("onclick", "javascript:cargaClienteGestionando();");
	$("#widget_menu").show();
	/* */
	//Deshabilitamos el checkbox para los albaranes que ya se hayan pasado a factura
	albaranes = $(".codigosAlbaranes").get();
	for (i = 0; i < albaranes.length; i++){
		albaran = albaranes[i];
		id = albaran.id;
		codigoAlbaran = $("#" + id).html().trim();
		facturado = $("#facturado_" + codigoAlbaran).val();
		//alert(facturado);
		if (facturado == 'S'){
			//No es posible facturar dos veces el mismo albarán
			//alert(codigoAlbaran);
			//setTimeout('$("#uniform-check_albaran_" + codigoAlbaran).hide();', 450);
			$("#uniform-check_albaran_" + codigoAlbaran).hide();
			//$("#check_albaran_" + codigoAlbaran).hide();
			$("#img_albaran_" + codigoAlbaran).show();
		}
	}
	
	//Configuramos la fecha de facturación con la fecha actual
	var date = new Date();
    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();
    if (month < 10) month = "0" + month;
    if (day < 10) day = "0" + day;
    var today = day + '/' + month + "/" + year;
    $("#date_fechaEntrega").val(today);
	
	drawChart();
});

function pedidoSeleccionado(){
	cuantosChecks = $(".checkPedidos").length;
	numeroPedidosSeleccionados = 0;
	for (i = 0; i < cuantosChecks; i++){
		id = $(".checkPedidos").get(i).id;
		if ($("#" + id).is(" :checked")){
			numeroPedidosSeleccionados++;
		}
	}
	//alert(numeroPedidosSeleccionados);
	if (numeroPedidosSeleccionados > 1){
		$("#bot_generarAlbaran").show();
		$("#bot_generarAlbaran").attr("onclick", "javascript:generarAlbaran(1);");
	}else{
		$("#bot_generarAlbaran").hide();
	}
}

function drawChart() {
	// Create the data table.
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Topping');
	data.addColumn('number', 'Slices');
	//Añadimos los productos cargados en la tabla oculta
	productosPedidos = $('.productoPedido').get();
	for (i = 0; i < productosPedidos.length; i++){
		id = productosPedidos[i].id;
		kilos = $('#kilos_' + id).val();
		nombreProducto = $('#nombreProducto_' + id).val();
		data.addRow([nombreProducto, parseFloat(kilos)]);
	}
	// Set chart options
	var options = {'title':'Kilos pedidos de cada producto', 'width': 400, 'height': 300};
	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	chart.draw(data, options);
}

function pedidoDireccionSeleccionado(bgmNum, linNum){
	//alert(bgmNum + " " + linNum);
	cuantosChecks = $(".checkPedidosDireccion").length;
	numeroPedidosSeleccionados = 0;
	for (i = 0; i < cuantosChecks; i++){
		id = $(".checkPedidosDireccion").get(i).id;
		if ($("#" + id).is(" :checked")){
			numeroPedidosSeleccionados++;
		}
	}
	//alert(numeroPedidosSeleccionados);
	if (numeroPedidosSeleccionados > 0){
		$("#bot_generarAlbaran").show();
		$("#bot_generarAlbaran").attr("onclick", "javascript:generarAlbaran(2);");
	}else{
		$("#bot_generarAlbaran").hide();
	}
}

function albaranSeleccionado(){
	$("#bot_generarFactura").show();
}

function direccionClienteSeleccionada(){
	//alert("direccionClienteSeleccionada");
	$url = "ListaLineasPedidoDireccion.action?idDireccion=" + $("#dropdown_direcciones").val();
	//alert($url);
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#contenedorPedidosDireccion").empty();
			$("#contenedorPedidosDireccion").append(html);
			//alert(html);
			$('#tablaPedidosDireccionClientes').dataTable({
				"oLanguage": {
					"sUrl": "languages/dataTable/es_ES.txt"
				},
				"aaSorting": [[ 0, 'desc' ]],
				"aoColumns": [
					null,
					null,
					null,
					null,
					null,
					/*null,
					null,*/
					null
				],
				"sPaginationType": "full_numbers"
			});
		},
		error: function(){
			$.msg("Error cargando los pedidos de la direccion");
		}
	});
}

function cargaClienteGestionando(){
	cargarEntidad($("#idCliente").val());
	$("#bot_vuelve").attr("onclick", 'javascript:gestionarCobrosCliente(' + $("#idCliente").val() + ');');
}

function muestraProductosPedido(idPedido){
	$url = "MuestraProductosPedido.action?idPedido=" + idPedido;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#ajaxProductos").empty();
			$("#ajaxProductos").append(html);
		}
	});
	simple_tooltip("productos", "filaPedido_" + idPedido, 'tooltip');
}

function muestraProductosAlbaran(idAlbaran){
	$url = "MuestraProductosPedido.action?idPedido=" + idAlbaran;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#ajaxProductos").empty();
			$("#ajaxProductos").append(html);
		}
	});
	simple_tooltip("productos", "filaAlbaran_" + idAlbaran, 'tooltip');
}

function muestraProductosFactura(idFactura){
	$url = "MuestraProductosPedido.action?idPedido=" + idFactura;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#ajaxProductos").empty();
			$("#ajaxProductos").append(html);
		}
	});
	simple_tooltip("productos", "filaFactura_" + idFactura, 'tooltip');
}

function muestraDireccionesAlbaran(codigoAlbaran){
	var $url = "MuestraDireccionesAlbaran.action?codigoAlbaran=" + codigoAlbaran;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#ajaxDirecciones").empty();
			$("#ajaxDirecciones").append(html);
		}
	});
	simple_tooltip("direccion", "codigoAlbaran_" + codigoAlbaran, 'tooltip');
}

function simple_tooltip(tipo, target_items, name){
	$('#' + target_items).each(function(i){
		if (tipo == "direccion"){
			if ($("#" + name + i).length > 0)
				$("#" + name + i).remove();
			html = "<div style='font-size:14px' class='" + name + "' id='" + name + i + "'>";
			direcciones = $(".direccionAlbaran");
			html += "<p>Direccion de entrega</p><ul>";
			for (j = 0; j < direcciones.length; j++){
				descDireccion = direcciones[j].value;
				html += '<li>' + descDireccion + '</li>';
			}
			html += "</ul></div>";
			$('body').append(html);
			var my_tooltip = $("#" + name + i);
			$(this).removeAttr("title").mouseover(function(){
					my_tooltip.css({opacity:0.8, display:"none"}).fadeIn(100);
			}).mousemove(function(kmouse){
					my_tooltip.css({left:kmouse.pageX+15, top:kmouse.pageY+15});
			}).mouseout(function(){
					my_tooltip.remove();
			});
		}else{
			if (tipo == "productos"){
				if ($("#" + name + i).length > 0)
					$("#" + name + i).remove();
				html = "<div style='font-size:14px' class='" + name + "' id='" + name + i + "'>" +
					"<span>Productos en el pedido " + $("#idPedido").val() + "</span>" +
					"<table><thead><tr><th>Producto</th><th>Kilos</th><tr></thead><tbody>";
				productos = $(".producto");
				for (j = 0; j < productos.length; j++){
					id = productos[j].id;
					id = id.split('_');
					numero = id[1];
					cantidad = $('#cantidadProducto_' + numero).val();
					//html += "<p>" + productos[j].value + "</p>";
					html += "<tr><td>" + productos[j].value + "</td><td>" + cantidad + "</td></tr>";
				}
				html += "</tbdoy></table></div>";
				$('body').append(html);
				var my_tooltip = $("#" + name + i);
				$(this).removeAttr("title").mouseover(function(){
						my_tooltip.css({opacity:0.8, display:"none"}).fadeIn(100);
				}).mousemove(function(kmouse){
						my_tooltip.css({left:kmouse.pageX+15, top:kmouse.pageY+15});
				}).mouseout(function(){
						my_tooltip.remove();
				});
			}
		}
	});
}

function cerrar(){
	$("#hiddens").append($("#divPreguntaFechaEntrega"));
	$("#divPreguntaFechaEntrega").hide();
	$('#fondo').remove();
	document.getElementsByTagName('body')[0].removeChild(mensaje);
	fondo = false;
	mensaje = false;
}

function generarFactura(){
	//Comprobar que todos los check seleccionados tienen la misma forma de pago
	cuantosChecks = $(".checkAlbaranes").length;
	primero = true;
	primeraFormaPago = 0;
	mismaFormaPago = true;
	//alert(1);
	for (i = 0; i < cuantosChecks; i++){
		id = $(".checkAlbaranes").get(i).id;
		if ($("#" + id).is(" :checked")){
			//Comprobar la forma de pago
			valor = $("#" + id).val();
			//alert(valor);
			idFormaPago = $("#idFormaPagoAlbaran_" + valor).val();
			//alert(idFormaPago);
			if (primero){
				primeraFormaPago = idFormaPago;
				primero = false;
			}else{
				if (primeraFormaPago != idFormaPago){
					mismaFormaPago = false;
					break;
				}
			}
		}
	}
	//alert(2);
	//alert(mismaFormaPago);
	if (mismaFormaPago){
		//generarNuevoAlbaranUnion();
		$.msg("Generando factura con los albaranes seleccionados...");
		fondo = document.createElement('div');
		mensaje = document.createElement('div');
		fondo.setAttribute('id','fondo');
		mensaje.setAttribute('id','msg');
		$('body').append(fondo);
		document.getElementsByTagName('body')[0].appendChild(mensaje);
		$("#fondo").height($(document).height());
		mensaje.innerHTML =
			'<div class="superior">Seleccione la fecha de facturacion<span class="cerrar" title="Cerrar" onclick="cerrar();">X</span></div>' +
			'<p style="font-size: 15px; text-align: center;"></p>';
		$("#msg").append($("#divPreguntaFechaEntrega"));
		$("#divPreguntaFechaEntrega").show();
		$("#bot_aceptarFechaEntrega").attr("onclick", "javascript:generarNuevoAlbaranUnion();");
	}else{
		$.msg("No es posible unir albaranes con distintas formas de pago");
	}
}

function generarNuevoAlbaranUnion(){
	var $url = "";
	$url = "AlbaranesFactura.action?idCliente=" + $("#idCliente").val() +
		"&fechaEntrega=" + $("#date_fechaEntrega").val();
	checks = $(".checkAlbaranes").get();
	for (i = 0; i < checks.length; i++){
		if($("#" + checks[i].id).is(" :checked") == true){
			$url += "&codigoAlbaran_" + $("#" + checks[i].id).val() + "=" + $("#" + checks[i].id).val();
		}
	}
	// alert($url);
	cerrar();
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consCli").empty();
			$("#widget_consCli").append(html);
			$("#widget_consCli").attr("id", "widget_consPedido");
			$.ajax({
				type: "POST",
				url: "registrosalida/detaRegiFact.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "registrosalida/edicionCamposFacturas.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "registrosalida/cuotas.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "js/script.js",
				dataType: "script"
			});
			$("#btMenuClientes").removeClass("active");
			$("#btMenuAlbaranes").addClass("active");
		},
		error: function(){
			$.msg("El albaran no se ha podido cargar");
		}
	});
}

function generarAlbaran(tipo){
	if (tipo == 1){//Union de varios pedidos de un mismo cliente
		//Comprobar que todos los check seleccionados tienen la misma forma de pago
		cuantosChecks = $(".checkPedidos").length;
		primero = true;
		primeraFormaPago = 0;
		mismaFormaPago = true;
		for (i = 0; i < cuantosChecks; i++){
			id = $(".checkPedidos").get(i).id;
			if ($("#" + id).is(" :checked")){
				//Comprobar la forma de pago
				valor = $("#" + id).val();
				//alert(valor);
				idFormaPago = $("#idFormaPago_" + valor).val();
				//alert(idFormaPago);
				if (primero){
					primeraFormaPago = idFormaPago;
					primero = false;
				}else{
					if (primeraFormaPago != idFormaPago){
						mismaFormaPago = false;
						break;
					}
				}
			}
		}
		if (mismaFormaPago){
			$.msg("Generando pedido con las lineas y las direcciones seleccionadas...");
			fondo = document.createElement('div');
			mensaje = document.createElement('div');
			fondo.setAttribute('id','fondo');
			mensaje.setAttribute('id','msg');
			$('body').append(fondo);
			document.getElementsByTagName('body')[0].appendChild(mensaje);
			$("#fondo").height($(document).height());
			mensaje.innerHTML =
				'<div class="superior">SELECCIONE LA FECHA DE ENTREGA<span class="cerrar" title="Cerrar" onclick="cerrar();">X</span></div>' +
				'<p style="font-size: 15px; text-align: center;"></p>';
			$("#msg").append($("#divPreguntaFechaEntrega"));
			$("#divPreguntaFechaEntrega").show();
			$("#bot_aceptarFechaEntrega").attr("onclick", "javascript:generarNuevoPedidoUnion(1);");
		}else{
			$.msg("No es posible unir pedidos con distintas formas de pago");
		}
	}else{
		if (tipo == 2){//Union de varios productos pedidos por el mismo cliente a la misma dirección
			//Comprobar que todos los check seleccionados tienen la misma forma de pago
			cuantosChecks = $(".checkPedidosDireccion").length;
			primero = true;
			primeraFormaPago = 0;
			mismaFormaPago = true;
			for (i = 0; i < cuantosChecks; i++){
				id = $(".checkPedidosDireccion").get(i).id;
				if ($("#" + id).is(" :checked")){
					//Comprobar la forma de pago
					valor = $("#" + id).val();
					//alert(valor);
					frag = valor.split("_");
					idFormaPago = $("#idFormaPago_" + frag[0] + "_" + frag[1]).val();
					//alert(idFormaPago);
					if (primero){
						primeraFormaPago = idFormaPago;
						primero = false;
					}else{
						if (primeraFormaPago != idFormaPago){
							mismaFormaPago = false;
							break;
						}
					}
				}
			}
			if (mismaFormaPago){
				$.msg("Generando pedido con las lineas y las direcciones seleccionadas...");
				fondo = document.createElement('div');
				mensaje = document.createElement('div');
				fondo.setAttribute('id','fondo');
				mensaje.setAttribute('id','msg');
				$('body').append(fondo);
				document.getElementsByTagName('body')[0].appendChild(mensaje);
				$("#fondo").height($(document).height());
				mensaje.innerHTML =
					'<div class="superior">SELECCIONE LA FECHA DE ENTREGA<span class="cerrar" title="Cerrar" onclick="cerrar();">X</span></div>' +
					'<p style="font-size: 15px; text-align: center;"></p>';
				$("#msg").append($("#divPreguntaFechaEntrega"));
				$("#divPreguntaFechaEntrega").show();
				$("#bot_aceptarFechaEntrega").attr("onclick", "javascript:generarNuevoPedidoUnion(2);");
			}else{
				$.msg("No es posible unir pedidos con distintas formas de pago");
			}
		}
	}
}

function generarNuevoPedidoUnion(tipo){
	var $url = "";
	if (tipo == 1){
		$url = "PedidosAlbaran.action?idCliente=" + $("#idCliente").val() +
			"&fechaEntrega=" + $("#date_fechaEntrega").val();
		checks = $(".checkPedidos").get();
		for (i = 0; i < checks.length; i++){
			if($("#" + checks[i].id).is(" :checked") == true){
				$url += "&codigoPedido_" + $("#" + checks[i].id).val() + "=" + $("#" + checks[i].id).val();
			}
		}
	}else{
		if (tipo == 2){
			$url = "LineasDireccionAlbaran.action?idCliente=" + $("#idCliente").val() +
				"&fechaEntrega=" + $("#date_fechaEntrega").val() + "&idDireccion=" + $("#dropdown_direcciones").val();
			checks = $(".checkPedidosDireccion").get();
			for (i = 0; i < checks.length; i++){
				if($("#" + checks[i].id).is(" :checked") == true){
					$url += "&codigoPedido_" + $("#" + checks[i].id).val() + "=" + $("#" + checks[i].id).val();
				}
			}
		}
	}
	cerrar();
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consCli").empty();
			$("#widget_consCli").append(html);
			$("#widget_consCli").attr("id", "widget_consPedido");
			$.ajax({
				type: "POST",
				url: "registrosalida/detaRegiAlbaOrden.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "js/script.js",
				dataType: "script"
			});
			$("#btMenuClientes").removeClass("active");
			$("#btMenuAlbaranes").addClass("active");
		},
		error: function(){
			$.msg("El albaran no se ha podido cargar");
		}
	});
}

function mostrarAlbaran(codigoAlbaran){
	action = "ConsAlbaOrdenJR.action?codigoAlbaran=" + codigoAlbaran + '&nuevo=true&encabezado=false&precios=true&lineaCarrefour=true';
	parent.get_ventana_emergente('ALBA', action,'no','no','800','640','','');
	//https://localhost:8443/opilion/ConsAlbaOrdenJR.action?codigoAlbaran=A20130401-99&nuevo=true&encabezado=false&precios=true&lineaCarrefour=true	
}