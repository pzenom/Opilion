//defino los arrays
var materiaPrima = new Array();
var envases = new Array();

$(document).ready(function(){
	//Mostramos solo el primer producto
	var contenedoresProductos = $(".contenedorProducto").get();
	var length = contenedoresProductos.length;
	for (i = 1; i < length; i++){
		$("#" + contenedoresProductos[i].id).hide();
		$("#" + contenedoresProductos[i].id).val("closed");
	}
	
	//$("#submitea").hide();
		//$('#submitea').val(1);
		//$("#submitea").attr("targets", "ajaxDiv");
		//$("#submitea").attr("event", "onchange");
	
	//Creamos un input oculto para cada uno de los productos q se cargan
	var productos = $("#productosEnvasar").children();
	//$.msg(productos.get(0).value);
	var oculto;
	for (i = 0; i < productos.length; i++)
	{
		oculto = document.createElement('input');
		oculto.setAttribute("id", "producto_" + i);
		oculto.setAttribute("name", "producto_" + i);
		oculto.setAttribute("type", "hidden");
		oculto.setAttribute("value", productos.get(i).value);
		$("#escogerIngredientesBody").append(oculto);
	}
	
	actualizaCantidad();
	
	$("#loteAsignado").hide();
	$("#cantidadElaborada").hide();
	$('#electedIngredients').hide();
	//$('#escogerIngredientes').hide();
	$('#electedPackages').hide();
	//$('#escogerEnvases').hide();
	$("#envasarTodo").hide();
	
	$("#viejo").hide();
	
	if($('#idProducto').val() == 0) {
		//$.msg("virgen");
		$("#nuevoEnvasado").hide();
	} else {
		//$.msg("ya esta inicializada");
		$("#pending").hide();
		$('#electedIngredients').show();
		$('#electedPackages').show();
	}
	/*	
	$('#botonNuevoEnvase').click(function() {
		if ($('#nuevoEnvasado').is(':hidden')) {
			$("#nuevoEnvasado").slideDown("slow");
			$("#pending").slideUp("slow");
		} else {
				//$("#nuevoEnvasado").hide();
		} 
		//$("#botonNuevoEnvase").hide(); 
		$('#nuevoEnvasado').slideDown('slow', function() { // Animation complete. 
		});
	});
	*/
	$("#nuevoEnvasado").show();
	$("#pending").hide();
});

/******************************************************/
/* 		FUNCIONAMIENTO DE PENDIENTES			 */
/******************************************************/
function elimina (i,j) {
	if(confirm("Desea eliminar el proceso pendiente " + i + "?")){
		$('#idEnvasado').val(i);
		$('#loteAsignado').val(j);
		document.oculto.submit();
	}
}

function actualizaCantidad(){
	//$.msg("hola");
	var seleccionado = $("#formu_productoSeleccionado").val();
	//$.msg(seleccionado);
	//A partir de seleccionado (ean), sacar la cantidad asociada (select que las asocia)
	var cantidades = document.getElementById("eanCantidad");
	length = cantidades.length;
	for (i = 0; i < length; i++)
	{
		if (document.formu.eanCantidad.options[i].value == seleccionado)
			//$.msg(document.formu.eanCantidad.options[i].text);
			var cantidad = document.formu.eanCantidad.options[i].text;
	}
	$("#cantidad").val(cantidad);
}

/******************************************************/
/* 		FUNCIONAMIENTO DEL FORMULARIO 			 */
/******************************************************/
function insertaMe(i) {
	//oculto el ingrediente
	$('#escogerIngredientes'+i).hide();	
	// Crear una fila
	var fila = document.createElement('tr');
	// Se le añade un id dependiente del ingrediente seleccionado
	fila.setAttribute("id", "electedIngredientsBodyRow"+i);
	// Crear la columna de la materia prima usada
	fila.innerHTML = '<td>'+i+'</td><td id="disponible_' + i + '">'+$('#saldo_'+i).text()+'</td><td><input class="teorica_ing" name="teorica_ing_'+i+'" type="text" value="'+$('#saldo_'+i).text()+'" /></td><td>0</td><td>0</td><td>'+$('#proveedorMP'+i).text()+'</td><td>'+$('#loteMP'+i).text()+'</td><td><a id="elimina" href="javascript:eliminaElectedIngredient(\''+i+'\')" title="Eliminar este ingrediente"><img src="img/cancel.png" alt="Eliminar" title="Eliminar ingrediente"/></a></td>';
	document.getElementById('electedIngredientsBody').appendChild(fila);
	//metemos el valor en el array
	materiaPrima.push(i);
	//$.msg("MP: "+materiaPrima);	
	if($('#disponibleMP tbody tr:visible').length==0) {
		//$.msg("Mo queda materia prima");
		$('#escogerIngredientes').slideUp('slow');
	}
	
	//$.msg(disponible);
	var teoricas = [];
	$(".teorica_ing").each(function () {
		var items = $(this).attr('value');
		teoricas.push(items);
	});
	if (teoricas.length == 1)
	{
			var flag = false;
			$(".teorica_ing").each(function () {
				if (flag == false)
				{
					var disponible = $('#disponible_' + i).text();
					var cantidad = (parseFloat($("#cantidad_" + i).val()));// * parseFloat($("#cantidad").val()));
					//$.msg(cantidad);
					var porcentaje = 0.02;
					var extra = cantidad * porcentaje;
					cantidad = Math.ceil(cantidad + extra);
					//$.msg(cantidad);
					//var celda = $(this).attr('id');
					if (disponible >= cantidad)
						$(this).attr('value', cantidad);
					else
						$(this).attr('value', disponible);
					flag = true;
				}
			});
	}
	comprueba();
}

function insertaENV(i) {
	
	//oculto el ingrediente
	$('#escogerEnvases'+i).hide();	
	// Crear una fila
	var fila = document.createElement('tr');
	// Se le añade un id dependiente del envase seleccionado
	fila.setAttribute("id", "electedEnvasesBodyRow"+i);
	// Crear la columna del envase usada
	fila.innerHTML = '<td>'+i+'</td><td id="disponible_' + i + '">'+$('#saldo_'+i).text()+'</td><td><input class="teorica_env" name="teorica_env_'+i+'" name="teorica_env_'+i+'"type="text" value="'+$('#saldo_'+i).text()+'" /></td><td>0</td><td>0</td><td>'+$('#proveedorEN'+i).text()+'</td><td>'+$('#loteEN'+i).text()+'</td><td><a id="elimina" href="javascript:eliminaElectedEnvase(\''+i+'\')" title="Eliminar este ingrediente"><img src="img/cancel.png" alt="Eliminar" title="Eliminar ingrediente"/></a></td>';
	document.getElementById('electedEnvasesBody').appendChild(fila);
	//metemos el valor en el array
	envases.push(i);
	//$.msg("ENV: "+envases);
	if($('#disponibleENV tbody tr:visible').length==0) {
		//$.msg("Mo quedan posibles envases");
		$('#escogerEnvases').slideUp('slow');
	}
	
	//$.msg(disponible);
	var teoricas = [];
	$(".teorica_env").each(function () {
		var items = $(this).attr('value');
		teoricas.push(items);
	});
	if (teoricas.length == 1)
	{
			var flag = false;
			$(".teorica_env").each(function () {
				if (flag == false)
				{
					var disponible = $('#disponible_' + i).text();
					var cantidad = (parseFloat($("#cantidad_" + i).val()));// * $("#cantidad").val());
					//$.msg(cantidad);
					var porcentaje = 0.02;
					var extra = cantidad * porcentaje;
					cantidad = Math.ceil(cantidad + extra);
					//$.msg(cantidad);
					//var celda = $(this).attr('id');
					if (disponible >= cantidad)
						$(this).attr('value', cantidad);
					else
						$(this).attr('value', disponible);
					flag = true;
				}
			});
	}
	
	comprueba();
}

function eliminaElectedIngredient (i){
	if (confirm("Quitar el ingrediente "+i)){
		//$.msg("Borrando "+i);
		if($('#disponibleMP tbody tr:visible').length==0) {
			//$.msg("Mo queda materia prima");
			$('#escogerIngredientes').slideDown('slow');
		}
		$('#escogerIngredientes'+i).show();
		$('#electedIngredientsBodyRow'+i).remove();
		//debemos eliminarlo del array
		materiaPrima.splice(materiaPrima.indexOf(i),1);
		//$.msg("MP: "+materiaPrima);
	}
}

function eliminaElectedEnvase (i){
	if (confirm("Quitar el envase "+i)){
		//$.msg("Borrando "+i);
		if($('#disponibleENV tbody tr:visible').length==0) {
			//$.msg("Mo quedan posibles envases");
			$('#escogerEnvases').slideDown('slow');
		}
		$('#escogerEnvases'+i).show();
		$('#electedEnvasesBodyRow'+i).remove();
		//debemos eliminarlo del array
		envases.splice(envases.indexOf(i),1);
		//$.msg("ENV: "+envases);
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
	//$.msg($('#idProducto').val()+" - "+$("#idProducto option:selected").text());
	//Vaciar los ingredientes y los envases
	
	$("#electedIngredients").slideDown("slow");
	//Ocultar la posibilidad de envasar
	$('#electedPackages').slideUp('slow');
}

function nuevoEnvasado() {
	//$.msg("Nuevo Envasado");
//	document.getElementById("botonNuevoEnvase").style.visibility = "hidden";
//	document.getElementById("nuevoEnvasado").style.visibility = "visible";
}

function insertaIngrediente() {
	$('#bot_ingrediente').hide();
	$('#escogerIngredientes').slideDown("slow");
}

function insertaEnvase() {
	$('#bot_envase').hide();
	$('#escogerEnvases').slideDown("slow");
}

function eliminaENV(i) {
	$.msg(" Elimina el "+i);
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
			$('#envasarTodo').show();
		} //else{$.msg("No ha elegido un envase");}
	} //else{$.msg("No ha elegido un Ingrediente");}
}

/******************************************************/
function inserta() {
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
		} else if (confirm("Las cantidades que figuran serán restadas (de manera temporal) del stock de los productos afectados. Desea proseguir?")) {
			//$.msg("METER ORDEN EN EL SISTEMA");
			$('#formulario').attr('action','InsertaOrdenEnv.action');			
			document.formulario.submit();
		}
	}
}


function visualiza(i, j) {
//function visualiza(){
	//$.msg("lote asignado: " + j);
	//$.msg("id: " + i);
	
	$("#loteAsignado").show();
	$("#cantidadElaborada").show();
	$('#electedIngredients').show();
	$('#escogerIngredientes').show();
	$('#electedPackages').show();
	$('#escogerEnvases').show();
	$("#envasarTodo").show();
	
	if ($('#idProducto').val()==0) {
		$("#nuevoEnvasado").show();
	} else {
		//$.msg("ya esta inicializada");
		$("#pending").show();
		$('#electedIngredients').show();
		$('#electedPackages').show();
	}
	
	$("#listado").attr('action', 'VisualizaProcesoEnvasado.action');
	$("#idEnvasado").val(i);
	document.listado.submit();
}

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

function seleccionProducto(){
	var divisas = document.getElementById("productosEnvasar");
	var indiceSeleccionado = divisas.selectedIndex;
	//$.msg(indiceSeleccionado);
	var selected = document.formu.productosEnvasar.options[indiceSeleccionado].value;
	//$.msg(selected);
	$("#formu_productoSeleccionado").val(selected);
	$("#formulario_productoSeleccionado").val(selected);
	//$("#productoSeleccionado").attr("value", selected);
	//$.msg($("#productoSeleccionado").value);
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

function ocultarProducto(ean){
	var id = 'contenedorProducto_' + ean;
	if ($("#" + id).val() == "" || $("#" + id).val() == "opened"){
		$("#" + id).hide('slow');
		$("#" + id).val("closed");
	
		//$('#submitea').val(12);
		//$('#submitea').attr("onchange", "true");
		//document.formu.submit();
	} else
		if ($("#" + id).val() == "closed")
		{
			$("#" + id).show('slow');
			$("#" + id).val("opened");
		}
	//$.msg($("#" + id).val());
}