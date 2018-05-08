function seleccionarMaquina(){
	var indice = document.formuAjax.idMaquinas.selectedIndex;
	var valor = document.formuAjax.idMaquinas.options[indice].value;
	//$.msg(valor);
	$("#idMaquina").val(valor);
}