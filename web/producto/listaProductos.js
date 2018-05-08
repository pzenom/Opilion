$(document).ready(function() {
	$('#tablaProductos').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 2, 'asc' ]],
		"aoColumns": [
			null,
			null,
			null,
			null,
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	$("#bot_consulta").show();
});