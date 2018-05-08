function seleccionarTipoMaquinas(){
	var indice = document.formulario.idTipoMaquinas.selectedIndex;
	var valor = document.formulario.idTipoMaquinas.options[indice].value;
	//$.msg(valor);
	$("#idTipo").val(valor);
	if (valor != 0)
		$("#bot_inserta").show();
	else
		$("#bot_inserta").hide();
}

$(document).ready(function() {
	$("#bot_inserta").hide();
});

function submitform() {
	document.forms["formulario"].submit();
}