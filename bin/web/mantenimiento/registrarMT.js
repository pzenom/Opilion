function verificadoCambio(){
	//$.msg("Seleccionando calibrado");
	//$.msg(document.formulario.calibrado.checked);
	/*if (document.formulario.verifica.checked)
		$("#verificado").val(1);
	else
		$("#verificado").val(0);*/
	$("#verificado").val(document.formulario.verifica.checked);
}

$(document).ready(function() {
	if ($("#estado").val() == 'F' || $("#estado").val() == 'F'){
		$("#boton").hide();
		$("textarea").attr("disabled","true");
		$("input").attr("disabled","true");
		$("#fechaRealizadaI").hide();
	}else
		$("#fechaRealizadaII").hide();
	$("#bot_back").attr("href", "FiltMTMaquina.action?idMaquina=" + $("#idMaquina").val());
});