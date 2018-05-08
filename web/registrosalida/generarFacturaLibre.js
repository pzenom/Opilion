var linea = 1;
var fondo = false;
var mensaje = false;
var decimales = 1000;

$(document).ready(function(){
	$("#nuevoIva").hide();
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$(".botonUbi").hide();
	$("#bot_vuelve").attr("onclick" , "javascript:listaFacturas();");
	$("#bot_vuelve").show();
	$("#bot_insertarCargos").show();
	$("#bot_editarCargosFactura").show();
	$("#bot_limpiaCargos").show();
	$("#bot_insertar").attr("onclick" , "javascript:generarFactura();");
	$("#bot_insertar").show();
	$("#bot_gestionarCuotas").show();
	$("#bot_addFacturaLibre").show();
	$("#widget_menu").show();
	/**/
});

function addProductoLinea(){
	//Abrir ventana donde se pregunte: Producto terminado o granel
	/*fondo = document.createElement('div');
	mensaje = document.createElement('div');
	fondo.setAttribute('id','fondo');
	mensaje.setAttribute('id','msg');
	$('body').append(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
	mensaje.innerHTML =
		'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar2();">X</span></div>' +
		'<p style="font-size: 15px; text-align: center;">AGREGAR LINEA A LA FACTURA</p>' +
		'<p style="font-size: 15px; text-align: center;">' +
			'<button id="bot_productoLibre" class="i_plus icon yellow" onclick="javascript:addLineaFacturaLibre();">Producto libre</button>' +
			'<button id="bot_loteExistente" class="i_plus icon yellow" onclick="javascript:addLineaFacturaLote();" disabled="disabled">Lote existente</button>' +
		'</p>';
	*/
	addLineaFacturaLibre();
}

function addLineaFacturaLote(){
	//$("#hiddens").append($("#contenedorProductos"));
	//$("#contenedorProductos").hide();
	//$("#bot_aceptarProducto").hide();
	//$("#breadcrum_grupo_seleccionar").show();
	//$(".temp").remove();
}

function addLineaFacturaLibre(){
	var fila = document.createElement('tr');
	fila.setAttribute("style", "height: 45px;");
	fila.setAttribute("id", "lineaFactura_" + linea);
	fila.setAttribute("class", "lineaFactura");
	fila.innerHTML =
		'<td onclick="javascript:celdaUnidades(' + linea + ');" style="vertical-align: middle; background-color: #EFF8FB;">' +
			'<span id="spanUnidades_' + linea + '" class="unidadesLinea">1</span>' +
			'<input id="textUnidades_' + linea + '" style="display: none; vertical-align: middle;" onblur="javascript:unidadesModificadas(' + linea + ');" onkeypress="return validarSoloNumeros(event);" value="1" />' +
		'</td>' +
		'<td onclick="javascript:celdaEan(' + linea + ');" style="vertical-align: middle; background-color: #EFF8FB;">' +
			'<span id="spanEan_' + linea + '">GTIN/EAN</span>' +
			'<input id="textEan_' + linea + '" style="display: none; vertical-align: middle;" onblur="javascript:eanModificado(' + linea + ');" />' +
		'</td>' +
		'<td onclick="javascript:celdaDescripcion(' + linea + ');" style="vertical-align: middle; background-color: #EFF8FB;">' +
			'<span id="spanDescripcion_' + linea + '">Descripcion</span>' +
			'<input id="textDescripcion_' + linea + '" style="display: none; vertical-align: middle;" onblur="javascript:descripcionModificada(' + linea + ');" />' +
		'</td>' +
		'<td onclick="javascript:celdaLote(' + linea + ');" style="vertical-align: middle; background-color: #EFF8FB;">' +
			'<span id="spanLote_' + linea + '">Lote</span>' +
			'<input id="textLote_' + linea + '" style="display: none; vertical-align: middle;" onblur="javascript:loteModificado(' + linea + ');" />' +
		'</td>' +
		'<td onclick="javascript:celdaKilos(' + linea + ');" style="vertical-align: middle; background-color: #EFF8FB;">' +
			'<span id="spanPesoLinea_' + linea + '">1</span>' +
			'<input id="textKilos_' + linea + '" style="display: none; vertical-align: middle;" onblur="javascript:kilosModificados(' + linea + ');" onkeypress="return validarNumerosDecimales(' + "'textKilos_" + linea + "'" + ', event);" value="1" />' +
		'</td>' +
		'<td onclick="javascript:celdaPrecio(' + linea + ');" style="vertical-align: middle; background-color: #EFF8FB;">' +
			'<span id="spanPrecio_' + linea + '">1</span>' +
			'<input id="textPrecio_' + linea + '" style="display: none; vertical-align: middle;" onblur="javascript:precioModificado(' + linea + ');" onkeypress="return validarNumerosDecimales(' + "'textPrecio_" + linea + "'" + ', event);" value="1" />' +
		'</td>' +
		'<td onclick="javascript:(' + linea + ');" style="vertical-align: middle;">' +
			'<span id="spanPrecioLinea_' + linea + '" class="totalesLinea">1</span>' +
		'</td>' +
		'<td style="vertical-align: middle; width: 15px;">' +
			'<a id="elimina_' + linea + '" title="Eliminar esta linea" href="javascript:eliminaLinea(' + linea + ')">' +
				'<img title="Eliminar esta linea" alt="Eliminar esta linea" src="img/cancel.png">' +
			'</a>' +
		'</td>';
	$("#tbodyRegistrosFactura").append(fila);
	linea++;
	//cerrar();
	$("#spanUnidadesTotal").text(parseFloat($("#spanUnidadesTotal").text()) + parseFloat(1));
	$("#spanPesoTotal").text(parseFloat($("#spanPesoTotal").text()) + parseFloat(1));
	$("#spanImporteTotal").text(parseFloat($("#spanImporteTotal").text()) + parseFloat(1));
	$("#spanSubtotal").text(parseFloat($("#spanSubtotal").text()) + parseFloat(1));
}

function eliminaLinea(linea){
	$("#lineaFactura_" + linea).unbind();
	$("#lineaFactura_" + linea).remove();
	calcularImporteTotal();
	cuotaModificada();
}

function celdaEan(numLinea){
	if ($("#spanEan_" + numLinea).is(" :visible") == true || $("#spanEan_" + numLinea).text() == ""){
		//Se está mostrando el span, asi que no es posible modificar. Habilitamos el input para modificar
		$("#spanEan_" + numLinea).hide();
		$("#textEan_" + numLinea).show();
		$("#textEan_" + numLinea).focus();
	}
}

function eanModificado(numLinea){
	$("#spanEan_" + numLinea).text($("#textEan_" + numLinea).val());
	$("#spanEan_" + numLinea).show();
	$("#textEan_" + numLinea).hide();
}

function celdaLote(numLinea){
	if ($("#spanLote_" + numLinea).is(" :visible") == true || $("#spanLote_" + numLinea).text() == ""){
		//Se está mostrando el span, asi que no es posible modificar. Habilitamos el input para modificar
		$("#spanLote_" + numLinea).hide();
		$("#textLote_" + numLinea).show();
		$("#textLote_" + numLinea).focus();
	}
}

function loteModificado(numLinea){
	$("#spanLote_" + numLinea).text($("#textLote_" + numLinea).val());
	$("#spanLote_" + numLinea).show();
	$("#textLote_" + numLinea).hide();
}

function celdaUnidades(numLinea){
	if ($("#spanUnidades_" + numLinea).is(" :visible") == true){
		//Se está mostrando el span, asi que no es posible modificar. Habilitamos el input para modificar
		$("#spanUnidades_" + numLinea).hide();
		$("#textUnidades_" + numLinea).show();
		$("#textUnidades_" + numLinea).focus();
	}
}

function unidadesModificadas(numLinea){
	ajustarDecimal('textUnidades_' + numLinea);	
	//Guardamos las unidades que habia
	udsOld = $("#spanUnidades_" + numLinea).text();
	//alert(udsOld);
	$("#spanUnidades_" + numLinea).text($("#textUnidades_" + numLinea).val());
	$("#spanUnidades_" + numLinea).show();
	$("#textUnidades_" + numLinea).hide();
	//Calcular unidades total
	total = parseFloat($("#spanUnidadesTotal").text());
	nuevoTotal = parseFloat(total) - parseFloat(udsOld) + parseFloat($("#spanUnidades_" + numLinea).text());
	$("#spanUnidadesTotal").text(nuevoTotal);
}

function celdaKilos(numLinea){
	if ($("#spanPesoLinea_" + numLinea).is(" :visible") == true || $("#spanPesoLinea_" + numLinea).text() == ""){
		//Se está mostrando el span, asi que no es posible modificar. Habilitamos el input para modificar
		$("#spanPesoLinea_" + numLinea).hide();
		$("#textKilos_" + numLinea).show();
		$("#textKilos_" + numLinea).focus();
	}
}

function kilosModificados(numLinea){
	ajustarDecimal('textKilos_' + numLinea);
	//Guardamos los kilos que habia
	kilosOld = $("#spanPesoLinea_" + numLinea).text();
	$("#spanPesoLinea_" + numLinea).text($("#textKilos_" + numLinea).val());
	//Calcular el nuevo precio de la linea
	pesoLinea = $("#spanPesoLinea_" + numLinea).text().trim();
	//alert(pesoLinea);
	precioKilo = $("#spanPrecio_" + numLinea).text().trim();
	//alert(precioKilo);
	precioLinea = parseFloat(pesoLinea) * parseFloat(precioKilo);
	//alert(precioLinea);
	precioLinea = redondearValor(precioLinea, decimales);
	//alert(precioLinea);
	$("#spanPrecioLinea_" + numLinea).html(precioLinea);
	totales = $(".totalesLinea").get();
	totalPedido = 0;
	for (i = 0; i < totales.length; i++){
		id = totales[i].id;
		total = $("#" + id).text().trim();
		totalPedido += parseFloat(total);
	}
	totalPedido = redondearValor(totalPedido, decimales);
	$("#spanSubtotal").html(totalPedido);
	$("#spanPesoLinea_" + numLinea).show();
	$("#textKilos_" + numLinea).hide();
	//Modificamos kilos totales
	/*total = parseFloat($("#spanPesoTotal").text());
	nuevoTotal = parseFloat(total) - parseFloat(kilosOld) + parseFloat($("#spanPesoLinea_" + numLinea).text());
	$("#spanPesoTotal").text(nuevoTotal);*/
	calcularImporteTotal();
}

function cerrar(borrarCargos){
	if (borrarCargos)
		$(".inputCargos").val(0);
	$("#divCargos").hide();
	$("#ocultos").append($("#divCargos"));
	$('#fondo').remove();
	document.getElementsByTagName('body')[0].removeChild(mensaje);
	fondo = false;
	mensaje = false;
	//calcularCargos();
}

function seleccionCliente(){
	var selected = $("#dropdown_clientes").val();
	frag = selected.split("_");
	selected = frag[0];
	nif = frag[1];
	//alert(selected);
	if (parseFloat(selected) > 0){
		limpiarDireccionesDelCliente();
		texto = $("#dropdown_clientes option[value=" + selected + "_" + nif + "]").text().trim();
		//alert(texto);
		$("#spanNombreCliente").text(texto);
		$("#text_nombreCliente").val(texto);
		$("#spanNifCliente").text(nif);
		$("#text_nifCliente").val(nif);
		$("#spanIdCliente").text(selected);
		$("#text_idCliente").val(selected);
		$("#contenedorSelect").show();
		$("#contenedorTexto").hide();
		$("#dropdown_formasDePago").val(0);
		$("#" + $("#dropdown_formasDePago").parent().attr("id")).children('span').text(
			$("#dropdown_formasDePago option[value=-1]").text());
		cargarFormasPago(selected);
		cargarDirecciones(selected);
	}else{
		$("#contenedorSelect").hide();
		$("#contenedorTexto").show();
		$("#textoSeleccioneCliente").text("Seleccione un cliente");
		$.msg("Seleccione un cliente");
	}
}

function formaPagoSeleccionada(){
	idFormaPago = $("#dropdown_formasDePago").val();
	if (parseFloat(idFormaPago) > 0){
		texto = $("#dropdown_formasDePago option[value=" + idFormaPago + "]").text().trim();
		$("#spanFormaPago").text(texto);
		$("#text_formaPago").val(texto);
	}
}

function direccionSeleccionada(){
	idDireccion = $("#dropdown_direcciones").val();
	if (parseFloat(idDireccion) > 0){
		texto = $("#dropdown_direcciones option[value=" + idDireccion + "]").text().trim();
		$("#spanDireccionEntrega").text(texto);
		$("#text_direccionEntrega").val(texto);
	}
}

function cargarFormasPago(cliente){
	var $url = "FormasPagoCliente.action?idUsuario=" + cliente;
	$.ajax({
		url: $url,
		cache: false,
		async: false,
		success: function(html){
			$("#formasPagoOcultas").empty();
			$("#formasPagoOcultas").append(html);
		}
	});
	//Leemos las formas de pago del cliente
	var formasPago = $(".formasPagoCliente");
	formasPago = formasPago.get();
	var cuantas = formasPago.length;
	$("#optgroupFormasPago").empty();
	if (cuantas > 0){
		for (i = 0; i < cuantas; i++){
			fp = formasPago[i];
			idFormaPago = fp.value;
			//alert(idFormaPago);
			descripcion = $("#descripcionFormaPago_" + idFormaPago).val();
			diasFormaPago = $("#diasFormaPago_" + idFormaPago).val();
			diaPago = $("#diaPago_" + idFormaPago).val();
			cuentaAsociada = $("#cuentaAsociada_" + idFormaPago).val();
			numeroCuenta = $("#numCuenta_" + idFormaPago).val();
			if (cuentaAsociada == true || cuentaAsociada == "true")
				o = new Option(descripcion + " a " + diasFormaPago + " dias. Dia de cobro: " + diaPago + ". Cuenta: " + numeroCuenta);
			else
				o = new Option(descripcion + " a " + diasFormaPago + " dias. Dia de cobro: " + diaPago + ".");
			o.id = "formaPago_" + i + "_" + cliente;
			o.value = idFormaPago;
			$("#optgroupFormasPago").append(o);
		}
	}else{
		$("#contenedorSelect").hide();
		$("#contenedorTexto").show();
		$("#textoSeleccioneCliente").text("El cliente seleccionado no tiene ninguna forma de pago asociada");
		$.msg("El cliente seleccionado no tiene ninguna forma de pago asociada");
	}
}

function cargarDirecciones(cliente){
	var $url = "DireccionesCliente.action?idUsuario=" + cliente;
	$.ajax({
		url: $url,
		cache: false,
		async: false,
		success: function(html){
			$("#direccionesOcultas").empty();
			$("#direccionesOcultas").append(html);
		}
	});
	//Leemos las direcciones del cliente
	var direcciones = $(".direccionesClienteCargadas");
	direcciones = direcciones.get();
	var cuantas = direcciones.length;
	//limpiarDireccionesUnidades();
	var dirClientesActuales = $(".direccionesCliente").get();
	var length = dirClientesActuales.length;
	//alert(length);
	//alert(cuantas);
	if (cuantas > 0){
		for (i = 0; i < cuantas; i++){
			idDireccion = direcciones[i].value;
			calle = $("#calleDireccion_" + idDireccion).val();
			localidad = $("#localidadDireccion_" + idDireccion).val();
			texto = calle + ", " + localidad;
			textoCompleto = calle + ", " + localidad;
			if (texto.length > 40)
				texto = calle.substring(0, 25) + "..., " + localidad;
			$("#entrega_-1").append('<option id="direccionClienteOculta_' + idDireccion + '" value="' + idDireccion + '" title="' + textoCompleto + '">' + texto + '</option>');
		}
		//limpiarDireccionesDelCliente();
		for (i = 0; i < length; i++){
			var id = dirClientesActuales[i].id;
			//alert(id);
			for (j = 0; j < cuantas; j++){
				idDireccion = direcciones[j].value;
				calle = $("#calleDireccion_" + idDireccion).val();
				localidad = $("#localidadDireccion_" + idDireccion).val();
				texto = calle + ", " + localidad;
				textoCompleto = calle + ", " + localidad;
				if (texto.length > 40)
					texto = calle.substring(0, 25) + "..., " + localidad;
				$("#" + id).append('<option value="' + idDireccion + '" title="' + textoCompleto + '">' + texto + '</option>');
			}
		}
		$("#contenedorSelectDirecciones").show();
		$("#contenedorTextoDirecciones").hide();
	}else{
		$("#contenedorSelectDirecciones").hide();
		$("#contenedorTextoDirecciones").show();
		$("#textoSeleccioneCliente").text("El cliente seleccionado no tiene ninguna direccion de entrega asociada");
		$.msg("El cliente seleccionado no tiene ninguna direcci&oacute;n de entrega asociada");
	}
}

function limpiarDireccionesDelCliente(){
	var dirClientesActuales = $(".direccionesCliente").get();
	var length = dirClientesActuales.length;
	//Limpia los selects
	for (i = 0; i < length; i++){
		var id = dirClientesActuales[i].id;
		var hijos = $("#" + id).children();
		var contador = hijos.length;
		//$.msg("contador: " + contador);
		for (j = 0; j < contador; j++){
			var array_nodos = document.getElementById(id).childNodes;
			for (var k = 0; k < array_nodos.length; k++) {
				if(array_nodos[k].nodeType == 1) {
					array_nodos[k].parentNode.removeChild(array_nodos[k]);
				}
			}
		}
	}
}

function cerrar2(){
	$('#fondo').remove();
	document.getElementsByTagName('body')[0].removeChild(mensaje);
	fondo = false;
	mensaje = false;
}

function generarFactura(){
	if ($("#tbodyRegistrosFactura").children().length == 0){
		$.msg("Imposible crear una factura sin ninguna linea");
	}else{
		$.confirm("&#191Desea generar la nueva factura?",
			function(){
				flag = true;
				//Si hay cuotas, comprobar que la suma de los porcentajes y los importes son correctas
				if ($('#tbodyCuotas').children().length > 1){
					//Hay cuotas definidas
					if (parseFloat(calcularSumaImportesCuotas()) != parseFloat($('#spanImporteTotal').text().trim())){
						$.msg("ERROR: Los importes introducidos para las cuotas deben sumar el importe total de la factura.");
						flag = false;
					}else{
						if (parseFloat(calcularSumaPorcentajes()) != 100){
							$.msg("ERROR: Los porcentajes introducidos para las cuotas deben sumar el 100%.");
							flag = false;
						}
					}
				}
				if (flag){
					if ($("#spanFechaVencimiento").text().length == 10){
						//Fecha metida, seguimos
						idCliente = $("#dropdown_clientes").val();
						frag = idCliente.split("_");
						idCliente = frag[0];
						if (idCliente > 0){
							//Cliente seleccionado, seguimos
							if ($("#dropdown_formasDePago").val() > 0){
								//Forma de pago seleccionada, seguimos
								if ($("#dropdown_direcciones").val() > 0){
									//Dirección seleccionada, seguimos
									//Mínimo una linea
									lineas = $(".lineaFactura").get();
									cuantas = lineas.length;
									if (cuantas > 0){
										flag = true;
										//Hay como mínimo una linea, seguimos
										for (i = 0; i < cuantas; i++){
											flag = true;
											id = lineas[i].id;
											frag2 = id.split("_");
											linea = frag2[1];
											//Kilos linea:
											kilos = $("#spanPesoLinea_" + linea).text();
											//alert(kilos);
											if (parseFloat(kilos) > 0){
												//OK
												//Uds linea:
												uds = $("#spanUnidades_" + linea).text();
												//alert(uds);
												if (parseFloat(uds) > 0){
													//OK
												}else{
													flag = false;
													$.msg("El numero de unidades debe ser para cada linea mayor que cero");
													break;
												}
											}else{
												flag = false;
												$.msg("El numero de kilos debe ser para cada linea mayor que cero");
												break;
											}
										}//END for
										if (flag == true){
											//Redondeamos campos con demasiados decimales
											//GUARDAMOS LA FACTURA!!
											$url =
												"SalvaFacturaLibre.action?idFactura=" + $("#spanNumeroFactura").text().trim() +
													"&codigoFactura=" + $("#spanCodigoFactura").text().trim() +
													"&fechavencimiento=" + $("#spanFechaVencimiento").text().trim() +
													"&importeTotal=" + Math.round(parseFloat($("#spanImporteTotal").text().trim()) * decimales) / decimales +
													"&idCliente=" + $("#spanIdCliente").text().trim() +
													"&cargostotal=" + Math.round(parseFloat($("#spanValorCargos").text().trim()) * decimales) / decimales +
													"&descuento=" + Math.round(parseFloat($("#spanDescuento").text().trim()) * decimales) / decimales +
													"&valorDescuento=" + Math.round(parseFloat($("#spanValorDescuento").text().trim()) * decimales) / decimales +
													"&subtotal=" + Math.round(parseFloat($("#spanSubtotal").text().trim()) * decimales) / decimales +
													"&fechavencimiento=" + $("#text_fechaFactura").val() +
													"&total=" + Math.round(parseFloat($("#spanImporteTotal").text().trim()) * decimales) / decimales + 
													"&iva=" + Math.round(parseFloat($("#spanValorIva").text().trim()) * decimales) / decimales +
													"&cargoTran=" + $("#cargoTran").val() +
													"&ivaCargoTran=" + $("#ivact").val() +
													"&totalCargoTran=" + Math.round(parseFloat($("#totalCargoTran").val()) * decimales) / decimales +
													"&cargoBanc=" + $("#cargoBanc").val() +
													"&ivaCargoBanc=" + $("#ivacb").val() +
													"&totalCargoBanc=" + Math.round(parseFloat($("#totalCargoBanc").val()) * decimales) / decimales +
													"&cargoDevo=" + $("#cargoDevo").val() +
													"&ivaCargoDevo=" + $("#ivacd").val() +
													"&totalCargoDevo=" + Math.round(parseFloat($("#totalCargoDevo").val()) * decimales) / decimales +
													"&ivaAplicable=" + Math.round(parseFloat($("#spanIvaAplicable").text().trim()) * decimales) / decimales +
													"&descripcionDestino=" + $("#spanDireccionEntrega").text().trim() +
													"&nombreCliente=" + $("#spanNombreCliente").text().trim() +
													"&nifCliente=" + $("#spanNifCliente").text().trim() +
													"&telefonoCliente=" + $("#spanTelefono").text().trim() +
													"&descripcionFormaPago=" + $("#spanFormaPago").text().trim() +
													"&idDireccion=" + $("#dropdown_direcciones").val() +
													"&observaciones=" + $("#observaciones").val() +
													"&radEdi=N";
											//Añadir lineas
											//De cada linea: unidades, ean, descripcion, lote, kilos, euros/kilo, importe total
											for (i = 0; i < cuantas; i++){
												id = lineas[i].id;
												frag2 = id.split("_");
												linea = frag2[1];
												$url += '&unidadesLinea_' + linea + '=' + $("#spanUnidades_" + linea).text().trim();
												$url += '&eanLinea_' + linea + '=' + $("#spanEan_" + linea).text().trim();
												$url += '&descripcionLinea_' + linea + '=' + $("#spanDescripcion_" + linea).text().trim();
												$url += '&loteLinea_' + linea + '=' + $("#spanLote_" + linea).text().trim();
												$url += '&kilosLinea_' + linea + '=' + $("#spanPesoLinea_" + linea).text().trim();
												$url += '&precioKiloLinea_' + linea + '=' + Math.round(parseFloat($("#spanPrecio_" + linea).text().trim()) * decimales) / decimales;
												$url += '&importeLinea_' + linea + '=' + Math.round(parseFloat($("#spanPrecioLinea_" + linea).text().trim()) * decimales) / decimales;
											}
											cuotas = $(".lineaCuota").get();
											//alert(lineas.length);
											flag = true;
											for (i = 0; i < cuotas.length; i++){
												id = cuotas[i].id;
												//alert("idcuota: " + id);
												frag = id.split('_');
												numCuota = frag[1];
												//id: lineaCuota_numCuota
												//alert(id);
												if ($("#spanFechaCuota_" + numCuota).text().length != 10){
													flag = false;
													$.msg("Introduzca la fecha de cobro para cada una de las cuotas");
													break;
												}
												$url +=
													"&" + id + "=" + numCuota +
													"&importeCuota_" + numCuota + "=" + $("#spanImporteCuota_" + numCuota).text().trim() +
													"&porcentajeCuota_" + numCuota + "=" + $("#spanPorcentajeCuota_" + numCuota).text().trim() +
													"&observacionesCuota_" + numCuota + "=" + $("#spanObservacionesCuota_" + numCuota).text().trim() +
													"&fechaCuota_" + numCuota + "=" + $("#spanFechaCuota_" + numCuota).text().trim();
											}
											if (flag){
												//alert($url);
												$.ajax({
													url: $url,
													cache: false,
													async:false,
													success: function(html){
														$("#widget_consPedido").empty();
														$("#widget_consPedido").append(html);
														$.ajax({
															type: "POST",
															url: "registrosalida/consRegiFact.js",
															dataType: "script"
														});
														$.ajax({
															type: "POST",
															url: "js/script.js",
															dataType: "script"
														});
														$.msg("Factura generada correctamente");
													},
														error: function(){
															$.msg("El pedido no se ha podido insertar");
													}
												});
											}
										}
									}else{
										$.msg("La factura debe tener al menos una linea");
									}
								}else{
									$.msg("Debes seleccionar la direccion de entrega");
								}
							}else{
								$.msg("Debes seleccionar la forma de pago para la factura");
							}
						}else{
							$.msg("Debes seleccionar un cliente para poder insertar la factura");
						}
					}else{
						$.msg("Debe seleccionar una fecha para la factura");
					}
				}
			},
			function(){
				$.msg("Cancelada la insercion");
			}
		);
	}
}