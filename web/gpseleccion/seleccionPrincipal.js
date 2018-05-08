$(document).ready(function(){
	//$.msg("primera vez q cargamos proveedores");
	var $url = "";
	$url = "ConsGPSele.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consGPSele").empty();
		$("#widget_consGPSele").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpseleccion/consGPSele.js",
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

function consGPSele(){
	var $url = "";
	$url = "ConsGPSele.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPSele");
		$("#widget_consGPSele").empty();
		$("#widget_consGPSele").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpseleccion/consGPSele.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function nuevoGPSele(){
	var $url = "";
	$url = "ConsRESele.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPSele");
		$("#widget_consGPSele").empty();
		$("#widget_consGPSele").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpseleccion/consRESele.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}