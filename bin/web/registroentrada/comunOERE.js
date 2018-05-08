function editRE(entrada, idTipoRegistro){
	if ($("#widget_consOE").length > 0)
		$("#widget_consOE").attr("id", "widget_consRE");
	//alert(1);
	var $url = "EditRE.action?codigoEntrada=" + entrada + "&idTipoRegistro=" + idTipoRegistro;
	//$.msg($url);
	$.ajax({
		url: $url,
		cache: false,
		async:false,
		success: function(html){
			//alert(html);
			$("#widget_consRE").empty();
			$("#widget_consRE").append(html);
			$("#idTipoRegistro").val(idTipoRegistro);
		}
	});
	//alert(2);
	$.ajax({
		type: "POST",
		url: "registroentrada/editRE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
	$("#btMenuRE").addClass("active");
	$("#btMenuOE").removeClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function bultos(entrada, idTipoRegistro){
	$("#orden").val($("#codigoOrden").val());
	var $url = "";
	if (idTipoRegistro == 'E' || idTipoRegistro == 'M' ||
		idTipoRegistro == 'P')
		$url = "BultosRE.action?codigoEntrada=" + entrada + "&idTipoRegistro=" + idTipoRegistro;
	else
		$url = "BultosRE.action?codigoEntrada=" + entrada;
	//$.msg($url);
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		clase = $("#btMenuOE").attr("class");
		if (clase.split("active").length > 1){
			$("#widget_consOE").empty();
			$("#widget_consOE").append(html);
		}else{
			$("#widget_consRE").empty();
			$("#widget_consRE").append(html);
		}		
	 }
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/bultosRE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
}

function listaRE(){
	if ($("#widget_consUbica").length > 0)
		$("#widget_consUbica").attr("id", "widget_consRE");
	else
		if ($("#widget_consOE").length > 0)
			$("#widget_consOE").attr("id", "widget_consRE");
	var $url = "";
	$url = "ConsRE.action?idProveedor=0";
	$.ajax({
	 url: $url,
	 cache: false,
	 async:false,
	 success: function(html){
		$("#widget_consRE").empty();
		$("#widget_consRE").append(html);
	 }
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/consRE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "registroentrada/comunOERE.js",
		dataType: "script"
	});
	$.ajax({
		type: "POST",
		url: "js/script.js",
		dataType: "script"
	});
	$("#btMenuRE").addClass("active");
	$("#btMenuOE").removeClass("active");
	$("#irAlEscritorio").removeClass("active");
}

function tipoRegistro() {
	var tipo = document.getElementById("idTipoRegistro").value;
	//$.msg("tipo: " + tipo);
	if(tipo=='M') {
		$("#categoriasEntrada").show();
		$("#categoriasSalida").show();
		$("#soloMaterias").show();
		$("#labelCantidad").text("Kilos");
		$("#temperatura").show();
		$("#humedad").show();
		$("#cosechas").show();
		$("#numPalets").show();
		$("#numBultos").show();
		$("#formatoEntrega").show();
	}
	else if(tipo=='E') {
		$("#categoriasEntrada").hide();
		$("#categoriasSalida").hide();
		$("#temperatura").hide();
		$("#humedad").hide();
		$("#cosechas").hide();
		$("#soloMaterias").hide();
		$("#labelCantidad").text("Unidades");
	}
	else {
		if(tipo == 'P') {
			$("#categoriasEntrada").hide();
			$("#categoriasSalida").hide();
			$("#temperatura").hide();
			$("#humedad").hide();
			$("#cosechas").hide();
			$("#soloMaterias").hide();
			$("#numPalets").hide();
			$("#numBultos").hide();
			$("#formatoEntrega").hide();
			$("#labelCantidad").text("Unidades");
		}else{
			//Cuando no se ha elegido nada
		}
	}
}