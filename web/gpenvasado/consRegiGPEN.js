//defino los arrays
var materiaPrima = new Array();
var envases = new Array();

$(document).ready(function() {
	$('#tablaProcesos').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'desc' ]],
		"aoColumns": [
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_nuevo").attr("onclick", "javascript:nuevoGPEnva();");
	$("#bot_nuevo").show();
	$("#bot_pendientes").show();
	$("#bot_listar").attr("onclick" , "javascript:consGPEnva();");
	$("#bot_listar").show();
	$("#bot_reporte").attr("onclick" , "javascript:reporteEnvasados();");
	$("#bot_reporte").show();
	$("#widget_menu").show();
	if (!$("#consulta").is(':visible')){
		$("#bot_consulta").addClass('ocultando');
		$("#bot_consulta").removeClass('mostrando');
	}
	$("#bot_vuelve").show();
	$("#bot_vuelve").attr("onclick" , "javascript:volverAlEscritorio();");
	$("#bot_consulta").show();
	//setTimeout('$("#tablaProcesos").show();', 250);
	$.unblockUI();
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
	fila.innerHTML = '<td>'+i+'</td><td>'+$('#escogerCantMP_'+i).text()+'</td><td><input name="teorica_ing_'+i+'" type="text" value="'+$('#escogerCantMP_'+i).text()+'" /></td><td></td><td></td><td>'+$('#escogerProvMP_'+i).text()+'</td><td>'+$('#escogerLoteMP_'+i).text()+'</td><td><a id="elimina" href="javascript:eliminaElectedIngredient(\''+i+'\')" title="Eliminar este ingrediente"><img src="img/cancel.png" alt="Eliminar" title="Eliminar ingrediente"/></a></td>';
	document.getElementById('electedIngredientsBody').appendChild(fila);
	//metemos el valor en el array
	materiaPrima.push(i);
	//$.msg("MP: "+materiaPrima);	
	if($('#disponibleMP tbody tr:visible').length==0) {
		//$.msg("Mo queda materia prima");
		$('#escogerIngredientes').slideUp('slow');
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
	fila.innerHTML = '<td>'+i+'</td><td>'+$('#escogerCantMP_'+i).text()+'</td><td><input id="teorica_env_'+i+'" name="teorica_env_'+i+'" type="text" value="'+$('#escogerCantMP_'+i).text()+'" /></td><td></td><td></td><td>'+$('#escogerProvMP_'+i).text()+'</td><td>'+$('#escogerLoteMP_'+i).text()+'</td><td><a id="elimina" href="javascript:eliminaElectedEnvase(\''+i+'\')" title="Eliminar este ingrediente"><img src="img/cancel.png" alt="Eliminar" title="Eliminar ingrediente"/></a></td>';
	document.getElementById('electedEnvasesBody').appendChild(fila);
	//metemos el valor en el array
	envases.push(i);
	//$.msg("ENV: "+envases);
	if($('#disponibleENV tbody tr:visible').length==0) {
		//$.msg("Mo quedan posibles envases");
		$('#escogerEnvases').slideUp('slow');
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

function insertaIngrediente() {
	$('#bot_ingrediente').hide();
	$('#escogerIngredientes').slideDown("slow");
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
		totalMP = 0;
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

function irAlProceso(orden){
	var estado = $('#estado_' + orden).text();
	var id = $('#idProducto_' + orden).val();
	var idEnvasado = $('#idEnvasado_' + orden).val();
	var lote = $('#lote_' + orden).text();
	$('#listado').attr('action','VisualizaProcesoEnvasado.action');
	$('#orden').attr('value', orden);
	$('#estado').attr('value', estado);
	$('#id').attr('value', id);
	$('#idEnvasado').attr('value', idEnvasado);
	$('#lote').attr('value', lote);
	$.ajax({
	 url: "VisualizaProcesoEnvasado.action?anula=false&orden=" + orden + "&idEnvasado=" + idEnvasado + "&lote=" + lote + "&estado=" + estado,
	 cache: false,
	 async: false,
	 success: function(html){
		$("#widget_consGPEnva").empty();
		$("#widget_consGPEnva").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "gpenvasado/visualizarProceso.js",
		dataType: "script"
	});
}

function filtraEnvasados(){
	$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
	setTimeout(function(){
		var $url = "";
		$url = "FiltGPEnva.action?idProducto=" + $("#dropdown_productos").val() + '&filtro=' + $("#dropdown_cuantos_mostrar").val();
		$.ajax({
		 url: $url,
		 cache: false,
		 async:false,
		 success: function(html){
			$("#demo").empty();
			$("#demo").append(html);
		 }
		});
		$.ajax({
			type: "POST",
			url: "gpenvasado/consRegiGPEN.js",
			dataType: "script"
		});
	}, 200);
}

function reporte(orden){
	//$.msg(orden);
	$.ajax({
	 url: "ConsDetaGPENJR.action?orden=" + orden,
	 cache: false,
	 async:false,
	});
}

function reporteEnvasados(){
	parent.get_ventana_emergente('GPEnva','ConsGPEnvaJR.action','no','no','800','640','','');
}

function controlTiempos(orden){
	var $url = "ControlTiempos.action?proceso=" + orden;
	//$.msg($url);
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consGPEnva").empty();
			$("#widget_consGPEnva").append(html);
			/* Configuramos la botonera */
			/*$(".botonBotonera").hide();
			$("#bot_vuelve").attr("onclick" , "javascript:consGPEnva();");
			$("#bot_vuelve").show();
			$("#widget_menu").show();*/
			$.ajax({
				type: "POST",
				url: "controlTiempos/controlTiempos.js",
				dataType: "script"
			});
		}
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick", "javascript:consGPEnva();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
}