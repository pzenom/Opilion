var odd = true;
var inicio = true;
var linea = 1;
var productos = new Array();

$(document).ready(function(){
		
	$('#tablaProductos').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			{ "sWidth": "12px" },
			{ "sWidth": "75px" },
			{ "sWidth": "50px" },
			{ "sWidth": "135px" },
			{ "sWidth": "135px" }
		],
		"sPaginationType": "full_numbers"
	});
	calculaSumatorios(0);
	calculaSumatorios(1);
	calculaBultos();
});

function addProducto(){
	//$.msg("Añadir producto al pedido");
	//var fila = document.createElement('tr');
	// Se le añade un id dependiente del ingrediente seleccionado
	var productos = document.getElementById("formulario_productos");
	var indiceSeleccionado = productos.selectedIndex;
	//$.msg(indiceSeleccionado);
	var selected = document.formulario.formulario_productos.options[indiceSeleccionado].value;
	var nombre = document.formulario.formulario_productos.options[indiceSeleccionado].text;
	var codigoEan = $("#ean_" + selected).val();
	//$.msg(codigoEan);
	$('#tablaProductos').dataTable().fnAddData( [
		'<td><p id="linea_' + linea + '" style="background: transparent !important;">' + linea + '</p></td>',
		'<td class=" sorting_1">'+nombre+'</td>',
		'<td>Ean: ' + codigoEan + '</td>',
		'<td>Cantidad pedida: <input id="cantidadPedida_' + selected + '" class="cantidades" onchange="javascript:calculaSumatorios(3);" style="width:25px;" value="0" type="text" onchange="javascript:calculaSumatorios(0);"/></br>U. de expedicion: <input id="uExpedicion_' + selected + '" type="text" value="0" style="width:25px;" onchange="javascript:calculaSumatorios(3);"/></td>',
		'<td>Neto unitario: <input id="netoUnitario_' + selected + '" style="width:45px;" value="0" type="text"/><br />Bruto unitario: <input id="brutoUnitario_' + selected + '" style="width:45px;" value="0" type="text"/><br />Neto linea: <input id="netoLinea_' + selected + '" class="netoLineas" value="0" style="width:45px;" type="text" onchange="javascript:calculaSumatorios(1);"/></td>',
		'<td>Observaciones:<textarea id="observaciones_' + selected + '" style="width:96%;"></textarea></td>',
		'<td style="vertical-align:middle;"><a id="elimina_' + linea + '" title="Eliminar este producto" href="javascript:eliminaProducto(' + linea + ')" id="elimina"><img title="Eliminar este producto" alt="Eliminar este producto" src="img/cancel.png"></a></td>'] );
		//PVP: <input id="PVP_' + selected + '" style="width:25px;" type="text"/><br />
	linea++;
	$("#cantidadPedida_" + selected).ForceNumericOnly();
	$("#uExpedicion_" + selected).ForceNumericOnly();
	$("#netoUnitario_" + selected).ForceNumericOnly();
	$("#brutoUnitario_" + selected).ForceNumericOnly();
	$("#PVP_" + selected).ForceNumericOnly();
	$("#netoLinea_" + selected).ForceNumericOnly();
	
	$("#netoUnitario_" + selected).val($("#pCoste_" + selected).val());
	
	//Aniadimos el producto al array de productos
	this.productos.push(selected);
	//$.msg("aniadido");
}

function seleccionCliente(){
	var clientes = document.getElementById("formulario_cliente_idUsuario");
	var indiceSeleccionado = clientes.selectedIndex;
	//$.msg(indiceSeleccionado);
	var selected = document.formulario.formulario_cliente_idUsuario.options[indiceSeleccionado].value;
	//$.msg(selected);
	var cliente = document.formulario.formulario_cliente_idUsuario.options[indiceSeleccionado].text;
	//$.msg("cliente seleccionado: " + cliente);
	//###
	//Cargar las direcciones de este cliente
	//###
	cargarDirecciones(selected);
}

function cargarDirecciones(cliente){
	//Leemos las direcciones del cliente
	var direcciones = $(".calles_" + cliente);
	direcciones = direcciones.get();
	//$.msg(direcciones);
	//$.msg(direcciones[0].value);
	//$.msg(direcciones[1].value);
	//$.msg(direcciones.val());
	//$.msg("hola: " + cliente);
	$("#formulario_direccionesCliente").attr("disabled", false);
	/*
	document.formulario.formulario_direccionesCliente[0].value = "new value";
	*/
	var length = document.formulario.formulario_direccionesCliente.length;
	//Limpia el select
	for (i = 0; i < length; i++)
		document.formulario.formulario_direccionesCliente[i] = null;
		
	var cuantas = direcciones.length;
	//$.msg(cuantas);
	for (i = 0; i < cuantas; i++){
		document.formulario.formulario_direccionesCliente[i] = new Option(direcciones[i].value);
		document.formulario.formulario_direccionesCliente[i].value = direcciones[i].value;
	}
}

function calculaSumatorios(tipo){
	//Tipo = 0 -> cantidad (total)
	//$.msg("Calcula sumatorio! Tipo: " + tipo + ", selected: " + selected);
	if (tipo == 0){
		var cantidades = $(".cantidades");
		cantidades = cantidades.get();
		var cuantas = cantidades.length;
		var cantidadTotal = 0;
		//$.msg(cuantas);
		for (i = 0; i < cuantas; i++){
			cantidadTotal += parseFloat(cantidades[i].value);
		}
		//$.msg(cantidadTotal);
		$("#cantidadTotal").text(cantidadTotal);
		return cantidadTotal;
	} else
		//Tipo = 1 -> importe total
		if (tipo == 1){
			var cantidades = $(".netoLineas");
			cantidades = cantidades.get();
			var cuantas = cantidades.length;
			var cantidadTotal = 0;
			//$.msg(cuantas);
			for (i = 0; i < cuantas; i++){
				cantidadTotal += parseFloat(cantidades[i].value);
			}
			//$.msg(cantidadTotal);
			$("#importeTotal").text(cantidadTotal);
		return cantidadTotal;
		} else
			if (tipo == 3)
			{
				var bultos = 0;
				//Recorrer array de productos, y sumar todos los 'cantidadPedida_'/'uExpedicion_'
				//$.msg(productos.length);
				for (i = 0; i < productos.length; i++)
				{
					id = productos[i];
					//$.msg("cantidadPedida_" + id);
					//$.msg($("#cantidadPedida_" + id).val());
					bultos += parseFloat(parseFloat($("#cantidadPedida_" + id).val()) /
						parseFloat($("#uExpedicion_" + id).val()));
				}
				//$.msg(bultos);
				if ((bultos != "Infinity") && (!isNaN(bultos)))
					$("#numBultosTotal").text(bultos);
				//$.msg(bultos);
			}
}

function calculaBultos()
{
	var bultos = 0;
	var lineas = $("#tablaProductosBody").children();
	cuantasLineas = lineas.length;
	for (i = 1; i <= cuantasLineas; i++)
	{
		bultos += parseFloat(parseFloat($("#formulario_cantidadPedida_" + i).val()) /
			parseFloat($("#formulario_uExpedicion_" + i).val()));
	}
	if ((bultos != "Infinity") && (!isNaN(bultos)))
		$("#numBultosTotal").text(bultos);
	//$.msg(bultos);
}

function aceptar(){
	$("#formulario").attr("action", "ListarOrders.action");
	document.formulario.submit();
}

function eliminaProducto(l){
	//$.msg("Eliminar linea: " + l);
	oTable = $('#tablaProductos').dataTable();
	oTable.fnDeleteRow(l - 1);
	//Sacar producto del array de productos
	if (l == 1){
		for (i = 1; i <= productos.length ;i++){
			$('#linea_' + parseFloat(i)).attr("id", "linea_" + parseFloat(i - 1));
			$('#elimina_' + parseFloat(i + 1)).attr("id", "elimina_" + parseFloat(i));
		}
	}else{
		for (i = l - 1; i < productos.length ;i++){
			$('#linea_' + parseFloat(i + 1)).attr("id", "linea_" + (i));
			$('#elimina_' + parseFloat(i + 1)).attr("id", "elimina_" + i);
		}
	}
	//$.msg(productos.length);
	//1. Desde i = linea + 1, "bajar" en 1 el numero de las lineas (Ej: elimino linea 3, entonces la 4 pasa a ser la 3, la 5 pasa a ser la 4...)
	for (i = 0; i < productos.length ;i++){
		$('#elimina_' + parseFloat(i)).attr("href", "javascript:eliminaProducto(" + i + ")");
		$('#linea_' + parseFloat(i)).text(parseFloat(i));
	}
	//2. Modificar el valor de linea. Reducir 1
	linea--;
}

// Numeric only control handler
jQuery.fn.ForceNumericOnly =
function()
{
  return this.each(function()
  {
    $(this).keydown(function(e)
    {
      var key = e.charCode || e.keyCode || 0;
      // allow backspace, tab, delete, arrows, numbers and keypad numbers ONLY
      return (
        key == 8 || 
        key == 9 ||
        key == 46 ||
        (key >= 37 && key <= 40) ||
        (key >= 48 && key <= 57) ||
        (key >= 96 && key <= 105));
    })
  })
};