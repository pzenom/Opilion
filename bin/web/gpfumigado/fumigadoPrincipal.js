$(document).ready(function(){
	//$.msg("primera vez q cargamos proveedores");
	var $url = "";
	$url = "ConsGPFumi.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consGPFumi").empty();
		$("#widget_consGPFumi").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpfumigado/consGPFumi.js",
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

function tipoSeleccionado(){
	idTipo = $("#dropdown_tipos").val();
	$("#idTipo").val(idTipo);
}

function reporteProveedores(){
	//ConsPRJR.action
	//$.msg("hola");
}
 
function consGPFumi(){
	var $url = "";
	$url = "ConsGPFumi.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPFumi");
		$("#widget_consGPFumi").empty();
		$("#widget_consGPFumi").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpfumigado/consGPFumi.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function nuevoGPFumi(){
	var $url = "";
	$url = "ConsREFumi.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPFumi");
		$("#widget_consGPFumi").empty();
		$("#widget_consGPFumi").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpfumigado/consREFumi.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}