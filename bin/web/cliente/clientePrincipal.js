$(document).ready(function(){
	//alert(-1);
	consCli();
});

function eliminarCliente(idUsuario){
	$("#idUsuarioEliminar").val(idUsuario);
	$('#botonEliminarCliente').trigger('click');
}

function tipoSeleccionado(){
	idSector = $("#dropdown_tipos").val();
	$("#idSector").val(idSector);
}

function reporteClientes(){
	parent.get_ventana_emergente('ConsCL','ConsCLJR.action','no','no','800','640','','');
}

function consCli(){
	//alert(0);
	//$("#mydiv").show();
	$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
	setTimeout(function(){
		muestraConsultaClientes();
	}, 200);
}

function muestraConsultaClientes(){
	$url = "ConsultaClientes.action?idSector=0";
	//alert(1);
	$.ajax({
		url: $url,
		cache: false,
		async: true,
		success: function(html){
			$("#widget_consCli").empty();
			$("#widget_consCli").append(html);
			$.ajax({
				type: "POST",
				url: "cliente/consultaClientes.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "js/script.js",
				dataType: "script"
			});
			//$("#mydiv").hide();
			$.unblockUI();
		},
		error: function(){
			$.msg("Error listando los clientes");
			//$("#mydiv").hide();
			$.unblockUI();
		}
	});
	//alert(2);
}