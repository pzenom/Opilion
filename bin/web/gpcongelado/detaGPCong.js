$(document).ready(function() {
	$('#tablaDetaCong').dataTable({
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
	$("#bot_vuelve").attr("onclick", "javascript:consGPCong();");
	$("#bot_vuelve").show();
	$("#bot_insertar").attr("onclick", "javascript:guardarCong();");
	$("#bot_insertar").show();
});

function guardarCong(){
	//estadoProceso=0&listProcSele=E11112-1%2F&cantidad=2
	$url = "InseGPCong.action?estadoProceso=0";
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
			procesoSeleccion = $("#InseGPCong_orden").val();
			//$.msg($("#widget_consGPCong").length);
			if ($("#widget_consGPCong").length > 0){
				$("#widget_consGPCong").attr("id", "widget_consUbica");
				$("#widget_consUbica").empty();
				$("#widget_consUbica").append(html);
			}
			//$.msg($("#reubicar").length);
			//$.msg("vamos");
			$(".botonUbi").hide();
			//$.msg("otru");
			$("#tipoProceso").val("Congelado");
			//$.msg("congelado");
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
	$("#btVolver").attr("onclick", "javascript:consGPCong();");
}