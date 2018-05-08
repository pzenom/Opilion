$(document).ready(function(){
	//$.msg("primera vez q cargamos proveedores");
	//idTipo = $("#dropdown_tipos").val($("#idTipo").val());
	consDevol();
});

function consDevol(){
	var $url = "";
	$url = "ListaDevoluciones.action?fecha=";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consDevol").empty();
		$("#widget_consDevol").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "devoluciones/listaDevoluciones.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function nuevaDevol(){
	var $url = "";
	$url = "Devolucion.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consDevol").empty();
		$("#widget_consDevol").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "devoluciones/devolucion.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}