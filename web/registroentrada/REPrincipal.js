$(document).ready(function(){
	consRE();
});

function consRE(){
	var $url = "";
	$url = "ConsRE.action?filtro=1";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consRE").empty();
		$("#widget_consRE").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/consRE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/comunOERE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function filtraRE(){
	$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
	setTimeout(function(){
		var $url = "";
		$url = "ConsRE.action?filtro=" + $("#dropdown_cuantos_mostrar").val();
		//$.msg($url);
		$.ajax({
		 url: $url,
		 cache: false,
		 async:false,
		 success: function(html){
			$("#widget_consRE").empty();
			$("#widget_consRE").append(html);
		 }
		});
		$.ajax({
			type: "POST",
			url: "registroentrada/consRE.js",
			dataType: "script"
		});
		$.ajax({
			type: "POST",
			url: "js/script.js",
			dataType: "script"
		});
	}, 400);
}

function reporteRE(){
	parent.get_ventana_emergente('ConsRE','ConsREJR.action','no','no','800','640','','');
}