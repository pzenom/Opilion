$(document).ready(function() {
	$('#tablaOE').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'desc' ]],
		"aoColumns": [
			null,
			null,
			{ "sType": "uk_date" },
			null
		],
		"sPaginationType": "full_numbers"
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_reporte").attr("onclick" , "javascript:reporteOE();");
	$("#bot_reporte").show();
	$("#bot_nuevo").attr("onclick", "javascript:nuevaOE();");
	$("#bot_nuevo").show();
	$("#bot_listar").attr("onclick" , "javascript:listaOE();");
	$("#bot_listar").show();
	$("#bot_vuelve").attr("onclick", "javascript:volverAlEscritorio();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	if (!$("#consulta").is(':visible')){
		$("#bot_consulta").addClass('ocultando');
		$("#bot_consulta").removeClass('mostrando');
	}
	$("#bot_consulta").show();
	setTimeout('$("#tablaOE").show();', 150);
	$.unblockUI();
});

function verOrden(codigoOrden){
	var $url = "PreviaRegistroOE.action?codigoOrden=" + codigoOrden;
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consOE").empty();
		$("#widget_consOE").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/etiquetasRE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function editarOE(codigoOrden){
	var $url = "PreviaRegistroOE.action?editable=true&codigoOrden=" + codigoOrden;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consOE").empty();
			$("#widget_consOE").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/etiquetasRE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}