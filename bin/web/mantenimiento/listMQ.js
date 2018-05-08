$(document).ready(function() {
	$('#tablaMaquinas').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			null,
			null,
			null,
			{ "sType": "uk_date" },
			null
		],
		"sPaginationType": "full_numbers"
	});
});

function filtrar(){
	document.formulario.submit();
}

function seleccionarTipoMaquinas(){
	var indice = document.formulario.idTipoMaquinas.selectedIndex;
	var valor = document.formulario.idTipoMaquinas.options[indice].value;
	//$.msg(valor);
	$("#idTipo").val(valor);
}