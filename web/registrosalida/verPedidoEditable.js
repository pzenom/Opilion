var odd = true;
var inicio = true;
var linea = 1;
var cuantos = 0;
var fondo = false;
var mensaje = false;

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
	$("#bot_actualizar").attr("onclick" , "javascript:actualizaPedido();");
	$("#bot_actualizar").show();
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
	//alert(6);
	$("#categorizacion").treeview();
	$("#bot_addProductoPedido").show();
	//Miramos para cada localizacion, si ya ha sido procesada(llevada a un albaran) o no
	localizaciones = $(".localizacionProcesada").get();
	for (i = 0; i < localizaciones.length; i++){
		idLocalizacion = localizaciones[i].id;
		//alert(idLocalizacion);
		frag = idLocalizacion.split("_");
		loc = frag[1] + "_" + frag[2];
		locProcesada = $("#" + idLocalizacion).val();
		//alert(locProcesada);
		if (locProcesada == 'S'){
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
		if (flag){
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
	/*uds = $('.preciosKilo').get();
	for (i = 0; i < uds.length; i++){
		id = uds[i].id;
		val = $('#' + id).val();
		if (val == 'NaN'){
			$('#' + id).val(0);
		}
	}*/
});

function actualizaPedido(){
	continua = true;
	$url = "UpdateOrder.action?";		
	idFormaPago = $("#dropdown_formasDePago").val();
	//$.msg(idFormaPago);
	if (idFormaPago < 0){
		$.msg("Seleccione la forma de pago para el pedido");
	} else{
		udsDireccion = $(".unidadesDireccion");
		uds = udsDireccion.get();
		cuantas = uds.length;
		for (i = 0; i < cuantas; i++){
			cantidad = uds[i];
			if (parseFloat(cantidad.value) <= 0){
				continua = false;
				$.msg("No pueden ir 0 unidades a una direccion");
				break;
			}
		}
		if (continua){
			if (validarFecha("date_fechaEntrega") == false){
				$.msg("El formato para las fechas debe ser DD/MM/AAAA");
			}else{
				if (validarFecha("date_fechaPedido") == false){
					$.msg("El formato para las fechas debe ser DD/MM/AAAA");
				}else{
					lineas = $(".linea");
					lineasPedido = lineas.get();
					cuantas = lineasPedido.length;
					for (i = 0; i < cuantas; i++){
						/* Para cada linea, kilos, unidades, unidades en agrupacion, precio kilo, precio unidad TIENEN que ser > 0 */
						idLinea = lineasPedido[i].id;
						frag = idLinea.split('_');
						numeroLinea = frag[1];
						/*if ($("#cantidadPedida_" + numeroLinea).val() <= 0){
							continua = false;
							$.msg('Hay un error en la linea ' + numeroLinea + ' del pedido. Los kilos pedidos deben de ser mayor que cero.');
							break;
						}*/
						if ($("#unidadesPedidas_" + numeroLinea).val() <= 0){
							continua = false;
							$.msg('Hay un error en la linea ' + numeroLinea + ' del pedido. Las unidades pedidas deben de ser mayor que cero.');
							break;
						}else{
							if ($("#unidadesPedidas_" + numeroLinea).val().split('.').length > 1){
								continua = false;
								$.msg('Hay un error en la linea ' + numeroLinea + ' del pedido. Las unidades pedidas deben ser un numero entero.');
								break;
							}
						}
						if ($("#unidadesAgrupaciones_" + numeroLinea).val() <= 0){
							continua = false;
							$.msg('Hay un error en la linea ' + numeroLinea + ' del pedido. Cada agrupacion debe contener mas de cero unidades.');
							break;
						}
						if ($("#direcciones_" + numeroLinea).children().length == 0){
							continua = false;
							$.msg('Hay un error en la linea ' + numeroLinea + ' del pedido. Esta linea debe enviarse al menos a una direccion.');
							break;
						}
						udsAgrupacion = parseFloat($("#unidadesAgrupaciones_" + numeroLinea).val());
						uds = parseFloat($("#unidadesPedidas_" + numeroLinea).val())
						if (uds % udsAgrupacion){
							$.msg("Compruebe el numero de unidades pedidas y el numero de unidades por agrupacion para la linea " + linea);
							break;
						}
					}
					if (continua == true){
						$.confirm("&#191Confirma que desea guardas los cambios en el pedido?",
							function(){
								$("#idFormaPago").val(idFormaPago);
								if (compruebaBultosBienRepartidos()){$("#text_pedido").val()
									$("#cnt").val(parseFloat(linea) - 1);
									setDivisa();
									$url += "moa79=" + $("#importeTotal").text() + "&bgmNum=" + $("#text_pedido").val() + 
										"&fechaPedido=" + $("#date_fechaPedido").val() + "&cnt=" + $("#cnt").val() +
										"&cux=" + $("#cux").val() + "&fechaEntrega=" + $("#date_fechaEntrega").val() +
										"&idFormaPago=" + idFormaPago + "&nadMs=" + $("#dropdown_clientes").val() +
										"&nadBy=" + $("#dropdown_clientes").val() + "&observacionesPedido=" + $("#observacionesPedido").val();
									//Las direcciones y las unidades para cada linea
									var direccionesLinea = $(".direccionesLinea");
									direcciones = direccionesLinea.get();
									var cuantas = direcciones.length;
									for (i = 0; i < cuantas; i++){
										var direccion = direcciones[i];
										$url += "&" + direcciones[i].id + "=" + $("#" + direcciones[i].id).val();
									}
									var unidadesDireccion = $(".unidadesDireccion");
									unidades = unidadesDireccion.get();
									cuantas = unidades.length;
									for (i = 0; i < cuantas; i++){
										var cantidad = unidades[i];
										$url += "&" + unidades[i].id + "=" + cantidad.value;
									}
									lineas = $(".linea");
									lineasPedido = lineas.get();
									cuantas = lineasPedido.length;
									for (i = 0; i < cuantas; i++){
										$url += "&" + lineasPedido[i].id + "=" + $("#idProductoLinea_" + parseFloat(i + 1)).val();
									}
									unidadesPedidas = $(".unidadesPedidas");
									unidades = unidadesPedidas.get();
									cuantas = unidades.length;
									for (i = 0; i < cuantas; i++){
										var unidad = unidades[i];
										$url += "&" + unidades[i].id + "=" + unidad.value;
									}
									unidadesAgrupaciones = $(".unidadesAgrupacion");
									agrupaciones = unidadesAgrupaciones.get();
									cuantas = agrupaciones.length;
									for (i = 0; i < cuantas; i++){
										var agrupacion = agrupaciones[i];
										$url += "&" + agrupaciones[i].id + "=" + agrupacion.value;
									}
									netosT = $(".preciosLineas");
									netosTPedido = netosT.get();
									cuantas = netosTPedido.length;
									for (i = 0; i < cuantas; i++){
										var neto = netosTPedido[i];
										$url += "&" + netosTPedido[i].id + "=" + neto.value;
									}
									kilosPedidos = $(".pesosLineas");
									kilos = kilosPedidos.get();
									cuantas = kilos.length;
									for (i = 0; i < cuantas; i++){
										var kilo = kilos[i];
										$url += "&" + kilos[i].id + "=" + kilo.value;
									}
									netosU = $(".preciosUnidades");
									netosUPedido = netosU.get();
									cuantas = netosUPedido.length;
									for (i = 0; i < cuantas; i++){
										var netoU = netosUPedido[i];
										$url += "&" + netosUPedido[i].id + "=" + netoU.value;
									}
									codsEan = $(".codsEan");
									codsEanPedido = codsEan.get();
									cuantas = codsEanPedido.length;
									for (i = 0; i < cuantas; i++){
										var codEan = codsEanPedido[i];
										$url += "&" + codsEanPedido[i].id + "=" + codEan.value;
									}
									observacionesLineas = $(".observacionesLineas").get();
									cuantas = observacionesLineas.length;
									for (i = 0; i < cuantas; i++){
										var observaciones = observacionesLineas[i];
										$url += "&" + observacionesLineas[i].id + "=" + observaciones.value;
									}
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
												url: "edifact/listaorder.js",
												dataType: "script"
											});
											$.ajax({
												type: "POST",
												url: "js/script.js",
												dataType: "script"
											});
											$.msg("Pedido insertado correctamente");
										},
										error: function(){
											$.msg("El pedido no se ha podido insertar");
										}
									});
								}
							},
							function(){
								$.msg("Cambios no salvados");
							}
						);
					}
				}
			}
		}
	}
}


function aceptarProducto(idProducto){
	$("#hiddens").append($("#contenedorProductos"));
	$("#contenedorProductos").hide();
	//Resetear el treeview
	$("#categorizacion").treeview();
	$(".treeViewNOSeleccion").removeClass("treeViewSeleccion");
	cerrar();
	addProducto(idProducto);
}

function addProducto(idProducto){
	//alert(idProducto);
	//$.msg("Añadir producto al pedido");
	var fila = document.createElement('tr');
	fila.setAttribute("id", "lineaPedido_" + linea);
	var indiceSeleccionado = 0;
	if (idProducto > 0){
		indiceSeleccionado = idProducto;
		$("#dropdown_productos").val($("#prod_" + indiceSeleccionado).val());
		$("#" + $("#dropdown_productos").parent().attr("id")).children('span').text(
					$("#dropdown_productos option[id=prod_" + indiceSeleccionado + "]").text());
	}else{
		var productos = document.getElementById("dropdown_productos");
		indiceSeleccionado = productos.selectedIndex;
	}
	//alert(indiceSeleccionado);
	if (indiceSeleccionado > 0){
		//$.msg(indiceSeleccionado);
		var idProductoEAN = $("#dropdown_productos").val();
		frag = idProductoEAN.split('_');
		var selected = frag[0];
		var codigoEan = frag[1];
		var peso = frag[2];
		var mostrar = frag[3];//0: item o agrupacion; 1: granel
		var EANs13 = frag[4];
		//alert(EANs13);
		//alert(peso);
		//Mirar si ya esta insertado el producto
		ids = $(".ids").get();
		existe = false;
		for (i = 0; i < ids.length; i++){
			idProd = $("#" + ids[i].id).val();
			//alert(selected + " vs " + $("#" + idProd).val());
			if (selected == idProd){
				existe = true;
				break;
			}
		}
		if (existe == true){
			$.msg("El producto seleccionado ya se encuentra en el pedido");
		}else{
			var nombre = $("#dropdown_productos option:selected").text();
			if (codigoEan != "" && selected > 0){
				minimizaLinea(0, false);
				fila.innerHTML =
					'<input id="linea_' + linea + '" name="linea_' + linea + '" class="linea" type="hidden"></input>' +
					'<input id="agrupaciones_' + linea + '" name="agrupaciones_' + linea + '" class="agrupaciones" type="hidden" value="0"></input>' +
					'<input id="codEan_' + linea + '" name="codEan_' + linea + '" value="' + codigoEan + "__" + selected + '" type="hidden" class="codsEan"/>'+
					'<input id="pesoProducto_' + linea + '" name="pesoProducto_' + linea + '" value="' + parseFloat(peso) + '" type="hidden" class="pesos"/>'+
					'<input id="pesoLinea_' + linea + '" name="pesoLinea_' + linea + '" value="0" type="hidden" class="pesosLineas"/>'+
					'<input id="EANs13Producto_' + linea + '" name="EANs13Producto_' + linea + '" value="' + EANs13 + '" type="hidden" class="eans13"/>'+
					'<input id="idProductoLinea_' + linea + '" name="idProductoLinea_' + linea + '" class="ids" value="' + selected + '" type="hidden" />' +
					'<td style="vertical-align: middle; width: 30px !important;" rowspan="2">' +
						'<p id="indiceLinea_' + linea + '" style="background: transparent !important;">' + linea + '</p>' +
					'</td>'+
					'<td id="celdaProductoLinea_' + linea + '" style="width: 180px !important; vertical-align: middle;" class="linea' + linea + '">' +
						nombre + '<br /> EAN: ' + codigoEan +
						'<fieldset>' +
							//'<label>Resumen de la linea</label>' +
							'<br /><br />Precio linea' +
							'<input id="precioTotalLinea_' + linea + '" class="preciosLineas" value="0" style="width:50px; background-color: #E6E6E6;" type="text" readonly="true" onchange="javascript:precioLineaModificado();" /> &euro;' +
							'<br /><br />Agrupaciones EDI linea' +
							'<input id="agrupacionesLinea_' + linea + '" class="agrupacionesLineas" value="0" style="width:50px; background-color: #E6E6E6;" type="text" readonly="true" />' +
						'</ fieldset>' +
					'</td>' +
					'<td style="width: 180px !important; vertical-align: middle; display: none;" id="ocultaLinea' + linea + '" colspan="5" rowspan="2">' +
						nombre +
					'</td>' +
					'<td id="celdaCantidadesLinea_' + linea + '" style="width: 90px !important; vertical-align: middle;" class="linea' + linea + '">' +
						/*'<div style="padding-bottom: 4px;">' +
							'Autoajustar' +
							'<input type="checkbox" id="checkAutoCantidad_' + linea + '" onchange="javascript:checkAutoCantidadLinea(' + linea + ');" checked="true" />' +
						'</div>' +
						'<br />Kilos' +
						'<input id="cantidadPedida_' + linea + '" name="cantidadPedida_' + linea + '" class="kilosPedidos" style="width:50px;" value="0" type="text" onKeyPress="return validarNumerosDecimales(' + "'cantidadPedida_" + linea + "'" + ',event);" onkeyup="javascript:kilosModificados(' + linea + ');" onblur="javascript:ajustarDecimal(' + "'cantidadPedida_" + linea + "'" + ');" />' +*/
						'<br />Unidades<br />' +
						'<input id="unidadesPedidas_' + linea + '" name="unidadesPedidas_' + linea + '" class="unidadesPedidas" onkeyup="unidadesModificadas(' + linea + ', ' + mostrar + ')" style="width:50px;" onKeyPress="return validarSoloNumeros(event);" value="0" />' +
					'</td>' +
					'<td id="celdaPreciosLinea_' + linea + '" style="width: 90px !important; vertical-align: middle;" class="linea' + linea + '">' +
						/*'<div style="padding-bottom: 4px;">' +
							'Autoajustar' +
							'<input type="checkbox" id="checkAutoPrecios_' + linea + '" onchange="javascript:checkAutoPreciosLinea(' + linea + ');" checked="true" />' +
						'</div>' +
						'<br />&euro;/Kg' +
						'<input id="precioKilo_' + linea + '" name="precioKilo_' + linea + '" class="preciosKilo" onKeyPress="return validarNumerosDecimales(' + "'precioKilo_" + linea + "'" + ',event);" onkeyup="javascript:precioKiloModificado(' + linea + ');" onblur="javascript:ajustarDecimal(' + "'precioKilo_" + linea + "'" + ');" style="width:50px;" value="0" type="text"/>' +*/
						'<br />&euro;/unidad de venta<br />' +
						'<input id="precioUnidad_' + linea + '" name="precioUnidad_' + linea + '" class="preciosUnidades" onKeyPress="return validarNumerosDecimales(' + "'precioUnidad_" + linea + "'" + ',event);" value="0" style="width:50px;" type="text" onblur="javascript:ajustarDecimal(' + "'precioUnidad_" + linea + "'" + ');" onkeyup="javascript:precioUnidadModificado(' + linea + ',' + mostrar + ');" />' +
					'</td>' +
					'<td id="celdaAgrupacionesLinea_' + linea + '" style="width: 80px !important; vertical-align: middle;" class="linea' + linea + '">' +
						'Cada agrupacion EDI contiene<br />' +
						'<input id="unidadesAgrupaciones_' + linea + '" name="unidadesAgrupaciones_' + linea + '" onKeyPress="return validarSoloNumeros(event);" class="unidadesAgrupacion" onkeyup="javascript:agrupacionesDePaquetesModificado(' + linea + ');" style="width:50px;" value="0" type="text"/>' +
						'<br />unidades' +
					'</td>' +
					'<td id="celdaDireccionesLinea_' + linea + '" style="width: 350px !important; max-width: 350px !important;" class="linea' + linea + '">' +
						'<select id="dropdown_direcciones_' + linea + '" onchange="javascript:aniadirDireccion(' + linea + ');">' +
							'<option selected value="0">Seleccione una direccion</option>' +
							'<optgroup id="entrega_' + linea + '" class="direccionesCliente" label="Direcciones"></optgroup>' +
						'</select>' +
						'<p style="background: transparent !important;">&nbsp;</p>' +
						'<table>' +
							'<thead><tr><th>Direccion</th><th>Agrupaciones EDI</th></tr></thead>' +
							'<tbody id="direcciones_' + linea + '"></tbody>' +
						'</table>' +
					'</td>' +
					'<td style="vertical-align: middle;" rowspan="2">' +
						'<a id="minimiza_' + linea + '" title="Minimizar" href="javascript:minimizaLinea(' + linea + ', true)">' +
							'<img id="imgMinimiza_' + linea + '" title="Minimiza esta linea" alt="Minimiza esta linea" src="img/j_arrow_up.png">' +
						'</a>' +
						'<a id="elimina_' + linea + '" title="Eliminar este producto" href="javascript:eliminaProducto(' + linea + ')">' +
							'<img title="Eliminar este producto" alt="Eliminar este producto" src="img/cancel.png">' +
						'</a>' +
					'</td>';
					var fila2 = document.createElement('tr');
					fila2.setAttribute("id", "dirsLinea_" + linea);
					fila2.innerHTML =
						'<td id="celdaObservacionesLinea_' + linea + '" colspan="5" class="linea' + linea + '">' +
							'Observaciones:' +
								'<textarea id="observaciones_' + linea + '" name="observaciones_' + linea + '" class="observacionesLineas" style="width:96%;" rows="2"></textarea>' +
						'</td>';
				$("#tablaProductosBody").append(fila);
				$("#tablaProductosBody").append(fila2);
				$.msg("Producto insertado en el pedido: " + nombre);
				$("#dropdown_productos").val(0);
				$("#" + $("#dropdown_productos").parent().attr("id")).children('span').text(
					$("#dropdown_productos option[value=0]").text());
				direccionesNuevoProducto(linea);
				linea++;
				//Aniadimos el producto al array de productos
				//this.productos.push(selected);
				$("#bot_actualizar").show();
				cuantos++;
			}else{
				$.msg("El producto seleccionado no esta bien registrado. Problema con el idProducto o con el codigo EAN del producto.");
			}
		}
	}
}

function minimizar(l, maximiza){
	minimizaLinea(0, false);
	minimizaLinea(l, maximiza);
}

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
				$("#minimiza_" + l).attr("href", "javascript:minimizar(" + l + ", false)");
			}
		}else{//Viceversa (Si la linea está maximizada, minimizamos
			$(".linea" + l).hide();
			$("#ocultaLinea" + l).show();
			//Ponemos la flecha hacia abajo
			$("#imgMinimiza_" + l).attr("src", "img/j_arrow_down.png");
			$("#imgMinimiza_" + l).attr("title", "Ampliar linea");
			$("#imgMinimiza_" + l).attr("alt", "Ampliar linea");
			$("#minimiza_" + l).attr("href", "javascript:minimizar(" + l + ", true)");
		}
	}
}

function eliminaProducto(l){
	$.confirm("&#191Eliminar la linea " + l + "?",
		function(){
			cuantosProductos = $("#tablaProductosBody").children().length / 2;
			$("#lineaPedido_" + l).unbind();
			$("#lineaPedido_" + l).remove();
			$("#dirsLinea_" + l).unbind();
			$("#dirsLinea_" + l).remove();
			//alert(productos.length);
			//for (i = parseFloat(parseFloat(l) + 1); i <= productos.length ; i++){
			//alert(cuantosProductos);
			for (i = parseFloat(parseFloat(l) + 1); i <= cuantosProductos ; i++){
				idProducto = $('#idProductoLinea_' + i).val();
				
				$('#indiceLinea_' + (i)).attr("id", "indiceLinea_" + (i - 1));
				$('#indiceLinea_' + (i -1)).text(i - 1);
				
				$('#agrupaciones_' + (i)).attr("id", "agrupaciones_" + (i - 1));

				$('#linea_' + parseFloat(i)).attr("id", "linea_" + parseFloat(i - 1));
				$('#linea_' + parseFloat(i - 1)).attr("name", "linea_" + (i - 1));
				$('#lineaProcesada_' + parseFloat(i)).attr("id", "lineaProcesada_" + parseFloat(i - 1));
				$('#lineaProcesada_' + parseFloat(i - 1)).attr("name", "lineaProcesada_" + (i - 1));
				$('#pesoProducto_' + parseFloat(i)).attr("id", "pesoProducto_" + parseFloat(i - 1));
				$('#pesoProducto_' + parseFloat(i - 1)).attr("name", "pesoProducto_" + (i - 1));
				$('#lineaPedido_' + parseFloat(i)).attr("id", "lineaPedido_" + parseFloat(i - 1));
				$('#lineaPedido_' + parseFloat(i - 1)).attr("name", "lineaPedido_" + (i - 1));
				$('#codEan_' + parseFloat(i)).attr("id", "codEan_" + parseFloat(i - 1));
				$('#codEan_' + parseFloat(i - 1)).attr("name", "codEan_" + parseFloat(i - 1));
				
				$('#celdaProductoLinea_' + parseFloat(i)).attr("id", "celdaProductoLinea_" + parseFloat(i - 1));
				$('#celdaProductoLinea_' + parseFloat(i - 1)).attr("class", "linea" + parseFloat(i - 1));
				$('#celdaPreciosLinea_' + parseFloat(i)).attr("id", "celdaPreciosLinea_" + parseFloat(i - 1));
				$('#celdaPreciosLinea_' + parseFloat(i - 1)).attr("class", "linea" + parseFloat(i - 1));
				$('#celdaCantidadesLinea_' + parseFloat(i)).attr("id", "celdaCantidadesLinea_" + parseFloat(i - 1));
				$('#celdaCantidadesLinea_' + parseFloat(i - 1)).attr("class", "linea" + parseFloat(i - 1));
				$('#celdaAgrupacionesLinea_' + parseFloat(i)).attr("id", "celdaAgrupacionesLinea_" + parseFloat(i - 1));
				$('#celdaAgrupacionesLinea_' + parseFloat(i - 1)).attr("class", "linea" + parseFloat(i - 1));
				$('#celdaDireccionesLinea_' + parseFloat(i)).attr("id", "celdaDireccionesLinea_" + parseFloat(i - 1));
				$('#celdaDireccionesLinea_' + parseFloat(i - 1)).attr("class", "linea" + parseFloat(i - 1));
				$('#celdaObservacionesLinea_' + parseFloat(i)).attr("id", "celdaObservacionesLinea_" + parseFloat(i - 1));
				$('#celdaObservacionesLinea_' + parseFloat(i - 1)).attr("class", "linea" + parseFloat(i - 1));
				
				$('#precioTotalLinea_' + parseFloat(i)).attr("id", "precioTotalLinea_" + parseFloat(i - 1));
				$('#agrupacionesLinea_' + parseFloat(i)).attr("id", "agrupacionesLinea_" + parseFloat(i - 1));
				$('#ocultaLinea' + parseFloat(i)).attr("id", "ocultaLinea" + parseFloat(i - 1));
				//$('#uniform-checkAutoCantidad_' + parseFloat(i)).attr("id", "uniform-checkAutoCantidad_" + parseFloat(i - 1));
				//$('#uniform-checkAutoPrecios_' + parseFloat(i)).attr("id", "uniform-checkAutoPrecios_" + parseFloat(i - 1));
				$('#minimiza_' + parseFloat(i)).attr("id", "minimiza_" + parseFloat(i - 1));
				$('#minimiza_' + parseFloat(i - 1)).attr("href", "javascript:minimizaLinea(" + parseFloat(i - 1) + ", true)");
				$('#imgMinimiza_' + parseFloat(i)).attr("id", "imgMinimiza_" + parseFloat(i - 1));
				
				$('#dirsLinea_' + parseFloat(i)).attr("id", "dirsLinea_" + parseFloat(i - 1));
				$('#dirsLinea_' + parseFloat(i - 1)).attr("name", "dirsLinea_" + (i - 1));
				
				$('#dropdown_direcciones_' + parseFloat(i)).attr("onchange", "javascript:aniadirDireccion('" + parseFloat(i - 1) + "');");
				$('#dropdown_direcciones_' + parseFloat(i)).attr("id", "dropdown_direcciones_" + parseFloat(i - 1));
				$('#dropdown_direcciones_' + parseFloat(i - 1)).attr("class", "editables_" + parseFloat(i - 1));
				$('#dropdown_direcciones_' + parseFloat(i - 1)).attr("name", "dropdown_direcciones_" + parseFloat(i - 1));
				$('#entrega_' + parseFloat(i)).attr("id", "entrega_" + parseFloat(i - 1));
				$('#entrega_' + parseFloat(i - 1)).attr("name", "entrega_" + parseFloat(i - 1));
				$('#direcciones_' + parseFloat(i)).attr("id", "direcciones_" + parseFloat(i - 1));
				$('#direcciones_' + parseFloat(i - 1)).attr("name", "direcciones_" + parseFloat(i - 1));
				$('#idProductoLinea_' + parseFloat(i)).attr("id", "idProductoLinea_" + parseFloat(i - 1));
				$('#idProductoLinea_' + parseFloat(i - 1)).attr("name", "idProductoLinea_" + parseFloat(i - 1));
							
				/*$('#checkAutoCantidad_' + parseFloat(i)).attr("onchange", "javascript:checkAutoCantidadLinea('" + parseFloat(i - 1) + "');");
				$('#checkAutoCantidad_' + parseFloat(i)).attr("id", "checkAutoCantidad_" + parseFloat(i - 1));
				$('#checkAutoCantidad_' + parseFloat(i)).attr("class", "editables_" + parseFloat(i - 1));*/
				/*$('#cantidadPedida_' + parseFloat(i)).attr("onkeypress", "return validarNumerosDecimales('cantidadPedida_" + parseFloat(i - 1) +"', event);");
				$('#cantidadPedida_' + parseFloat(i)).attr("onkeyup", "javascript:kilosModificados(" + parseFloat(i - 1) + ");");
				$('#cantidadPedida_' + parseFloat(i)).attr("onblur", "javascript:ajustarDecimal('cantidadPedida_" + parseFloat(i - 1) + "');");
				$('#cantidadPedida_' + parseFloat(i)).attr("id", "cantidadPedida_" + parseFloat(i - 1));
				$('#cantidadPedida_' + parseFloat(i - 1)).attr("name", "cantidadPedida_" + parseFloat(i - 1));
				$('#cantidadPedida_' + parseFloat(i - 1)).removeClass('editables_' + i);
				$('#cantidadPedida_' + parseFloat(i - 1)).addClass('editables_' + (i - 1));*/
				$('#unidadesPedidas_' + parseFloat(i)).attr("onkeyup", "javascript:unidadesModificadas(" + parseFloat(i - 1) + ");");
				$('#unidadesPedidas_' + parseFloat(i)).attr("id", "unidadesPedidas_" + parseFloat(i - 1));
				$('#unidadesPedidas_' + parseFloat(i - 1)).attr("name", "unidadesPedidas_" + parseFloat(i - 1));
				$('#unidadesPedidas_' + parseFloat(i - 1)).removeClass('editables_' + i);
				$('#unidadesPedidas_' + parseFloat(i - 1)).addClass('editables_' + (i - 1));
				
				/*$('#checkAutoPrecios_' + parseFloat(i)).attr("onchange", "javascript:checkAutoPreciosLinea('" + parseFloat(i - 1) + "');");
				$('#checkAutoPrecios_' + parseFloat(i)).attr("id", "checkAutoPrecios_" + parseFloat(i - 1));
				$('#checkAutoPrecios_' + parseFloat(i)).attr("class", "editables_" + parseFloat(i - 1));*/
				/*$('#precioKilo_' + parseFloat(i)).attr("onkeypress", "return validarNumerosDecimales('precioKilo_" + parseFloat(i - 1) +"', event);");
				$('#precioKilo_' + parseFloat(i)).attr("onkeyup", "javascript:precioKiloModificado('" + parseFloat(i - 1) + "');");
				$('#precioKilo_' + parseFloat(i)).attr("onblur", "javascript:ajustarDecimal('precioKilo_" + parseFloat(i - 1) + "');");
				$('#precioKilo_' + parseFloat(i)).attr("id", "precioKilo_" + parseFloat(i - 1));
				$('#precioKilo_' + parseFloat(i - 1)).attr("name", "precioKilo_" + parseFloat(i - 1));
				$('#precioKilo_' + parseFloat(i - 1)).removeClass('editables_' + i);
				$('#precioKilo_' + parseFloat(i - 1)).addClass('editables_' + (i - 1));*/
				$('#precioUnidad_' + parseFloat(i)).attr("onkeypress", "return validarNumerosDecimales('precioUnidad_" + parseFloat(i - 1) +"', event);");
				$('#precioUnidad_' + parseFloat(i)).attr("onkeyup", "javascript:precioUnidadModificado('" + parseFloat(i - 1) + "');");
				$('#precioUnidad_' + parseFloat(i)).attr("onblur", "javascript:ajustarDecimal('precioUnidad_" + parseFloat(i - 1) + "');");
				$('#precioUnidad_' + parseFloat(i)).attr("id", "precioUnidad_" + parseFloat(i - 1));
				$('#precioUnidad_' + parseFloat(i - 1)).attr("name", "precioUnidad_" + parseFloat(i - 1));
				$('#precioUnidad_' + parseFloat(i - 1)).removeClass('editables_' + i);
				$('#precioUnidad_' + parseFloat(i - 1)).addClass('editables_' + (i - 1));
				
				$('#observaciones_' + parseFloat(i)).attr("id", "observaciones_" + parseFloat(i - 1));
				$('#observaciones_' + parseFloat(i - 1)).attr("name", "observaciones_" + parseFloat(i - 1));

				$('#unidadesAgrupaciones_' + parseFloat(i)).attr("onkeyup", "javascript:agrupacionesDePaquetesModificado('" + parseFloat(i - 1) + "');");
				$('#unidadesAgrupaciones_' + parseFloat(i)).attr("id", "unidadesAgrupaciones_" + parseFloat(i - 1));
				$('#unidadesAgrupaciones_' + parseFloat(i - 1)).attr("name", "unidadesAgrupaciones_" + parseFloat(i - 1));
				$('#unidadesAgrupaciones_' + parseFloat(i - 1)).removeClass('editables_' + i);
				$('#unidadesAgrupaciones_' + parseFloat(i - 1)).addClass('editables_' + (i - 1));
			
				$('#elimina_' + parseFloat(i)).attr("id", "elimina_" + parseFloat(i - 1));
				$('#elimina_' + parseFloat(i - 1)).attr("href", "javascript:eliminaProducto(" + parseFloat(i - 1) + ")");
			
				//Modificar las direcciones de la linea
				localizaciones = $('.localizacionProcesada_' + i).get();
				for (k = 0; k < localizaciones.length; k++){
					idLoca = localizaciones[k].id;
					fragAux = idLoca.split('_');
					loca = fragAux[2];
					$("#" + idLoca).attr("name", "localizacionProcesada_" + parseFloat(i - 1) + "_" + loca);
					$("#" + idLoca).removeClass("localizacionProcesada_" + parseFloat(i));
					$("#" + idLoca).addClass("localizacionProcesada_" + parseFloat(i - 1));
					$("#" + idLoca).attr("id", "localizacionProcesada_" + parseFloat(i - 1) + "_" + loca);
					$("#imgLocalizacionProcesada_" + i + "_" + loca).attr("style", $("localizacionProcesada_" + parseFloat(i - 1) + "_" + loca).attr('style'));
					$("#imgLocalizacionProcesada_" + i + "_" + loca).attr("title", $("localizacionProcesada_" + parseFloat(i - 1) + "_" + loca).attr('title'));
					$("#imgLocalizacionProcesada_" + i + "_" + loca).attr("id", "localizacionProcesada_" + parseFloat(i - 1) + "_" + loca);
				}
				
				//Fila: id, class
				direccionesLinea = $(".direccionesUnidades_" + i).get();
				for (j = 0; j < direccionesLinea.length; j++){
					//Cada linea de direccion
					lineaDireccion = direccionesLinea[j];
					id = lineaDireccion.id;
					//alert(id);
					//id = direccionUnidades_2_1
					$('#' + id).removeClass('direccionUnidades_' + i);
					$('#' + id).addClass('direccionUnidades_' + (i - 1));

					frag = id.split('_');
					cuenta = frag[2];
					
					$("#direccion_" + i + "_" + cuenta).attr("name", "direccion_" + parseFloat(i - 1) + "_" + cuenta);
					$("#direccion_" + i + "_" + cuenta).attr("id", "direccion_" + parseFloat(i - 1) + "_" + cuenta);
					
					$("#" + id).attr("id", frag[0] + "_" + parseFloat(i - 1) + "_" + cuenta);
					$("#" + frag[0] + "_" + parseFloat(i - 1) + "_" + cuenta).removeClass("direccionesUnidades_" + i);
					$("#" + frag[0] + "_" + parseFloat(i - 1) + "_" + cuenta).addClass("direccionesUnidades_" + parseFloat(i - 1));
					
					$("#direccion_" + parseFloat(i) + "_" + cuenta).attr("id", "direccion_" + parseFloat(i - 1) + "_" + cuenta);
					$("#direccion_" + parseFloat(i - 1) + "_" + cuenta).attr("name", "direccion_" + parseFloat(i - 1) + "_" + cuenta);
					
					$("#inputDireccion_" + parseFloat(i) + "_" + cuenta).attr("id", "inputDireccion_" + parseFloat(i - 1) + "_" + cuenta);
					
					clase = $("#elimina_" + parseFloat(i) + "_" + cuenta).attr('class');
					frag = clase.split("_");
					local = frag[2];
					$("#elimina_" + parseFloat(i) + "_" + cuenta).removeClass("img_" + parseFloat(i) + "_" + local);
					$("#elimina_" + parseFloat(i) + "_" + cuenta).addClass("img_" + parseFloat(i - 1) + "_" + local);
					$("#elimina_" + parseFloat(i) + "_" + cuenta).attr("id", "elimina_" + parseFloat(i - 1) + "_" + cuenta);
					$("#elimina_" + parseFloat(i - 1) + "_" + cuenta).attr("href", "javascript:eliminaDireccion(" + parseFloat(i - 1) + "," + cuenta + ");");
					
					$("#unidades_" + parseFloat(i) + "_" + cuenta).removeClass("uds_" + parseFloat(i) + "_" + local);
					$("#unidades_" + parseFloat(i) + "_" + cuenta).addClass("uds_" + parseFloat(i - 1) + "_" + local);
					$("#unidades_" + parseFloat(i) + "_" + cuenta).attr("id", "unidades_" + parseFloat(i - 1) + "_" + cuenta);
					$("#unidades_" + parseFloat(i - 1) + "_" + cuenta).attr("name", "unidades_" + parseFloat(i - 1) + "_" + cuenta);
					$("#unidades_" + parseFloat(i - 1) + "_" + cuenta).attr("onchange", "javascript:compruebaBultosUnicaDireccion(" + parseFloat(i - 1) + ");");
				}
			}
			//2. Modificar el valor de linea. Reducir 1
			linea--;
			cuantos--;
			if (cuantos == 0)
				$("#bot_actualizar").hide();
			calcularKilosTotales();
			calcularUnidadesTotales();
			calcularAgrupacionesTotales();
			calcularImporteTotal();
			$.msg("Linea " + l + " eliminada del pedido");
		},
		function(){
			$.msg("La linea " + l + " no se ha eliminado");
		}
	);
}

function calcularKilosTotales(){
	kilos = $(".pesosLineas");
	sumaKilos = 0;
	for (i = 0; i < kilos.length; i++){
		id = kilos[i].id;
		sumaKilos += parseFloat($("#" + id).val());
	}
	$("#pesoNetoTotal").text(sumaKilos);
}

function calcularUnidadesTotales(){
	unidadesPedidas = $(".unidadesPedidas");
	suma = 0;
	for (i = 0; i < unidadesPedidas.length; i++){
		id = unidadesPedidas[i].id;
		suma += parseFloat($("#" + id).val());
	}
	$("#unidadesTotal").text(suma);
}

function calcularAgrupacionesTotales(){
	agrupaciones = $(".agrupacionesLineas");
	suma = 0;
	for (i = 0; i < agrupaciones.length; i++){
		id = agrupaciones[i].id;
		suma += parseFloat($("#" + id).val());
	}
	$("#agrupacionesTotal").text(suma);
}

function calcularImporteTotal(){
	precios = $(".preciosLineas");
	suma = 0;
	for (i = 0; i < precios.length; i++){
		id = precios[i].id;
		suma += parseFloat($("#" + id).val());
	}
	$("#importeTotal").text(suma);
}

function autocalcularUnidades(linea){
	/*if ($("#checkAutoCantidad_" + linea).is(" :checked") == true){
		//A partir de los kilos pedidos, si el producto está bien definido, autocalcular el numero de bultos (sacos, cajas) pedidos.
		pesoUnidad = $("#pesoProducto_" + linea).val();
		//alert(pesoUnidad);
		cantidadPedida = $("#cantidadPedida_" + linea).val();
		bultosCalculados = cantidadPedida / pesoUnidad;
		//alert(bultosCalculados);
		$("#unidadesPedidas_" + linea).val(bultosCalculados);
		calcularUnidadesTotales();
		unidadesModificadas(linea);
	}*/
}

function autocalcularKilos(linea){
	/*if ($("#checkAutoCantidad_" + linea).is(" :checked") == true){
		//A partir de las unidades pedidas, si el producto está bien definido, autocalcular el numero de kilos pedidos.
		pesoUnidad = $("#pesoProducto_" + linea).val();
		//alert(pesoUnidad);
		cantidadPedida = $("#unidadesPedidas_" + linea).val();
		kilosCalculados = cantidadPedida * pesoUnidad;
		//alert(bultosCalculados);
		$("#cantidadPedida_" + linea).val(kilosCalculados);
		calcularKilosTotales();
	}*/
}

function checkAutoCantidadLinea(linea){
	/*if ($("#checkAutoCantidad_" + linea).is(" :checked") == true)
		autocalcularUnidades(linea);*/
}

function autocalcularPrecioKilo(linea){
	/*if ($("#checkAutoPrecios_" + linea).is(" :checked") == true){
		// A partir el precio de la unidad, tenemos el numero de kilos de la unidad, calculamos el precio por kilo
		pesoUnidad = $("#pesoProducto_" + linea).val();
		precioUnidad = $("#precioUnidad_" + linea).val();
		precioKilo = precioUnidad / pesoUnidad;
		precioKilo = redondearValor(precioKilo, 100);
		$("#precioKilo_" + linea).val(precioKilo);
	}*/
}

function autocalcularPrecioUnidad(linea){
	/*if ($("#checkAutoPrecios_" + linea).is(" :checked") == true){
		//A partir de las unidades pedidas, si el producto está bien definido, autocalcular el numero de kilos pedidos.
		pesoUnidad = $("#pesoProducto_" + linea).val();
		precioKilo = $("#precioKilo_" + linea).val();
		precioUnidad = precioKilo * pesoUnidad;
		precioUnidad = redondearValor(precioUnidad, 100);
		$("#precioUnidad_" + linea).val(precioUnidad);
		calculaPrecioTotalLineaKilos(linea);
	}*/
}

function checkAutoPreciosLinea(linea){
	/*if ($("#checkAutoPrecios_" + linea).is(" :checked") == true)
		autocalcularPrecioUnidad(linea);*/
}

function kilosModificados(linea){
	calcularKilosTotales();
	autocalcularUnidades(linea);
	//Re-Calculamos el precio total de la linea
	calculaPrecioTotalLineaKilos(linea);
}

//MOSTRAR = 1 --> granel
function unidadesModificadas(linea, mostrar){
	calcularUnidadesTotales();
	autocalcularKilos(linea);
	calculaPrecioTotalLineaUnidades(linea, mostrar);
	//agrupacionesDePaquetesModificado(linea);
	//Damos valor a 'agrupaciones_' + linea
	unidades = $("#unidadesPedidas_" + linea).val();
	$("#unidadesAgrupaciones_" + linea).val(unidades);
	$("#agrupaciones_" + linea).val(1);
	$("#agrupacionesLinea_" + linea).val(1);
	calcularAgrupacionesTotales();
}

function precioKiloModificado(linea){
	autocalcularPrecioUnidad(linea);
	calculaPrecioTotalLineaKilos(linea);
}

//MOSTRAR = 1 --> granel
function precioUnidadModificado(linea, mostrar){
	//autocalcularPrecioKilo(linea);
	calculaPrecioTotalLineaUnidades(linea, mostrar);
}

function precioLineaModificado(){
	calcularImporteTotal();
}

function agrupacionesDePaquetesModificado(linea){
	//alert(linea);
	//Damos valor a 'agrupaciones_' + linea
	agrupaciones = 0;
	unidades = $("#unidadesPedidas_" + linea).val();
	//alert(unidades);
	unidadesEnCadaAgrupacion = $("#unidadesAgrupaciones_" + linea).val();
	//alert(unidadesEnCadaAgrupacion);
	if (unidadesEnCadaAgrupacion == 0)
		agrupaciones = 0;
	else
		if (unidadesEnCadaAgrupacion > 0)
			agrupaciones = unidades / unidadesEnCadaAgrupacion;
	//alert(agrupaciones);
	$("#agrupaciones_" + linea).val(agrupaciones);
	$("#agrupacionesLinea_" + linea).val(agrupaciones);
	calcularAgrupacionesTotales();
}

function calculaPrecioTotalLineaKilos(linea){
	/*precioKilo = $("#precioKilo_" + linea).val();
	numeroKilos = $("#cantidadPedida_" + linea).val();
	precioTotalLinea = precioKilo * numeroKilos;
	//alert(precioTotalLinea);
	$("#precioTotalLinea_" + linea).val(precioTotalLinea);
	calcularImporteTotal();*/
}

function calculaPrecioTotalLineaUnidades(linea, mostrar){
	cantidad = $("#unidadesPedidas_" + linea).val() * $("#EANs13Producto_" + linea).val();
	//alert(mostrar);
	peso = $("#pesoProducto_" + linea).val();
	uds = $("#unidadesPedidas_" + linea).val();
	pesoLinea = peso * uds;
	if (mostrar == "1"){
		//alert("entra");
		cantidad = pesoLinea;
	}
	precioUd = $("#precioUnidad_" + linea).val();
	//alert(precioUd);
	//alert(cantidad);
	precioTotalLinea = precioUd * cantidad;
	//alert(precioTotalLinea);
	precioTotalLinea = redondearValor(precioTotalLinea, 100);
	$("#precioTotalLinea_" + linea).val(precioTotalLinea);
	$("#pesoLinea_" + linea).val(pesoLinea);
	calcularImporteTotal();
	calcularKilosTotales();
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

function limpiarDireccionesUnidades(){
	var direcciones = $(".direccionesUnidades");
	direcciones = direcciones.get();
	cuantos = direcciones.length;
	//$.msg("Cuantas filas: " + cuantos);
	for (i = 0; i < cuantos; i++){
		elemento = direcciones[i];
		elemento.parentNode.removeChild(elemento);
	}
	var oculto = $("#entrega_-1");
	hijos = oculto.children();
	//$.msg(hijos.length);
	for (i = 0; i < hijos.length; i++){
		elemento = hijos[i];
		elemento.parentNode.removeChild(elemento);
	}
}

function aniadirDireccion(linea){
	var opcionSeleccionada = $("#dropdown_direcciones_" + linea).val();
	if (opcionSeleccionada != 0){
		//Mirar si la direccion no esta ya seleccionada
		direccionesSeleccionadas = $("#direcciones_" + linea).children().length;
		flag = true;
		//alert(direccionesSeleccionadas);
		//Para cada una de las direcciones que ya hay...
		for (i = 1; i <= direccionesSeleccionadas; i++){
			//... si la direccion seleccionada ya está añadida, flag a false y break, y no se añade
			//alert($("#inputDireccion_" + linea + "_" + i).val());
			//alert($("#entrega_" + linea + " :selected").val());
			//alert($("#inputDireccion_" + linea + "_" + i).val() + " - " + $("#entrega_" + linea + " :selected").val());
			if ($("#inputDireccion_" + linea + "_" + i).val() == $("#entrega_" + linea + " :selected").val()){
				flag = false;
				break;
			}
		}
		if (flag){
			var fila = document.createElement('tr');
			cuenta = $("#direcciones_" + linea).children().length + parseFloat(1);
			fila.setAttribute("id", "direccionUnidades_" + linea + "_" + cuenta);
			fila.setAttribute("class", "direccionesUnidades direccionesUnidades_" + linea);
			dirSeleccionada = $("#entrega_" + linea + " :selected").val();
			descripcionDirSeleccionada = $("#entrega_" + linea + " :selected").text();
			fila.innerHTML =
				'<td>' +
					'<p id="direccion_' + linea + '_' + cuenta + '" name="direccion_' + linea + '_' + cuenta + '" style="background: transparent !important" value="' + dirSeleccionada + '">' + descripcionDirSeleccionada + '</p>' +
					'<input style="display:none;" id="inputDireccion_' + linea + '_' + cuenta + '" class="direccionesLinea" />' +
				'</td>' +
				'<td style="width: 25px;">' +
					'<input id="unidades_' + linea + '_' + cuenta + '" name="unidades_' + linea + '_' + cuenta + '" style="width: 28px;" class="unidadesDireccion" onKeyPress="return validarSoloNumeros(event);" onchange="javascript:compruebaUnidadesUnicaDireccion(' + linea + ');" value="1"></input>' +
				'</td>' +
				'<td>' +
					'<a id="elimina_' + linea + '_' + (parseFloat(cuenta)) + '" href="javascript:eliminaDireccion(' + linea + ',' + (parseFloat(cuenta)) + ')" title="Eliminar esta direccion" style="vertical-align: middle;"><img src="img/cancel.png" alt="Eliminar esta direccion" title="Eliminar esta direccion"></a>' +
				'</td>';
			$("#direcciones_" + linea).append(fila);
			$('#inputDireccion_' + linea + '_' + cuenta).val(dirSeleccionada);
			if (cuenta == 0){
				$("#unidades_" + linea + "_0").val($("#agrupacionesLinea_" + linea).val());
			}
		}
	}
}

function direccionesNuevoProducto(linea){
	var opciones = $("#entrega_-1").children();
	//Copiamos las opciones en nuestra nueva linea
	for (i = 0; i < opciones.length; i++){
		id = opciones[i].id;
		//id = direccionClienteOculta_31
		$("#entrega_" + linea).append('<option value="' + $("#" + id).val() + '" title="' + $("#" + id).attr("title") + '">' + $("#" + id).text() + '</option>');
	}
}

function setDivisa(){
	selected = $("#dropdown_divisas").val();
	$("#cux").val(selected);
}

function seleccionCliente(limpia){
	var selected = $("#dropdown_clientes").val();
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

function validarFecha(idCampo){
	//alert(idCampo);
	fecha = $("#" + idCampo).val();
	frag = fecha.split('/');
	if (frag.length != 3){
		return false;
	}else{
		if (frag[0].length != 2 || !IsNumeric(frag[0])){
			return false;
		}else{
			if (frag[1].length != 2 || !IsNumeric(frag[1])){
				return false;
			}else{
				if (frag[2].length != 4 || !IsNumeric(frag[2])){
					return false;
				}else{
					return true;
				}
			}
		}
	}
}

function compruebaBultosBienRepartidos(){
	numeroLineas = $(".cantidades").length;
	if (numeroLineas > 0){
		for (i = 1; i <= numeroLineas; i++){
			direccionesLinea = $("#direcciones_" + i).children();
			cuantasDirecciones = direccionesLinea.length;
			sumaBultos = 0;
			for (j = 0; j < cuantasDirecciones; j++){
				sumaBultos += parseFloat($("#unidades_" + i + "_" + j).val());
			}
			todosBultosLineas = $(".bultos");
			cuantosHay = todosBultosLineas.length;
			b = todosBultosLineas.get(i - 1);
			bultosPedidos = b.value;
			//alert("bultos pedidos: " + bultosPedidos);
			//alert("suma bultos: " + sumaBultos);
			if (bultosPedidos != sumaBultos){
				$.msg("La suma de las agrupaciones EDI que van a cada destino debe ser igual al numero de agrupaciones EDI pedidas para la linea (Linea numero " + i + ")");
				return false;
			}
		}
	}
	return true;
}

function eliminaDireccion(linea, direccion){
	total = $("#direcciones_" + linea).children().length;
	for (i = direccion + 1; i < total; i++){
		$("#elimina_" + linea + "_" + i).attr("href", "javascript:eliminaDireccion(" + linea + "," + (i - 1) + ");");
		$("#elimina_" + linea + "_" + i).attr("id", "elimina_" + linea + "_" + (i - 1));
		$("#direccionUnidades_" + linea + "_" + i).attr("id", "direccionUnidades_" + linea + "_" + (i - 1));
		$("#direccion_" + linea + "_" + (i)).attr("name", "direccion_" + linea + "_" + (i - 1));
		$("#direccion_" + linea + "_" + i).attr("id", "direccion_" + linea + "_" + (i - 1));
		$("#unidades_" + linea + "_" + (i)).attr("name", "unidades_" + linea + "_" + (i - 1));
		$("#unidades_" + linea + "_" + i).attr("id", "unidades_" + linea + "_" + (i - 1));
	}
	$("#direccionUnidades_" + linea + "_" + (direccion)).remove();
	//Al final, comprobar si solo queda una dirección. Si solo queda una, asignarle a ella todos los bultos.
	if ($("#direcciones_" + linea).children().length == 1)
		$("#unidades_" + linea + "_0").val($("#agrupaciones_" + linea).val());
}

function compruebaUnidadesUnicaDireccion(linea){
	if ($("#direcciones_" + linea).children().length == 1){
		//Una sola direccion...
		unidadesPedidas = $("#unidadesPedidas_" + linea).val();
		//alert(unidadesPedidas);
		bultosIntroducidos = $("#unidades_" + linea + "_0").val();
		//alert(bultosIntroducidos);
		//$.msg("Bultos pedidos: " + unidadesPedidas + ". Bultos introducidos: " + bultosIntroducidos);
		if (unidadesPedidas != bultosIntroducidos){
			//$.msg("Las " + unidadesPedidas + " unidades pedidas deben de ir a la unica direccion introducida");
			//$("#unidades_" + linea + "_0").val($("#unidadesPedidas_" + linea).val());
		}
	}
}

function cerrar(){
	$("#hiddens").append($("#contenedorProductos"));
	$("#contenedorProductos").hide();
	$("#bot_aceptarProducto").hide();
	$("#breadcrum_grupo_seleccionar").show();
	$(".temp").remove();
	$('#fondo').remove();
	document.getElementsByTagName('body')[0].removeChild(mensaje);
	fondo = false;
	mensaje = false;
}

function addProductoPedido(){
	//Abrir ventana donde se pregunte: Producto terminado o granel
	/*fondo = document.createElement('div');
	mensaje = document.createElement('div');
	fondo.setAttribute('id','fondo');
	mensaje.setAttribute('id','msg');
	$('body').append(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
	$("#fondo").height($(document).height());
	mensaje.innerHTML =
		'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar();">X</span></div>' +
		'<p style="font-size: 15px; text-align: center;">AGREGAR PRODUCTO AL PEDIDO</p>' +
		'<p style="font-size: 15px; text-align: center;">' +
			'<button id="bot_productoFinal" class="i_plus icon yellow" onclick="javascript:listaProductosPedido(1);">Producto final</button>' +
			'<button id="bot_granel" class="i_plus icon yellow" onclick="javascript:listaProductosPedido(2);">Granel</button>' +
		'</p>';*/
	listaProductosPedido(1);
}

function listaProductosPedido(tipoProducto){
	//cerrar();
	//alert(tipoProducto);
	if (tipoProducto == 1){//Producto final
		fondo = document.createElement('div');
		mensaje = document.createElement('div');
		fondo.setAttribute('id','fondo');
		mensaje.setAttribute('id','msg');
		$('body').append(fondo);
		document.getElementsByTagName('body')[0].appendChild(mensaje);
		$("#fondo").height($(document).height());
		mensaje.innerHTML =
			'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar();">X</span></div>' +
			'<p style="font-size: 15px; text-align: center;">Seleccione el producto</p>';
		$("#msg").append($("#contenedorProductos"));
		$("#contenedorProductos").show();
	}else
		if (tipoProducto == 2){//Granel
		}
}