$(document).ready(function() {
	$('#tablaDevoluciones').dataTable({
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
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_nuevo").attr("onclick", "javascript:nuevaDevol();");
	$("#bot_nuevo").show();
	$("#bot_listar").attr("onclick" , "javascript:consDevol();");
	$("#bot_listar").show();
	$("#bot_vuelve").attr("onclick" , "javascript:volverAlEscritorio();");
	$("#bot_vuelve").show();
	$("#bot_reporte").attr("onclick" , "javascript:reporteDEVOL();");
	//$("#bot_reporte").show();
	$("#widget_menu").show();
	entradas = $('.entrada').get();
	cuantas = entradas.length;
	for (i = 0; i < cuantas; i++){
		codigo = entradas[i].id.split("_")[1];
		//alert(codigo);
		reaprovechar = $('#reaprovechar_' + codigo).text().trim();
		//alert(reaprovechar);
		if (reaprovechar == 'true'){
			$('#botonesEntrada_' + codigo).show();
		}
	}
	setTimeout('$("#tablaDevoluciones").show();', 400);
});

function filtraDEVOL(){
	var $url = "ListaDevoluciones.action?strFecha=" + $("#date_fecha").val();
	//$.msg($url);
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consDevol").empty();
		$("#widget_consDevol").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "devoluciones/listaDevoluciones.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function reaprovechar(){
}

function destriparDevolucion(lote){
	var $url = "";
	$url = "DestriparDevolucion.action?lote=" + lote;
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consDevol").empty();
		$("#widget_consDevol").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "devoluciones/destriparDevolucion.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}