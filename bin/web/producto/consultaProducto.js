$(document).ready(function() {
	ajustarRol();
	numeros = $(".numeroMilesDecimal");
	for (h = 0; h < numeros.length; h++){
		id = numeros[h].id;
		frag = $("#" + id).text().split(" ");
		valor = frag[0];
		$("#" + id).text(formatearNumeroMilesDecimales("" + valor));
	}
	$('#tablaProductos').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 2, 'asc' ]],
		"aoColumns": [
			null,
			null,
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	// CONFIGURACION DE LA BOTONERA
	$(".botonBotonera").hide();//Ocultamos todos los botones
	$(".botonesCategorias").hide();
	$("#bot_vuelve").attr("onclick", "javascript:volverAlEscritorio();");
	$("#bot_vuelve").show();
	$("#bot_consulta").show();
	if (!$("#consulta").is(':visible')){
		$("#bot_consulta").addClass('ocultando');
		$("#bot_consulta").removeClass('mostrando');
	}
	if ($("#bot_nuevo").hasClass('puedeVer')){
		$("#bot_nuevo").attr("onclick", "javascript:nuevoProdu();");
		$("#bot_nuevo").show();
	}
	$("#bot_listar").attr("onclick", "javascript:consProdu();");
	$("#bot_listar").show();
	$("#bot_reporte").attr("onclick", "javascript:reporteProductos();");
	$("#bot_reporte").show();
	$("#widget_menu").show();
	//BOTONERA CONFIGURADA
	setTimeout('$("#tablaProductos").show();', 100);
});

function filtraProductos(){
	var $url = "FiltroProductos.action?idGrupo=" + $("#dropdown_grupos").val() + "&idFamilia=" + $("#dropdown_familias").val() + "&idCategoria=" + $("#dropdown_categorias").val();
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#productos").empty();
		$("#productos").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "producto/consultaProducto.js",
		dataType: "script"
	});
}

function reporteProductos(){
	parent.get_ventana_emergente('ConsCL','ConsProductosJR.action','no','no','800','640','','');
}