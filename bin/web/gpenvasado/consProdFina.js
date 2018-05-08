//defino los arrays
var materiaPrima = new Array();
var envases = new Array();

$(document).ready(function(){
	$("#loteAsignado").hide();
	$("#cantidadElaborada").hide();
	$('#electedIngredients').hide();
	$('#escogerIngredientes').hide();
	$('#electedPackages').hide();
	$('#escogerEnvases').hide();
	$('#electedPackagesAgrupar').hide();
	$('#escogerEnvasesAgrupar').hide();
	$("#viejo").hide();
	if($('#dropdown_productos').val() == 0) {
		$("#nuevoEnvasado").hide();
	} else {
		$("#pending").hide();
		$('#electedIngredients').show();
		$('#electedPackages').show();
		$('#electedPackagesAgrupar').show();
	}
	$("#nuevoEnvasado").show();			
	$("#pending").hide();
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick" , "javascript:consGPEnva();");
	$("#bot_vuelve").show();
	$("#bot_insertar").attr("onclick" , "javascript:inserta();");
	$("#widget_menu").show();
	/**/
	cuantosEAN13 = $("#optGroupEAN13").children().length;
	if (cuantosEAN13 == 0){
		$("#optGroupEAN13").attr("label", "No disponemos de stock suficiente para envasar ningun item (EAN13)");
	}
	cuantosEAN14 = $("#optGroupEAN14").children().length;
	if (cuantosEAN14 == 0){
		$("#optGroupEAN14").attr("label", "No disponemos de stock suficiente para envasar ninguna agrupacion (EAN14)");
	}
});

function inserta() {
	if($('#text_cantidad').val() == "") {
		$.msg("Debe indicar cuantas unidades se van a empaquetar");
	} else {
		//$.msg($("#date_fecha").val());
		if ($("#date_fecha").val() != ""){
			totalEmpaque = $('#text_cantidad').val();
			totalMP = 0;
			//$.msg("comenzamos");
			$("#electedIngredientsBody tr ").each(function() {
				$("td input",this).each(function() {
					totalMP += Number($(this).val());
				});
			});
			//$.msg("total mp = " + totalMP);
			totalENV = 0;
			$("#electedEnvasesBody tr ").each(function() {
				$("td input",this).each(function() {
					totalENV += Number($(this).val());
				});
			});
			//$.confirm("&#191Las cantidades que figuran seran restadas del stock de los productos afectados. Desea proseguir?",
			/*$.confirm("&#191Son correctos los datos y las cantidades introducidas para el proceso de envasado? No es posible modificar estos datos posteriormente",
				function(){
					
				},
				function(){
					$.msg("Cancelado");
				}
			);	*/
			insertarOrden();
		}else
			$.msg("Debe insertar la fecha prevista para el envasado");
	}
}

function modifica() {
	if($('#cantidad').val()=="") {
		$.msg$.msg("Debe indicar cuantas unidades se van a empaquetar");
	} else {
		totalEmpaque=$('#cantidad').val();
		totalMP=0;
		$("#electedIngredientsBody tr ").each(function() {
			$("td input",this).each(function() {
				totalMP += Number($(this).val());
			});
		});
		totalENV=0;
		$("#electedEnvasesBody tr ").each(function() {
			$("td input",this).each(function() {
				totalENV += Number($(this).val());
			});
		});
		//$.msg("Total MP: "+totalMP+" Total ENV: "+totalENV+" Para "+totalEmpaque+" unidades");
		if(totalEmpaque>totalMP) {
			$.msg("Hay menos ingredientes de los necesarios para esa cantidad");
		} else if(totalEmpaque>totalENV) {
			$.msg("Hay menos envases de los necesarios para esa cantidad");
		} else if (confirm("Se actualizarán las cantidades introducidas")) {
			//$.msg("METER ORDEN EN EL SISTEMA");
			$('#formulario').attr('action','ActualizaOrdenEnv.action');
			cargarOcultos();
			//document.formulario.submit();
		}
	}
}

function irAlProceso(orden){
	var estado = $('#estado_' + orden).text();
	//$.msg("estado: " + estado);
	var id = $('#idProducto_' + orden).val();
	//$.msg("id produ: " + id);
	var idEnvasado = $('#idEnvasado_' + orden).val();
	//$.msg("id enva: " + idEnvasado);
	var lote = $('#lote_' + orden).text();
	//$.msg("lote: " + lote);
	$('#listado').attr('action','GestionarProcesoEnvasado.action');
	$('#orden').attr('value', orden);
	$('#estado').attr('value', estado);
	$('#id').attr('value', id);
	$('#idEnvasado').attr('value', idEnvasado);
	$('#lote').attr('value', lote);
	document.listado.submit();
}

function seleccionProducto(){
	agrupar = false;
	if($("#dropdown_productos").val() != 0){
		frag = $("#dropdown_productos").val().split("_");
		$("#idProd").val(frag[0]);
		$("#tipoEan").val(frag[1]);
		var $url = "";
		if (frag[1] == 13){
			$.confirm("&#191Vas a agrupar los EANs13?",
				function(){
					$("#agrupar").val(true);
					agrupar = true;
					$.msg("Los items envasados se introduciran en agrupaciones (EAN14)");
					$url = "ListREMP.action?idProd=" + frag[0] + "&tipoEan=" + frag[1] + "&agrupar=" + agrupar;
					$.ajax({
						url: $url,
						cache: false,
						async:false,
						success: function(html){
							$("#colgarNuevoEnvasado").empty();
							$("#colgarNuevoEnvasado").append(html);
						}
					}); 
					script =
					$.ajax({
						type: "POST",
						url: "gpenvasado/nuevoEnvasadoEan" + $("#tipoEan").val() + ".js",
						dataType: "script"
					});
				},
				function(){
					$("#agrupar").val(false);
					agrupar = false;
					$.msg("Los items envasados NO se introduciran en agrupaciones (EAN14)");
					$url = "ListREMP.action?idProd=" + frag[0] + "&tipoEan=" + frag[1] + "&agrupar=" + agrupar;
					$.ajax({
						url: $url,
						cache: false,
						async:false,
						success: function(html){
							$("#colgarNuevoEnvasado").empty();
							$("#colgarNuevoEnvasado").append(html);
						}
					}); 
					script =
					$.ajax({
						type: "POST",
						url: "gpenvasado/nuevoEnvasadoEan" + $("#tipoEan").val() + ".js",
						dataType: "script"
					});
					autoseleccionEnvases();
				}
			);
		}else{
			if (frag[1] == 14){
				$("#agrupar").val(false);
				agrupar = false;
				$url = "ListREMP.action?idProd=" + frag[0] + "&tipoEan=" + frag[1] + "&agrupar=" + agrupar;
				$.ajax({
					url: $url,
					cache: false,
					async:false,
					success: function(html){
						$("#colgarNuevoEnvasado").empty();
						$("#colgarNuevoEnvasado").append(html);
					}
				}); 
				script =
				$.ajax({
					type: "POST",
					url: "gpenvasado/nuevoEnvasadoEan" + $("#tipoEan").val() + ".js",
					dataType: "script"
				});
			}
		}
	}else
		nuevoGPEnva();
}

function insertarOrden(){
	//$.msg("Inserta orden");
	//Dependiendo de si estamos envasando EAN13 o EAN14, cargaremos productos ó materias primas y envases
	tipo = $("#tipoEan").val();
	urlFINAL = "";
	urlAsociados = "?";
	if (tipo == 13){
		envasesEscogidos = $(".envaseEscogido");
		cuantos = envasesEscogidos.length;
		for (i = 0; i < cuantos; i++){
			id = envasesEscogidos.get(i).id;
			//$.msg(id);
			frag = id.split("electedEnvasesBodyRow");
			//En frag[1] tenemos el codigo de entrada
			entrada = frag[1];
			//$.msg(entrada);
			disponible = $("#disponible_" + entrada).text();
			//$.msg(disponible);
			teorica = $("#teorica_env_" + entrada).val();
			//$.msg(teorica);
			if (i > 0)
				urlAsociados += "&"; 
			urlAsociados += "cantidad_" + entrada + "=" + disponible + "&teorica_env_" + entrada + "=" + teorica;
		}
		ingredientesEscogidos = $(".ingredienteEscogido");
		cuantos = ingredientesEscogidos.length;
		//alert(cuantos);
		for (i = 0; i < cuantos; i++){
			id = ingredientesEscogidos.get(i).id;
			//$.msg(id);
			frag = id.split("electedIngredientsBodyRow");
			//En frag[1] tenemos el codigo de entrada
			entrada = frag[1];
			//$.msg("Entrada = " + entrada);
			disponible = $("#disponible_" + entrada).text();
			//$.msg("disponible = " + disponible);
			teorica = $("#teorica_ing_" + entrada).val();
			//$.msg("teorica = " + teorica);
			urlAsociados += "&cantidad_" + entrada + "=" + disponible + "&teorica_ing_" + entrada + "=" + teorica;
		}
		itemsAsociados = $(".itemEscogido");
		cuantos = itemsAsociados.length;
		//alert(cuantos);
		for (i = 0; i < cuantos; i++){
			id = itemsAsociados.get(i).id;
			//$.msg(id);
			frag = id.split("electedIngredientsBodyRow");
			//En frag[1] tenemos el codigo de entrada
			entrada = frag[1];
			//$.msg("Entrada = " + entrada);
			disponible = $("#disponible_" + entrada).text();
			//$.msg("disponible = " + disponible);
			teorica = $("#teorica_EAN13_" + entrada).val();
			//$.msg("teorica = " + teorica);
			urlAsociados += "&cantidad_" + entrada + "=" + disponible + "&teorica_EAN13_" + entrada + "=" + teorica;
		}
		if ($("#agrupar").val() == "true" || $("#agrupar").val() == true){
			//Tenemos que enviar los envases que añadimos para agrupar
			envasesAgruparEscogidos = $(".envaseAgruparEscogido");
			cuantos = envasesAgruparEscogidos.length;
			for (i = 0; i < cuantos; i++){
				id = envasesAgruparEscogidos.get(i).id;
				//$.msg(id);
				frag = id.split("electedEnvasesAgruparBodyRow");
				//En frag[1] tenemos el codigo de entrada
				entrada = frag[1];
				//$.msg(entrada);
				disponible = $("#disponible_" + entrada).text();
				//$.msg(disponible);
				teorica = $("#teorica_envAgrupar_" + entrada).val();
				//$.msg(teorica);
				if (i > 0)
					urlAsociados += "&"; 
				urlAsociados += "&cantidad_" + entrada + "=" + disponible + "&teorica_envAgrupar_" + entrada + "=" + teorica;
			}
		}
	}else
		if (tipo == 14){
			envasesEscogidos = $(".envaseEscogido");
			cuantos = envasesEscogidos.length;
			for (i = 0; i < cuantos; i++){
				id = envasesEscogidos.get(i).id;
				//$.msg(id);
				frag = id.split("electedEnvasesBodyRow");
				//En frag[1] tenemos el codigo de entrada
				entrada = frag[1];
				//$.msg(entrada);
				disponible = $("#disponible_" + entrada).text();
				//$.msg(disponible);
				teorica = $("#teorica_env_" + entrada).val();
				//$.msg(teorica);
				if (i > 0)
					urlAsociados += "&"; 
				urlAsociados += "cantidad_" + entrada + "=" + disponible + "&teorica_env_" + entrada + "=" + teorica;
			}
			productosEscogidos = $(".productoEscogido");
			cuantos = productosEscogidos.length;
			for (i = 0; i < cuantos; i++){
				id = productosEscogidos.get(i).id;
				frag = id.split("electedProductosBodyRow");
				entrada = frag[1];
				disponible = $("#disponible_" + entrada).text();
				teorica = $("#teorica_ing_" + entrada).val();
				if (i > 0)
					urlAsociados += "&"; 
				urlAsociados += "&cantidad_" + entrada + "=" + disponible + "&teorica_ing_" + entrada + "=" + teorica;
			}
		}else{
			$.msg("ERROR seleccionando el producto, no se ha identificado el tipo de EAN");
			return -1;
		}
	urlInfoExtra = "&fechaRegistro=" + $("#date_fecha").val() + "&fechaLlegada=" + $("#date_fecha").val() + "&cantidad=" + $("#text_cantidad").val() + "&idProd=" + $("#idProd").val() + "&observaciones=" + $("#observaciones").val() + "&tipoEan=" + tipo + "&idAgrupacion=" + $("#dropdown_agrupaciones").val();
	urlFINAL = urlAsociados + urlInfoExtra;
	//$.msg(urlFINAL);	
	var $url = "InsertaOrdenEnv.action" + urlFINAL;
	//alert(urlFINAL);
	$.ajax({
		type: 'POST',
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$.msg("Envasado programado correctamente");
		},
		error: function(html){
			$.msg("Envasado programado correctamente");
		}
	});
	//Mostramos todos los envasados programados
	$url = "ListaEnvasar.action";
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

function mostrarIngredientes(){
	visible = $("#todoIngredientes").is(" :visible");
	if (visible == true){
		$("#todoIngredientes").hide();
		$("#bot_mostrarMaterias").text("Mostrar");
	}else{
		$("#todoIngredientes").show();
		$("#bot_mostrarMaterias").text("Ocultar");
	}
}

function mostrarEnvases(){
	visible = $("#todoEnvases").is(" :visible");
	if (visible == true){
		$("#todoEnvases").hide();
		$("#bot_mostrarEnvases").text("Mostrar");
	}else{
		$("#todoEnvases").show();
		$("#bot_mostrarEnvases").text("Ocultar");
	}
}

function mostrarProductos(){
	visible = $("#todoProductos").is(" :visible");
	if (visible == true){
		$("#todoProductos").hide();
		$("#bot_mostrarProductos").text("Mostrar");
	}else{
		$("#todoProductos").show();
		$("#bot_mostrarProductos").text("Ocultar");
	}
}

function autoseleccionEnvases(){
	lineas = $('.encabezadosEnvases').next('tr:visible');
	//alert(lineas.length);
	var i;
	for (i = 0; i < lineas.length; i++){
		//alert(i);
		var linea = lineas.get(i);
		var aux = linea.id.split('escogerEnvases')[1].split('_');
		var entrada = aux[0];
		var producto = aux[1];
		/*alert(entrada);
		alert(producto);*/
		insertaENV(entrada, producto);
		//alert('insertado');
		//alert(i);
		//alert(lineas.length);
	}
}