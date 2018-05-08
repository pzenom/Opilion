$(document).ready(function(){
	var $url = "";
	$url = "ConsultaEnvase.action?idGrupo=0";
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consEnva").empty();
			$("#widget_consEnva").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "envase/consultaEnvase.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
});

function cargarEnvase(idEnvase){
	$.ajax({
	 url: "CargarEnvase.action?IdEnvase=" + idEnvase,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consEnva").empty();
		$("#widget_consEnva").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "envase/registroEnvase.js",
		dataType: "script"
	});
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick", "javascript:consEnva();");
	$("#bot_actualizar").attr("onclick", "javascript:actualizaEnvase();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	$("#bot_actualizar").show();
}

function eliminarEnvase(idEnvase){
	$("#idUsuarioEliminar").val(idEnvase);
	$('#botonEliminarProveedor').trigger('click');
}

function tipoSeleccionado(){
	idTipo = $("#dropdown_tipos").val();
	$("#idTipo").val(idTipo);
}

function reporteEnvases(){
	parent.get_ventana_emergente('ConsCL','ConsEnvsJR.action','no','no','800','640','','');
}

function consEnva(){
	var $url = "";
	$url = "ConsultaEnvase.action?idMaterial=0";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consEnva").empty();
		$("#widget_consEnva").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "envase/consultaEnvase.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function nuevoEnva(){
	var $url = "";
	$url = "RegistroEnvase.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consEnva").empty();
		$("#widget_consEnva").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "envase/registroEnvase.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick", "javascript:consEnva();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	$("#bot_insertar").show();
	$("#bot_insertar").attr("onclick", "javascript:registraEnvase();");
}