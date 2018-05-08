$(document).ready(function(){
	listaIncidencias();
});

function listaIncidencias(){
	var $url = "";
	$url = "ConsMermas.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consUbica").empty();
		$("#widget_consUbica").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "ubicacion/consultaIncidencias.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function nuevaIncidencia(){
	var $url = "";
	$url = "SeleccionAlmacen.action?mover=false&incidencia=true";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consUbica").empty();
		$("#widget_consUbica").append(html);
		$("#incidencia").val("true");
		$.msg("Seleccione el almacen donde se registra la incidencia",{live:7000});
	 }
	});
	$.ajax({
		type: "POST",
		url: "ubicacion/seleccionAlmacen.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}