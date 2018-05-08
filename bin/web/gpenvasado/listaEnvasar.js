//defino los arrays
var materiaPrima = new Array();
var envases = new Array();

$(document).ready(function() {
	//$.msg("listarEnvasar");
	$('#tablaEnvasar').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'desc' ]],
		"aoColumns": [
			null,
			{ "sType": "uk_date" },
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
	$("#bot_nuevo").attr("onclick", "javascript:nuevoGPEnva();");
	//$("#bot_nuevo").show();
	$("#bot_listar").attr("onclick" , "javascript:consEnvasadosActivos();");
	$("#bot_listar").show();
	$("#bot_vuelve").attr("onclick" , "javascript:consGPEnva();");
	$("#bot_vuelve").show();
	/*$("#bot_reporte").attr("onclick" , "javascript:reporteEnvasadosActivos();");
	$("#bot_reporte").show();*/
	$("#widget_menu").show();
	/*if (!$("#consulta").is(':visible')){
		$("#bot_consulta").addClass('ocultando');
		$("#bot_consulta").removeClass('mostrando');
	}
	$("#bot_consulta").show();*/
	rolUsuario = $('#rolUsuario').val();
	//alert(rolUsuario);
	$('.rol' + rolUsuario).show();
	setTimeout('$("#tablaEnvasar").show();', 250);
});