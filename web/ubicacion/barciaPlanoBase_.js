var numeroPalets;
var paletActual = 1;
var sacar = false;
var meter = false;
var mover = false;
var incidencia = false;
var punto = false;

$(document).ready(function() {
	//alert(1);
	$('#confirmarUbicacion').hide();
	$('#reubicar').hide();
	$('#confirmarSacar').hide();
	if ($('#incidencia').val() == 'true'){
		incidencia = true;
	}
	meter = $('#meter').val();
	mover = $('#mover').val();
	sacar = $('#sacar').val();
	if (meter == 'true'){
		$('#tablaGeneral').show();
	}else{
		$('#tablaGeneral').hide();
		if ($('#sacar').val() == 'true'){
			sacar = true;
			$('#tablaGeneral').hide();
		} else{
			$('#confirmarSacar').hide();
		}
	}
	if (sacar == "true")
		$('#confirmarSacar').show();
	else
		$('#confirmarSacar').hide();
	//$.unblockUI();
});

function seleccionLinea(zona, linea, lineaAbsoluta, nombreLinea){
	$("#idZona").val(zona);
	$("#idLinea").val(lineaAbsoluta);
	$url = "SeleccionLineaAjax.action?idAlmacen=1&idZona=" + zona + "&idLineaZona=" + linea + "&idLinea=" + lineaAbsoluta;
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		//$.msg("oook");
		$("#widget_consUbica").empty();
		$("#widget_consUbica").append(html);
		//$.msg("ya hizo el anden");
	 }
	});
	$("#btreubicar").show();
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function seleccionBigBag(){
	//$.msg("seleccionBigBag");
	/*indice = document.formulario.bigBags.selectedIndex;
	valor = document.formulario.bigBags.options[indice].value;
	textoEscogido = document.formulario.bigBags.options[indice].text;*/
	valor = $("#dropdown_bigbags").val();
	$(".idHueco").val(valor);
	//$.msg($(".idHueco").val());
	//$.msg(valor);
	$("#ubicacionBigBag").val(true);
	$url = "SeleccionHueco.action?idZona=6&idLinea=11&idLineaZona=1&idAlmacen=1&registro=" + $("#registro").val() + "&cuantosPalets" + $("#cuantosPalets").val() + "&idHueco=" + $("#dropdown_bigbags").val();
	//$.msg($url);
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			//$.msg("oook");
			$("#widget_consUbica").empty();
			$("#widget_consUbica").append(html);
			//$.msg("ya hizo el anden");
		}
	});
	meter = $('#meter').val();
	sacar = $('#sacar').val();
	//$.msg(sacar);
	if ($("#incidencia").val() == "true"){
		//$.msg("Incidencia");
		$("#registrosActuales").hide();
		$("#btConfirmarIncidencia").show();
		$("#contenedorSacar").hide();
		$("#contenedorIncidencia").show();
	}else{
		//Si estamos moviendo...
		if ($("#mover").val() == "true"){
			$("#contenedorSacar").show();
			$("#registrosActuales").hide();
			$("#btConfirmarSacar").show();
			$("#btConfirmarUbicacion").hide();
			//$.msg("moviendo");
		}else
			if ($("#meter").val() == "true"){
				$("#registrosActuales").show();
				$("#btConfirmarUbicacion").show();
			}
		//$.msg("sacar: " + sacar);
		if (sacar == "true" || sacar == true){
			//$('#btConfirmarSacar').show();
			$("#contenedorIncidencia").hide();
			$('#btConfirmarSacar').show();
			//$.msg("entro en sacar = true");
		}else{
			$('#btConfirmarSacar').hide();
			$("#contenedorSacar").hide();
		}
		//$.msg(meter);
		if (meter == "true")
			$('#btConfirmarUbicacion').show();
		else
			$('#btConfirmarUbicacion').hide();
	}
	//$.msg($("#gestionBultos").val());
	if ($("#gestionBultos").val() == "true"){
		$("#indicencia").val(false);
		$("#btConfirmarUbicacion").show();
		//$.msg($("#btConfirmarUbicacion").length);
		//$.msg("adios");
	}
	if ($("#ubicar").val() == "true"){
		$("#registrosActuales").show();
		$("#btConfirmarUbicacion").show();
	}
	//$.msg("ciao");
}

function seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro){
	//$.msg($("#reubicar").val());
	//$.msg($("#seleccionado").val());
	refHueco = "A" + $("#idAlmacen").val() + ":Z" + $("#idZona").val() + ":L" + linea + ":E" + estanteria + ":P" + piso + ":H" + hueco;
	$("#tooltip0").remove();
	$('#idEstanteria').val(estanteria);
	$('#idPiso').val(piso);
	$('.idHueco').val(huecoAbsoluto);
	$('#idHuecoPiso').val(hueco);
	registro = $("#registro").val();
	$('.registro').val(registro);
	//$.msg("6" + $("#cantidad").val());
	//$('#formulario_0').trigger('click');
	//SeleccionHueco
	$url = "SeleccionHueco.action?idHueco=" + huecoAbsoluto;
	//$.msg($url);
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			cantidad = $("#cantidad").val();
			//$.msg($("#cantidad").val());
			$("#widget_consUbica").empty();
			//$.msg($("#cantidad").val());
			$("#widget_consUbica").append(html);
			//$.msg($("#cantidad").val());
			//$.msg(html,{live:3000});
			$("#cantidad").val(cantidad);
			$("#registrosActuales").show();
			$('#tablaActual').dataTable({
				"oLanguage": {
					"sUrl": "languages/dataTable/es_ES.txt"
				},
				"aaSorting": [[ 0, 'asc' ]],
				"aoColumns": [
					null,
					null,
					null,
					{ "sType": "uk_date"},
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
					null,
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
					null,
					null,
					null
				],
				"sPaginationType": "full_numbers"
			});
			//$.msg("hola");
		}
	});
	//alert(1);
	/*$.msg("7" + $("#cantidad").val());
	$.msg($("#cantidad").val());*/
	//$.msg(registro);
	$('#seleccionado').val(huecoAbsoluto + "__" + refHueco + "__" + registro);
	//$.msg($('#seleccionado').val());
	$('.nombreHueco').val(refHueco);
	meter = $('#meter').val();
	sacar = $('#sacar').val();
	//alert($("#incidencia").val());
	if ($("#incidencia").val() == "true"){
		//$.msg("Incidencia");
		$("#registrosActuales").hide();
		$("#btConfirmarIncidencia").show();
		$("#contenedorSacar").hide();
		$("#contenedorIncidencia").show();
	}else{
		//Si estamos moviendo...
		if ($("#mover").val() == "true"){
			$("#contenedorSacar").show();
			$("#registrosActuales").hide();
			$("#btConfirmarSacar").show();
			//$.msg("moviendo");
		}else
			if ($("#meter").val() == "true"){
				$("#registrosActuales").show();
				$("#btConfirmarUbicacion").show();
			}
		//$.msg("sacar: " + sacar);
		if (sacar == "true" || sacar == true){
			//$('#btConfirmarSacar').show();
			$("#contenedorIncidencia").hide();
			$('#btConfirmarSacar').show();
			$("#contenedorSacar").show();
			//$.msg("entro en sacar = true");
		}else
			$('#btConfirmarSacar').hide();
		//$.msg(meter);
		if (meter == "true"){
			$('#btConfirmarUbicacion').show();
			$("#contenedorSacar").hide();
			$('#btConfirmarSacar').hide();
		}
		else
			$('#btConfirmarUbicacion').hide();
	}
	//$.msg($("#gestionBultos").val());
	if ($("#gestionBultos").val() == "true"){
		$("#indicencia").val(false);
		$("#btConfirmarUbicacion").show();
		//$.msg($("#btConfirmarUbicacion").length);
		//$.msg("adios");
	}
	if ($("#ubicar").val() == "true" || $("#reubicar").val() == "true"){
		$("#registrosActuales").show();
		$("#btConfirmarUbicacion").show();
	}
	//$.msg($("#reubicar").val());
	//$.msg($("#cantidad").val());
}

function muestraAlmacenados(idHueco){
	//$.msg("Hola, mostrando los almacenados...");
	//$('#idHuecoMuestra').val(idHueco);
	//$('#submitMuestra').trigger('click');
	//MuestraAlmacenadosAjax
	$url = "MuestraAlmacenadosAjax.action?idHuecoMuestra=" + idHueco;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#ajaxMuestra").empty();
			$("#ajaxMuestra").append(html);
			//$.msg(html,{live:3000});
		}
	});
}

function confirmar(){
	//alert("holaaa");
	var $url = "SalvaDatosUbicacion.action?reubicar=" + $("#reubicar").val() + "&seleccion=" + $("#seleccion").val() +
		"&ubicar=" + $("#ubicar").val() + "&idEnvasado=" + $("#idEnvasado").val() + "&orden=" + $("#orden").val() +
		"&procesoSeleccion=" + $("#procesoSeleccion").val() + "&tipoProceso=" + $("#tipoProceso").val() +
		"&estado=" + $("#estado").val() + "&idUbicacion=" + $("#idUbicacion").val() +
		"&seleccionado=" + $("#seleccionado").val() + "&ubicacionBigBag=" + $("#ubicacionBigBag").val() +
		"&idPalet=" + $("#idPalet").val() + "&idHueco=" + $("#idHueco").val() + "&registro=" + $("#registro").val() +
		"&proceso=" + $("#registro").val() +
		"&nombreHueco=" + $("#nombreHueco").val() + "&cantidad=" + $("#cantidad").val() + "&numeroBultos=" + $("#numeroBultos").val() + "&gestionBultos=" + $("#gestionBultos").val() + "&devolucion=" + $("#devolucion").val() + "&lote=" + $("#lote").val();
	//+ "&lote=" + 
	//Ejecutamos el ajax
	//alert($url);
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			if ($("#gestionBultos").val() == "true"){
				$("#indicencia").val(false);
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
						volverAlEscritorio();
					}else{
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
												}else{
													//alert("ni idea");
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

function dibujarOcupados(linea){
	$('#reubicar').show();
	huecosOcupados = $(".ocupado");
	//$.msg(huecosOcupados.length);
	for (i = 0; i < huecosOcupados.length; i++){
		//$.msg(huecosOcupados[i]);
		//Creamos la imagen
		var imgPalet = document.createElement('img');
		// Se le añade un id
		imgPalet.setAttribute("id", "imgOcupado_" + i);
		// y la ruta de la imagen
		imgPalet.setAttribute("src", "img/planos/palet.png;");
		//Id del hueco
		id = huecosOcupados[i].value;
		//$.msg(id);
		imageUrl = "img/planos/palet.png";
		$('#' + id).css('background-image', 'url(' + imageUrl + ')');
		$('#' + id).css('background-repeat', 'no-repeat');
	}
}

function simple_tooltip(target_items, name){
	//$.msg("Simple too");
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
					"'><span>El hueco " + $("#descripcionHueco").val() + " <b>no contiene registros</b></span>";
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

function infoHuecoCargada(){
		$('#tablaActual').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			null,
			null,
			{ "sType": "uk_date" },
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});

	//$.msg("hiiirr");
	if (incidencia){
		//$.msg("sacando");
		$('#confirmarSacar').show();
		$('#contenedorIncidencia').show();
		$('#contenedorSacar').hide();
	}else
		if (sacar){
			//$.msg("sacando");
			$('#contenedorSacar').show();
		}else{
			$('#contenedorSacar').hide();
			//$.msg($('#canti').val());
			$('#cantidad').val($('#canti').val());
		}
	/*$.msg("sacar: " + sacar);
	$.msg("mover: " + mover);
	$.msg("meter: " + meter);*/
	if (mover == "true"){
		$('#confirmarSacar').show();
		$('#contenedorSacar').show();
	}else
		if (meter == "true"){
		$('#confirmarUbicacion').show();
	}
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
						alert($url);
						$url += "&observacionIncidencia=" + $("#observacionIncidencia").val();
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
						id = sacando[i].id;
						frag = id.split("_");
						
						lote = frag[1];
						aux = lote.split(" ");
						if (aux.length > 1)
							lote = aux[0];
						//alert(lote);
						$("#registro").val(lote);
					
						//$.msg(sacando[i].name);
						var cadenaSacar = sacando[i].name + "=" + sacando[i].value;
						$url += "&" + cadenaSacar;
						//Ejecutamos el ajax, ya tenemos la url completa
						//$.msg($url);
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
								
								frag = sacando[i].name.split("__");
								//idPalet_E111128-58__500__1
								palet = frag[2];
								$("#idPalet").val(palet);
								//$.msg($("#idPalet").val());
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