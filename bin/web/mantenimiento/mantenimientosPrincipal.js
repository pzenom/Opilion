$(document).ready(function(){
	var $url = "";
	$url = "ConsAlba.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consMant").empty();
		$("#widget_consMant").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "mantenimiento/consRegiAlba.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
});

function reporteAlbaranes(){
	//ConsPRJR.action
	print();
}

function listaAlbaranes(){
	var $url = "";
	$url = "ConsAlba.action";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consMant").empty();
		$("#widget_consMant").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "mantenimiento/consRegiAlba.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function filtraAlbaranes(){
	var $url = "";
	$url = "ConsAlba.action?idUsuario=" + $("#dropdown_clientes").val() +
		"&codigoAlbaran=" + $("#text_albaran").val();
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consMant").empty();
		$("#widget_consMant").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "mantenimiento/consRegiAlba.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}