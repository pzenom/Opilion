function seleccionarTipoMaquinas(){
	var indice = document.formulario.idTipoMaquinas.selectedIndex;
	var valor = document.formulario.idTipoMaquinas.options[indice].value;
	//$.msg(valor);
	$("#idTipo").val(valor);
	$("#bot_inserta").show();
}

$(document).ready(function() {
	$("#bot_inserta").hide();
});

function submitform() {
	if ($("#InseMT_idTipoMaquinas").val() != 0){
		if ($("#InseMT_nombre").val() == "" || $("#InseMT_descripcion").val() == ""){
			if (confirm("Crear el mantenimiento sin nombre/descripcion?"))
				document.forms["formulario"].submit();
		} else
			document.forms["formulario"].submit();
	} else
		$.msg("Seleccione un tipo de máquina");
}