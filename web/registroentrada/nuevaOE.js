$(document).ready(function() {
	$("#bot_consulta").hide();
	//Cuando el sitio carga...
	tipoReg = $("#text_tipoRegistro").val();
	//$.msg(tipoReg);
	if (tipoReg == 'M' || tipoReg == 'E'){
		//$.msg(tipoReg);
		$("#dropdown_proveedores").removeAttr("disabled");
		if (tipoReg == 'M'){//Materias primas
			//Recorro todas las opciones del select, y oculto las que no son M (id = 1)
			$("#dropdown_proveedores option").each(function(){
				//$.msg('opcion '+$(this).text()+' valor '+ $(this).attr('value'))
				aRomper = $(this).attr('value');
				frag = aRomper.split("_");
				tipoReg = frag[1];
				//$.msg(frag[0]);
				if (tipoReg == 4)
					$(this).attr("style", "display: none;");
				else
					$(this).attr("style", "");
			});
		}else
			if (tipoReg == 'E'){
				//Recorro todas las opciones del select, y oculto las que no son M (id = 1)
				$("#dropdown_proveedores option").each(function(){
					//$.msg('opcion '+$(this).text()+' valor '+ $(this).attr('value'))
					aRomper = $(this).attr('value');
					frag = aRomper.split("_");
					tipoReg = frag[1];
					//$.msg(frag[0]);
					if (tipoReg == 1)
						$(this).attr("style", "display: none;");
					else
						$(this).attr("style", "");
				});
			}
	}
	checkTransportista();
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick", "javascript:listaOE();");
	$("#bot_vuelve").show();
	$("#bot_insertar").attr("onclick", "javascript:insertarOE();");
	$("#bot_insertar").show();
	$("#widget_menu").show();
});

function checkTransportista(){
	if ($("#dropdown_transportistas").val() > 0){
		$("#etiVehi").show();
		if ($("#dropdown_transportistas").val() == 52){
			$("#nuestrosVehiculos").show();
			$("#otrosVehiculos").hide();
			$("#text_descVehiculo").hide();
		}else{
			$("#nuestrosVehiculos").hide();
			$("#otrosVehiculos").show();
			$("#text_descVehiculo").show();
		}
	}else{
		$("#etiVehi").hide();
		$("#nuestrosVehiculos").hide();
		$("#text_descVehiculo").hide();
		$("#text_descVehiculo").val("");
	}
}

function seleccionVehiculoPropio(){
	if ($("#nuestrosVehiculos").val() == 0)
		$("#descVehiculo").val("");
	else
		$("#descVehiculo").val($("#nuestrosVehiculos").val());
}

function validar() {
	var errores = "";
	var albaran=document.getElementById("formulario_albaran").value;
	var origen=document.getElementById("formulario_origen").value;
	//var proveedor=document.getElementById("formulario_idProveedor").selectedIndex;
	var proveedor=$("#idProveedor").val();
	//$.msg(proveedor);
	//var tipo=document.getElementById("formulario_idTipoRegistro").selectedIndex;
	var tipo = document.getElementById("idTipoRegistro").value;
	//$.msg(tipo);
	var transportista=document.getElementById("idTransportista").selectedIndex;
	var vehiculo=document.getElementById("descVehiculo").value;	
	if (albaran.length ==0 ) {
		$.msg("No ha especificado un número de albarán.");
	} else if (origen.length ==0 ) {
		$.msg("No ha especificado el origen.");
	} else if (proveedor == 0 ) {
		$.msg("No ha especificado un proveedor válido.");
	} else if (tipo == 0 ) {
		$.msg("No ha especificado un tipo válido.");
	} else if (transportista == 0 ) {
		$.msg("No ha especificado un transportista válido.");
	} else if (vehiculo.length == 0) {
		$.msg("No ha especificado un vehículo válido.");
	} else {
		if(confirm("¿Los datos son correctos?")) {
			document.formulario.submit();
		} else $.msg('Repasa los datos');
		//$.msg('Validación pasada');
		//document.getElementById('formulario_0').style.visibility = 'visible';
		//document.getElementById('botonera').style.visibility = 'hidden';
	}
}