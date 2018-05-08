var action = "";

$(document).ready(function(){
	$("#spanTelefono").text($("#dropdown_telefono option[value=" + $("#idTelefono").val() + "]").text());
	$("#dropdown_telefono").val($("#idTelefono").val());
	$("#" + $("#dropdown_telefono").parent().attr("id")).children('span').text(
			$("#dropdown_telefono option[value=" + $("#idTelefono").val() + "]").text());
	$("#spanDireccionCliente").text($("#dropdown_direcciones option[value=" + $("#idDireccionCliente").val() + "]").text());
	$("#dropdown_direcciones").val($("#idDireccionCliente").val());
	$("#" + $("#dropdown_direcciones").parent().attr("id")).children('span').text(
			$("#dropdown_direcciones option[value=" + $("#idDireccionCliente").val() + "]").text());
	$("#spanDireccionEntrega").text($("#dropdown_direccionesEntrega option[value=" + $("#idDireccionEntrega").val() + "]").text());
	$("#dropdown_direccionesEntrega").val($("#idDireccionEntrega").val());
	$("#" + $("#dropdown_direccionesEntrega").parent().attr("id")).children('span').text(
			$("#dropdown_direccionesEntrega option[value=" + $("#idDireccionEntrega").val() + "]").text());
	//Del dropdown_direccionEntrega sacamos tambien el spanHorario
	//$("#spanHorario").text($("#dropdown_direccionesEntrega option[value=" + $("#idDireccionEntrega").val() + "]").attr('class'));
	$("#spanFormaPago").text($("#dropdown_formasPago option[value=" + $("#idDatoBancario").val() + "]").text().trim());
	$("#textoFormaPago").val($("#dropdown_formasPago option[value=" + $("#idDatoBancario").val() + "]").text().trim());
	$("#dropdown_formasPago").val($("#idDatoBancario").val());
	$("#" + $("#dropdown_formasPago").parent().attr("id")).children('span').text(
			$("#dropdown_formasPago option[value=" + $("#idDatoBancario").val() + "]").text());
	$("#spanNombreTransportista").text($("#dropdown_transportistas option[value=" + $("#idTransportista").val() + "]").text());
	$("#dropdown_transportistas").val($("#idTransportista").val());
	$("#" + $("#dropdown_transportistas").parent().attr("id")).children('span').text(
			$("#dropdown_transportistas option[value=" + $("#idTransportista").val() + "]").text());
	$("#spanTemperatura").text($("#dropdown_temperaturas option[value=" + $("#idTemperatura").val() + "]").text());
	$("#dropdown_temperaturas").val($("#idTemperatura").val());
	$("#" + $("#dropdown_temperaturas").parent().attr("id")).children('span').text(
			$("#dropdown_temperaturas option[value=" + $("#idTemperatura").val() + "]").text());
	$("#spanPortes").text($("#dropdown_portes option[value=" + $("#idPortes").val() + "]").text());
	$("#dropdown_portes").val($("#idPortes").val());
	$("#" + $("#dropdown_portes").parent().attr("id")).children('span').text(
			$("#dropdown_portes option[value=" + $("#idPortes").val() + "]").text());
	$("#nuevoIva").hide();
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$(".botonUbi").hide();
	$("#bot_vuelve").attr("onclick" , "javascript:listaAlbaranes();");
	$("#bot_vuelve").show();
	$("#bot_actualizar").attr("onclick" , "javascript:actualizarAlbaran(true);");
	$("#bot_actualizar").show();
	/*$("#bot_imprimir").attr("onclick" , "javascript:reporte('" + $("#spanCodigoAlbaran").text().trim() + "');");
	$("#bot_imprimir").show();*/
	$("#widget_menu").show();
	/**/
	//$.msg("Para modificar un campo, haz click sobre el");
	$("#sectionFormaPago").bind("contextmenu", function(e) {
		$('#textoFormaPago').show();
		$('#textoFormaPago').focus();
		$("#spanFormaPago").hide();
		return false;
	});
});

function reporte(codigoAlbaran){
	actualizarAlbaran(false);
	action = "ConsAlbaOrdenJR.action?codigoAlbaran=" + codigoAlbaran;
	/*$("#dialogo").dialog({
		close: function(event, ui) {
			if ( event.originalEvent && $(event.originalEvent.target).closest(".ui-dialog-titlebar-close").length ) {
				$.msg("Cancelado el reporte");
			}
		}}).find("button").click(function(){
			//Cerramos el dialogo
			$(this).closest(".ui-dialog-contenido").dialog("close");
		});*/
	preparar('nuevo');
}

function textoFormaPagoModificado2(){
	//alert(1);
	$("#spanFormaPago").text($("#textoFormaPago").val());
	$("#textoFormaPago").hide();
	$("#spanFormaPago").show();
}

function preparar(nuevoViejo){
	if (nuevoViejo == 'nuevo'){
		action += "&nuevo=true";
	}else
		action += "&nuevo=false";
	//action += "&encabezado=" + $('#checkEncabezado').is(':checked');
	//action += "&precios=" + $('#checkPrecios').is(':checked');
	action += "&encabezado=true&precios=true";
	parent.get_ventana_emergente('ALBA', action,'no','no','800','640','','');
}

function celdaDescripcion(numLinea, idBulto){
	if ($("#spanDescripcion_" + numLinea + "_" + idBulto).is(" :visible") == true){
		//Se está mostrando el span, asi que no es posible modificar. Habilitamos el input para modificar
		$("#spanDescripcion_" + numLinea + "_" + idBulto).hide();
		$("#textDescripcion_" + numLinea + "_" + idBulto).show();
		$("#textDescripcion_" + numLinea + "_" + idBulto).focus();
	}
}

function descripcionModificada(numLinea, idBulto){
	$("#spanDescripcion_" + numLinea + "_" + idBulto).text($("#textDescripcion_" + numLinea + "_" + idBulto).val());
	$("#spanDescripcion_" + numLinea + "_" + idBulto).show();
	$("#textDescripcion_" + numLinea + "_" + idBulto).hide();
}

function celdaPrecio(numLinea, idBulto){
	if ($("#spanPrecio_" + numLinea + "_" + idBulto).is(" :visible") == true){
		//Se está mostrando el span, asi que no es posible modificar. Habilitamos el input para modificar
		$("#spanPrecio_" + numLinea + "_" + idBulto).hide();
		$("#textPrecio_" + numLinea + "_" + idBulto).show();
		$("#textPrecio_" + numLinea + "_" + idBulto).focus();
	}
}

function precioModificado(numLinea, idBulto){
	precioUnitario = $("#textPrecio_" + numLinea + "_" + idBulto).val();
	precioUnitario = redondearValor(precioUnitario, 1000);
	ajustarDecimal('textPrecio_' + numLinea + "_" + idBulto);
	$("#spanPrecio_" + numLinea + "_" + idBulto).text(precioUnitario);
	//Calcular el nuevo precio de la linea
	pesoLinea = $("#spanPesoLinea_" + numLinea + "_" + idBulto).html().trim();
	precioKilo = $("#spanPrecio_" + numLinea + "_" + idBulto).html().trim();
	precioLinea = parseFloat(pesoLinea) * parseFloat(precioKilo);
	precioLinea = redondearValor(precioLinea, 1000);
	$("#spanPrecioLinea_" + numLinea + "_" + idBulto).html(precioLinea);
	totales = $(".totalesLinea").get();
	totalPedido = 0;
	for (i = 0; i < totales.length; i++){
		id = totales[i].id;
		total = $("#" + id).html().trim();
		totalPedido += parseFloat(total);
	}
	totalPedido = redondearValor(totalPedido, 1000);
	$("#spanImporteTotal").html(totalPedido);
	$("#spanPrecio_" + numLinea + "_" + idBulto).show();
	$("#textPrecio_" + numLinea + "_" + idBulto).hide();
}

function modificarIva(){
	$("#spanIvaAplicable").hide();
	$("#nuevoIva").show();
	$("#nuevoIva").focus();
}

function ivaModificado(){
	$("#spanIvaAplicable").text($("#nuevoIva").val());
	$("#spanIvaAplicable").show();
	$("#nuevoIva").hide();
	textoFormaPagoModificado();
}

function modificaDireccionCliente(){
	$("#spanDireccionCliente").hide();
	$("#divDropdownDirecciones").show();
	$("#dropdown_direcciones").focus();
}

function direccionClienteModificada(){
	$("#spanDireccionCliente").text($("#dropdown_direcciones option[value=" + $("#dropdown_direcciones").val() + "]").text());
	$("#divDropdownDirecciones").hide();
	$("#spanDireccionCliente").show();
}

function modificaDireccionEntrega(){
	$("#spanDireccionEntrega").hide();
	$("#divDropdownDireccionesEntrega").show();
	$("#dropdown_direccionesEntrega").focus();
}

function direccionEntregaModificada(){
	$("#spanDireccionEntrega").text($("#dropdown_direccionesEntrega option[value=" + $("#dropdown_direccionesEntrega").val() + "]").text());
	//Del dropdown_direccionEntrega sacamos tambien el spanHorario
	//$("#spanHorario").text($("#dropdown_direccionesEntrega option[value=" + $("#dropdown_direccionesEntrega").val() + "]").attr('class'));
	$("#divDropdownDireccionesEntrega").hide();
	$("#spanDireccionEntrega").show();
}

function modificaHorarioEntrega(){
	$("#spanHorario").hide();
	$("#text_horario").show();
	$("#text_horario").focus();
}

function horarioEntregaModificado(){
	$("#spanHorario").text($("#text_horario").val());
	$("#text_horario").hide();
	$("#spanHorario").show();
}

function modificaTelefono(){
	$("#spanTelefono").hide();
	$("#divDropdownTelefono").show();
	$("#dropdown_telefono").focus();
}

function telefonoModificado(){
	$("#spanTelefono").text($("#dropdown_telefono option[value=" + $("#dropdown_telefono").val() + "]").text());
	$("#divDropdownTelefono").hide();
	$("#spanTelefono").show();
}

function modificaTransportista(){
	$("#spanNombreTransportista").hide();
	$("#divDropdownTransportista").show();
	$("#dropdown_transportistas").focus();
}

function transportistaModificado(){
	$("#spanNombreTransportista").text($("#dropdown_transportistas option[value=" + $("#dropdown_transportistas").val() + "]").text());
	$("#divDropdownTransportista").hide();
	$("#spanNombreTransportista").show();
}

function modificaTemperatura(){
	$("#spanTemperatura").hide();
	$("#divDropdownTemperatura").show();
	$("#dropdown_temperaturas").focus();
}

function temperaturaModificada(){
	$("#spanTemperatura").text($("#dropdown_temperaturas option[value=" + $("#dropdown_temperaturas").val() + "]").text());
	$("#divDropdownTemperatura").hide();
	$("#spanTemperatura").show();
}

function modificaPortes(){
	$("#spanPortes").hide();
	$("#divDropdownPortes").show();
	$("#dropdown_portes").focus();
}

function portesModificados(){
	$("#spanPortes").text($("#dropdown_portes option[value=" + $("#dropdown_portes").val() + "]").text());
	$("#divDropdownPortes").hide();
	$("#spanPortes").show();
}

function modificaNombreCliente(){
	/*$("#spanNombreCliente").hide();
	$("#text_nombreCliente").show();
	$("#text_nombreCliente").focus();*/
}

function nombreClienteModificado(){
	$("#spanNombreCliente").text($("#text_nombreCliente").val());
	$("#spanNombreCliente").show();
	$("#text_nombreCliente").hide();
}

function modificaNifCliente(){
	/*$("#spanNifCliente").hide();
	$("#text_nifCliente").show();
	$("#text_nifCliente").focus();*/
}

function nifClienteModificado(){
	$("#spanNifCliente").text($("#text_nifCliente").val());
	$("#spanNifCliente").show();
	$("#text_nifCliente").hide();
}

function modificaIdCliente(){
	/*$("#spanIdCliente").hide();
	$("#text_idCliente").show();
	$("#text_idCliente").focus();*/
}

function idClienteModificado(){
	$("#spanIdCliente").text($("#text_idCliente").val());
	$("#spanIdCliente").show();
	$("#text_idCliente").hide();
}

function modificaNumeroPedido(){
	$("#spanNPedido").hide();
	$("#text_nPedido").show();
	$("#text_nPedido").focus();
}

function numeroPedidoModificado(){
	$("#spanNPedido").text($("#text_nPedido").val());
	$("#spanNPedido").show();
	$("#text_nPedido").hide();
}

function modificaFormaPago(){
	$("#spanFormaPago").hide();
	$("#divDropdownFormasPago").show();
	$("#dropdown_formasPago").focus();
}

function formaPagoModificada(){
	$("#spanFormaPago").text($("#dropdown_formasPago option[value=" + $("#dropdown_formasPago").val() + "]").text());
	$("#divDropdownFormasPago").hide();
	$("#spanFormaPago").show();
}

function modificaFechaEntrega(){
	$("#spanFechaEntrega").hide();
	$("#date_fechaEntrega").show();
	$("#date_fechaEntrega").focus();
}

function fechaEntregaModificada(){
	$("#spanFechaEntrega").text($("#date_fechaEntrega").val());
	$("#spanFechaEntrega").show();
	$("#date_fechaEntrega").hide();
}

function modificaFechaVencimiento(){
	$("#spanFechaVencimiento").hide();
	$("#date_fechaVencimiento").show();
	$("#date_fechaVencimiento").focus();
}

function fechaVencimientoModificada(){
	$("#spanFechaVencimiento").text($("#date_fechaVencimiento").val());
	$("#spanFechaVencimiento").show();
	$("#date_fechaVencimiento").hide();
}

function actualizarAlbaran(salir){
	if ($("#dropdown_formasPago").val() == 0){
		$.msg('Debe seleccionar la forma de pago');
	}else{
		if ($("#dropdown_direcciones").val() == 0){
			$.msg('Debe seleccionar una direccion del cliente');
		}else{
			if ($("#dropdown_direccionesEntrega").val() == 0){
				$.msg('Debe seleccionar una direccion de entrega');
			}else{
				if ($("#dropdown_temperaturas").val() == 0){
					$.msg('Debe seleccionar la temperatura del transporte');
				}else{
					if ($("#dropdown_portes").val() == 0){
						$.msg('Debe seleccionar la forma en que se pagar&aacute;n los portes');
					}else{
						if ($("#dropdown_transportistas").val() == 0){
							$.msg('Debe seleccionar un transportista');
						}else{
							$.confirm("&#191Actualizar el albaran?",
								function(){
									//En url: direccion entrega, forma pago, nombre cliente, id cliente, nifcliente, tfnoCliente, ivaaplicable
									$url =
										"ActualizarAlbaran.action?codigoAlbaran=" + $("#spanCodigoAlbaran").html().trim() +
											"&descripcionFormaPago=" + $("#spanFormaPago").html().trim() +
											"&nombreCliente=" + $("#spanNombreCliente").html().trim() +
											"&nifCliente=" + $("#spanNifCliente").html().trim() +
											"&numeroTelefono=" + $("#spanTelefono").html().trim() +
											"&ivaAplicable=" + $("#spanIvaAplicable").html().trim() +
											"&idCliente=" + $("#spanIdCliente").html().trim() +
											"&idTelefono=" + $("#dropdown_telefono").val() +
											"&idDireccionCliente=" + $("#dropdown_direcciones").val() +
											"&direccionEntrega=" + $("#dropdown_direccionesEntrega").val() +
											"&idFormaPago=" + $("#dropdown_formasPago").val() +
											"&importeTotal=" + $("#spanImporteTotal").text().trim() +
											"&idTransportista=" + $("#dropdown_transportistas").val() +
											"&idPortesTransporte=" + $("#dropdown_portes").val() +
											"&idTemperaturaTransporte=" + $("#dropdown_temperaturas").val() +
											"&fechaEntrega=" + $("#spanFechaEntrega").text().trim() +
											"&fechaVencimiento=" + $("#spanFechaVencimiento").text().trim() +
											"&codigoOrden=" + $("#spanNPedido").text().trim() +
											"&horarioEntrega=" + $("#spanHorario").text().trim() +
											"&total=" + $("#spanImporteTotal").html().trim();
									observaciones = $("#observaciones").val();
									observaciones = observaciones.replace("&", "");
									$url += "&observaciones=" + observaciones;
									//Ahora las descripciones y los precios de cada linea
									lineas = $(".linea").get();
									//alert(lineas.length);
									for (i = 0; i < lineas.length; i++){
										//alert(lineas[i].id);
										//alert(i);
										id = lineas[i].id;
										//alert(id);
										$url += "&descripcion_" + id + "=" + $("#spanDescripcion_" + id).html().trim() + "&precio_" + id + "=" + $("#spanPrecio_" + id).html().trim();
									}
									//alert($url);
									$.ajax({
										url: $url,
										cache: false,
										async:false,
										success: function(html){
											//$("#widget_consPedido").empty();
											//$("#widget_consPedido").append(html);
											//listaAlbaranes();
											if (salir){
												$("#widget_consPedido").empty();
												$("#widget_consPedido").append(html);
												$.ajax({
													type: "POST",
													url: "registrosalida/consRegiAlba.js",
													dataType: "script"
												});
												$.ajax({
													type: "POST",
													url: "js/script.js",
													dataType: "script"
												});
											}
										}
									});
								},
								function(){
									$.msg("El albaran NO se ha actualizado");
								}
							);
						}
					}
				}
			}
		}
	}
}