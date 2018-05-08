var fondo = false;
var mensaje = false;
var punto = false;
var ventana;
var interval;
var agrupar = false;

$(document).ready(function(){
	var estadoActual = $('#estado').val();
	var editar = $('#editar').val();
	$('#boton_finalizar_envasado').hide();
	$('#boton_anular_envasado').hide();
	if (estadoActual == 'Iniciado'){
		$('#boton_continuar_envasado').hide();
		$('#boton_finalizar_envasado').show();
		$(".inputRellenarMP").removeAttr("disabled");
		$(".inputRellenarEN").removeAttr("disabled");
		$(".inputRellenarENAgrupar").removeAttr("disabled");
	} else
		if (estadoActual == 'Pendiente' || estadoActual == 'Parado' || estadoActual == 'Pausado'){
			if (estadoActual == 'Pendiente'){
				$("#botContinuar").children(0).text("Iniciar");
				$('#boton_anular_envasado').show();
			}
			$(".inputRellenarMP").attr("disabled", "disabled");
			$(".inputRellenarEN").attr("disabled", "disabled");
			$(".inputRellenarENAgrupar").attr("disabled", "disabled");
			$('#boton_pausar_envasado').hide();
		} else
			if (editar == 'false' || estadoActual == 'Finalizado' || estadoActual == 'Bloqueado' || estadoActual == 'Anulado'){
				$(".inputRellenarMP").attr("disabled", "disabled");
				$(".inputRellenarEN").attr("disabled", "disabled");
				$(".inputRellenarENAgrupar").attr("disabled", "disabled");
				$('#botonesEnvasar').hide();
				$('#observa').attr('disabled', 'disabled');
			}
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	vuelveEscritorio = $('#vuelveEscritorio').val();
	//alert(vuelveEscritorio);
	if (vuelveEscritorio == 'true'){
		$("#bot_vuelve").attr("onclick", "javascript:volverAlEscritorio();");
	}else{
		$("#bot_vuelve").attr("onclick", "javascript:consEnvasadosActivos();");
	}
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	/**/
	agrupar = $("#agrupar").val();
	//alert(agrupar);
	if (agrupar == true || agrupar == "true"){
		//$("#boton_etiquetaEAN14").show();
		$("#th_eans14Elaborados").show();
		$("#td_cantidadEnvasadaAgrupar").show();
	}//else
		//$("#boton_etiquetaEAN14").hide();
	if ($("#electedEnvasesAgruparBody").children().length == 0){
		$("#electedEnvasesAgruparTable").hide();
	}
});

function continuarEnvasado(orden, estado){
	if (confirm("Continuar con el envasado " + orden + "?")){
		$.ajax({
			 url: "ContinuaProcesoEnvasado.action?orden=" + orden + "&estado=" + estado + "&idEnvasado=" + $("#idEnvasado").val(),
			 cache: false,
			 async:false,
			 success: function(html){
				$("#widget_consGPEnva").empty();
				$("#widget_consGPEnva").append(html);
				$('#estado').val("Iniciado");
			 }
			});
			$.ajax({
				type: "POST",
				url: "gpenvasado/gestionarProcesoEnvasado.js",
				dataType: "script"
			});
		}
}

function compruebaCantidadesUtilizadas(){
	var ingredientes = $('.ingrediente');
	var envases = $('.envase');
	var envasesAgrupar = $('.envaseAgrupar');
	var cuantosIngre = ingredientes.length;
	var cuantosEnva = envases.length;
	var cuantosEnvaAgrupar = envasesAgrupar.length;
	for (i = 0; i < cuantosIngre; i++){
		var id = ingredientes[i].id;
		var maximo = $('#teoricaMP_' + id).val();
		var utilizado = $('#gestionaEnvasado_real_mp_' + id).val();
		if (parseFloat(utilizado) > parseFloat(maximo)){
			return false;
		}
	}
	for (i = 0; i < cuantosEnva; i++){
		var id = envases[i].id;
		var maximo = $('#teoricaEN_' + id).val();
		var utilizado = $('#gestionaEnvasado_real_env_' + id).val();
		if (parseFloat(utilizado) > parseFloat(maximo)){
			return false;
		}
	}
	for (i = 0; i < cuantosEnvaAgrupar; i++){
		var id = envasesAgrupar[i].id;
		var maximo = $('#teoricaENAgrupar_' + id).val();
		var utilizado = $('#gestionaEnvasado_real_envAgrupar_' + id).val();
		if (parseFloat(utilizado) > parseFloat(maximo)){
			return false;
		}
	}
	return true;
}

function observacion(operacion, orden){
	if (operacion == 'F' && !compruebaCantidadesUtilizadas()){
		$.msg("No es posible seleccionar más unidades de las disponibles");
	}
	else {
		if (operacion == 'F' && ($('#estadoViejo').val() == 'Finalizado' || $('#cantidadEnvasada').text() == 0)){
			total = $('#cantidadTotal').text();
			$('#elaborado').val(total);
			if (agrupar == "true"){
				//Tenemos que pedir el numero de agrupaciones envasadas
				pideAgrupaciones(orden);
			}else{
				finalizarEnvasado(orden);
			}
		}
		else
			if (operacion == 'F'){
				fondo = document.createElement('div');
				mensaje = document.createElement('div');
				fondo.setAttribute('id','fondo');
				mensaje.setAttribute('id','msg');
				$('body').append(fondo);
				document.getElementsByTagName('body')[0].appendChild(mensaje);
				$("#fondo").height($(document).height());
				mensaje.innerHTML =
					'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar();">X</span></div>' +
					'<form id="motivoPausa" name="motivoPausa" action="">' +
						//Unidades simples envasadas
						'<div id="teniasEnvasado" style="position:absolute; text-align:center; width:50%;"><p style="font-size: 16px;">Tenias envasado <span></br></br><span id="tenias" style="font-size:20px; width:70px;">' + $("#envasado").val() + '</span> unidades</span></br></p></div>' +
						'<div id="hasEnvasado" style="margin-left: 224px; text-align: center; width: 55%;"><p style="font-size: 16px;">Has envasado</br></br><input onblur="javascript:compruebaNumero();" id="elabora" name="elabora" onkeypress="return validarSoloNumeros(event);" style="font-size:20px; width:70px;" value="0"></input>unidades</p></div><br /><hr />' +
						//EANs14 envasados
						'<div id="teniasEnvasadoAgrupar" style="position:absolute; text-align:center; width:50%;"><p style="font-size: 16px;">Tenias envasadas <span></br></br><span id="tenias" style="font-size:20px; width:70px;">' + $("#envasadoAgrupar").val() + '</span> agrupaciones</span></br></p></div>' +
						'<div id="hasEnvasadoAgrupar" style="margin-left: 224px; text-align: center; width: 55%;"><p style="font-size: 16px;">Has envasado</br></br><input onblur="javascript:compruebaNumero();" id="elaboraAgrupar" name="elaboraAgrupar" style="font-size:20px; width:70px;" onkeypress="return validarSoloNumeros(event);" value="0"></input> EANs14</p></div>' +
						'<input type="button" name="button" value="Aceptar" onClick="compruebaCheckbox(&quot;' + operacion + '&quot;, &quot;' + orden + '&quot;)">' +
					'</form>';
				$("#elabora").focus();
			}
		else {
			fondo = document.createElement('div');
			mensaje = document.createElement('div');
			fondo.setAttribute('id','fondo');
			mensaje.setAttribute('id','msg');
			$('body').append(fondo);
			document.getElementsByTagName('body')[0].appendChild(mensaje);
			$("#fondo").height($(document).height());
			mensaje.innerHTML =
				'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar();">X</span></div>' +
				'<form id="motivoPausa" name="motivoPausa" action="">' +
				//Unidades simples envasadas
				'<div id="teniasEnvasado" style="position:absolute; text-align:center; width:50%;"><p style="font-size: 16px;">Tenias envasado <span></br></br><span id="tenias" style="font-size:20px; width:70px;">' + $("#envasado").val() + '</span> unidades</span></br></p></div>' +
				'<div id="hasEnvasado" style="margin-left: 224px; text-align: center; width: 55%;"><p style="font-size: 16px;">Has envasado</br></br><input onblur="javascript:compruebaNumero();" id="elabora" name="elabora" onkeypress="return validarSoloNumeros(event);" style="font-size:20px; width:70px;" value="0"></input>unidades</p></div><br /><hr />' +
				//EANs14 envasados
				'<div id="teniasEnvasadoAgrupar" style="position:absolute; text-align:center; width:50%;"><p style="font-size: 16px;">Tenias envasadas <span></br></br><span id="tenias" style="font-size:20px; width:70px;">' + $("#envasadoAgrupar").val() + '</span> agrupaciones</span></br></p></div>' +
				'<div id="hasEnvasadoAgrupar" style="margin-left: 224px; text-align: center; width: 55%;"><p style="font-size: 16px;">Has envasado</br></br><input onblur="javascript:compruebaNumero();" id="elaboraAgrupar" name="elaboraAgrupar" style="font-size:20px; width:70px;" onkeypress="return validarSoloNumeros(event);" value="0"></input> EANs14</p></div>' +
				'<div class="superior" style="background-color:#A9A9F5 !important;"><span>Seleccione el motivo por el que pausa el proceso de envasado</span></div>' +
				'<input id="r1" type="radio" name="opciones" value="Finalizaci&oacute;n horario de trabajo"><span style="font-size: 20px;">Finalizaci&oacute;n horario de trabajo</span><br>' +
				'<input id="r2" type="radio" name="opciones" value="Cambio a otro proceso"><span style="font-size: 20px;">Cambio a otro proceso</span><br>' +
				'<input id="r3" type="radio" name="opciones" value="otro"><span style="font-size: 20px;">Otro...</span><br>' +
				'<input type="button" name="button" value="Aceptar" onClick="compruebaCheckbox(&quot;' + operacion + '&quot;, &quot;' + orden + '&quot;)">' +
				'</form>';
			$("#elabora").focus();
		}
	}
}

function compruebaNumero(){
	valor = $("#elabora").val();
	//1. Si está vacío, poner un cero
	if (valor == ""){
		$("#elabora").val(0);
	}
}

function pideExplicacion(orden){
	cerrar();
	fondo = document.createElement('div');
	mensaje = document.createElement('div');
	fondo.setAttribute('id','fondo');
	mensaje.setAttribute('id','msg');
	$('body').append(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
	$("#fondo").height($(document).height());
	mensaje.innerHTML =
		'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar();">X</span></div>' +
		'<p style="font-size: 16px;">Motivo por el que pausa el proceso de envasado</p>' +
		'<form id="explicacion" name="explicacion" action="">' +
		'<textarea id="explica" type="text" style="height: 70px; width: 98%;" name="explica"></textarea><br>' +
		'<input type="button" name="button" value="Aceptar" onClick="pausarEnvasado(&quot;' + orden + '&quot;, 100)">' +
		'</form>';
}

function pideAgrupaciones(orden){
	fondo = document.createElement('div');
	mensaje = document.createElement('div');
	fondo.setAttribute('id','fondo');
	mensaje.setAttribute('id','msg');
	$('body').append(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
	$("#fondo").height($(document).height());
	mensaje.innerHTML =
		'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar();">X</span></div>' +
		'<form id="motivoPausa" name="motivoPausa" action="">' +
		//Unidades simples envasadas
		/*'<div id="teniasEnvasado" style="position:absolute; text-align:center; width:50%;"><p style="font-size: 16px;">Tenias envasado <span></br></br><span id="tenias" style="font-size:20px; width:70px;">' + $("#envasado").val() + '</span> unidades</span></br></p></div>' +
		'<div id="hasEnvasado" style="margin-left: 224px; text-align: center; width: 55%;"><p style="font-size: 16px;">Has envasado</br></br><input onblur="javascript:compruebaNumero();" id="elabora" name="elabora" onkeypress="return validarSoloNumeros(event);" style="font-size:20px; width:70px;" value="0"></input>unidades</p></div><br /><hr />' +*/
		//EANs14 envasados
		'<div id="teniasEnvasadoAgrupar" style="position:absolute; text-align:center; width:50%;"><p style="font-size: 16px;">Tenias envasadas <span></br></br><span id="tenias" style="font-size:20px; width:70px;">' + $("#envasadoAgrupar").val() + '</span> agrupaciones</span></br></p></div>' +
		'<div id="hasEnvasadoAgrupar" style="margin-left: 224px; text-align: center; width: 55%;"><p style="font-size: 16px;">Has envasado</br></br><input onblur="javascript:compruebaNumero();" id="elaboraAgrupar" name="elaboraAgrupar" style="font-size:20px; width:70px;" onkeypress="return validarSoloNumeros(event);" value="0"></input> EANs14</p></div>' +
		'<input type="button" name="button" value="Aceptar" onClick="compruebaCheckbox(&quot;F&quot;, &quot;' + orden + '&quot;)">' +
		'</form>';
	$("#envasadoAgrupar").focus();
}

function compruebaCheckbox(operacion, orden){
	var introducidoElaborado = $("#elabora").val();
	$("#elaborado").val(parseFloat($("#elabora").val()) + parseFloat($("#tenias").val()));
	var valorSeleccionado;
	if (operacion == 'F'){
		finalizarEnvasado(orden);//, i);
	}else{
		for (i = 0; i <document.motivoPausa.opciones.length; i++){
			if (document.motivoPausa.opciones[i].checked){
				valorSeleccionado = document.motivoPausa.opciones[i].value;
				if (i == 2){
					pideExplicacion(orden);
					break;
				}else{
					if (operacion == 'P'){
					
						pausarEnvasado(orden, i);
					}
				}
			}
		}
	}
}

function pausarEnvasado(orden, opcion){
	var motivo = "";
	var flag = true;
	if (opcion == 100){
		motivo = $('#explica').val();
		if (motivo.length == 0){
			$.msg("Introduce el motivo de la pausa");
			return;
		}
	}else{
			motivo = $('#r' + (opcion + 1)).val();
		}
	$('#observaciones').attr('value', motivo);
	if ($('#estado').val() != 'Iniciado')
		$.msg('No se puede pausar un proceso de envasado que no esté en estado pendiente');
	else{
		if (flag == true && confirm("Pausar el envasado " + orden + "?")){
			var observaciones = $('#observaciones').val();
			var longitud = observaciones.length;
			var flag = false;
			if (longitud <= 0){
				if(confirm('No ha introducido ninguna observación. Esta seguro de que desea continuar?'))
					flag = true;
			}
			if (longitud > 0 || (flag == true)){
				$.ajax({
					url: "PausaProcesoEnvasado.action?orden=" + orden +
						"&idEnvasado=" + $("#idEnvasado").val() + "&elaborado=" + $("#elabora").val() + "&elaboradoAgrupar=" +  $("#elaboraAgrupar").val(),
					cache: false,
					async:false,
					success: function(html){
						cerrar();
						$("#widget_consGPEnva").empty();
						$("#widget_consGPEnva").append(html);
						$('#estado').val("Pausado");
					}
				});
				$.ajax({
					type: "POST",
					url: "gpenvasado/gestionarProcesoEnvasado.js",
					dataType: "script"
				});
			}
		}
	}
}

function cerrar(){
	$('#fondo').remove();
	document.getElementsByTagName('body')[0].removeChild(mensaje);
	fondo = false;
	mensaje = false;
}

function finalizarEnvasado(orden){
	var $urlI = "", $urlII = "";
	$("#observaciones").val($("#observa").val());
	var flag = false;
	var total = 0, envasado = 0;
	total = $('#cantidadTotal').text();
	envasado = $('#elaborado').val();
	envases = $(".entradaEnvase").get();
	materias = $(".entradaMateria").get();
	entra = true;
	//Comprobamos que la suma de cantidad real más las mermas no sobrepasa la cantidad reservada para cada entrada y ubicación
	rellenarMaterias = $(".inputRellenarMP");
	cuantos = rellenarMaterias.length;
	for (i = 0; i < cuantos; i++){
		frag = rellenarMaterias[i].id.split("_");
		if (frag[1] == "real"){
			id = frag[3] + "_" + frag[4] + "_" + frag[5];
			real = $("#gestionaEnvasado_real_mp_" + id).val();
			merma = $("#gestionaEnvasado_mermas_mp_" + id).val();
			disponible = $("#cantidadMP_" + id).text();
			$urlI += "&real_mp_" + id + "=" + real + "&mermas_mp_" + id + "=" + merma;
			if ((parseFloat(real) + parseFloat(merma)) > parseFloat(disponible)){
				entra = false;
				$.msg("La suma de la cantidad utilizada y las mermas para la entrada " + frag[3] + " y la ubicacion " + $("#ubicaMP_" + frag[3] + "_" + frag[4]).text() + " no puede superar la cantidad reservada: " + disponible);
				$("#gestionaEnvasado_real_mp_" + id).val(0);
				merma = $("#gestionaEnvasado_mermas_mp_" + id).val(0);
				break;
			}
		}
	}
	//alert($urlI);
	if (entra){
		rellenarEnvases = $(".inputRellenarEN");
		cuantos = rellenarEnvases.length;
		for (i = 0; i < cuantos; i++){	
			frag = rellenarEnvases[i].id.split("_");
			if (frag[1] == "real"){
				id = frag[3] + "_" + frag[4] + "_" + frag[5];
				//alert(id);
				real = $("#gestionaEnvasado_real_en_" + id).val();
				//alert("#gestionaEnvasado_real_en_" + id);
				//alert(real);
				merma = $("#gestionaEnvasado_mermas_en_" + id).val();
				//alert(merma);
				disponible = $("#cantidadEN_" + id).text();
				$urlII += "&real_env_" + id + "=" + real + "&mermas_env_" + id + "=" + merma;
				if ((parseFloat(real) + parseFloat(merma)) > parseFloat(disponible)){
					entra = false;
					$.msg("La suma de la cantidad utilizada y las mermas para la entrada " + frag[3] + " y la ubicacion " + $("#ubicaEN_" + frag[3] + "_" + frag[4] + "_" + frag[5]).text() + " no puede superar la cantidad reservada: " + disponible);
					$("#gestionaEnvasado_real_env_" + id).val(0);
					merma = $("#gestionaEnvasado_mermas_env_" + id).val(0);
					break;
				}
			}
		}
		//alert($urlII);
		if (entra){
			if (parseFloat(envasado) < parseFloat(total)){
				$.confirm("&#191No se han envasado todas las unidades programadas para el proceso de envasado. Continuar de todas formas?",
					function(){
						finalizar(orden, $urlI + $urlII);
					},
					function(){
						$.msg("Cancelado");
					});
			} else
				if (parseFloat(envasado) > parseFloat(total)){
					$.confirm("&#191Ha indicado una cantidad de unidades envasadas superior a las programadas. Continuar de todas formas?",
						function(){
							finalizar(orden, $urlI + $urlII);
						},
						function(){
							$.msg("Cancelado");
						});
				}else
					finalizar(orden, $urlI + $urlII);
					//alert(1);
		}
	}
}

function finalizar(orden, urlI){
	//alert(urlI);
	$urlI = urlI;
	//$('#gestionaEnvasado').attr('action','FinalizaProcesoEnvasado.action?loteAsignado=' + $("#loteProceso").text() + '=&anula=false');
	$('#orden').attr('value', orden);
	$.confirm("&#191Finalizar el envasado " + orden + "?",
		function(){
			if (agrupar == "true"){
				$url = "FinalizaProcesoEnvasado.action?anula=false&observaciones=" + $('#observaciones').val() + "&orden=" + orden + "&idEnvasado=" + $("#idEnvasado").val() + "&elaborado=" + $("#cantidadTotal").text() + "&elaboradoAgrupar=" + $("#elaboraAgrupar").val() + "&loteAsignado=" + $("#loteProceso").text() + $urlI;
				cerrar();
			}else{
				if ($("#fondo").is(':visible')){
					//alert(1);
					$url = "FinalizaProcesoEnvasado.action?anula=false&observaciones=" + $('#observaciones').val() + "&orden=" + orden + "&idEnvasado=" + $("#idEnvasado").val() + "&elaborado=" + $("#elabora").val() + "&elaboradoAgrupar=" + $("#elaboraAgrupar").val() + "&loteAsignado=" + $("#loteProceso").text() + $urlI;
					cerrar();
				}else{
					$url = "FinalizaProcesoEnvasado.action?anula=false&observaciones=" + $('#observaciones').val() + "&orden=" + orden + "&idEnvasado=" + $("#idEnvasado").val() + "&elaborado=" + $("#cantidadTotal").text() + "&loteAsignado=" + $("#loteProceso").text() + $urlI;
					//alert(2);
				}
			}
			//alert($url);
			$.ajax({
				url: $url,
				cache: false,
				async:false,
				success: function(html){
					$("#widget_consGPEnva").empty();
					$("#widget_consGPEnva").append(html);
				}
			});
			$("#estado").val("Finalizado");
			$.ajax({
				type: "POST",
				url: "gpenvasado/visualizarProceso.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "gpenvasado/visualizarProcesoEan14.js",
				dataType: "script"
			});
		},
		function(){
			$.msg("Cancelado");
		});
}

function anularEnvasado(orden){
	$.confirm("&#Anular el envasado " + orden + "?",
		function(){
			var $url = "";
			$url = "CancelaPendiente.action?proceso=" + orden;
			$.ajax({
				url: $url,
				cache: false,
				async:false,
				success: function(html){
					$("#widget_consGPEnva").empty();
					$("#widget_consGPEnva").append(html);
				}
			});
			$.ajax({
				type: "POST",
				url: "gpenvasado/listaEnvasar.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "js/script.js",
				dataType: "script"
			});
		},
		function(){
			$.msg("Cancelada la anulaci&oacute;n");
		});
}

function checkCantidadIngrediente(orden, idUbicacion){
	if ($("#gestionaEnvasado_real_mp_" + orden + "_" + idUbicacion).val() == "")
		$("#gestionaEnvasado_real_mp_" + orden + "_" + idUbicacion).val(0);
	if ($("#gestionaEnvasado_mermas_mp_" + orden + "_" + idUbicacion).val() == "")
		$("#gestionaEnvasado_mermas_mp_" + orden + "_" + idUbicacion).val(0);
}

function checkCantidadEnvase(orden, idUbicacion){
	if ($("#gestionaEnvasado_real_env_" + orden + "_" + idUbicacion).val() == "")
		$("#gestionaEnvasado_real_env_" + orden + "_" + idUbicacion).val(0);
	if ($("#gestionaEnvasado_mermas_env_" + orden + "_" + idUbicacion).val() == "")
		$("#gestionaEnvasado_mermas_env_" + orden + "_" + idUbicacion).val(0);
}

function habilitarReubicacion(){
	$(".verde").attr("colspan" , 8);
	$(".ubicacionesDevolver").attr("style", "");
	$("#encabezadoDevolver").attr("style", "");
	reubicaciones = $(".inputReubicarMP");
	for (i = 0; i < reubicaciones.length; i++){
		id = reubicaciones.get(i).id;
		//$.msg(id);
		//reubicar_E110630-7
		frag = id.split('_');
		entrada = frag[1];
		ubicacionVieja = frag[2];
		total = $("#cantidadMP" + "_" + entrada + "_" + ubicacionVieja).text();
		total = document.getElementById("cantidadMP" + "_" + entrada + "_" + ubicacionVieja).innerHTML;
		real = document.getElementById("gestionaEnvasado_real_mp_" + entrada + "_" + ubicacionVieja).value;
		mermas = $("#gestionaEnvasado_mermas_mp_" + entrada + "_" + frag[2]).val();
		mermas = document.getElementById("gestionaEnvasado_mermas_mp_" + entrada + "_" + ubicacionVieja).value;
		if ((parseFloat(real) + parseFloat(mermas)) < total){
			document.getElementById(id).removeAttribute("disabled");
		}
		else{
			document.getElementById(id).value = 'Nada para ubicar';
			document.getElementById(id).setAttribute("disabled", "");
		}
	}
	reubicaciones = $(".inputReubicarEN");
	for (i = 0; i < reubicaciones.length; i++){
	}
}

function comprobarVentanaCerrada(){
	if (ventana.closed){
		clearInterval(interval);
	}
}

function sobranteModificado(registroEntrada, idUbicacion, idPalet, tipo, idProducto){
	disponible = $("#cantidad" + tipo.toUpperCase() + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).html().trim();
	real = $("#gestionaEnvasado_real_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val();
	//sobrante = $("#gestionaEnvasado_sobrante_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val();
	//merma = parseFloat(disponible) - parseFloat(parseFloat(real) + parseFloat(sobrante));
	merma = $("#gestionaEnvasado_mermas_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val();
	sobrante = parseFloat(disponible) - parseFloat(parseFloat(real) + parseFloat(merma));
	//alert(disponible + ' real:' + real + ' sobra:' + sobrante + ' merma:' + merma);
	if (sobrante < 0){
		$("#gestionaEnvasado_real_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val(disponible);
		real = $("#gestionaEnvasado_real_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val();
		$("#gestionaEnvasado_mermas_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val(parseFloat(real) - parseFloat(disponible));
		$("#gestionaEnvasado_sobrante_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val(0);
		$.msg("La suma de la cantidad real utilizada y la sobrante no puede superar nunca la cantidad disponible");
	}else{
		$("#gestionaEnvasado_sobrante_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val(sobrante);
		//$("#gestionaEnvasado_sobrante_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val(merma);
	}
	/*if (merma < 0){
		$("#gestionaEnvasado_real_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val(disponible);
		real = $("#gestionaEnvasado_real_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val();
		$("#gestionaEnvasado_sobrante_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val(parseFloat(real) - parseFloat(disponible));
		$("#gestionaEnvasado_mermas_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val(0);
		$.msg("La suma de la cantidad real utilizada y la sobrante no puede superar nunca la cantidad disponible");
	}else{
		$("#gestionaEnvasado_mermas_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val(merma);
		//$("#gestionaEnvasado_sobrante_" + tipo + "_" + registroEntrada + "_" + idUbicacion + "_" + idPalet).val(merma);
	}*/
}