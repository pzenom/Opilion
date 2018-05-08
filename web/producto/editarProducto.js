var tipo = "X";

$(document).ready(function() {
	//Cuando el sitio carga...
	$(".tab_contenido").hide(); //Esconde todo el contenido
	$("ul.tabs li:first").addClass("active").show(); //Activa la primera tab
	$(".tab_contenido:first").show(); //Muestra el contenido de la primera tab
	//On Click Event
	$("ul.tabs li").click(function() {
		$("ul.tabs li").removeClass("active"); //Elimina las clases activas
		$(this).addClass("active"); //Agrega la clase activa a la tab seleccionada
		$(".tab_contenido").hide(); //Esconde todo el contenido de la tab
		var activeTab = $(this).find("a").attr("href"); //Encuentra el valor del atributo href para identificar la tab activa + el contenido
		$(activeTab).fadeIn(); //Agrega efecto de transición (fade) en el contenido activo
		return false;
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick", "javascript:consProdu();");
	$("#bot_vuelve").show();
	$("#bot_actualizar").attr("onclick", "javascript:registraProducto();");
	$("#bot_actualizar").show();
	$("#widget_menu").show();
	/**/
	cuantos = $("#tbody_items").children().length;
	//alert(cuantos);
	if (cuantos > 0){
		//Es un EAN14
		tipoEan('14');
		//$("#radio_ean14").attr("checked", true);
		$("#radio_ean14").parent().addClass("checked");
	}else{
		//Es un EAN13
		tipoEan('13');
		$("#radio_ean13").parent().addClass("checked");
		//$("#radio_ean13").attr("checked", true);
	}
	$("#categorizacion").treeview();
	grupo = $("#idGrupo").val();
	//Granel???
	mostrar = $("#mostrar").val();
	if (mostrar == 1){
		//Marchar el check de granel
		checkBox = $('#check_granel');
		$('#check_granel').checked = true;
		//$('#check_granel').click();
		$('#check_granel').attr("checked", "checked");
		$('#check_granel').parent().addClass("checked");
	}
	grupoCategorizacionSeleccionado(grupo);
});

function tipoEan(tProd){
	if (tProd == 13){
		tipo = 13;
		$("#fieldset").show();
		$("#pestaniaEANS13").hide();
		$("#pestaniaIngredientes").show();
		$("#pestaniaEnvases").show();
	}else{
		if (tProd == 14){
			tipo = 14;
			$("#fieldset").show();
			$("#pestaniaEANS13").show();
			$("#pestaniaIngredientes").hide();
			$("#pestaniaEnvases").show();
		}
	}
	$("#pestaniaCategorizacion").show();
	$("#pestaniaImagen").show();
	$("#label13").removeAttr("style");
	$("#label14").removeAttr("style");
}

function seleccionProducto(){
	seleccion = $('#dropdown_productos').val();
	productoSeleccionado = $('#dropdown_productos option:selected').text();
	//alert(seleccion);//110_MPs, 46_EAN13
	frag = seleccion.split('_');
	tipoProd = frag[1];
	//alert(tipoProd);
	id = frag[0];
	if (tipoProd == 'MPs'){
		idMateriaCategoria = id;
		if (idMateriaCategoria > 0){
			if (!$("#CANTIDAD_MP_" + idMateriaCategoria).length){
				//$.msg("Indice de la materia prima seleccionada: " + selected);
				//document.getElementById("cabeceraMateriaPrima").setAttribute("style", "display:inline");
				var nuevaFila = document.createElement("tr");
				nuevaFila.setAttribute("id", "seleccionMP_" + idMateriaCategoria);
				// Crear la fila del envase usada
				nuevaFila.innerHTML =
					'<td style="width: 25px;"><input type="hidden" name="MP_' + idMateriaCategoria + '" value="' + idMateriaCategoria + '"/>' + idMateriaCategoria + '</td>' +
					'<td>' + productoSeleccionado + '</td>' +
					'<td nowrap="nowrap"><input type="text" name="CANTIDAD_MP_' + idMateriaCategoria + '" id="CANTIDAD_MP_' + idMateriaCategoria + '" value="1" onKeyPress="return validarNumerosDecimales(' + "'" + 'CANTIDAD_MP_' + idMateriaCategoria + "'" + ', event);" onblur="javascript:ajustarDecimal(' + "'" + "CANTIDAD_MP_" + idMateriaCategoria + "'" + ');" class="inputCantidadIngrediente" style="text-align: right; width: 94%;"/></td>' +
					'<td style="width: 25px;">' +
						'<a id="elimina" href="javascript:eliminaMateria(' + idMateriaCategoria +')" title="Eliminar este materia">' +
							'<img src="img/cancel.png" alt="Eliminar" title="Eliminar materia prima"/>' +
						'</a>' +
					'</td>';
				document.getElementById('tabla_MP_body').appendChild(nuevaFila);
			}
		}
	}else{
		if (tipoProd == 'EAN13'){
			idProducto = id;
			if (idProducto > 0){
				if (!$("#CANTIDAD_EAN13_" + idProducto).length){
					//$.msg("Indice de la EAN13 prima seleccionada: " + selected);
					//document.getElementById("cabeceraMateriaPrima").setAttribute("style", "display:inline");
					var nuevaFila = document.createElement("tr");
					nuevaFila.setAttribute("id", "seleccionEAN13_" + idProducto);
					// Crear la fila del envase usada
					nuevaFila.innerHTML =
						'<td style="width: 25px;"><input type="hidden" name="EAN13_' + idProducto + '" value="' + idProducto + '"/>' + idProducto + '</td>' +
						'<td>' + productoSeleccionado + '</td>' +
						'<td nowrap="nowrap"><input type="text" name="CANTIDAD_EAN13_' + idProducto + '" id="CANTIDAD_EAN13_' + idProducto + '" value="1" onKeyPress="return validarNumerosDecimales(' + "'" + 'CANTIDAD_EAN13_' + idProducto + "'" + ', event);" onblur="javascript:ajustarDecimal(' + "'" + "CANTIDAD_EAN13_" + idProducto + "'" + ');" class="inputCantidadIngrediente" style="text-align: right; width: 94%;"/></td>' +
						'<td style="width: 25px;">' +
							'<a id="elimina" href="javascript:eliminaEAN13(' + idProducto +')" title="Eliminar este EAN13">' +
								'<img src="img/cancel.png" alt="Eliminar" title="Eliminar EAN13"/>' +
							'</a>' +
						'</td>';
					document.getElementById('tabla_MP_body').appendChild(nuevaFila);
				}
			}
		}
	}
}

function cargaEnvase(){
	//EJEMPLO: Faba Asturiana DO -> id = 1; selected = 1. Coinciden siempre.
	//$.msg("hola");
	var envases = document.getElementById("envases");
	//$.msg(envases);
	var selected = envases.selectedIndex;
	var cuantosEnvases = envases.length;
	var idEnvase = envases.value;
	var cuantosEnvases = envases.length;
	if (selected > 0 && selected < cuantosEnvases){
		//$.msg($("#CANTIDAD_MP_" + selected).length);
		if (!$("#CANTIDAD_EN_" + idEnvase).length){
			//$.msg("Indice del envase seleccionado: " + selected);
			//document.getElementById("cabeceraEnvase").setAttribute("style", "display:inline");
			var nuevaFila = document.createElement("tr");
			nuevaFila.setAttribute("id", "seleccionEN_" + idEnvase);
			// Crear la fila del envase usada
			nuevaFila.innerHTML =
				'<td style="width: 25px;"><input type="hidden" name="EN_' + idEnvase + '" value="' + idEnvase + '"/>' + idEnvase + '</td>' +
				'<td>' + envases[selected].text + '</td>' +
				'<td nowrap="nowrap"><input type="text" name="CANTIDAD_EN_' + idEnvase + '" id="CANTIDAD_EN_' + idEnvase + '" value="1" onkeypress="return validarSoloNumeros(event);" class="inputCantidadEnvase" style="text-align: right; width: 94%;" onblur="javascript:ajustarDecimal(' + "'" + "CANTIDAD_EN_" + idEnvase + "'" + ');" /></td>' +
				'<td style="width: 25px;">' +
					'<a id="elimina" href="javascript:eliminaEnvase(' + idEnvase +')" title="Eliminar este envaae">' +
						'<img src="img/cancel.png" alt="Eliminar" title="Eliminar envase"/>' +
					'</a>' +
				'</td>';
			document.getElementById('tabla_EN_body').appendChild(nuevaFila);
		}
	}
}

function cargaItem(){
	//EJEMPLO: Faba Asturiana DO -> id = 1; selected = 1. Coinciden siempre.
	//$.msg("hola");
	var productos = document.getElementById("items");
	var selected = productos.selectedIndex;
	var idProducto = productos.value;
	var cuantosProductos = productos.length;
	if (selected > 0 && selected < cuantosProductos){
		//$.msg($("#CANTIDAD_MP_" + selected).length);
		if (!$("#CANTIDAD_Item_" + idProducto).length){
			//$.msg("Indice de la materia prima seleccionada: " + selected);
			//document.getElementById("cabeceraMateriaPrima").setAttribute("style", "display:inline");
			var nuevaFila = document.createElement("tr");
			nuevaFila.setAttribute("id", "seleccionItem_" + idProducto);
			// Crear la fila del envase usada
			nuevaFila.innerHTML =
				'<td style="width: 25px;"><input type="hidden" name="Item_' + idProducto + '" value="' + idProducto + '"/>' + idProducto + '</td>' +
				'<td>' + productos[selected].text + '</td>' +
				'<td nowrap="nowrap"><input type="text" name="CANTIDAD_Item_' + idProducto + '" id="CANTIDAD_Item_' + idProducto + '" value="1" onkeypress="return validarSoloNumeros(event);" style="text-align: right; width: 94%;" class="inputCantidadEAN13" onblur="javascript:ajustarDecimal(' + "'" + "CANTIDAD_Item_" + idProducto + "'" + ');" /></td>' +
				'<td style="width: 25px;">' +
					'<a id="elimina" href="javascript:eliminaItem(' + idProducto +')" title="Eliminar este item">' +
						'<img src="img/cancel.png" alt="Eliminar" title="Eliminar item"/>' +
					'</a>' +
				'</td>';
			document.getElementById('tbody_items').appendChild(nuevaFila);
		}
	}
}

function eliminaMateria(id){
	//confirm("Eliminar materia: " + id + "?");
	var fila = document.getElementById('seleccionMP_' + id);
	fila.parentNode.removeChild(fila);
}

function eliminaEAN13(id){
	var fila = document.getElementById('seleccionEAN13_' + id);
	fila.parentNode.removeChild(fila);
}

function eliminaItem(id){
	//confirm("Eliminar item: " + id + "?");
	var fila = document.getElementById('seleccionItem_' + id);
	fila.parentNode.removeChild(fila);
}

function eliminaEnvase(id){
	//confirm("Eliminar envase: " + id + "?");
	var fila = document.getElementById('seleccionEN_' + id);
	fila.parentNode.removeChild(fila);
}

function nuevaCategoria(){
	document.getElementById('presenta').innerHTML = 
		'<fieldset>' +
			'<legend><span>Nueva categoria</span></legend>' +
				'<table width="100%" cellpadding="2" cellspacing="2" border="0">' +
					'<tr>' +
						'<td class="label"><label name="Nombre" value="Nombre">Nombre</label></td>' +
						'<td nowrap="nowrap">' +
							'<input id="nombreCate" key="nombre" label="Nombre"/>' +
						'</td>' +
					'</tr>' +
				'</table>' +
		'</fieldset>' +
		'<br />' +
		'<a id="bot_inserta" class="botonInserta" href="javascript:addCategoria();" title="Insertar">' +
			'<img src="img/j_button1_add.png" alt="Insertar " title="Insertar"/>Insertar' +
		'</a>' +
		'<br />' +
		'<br />';
	$("#msg").attr("height", "20px");
}

function addCategoria(){
	$("#nombreCategoria").val($("#nombreCate").val());
	nombre = $("#nombreCategoria").val();
	//$.msg($("#nombreCategoria").val());
	$('#submitSalvaCategoria').trigger('click');
	ocultar();
	var maxBanco = 0;
	opciones = $("#idCategoria").children();
	//$.msg(opciones.length);
	for (i = 0; i < opciones.length; i++){
		opciones[i] = opciones[i].value;
	}
	maxBanco = parseFloat(Array.max(opciones)) + 1;
	//$.msg(maxBanco);
	if (maxBanco == "-Infinity"){
		maxBanco = 1;
		//$.msg(maxBanco);
	}
	$('#idCategoria').append(new Option(nombre, maxBanco, true, true));
}

function nuevoGrupo(){
	$("#fondo").show();
	$("#msg").show();
	//$("#idMateriaPrima").val(idMateriaPrima);
	//document.getElementById('presenta').innerHTML = 'Categorias para la materia prima con id: ' + idMateriaPrima;
	//document.getElementById('presenta').innerHTML = 'Categorias de ' + nombre;
	document.getElementById('presenta').innerHTML = 
		'<fieldset>' +
			'<legend><span>Nuevo grupo producto</span></legend>' +
				'<table width="100%" cellpadding="2" cellspacing="2" border="0">' +
					'<tr>' +
						'<td class="label"><label name="Nombre" value="Nombre">Nombre</label></td>' +
						'<td nowrap="nowrap">' +
							'<input id="nombreGru" key="nombre" label="Nombre"/>' +
						'</td>' +
					'</tr>' +
				'</table>' +
		'</fieldset>' +
		'<br />' +
		'<a id="bot_inserta" class="botonInserta" href="javascript:addGrupo();" title="Insertar">' +
			'<img src="img/j_button1_add.png" alt="Insertar " title="Insertar"/>Insertar' +
		'</a>' +
		'<br />' +
		'<br />';
	$("#msg").attr("height", "20px");
}

function addGrupo(){
	$("#nombreGrupo").val($("#nombreGru").val());
	nombre = $("#nombreGrupo").val();
	//$.msg($("#nombre").val());
	$('#submitSalvaGrupo').trigger('click');
	ocultar();
	var maxBanco = 0;
	opciones = $("#idGrupo").children();
	//$.msg(opciones.length);
	for (i = 0; i < opciones.length; i++){
		opciones[i] = opciones[i].value;
	}
	maxBanco = parseFloat(Array.max(opciones)) + 1;
	//$.msg(maxBanco);
	if (maxBanco == "-Infinity"){
		maxBanco = 1;
		//$.msg(maxBanco);
	}
	$('#idGrupo').append(new Option(nombre, maxBanco, true, true));
}

function nuevaFamilia(){
	$("#fondo").show();
	$("#msg").show();
	document.getElementById('presenta').innerHTML = 
		'<fieldset>' +
			'<legend><span>Nuevo banco</span></legend>' +
				'<table width="100%" cellpadding="2" cellspacing="2" border="0">' +
					'<tr>' +
						'<td class="label"><label name="Descripcion" value="Descripcion">Descripcion</label></td>' +
						'<td nowrap="nowrap">' +
							'<input id="descripcionFami" key="desc" label="Descripcion"/>' +
						'</td>' +
					'</tr>' +
				'</table>' +
		'</fieldset>' +
		'<br />' +
		'<a id="bot_inserta" class="botonInserta" href="javascript:addFamilia();" title="Insertar">' +
			'<img src="img/j_button1_add.png" alt="Insertar " title="Insertar"/>Insertar' +
		'</a>' +
		'<br />' +
		'<br />';
	$("#msg").attr("height", "20px");
}

function addFamilia(){
	$("#descripcionFamilia").val($("#descripcionFami").val());
	nombre = $("#descripcionFamilia").val();
	//$.msg($("#descripcionFamilia").val());
	$('#submitSalvaFamilia').trigger('click');
	ocultar();
	var maxBanco = 0;
	opciones = $("#idFamilia").children();
	//$.msg(opciones.length);
	for (i = 0; i < opciones.length; i++){
		opciones[i] = opciones[i].value;
	}
	maxBanco = parseFloat(Array.max(opciones)) + 1;
	//$.msg(maxBanco);
	if (maxBanco == "-Infinity"){
		maxBanco = 1;
		//$.msg(maxBanco);
	}
	$('#idFamilia').append(new Option(nombre, maxBanco, true, true));
}

function nuevoImpuesto(){
	$("#fondo").show();
	$("#msg").show();
	//$("#idMateriaPrima").val(idMateriaPrima);
	//document.getElementById('presenta').innerHTML = 'Categorias para la materia prima con id: ' + idMateriaPrima;
	//document.getElementById('presenta').innerHTML = 'Categorias de ' + nombre;
	document.getElementById('presenta').innerHTML = 
		'<fieldset>' +
			'<legend><span>Nuevo impuesto</span></legend>' +
				'<table width="100%" cellpadding="2" cellspacing="2" border="0">' +
					'<tr>' +
						'<td class="label"><label name="Nombre" value="Nombre">Nombre</label></td>' +
						'<td nowrap="nowrap">' +
							'<input id="nombreImp" key="nombre" label="Nombre"/>' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<td class="label"><label name="Porcentaje" value="Porcentaje">Porcentaje</label></td>' +
						'<td nowrap="nowrap">' +
							'<input id="porcentajeImp" key="porcentaje" label="Porcentaje"/>' +
						'</td>' +
					'</tr>' +
				'</table>' +
		'</fieldset>' +
		'<br />' +
		'<a id="bot_inserta" class="botonInserta" href="javascript:addImpuesto();" title="Insertar">' +
			'<img src="img/j_button1_add.png" alt="Insertar " title="Insertar"/>Insertar' +
		'</a>' +
		'<br />' +
		'<br />';
	$("#msg").attr("height", "20px");
}

function addImpuesto(){
	$("#nombreImpuesto").val($("#nombreImp").val());
	nombre = $("#nombreImpuesto").val();
	$("#porcentaje").val($("#porcentajeImp").val());
	$('#submitSalvaImpuesto').trigger('click');
	ocultar();
	var maxBanco = 0;
	opciones = $("#idImpuesto").children();
	//$.msg(opciones.length);
	for (i = 0; i < opciones.length; i++){
		opciones[i] = opciones[i].value;
	}
	maxBanco = parseFloat(Array.max(opciones)) + 1;
	//$.msg(maxBanco);
	if (maxBanco == "-Infinity"){
		maxBanco = 1;
		//$.msg(maxBanco);
	}
	$('#idImpuesto').append(new Option(nombre, maxBanco, true, true));
}

function registraProducto(){
	stock = $("#text_stock").val();
	stockAgrupado = $("#text_stockAgrupado").val();
	if (parseFloat(stock) > 0 || parseFloat(stockAgrupado) > 0){
		$.confirm("El producto que intentas modificar tiene actualmente stock. Si modificas este producto, podrias generar inconsistencias en la base de datos. &#191Deseas modificar de todos modos?",
			function(){
				actualizar();
			},
			function(){
				$.msg("Cancelada la actualizacion del producto");
			}
		);
	}else{
		actualizar();
	}
}

function actualizar(){
	cuantosGruposSeleccionados = $(".treeViewSeleccion").get().length;
	//alert(cuantosGruposSeleccionados);
	//Miramos que ningun envase tenga cantidad 0
	cantidadesEnvases = $(".inputCantidadEnvase").get();
	flag = true;
	if (cantidadesEnvases.length == 0){
		flag = false;
		$.msg("No se puede definir un producto sin envases asociados");
	}else{
		for (i = 0; i < cantidadesEnvases.length; i++){
			id = cantidadesEnvases[i].id;
			cantidad = $("#" + id).val();
			if (parseFloat(cantidad) == 0){
				flag = false;
				$.msg("La cantidad de cada envase asociado al producto debe ser mayor que cero.");
				break;
			}
		}
	}
	if (flag){
		if (tipo == 13){//Definimos un item
			cantidadesIngredientes = $(".inputCantidadIngrediente").get();
			if (cantidadesIngredientes.length == 0){
				flag = false;
				$.msg("No se puede definir un EAN13 sin materias primas asociadas");
			}else{
				for (i = 0; i < cantidadesIngredientes.length; i++){
					id = cantidadesIngredientes[i].id;
					cantidad = $("#" + id).val();
					if (parseFloat(cantidad) == 0){
						flag = false;
						$.msg("La cantidad de cada materiaPrima asociado al producto debe ser mayor que cero.");
						break;
					}
				}
			}
		}else{
			if(flag){
				cantidadesEANs13 = $(".inputCantidadEAN13").get();
				if (cantidadesEANs13.length == 0){
					flag = false;
					$.msg("No se puede definir un EAN14 sin EANs13 asociados");
				}else{
					for (i = 0; i < cantidadesEANs13.length; i++){
						id = cantidadesEANs13[i].id;
						cantidad = $("#" + id).val();
						if (parseFloat(cantidad) == 0){
							flag = false;
							$.msg("La cantidad de cada EAN13 asociado al producto debe ser mayor que cero.");
							break;
						}
					}
				}
			}
		}
	}
	if (flag){
		if ($("#text_nombre").val() == ""){
			$.msg("Debe introducir un nombre para el producto");
		}else{
			if (cuantosGruposSeleccionados == 0){
				$.msg("Debe seleccionar el grupo del producto");
			}else{
				$.confirm("&#191Actualizar producto?",
					function(){
						$.msg("Actualizando el producto...");
						var $url = "ActualizaProducto.action?";
						//1. Datos generales del producto
						$url += "idProducto=" + $("#text_id").val() + "&nombre=" + $("#text_nombre").val() +
							"&descripcion=" + $("#text_descripcion").val() + "&stock=" + $("#text_stock").val() +
							"&stockMinimo=" + $("#text_stockMinimo").val() + "&codigoEan=" + $("#text_ean").val() +
							"&peso=" + $("#text_peso").val() + "&precio=" + $("#text_precio").val() + "&caducidad=" + $("#text_caducidad").val() + 
							"&idImpuesto=" + $("#dropdown_impuestos").val() + "&idCategoria=" + $("#dropdown_categorias").val();
						if ($("#check_granel").is(':checked')){
							$url += "&mostrar=1";
						}else{
							$url += "&mostrar=0";
						}
						//Nuevo
						idGrupo = $(".treeViewSeleccion").get(0).id;
						//alert(idGrupo);
						$url += "&idFamilia=0&idGrupo=" + idGrupo;
						//$.msg($url);
						//2. Datos de composicion (Agrupacion)
						if (tipo == 14){
							items = $("#tbody_items").children();
							for (i = 0; i < items.length; i++){
								id = items.get(i).id;
								frag = id.split("_");
								$url += "&Item_" + frag[1] + "=" + $("#CANTIDAD_Item_" + frag[1]).val() + "&CANTIDAD_Item_" + frag[1] + "=" + $("#CANTIDAD_Item_" + frag[1]).val();
								if (i < parseFloat(parseFloat(items.length) - 1)){
								}
							}
						}
						//3. Ingredientes asociados
						if (tipo == 13){
							ingredientes = $("#tabla_MP_body").children();
							//$.msg(ingredientes);
							//$.msg(ingredientes.length);
							for (i = 0; i < ingredientes.length; i++){
								id = ingredientes.get(i).id;
								//$.msg(id);
								//seleccionMP_20 Ó seleccionEAN13_45
								//alert(id);
								frag = id.split("_");
								//$.msg(frag);
								frag2 = frag[0].split('seleccion');
								//alert(frag2[0]);
								//alert(frag2[1]);
								tipoProd = frag2[1];
								$url += "&" + tipoProd + "_" + frag[1] + "="
									+ "&CANTIDAD_" + tipoProd + "_" + frag[1] + "=" + $("#CANTIDAD_" + tipoProd + "_" + frag[1]).val();
								if (i < parseFloat(parseFloat(ingredientes.length) - 1)){
									//$.msg("escribe");
									//$url += "&";
								}
							}
						}
						//4. Envases asociadas
						envases = $("#tabla_EN_body").children();
						//$.msg(envases);
						//$.msg(envases.length);
						for (i = 0; i < envases.length; i++){
							id = envases.get(i).id;
							//$.msg(id);
							//seleccionEN_20
							frag = id.split("_");
							//$.msg(frag);
							$url += "&EN_" + frag[1] + "=" + $("#CANTIDAD_EN_" + frag[1]).val() + "&CANTIDAD_EN_" + frag[1] + "=" + $("#CANTIDAD_EN_" + frag[1]).val();
							if (i < parseFloat(parseFloat(envases.length) - 1)){
								//$.msg("escribe");
								//$url += "&";
							}
						}
						//alert($url);
						$.ajax({
							type: 'POST',
							url: $url,
							cache: false,
							async:false,
							success: function(html){
								$.msg("Producto actualizado");
							}
						});
						//2. Consultamos los productos
						consProdu();
					},
					function(){
						$.msg("Cancelada la actualizacion del producto");
					}
				);
			}
		}
	}
}