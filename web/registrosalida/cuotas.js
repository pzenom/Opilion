var fondo = false;
var mensaje = false;
var lineaCuota = 0;
var decimales = 100;//100 -> 2 decimales

$(document).ready(function(){
	lineaCuota = $("#tbodyCuotas").children().length;
	//alert(lineaCuota);
	//Si hay cuotas, las mostramos
	if ($("#tbodyCuotas").children().length > 1){
		$('#contenedorCuotas').show();
		cuotaModificada();
	}else{
		$('#contenedorCuotas').hide();
	}
	$("#lineaCuota_" + (parseFloat(lineaCuota) - 1)).addClass("ultimaCuota");
});

function addCuota(){
	//Si las sumas que hay ya cubren el importe total, no permitir añadir mas cuotas
	flag = true;
	if (parseFloat(calcularSumaPorcentajes()) == 100){
		$.msg('La suma de porcentajes de las cuotas hace ya el 100%, no es posible registrar mas cuotas.');
		flag = false;
	}else{
		if (parseFloat(calcularSumaImportesCuotas()) == parseFloat($('#spanImporteTotal').text().trim())){
			$.msg('La suma de los importes de las cuotas es ya igual al importe total de la factura, no es posible registrar mas cuotas.');
			flag = false;
		}
	}
	if (flag){
		addCuotaManual();
		/*cuantasHay = $("#tbodyCuotas").children().length;
		//alert(cuantasHay);
		fondo = document.createElement('div');
		mensaje = document.createElement('div');
		fondo.setAttribute('id','fondo');
		mensaje.setAttribute('id','msg');
		$('body').append(fondo);
		document.getElementsByTagName('body')[0].appendChild(mensaje);
		$("#fondo").height($(document).height());
		mensaje.innerHTML =
			'<div class="superior">Nueva cuota<span class="cerrar" title="Cerrar" onclick="cerrar(2, false);">X</span></div>' +
			'<p style="font-size: 15px; text-align: center; margin-top: 25px;">' +
				'<button id="bot_nuevaCuotaManual" class="i_scan_label icon verdeOpilion" onclick="javascript:addCuotaManual();">Cuota manual</button>' +
				'<button id="bot_cuotasProporcionales" class="i_scan_label icon verdeOpilion" onclick="javascript:cuotasProporcionales();">Cuotas proporcionales</button>' +
			'</p>';*/
	}
}

function eliminaCuota(numCuota){
	$("#lineaCuota_" + numCuota).unbind();
	$("#lineaCuota_" + numCuota).remove();
	//alert($("#tbodyCuotas").children().length);
	for (i = parseFloat(parseFloat(numCuota) + 1); i <= parseFloat($("#tbodyCuotas").children().length); i++){
		//alert(i);
		$('#lineaCuota_' + parseFloat(i)).attr("id", "lineaCuota_" + parseFloat(i - 1));
		//alert("lineaCuota_" + parseFloat(i));
		$('#indiceCuota_' + (i)).attr("id", "indiceCuota_" + parseFloat(i - 1));
		$('#indiceCuota_' + (i - 1)).text(i - 1);
		
		$('#celdaFechaCuota_' + (i)).attr("id", "celdaFechaCuota_" + parseFloat(i - 1));
		$('#celdaFechaCuota_' + (i - 1)).attr("onclick", "javascript:celdaFechaCuota(" + parseFloat(i - 1) + ");");
		$('#spanFechaCuota_' + (i)).attr("id", "spanFechaCuota_" + parseFloat(i - 1));
		$('#textFechaCuota_' + (i)).attr("id", "textFechaCuota_" + parseFloat(i - 1));
		$('#textFechaCuota_' + (i - 1)).attr("key", "textFechaCuota_" + parseFloat(i - 1));
		$('#textFechaCuota_' + (i - 1)).attr("onchange", "javascript:fechaCuotaModificada(" + parseFloat(i - 1) + ");");
		$('#textFechaCuota_' + (i - 1)).attr("onblur", "javascript:fechaCuotaModificada(" + parseFloat(i - 1) + ");");
		$('#textFechaCuota_' + (i - 1)).wl_Date();
		
		$('#tdPorcentajeCuota_' + (i)).attr("id", "tdPorcentajeCuota_" + parseFloat(i - 1));
		$('#tdPorcentajeCuota_' + (i - 1)).attr("onclick", "javascript:celdaPorcentajeCuota(" + parseFloat(i - 1) + ");");
		$('#spanPorcentajeCuota_' + (i)).attr("id", "spanPorcentajeCuota_" + parseFloat(i - 1));
		$('#textPorcentajeCuota_' + (i)).attr("id", "textPorcentajeCuota_" + parseFloat(i - 1));
		$('#textPorcentajeCuota_' + (i - 1)).attr("onblur", "javascript:porcentajeCuotaModificado(" + parseFloat(i - 1) + ");");
		$('#textPorcentajeCuota_' + (i - 1)).attr("onkeypress",
			"javascript:validarNumerosDecimales('textPorcentajeCuota_" + parseFloat(i - 1) + "', event);");
			
		$('#tdImporteCuota_' + (i)).attr("id", "tdImporteCuota_" + parseFloat(i - 1));
		$('#tdImporteCuota_' + (i - 1)).attr("onclick", "javascript:celdaImporteCuota(" + parseFloat(i - 1) + ");");
		$('#spanImporteCuota_' + (i)).attr("id", "spanImporteCuota_" + parseFloat(i - 1));
		$('#textImporteCuota_' + (i)).attr("id", "textImporteCuota_" + parseFloat(i - 1));
		$('#textImporteCuota_' + (i - 1)).attr("onblur", "javascript:importeCuotaModificado(" + parseFloat(i - 1) + ");");
		$('#textImporteCuota_' + (i - 1)).attr("onkeypress",
			"javascript:validarNumerosDecimales('textImporteCuota_" + parseFloat(i - 1) + "', event);");
			
		$('#tdObservacionesCuota_' + (i)).attr("id", "tdObservacionesCuota_" + parseFloat(i - 1));
		$('#tdObservacionesCuota_' + (i - 1)).attr("onclick", "javascript:celdaObservacionesCuota(" + parseFloat(i - 1) + ");");
		$('#spanObservacionesCuota_' + (i)).attr("id", "spanObservacionesCuota_" + parseFloat(i - 1));
		$('#textObservacionesCuota_' + (i)).attr("id", "textObservacionesCuota_" + parseFloat(i - 1));
		$('#textObservacionesCuota_' + (i - 1)).attr("onblur", "javascript:observacionesModificadasCuota(" + parseFloat(i - 1) + ");");
		
		$('#elimina_' + (i)).attr("id", "elimina_" + parseFloat(i - 1));
		$('#elimina_' + (i - 1)).attr("onclick", "javascript:eliminaCuota(" + parseFloat(i - 1) + ");");
	}
	cuotaModificada();
	lineaCuota--;
	if (lineaCuota == 1){
		$('#contenedorCuotas').hide();
	}
	//alert(lineaCuota);
	$("#lineaCuota_" + (parseFloat(lineaCuota) - 1)).addClass('ultimaCuota');
}

function addCuotaManual(){
	//Calculamos el porcentaje restante
	porcentajeTotal = calcularSumaPorcentajes();
	porcentajeActual = 100 - parseFloat(porcentajeTotal);
	porcentajeTotal = redondearValor(porcentajeTotal, decimales);
	importeTotal = calcularSumaImportesCuotas();
	importeActual = parseFloat($("#spanImporteTotal").text().trim()) - parseFloat(importeTotal);
	importeActual = redondearValor(importeActual, decimales);
	//La suma de los porcentajes y del importe de las cuotas será el 100% y el importe total de la factura respectivamente
	$('#totalPorcentajeCuotas').text("100");
	$('#totalImporteCuotas').text($('#spanImporteTotal').text().trim());
	var fila = document.createElement('tr');
	fila.setAttribute("style", "height: 45px;");
	fila.setAttribute("id", "lineaCuota_" + lineaCuota);
	fila.setAttribute("class", "lineaCuota");
	fila.innerHTML =
		'<td style="vertical-align: middle;"><span id="indiceCuota_' + lineaCuota + '">' + lineaCuota + '</span></td>' +
		'<td id="celdaFechaCuota_' + lineaCuota + '" onclick="javascript:celdaFechaCuota(' + lineaCuota + ');" style="vertical-align: middle; background-color: #EFF8FB;">' +
			'<span id="spanFechaCuota_' + lineaCuota + '"></span>' +
		'</td>' +
		'<td id="tdPorcentajeCuota_' + lineaCuota + '" onclick="javascript:celdaPorcentajeCuota(' + lineaCuota + ');" style="vertical-align: middle; background-color: #EFF8FB;">' +
			'<span id="spanPorcentajeCuota_' + lineaCuota + '" class="porcentajesCuotas">' + porcentajeActual + '</span>' +
			'<input id="textPorcentajeCuota_' + lineaCuota + '" style="display: none; vertical-align: middle; width: 92%;" onblur="javascript:porcentajeCuotaModificado(' + lineaCuota + ');" onkeypress="return validarNumerosDecimales(' + "'textPorcentajeCuota_" + lineaCuota + "'" + ',event);" value="' + porcentajeActual + '"/>' +
		'</td>' +
		'<td id="tdImporteCuota_' + lineaCuota + '" onclick="javascript:celdaImporteCuota(' + lineaCuota + ');" style="vertical-align: middle; background-color: #EFF8FB;">' +
			'<span id="spanImporteCuota_' + lineaCuota + '" class="importesCuotas">' + importeActual + '</span>' +
			'<input id="textImporteCuota_' + lineaCuota + '" style="display: none; vertical-align: middle; width: 92%;" onblur="javascript:importeCuotaModificado(' + lineaCuota + ');" onkeypress="return validarNumerosDecimales(' + "'textImporteCuota_" + lineaCuota + "'" + ',event);" value="' + importeActual + '" />' +
		'</td>' +
		'<td id="tdObservacionesCuota_' + lineaCuota + '" onclick="javascript:celdaObservacionesCuota(' + lineaCuota + ');" style="vertical-align: middle; background-color: #EFF8FB; max-width: 400px;">' +
			'<span id="spanObservacionesCuota_' + lineaCuota + '" class="unidadesLinea"></span>' +
			'<input id="textObservacionesCuota_' + lineaCuota + '" style="display: none; vertical-align: middle; width: 92%;" onblur="javascript:observacionesModificadasCuota(' + lineaCuota + ');" />' +
		'</td>' +
		'<td style="vertical-align: middle; width: 15px;">' +
			'<a id="elimina_' + lineaCuota + '" title="Eliminar esta cuota" href="javascript:eliminaCuota(' + lineaCuota + ')">' +
				'<img title="Eliminar esta cuota" alt="Eliminar esta cuota" src="img/cancel.png">' +
			'</a>' +
		'</td>';
	//$("#tbodyCuotas").append(fila);
	cuantasCuotas = $("#tbodyCuotas").children().length - 1;
	if (cuantasCuotas == 0){
		$("#tbodyCuotas").prepend(fila);
	}else{
		$(fila).insertAfter(".ultimaCuota");
		$(".ultimaCuota").removeClass('ultimaCuota');
	}
	$("#lineaCuota_" + lineaCuota).addClass('ultimaCuota');
	clon = $("#fechaGenerico").clone();
	clon.attr("id", "textFechaCuota_" + lineaCuota);
	clon.attr("key", "textFechaCuota_" + lineaCuota);
	clon.attr("class", "date");
	clon.attr("data-format", "dd-mm-yyyy");
	clon.attr("onchange", "javascript:fechaCuotaModificada('" + lineaCuota + "');");
	clon.attr("onblur", "javascript:fechaCuotaNoModificada('" + lineaCuota + "');");
	//clon.css("display: none");
	clon.appendTo("#celdaFechaCuota_" + lineaCuota);
	$("#textFechaCuota_" + lineaCuota).wl_Date();
	//$("#fechaGenerico1").wl_Date();
	lineaCuota++;
	//cerrar(2, false);
	$('#contenedorCuotas').show();
}

function cuotasProporcionales(){
	cerrar(2, false);
	fondo = document.createElement('div');
	mensaje = document.createElement('div');
	fondo.setAttribute('id','fondo');
	mensaje.setAttribute('id','msg');
	$('body').append(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
		$("#fondo").height($(document).height());
	mensaje.innerHTML =
		'<div class="superior">Cuotas proporcionales<span class="cerrar" title="Cerrar" onclick="cerrar(2, false);">X</span></div>' +
		'<p style="font-size: 15px; text-align: center; margin-top: 25px;">' +
		'Dividir el importe total en <input id="inputNumeroCuotas" style="width: 40px;" onkeypress="return validarSoloNumeros(event);" /> cuotas de <input id="inputEurosCuotas" style="width: 100px;" onkeypress="return validarNumerosDecimales(' +
			"'" + "inputEurosCuotas" + "'" +
			', event);" /> Euros' +
		'</p>' +
		'<button id="bot_aceptarCuotasProporcionales" class="i_scan_label icon verdeOpilion" onclick="javascript:aceptarCuotasProporcionales();">Aceptar</button>';
	$("#inputNumeroCuotas").focus();
}

function aceptarCuotasProporcionales(){

}

function celdaFechaCuota(numCuota){
	$("#spanFechaCuota_" + numCuota).hide();
	$("#textFechaCuota_" + numCuota).show();
	$("#textFechaCuota_" + numCuota).focus();
}

function fechaCuotaModificada(numCuota){
	//alert(numCuota);
	//alert($("#spanFechaCuota_" + numCuota).text());
	//alert($("#textFechaCuota_" + numCuota).val());
	$("#spanFechaCuota_" + numCuota).text($("#textFechaCuota_" + numCuota).val());
	$("#spanFechaCuota_" + numCuota).show();
	$("#textFechaCuota_" + numCuota).hide();
}

function fechaCuotaNoModificada(numCuota){
	$("#spanFechaCuota_" + numCuota).show();
	$("#textFechaCuota_" + numCuota).hide();
}

function celdaPorcentajeCuota(numCuota){
	$("#spanPorcentajeCuota_" + numCuota).hide();
	$("#textPorcentajeCuota_" + numCuota).show();
	$("#textPorcentajeCuota_" + numCuota).focus();
}

function porcentajeCuotaModificado(numCuota){
	ajustarDecimal('textPorcentajeCuota_' + numCuota);
	valor = $("#textPorcentajeCuota_" + numCuota).val();
	valor = redondearValor(valor, decimales);
	if (parseFloat(valor) > 100){
		$.msg('El porcentaje de una cuota nunca puede ser mayor del 100%');
		$("#spanPorcentajeCuota_" + numCuota).text(0);
		$("#textPorcentajeCuota_" + numCuota).val(0);
		$("#textPorcentajeCuota_" + numCuota).val(maximoPorcentaje());
		porcentajeCuotaModificado(numCuota);
	}else{
		$("#spanPorcentajeCuota_" + numCuota).text($("#textPorcentajeCuota_" + numCuota).val());
		$("#spanPorcentajeCuota_" + numCuota).show();
		$("#textPorcentajeCuota_" + numCuota).hide();
		nuevoImporte = parseFloat(valor) * parseFloat($("#spanImporteTotal").text().trim()) / 100;
		nuevoImporte = redondearValor(nuevoImporte, decimales);
		$("#spanImporteCuota_" + numCuota).text(nuevoImporte);
		$("#textImporteCuota_" + numCuota).val(nuevoImporte);
		cuotaModificada();
	}
}

function cuotaModificada(){
	//alert(222);
	$('#totalPorcentajeCuotas').text(calcularSumaPorcentajes());
	$('#totalImporteCuotas').text(calcularSumaImportesCuotas());
	//alert(223)
}

function celdaImporteCuota(numCuota){
	$("#spanImporteCuota_" + numCuota).hide();
	$("#textImporteCuota_" + numCuota).show();
	$("#textImporteCuota_" + numCuota).focus();
}

function importeCuotaModificado(numCuota){
	ajustarDecimal('textImporteCuota_' + numCuota);
	valor = $("#textImporteCuota_" + numCuota).val();
	valor = redondearValor(valor, decimales);
	if (parseFloat(valor) > parseFloat($("#spanImporteTotal").text().trim())){
		$.msg('El importe de una cuota no puede nunca sobrepasar el importe total de la factura');
		$("#spanImporteCuota_" + numCuota).text(0);
		$("#textImporteCuota_" + numCuota).val(0);
		$("#textImporteCuota_" + numCuota).val(maximoImporte());
		importeCuotaModificado(numCuota);
	}else{
		$("#spanImporteCuota_" + numCuota).text($("#textImporteCuota_" + numCuota).val());
		$("#spanImporteCuota_" + numCuota).show();
		$("#textImporteCuota_" + numCuota).hide();
		nuevoPorcentaje = (parseFloat(valor) * 100) / parseFloat($("#spanImporteTotal").text().trim());
		nuevoPorcentaje = redondearValor(nuevoPorcentaje, decimales);
		$("#spanPorcentajeCuota_" + numCuota).text(nuevoPorcentaje);
		$("#textPorcentajeCuota_" + numCuota).val(nuevoPorcentaje);
		cuotaModificada();
	}
}

function celdaObservacionesCuota(numCuota){
	$("#spanObservacionesCuota_" + numCuota).hide();
	$("#textObservacionesCuota_" + numCuota).show();
	$("#textObservacionesCuota_" + numCuota).focus();
}

function observacionesModificadasCuota(numCuota){
	$("#spanObservacionesCuota_" + numCuota).text($("#textObservacionesCuota_" + numCuota).val());
	$("#spanObservacionesCuota_" + numCuota).show();
	$("#textObservacionesCuota_" + numCuota).hide();
}

function maximoPorcentaje(){
	total = calcularSumaPorcentajes();
	//alert(total);
	return 100 - parseFloat(total);
}

function calcularSumaPorcentajes(){
	porcentajeTotal = 0;
	porcentajes = $(".porcentajesCuotas").get();
	//alert(porcentajes.length);
	for (i = 0; i < porcentajes.length; i++){
		id = porcentajes[i].id;
		//alert(id);
		//alert(id);
		//alert(parseFloat($('#' + id).text().trim()));
		porcentajeTotal += parseFloat($('#' + id).text().trim());
		//alert(porcentajeTotal);
	}
	//alert("total: " + porcentajeTotal);
	porcentajeTotal = redondearValor(porcentajeTotal, decimales);
	//alert("total redondeado: " + porcentajeTotal);
	return porcentajeTotal;
}

function maximoImporte(){
	total = calcularSumaImportesCuotas();
	//alert(total);
	return parseFloat($("#spanImporteTotal").text().trim()) - parseFloat(total);
}

function calcularSumaImportesCuotas(){
	importeTotal = 0;
	importes = $(".importesCuotas").get();
	for (i = 0; i < importes.length; i++){
		id = importes[i].id;
		importeTotal += parseFloat($('#' + id).text().trim());
	}
	importeTotal = redondearValor(importeTotal, decimales);
	return importeTotal;
}