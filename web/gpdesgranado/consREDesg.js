$(document).ready(function() {
	$('#tablaRegistros').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 1, 'asc' ]],
		"aoColumns": [
			null,
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").show();
	$("#bot_vuelve").attr("onclick" , "javascript:volverAlEscritorio();");
});

function regiGPDesg(entrada){
	$url = "RegiGPDesg.action?codigoEntrada=" + entrada;
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
		type: "GET",
		url: "gpdesgranado/regiGPDesg.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}