var action = "";
var fondo = false;
var mensaje = false;

$(document).ready(function(){
	$('#tablaAlbaranes').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 1, 'desc' ]],
		"aoColumns": [
			null,
			{ "sType": "uk_date" },
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
	//Configuramos la botonera 
	$(".botonBotonera").hide();
	$("#bot_listar").attr("onclick" , "javascript:listaAlbaranes();");
	$("#bot_listar").show();
	$("#widget_menu").show();
	if (!$("#consulta").is(':visible')){
		$("#bot_consulta").addClass('ocultando');
		$("#bot_consulta").removeClass('mostrando');
	}
	$("#bot_consulta").show();
	$("#bot_vuelve").show();
	//
	celdas = $(".celdasEstadosAlbaranes");
	for (h = 0; h < celdas.length; h++){
		id = celdas[h].id;
		frag = id.split("_");
		idAlbaran = frag[1];
		estado = $("#celdasEstados_" + idAlbaran).html().trim();
		//alert(estado);
		if (estado == 'Preparado'){//Mostramos el boton para entregar
		}else{
			if (estado == 'Entregado'){//Ocultamos el boton para entregar
				//$("#bt_entregarAlbaran_" + idAlbaran).hide();
				$("#img_bt_entregarAlbaran_" + idAlbaran).attr("src", "img/enabled.gif");
				$("#img_bt_entregarAlbaran_" + idAlbaran).attr("alt", "El albaran ya ha sido entregado");
				$("#img_bt_entregarAlbaran_" + idAlbaran).attr("title", "El albaran ya ha sido entregado");
				
			}
		}
		//Miramos aqui si el albaran ya ha sido facturado, para permitir o no la facturacion
		facturado = $("#facturado_" + idAlbaran).val();
		//alert(facturado);
		if (facturado == 'N'){//Mostramos el boton para pasar a factura
		}else{
			if (facturado == 'S'){//Ocultamos el boton para pasar a factura
				//$("#facturar_" + idAlbaran).hide();
				$("#img_facturar_" + idAlbaran).attr("src", "img/enabled.gif");
				$("#img_facturar_" + idAlbaran).attr("alt", "Ya se ha generado la factura para este albaran");
				$("#img_facturar_" + idAlbaran).attr("title", "Ya se ha generado la factura para este albaran");
				$("#facturar_" + idAlbaran).attr("href", "javascript:mostrarFacturasAlbaran('" + idAlbaran + "')");
			}
		}
	}
	$.unblockUI();
});

function cerrar(){
	$('#fondo').remove();
	document.getElementsByTagName('body')[0].removeChild(mensaje);
	fondo = false;
	mensaje = false;
}

function mostrarFacturasAlbaran(codigoAlbaran){
	//$.msg('Ya se ha generado la factura para este albaran');
	$url = "FacturasAlbaran.action?codigoAlbaran=" + codigoAlbaran;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#ajaxFacturas").empty();
			$("#ajaxFacturas").append(html);
		}
	});
	//Abrir ventana donde se pregunte: Producto terminado o granel
	fondo = document.createElement('div');
	mensaje = document.createElement('div');
	fondo.setAttribute('id','fondo');
	mensaje.setAttribute('id','msg');
	$('body').append(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
	$("#fondo").height($(document).height());
	contenidoMensaje =
		'<div class="superior"><span class="cerrar" title="Cerrar" onclick="cerrar();">X</span></div>' +
		'<p style="font-size: 15px; text-align: center;">Facturas asociadas al albaran</p>' +
		'<div style="font-size: 15px; text-align: center;">' +
		'<table><thead><tr><th>Factura</th><th>Fecha</th><th>Fecha vencimiento</th><th>Importe total</th></tr></thead><tbody>';
	codigos = $('.codigoFactura').get();
	//alert(11);
	//alert(contenidoMensaje);
	for (i = 0; i < codigos.length; i++){
		//alert(i);
		id = codigos[i].id;
		codigoFactura = $("#" + id).val();
		fecha = $('#fecha_' + codigoFactura).val();
		fechaVencimiento = $('#fechaVencimiento_' + codigoFactura).val();
		importeTotal = $('#importeTotal_' + codigoFactura).val();
		contenidoMensaje +=
			'<tr>' +
				'<td>' + codigoFactura + '</td>' +
				'<td>' + fecha + '</td>' +
				'<td>' + fechaVencimiento + '</td>' +
				'<td>' + importeTotal + '</td>' +
			'</tr>';
	}
	//alert("ciao");
	//alert(contenidoMensaje);
	contenidoMensaje += '</tbody></table></div>';
	//alert(contenidoMensaje);
	mensaje.innerHTML = contenidoMensaje;
}

function reporte(codigoAlbaran){
	fondo = document.createElement('div');
	fondo.setAttribute('id','fondo');
	$('body').append(fondo);
	action = "ConsAlbaOrdenJR.action?codigoAlbaran=" + codigoAlbaran;
	$("#dialogo").dialog({
		close: function(event, ui) {
			if ( event.originalEvent && $(event.originalEvent.target).closest(".ui-dialog-titlebar-close").length ) {
				$.msg("Cargando reporte");
				$('#fondo').remove();
				fondo = false;
			}
		}}).find("button").click(function(){
			//Cerramos el dialogo
			$('.ui-dialog-titlebar-close').click();
			$('#fondo').remove();
			fondo = false;
		});
	//preparar('nuevo');
}

function preparar(nuevoViejo){
	if (nuevoViejo == 'nuevo'){
		action += "&nuevo=true";
	}else
		action += "&nuevo=false";
	/*action += "&encabezado=" + $('#checkEncabezado').is(':checked');
	action += "&precios=" + $('#checkPrecios').is(':checked');*/
	action += "&encabezado=false";
	action += "&precios=" + $('#checkPrecios').is(':checked');
	action += "&lineaCarrefour=" + $('#checkLineaCarrefour').is(':checked');
	//alert(action);
	parent.get_ventana_emergente('ALBA', action,'no','no','800','640','','');
}

function albaranAFactura(codigoAlbaran, tipoAlbaran, estado){
	//alert(estado);
	if (estado == 'P'){//Avisamos antes de generar factura
		//$.confirm("El albaran no ha sido entregado. &#191Desea generar la factura de todas formas?.",
			//function(){
				$url = "EditDetaFact.action?codigoAlbaran=" + codigoAlbaran +
					"&tipoAlbaran=" + tipoAlbaran;
				$.ajax({
				 url: $url,
				 cache: false,
				 async:false,
				 success: function(html){
					$("#widget_consPedido").empty();
					$("#widget_consPedido").append(html);
				 }
				});
				$.ajax({
					type: "POST",
					url: "registrosalida/detaRegiFact.js",
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
				setTimeout('modificarValorIva();', 250);
			/*},
			function(){
				$.msg("Factura cancelada");
			}
		);*/
	}else{
		$url = "EditDetaFact.action?codigoAlbaran=" + codigoAlbaran +
			"&tipoAlbaran=" + tipoAlbaran;
		$.ajax({
		 url: $url,
		 cache: false,
		 async:false,
		 success: function(html){
			$("#widget_consPedido").empty();
			$("#widget_consPedido").append(html);
		 }
		});
		$.ajax({
			type: "POST",
			url: "registrosalida/detaRegiFact.js",
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
	}
}

function muestraInfoEstadoAlbaran(estado, idAlbaran){
	$url = "MuestraInformacionEstado.action?idPedido=" + idAlbaran + "&estado=" + estado;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#ajaxEstados").empty();
			$("#ajaxEstados").append(html);
		}
	});
	simple_tooltip("celdasEstados_" + idAlbaran, 'tooltip');
}

function simple_tooltip(target_items, name){
	$('#' + target_items).each(function(i){
		if ($("#" + name + i).length > 0)
			$("#" + name + i).remove();
		html = "<div style='font-size:14px' class='" + name + "' id='" + name + i + "'>" +
			"<span>Estado del albaran " + $("#idPedido").val() + "</span>";
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

function entregarAlbaran(albaran){
	$.confirm("&#191Marcar el albaran como entregado?.",
		function(){
			$url = "EntregarAlbaran.action?codigoAlbaran=" + albaran;
			$.ajax({
				url: $url,
				cache: false,
				async:false,
				success: function(html){
					/*$("#ajaxEstados").empty();
					$("#ajaxEstados").append(html);*/
					listaAlbaranes();
				}
			});
		},
		function(){
			$.msg("Cancelado");
		}
	);
}

function editarAlbaran(albaran){
	//alert(albaran);
	$url = "EditDetaAlba.action?codigoAlbaran=" + albaran;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consPedido").empty();
			$("#widget_consPedido").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "registrosalida/detaRegiAlba.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}