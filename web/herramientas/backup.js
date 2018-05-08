$(document).ready(function(){
	copias = $(".copiaSeguridad");
	cuantasCopias = copias.length;
	//$.msg(cuantasCopias);
	if (cuantasCopias <= 0){
		$("#historicoCopias").hide();
	}
});

function hacerBackup(){
	var $url = "";
	$url = "Backup.action";
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
		url: "herramientas/muestraBackup.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}