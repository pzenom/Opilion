function habilitarEstado(cadena){
  pat = /Materia/
  if(pat.test(cadena)){
		InseRE.InseRE_idEnvase.disabled = true;
		InseRE.InseRE_idProducto.disabled = false;
		InseRE.InseRE_idCosecha.disabled = false;
		InseRE.InseRE_idCategoriaEntrada.disabled = false;
		InseRE.InseRE_idCategoria.disabled = false;
		InseRE.InseRE_Temperatura.disabled = false;
		InseRE.InseRE_Humedad.disabled = false;
	}else{
  		InseRE.InseRE_idEnvase.disabled = false;
		InseRE.InseRE_idProducto.disabled = true;
		InseRE.InseRE_idCosecha.disabled = true;
		InseRE.InseRE_idCategoriaEntrada.disabled = true;
		InseRE.InseRE_idCategoria.disabled = true;
		InseRE.InseRE_Temperatura.disabled = true;
		InseRE.InseRE_Humedad.disabled = true;
	}
}

function habilitarEstadoActualizar(cadena){
	pat = /Materia/
	if(pat.test(cadena)){
		ActualizarRegistroEntrada.ActualizarRegistroEntrada_idEnvase.disabled = true;
		ActualizarRegistroEntrada.ActualizarRegistroEntrada_idProducto.disabled = false;
		ActualizarRegistroEntrada.ActualizarRegistroEntrada_idCosecha.disabled = false;
		ActualizarRegistroEntrada.ActualizarRegistroEntrada_idCategoriaEntrada.disabled = false;
		ActualizarRegistroEntrada.ActualizarRegistroEntrada_idCategoria.disabled = false;
		ActualizarRegistroEntrada.ActualizarRegistroEntrada_Temperatura.disabled = false;
		ActualizarRegistroEntrada.ActualizarRegistroEntrada_Humedad.disabled = false;
	}else{
		ActualizarRegistroEntrada.ActualizarRegistroEntrada_idEnvase.disabled = false; 
		ActualizarRegistroEntrada.ActualizarRegistroEntrada_idProducto.disabled = true;
		ActualizarRegistroEntrada.ActualizarRegistroEntrada_idCosecha.disabled = true;
		ActualizarRegistroEntrada.ActualizarRegistroEntrada_idCategoriaEntrada.disabled = true;
		ActualizarRegistroEntrada.ActualizarRegistroEntrada_idCategoria.disabled = true;
		ActualizarRegistroEntrada.ActualizarRegistroEntrada_Temperatura.disabled = true;
		ActualizarRegistroEntrada.ActualizarRegistroEntrada_Humedad.disabled = true;
	}
}

function habilitarEstadoCRE(cadena){
	pat = /Materia/
	if(pat.test(cadena)){
		ConsultaEntradas.ConsultaEntradas_idEnvase.disabled = true;
		ConsultaEntradas.ConsultaEntradas_idProducto.disabled = false;
	}else{
		ConsultaEntradas.ConsultaEntradas_idEnvase.disabled = false;
		ConsultaEntradas.ConsultaEntradas_idProducto.disabled = true;
	}
}

//Redirecciona una página
function redirect(linkid){
	opener.location.href = linkid;
	window.close();
}

// levanta una ventana emergente (popup window)
function get_ventana_emergente (input_vent_iden, input_vent_url, input_opci_scrollbars, input_opci_resizable, input_width, input_height, input_position_left, input_position_top){
	// declaracion de variables
	var var_ventana_emergente, var_screen_width, var_screen_height;
	var var_CN_marghori = 15, var_CN_margvert = 60;
	// inicializacion de variables
	var_screen_width = screen.width;
	var_screen_height = screen.height;
	// controla el ancho maximo de la ventana
	if (input_width > var_screen_width-var_CN_marghori){
		input_width = var_screen_width - var_CN_marghori;
		input_position_left = "";
	}
	// controla el alto maximo de la ventana
	if (input_height > var_screen_height-var_CN_margvert){
		input_height = var_screen_height - var_CN_margvert;
		input_position_top = "";
	}
	// si no se ha pasado la posicion horizontal se centra la ventana
	if (input_position_left == "") input_position_left = (var_screen_width - input_width) / 2;
	// si no se ha pasado la posicion vertical se centra la ventana
	if (input_position_top == "") input_position_top = (var_screen_height - input_height) / 2;
	// levanta la ventana emergente
	var_ventana_emergente = window.open (input_vent_url, input_vent_iden, "resizable=" + input_opci_resizable + ",scrollbars=" + input_opci_scrollbars + ",width=" + input_width + ",height=" + input_height + ",left=" + input_position_left + ",top=" + input_position_top);
	// devuelve el objeto puntero a la ventana emergente
	return var_ventana_emergente;
}

//funciones de facturacion
function doCalcCargoIndividual(varcargo,vardscto,vartipocargo) {
	//definicion de variables
	var varcargoindividual=0;
	val1 = varcargo;
	val2 = vardscto;
	varcargoindividual = parseFloat(val1)+((parseFloat(val1) * parseFloat(val2))/100);
	if(vartipocargo=='T'){
		document.forms[0].totalCargoTran.value = varcargoindividual;
	}
	if(vartipocargo=='B'){
		document.forms[0].totalCargoBanc.value = varcargoindividual;
	}	
	if(vartipocargo=='D'){
		document.forms[0].totalCargoDevo.value = varcargoindividual;
	}
}

function doCalcCargoTotal() {
	val1=document.forms[0].totalCargoTran.value;
	val2=document.forms[0].totalCargoBanc.value;
	val3=document.forms[0].totalCargoDevo.value;
	vartotal = parseFloat(val1) + parseFloat(val2)+ parseFloat(val3);
	//$.msg(vartotal);
	document.forms[0].cargosTotal.value = vartotal;
}

function loadjscssfile(filename, filetype){
	if (filetype=="js"){ //if filename is a external JavaScript file
		var fileref=document.createElement('script');
		fileref.setAttribute("type","text/javascript");
		fileref.setAttribute("src", filename);
	}else
		if (filetype=="css"){ //if filename is an external CSS file
			var fileref=document.createElement("link");
			fileref.setAttribute("rel", "stylesheet");
			fileref.setAttribute("type", "text/css");
			fileref.setAttribute("href", filename);
		}
	if (typeof fileref!="undefined")
		document.getElementsByTagName("head")[0].appendChild(fileref);
}

function ResaltarFila(id_tabla){
    if (id_tabla == undefined)// si no se le pasa parametro
        // recupera todas las filas de todas las tablas
        var filas = document.getElementsByTagName("tr");
    else{
        // recupera todas las filas de la tabla indicada en el parametro
        var tabla = document.getElementById(id_tabla);
        var filas = tabla.getElementsByTagName("tr");
    }
    // recorre cada una de las filas
    for(var i in filas) {
        // si el puntero esta encima de la fila asigna la regla css: resaltar
        filas[i].onmouseover = function() {
			//$.msg(this.previousSibling.attribute("class"));
			previo = this.previousSibling;
			clase = "odd";
			if (previo != null)
				 clase = previo.className;
			//$.msg(clase);
			if (previo == null)
				this.className = "odd resaltar";
			else{
				if (clase == "odd")
					this.className = "even resaltar";
				else
					this.className = "odd resaltar";
			}
        }
        // si el puntero salga de la fila asigna ninguna regla
        filas[i].onmouseout = function() {
			frag = this.className.split(" ");
			clase = frag[0];
            this.className = clase;
        }
    }
}

function validarSoloNumeros(e){
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 8)//Borrar
		return true;
	if (tecla == 0)//Tab
		return true;
	patron = /[0-9]/;
	te = String.fromCharCode(tecla);//defino los arrays
	opciones = {
		header: 'Aviso',
		live: 5000,
		topoffset: 90,
		fadeTime: 500,
		sticky: false,
		background: '#FAAC58',
		colorLetra: '#F1F1F1'
	};
	if (patron.test(te) == false)
		$.msg("Inserte solo dígitos", opciones);
	return patron.test(te);
}

function validarNumerosDecimales(idInput, e){
	input = $("#" + idInput);
	frag = input.val().split('.');
	punto = false;
	if (frag.length > 1)//Ya había punto decimal
		punto = true;
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 46 && punto){//46 = punto
		$.msg("Solo se permite un punto decimal");
		return false;
	}
	if (tecla == 8)//Borrar
		return true;
	if (tecla == 0)//Tab
		return true;
	patron = /[0-9.]/;
	te = String.fromCharCode(tecla);//defino los arrays
	if (patron.test(te) == false)
		$.msg("Inserte solo números (Decimales separados por punto)");
	return patron.test(te);
}

function validarSinComas(e){
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 44){//Coma
		$.msg("No es posible introducir comas");
		return false;
	}
	return true;
}

function ajustarDecimal(idInput){
	//alert(idInput);
	input = $("#" + idInput);
	frag = input.val().split('.');
	if (frag.length > 1 && frag[1] == "")
		input.val(input.val() + "0");
	else
		if (frag.length > 1 && frag[0] == "")
			input.val("0" + input.val());
	if (input.val() == "")
		input.val(0);
}

function calcularMD5(idCampo){
	return hex_md5($('#' + idCampo).val());
}

function guardarConfiguracionPersonal(){
	var $url = "";
	$url = "EditarInformacionUsuario.action?nuevoLogin=" + $("#nuevoNombre").val() + "&login=" + $("#loginUsuario").val() +
		"&password=" + calcularMD5("nuevoPass");
	$.ajax({
	  url: $url,
	  cache: false,
	  async:false,
		success: function(html){
			$("#mensajeResultadoConfiguracion").show();
		},
		error: function(){
			$.msg("Error modificando los datos");
		}
	});
}

function volverAlEscritorio(){
	$.ajax({
		url: "InicioAjax.action",
		cache: false,
		async:false,
		success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "pagEscritorio.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "gestionCalendarioEscritorio.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function nuevaEntidad(){
	//$("#mydiv").show();
$.blockUI({ message: '<h1>Cargando...</h1>' });
	$url = "RegistroEntidad.action";
	setTimeout("" + 
		"$.ajax({" +
			"url: $url," +
			"cache: false," +
			"async:false," +
			"success: function(html){" +
				"if ($('#widget_consCli').length){" +
					"$('#widget_consCli').empty();" +
					"$('#widget_consCli').append(html);" +
				"}else{" +
					"if ($('#widget_consProv').length){" +
						"$('#widget_consProv').empty();" +
						"$('#widget_consProv').append(html);" +
					"}" +
				"}" +
				"$.ajax({" +
					"type: 'POST'," +
					"url: 'entidad/registroEntidad.js'," +
					"dataType: 'script'" +
				"});" +
				"$.ajax({" +
					"type: 'POST'," +
					"url: 'js/script.js'," +
					"dataType: 'script'" +
				"});" +
				//'$("#mydiv").hide();
				'$.unblockUI(); '+
			"}" +
		"});",250);
}

function cargarEntidad(idUsuario){
	//alert(1);
	//$("#mydiv").show();
$.blockUI({ message: '<h1>Cargando...</h1>' });
	$url = "CargarEntidad.action?idUsuario=" + idUsuario;
	//alert($url);
	setTimeout("" + 
		"$.ajax({" +
			"url: $url," +
			"cache: false," +
			"async:false," +
			"success: function(html){" +
				"if ($('#widget_consCli').length){" +
					"$('#widget_consCli').empty();" +
					"$('#widget_consCli').append(html);" +
				"}else{" +
					"if ($('#widget_consProv').length){" +
						"$('#widget_consProv').empty();" +
						"$('#widget_consProv').append(html);" +
					"}" +
				"}" +
				"$.ajax({" +
					"type: 'POST'," +
					"url: 'entidad/actualizaEntidad.js'," +
					"dataType: 'script'" +
				"});" +
				"$.ajax({" +
					"type: 'POST'," +
					"url: 'js/script.js'," +
					"dataType: 'script'" +
				"});" +
				//'$("#mydiv").hide();
				'$.unblockUI(); '+
			"}" +
		"});",250);
}

function muestraConsulta(){
	if ($("#consulta").is(':visible')){
		$("#consulta").hide();
		$("#bot_consulta").addClass("ocultando");
		$("#bot_consulta").removeClass("mostrando");
	}
	else{
		$("#consulta").show();
		$("#bot_consulta").addClass("mostrando");
		$("#bot_consulta").removeClass("ocultando");
	}
}

function IsNumeric(sText){
	var ValidChars = "0123456789";
	var IsNumber = true;
	var Char;
	for (i = 0; i < sText.length && IsNumber == true; i++){
		Char = sText.charAt(i);
		if (ValidChars.indexOf(Char) == -1){
			IsNumber = false;
		}
	}
	return IsNumber;
}

function formatearNumeroMilesDecimales(numero){
	numero = "" + numero;
	nuevaCadena = "";
	frag = numero.split(".");
	numero = frag[0];
	longitud = numero.length;
	contador = 0;
	for (i = longitud - 1; i >= 0; i--){
		nuevaCadena = numero.substring(i, parseFloat(i) + 1) + nuevaCadena;
		contador++;
		if (contador == 3 && i > 0){
			contador = 0;
			nuevaCadena = '.' + nuevaCadena;
		}
	}
	if (frag.length > 1){
		return (nuevaCadena + "," + frag[1]);
	}else
		return (nuevaCadena + ",0");
}

function soloDosDecimales (mi_numero) {
	mi_numero = mi_numero * 100;
	mi_numero = Math.floor(mi_numero);
	mi_numero = mi_numero / 100;
	return mi_numero;
}

/* FUNCIONES TREEVIEW */
function grupoCategorizacionSeleccionado(idGrupo){
	//alert("hola");
	//alert(idGrupo);
	//Miramos los hijos de idGrupo
	//alert(idGrupo);
	//alert($("#lista_" + idGrupo).children());
	cuantosHijos = $("#lista_" + idGrupo).children().length;
	//alert(idGrupo + " - " + cuantosHijos);
	//Si tiene hijos, no es nodo final, pasamos de él
	//alert(cuantosHijos);
	if (cuantosHijos == 0){
		$(".temp").remove();
		$(".treeViewNOSeleccion").removeClass("treeViewSeleccion");
		$("#" + idGrupo).addClass("treeViewSeleccion");
		$("#breadcrum_grupo_seleccionar").hide();
		texto = "";
		id = "";
		idAux = "0";
		hijo = $("#" + idGrupo).children(0);
		//alert(hijo);
		texto = hijo.text();
		var bread = document.createElement("li");
		bread.setAttribute("id", "bread_" + idGrupo);
		bread.setAttribute("class", "temp");
		bread.innerHTML =
			'<a onclick="javascript:muestraSeleccionarGrupo();" style="color: red;">' + texto + '</a>';
		$('#breadcrum_grupo_seleccionar').after(bread);
		//alert("texto: " + texto);
		//alert("ID: " + id);
		//alert("y el idAux es " + idAux);
		while (true){
			//alert(1);
			idAux = $("#" + idGrupo).parent().get(0).id;
			//alert("idAux: " + idAux);
			if (idAux == "categorizacion")
				break;
			frag = idAux.split("_");
			idAux2 = frag[1];
			//alert("idAux2: " + idAux2);
			span = $("#" + idAux2).children().get(1);
			//alert(span);
			//alert(span.id);
			//alert($("#" + span.id).text());
			var bread = document.createElement("li");
			bread.setAttribute("id", "bread_" + idAux2);
			bread.setAttribute("disabled", "disabled");
			bread.setAttribute("class", "temp");
			bread.innerHTML =
				'<a onclick="">' + $("#" + span.id).text() + '</a>';
			$('#breadcrum_grupo_seleccionar').after(bread);
			idGrupo = idAux2;
			//alert("idGrupo: " + idGrupo);
		}
		//alert(2);
		//alert("texto: " + texto);
		$("#breadcrumb_grupo").show();
		$("#treeView_grupo").hide();
	}
}

function productoSeleccionado(idProducto){
	idProductoOriginal = idProducto;
	//alert(idProducto);
	$(".temp").remove();
	$(".treeViewNOSeleccion").removeClass("treeViewSeleccion");
	$("#producto_" + idProducto).addClass("treeViewSeleccion");
	$("#breadcrum_grupo_seleccionar").hide();
	texto = "";
	hijo = $("#producto_" + idProducto).children(0);
	//alert(hijo);
	texto = hijo.text();
	//alert(texto);
	var bread = document.createElement("li");
	bread.setAttribute("id", "bread_" + idProducto);
	bread.setAttribute("class", "temp");
	bread.innerHTML =
		'<a onclick="javascript:muestraSeleccionarGrupo();" style="color: red;">' + texto + '</a>';
	$('#breadcrum_grupo_seleccionar').after(bread);
	//alert("texto: " + texto);
	primeraPasada = true;
	while (true){
		//alert(1);
		//alert($("#producto_" + idProducto).parent().get(0).id);
		if (primeraPasada){
			idAux = $("#producto_" + idProducto).parent().get(0).id;
			primeraPasada = false;
		}else{
			idAux = $("#" + idProducto).parent().get(0).id;
		}
		//alert("idAux: " + idAux);
		if (idAux == "categorizacion")
			break;
		frag = idAux.split("_");
		idAux2 = frag[1];
		//alert("idAux2: " + idAux2);
		span = $("#" + idAux2).children().get(1);
		//alert(span);
		//alert(span.id);
		//alert($("#" + span.id).text());
		var bread = document.createElement("li");
		bread.setAttribute("id", "bread_" + idAux2);
		bread.setAttribute("disabled", "disabled");
		bread.setAttribute("class", "temp");
		bread.innerHTML =
			'<a onclick="">' + $("#" + span.id).text() + '</a>';
		$('#breadcrum_grupo_seleccionar').after(bread);
		idProducto = idAux2;
		//alert("idProducto: " + idProducto);
	}
	//alert(texto);
	$("#breadcrumb_grupo").show();
	$("#treeView_grupo").hide();
	$("#bot_aceptarProducto").attr("onclick", "aceptarProducto(" + idProductoOriginal + ")");
	$("#bot_aceptarProducto").show();
}

function muestraSeleccionarGrupo(){
	$("#breadcrumb_grupo").hide();
	$("#treeView_grupo").show();
}
/* END FUNCIONES TREEVIEW */

//decimales: si queremos dos decimales: 100; si queremos 5 decimales: 100000
function redondearValor(valor, decimales){
	return Math.round(parseFloat(valor) * decimales) / decimales;
}

function ajustarRol(){
	rolesUsuario = $('.rolUsuario');
	acciones = $('.accionRol');
	//alert(rolesUsuario.length);
	for (k = 0; k < rolesUsuario.length; k++){
		id = rolesUsuario[k].id;
		frag2 = id.split('_');
		rolUsuario = frag2[1];
		//alert(rolUsuario);
		for (i = 0; i < acciones.length; i++){
			id = acciones[i].id;
			frag = id.split('_');
			idAccion = frag[1];
			idRol = frag[2];
			if (idRol == rolUsuario){
				idsRelacionados = $('.accionIdRelacionado_' + idAccion);
				for (j = 0; j < idsRelacionados.length; j++){
					id = idsRelacionados[j].id;
					idRelacionado = $('#' + id).val();
					clases = $('#' + idRelacionado).attr('class');
					if (clases != null){
						//Miramos si es un boton de la botonera
						if (parseFloat(clases.indexOf("botonBotonera")) > -1){
							$('#' + idRelacionado).addClass('puedeVer');
						}else{
							$('#' + idRelacionado).show();
						}
					}else{
						$('#' + idRelacionado).show();
					}
				}
				clasesRelacionadas = $('.accionClaseRelacionada_' + idAccion);
				for (j = 0; j < clasesRelacionadas.length; j++){
					id = clasesRelacionadas[j].id;
					claseRelacionada = $('#' + id).val();
					//alert(claseRelacionada);
					if (claseRelacionada == 'facturaCalendario'){
						clase = $('.' + claseRelacionada);
						//alert(clase.length);
						for (h = 0; h < clase.length; h++){
							id = clase[h].id;
							//alert(id);
							$('#' + id).show();
							$('#' + id).removeClass('facturaCalendario');
						}
					}else{
						if (claseRelacionada == 'envasadoCalendario'){
							clase = $('.' + claseRelacionada);
							//alert(clase.length);
							for (h = 0; h < clase.length; h++){
								id = clase[h].id;
								//alert(id);
								$('#' + id).show();
								$('#' + id).removeClass('envasadoCalendario');
							}
						}else{
							$('.' + claseRelacionada).show();
						}
					}
				}
			}
		}
	}
}