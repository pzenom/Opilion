$(document).ready(function() {
	$('#tablaIncidencias').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			null,
			null,
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick", "javascript:volverAlEscritorio();");
	$("#bot_vuelve").show();
	$("#bot_nuevo").attr("onclick", "javascript:nuevaIncidencia();");
	$("#bot_nuevo").show();
	$("#bot_listar").attr("onclick", "javascript:listaIncidencias();");
	$("#bot_listar").show();
	$("#widget_menu").show();
});