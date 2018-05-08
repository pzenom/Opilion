$(document).ready(function(){
	var $url = "";
	$url = "ConsAlba.action";
	$.ajax({
		 url: $url,
		 cache: false,
		 async: true,
		 success: function(html){
			$("#widget_consPedido").empty();
			$("#widget_consPedido").append(html);
			$.ajax({
				type: "POST",
				url: "registrosalida/consRegiAlba.js",
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
	
});

function listaAlbaranes(){
	var $url = "";
	$url = "ConsAlba.action";
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			$("#widget_consPedido").empty();
			$("#widget_consPedido").append(html);
		}
	});
	$.ajax({
		type: "POST",
		url: "registrosalida/consRegiAlba.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function filtraAlbaranes(){
	$.blockUI({ message: '<h1><img src="img/loading-bars.gif" /></h1>' });
	setTimeout(function(){
		var $url = "";
		$url = "FiltroAlbaranes.action?idUsuario=" + $("#dropdown_clientes").val() + '&filtro=' + $("#dropdown_cuantos_mostrar").val();
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
			url: "registrosalida/consRegiAlba.js",
			dataType: "script"
		});
	}, 200);
}