$(document).ready(function() {
	$('#tablaDetaFumi').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 1, 'desc' ]],
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
	$("#bot_vuelve").attr("onclick", "javascript:consGPFumi();");
	$("#bot_vuelve").show();
	$("#bot_insertar").attr("onclick", "javascript:guardarFumi();");
	$("#bot_insertar").show();
});

function guardarFumi(){
	//estadoProceso=0&listProcSele=E11112-1%2F&cantidad=2
	$url = "InseGPFumi.action?estadoProceso=0";
	mermas = $(".mermas");
	for (i = 0; i < mermas.length; i++){
		$url += "&listProcSele=" + mermas[i].id	 + "%2F&cantidad=" + $("#" + id).val();
	}
	//$.msg($url);
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			procesoSeleccion = $("#InseGPFumi_orden").val();
			//$.msg($("#widget_consGPFumi").length);
			if ($("#widget_consGPFumi").length > 0){
				$("#widget_consGPFumi").attr("id", "widget_consUbica");
				$("#widget_consUbica").empty();
				$("#widget_consUbica").append(html);
			}
			//$.msg($("#reubicar").length);
			//$.msg("vamos");
			$(".botonUbi").hide();
			//$.msg("otru");
			$("#tipoProceso").val("Fumigado");
			//$.msg("fumigado");
			//$.msg($("#reubicar").val());
			//$("#reubicar").val(true);
			$("#reubicar").val(false);
			$("#ubicar").val(true);
			//$.msg($("#reubicar").val());
			//$("#reubicar").val(123);
			//$.msg($("#reubicar").val());
			$("#procesoSeleccion").val(procesoSeleccion);
			$("#gestionBultos").val("false");
		}
	});
	$.ajax({
		type: "POST",
		url: "ubicacion/ubicarProceso.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
	$("#btVolver").attr("onclick", "javascript:consGPFumi();");
}