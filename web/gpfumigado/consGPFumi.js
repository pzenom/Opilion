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
	$("#bot_nuevo").attr("onclick", "javascript:nuevoGPFumi();");
	$("#bot_nuevo").show();
	$("#bot_listar").attr("onclick" , "javascript:consGPFumi();");
	$("#bot_listar").show();
	$("#widget_menu").show();
	$("#bot_vuelve").show();
	$("#bot_vuelve").attr("onclick" , "javascript:volverAlEscritorio();");
	setTimeout('$("#tablaRegistros").show();', 250);
});
 
function filtraFumigados(){
	var $url = "";
	$url = "ConsGPFumi.action?orden=" + $("#ordenConsulta").val() +
		"&fechaConsulta=" + $("#fechaConsulta").val();
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

function reporteFumigados(){
	var $url = "";
	$url = "ConsGPFumiJR.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false
	});
}

function ubicarSalidaFumigado(proceso){
	$url = "UbicarProceso.action?tipoProceso=Fumigado&proceso=" + proceso;
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPFumi");
		$("#widget_consGPFumi").empty();
		$("#widget_consGPFumi").append(html);
		//$.msg($("#tipoProceso").val());
		$("#tipoProceso").val("Fumigado");
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
	$("#btVolver").attr("onclick", "javascript:consGPFumi();");
}