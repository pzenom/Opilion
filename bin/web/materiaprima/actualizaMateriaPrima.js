$(document).ready(function() {
	$("#dropdown_grupos").val($("#idGrupo").val());
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick", "javascript:consuIngres();");
	$("#bot_actualizar").attr("onclick", "javascript:actualizaIngrediente();");
	$("#bot_vuelve").show();
	$("#bot_actualizar").show();
	$("#widget_menu").show();
	$("#categorizacion").treeview();
	grupo = $("#idGrupo").val();
	grupoCategorizacionSeleccionado(grupo);
});

function categoriaSeleccionada(){
	indice = document.formu.dropdown_categorias.selectedIndex;
	id = document.formu.dropdown_categorias.options[indice].value;
	nombre = document.formu.dropdown_categorias.options[indice].text;
	if ($("#categoria_" + id).length == 0 && indice > 0){
		var fila = document.createElement('tr');
		// Se le a�ade un id dependiente de la categoria seleccionada
		fila.setAttribute("id", "categoria_" + id);
		// Crear la columna de la materia prima usada
		fila.innerHTML = '<input class="categoriasSeleccion" id="' + id + '" name="' + id + '" value="' + id + '" style="display:none;"></input><td id="' + id + '" name="' + id + '">' + id + '</td><td>' + nombre + '</td>' +
		//'<td><input id="stock_' + id + '" readonly key="stock" value="0"/></td>' +
		'<td><a href="javascript:eliminaCategoria(\''+id+'\')" title="Eliminar este ingrediente"><img src="img/cancel.png" alt="Eliminar" title="Eliminar ingrediente"/></a>';
		document.getElementById('bodyCategorias').appendChild(fila);
	}
}

function eliminaCategoria(id){
	if ($("#stock_" + id).val() > 0)
		$.msg("Imposible eliminar una categoria para la cual existe stock");
	else{
		$("#" + id).remove();
		document.getElementById("categoria_" + id).parentNode.removeChild(document.getElementById("categoria_" + id));
	}
}

function actualizaIngrediente(){
	cuantosGruposSeleccionados = $(".treeViewSeleccion").get().length;
	//alert(cuantosGruposSeleccionados);
	if (cuantosGruposSeleccionados == 0){
		$.msg("Debe seleccionar el grupo de la materia prima");
	}else{
		var nombre = $("#text_nombre").val();
		//$.msg(nombre);
		if (nombre == "") {
			$.msg("Introduzca un nombre");
		} else {
			if(confirm("Es correcta la materia prima?")) {
			
				//Nuevo
				idGrupo = $(".treeViewSeleccion").get(0).id;
				//alert(idGrupo);

				//1. Url que guarda la materia prima
				var $url = "ActualizarMateriaPrima.action?nombre=" + nombre + "&idGrupo=" + idGrupo +
					"&idMateriaPrima=" + $("#idMateriaPrima").val();
				//$.msg($url);
				//2. Aniadimos la url con las categorias
				urlCategorias = "&";
				categoriasSeleccion = $(".categoriasSeleccion");
				//$.msg("Cuantas categorias: " + categoriasSeleccion.length);
				for (i = 0; i < categoriasSeleccion.length; i++){
					id = categoriasSeleccion[i].id;
					//$.msg(id);
					urlCategorias += id + "=";
					if (i < parseFloat(categoriasSeleccion.length) - 1)
						urlCategorias += "&";
				}
				//$.msg(urlCategorias);
				if (urlCategorias.length > 1)
					$url += urlCategorias;
				//$.msg($url);
				$.ajax({
					type: 'POST',
					url: $url,
					cache: false,
					async:false,
					success: function(html){
						$.msg("Materia prima actualizada correctamente");
					}
				});
				consuIngres();
			}
		}
	}
}