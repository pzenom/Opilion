var fondo = false;
var mensaje = false;
var lineaCuota = 1;
var decimales = 100;//100 -> 2 decimales

$(document).ready(function(){
	idFormaPago = $("#idFormaPago").val();
	if (idFormaPago == 0)
		$("#spanFormaPago").text('');
	else
		$("#spanFormaPago").text($("#dropdown_formasPago option[value=" + idFormaPago + "]").text());
	$("#dropdown_formasPago").val(idFormaPago);
	$("#" + $("#dropdown_formasPago").parent().attr("id")).children('span').text(
			$("#dropdown_formasPago option[value=" + idFormaPago + "]").text());
	if ($("#idDestinoFactura").val() == 0)
		$("#spanDireccionFacturacion").text('');
	else
		$("#spanDireccionFacturacion").text($("#dropdown_direccionFacturacion option[value=" + $("#idDestinoFactura").val() + "]").text());
	$("#dropdown_direccionFacturacion").val($("#idDestinoFactura").val());
	$("#" + $("#dropdown_direccionFacturacion").parent().attr("id")).children('span').text(
			$("#dropdown_direccionFacturacion option[value=" + $("#idDestinoFactura").val() + "]").text());
	$("#nuevoIva").hide();
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_insertar").attr("onclick" , "javascript:insertaFactura();");
	$("#bot_insertar").show();
	$("#bot_vuelve").attr("onclick" , "javascript:listaAlbaranes();");
	$("#bot_vuelve").show();
	$("#bot_gestionarCuotas").show();
	//$("#bot_insertarCargos").show();
	$("#bot_editarCargosFactura").show();
	$("#widget_menu").show();
	/**/
	$.msg("Para modificar un campo, haz click sobre el");
});

function cerrar(tipo, borrarCargos){//1: cargos; 2: cuotas
	if (tipo == 1){
		//Cargos
		if (borrarCargos)
			$(".inputCargos").val(0);
		$("#divCargos").hide();
		$("#ocultos").append($("#divCargos"));
	}else{
		//Cuotas
		if (tipo == 2){
		}
	}
	$('#fondo').remove();
	document.getElementsByTagName('body')[0].removeChild(mensaje);
	fondo = false;
	mensaje = false;
}

function insertaFactura(){
	if ($("#dropdown_direccionFacturacion").val() == 0){
		$.msg('Escoja la direcci&oacute;n de facturaci&oacute;n');
	}else{
		if ($("#dropdown_formasPago").val() == 0){
			$.msg('Escoja la forma de pago');
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
					//alert(flag);
					if (flag){
						if ($("#date_fecha").val() != ''){
							if ($("#spanIvaAplicable").text().trim() > 0){
								//if($("#dropdown_direccionFacturacion").val() > 0){
								$url= "InseDetaFact.action?codigoAlbaran=" + $("#codigoAlbaran").val() +
									"&cargostotal=" + $("#spanValorCargos").text().trim() +
									"&valorDescuento=" + $("#spanValorDescuento").text().trim() +
									"&descuento=" + $("#spanDescuento").text().trim() +
									"&subtotal=" + $("#spanSubtotal").text().trim() +
									"&fechavencimiento=" + $("#date_fecha").val() +
									"&total=" + $("#spanImporteTotal").text().trim() +
									"&iva=" + $("#spanValorIva").text().trim() +
									"&cargoTran=" + $("#cargoTran").val() +
									"&ivaCargoTran=" + $("#ivact").val() +
									"&totalCargoTran=" + $("#totalCargoTran").val() +
									"&cargoBanc=" + $("#cargoBanc").val() +
									"&ivaCargoBanc=" + $("#ivacb").val() +
									"&totalCargoBanc=" + $("#totalCargoBanc").val() +
									"&cargoDevo=" + $("#cargoDevo").val() +
									"&ivaCargoDevo=" + $("#ivacd").val() +
									"&totalCargoDevo=" + $("#totalCargoDevo").val() +
									"&ivaAplicable=" + $("#spanIvaAplicable").text().trim() +
									"&idDestino=" + $("#dropdown_direccionFacturacion").val() +
									"&idFormaPago=" + $("#dropdown_formasPago").val() +
									"&nombreCliente=" + $("#spanNombreCliente").text().trim() +
									"&nifCliente=" + $("#spanNifCliente").text().trim() +
									"&telefonoCliente=" + $("#spanTelefono").text().trim() +
									"&descripcionFormaPago=" + $("#spanFormaPago").text().trim() +
									"&observaciones=" + $("#observaciones").val() +
									"&radEdi=N";
								/*generarEdi = $('#radEdi').is(':checked');
								//alert($('#radEdi').is(':checked'));
								if (generarEdi == true){
									$url += "&radEdi=S";
								}else
									$url += "&radEdi=N";*/
								//Ahora las descripciones y los precios de cada linea
								lineas = $(".linea").get();
								//alert(lineas.length);
								for (i = 0; i < lineas.length; i++){
									id = lineas[i].id;
									//alert(id);
									$url +=
										"&descripcion_" + id + "=" + encodeURIComponent($("#spanDescripcion_" + id).text().trim()) +
										"&precio_" + id + "=" + $("#spanPrecio_" + id).text().trim() +
										"&precioTotal_" + id + "=" + $("#spanPrecioLinea_" + id).text().trim() +
										"&peso_" + id + "=" + $("#spanPesoLinea_" + id).text().trim() +
										"&cantidad_" + id + "=" + $("#cantidad_" + id).text().trim() +
										"&idItem_" + id + "=" + id +
										"&idProducto_" + id + "=" + $("#idProducto_" + id).val() +
										"&codigoItem_" + id + "=" + $("#codigoItem_" + id).val();
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
										}
									});
									limpiarMenuSuperior();
									$("#btMenuFacturas").addClass("active");
									$("#btMenuAlbaranes").removeClass("active");
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
								}
							}else
								$.msg('Debe seleccionar el IVA que se aplica');
						}else
							$.msg('Debe introducir la fecha de vencimiento antes de continuar');
					}
				},
				function(){
					$.msg("Cancelada la insercion");
				}
			);
		}
	}
}