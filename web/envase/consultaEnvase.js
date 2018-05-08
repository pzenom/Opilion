$(document).ready(function() {
	ajustarRol();
	numeros = $(".numeroMilesDecimal");
	for (h = 0; h < numeros.length; h++){
		id = numeros[h].id;
		frag = $("#" + id).text().split(" ");
		valor = frag[0];
		$("#" + id).text(formatearNumeroMilesDecimales("" + valor));
	}
	$('#tablaEnvases').dataTable({
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
	// CONFIGURACION DE LA BOTONERA
	$(".botonBotonera").hide();//Ocultamos todos los botones
	$(".botonesCategorias").hide();
	if ($("#bot_nuevo").hasClass('puedeVer')){
		$("#bot_nuevo").attr("onclick", "javascript:nuevoEnva();");
		$("#bot_nuevo").show();
	}
	$("#bot_listar").attr("onclick" , "javascript:consEnva();");
	$("#bot_listar").show();
	$("#bot_reporte").attr("onclick", "javascript:reporteEnvases();");
	$("#bot_reporte").show();
	$("#bot_vuelve").attr("onclick", "javascript:volverAlEscritorio();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	$("#bot_consulta").show();
	if (!$("#consulta").is(':visible')){
		$("#bot_consulta").addClass('ocultando');
		$("#bot_consulta").removeClass('mostrando');
	}
	//BOTONERA CONFIGURADA
	setTimeout('$("#tablaEnvases").show();', 100);
});

function filtraEnvases(){
	var $url = "";
	idTipo = $("#dropdown_materiales").val();
	if (idTipo == undefined)
		$url = "FiltroEnvases.action?idMaterial=0";
	else
		$url = "FiltroEnvases.action?idMaterial=" + idTipo;
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#envases").empty();
		$("#envases").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "envase/consultaEnvase.js",
		dataType: "script"
	});
}

function verLotesEnvase(idEnvase){
	var $url = "";
	$url = "VerLotesEnvase.action?idEnvase=" + idEnvase;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consEnva").empty();
			$("#widget_consEnva").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "envase/verLotesEnvase.js",
		dataType: "script"
	});
}