$(document).ready(function() {
	// CONFIGURACION DE LA BOTONERA
	$(".botonBotonera").hide();//Ocultamos todos los botones
	$(".botonesCategorias").hide();
	$("#bot_insertar").attr("onclick", "javascript:insertarRuta();");
	$("#bot_insertar").show();
	$("#bot_vuelve").attr("onclick" , "javascript:volverGestionRutas();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	//BOTONERA CONFIGURADA
	$("#texto_nombre").focus();
});

function insertarRuta(){
	//Comprobar contraseña
	nombre = $("#texto_nombre").val();
	comercial = $("#dropdown_comerciales").val();
	sigue = true;
	if (nombre == ""){
		sigue = false;
		$.msg("Debes introducir el nombre de la ruta");
	}
	if (sigue){
		if (comercial == "" || comercial == 0){
			sigue = false;
			$.msg("Debes seleccionar el comercial por defecto");
		}
		if (sigue){
			$.confirm("&#191Insertar ruta?",
				function(){
					//Preparar url
					var $url = "InsertarRuta.action?nombre=" + nombre + "&idComercialDefecto=" + comercial;
					//alert($url);
					$.ajax({
						type: 'POST',
						url: $url,
						cache: false,
						async:false,
						success: function(html){
							$.msg("Ruta insertada");
						}
					});
					volverGestionRutas();
				},
				function(){
					$.msg("Cancelado");
				}
			);
		}
	}
}

function volverGestionRutas(){
	volverAlEscritorio();
}