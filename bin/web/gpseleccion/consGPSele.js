$(document).ready(function() {
	$('#tablaSelecciones').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			null,
			{ "sType": "uk_date" },
			null,
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_nuevo").attr("onclick", "javascript:nuevoGPSele();");
	$("#bot_nuevo").show();
	$("#bot_listar").attr("onclick" , "javascript:consGPSele();");
	$("#bot_listar").show();
	/*$("#bot_reporte").attr("onclick" , "javascript:reporteEnvasados();");
	$("#bot_reporte").show();*/
	$("#widget_menu").show();
	if (!$("#consulta").is(':visible')){
		$("#bot_consulta").addClass('ocultando');
		$("#bot_consulta").removeClass('mostrando');
	}
	$("#bot_vuelve").show();
	$("#bot_vuelve").attr("onclick" , "javascript:volverAlEscritorio();");
	$("#bot_consulta").show();
	setTimeout('$("#tablaSelecciones").show();', 250);
});

function reporteSelecciones(){
	//ConsGPSeleJR
	$.ajax({
		url: "consultaGestionProduccion/ConsGPSeleJR.action",
		cache: false,
		async:false,
		success: function(html){
		}
	});
}

function filtraSelecciones(){
	var $url = "";
	$url = "ConsGPSele.action?orden=" + $("#ordenSeleccion").val() + //"&lote=" + $("#lote").val() +
		"&fechaConsulta=" + $("#fechaConsulta").val();
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPSele");
		$("#widget_consGPSele").empty();
		$("#widget_consGPSele").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpseleccion/consGPSele.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function ubicarProceso(proceso){
	//UbicarProceso.action?tipoProceso=Seleccion&proceso=<s:property id="ingre" value="orden" />"
	$url = "UbicarProceso.action?tipoProceso=Seleccion&proceso=" + proceso;
	$("#procesoSeleccion").val(proceso);
	$("#tipoProceso").val("Seleccion");
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
		url: "ubicacion/ubicarProceso.js",
		dataType: "script"
	});
}