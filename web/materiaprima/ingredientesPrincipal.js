$(document).ready(function(){
	var $url = "";
	$url = "ConsultaIngrediente.action?idGrupo=0";
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consIngre").empty();
			$("#widget_consIngre").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "materiaprima/consultaIngrediente.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
});

function cargarIngrediente(idIngrediente){
	$.ajax({
	 url: "CargarMP.action?IdMateriaPrima=" + idIngrediente,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consIngre").empty();
		$("#widget_consIngre").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
	setTimeout('$.ajax({type: "POST", url: "materiaprima/actualizaMateriaPrima.js", dataType: "script"});', 100);
}

function eliminarIngrediente(idIngrediente){
	$("#idUsuarioEliminar").val(idIngrediente);
	$('#botonEliminarProveedor').trigger('click');
}

function tipoSeleccionado(){
	idTipo = $("#dropdown_tipos").val();
	$("#idTipo").val(idTipo);
}

function reporteIngredientes(){
	//ConsMateriasJR..action
	parent.get_ventana_emergente('ConsINGRE','ConsEnvsJR.action','no','no','800','640','','');
}

function consIngre(){
	var $url = "";
	$url = "ConsultaIngrediente.action?idGrupo=0";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consIngre").empty();
		$("#widget_consIngre").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "materiaprima/consultaIngrediente.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function nuevoIngre(){
	var $url = "";
	$url = "RegistroMateriaPrima.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consIngre").empty();
		$("#widget_consIngre").append(html);
		//$.msg("exito");
	 }
	});
	$.ajax({
		type: "POST",
		url: "materiaprima/registroMateriaPrima.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
	//$.msg("ciao");
}

function consuIngres(){
	var $url = "";
	$url = "ConsultaIngrediente.action?idGrupo=0";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consIngre").empty();
		$("#widget_consIngre").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "materiaprima/consultaIngrediente.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}