$(document).ready(function() {
	numeros = $(".numeroMilesDecimal");
	for (h = 0; h < numeros.length; h++){
		id = numeros[h].id;
		frag = $("#" + id).text().split(" ");
		valor = frag[0];
		$("#" + id).text(formatearNumeroMilesDecimales("" + valor));
	}
	$('#tablaRE').dataTable({
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
			null
		],
		"sPaginationType": "full_numbers"
	});
	tipos = $(".tipos");
	String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ""); };
	for (i = 0; i < tipos.length; i++){
		codigo = tipos[i].id.split("_")[1];
		if ($("#" + tipos[i].id).text().trim() == 'M' || $("#" + tipos[i].id).text().trim() == 'E'){
			$("#bultos_" + codigo).attr("style", "vertical-align: middle; visibility: block;");
		}
	}
	//alert(1);
	tiposRegistros = $(".tipoReg").get();
	//alert(tiposRegistros.length);
	for (i = 0; i < tiposRegistros.length; i++){
		id = tiposRegistros[i].id;
		//alert(id);
		//alert($("#" + id).val());
		tipo = $("#" + id).val();
		//alert(tipo);
		if (tipo == 'M' || tipo == 'E'){
			$("#bultos_" + id).show();
			$("#etiqueta_" + id).show();
			//$("#todasEti_" + id).hide();
		}else{
			if (tipo == 'P'){
				//$("#todasEti_" + id).show();
				$("#etiquetaProductoFinal_" + id).show();
				$("#bultos_" + id).hide();
			}
		}
	}
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_reporte").attr("onclick" , "javascript:reporteRE();");
	$("#bot_reporte").show();
	$("#bot_listar").attr("onclick" , "javascript:listaRE();");
	$("#bot_listar").show();
	$("#bot_vuelve").attr("onclick", "javascript:volverAlEscritorio();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	if (!$("#consulta").is(':visible')){
		$("#bot_consulta").addClass('ocultando');
		$("#bot_consulta").removeClass('mostrando');
	}
	$("#bot_consulta").show();
	/* Miramos si permitimos borrar algun registro de entrada */
	registros = $(".registrosBorrar").get();
	for (i = 0; i < registros.length; i++){
		id = registros[i].id;
		if ($("#" + id).val() == "true"){
			frag = id.split("podemosBorrar_");
			codigoEntrada = frag[1];
			$("#bt_deshabilitarRE_" + codigoEntrada).show();
		}
	}
	setTimeout('$("#tablaRE").show();', 150);
	$.unblockUI();
});

function deshabilitarRE(entrada){
	$.confirm("&#191Confirma que desea eliminar el registro de entrada " + entrada + "?<br />Despues de borrar un registro de entrada no es posible volver a recuperarlo.",
		function(){
			$url = "DeleRE.action?codigoEntrada=" + entrada;
			$.ajax({
				url: $url,
				cache: false,
				async:false,
				success: function(html){
					consRE();
				}
			});
		},
		function(){
			$.msg("CANCELADO el borrado del registro " + entrada);
		}
	);
}