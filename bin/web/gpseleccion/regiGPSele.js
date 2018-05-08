var punto = false;

$(document).ready(function() {
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_insertar").attr("onclick", "javascript:insertar();");
	$("#bot_insertar").show();
	$("#bot_vuelve").show();
	$("#bot_vuelve").attr("onclick" , "javascript:volverAlEscritorio();");
	setTimeout('$("#tablaProcesos").show();', 250);
});

function insertar(){
	//$.msg($('input[name=listCateSele]').is(':checked'));
	if ($("#cantidadSeleccionar").val() == "" || $("#cantidadSeleccionar").val() == 0)
		$.msg("Introduzca la cantidad que se va a seleccionar");
	else{
		sacar = $(".sacar").get();
		cantidadSacar = 0;
		for (i = 0; i < sacar.length; i++){
			cantidadSacar += parseFloat(sacar[i].value);
		}
		//$.msg(cantidadSacar);
		if (cantidadSacar != $("#cantidadSeleccionar").val()){
			$.msg("La cantidad que se va a seleccionar tiene que ser igual que la cantidad que se va a sacar");
		}else{
			sacar = $(".cantidadSeleccionada").get();
			cantidadSacar = 0;
			for (i = 0; i < sacar.length; i++){
				cantidadSacar += parseFloat(sacar[i].value);
			}
			if (cantidadSacar > $("#cantidadSeleccionar").val()){
				$.msg("La cantidad total del resultado no puede superar la cantidad que se est&aacute; seleccionando");
			}else
				if (cantidadSacar == 0){
					$.msg("Introduzca las cantidades resultantes");
				}else
					if ($('input[name=listCateSele]').is(':checked') == false)
						$.msg("Seleccione las categorias de salida de la selección");
					else{
						//document.formulario.submit();
						$url = "InseRESele.action";
						//Crear la ruta completa
						//saldoregistro=5&sacar_217_E11112-3=5&listCateSele=2%2F&cantidad=1&listCateSele=3%2F&cantidad=2&listCateSele=4%2F&cantidad=2
						$url += "?saldoregistro=" + $("#cantidadSeleccionar").val();
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
								$url += "&listCateSele=" + check.id + "%2F&cantidad=" + $("#cantidad_" + check.id).val();
							}
						}
						$.confirm("&#191Insertar el proceso de selecci&oacute;n?",
						function(){
							//$.msg($url);
							$.ajax({
								url: $url,
								cache: false,
								async:false,
								success: function(html){
									$("#widget_consGPSele").empty();
									$("#widget_consGPSele").append(html);
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