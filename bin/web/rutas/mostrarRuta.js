var rutaImagenSi = './img/si.png';
var rutaImagenNo = './img/no.png';
var rutaImagenTransparente = './img/transparente.png';
var numeroRoles = 0;

$(document).ready(function() {
	// CONFIGURACION DE LA BOTONERA
	$(".botonBotonera").hide();//Ocultamos todos los botones
	$(".botonesCategorias").hide();
	$("#bot_vuelve").attr("onclick" , "javascript:gestionRutas();");
	$("#bot_vuelve").show();
	$("#bot_actualizar").attr("onclick", "javascript:actualizarRuta();");
	$("#bot_actualizar").show();
	$("#widget_menu").show();
	//BOTONERA CONFIGURADA
	$('#tablaClientes').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			null
		],
		"sPaginationType": "full_numbers"
	});
	ResaltarFila();
	setTimeout('$("#tablaClientes").show();', 250);
});

function actualizarRuta(){
}