var idVehiculo = 0;

$(document).ready(function(){
	idVehiculo = $('#idVehiculoLanzadera').val();
	ajustarRol();
	$('#tablaLanzaderas').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 4, 'desc' ]],
		"aoColumns": [
			{ "bVisible": false },
			{ "bVisible": false },
			{ "iDataSort": 2 },//Use column 3 (oculta) to perform sorting
			null,
			null,
			{ "bSortable": false}
		],
		"sPaginationType": "full_numbers"
	});
	// Configuramos la botonera
	$(".botonBotonera").hide();
	$("#bot_nuevo").attr("onclick", "javascript:nuevaLanzadera();");
	$("#bot_nuevo").show();
	$("#bot_vuelve").show();
	$("#bot_vuelve").attr("onclick", 'javascript:volverListaLanzaderas();');
	setTimeout('$("#tablaLanzaderas").show();', 250);
});

function volverListaLanzaderas(){
	$('#widget_lanzaderas').attr('id', 'widget_consUbica');
	gestionarAlmacenSeleccionado(' + idVehiculo + ');
}

function nuevaLanzadera(){
	//TODO: Comprobar si hay productos para la nueva lanzadera, para no generar una lanzadera vacía
	$url = "ComprobarLanzaderaVacia.action?idVehiculo=" + idVehiculo;
	//alert(1);
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#ajax_div_consultaProductosLanzadera").empty();
			$("#ajax_div_consultaProductosLanzadera").append(html);
			cuantosProductos = $('#numeroProductosLanzadera').val();
			if (cuantosProductos > 0){
				//alert('Generando lanzadera para: ' + idVehiculo);
				action = 'ReporteVehiculoLanzadera.action?idVehiculo=' + idVehiculo;
				parent.get_ventana_emergente('LANZADERA', action, 'no', 'no', '800', '640','','');
				setTimeout('listaLanzaderas();', 300);
			}else{
				opciones = {
					header: 'Aviso',
					live: 5000,
					topoffset: 90,
					fadeTime: 500,
					sticky: false,
					background: '#FF8000',
					colorLetra: '#190707'
				};
				$.msg("No hay productos nuevos para la lanzadera", opciones);
			}
		}
	});
}

function muestraReporteLanzadera(nombreReporte){
	parent.get_ventana_emergente('LANZADERA', './reportes_generados/lanzadera/' + nombreReporte + '.pdf', 'no', 'no', '800', '640','','');
}