//defino los arrays
var materiaPrima = new Array();
var envases = new Array();


	$("#loteAsignado").hide();
	$("#cantidadElaborada").hide();
	$('#electedProductos').hide();
	$('#escogerProductos').hide();
	$('#electedPackages').hide();
	$('#escogerEnvases').hide();
	$("#dropdown_productos").selectedIndex = 1;
	$("#dropdown_productos option[value='" + $("#dropdown_productosValor").val() + "']").attr("selected", "selected");
	$("#viejo").hide();
	if($('#dropdown_productos').val() == 0) {
		$("#nuevoEnvasado").hide();
	} else {
		$("#pending").hide();
		$('#electedProductos').show();
		$('#electedPackages').show();
	}
	$("#nuevoEnvasado").show();			
	$("#pending").hide();
	
	//aux = alert($('.encabezadosEnvases').next('tr').first('').get(0).id.split('escogerEnvases'));
	
	alert(321);
	aux = $('.encabezadosEnvases').next('tr').first('').get(0).id.split('escogerEnvases');
	alert(aux);
	
$(document).ready(function(){

});

/******************************************************/
/* 		FUNCIONAMIENTO DEL FORMULARIO 			 */
/******************************************************/
function insertaMe(i, idProducto) {
	//$.msg(i);
	//oculto el producto
	$('#escogerProductos' + i).hide();	
	// Crear una fila
	var fila = document.createElement('tr');
	// Se le añade un id dependiente del producto seleccionado
	fila.setAttribute("id", "electedProductosBodyRow" + i);
	fila.setAttribute("class", "productoEscogido");
	// Crear la columna de la materia prima usada
	fila.innerHTML =
		'<td>' +
			$('#nombre_' + i).val() +
		'</td>' +
		'<td>' +
			i +
		'</td>' +
		'<td id="disponible_' + i + '">' +
			$('#saldo_'+i).text() +
		'</td>' +
		'<td>' +
			'<input class="teorica_ing" name="teorica_ing_' + i + '" id="teorica_ing_' + i + '" type="text" value="' + $('#saldo_'+i).text() + '" />' +
		'</td>' +
		'<td>' +
			$('#fechaEnvasado' + i).text() +
		'</td>' +
		'<td>' +
			$('#fechaCaducidad' + i).text() +
		'</td>' +
		'<td>' +
			'<a id="elimina" href="javascript:eliminaElectedProducto(\'' + i + '\')" title="Eliminar este producto">' +
				'<img src="img/cancel.png" alt="Eliminar" title="Eliminar producto"/>' +
			'</a>' +
		'</td>';
	$('#electedProductosBody').prepend(fila);
	//metemos el valor en el array
	materiaPrima.push(i);
	if($('#disponibleMP tbody tr:visible').length==0){
		//$.msg("Mo queda materia prima");
		$('#escogerProductos').slideUp('slow');
	}
	previos = $("#escogerProductos" + i).prevAll();
	var disponible = $('#disponible_' + i).text();
	var cantidad = $("#text_cantidad").val();
	var porcentaje = 0.02;
	cadaUno = $("#cantidadCompone_" + idProducto).val();
	canti = $("#saldo_" + i).text();
	var extra = parseFloat(cantidad) * parseFloat(porcentaje);
	cantidad = Math.ceil((parseFloat(cantidad) + parseFloat(extra)) * cadaUno);
	if (disponible >= cantidad)
		$("#teorica_ing_" + i).val(cantidad);
	else
		$("#teorica_ing_" + i).val(disponible);
	ocultaEscogerProductos();
	comprueba();
	calcularTotalesProductos();
}

function insertaENV(i) {
	//oculto el ingrediente
	$('#escogerEnvases'+i).hide();	
	// Crear una fila
	var fila = document.createElement('tr');
	// Se le añade un id dependiente del envase seleccionado
	fila.setAttribute("id", "electedEnvasesBodyRow" + i);
	fila.setAttribute("class", "envaseEscogido");
	// Crear la columna del envase usada
	fila.innerHTML =
		'<td>' +
			$('#nombre_'+i).val() +
		'</td>' +
		'<td>' +
			i +
		'</td>' +
		'<td id="disponible_' + i + '">' +
			$('#saldo_' + i).text() +
		'</td>' +
		'<td>' +
			'<input class="teorica_env" id="teorica_env_' + i + '" name="teorica_env_' + i + '" type="text" value="' + $('#saldo_' + i).text()+'" />' +
		'</td>' +
		'<td>' +
			$('#proveedorEN' + i).text() +
		'</td>' +
		'<td>' +
			$('#loteEN' + i).text() +
		'</td>' +
		'<td>' +
			'<a id="elimina" href="javascript:eliminaElectedEnvase(\'' + i + '\')" title="Eliminar este ingrediente">' +
				'<img src="img/cancel.png" alt="Eliminar" title="Eliminar ingrediente"/>' +
			'</a>' +
		'</td>';
	$('#electedEnvasesBody').prepend(fila);
	//metemos el valor en el array
	envases.push(i);
	if($('.filaEnvase:visible').length == 0){
		//$.msg("Mo quedan posibles envases");
		$('#escogerEnvases').slideUp('slow');
	}
	var disponible = $('#disponible_' + i).text();
	var cantidad = ($("#cantidad_" + i).val() * $("#text_cantidad").val());
	var porcentaje = 0.02;
	var extra = cantidad * porcentaje;
	cantidad = Math.ceil(cantidad + extra);
	if (disponible >= cantidad)
		$("#teorica_env_" + i).val(cantidad);
	else
		$("#teorica_env_" + i).val(disponible);
	ocultaEscogerEnvases();
	comprueba();
	calcularTotalesEnvases();
}

function eliminaElectedProducto(i){
	if (confirm("Quitar el producto "+i)){
		//$.msg("Borrando "+i);
		if($('#disponibleMP tbody tr:visible').length == 0) {
			//$.msg("Mo queda materia prima");
			$('#escogerProductos').slideDown('slow');
		}
		$('#escogerProductos'+i).show();
		$('#electedProductosBodyRow'+i).remove();
		//debemos eliminarlo del array
		materiaPrima.splice(materiaPrima.indexOf(i),1);
		calcularTotalesProductos();
	}
}

function eliminaElectedEnvase (i){
	if (confirm("Quitar el envase "+i)){
		//$.msg("Borrando "+i);
		if($('#disponibleENV tbody tr:visible').length == 0) {
			//$.msg("Mo quedan posibles envases");
			$('#escogerEnvases').slideDown('slow');
		}
		$('#escogerEnvases'+i).show();
		$('#electedEnvasesBodyRow'+i).remove();
		//debemos eliminarlo del array
		envases.splice(envases.indexOf(i),1);
		calcularTotalesEnvases();
	}
}

function ocultaEscogerEnvases() {
	$('#escogerEnvases').slideUp("slow");
	$('#bot_envase').show();
}

function ocultaEscogerProductos() {
	$('#escogerProductos').slideUp("slow");
	$('#btMasProducto').show();
}

function ocultarEnvasado() {
	$('#nuevoEnvasado').slideUp('slow');
	$("#pending").slideDown("slow");
}

function cambioProducto() {
	//$.msg($('#idProducto').val()+" - "+$("#idProducto option:selected").text());
	//Vaciar los productos y los envases
	$("#electedProductos").slideDown("slow");
	//Ocultar la posibilidad de envasar
	$('#electedPackages').slideUp('slow');
}

function masProducto(){
	//$.msg("masProducto");
	$('#btMasProducto').hide();
	$('#escogerProductos').slideDown("slow");
	//$.msg("ende masProducto");
}

function insertaEnvase() {
	$('#bot_envase').hide();
	$('#escogerEnvases').slideDown("slow");
}

function eliminaENV(i) {
	//$.msg(" Elimina el "+i);
}

/******************************************************/
/* 		VALIDACION DEL FORMULARIO 				 */
/******************************************************/
function comprueba() {
	//si al menos hay una tupla en productos
	if($('#electedProductosTable tbody tr').length>0) {
		//$.msg("HABEMUS ENVASADO SIN VALIDAR UNIDADES");
		if($('#electedEnvasesTable tbody tr').length>0) {
			//$.msg("HABEMUS ENVASADO SIN VALIDAR UNIDADES");
			$('#bot_insertar').show();
		} //else{$.msg("No ha elegido un envase");}
	} //else{$.msg("No ha elegido un Productos");}
}

function calcularTotalesProductos(){
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