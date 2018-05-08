$(document).ready(function(){
	var $url = "";
	$url = "ConsultaProducto.action?idGrupo=0";
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consProdu").empty();
			$("#widget_consProdu").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "producto/consultaProducto.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
});

function cargarProducto(idProducto){
	$.ajax({
	 url: "MostrarProducto.action?idProducto=" + idProducto,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consProdu").empty();
		$("#widget_consProdu").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
	setTimeout('$.ajax({type: "POST", url: "producto/editarProducto.js", dataType: "script"});', 100);
}

function eliminarProducto(idProducto){
	$("#idUsuarioEliminar").val(idProducto);
	$('#botonEliminarProveedor').trigger('click');
}

function tipoSeleccionado(){
	idTipo = $("#dropdown_tipos").val();
	$("#idTipo").val(idTipo);
}

function reporteProductos(){
	parent.get_ventana_emergente('ConsINGRE','ConsProductosJR.action','no','no','800','640','','');
}

function consProdu(){
	var $url = "";
	$url = "ConsultaProducto.action?idGrupo=0";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consProdu").empty();
		$("#widget_consProdu").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "producto/consultaProducto.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function nuevoProdu(){
	$.confirm("Esta opcion es para definir un producto nuevo. &#191Desea continuar?",
		function(){
			var $url = "";
			$url = "AltaProducto.action";
			$.ajax({
			 url: $url,
			 cache: false,
			 async:false,
			 success: function(html){
				$("#widget_consProdu").empty();
				$("#widget_consProdu").append(html);
			 }
			});
			$.ajax({
				type: "POST",
				url: "producto/altaProducto.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "js/script.js",
				dataType: "script"
			});
			setTimeout('$("#widget_tabs").show();', 10);
		},
		function(){
			$.msg("Cancelado");
		}
	);
}