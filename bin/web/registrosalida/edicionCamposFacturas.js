function aceptarCargos(){
	//Calcular la suma de los cargos
	sumaCargos =  parseFloat($("#totalCargoTran").val()) + parseFloat($("#totalCargoBanc").val()) + parseFloat($("#totalCargoDevo").val());
	sumaCargos = redondearValor(sumaCargos, decimales);
	$("#spanValorCargos").text(sumaCargos);
	//Total
	calcularImporteTotal();
	//Cerramos
	cerrar(1, false);
}

function modificarValorIva(){
	//alert(1);
	//Calculamos el valor del IVA
	subTotal = parseFloat($("#spanSubtotal").text().trim());
	//alert(subTotal);
	iva = parseFloat($("#spanIvaAplicable").text().trim());
	//alert(iva);
	//alert(5.5 * 7);
	valorIva = iva * subTotal / 100;
	valorIva = redondearValor(valorIva, decimales);
	//alert(valorIva);
	$("#spanValorIva").text(valorIva);
	//alert(valorIva);
	calcularImporteTotal();
}

function calcularImporteTotal(){
	subTotal = subTotalFactura();
	$("#spanSubtotal").text(subTotal);
	importeTotal = parseFloat($("#spanSubtotal").text().trim()) + parseFloat($("#spanValorCargos").text().trim()) +
		parseFloat($("#spanValorIva").text().trim()) - parseFloat($("#spanValorDescuento").text().trim());
	importeTotal = redondearValor(importeTotal, decimales);
	$("#spanImporteTotal").text(importeTotal);
}

function calcularValorDescuento(){
	valorDescuento = parseFloat($("#spanSubtotal").text().trim()) * parseFloat($("#spanDescuento").text().trim()) / 100;
	valorDescuento = redondearValor(valorDescuento, decimales);
	$("#spanValorDescuento").text(valorDescuento);
}

function editarCargosFactura(){
	fondo = document.createElement('div');
	mensaje = document.createElement('div');
	fondo.setAttribute('id','fondo');
	mensaje.setAttribute('id','msg');
	$('body').append(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
	$("#fondo").height($(document).height());
	mensaje.innerHTML =
		'<div class="superior">Editar cargos<span class="cerrar" title="Cerrar" onclick="aceptarCargos();">X</span></div>' +
		'<p style="font-size: 15px; text-align: center;"></p>';
	$("#msg").append($("#divCargos"));
	$("#divCargos").show();
	$("#cargoTran").focus();
}

function calcularCargos(){
	//1. Transporte
	$("#totalCargoTran").val(parseFloat($("#cargoTran").val()) + (parseFloat($("#ivact").val()) * parseFloat($("#cargoTran").val()) / 100));
	//2. Banco
	$("#totalCargoBanc").val(parseFloat($("#cargoBanc").val()) + (parseFloat($("#ivacb").val()) * parseFloat($("#cargoBanc").val()) / 100));
	//3. Devolucion
	$("#totalCargoDevo").val(parseFloat($("#cargoDevo").val()) + (parseFloat($("#ivacd").val()) * parseFloat($("#cargoDevo").val()) / 100));
}

function modificaFechaVencimiento(){
	$("#spanFechaVencimiento").hide();
	$("#date_fecha").show();
	$("#date_fecha").focus();
}

function fechaVencimientoModificada(){
	$("#spanFechaVencimiento").text($("#date_fecha").val());
	$("#spanFechaVencimiento").show();
	$("#date_fecha").hide();
}

function celdaDescripcion(numLinea){
	//if ($("#spanDescripcion_" + numLinea).is(" :visible") == true){
		//Se está mostrando el span, asi que no es posible modificar. Habilitamos el input para modificar
		$("#spanDescripcion_" + numLinea).hide();
		$("#textDescripcion_" + numLinea).show();
		$("#textDescripcion_" + numLinea).focus();
	//}
}

function descripcionModificada(numLinea){
	$("#spanDescripcion_" + numLinea).text($("#textDescripcion_" + numLinea).val());
	$("#spanDescripcion_" + numLinea).show();
	$("#textDescripcion_" + numLinea).hide();
}

function descuento(){
	//if ($("#spanDescuento").is(" :visible") == true){
		//Se está mostrando el span, asi que no es posible modificar. Habilitamos el input para modificar
		$("#spanDescuento").hide();
		$("#textDescuento").show();
		$("#textDescuento").focus();
	//}
}

function descuentoModificado(){
	ajustarDecimal('textDescuento');
	descuento = redondearValor($("#textDescuento").val(), decimales);
	$("#spanDescuento").text(descuento);
	$("#spanDescuento").show();
	$("#textDescuento").hide();
	calcularValorDescuento();
	calcularImporteTotal();
}

function celdaPrecio(numLinea){
	//if ($("#spanPrecio_" + numLinea).is(" :visible") == true){
		//Se está mostrando el span, asi que no es posible modificar. Habilitamos el input para modificar
		$("#spanPrecio_" + numLinea).hide();
		$("#textPrecio_" + numLinea).show();
		$("#textPrecio_" + numLinea).focus();
	//}
}

function precioModificado(numLinea){
	ajustarDecimal('textPrecio_' + numLinea);
	$("#spanPrecio_" + numLinea).text(redondearValor(parseFloat($("#textPrecio_" + numLinea).val()), decimales));
	//Calcular el nuevo precio de la linea
	pesoLinea = $("#spanPesoLinea_" + numLinea).html().trim();
	precioKilo = $("#spanPrecio_" + numLinea).html().trim();
	precioLinea = parseFloat(pesoLinea) * parseFloat(precioKilo);
	precioLinea = redondearValor(precioLinea, decimales);
	$("#spanPrecioLinea_" + numLinea).html(precioLinea);
	subTotal = subTotalFactura();
	$("#spanPrecio_" + numLinea).show();
	$("#textPrecio_" + numLinea).hide();
	//modificamos el subtotal, y calculamos el total otra vez
	$("#spanSubtotal").html(subTotal);
	calcularImporteTotal();
}

function subTotalFactura(){
	totales = $(".totalesLinea").get();
	subTotal = 0;
	for (i = 0; i < totales.length; i++){
		id = totales[i].id;
		total = $("#" + id).html().trim();
		subTotal += parseFloat(total);
	}
	subTotal = redondearValor(subTotal, decimales);
	return subTotal;
}

function modificarIva(){
	$("#spanIvaAplicable").hide();
	$("#nuevoIva").show();
	$("#nuevoIva").focus();
}

function ivaModificado(){
	ajustarDecimal('nuevoIva');
	$("#spanIvaAplicable").text($("#nuevoIva").val());
	$("#spanIvaAplicable").show();
	$("#nuevoIva").hide();
	modificarValorIva();
}

function modificaTelefono(){
	$("#spanTelefono").hide();
	$("#text_telefono").show();
	$("#text_telefono").focus();
}

function telefonoModificado(){
	$("#spanTelefono").text($("#text_telefono").val());
	$("#spanTelefono").show();
	$("#text_telefono").hide();
}

function modificaNombreCliente(){
	/*$("#spanNombreCliente").hide();
	$("#text_nombreCliente").show();
	$("#text_nombreCliente").focus();*/
}

function nombreClienteModificado(){
	/*$("#spanNombreCliente").text($("#text_nombreCliente").val());
	$("#spanNombreCliente").show();
	$("#text_nombreCliente").hide();*/
}

function modificaNifCliente(){
	/*$("#spanNifCliente").hide();
	$("#text_nifCliente").show();
	$("#text_nifCliente").focus();*/
}

function nifClienteModificado(){
	/*$("#spanNifCliente").text($("#text_nifCliente").val());
	$("#spanNifCliente").show();
	$("#text_nifCliente").hide();*/
}

function modificaFormaPago(){
	/*$("#spanFormaPago").hide();
	$("#text_formaPago").show();
	$("#text_formaPago").focus();*/
	$("#spanFormaPago").hide();
	$("#divDropdownFormasPago").show();
	$("#dropdown_formasPago").focus();
}

function formaPagoModificada(){
	/*$("#spanFormaPago").text($("#text_formaPago").val());
	$("#spanFormaPago").show();
	$("#text_formaPago").hide();*/
	$("#spanFormaPago").text($("#dropdown_formasPago option[value=" + $("#dropdown_formasPago").val() + "]").text());
	$("#spanFormaPago").show();
	$("#divDropdownFormasPago").hide();
}

function modificaDireccionEntrega(){
	$("#spanDireccionEntrega").hide();
	$("#text_direccionEntrega").show();
	$("#text_direccionEntrega").focus();
}

function direccionEntregaModificada(){
	$("#spanDireccionEntrega").text($("#text_direccionEntrega").val());
	$("#spanDireccionEntrega").show();
	$("#text_direccionEntrega").hide();
}

function modificaDireccionFacturacion(){
	$("#spanDireccionFacturacion").hide();
	$("#divDropdownDireccionFacturacion").show();
	$("#dropdown_direccionFacturacion").focus();
}

function direccionFacturacionModificada(){
	$("#spanDireccionFacturacion").text($("#dropdown_direccionFacturacion option[value=" + $("#dropdown_direccionFacturacion").val() + "]").text());
	$("#spanDireccionFacturacion").show();
	$("#divDropdownDireccionFacturacion").hide();
}