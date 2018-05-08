$(document).ready(function(){

	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick" , "javascript:consGPEnva();");
	$("#bot_vuelve").show();
	$("#bot_actualizar").attr("onclick" , "javascript:guardarTiempos();");
	$("#bot_actualizar").show();
	$("#widget_menu").show();

});

function guardarTiempos(){
	fecha = $('#text_fechaCaducidad').val();
	if (fecha.length != 10){
		alert('Fecha incorrecta. Introduzca una fecha con el formato: aaaa-mm-dd');
		return;
	}
	//alert(fecha);
	$url = "ActualizaTiempos.action?proceso=" + $('#text_proceso').val() + "&fechaCaducidad=" + fecha;
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			consGPEnva();
		}
	});
}