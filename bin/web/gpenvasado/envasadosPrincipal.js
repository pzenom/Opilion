$(document).ready(function(){
	//$.blockUI({ message: '<h1>Cargando...</h1>' });
	//cargar();
});

function cargar(){
	//alert('enva_princip');
	var $url = "";
	$url = "ConsGPEnva.action";
	$.ajax({
		url: $url,
		cache: true,
		async: true,
		success: function(html){
			$("#widget_consGPEnva").empty();
			$("#widget_consGPEnva").append(html);
			$.ajax({
				type: "POST",
				url: "gpenvasado/consRegiGPEN.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "js/script.js",
				dataType: "script"
			});
			$('#vuelveEscritorio').val(false);
		}
	});
}

function eliminarProveedor(idUsuario){
	$("#idUsuarioEliminar").val(idUsuario);
	$('#botonEliminarProveedor').trigger('click');
}

function tipoSeleccionado(){
	idTipo = $("#dropdown_tipos").val();
	$("#idTipo").val(idTipo);
}

function consGPEnva(){
	var $url = "";
	$url = "ConsGPEnva.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPEnva");
		$("#widget_consGPEnva").empty();
		$("#widget_consGPEnva").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpenvasado/consRegiGPEN.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function nuevoGPEnva(){
	var $url = "";
	$url = "ConsProdFina.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPEnva");
		$("#widget_consGPEnva").empty();
		$("#widget_consGPEnva").append(html);
		$(".botonUbi").hide();
	 }
	});
	$.ajax({
		type: "POST",
		url: "gpenvasado/consProdFina.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function consEnvasadosActivos(){
	var $url = "";
	$url = "ListaEnvasar.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		if ($("#widget_consUbica").length > 0)
			$("#widget_consUbica").attr("id", "widget_consGPEnva");
		$("#widget_consGPEnva").empty();
		$("#widget_consGPEnva").append(html);
		$(".botonUbi").hide();
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
}