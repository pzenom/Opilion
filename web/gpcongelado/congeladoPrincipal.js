$(document).ready(function(){
	//$.msg("primera vez q cargamos proveedores");
	var $url = "";
	$url = "ConsGPCong.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consGPCong").empty();
		$("#widget_consGPCong").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpcongelado/consGPCong.js",
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
 
function consGPCong(){
	var $url = "";
	$url = "ConsGPCong.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPCong");
		$("#widget_consGPCong").empty();
		$("#widget_consGPCong").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpcongelado/consGPCong.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function nuevoGPCong(){
	var $url = "";
	$url = "ConsRECong.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPCong");
		$("#widget_consGPCong").empty();
		$("#widget_consGPCong").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpcongelado/consRECong.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}