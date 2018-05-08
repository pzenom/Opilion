$(document).ready(function(){
	//$.msg("primera vez q cargamos proveedores");
	/*var $url = "";
	$url = "ConsultaProveedores.action?idTipo=0";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consProv").empty();
		$("#widget_consProv").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "proveedor/consultaProveedores.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});*/
	consProve();
	/* Configuramos la botonera */
	/*$(".botonBotonera").hide();
	$("#bot_nuevo").attr("onclick", "javascript:nuevaEntidad();");
	$("#bot_listar").attr("onclick" , "javascript:consProve();");
	$("#bot_reporte").attr("onclick" , "javascript:reporteProveedores();");
	$("#bot_nuevo").show();
	$("#bot_listar").show();
	$("#bot_reporte").show();
	//$(".botonUbi").hide();
	if (!$("#consulta").is(':visible')){
		$("#bot_consulta").addClass('ocultando');
		$("#bot_consulta").removeClass('mostrando');
	}
	$("#bot_vuelve").show();
	$("#widget_menu").show();*/
	//setTimeout('$("#tablaProveedores").show();', 250);
});

function eliminarProveedor(idUsuario){
	$("#idUsuarioEliminar").val(idUsuario);
	$('#botonEliminarProveedor').trigger('click');
}

function tipoSeleccionado(){
	idTipo = $("#dropdown_tipos").val();
	$("#idTipo").val(idTipo);
}

function reporteProveedores(){
	parent.get_ventana_emergente('ConsCL','ConsPRJR.action','no','no','800','640','','');
}

function consProve(){
	//$("#mydiv").show();
	$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
	$url = "ConsultaProveedores.action?idTipo=0";
	setTimeout(""+ 
		"$.ajax({"+
			"url: $url,"+
			"cache: false,"+
			"async: true,"+
			"success: function(html){"+
				'$("#widget_consProv").empty();' +
				'$("#widget_consProv").append(html);'+
				'$.ajax({'+
					'type: "POST",'+
					'url: "proveedor/consultaProveedores.js",'+
					'dataType: "script"'+
				'});'+
				'$.ajax({'+
				'	type: "POST",'+
				'	url: "js/script.js",'+
				'	dataType: "script"'+
				'});'+
				//$("#mydiv").hide();
				'$.unblockUI(); '+
			"},"+
			"error: function(){"+
			'	$.msg("Error listando los proveedores");'+
				//$("#mydiv").hide();
				'$.unblockUI(); '+
			"}"+
		"});",250);
}