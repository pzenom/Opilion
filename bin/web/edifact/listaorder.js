$(document).ready(function(){
	numeros = $(".numeroMilesDecimal");
	for (h = 0; h < numeros.length; h++){
		id = numeros[h].id;
		frag = $("#" + id).text().split(" ");
		valor = frag[0];
		valor = redondearValor(valor, 1000);
		$("#" + id).text(formatearNumeroMilesDecimales("" + valor) + " " + String.fromCharCode(8364));
	}
	$('#tablaOrders').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 2, 'desc' ]],
		"aoColumns": [
			{ "sWidth": "12px" },
			{ "sWidth": "12px" },
			{ "sType": "uk_date" },
			{ "sWidth": "200px" },
			{ "sWidth": "135px" },
			{ "sWidth": "135px" },
			null
		],
		"sPaginationType": "full_numbers"
	});
	ResaltarFila();
	//Estado a tratar: eliminados, listos
	eliminados = $(".estado_X");
	for (i = 0; i < eliminados.length; i++){
		id = eliminados[i].id;
		//alert(id);
		frag = id.split('_');
		idPedido = frag[1];
		$("#eliminarPedido_" + idPedido).css('visibility', 'hidden');
		$("#editarPedido_" + idPedido).css('visibility', 'hidden');
		$("#procesarPedido_" + idPedido).css('visibility', 'hidden');
	}
	listos = $(".estado_L");
	for (i = 0; i < listos.length; i++){
		id = listos[i].id;
		//alert(id);
		frag = id.split('_');
		idPedido = frag[1];
		$("#eliminarPedido_" + idPedido).css('visibility', 'hidden');
		$("#procesarPedido_" + idPedido).css('visibility', 'hidden');
		$("#editarPedido_" + idPedido).css('visibility', 'hidden');
	}
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_nuevo").attr("onclick", "javascript:nuevoPedido();");
	$("#bot_listar").attr("onclick", "javascript:listaPedidos();");
	$("#bot_nuevo").show();
	$("#bot_listar").show();
	$("#bot_consulta").show();
	$("#widget_menu").show();
	$("#bot_nuevo_EDI").show();
	$(".botonUbi").hide();
	$("#bot_insertar").hide();
	if (!$("#consulta").is(':visible')){
		$("#bot_consulta").addClass('ocultando');
		$("#bot_consulta").removeClass('mostrando');
	}
	/* Miramos si algun pedido tiene mas de una direccion de entrega, entonces no dejamos pasarlo a albaran */
	dirs = $(".direccionesPedidos").get();
	for (i = 0; i < dirs.length; i++){
		id = dirs[i].id;
		numeroDirs = $("#" + id).val();
		if (numeroDirs > 1){
			frag = id.split("_");
			bgmNum = frag[1];
			$("#enlaceProcesarPedido_" + bgmNum).hide();
			$("#imgNoProcesarPedido_" + bgmNum).show();
			
		}
	}
	/**/
	setTimeout('$("#tablaOrders").show();', 250);
	$.unblockUI();
});

function procesarPedido(codigoPedido){
	$url = "ProcesarPedido.action?bgmNum=" + codigoPedido + "&estado=" + $("#estado_" + codigoPedido).text();	
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consPedido").empty();
			$("#widget_consPedido").append(html);
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
		},
		error: function(){
			$.msg("El albaran no se ha podido cargar");
		}
	});
}

function mostrar(codigoSalida){
	//$("#mydiv").show();
$.blockUI({ message: '<h1>Cargando...</h1>' });
	$url = "CargaPedido.action?codigoSalida=" + codigoSalida;
	setTimeout(""+ 
		"$.ajax({"+
			"url: $url,"+
			"cache: false,"+
			"async:false,"+
			"success: function(html){"+
			'	$("#widget_consPedido").empty();'+
			'	$("#widget_consPedido").append(html);'+
			"	$.ajax({"+
			'		type: "POST",'+
			'		url: "registrosalida/verPedido.js",'+
			'		dataType: "script"'+
			"	});"+
			"	$.ajax({"+
			'		type: "POST",'+
			'		url: "js/script.js",'+
			'		dataType: "script"'+
			"	});"+
			//$("#mydiv").hide();
			'$.unblockUI(); '+
			"},"+
			"error: function(){"+
			'	$.msg("El pedido no se ha podido mostrar correctamente");'+
			"}"+
		"});",250);
	$("#bot_consulta").hide();
}

function cargarEditar(codigoSalida){
	//$("#mydiv").show();
$.blockUI({ message: '<h1>Cargando...</h1>' });
	$url = "CargaPedidoEditable.action?codigoSalida=" + codigoSalida;
	setTimeout(""+ 
		"$.ajax({"+
			"url: $url,"+
			"cache: false,"+
			"async:false,"+
			"success: function(html){"+
			'	$("#widget_consPedido").empty();'+
			'	$("#widget_consPedido").append(html);'+
			"	$.ajax({"+
			'		type: "POST",'+
			'		url: "registrosalida/verPedidoEditable.js",'+
			'		dataType: "script"'+
			"	});"+
			"	$.ajax({"+
			'		type: "POST",'+
			'		url: "js/script.js",'+
			'		dataType: "script"'+
			"	});"+
		 //$("#mydiv").hide();
		'$.unblockUI(); '+
			"},"+
			"error: function(){"+
			'	$.msg("El pedido no se ha podido mostrar correctamente");'+
			"}"+
		"});",250);
	$("#bot_consulta").hide();
}

function eliminar(codigoSalida){
	$.confirm("&#191Confirme si desde eliminar el pedido " + codigoSalida,
		function(){
			estadoPedido = $("#estado_" + codigoSalida).text();
			//alert(estadoPedido);
			if (estadoPedido == 'Recibido' || estadoPedido == 'Rechazado'|| estadoPedido == 'Aceptado'){
				$url = "EliminarPedido.action?codigoSalida=" + codigoSalida;
				$.ajax({
					url: $url,
					cache: false,
					async:false,
					success: function(html){
						$("#widget_consPedido").empty();
						$("#widget_consPedido").append(html);
						$.msg("Pedido " + codigoSalida + " eliminado correctamente");
						/*$.ajax({
							type: "POST",
							url: "edifact/listaorder.js",
							dataType: "script"
						});
						$.ajax({
							type: "POST",
							url: "js/script.js",
							dataType: "script"
						});*/
						listaPedidos();
					},
					error: function(){
						$.msg("El pedido no se ha podido eliminar");
					}
				});
			}else{
				$.msg('Solo se pueden eliminar pedidos con estado Recibido, Aceptado o Rechazado');
			}
		},
		function(){
			$.msg("Cancelado. NO se ha eliminado el pedido");
		}
	);
}

function muestraProductos(idPedido){
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
	simple_tooltip("celdasProductos_" + idPedido, "productos", 'tooltip');
}

function muestraEstadoPedido(estado, idPedido){
	$url = "MuestraInformacionEstado.action?idPedido=" + idPedido + "&estado=" + estado;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#ajaxEstados").empty();
			$("#ajaxEstados").append(html);
		}
	});
	simple_tooltip("celdasEstados_" + idPedido, "estados", 'tooltip');
}

function simple_tooltip(target_items, tipo, name){
	$('#' + target_items).each(function(i){
		if (tipo == "estados"){
			if ($("#" + name + i).length > 0)
				$("#" + name + i).remove();
			html = "<div style='font-size:14px' class='" + name + "' id='" + name + i + "'>" +
				"<span>Estado del pedido " + $("#idPedido").val() + "</span>";
			estados = $(".estado");
			for (j = 0; j < estados.length; j++){
				html += "<p>" + estados[j].value + "</p>";
			}
			html += "</div>";
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