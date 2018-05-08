$(document).ready(function(){
	if ($("#cantidad").val() == "0.0"){
		//$.msg("cambia");
		$("#cantidad").val("");
	}
	if ($("#lote").val() != ""){
		$("#lote").attr("readonly", "");
		$("#cantidad").removeAttr("disabled");
		$("#cantidad").focus();
		//$("#cantidad").val("");
	}else{
		$("#lote").focus();
	}
		
	if ($("#cantidad").val() != ""){
		$("#cantidad").attr("readonly", "");
		$("#devolverStock").removeAttr("disabled");
		$("#devolverStock").focus();
	}else{
		$("#cantidad").focus();
	}
	
	if ($("#devolverStock").val() != ""){
		$("#devolverStock").attr("readonly", "");
		if ($("#devolverStock").val() == "X0000011"){
			$("#fieldsetDestino").show();
			$("#destino").removeAttr("disabled");
			$("#destino").focus();
		}else{
			$("#codigoEscape").removeAttr("disabled");
			$("#codigoEscape").focus();
		}
	}else{	
		$("#devolverStock").focus();
	}
	
	if ($("#destino").val() != ""){
		$("#destino").attr("readonly", "");
		$("#codigoEscape").removeAttr("disabled");
		$("#codigoEscape").focus();
	}else{
		$("#cantidad").focus();
	}
	
	if ($("#lote").val() != "")
		$("#cantidad").focus();
	if ($("#cantidad").val() != "")
		$("#devolverStock").focus();
	if ($("#devolverStock").val() == "X0000011")
		$("#destino").focus();
	else
		$("#codigoEscape").focus();
	if ($("#destino").val() != "")
		$("#codigoEscape").focus();
	
	if ($("#msg").val() != ""){
		//$.msg($("#msg").val());
		$(".inputs").attr("disabled", "disabled");
		$(".inputs").val("");
		$("#lote").removeAttr("readonly");
		$("#lote").val("");
		$("#lote").focus();
	}
	$("#bot_consulta").hide();
});

function enviar(){
	document.formuFlujoMovimiento.submit();
}