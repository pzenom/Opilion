$(document).ready(function() {
	//$.msg("hola");
	//Deshabilitamos los input que ya estan ubicados
	inputs = $(".ubicacionFinal").get();
	contador = 0;
	//$.msg(inputs.length);
	for (i = 0; i < inputs.length; i++){
		longitud = $("#" + inputs[i].id).val().length;
		//$.msg($("#" + inputs[i].id).val());
		//$.msg(longitud);
		id = inputs[i].id;
		frag = id.split("ubicacion_");
		//$.msg(frag[1].split("_")[0]);
		if ($("#" + frag[1]).val() == "" || $("#" + frag[1]).val() == 0)
				$("#" + frag[1]).val($("#cuanto_" + frag[1].split("_")[0]).val());
		if ($("#" + inputs[i].id).val() != "Entrada" && longitud > 0){
			//$.msg("Ubicado");
			//$("#" + inputs[i].id).attr("disabled", "");
			id = inputs[i].id;
			//$.msg(id);
			//$.msg(frag[0]);
			//$.msg(frag[1]);
			//$("#" + frag[1]).attr("disabled", "");
			$("#fila_" + frag[1]).attr("ubicado", "SI");
			//$.msg($("#fila_" + frag[1]).attr("ubicado"));
		} else{
			$("#" + inputs[i].id).removeAttr("disabled");
			$("#" + frag[1]).removeAttr("disabled");
			//$("#" + frag[1]).attr("disabled", "");
		}
		contador++;
	}
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
});

function seleccionaAlmacen(){
	  indice = document.almac.almacenes.selectedIndex;
		//if (indice > 0){
			valor = document.almac.almacenes.options[indice].value;
			textoEscogido = document.almac.almacenes.options[indice].text;
			$("#idTipoUbicacion").val(valor);
			//$.msg($("#idTipoUbicacion").val());
		//}
}

function almacenSeleccionado(){
	/*if ($("#idTipoUbicacion").val() > 0){
		document.almac.submit();
	}*/
}

function addUbicacion(orden, indiceOrden){
	//$.msg(indiceOrden);
	cuantos = $("#body_" + indiceOrden).children().length;
	cuantos = $("#fila_0_0").siblings().length;
	cuantos = $(".grupo_" + indiceOrden).length;
	//$.msg(cuantos);
	cadena = "'" + orden + "'";
	//$.msg(cadena);
	//$.msg(cuantos);
	//$.msg(("#fila_" + indiceOrden + "_" + (parseFloat(cuantos) - 1)));
	total = 0;
	cantidades = $(".cantidadesUbicar_" + indiceOrden);
	for (i = 0; i < cantidades.length; i++){
		total += parseFloat(cantidades[i].value);
	}
	//$.msg(total);
	if ($("#fila_" + indiceOrden + "_" + (parseFloat(cuantos) - 1)).attr("ubicado") == "SI"){
		diferencia = parseFloat($("#cuanto_" + indiceOrden).val()) - parseFloat(total);
		if (diferencia > 0){
			var nuevaFila = document.createElement("tr");
			// Crear la fila del envase usada
			id = "fila_" + (parseFloat(indiceOrden)) + "_" + (parseFloat(cuantos));
			nuevaFila.setAttribute("id", id);
			nuevaFila.setAttribute("style", "height: 28px;");
			nuevaFila.setAttribute("class", "grupo_" + indiceOrden);
			nuevaFila.setAttribute("ubicado", "NO");
			nuevaFila.innerHTML =
				'<td style="font-size: 20px;">' +
					'<input style="text-align:right; font-size:20px;" value="' + orden + '" class="orden" disabled="true" name="orden_' + indiceOrden + "_" + (parseFloat(cuantos)) + '" id="orden_' + indiceOrden + "_" + (parseFloat(cuantos)) + '"/>' +
				'</td>' +
				'<td style="font-size: 20px;">' +
					'<input onkeyup="checkCantidad(' + indiceOrden + ',' + (parseFloat(cuantos)) + ');" style="text-align:right; font-size:20px; width:60px;" value="' + diferencia + '" class="cantidadesUbicar_' + indiceOrden + '" name="' + indiceOrden + "_" + (parseFloat(cuantos)) + '" id="' + indiceOrden + "_" + (parseFloat(cuantos)) + '"/>' +
				'</td>' +
				'<td style="font-size: 20px;">' +
					'<input value="" name="ubicacion_' + indiceOrden + '_' + (parseFloat(cuantos)) + '" id="ubicacion_' + indiceOrden + '_' + (parseFloat(cuantos)) + '" style="text-align:right; font-size:20px;" onclick = "javascript:ubicar(' + cadena + ',' + indiceOrden + ',' + (parseFloat(cuantos)) + ');" value="Click aqui" class="ubicacionFinal"/>' +
				'</td>' +
				'<td style="font-size: 20px;">' +
					'<a id="elimina" href="javascript:eliminaUbicacion(' + indiceOrden + ',' + (parseFloat(cuantos)) + ')" title="Eliminar esta ubicacion">' +
						'<img src="img/cancel.png" alt="Eliminar" title="Eliminar ubicacion"/>' +
					'</a>' +
				'</td>';
			//document.getElementById('body_' + indiceOrden).appendChild(nuevaFila);
			//document.getElementById('fila_2_1').appendChild(nuevaFila);
			$(nuevaFila).insertAfter('#fila_' + indiceOrden + '_' + (parseFloat(cuantos - 1)));
		} else
			$.msg("No quedan unidades por ubicar");
	}else
		$.msg("Ubique primero la linea anterior");
	//$.msg("Salgo");
}

function eliminaUbicacion(indiceOrden, indiceLinea){
	$("#fila_" + indiceOrden + "_" + indiceLinea).remove();
}

function ubicar(proceso, indiceOrden, indiceLinea){
	cantidad = $("#" + indiceOrden + "_" + indiceLinea).val();
	//$.msg(cantidad);
	if (cantidad > 0){
		//$.msg("Ubicando el proceso " + proceso + ", indiceOrden " + indiceOrden + ", indiceLinea " + indiceLinea + ", cantidad = " + cantidad);
		action = "SalvaDatosSacarUbicacion.action?ubicar=true&reubicar=false&gestionBultos=false&mover=true&sacarRegistro_" + proceso + "__" + $("#ubica_" + indiceOrden + "_" + indiceLinea).val() + "__" + indiceLinea + "=" + $("#" + indiceOrden + "_" + indiceLinea).val();
		/*action = "Reubicar.action?meter=true&reubicar=false&ubicar=true&seleccion=true&sacar=false&registro=" + proceso + "&idPalet=" + indiceLinea + "&cantidad=" + cantidad;*/
		$("#ubicarSeleccion").attr("action", action);
		//document.ubicarSeleccion.submit();
		$("#meter").val("true");
		//$("#reubicar").val("false");
		$("#ubicar").val("true");
		$("#reubicar").val("false");
		$("#gestionBultos").val("false");
		$("#seleccion").val("true");
		$("#sacar").val("false");
		$("#mover").val("true");
		//$.msg($("#registro").val());
		$("#registro").val(proceso);
		//$.msg($("#registro").val());
		$("#idPalet").val(indiceLinea);
		//$.msg($("#cantidad").val());
		$("#cantidad").val(cantidad);
		//$.msg($("#cantidad").val());
		$url = action;
		//$.msg($url);
		$.ajax({
			url: $url,
			cache: false,
			async:false,
			success: function(html){
				if ($("#widget_consGPSele").length > 0){
					//$.msg("consgpsele");
					$("#widget_consGPSele").empty();
					$("#widget_consGPSele").append(html);
					$("#widget_consGPSele").attr("id", "widget_consUbica");
				}else{
					if ($("#widget_consGPCrib").length > 0){
						//$.msg("widget_consGPCrib");
						$("#widget_consGPCrib").empty();
						$("#widget_consGPCrib").append(html);
						$("#widget_consGPCrib").attr("id", "widget_consUbica");
					}else{
						if ($("#widget_consGPDesg").length > 0){
							//$.msg("widget_consGPDesg");
							$("#widget_consGPDesg").empty();
							$("#widget_consGPDesg").append(html);
							$("#widget_consGPDesg").attr("id", "widget_consUbica");
						}else{
							if ($("#widget_consGPFumi").length > 0){
								//$.msg("consgpfumi");
								$("#widget_consGPFumi").empty();
								$("#widget_consGPFumi").append(html);
								$("#widget_consGPFumi").attr("id", "widget_consUbica");
							}else{
								if ($("#widget_consGPCong").length > 0){
									//$.msg("consgpfumi");
									$("#widget_consGPCong").empty();
									$("#widget_consGPCong").append(html);
									$("#widget_consGPCong").attr("id", "widget_consUbica");
								}else{
									$("#widget_consUbica").empty();
									$("#widget_consUbica").append(html);
								}
							}
						}
					}
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
	}else
		$.msg("Debe ubicar una cantidad mayor que cero");
}

function checkCantidad(indiceOrden, contador){
	//$.msg(indiceOrden);
	//$.msg(contador);
	topeMaximo = $("#cuanto_" + indiceOrden).val();
	//$.msg(topeMaximo);
	valorIntroducido = $("#" + indiceOrden + "_" + contador).val();
	//$.msg(valorIntroducido);
	if (valorIntroducido == "")
		$("#" + indiceOrden + "_" + contador).val(0);
	flag = true;
	reseteaValor = false;
	if (parseFloat(valorIntroducido) > parseFloat(topeMaximo)){
		$.msg("El valor introducido no puede superar la cantidad seleccionada: " + topeMaximo);
		flag = false;
		reseteaValor = true;
	}
	if (flag){
		inputs = $(".cantidadesUbicar_" + indiceOrden).get();
		//$.msg(inputs.length);
		valor = 0;
		for (i = 0; i < inputs.length; i++){
			//$.msg($("#" + inputs[i].id).val());
			valor += parseFloat($("#" + inputs[i].id).val());
			if (parseFloat(valor) > parseFloat(topeMaximo)){
				$.msg("La suma de las cantidad introducidas para reubicar la " + (parseFloat(indiceOrden) + 1) + "a. orden no puede superar la cantidad seleccionada: " + topeMaximo);
				//$("#" + indiceOrden + "_" + contador).val(parseFloat(topeMaximo) - (parseFloat(valor)) + parseFloat(valorIntroducido));
				flag = false;
				break;
			}
		}
	}
	if (!flag){
		inputs = $(".cantidadesUbicar_" + indiceOrden).get();
		//$.msg(inputs.length);
		valor = 0;
		for (i = 0; i < inputs.length; i++){
			//$.msg($("#" + inputs[i].id).val());
			valor += parseFloat($("#" + inputs[i].id).val());
		}
		$("#" + indiceOrden + "_" + contador).val(parseFloat(topeMaximo) - (parseFloat(valor)) + parseFloat(valorIntroducido));
	}
}