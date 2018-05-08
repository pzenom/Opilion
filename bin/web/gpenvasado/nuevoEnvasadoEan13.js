//defino los arrays
var materiaPrima = new Array();
var envases = new Array();
var envasesAgrupar = new Array();
var fondo = false;
var mensaje = false;

$("#loteAsignado").hide();
$("#text_cantidadElaborada").hide();
$('#electedIngredients').hide();
$('#bot_ingrediente').hide();
$('#electedPackages').hide();
//$('#escogerEnvases').hide();
$('#electedPackagesAgrupar').hide();
//$('#escogerEnvasesAgrupar').hide();
$("#dropdown_productos").selectedIndex = 1;
$("#dropdown_productos option[value='" + $("#dropdown_productosValor").val() + "']").attr("selected", "selected");
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
comprobarAgruparItems();
/* Configuramos la botonera */
$("#bot_insertar").hide();

$(document).ready(function(){

});

function comprobarAgruparItems(){
	stock = $("#stockEnvaseAgrupar").val();
	agrupar = $("#agrupar").val();
	//alert(stock);
	//alert(agrupar);
	if (agrupar == "true" || agrupar == true){
		/*MOSTRAMOS EL BOTON AYUDA*/
		$("#bot_ayuda").show();
		if (stock == 0){
			$.confirm("No dispones de envases en los que agrupar los items que se vayan a envasar. &#191Quieres envasarlos sin agruparlos?",
				function(){
					$("#agrupar").val("false");
					comprobarAgruparItems();
					//$("#generalEnvasesAgrupar").hide();
				},
				function(){
					nuevoGPEnva();
				}
			);
		}
	}
	agrupar = $("#agrupar").val();
	//alert(agrupar);
	if (agrupar == "false" || agrupar == false){
		//$("#generalEnvasesAgrupar").hide();
		$("#sectionAgrupaciones").hide();
		$("#sectionAvisoSeleccionAgrupacion").hide();
		$(".fieldsetEnvasado").show();
		autoseleccionEnvases();
	}else{
		//$("#generalEnvasesAgrupar").show();
		cargarAgrupaciones();
		$("#sectionAgrupaciones").show();
		if ($('#dropdown_agrupaciones').val() > 0){
			agrupacionSeleccionada();
		}else{
			$("#sectionAvisoSeleccionAgrupacion").show();
			$(".fieldsetEnvasado").hide();
		}
	}
}

function agrupacionSeleccionada(){
	$(".encabezadosEnvases").show();
	$(".filaEnvase").show();
	idAgrupacion = $("#dropdown_agrupaciones").val();
	//alert(idAgrupacion);
	if (parseFloat(idAgrupacion) > 0){//OK
		$(".fieldsetEnvasado").show();
		//$("#sectionAgrupaciones").hide();
		$("#sectionAvisoSeleccionAgrupacion").hide();
		//Si el boton insertar tiene la clase "insertando", lo mostramos. si no, nada
		if ($('#bot_insertar').hasClass('insertando')){
			$('#bot_insertar').show();
		}
		//Ocultar los envases para otras agrupaciones distintas a idAgrupacion
		envases = $(".filaEnvase").get();
		for (i = 0; i < envases.length; i++){
			id = envases[i].id;
			//alert(id);
			frag = id.split('_');
			if (frag.length == 2){//Encontramos un envase para agrupacion
				idAgrupacionEnvase = frag[1];
				if (idAgrupacionEnvase > 0){
					//alert("idAgrupacion: " + idAgrupacion + ". idAgrupacionEnvase: " + idAgrupacionEnvase);
					//alert(id);
					if (idAgrupacion != idAgrupacionEnvase){//Si el envase no es para la agrupacion seleccionada...
						//alert(idAgrupacion);
						//alert("NOOOOOOOOOOOO te vale");
						$(".encabezadoEnvase_" + idAgrupacionEnvase).hide();
						$("#" + id).hide();
					}
				}
			}
		}
	}else{//MAL
		//Si el boton insertar estaba visible, añadimos una clase al boton ("insertando")
		if ($('#bot_insertar').is(":visible")){
			$('#bot_insertar').addClass("insertando");
			$('#bot_insertar').hide();
		}
		$(".fieldsetEnvasado").hide();
		$("#sectionAvisoSeleccionAgrupacion").show();
		$.msg("Seleccione un EAN14 para poder continuar");
	}
	autoseleccionEnvases();
}

function cargarAgrupaciones(){
	//Leemos las agrupaciones
	var agrupaciones = $(".agrupacionPosibleOculta").get();
	var cuantas = agrupaciones.length;
	$("#optgroupAgrupaciones").empty();
	//alert(cuantas);
	var idAgrupacion;
	for (i = 0; i < cuantas; i++){
		idAux = agrupaciones[i].id;
		//alert(idAux);
		//idAux = idAgrupacion_73
		frag = idAux.split("_");
		idAgrupacion = frag[1];
		//alert(idAgrupacion);
		nombreAgrupacion = $("#" + idAux).val();
		//alert(nombreAgrupacion);
		var selected = false;
		if (cuantas == 1){
			selected = true;
		}
		o = new Option(nombreAgrupacion, nombreAgrupacion, selected, selected);
		//alert(o);
		o.id = "agrupacionPosible_" + idAgrupacion;
		o.value = idAgrupacion;
		$("#optgroupAgrupaciones").append(o);
	}
	if (cuantas == 1){
		$("#dropdown_agrupaciones").val(idAgrupacion);
		$("#" + $("#dropdown_agrupaciones").parent().attr("id")).children('span').text($("#dropdown_agrupaciones option[value="+ idAgrupacion +"]").text());
	}
}

function calcularTotalesIngredientes(){
	total = 0;
	teoricas = $(".teorica_ing").get();
	for (i = 0; i < teoricas.length; i++){
		id = teoricas[i].id;
		teorica = $("#" + id).val();
		total += parseFloat(teorica);
	}
	$("#totalIngredientes").html(total);
}

function calcularTotalesEnvases(){
	total = 0;
	teoricas = $(".teorica_env").get();
	for (i = 0; i < teoricas.length; i++){
		id = teoricas[i].id;
		teorica = $("#" + id).val();
		total += parseFloat(teorica);
	}
	$("#totalEnvases").html(total);
}

/******************************************************/
/* 		FUNCIONAMIENTO DEL FORMULARIO 			 */
/******************************************************/
function insertaMe(i) {
	//$.msg(i);
	//oculto el ingrediente
	$('#escogerIngredientes' + i).hide();	
	// Crear una fila
	var fila = document.createElement('tr');
	// Se le añade un id dependiente del ingrediente seleccionado
	fila.setAttribute("id", "electedIngredientsBodyRow" + i);
	fila.setAttribute("class", "ingredienteEscogido");
	// Crear la columna de la materia prima usada
	fila.innerHTML =
		'<td>' +
			$('#nombre_'+i).val() +
		'</td>' +
		'<td>' +
			i +
		'</td>' +
		'<td id="disponible_' + i + '">' +
			$('#saldo_'+i).text() +
		'</td>' +
		'<td>' +
			'<input class="teorica_ing" name="teorica_ing_' + i + '" id="teorica_ing_' + i + '" type="text" value="' + $('#saldo_' + i).text() + '"  onblur="javascript:ajustarDecimal(' + "'teorica_ing_" + i + "'" + ');" onchange="javascript:comprobarDisponible(' + "'" + i + "'" + ');" onkeypress="return validarNumerosDecimales(' + "'teorica_ing_" + i + "'" + ', event);" style="text-align: right; width: 92%;" />' +
		'</td>' +
		'<td>' +
			$('#proveedorMP' + i).text() +
		'</td>' +
		'<td>' + $('#loteMP' + i).text() + '</td><td><a id="elimina" href="javascript:eliminaElectedIngredient(\'' + i + '\')" title="Eliminar este ingrediente"><img src="img/cancel.png" alt="Eliminar" title="Eliminar ingrediente"/></a></td>';
	$('#electedIngredientsBody').prepend(fila);	
	//metemos el valor en el array
	materiaPrima.push(i);	
	if($('.filaIngre:visible').length == 0){
		//$.msg("Mo quedan posibles ingredientes");
		$('#escogerIngredientes').slideUp('slow');
	}
	previos = $("#escogerIngredientes" + i).prevAll();
	var canti = 1;
	for (j = 0; j < previos.length; j++){
		//$.msg(previos[j].id);
		//$.msg(previos[j].id.indexOf("Nuevo", 0));
		if (previos[j].id.indexOf("Nuevo", 0) > -1){
			nodo = $("#" + previos[j].id).children(1);
			canti = nodo.children(0).val();
			//$.msg(canti);
			break;
		}
	}
	//$.msg(disponible);
	var disponible = $('#disponible_' + i).text();
	//$.msg("dispo: " + disponible);
	var cantidad = $("#text_cantidad").val();//* $("#cantidad_" + i).val();
	//$.msg("cantidad: " + cantidad);
	var porcentaje = 0.02;
	var extra = cantidad * porcentaje;
	//$.msg(extra);
	cantidad = Math.ceil(parseFloat(canti) * (parseFloat(cantidad) + parseFloat(extra)));
	//$.msg(cantidad);
	//$.msg(disponible);
	//var celda = $(this).attr('id');
	if (disponible >= cantidad)
		$("#teorica_ing_" + i).val(cantidad);
	else
		$("#teorica_ing_" + i).val(disponible);
	ocultaEscogerIngredientes();
	comprueba();
	calcularTotalesIngredientes();
}

/******************************************************/
/* 		FUNCIONAMIENTO DEL FORMULARIO 			 */
/******************************************************/
function insertaEAN13(i) {
	//$.msg(i);
	//oculto el ingrediente
	$('#escogerIngredientes' + i).hide();	
	// Crear una fila
	var fila = document.createElement('tr');
	// Se le añade un id dependiente del ingrediente seleccionado
	fila.setAttribute("id", "electedIngredientsBodyRow" + i);
	fila.setAttribute("class", "itemEscogido");
	// Crear la columna de la materia prima usada
	fila.innerHTML =
		'<td>' +
			$('#nombre_'+i).val() +
		'</td>' +
		'<td>' +
			i +
		'</td>' +
		'<td id="disponible_' + i + '">' +
			$('#saldo_'+i).text() +
		'</td>' +
		'<td>' +
			'<input class="teorica_EAN13" name="teorica_EAN13_' + i + '" id="teorica_EAN13_' + i + '" type="text" value="' + $('#saldo_' + i).text() + '"  onblur="javascript:ajustarDecimal(' + "'teorica_EAN13_" + i + "'" + ');" onchange="javascript:comprobarDisponible(' + "'" + i + "'" + ');" onkeypress="return validarNumerosDecimales(' + "'teorica_EAN13_" + i + "'" + ', event);" style="text-align: right; width: 92%;" />' +
		'</td>' +
		'<td>' +
			$('#proveedorMP' + i).text() +
		'</td>' +
		'<td>' + $('#loteMP' + i).text() + '</td><td><a id="elimina" href="javascript:eliminaElectedIngredient(\'' + i + '\')" title="Eliminar este ingrediente"><img src="img/cancel.png" alt="Eliminar" title="Eliminar ingrediente"/></a></td>';
	$('#electedIngredientsBody').prepend(fila);	
	//metemos el valor en el array
	materiaPrima.push(i);	
	if($('.filaIngre:visible').length == 0){
		//$.msg("Mo quedan posibles ingredientes");
		$('#escogerIngredientes').slideUp('slow');
	}
	previos = $("#escogerIngredientes" + i).prevAll();
	var canti = 1;
	for (j = 0; j < previos.length; j++){
		//$.msg(previos[j].id);
		//$.msg(previos[j].id.indexOf("Nuevo", 0));
		if (previos[j].id.indexOf("Nuevo", 0) > -1){
			nodo = $("#" + previos[j].id).children(1);
			canti = nodo.children(0).val();
			//$.msg(canti);
			break;
		}
	}
	//$.msg(disponible);
	var disponible = $('#disponible_' + i).text();
	//$.msg("dispo: " + disponible);
	var cantidad = $("#text_cantidad").val();//* $("#cantidad_" + i).val();
	//$.msg("cantidad: " + cantidad);
	var porcentaje = 0.02;
	var extra = cantidad * porcentaje;
	//$.msg(extra);
	cantidad = Math.ceil(parseFloat(canti) * (parseFloat(cantidad) + parseFloat(extra)));
	//$.msg(cantidad);
	//$.msg(disponible);
	//var celda = $(this).attr('id');
	if (disponible >= cantidad)
		$("#teorica_EAN13_" + i).val(cantidad);
	else
		$("#teorica_EAN13_" + i).val(disponible);
	ocultaEscogerIngredientes();
	comprueba();
	calcularTotalesIngredientes();
}

function insertaENV(i, idAgrupacion) {
	//oculto el ingrediente
	$('#escogerEnvases' + i + "_" + idAgrupacion).hide();	
	// Crear una fila
	var fila = document.createElement('tr');
	// Se le añade un id dependiente del envase seleccionado
	fila.setAttribute("id", "electedEnvasesBodyRow" + i + "_" + idAgrupacion);
	fila.setAttribute("class", "envaseEscogido");
	// Crear la columna del envase usada
	fila.innerHTML =
		'<td>' +
			$('#nombre_' + i + "_" + idAgrupacion).val() +
		'</td>' +
		'<td>' +
			i +
		'</td>' +
		'<td id="disponible_' + i + "_" + idAgrupacion + '">' +
			$('#saldo_' + i + "_" + idAgrupacion).text() +
		'</td>' +
		'<td>' +
			'<input class="teorica_env" name="teorica_env_' + i + "_" + idAgrupacion + '" id="teorica_env_' + i + "_" + idAgrupacion + '" type="text" value="' + $('#saldo_' + i + "_" + idAgrupacion).text() + '" onchange="javascript:comprobarDisponibleEnvase(' + "'" + i + "'" + ',' + "'" + idAgrupacion + "'" +');" onblur="javascript:ajustarDecimal(' + "'teorica_env_" + i + "_" + idAgrupacion + "'" + ');" onkeypress="return validarSoloNumeros(event);" style="text-align: right; width: 92%;" />' +
		'</td>' +
		/*'<td>0</td>' +
		'<td>0</td>' +*/
		'<td>' +
			$('#proveedorEN' + i + "_" + idAgrupacion).text() + '</td>' +
		'<td>' +
			$('#loteEN' + i + "_" + idAgrupacion).text() +
		'</td>' +
		'<td>' +
			'<a id="elimina" href="javascript:eliminaElectedEnvase(\'' + i + "_" + idAgrupacion + '\')" title="Eliminar este ingrediente">' +
				'<img src="img/cancel.png" alt="Eliminar" title="Eliminar ingrediente"/>' +
			'</a>' +
		'</td>';
	$('#electedEnvasesBody').prepend(fila);
	//metemos el valor en el array
	envases.push(i + "_" + idAgrupacion);
	if($('.filaEnvase:visible').length == 0){
		//$.msg("Mo quedan posibles envases");
		$('#escogerEnvases').slideUp('slow');
	}
	var disponible = $('#disponible_' + i + "_" + idAgrupacion).text();
	var cantidad = ($("#cantidad_" + i + "_" + idAgrupacion).val() * $("#text_cantidad").val());
	var porcentaje = 0.02;
	var extra = cantidad * porcentaje;
	cantidad = Math.ceil(cantidad + extra);
	if (disponible >= cantidad)
		$("#teorica_env_" + i + "_" + idAgrupacion).val(cantidad);
	else
		$("#teorica_env_" + i + "_" + idAgrupacion).val(disponible);
	ocultaEscogerEnvases();
	comprueba();
	calcularTotalesEnvases();
}

function eliminaElectedIngredient(i){
	if (confirm("Quitar el ingrediente " + i)){
		//$.msg("Borrando "+i);
		if($('#disponibleMP tbody tr:visible').length==0) {
			//$.msg("Mo queda materia prima");
			$('#escogerIngredientes').slideDown('slow');
		}
		$('#escogerIngredientes' + i).show();
		$('#electedIngredientsBodyRow' + i).remove();
		//debemos eliminarlo del array
		materiaPrima.splice(materiaPrima.indexOf(i), 1);
		//$.msg("MP: "+materiaPrima);
		calcularTotalesIngredientes();
	}
}

function eliminaElectedEnvase(i){
	if (confirm("Quitar el envase "+i)){
		//$.msg("Borrando "+i);
		if($('#disponibleENV tbody tr:visible').length==0) {
			//$.msg("Mo quedan posibles envases");
			$('#escogerEnvases').slideDown('slow');
		}
		$('#escogerEnvases' + i).show();
		$('#electedEnvasesBodyRow' + i).remove();
		//debemos eliminarlo del array
		envases.splice(envases.indexOf(i), 1);
		//$.msg("ENV: "+envases);
		calcularTotalesEnvases();
	}
}

function ocultaEscogerEnvases() {
	$('#escogerEnvases').slideUp("slow");
	$('#bot_envase').show();
}

function ocultaEscogerIngredientes() {
	$('#escogerIngredientes').slideUp("slow");
	$('#bot_ingrediente').show();
}

function ocultarEnvasado() {
	$('#nuevoEnvasado').slideUp('slow');
	$("#pending").slideDown("slow");
}

function cambioProducto() {
	$("#electedIngredients").slideDown("slow");
	//Ocultar la posibilidad de envasar
	$('#electedPackages').slideUp('slow');
}

function insertaIngrediente() {
	$('#bot_ingrediente').hide();
	$('#escogerIngredientes').slideDown("slow");
}

function insertaEnvase() {
	$('#bot_envase').hide();
	$('#escogerEnvases').slideDown("slow");
}

/******************************************************/
/* 		VALIDACION DEL FORMULARIO 				 */
/******************************************************/
function comprueba() {
	//si al menos hay una tupla en ingredientes
	if($('#electedIngredientsTable tbody tr').length > 1) {
		//y si al menos hay una tupla en envases
		if($('#electedEnvasesTable tbody tr').length > 1) {
			//$.msg("HABEMUS ENVASADO SIN VALIDAR UNIDADES");
			$('#bot_insertar').show();
			//comprobarAgruparItems();
		} //else{$.msg("No ha elegido un envase");}
	} //else{$.msg("No ha elegido un Ingrediente");}
}

function ayuda(){
	fondo = document.createElement('div');
	mensaje = document.createElement('div');
	fondo.setAttribute('id','fondo');
	mensaje.setAttribute('id','msg');
	$('body').append(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
	$("#fondo").height($(document).height());
	mensaje.innerHTML =
		'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar(true);">X</span></div>' +
		'<p style="font-size: 15px; text-align: center;">PROCESO DE ENVASADO. Envasando EANs13 y agrup&aacute;ndolos al mismo tiempo.</p>' +
		'<p style="text-align: center;">Cantidad: La cantidad ser&aacute; el n&uacute;mero de EANs13 que vamos a envasar, no el n&uacute;mero de EANs14.</p>' +
		'<p style="text-align: center;">Materias primas: Seleccionar los kilogramos de los ingredientes que componen el <b>EAN13</b>.</p>' +
		'<p style="text-align: center;">Envases: Seleccionar los envases para los <b>EANs13</b>. Seleccionar los envases para los <b>EANs14</b>.</p>';
}

function cerrarMensajeLeerLoteBulto(){
	$('#fondo').remove();
	document.getElementsByTagName('body')[0].removeChild(mensaje);
	fondo = false;
	mensaje = false;
}

function comprobarDisponible(registro){
	//alert(registro);
	//Comparar
	disponible = $('#disponible_' + registro).html().trim();
	//alert(disponible);
	seleccion = $('#teorica_ing_' + registro).val();
	//alert(seleccion);
	if (parseFloat(seleccion) > parseFloat(disponible)){
		$.msg("La cantidad seleccionada supera la disponible");
		$('#teorica_ing_' + registro).val(parseFloat(disponible));
	}
	calcularTotalesIngredientes();
}

function comprobarDisponibleEnvase(registro, idAgrupacion){
	//alert(registro);
	//Comparar
	disponible = $('#disponible_' + registro + '_' + idAgrupacion).html().trim();
	//alert(disponible);
	seleccion = $('#teorica_env_' + registro + '_' + idAgrupacion).val();
	//alert(seleccion);
	if (parseFloat(seleccion) > parseFloat(disponible)){
		$.msg("La cantidad seleccionada supera la disponible");
		$('#teorica_env_' + registro + '_' + idAgrupacion).val(parseFloat(disponible));
	}
	calcularTotalesEnvases();s
}