var rutaImagenSi = './img/si.png';
var rutaImagenNo = './img/no.png';
var rutaImagenTransparente = './img/transparente.png';
var numeroRoles = 0;

$(document).ready(function() {
	ajustarRol();
	// CONFIGURACION DE LA BOTONERA
	$(".botonBotonera").hide();//Ocultamos todos los botones
	$(".botonesCategorias").hide();
	if ($("#bot_actualizar").hasClass('puedeVer')){
		$("#bot_actualizar").attr("onclick", "javascript:actualizarConfiguracion();");
		$("#bot_actualizar").show();
	}
	$("#bot_vuelve").attr("onclick" , "javascript:volverAlEscritorio();");
	$("#bot_vuelve").show();
	$("#bot_nuevo").attr("onclick", "javascript:nuevoCliente();");
	$("#widget_menu").show();
	//BOTONERA CONFIGURADA
	padres = $('.celdaAccionPadre');
	for (i = 0; i < padres.length; i++){
		id = padres[i].id;
		colspan = $('#' + id).attr('colspan');
		nuevoColspan = parseFloat(colspan) + 1;
		$('#' + id).attr('colspan', nuevoColspan);
	}
	cargarAccionesRoles();
	cargarUsuariosRoles();
});

function pestaniaUsuarios(){
	$("#bot_nuevo").show();
}

function otraPestania(){
	$("#bot_nuevo").hide();
}

function nuevoCliente(){
	$.ajax({
		 url: "administracion/nuevoUsuario.jsp",
		 cache: false,
		 async:false,
		 success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		 }
	});
	$.ajax({
		type: "GET",
		url: "administracion/nuevoUsuario.js",
		dataType: "script"
	});
	//cargarUsuariosRoles();
}

function cargarAccionesRoles(){
	acciones = $('.filaAccion');
	for (i = 0; i < acciones.length; i++){
		accion = acciones.get(i);
		id = accion.id;
		frag = id.split('_');
		idAccion = frag[1];
		//alert(idAccion);
		accesos = $('.rolAccede_' + idAccion);
		for (j = 0; j < accesos.length; j++){
			id = accesos[j].id;
			frag = id.split('_');
			idRol = frag[1];
			//alert(idRol);
			$('#imgAccionRol_' + idAccion + "_" + idRol).attr('src', rutaImagenSi);
		}
	}
}

function cargarUsuariosRoles(){
	usuarios = $('.filaUsuario');
	for (i = 0; i < usuarios.length; i++){
		usuario = usuarios.get(i);
		id = usuario.id;
		frag = id.split('_');
		idUsuario = frag[1];
		//alert(idUsuario);
		accesos = $('.rolUsuario_' + idUsuario);
		for (j = 0; j < accesos.length; j++){
			id = accesos[j].id;
			frag = id.split('_');
			idRol = frag[1];
			//alert(idRol);
			$('#imgUsuarioRol_' + idUsuario + "_" + idRol).attr('src', rutaImagenSi);
			numeroRoles++;
		}
	}
}

function cambiaPermisos(idAccion, idRol){
	if ($('#imgAccionRol_' + idAccion + "_" + idRol).attr('src') == rutaImagenSi){
		$('#imgAccionRol_' + idAccion + "_" + idRol).attr('src', rutaImagenNo);
		$('#ocultoAccionRol_' + idAccion + "_" + idRol).val('no');
	}else{
		if ($('#imgAccionRol_' + idAccion + "_" + idRol).attr('src') == rutaImagenNo){
			$('#imgAccionRol_' + idAccion + "_" + idRol).attr('src', rutaImagenSi);
			$('#ocultoAccionRol_' + idAccion + "_" + idRol).val('si');
		}
	}
	//Marcamos como modificada (o no)
	if ($('#imgAccionRol_' + idAccion + "_" + idRol).hasClass('accionModificada')){
		$('#imgAccionRol_' + idAccion + "_" + idRol).removeClass('accionModificada');
	}else{
		$('#imgAccionRol_' + idAccion + "_" + idRol).addClass('accionModificada');
	}
}

function cambiaRol(idUsuario, idRol){
	if ($('#imgUsuarioRol_' + idUsuario + "_" + idRol).attr('src') == rutaImagenSi){
		$('#imgUsuarioRol_' + idUsuario + "_" + idRol).attr('src', rutaImagenTransparente);
		$('#ocultoUsuarioRol_' + idUsuario + "_" + idRol).val('no');
	}else{
		if ($('#imgUsuarioRol_' + idUsuario + "_" + idRol).attr('src') == rutaImagenTransparente){
			$('#imgUsuarioRol_' + idUsuario + "_" + idRol).attr('src', rutaImagenSi);
			$('#ocultoUsuarioRol_' + idUsuario + "_" + idRol).val('si');
		}
	}
	//Marcamos como modificada (o no)
	if ($('#imgUsuarioRol_' + idUsuario + "_" + idRol).hasClass('usuarioModificado')){
		$('#imgUsuarioRol_' + idUsuario + "_" + idRol).removeClass('usuarioModificado');
	}else{
		$('#imgUsuarioRol_' + idUsuario + "_" + idRol).addClass('usuarioModificado');
	}
}

function actualizarConfiguracion(){
	$.confirm("&#191Guardar la configuracion?",
		function(){
			$url = 'GuardarConfiguracionSistema.action?idEmpresa=1&registroSanitario=' + $('#text_registroSanitario').val() +
				'&lopdFactura=' + $('#text_LOPDFactura').val();
			//Cargamos las acciones / roles que hayan sido modificadas
			modificaciones = $('.accionModificada');
			for (i = 0; i < modificaciones.length; i++){
				modificacion = modificaciones[i];
				idImagen = modificacion.id;
				//alert(idImagen);
				frag = idImagen.split('_');
				idAccion = frag[1];
				idRol = frag[2];
				rutaImagen = $('').attr('src');
				$url += '&accionRolModificado_' + idAccion + "_" + idRol + "=" + $('#ocultoAccionRol_' + idAccion + "_" + idRol).val();
			}
			//Cargamos los usuarios modificados
			modificaciones = $('.usuarioModificado');
			for (i = 0; i < modificaciones.length; i++){
				modificacion = modificaciones[i];
				idImagen = modificacion.id;
				//alert(idImagen);
				frag = idImagen.split('_');
				idUsuario = frag[1];
				idRol = frag[2];
				rutaImagen = $('').attr('src');
				$url += '&usuarioRolModificado_' + idUsuario + "_" + idRol + "=" + $('#ocultoUsuarioRol_' + idUsuario + "_" + idRol).val();
			}
			//alert($url);
			$.ajax({
				url: $url,
				cache: false,
				async:false,
				success: function(html){
				}
			});
			volverAlEscritorio();
		},
		function(){
			$.msg("Cancelado");
		}
	);
}

function expanderFila(idAccion){
	//alert(idAccion);
	if ($('#filaAccion_' + idAccion).hasClass('filaAccionMostrando')){
		$('.filaAccionPadre_' + idAccion).hide();
		filas = $('.filaAccionPadre_' + idAccion);
		$('#filaAccion_' + idAccion).addClass('filaAccionOcultando');
		$('#filaAccion_' + idAccion).removeClass('filaAccionMostrando');
		$('#imagenCeldaAccion_' + idAccion).attr('src', './img/j_arrow_down.png');
	}else{
		if ($('#filaAccion_' + idAccion).hasClass('filaAccionOcultando')){
			$('.filaAccionPadre_' + idAccion).show();
			$('#filaAccion_' + idAccion).addClass('filaAccionMostrando');
			$('#filaAccion_' + idAccion).removeClass('filaAccionOcultando');
		$('#imagenCeldaAccion_' + idAccion).attr('src', './img/j_arrow_up.png');
		}
	}
}