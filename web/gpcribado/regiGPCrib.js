var punto = false;


$(document).ready(function() {
	$('#tablaRegistros').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 1, 'asc' ]],
		"aoColumns": [
			null,
			{ "sType": "uk_date" },
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").show();
	$("#bot_vuelve").attr("onclick" , "javascript:nuevoGPCrib();");
	$("#bot_insertar").show();
	$("#bot_insertar").attr("onclick" , "javascript:insertar();");
	setTimeout('$("#tablaRegistros").show();', 250);
});

function insertar(){
	//$.msg($('input[name=listCateCrib]').is(':checked'));
	if ($("#cantidadCribar").val() == "" || $("#cantidadCribar").val() == 0)
		$.msg("Introduzca la cantidad que se va a cribar");
	else{
		sacar = $(".sacar").get();
		cantidadSacar = 0;
		for (i = 0; i < sacar.length; i++){
			cantidadSacar += parseFloat(sacar[i].value);
		}
		//$.msg(cantidadSacar);
		if (cantidadSacar != $("#cantidadCribar").val()){
			$.msg("La cantidad que se va a cribar tiene que ser igual que la cantidad que se va a sacar");
		}else{
			/*sacar = $(".sacar").get();
			cantidadSacar = 0;
			for (i = 0; i < sacar.length; i++){
				cantidadSacar += parseFloat(sacar[i].value);
			}*/
			if (cantidadSacar > $("#cantidadCribar").val()){
				$.msg("La cantidad total del resultado no puede superar la cantidad que se est&aacute; cribando");
			}else
				if (cantidadSacar == 0){
					$.msg("Introduzca las cantidades resultantes");
				}else
					if ($('input[name=listCateCrib]').is(':checked') == false)
						$.msg("Seleccione las categorias de salida del cribado");
					else{
						//document.formulario.submit();
						$url = "InseRECrib.action";
						//Crear la ruta completa
						//saldoregistro=5&sacar_217_E11112-3=5&listCateCrib=2%2F&cantidad=1&listCateCrib=3%2F&cantidad=2&listCateCrib=4%2F&cantidad=2
						$url += "?saldoregistro=" + $("#cantidadCribar").val();
						sacar = $(".sacar");
						cuantos = sacar.length;
						for (i = 0; i < cuantos; i++){
							saco = sacar[i];
							$url += "&" + saco.name + "=" + $("#" + saco.id).val();
						}
						checks = $(".check");
						cuantos = checks.length;
						//$.msg(cuantos);
						for (i = 0; i < cuantos; i++){
							check = checks[i];
							//$.msg(check.id);
							if ($('#' + check.id).is(':checked')){
								$url += "&listCateCrib=" + check.id + "%2F&cantidad=" + $("#cantidad_" + check.id).val();
							}
						}
						$.confirm("&#191Insertar el proceso de cribado?",
						function(){
							//$.msg($url);
							$.ajax({
								url: $url,
								cache: false,
								async:false,
								success: function(html){
									$("#widget_consGPCrib").empty();
									$("#widget_consGPCrib").append(html);
								}
							});
							$.ajax({
								type: "GET",
								url: "ubicacion/ubicarProceso.js",
								dataType: "script"
							});
						},
						function(){
							$.msg("Cancelado");
						});
					}
		}
	}
}