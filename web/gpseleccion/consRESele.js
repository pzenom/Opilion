$(document).ready(function() {
	$('#tablaRegistros').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 1, 'asc' ]],
		"aoColumns": [
			null,
			{ "sType": "uk_date" },
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").show();
	$("#bot_vuelve").attr("onclick" , "javascript:nuevoGPSele();");
	setTimeout('$("#tablaRegistros").show();', 250);
});

function regiGPSele(entrada){
	$url = "RegiGPSele.action?codigoEntrada=" + entrada;
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
		type: "GET",
		url: "gpseleccion/regiGPSele.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}