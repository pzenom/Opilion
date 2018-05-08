var punto = false;

$(document).ready(function() {
	$('#tablaFumi').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 1, 'desc' ]],
		"aoColumns": [
			null,
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_vuelve").attr("onclick", "javascript:nuevoGPFumi();");
	$("#bot_vuelve").show();
	$("#bot_insertar").attr("onclick", "javascript:sacarUnidadesFumigar();");
	$("#bot_insertar").show();
});

function insertar(){
	seleccionados = $(".seleccionado");
	//$.msg(seleccionados.length);
	flag = true;
	for (i = 0; i < seleccionados.length; i++){
		id = seleccionados[i].id;
		if ($("#cantidad_" + id).val() == "" || $("#cantidad_" + id).val() == 0){
			$.msg("Introduzca la cantidad a fumigar para la entrada " + id);
			flag = false;
			break;
		}
		//$.msg(id);
		sacando = $(".sacar_" + id);
		topes = $(".enHueco_" + id);
		//$.msg(sacando.length);
		total = 0;
		sigue = true;
		for (j = 0; j < sacando.length; j++){
			valor = parseFloat(sacando[j].value);
			//$.msg(topes[i]);
			tope = parseFloat(topes[j].innerHTML);
			//$.msg(tope);
			if (valor > tope){
				sigue = false;
				flag = false;
				$.msg("No puede sacar más cantidades de las que hay ubicadas");
				break;
				break;
			}
		}
		if (sigue){
			for (j = 0; j < sacando.length; j++){
				total += parseFloat(sacando[j].value);
			}
			//$.msg(total);
			if (total != $("#cantidad_" + id).val()){
				$.msg("La suma de las cantidades escogidas de las ubicaciones debe ser igual a la cantidad seleccionada para congelar");
				flag = false;
				break;
			}
		}
	}
	if (flag){
		document.formulario.submit();
	}
	//document.formulario.submit();
}

function sacarUnidadesFumigar(){
	//$.msg("holaa");
	$url = "UpdaREFumiCant.action";
	//listProcSele=E11112-1%2F&cantidad=10&sacar_211_E11112-1=10
	seleccionados = $(".seleccionado");
	contador = 0;
	for (i = 0; i < seleccionados.length; i++){
		id = seleccionados[i].id;
		//$.msg(id);
		if (i == 0)
			$url += "?listProcSele=" + id;
		else
			$url += "&listProcSele=" + id;
		sacar = $(".cantidad_" + id);
		for (j = 0; j < sacar.length; j++){
			$url += "&cantidad=" + $("#" + sacar[j].id).val();
		}
		sacar = $(".sacar_" + id);
		for (j = 0; j < sacar.length; j++){
			if ($("#" + sacar[j].id).val() > 0)
				contador++;
			$url += "&" + sacar[i].id + "=" + $("#" + sacar[j].id).val();
		}
	}
	//$.msg(contador);
	if (contador == 1){
		//$.msg($url);
		$.ajax({
			url: $url,
			cache: false,
			async:false,
			success: function(html){
				if ($("#widget_consUbica").length > 0)
					$("#widget_consUbica").attr("id", "widget_consGPFumi");
				$("#widget_consGPFumi").empty();
				$("#widget_consGPFumi").append(html);
				$(".botonUbi").hide();
			}
		});
		$.ajax({
			type: "POST",
			url: "gpfumigado/detaGPFumi.js",
			dataType: "script"
		});
		$.ajax({
			type: "POST",
			url: "js/script.js",
			dataType: "script"
		});
	}else{
		if (contador == 0)
			$.msg("Introduzca las cantidades a retirar de la ubicación");
		else
			if (contador > 1)
				$.msg("Solo se permite fumigar materias de la misma ubicación");
	}
}

function doCalcAndSubmit(cadena, proceso) {
	//$.msg(proceso);
	val1 = document.getElementById(proceso).innerHTML;
	val2 = $("#cantidad_" + proceso).val();
	//$.msg(val2);
	valresta = parseFloat(val1) - parseFloat(val2);
	//$.msg(valresta);
	if(parseFloat(val1)<parseFloat(val2)){
		$.msg("Cantidad sobrepasa el saldo actual");
		$("#cantidad_" + proceso).val(0);
	}else
		// asigna el valor al campo restado
		$("#resta_" + proceso).val(valresta);
}