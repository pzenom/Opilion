$(document).ready(function(){
	ajustarRol();
	$('#tablaProveedores').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 1, 'asc' ]],
		"aoColumns": [
			null,
			null,
			null,
			null,
			{"bSortable": false}
		],
		"sPaginationType": "full_numbers"
	});
	ResaltarFila();
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	if ($("#bot_nuevo").hasClass('puedeVer')){
		$("#bot_nuevo").attr("onclick", "javascript:nuevaEntidad();");
		$("#bot_nuevo").show();
	}	
	$("#bot_listar").attr("onclick" , "javascript:consProve();");
	$("#bot_reporte").attr("onclick" , "javascript:reporteProveedores();");
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
	setTimeout('$("#tablaProveedores").show();', 250);
});

function eliminarProveedor(idUsuario){
	$("#idUsuarioEliminar").val(idUsuario);
	$('#botonEliminarProveedor').trigger('click');
}

function consultaProveedores(){
	var $url = "";
	if ($("#idTipo").val() == undefined)
		$url = "ConsultaProveedores.action?idTipo=0";
	else
		$url = "ConsultaProveedores.action?idTipo=" + $("#idTipo").val();
	//$.msg($url);
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
	});
	$("#irAlEscritorio").removeAttr("class");
}

function filtraProveedores(){
	var $url = "";
	if ($("#dropdown_tipos").val() == undefined)
		$url = "FiltroProveedores.action?idTipo=0";
	else
		$url = "FiltroProveedores.action?idTipo=" + $("#dropdown_tipos").val();
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
		url: "proveedor/consultaProveedores.js",
		dataType: "script"
	});
	$("#irAlEscritorio").removeAttr("class");
}

function muestraConsultaProveedores(){
	//$.msg($("#consulta").is (':visible'));
	if ($("#consulta").is (':visible'))
		$("#consulta").hide();
	else
		$("#consulta").show();
}