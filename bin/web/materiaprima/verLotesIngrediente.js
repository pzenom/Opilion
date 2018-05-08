$(document).ready(function() {
	numeros = $(".numeroMilesDecimal");
	for (h = 0; h < numeros.length; h++){
		id = numeros[h].id;
		frag = $("#" + id).text().split(" ");
		valor = frag[0];
		$("#" + id).text(formatearNumeroMilesDecimales("" + valor));
	}
	$('#tablaLotesIngredientes').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	// CONFIGURACION DE LA BOTONERA
	$(".botonBotonera").hide();//Ocultamos todos los botones
	$(".botonesCategorias").hide();
	$("#bot_vuelve").show();
	$("#bot_vuelve").attr("onclick", "javascript:consIngre();");
	$("#widget_menu").show();
	//BOTONERA CONFIGURADA
	setTimeout('$("#tablaLotesIngredientes").show();', 100);
});