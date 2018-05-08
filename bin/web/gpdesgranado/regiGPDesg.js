var punto = false;

$(document).ready(function() {
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_insertar").attr("onclick", "javascript:insertar();");
	$("#bot_insertar").show();
	$("#bot_vuelve").show();
	$("#bot_vuelve").attr("onclick" , "javascript:nuevoGPDesg();");
});

function insertar(){
	//$.msg($('input[name=listCateDesg]').is(':checked'));
	if ($("#cantidadDesgranadoar").val() == "" || $("#cantidadDesgranadoar").val() == 0)
		$.msg("Introduzca la cantidad que se va a desgranar");
	else{
		sacar = $(".sacar").get();
		cantidadSacar = 0;
		for (i = 0; i < sacar.length; i++){
			cantidadSacar += parseFloat(sacar[i].value);
		}
		//$.msg(cantidadSacar);
		if (cantidadSacar != $("#cantidadDesgranadoar").val()){
			$.msg("La cantidad que se va a desgranar tiene que ser igual que la cantidad que se va a sacar");
		}else{
			sacar = $(".cantidadDesgranadoada").get();
			cantidadSacar = 0;
			for (i = 0; i < sacar.length; i++){
				cantidadSacar += parseFloat(sacar[i].value);
			}
			if (cantidadSacar > $("#cantidadDesgranadoar").val()){
				$.msg("La cantidad total del resultado no puede superar la cantidad que se est&aacute; desgranadoando");
			}else
				if (cantidadSacar == 0){
					$.msg("Introduzca las cantidades resultantes");
				}else
					if ($('input[name=listCateDesg]').is(':checked') == false)
						$.msg("Seleccione las categorias de salida del desgranado");
					else{
						//document.formulario.submit();
						$url = "InseREDesg.action";
						//Crear la ruta completa
						//saldoregistro=5&sacar_217_E11112-3=5&listCateDesg=2%2F&cantidad=1&listCateDesg=3%2F&cantidad=2&listCateDesg=4%2F&cantidad=2
						$url += "?saldoregistro=" + $("#cantidadDesgranadoar").val();
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
								$url += "&listCateDesg=" + check.id + "%2F&cantidad=" + $("#cantidad_" + check.id).val();
							}
						}
						/*$url += "&destrio=" + $("#cantidadDestrio").val();*/
						$.confirm("&#191Insertar el proceso de desgranado?",
						function(){
							//$.msg($url);
							$.ajax({
								url: $url,
								cache: false,
								async:false,
								success: function(html){
									$("#widget_consGPDesg").empty();
									$("#widget_consGPDesg").append(html);
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