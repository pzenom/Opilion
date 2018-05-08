// Vector para guardar los estados y las incidencias seleccionadas
var vestados = new Array();
var vincidencias = new Array();
//var vcategoriasE = new Array();
//var vcategoriasS = new Array();
var punto = false;
var unitarioAnterior = 0;
var cantidadAnterior = 0;

$(document).ready(function() {
	tipoRegistro();
	tipo = document.getElementById("idTipoRegistro").value;
	//$.msg("tipo: " + tipo);
	if(tipo=='M') {
		estados = $(".estados");
		for (i = 0; i < estados.length; i++){
			idEstado = estados.get(i).id;
			vestados.push(idEstado.split("est")[1]);
		}
		//$.msg(vestados);
		incidencias = $(".incidencias");
		for (i = 0; i < incidencias.length; i++){
			idIncidencia = incidencias.get(i).id;
			vincidencias.push(idIncidencia.split("inc")[1]);
		}
		//$.msg(vincidencias);
	}
	if ($("previa").val() == "SI"){
		$("#btGuardaOE").show();
		$("#btOtroRE").show();
	}
	tiposReg = $(".tiposReg");
	for (i = 0; i < tiposReg.length; i++){
		id = tiposReg.get(i).id;
		//alert(id);
		tipo = $("#" + id).val();
		//alert(tipo)
		if (tipo == 1){
			$("#opcionMateria").show();
		}else
			if (tipo == 4){
				$("#opcionEnvase").show();
			}
	}
	//alert("sigue");
	$("#opcionProducto").show();
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick", "javascript:volverNuevaOETmp();");
	$("#bot_vuelve").show();
	$("#bot_insertar").attr("onclick", "javascript:inseRE();");
	//$("#bot_insertar").show();
	$("#widget_menu").show();
});

function productoFinalSeleccion(){
	idProducto = 0;
	valor = $("#dropdown_productosFinales").val();
	frag = valor.split('_');
	idProducto = frag[0];
	peso = frag[1];
	if (idProducto <= 0){
		/*$("#contenedorSecundario").hide();
		$("#contenedorAvisoTipoRegistro").show();
		$("#materias").hide();
		$("#envases").hide();
		$("#productosFinales").hide();
		$("#bot_insertar").hide();*/
	}else{
		$("#pesoProductoFinal").val(peso);
		$("#idProductoFinal").val(idProducto);
		ajustaCostoUnitario();
	}
}

function ajustaCostoUnitario(){
	precioKilo = $("#text_precioKilo").val();
	kilos = $("#pesoProductoFinal").val() * $("#text_cantidad").val();
	costoUnitario = $("#text_costoUnitario").val();
	costoTotal = kilos * precioKilo;
	costoTotal = redondearValor(costoTotal, 1000);
	$("#text_costoTotal").val(costoTotal);
	$("#text_costoUnitario").val(redondearValor($("#pesoProductoFinal").val() * precioKilo, 1000));
}

function multiplica() {
	var resultado = ($("#text_cantidad").val()) * ($("#text_costoUnitario").val());
	$("#text_costoTotal").val(Math.round(resultado * 100) / 100);
}

function habilitarEstado(){
	tipoActual = $("#tiporegistro").val();
	//$.msg(tipoActual);
  indice = document.formu.selectIdTipoRegistro.selectedIndex;
	valor = document.formu.selectIdTipoRegistro.options[indice].value;
	textoEscogido = document.formu.selectIdTipoRegistro.options[indice].text;
	if (indice > 0)
		if (textoEscogido == tipoActual){
		}else{
			//$.msg("cambia");
			$("#tiporegistro").val(textoEscogido);
			tipoRegistro();
		}
}

function tipoRegistroSeleccionado(){
	var tipo = $("#dropdown_tipoRegistro").val();
	$("#idTipoRegistro").val(tipo);
	tipoRegistro();
	$("#" + $("#dropdown_proveedores").parent().attr("id")).children('span').text(
		$('#dropdown_proveedores option[value=0]').text());
	if (tipo == 'M' || tipo == 'E'){
		$("#contenedorSecundario").show();
		$("#contenedorAvisoTipoRegistro").hide();
		$("#sectionPrecioKilo").hide();
		$("#text_costoUnitario").removeAttr("disabled");
		/*$("#divProveedores").show();
		$("#divMensajeTipo").hide();*/
		//$.msg(tipo);
		if (tipo == 'M'){//Materias primas
			$("#materias").show();
			$("#materias").attr("style", "visibility: block");
			$("#envases").hide();
			$("#productosFinales").hide();
			//Recorro todas las opciones del select, y oculto las que no son M (id = 1)
			$("#dropdown_proveedores option").each(function(){
				//$.msg('opcion '+$(this).text()+' valor '+ $(this).attr('value'))
				aRomper = $(this).attr('value');
				frag = aRomper.split("_");
				tipo = frag[1];
				//$.msg(frag[0]);
				if (tipo == 4)
					$(this).attr("style", "display: none;");
				else
					if (tipo == 1)
						$(this).attr("style", "");
			});
			$("#bot_insertar").show();
		}else
			if (tipo == 'E'){
				$("#materias").hide();
				$("#envases").show();
				$("#productosFinales").hide();
				//Recorro todas las opciones del select, y oculto las que no son M (id = 1)
				$("#dropdown_proveedores option").each(function(){
					//$.msg('opcion '+$(this).text()+' valor '+ $(this).attr('value'))
					aRomper = $(this).attr('value');
					frag = aRomper.split("_");
					tipo = frag[1];
					//$.msg(frag[0]);
					if (tipo == 1)
						$(this).attr("style", "display: none;");
					else
						if (tipo == 4)
							$(this).attr("style", "");
				});
				$("#bot_insertar").show();
			}
		$("#dropdown_proveedores").val(0);
	}else{
		if (tipo == 'P'){
			$("#sectionPrecioKilo").show();
			$("#contenedorSecundario").show();
			$("#contenedorAvisoTipoRegistro").hide();
			$("#materias").hide();
			$("#envases").hide();
			$("#productosFinales").show();
			$("#bot_insertar").show();
		}else{
			$("#contenedorSecundario").hide();
			$("#contenedorAvisoTipoRegistro").show();
			$("#materias").hide();
			$("#envases").hide();
			$("#productosFinales").hide();
			$("#bot_insertar").hide();
		}
	}
}

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

function guardaRE(){
	if ($("#numeroBultos").val() <= 0){
		$.msg("Debe introducir un número de bultos mayor que cero");
	}else{
		if ($("#cantidad").val() <= 0){
			$.msg("Debe introducir una cantidad mayor que cero");
		}else{
			//comprobamos que haya elegido un tipo de registro
			//var elto = document.getElementById("idTipoRegistro").selectedIndex;
			var elto = document.getElementById("tiporegistro").value;
			if ((elto =='Materia Prima') || (elto =='Envases')) {
				//Has elegido un tipo de registro
				//var producto = document.getElementById("idTipoRegistro").selectedIndex;
				if ((document.getElementById("idProducto").selectedIndex != 0) || (document.getElementById("idEnvase").selectedIndex != 0)) {
					//Has elegido un producto/envase
					//$.msg('Has escogido un producto');
					salvarDatos();
				}
				else {
					$.msg('No has escogido el producto/envase');
				}
			} else {
				$.msg("No ha escogido el tipo de registro aun");
			}
		}
	}
}

function estadoSelec() {
	// Variable que indica si ya se ha insertado o no el elemento seleccionado 0 --> NO EXISTE
	//																		  1 --> EXISTE
	var existe_est=0;
	// Se guarda el indice del elemento seleccionado
	var idest = $("#dropdown_estadosFabas").val();
	//document.getElementById("dropdown_estadosFabas").selectedIndex;
	// Se guardar el texto del elemento seleccionado
	var est = document.getElementById("dropdown_estadosFabas").options[document.getElementById("dropdown_estadosFabas").selectedIndex].text;
	//Comprobar que el elemento no este ya insertado!!
	for(var i = 0; i < vestados.length; i++) {
		//$.msg("vestados: " + vestados[i] + "; idest: " + idest);
		if(vestados[i] == idest) {
			$.msg("Ya ha seleccionado este estado");
			existe_est = 1;
			break;
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
		fila.setAttribute("class", "estados");
		// Crear la columna del estado
		var e = document.createElement('td');
		e.innerHTML = est;
		document.getElementById("tbodyEstados").appendChild(fila);
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
	var f = function() {
		borrarIncidencia(idinc);
	};
	return f;
}

function borrarIncidencia(inc){
	var posBorrar = vincidencias.indexOf(inc);
	vincidencias.splice(posBorrar, 1);
	// Eliminar el elemento de la lista
	var incborrar = document.getElementById("inc" + inc);
	var padre = incborrar.parentNode;
	padre.removeChild(incborrar);
}

function nuevaCategoria(tipo){
	$("#fondo").show();
	$("#msg").show();
	//$("#idMateriaPrima").val(idMateriaPrima);
	//document.getElementById('presenta').innerHTML = 'Categorias para la materia prima con id: ' + idMateriaPrima;
	//document.getElementById('presenta').innerHTML = 'Categorias de ' + nombre;
	document.getElementById('presenta').innerHTML = 
		'<fieldset>' +
			'<legend><span>Nueva categoria</span></legend>' +
				'<table width="100%" cellpadding="2" cellspacing="2" border="0">' +
					'<tr>' +
						'<td class="label"><label name="Nombre" value="Nombre">Nombre</label></td>' +
						'<td nowrap="nowrap">' +
							'<input id="nombreCatego" key="nombreCatego" label="Nombre"/>' +
						'</td>' +
					'</tr>' +
				'</table>' +
		'</fieldset>' +
		'<br />' +
		'<a id="bot_inserta" class="botonInserta" href="javascript:addCategoria(' + tipo + ');" title="Insertar">' +
			'<img src="img/j_button1_add.png" alt="Insertar " title="Insertar"/>Insertar' +
		'</a>' +
		'<br />' +
		'<br />';
	$("#msg").attr("height", "20px");
}

function addCategoria(tipo){
	$("#nombreCategoria").val($("#nombreCatego").val());
	nombre = $("#nombreCategoria").val();
	//$.msg($("#nombreCategoria").val());
	$('#submitSalvaCategoria').trigger('click');
	ocultar();
	var maxSector = 0;
	if (tipo == 1){
		opciones = $("#idCategoriaEntrada").children();
		//$.msg(opciones.length);
		for (i = 0; i < opciones.length; i++){
			opciones[i] = opciones[i].value;
		}
		maxSector = parseFloat(Math.max.apply( Math, opciones)) + 1;
		//$.msg(maxSector);
		if (maxSector == "-Infinity"){
			maxSector = 1;
			//$.msg(maxSector);
		}
		$('#idCategoriaEntrada').append(new Option(nombre, maxSector, true, true));
	}else
		if (tipo == 2){
			opciones = $("#idCategoria").children();
			//$.msg(opciones.length);
			for (i = 0; i < opciones.length; i++){
				opciones[i] = opciones[i].value;
			}
			maxSector = parseFloat(Math.max.apply( Math, opciones)) + 1;
			//$.msg(maxSector);
			if (maxSector == "-Infinity"){
				maxSector = 1;
				//$.msg(maxSector);
			}
			$('#idCategoria').append(new Option(nombre, maxSector, true, true));
		}
}

function inseRE(){
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
									if (tipoReg == "E")
										$url = "InseRE.action?listoDistribuir=false&codigoOrden=" + $("#text_orden").val() +
											"&lote=" + $("#text_lote").val() +
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
									else
										if (tipoReg == "M")
											$url = "InseRE.action?listoDistribuir=false&codigoOrden=" + $("#text_orden").val() +
												"&lote=" + $("#text_lote").val() +
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
												$url = "InseRE.action?listoDistribuir=true&codigoOrden=" + $("#text_orden").val() +
													"&lote=" + $("#text_lote").val() +
													"&cantidad=" + $("#text_cantidad").val() +
													"&numeroBultos=" + $("#text_cantidad").val() +
													//"&numeroPallets=" + $("#text_cantidad").val() +
													"&numeroPallets=1" +
													"&idProducto=" + $("#idProductoFinal").val() +
													"&costoUnitario=" + $("#text_costoUnitario").val() +
													"&costoTotal=" + $("#text_costoTotal").val().split("_")[0] +
													"&fechaLlegada=" + $("#date_fechaRegistro").val() +
													"&temperatura=" + $("#text_temperatura").val() +
													"&humedad=" + $("#text_humedad").val() +
													"&fechaCaducidad=" + $("#date_fechaCaducidad").val() +
													"&idTipoRegistro=" + tipoReg +
													"&idTipoUbicacion=1";
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
											$("#widget_consOE").empty();
											$("#widget_consOE").append(html);
										}
									});
									$.ajax({
										type: "POST",
										url: "registroentrada/nueva2OE.js",
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

function volverNuevaOETmp(){
	$.ajax({
		url: "PreviaRegistroOETmp.action",
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consOE").empty();
			$("#widget_consOE").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/nueva2OE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}