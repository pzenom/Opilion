$(document).ready(function() {
	$('#tablaRegistros').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 1, 'desc' ]],
		"aoColumns": [
			null,
			{ "sType": "uk_date" },
			null,
			null,
			{ "sType": "uk_date" },
			{ "sType": "uk_date" },
			null,
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_nuevo").attr("onclick", "javascript:nuevoGPCong();");
	$("#bot_nuevo").show();
	$("#bot_listar").attr("onclick" , "javascript:consGPCong();");
	$("#bot_listar").show();
	$("#widget_menu").show();
	$("#bot_vuelve").show();
	$("#bot_vuelve").attr("onclick" , "javascript:volverAlEscritorio();");
	setTimeout('$("#tablaRegistros").show();', 250);
});
 
function filtraCongelados(){
	var $url = "";
	$url = "ConsGPCong.action?orden=" + $("#ordenConsulta").val() +
		"&fechaConsulta=" + $("#fechaConsulta").val();
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

function reporteCongelados(){
	var $url = "";
	$url = "ConsGPCongJR.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false
	});
}

function ubicarSalidaCongelado(proceso){
	$url = "UbicarProceso.action?tipoProceso=Congelado&proceso=" + proceso;
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPCong");
		$("#widget_consGPCong").empty();
		$("#widget_consGPCong").append(html);
		//$.msg($("#tipoProceso").val());
		$("#tipoProceso").val("Congelado");
		//$.msg($("#tipoProceso").val());
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "ubicacion/ubicarProceso.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
	$("#btVolver").attr("onclick", "javascript:consGPCong();");
}