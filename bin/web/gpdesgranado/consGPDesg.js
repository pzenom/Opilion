$(document).ready(function() {
	$('#tablaDesgranados').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			null,
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
	$("#bot_nuevo").attr("onclick", "javascript:nuevoGPDesg();");
	$("#bot_nuevo").show();
	$("#bot_listar").attr("onclick" , "javascript:consGPDesg();");
	$("#bot_listar").show();
	$("#widget_menu").show();
	/*if (!$("#consulta").is(':visible')){
		$("#bot_consulta").addClass('ocultando');
		$("#bot_consulta").removeClass('mostrando');
	}*/
	$("#bot_vuelve").show();
	$("#bot_vuelve").attr("onclick" , "javascript:volverAlEscritorio();");
	//$("#bot_consulta").show();
	setTimeout('$("#tablaDesgranados").show();', 250);
});

function reporteDesgranados(){
	//ConsGPDesgJR
	$.ajax({
		url: "consultaGestionProduccion/ConsGPDesgJR.action",
		cache: false,
		async:false,
		success: function(html){
		}
	});
}

function filtraDesgranados(){
	var $url = "";
	$url = "ConsGPDesg.action?orden=" + $("#ordenDesgranado").val() + //"&lote=" + $("#lote").val() +
		"&fechaConsulta=" + $("#fechaConsulta").val();
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPDesg");
		$("#widget_consGPDesg").empty();
		$("#widget_consGPDesg").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpdesgranado/consGPDesg.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function ubicarProceso(proceso){
	//UbicarProceso.action?tipoProceso=Desgranado&proceso=<s:property id="ingre" value="orden" />"
	$url = "UbicarProceso.action?tipoProceso=Desgranado&proceso=" + proceso;
	//$.msg("Proceso: " + proceso);
	$("#procesoSeleccion").val(proceso);
	$("#tipoProceso").val("Desgranado");
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
		url: "ubicacion/ubicarProceso.js",
		dataType: "script"
	});
}