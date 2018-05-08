$(document).ready(function(){
	var $url = "";
	$url = "ConsFact.action";
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
		url: "registrosalida/consRegiFact.js",
		dataType: "script"
	});
	$.ajax({
		type: "GET",
		url: "registrosalida/accionesFacturas.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
	$('#vuelveEscritorio').val(false);
});

function listaFacturas(){
	var $url = "";
	$url = "ConsFact.action";
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
		url: "registrosalida/consRegiFact.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function filtraFacturas(){
	var $url = "FiltroFacturas.action?idUsuario=" + $("#dropdown_clientes").val();
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
		url: "registrosalida/consRegiFact.js",
		dataType: "script"
	});
}

function generarFacturaLibre(){
	var $url = "GenerarFacturaLibre.action";
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
		url: "registrosalida/generarFacturaLibre.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "registrosalida/cuotas.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "registrosalida/edicionCamposFacturas.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}