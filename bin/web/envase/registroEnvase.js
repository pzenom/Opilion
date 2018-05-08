$(document).ready(function() {
	$("#dropdown_tiposMaterial").val($("#idMaterial").val());
});

function materialSeleccionado(){
	$("#idMaterial").val($("#dropdown_tiposMaterial").val());
}

function registraEnvase(){
	var nombre = $("#text_nombre").val();
	if (nombre == "") {
		$.msg("Introduzca un nombre");
	} else {
		if ($("#dropdown_tiposMaterial").val() == -1){
			$.msg("Seleccione el tipo de material");
		}else{
			$.confirm("&#191Es correcto el envase?",
				function(){
					//1. Url que guarda la materia prima
					var $url = "IngresarRegistroEnvase.action?nombre=" + nombre + "&descripcion=" + $("#text_descripcion").val() +
						"&idMaterial=" + $("#idMaterial").val() + "&peso=" + $("#text_peso").val() + "&dimensiones=" + $("#text_dimensiones").val();
					$.ajax({
						type: 'POST',
						url: $url,
						cache: false,
						async:false,
						success: function(html){
							$.msg("Envase insertado");
						}
					});
					consEnva();
				},
				function(){
					$.msg("Cancelado");
				}
			);	
		}
	}
}

function actualizaEnvase(){
	var nombre = $("#text_nombre").val();
	//$.msg(nombre);
	if (nombre == "") {
		$.msg("Introduzca un nombre");
	} else {
		if(confirm("Es correcto el nombre?")) {
			//1. Url que guarda la materia prima
			var $url = "ActualizaEnvase.action?nombre=" +nombre + "&descripcion=" + $("#text_descripcion").val() +
				"&idMaterial=" + $("#idMaterial").val() + "&peso=" + $("#text_peso").val() + "&dimensiones=" + $("#text_dimensiones").val();
			//$.msg($url);
			$.ajax({
				type: 'POST',
				url: $url,
				cache: false,
				async:false,
				success: function(html){
					$.msg("Envase actualizado correctamente");
				}
			});
			consEnva();
		}
	}
}