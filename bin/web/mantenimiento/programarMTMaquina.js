$(document).ready(function() {
	$("#tablaCiclo").hide();
});

function seleccionarMantenimiento(){
	var indice = document.formulario.idMantenimientos.selectedIndex;
	var valor = document.formulario.idMantenimientos.options[indice].value;
	//$.msg(valor);
	if (valor > 0){
		$("#idMantenimiento").val(valor);
		$("#tablaCiclo").show();
		//$("#formuAjax_0").click();
	}
}

function seleccionarCiclo(){
	//$.msg("hola");
	var indice = document.formulario.idCiclos.selectedIndex;
	var valor = document.formulario.idCiclos.options[indice].value;
	//$.msg(valor);
	if (valor > 0){
		$("#idCiclo").val(valor);
		//$("#formuAjax_0").click();
	}
}

function calibradoCambio(){
	//$.msg("Seleccionando calibrado");
	//$.msg(document.formulario.calibrado.checked);
	if (document.formulario.calibrado.checked)
		$("#idCalibrado").val(1);
	else
		$("#idCalibrado").val(0);
}