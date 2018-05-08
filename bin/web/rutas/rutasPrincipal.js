$(document).ready(function(){
	consRutas();
});

function consRutas(){
	//$("#mydiv").show();
$.blockUI({ message: '<h1>Cargando...</h1>' });
	$url = "AdministracionRutas.action";
	setTimeout(""+ 
		"$.ajax({"+
			"url: $url,"+
			"cache: false,"+
			"async:false,"+
			"success: function(html){"+
				'$("#widget_consRutas").empty();' +
				'$("#widget_consRutas").append(html);'+
				'$.ajax({'+
					'type: "POST",'+
					'url: "rutas/rutas.js",'+
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
			'	$.msg("Error listando las rutas");'+
				//$("#mydiv").hide();
				'$.unblockUI(); '+
			"}"+
		"});",250);	
}