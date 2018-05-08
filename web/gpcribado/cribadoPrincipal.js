$(document).ready(function(){
	//$.msg("primera vez q cargamos proveedores");
	var $url = "";
	$url = "ConsGPCrib.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consGPCrib").empty();
		$("#widget_consGPCrib").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpcribado/consGPCrib.js",
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

function tipoCribadoado(){
	idTipo = $("#dropdown_tipos").val();
	$("#idTipo").val(idTipo);
}

function consGPCrib(){
	var $url = "";
	$url = "ConsGPCrib.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPCrib");
		$("#widget_consGPCrib").empty();
		$("#widget_consGPCrib").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpcribado/consGPCrib.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function nuevoGPCrib(){
	var $url = "";
	$url = "ConsRECrib.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPCrib");
		$("#widget_consGPCrib").empty();
		$("#widget_consGPCrib").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpcribado/consRECrib.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}