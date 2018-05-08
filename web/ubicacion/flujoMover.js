$(document).ready(function(){

	if ($("#cantidad").val() == "0.0"){
		$("#cantidad").val("");
	}

	if ($("#origen").val() != ""){
		$("#origen").attr("readonly", "");
		$("#producto").removeAttr("disabled");
		$("#producto").focus();
	}else{
		$("#origen").focus();
	}
	
	if ($("#producto").val() != ""){
		$("#producto").attr("readonly", "");
		$("#cantidad").removeAttr("disabled");
		$("#cantidad").focus();
	}else{
		$("#producto").focus();
	}
		
	if ($("#cantidad").val() != ""){
		$("#cantidad").attr("readonly", "");
		$("#destino").removeAttr("disabled");
		$("#destino").focus();
	}else{
		$("#cantidad").focus();
	}
	
	if ($("#destino").val() != ""){
		$("#destino").attr("readonly", "");
		$("#codigoEscape").removeAttr("disabled");
		$("#codigoEscape").focus();
	}else{
		$("#destino").focus();
	}
	
	if ($("#origen").val() != "")
		$("#producto").focus();
	if ($("#producto").val() != "")
		$("#cantidad").focus();
	if ($("#cantidad").val() != "")
		$("#destino").focus();
	if ($("#destino").val() != "")
		$("#codigoEscape").focus();
		
	if ($("#msg").val() != ""){
		//$.msg($("#msg").val());
		$(".inputs").attr("disabled", "disabled");
		$(".inputs").val("");
		$("#origen").removeAttr("readonly");
		$("#origen").val("");
		$("#origen").focus();
	}
});

function enviar(){
	if ($("#codigoEscape").val() != "")
		if ($("#codigoEscape").val() == "X0000005")
			document.formuFlujoMovimiento.submit();
		else{
			//$.msg("Código de escape incorrecto");
			$("#codigoEscape").val("");
			$("#codigoEscape").focus();
		}
	else
		document.formuFlujoMovimiento.submit();
}

function keyup(id){
	if (id == 4){
		//Si se pulsa borrar, borramos
		frag = $("#cantidad").val().split("X0000003");
		if (frag.length > 1){
			$("#cantidad").val($("#cantidad").val().replace("X0000003", ""));
			largo = $("#cantidad").val().length;
			cadena = "";
			for (i = 0; i < parseFloat(largo - 1); i++){
				cadena += $("#cantidad").val().charAt(i);
			}
			$("#cantidad").val(cadena);
		}else{
			//Si no, si se pulsa intro, saltamos
			frag = $("#cantidad").val().split("X0000002");
			if (frag.length > 1){
				$("#cantidad").val($("#cantidad").val().replace("X0000002", ""));
				enviar();
			}else{
				//Si no, si se pulsa cancelar, volvemos a la página de lectura del codigo que comienza el flujo
				frag = $("#cantidad").val().split("X0000004");
				if (frag.length > 1){
					$("#cantidad").val($("#cantidad").val().replace("X0000004", ""));
					//window.history.back();
					$("#formuFlujoMovimiento").attr("action", "FlujoCodigos.action");
					document.formuFlujoMovimiento.submit();
				}else{
					//Si no, si se pulsa un número, lo escribimos
					$("#cantidad").val($("#cantidad").val().replace("0000000", ""));
				}
			}
		}
	}else{
		//Vemos si se cancela desde otros inputs
		switch (id){
			case 1:
				frag = $("#descripcionAccion").val().split("X0000004");
				if (frag.length > 1){
					$("#formuFlujoMovimiento").attr("action", "FlujoCodigos.action");
					document.formuFlujoMovimiento.submit();
				}
				break;
			case 2:
				frag = $("#origen").val().split("X0000004");
				if (frag.length > 1){
					$("#formuFlujoMovimiento").attr("action", "FlujoCodigos.action");
					document.formuFlujoMovimiento.submit();
				}
				break;
			case 3:
				frag = $("#producto").val().split("X0000004");
				if (frag.length > 1){
					$("#formuFlujoMovimiento").attr("action", "FlujoCodigos.action");
					document.formuFlujoMovimiento.submit();
				}
				break;
			break;
			case 5:
				frag = $("#destino").val().split("X0000004");
				if (frag.length > 1){
					$("#formuFlujoMovimiento").attr("action", "FlujoCodigos.action");
					document.formuFlujoMovimiento.submit();
				}
				break;
			case 6:
				frag = $("#codigoEscape").val().split("X0000004");
				if (frag.length > 1){
					$("#formuFlujoMovimiento").attr("action", "FlujoCodigos.action");
					document.formuFlujoMovimiento.submit();
				}
				break;
		}
	}
}