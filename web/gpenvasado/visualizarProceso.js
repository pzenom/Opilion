//defino los arrays
var materiaPrima = new Array();
var envases = new Array();

$(document).ready(function(){
	$("#loteAsignado").show();
	$("#cantidadElaborada").show();
	$('#electedIngredients').show();
	$('#escogerIngredientes').hide();
	$('#electedPackages').show();
	$('#escogerEnvases').hide();
	$("#grabarModificaciones").show();
	$("#nuevoEnvasado").show();
	$("#pending").show();
	$('#electedIngredients').show();
	$('#electedPackages').show();
	var estadoActual = $('#estado').val();
	//$.msg(estadoActual);
	if (estadoActual == 'Finalizado' || estadoActual == 'F'){
		habilitarReubicacion();
		reubicaciones = $(".inputReubicarMP");
		for (i = 0; i < reubicaciones.length; i++){
			id = reubicaciones.get(i).id;
			frag = id.split('_');
			entrada = frag[1];
			ubicacionVieja = frag[2];
			idPalet = frag[3];
			total = $("#cantidadMP_" + entrada + "_" + ubicacionVieja + "_" + idPalet).html();
			real = $("#gestionaEnvasado_real_mp_" + entrada + "_" + ubicacionVieja + "_" + idPalet).val();
			mermas = $("#gestionaEnvasado_mermas_mp_" + entrada + "_" + ubicacionVieja + "_" + idPalet).val();
			$("#sobra_mp_" + entrada + "_" + ubicacionVieja + "_" + idPalet).val(parseFloat(total) - (parseFloat(mermas) + parseFloat(real)));
		}
		reubicaciones = $(".inputReubicarEN");
		for (i = 0; i < reubicaciones.length; i++){
			id = reubicaciones.get(i).id;
			frag = id.split('_');
			entrada = frag[1];
			ubicacionVieja = frag[2];
			idPalet = frag[3];
			total = $("#cantidadEN_" + entrada + "_" + ubicacionVieja + "_" + idPalet).html();
			real = $("#gestionaEnvasado_real_en_" + entrada + "_" + ubicacionVieja + "_" + idPalet).val();
			mermas = $("#gestionaEnvasado_mermas_en_" + entrada + "_" + ubicacionVieja + "_" + idPalet).val();
			$("#sobra_en_" + entrada + "_" + ubicacionVieja + "_" + idPalet).val(parseFloat(total) - (parseFloat(mermas) + parseFloat(real)));
		}
		reubicaciones = $(".inputReubicarPROD");
		for (i = 0; i < reubicaciones.length; i++){
			id = reubicaciones.get(i).id;
			frag = id.split('_');
			entrada = frag[1];
			ubicacionVieja = frag[2];
			total = document.getElementById("cantidadPROD" + "_" + entrada + "_" + ubicacionVieja).innerHTML;
			//alert(total);
			real = document.getElementById("gestionaEnvasado_real_prod_" + entrada + "_" + ubicacionVieja).value;
			mermas = document.getElementById("gestionaEnvasado_mermas_prod_" + entrada + "_" + ubicacionVieja).value;
			//$.msg(total + " - " + real + " - " + mermas);
			document.getElementById("sobra_prod_" + entrada + "_" + ubicacionVieja).value = parseFloat(total) - (parseFloat(mermas) + parseFloat(real));
		}
	}
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick" , "javascript:consGPEnva();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	/**/
	agrupar = $("#agrupar").val();
	//alert(agrupar);
	if (agrupar == true || agrupar == "true"){
		$("#boton_etiquetaEAN14").show();
		$("#th_eans14Elaborados").show();
		$("#td_cantidadEnvasadaAgrupar").show();
	}else{
		$("#boton_etiquetaEAN14").hide();
		$("#th_eans14Elaborados").hide();
		$("#td_cantidadEnvasadaAgrupar").hide();
	}
	if ($("#electedEnvasesAgruparBody").children().length == 0){
		$("#electedEnvasesAgruparTable").hide();
	}
});

/******************************************************/
/* 		FUNCIONAMIENTO DE PENDIENTES			 */
/******************************************************/
function elimina (i,j) {
	if(confirm("Desea Eliminar el proceso pendiente "+i+"?")) {
		$('#idEnvasado').val(i);
		$('#loteAsignado').val(j);
		document.formulario_pendiente.submit();
	}
}

/******************************************************/
/* 		FUNCIONAMIENTO DEL FORMULARIO 			 */
/******************************************************/
function insertaMe(i) {
	//$.msg("inserta ingred: " + i);
	// Crear una fila
	var fila = document.createElement('tr');
	// Se le añade un id dependiente del ingrediente seleccionado
	fila.setAttribute("id", "electedIngredientsBodyRow"+i);
	var cantidad = $('#escogerCantMP_' + i).text();
	var totalEmpaque = $('#cantidad').val();
	var disponible = $('#escogerCantMP_'+ i ).text();
	//$.msg("Cantidad :" + cantidad);
	//$.msg("Total empaque: " + totalEmpaque);
	//if (cantidad >= totalEmpaque)
		//cantidad = totalEmpaque;
	// Crear la columna de la materia prima usada

	fila.html() = '<td>' + i + '</td>' +
		'<td id="disponibleMP_' + i + '">' + $('#saldo_'+i).text()+'</td>' +
		'<td id="cantidadMP_' + i + '"><input name="teorica_ing_'+i+'" type="text" value="'+$('#saldo_'+i).text()+'" /></td>' +
		'<td id="realMP_' + i + '">0</td>' +
		'<td id="mermasMP_' + i + '">0</td>' +
		'<td id="proveedorMP_' + i + '">' + $('#proveedorMP' + i).text() + '</td>' +
		'<td id="loteMP_' + i + '">' + $('#loteMP' + i).text() + '</td>' +
		'<td><a id="elimina" href="javascript:eliminaElectedIngredient(\''+i+'\')" title="Eliminar este ingrediente"><img src="img/cancel.png" alt="Eliminar" title="Eliminar ingrediente"/></a></td>' +
		'<td id=fechaMP_' + i + ' style="visibility:hidden;">' + $('#fechaMP_'+i).text()+'</td>' +
		'<td id=fechaCaducidadMP_' + i + ' style="visibility:hidden;">' + $('#fechaCaducidadMP_'+i).text() + '</td>' +
		'<td id="nombreMP_' + i + '" style="visibility:hidden;">' + $('#nombreMP_'+i).text() + '</td>' +
		'<td id="idMP_' + i + '" style="visibility:hidden;">' + $('#idMP_' + i).text() + '</td>';
	//$.msg(fila.html());
	$('electedIngredientsBody').appendChild(fila);
	//metemos el valor en el array
	materiaPrima.push(i);
	//$.msg("MP: "+materiaPrima);	
	if($('#disponibleMP tbody tr:visible').length == 0) {
		//$.msg("No queda materia prima");
		$('#escogerIngredientes').slideUp('slow');
	}
	var saldoRestar = parseFloat($('#escogerCantMP_'+i).text());
	//$.msg("saldo restar: " + saldoRestar);
	//$.msg("saldo disponible: " + $("#saldoDisponibleMP_" + i).text());
	$("#saldoDisponibleMP_" + i).text(parseFloat($("#saldoDisponibleMP_" + i).text()) - saldoRestar);
	//$.msg("disponible final: " + $("#saldoDisponibleMP_" + i).text());
	$('#escogerIngredientes' + i).remove();
	comprueba();
}

function insertaIngredienteArray(i){
	materiaPrima.push(i);
}

function insertaENV(i) {
	//oculto el ingrediente
	//$('#escogerEnvases'+i).hide();	
	// Crear una fila
	var fila = document.createElement('tr');
	// Se le añade un id dependiente del envase seleccionado
	fila.setAttribute("id", "electedEnvasesBodyRow" + i);
	// Crear la columna del envase usada
	//fila.html() = '<td>'+i+'</td><td>'+$('#escogerCantMP_'+i).text()+'</td><td><input id="teorica_env_'+i+'" name="teorica_env_'+i+'" type="text" value="'+$('#escogerCantMP_'+i).text()+'" /></td><td></td><td></td><td>'+$('#escogerProvMP_'+i).text()+'</td><td>'+$('#escogerLoteMP_'+i).text()+'</td><td><a id="elimina" href="javascript:eliminaElectedEnvase(\''+i+'\')" title="Eliminar este ingrediente"><img src="img/cancel.png" alt="Eliminar" title="Eliminar ingrediente"/></a></td>';
	fila.html() = '<td>' + i + '</td>' +
		'<td id="disponibleEN_' + i + '">' + $('#saldo_'+i).text()+'</td>' +
		'<td id="cantidadEN_' + i + '"><input name="teorica_env_'+i+'" type="text" value="'+$('#saldo_'+i).text()+'" /></td>' +
		'<td id="realEN_' + i + '">0</td>' +
		'<td id="mermasEN_' + i + '">0</td>' +
		'<td id="proveedorEN_' + i + '">' + $('#proveedorEN' + i).text() + '</td>' +
		'<td id="loteEN_' + i + '">' + $('#loteEN' + i).text() + '</td>' +
		'<td><a id="elimina" href="javascript:eliminaElectedEnvase(\''+i+'\')" title="Eliminar este envase"><img src="img/cancel.png" alt="Eliminar" title="Eliminar envase"/></a></td>' +
		'<td id=fechaEN_' + i + ' style="visibility:hidden;">' + $('#fechaEN_'+i).text()+'</td>' +
		'<td id=fechaCaducidadEN_' + i + ' style="visibility:hidden;">' + $('#fechaCaducidadEN_'+i).text()+'</td>' +
		'<td id="nombreEN_' + i + '" style="visibility:hidden;">' + $('#nombreEN_'+i).text() + '</td>' +
		'<td id="idEN_' + i + '" style="visibility:hidden;">' + $('#idEN_' + i).text() + '</td>';
	$('electedEnvasesBody').appendChild(fila);
	//metemos el valor en el array
	envases.push(i);
	//$.msg("ENV: "+envases);
	if($('#disponibleENV tbody tr:visible').length == 0) {
		//$.msg("Mo quedan posibles envases");
		$('#escogerEnvases').slideUp('slow');
	}
	var saldoRestar = parseFloat($('#escogerCanteEN_'+i).text());
	//$.msg("saldo restar: " + saldoRestar);
	//$.msg("saldo disponible: " + $("#saldoDisponibleEN_" + i).text());
	$("#saldoDisponibleEN_" + i).text(parseFloat($("#saldoDisponibleEN_" + i).text()) - saldoRestar);
	//$.msg("disponible final: " + $("#saldoDisponibleEN_" + i).text());
	$('#escogerEnvases' + i).remove();
	comprueba();
	cargarOcultos();
}

function insertaEnvaseArray(i){
	envases.push(i);
}

function regresa()
{
	$('#formulario').attr('action','ConsProdFina.action');			
	document.formulario.submit();
}

function eliminaElectedIngredient(i){
	if (confirm("Quitar el ingrediente " + i)){
		$('#bot_ingrediente').hide();
		//$.msg("Borrando "+i);
		if($('#disponibleMP_' + i + ' tbody tr:visible').length==0) {
			//$.msg("No queda materia prima");
			$('#escogerIngredientes').slideDown('slow');
		}
		var cantidad = $('#disponibleMP_' + i).text();
		//$.msg(cantidad);
		var real = $('#realMP_' + i).text();
		var mermas = $('#mermasMP_' + i).text();
		var proveedor = $('#proveedorMP_' + i).text();
		//$.msg("proveedor: " + proveedor);
		var lote = $('#loteMP_' + i).text();
		var fecha = $('#fechaMP_' + i).text();
		//$.msg("fecha: " + fecha);
		var fechaCaducidad = $('#fechaCaducidadMP_' + i).text();
		//$.msg("cantidad: " + cantidad);
		$('#escogerIngredientes'+i).show();
		//debemos eliminarlo del array
		//$.msg("MP: "+materiaPrima);
		materiaPrima.splice(materiaPrima.indexOf(i),1);
		//$.msg("MP: "+materiaPrima);
		//Ahora hay que mostrarlo de nuevo para que se pueda volver a agregar
		var saldoTotal = $("#disponibleMP_" + i).text();
		//$.msg("disponibleMP_" + i);
		//$.msg("saldo total: " + saldoTotal);
		
		var nuevoSaldo = parseFloat(saldoTotal);// + parseFloat(cantidad);
		$("#saldoDisponibleMP_" + i).text(nuevoSaldo);
		var fila = document.createElement('tr');
		//fila = $('#escogerIngredientes' + i);
		// Se le añade un id dependiente del envase seleccionado
		fila.setAttribute("id", "escogerIngredientes" + i);
		// codigoEntrada, fecha, fecha caducidad, lote, proveedor, aniadir
		fila.html() = '<td>' + i + '</td>' +
			'<td id="fechaMP_' + i + '">' + fecha + '</td>' +
			'<td id="fechaCaducidadMP_' + i + '">' + fechaCaducidad + '</td>' +
			'<td id="loteMP' + i + '">' + lote + '</td>' +
			'<td id="saldo_' + i + '">' + nuevoSaldo + '</td>' +
			'<td id="proveedorMP' + i + '">' + proveedor + '</td>' +
			'<td><a id="bot_ingrediente" href="javascript:insertaMe(' + "'" + i + "'" +')" title="Envasar estas unidades"><img src="img/anadir_01.gif" alt="Unidades a Envasar " title="Unidades a envasar"/></a></td>' +
			'<td id="idMP_' + i + '" style="visibility:hidden;">' + $("#idMP_" + i).text() + '</td>';
		//$.msg(fila.html());
		//Si escogerIngredientesNuevo + i no existe, crearlo
		var id = $("#idMP_" + i).text();
		if ($("#escogerIngredientesNuevo" + id) == null)
		{
			var creaFila = document.createElement('tr');
			var nombre = $("#nombreMP_" + i).text();
			creaFila.setAttribute("id", "escogerIngredientesNuevo" + id);
			creaFila.html() = 
				'<td colspan="6" style="text-align:center; background-color: #CEF6CE !important;" >INGREDIENTE: ' + nombre + '</td>';
			//$.msg(fila.html());
			$("#escogerIngredientesBody").appendChild(creaFila);
		}
		//añadir despues de escogerIngredientesNuevo + i
		insertAfter($("#escogerIngredientesNuevo" + id), fila);
		//$('#escogerIngredientes'+i).show();
		$('#electedIngredientsBodyRow' + i).remove();
	}
}

function eliminaElectedEnvase (i){
	if (confirm("Quitar el envase "+i)){
		//$.msg("Borrando "+i);
		$('#bot_envase').hide();
		if($('#disponibleEN_' + i + ' tbody tr:visible').length==0) {
			//$.msg("No quedan posibles envases");
			$('#escogerEnvases').slideDown('slow');
		}
		var cantidad = $('#disponibleEN_' + i).text();
		var real = $('#realEN_' + i).text();
		var mermas = $('#mermasEN_' + i).text();
		var proveedor = $('#proveedorEN_' + i).text();
		var lote = $('#loteEN_' + i).text();
		//$.msg("lote: " + lote);
		var fecha = $('#fechaEN_' + i).text();
		//$.msg("fecha: " + fecha);
		var fechaCaducidad = $('#fechaCaducidadEN_' + i).text();
		//$.msg("muestra escoger envases");
		$('#escogerEnvases'+i).show();
		//$.msg("borra la fila con el envase");
		//debemos eliminarlo del array
		envases.splice(envases.indexOf(i),1);
		//$.msg("ENV: "+envases);
		var saldoTotal = $("#disponibleEN_" + i).text();
		//$.msg("saldo total: " + saldoTotal);
		//var saldo = "'" + saldoTotal + "'";
		//saldo.replace(".", ".");
		//$.msg("saldo total (parseado): " + saldo + " - " + parseFloat('111.78'));
		var nuevoSaldo = parseFloat(saldoTotal);// + parseFloat(cantidad);
		//$("#disponibleEN_" + i).text(nuevoSaldo);
		var fila = document.createElement('tr');
		//fila = $('#escogerIngredientes' + i);
		// Se le añade un id dependiente del envase seleccionado
		fila.setAttribute("id", "escogerEnvases" + i);
		// codigoEntrada, fecha, fecha caducidad, lote, saldo, proveedor, aniadir

		fila.html() = '<td>' + i + '</td>' +
			'<td id="fechaEN_' + i + '">' + fecha + '</td>' +
			'<td id="fechaCaducidadEN_' + i + '">' + fechaCaducidad + '</td>' +
			'<td id="loteEN' + i + '">' + lote + '</td>' +
			'<td id="saldo_' + i + '">' + nuevoSaldo + '</td>' +
			'<td id="proveedorEN' + i + '">' + proveedor + '</td>' +
			'<td><a id="bot_envase" href="javascript:insertaENV(' + "'" + i + "'" +')" title="Envasar estas unidades"><img src="img/anadir_01.gif" alt="Unidades del envase " title="Unidades del envase"/></a></td>' +
			'<td id="idEN_' + i + '" style="visibility:hidden;">' + $("#idEN_" + i).text() + '</td>';
		//$.msg(fila.html());
		//Si escogerEnvasesNuevo + i no existe, crearlo
		var id = $("#idEN_" + i).text();
		if ($("#escogerEnvasesNuevo" + id) == null)
		{
			var creaFila = document.createElement('tr');
			var nombre = $("#nombreEN_" + i).text();
			creaFila.setAttribute("id", "escogerEnvasesNuevo" + id);
			creaFila.html() = 
				'<td colspan="6" style="text-align:center; background-color: #CEF6CE !important;" >ENVASE: ' + nombre + '</td>';
			//$.msg(fila.html());
			$("#escogerEnvasesBody").appendChild(creaFila);
		}
		//añadir despues de escogerEnvasesNuevo + i
		insertAfter($("#escogerEnvasesNuevo" + id), fila);
		//$('#escogerIngredientes'+i).show();
		$('#electedEnvasesBodyRow'+i).remove();
	}
}

function ocultaEscogerIngredientes() {
	$('#escogerIngredientes').slideUp("slow");
	$('#bot_ingrediente').show();
}

/******************************************************/
/* 		VALIDACION DEL FORMULARIO 				 */
/******************************************************/
function comprueba() {
	//si al menos hay una tupla en ingredientes
	if($('#electedIngredientsTable tbody tr').length>0) {
		//y si al menos hay una tupla en envases
		if($('#electedEnvasesTable tbody tr').length>0) {
			//$.msg("HABEMUS ENVASADO SIN VALIDAR UNIDADES");
			$('#grabarModificaciones').show();
		} //else{$.msg("No ha elegido un envase");}
	} //else{$.msg("No ha elegido un Ingrediente");}
}

function ocultaEscogerEnvases() {
	$('#escogerEnvases').slideUp("slow");
	$('#bot_envase').show();
}

function ocultarEnvasado() {
	$('#nuevoEnvasado').slideUp('slow');
	$("#pending").slideDown("slow");
}

function cambioProducto() {
	//$.msg($('#idProducto').val()+" - "+$("#idProducto option:selected").text());
	//Vaciar los ingredientes y los envases
	
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

function checkCantidadIngrediente(orden){
	//$.msg("check cantidad");
	//$.msg(orden);
	var cantidad = $("#cantidadMP_" + orden).children(0).val();
	//$.msg("cantidad: " + cantidad);
	var saldo = parseFloat($("#disponibleMP_" + orden).text());
	//$.msg("saldo: " + saldo);
	if (cantidad > saldo){
		$.msg("La cantidad seleccionada no puede superar a la cantidad de ingredientes disponibles");
		$("#cantidadMP_" + orden).children(0).val(saldo);
	}
}

function checkCantidadEnvases(orden){
	//$.msg("check cantidad");
	//$.msg(orden);
	var cantidad = $("#cantidadEN_" + orden).children(0).val();
	//$.msg("cantidad: " + cantidad);
	var saldo = parseFloat($("#disponibleEN_" + orden).text());
	if (cantidad > saldo)
	{
		$.msg("La cantidad seleccionada no puede superar a la cantidad de envases disponibles");
		$("#cantidadEN_" + orden).children(0).val(saldo);
	}
}

/******************************************************/
function modifica() {
	if($('#cantidad').val()=="") {
		$.msg("Debe indicar cuantas unidades se van a empaquetar");
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
			document.formulario.submit();
		}
	}
}

//Inserta el nodo 'newNode' después del nodo 'referenceNode'
function insertAfter( referenceNode, newNode ){
  referenceNode.parentNode.insertBefore( newNode, referenceNode.nextSibling );
}

//Carga los envases y las materias ocultos, para luego comprobar los elementos que se han eliminado del proceso de envasado
function cargarOcultos(){
	var i;
	var select = $("#MPOcultos");
	var cuantos = select.children().length;
	for (i = 1; i < cuantos; i++){
		var crear = document.createElement('tr');
		var hijo = select.children(i);
		var id = hijo.parent().children().eq(i).val();
		var value = hijo.parent().children().eq(i).text();
		crear.setAttribute("id", "oculto_MP_" + id);
		crear.html() = '<td><input id="input_oculto_MP_' + id + '" name="oculto_MP_' + id +
			'" type="text" value="' + value + '">' + value + '</input></td>';
		$("#MPOcultosCargados").appendChild(crear);
	}
	select = $("#ENOcultos");
	cuantos = select.children().length;
	for (i = 1; i < cuantos; i++){
		var crear = document.createElement('tr');
		var hijo = select.children(i);
		var id = hijo.parent().children().eq(i).val();
		var value = hijo.parent().children().eq(i).text();
		crear.setAttribute("id", "oculto_EN_" + id);
		crear.html() = '<td><input id="input_oculto_EN_' + id + '" name="oculto_EN_' + id +
			'" type="text" value="' + value + '">' + value + '</input></td>';
		$("#ENOcultosCargados").appendChild(crear);
	}
}

function habilitarReubicacion(){
	$(".ubicacionesDevolver").attr("style", "");
	$("#encabezadoDevolverEN").attr("style", "");
	$("#encabezadoDevolverMP").attr("style", "");
	reubicaciones = $(".inputReubicarMP");
	for (i = 0; i < reubicaciones.length; i++){
		id = reubicaciones.get(i).id;
		if($("#" + id).val() == "No devuelve"){
		}else{
			frag = id.split('_');
			entrada = frag[1];
			ubicacionVieja = frag[2];
			idPalet = frag[3];
			total = $("#cantidadMP" + "_" + entrada + "_" + ubicacionVieja + "_" + idPalet).html();
			real = $("#gestionaEnvasado_real_mp_" + entrada + "_" + ubicacionVieja + "_" + idPalet).val();
			mermas = $("#gestionaEnvasado_mermas_mp_" + entrada + "_" + ubicacionVieja + "_" + idPalet).val();
			if ((parseFloat(real) + parseFloat(mermas)) < parseFloat(total)){
				$("#" + id).removeAttr("disabled");
			}
			else{
				$("#" + id).val('No devuelve');
				$("#" + id).attr("disabled", "");
			}
		}
	}
	reubicaciones = $(".inputReubicarEN");
	for (i = 0; i < reubicaciones.length; i++){
		id = reubicaciones.get(i).id;
		if($("#" + id).val().length == 17 || $("#" + id).val().length == 8){
		}else{
			frag = id.split('_');
			entrada = frag[1];
			ubicacionVieja = frag[2];
			idPalet = frag[3];
			total = $("#cantidadEN_" + entrada + "_" + ubicacionVieja + "_" + idPalet).html();
			real = $("#gestionaEnvasado_real_en_" + entrada + "_" + ubicacionVieja + "_" + idPalet).val();
			mermas = $("#gestionaEnvasado_mermas_en_" + entrada + "_" + ubicacionVieja + "_" + idPalet).val();
			if ((parseFloat(real) + parseFloat(mermas)) < parseFloat(total)){
				$("#" + id).removeAttr("disabled");
			}
			else{
				$("#" + id).val('No devuelve');
				$("#" + id).attr("disabled", "");
			}
		}
	}
	reubicaciones = $(".inputReubicarPROD");
	for (i = 0; i < reubicaciones.length; i++){
		id = reubicaciones.get(i).id;
		if($("#" + id).val().length == 17 || $("#" + id).val().length == 8){
			document.getElementById(id).setAttribute("disabled", "");
		}else{
			frag = id.split('_');
			entrada = frag[1];
			ubicacionVieja = frag[2];
			total = document.getElementById("cantidadPROD" + "_" + entrada + "_" + ubicacionVieja).innerHTML;
			real = document.getElementById("gestionaEnvasado_real_prod_" + entrada + "_" + ubicacionVieja).value;
			mermas = document.getElementById("gestionaEnvasado_mermas_prod_" + entrada + "_" + ubicacionVieja).value;
			if ((parseFloat(real) + parseFloat(mermas)) < total){
				document.getElementById(id).removeAttribute("disabled");
			}
			else{
				document.getElementById(id).value = 'No devuelve';
				document.getElementById(id).setAttribute("disabled", "");
			}
		}
	}
}

function comprobarVentanaCerrada(){
	if (ventana.closed){
		location.reload();
		clearInterval(interval);
	}
}

function ubicarFinal(lote, cantidad, id){
	action = "Reubicar.action?meter=true&reubicar=false&ubicar=true&registro=" + lote + "&cantidad=" + cantidad + "&orden=" + $("#orden").val() +
		"&estado=" + $("#estado").val() + "&idEnvasado=" + $("#idEnvasado").val() + "&lote=" + $("#lote").val() + "&idPalet=" + id;
	ventana = window.open(action, "fullscreen", 'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',fullscreen=yes,toolbar=0 ,location=0,directories=0,status=0,menubar=0,resiz able=0,scrolling=0,scrollbars=0');
	interval = setInterval("comprobarVentanaCerrada()", 1000);
}

function reubicar(entrada, tipo, idPalet, idUbicacion){
	total = $("#cantidad" + tipo + "_" + entrada + "_" + idUbicacion).html();
	real = $("#gestionaEnvasado_real_" + tipo.toLowerCase() + "_" + entrada + "_" + idUbicacion).val();
	mermas = $("#gestionaEnvasado_mermas_" + tipo.toLowerCase() + "_" + entrada + "_" + idUbicacion).val();
	cantidadUbicar = parseFloat(total) - (parseFloat(real) + parseFloat(mermas));
	$action = "Reubicar.action?gestionBultos=false&reubicar=true&orden=" + $("#orden").val() + "&registro=" + entrada + "&idPalet=" +
		idPalet + "&cantidad=" + cantidadUbicar + "&estado=" + $("#estado").val() + "&idEnvasado=" + $("#idEnvasado").val() +
		"&lote=" + $("#lote").val() + "&idUbicacion=" + idUbicacion;
	$.ajax({
		url: $action,
		cache: false,
		async: false,
		success: function(html){
			$("#widget_consGPEnva").empty();
			$("#widget_consGPEnva").append(html);
			$("#registro").val(entrada);
			$("#gestionBultos").val("false");
			$("#reubicar").val("true");
			$("#cantidad").val(cantidadUbicar);
			$("#idPalet").val(idPalet);
			$("#idUbicacion").val(idUbicacion);
			$("#widget_consGPEnva").attr("id", "widget_consUbica");
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

function actualizaCantidad(id){
	//$.msg(id + " <-> " + $("#" + id).val());
	idOriginal = id;
	cantidadesUbicar = $(".cantidadesUbicar");
	total = 0;
	pseudoTotal = 0;
	for (i = 0; i < cantidadesUbicar.length; i++){
		id = cantidadesUbicar.get(i).id;
		//$.msg(id);
		//$.msg($("#" + id).val());
		total += parseFloat($("#" + id).val());
		//$.msg("i: " + i + ". id menos 1: " + (parseFloat(idOriginal) - 1));
		if (i != (parseFloat(idOriginal) - 1)){
			pseudoTotal += parseFloat($("#" + id).val());
			//$.msg("hi");
		}
	}
	if ($("#" + id).val() == "" || $("#" + id).val() == "0"){
		//$.msg("entra, id: " + id);
		$("#ubicacion_" + id).attr("disabled", "");
	}
	else{
		//$.msg(total);
		if (total > parseFloat($("#envasado").val())){
			//$.msg(total);
			//$.msg(pseudoTotal);
			//$.msg("La cantidad total a ubicar no puede ser mayor que los " + $("#envasado").val() + " productos envasados");
			$.msg("La cantidad a ubicar no puede ser mayor que los " + (parseFloat($("#envasado").val()) - parseFloat(pseudoTotal)) + " productos envasados que faltan por ubicar");
			$("#" + idOriginal).val(parseFloat($("#envasado").val()) - parseFloat(pseudoTotal));
			$("#ubicacion_" + id).removeAttr("disabled");
			$("#ubicacion_" + id).attr("onclick", "javascript:ubicarFinal('" + $("#lote").val() + "'," + $("#" + id).val() + ",'" + id + "');");
		}else{
			$("#ubicacion_" + id).attr("onclick", "javascript:ubicarFinal('" + $("#lote").val() + "'," + $("#" + id).val() + ",'" + id + "');");
			$("#ubicacion_" + id).removeAttr("disabled");
		}
	}
}

function aniadirUbicacion(){
	//$.msg("aniadiruBicacion");
	cantidadesUbicar = $(".cantidadesUbicar");
	suma = 0;
	for (i = 0; i < cantidadesUbicar.length; i++){
		//$.msg($("#" + cantidadesUbicar[i].id).val());
		suma += parseFloat($("#" + cantidadesUbicar[i].id).val());
	}
	//$.msg("yija");
	if (parseFloat($("#envasado").val()) > suma){
		var fila = document.createElement('tr');
		cuantos = $("#bodyUbicar").children().length;
		//$.msg(cuantos);
		flag = true;
		if (cuantos > 0)
			if ($("#ubicacion_" + parseFloat(cuantos)).val() == ""){
				flag = false;
			}
		if (flag == true){
			cuantos++;
			// Se le añade un id dependiente del ingrediente seleccionado
			fila.setAttribute("id", "cuantos");
			fila.setAttribute("style", "height: 28px;");
			fila.html() =
				'<td id="cantidadTotal" style="font-size: 20px;">' +
					'<input style="text-align:right; font-size:20px; width:60px;" onkeyup="actualizaCantidad(' + cuantos + ');" value="0" class="cantidadesUbicar" name="' + cuantos + '" id="' + cuantos + '"/>' +
				'</td>' +
				'<td style="font-size: 20px;">' +
					'<input value="" name="ubicacion_' + cuantos + '" id="ubicacion_' + cuantos + '" style="text-align:right; font-size:20px;" disabled="" onclick = "javascript:ubicarFinal(' + $("#lote").val() + ',, ' + cuantos + ');" />' +
				'</td>';
			//$.msg(fila.html());
			$('bodyUbicar').appendChild(fila);
		}else
			$.msg("Ubique primero la cantidad anterior");
	}
}