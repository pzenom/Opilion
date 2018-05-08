var nombreNuevoAlmacen = "";

$(document).ready(function() {
	if ($("#mover").val() == "true"){
		$("#infoMover").show();
	}else
		if ($("#meter").val() == "true")
			$("#infoMeter").show();
	$("#idTipoUbicacion").val(0);
	$("#idAlmacen").val(0);
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick", "javascript:volverAlEscritorio();");
	$("#bot_vuelve").show();
	$("#bot_nuevo").attr("onclick", "javascript:nuevoAlmacen(false);");
	$("#bot_nuevo").show();
	$("#bot_nuevoVehiculo").show();
	$("#widget_menu").show();
	//$('#mydiv').show();
});

function gestionarAlmacenSeleccionado(idAlmacen){
	almacenActual = 0;
	if(idAlmacen != null && idAlmacen > 0){
		almacenActual = idAlmacen;
	}else{
		if ($("#dropdown_almacenes").length == 1){
			valor = $("#dropdown_almacenes").val();
		}else{
			valor = $("#idAlmacen").val();
		}
		$("#idAlmacen").val(valor);
		almacenActual = $("#idAlmacen").val();
	}
	//alert(almacenActual);
	if (almacenActual > 0){
		$("#bot_nuevo").hide();
		$("#bot_nuevoVehiculo").hide();
		$("#idAlmacen").val(almacenActual);
		parametros = "?idTipoUbicacion=" + almacenActual + "&mover=" + $("#mover").val() +
			"&reubicar=" + $("#reubicar").val() + "&registro=" + $("#registro").val() + "&meter=" + $("#meter").val() +
			"&cantidad=" + $("#cantidad").val() + "&numeroBultos=" + $("#numeroBultos").val() + "&sacar=" + $("#sacar").val() +
			"&gestionBultos=" + $("#gestionBultos").val() + "&idPalet" + $("#idPalet").val();
		var $url = "";
		$url = "UbicarPalet.action" + parametros;
		$.ajax({
			url: $url,
			cache: false,
			async:false,
			success: function(html){
				//alert(html);
				//alert(1);
				$("#widget_consUbica").empty();
				$("#widget_consUbica").append(html);
				//alert(2);
				//$.msg("ya hizo el anden");
				$("#btConfirmarSacar").hide();
				$("#btConfirmarIncidencia").hide();
				$("#btConfirmarUbicacion").hide();
				$("#btreubicar").hide();
				urlScript = "";
				//alert(almacenActual);
				if (almacenActual == 1){
					urlScript = "ubicacion/barciaPlanoBase_.js";
					//$("#btreubicar").show();
				}else{
					//Si no, lo tratamos como si fuera un vehículo, aunque sea un almacen
					if (almacenActual > 1){
						urlScript = "ubicacion/ubicacionVehiculo.js";
					}
				}
				$.ajax({
					type: "POST",
					url: urlScript,
					dataType: "script"
				});
				$.ajax({
					type: "POST",
					url: "js/script.js",
					dataType: "script"
				});
				$("#bot_vuelve").hide();
				$("#btvolver").show();
				//$(".blocker").hide();
				$.unblockUI();
			}
		});
	}else{
		$.msg("Seleccione un almacen para continuar");
	}
}

function almacenSeleccionado(idAlmacen){
	$.blockUI({ message: '<h1>Cargando...</h1>' });
	//$(".blocker").show();
    //setTimeout(, 2000); 
	setTimeout(function(){
		gestionarAlmacenSeleccionado(idAlmacen);
	}, 200);
}

function nuevoAlmacen(esVehiculo){
	var $url = "";
	var prompt = "";
	var valor = "";
	if (esVehiculo == false){
		frase = "Introduzca una descripcion para el nuevo almacen";
		$url = "IngresarRegistroAlmacen.action?";
		valor = "Almacen";
	}
	else{
		frase = "Introduzca la matricula del nuevo vehiculo";
		$url = "IngresarRegistroAlmacen.action?esVehiculo=true&";
		valor = "Vehiculo";
	}
	$.prompt(frase, valor,
	function(value){
		nombreNuevoAlmacen = value;
		//Calculamos el id que tendria la forma de pago
		var maxAlmacen = 0;
		opciones = $("#optgroup_almacenes").children();
		//$.msg("tenemos las opciones");
		//$.msg("length: " + opciones.length);
		$cadenaMaximo = "";
		var arrayFormasPago = [];
		for (i = 0; i < opciones.length; i++){
			//$.msg(opciones[i].text);
			opciones[i] = opciones[i].value;
			arrayFormasPago.push(parseFloat(opciones[i]));
			//$.msg(arrayFormasPago);
		}
		maxAlmacen = Math.max.apply( Math, arrayFormasPago ) + 1;
		//$.msg("maxAlmacen: " + maxAlmacen);
		if (maxAlmacen == "-Infinity"){
			maxAlmacen = 1;
			//$.msg(maxAlmacen);
		}
		$("#idTipoUbicacion").val(maxAlmacen);
		//Mediante ajax, intentar guardar en BD
		$url += "idAlmacen=" + maxAlmacen + "&nombre=" + value;
		$.ajax({
			url: $url,
			cache: false,
			async:false,
			success: function(html){
				$('#optgroup_almacenes').append(new Option(value, maxAlmacen, true, true));
				//$('#optgroup_almacenes').text(value);
				$.msg("Almacen salvado: " + value);
				addAlmacen();
			},
			error: function(){
				$.msg("Error insertando el nuevo almacen");
			}
		});
	},
	function(){
		$.msg("Cancelado");
	});
}

function muestraMatricula(){
	if ($("#checkVehiculo").is(":checked")){
		$("#filaMatricula").show();
	}else
		$("#filaMatricula").hide();
}

function addAlmacen(){
	$("#nombre").val(nombreNuevoAlmacen);
	//document.miFormulario.miCheck.checked
	nombre = nombreNuevoAlmacen;
	//alert(nombre);
	/*var maxAlmacen = 0;
	opciones = $("#optgroup_almacenes").children();
	//$.msg(opciones.length);
	for (i = 0; i < opciones.length; i++){
		//$.msg(opciones[i].value);
		opciones[i] = opciones[i].value;
	}
	maxAlmacen = parseFloat(Array.max(opciones)) + 1;
	//$.msg(maxAlmacen);
	if (maxAlmacen == "-Infinity"){
		maxAlmacen = 1;
		//$.msg(maxAlmacen);
	}
	//alert(maxAlmacen);
	$('#optgroup_almacenes').append(new Option(nombre, maxAlmacen, true, true));*/
	$("#" + $("#dropdown_almacenes").parent().attr("id")).children('span').text(nombre);
	//$("#idTipoUbicacion").val(maxAlmacen);
}

Array.max = function( array ){
return Math.max.apply( Math, array );
};

function volverSeleccionAlmacen(){
	var $url = "";
	$url = "SeleccionAlmacen.action?mover=false&reubicar=false";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consUbica").empty();
		$("#widget_consUbica").append(html);
		$("#btConfirmarSacar").hide();
		$("#btvolver").hide();
		$("#btreubicar").hide();
		$("#btConfirmarIncidencia").hide();
		$("#btConfirmarUbicacion").hide();
		if ($("#sacar").val() == "false" && $("#meter").val() == "false" && $("#reubicar").val() == "false"
			&& $("#mover").val() == "false" && $("#incidencia").val() == "false")//Mostrando almacen
			$.msg("Seleccione el almacen que desea inspeccionar",{live:7000});
		else
			if($("#sacar").val() == "false" && $("#meter").val() == "false" && $("#reubicar").val() == "false"
				&& $("#mover").val() == "true" && $("#incidencia").val() == "false")//Moviendo DESDE
				$.msg("Seleccione el almacen DESDE el que quiere mover",{live:7000});
			else
				if($("#sacar").val() == "false" && $("#meter").val() == "true" && $("#reubicar").val() == "false"
					&& $("#mover").val() == "false" && $("#incidencia").val() == "false")//Moviendo HACIA
					$.msg("Seleccione el almacen HACIA el que quiere mover",{live:7000});
				else
					if($("#incidencia").val() == "true")
						$.msg("Seleccione el almacen donde se registra la incidencia",{live:7000});
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