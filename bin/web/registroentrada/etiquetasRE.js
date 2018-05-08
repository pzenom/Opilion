$(document).ready(function() {
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick", "javascript:listaOE();");
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	$("#dropdown_proveedores").val($("#idProve").val());
	$("#" + $("#dropdown_proveedores").parent().attr("id")).children('span').text(
		$('#dropdown_proveedores option[value=' + $("#idProve").val() + ']').text());
	$("#dropdown_transportistas").val($("#idTrans").val());
	$("#" + $("#dropdown_transportistas").parent().attr("id")).children('span').text(
		$('#dropdown_transportistas option[value=' + $("#idTrans").val() + ']').text());
	tiposRegistros = $(".tipoReg").get();
	//alert(tiposRegistros.length);
	for (i = 0; i < tiposRegistros.length; i++){
		id = tiposRegistros[i].id;
		//alert(id);
		//alert($("#" + id).text());
		tipo = $("#" + id).text();
		if (tipo == 'M' || tipo == 'E'){
			$("#bultos_" + id).show();
			$("#etiquetaNormal_" + id).show();
			$("#todasEti_" + id).hide();
			$("#unaEti_" + id).hide();
		}else{
			if (tipo == 'P'){
				$("#todasEti_" + id).show();
				$("#unaEti_" + id).show();
				$("#etiquetaNormal_" + id).hide();
				$("#bultos_" + id).hide();
			}
		}
	}
	/* Miramos si permitimos borrar algun registro de entrada */
	registros = $(".registrosBorrar").get();
	//alert(registros.length);
	for (i = 0; i < registros.length; i++){
		id = registros[i].id;
		//alert($("#" + id).val());
		if ($("#" + id).val() == "true"){
			frag = id.split("podemosBorrar_");
			codigoEntrada = frag[1];
			$("#bt_deshabilitarRE_" + codigoEntrada).show();
		}
	}
	editable = $("#editable").val();
	//alert(editable);
	if (editable == "true"){
		$("#fieldsetRegistros").hide();
		$("#text_albaran").removeAttr("disabled");
		$("#text_origen").removeAttr("disabled");
		$("#dropdown_proveedores").removeAttr("disabled");
		$("#dropdown_transportistas").removeAttr("disabled");
		$("#text_albaran").removeAttr("disabled");
		$("#text_descVehiculo").removeAttr("disabled");
		$("#text_notasVehiculo").removeAttr("disabled");
		//Boton actualizar
		$("#bot_actualizar").attr("onclick", "javascript:actualizarOE();");
		$("#bot_actualizar").show();
	}
});

function deshabilitarRE(entrada){
	cuantosHijos = $("#tbody").children().length;
	cadena = "";
	if (cuantosHijos == 1){
		cadena = "<br />La orden contiene un solo registro de entrada. Se van a eliminar tanto el registro como la orden de entrada.";
	}
	$.confirm("&#191Confirma que desea eliminar el registro de entrada " + entrada + "?<br />Despues de borrar un registro de entrada no es posible volver a recuperarlo." + cadena,
		function(){
			$url = "DeleRE.action?codigoEntrada=" + entrada;
			$.ajax({
				url: $url,
				cache: false,
				async:false,
				success: function(html){
					listaOE();
				}
			});
		},
		function(){
			$.msg("CANCELADO el borrado del registro " + entrada);
		}
	);
}