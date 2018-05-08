var action = "";

function reporteFac(codigoFactura){
	action = "ConsFactJR.action?codigoFactura=" + codigoFactura;
	//alert(1);
	/*$("#dialogo").dialog({
		close: function(event, ui) {
			if ( event.originalEvent && $(event.originalEvent.target).closest(".ui-dialog-titlebar-close").length ) {
				$.msg("Cancelado el reporte");
			}
		}}).find("button").click(function(){
			//Cerramos el dialogo
			$(this).closest(".ui-dialog-contenido").dialog("close");
		});*/
	preparar();
}

function preparar(){
	action += "&encabezado=true"; //+ $('#checkEncabezado').is(':checked');
	parent.get_ventana_emergente('ALBA', action,'no','no','800','640','','');
}

function editarFactura(codigoFactura){
	flag = true;
	if ($("#widget_consPedido").length){
		flag = true;
		//Ya estabamos en el menu de salidas
		$url = "EditFact.action?codigoFactura=" + codigoFactura;
		$.ajax({
			url: $url,
			cache: false,
			async: false,
			success: function(html){
				$("#widget_consPedido").empty();
				$("#widget_consPedido").append(html);
			}
		});
	}else{
		//Estamos en el escritorio
		$('#vuelveEscritorio').val(true);
		flag = false;
		functionFacturasMenu(false);
	}
	if (flag){
		$.ajax({
			type: "POST",
			url: "registrosalida/editarFactura.js",
			dataType: "script"
		});
		$.ajax({
			type: "POST",
			url: "registrosalida/cuotas.js",
			dataType: "script"
		});
		$.ajax({
			type: "POST",
			url: "registrosalida/edicionCamposFacturas.js",
			dataType: "script"
		});
		$.ajax({
			type: "POST",
			url: "js/script.js",
			dataType: "script"
		});
	}else{
		editarFactura(codigoFactura);
	}
}

function anularFactura(codigoFactura){
	$.confirm("&#191Anular la factura " + codigoFactura + "? Se va a generar una factura rectificativa.",
		function(){
			alert("Actualmente no es posible eliminar una factura");
		},
		function(){
			$.msg("La factura NO se ha anulado");
		}
	);
}

function cobrarFactura(codigoFactura){
	$.confirm("&#191Marcar la factura como cobrada?.",
		function(){
			$url = "ActualizarEstadoFactura.action?estado=0&codigoFactura=" + codigoFactura;
			$.ajax({
				url: $url,
				cache: false,
				async:false,
				success: function(html){
					$("#widget_consPedido").empty();
					$("#widget_consPedido").append(html);
				}
			});
			if ($("#widget_consPedido").length){
				flag = true;
				//Ya estabamos en el menu de salidas
				$.ajax({
					type: "POST",
					url: "registrosalida/consRegiFact.js",
					dataType: "script"
				});
				$.ajax({
					type: "POST",
					url: "js/script.js",
					dataType: "script"
				});
			}else{
				//Estamos en el escritorio
				volverAlEscritorio();
			}
		},
		function(){
			$.msg("La factura NO se ha marcado como cobrada");
		}
	);
}

function emitirFactura(codigoFactura){
	$.confirm("&#191Emitir la factura " + codigoFactura + "? No sera posible editar dicha factura despues de emitirla.",
		function(){
			$url = "ActualizarEstadoFactura.action?estado=1&codigoFactura=" + codigoFactura;
			$.ajax({
				url: $url,
				cache: false,
				async: false,
				success: function(html){
					$("#widget_consPedido").empty();
					$("#widget_consPedido").append(html);
				}
			});
			if ($("#widget_consPedido").length){
				flag = true;
				//Ya estabamos en el menu de salidas
				$.ajax({
					type: "POST",
					url: "registrosalida/consRegiFact.js",
					dataType: "script"
				});
				$.ajax({
					type: "POST",
					url: "js/script.js",
					dataType: "script"
				});
			}else{
				//Estamos en el escritorio
				volverAlEscritorio();
			}
		},
		function(){
			$.msg("La factura NO se ha emitido");
		}
	);
}

function cuotasFactura(codigoFactura){
	$.confirm("&#191Dividir la factura " + codigoFactura + " en las cuotas definidas? No sera posible deshacer este paso posteriormente.",
		function(){
			$url = "DividirFacturaCuotas.action?codigoFactura=" + codigoFactura;
			$.ajax({
				url: $url,
				cache: false,
				async:false,
				success: function(html){
					$("#widget_consPedido").empty();
					$("#widget_consPedido").append(html);
				}
			});
			if ($("#widget_consPedido").length){
				flag = true;
				//Ya estabamos en el menu de salidas
				$.ajax({
					type: "POST",
					url: "registrosalida/consRegiFact.js",
					dataType: "script"
				});
				$.ajax({
					type: "POST",
					url: "js/script.js",
					dataType: "script"
				});
			}else{
				//Estamos en el escritorio
				volverAlEscritorio();
			}
		},
		function(){
			$.msg("Cancelado");
		}
	);
}

function controlCuotas(codigoFactura){
	flag = true;
	if ($("#widget_consPedido").length){
		flag = true;
		//Ya estabamos en el menu de salidas
		$url = "VerCuotasFactura.action?codigoFactura=" + codigoFactura;
		$.ajax({
			url: $url,
			cache: false,
			async: false,
			success: function(html){
				$("#widget_consPedido").empty();
				$("#widget_consPedido").append(html);
			}
		});
	}else{
		//Estamos en el escritorio
		$('#vuelveEscritorio').val(true);
		flag = false;
		functionFacturasMenu(false);
	}
	if (flag){
		$.ajax({
			type: "POST",
			url: "registrosalida/verCuotasFactura.js",
			dataType: "script"
		});
		$.ajax({
			type: "POST",
			url: "js/script.js",
			dataType: "script"
		});
	}else{
		controlCuotas(codigoFactura);
	}
}