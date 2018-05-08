var punto = false;

$(document).ready(function(){
	//alert("hola");
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick" , "javascript:consEnvasadosActivos();");
	$("#bot_vuelve").show();
	$("#bot_salvarSacar").show();
	$("#widget_menu").show();
	/***/
	$.msg("Compruebe que los datos del proceso de envasado son correctos, ya que no sera posible deshacer este proceso posteriormente.");
	inputsSacar = $(".sacar").get();
	for (i = 0; i < inputsSacar.length; i++){
		id = inputsSacar[i].id;
		//alert(id);
		frag = id.split("_");
		idFrag = "";
		if (frag.length == 4)
			idFrag = frag[1] + "_" + frag[2] + "_" + frag[3];
		else
			idFrag = frag[1] + "_" + frag[2];
		//alert(idFrag);
		cantidadSacarActualiza(idFrag, false);
		//alert("pfff");
	}
	/* Mostramos la primera ubicacion para cada envase */
	envases = $(".envase").get();
	//alert(envases.length);
	for (i = 0; i < envases.length; i++){
		codigoEntrada = envases[i].id;
		//alert(codigoEntrada);
		//alert($("." + codigoEntrada).get().length);
		if ($("." + codigoEntrada).get().length == 1){
			$("#botonMasUbicaciones_" + codigoEntrada).hide();
		}
	}
	$.unblockUI();
});

function compruebaNumero(){
	valor = $("#elabora").val();
	//$.msg(valor);
	//1. Si está vacío, poner un cero
	if (valor == ""){
		$("#elabora").val(0);
	}
	$("#lote").val($("#loteAsignado").val());
}

function salvarSacar(){
	$.confirm("&#191Las cantidades seleccionadas seran restadas del stock actual. Desea continuar?",
		function(){
			submit = true;
			materias = $('.entradaMateria');
			//$.msg(materias.length);
			for (i = 0; i < materias.length; i++){
				//$.msg("i: " + i);
				cantidadTotal = 0;
				entrada = materias[i].innerHTML;
				//$.msg(entrada);
				entradas = $("." + entrada);
				//$.msg(entradas.length);
				for (j = 0; j < entradas.length; j++){
					/*if (i == 4)
						$.msg("el i es 4");*/
					id = entradas[j].id;
					//$.msg(id);
					//hijo5 = $("#" + id).children(5);
					//$.msg("sacar_" + entrada + "_" + (j + 1));
					//$.msg($("#sacar_E110628-2_1").children(0).val());
					//$.msg($("#sacar_" + entrada + "_" + (j + 1)).children(0).val());
					cantidad = $("#sacar_" + entrada + "_" + (j + 1)).children(0).val();
					//$.msg(cantidad);
					cantidadTotal += parseFloat(cantidad);
					/*$urlI += "&" + $("#sacar_" + entrada + "_" + (j + 1)).children(0).attr("name") + "=" +
						$("#sacar_" + entrada + "_" + (j + 1)).children(0).val();*/
				}
				//$.msg(cantidadTotal);
				//$.msg($("#cantidadMP_" + entrada).text());
				cantidadMP = $("#cantidadMP_" + entrada).text().trim();
				cantidadMP = redondearValor(cantidadMP, 100);
				cantidadTotal = redondearValor(cantidadTotal, 100);
				/*alert(cantidadMP);
				alert(cantidadTotal);*/
				if (parseFloat(cantidadTotal) > parseFloat(cantidadMP)){
					$.msg("Seleccionada demasiada cantidad para la entrada " + entrada + ".");
					submit = false;
					break;
				} else
					if (parseFloat(cantidadTotal) < parseFloat(cantidadMP)){
						$.msg("Seleccionada insuficiente cantidad para la entrada " + entrada + ".");
						submit = false;
						break;
					}
			}
			if (submit){
				//Comprobamos que nunca se selecciona mas cantidad para sacar que la que hay en el hueco
				sacamos = $('.sacar');
				for (i = 0; i < sacamos.length; i++){
					id = sacamos[i].id;
					//$.msg(id);
					cantidadSacar = $('#' + id).val();
					//$.msg(cantidadSacar);
					if (cantidadSacar > 0){
						array = id.split('_');
						//$.msg(array);
						entrada = array[1];
						//$.msg(entrada);
						cantidadUbicada = $('#enHueco_' + entrada).text();
						//$.msg(cantidadUbicada);
						//$.msg(cantidadSacar);
						cantidadSacar = redondearValor(cantidadSacar, 100);
						cantidadUbicada = redondearValor(cantidadUbicada, 100);
						if (parseFloat(cantidadSacar) > parseFloat(cantidadUbicada)){
							$.msg("Problema introduciendo la cantidad a retirar para el registro de entrada " + entrada +
								". No hay tanta cantidad almacenada en la ubicacion.");
							submit = false;
							break;
						}
					}
				}
			}
			//$.msg(3);
			//$.msg(submit);
			if (submit){
				var $url = "";
				$url = "SalvarUbicacionesEnvasado.action?orden=" + $("#orden").val();// + $urlI;
				cuantosSacar = $(".sacar").length;
				for (i = 0; i < cuantosSacar; i++){
					//$.msg($(".sacar").get(i).id);
					name = $("#" + $(".sacar").get(i).id).attr("name");
					//alert(name);
					//$.msg(name);
					$url += "&" + name + "=" + $("#" + $(".sacar").get(i).id).val();
				}
				//sacar_%{#ubicacionesEn.idHueco}_%{#ubicacionesEn.idUbicacion}_%{#infoen.registroEntrada}
				//$.msg($url);
				$.ajax({
					url: $url,
					cache: false,
					async:false,
					success: function(html){
						$("#widget_consGPEnva").empty();
						$("#widget_consGPEnva").append(html);
					}
				});
				$.ajax({
					type: "POST",
					url: "gpenvasado/listaEnvasar.js",
					dataType: "script"
				});
				$.ajax({
					type: "POST",
					url: "js/script.js",
					dataType: "script"
				});
			}
		},
		function(){
			$.msg("Cancelado");
		}
	);
}

function cantidadSacarActualiza(id, mensaje){
	//alert("hola");
	//alert(id);
	aSacar = $("#aSacar_" + id).val();
	//alert(aSacar);
	enHueco = $("#enHueco_" + id).html().trim();
	//alert(enHueco);
	//alert("a sacar: " + aSacar + ".en hueco: " + enHueco);
	if (parseFloat(aSacar) > parseFloat(enHueco)){
		if (mensaje)
			$.msg("Ha introducido mas cantidad de la que hay disponible en la ubicacion");
		$("#aSacar_" + id).val(enHueco.trim());
	}
}

function muestraMasUbicaciones(entrada){
	//alert(entrada);
	filas = $("." + entrada).get();
	visibles = 0;
	for (i = 0; i < filas.length; i++){
		id = filas[i].id;
		if ($("#" + id).is(" :visible")){
			//alert("id: " + id + " visible");
			visibles++;
		}
		if (visibles > 1)
			break;
	}
	//Si solo hay una fila mostrada, mostramos todas
	//alert(entrada);
	//alert(visibles);
	if (visibles == 1){
		$("." + entrada).show();
		$("#botonMasUbicaciones_" + entrada).html("Menos ubicaciones");
	}
	//Si hay mas de una mostrada, ocultamos todas y mostramos solo la primera
	if (visibles > 1){
		$("." + entrada).hide();
		$(".ubicacion_1").show();
		$("#botonMasUbicaciones_" + entrada).html("Mas ubicaciones");
	}
	//alert(visibles);
}