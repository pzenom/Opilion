$(document).ready(function(){
	var $url = "";
	$url = "PrepararBackup.action";
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consHerra").empty();
			$("#widget_consHerra").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "herramientas/backup.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
});

function configurarDB(){
	//ConfigurarDB
	$.ajax({
	 url: "ConfigurarDB.action",
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consHerra").empty();
		$("#widget_consHerra").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function volver(){
	var $url = "";
	$url = "herramientas/herramientasPrincipal.jsp";
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consHerra").empty();
			$("#widget_consHerra").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "herramientas/herramientasPrincipal.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function valoresPredeterminados(){
	//ConfigurarDBDefecto
	var $url = "";
	$url = "ConfigurarDBDefecto.action";
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consHerra").empty();
			$("#widget_consHerra").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function procederCambios(){
	//SalvarCambiosDB
	if ($("#pass").val() == ""){
		$.msg("Debe introducir la nueva contrase&ntilde;a para la base de datos");
	}else{
		var $url = "";
		$url = "SalvarCambiosDB.action?host=" + $("#host").val() + "&puerto=" + $("#puerto").val() +
			"&db=" + $("#db").val() + "&filePath=" + $("#filePath").val() + "&rutaProceso=" + $("#rutaProceso").val() + "&rutaBackups=" + $("#rutaBackups").val() +
			"&user=" + $("#user").val() + "&pass=" + $("#pass").val();
		$.ajax({
			url: $url,
			cache: false,
			async:false,
			success: function(html){
				$("#widget_consHerra").empty();
				$("#widget_consHerra").append(html);
			}
		});
		$.ajax({
			type: "POST",
			url: "js/script.js",
			dataType: "script"
		});
	}
}