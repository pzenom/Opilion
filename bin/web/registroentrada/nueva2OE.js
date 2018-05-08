$(document).ready(function() {
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick", "javascript:consOE();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	if ($(".fila").length > 0)
		$("#btGuardaOrden").show();
	$("#btAniadirRE").show();
});