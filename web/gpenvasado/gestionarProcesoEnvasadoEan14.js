var fondo = false;
var mensaje = false;
var punto = false;
var ventana;
var interval;

$(document).ready(function(){
	//$.msg("hola");
	var estadoActual = $('#estado').val();
	//alert(estadoActual);
	var editar = $('#editar').val();
	//$.msg("Estado actual: " + estadoActual);
	$('#boton_finalizar_envasado').hide();
	$('#boton_anular_envasado').hide();
	//$.msg(estadoActual);
	if (estadoActual == 'Iniciado' || estadoActual == 'I'){
		//$('#botContinuar').attr('disabled', 'true');
		//$('#botContinuar').attr('style', 'visibility:hidden;');
		$('#boton_continuar_envasado').hide();
		$('#boton_finalizar_envasado').show();
		$(".inputRellenarPROD").removeAttr("disabled");
		$(".inputRellenarEN").removeAttr("disabled");
	} else
		if (estadoActual == 'Pendiente' || estadoActual == 'Parado' || estadoActual == 'Pausado'){
			if (estadoActual == 'Pendiente'){
				$("#botContinuar").children(0).text("Iniciar");
				$('#boton_anular_envasado').show();
			}
			$(".inputRellenarPROD").attr("disabled", "disabled");
			$(".inputRellenarEN").attr("disabled", "disabled");			//$('#botPausar').attr('disabled', 'true');
			//$('#botPausar').attr('style', 'visibility:hidden;');
			//$('#botPausar').hide();
			$('#boton_pausar_envasado').hide();
		} else
			if (editar == 'false' || estadoActual == 'Finalizado' || estadoActual == 'Bloqueado' || estadoActual == 'Anulado'){
				//$.msg("hi");
				$(".inputRellenarPROD").attr("disabled", "disabled");
				$(".inputRellenarEN").attr("disabled", "disabled");
				$('#botonesEnvasar').hide();
				$('#observa').attr('disabled', 'disabled');
				//$('#observaciones').attr('disabled', 'disabled');
				//$('#botonesEnvasar').attr('style', 'visibility:hidden;');
				//$('#observaciones').attr('disabled', 'true');
				/*
				$('#botPausar').attr('disabled', 'true');
				$('#botContinuar').attr('disabled', 'true');
				$('#botFinalizar').attr('disabled', 'true');
				*/
				//$('#botBloquear').attr('disabled', 'true');
				//$('input').attr('disabled', 'disabled');
				//habilitarReubicacion();
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
});

function continuarEnvasado(orden, estado){
	if (confirm("Continuar con el envasado " + orden + "?")){
		//$.msg("Continuando con el envasado " + orden);
		/*$('#gestionaEnvasado').attr('action','ContinuaProcesoEnvasado.action');
		$('#orden').attr('value', orden);
		$('#estado').attr('value', estado);
		document.gestionaEnvasado.submit();*/
		
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
		/*$.ajax({
			type: "POST",
			url: "js/script.js",
			dataType: "script"
		});*/
		$.ajax({
			type: "POST",
			url: "gpenvasado/gestionarProcesoEnvasadoEan14.js",
			dataType: "script"
		});
	}
}

function coprodruebaCantidadesUtilizadas(){
	var ingredientes = $('.ingrediente');
	var envases = $('.envase');
	//$.msg(ingredientes.length);
	//$.msg(envases.length);
	var cuantosIngre = ingredientes.length;
	var cuantosEnva = envases.length;
	for (i = 0; i < cuantosIngre; i++){
		var id = ingredientes[i].id;
		//$.msg(id);
		var maximo = $('#teoricaPROD_' + id).val();
		var utilizado = $('#gestionaEnvasado_real_prod_' + id).val();
		//$.msg(maximo);
		//$.msg(utilizado);
		if (parseFloat(utilizado) > parseFloat(maximo))
		{
			//$.msg("No puede seleccionar más unidades de las disponibles");
			return false;
		}
	}
	for (i = 0; i < cuantosEnva; i++){
		var id = envases[i].id;
		//$.msg(id);
		var maximo = $('#teoricaEN_' + id).val();
		var utilizado = $('#gestionaEnvasado_real_env_' + id).val();
		//$.msg(maximo);
		//$.msg(utilizado);
		if (parseFloat(utilizado) > parseFloat(maximo)){
			//$.msg("No puede seleccionar más unidades de las disponibles");
			return false;
		}
	}
	return true;
}

function observacion(operacion, orden){

	if (operacion == 'F' && !coprodruebaCantidadesUtilizadas()){
		$.msg("No es posible seleccionar más unidades de las disponibles");
	}
	//if (coprodruebaCantidadesUtilizadas() == true)
	else {
		//$.msg($('#estadoViejo').val());
		//$.msg($('#cantidadEnvasada').text());
//		if (operacion == 'F' && ( == 'Finalizado' || $('#cantidadEnvasada').val() > 0)){
		if (operacion == 'F' && ($('#estadoViejo').val() == 'Finalizado' || $('#cantidadEnvasada').text() == 0)){
			total = $('#cantidadTotal').text();
			//$.msg(total);
			$('#elaborado').val(total);
			//$.msg($('#elaborado').val());
			//coprodruebaCheckbox('F', orden);
			finalizarEnvasado(orden);
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
				//mensaje.innerHTML="<div class='superior'><span class='cerrar' title='Cerrar' onclick='cerrar();'>X</span></div><p>aqui puedes meter el formulario</p>";
				mensaje.innerHTML =
					'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar();">X</span></div>' +
					'<form id="motivoPausa" name="motivoPausa" action="">' +
					//'<p style="font-size: 16px;">Productos envasados</p>' +
					'<div id="teniasEnvasado" style="position:absolute; text-align:center; width:50%;"><p style="font-size: 16px;">Tenias envasado <span></br></br><input id="tenias" style="font-size:20px; width:70px;" disabled="disabled" value="' + $("#envasado").val() + '"></input>unidades</span></br></p></div>' +
					'<div id="hasEnvasado" style="margin-left: 224px; text-align: center; width: 55%;"><p style="font-size: 16px;">Has envasado</br></br><input onblur="javascript:coprodruebaNumero();" id="elabora" name="elabora" style="font-size:20px; width:70px;" value="0"></input>unidades</p></div>' +
					'<input type="button" name="button" value="Aceptar" onClick="coprodruebaCheckbox(&quot;' + operacion + '&quot;, &quot;' + orden + '&quot;)">' +
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
			//mensaje.innerHTML="<div class='superior'><span class='cerrar' title='Cerrar' onclick='cerrar();'>X</span></div><p>aqui puedes meter el formulario</p>";
			mensaje.innerHTML =
				'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar();">X</span></div>' +
				'<form id="motivoPausa" name="motivoPausa" action="">' +
				//'<p style="font-size: 16px;">Productos envasados</p>' +
				'<div id="teniasEnvasado" style="position:absolute; text-align:center; width:50%;"><p style="font-size: 16px;">Tenias envasado <span></br></br><input id="tenias" style="font-size:20px; width:70px;" disabled="disabled" value="' + $("#envasado").val() + '"></input>unidades</span></br></p></div>' +
				'<div id="hasEnvasado" style="margin-left: 224px; text-align: center; width: 55%;"><p style="font-size: 16px;">Has envasado</br></br><input onblur="javascript:coprodruebaNumero();" id="elabora" name="elabora" style="font-size:20px; width:70px;" value="0"></input>unidades</p></div>' +
				'<div class="superior" style="background-color:#A9A9F5 !iprodortant;"><span class="cerrar" title="Cerrar" onclick="cerrar();"></span></div>' +
				'<p style="font-size: 16px;">Seleccione el motivo por el que pausa el proceso de envasado</p>' +
				'<input id="r1" type="radio" name="opciones" value="Finalizaci&oacute;n horario de trabajo"><span style="font-size: 20px;">Finalizaci&oacute;n horario de trabajo</span><br>' +
				'<input id="r2" type="radio" name="opciones" value="Cambio a otro proceso"><span style="font-size: 20px;">Cambio a otro proceso</span><br>' +
				'<input id="r3" type="radio" name="opciones" value="otro"><span style="font-size: 20px;">Otro...</span><br>' +
				'<input type="button" name="button" value="Aceptar" onClick="coprodruebaCheckbox(&quot;' + operacion + '&quot;, &quot;' + orden + '&quot;)">' +
				'</form>';
			$("#elabora").focus();
		}
	}
}

function coprodruebaNumero(){
	valor = $("#elabora").val();
	//$.msg(valor);
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
	//mensaje.innerHTML="<div class='superior'><span class='cerrar' title='Cerrar' onclick='cerrar();'>X</span></div><p>aqui puedes meter el formulario</p>";
	mensaje.innerHTML =
		'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar();">X</span></div>' +
		'<p style="font-size: 16px;">Motivo por el que pausa el proceso de envasado</p>' +
		'<form id="explicacion" name="explicacion" action="">' +
		'<textarea id="explica" type="text" style="height: 70px; width: 98%;" name="explica"></textarea><br>' +
		'<input type="button" name="button" value="Aceptar" onClick="pausarEnvasado(&quot;' + orden + '&quot;, 100)">' +
		'</form>';
}

function coprodruebaCheckbox(operacion, orden){
	var introducidoElaborado = $("#elabora").val();
	//$.msg(introducidoElaborado);
	$("#elaborado").val(parseFloat($("#elabora").val()) + parseFloat($("#tenias").val()));
	var valorSeleccionado;
	if (operacion == 'F')
		finalizarEnvasado(orden);//, i);
	else
		for(i = 0; i <document.motivoPausa.opciones.length; i++){
			if (document.motivoPausa.opciones[i].checked){
				valorSeleccionado = document.motivoPausa.opciones[i].value;
				//$.msg(valorSeleccionado);
				if (i == 2){
					pideExplicacion(orden);
					break;
				}else
					if (operacion == 'P')
						pausarEnvasado(orden, i);
					//else
						//if (operacion == 'F')
							//finalizarEnvasado(orden);//, i);
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
						"&idEnvasado=" + $("#idEnvasado").val() + "&elaborado=" + $("#elabora").val(),
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
					url: "gpenvasado/gestionarProcesoEnvasadoEan14.js",
					dataType: "script"
				});
			}
		}
	}
}

function cerrar(){
	$('#fondo').remove();
	document.getElementsByTagName('body')[0].removeChild(mensaje);
	fondo=false;
	mensaje=false;
}

function finalizarEnvasado(orden){
	var $urlI = "", $urlII = "";
	//$.msg(finaliuzando);
	//$.msg("hola");
	$("#observaciones").val($("#observa").val());
	var flag = false;
	var total = 0, envasado = 0;
	total = $('#cantidadTotal').text();
	envasado = $('#elaborado').val();
	//$.msg(total);
	//$.msg(envasado);
	envases = $(".entradaEnvase").get();
	productos = $(".entradaProducto").get();
	entra = true;
	//Coprodrobamos que la suma de cantidad real más las mermas no sobrepasa la cantidad reservada para cada entrada y ubicación
	rellenarProductos = $(".inputRellenarPROD");
	cuantos = rellenarProductos.length;
	for (i = 0; i < cuantos; i++){
		//$.msg(rellenar[i].id);
		frag = rellenarProductos[i].id.split("_");
		if (frag[1] == "real"){
			id = frag[3] + "_" + frag[4];
			//$.msg(id);
			real = $("#gestionaEnvasado_real_prod_" + id).val();
			//$.msg(real);
			merma = $("#gestionaEnvasado_mermas_prod_" + id).val();
			//$.msg(merma);
			disponible = $("#cantidadPROD_" + id).text();
			//$.msg(disponible);

			$urlI += "&real_prod_" + id + "=" + real + "&mermas_prod_" + id + "=" + merma;

			if ((parseFloat(real) + parseFloat(merma)) > parseFloat(disponible)){
				entra = false;
				$.msg("La suma de la cantidad utilizada y las mermas para la entrada " + frag[3] + " y la ubicacion " + $("#ubicaPROD_" + frag[3] + "_" + frag[4]).text() + " no puede superar la cantidad reservada: " + disponible);
				$("#gestionaEnvasado_real_prod_" + id).val(0);
				merma = $("#gestionaEnvasado_mermas_prod_" + id).val(0);
				break;
			}
			//$.msg("#gestionEnvasado_real_prod_" + id);
			//$.msg($("#gestionEnvasado_real_prod_" + id).innerHTML);
			//$.msg(document.getElementById("gestionaEnvasado_real_prod_" + id).value);
		}
	}
	if (entra){
		rellenarEnvases = $(".inputRellenarEN");
		cuantos = rellenarEnvases.length;
		for (i = 0; i < cuantos; i++){	
			frag = rellenarEnvases[i].id.split("_");
			if (frag[1] == "real"){
				id = frag[3] + "_" + frag[4] + "_" + frag[5];
				//alert(frag);
				//alert(id);
				real = $("#gestionaEnvasado_real_env_" + id).val();
				merma = $("#gestionaEnvasado_mermas_env_" + id).val();
				disponible = $("#cantidadEN_" + id).text();
				$urlII += "&real_env_" + id + "=" + real + "&mermas_env_" + id + "=" + merma;
				//alert($urlII);
				if ((parseFloat(real) + parseFloat(merma)) > parseFloat(disponible)){
					entra = false;
					$.msg("La suma de la cantidad utilizada y las mermas para la entrada " + frag[3] + " y la ubicacion " + $("#ubicaEN_" + frag[3] + "_" + frag[4]).text() + " no puede superar la cantidad reservada: " + disponible);
					$("#gestionaEnvasado_real_env_" + id).val(0);
					merma = $("#gestionaEnvasado_mermas_env_" + id).val(0);
					break;
				}
			}
		}
	}
	if (entra){
		if (parseFloat(envasado) < parseFloat(total)){
			if (confirm("No se han envasado todas las unidades programadas para el proceso de envasado. Continuar de todas formas?")){
				var observaciones = $('#observaciones').val();
				var longitud = observaciones.length;
				if (longitud <= 0)
				{
					if(confirm('No ha introducido ninguna observación. Esta seguro de que desea continuar?'))
						flag = true;
				} else
					flag = true;
			}
		} else
			flag = true;
	}
	if (flag == true && confirm("Finalizar el envasado " + orden + "?")){
		if ($("#fondo").is(':visible')){
			$url = "FinalizaProcesoEnvasado.action?anula=false&orden=" + orden + "&idEnvasado=" + $("#idEnvasado").val() + "&elaborado=" + $("#elabora").val() + "&loteAsignado=" + $("#loteProceso").text();
			cerrar();
		}else
			$url = "FinalizaProcesoEnvasado.action?anula=false&orden=" + orden + "&idEnvasado=" + $("#idEnvasado").val() + "&elaborado=" + $("#cantidadTotal").text() + "&loteAsignado=" + $("#loteProceso").text();
		$url += $urlI + $urlII;
		//$.msg($url);
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
			url: "gpenvasado/visualizarProcesoEan14.js",
			dataType: "script"
		});
	}
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
	/*
		var disponible = $("#cantidadPROD_" + orden).children(0).val();
		//$.msg("cantidad: " + cantidad);
		var seleccion = parseFloat($("#realPROD" + orden).text());
		//$.msg("saldo: " + saldo);
		$.msg(seleccion);
		if (seleccion > disponible){
			$.msg("La cantidad seleccionada no puede superar a la cantidad de ingredientes disponibles");
			$("#realPROD" + orden).children(0).val(saldo);
		}
	*/
	
	if ($("#gestionaEnvasado_real_prod_" + orden + "_" + idUbicacion).val() == "")
		$("#gestionaEnvasado_real_prod_" + orden + "_" + idUbicacion).val(0);
	if ($("#gestionaEnvasado_mermas_prod_" + orden + "_" + idUbicacion).val() == "")
		$("#gestionaEnvasado_mermas_prod_" + orden + "_" + idUbicacion).val(0);
}

function checkCantidadEnvase(orden, idUbicacion){
	/*var disponible = $("#cantidadEN_" + orden).children(0).val();
	//$.msg("cantidad: " + cantidad);
	var seleccion = parseFloat($("#realEN" + orden).text());
	//$.msg("saldo: " + saldo);
	if (seleccion > disponible)
	{
		$.msg("La cantidad seleccionada no puede superar a la cantidad de ingredientes disponibles");
		$("#realEN" + orden).children(0).val(saldo);
	}*/
	if ($("#gestionaEnvasado_real_env_" + orden + "_" + idUbicacion).val() == "")
		$("#gestionaEnvasado_real_env_" + orden + "_" + idUbicacion).val(0);
	if ($("#gestionaEnvasado_mermas_env_" + orden + "_" + idUbicacion).val() == "")
		$("#gestionaEnvasado_mermas_env_" + orden + "_" + idUbicacion).val(0);
}

function habilitarReubicacion(){
	$(".verde").attr("colspan" , 8);
	//$(".inputReubicarPROD").removeAttr("disabled");
	$(".ubicacionesDevolver").attr("style", "");
	$("#encabezadoDevolver").attr("style", "");
	
	reubicaciones = $(".inputReubicarPROD");
	for (i = 0; i < reubicaciones.length; i++){
		id = reubicaciones.get(i).id;
		//$.msg(id);
		//reubicar_E110630-7
		frag = id.split('_');
		entrada = frag[1];
		ubicacionVieja = frag[2];
		//$.msg(entrada);
		total = $("#cantidadPROD" + "_" + entrada + "_" + ubicacionVieja).text();
		//$.msg("#cantidadPROD" + "_" + entrada + "_" + frag[2]);
		//$.msg(total);
		//$.msg(document.getElementById("cantidadPROD" + "_" + entrada + "_" + ubicacionVieja).innerHTML);
		total = document.getElementById("cantidadPROD" + "_" + entrada + "_" + ubicacionVieja).innerHTML;
		//$.msg(total);
		//real = $("#gestionaEnvasado_real_prod_" + entrada + "_" + ubicacionVieja).val();
		real = document.getElementById("gestionaEnvasado_real_prod_" + entrada + "_" + ubicacionVieja).value;
		//$.msg(real);
		mermas = $("#gestionaEnvasado_mermas_prod_" + entrada + "_" + frag[2]).val();
		mermas = document.getElementById("gestionaEnvasado_mermas_prod_" + entrada + "_" + ubicacionVieja).value;
		//$.msg(mermas);
		//$.msg((real + mermas));
		//$.msg(total);
		//$.msg((parseFloat(real) + parseFloat(mermas)));
		//$.msg(id);
		if ((parseFloat(real) + parseFloat(mermas)) < total){
			//$("#" + id).removeAttr("disabled");
			document.getElementById(id).removeAttribute("disabled");
			//document.getElementById("reubicar_E110630-7_A1:Z2:L2:E2:P3:H2").setAttribute("holaaa", "adiossssss");
			//$.msg("hiiiiiir");
		}
		else{
			document.getElementById(id).value = 'Nada para ubicar';
			document.getElementById(id).setAttribute("disabled", "");
			//$("#" + id).val("Nada para ubicar");
		}
	}
	reubicaciones = $(".inputReubicarEN");
	for (i = 0; i < reubicaciones.length; i++){
	}
}

function coprodrobarVentanaCerrada(){
	//$.msg(ventana.closed);
	if (ventana.closed){
		clearInterval(interval);
		//location.reload();
	}
}

function sobranteModificado(registroEntrada, idUbicacion, tipo){
	disponible = $("#cantidad" + tipo.toUpperCase() + "_" + registroEntrada + "_" + idUbicacion).html().trim();
	real = $("#gestionaEnvasado_real_" + tipo + "_" + registroEntrada + "_" + idUbicacion).val();
	sobrante = $("#gestionaEnvasado_sobrante_" + tipo + "_" + registroEntrada + "_" + idUbicacion).val();
	merma = parseFloat(disponible) - parseFloat(parseFloat(real) + parseFloat(sobrante));
	if (merma < 0){
		$("#gestionaEnvasado_real_" + tipo + "_" + registroEntrada + "_" + idUbicacion).val(disponible);
		real = $("#gestionaEnvasado_real_" + tipo + "_" + registroEntrada + "_" + idUbicacion).val();
		$("#gestionaEnvasado_sobrante_" + tipo + "_" + registroEntrada + "_" + idUbicacion).val(parseFloat(real) - parseFloat(disponible));
		$("#gestionaEnvasado_mermas_" + tipo + "_" + registroEntrada + "_" + idUbicacion).val(0);
		$.msg("La suma de la cantidad real utilizada y la sobrante no puede superar nunca la cantidad disponible");
	}else{
		$("#gestionaEnvasado_mermas_" + tipo + "_" + registroEntrada + "_" + idUbicacion).val(merma);
	}
}