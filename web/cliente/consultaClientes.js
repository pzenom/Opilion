$(document).ready(function(){
	ajustarRol();
	$('#tablaClientes').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 1, 'asc' ]],
		"aoColumns": [
			null,
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	ResaltarFila();
	// Configuramos la botonera
	$(".botonBotonera").hide();
	if ($("#bot_nuevo").hasClass('puedeVer')){
		$("#bot_nuevo").attr("onclick", "javascript:nuevaEntidad();");
		$("#bot_nuevo").show();
	}
	$("#bot_listar").attr("onclick" , "javascript:consCli();");
	$("#bot_reporte").attr("onclick" , "javascript:reporteClientes();");
	$("#bot_vuelve").show();
	$("#bot_vuelve").attr("onclick", "javascript:volverAlEscritorio();");
	$("#bot_listar").show();
	$("#bot_reporte").show();
	$("#widget_menu").show();
	if (!$("#consulta").is(':visible')){
		$("#bot_consulta").addClass('ocultando');
		$("#bot_consulta").removeClass('mostrando');
	}
	$("#bot_consulta").show();
	clientes = $(".clientes").get();
	for (i = 0; i < clientes.length; i++){
		id = clientes[i].id;
		deudas = $("#deudas_" + id).val();
		if (deudas == "true" || deudas == true){
			$("#enlaceDeudas_" + id).show();
		}
	}
	setTimeout('$("#tablaClientes").show();', 250);
});

function limpiarResaltado(){
	resaltados = $(".resaltar");
	for (i = 0; i < resaltados.length; i++){
		clase = resaltados[i].class;
	}
}

function eliminarCliente(idUsuario){
	if (confirm("Eliminar el cliente?")){
		var $url = "EliminarEntidad.action?tipoUsuario=C&idUsuarioEliminar=" + idUsuario;
		$.ajax({
			type: 'POST',
			url: $url,
			cache: false,
			async:false,
			success: function(html){
				$.msg("Cliente eliminado");
			}
		});
		//2. Consultamos los clientes
		$url = "ConsultaClientes.action";
		$.ajax({
		 url: $url,
		 cache: false,
		 async:false,
		 success: function(html){
			$("#widget_consCli").empty();
			$("#widget_consCli").append(html);
		 }
		});
		$.ajax({
			type: "POST",
			url: "cliente/consultaClientes.js",
			dataType: "script"
		});
	}
}

function filtraClientes(){
	$(".blocker").show();
	var $url = "";
	if ($("#dropdown_sectores").val() == undefined)
		$url = "FiltroClientes.action?idSector=0";
	else
		$url = "FiltroClientes.action?idSector=" + $("#dropdown_sectores").val();
	$.ajax({
		url: $url,
		cache: false,
		async: false,
		success: function(html){
			$("#demo").empty();
			$("#demo").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "cliente/consultaClientes.js",
		dataType: "script"
	});
	$("#irAlEscritorio").removeAttr("class");
	$(".blocker").hide();
}

function muestraConsultaCliente(){
	//$.msg($("#consulta").is (':visible'));
	if ($("#consulta").is (':visible'))
		$("#consulta").hide();
	else
		$("#consulta").show();
}

function gestionarSalidas(idEntidad){
	var $url = "";
	$url = "GestionSalidasClientes.action?idUsuario=" + idEntidad;
	$.ajax({
		url: $url,
		cache: false,
		async: false,
		success: function(html){
			$("#widget_consCli").empty();
			$("#widget_consCli").append(html);
			$.ajax({
				type: "POST",
				url: "js/script.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "cliente/gestionSalidasClientes.js",
				dataType: "script"
			});
			$("#irAlEscritorio").removeAttr("class");
		}
	});
}