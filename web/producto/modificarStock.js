$(document).ready(function() {
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_modificar_stock").attr("onclick", "javascript:modificaStockRegistro();");
	$("#bot_modificar_stock").show();
	$("#bot_vuelve").attr("onclick", "javascript:volverAlEscritorio();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	$('#loteRegistro').focus();
	
	/*$('#loteTraza').bind("enterKey",function(e){
	   trazabilidad();
	});
	$('#loteTraza').keyup(function(e){
		if(e.keyCode == 13){
			$(this).trigger("enterKey");
		}
	});*/
	
	var d = new Date();
	var month = d.getMonth()+1;
	var day = d.getDate();
	var hour = d.getHours();
	var minute = d.getMinutes();
	var second = d.getSeconds();
	var fechaActual = ((''+day).length<2 ? '0' : '') + day + '/' +
		((''+month).length<2 ? '0' : '') + month + '/' +
		d.getFullYear() + ' ' +
		((''+hour).length<2 ? '0' :'') + hour + ':' +
		((''+minute).length<2 ? '0' :'') + minute + ':' +
		((''+second).length<2 ? '0' :'') + second;
	$('#fecha').val(fechaActual);
});

function modificaStockRegistro() {
	//alert($('#loteOK').val());//O, M, NR, NL
	if ($("#loteRegistro").val() != ""){
		if ($("#cantidadModificar").val() != "" && $.isNumeric($("#cantidadModificar").val())){
			if ($("#sel_ubicacion").val() > 0){
				loteOK = $('#loteOK').val();
				if (loteOK == 'O'){
					$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
					$.ajax({
						url: "ModificarStockRegistro.action?lote=" + $("#loteRegistro").val() + '&cantidad=' + $("#cantidadModificar").val() + '&idHueco=' + $("#sel_ubicacion").val() + '&causas=' + encodeURIComponent($("#causas").val()),
						cache: false,
						async: true,
						success: function(html){
							respuesta = html.trim();
							//alert(respuesta);
							if (respuesta == 'OK'){
								$.msg("Stock modificado correctamente");
								volverAlEscritorio();
							}else{
								$.msg("ERROR");
							}
							$.unblockUI();
						}
					});
				}else{
					if (loteOK == 'M'){
						$.msg("El lote/registro esta mal formado");
					}else{
						if (loteOK == 'NR'){
							$.msg("Registro de entrada desconocido");
						}else{
							if (loteOK == 'NL'){
								$.msg("Lote desconocido");
							}
						}
					}
				}
			}else{
				$.msg("Selecciona en que ubicacion quieres modificar el stock");
			}
		}else{
			$.msg("Introduce la cantidad correcta");
		}
	}else{
		$.msg("Debe introducir un lote para poder continuar");
	}
}

function loteRegistroModificado(){
	lote = $('#loteRegistro').val().trim();
	//alert(lote);
	$.ajax({
		url: "ValidarLoteRegistroAjax.action?lote=" + encodeURIComponent(lote),
		cache: false,
		async: true,
		success: function(html){
			//alert(html);
			$('#loteOK').val(html.trim());
			$.ajax({
				url: "ListaProductoAjax.action?lote=" + encodeURIComponent(lote),
				cache: false,
				async: true,
				success: function(html){
					//alert(html);
					nombreProducto = $(html).filter('#nombreProducto').val().trim();
					if (nombreProducto == '')
						nombreProducto = 'LOTE/REGISTRO NO IDENTIFICADO';
					$('#producto').val(nombreProducto);
					$url = 'ListaUbicacionesRegistroAjax.action?lote=' + lote;
					$.ajax({
						url: $url,
						cache: false,
						async: true,
						dataType : "html",
						success: function(html){
							$('#sel_ubicacion').empty();
							//alert(html);
							ubicaciones = $(html).filter('.ubicacionAjax');
							//alert(ubicaciones.length);
							var salida = false;
							var i;
							for (i = 0; i < ubicaciones.length; i++){
								ubicacion = ubicaciones.get(i);
								idUbicacion = ubicacion.id.split('_')[1];
								//alert(entrada);
								//alert(ubicacion.id);
								//alert(idUbicacion);
								idUbicacion = $(html).filter('#ubicacion_' + idUbicacion).val().trim();
								idHueco = $(html).filter('#idHueco_' + idUbicacion).val().trim();
								//alert(idUbicacion);
								if (parseFloat(idHueco) == 223)
									salida = true;
								ubicacion = $(html).filter('#descUbicacion_' + idUbicacion).val();
								//alert(ubicacion);
								html = '<option value="' + idHueco + '">' + ubicacion + '</option>';
								$('#sel_ubicacion').append(html);
							}
							//alert(salida);
							if (!salida){
								$('#sel_ubicacion').append('<option value="223">Salida</option>');
							}
							ubicacionSeleccionada();
						},
						error:function(result){
							//alert(1);
							nombreProducto = 'LOTE/REGISTRO NO IDENTIFICADO';
							$('#producto').val(nombreProducto);
						}
					});
				}
			});
		}
	});
	
	//Cargar las ubicaciones para el lote/el registro
	/*$url = 'ListaUbicacionesRegistroAjax.action?lote=' + lote;
	$.ajax({
		url: $url,
		cache: false,
		async: true,
		dataType : "html",
		success: function(html){
			$('#sel_ubicacion').empty();
			//alert(html);
			ubicaciones = $(html).filter('.ubicacionAjax');
			//alert(ubicaciones.length);
			var salida = false;
			var i;
			for (i = 0; i < ubicaciones.length; i++){
				ubicacion = ubicaciones.get(i);
				idUbicacion = ubicacion.id.split('_')[1];
				//alert(entrada);
				//alert(ubicacion.id);
				//alert(idUbicacion);
				idUbicacion = $(html).filter('#ubicacion_' + idUbicacion).val().trim();
				idHueco = $(html).filter('#idHueco_' + idUbicacion).val().trim();
				//alert(idUbicacion);
				if (parseFloat(idHueco) == 223)
					salida = true;
				ubicacion = $(html).filter('#descUbicacion_' + idUbicacion).val();
				//alert(ubicacion);
				html = '<option value="' + idHueco + '">' + ubicacion + '</option>';
				$('#sel_ubicacion').append(html);
			}
			//alert(salida);
			if (!salida){
				$('#sel_ubicacion').append('<option value="223">Salida</option>');
			}
		}
	});*/
}

function ubicacionSeleccionada(){
	$.ajax({
		url: "ListaUbicacionRegistroAjax.action?lote=" + encodeURIComponent(lote) + '&idHueco=' + $("#sel_ubicacion").val(),
		cache: false,
		async: true,
		success: function(html){
			cantidadUbicacion = $(html).filter('#cantidad').val().trim();
			$('#cantidadUbicacion').val('Cantidad actual: ' + cantidadUbicacion);
		}
	});
}