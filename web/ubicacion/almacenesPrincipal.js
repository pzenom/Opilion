$(document).ready(function(){
	var $url = "";
	$url = "SeleccionAlmacen.action?mover=" + $("#mover").val() + "&reubicar=" + $("#reubicar").val() + "&sacar=" + $("#sacar").val() + "&meter=" + $("#meter").val();
	//$.msg($url);
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consUbica").empty();
		$("#widget_consUbica").append(html);
	 }
	});
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
	if ($("#meter").val() == "true" && $("#mover").val() == "false" && $("#reubicar").val() == "false" && $("#sacar").val() == "false")
		$.msg("Seleccione el almacen HACIA el que quiere mover",{live:7000});
});

function esconderBotones(){
	//$.msg("escondo botones menu");
	$(".botonUbi").hide();
}