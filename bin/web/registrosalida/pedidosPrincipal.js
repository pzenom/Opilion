var fondo = false;
var mensaje = false;

$(document).ready(function(){
	//alert(0);
	var $url = "";
	$url = "ListarOrders.action?filtro=1";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consPedido").empty();
		$("#widget_consPedido").append(html);
		//alert(1);
	 }
	});
	$.ajax({
		type: "POST",
		url: "edifact/listaorder.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
	//alert(2);
});

function eliminarProveedor(idUsuario){
	$("#idUsuarioEliminar").val(idUsuario);
	$('#botonEliminarProveedor').trigger('click');
}

function nuevoEDI(){
	var $url = "edifact/registroOrders.jsp";
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consPedido").empty();
			$("#widget_consPedido").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "edifact/registroOrders.js",
		dataType: "script"
	});
}

function nuevoPedido(){
	continuarNuevoPedido();
	//Antes de pasar a crear el pedido, avisar de los productos que no estén bien definidos.
	/*var $url = "";
	$url = "FiltroProductosMalDefinidos.action";
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#ajaxProductosMalDefinidos").empty();
			$("#ajaxProductosMalDefinidos").append(html);
			$("#ajaxProductosMalDefinidos").show();
			//alert(html);
			fondo = document.createElement('div');
			mensaje = document.createElement('div');
			fondo.setAttribute('id','fondo');
			mensaje.setAttribute('id','msg');
			$("#fondo").hide();
			$("#msg").hide();
			$('body').append(fondo);
			document.getElementsByTagName('body')[0].appendChild(mensaje);
			mensaje.innerHTML = 
				'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar(true);">X</span></div>' +
					'<p style="font-size: 15px; text-align: center;">PRODUCTOS MAL DEFINIDOS. Definir correctamente antes de realizar el pedido</p>';
			mensaje.innerHTML += html;
			mensaje.innerHTML +=
				'<hr />' +
				'<div id="botonLeerLotes" class="btAlbaran" style="height: 57px; padding: 5px;">' +
					'<button id="botLotes" class="botonGestionAlbaran" onClick="javascript:continuarNuevoPedido();" type="button" name="botLotes">' +
						'<h2 id="tituloBotonSiguiente">IGNORAR</h2>' +
					'</button>' +
					'<button id="botLotes" class="botonGestionAlbaran" onClick="javascript:saltarAProductos();" type="button" name="botLotes">' +
						'<h2 id="tituloBotonSiguiente">IR A PRODUCTOS</h2>' +
					'</button>' +
					'<button id="botLotes" class="botonGestionAlbaran" onClick="javascript:cerrar();" type="button" name="botLotes">' +
						'<h2 id="tituloBotonSiguiente">CANCELAR</h2>' +
					'</button>' +
				'</div>';
			if ($("#tbodyProductosMalDefinidos").children().length > 0){
				$("#fondo").show();
				$("#msg").show();
			}
			$('#tablaProductosMalDefinidos').dataTable({
				"bLengthChange": false,
				"oLanguage": {
					"sUrl": "languages/dataTable/es_ES.txt"
				},
				"aaSorting": [[ 0, 'asc' ]],
				"aoColumns": [
					null,
					null,
					null,
					null
				],
				"sPaginationType": "full_numbers"
			});
		}
	});*/
}

function saltarAProductos(){
	cerrar();
	productos();
}

function cerrar(){
	$('#fondo').remove();
	document.getElementsByTagName('body')[0].removeChild(mensaje);
	fondo = false;
	mensaje = false;
}

function continuarNuevoPedido(){
	//cerrar();
	//$("#mydiv").show();
	$.blockUI({ message: '<h1>Cargando...</h1>' });
	$url = "NuevoPedido.action";
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
			'		url: "registrosalida/nuevoPedido.js",'+
			'		dataType: "script"'+
			"	});"+
			"	$.ajax({"+
			'		type: "POST",'+
			'		url: "js/script.js",'+
			'		dataType: "script"'+
			"	});"+
				'$("#bot_insertar").attr("onclick", "javascript:inserta();");'+
				//$("#mydiv").hide();
				'$.unblockUI(); '+
			"},"+
			"error: function(){"+
			'	$.msg("El pedido no se ha podido mostrar correctamente");'+
				//$("#mydiv").hide();
				'$.unblockUI(); '+
			"}"+
		"});",250);
}

function listaPedidos(){
	var $url = "";
	$url = "ListarOrders.action?filtro=1";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consPedido").empty();
		$("#widget_consPedido").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "edifact/listaorder.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function filtraPedidos(){
	$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
	setTimeout(function(){
		var $url = "";
		$url = 'FiltroPedidos.action?filtro=' + $("#dropdown_cuantos_mostrar").val();//estado=" + $("#dropdown_estadoPedido").val() +
		$.ajax({
			url: $url,
			cache: false,
			async:false,
			success: function(html){
				$("#demo").empty();
				$("#demo").append(html);
			}
		});
		$.ajax({
			type: "POST",
			url: "edifact/listaorder.js",
			dataType: "script"
		});
	}, 200);
}