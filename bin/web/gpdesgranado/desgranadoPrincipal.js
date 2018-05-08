$(document).ready(function(){
	//$.msg("primera vez q cargamos proveedores");
	var $url = "";
	$url = "ConsGPDesg.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consGPDesg").empty();
		$("#widget_consGPDesg").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpdesgranado/consGPDesg.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
});

function eliminarProveedor(idUsuario){
	$("#idUsuarioEliminar").val(idUsuario);
	$('#botonEliminarProveedor').trigger('click');
}

function tipoDesgranadoado(){
	idTipo = $("#dropdown_tipos").val();
	$("#idTipo").val(idTipo);
}

function reporteProveedores(){
	//ConsPRJR.action
	//$.msg("hola");
}

function consGPDesg(){
	var $url = "";
	$url = "ConsGPDesg.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPDesg");
		$("#widget_consGPDesg").empty();
		$("#widget_consGPDesg").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpdesgranado/consGPDesg.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function nuevoGPDesg(){
	var $url = "";
	$url = "ConsREDesg.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPDesg");
		$("#widget_consGPDesg").empty();
		$("#widget_consGPDesg").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpdesgranado/consREDesg.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}