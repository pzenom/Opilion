var odd = true;
var inicio = true;
var linea = 1;
var productos = new Array();
var cuantos = 0;

$(document).ready(function(){
	//alert(1);
	$("#formulario_direccionesCliente").attr("disabled", true);
	//Cargamos la divisa
	$("#dropdown_divisas").val($('#divisa_hidden').val());
	$("#" + $("#dropdown_divisas").parent().attr("id")).children('span').text(
		$('#dropdown_divisas option[value=' + $('#divisa_hidden').val() + ']').text());
	//Cargamos el cliente
	$("#dropdown_clientes").val($('#cliente_hidden').val());
	$("#" + $("#dropdown_clientes").parent().attr("id")).children('span').text(
		$('#dropdown_clientes option[value=' + $('#cliente_hidden').val() + ']').text());
	if ($('#cliente_hidden').val() > 0){
		seleccionCliente(false);
	}
	//Cargamos la forma de pago seleccionada
	$("#dropdown_formasDePago").val($('#formaPago_hidden').val());
	$("#" + $("#dropdown_formasDePago").parent().attr("id")).children('span').text(
		$('#dropdown_formasDePago option[value=' + $('#formaPago_hidden').val() + ']').text());
	//Activamos el botón para actualizar, y le definimos el onClick	
	$("#bot_actualizar").show();
	$("#bot_actualizar").attr("onclick", "javascript:actualizaPedido();");
	//alert(parseFloat($("#tablaProductosBody").children().length));
	linea = parseInt(parseFloat($("#tablaProductosBody").children().length) / 2) + 1;
	//alert(parseInt(parseFloat($("#tablaProductosBody").children().length) / 2));
	//alert(linea);
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick" , "javascript:listaPedidos();");
	$("#bot_vuelve").show();
	//$("#bot_procesarPedido").attr("onclick" , "javascript:procesarPedido('" + $("#text_pedido").val() + "');");
	//$("#bot_procesarPedido").show();
	$("#widget_menu").show();
	$(".botonUbi").hide();
	/**/
	//alert(2);
	minimizaLinea(0, false);
	//Calcular todos los campos 'agrupacionesLineas'
	agrupacionesLineas = $(".agrupacionesLineas").get();
	for (i = 0; i < agrupacionesLineas.length; i++){
		idAgrupacion = agrupacionesLineas[i].id;
		idLinea = idAgrupacion.split("_")[1];
		qty21 = $("#unidadesPedidas_" + idLinea).val();
		qty59 = $("#unidadesAgrupaciones_" + idLinea).val();
		$("#" + idAgrupacion).val(qty21 / qty59);
	}
	/*preciosUnidades = $(".preciosKilo").get();
	for (i = 0; i < preciosUnidades.length; i++){
		idAgrupacion = preciosUnidades[i].id;
		idLinea = idAgrupacion.split("_")[1];
		precioTotalLinea = $("#precioTotalLinea_" + idLinea).val();
		unidades = $("#cantidadPedida_" + idLinea).val();
		$("#" + idAgrupacion).val(precioTotalLinea / unidades);
	}*/
	//Miramos para cada localizacion, si ya ha sido procesada(llevada a un albaran) o no
	localizaciones = $(".localizacionProcesada").get();
	//alert(localizaciones.length);
	//estado = $("#estado").val();
	estado = $("#estado").val();
	for (i = 0; i < localizaciones.length; i++){
		idLocalizacion = localizaciones[i].id;
		//alert(idLocalizacion);
		frag = idLocalizacion.split("_");
		loc = frag[1] + "_" + frag[2];
		locProcesada = $("#" + idLocalizacion).val();
		//alert(locProcesada);
		if (locProcesada == 'S' || estado == 'L'){
			$("#imgLocalizacionProcesada_" + loc).show();
			$(".uds_" + loc).attr("disabled", "disabled");
			$(".img_" + loc).hide();
		}
	}
	//Si ya se han pasado a albaran todas las localizaciones de una linea, esta linea ya no sera modificable. Esta bien?
	lineas = $(".linea").get();
	cuantas = lineas.length;
	for (i = 0; i < cuantas; i++){
		idLinea = lineas[i].id;
		frag = idLinea.split("_");
		numLinea = frag[1];
		localizacionesLinea = $(".localizacionProcesada_" + $("#" + idLinea).val()).get();
		flag = true;
		for (j = 0; j < localizacionesLinea.length; j++){
			idLocalizacion = localizacionesLinea[j].id;
			localizacionProcesada = $("#" + idLocalizacion).val();
			//alert(localizacionProcesada);
			if (localizacionProcesada == 'N'){
				flag = false;
				break;
			}
		}
		if (flag || estado == 'L'){
			$(".editables_" + numLinea).attr("disabled", "disabled");
			$("#imgLineaProcesada_" + numLinea).show();
		}
	}
	//Las unidades nunca pueden ser número decimales
	uds = $('.unidadesPedidas').get();
	for (i = 0; i < uds.length; i++){
		id = uds[i].id;
		val = $('#' + id).val();
		//alert(val);
		frag = val.split('.');
		$('#' + id).val(frag[0]);
	}
	//Controlamos los NaN
	uds = $('.preciosKilo').get();
	for (i = 0; i < uds.length; i++){
		id = uds[i].id;
		val = $('#' + id).val();
		if (val == 'NaN'){
			$('#' + id).val(0);
		}
	}
});

function minimizaLinea(l, maximiza){
	if (l == 0){//Si la linea que le pasamos es 0, minimiza todas la lineas que hay hasta ahora
		//linea = siguiente linea que se inserta
		//Entonces, tenemos que minimizar desde l = 1 hasta l = linea - 1
		for (i = 1; i <= linea - parseFloat(1); i++){
			minimizaLinea(i, false);
		}
	}else{
		if ($("#ocultaLinea" + l).is(" :visible")){//Si la linea esta minimizada, maximizamos
			if (maximiza == true || maximiza == "true"){
				$(".linea" + l).show();
				$("#ocultaLinea" + l).hide();
				//Ponemos la flecha hacia arriba
				$("#imgMinimiza_" + l).attr("src", "img/j_arrow_up.png");
			$("#imgMinimiza_" + l).attr("title", "Minimizar linea");
			$("#imgMinimiza_" + l).attr("alt", "Minimizar linea");
			}
		}else{//Viceversa (Si la linea está maximizada, minimizamos
			$(".linea" + l).hide();
			$("#ocultaLinea" + l).show();
			//Ponemos la flecha hacia abajo
			$("#imgMinimiza_" + l).attr("src", "img/j_arrow_down.png");
			$("#imgMinimiza_" + l).attr("title", "Ampliar linea");
			$("#imgMinimiza_" + l).attr("alt", "Ampliar linea");
		}
	}
}

function seleccionCliente(limpia){
	var selected = $("#dropdown_clientes").val();
	//alert(selected);
	if (selected > 0){
		limpiarDireccionesDelCliente();
		$("#contenedorSelect").show();
		$("#contenedorTexto").hide();
		$("#dropdown_formasDePago").val(0);
		$("#" + $("#dropdown_formasDePago").parent().attr("id")).children('span').text(
			$("#dropdown_formasDePago option[value=-1]").text());
		cargarFormasPago(selected);
		cargarDirecciones(selected, limpia);
		//######## Aprovechamos aqui y cargamos los hidden con los id del cliente
		$("#nadBy").val(selected);//comprador
		$("#nadMs").val(selected);//emisor del mensaje
		$("#nadDp").val(selected);
		$("#nadIv").val(selected);
	}else{
		$("#contenedorSelect").hide();
		$("#contenedorTexto").show();
		$("#textoSeleccioneCliente").text("Seleccione un cliente");
		$.msg("Seleccione un cliente");
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

function cargarDirecciones(cliente, limpia){
	//alert("0001");
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
	if (limpia == true)
		limpiarDireccionesUnidades();
	var dirClientesActuales = $(".direccionesCliente").get();
	var length = dirClientesActuales.length;
	//alert("0002");
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
	}else{
		$("#contenedorSelect").hide();
		$("#contenedorTexto").show();
		$("#textoSeleccioneCliente").text("El cliente seleccionado no tiene ninguna direccion de entrega asociada");
		$.msg("El cliente seleccionado no tiene ninguna direcci&oacute;n de entrega asociada");
	}
	//alert("0004");
}

function procesarLocalizacion(linea, direccion){
	$url = "LineasDireccionAlbaran.action?idCliente=" + $("#cliente_hidden").val() +
		"&fechaEntrega=" + $("#date_fechaEntrega").val() + "&idDireccion=" + direccion;
	codigoPedido = $("#text_pedido").val();
	//codigoPedido_PXX_linea=PXX_linea
	$url += "&codigoPedido_" + codigoPedido + "_" + linea + "=" + codigoPedido + "_" + linea;
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
				url: "registrosalida/detaRegiAlbaOrden.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "js/script.js",
				dataType: "script"
			});
			$("#btMenuPedidos").removeClass("active");
			$("#btMenuAlbaranes").addClass("active");
		},
		error: function(){
			$.msg("El albaran no se ha podido cargar");
		}
	});
}