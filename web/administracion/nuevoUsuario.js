$(document).ready(function() {
	// CONFIGURACION DE LA BOTONERA
	$(".botonBotonera").hide();//Ocultamos todos los botones
	$(".botonesCategorias").hide();
	$("#bot_insertar").attr("onclick", "javascript:insertarUsuario();");
	$("#bot_insertar").show();
	$("#bot_vuelve").attr("onclick" , "javascript:volverAdministracion();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	//BOTONERA CONFIGURADA
	$("#texto_nombre").focus();
});

function volverAdministracion(){
	administrarSistema();
}

function insertarUsuario(){
	//Comprobar contraseña
	nombre = $("#texto_nombre").val();
	pass = $("#texto_contrasenia").val();
	sigue = true;
	if (nombre == ""){
		sigue = false;
		$.msg("Debes introducir el nombre del usuario");
	}
	if (sigue){
		if (pass == ""){
			sigue = false;
			$.msg("Debes introducir la contrase&ntildea del usuario");
		}
		if (sigue){
			confirmacion = $("#texto_confirma_contrasenia").val();
			if (pass == confirmacion){
				$.confirm("&#191Insertar usuario?",
					function(){
						//Preparar url
						var $url = "InsertarUsuario.action?login=" + nombre + "&password=" + hex_md5(pass);
						//alert($url);
						$.ajax({
							type: 'POST',
							url: $url,
							cache: false,
							async:false,
							success: function(html){
								$.msg("Usuario insertado");
							}
						});
						administrarSistema();
					},
					function(){
						$.msg("Cancelado");
					}
				);
			}else{
				$.msg("Confirme la contrase&ntildea correctamente");
			}
		}
	}
}