$(document).ready(function(){
	//$.msg("primera vez q cargamos proveedores");
	//idTipo = $("#dropdown_tipos").val($("#idTipo").val());
	var $url = "";
	$url = "SeleccionAlmacen.action?mover=true&reubicar=false";
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consUbica").empty();
			$("#widget_consUbica").append(html);
			$.ajax({
				type: "POST",
				url: "ubicacion/seleccionAlmacen.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "js/script.js",
				dataType: "script"
			});
		}
	});
});

function cargarOE(idUsuario){
	$.ajax({
		url: "CargarProveedor.action?idUsuario=" + idUsuario,
		cache: false,
		async:false,
		success: function(html){
			$("#divNecesarioWidget").empty();
			$("#divNecesarioWidget").append(html);
			$.ajax({
				type: "POST",
				url: "js/script.js",
				dataType: "script"
			});
			$.ajax({
				type: "POST",
				url: "proveedor/actualizarProveedor.js",
				dataType: "script"
			});
		}
	});
	//$.msg("proveedor terminado de cargar");
}

function eliminarOE(idUsuario){
	$("#idUsuarioEliminar").val(idUsuario);
	$('#botonEliminarProveedor').trigger('click');
}

function tipoSeleccionado(){
	idTipo = $("#dropdown_tipos").val();
	$("#idTipo").val(idTipo);
}

function consProve(){
	var $url = "";
	$url = "ConsultaProveedores.action?idTipo=0";
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consProv").empty();
			$("#widget_consProv").append(html);
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
		}
	});
}

function nuevoProve(){
	var $url = "";
	$url = "RegistroProveedor.action";
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consProv").empty();
			$("#widget_consProv").append(html);
			$.ajax({
				type: "POST",
				url: "proveedor/registroProveedor.js",
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