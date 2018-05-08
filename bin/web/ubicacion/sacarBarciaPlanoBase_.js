var numeroPalets;
var paletActual = 1;

$(document).ready(function() {
	$('#confirmarUbicacion').hide();
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
	$("#tooltip0").remove();
	refHueco = "A" + $("#idAlmacen").val() + ":Z" + $("#idZona").val() + ":L" + linea + ":E" + estanteria + ":P" + piso + ":H" + hueco;
	//if (confirm("Seleccionar hueco " + refHueco + "?")){
		//$.msg("Piso: " + piso);
		$('#idEstanteria').val(estanteria);
		$('#idPiso').val(piso);
		$('.idHueco').val(huecoAbsoluto);
		$('#idHuecoPiso').val(hueco);
		$('.registro').val(registro);
		//$.msg("Piso: " + $("idPiso").val());
		$('#formulario').attr("action", "SacarSeleccionHueco");
		//$.msg("hi");
		$('#formulario_0').trigger('click');
		$('#confirmarUbicacion').show();
		$('#seleccionado').val(huecoAbsoluto + "__" + refHueco + "__" + registro);
		$('.nombreHueco').val(refHueco);
	//}
}

function muestraAlmacenados(idHueco){
	//$.msg("Hola, mostrando los almacenados...");
	$('#idHuecoMuestra').val(idHueco);
	$('#submitMuestra').trigger('click');
	//$('#ajaxMuestra').hide();
}

function confirmar(){
	//$.msg("Confirmando ubicación...");
	//this.document.formulario.submit();
	//$("#ubica_palet_" + paletActual).val($('#ref').val());
	this.document.selecciones.submit();
}

function dibujarOcupados(linea){
	huecosOcupados = $(".ocupado");
	//$.msg(huecosOcupados.length);
	for (i = 0; i < huecosOcupados.length; i++){
		//$.msg(huecosOcupados[i]);
		//Creamos la imagen
		var imgPalet = document.createElement('img');
		// Se le añade un id
		imgPalet.setAttribute("id", "imgOcupado_" + i);
		// y la ruta de la imagen
		imgPalet.setAttribute("src", "img/planos/palet.png;");
		//Id del hueco
		id = huecosOcupados[i].value;
		//$.msg(id);
		imageUrl = "img/planos/palet.png";
		$('#' + id).css('background-image', 'url(' + imageUrl + ')');
	}
}

function simple_tooltip(target_items, name){
	$('#' + target_items).each(function(i){
		if ($("#" + name + i).length > 0)
			$("#" + name + i).remove();
		//$.msg(target_items);
		//$.msg($("#ocupado_" + target_items));
		if ($("#ocupado_" + target_items).length > 0)
			html = "<div style='font-size:14px' class='" + name + "' id='" + name + i +
				"'><span>Registros almacenados en " + $("#descripcionHueco").val() + "</span>";
		else
			html = "<div style='font-size:14px' class='" + name + "' id='" + name + i +
					"'><span>El hueco " + $("#descripcionHueco").val() + " <b>no contiene ningún registro</b></span>";
		almacenados = $(".almacenado");
		//$.msg(almacenados + " -- " + almacenados.length);
		for (j = 0; j < almacenados.length; j++){
			//$.msg(almacenados[i].value);
			html += "<p>" + almacenados[j].value + "</p>";
		}
		html += "</div>";
		//$.msg(html);
		$('body').append(html);
		var my_tooltip = $("#" + name + i);
		$(this).removeAttr("title").mouseover(function(){
				my_tooltip.css({opacity:0.8, display:"none"}).fadeIn(100);
		}).mousemove(function(kmouse){
				my_tooltip.css({left:kmouse.pageX+15, top:kmouse.pageY+15});
		}).mouseout(function(){
				my_tooltip.remove();//.fadeOut(400);
		});
	});
}