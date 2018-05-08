var numeroPalets;
var paletActual = 1;

$(document).ready(function() {
	$('#confirmarUbicacion').hide();
	
	numeroPalets = $("#cuantosPalets").val();
	//$.msg(numeroPalets);
	for (i = 1; i <= numeroPalets; i++){
		//$.msg("hola");
		var fila = document.createElement('tr');
		// Se le añade un id
		fila.setAttribute("id", "ubicacionPalet" + i);
		// Crear la columna del envase usada
		fila.innerHTML = '<td id="label_palet_' + i + '" class="label" style="padding: 2px; font-size: 12px;"><label name="Nº" value="Nº ' + i + 	'"/>' + i + '</td>' +
			'<td id="input_palet_' + i + '" nowrap="nowrap"><input id="ubica_palet_' + i + '" label="Nº" style="width:135px;" disabled="disabled"/></td>';
		document.getElementById('paletsBody').appendChild(fila);
	}
	$("#label_palet_1").attr("style", "background-color:yellow; padding: 2px; font-size: 12px;");
	$("#input_palet_1").attr("style", "background-color:yellow; width:135px;");
});

function seleccionLinea(zona, linea, lineaAbsoluta, nombreLinea){
	//if (confirm("Seleccionar linea " + nombreLinea + "?")){
		$('#zona').val(zona);
		$('#idLineaZona').val(linea);
		$('#idLinea').val(lineaAbsoluta);
		$('#formuAjax_0').trigger('click');
//	}
}

function seleccionHueco(linea, lineaAbsoluta, estanteria, estanteriaAbsoluta, piso, pisoAbsoluto, hueco, huecoAbsoluto, registro){
	refHueco = "A" + $("#idAlmacen").val() + ":Z" + $("#idZona").val() + ":L" + linea + ":E" + estanteria + ":P" + piso + ":H" + hueco;
	//if (confirm("Seleccionar hueco " + refHueco + "?")){
		//$.msg("Piso: " + piso);
		$('#idEstanteria').val(estanteria);
		$('#idPiso').val(piso);
		$('#idHueco').val(huecoAbsoluto);
		$('#idHuecoPiso').val(hueco);
		$('#registro').val(registro);
		//$.msg("Piso: " + $("idPiso").val());
		$('#formulario_0').trigger('click');
		$('#confirmarUbicacion').show();
	//}
}

function muestraAlmacenados(idHueco){
	//$.msg("Hola, mostrando los almacenados...");
	/*$('#idHuecoMuestra').val(idHueco);
	$('#submitMuestra').trigger('click');
	$('#ajaxMuestra').hide();*/
}

function guardarUbicaciones(){
	$.msg("Mostrar ubicaciones y opcion de guardar o modificar");
}

function confirmar(){
	//$.msg("Confirmando ubicación...");
	//this.document.formulario.submit();
	$("#ubica_palet_" + paletActual).val($('#ref').val());
	if (paletActual == numeroPalets)
		guardarUbicaciones();
	else{
		$("#label_palet_" + paletActual).attr("style", "background-color: #E8EDFF; padding: 2px; font-size: 12px;");
		$("#input_palet_" + paletActual).attr("style", "background-color: #E8EDFF; width:135px;");
		paletActual++;
		$("#label_palet_" + paletActual).attr("style", "background-color: yellow; padding: 2px; font-size: 12px;");
		$("#input_palet_" + paletActual).attr("style", "background-color: yellow; width:135px;");
		$('#submitAjax').trigger('click');
	}
	//document.location.reload();
}

function dibujarOcupados(linea){
	huecosOcupados = $(".ocupado");
	//$.msg(huecosOcupados.length);
	for (i = 0; i < huecosOcupados.length; i++){
		//$.msg(huecosOcupados[i]);
		//Creamos la imagen
		var imgPalet = document.createElement('div');
		// Se le añade un id
		imgPalet.setAttribute("id", "divImgOcupado_" + i);
		imgPalet.setAttribute("style", "position: absolute;");
		// Crear la columna del envase usada
		imgPalet.innerHTML = '<img id="imgOcupado_' + i + '" src="img/planos/palet.png"/>';
		
		//$("#conjunto").prepend(imgPalet);
		//document.getElementById('conjunto').appendChild(imgPalet);
		
		//Id del hueco
		id = huecosOcupados[i].value;
		//$.msg(id);
		document.getElementById(id).appendChild(imgPalet);
		//$.msg(id);
		//switch (linea){
			//case 'A1Z1L1':
//				switch (id){
//				case '2':
					//$.msg("hi");
					//coord_vals= $('#' + id).attr('coords').split(',');
					//$.msg(coord_vals[0]);
					//$.msg($("#divImgOcupado_" + i).attr("style"));
					//$("#divImgOcupado_" + i).attr("style", "position: absolute; margin-left: " + coord_vals[0] +
					//	"px; margin-top: " + coord_vals[1] + "px");
					//$.msg($("#divImgOcupado_" + i).attr("style"));
					//$.msg($('#' + id).attr("style"));
					//$('#' + id).attr("style", "background-image: img/planos/palet.png");
					/*$('#' + id).attr("onclick", "");
					$('#' + id).attr("onmouseover", "");*/
					//$.msg("bye");
	//				break;
		//		}
			//break;
		//}
		break;
	}
}