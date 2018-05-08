$(document).ready(function() {
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_trazabilidad").attr("onclick", "javascript:trazabilidad();");
	$("#bot_trazabilidad").show();
	$("#bot_vuelve").attr("onclick", "javascript:volverAlEscritorio();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	$('#loteTraza').focus();
	
	$('#loteTraza').bind("enterKey",function(e){
	   trazabilidad();
	});
	$('#loteTraza').keyup(function(e){
		if(e.keyCode == 13){
			$(this).trigger("enterKey");
		}
	});
});

function trazabilidad() {
	if ($("#loteTraza").val() != "" && $("#loteTraza").val().length == 8 && $("#loteTraza").val().charAt(0) == '0'){
		$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
		$.ajax({
			url: "ConsultaTrazaProducto.action?lote=" + $("#loteTraza").val(),
			cache: false,
			async: true,
			success: function(html){
				$("#widget_consTraza").empty();
				$("#widget_consTraza").append(html);
				/* Configuramos la botonera */
				$(".botonBotonera").hide();
				$("#bot_vuelve").attr("onclick", "javascript:consultarTrazabilidad();");
				$("#bot_vuelve").show();
				/* Formateo las fechas que llegan de la forma aaaa-mm-dd */
				fechas = $('.fechas').get();
				cuantas = fechas.length;
				for (i = 0; i < cuantas; i++){
					id = fechas[i].id;
					fecha = $('#' + id).val();
					//alert(fecha);
					frag = fecha.split('-');
					if (frag.length == 3){
						fechaNueva = '';
						fechaNueva = frag[2] + "/" + frag[1] + "/" + frag[0];
						$('#' + id).val(fechaNueva);
					}
				}
				if ($('.informacionEnvasado').get().length == 0){
					$('#fieldsetInformacionEnvasado').hide();
				}
				if ($('.informacionVenta').get().length == 0){
					$('#fieldsetInformacionVenta').hide();
				}
				if ($('.informacionDevoluciones').get().length == 0){
					$('#fieldsetInformacionDevoluciones').hide();
				}
				$.unblockUI();
			}
		});
	}else{
		if ($("#loteTraza").val().charAt(0).toUpperCase() == 'E'){
			$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
			$.ajax({
				url: "ConsultaTrazaRegistroEntrada.action?lote=" + $("#loteTraza").val(),
				cache: false,
				async: true,
				success: function(html){
					$("#widget_consTraza").empty();
					$("#widget_consTraza").append(html);
					/* Configuramos la botonera */
					$(".botonBotonera").hide();
					$("#bot_vuelve").attr("onclick", "javascript:consultarTrazabilidad();");
					$("#bot_vuelve").show();
					/* Formateo las fechas que llegan de la forma aaaa-mm-dd */
					fechas = $('.fechas').get();
					cuantas = fechas.length;
					for (i = 0; i < cuantas; i++){
						id = fechas[i].id;
						fecha = $('#' + id).val();
						//alert(fecha);
						frag = fecha.split('-');
						if (frag.length == 3){
							fechaNueva = '';
							fechaNueva = frag[2] + "/" + frag[1] + "/" + frag[0];
							$('#' + id).val(fechaNueva);
						}
					}
					$.unblockUI();
				}
			});
		}else
			$.msg("Debe introducir un lote correcto para poder continuar");
	}
}

/*function nuevaConsultaTraza(){
	$.ajax({
		url: "trazabilidad/consultaTrazaInversa.jsp",
		cache: false,
		async:false,
		success: function(html){
			$("#contenido").empty();
			$("#contenido").append(html);
			$.msg("Introduzca un lote",{live:7000});
			$.ajax({
				type: "GET",
				url: "trazabilidad/consultaTrazaInversa.js",
				dataType: "script"
			});
		}
	});
}*/