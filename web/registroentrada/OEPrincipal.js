$(document).ready(function(){
	//$.msg("primera vez q cargamos proveedores");
	//idTipo = $("#dropdown_tipos").val($("#idTipo").val());
	var $url = "";
	$url = "ConsOE.action?idProveedor=0&filtro=1";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consOE").empty();
		$("#widget_consOE").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/consOE.js",
		dataType: "script"
	});
	//$.msg(3);
	$.ajax({
		type: "POST",
		url: "registroentrada/comunOERE.js",
		dataType: "script"
	});
	//$.msg(4);
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
});

function listaOE(){
	if ($("#widget_consRE").length > 0)
		$("#widget_consRE").attr("id", "widget_consOE");
	if ($("#widget_consUbica").length > 0)
		$("#widget_consUbica").attr("id", "widget_consOE");
	var $url = "";
	$url = "ConsOE.action?idProveedor=0";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consOE").empty()
		$("#widget_consOE").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/consOE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/comunOERE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
	$("#btMenuOE").addClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function tipoSeleccionado(){
	idTipo = $("#dropdown_tipos").val();
	$("#idTipo").val(idTipo);
}

function reporteOE(){
	parent.get_ventana_emergente('ConsOE','ConsREJR.action','no','no','800','640','','');
}

function filtraOE(){
	$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
	setTimeout(function(){
		var $url = "";
		$url = "ConsOE.action?idProveedor=" + $("#dropdown_proveedores").val() + '&filtro=' + $("#dropdown_cuantos_mostrar").val();
		$.ajax({
		 url: $url,
		 cache: false,
		 async:false,
		 success: function(html){
			$("#widget_consOE").empty();
			$("#widget_consOE").append(html);
		 }
		});
		$.ajax({
			type: "POST",
			url: "registroentrada/consOE.js",
			dataType: "script"
		});
		$.ajax({
			type: "POST",
			url: "js/script.js",
			dataType: "script"
		});
	}, 400);
}

function nuevaOE(){
	if ($("#widget_consRE").length > 0)
		$("#widget_consRE").attr("id", "widget_consOE");
	if ($("#widget_consUbica").length > 0)
		$("#widget_consUbica").attr("id", "widget_consOE");
	var $url = "";
	$url = "NuevaOE.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consOE").empty();
		$("#widget_consOE").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/nuevaOE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function insertarOE(){
	if ($("#dropdown_tipoRegistro").val() == 0){
		$.msg("Seleccione el tipo de registros que contiene la orden");
	}else{
		if ($("#dropdown_proveedores").val() == 0){
			$.msg("Seleccione un proveedor de la lista");
		}else{
			if ($("#dropdown_transportistas").val() == 0){
				$.msg("Seleccione un transportista de la lista");
			}else{
				if ($("#dropdown_transportistas").val() == 52 && $("#dropdown_vehiculos").val() == 0){
					$.msg("Selecciona uno de nuestros vehiculos de la lista");
				}else{
					var $url = "";
					descVehiculo = "";
					if ($("#dropdown_transportistas").val() == 52){
						descVehiculo = $("#dropdown_vehiculos").val();
					}else{
						descVehiculo = $("#text_descVehiculo").val();
					}
					$url = "NuevaOETmp.action?codigoOrden=" + $("#text_orden").val() + "&albaran=" + $("#text_albaran").val() +
						"&origen=" + encodeURIComponent($("#text_origen").val()) +
						"&idTipoRegistro=" + $("#dropdown_tipoRegistro").val() +
						"&idProveedor=" + $("#dropdown_proveedores").val().split("_")[0] +
						"&idTransportista=" + $("#dropdown_transportistas").val() +
						"&descVehiculo=" + descVehiculo +
						"&notasVehiculo=" + $("#text_notas").val();
					//$.msg($url);
					$("#idTipoRegistro").val($("#dropdown_tipoRegistro").val());
					$.ajax({
					 url: $url,
					 cache: false,
					 async:false,
					 success: function(html){
						$("#widget_consOE").empty();
						$("#widget_consOE").append(html);
					 }
					});
					$.ajax({
						type: "POST",
						url: "registroentrada/nuevaOETmp.js",
						dataType: "script"
					});
					$.ajax({
						type: "POST",
						url: "js/script.js",
						dataType: "script"
					});
				}
			}
		}
	}
}

function actualizarOE(){
	var $url = "";
	$url =
		" UpdateOE.action?codigoOrden=" + $("#text_orden").val() +
			"&albaran=" + $("#text_albaran").val() +
			"&origen=" + encodeURIComponent($("#text_origen").val()) +
			"&idTipoRegistro=" + $("#dropdown_tipoRegistro").val() +
			"&idProveedor=" + $("#dropdown_proveedores").val().split("_")[0] +
			"&idTransportista=" + $("#dropdown_transportistas").val() +
			"&descVehiculo=" + $("#text_descVehiculo").val() +
			"&notasVehiculo=" + $("#text_notasVehiculo").val();
	//$.msg($url);
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consOE").empty();
			$("#widget_consOE").append(html);
		}
	});
	listaOE();
}

function otroRE(){
	var $url = "";
	//alert($("#selectProveedores").val());
	$url = "Nueva2OETmp.action?idProveedor=" + $("#selectProveedores").val();
	//$.msg($url);
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consOE").empty();
		$("#widget_consOE").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/nuevaOETmp.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function salvarOE(){
	var $url = "";
	$url = "InseOE.action";
	//$.msg($url);
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consOE").empty();
		$("#widget_consOE").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/etiquetasRE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}