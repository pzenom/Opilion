var numeroPalets;
var paletActual = 1;
var sacar = false;
var mover = false;
var meter = false;
var incidencia = false;
var punto = false;

$(document).ready(function() {
	$('#tablaActual').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			null,
			null,
			null,
			{ "bVisible": false},
			{ "bVisible": false},
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	$('#tablasacar').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			null,
			null,
			null,
			{ "bVisible": false},
			{ "bVisible": false},
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	$('#tablasacarIncidencia').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			null,
			null,
			null,
			{ "bVisible": false},
			{ "bVisible": false},
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	meter = $('#meter').val();
	mover = $('#mover').val();
	$('#reubicar').hide();
	$('#btConfirmarSacar').hide();
	$('#contenedorSacar').hide();
	$('#btConfirmarUbicacion').hide();
	$('#tablaGeneral').hide();
	$('#registrosActuales').show();
	if ($('#incidencia').val() == 'true'){
		incidencia = true;
		$('#btConfirmarUbicacion').hide();
		$('#contenedorIncidencia').show();
		$('#contenedorSacar').hide();
		$("#btConfirmarIncidencia").show();
		$('#registrosActuales').hide();
		//alert(1);
	}
	if ($('#meter').val() == 'true'){
		$("#btConfirmarUbicacion").show();
		$('#tablaGeneral').show();
		$('#btConfirmarUbicacion').show();
		$('#contenedorSacar').hide();
		$('#registrosActuales').show();
		//alert(2);
	}else{
		if ($('#sacar').val() == 'true'){
			sacar = true;
			$('#tablaGeneral').hide();
			$('#btConfirmarSacar').show();
		} else{
			$('#btConfirmarSacar').hide();
		}
	}
	if ($("#gestionBultos").val() == "true")
		$('#btConfirmarUbicacion').show();
	$("#seleccionado").val($("#idAlmacen").val());
	$("#idHueco").val($("#hueco").val());
	if (sacar == "true"){
		$('#btConfirmarSacar').show();
		$('#contenedorSacar').show();
		$('#registrosActuales').hide();
	}else{
		if (mover == "true"){
			$('#btConfirmarSacar').show();
			$('#contenedorSacar').show();
			$('#registrosActuales').hide();
		}else{
			/*$('#registrosActuales').show();
			alert(3);*/
		}
	}
	
	//Mostrar el boton de reporte de vehiculo
	$('#bot_reporte').show();
	$('#bot_reporte').attr('onclick', 'javascript:reporteVehiculo(' + $('#idHueco').val() + ');');
	
	$('#bot_lanzadera').show();
	//$('#bot_lanzadera').attr('onclick', 'javascript:lanzadera(' + $('#idHueco').val() + ');');
	
});

function gestionLanzaderas(){
	$.ajax({
		url: "ubicacion/lanzaderasPrincipal.jsp",
		cache: false,
		async:false,
		success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
			$('#idVehiculoLanzadera').val($('#idHueco').val());
			$.ajax({
				type: "GET",
				url: "ubicacion/lanzaderasPrincipal.js",
				dataType: "script"
			});
		}
	});
}

function reporteVehiculo(idHuecoVehiculo){
	//alert(idHuecoVehiculo);
	action = 'ReporteVehiculo.action?idVehiculo=' + idHuecoVehiculo;
	parent.get_ventana_emergente('VEHICULO', action, 'no', 'no', '800', '640','','');
}

function seleccionLinea(zona, linea, lineaAbsoluta, nombreLinea){
	$('#zona').val(zona);
	$('#idLineaZona').val(linea);
	$('#idLinea').val(lineaAbsoluta);
	$('#formuAjax_0').trigger('click');
}

function muestraAlmacenados(idHueco){
	$('#idHuecoMuestra').val(idHueco);
	$('#submitMuestra').trigger('click');
}

function confirmar(){
	var $url = "SalvaDatosUbicacion.action?reubicar=" + $("#reubicar").val() + "&seleccion=" + $("#seleccion").val() +
		"&ubicar=" + $("#ubicar").val() + "&idEnvasado=" + $("#idEnvasado").val() + "&orden=" + $("#orden").val() +
		"&procesoSeleccion=" + $("#procesoSeleccion").val() + "&tipoProceso=" + $("#tipoProceso").val() +
		"&estado=" + $("#estado").val() + "&lote=" + $("#lote").val() + "&idUbicacion=" + $("#idUbicacion").val() +
		"&seleccionado=" + $("#seleccionado").val() + "&ubicacionBigBag=" + $("#ubicacionBigBag").val() +
		"&idPalet=" + $("#idPalet").val() + "&idHueco=" + $("#hueco").val() + "&registro=" + $("#registro").val() +
		"&nombreHueco=" + $("#nombreHueco").val() + "&cantidad=" + $("#cantidad").val() + "&numeroBultos=" + $("#numeroBultos").val() +
		"&gestionBultos=" + $("#gestionBultos").val() + "&ubicacionVehiculo=true" + "&devolucion=" + $("#devolucion").val();;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			//$.msg("ubicado");
			if ($("#gestionBultos").val() == "true"){
				//$.msg("bultos");
				//$.msg("entra");
				//Mirar si estamos en ordenes o en registros
				clase = $("#btMenuRE").attr("class");
				if (clase.split("active").length > 1){//RE
					$("#widget_consUbica").attr("id", "widget_consRE");
					$("#widget_consRE").empty();
					$("#widget_consRE").append(html);
				}else{//OE
					$("#widget_consUbica").attr("id", "widget_consOE");
					$("#widget_consOE").empty();
					$("#widget_consOE").append(html);
				}
				$.ajax({
					type: "GET",
					url: "registroentrada/bultosRE.js",
					dataType: "script"
				});
			}else{
				if ($("#btMenuDevoluciones").hasClass('active') == true){
					if ($("#widget_consUbica").length > 0){
						$("#widget_consUbica").attr("id", "widget_consDevol");
					}
					consDevol();
				}else{
					if ($("#btMenuAlmacenes3").hasClass('active') == true){
						//alert(1);
						//Estamos en gestiones del almacen (mermas, movimientos...)
						//$.msg("gestion almacenes");
						//widget_consUbica
						//$("html").empty();
						//$("html").append(html);
						volverAlEscritorio();
					}else{
						//$.msg("no bultos");
						//$.msg($("#reubicar").val());
						if ($("#reubicar").val() == "true" || $("#reubicar").val() == true){
							//$.msg("no bultos -> reubicar");
							if ($("#widget_consUbica").length > 0){
								$("#widget_consUbica").attr("id", "widget_consGPEnva");
							}
							$("#widget_consGPEnva").empty();
							$("#widget_consGPEnva").append(html);
							$.ajax({
								type: "GET",
								url: "gpenvasado/visualizarProceso.js",
								dataType: "script"
							});
						}else{
							//$.msg("no bultos -> no reubicar");
							if ($("#ubicar").val() == "true" || $("#ubicar").val() == true){
								//$.msg("no bultos -> ubicar");
								if ($("#tipoProceso").val() == "Fumigado"){
									//$.msg("no bultos -> ubicar -> Fumigado");
									if ($("#widget_consUbica").length > 0){
										$("#widget_consUbica").attr("id", "widget_consGPFumi");
									}
									$("#widget_consGPFumi").empty();
									$("#widget_consGPFumi").append(html);
									$.ajax({
										type: "GET",
										url: "ubicacion/ubicarProceso.js",
										dataType: "script"
									});
									//$.msg("FIN: no bultos -> ubicar -> Fumigado");
								}else{
									if ($("#tipoProceso").val() == "Congelado"){
										//$.msg("no bultos -> ubicar -> Congelado");
										if ($("#widget_consUbica").length > 0){
											$("#widget_consUbica").attr("id", "widget_consGPCong");
										}
										$("#widget_consGPCong").empty();
										$("#widget_consGPCong").append(html);
										$.ajax({
											type: "GET",
											url: "ubicacion/ubicarProceso.js",
											dataType: "script"
										});
										//$.msg("FIN: no bultos -> ubicar -> Congelado");
									}else{
										if ($("#tipoProceso").val() == "Cribado"){
											//$.msg("no bultos -> ubicar -> Cribado");
											if ($("#widget_consUbica").length > 0){
												$("#widget_consUbica").attr("id", "widget_consGPCrib");
											}
											$("#widget_consGPCrib").empty();
											$("#widget_consGPCrib").append(html);
											$.ajax({
												type: "GET",
												url: "ubicacion/ubicarProceso.js",
												dataType: "script"
											});
											//$.msg("FIN: no bultos -> ubicar -> Cribado");
										}else{
											if ($("#tipoProceso").val() == "Seleccion"){
												//$.msg("no bultos -> ubicar -> Seleccion");
												if ($("#widget_consUbica").length > 0){
													$("#widget_consUbica").attr("id", "widget_consGPSele");
												}
												$("#widget_consGPSele").empty();
												$("#widget_consGPSele").append(html);
												$.ajax({
													type: "GET",
													url: "ubicacion/ubicarProceso.js",
													dataType: "script"
												});
												//$.msg("FIN: no bultos -> ubicar -> Seleccion");
											}else{
												if ($("#tipoProceso").val() == "Desgranado"){
													//$.msg("no bultos -> ubicar -> Desgranado");
													if ($("#widget_consUbica").length > 0){
														$("#widget_consUbica").attr("id", "widget_consGPDesg");
													}
													$("#widget_consGPDesg").empty();
													$("#widget_consGPDesg").append(html);
													$.ajax({
														type: "GET",
														url: "ubicacion/ubicarProceso.js",
														dataType: "script"
													});
													//$.msg("FIN: no bultos -> ubicar -> Desgranado");
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			esconderBotones();
			$.msg("Ubicado correctamente",{live:7000});
			$("#meter").val("false");
			$("#sacar").val("false");
			$("#reubicar").val("false");
			$("#mover").val("false");
			$("#gestionBultos").val("false");
		}
	});
}

function simple_tooltip(target_items, name){
	$('#' + target_items).each(function(i){
		if ($("#" + name + i).length > 0)
			$("#" + name + i).remove();
		//$.msg(target_items);
		//$.msg($("#ocupado_" + target_items));
		if ($("#ocupado_" + target_items).length > 0)
			html = "<div style='font-size:14px' class='" + name + "' id='" + name + i +
				"'><span>Registros almacenados en " + $("#descripcionHueco").val() + "</span>";
		else
			html = "<div style='font-size:14px' class='" + name + "' id='" + name + i +
					"'><span>El hueco " + $("#descripcionHueco").val() + " <b>no contiene ningún registro</b></span>";
		almacenados = $(".almacenado");
		//$.msg(almacenados + " -- " + almacenados.length);
		for (j = 0; j < almacenados.length; j++){
			//$.msg(almacenados[i].value);
			html += "<p>" + almacenados[j].value + "</p>";
		}
		html += "</div>";
		//$.msg(html);
		$('body').append(html);
		var my_tooltip = $("#" + name + i);
		$(this).removeAttr("title").mouseover(function(){
				my_tooltip.css({opacity:0.8, display:"none"}).fadeIn(100);
		}).mousemove(function(kmouse){
				my_tooltip.css({left:kmouse.pageX+15, top:kmouse.pageY+15});
		}).mouseout(function(){
				my_tooltip.remove();//.fadeOut(400);
		});
	});
}

function sacarRegistros(){
	//$.msg("sacabdo");
	if(incidencia){
		//$.msg("hi");
		sacando = $('.mermando');
		contador = 0;
		for (i = 0; i < sacando.length; i++){
			if (parseFloat(sacando[i].value) > 0)
				contador++;
			if (contador > 1)
				break;
		}
		if (contador > 1){
			$.msg("Solo se puede generar una incidencia sobre un registro de cada vez. Seleccione únicamente unidades para un registro");
		} else
			if (contador == 0){
				$.msg("Seleccione al menos unidades para un registro");
			} else{
				//document.formuSacar.submit();
				//Con ajax, marcar la incidencia deseada del registro seleccionado
				//+ "&idPalet=" + $("#idPalet").val()
				var $url = "SalvaDatosSacarUbicacion.action?idHueco=" + $("#idHueco").val() + "&mover=false&incidencia=true&ubicar=false&reubicar=false";
				//$.msg("Sacando!!");
				for (i = 0; i < sacando.length; i++){
					if (parseFloat(sacando[i].value) > 0){
						//Encontramos el registro a sacar
						//$.msg(sacando[i].name);
						var cadenaSacar = sacando[i].name + "=" + sacando[i].value;
						$url += "&" + cadenaSacar;
						$url += "&observacionIncidencia=" + $("#observacionIncidencia").val() + "&ubicacionVehiculo=true";
						//Ejecutamos el ajax, ya tenemos la url completa
						$.ajax({
							url: $url,
							cache: false,
							async:false,
							success: function(html){
								$("#widget_consUbica").empty();
								$("#widget_consUbica").append(html);
								$("#meter").val("false");
								$("#mover").val("false");
								$("#reubicar").val("false");
								$("#sacar").val("false");
								$("#btvolver").hide();
								$("#btreubicar").hide();
								$("#btConfirmarIncidencia").hide();
							}
						});
						$.ajax({
							type: "GET",
							url: "ubicacion/consultaIncidencias.js",
							dataType: "script"
						});
						break;
					}
				}
			}
	}
	else{
		sacando = $('.sacando');
		contador = 0;
		for (i = 0; i < sacando.length; i++){
			if (parseFloat(sacando[i].value) > 0)
				contador++;
			if (contador > 1)
				break;
		}
		if (contador > 1){
			$.msg("Solo se puede mover un registro de cada vez. Seleccione únicamente unidades para un registro");
		} else
			if (contador == 0){
				$.msg("Seleccione al menos unidades para un registro");
			} else{
				//document.formuSacar.submit();
				//Con ajax, sacar la cantidad deseada del registro seleccionado
				//+ "&idPalet=" + $("#idPalet").val()
				var $url = "SalvaDatosSacarUbicacion.action?idHueco=" + $("#idHueco").val() + "&mover=" + $("#mover").val() + "&incidencia=" + $("#incidencia").val();
				//$.msg("Sacando!!");
				for (i = 0; i < sacando.length; i++){
					if (parseFloat(sacando[i].value) > 0){
						//Encontramos el registro a sacar
						//$.msg(sacando[i].name);
						id = sacando[i].id;
						frag = id.split("_");
						
						lote = frag[1];
						aux = lote.split(" ");
						if (aux.length > 1)
							lote = aux[0];
						//alert(lote);
						
						cantidadActual = $('#span_cantidad_lote_' + lote).text();
						//alert(cantidadActual);
						if (cantidadActual < parseFloat(sacando[i].value)){
							$.msg("No puedes sacar tanta cantidad");
							return;
						}					
						
						$("#registro").val(lote);
						var cadenaSacar = sacando[i].name + "=" + sacando[i].value;
						$url += "&" + cadenaSacar + "&ubicacionVehiculo=true";
						//$.msg($url);
						//Ejecutamos el ajax, ya tenemos la url completa
						$.ajax({
							url: $url,
							cache: false,
							async:false,
							success: function(html){
								$("#widget_consUbica").empty();
								$("#widget_consUbica").append(html);
								$("#meter").val("true");
								$("#mover").val("false");
								$("#reubicar").val("false");
								$("#sacar").val("false");
								$("#cantidad").val(sacando[i].value);
							}
						});
						$.ajax({
							type: "GET",
							url: "ubicacion/almacenesPrincipal.js",
							dataType: "script"
						});
						break;
					}
				}
			}
	}
}

function compruebaCantidad(registro){
	//$.msg("compruebaCantidad");
	//$.msg(registro);
	//$.msg($("#" + registro).val());
	if ($("#" + registro).val() == "")
		$("#" + registro).val(0);
}

function trazar(lote){
	frag = lote.split(" (");
	lote = frag[0];
	//alert(lote);
	$.ajax({
		url: "ConsultaTrazaProducto.action?lote=" + lote,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consUbica").empty();
			$("#widget_consUbica").append(html);
			/* Configuramos la botonera */
			$(".botonBotonera").hide();
			$("#bot_vuelve").attr("onclick", "javascript:muestraAlmacen();");
			$("#bot_vuelve").show();
			/* Formateo las fechas que llegan de la forma aaaa-mm-dd */
			fechas = $('.fechas').get();
			cuantas = fechas.length;
			for (i = 0; i < cuantas; i++){
				id = fechas[i].id;
				fecha = $('#' + id).val();
				//alert(fecha);
				frag = fecha.split('-');
				fechaNueva = '';
				fechaNueva = frag[2] + "/" + frag[1] + "/" + frag[0];
				$('#' + id).val(fechaNueva);
			}
			if ($('.informacionEnvasado').get().length == 0){
				$('#fieldsetInformacionEnvasado').hide();
			}
			if ($('.informacionVenta').get().length == 0){
				$('#fieldsetInformacionVenta').hide();
			}
			if ($('.informacionDevoluciones').get().length == 0){
				$('#fieldsetInformacionDevoluciones').hide();
			}
		}
	});
}