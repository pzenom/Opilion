var agrupar = false;

$(document).ready(function(){
	//Miramos si hay envases de agrupacion -> Si se va a agrupar o no
	if ($("#electedEnvasesAgruparBody").children().length > 0){
		agrupar = true;
		$("#electedEnvasesAgruparTable").show();
	}
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick" , "javascript:consEnvasadosActivos();");
	$("#bot_vuelve").show();
	$("#bot_salvarSacar").show();
	$("#widget_menu").show();
	/***/
	$.msg("Compruebe que los datos del proceso de envasado son correctos, ya que no sera posible deshacer este proceso posteriormente.");
	inputsSacar = $(".sacar").get();
	//alert(inputsSacar.length);
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
	//alert(1);
	/* Mostramos la primera ubicacion para cada ingrediente */
	ingredientes = $(".ingrediente").get();
	for (i = 0; i < ingredientes.length; i++){
		codigoEntrada = ingredientes[i].id;
		//alert($("." + codigoEntrada).get().length);
		if ($("." + codigoEntrada).get().length == 1){
			$("#botonMasUbicaciones_" + codigoEntrada).hide();
		}
	}
	//alert(2);
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
	//1. Si está vacío, poner un cero
	if (valor == ""){
		$("#elabora").val(0);
	}
	$("#lote").val($("#loteAsignado").val());
}

function salvarSacar(){
	puedeSeguir = true;
	if (puedeSeguir){
		$.confirm("&#191Las cantidades seleccionadas seran restadas del stock actual. Desea continuar?",
			function(){
				submit = true;
				materias = $('.entradaMateria');
				for (i = 0; i < materias.length; i++){
					cantidadTotal = 0;
					entrada = materias[i].innerHTML;
					//alert(entrada);
					entradas = $(".sacar_" + entrada);
					for (j = 0; j < entradas.length; j++){
						id = entradas[j].id;
						cantidad = $('#' + id).val();
						cantidadTotal += parseFloat(cantidad);
					}
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
				//$.msg(1);
				if (submit){
					envases = $('.envase');
					for (i = 0; i < envases.length; i++){
						cantidadTotal = 0;
						entrada = envases[i].id;
						entradas = $(".sacar_" + entrada);
						for (j = 0; j < entradas.length; j++){
							id = entradas[j].id;
							cantidad = $('#' + id).val();
							cantidadTotal += parseFloat(cantidad);
						}
						cantidadEN = $("#cantidadEN_" + entrada).text().trim();
						cantidadEN = redondearValor(cantidadEN, 100);
						cantidadTotal = redondearValor(cantidadTotal, 100);
						/*alert(cantidadEN);
						alert(cantidadTotal);*/
						if (parseFloat(cantidadTotal) > parseFloat(cantidadEN)){
							$.msg("Seleccionada demasiada cantidad para la entrada " + entrada + ".");
							submit = false;
							break;
						} else
							if (parseFloat(cantidadTotal) < parseFloat(cantidadEN)){
								$.msg("Seleccionada insuficiente cantidad para la entrada " + entrada + ".");
								submit = false;
								break;
							}
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
							if (parseFloat(cantidadSacar) > parseFloat(cantidadUbicada)){
								$.msg("Problema introduciendo la cantidad a retirar para el registro de entrada " + entrada +
									". No hay tanta cantidad almacenada en la ubicacion.");
								submit = false;
								break;
							}
						}
					}
				}
				if (submit){
					var $url = "";
					$url = "SalvarUbicacionesEnvasado.action?orden=" + $("#orden").val();
					cuantosSacar = $(".sacar").length;
					for (i = 0; i < cuantosSacar; i++){
						name = $("#" + $(".sacar").get(i).id).attr("name");
						$url += "&" + name + "=" + $("#" + $(".sacar").get(i).id).val();
					}
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
}

function muestraMasUbicaciones(entrada){
	//alert(entrada);
	filas = $("." + entrada).get();
	visibles = 0;
	//alert(filas.length);
	for (i = 0; i < filas.length; i++){
		id = filas[i].id;
		//alert(id);
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

function cantidadSacarActualiza(id, mensaje){
	//alert(id);
	aSacar = $("#aSacar_" + id).val();
	//alert("aSacar: " + aSacar);
	enHueco = $("#enHueco_" + id).html().trim();
	//alert(enHueco);
	//alert("a sacar: " + aSacar + ".en hueco: " + enHueco);
	if (parseFloat(aSacar) > parseFloat(enHueco)){
		if (mensaje)
			$.msg("Ha introducido mas cantidad de la que hay disponible en la ubicacion");
		$("#aSacar_" + id).val(enHueco.trim());
	}
}