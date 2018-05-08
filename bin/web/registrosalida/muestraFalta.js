$(document).ready(function(){
	$('#tablaEnvases').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	$('#tablaMaterias').dataTable({
		"oLanguage": {
			"sUrl": "languages/dataTable/es_ES.txt"
		},
		"aaSorting": [[ 0, 'asc' ]],
		"aoColumns": [
			null,
			null,
			null
		],
		"sPaginationType": "full_numbers"
	});
	calculaCantidadPedir();
});

function programarEnvasado(idPedido){
	//$.msg("Programar proceso de envasado para los productos listados");
	//$.msg("idPedido: " + idPedido);
	document.formulario.submit();
}

function calculaCantidadPedir(){
	var stocks = $(".stocks").get();
	length = stocks.length;
	//$.msg(length);
	for (i = 0; i < length; i++){
		var stock = stocks[0];
		//$.msg(stock.id);
		//$.msg(stock.innerHTML);
		var ean = stock.id.split("_")[1];
		//$.msg(ean);
		$("#falta_" + ean).text(parseFloat($("#necesitan_" + ean).text()) - parseFloat($("#stock_" + ean).text()));
		$("#envasar_" + ean).attr("value", (parseFloat($("#necesitan_" + ean).text()) - parseFloat($("#stock_" + ean).text())));
	}
}