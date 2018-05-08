// Vector para guardar los estados y las incidencias seleccionadas
vestados = new Array();
vincidencias = new Array();
var c = 0;
if(c == 0){
	cargaInicial();
}

$(document).ready(function() {
	document.getElementById("idTipoRegistro").setAttribute("onchange", "tipoRegistro()");
	tipoRegistro();
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_actualizar").attr("onclick" , "javascript:updaRE();");
	$("#bot_actualizar").show();
	$("#bot_vuelve").attr("onclick", "javascript:listaRE();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	tipo = $("#dropdown_tipoRegistro").val();
	//alert(tipo);
	if (tipo == 'M'){
		$("#materias").show();
	}else{
		if (tipo == 'E'){
			$("#envases").show();
		}else{
			if (tipo == 'P'){
				$("#productosFinales").show();
			}
		}
	}
});

function actualizarCosto(){
	if (puntosEnCadena($("#costoUnitario").val()) > 1){
		$.msg("Introducir números con el punto como separador decimal. Ejemplo: 23400.50");
		//$.msg("escribe " + unitarioAnterior);
		$("#costoUnitario").val(unitarioAnterior);
	}
	else{
		if (puntosEnCadena($("#cantidad").val()) > 1){
			$.msg("Introducir números con el punto como separador decimal. Ejemplo: 23400.50");
			$("#cantidad").val(cantidadAnterior);
			//$.msg("escribe " + cantidadAnterior);
		}
		else{
			$("#costoTotal").val($("#costoUnitario").val() * $("#cantidad").val());
			unitarioAnterior = $("#costoUnitario").val();
			cantidadAnterior = $("#cantidad").val();
		}
	}
}

function puntosEnCadena(cadena){
	noNumero = 0;
	frag = cadena.split("");
	for (i = 0; i < cadena.length; i++){
		if ((i == 0) && (frag[i] == '.'))
			return (2);
		if (frag[i] == '.'){
			noNumero++;
		}
	}
	return noNumero;
}

// Funcion que, una vez cargada las listas con los estados y las incidencias, las guarda en los vectores
function cargaInicial() {
	// Estados
	var ini=document.getElementsByClassName("idEstado");
	for (i = 0; i < ini.length; i++) {
		vestados.push(ini[i].innerHTML);
		// Asignar la funcion a onClick pasandole el id a borrar
		var f=document.getElementById("fallo"+ini[i].innerHTML);
		f.onclick = borrarE(ini[i].innerHTML);
	}
	//$.msg(vestados);
	// Incidencias
	ini=document.getElementsByClassName("idInc");
	for (i = 0; i < ini.length; i++) {
		vincidencias.push(ini[i].innerHTML);
		// Asignar la funcion a onClick pasandole el id a borrar
		var f=document.getElementById("incid"+ini[i].innerHTML);
		f.onclick = borrarI(ini[i].innerHTML);
	}
	//$.msg(vincidencias);
	c=1;
	tipoRegistro();
}

function estadoSelec() {
	// Variable que indica si ya se ha insertado o no el elemento seleccionado 0 --> NO EXISTE
	//																		  1 --> EXISTE
	var existe_est=0;
	// Se guarda el indice del elemento seleccionado
	var idest=document.getElementById("estado").selectedIndex;

	// Se guardar el texto del elemento seleccionado
	var est=document.getElementById("estado").options[document.getElementById("estado").selectedIndex].text;

	//Comprobar que el elemento no este ya insertado!!
	for(var i=0; i<vestados.length; i++) {
		if(vestados[i]==idest) {
			$.msg("Ya ha seleccionado este estado");
			existe_est=1;
		}
	}

	// Si no existe el elemento && no es el titulo, se inserta
	if(existe_est==0 && idest!=0) {
		vestados.push(idest);
		//$.msg(vestados);

		// Componer la tabla que muestra los estados
		// Crear una fila
		var fila = document.createElement('tr');
		// Se le añade un id dependiente del estado seleccionado
		fila.setAttribute("id", "est"+idest);
		// Crear la columna del estado
		var e = document.createElement('td');
		e.innerHTML = est;

		document.getElementById("e").appendChild(fila);
		fila.appendChild(e);

		// Crear la columna del boton borrar
		var borrar = document.createElement('td');
		borrar.innerHTML = '<a href="#"><img id="fallo'+idest+'" src="img/cancel.png" alt="Eliminar" title="Eliminar"/></a>';

		fila.appendChild(borrar);

		// Asignar la funcion a onClick pasandole el id a borrar
		var f=document.getElementById("fallo"+idest);
		f.onclick = borrarE(idest);
	}
}

function borrarE(idest) {
	var f = function() {
		borrarEstado(idest);
	};
	return f;
}

function borrarEstado(est) {
	var posBorrar=vestados.indexOf(est);
	vestados.splice(posBorrar, 1);
	// Eliminar el elemento de la lista
	var estborrar = document.getElementById("est"+est);
	var padre = estborrar.parentNode;
	padre.removeChild(estborrar);
}

function incidenciaSelec() {
	if ($("#dropdown_incidencias").val() > 0){
		// Variable que indica si ya se ha insertado o no el elemento seleccionado 0 --> NO EXISTE
		//																		  1 --> EXISTE
		var existe_inc = 0;
		// Se guarda el indice del elemento seleccionado
		//var idinc = document.getElementById("dropdown_incidencias").selectedIndex;
		var idinc = $("#dropdown_incidencias").val();
		// Se guardar el texto del elemento seleccionado
		var inc=document.getElementById("dropdown_incidencias").options[document.getElementById("dropdown_incidencias").selectedIndex].text;
		//Comprobar que el elemento no este ya insertado!!
		for(var i = 0; i < vincidencias.length; i++) {
			if(vincidencias[i] == idinc) {
				$.msg("Ya ha seleccionado esta incidencia");
				existe_inc = 1;
			}
		}
		// Si no existe el elemento && no es el titulo, se inserta
		if(existe_inc==0 && idinc!=0) {
				vincidencias.push(idinc);
				//$.msg(vincidencias);
			// Componer la tabla que muestra las incidencias
			// Crear una fila
			var fila = document.createElement('tr');
			// Se le añade un id dependiente del estado seleccionado
			fila.setAttribute("id", "inc"+idinc);
			fila.setAttribute("class", "incidencias");
			// Crear la columna de la incidencia
			var i = document.createElement('td');
			i.innerHTML = inc;

			document.getElementById("tbdoyIncidencias").appendChild(fila);
			fila.appendChild(i);

			// Crear la columna del boton borrar
			var borrar = document.createElement('td');
			borrar.innerHTML = '<a href="#"><img id="incid'+idinc+'" src="img/cancel.png" alt="Eliminar" title="Eliminar"/></a>';

			fila.appendChild(borrar);

			// Asignar la funcion a onClick pasandole el id a borrar
			var f=document.getElementById("incid"+idinc);
			f.onclick = borrarI(idinc);
		}
	}
}

function borrarI(idinc) {
	//$.msg("hola borrarI...");
	var f = function() {
		borrarIncidencia(idinc);
	};
	return f;
}

function borrarIncidencia(inc) {
	//$.msg("hiii: " + inc);
	var posBorrar=vincidencias.indexOf(inc);
	vincidencias.splice(posBorrar, 1);
	// Eliminar el elemento de la lista
	var incborrar = document.getElementById("inc"+inc);
	var padre = incborrar.parentNode;
	padre.removeChild(incborrar);
}

function updaRE(){
	//alert("hola");
	var $url = "";
	tipoReg = $("#idTipoRegistro").val();
	if ((tipoReg == "M" && $("#dropdown_productos").val() == 0) ||
		(tipoReg == "E" && $("#dropdown_envases").val() == 0) ||
		(tipoReg == "P" && $("#dropdown_productosFinales").val() == 0)){
			$.msg("Seleccione el producto registrado");
	}else{
		if ($("#text_cantidad").val() == "" || $("#text_cantidad").val() == "0"){
			$.msg("Introduzca la cantidad");
		}else{
			if ((tipoReg == "M") && $("#dropdown_categoriasEntrada").val() == 0){
				$.msg("Debe seleccionar la categoria de entrada para la materia prima");
			}else{
				if ((tipoReg == "M") && $("#dropdown_categoriasSalida").val() == 0){
					$.msg("Debe seleccionar la categoria de salida para la materia prima");
				}else{
					if ((tipoReg != "P") && $("#text_numeroBultos").val() == "" || $("#text_numeroBultos").val() == "0")
						$.msg("El numero de bultos tiene que ser mayor que 0");
					else{
						if ((tipoReg != "P") && $("#text_numeroPalets").val() == "" || $("#text_numeroPalets").val() == "0"){
							$.msg("El numero de palets tiene que ser mayor que 0");
						}else{
							$.confirm("&#191Son correctos los datos introducidos?",
								function(){
									if (tipoReg == "E"){
										$url = "UpdaRE.action?codigoOrden=" + $("#text_orden").val() + "&lote=" + $("#text_lote").val() +
											"&cantidad=" + $("#text_cantidad").val() +
											"&costoUnitario=" + $("#text_costoUnitario").val() +
											"&costoTotal=" + $("#text_costoTotal").val().split("_")[0] +
											"&fechaLlegada=" + $("#date_fechaRegistro").val() +
											"&fechaCaducidad=" + $("#date_fechaCaducidad").val() +
											"&idEnvase=" + $("#dropdown_envases").val() +
											"&idCosecha=" + $("#dropdown_cosechas").val() +
											"&idFormatoEntrega=" + $("#dropdown_formatosEntrega").val() +
											"&numeroBultos=" + $("#text_numeroBultos").val() +
											"&numeroPallets=" + $("#text_numeroPalets").val() +
											"&temperatura=" + $("#text_temperatura").val() +
											"&humedad=" + $("#text_humedad").val() +
											"&notasIncidencias=" + $("#text_notasIncidencias").val() +
											"&idTipoRegistro=" + tipoReg +
											"&idTipoUbicacion=1";
									}
									else{
										if (tipoReg == "M")
											$url = "UpdaRE.action?codigoOrden=" + $("#text_orden").val() + "&lote=" + $("#text_lote").val() +
												"&cantidad=" + $("#text_cantidad").val() +
												"&costoUnitario=" + $("#text_costoUnitario").val() +
												"&costoTotal=" + $("#text_costoTotal").val().split("_")[0] +
												"&fechaLlegada=" + $("#date_fechaRegistro").val() +
												"&fechaCaducidad=" + $("#date_fechaCaducidad").val() +
												"&idProducto=" + $("#dropdown_productos").val() +
												"&idCategoriaEntrada=" + $("#dropdown_categoriasEntrada").val() +
												"&idCategoria=" + $("#dropdown_categoriasSalida").val() +
												"&idCosecha=" + $("#dropdown_cosechas").val() +
												"&idFormatoEntrega=" + $("#dropdown_formatosEntrega").val() +
												"&numeroBultos=" + $("#text_numeroBultos").val() +
												"&numeroPallets=" + $("#text_numeroPalets").val() +
												"&temperatura=" + $("#text_temperatura").val() +
												"&humedad=" + $("#text_humedad").val() +
												"&notasIncidencias=" + $("#text_notasIncidencias").val() +
												"&idTipoRegistro=" + tipoReg +
												"&idTipoUbicacion=1";
										else
											if (tipoReg == "P")
												$url = "UpdaRE.action?listoDistribuir=true&codigoOrden=" + $("#text_orden").val() +
													"&lote=" + $("#text_lote").val() +
													"&cantidad=" + $("#text_cantidad").val() +
													"&costoUnitario=" + $("#text_costoUnitario").val() +
													"&costoTotal=" + $("#text_costoTotal").val().split("_")[0] +
													"&fechaLlegada=" + $("#date_fechaRegistro").val() +
													"&fechaCaducidad=" + $("#date_fechaCaducidad").val() +
													"&idProducto=" + $("#dropdown_productosFinales").val() +
													"&idCategoriaEntrada=" + $("#dropdown_categoriasEntrada").val() +
													"&idCategoria=" + $("#dropdown_categoriasSalida").val() +
													"&idCosecha=" + $("#dropdown_cosechas").val() +
													"&idFormatoEntrega=" + $("#dropdown_formatosEntrega").val() +
													"&numeroBultos=" + $("#text_numeroBultos").val() +
													"&numeroPallets=" + $("#text_numeroPalets").val() +
													"&temperatura=" + $("#text_temperatura").val() +
													"&humedad=" + $("#text_humedad").val() +
													"&notasIncidencias=" + $("#text_notasIncidencias").val() +
													"&idTipoRegistro=" + tipoReg +
													"&idTipoUbicacion=1";
									}
									if (vestados.length > 0)
										$url += "&listaEstadoFabas=" + vestados;
									if (vincidencias.length > 0)
										$url += "&listaIncidencias=" + vincidencias;
									//alert($url);
									$.ajax({
										url: $url,
										cache: false,
										async:false,
										success: function(html){
											$("#widget_consRE").empty();
											$("#widget_consRE").append(html);
										}
									});
									$.ajax({
										type: "POST",
										url: "registroentrada/consRE.js",
										dataType: "script"
									});
									$.ajax({
										type: "POST",
										url: "js/script.js",
										dataType: "script"
									});
								},
								function(){
									$.msg("Cancelada la inserción");
								}
							);
						}
					}
				}
			}
		}
	}
}