$(document).ready(function(){
	$('#tablaFacturas').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'desc' ]],
		"aoColumns": [
			null,
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	numeros = $(".numeroMilesDecimal");
	for (h = 0; h < numeros.length; h++){
		id = numeros[h].id;
		frag = $("#" + id).text().split(" ");
		valor = frag[0];
		$("#" + id).text(formatearNumeroMilesDecimales("" + valor) + " " + String.fromCharCode(8364));
	}
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick" , "javascript:volverAlEscritorio();");
	$("#bot_vuelve").show();
	$("#bot_listar").attr("onclick" , "javascript:listaFacturas();");
	$("#bot_listar").show();
	$("#bot_generarFacturaLibre").show();
	$("#bot_consulta").show();
	$("#widget_menu").show();
	$("#bot_vuelve").show();
	if (!$("#consulta").is(':visible')){
		$("#bot_consulta").addClass('ocultando');
		$("#bot_consulta").removeClass('mostrando');
	}
	estados = $(".estados").get();
	for (i = 0; i < estados.length; i++){
		id = estados[i].id;
		frag = id.split('_');
		codigoFactura = frag[1];
		estado = $("#estado_" + codigoFactura).val();
		//0: Cobrada; 1: Emitida confirmada entrega albaran; 2: Emitida SIN confirmación de entrega del albarán; 3: No emitida
		if (estado == "3"){
			//Mirar si cuotas
			//alert($("#cuotas_" + codigoFactura).val());
			if ($("#cuotas_" + codigoFactura).val() == "si"){
				$("#celdaCuotas_" + codigoFactura).show();					
			}else{
				$("#celdaEmitir_" + codigoFactura).show();				
			}
			$("#celdaEditar_" + codigoFactura).show();
			$("#celdaAnular_" + codigoFactura).show();
		}else{
			if (estado == "1" || estado == "2"){
				$("#celdaImprimir_" + codigoFactura).show();
				$("#celdaCobrar_" + codigoFactura).show();
				$("#celdaAgrupar_" + codigoFactura).show();
				$("#celdaAnular_" + codigoFactura).show();
			}else{
				if (estado == "0"){
					$("#celdaImprimir_" + codigoFactura).show();
					$("#celdaCobradaNoAnular_" + codigoFactura).show();
					$("#celdaCobradaNoCobrar_" + codigoFactura).show();
				}else{
					if (estado == "4"){
						$("#celdaControlCuotas_" + codigoFactura).show();
					}
				}
			}
		}
	}
});

function muestraEstadoFactura(estado, idFactura){
	$url = "MuestraInformacionEstado.action?idPedido=" + idFactura + "&estado=" + estado;
	//alert($url);
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#ajaxEstados").empty();
			$("#ajaxEstados").append(html);
		}
	});
	simple_tooltip("celdasEstados_" + idFactura, 'tooltip');
}

function simple_tooltip(target_items, name){
	$('#' + target_items).each(function(i){
		if ($("#" + name + i).length > 0)
			$("#" + name + i).remove();
		html = "<div style='font-size:14px' class='" + name + "' id='" + name + i + "'>" +
			"<span>Estado de la factura	" + $("#idPedido").val() + "</span>";
		estados = $(".estado");
		for (j = 0; j < estados.length; j++){
			html += "<p>" + estados[j].value + "</p>";
		}
		html += "</div>";
		$('body').append(html);
		var my_tooltip = $("#" + name + i);
		$(this).removeAttr("title").mouseover(function(){
				my_tooltip.css({opacity:0.8, display:"none"}).fadeIn(100);
		}).mousemove(function(kmouse){
				my_tooltip.css({left:kmouse.pageX+15, top:kmouse.pageY+15});
		}).mouseout(function(){
				my_tooltip.remove();
		});
	});
}