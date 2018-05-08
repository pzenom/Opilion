function seleccionaAlmacen(){
	  indice = document.almac.almacenes.selectedIndex;
		//if (indice > 0){
			valor = document.almac.almacenes.options[indice].value;
			textoEscogido = document.almac.almacenes.options[indice].text;
			$("#idTipoUbicacion").val(valor);
			//$.msg($("#idTipoUbicacion").val());
		//}
}

function almacenSeleccionado(){
	/*if ($("#idTipoUbicacion").val() > 0){
		document.almac.submit();
	}*/
}