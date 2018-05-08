$(document).ready(function(){
	numeros = $(".numeroMilesDecimal");
	for (h = 0; h < numeros.length; h++){
		id = numeros[h].id;
		frag = $("#" + id).text().split(" ");
		valor = frag[0];
		$("#" + id).text(formatearNumeroMilesDecimales("" + valor) + " " + String.fromCharCode(8364));
	}
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	vuelveEscritorio = $('#vuelveEscritorio').val();
	//alert(vuelveEscritorio);
	if (vuelveEscritorio == 'true'){
		$("#bot_vuelve").attr("onclick", "javascript:volverAlEscritorio();");
	}else{
		if ($("#btMenuFacturas").hasClass('active')){
			$("#bot_vuelve").attr("onclick", "javascript:listaFacturas();");
		}else
			$("#bot_vuelve").attr("onclick", "javascript:listaAlbaranes();");
	}
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	/**/
	$('#tablaCuotasFacturas').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'desc' ]],
		"aoColumns": [
			null,
			null,
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
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