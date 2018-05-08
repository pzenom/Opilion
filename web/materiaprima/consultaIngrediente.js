$(document).ready(function() {
	ajustarRol();
	numeros = $(".numeroMilesDecimal");
	for (h = 0; h < numeros.length; h++){
		id = numeros[h].id;
		frag = $("#" + id).text().split(" ");
		valor = frag[0];
		$("#" + id).text(formatearNumeroMilesDecimales("" + valor));
	}
	$('#tablaIngredientes').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 1, 'asc' ]],
		"aoColumns": [
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	// CONFIGURACION DE LA BOTONERA
	$(".botonBotonera").hide();//Ocultamos todos los botones
	$(".botonesCategorias").hide();
	if ($("#bot_nuevo").hasClass('puedeVer')){
		$("#bot_nuevo").attr("onclick", "javascript:nuevoIngre();");
		$("#bot_nuevo").show();
	}
	$("#bot_listar").attr("onclick" , "javascript:consuIngres();");
	$("#bot_listar").show();
	$("#bot_reporte").attr("onclick" , "javascript:reporteIngredientes();");
	$("#bot_reporte").show();
	$("#bot_consulta").show();
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	if (!$("#consulta").is(':visible')){
		$("#bot_consulta").addClass('ocultando');
		$("#bot_consulta").removeClass('mostrando');
	}
	//BOTONERA CONFIGURADA
	setTimeout('$("#tablaIngredientes").show();', 100);
});

function muestraCategorias(idMateriaPrima, nombre){
	var $url = "MateriasPrimasAjax.action?idMateriaPrima=" + idMateriaPrima;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#ajaxCategorias").empty();
			$("#ajaxCategorias").append(html);
		}
	});
	simple_tooltip("celdaIngrediente_" + idMateriaPrima, "categoriasIngredientes", 'tooltip', nombre);
}

function simple_tooltip(target_items, tipo, name, nombreIngrediente){
	$('#' + target_items).each(function(i){
		if (tipo == "categoriasIngredientes"){
			if ($("#" + name + i).length > 0)
				$("#" + name + i).remove();
			html = "<div style='font-size:14px' class='" + name + "' id='" + name + i + "'>";
			categorias = $(".categoria");
			html += "<table><thead><tr><th>Categoria</th><th>Stock</th></tr></thead><tbody>";
			if (categorias.length == 0){
				html += '<tr><td colspan="2">No hay categorias asociadas</td></tr>';
			}else{
				for (j = 0; j < categorias.length; j++){
					id = categorias[j].value;
					html += '<tr><td>' + $("#nombreCategoria_" + id).val() + '</td><td>' + $("#stockCategoria_" + id).val() + '</td></tr>';
				}
			}
			html += "</tbody></table></div>";
			$('body').append(html);
			var my_tooltip = $("#" + name + i);
			$(this).removeAttr("title").mouseover(function(){
					my_tooltip.css({opacity:0.8, display:"none"}).fadeIn(100);
			}).mousemove(function(kmouse){
					my_tooltip.css({left:kmouse.pageX+15, top:kmouse.pageY+15});
			}).mouseout(function(){
					my_tooltip.remove();
			});
		}
	});
}

function filtraIngredientes(){
	idGrupo = $("#dropdown_tipos").val();
	var $url = "";
	if (idGrupo == undefined)
		$url = "FiltroIngredientes.action?idGrupo=0";
	else
		$url = "FiltroIngredientes.action?idGrupo=" + idGrupo;
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#ingredientes").empty();
		$("#ingredientes").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "materiaprima/consultaIngrediente.js",
		dataType: "script"
	});
}

function verLotesIngrediente(idMateriaPrima){
	var $url = "";
	$url = "VerLotesIngrediente.action?idMateriaPrima=" + idMateriaPrima;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consIngre").empty();
			$("#widget_consIngre").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "materiaprima/verLotesIngrediente.js",
		dataType: "script"
	});
}