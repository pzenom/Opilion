$(document).ready(function(){
	$(".botonBotonera").hide();
	$("#bot_insertar").show();
	$("#bot_insertar").attr("onclick", "javascript:insertarEDI();");
	$("#bot_vuelve").show();
	$("#bot_vuelve").attr("onclick", "javascript:listaPedidos();");
});

function insertarEDI(){
	//Comprobar el id del ultimo pedido
	$.ajaxFileUpload({
		url:'RegistroOrders.action', 
		secureuri:false,
		fileElementId:'upload',
		dataType: 'json',
		success: function (data, status){
		
		},
		error: function (data, status, e){
		}
	});
}