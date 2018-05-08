$(document).ready(function() {
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_insertar").attr("onclick" , "javascript:insertarDevolucion();");
	$("#bot_insertar").show();
	$("#bot_vuelve").attr("onclick" , "javascript:consDevol();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	$("#text_lote").focus();
	$("#text_cantidad").keydown(function(event) {
        // Allow: backspace, delete, tab, escape, and enter
		//return;
        if ( event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || event.keyCode == 13 || 
             // Allow: Ctrl+A
            (event.keyCode == 65 && event.ctrlKey === true) || 
             // Allow: home, end, left, right
            (event.keyCode >= 35 && event.keyCode <= 39)) {
                 // let it happen, don't do anything
                 return;
        }
        else {
            // Ensure that it is a number and stop the keypress
            if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
                event.preventDefault();
            }   
        }
    });
});

function insertarDevolucion(){
	if ($("#text_lote").val() != "" && $("#text_lote").val() != "undefined"){
		if ($("#text_cantidad").val() != "" && $("#text_cantidad").val() != "undefined"){
			if (parseFloat($('#dropdown_clientes').val()) > 0){
				$.confirm("&#191Insertar la devoluci&oacute;n?",
					function(){
						var $url = "";
						checked = $("#checkbox_devolver").is(':checked');
						reaprovechar = $("#checkbox_reaprovechar").is(':checked');
						$url = "DevolverLote.action?lote=" + $("#text_lote").val() +
							"&cantidad=" + $("#text_cantidad").val() +
							"&fecha=" + $("#date_fecha").val() +
							"&notasIncidencias=" + $("#text_observa").val() +
							"&idIncidencias=" + $("#dropdown_estadosFabas").val() +
							"&idClienteDevolucion=" + $('#dropdown_clientes').val() +
							"&reaprovechar=" + reaprovechar;
						lote = $("#text_lote").val();
						cantidad = $("#text_cantidad").val();
						//COMPROBACIÓN
						flagSigue = false;
						//Para el lote introducido, se envasó como mínimo la cantidad introducida?
						$urlComprobacion = "CompruebaLoteCantidad.action?lote=" + lote + "&cantidad=" + cantidad;
						$.ajax({
							url: $urlComprobacion,
							cache: false,
							async:false,
							success: function(html){
								$("#resultadoComprobacion").empty();
								$("#resultadoComprobacion").append(html);
								comprobacion = $("#comprobacion").val();
								//alert(comprobacion);
								if (comprobacion == "O"){//O = OK
									flagSigue = true;
								}else{
									if (comprobacion == "N"){//N = no existe el lote
										$.msg("El lote " + lote + " no se encuentra en la base de datos.");
										flagSigue = false;
									}else{
										if (comprobacion == "C"){//C = no hay cantidad
											$.msg("No se ha elaborado tanta cantidada del lote " + lote + ".");
											flagSigue = false;
										}/*else{
											if (comprobacion == "S"){//S = ya se ha devuelto todo lo posible
												$.msg("No que " + lote + ".");
												flagSigue = false;
											}
										}*/
									}
								}
							}
						});
						if (flagSigue == true){
							if (checked == true)
								$url += "&devolver=true";
							//$.msg($url);
							$.ajax({
							 url: $url,
							 cache: false,
							 async:false,
							 success: function(html){
								$("#widget_consDevol").empty();
								$("#widget_consDevol").append(html);
								//$.msg("Devolución realizada");
								//$.msg(checked);
								//Si devuelve el stock, seleccionAlmacen.js; si no, listaDevoluciones.js
								$url = "";
								if (checked == true){
									$.msg("Ubique el lote devuelto");
									$url = "ubicacion/seleccionAlmacen.js";
									$("#widget_consDevol").attr("id", "widget_consUbica");
									//$("#widget_menuDevol").attr("id", "widget_menuUbica");
									$("#devolucion").val("true");
									$("#meter").val("true");
									$("#registro").val(lote);
									$("#cantidad").val(cantidad);
								}else{
									$.msg("Devolucion realizada!");
									$url = "devoluciones/listaDevoluciones.js";
								}
								//$.msg("alto");
								$.ajax({
									type: "POST",
									url: $url,
									dataType: "script"
								});
								$.ajax({
									type: "POST",
									url: "js/script.js",
									dataType: "script"
								});
							 }
							});
						}
					},
					function(){
						$.msg("Cancelada la devoluci&oacute;n.");
					}
				);
			}else
				$.msg("Seleccione un cliente");
		}else
			$.msg("Debe introducir la cantidad devuelta");
	}else
		$.msg("Debe insertar el lote del producto devuelto");
}