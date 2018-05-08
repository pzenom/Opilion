$(document).ready(function() {
	$('#tablaCribados').dataTable({
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
	$("#bot_nuevo").attr("onclick", "javascript:nuevoGPCrib();");
	$("#bot_nuevo").show();
	$("#bot_listar").attr("onclick" , "javascript:consGPCrib();");
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
	setTimeout('$("#tablaCribados").show();', 250);
});

function reporteCribados(){
	//ConsGPCribJR
	$.ajax({
		url: "consultaGestionProduccion/ConsGPCribJR.action",
		cache: false,
		async:false,
		success: function(html){
		}
	});
}

function filtraCribados(){
	var $url = "";
	$url = "ConsGPCrib.action?orden=" + $("#ordenCribado").val() + //"&lote=" + $("#lote").val() +
		"&fechaConsulta=" + $("#fechaConsulta").val();
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPCrib");
		$("#widget_consGPCrib").empty();
		$("#widget_consGPCrib").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpcribado/consGPCrib.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function ubicarProceso(proceso){
	//UbicarProceso.action?tipoProceso=Cribado&proceso=<s:property id="ingre" value="orden" />"
	$url = "UbicarProceso.action?tipoProceso=Cribado&proceso=" + proceso;
	$("#procesoCribado").val(proceso);
	$("#tipoProceso").val("Cribado");
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consGPCrib").empty();
			$("#widget_consGPCrib").append(html);
		}
	});
	$.ajax({
		type: "GET",
		url: "ubicacion/ubicarProceso.js",
		dataType: "script"
	});
}