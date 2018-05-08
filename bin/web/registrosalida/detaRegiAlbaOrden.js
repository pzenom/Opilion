var fondo = false;
var mensaje = false;
//var flag = false;
var lote = 0;
var numeroAgrupacion = 1;
var numeroLinea = 1;
var lotesLeidos = 0;
var bultosLeidos = 0;
var totalBultos;
var fin = false;
var primeraVez = true;
var linea = 1;
var agrupacion = 1;
var saltaLote = false;
var numeroLote = 1;
var unico = false;
var nuevoLote = "";
var nuevaCantidad = "";
var soloUnLote = false;

$(document).ready(function(){
	// Calculamos totales albaran
	calculaTotalesAlbaran();
	prepararBultosSalida();
	$("#thCantidades").attr('style', 'width="94px;"');
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_insertar").attr("onclick", "javascript:insertaAlbaran();");
	$("#bot_vuelve").attr("onclick", "javascript:listaPedidos();");
	if ($("#tablaAlbaranesFalta").length){
	}else{
		if ($("#errorSalida").length){
		}else{
			$("#bot_insertar").show();
			$("#bot_siguienteLote").show();
		}
	}
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	/* Fin configuracion botonera */
	//Las unidades nunca pueden ser número decimales
	uds = $('.cantidadPedida').get();
	for (i = 0; i < uds.length; i++){
		id = uds[i].id;
		val = $('#' + id).text().trim();
		//alert(val);
		frag = val.split('.');
		$('#' + id).text(frag[0]);
	}
	
	$('#dropdown_temperaturaTransporte').val(2);
	$('#dropdown_portesTransporte').val(1);
	$('#dropdown_transportistas').val(52);
	$("#dropdown_temperaturaTransporte").trigger("liszt:updated");
	$("#dropdown_portesTransporte").trigger("liszt:updated");
	$("#dropdown_transportistas").trigger("liszt:updated");
});

function calculaTotalesAlbaran(){
	netos = $(".netoLinea").get();
	importeTotal = 0;
	for (i = 0; i < netos.length; i++){
		id = netos[i].id;
		neto = $("#" + id).html().trim();
		importeTotal += parseFloat(neto);
	}
	$("#importeTotal").html(importeTotal);
	kilos = $(".kilosPedidos").get();
	kilosTotal = 0;
	for (i = 0; i < kilos.length; i++){
		id = kilos[i].id;
		peso = $("#" + id).html().trim();
		kilosTotal += parseFloat(peso);
	}
	$("#pesoNetoTotal").html(kilosTotal);
	cantidades = $(".cantidadPedida").get();
	cantidadesTotal = 0;
	for (i = 0; i < cantidades.length; i++){
		id = cantidades[i].id;
		uds = $("#" + id).html().trim();
		cantidadesTotal += parseFloat(uds);
	}
	$("#cantidadTotal").html(cantidadesTotal);
	agrupaciones = $(".agrupacionesPedidas").get();
	numeroAgrupacionesTotal = 0;
	for (i = 0; i < agrupaciones.length; i++){
		id = agrupaciones[i].id;
		agrup = $("#" + id).html().trim();
		numeroAgrupacionesTotal += parseFloat(agrup);
	}
	$("#numeroAgrupacionesTotal").val(numeroAgrupacionesTotal);
	$("#resumenNumeroAgrupacionesTotal").html(numeroAgrupacionesTotal);
}

function prepararBultosSalida(){
	var bultos = $(".bultos").get();
	length = bultos.length;
	//alert(length);
	totalBultos = 0;
	for (i = 0; i < length; i++){
		var agrupacion = bultos[i];
		var idProd = agrupacion.id.split("_")[1];
		var lin = agrupacion.id.split("_")[2];
		//alert(lin);
		var cuantos = $("#bultos_" + idProd + "_" + lin).val();
		//Preparamos para leer el codigo para cada uno de los bultos a entregar
		var padre = $("#detallesSalidas_" + idProd + "_" + lin);
		var hijo = document.createElement('table');
		hijo.setAttribute("id", "tablaLotes_" + lin);
		hijo.innerHTML =
			/*'<thead>' +
				'<th style="width: 10em;">Lote</th>' +
				'<th style="width: 5em;">Cantidad</th>' +
				'<th style="width: 2em;"></th>' +
			'</thead>' +*/
			'<tbody id="tbodyLotesAgrupaciones_' + lin + '">' +
			'</tbody>';
		padre.append(hijo);
		padre = $("#tbodyLotesAgrupaciones_" + lin);
		//alert(cuantos);
		for (j = 0; j < cuantos; j++){
			var hijo2 = document.createElement('tr');
			hijo2.setAttribute("style", 'background-color: yellow;');
			hijo2.setAttribute("id", "lotesBulto_" + lin + '_' + (j + 1) + '_' + idProd);
			hijo2.setAttribute("class", "lineaLotesBulto");
			boton = '<a title="Mas lotes para la agrupacion" href="javascript:leerLotesBulto(' + lin + ',' + (j + 1) + ');"><img alt="Mas lotes para el agrupacion" src="img/anadir_01.gif"></a>';
			hijo2.innerHTML =
				'<td id="agrupacion_' + (j + 1) + '_' + lin +'" class="agrupacion agrupacion_' + lin + '" colspan="3">' + boton + '<b>AGRUPACION ' + (j + 1) + '</b></td>';
			padre.append(hijo2);
			var hijo3 = document.createElement('tr');
			hijo3.setAttribute("id", "headLotesBulto_" + lin + '_' + (j + 1) + '_' + idProd);
			hijo3.innerHTML =
				'<th style="width: 10em;">Lote</th>' +
				'<th style="width: 5em;">Cantidad</th>' +
				'<th style="width: 2em;"></th>';
			padre.append(hijo3);
			totalBultos++;
		}
	}
	asignarBultoDireccion();
}

function asignarBultoDireccion(){
	lineaUbicar = 1;
	agrupacionUbicar = 1;
	numeroLineas = $(".lineas").get().length;
	for (i = 1; i <= numeroLineas; i++){
		//queAgrupacion = 1;
		direccionesLinea = $(".direcciones_" + i).get().length;
		//alert(direccionesLinea);
		for (j = 0; j < direccionesLinea; j++){
			idDireccion = $(".direcciones_" + i).get(j).innerHTML;
			cuantasAgrupacionesDireccion = $("#bultos_" + i + "_" + idDireccion).val();
			//for (h = 0; h < cuantasAgrupacionesDireccion; h++){
			//cuantas = 2 -> necesitamos dos agrupaciones para asignar a idDireccion
			todasAgrupaciones = $(".agrupacion").get();
			//alert(todasAgrupaciones.length);
			contador = 0;
			//alert(todasAgrupaciones.length);
			for (g = 0; g < todasAgrupaciones.length; g++){
				idAgrupacion = todasAgrupaciones[g].id;
				//alert(idAgrupacion);
				//idAgrupacion para idDireccion
				$("#" + idAgrupacion).html($("#" + idAgrupacion).html() + ". Destino: " + $("#nombreDireccion_" + idDireccion).text());
				frag = idAgrupacion.split("_");
				queAgrupacion = frag[1];
				oculto = document.createElement('input');
				oculto.setAttribute("id", 'bultoDireccion_' + i + '_' + queAgrupacion + '_' + $(".direcciones_" + i).get(j).innerHTML);
				oculto.setAttribute("name", 'bultoDireccion_' + i + '_' + queAgrupacion + '_' + $(".direcciones_" + i).get(j).innerHTML);
				oculto.setAttribute("class", 'bultoDireccion');
				oculto.setAttribute("style", 'display: none;');
				$("#ocultos").append(oculto);
				//$('#bultoDireccion_' + i + '_' + queAgrupacion + '_' + $(".direcciones_" + i).get(j).innerHTML).hide();
				//quitarle a idAgrupacion la clase agrupacion
				//alert(idAgrupacion);
				$("#" + idAgrupacion).removeClass("agrupacion");
				contador++;
				if (contador == cuantasAgrupacionesDireccion)
					break;
			}
		}
	}
}

function leeLote(key_count){
	if (key_count == key_count_global){
		if ($("#loteLeyendo").val() != 0){
			if (($("#loteLeyendo").val().length == 8) && ($("#loteLeyendo").val().charAt(0) == '0')){
				document.getElementById("resultado").innerHTML = "LOTE LEIDO CORRECTAMENTE";
				lotesLeidos++;
				//alert(saltaLote);
				if (saltaLote == true)
					setTimeout("siguienteLote(false)", 1500);
				else
					setTimeout("cantidadLote(false, false)", 1500);
			}else{
				if (($("#loteLeyendo").val().length > 3) && ($("#loteLeyendo").val().charAt(0) == 'E')){
					document.getElementById("resultado").innerHTML = "LOTE LEIDO CORRECTAMENTE (Registro Entrada)";
					lotesLeidos++;
					if (saltaLote == true)
						setTimeout("siguienteLote(false)", 1500);
					else
						setTimeout("cantidadLote(false, false)", 1500);
				}else{
					if ($("#loteLeyendo").val() == 'X0000001'){//Código de lote especial. Su lectura provca un salto de agrupacion
						pasaBulto();
						$("#loteLeyendo").val("");
						setTimeout("limpiaError()", 1500);
						leeLote(0);
					}else{
						$("#loteLeyendo").val("");
						document.getElementById("resultado").innerHTML = "No ha introducido un numero de lote correcto";
						setTimeout("limpiaError()", 1500);
						leeLote(0);
					}
				}
			}
		}
	}
}

function leeLoteBulto(key_count) {
	if(key_count == key_count_global) {
		if ($("#loteLeyendo").val() != 0){
			if (($("#loteLeyendo").val().length == 8) && ($("#loteLeyendo").val().charAt(0) == '0')){
				document.getElementById("resultado").innerHTML = "LOTE LEIDO CORRECTAMENTE";
				lotesLeidos++;
				setTimeout("cantidadLote(true, false)", 1500);
			}else{
				if (($("#loteLeyendo").val().length > 3) && ($("#loteLeyendo").val().charAt(0) == 'E')){
					document.getElementById("resultado").innerHTML = "LOTE LEIDO CORRECTAMENTE (Registro Entrada)";
					lotesLeidos++;
					setTimeout("cantidadLote(true, false)", 1500);
				}else{
					if ($("#loteLeyendo").val() == 'X0000001'){//Código de lote especial. Su lectura provca un salto de agrupacion
						cerrarMensajeLeerLoteBulto();
						setTimeout("limpiaError()", 1500);
						leeLoteBulto(0);
					}else{
						$("#loteLeyendo").val("");
						document.getElementById("resultado").innerHTML = "No ha introducido un numero de lote correcto";
						setTimeout("limpiaError()", 1500);
						leeLoteBulto(0);
					}
				}
			}
		}
	}
}

function leeUnicoLote(key_count) {
	if(key_count == key_count_global) {
		if ($("#loteLeyendo").val() != 0){
			if (($("#loteLeyendo").val().length == 8) && ($("#loteLeyendo").val().charAt(0) == '0')){
				document.getElementById("resultado").innerHTML = "LOTE LEIDO CORRECTAMENTE";
				lotesLeidos++;
				setTimeout("cantidadLote(true, true)", 1500);
			}else{
				if (($("#loteLeyendo").val().length > 3) && ($("#loteLeyendo").val().charAt(0) == 'E')){
					document.getElementById("resultado").innerHTML = "LOTE LEIDO CORRECTAMENTE (Registro Entrada)";
					lotesLeidos++;
					setTimeout("cantidadLote(true, true)", 1500);
				}else{
					if ($("#loteLeyendo").val() == 'X0000001'){//Código de lote especial. Su lectura provca un salto de agrupacion
						cerrarMensajeLeerLoteBulto();
						setTimeout("limpiaError()", 1500);
						leeUnicoLote(0);
					}else{
						$("#loteLeyendo").val("");
						document.getElementById("resultado").innerHTML = "No ha introducido un numero de lote correcto";
						setTimeout("limpiaError()", 1500);
						leeUnicoLote(0);
					}
				}
			}
		}
	}
}

function leeCantidadLote(key_count) {
	if(key_count == key_count_global) {
		if ($("#cantidadLeyendo").val() != 0){
			document.getElementById("resultado").innerHTML = "CANTIDAD LEIDA CORRECTAMENTE";
			setTimeout("siguienteLote(false)", 1500);
		}
	}
}

function leeCantidadLoteBulto(key_count) {
	if(key_count == key_count_global) {
		if ($("#cantidadLeyendo").val() != 0){
				document.getElementById("resultado").innerHTML = "CANTIDAD LEIDA CORRECTAMENTE";
				setTimeout("siguienteLote(true)", 1500);
			}
	}
}

function eliminarLote(numeroLinea, numeroAgrupacion, idProd, numeroLote){
	$("#filaLote_" + numeroLinea + "_" + numeroAgrupacion + "_" + idProd + "_" + numeroLote).remove();
	$("#cantidadLoteLeido_" + numeroLinea + "_" + numeroAgrupacion + "_" + idProd + "_" + numeroLote).remove();
	$("#lotesLeidos_" + numeroLinea + "_" + numeroAgrupacion + "_" + idProd + "_" + numeroLote).remove();
}

function siguienteLote(unicoBulto){
	saltaLote = false;
	if (unicoBulto == true){
		numeroLinea = linea;
		numeroAgrupacion = agrupacion;
		//numeroAgrupacion++;
	}
	//alert("numero linea " + numeroLinea);
	//1. Leemos el lote actual antes de pasar al siguiente
	if (primeraVez)
		fin = false;
	if ($("#numeroAgrupacionesTotal").val() == 1){
		fin = true;
	}
	//alert("numeroAgrupaciones = " + $("#numeroAgrupacionesTotal").val());
	//alert(fin);
	//alert(lote);
	if (lote > 0){
		idProd = $("#idProd").text();
		//alert(idProd);
		$('#cantidad_' + numeroLinea + "_" + numeroAgrupacion + "_" + lote).html($("#cantidadLeyendo").val());
		//Oculto, para leer en el action (InseDetaAlbaOrdenAction)
		var oculto = document.createElement('input');
		oculto.setAttribute("id", 'cantidadLoteLeido_' + numeroLinea + '_' + numeroAgrupacion + '_' + idProd + '_' + lote);
		oculto.setAttribute("name", 'cantidadLoteLeido_' + numeroLinea + '_' + numeroAgrupacion + '_' + idProd + '_' + lote);
		oculto.setAttribute("class", "cantidadLoteLeido");
		oculto.setAttribute("style", 'display: none;');
		$("#ocultos").append(oculto);
		$('#cantidadLoteLeido_' + numeroLinea + '_' + numeroAgrupacion + '_' + idProd + '_' + lote).val($("#cantidadLeyendo").val());
		cerrar(false);
	}
	//alert(1);
	if (soloUnLote == false){
		//alert(unicoBulto);
		lote++;
		fondo = document.createElement('div');
		mensaje = document.createElement('div');
		fondo.setAttribute('id','fondo');
		mensaje.setAttribute('id','msg');
		$('body').append(fondo);
		document.getElementsByTagName('body')[0].appendChild(mensaje);
		$("#fondo").height($(document).height());
		cadena = "";
		if (fin)
			cadena = 'Finalizar lectura';
		else
			cadena = 'Siguiente agrupacion';
		if (unicoBulto == true)
			mensaje.innerHTML =
				'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar(true);">X</span></div>' +
				'<p style="font-size: 15px; text-align: center;">LECTURA DE LOS LOTES DEL <b>PRODUCTO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="eanProd" style="font-size: 15px; text-align: center; margin: auto;">' + $("#nombreProducto_" + linea + "").text() + '</p><p id="idProd" style="display: none;">' + $("#idProducto_" + linea + "").text() + '</p></div></p>' +
				'<p style="font-size: 15px; text-align: center;"><b>LINEA NUMERO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="linea" style="font-size: 15px; text-align: center; width: 30px; margin: auto;">' +
					linea + '</p></div></p>' +
				'<p style="font-size: 15px; text-align: center;"><b>Agrupacion:</b><div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="agrupacion" style="text-align: center; width: 30px; margin: auto; font-size: 38px;">' +
					numeroAgrupacion + '</p></div></p></br>' +
				'<h1 style="color:black;">LOTE <span id="loteActual">' + lote + '</span></h1> <input style="width: 98%; font-size: 40px; text-align: center;" type="text" id="loteLeyendo" onKeyUp="keyup(1);"/><p style="font-size: 15px; text-align: right;" id="resultado">Leyendo...</p>' +
				'<div id="botonLeerLotes" class="btAlbaran" style="height: 57px; padding: 5px;">' +
					'<button id="botLotes" class="botonGestionAlbaran" onClick="javascript:finAgrupacion();" type="button" name="botLotes">' +
						'<h2 id="tituloBotonSiguiente">Fin</h2>' +
					'</button>' +
				'</div>';
		else
			mensaje.innerHTML =
				'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar(true);">X</span></div>' +
				'<p style="font-size: 15px; text-align: center;">LECTURA DE LOS LOTES DEL <b>PRODUCTO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="eanProd" style="font-size: 15px; text-align: center; margin: auto;">' + $("#nombreProducto_" + numeroLinea + "").text() + '</p><p id="idProd" style="display: none;">' + $("#idProducto_" + numeroLinea + "").text() + '</p></div></p>' +
				'<p style="font-size: 15px; text-align: center;"><b>LINEA NUMERO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="linea" style="font-size: 15px; text-align: center; width: 30px; margin: auto;">' +
					numeroLinea + '</p></div></p>' +
				'<p style="font-size: 15px; text-align: center;"><b>Agrupacion:</b><div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="agrupacion" style="text-align: center; width: 30px; margin: auto; font-size: 38px;">' +
					numeroAgrupacion + '</p></div></p></br>' +
				'<h1 style="color:black;">LOTE <span id="loteActual">' + lote + '</span></h1> <input style="width: 98%; font-size: 40px; text-align: center;" type="text" id="loteLeyendo" onKeyUp="keyup(1);"/><p style="font-size: 15px; text-align: right;" id="resultado">Leyendo...</p>' +
				'<div id="botonLeerLotes" class="btAlbaran" style="height: 57px; padding: 5px;">' +
					'<button id="botLotes" class="botonGestionAlbaran" onClick="javascript:pasaBulto();" type="button" name="botLotes">' +
						'<h2 id="tituloBotonSiguiente">' + cadena + '</h2>' +
					'</button>' +
				'</div>';
		if (!primeraVez)
			resaltar(2, true);
		document.getElementById("loteLeyendo").focus();
		if (unicoBulto == true)
			initBulto();
		else
			init();
		if (!primeraVez)
			setTimeout("resaltar(2, false)", 1000);
		else
			primeraVez = false;
	}
}

function editarLote(numeroLinea, numeroAgrupacion, idProd, loteEditar){
	linea = numeroLinea;
	lote = loteEditar;
	//alert(lote);
	fondo = document.createElement('div');
	mensaje = document.createElement('div');
	fondo.setAttribute('id','fondo');
	mensaje.setAttribute('id','msg');
	$('body').append(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
	$("#fondo").height($(document).height());
	mensaje.innerHTML =
			'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar(true);">X</span></div>' +
			'<p style="font-size: 15px; text-align: center;">LECTURA DE LOS LOTES DEL <b>PRODUCTO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="eanProd" style="font-size: 15px; text-align: center; margin: auto;">' + $("#nombreProducto_" + numeroLinea + "").text() + '</p><p id="idProd" style="display: none;">' + $("#idProducto_" + numeroLinea + "").text() + '</p></div></p>' +
			'<p style="font-size: 15px; text-align: center;"><b>LINEA NUMERO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="linea" style="font-size: 15px; text-align: center; width: 30px; margin: auto;">' +
				numeroLinea + '</p></div></p>' +
			'<p style="font-size: 15px; text-align: center;"><b>Agrupacion:</b><div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="agrupacion" style="text-align: center; width: 30px; margin: auto; font-size: 38px;">' +
				numeroAgrupacion + '</p></div></p></br>' +
			'<h1 style="color:black;">LOTE <span id="loteActual">' + lote + '</span></h1> <input style="width: 98%; font-size: 40px; text-align: center;" type="text" id="loteLeyendo" onKeyUp="keyup(1);"/><p style="font-size: 15px; text-align: right;" id="resultado">Leyendo...</p>' +
			'<div id="botonLeerLotes" class="btAlbaran" style="height: 57px; padding: 5px;">' +
				'<button id="botLotes" class="botonGestionAlbaran" onClick="javascript:finAgrupacion();" type="button" name="botLotes">' +
					'<h2 id="tituloBotonSiguiente">Fin</h2>' +
				'</button>' +
			'</div>';
	$("#loteLeyendo").focus();
	initUnicoLote();
}

function aceptarEditarLote(numeroLinea, numeroAgrupacion, idProd, lote){
	$("#lotesLeidos_" + numeroLinea + "_" + numeroAgrupacion + "_" + idProd + "_" + lote).val(nuevoLote);
	$("#cantidadLoteLeido_" + numeroLinea + "_" + numeroAgrupacion + "_" + idProd + "_" + lote).val(nuevaCantidad);
	$("#lote_" + numeroLinea + "_" + numeroAgrupacion + "_" + lote).html(nuevoLote);
	$("#cantidad_" + numeroLinea + "_" + numeroAgrupacion + "_" + lote).html(nuevaCantidad);
}

function cancelarEditarLote(){
	$.msg("Lote no modificado");
}

function cantidadLote(unicoBulto, unicoLote){
	//alert(numeroLinea);
	if (unicoBulto == true){
		numeroLinea = linea;
		numeroAgrupacion = agrupacion;
	}
	//alert(agrupacion);
	//1. Leemos el lote actual antes de pasar al siguiente
	if (primeraVez)
		fin = false;
	if ($("#numeroAgrupacionesTotal").val() == 1){
		fin = true;
	}
	//alert(lote);
	if (lote > 0){
		idProd = $("#idProd").text();
		//alert(idProd);
		//Oculto, para leer en el action (InseDetaAlbaOrdenAction)
		var oculto = document.createElement('input');
		oculto.setAttribute("id", 'lotesLeidos_' + numeroLinea + '_' + numeroAgrupacion + '_' + idProd + '_' + lote);
		oculto.setAttribute("name", 'lotesLeidos_' + numeroLinea + '_' + numeroAgrupacion + '_' + idProd + '_' + lote);
		oculto.setAttribute("class", 'lotesLeidos');
		oculto.setAttribute("style", 'display: none;');
		$("#ocultos").append(oculto);
		oculto.value = $("#loteLeyendo").val();
		//alert(numeroLinea);
		//alert("#filaLote_" + numeroLinea + "_" + numeroAgrupacion + "_" + idProd + "_" + lote);
		//Miramos si ya existe la fila. Si existe, tenemos que modificar, no meter otra fila más
		if ($("#filaLote_" + numeroLinea + "_" + numeroAgrupacion + "_" + idProd + "_" + lote).length){
			//alert("Existe");
			//alert("ya existe, modificar");
			//alert('#lote_' + numeroLinea + "_" + numeroAgrupacion + "_" + lote);
			//alert($('#lote_' + numeroLinea + "_" + numeroAgrupacion + "_" + lote).html());
			$('#lote_' + numeroLinea + "_" + numeroAgrupacion + "_" + lote).html($("#loteLeyendo").val());
		}else{
			//alert("no existe, insertamos");
			//alert("linea: " + numeroLinea + "  agrupacion: " + numeroAgrupacion + "  producto: " + idProd + "  lote: " + lote);
			var fila = document.createElement("tr");
			fila.setAttribute("id", "filaLote_" + numeroLinea + "_" + numeroAgrupacion + "_" + idProd + "_" + lote);
			fila.setAttribute("class", "filaLote");
			fila.innerHTML =
				'<td id="lote_' + numeroLinea + "_" + numeroAgrupacion + "_" + lote + '" class="lotes_' + numeroLinea + "_" + numeroAgrupacion + '">' + $("#loteLeyendo").val() + '</td>' +
				'<td id="cantidad_' + numeroLinea + "_" + numeroAgrupacion + "_" + lote + '" class="cantidadLeida cantidad_' + numeroLinea + '"></td>' +
				'<td>' +
					'<a title="Editar lote" href="javascript:editarLote(' + numeroLinea + ',' + numeroAgrupacion + ',' + idProd + ',' + lote + ');"><img alt="Editar lote" src="img/edit.png"></a>' +
					'<a title="Eliminar lote" href="javascript:eliminarLote(' + numeroLinea + ',' + numeroAgrupacion + ',' + idProd + ',' + lote + ');"><img alt="Eliminar lote" src="img/cancel.png"></a>' +
				'</td>';
			//alert(fila);
			//alert("linea: " + numeroLinea + "  agrupacion: " + numeroAgrupacion + "  producto: " + idProd + "  lote: " + lote);
			//alert('#headLotesBulto_' + numeroLinea + '_' + numeroAgrupacion + '_' + idProd);
			//alert($('#headLotesBulto_' + numeroLinea + '_' + numeroAgrupacion + '_' + idProd).children().length);
			$('#headLotesBulto_' + numeroLinea + '_' + numeroAgrupacion + '_' + idProd).after(fila);
			//alert($('#headLotesBulto_' + numeroLinea + '_' + numeroAgrupacion + '_' + idProd).children().length);
		}
		cerrar(false);
	}
	fondo = document.createElement('div');
	mensaje = document.createElement('div');
	fondo.setAttribute('id','fondo');
	mensaje.setAttribute('id','msg');
	$('body').append(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
	$("#fondo").height($(document).height());
	cadena = "";
	if (fin)
		cadena = 'Finalizar lectura';
	else
		cadena = 'Siguiente agrupacion';
	/*alert("hola: " + $("#idProducto_" + linea + "").text());
	if (unicoBulto == true)
		mensaje.innerHTML =
			'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar(true);">X</span></div>' +
			'<p style="font-size: 15px; text-align: center;">LECTURA DE LOS LOTES DEL <b>PRODUCTO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="eanProd" style="font-size: 15px; text-align: center; margin: auto;">' + $("#nombreProducto_" + linea + "").text() + '</p><p id="idProd" style="display: none;">' + $("#idProducto_" + linea + "").text() + '</p></div></p>' +
			'<p style="font-size: 15px; text-align: center;"><b>LINEA NUMERO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="linea" style="font-size: 15px; text-align: center; width: 30px; margin: auto;">' +
				linea + '</p></div></p>' +
			'<p style="font-size: 15px; text-align: center;"><b>Agrupacion:</b><div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="agrupacion" style="text-align: center; width: 30px; margin: auto; font-size: 38px;">' +
				agrupacion + '</p></div></p></br>' +
			'<h1 style="color:black;">CANTIDAD LOTE <span id="loteActual">' + lote + '</span></h1><input style="width: 98%; font-size: 40px; text-align: center;" type="text" id="cantidadLeyendo" onKeyUp="keyup(3);"/><p style="font-size: 15px; text-align: right;" id="resultado">Leyendo...</p>' +
			'<div id="botonLeerLotes" class="btAlbaran" style="height: 57px; padding: 5px;">' +
				'<button id="botLotes" class="botonGestionAlbaran" onClick="javascript:finAgrupacion();" type="button" name="botLotes">' +
					'<h2 id="tituloBotonSiguiente">Fin</h2>' +
				'</button>' +
			'</div>';
	else
		mensaje.innerHTML =
		'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar(true);">X</span></div>' +
		'<p style="font-size: 15px; text-align: center;">LECTURA DE LOS LOTES DEL <b>PRODUCTO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="eanProd" style="font-size: 15px; text-align: center; margin: auto;">' + $("#nombreProducto_" + numeroLinea + "").text() + '</p><p id="idProd" style="display: none;">' + $("#idProducto_" + linea + "").text() + '</p></div></p>' +
		'<p style="font-size: 15px; text-align: center;"><b>LINEA NUMERO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="linea" style="font-size: 15px; text-align: center; width: 30px; margin: auto;">' +
			numeroLinea + '</p></div></p>' +
		'<p style="font-size: 15px; text-align: center;"><b>Agrupacion:</b><div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="agrupacion" style="text-align: center; width: 30px; margin: auto; font-size: 38px;">' +
			numeroAgrupacion + '</p></div></p></br>' +
		'<h1 style="color:black;">CANTIDAD LOTE <span id="loteActual">' + lote + '</span></h1><input style="width: 98%; font-size: 40px; text-align: center;" type="text" id="cantidadLeyendo" onKeyUp="keyup(3);"/><p style="font-size: 15px; text-align: right;" id="resultado">Leyendo...</p>' +
		'<div id="botonLeerLotes" class="btAlbaran" style="height: 57px; padding: 5px;">' +
			'<button id="botLotes" class="botonGestionAlbaran" onClick="javascript:pasaBulto();" type="button" name="botLotes">' +
				'<h2 id="tituloBotonSiguiente">' + cadena + '</h2>' +
			'</button>' +
		'</div>';*/
	if (unicoBulto == true)
		mensaje.innerHTML =
			'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar(true);">X</span></div>' +
			'<p style="font-size: 15px; text-align: center;">LECTURA DE LOS LOTES DEL <b>PRODUCTO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="eanProd" style="font-size: 15px; text-align: center; margin: auto;">' + $("#nombreProducto_" + numeroLinea + "").text() + '</p><p id="idProd" style="display: none;">' + $("#idProducto_" + numeroLinea + "").text() + '</p></div></p>' +
			'<p style="font-size: 15px; text-align: center;"><b>LINEA NUMERO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="linea" style="font-size: 15px; text-align: center; width: 30px; margin: auto;">' +
				numeroLinea + '</p></div></p>' +
			'<p style="font-size: 15px; text-align: center;"><b>Agrupacion:</b><div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="agrupacion" style="text-align: center; width: 30px; margin: auto; font-size: 38px;">' +
				agrupacion + '</p></div></p></br>' +
			'<h1 style="color:black;">CANTIDAD LOTE <span id="loteActual">' + lote + '</span></h1><input style="width: 98%; font-size: 40px; text-align: center;" type="text" id="cantidadLeyendo" onKeyUp="keyup(3);"/><p style="font-size: 15px; text-align: right;" id="resultado">Leyendo...</p>' +
			'<div id="botonLeerLotes" class="btAlbaran" style="height: 57px; padding: 5px;">' +
				'<button id="botLotes" class="botonGestionAlbaran" onClick="javascript:finAgrupacion();" type="button" name="botLotes">' +
					'<h2 id="tituloBotonSiguiente">Fin</h2>' +
				'</button>' +
			'</div>';
	else
		mensaje.innerHTML =
		'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar(true);">X</span></div>' +
		'<p style="font-size: 15px; text-align: center;">LECTURA DE LOS LOTES DEL <b>PRODUCTO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="eanProd" style="font-size: 15px; text-align: center; margin: auto;">' + $("#nombreProducto_" + numeroLinea + "").text() + '</p><p id="idProd" style="display: none;">' + $("#idProducto_" + numeroLinea + "").text() + '</p></div></p>' +
		'<p style="font-size: 15px; text-align: center;"><b>LINEA NUMERO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="linea" style="font-size: 15px; text-align: center; width: 30px; margin: auto;">' +
			numeroLinea + '</p></div></p>' +
		'<p style="font-size: 15px; text-align: center;"><b>Agrupacion:</b><div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="agrupacion" style="text-align: center; width: 30px; margin: auto; font-size: 38px;">' +
			numeroAgrupacion + '</p></div></p></br>' +
		'<h1 style="color:black;">CANTIDAD LOTE <span id="loteActual">' + lote + '</span></h1><input style="width: 98%; font-size: 40px; text-align: center;" type="text" id="cantidadLeyendo" onKeyUp="keyup(3);"/><p style="font-size: 15px; text-align: right;" id="resultado">Leyendo...</p>' +
		'<div id="botonLeerLotes" class="btAlbaran" style="height: 57px; padding: 5px;">' +
			'<button id="botLotes" class="botonGestionAlbaran" onClick="javascript:pasaBulto();" type="button" name="botLotes">' +
				'<h2 id="tituloBotonSiguiente">' + cadena + '</h2>' +
			'</button>' +
		'</div>';
	document.getElementById("cantidadLeyendo").focus();
	soloUnLote = unicoLote;
	if (unicoBulto == true)
		initCantidadLoteBulto();
	else
		initCantidadLote();
	if (primeraVez)
		primeraVez = false;
	unico = unicoBulto;
}

function keyup(id){
	if (id == 3){
		//Si se pulsa borrar, borramos
		frag = $("#cantidadLeyendo").val().split("X0000003");
		if (frag.length > 1){
			$("#cantidadLeyendo").val($("#cantidadLeyendo").val().replace("X0000003", ""));
			largo = $("#cantidadLeyendo").val().length;
			cadena = "";
			for (i = 0; i < parseFloat(largo - 1); i++){
				cadena += $("#cantidadLeyendo").val().charAt(i);
			}
			$("#cantidadLeyendo").val(cadena);
		}else{
			//Si no, si se pulsa intro, saltamos
			frag = $("#cantidadLeyendo").val().split("X0000002");
			if (frag.length > 1){
				$("#cantidadLeyendo").val($("#cantidadLeyendo").val().replace("X0000002", ""));
				document.getElementById("resultado").innerHTML = "CANTIDAD LEIDA CORRECTAMENTE";
				setTimeout("siguienteLote(" + unico + ")", 1500);
			}else{
				//Si no, si se pulsa cancelar, cerramos el dialogo de lectura de lotes
				frag = $("#cantidadLeyendo").val().split("X0000004");
				if (frag.length > 1){
					$("#cantidadLeyendo").val($("#cantidadLeyendo").val().replace("X0000004", ""));
					cerrar(true);
				}else{
					//Si no, si se pulsa un número, lo escribimos
					$("#cantidadLeyendo").val($("#cantidadLeyendo").val().replace("0000000", ""));
				}
			}
		}
	}else{
		//Vemos si se cancela desde otros inputs
		switch (id){
			case 1:
				frag = $("#loteLeyendo").val().split("X0000004");
				if (frag.length > 1){
					cerrar(true);
				}
			break;
			case 2:
				frag = $("#cantidadLeyendo").val().split("X0000004");
				if (frag.length > 1){
					cerrar(true);
				}
			break;
		}
	}
}

function leerLotesBulto(nlinea, nagrupacion){
	//limpiarLotesBulto(nlinea, nagrupacion);
	linea = nlinea;
	agrupacion = nagrupacion;
	//lotes_nlinea_nagrupacion
	lotesLinea = $(".lotes_" + nlinea + "_" + nagrupacion).get();
	cuantos = lotesLinea.length;
	max = 1;
	for (i = 0; i < cuantos; i++){
		id = lotesLinea[i].id;
		frag = id.split('_');
		aux = frag[3];
		if (aux > max)
			max = aux;
	}
	if (cuantos > 0)
		lote = parseFloat(max) + 1;
	else
		lote = 1;
	fondo = document.createElement('div');
	mensaje = document.createElement('div');
	fondo.setAttribute('id','fondo');
	mensaje.setAttribute('id','msg');
	$('body').append(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
	$("#fondo").height($(document).height());
	mensaje.innerHTML =
		'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar(true);">X</span></div>' +
		'<p style="font-size: 15px; text-align: center;">LECTURA DE LOS LOTES DEL <b>PRODUCTO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="eanProd" style="font-size: 15px; text-align: center; margin: auto;">' + $("#nombreProducto_" + linea + "").text() + '</p><p id="idProd" style="display: none;">' + $("#idProducto_" + linea + "").text() + '</p></div></p>' +
		'<p style="font-size: 15px; text-align: center;"><b>LINEA NUMERO</b>:<div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="linea" style="font-size: 15px; text-align: center; width: 30px; margin: auto;">' +
			linea + '</p></div></p>' +
		'<p style="font-size: 15px; text-align: center;"><b>Agrupacion:</b><div style="text-align: center; margin: auto; width: 100%; background: none repeat scroll 0% 0% ;"><p id="agrupacion" style="text-align: center; width: 30px; margin: auto; font-size: 38px;">' +
			agrupacion + '</p></div></p></br>' +
		'<h1 style="color:black;">LOTE <span id="loteActual">' + lote + '</span></h1> <input style="width: 98%; font-size: 40px; text-align: center;" type="text" id="loteLeyendo" onKeyUp="keyup(1);"/><p style="font-size: 15px; text-align: right;" id="resultado">Leyendo...</p>' +
		'<div id="botonLeerLotes" class="btAlbaran" style="height: 57px; padding: 5px;">' +
			'<button id="botLotes" class="botonGestionAlbaran" onClick="javascript:finAgrupacion();" type="button" name="botLotes">' +
				'<h2 id="tituloBotonSiguiente">Fin</h2>' +
			'</button>' +
		'</div>';
		$("#loteLeyendo").focus();
	initBulto();
}

function finAgrupacion(){
	cerrarMensajeLeerLoteBulto();
}

function pasaBulto(){
	resaltar(1, true);
	siguienteBulto();
	setTimeout("resaltar(1, false)", 1000);
}

function cerrarMensajeLeerLoteBulto(){
	$('#fondo').remove();
	document.getElementsByTagName('body')[0].removeChild(mensaje);
	fondo = false;
	mensaje = false;
	cerrar(true);
}

function siguienteBulto(){
	if (fin){
		cerrar(true);
	}else{
		cambia = false;
		if ($("#loteActual").text() != 1)
			cambia = true;
		lote = 1;
		$("#loteActual").text(lote);
		if (cambia){
			resaltar(2, true);
			setTimeout("resaltar(2, false)", 1000);
		}
		numeroAgrupacion++;
		$("#agrupacion").text(numeroAgrupacion);
		if ((numeroLinea == $(".lineas").get().length) && (numeroAgrupacion == $("#numBultos_" + numeroLinea).text())){
			fin = true;
			$("#tituloBotonSiguiente").text("Finalizar lectura");
		}else{
			var bultosLinea = $("#numBultos_" + numeroLinea).text();
			if (numeroAgrupacion > bultosLinea){
				resaltar(3, true);
				numeroLinea++;
				numeroAgrupacion = 1;
				cambia = false;
				$("#agrupacion").text(numeroAgrupacion);
				$("#linea").text(numeroLinea);
				$("#eanProd").text($("#nombreProducto_" + numeroLinea).text());
				//alert($("#eanProd").text());
				//alert("va a cambiar");
				$("#idProd").text($("#idProducto_" + numeroLinea).text());
				//alert($("#idProd").text());
				if ((numeroLinea == $(".lineas").get().length) && (numeroAgrupacion == $("#numBultos_" + numeroLinea).text())){
					fin = true;
					$("#tituloBotonSiguiente").text("Finalizar lectura");
				}
				setTimeout("resaltar(3, false)", 1000);
			}
		}
		document.getElementById("loteLeyendo").focus();
	}
}

//Tipo: 1->Bulto; 2->Lote; 3->Linea
function resaltar(tipo, muestra){
	if (tipo == 1){//Resaltar paso de agrupacion
		if (muestra){
			$("#agrupacion").attr("style", "text-align: center; width: 30px; margin: auto; border: 5px solid green; font-size: 38px;");
		}else
			$("#agrupacion").attr("style", "text-align: center; width: 30px; margin: auto; font-size: 38px;");
	} else
		if (tipo == 2){//Resaltar paso de lote
			if (muestra){
				$("#loteActual").attr("style", "border: 2px solid red; padding: 5px;");
			}else
				$("#loteActual").attr("style", "");
		} else
			if (tipo == 3){//Resaltar paso de lote
				if (muestra){
					$("#linea").attr("style", "font-size: 15px; text-align: center; width: 30px; margin: auto; border: 3px solid red; padding: 5px;");
					$("#eanProd").attr("style", "font-size: 15px; text-align: center; text-align: center; margin: auto; border: 3px solid red;");
				}else{
					$("#linea").attr("style", "font-size: 15px; text-align: center; width: 30px; margin: auto;");
					$("#eanProd").attr("style", "font-size: 15px; text-align: center; text-align: center; margin: auto;");
				}
			}
}

function limpiaError(){
	document.getElementById("resultado").innerHTML = "Leyendo ...";
}

function cerrar(fin){
	if (fin){
		lote = 0;
		numeroAgrupacion = 1;
		numeroLinea = 1;
		lotesLeidos = 0;
		bultosLeidos = 0;
		totalBultos;
		primeraVez = true;
		fin = false;
	}
	$('#fondo').remove();
	document.getElementsByTagName('body')[0].removeChild(mensaje);
	fondo = false;
	mensaje = false;
}

function init() {
	key_count_global = 0;
	document.getElementById("loteLeyendo").onchange = function() {
		key_count_global++;
		setTimeout("leeLote("+key_count_global+")", 1000);
	}
	document.getElementById("loteLeyendo").onkeypress = function() {
		key_count_global++;
		setTimeout("leeLote("+key_count_global+")", 1000);
	}
}

function initBulto(){
	key_count_global = 0;
	document.getElementById("loteLeyendo").onchange = function() {
		key_count_global++;
		setTimeout("leeLoteBulto("+key_count_global+")", 1000);
	}
	document.getElementById("loteLeyendo").onkeypress = function() {
		key_count_global++;
		setTimeout("leeLoteBulto("+key_count_global+")", 1000);
	}
}

function initUnicoLote() {
	key_count_global = 0;
	document.getElementById("loteLeyendo").onchange = function() {
		key_count_global++;
		setTimeout("leeUnicoLote("+key_count_global+")", 1000);
	}
	document.getElementById("loteLeyendo").onkeypress = function() {
		key_count_global++;
		setTimeout("leeUnicoLote("+key_count_global+")", 1000);
	}
}

function initCantidadLote() {
	key_count_global = 0;
	document.getElementById("cantidadLeyendo").onchange = function() {
		key_count_global++;
		setTimeout("leeCantidadLote("+key_count_global+")", 1000);
	}
	document.getElementById("cantidadLeyendo").onkeypress = function() {
		key_count_global++;
		setTimeout("leeCantidadLote("+key_count_global+")", 1000);
	}
}

function initCantidadLoteBulto(){
	key_count_global = 0;
	document.getElementById("cantidadLeyendo").onchange = function() {""
		key_count_global++;
		setTimeout("leeCantidadLoteBulto("+key_count_global+")", 1000);
	}
	document.getElementById("cantidadLeyendo").onkeypress = function() {
		key_count_global++;
		setTimeout("leeCantidadLoteBulto("+key_count_global+")", 1000);
	}
}

function initCantidadUnicoLote() {
	key_count_global = 0;
	document.getElementById("cantidadLeyendo").onchange = function() {
		key_count_global++;
		setTimeout("leeCantidadUnicoLote("+key_count_global+")", 1000);
	}
	document.getElementById("cantidadLeyendo").onkeypress = function() {
		key_count_global++;
		setTimeout("leeCantidadUnicoLote(" + key_count_global + ")", 1000);
	}
}

function insertaAlbaran(){
	flag = true;
	//alert($("#date_fechaVencimiento").val());
	if ($("#date_fechaVencimiento").val() == ""){
		$.msg('Debe insertar la fecha de vencimiento');
		flag = false;
	}
	if (flag){
		//Comprobamos primero que se leen los lotes para cada agrupacion del albaran
		lineasLotesBulto = $('.lineaLotesBulto').get();
		//alert(lineasLotesBulto.length);
		for (i = 0; i < lineasLotesBulto.length; i++){
			id = lineasLotesBulto[i].id;
			tbodyPadre = $('#' + id).parent();
			idTBody = tbodyPadre.attr('id');
			//alert(idTBody);
			hijos = $('#' + idTBody).children().length;
			//alert(hijos);
			if (hijos <= 2){
				$.msg("Debe leer los lotes para cada una de las agrupaciones del albar&aacute;n");
				flag = false;
				break;
			}
		}
		//Comprobamos ahora que se introducen las cantidades para cada lote leido
		if (flag){
			cantidades = $('.cantidadLeida').get();
			//alert(cantidades.length);
			for (i = 0; i < cantidades.length; i++){
				id = cantidades[i].id;
				val = $('#' + id).html().trim();
				//alert(val);
				if (val == "" || val == 0){
					$.msg("Debe introducir las cantidades de cada lote en el albar&aacute;n");
					flag = false;
					break;
				}
			}
		}
		//Comprobamos ahora que las cantidades leidas para cada agrupacion se corresponden con las cantidades pedidas
		if (flag){
			cantidades = $('.cantidadPedida').get();
			//alert(cantidades.length);
			for (i = 0; i < cantidades.length; i++){
				id = cantidades[i].id;
				//cantidadUnitaria_X, donde X es la linea del albarán
				frag = id.split('_');
				lineaAlbaran = frag[1];
				cantidadLinea = parseFloat($('#' + id).text().trim());
				//alert(cantidadLinea);
				//Buscamos las cantidades leidas para esta linea
				cantidadesLeidasLinea = $('.cantidad_' + lineaAlbaran).get();
				cantidadTotal = 0;
				for (j = 0; j < cantidadesLeidasLinea.length; j++){
					idCanti = cantidadesLeidasLinea[j].id;
					cantidad = parseFloat($('#' + idCanti).html().trim());
					cantidadTotal += parseFloat(cantidad);
				}
				if (cantidadLinea != cantidadTotal){
					$.msg('Debe leer las cantidades necesarias para cada linea del albar&aacute;n');
					flag = false;
					break;
				}
			}
		}
		if (flag){
			//La suma de los .cantidad_linea es igual a cantidadUnitaria_linea
			lineas = $(".lineas");
			cuantas = lineas.length;
			idTransportista = $('#dropdown_transportistas').val();
			idTemperatura = $('#dropdown_temperaturaTransporte').val();
			idPortes = $('#dropdown_portesTransporte').val();
			if (idTransportista == 0){
				flag = false;
				$.msg('Debe seleccionar un transportista');
			}
			if (idTemperatura == 0){
				flag = false;
				$.msg('Debe seleccionar la temperatura del transporte');
			}
			if (idPortes == 0){
				flag = false;
				$.msg('Debe seleccionar los portes del transporte');
			}
			if (flag){
				//En este punto, vamos a comprobar que los lotes introducidos existen
				//A parte de existir, miramos si hay stock suficiente, y que se corresponden con el producto de la linea
				//Lo vamos a hacer lote a lote
				lotes = $(".filaLote").get();
				flagSigue = true;
				for (i = 0; i < lotes.length; i++){
					idFilaLote = lotes[i].id;
					//filaLote_linea_agrupacion_eanProductoLinea_indiceLote
					frag = idFilaLote.split("_");
					id = frag[1] + "_" + frag[2] + "_" + frag[4];
					lote = $("#lote_" + id).html().trim();
					cantidad = $("#cantidad_" + id).html().trim();
					cantidadNecesaria = $("#cantidadUnitaria_" + frag[1]).html().trim();
					//alert(id);
					//alert(cantidadNecesaria);
					//alert(frag[2]);
					idProd = frag[3];
					$urlComprobacion = "CompruebaLotesLeidosAlbaran.action?lote=" + lote +
						"&cantidad=" + cantidad + "&cantidadPedida=" + cantidadNecesaria + "&idProducto=" + idProd;
					$.ajax({
						url: $urlComprobacion,
						cache: false,
						async: false,
						success: function(html){
							comprobacion = html.trim();
							if (comprobacion == "O"){//O = OK
							}else{
								if (comprobacion == "C"){//C = el lote no Corresponde con el producto
									$.msg("El lote leido: " + lote + " no se corresponde con el producto con idProd " + idProd);
									flagSigue = false;
								}else{
									if (comprobacion == "S"){//S = Stock insuficiente
										$.msg("No hay stock suficiente del lote: " + lote);
										flagSigue = false;
									}else{
										if (comprobacion == "N"){//N = No existe el lote
											$.msg("El lote: " + lote + " no esta registrado en la base de datos");
											flagSigue = false;
										}else{
											if (comprobacion == "Q"){//Q = No existe el lote
												$.msg("Problema con la cantidad leida. Revise las cantidades introducidas.");
												flagSigue = false;
											}
										}
									}
								}
							}
						}
					});
				}
				if (flagSigue){
					$.confirm("&#191Confirma que los lotes leidos y sus cantidades para el albaran son correctas? <br />ATENCION: Una vez generado el albaran, no podra modificarse.",
						function(){
							sigue = true;
							//Comprobar que cada agrupacion tiene al menos un lote!!
							lotesBultos = $(".lotesBultos").get();
							cuantosHay = lotesBultos.length;
							for (i = 0; i < cuantosHay; i++){
								bultoActual = $(".lotesBultos").get(i);
								hijos = $("#" + bultoActual.id).children();
								hijoEspecifico = hijos[2];
								lotesBulto = $("#" + hijoEspecifico.id).children();
								numeroHijos = lotesBulto.length;
								if (numeroHijos <= 0){
									sigue = false;
									break;
								}
							}
							if (sigue){
								$url = "InseDetaAlbaOrden.action?auto=true&cli=" + $("#cli").val() + "&idAlbaran=" + $("#idAlbaran").val() +
									"&codigoPedido=" + $("#codigoPedido").val() + "&codigoAlbaran=" + $("#codigoAlbaran").val() +
									"&numeroAgrupacionesTotal=" + $("#numeroAgrupacionesTotal").val() +
									"&pesoNetoTotal=" + $("#pesoNetoTotal").html().trim() +
									"&cantidadTotal=" + $("#cantidadTotal").html().trim() +
									"&importeTotal=" + $("#importeTotal").html().trim() +
									"&cnt=" + $("#cnt").val() + "&moa79=" + $("#moa79").val() +
									"&idTransportista=" + $("#dropdown_transportistas").val() +
									"&idTemperaturaTransporte=" + idTemperatura +
									"&idPortesTransporte=" + idPortes +
									"&observaciones=" + $("#observaciones").val() +
									"&fechaVencimiento=" + $("#date_fechaVencimiento").val() +
									"&idFormaPago=" + $("#idFormaPago").val();
								$urlI = "";
								lineas = $(".lineas");
								cuantas = lineas.length;
								for (j = 0; j < cuantas; j++){
									$urlI += "&cantidadUnitaria_" + lineas[j].id + "=" + $("#cantidadUnitaria_" + lineas[j].id).text() +
										"&netoUnitario_" + lineas[j].id + "=" + $("#netoUnitario_" + lineas[j].id).text() +
										"&netoLinea_" + lineas[j].id + "=" + $("#netoLinea_" + lineas[j].id).text() +
										"&bultos_" + $("#idProducto_" + lineas[j].id).text() + "_" + lineas[j].id + "=" +
										$("#numBultos_" + lineas[j].id).text();
								}
								$urlII = "";
								lotesLeidos = $(".lotesLeidos");
								cuantos = lotesLeidos.length;
								//$.msg(cuantos);
								sigue = true;
								for (k = 0; k < cuantos; k++){
									$urlII += "&" + lotesLeidos[k].id + "=" + $("#" + lotesLeidos[k].id).val();
									//Miramos si la cantidad para el lote se ha introducido
									//id = lotesLeidos_1_3_8436016095044_2=01478963 (lotesLeidos_linea_agrupacion_ean_loteAgrupacion = lote)
									id = lotesLeidos[k].id;
									corta = id.split("lotesLeidos_");
									//alert(corta.length);
									cadena = corta[1];
									cantidad = $("#cantidadLoteLeido_" + cadena).val();
									if (cantidad == "undefined" || cantidad <= 0){
										sigue = false;
										$.msg("Es necesario leer la cantidad para cada lote");
										break;
									}
								}
								if (sigue == true){
									$urlIII = "";
									cantidadesLotesLeidos = $(".cantidadLoteLeido");
									cuantos = cantidadesLotesLeidos.length;
									//alert(cuantos);
									for (k = 0; k < cuantos; k++){
										$urlIII += "&" + cantidadesLotesLeidos[k].id + "=" + $("#" + cantidadesLotesLeidos[k].id).val();
									}
									//alert($urlIII);
									//alert($urlII + " <<->> " + $urlIII);
									$urlIV = "";
									bultosDireccion = $(".bultoDireccion");
									cuantos = bultosDireccion.length;
									//alert(cuantos);
									for (k = 0; k < cuantos; k++){
										$urlIV += "&" + bultosDireccion[k].id + "=" + $("#" + bultosDireccion[k].id).val();
									}
									$url += $urlI + $urlII + $urlIII + $urlIV;
									//alert($url);
									$.ajax({
										url: $url,
										cache: false,
										async:false,
										success: function(html){
											$("#widget_consPedido").empty();
											$("#widget_consPedido").append(html);
											$url = "registrosalida/consRegiAlba.js";
											$.ajax({
												type: "POST",
												url: $url,
												dataType: "script"
											});
											$.ajax({
												type: "POST",
												url: "js/script.js",
												dataType: "script"
											});
										}
									});
								}
							} else
								$.msg("Para insertar el albaran es necesario leer los lotes para todas las agrupaciones");
						},
						function(){
							$.msg("El albaran NO se ha generado");
						}
					);
				}else
					$.msg("No se ha podido generar el albaran");
			}else
				$.msg("No se ha podido generar el albaran");
		}
	}
}

function muestraLotesProducto(idProducto, linea){
	$url = "MuestraLotesProducto.action?idProducto=" + idProducto;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#ajaxEstados").empty();
			$("#ajaxEstados").append(html);
		}
	});
	simple_tooltip("celdaMuestraLotesProducto_" + linea, 'tooltip', linea);
}

function simple_tooltip(target_items, name, linea){
	$('#' + target_items).each(function(i){
		if ($("#" + name + i).length > 0)
			$("#" + name + i).remove();
		html = "<div style='font-size:14px' class='" + name + "' id='" + name + i + "'>" +
			"<span>Lotes disponibles para el producto " + $("#nombreProducto_" + linea).text() + "</span>";
		lotes = $(".loteProducto").get();
		//alert(lotes.length);
		for (j = 0; j < lotes.length; j++){
			html += "<p>" + lotes[j].value + "</p>";
		}
		html += "</div>";
		$('body').append(html);
		var my_tooltip = $("#" + name + i);
		$(this).removeAttr("title").mouseover(function(){
				my_tooltip.css({opacity:0.8, display:"none"}).fadeIn(100);
		}).mousemove(function(kmouse){
				my_tooltip.css({left:kmouse.pageX+15, top:kmouse.pageY+15});
		}).mouseout(function(){
				my_tooltip.remove();
		});
	});
}