var idVehiculo = 0;

$(document).ready(function(){
	idVehiculo = $('#idVehiculoLanzadera').val();
	//alert(idVehiculo);
	listaLanzaderas();
});

function listaLanzaderas(){
	//alert(0);
	////$("#mydiv").show();
$.blockUI({ message: '<h1>Cargando...</h1>' });
	setTimeout(function(){
		muestraListaLanzaderas();
	}, 200);
}

function muestraListaLanzaderas(){
	$url = "ListaLanzaderas.action?idVehiculo=" + idVehiculo;
	//alert(1);
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_lanzaderas").empty();
			$("#widget_lanzaderas").append(html);
			$.ajax({
				type: "POST",
				url: "ubicacion/listaLanzaderas.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "js/script.js",
				dataType: "script"
			});
			//$("#mydiv").hide();
$.unblockUI();
		},
		error: function(){
			$.msg("Error listando las lanzaderas");
			//$("#mydiv").hide();
$.unblockUI();
		}
	});
}