var rutaImagenSi = './img/si.png';
var rutaImagenNo = './img/no.png';
var rutaImagenTransparente = './img/transparente.png';
var numeroRoles = 0;

$(document).ready(function() {
	// CONFIGURACION DE LA BOTONERA
	$(".botonBotonera").hide();//Ocultamos todos los botones
	$(".botonesCategorias").hide();
	$("#bot_vuelve").attr("onclick" , "javascript:volverAlEscritorio();");
	$("#bot_vuelve").show();
	$("#bot_nuevo").attr("onclick", "javascript:nuevaRuta();");
	$("#bot_nuevo").show();
	$("#widget_menu").show();
	//BOTONERA CONFIGURADA
	$('#tablaRutas').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	ResaltarFila();
	setTimeout('$("#tablaRutas").show();', 300);
});

function nuevaRuta(){
	$.ajax({
		 url: "rutas/nuevaRuta.jsp",
		 cache: false,
		 async:false,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "rutas/nuevaRuta.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function rutaSeleccionada(idRuta){
	/*$url = "CargarRuta.action?idRuta=" + idRuta;
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consRutas").empty();
		$("#widget_consRutas").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "rutas/mostrarRuta.js",
		dataType: "script"
	});*/
}