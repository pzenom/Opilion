function elimina (i,j) {
	if(confirm("Desea eliminar el proceso pendiente " + i + "?")){
		$('#idEnvasado').val(i);
		$('#loteAsignado').val(j);
		document.oculto.submit();
	}
}

function irAlProceso(orden){
	flag = true;
	if ($("#widget_consGPEnva").length){
		flag = true;
		//Ya estabamos en el menu de salidas
		var estado = $('#estado_' + orden).text();
		var id = $('#idProducto_' + orden).val();
		var lote = $('#lote_' + orden).text();
		$('#listado').attr('action','VisualizaProcesoEnvasado.action');
		$('#orden').attr('value', orden);
		$('#estado').attr('value', estado);
		$('#id').attr('value', id);
		$('#lote').attr('value', lote);
		$.ajax({
			url: "VisualizaProcesoEnvasado.action?anula=false&orden=" + orden + "&lote=" + lote + "&estado=" + estado,
			cache: false,
			async: false,
			success: function(html){
				$("#widget_consGPEnva").empty();
				$("#widget_consGPEnva").append(html);
			}
		});
	}else{
		//Estamos en el escritorio
		$('#vuelveEscritorio').val(true);
		flag = false;
		btMenuEnvasados(false);
	}
	if (flag){
		$.ajax({
			type: "POST",
			url: "js/script.js",
			dataType: "script"
		});
		$.ajax({
			type: "POST",
			url: "gpenvasado/visualizarProceso.js",
			dataType: "script"
		});
	}else{
		irAlProceso(orden);
	}
}

function gestionarProceso(orden){
	flag = true;
	if ($("#widget_consGPEnva").length){
		flag = true;
		//Ya estabamos en el menu de salidas
		var estado = $('#estado_' + orden).text();
		$("#estado").val(estado);
		var id = $('#idProducto_' + orden).val();
		var lote = $('#lote_' + orden).val();
		var tipoEan = $('#tipoEan_' + orden).val();
		var $url = "GestionarProcesoEnvasado.action?orden=" + orden;
		$.ajax({
			url: $url,
			cache: false,
			async:false,
			success: function(html){
				$("#widget_consGPEnva").empty();
				$("#widget_consGPEnva").append(html);
			}
		});
	}else{
		//Estamos en el escritorio
		$('#vuelveEscritorio').val(true);
		flag = false;
		btMenuEnvasados(false);
	}
	if (flag){
		$url = "";
		if (tipoEan == 13)
			$url = "gpenvasado/gestionarProcesoEnvasado.js";
		else
			$url = "gpenvasado/gestionarProcesoEnvasadoEan14.js";
			
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
	}else{
		gestionarProceso(orden);
	}
}

function filtraEnvasados(){
	$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
	setTimeout(function(){
		var $url = "";
		$url = "FiltGPEnva.action?idProducto=" + $("#dropdown_productos").val() + '&filtro=' + $("#dropdown_cuantos_mostrar").val();
		$.ajax({
			url: $url,
			cache: false,
			async:false,
			success: function(html){
				$("#demo").empty();
				$("#demo").append(html);
			}
		});
		$.ajax({
			type: "POST",
			url: "gpenvasado/consRegiGPEN.js",
			dataType: "script"
		});
	}, 200);
}

function reporte(orden){
	//$.msg(orden);
	$.ajax({
	 url: "ConsDetaGPENJR.action?orden=" + orden,
	 cache: false,
	 async:false,
	});
}

function reporteEnvasados(){
	parent.get_ventana_emergente('GPEnva','ConsGPEnvaJR.action','no','no','800','640','','');
}

function controlTiempos(orden){
	var $url = "ControlTiempos.action?proceso=" + orden;
	//$.msg($url);
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consGPEnva").empty();
			$("#widget_consGPEnva").append(html);
			/* Configuramos la botonera */
			$(".botonBotonera").hide();
			$("#bot_vuelve").attr("onclick" , "javascript:consGPEnva();");
			$("#bot_vuelve").show();
			$("#widget_menu").show();
		}
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick", "javascript:consGPEnva();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
}

function sacarEnvasado(orden){
	$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
	//alert(1);
	//$.msg("sacar");
	var estado = $('#estado_' + orden).text();
	var id = $('#idProducto_' + orden).val();
	//$.msg(id);
	var lote = $('#lote_' + orden).val();
	var tipoEan = $('#tipoEan_' + orden).val();
	$('#orden').attr('value', orden);
	//SeleccionUbicacionesEnvasado
	var $url = "SeleccionUbicacionesEnvasado.action?orden=" + orden +
		"&estadoProceso=" + estado + "&idProducto=" + id + "&loteAsignado=" + lote + "&tipoEan=" + tipoEan;
	//$.msg($url);
	$.ajax({
	 url: $url,
	 cache: false,
	 async: true,
	 success: function(html){
		$("#widget_consGPEnva").empty();
		$("#widget_consGPEnva").append(html);
		if (tipoEan == 13)
			$url = "ubicacion/seleccionUbicacionesEnvasado.js";
		else
			$url = "ubicacion/seleccionUbicacionesEnvasadoEan14.js";
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

function reporteEnvasadosActivos(){
	parent.get_ventana_emergente('GPEnva','ConsGPEnvaJR.action','no','no','800','640','','');
}

function filtraEnvasadosActivos(){
	var $url = "";
	$url = "FiltraPendientes.action?idProducto=" + $("#dropdown_productos").val();
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#demo").empty();
			$("#demo").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "gpenvasado/listaEnvasar.js",
		dataType: "script"
	});
}

function eliminarEnvasadoPendiente(orden){
	$.confirm("&#Anular el envasado " + orden + "?",
		function(){
			var $url = "";
			$url = "CancelaPendiente.action?proceso=" + orden;
			$.ajax({
				url: $url,
				cache: false,
				async:false,
				success: function(html){
					$("#widget_consGPEnva").empty();
					$("#widget_consGPEnva").append(html);
				}
			});
			$.ajax({
				type: "POST",
				url: "gpenvasado/listaEnvasar.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "js/script.js",
				dataType: "script"
			});
		},
		function(){
			$.msg("Cancelada la anulaci&oacute;n");
		});
}