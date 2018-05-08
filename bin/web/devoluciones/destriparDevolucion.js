$(document).ready(function() {
	$('#tablaDestripado').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick" , "javascript:consDevol();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	setTimeout('$("#tablaDestripado").show();', 400);
});

function reaprovechar(lote){
	var $url = "";
	var prompt = "";
	var valor = "";
	frase = "Introduzca la cantidad que se reaprovechar&aacute;";
	valor = "1";
	$.prompt(frase, valor,
		function(value){
			if (value == ""){
				$.msg('Introduzca la cantidad');
			}else{
				//alert(IsNumeric(value));
				if (IsNumeric(value))
					reaprovecharCantidad(lote, value);
				else
					$.msg('Introduzca &uacute;nicamente n&uacute;meros');
			}
		},
		function(){
			$.msg("Cancelado");
		}
	);
}

function reaprovecharCantidad(lote, cantidad){
	$.msg('Reaprovechando lote ' + lote);
	var $url = "";
	$url = "ReaprovecharDevolucion.action?lote=" + lote + "&teorica=" + cantidad;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			/*$("#widget_consDevol").empty();
			$("#widget_consDevol").append(html);*/
			$.msg('Puedes consultar el lote devuelto en los registros de entrada');
			consDevol();
		}
	});
	/*$.ajax({
		type: "POST",
		url: "devoluciones/listarDevoluciones.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});*/
}