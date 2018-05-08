// obtenemos el peso total
var totalPeso = document.getElementById("totalPeso").innerHTML;
totalPeso = totalPeso/document.getElementById("totalBultos").innerHTML;
totalPeso = soloDosDecimales(totalPeso);
var punto = false;
var tipo = 'X';

$(document).ready(function() {
	tipo = $("#tipo").val();
	if (tipo == 'E'){
		$("#th_kilosUnidades1").text("Unidades");
		$("#spanAutoajuste").text("Autoajuste de unidades");
		$("#textAutoajuste").text("Realiza el ajuste automatico de unidades para todos los palets");
		$("#autoajusteE").show();
		$("#spanDistribucion").text("Distribuci&oacute;n de bultos y unidades por pallets");
		$("#thPesoUdsPalet").text("Unidades por palet");
		$(".celdaPBruto").css("visibility", "hidden");
	}else{
		if (tipo == 'M'){
			$("#th_kilosUnidades1").text("Kgs");
			$("#spanAutoajuste").text("Autoajuste de kilogramos");
			$("#textAutoajuste").text("Realiza el ajuste automatico de kgs. para todos los palets");
			$("#autoajusteM").show();
		}
	}
	cuantos = $(".ubicaciones").length;
	for (i = 1; i <= cuantos; i++){
		//$.msg("hi");
		creaUrl(i, true, false);
	}
	numBultos = $(".numBultos");
	for (i = 0; i < numBultos.length; i++){
		id = numBultos.get(i).id;
		//$.msg("Id: " + id);
		//$.msg("Valor: " + $("#" + id).val());
		if ($("#" + id).val() != 0){
			$("#" + id).attr("style", "text-align:right; width: 50px; background-color: #F5F6CE;");
			//$.msg("i: " + i);
			//$.msg("#ubicar" + parseFloat(parseFloat(i) + 1));
			if (tipo != 'P')
				$("#ubicar" + parseFloat(parseFloat(i) + 1)).show();
			$("#enlace" + parseFloat(parseFloat(i) + 1)).show();
			//$.msg("mostrado");
		}
	}
	brutos = $(".brutos");
	for (i = 0; i < brutos.length; i++){
		id = brutos.get(i).id;
		//$.msg($("#" + id).val());
		if ($("#" + id).val() != $("#pesoBruto").val()){
			$("#" + id).attr("style", "text-align:right; width: 50px; background-color: #F5F6CE;");
			if (tipo != 'P')
				$("#ubicar" + parseFloat(parseFloat(i) + 1)).show();
			$("#enlace" + parseFloat(parseFloat(i) + 1)).show();
		}
	}
	netos = $(".netos");
	for (i = 0; i < netos.length; i++){
		id = netos.get(i).id;
		//$.msg($("#" + id).val());
		if ($("#" + id).val() != $("#pesoNeto").val()){
			$("#" + id).attr("style", "text-align:right; width: 50px; background-color: #F5F6CE;");
			if (tipo != 'P')
				$("#ubicar" + parseFloat(parseFloat(i) + 1)).show();
			$("#enlace" + parseFloat(parseFloat(i) + 1)).show();
		}
	}
	netos = $(".netos");
	for (i = 0; i < netos.length; i++){
		id = netos.get(i).id;
		//$.msg($("#" + id).val());
		if ($("#" + id).val() != $("#uds").val()){
			$("#" + id).attr("style", "text-align:right; width: 50px; background-color: #F5F6CE;");
			if (tipo != 'P')
				$("#ubicar" + parseFloat(parseFloat(i) + 1)).show();
			$("#enlace" + parseFloat(parseFloat(i) + 1)).show();
		}
	}
	ubicaciones = $(".ubicacion");
	cuantos = ubicaciones.length;
	//$.msg(cuantos);
	for (i = 0; i < cuantos; i++){
		//$.msg("hi");
		id = ubicaciones.get(i).id;
		//$.msg(id);
		//$.msg($("#" + id).val());
		if ($("#" + id).val() == "")
			$("#" + id).val("Ya no se encuentra en stock");
		//$("#" + id).val()
	}
	filas = $(".filaPalet");
	for (i = 0; i < filas.length; i++){
		//if ($("#enlace" + (parseFloat(i) + 1)).is (':visible'))
		//	$("#ubicar" + (parseFloat(i) + 1)).hide();
		if ($("#ubicacion" + (parseFloat(i) + 1)).val() != 'Entrada'){
			$("#numBulto" + (parseFloat(i) + 1)).attr("disabled", "disabled");
			$("#pBruto" + (parseFloat(i) + 1)).attr("disabled", "disabled");
			$("#pNeto" + (parseFloat(i) + 1)).attr("disabled", "disabled");
			//$.msg("oculta");
			if (tipo != 'P')
				$("#ubicar" + parseFloat(parseFloat(i) + 1)).show();
			$("#ubicar" + (parseFloat(i) + 1)).hide();
			$("#enlace" + (parseFloat(i) + 1)).hide();
		}
	}
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_salvarBultos").show();
	$("#bot_vuelve").attr("onclick", "javascript:volverOrden();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	$("#bot_todasEtiquetas").show();
	if (tipo == 'P'){
		$("#bot_salvarBultos").hide();
		$("#th_kilosUnidades1").text("Unidades");
		$(".noProductosFinales").hide();
		$(".thPalet").text("Unidad");
		/*$("#spanAutoajuste").text("Autoajuste de unidades");
		$("#textAutoajuste").text("Realiza el ajuste automatico de unidades para todos los palets");
		$("#autoajusteE").show();
		$("#spanDistribucion").text("Distribuci&oacute;n de bultos y unidades por pallets");
		$("#thPesoUdsPalet").text("Unidades por palet");
		$(".celdaPBruto").css("visibility", "hidden");*/
	}
});

function imprimirTodasEtiquetas(){
	parent.get_ventana_emergente('ToRE','EtiquetaTodosBultosRE.action?codigoEntrada=' + $("#codigo").val(),'no','no','590','420','','');
}

function creaUrl(i, ubicar, reintenta) {
	$("#guarda" + i).hide();
	iViejo = i;
	$('#enlace' + i).show();
	//$.msg(ubicar);
	//$.msg(i);
	//$('#ubicar' + i).show();
	if (ubicar == "false" || ubicar == false){
		//$.msg("hirr");
		document.getElementById("guarda" + i).src="img/disk.png";
		document.getElementById("guarda" + i).title="Guardar Cambios";
		$("#guarda" + i).show();
		$("#ubicar" + i).hide();
		$('#etiqueta' + i).hide();
	}else{
		/*$("#guarda"+i).hide();
		$("#ubicar"+i).show();
		$('#etiqueta' + i).show();*/
	}
	//$.msg("Estamos modificando "+i);
	var pNeto = document.getElementById("pNeto"+i).value;
	var pBruto = document.getElementById("pBruto"+i).value;
	//$.msg("pNeto ("+pNeto+") >pBruto ("+pBruto+")");
	if (pNeto > pBruto) {
		$.msg("Algo no va bien, el peso Neto (sin embalaje), no puede ser mayor que el peso Bruto (con embalaje). Autoajustando");
		document.getElementById("enlace"+i).setAttribute("href", "#");
		document.getElementById("etiqueta"+i).setAttribute("href", "#");
		document.getElementById("pNeto"+i).value=document.getElementById("pBruto"+i).value;
		pNeto=pBruto;
		//$('#enlace'+i).hide();
		ubicaciones = $(".filaUbicacion").get();
		//$.msg(ubicaciones.length);
		for (i = 1; i < ubicaciones.length; i++){
			$("#" + ubicaciones[i].id).slideDown();
		}
	}//else {
	i = iViejo;
	var numBulto = document.getElementById("numBulto" + i).value;
	var numBultoEnPalet = document.getElementById("numBulto" + i).value;
	//$.msg(numBultoEnPalet);
	var entrada = document.getElementById("codigoEntrada").innerHTML;
	var update = "javascript:updateBultoRE('" + entrada + "','" + i + "','" + pBruto + "','" + pNeto + "','" + numBultoEnPalet + "');";
	var etiqueta = "javascript:parent.get_ventana_emergente('BULTO_RE',''EtiquetaBultoRE.action?idEntrada='+entrada+'&numBulto='+i+'&pBruto='+pBruto+'&pNeto='+pNeto+'&numBultosPalet='+numBultoEnPalet','no','no','590','420','','');";
	//var etiqueta = 'EtiquetaBultoRE.action?idEntrada='+entrada+'&numBulto='+i+'&pBruto='+pBruto+'&pNeto='+pNeto+'&numBultosPalet='+numBultoEnPalet;
	document.getElementById("enlace" + i).setAttribute("href", update);
	document.getElementById("etiqueta" + i).setAttribute("href", etiqueta);
	//var ubica = "SalvaDatosSacarUbicacion.action?gestionBultos=true&mover=true&sacarRegistro_" + $("#codigoEntrada").text() + "__" +
	//	$("#idUbicacion" + i).val() + "__" + i + "=" + $("#pNeto" + i).val();
	document.getElementById("ubicar" + i).setAttribute("onClick", "javascript:ubicarII('" + i + "');");
	//$.msg(reintenta);
	if (reintenta == "true" || reintenta == true){
		//$.msg("entra al if");
		$("#guarda" + i).show();
		/*$("#ubicar" + i).show();
		$('#etiqueta' + i).show();*/
		//$.msg("sale del if");
	}
}

function updateBultoRE(entrada,i,pBruto,pNeto,numBultoEnPalet){
	//$.msg("hola " + i);
	//Comprobar que la suma de los bultos y pesos de todos los palets no sobrepasan el maximo
	bultos = $(".numBultos");
	suma = 0, sumaSinUltimo = 0;
	flag = true;
	totalBultos = $("#totalBultos").text();
	//Calculamos la suma de bultos sin el actual
	sumaI = 0;
	for (j = 0; j < bultos.length; j++){
		if ((parseFloat(j) + 1) != i)
			sumaI += parseFloat($("#" + bultos[j].id).val());
	}
	for (j = 0; j < bultos.length; j++){
		suma += parseFloat($("#" + bultos[j].id).val());
		if (suma > totalBultos){
			$.msg("El numero de bultos en todos los palets no puede superar el total de bultos del registro");
			flag = false;
			//$.msg(suma);
			//$.msg(sumaSinUltimo);
			//$.msg(totalBultos);
			//$.msg("valdria: " + (totalBultos - sumaSinUltimo));
			$("#numBulto" + i).val(parseFloat(totalBultos) - sumaI);
				//(parseFloat(suma) - parseFloat($("#" + bultos[j].id).val())));
			creaUrl(i, false, true);
			break;
		}
		sumaSinUltimo += parseFloat($("#" + bultos[j].id).val());
	}
	if (flag)
		$.confirm("&#191Son correctos los datos para el palet numero" + i + "? Numero de bultos: " + numBultoEnPalet +
			"; Peso bruto: " + pBruto + "; Peso neto: " + pNeto,
			function(){
				var $url = 'UpdateBultoRE.action?unico=true&idEntrada='+entrada+'&numBulto='+i+'&pBruto='+pBruto+'&pNeto='+pNeto+'&numBultosPalet='+numBultoEnPalet;
				//$.msg(i);
				if (i == 1)
					$url += '&primero=true';
				else
					$url += '&primero=false';
				//$.msg($url);
				$.ajax({
				 url: $url,
				 cache: false,
				 async:false,
				 success: function(html){
					$("#widget_consOE").empty();
					$("#widget_consOE").append(html);
				 }
				});
				$.ajax({
					type: "POST",
					url: "registroentrada/bultosRE.js",
					dataType: "script"
				});
				$.ajax({
					type: "POST",
					url: "js/script.js",
					dataType: "script"
				});
				$.msg("Palet actualizado!");
			},
			function(){
				$.msg("No has actualizado el palet!");
		});
}

function mover(i){
	//$.msg("i: " + i + "; seleccionado: " + $("#cantidadMover").val() + " ; tope: " + $("#numBulto" + i).val());
	if ($("#cantidadMover").val() == "" || $("#cantidadMover").val() == 0)
		$.msg("Seleccione la cantidad a mover");
	else{
		if (parseFloat($("#cantidadMover").val()) > parseFloat($("#numBulto" + i).val())){
			$.msg("Demasiados bultos seleccionados");
			$("#cantidadMover").val($("#numBulto" + i).val());
		}else{
			//$.msg("listo");
			$("#cantidad").val($("#cantidadMover").val());
			$("#numeroBultos").val($("#numeroBultosMover").val());
			$("#idNuevoPalet").val($(".filaUbicacion").get().length);
			$("#idHuecoViejo").val($("#idHueco" + i).val());
			//$.msg($("#idHuecoViejo").val());
			$("#idPaletViejo").val(i);
			document.formu_mover.submit();
		}
	}
}

function pedirOpcionesMover(i){
	$("#fondo").show();
	$("#msg").show();
	//$("#idMateriaPrima").val(idMateriaPrima);
	//document.getElementById('presenta').innerHTML = 'Categorias para la materia prima con id: ' + idMateriaPrima;
	//document.getElementById('presenta').innerHTML = 'Categorias de ' + nombre;
	document.getElementById('presenta').innerHTML = 
		'<fieldset>' +
			'<legend><span>Mover ubicación</span></legend>' +
				'<table width="100%" cellpadding="2" cellspacing="2" border="0">' +
					'<tr>' +
						'<td class="label"><label name="Cantidad" value="Cantidad">Cantidad</label></td>' +
						'<td nowrap="nowrap">' +
							'<input id="cantidadMover" key="cantidad" label="Cantidad"/>' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<td class="label"><label name="Numero de bultos" value="Numero de bultos">Numero de bultos</label></td>' +
						'<td nowrap="nowrap">' +
							'<input id="numeroBultosMover" key="numeroBultosMover" label="Numero de bultos"/>' +
						'</td>' +
					'</tr>' +
				'</table>' +
		'</fieldset>' +
		'<br />' +
		'<a id="bot_inserta" class="botonInserta" href="javascript:mover(' + i + ');" title="Seleccionar hueco de destino">' +
			'<img src="img/j_button1_add.png" alt="Seleccionar hueco de destino" title="Seleccionar hueco de destino"/>Mover' +
		'</a>' +
		'<br />' +
		'<br />';
	$("#msg").attr("height", "20px");
}

function ajustarPesos(){
	var pNeto = $("#pesoNeto").val();
	var pBruto = $("#pesoBruto").val();
	var uds = $("#uds").val();
	//$.msg("pNeto ("+pNeto+") >pBruto ("+pBruto+")");
	if (tipo == 'M'){
		if (pNeto > pBruto) {
			$.msg("Algo no va bien, el peso Neto (sin embalaje), no puede ser mayor que el peso Bruto (con embalaje). Autoajustando");
			$("#pesoNeto").val(pBruto);
		}else{
			//$.msg("Ajustando pesos");
			netos = $(".netos");
			for (i = 0; i < netos.length; i++){
				alert(1);
				if (!$("#" + netos[i].id).is(":disabled"))
					$("#" + netos[i].id).val(pNeto);
			}
			brutos = $(".brutos");
			for (i = 0; i < brutos.length; i++){
				if (!$("#" + brutos[i].id).is(":disabled")){
					$("#" + brutos[i].id).val(pBruto);
					creaUrl((parseFloat(i) + 1), false, true);
				}
			}
		}
	}else{
		if (tipo == 'E'){
			netos = $(".netos");
			for (i = 0; i < netos.length; i++){
				//alert(1);
				if (!$("#" + netos[i].id).is(":disabled"))
					$("#" + netos[i].id).val(uds);
			}
			brutos = $(".brutos");
			for (i = 0; i < brutos.length; i++){
				if (!$("#" + brutos[i].id).is(":disabled")){
					$("#" + brutos[i].id).val(uds);
					creaUrl((parseFloat(i) + 1), false, true);
				}
			}
		}
	}
}

function ajustarBultos(){
	maximoBultos = $("#totalBultos").text();
	numeroBultosAjustar = $("#numeroBultosAjustar").val();
	//$.msg(numeroBultosAjustar);
	numeroBultos = $(".numBultos");
	cuantosVan = 0;
	for (i = 0; i < numeroBultos.length; i++){
		if (!$("#" + numeroBultos[i].id).is(":disabled")){
			if (cuantosVan + parseFloat(numeroBultosAjustar) <= maximoBultos){//Caso normal
				$("#" + numeroBultos[i].id).val(numeroBultosAjustar);
			}else
				if (cuantosVan + parseFloat(numeroBultosAjustar) > maximoBultos){//Caso 2, se va a pasar del numero de bultos
					//$.msg("Caso 2 -> " + (maximoBultos - cuantosVan));
					$("#" + numeroBultos[i].id).val(maximoBultos - cuantosVan);
				}
			cuantosVan += parseFloat($("#" + numeroBultos[i].id).val());
			if (i == (numeroBultos.length - 1)){
				if (cuantosVan < maximoBultos)
					$("#" + numeroBultos[i].id).val(parseFloat($("#" + numeroBultos[i].id).val()) + (maximoBultos - cuantosVan));
			}
		}
		creaUrl((parseFloat(i) + 1), false, true);
	}
	brutoBulto = $("#pBruto1").val() / $("#numBulto1").val();
	netoBulto = $("#pNeto1").val() / $("#numBulto1").val();
	if ($("#numBulto1").val() != $("#numBulto" + i).val()){
		if (!$("#pBruto" + i).is(":disabled"))
			$("#pBruto" + i).val(brutoBulto * $("#numBulto" + i).val());
		if (!$("#pNeto" + i).is(":disabled"))
			$("#pNeto" + i).val(netoBulto * $("#numBulto" + i).val());
	}
}

function salvarBultos(){
	var entrada = document.getElementById("codigoEntrada").innerHTML;
	var $url = "UpdateBultoRE.action?unico=false&idEntrada=" + entrada;
	filas = $(".filaPalet");
	//$.msg(filas.length);
	for (i = 1; i <= filas.length; i++){
		if (i == 1)
			$url += "&primero=true";
		//$.msg($("#pNeto" + i).val());
		//$.msg($("#pNeto1").val());
		pNeto = $("#pNeto" + i).val();
		pBruto = $("#pBruto" + i).val();
		numBultoEnPalet = $("#numBulto" + i).val();
		$url += "&numBulto" + i + "=" + numBultoEnPalet + "&pBruto" + i + "=" + pBruto + "&pNeto" + i + "=" + pNeto;// + "&numBultosPalet=" + numBultoEnPalet;
	}
	//alert($url);
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consOE").empty();
		$("#widget_consOE").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/bultosRE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

/*function imprimirTodasEtiquetas(){
	var entrada = document.getElementById("codigoEntrada").innerHTML;
	var $url = "TodasEtiquetasRE.action?codigoEntrada=" + entrada;
	//$.msg($url);
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consOE").empty();
		$("#widget_consOE").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/bultosRE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}*/

function ubicarI(idPalet){
	//$.msg("Asegurese de introducir el numero de bultos y los pesos correctos antes de ubicar el palet numero " + idPalet);
	creaUrl(idPalet, true, false);
}

function ubicarII(idPalet){
	//$.msg("bultosRE -> ubicarII");
	var $ubica = "SalvaDatosSacarUbicacion.action?gestionBultos=true&mover=true&sacarRegistro_" + $("#codigoEntrada").text() + "__" +
		$("#idUbicacion" + idPalet).val() + "__" + idPalet + "=" + $("#pNeto" + idPalet).val();
	reg = $("#codigoEntrada").text();
	numBultosEnPalet = $("#numBulto" + idPalet).val();
	cantidad = $("#pNeto" + idPalet).val();
	//$.msg($ubica);
	$("#gestionBultos").val("true");
	$("#registro").val(reg);
	$("#cantidad").val(cantidad);
	//$.msg($("#cantidad").val());
	$("#idPalet").val(idPalet);
	$("#numeroBultos").val(numBultosEnPalet);
	//$.msg($ubica);
	$.ajax({
	 url: $ubica,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consOE").length > 0){
			$("#widget_consOE").empty();
			$("#widget_consOE").append(html);
			$("#widget_consOE").attr("id", "widget_consUbica");
		}else
			if ($("#widget_consRE").length > 0){
				$("#widget_consRE").empty();
				$("#widget_consRE").append(html);
				$("#widget_consRE").attr("id", "widget_consUbica");
			}
	 }
	});
	$.ajax({
		type: "POST",
		url: "ubicacion/seleccionAlmacen.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function volverOrden(){
	//$.msg($("#orden").val());
	var $url = "";
	if ($("#widget_consOE").length > 0){
		$url = "PreviaRegistroOE.action?codigoOrden=" + $("#orden").val();
	}else
		if ($("#widget_consRE").length > 0){
			listaRE();
			return 1;
		}
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consOE").length > 0){
			$("#widget_consOE").empty();
			$("#widget_consOE").append(html);
		}
	 }
	});
	var $js = "";
	$.ajax({
		type: "POST",
		url: "registroentrada/etiquetasRE.js",
		dataType: "script"
	});
		$.ajax({
		type: "POST",
		url: "registroentrada/comunOERE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}