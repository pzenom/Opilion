var cuantasLineas = 0;
var siguienteLinea = 1;
var siguienteLineaContacto = 1;
var siguienteLineaTfno = 1;
var siguienteLineaEmail = 1;
var siguienteLineaFormaPago = 1;
var numLin;

$(document).ready(function() {
	seleccionDatosGenerales();
	autoSaltaCampos();
	ocultarBreadcrumbs();
	/* Configuramos la botonera */
	$(".botonBotonera").hide();
	$("#bot_insertar").attr("onclick", "javascript:registraEntidad();");
	//$("#bot_insertar").show();
	if ($("#btMenuClientes").hasClass("active") == true){
		$("#bot_vuelve").attr("onclick", "javascript:consCli();");
	}else{
		if ($("#btMenuProveedores").hasClass("active") == true){
			$("#bot_vuelve").attr("onclick", "javascript:consProve();");
		}
	}
	$("#bot_vuelve").show();
	$("#widget_menu").show();
	setTimeout('$("#widget_tabs").show();', 250);
});

function cuentaBancariaAsociada(){
	if ($("#cuentaAsociada").is(':checked')){
		$("#sectionCuentaBancaria").show();
		$("#cuenta_banco").focus();
	}else{
		$("#sectionCuentaBancaria").hide();
	}
}

function ocultarBreadcrumbs(){
	$("#liDatosGenerales").hide();
	$("#liContactos").hide();
	$("#liDirecciones").hide();
	$("#liDatosBancarios").hide();
	$("#liOtros").hide();
}

function arrayMax(array){
	return Math.max.apply( Math, array);
}

function seleccionDirecciones(){
	seleccionDatosGenerales();
	if ($("#tbodyDirsContacto").children().length > 0)
		$("#tablaDirecciones").show();
	$("#dirs").append($("#nueva_direccion"));
	$("#dirs").append($("#tablaDirecciones"));
}

function seleccionBancarios(){
	seleccionDatosGenerales();
	$("#formasPago").append($("#nueva_formaPago"));
	$("#formasPago").append($("#tablaCuentasBanco"));
	if ($("#tbodyCuentasBanco").children().length > 0)
		$("#tablaCuentasBanco").show();
}

function ocultarTablas(){
	$("table").hide();
}

function seleccionDatosGenerales(){
	$("table").hide();
	$("#tablaTiposProveedor").show();
}

function seleccionContactos(){
	$("#tfnos").append($("#nuevo_tfno"));
	$("#tfnos").append($("#tablaTelefonos"));
	$("#mails").append($("#nuevo_email"));
	$("#mails").append($("#tablaEmails"));
	seleccionDatosGenerales();
	if ($("#tbodyTfnos").children().length > 0)
		$("#tablaTelefonos").show();
	if ($("#tbodyEmails").children().length > 0)
		$("#tablaEmails").show();
}

function seleccionRequisitos(){
	seleccionDatosGenerales();
	$("#tab_requisitos").append($("#tablaRequisitos"));
	$("#tablaRequisitos").show();
}

function guardarDireccion(){
	//$.msg("addDirContacto()");
	if ($("#dropdown_tiposDire").val() == 0)
		$.msg("Debe seleccionar el tipo de la direccion");
	else{
		var nuevaFila = document.createElement("tr");
		nuevaFila.setAttribute("id", "direccionContacto_" + siguienteLineaContacto);
		nuevaFila.setAttribute("class", "dirsContacto");
		// Crear la fila
		//$.msg("hallo");
		nuevaFila.innerHTML =
			'<input type="hidden" id="lineaContacto_' + siguienteLineaContacto + '" name="lineaContacto_' + siguienteLineaContacto + '"/>' +
			'<td><input type="hidden" value="' + $("#dropdown_tiposDire").val() + '" id="tipoContacto_' + siguienteLineaContacto + '" name="tipoContacto_' + siguienteLineaContacto + '"/>' + $("#dropdown_tiposDire :selected").text() + '</td>';
			if ($("#text_eanDireccion").val() == "")
				nuevaFila.innerHTML += '<td><input type="hidden" value="00000000000000" id="codigoEanDireccion_' + siguienteLineaContacto + '" name="codigoEanDireccion_' + siguienteLineaContacto + '"/>00000000000000</td>';
			else
				nuevaFila.innerHTML += '<td><input type="hidden" value="' + $("#text_eanDireccion").val() + '" id="codigoEanDireccion_' + siguienteLineaContacto + '" name="codigoEanDireccion_' + siguienteLineaContacto + '"/>' + $("#text_eanDireccion").val() + '</td>';
			nuevaFila.innerHTML += '<td><input type="hidden" value="' + $("#text_localidad").val() + '" id="localidadContacto_' + siguienteLineaContacto + '" name="localidadContacto_' + siguienteLineaContacto + '"/>' + $("#text_localidad").val() + '</td>' +
			'<td><input type="hidden" value="' + $("#text_calle").val() + '" id="calleContacto_' + siguienteLineaContacto + '" name="calleContacto_' + siguienteLineaContacto + '"/>' + $("#text_calle").val() + '</td>' +
			'<td><input type="hidden" value="' + $("#text_municipio").val() + '" id="municipioContacto_' + siguienteLineaContacto + '" name="municipioContacto_' + siguienteLineaContacto + '"/>' + $("#text_municipio").val() + '</td>' +
			'<td><input type="hidden" value="' + $("#dropdown_provincias").val() + '" id="provinciaContacto_' + siguienteLineaContacto + '" name="provinciaContacto_' + siguienteLineaContacto + '"/>' + $("#dropdown_provincias :selected").text() + '</td>' +
			'<td><input type="hidden" value="' + $("#text_codigoPostal").val() + '" id="codigoPostalContacto_' + siguienteLineaContacto + '" name="codigoPostalContacto_' + siguienteLineaContacto + '"/>' + $("#text_codigoPostal").val() + '</td>' +
			'<td><input type="hidden" value="' + $("#text_horario").val() + '" id="horarioContacto_' + siguienteLineaContacto + '" name="horarioContacto_' + siguienteLineaContacto + '"/>' + $("#text_horario").val() + '</td>' +
			'<td>' +
				'&nbsp;<a id="bot_editar" href="javascript:editaDireccionContacto(' + siguienteLineaContacto +');" title="Editar direcci&oacute;n"><img src="img/edit.png" alt="Editar producto" title="Editar direcci&oacute;n"/></a>'+
				'&nbsp;<a id="bot_borra" href="javascript:borraDireccionContacto(' + siguienteLineaContacto +');" title="Borrar direcci&oacute;n"><img src="img/disabled.gif" alt="Eliminar direcci&oacute;n" title="Eliminar direcci&oacute;n"/></a>'+
			'</td>';
		document.getElementById('tbodyDirsContacto').appendChild(nuevaFila);	
		siguienteLineaContacto++;
		limpiarDirContacto();
		hideFormularioDireccion();
		$("#tablaDirecciones").show();
	}
}

function addTelefono(){
	if ($("#tipo_tfno").val() == 0){
		$.msg("Debe indicar el tipo de tfno.");
	}else{
		if ($("#text_numero").val() == "")
			$.msg("Debe insertar un numero de telefono");
		else{
			var nuevaFila = document.createElement("tr");
			nuevaFila.setAttribute("id", "tfno_" + siguienteLineaTfno);
			nuevaFila.setAttribute("class", "tfnosContacto");
			// Crear la fila
			nuevaFila.innerHTML =
				'<input type="hidden" id="lineaTfno_' + siguienteLineaTfno + '" name="lineaTfno_' + siguienteLineaTfno + '"/>' +
				'<td><input type="hidden" value="' + $("#tipo_tfno").val() + '" id="tipoTfno_' + siguienteLineaTfno + '" name="tipoTfno_' + siguienteLineaTfno + '"/>' + $("#tipo_tfno :selected").text() + '</td>' +
				'<td><input type="hidden" value="' + $("#text_numero").val() + '" id="numeroTelefono_' + siguienteLineaTfno + '" name="numeroTelefono_' + siguienteLineaTfno + '"/>' + $("#text_numero").val() + '</td>' +
				'<td><input type="hidden" value="' + $("#text_notasTfno").val() + '" id="notasTelefono_' + siguienteLineaTfno + '" name="notasTelefono_' + siguienteLineaTfno + '"/>' + $("#text_notasTfno").val() + '</td>' +
				'<td>' +
					'&nbsp;<a id="bot_editar" href="javascript:editaTelefono(' + siguienteLineaTfno +');" title="Editar tel&eacute;fono"><img src="img/edit.png" alt="Editar tel&eacute;fono" title="Editar tel&eacute;fono"/></a>'+
					'&nbsp;<a id="bot_borra" href="javascript:borraTfno(' + siguienteLineaTfno +');" title="Borrar tel&eacute;fono"><img src="img/disabled.gif" alt="Eliminar tel&eacute;fono" title="Eliminar tel&eacute;fono"/></a>'+
				'</td>';
			document.getElementById('tbodyTfnos').appendChild(nuevaFila);
			siguienteLineaTfno++;
			limpiarTfno();
			hideFormularioTfno();
			$("#tablaTelefonos").show();
		}
	}
}

function addFormaPagoEntidad(){
	if ($('#text_diasFormaPago').val() > 0 && $('#dropdown_formaPagoDesde').val() == 0){
		$.msg("Debe seleccionar a partir de cuando se contar&aacute;n los d&iacute;as para el vencimiento de la forma de pago.");
	}else{
		if ($("#dropdown_formasPago").val() < 1){
			$.msg("Debe indicar la forma de pago");
		}else{
			if (!($("#cuentaAsociada").is(':checked')) || ($("#cuentaAsociada").is(':checked') && validar())){
				var celdaCuentaBanco = '';
				if ($("#cuentaAsociada").is(':checked')){
					celdaCuentaBanco = '<td><span id="cuenta_iban_' + siguienteLineaFormaPago + '">' + $("#cuenta_iban").val() + '</span></td>' +
						'<td><span id="cuenta_banco_' + siguienteLineaFormaPago + '">' + $("#cuenta_banco").val() + '</span></td>' +
						'<td><span id="cuenta_ofici_' + siguienteLineaFormaPago + '">' + $("#cuenta_ofici").val() + '</span></td>' +
						'<td><span id="cuenta_contr_' + siguienteLineaFormaPago + '">' + $("#cuenta_contr").val() + '</span></td>' +
						'<td><span id="cuenta_numer_' + siguienteLineaFormaPago + '">' + $("#cuenta_numer").val() + '</span></td>';
				}else
					celdaCuentaBanco = '<td><span id="cuenta_iban_' + siguienteLineaFormaPago + '">' + '0000' + '</span></td>' +
						'<td><span id="cuenta_banco_' + siguienteLineaFormaPago + '">' + '0000' + '</span></td>' +
						'<td><span id="cuenta_ofici_' + siguienteLineaFormaPago + '">' + '0000' + '</span></td>' +
						'<td><span id="cuenta_contr_' + siguienteLineaFormaPago + '">' + '00' + '</span></td>' +
						'<td><span id="cuenta_numer_' + siguienteLineaFormaPago + '">' + '0000000000' + '</span></td>';
				var nuevaFila = document.createElement("tr");
				nuevaFila.setAttribute("id", "formaPago_" + siguienteLineaFormaPago);
				nuevaFila.setAttribute("class", "formaPagoEntidad");
				// Crear la fila
				nuevaFila.innerHTML =
					'<input type="hidden" id="lineaFormaPago_' + siguienteLineaFormaPago + '" name="lineaFormaPago_' + siguienteLineaFormaPago + '"/>' +
					'<input type="hidden" id="cuentaAsociada_' + siguienteLineaFormaPago + '" name="cuentaAsociada_' + siguienteLineaFormaPago + '" value="' + $("#cuentaAsociada").is(':checked') + '"/>' +
					'<td>' +
						'<input type="hidden" id="tipoFormaPago_' + siguienteLineaFormaPago + '" name="tipoFormaPago_' + siguienteLineaFormaPago + '" value="' + $("#dropdown_formasPago").val() + '" />' +
						'<input type="hidden" id="tipoFormaPagoDesde_' + siguienteLineaFormaPago + '" name="tipoFormaPagoDesde_' + siguienteLineaFormaPago + '" value="' + $("#dropdown_formaPagoDesde").val() + '" />' +
						'<span id="descFormaPago_' + siguienteLineaFormaPago + '">' +
						$("#dropdown_formasPago :selected").text() + '</span><div id="spanFormaPagoDias_' + siguienteLineaFormaPago + '" style="display: none;"> a <span id="diasFormaPago_' + siguienteLineaFormaPago + '">' + $("#text_diasFormaPago").val() + '</span> dias desde <span id="diasFormaPagoDesde_' + siguienteLineaFormaPago + '">' + $("#dropdown_formaPagoDesde :selected").text() + '</span></div>' +
					'</td>' +
					//'<td>' +
						celdaCuentaBanco +
					//'</td>' +
					'<td><input type="hidden" value="' + $("#diaPago").val() + '" id="diaPago_' + siguienteLineaFormaPago + '" name="diaPago_' + siguienteLineaFormaPago + '"/><span id="diaFP_' + siguienteLineaFormaPago + '">' + $("#diaPago").val() + '</span></td>' +
					'<td>' +
						'&nbsp;<a id="bot_editar" href="javascript:editaFormaPago(' + siguienteLineaFormaPago +');" title="Editar forma de pago"><img src="img/edit.png" alt="Editar forma de pago" title="Editar forma de pago"/></a>'+
						'&nbsp;<a id="bot_borra" href="javascript:borraFormaPago(' + siguienteLineaFormaPago +');" title="Borrar forma de pago"><img src="img/disabled.gif" alt="Eliminar forma de pago" title="Eliminar forma de pago"/></a>'+
					'</td>';
				document.getElementById('tbodyCuentasBanco').appendChild(nuevaFila);
				$("#formaPago_" + siguienteLineaFormaPago).val($("#dropdown_formasPago").val());
				if ($("#text_diasFormaPago").val() == "")
					$("#diasFormaPago_" + siguienteLineaFormaPago).text(0);
				else
					$("#diasFormaPago_" + siguienteLineaFormaPago).text($("#text_diasFormaPago").val());
				if (parseFloat($("#text_diasFormaPago").val()) > 0){
					$("#spanFormaPagoDias_" + siguienteLineaFormaPago).show();
				}else{
					$("#spanFormaPagoDias_" + siguienteLineaFormaPago).hide();
				}
				siguienteLineaFormaPago++;
				limpiarFormaPago();
				hideFormularioFormaPago();
				$("#tablaCuentasBanco").show();
			}
		}
	}
}

function guardaCambiosFormaPago(){
	if ($('#text_diasFormaPago').val() > 0 && $('#dropdown_formaPagoDesde').val() == 0){
		$.msg("Debe seleccionar a partir de cuando se contar&aacute;n los d&iacute;as para el vencimiento de la forma de pago.");
	}else{
		if ($("#dropdown_formasPago").val() < 1){
			$.msg("Debe indicar la forma de pago");
		}else{
			if ($("#cuentaAsociada").is(':checked')){
				$("#cuenta_iban_" + numLin).text($("#cuenta_iban").val());
				$("#cuenta_banco_" + numLin).text($("#cuenta_banco").val());
				$("#cuenta_ofici_" + numLin).text($("#cuenta_ofici").val());
				$("#cuenta_contr_" + numLin).text($("#cuenta_contr").val());
				$("#cuenta_numer_" + numLin).text($("#cuenta_numer").val());
				$("#cuentaAsociada_" + numLin).val(true);
			}else{
				$("#cuenta_iban_" + numLin).text("0000");
				$("#cuenta_banco_" + numLin).text("0000");
				$("#cuenta_ofici_" + numLin).text("0000");
				$("#cuenta_contr_" + numLin).text("00");
				$("#cuenta_numer_" + numLin).text("0000000000");
				$("#cuentaAsociada_" + numLin).val(false);
			}
			$('#tipoFormaPago_' + numLin).val($("#dropdown_formasPago").val());
			$('#tipoFormaPagoDesde_' + numLin).val($("#dropdown_formaPagoDesde").val());
			//$('#formaPagoDesde_' + numLin).text($("#dropdown_formaPagoDesde :selected").text());
			$('#descFormaPago_' + numLin).text($("#dropdown_formasPago :selected").text());
			if ($("#text_diasFormaPago").val() == "")
				$("#diasFormaPago_" + numLin).text(0);
			else
				$("#diasFormaPago_" + numLin).text($("#text_diasFormaPago").val());
			if (parseFloat($("#text_diasFormaPago").val()) > 0){
				$("#spanFormaPagoDias_" + numLin).show();
			}else{
				$("#spanFormaPagoDias_" + numLin).hide();
			}
			$("#diaFP_" + numLin).text($("#diaPago").val());
			$("#diaPago_" + numLin).val($("#diaPago").val());
			$("#diasFormaPagoDesde_" + numLin).text($("#dropdown_formaPagoDesde :selected").text().trim());						
			hideFormularioFormaPago();
		}
	}
}

function addNuevoEmail(){
	if ($("#dropdown_tipoEmail").val() == 0){
		$.msg("Debe indicar el tipo de email");
	}else{
		if ($("#text_mail").val() == "")
			$.msg("Debe insertar una direccion de email");
		else{
			var nuevaFila = document.createElement("tr");
			nuevaFila.setAttribute("id", "email_" + siguienteLineaEmail);
			nuevaFila.setAttribute("class", "emailsContacto");
			// Crear la fila
			nuevaFila.innerHTML =
				'<input type="hidden" id="lineaEmail_' + siguienteLineaEmail + '" name="lineaEmail_' + siguienteLineaEmail + '"/>' +
				'<td><input type="hidden" value="' + $("#dropdown_tipoEmail").val() + '" id="tipoEmail_' + siguienteLineaEmail + '" name="tipoEmail_' + siguienteLineaEmail + '"/>' + $("#dropdown_tipoEmail :selected").text() + '</td>' +
				'<td><input type="hidden" value="' + $("#text_mail").val() + '" id="direccionEmail_' + siguienteLineaEmail + '" name="direccionEmail_' + siguienteLineaEmail + '"/>' + $("#text_mail").val() + '</td>' +
				'<td><input type="hidden" value="' + $("#text_notasEmail").val() + '" id="notasEmail_' + siguienteLineaEmail + '" name="notasEmail_' + siguienteLineaEmail + '"/>' + $("#text_notasEmail").val() + '</td>' +
				'<td>' +
					'&nbsp;<a id="bot_editar" href="javascript:editaEmail(' + siguienteLineaEmail +');" title="Editar email"><img src="img/edit.png" alt="Editar email" title="Editar email"/></a>'+
					'&nbsp;<a id="bot_borra" href="javascript:borraEmail(' + siguienteLineaEmail +');" title="Borrar email"><img src="img/disabled.gif" alt="Eliminar email" title="Eliminar email"/></a>'+
				'</td>';
			document.getElementById('tbodyEmails').appendChild(nuevaFila);
			siguienteLineaEmail++;
			limpiarEmail();
			hideFormularioEmail();
			$("#tablaEmails").show();
		}
	}
}

function limpiarDirContacto(){
	$("#text_localidad").val("");
	$("#text_calle").val("");
	$("#text_codigoPostal").val("");
	$("#text_eanDireccion").val("");
	$("#text_municipio").val("");
	$("#text_horario").val("");
	$("#dropdown_tiposDire").val(0);
	$("#" + $("#dropdown_tiposDire").parent().attr("id")).children('span').text(
		$("#dropdown_tiposDire option[value=0]").text());
	$("#" + $("#dropdown_provincias").parent().attr("id")).children('span').text(
		$("#dropdown_provincias option[value=0]").text());
	$("#dropdown_provincias").val(0);
}

function limpiarTfno(){
	$("#text_numero").val("");
	$("#text_notasTfno").val("");
	$("#tipo_tfno").val(0);
	$("#" + $("#tipo_tfno").parent().attr("id")).children('span').text(
		$('#tipo_tfno option[value=0]').text());
}

function limpiarFormaPago(){
	$(".numerosCuenta").val("");
	$("#diaPago").val(0);
	$("#text_diasFormaPago").val(0);
	$("#" + $("#dropdown_formasPago").parent().attr("id")).children('span').text(
		$('#dropdown_formasPago option[value=-1]').text());
	$("#dropdown_formasPago").val(-1);
	$('input[id=cuentaAsociada]').attr('checked', false);
	$("#cuentaAsociada").parent().attr("style", "");//background-position: -92px -320px;
	$("#cuentaAsociada").parent().removeClass("checked");
	$("#sectionCuentaBancaria").hide();
	$("#cuenta_iban").val("0000");
}

function limpiarEmail(){
	$("#text_mail").val("");
	$("#text_notasEmail").val("");
	$("#dropdown_tipoEmail").val(0);
	$("#" + $("#dropdown_tipoEmail").parent().attr("id")).children('span').text(
		$('#dropdown_tipoEmail option[value=0]').text());
}

function addDireccionContacto(){
	$("#btAddDirCon").show();
	$("#btEditDirCon").hide();
	showFormularioDireccion();
}

function nuevaFormaPago(){
	$("#btAddFormaPago").show();
	$("#btEditFormaPago").hide();
	showFormularioFormaPago();
}

function showFormularioDireccion(){	
	$("#nueva_direccion").slideDown();
	$("#addDirContacto").slideUp();
}

function showFormularioFormaPago(){	
	$("#nueva_formaPago").slideDown();
	$("#btNuevaFormaPago").slideUp();
}

function nuevoTfno(){
	//$.msg("nuevo");
	$("#btAddTfno").show();
	$("#btEditTfno").hide();
	showFormularioTfno();
	//$("#addTfno").hide();
}

function showFormularioTfno(){	
	$("#nuevo_tfno").slideDown('fast', function() {
	  $("#addTfno").hide();
	});
}

function addMail(){
	$("#addEmail").hide();
	$("#btAddEmail").show();
	$("#btEditEmail").hide();
	showFormularioEmail();
}

function showFormularioEmail(){	
	$("#nuevo_email").slideDown();
	$("#addEmail").slideUp();
}

function hideFormularioTfno(){
	$("#nuevo_tfno").slideUp();
	limpiarTfno();
	$("#addTfno").show();
}

function hideFormularioFormaPago(){
	$("#nueva_formaPago").slideUp();
	limpiarFormaPago();
	$("#btNuevaFormaPago").show();
}

function borraTfno(numLinea){
	$("#tfno_" + numLinea).unbind();
	$("#tfno_" + numLinea).remove();
	hideFormularioTfno();
	if ($("#tbodyTfnos").children().length == 0)
		$("#tablaTelefonos").hide();
}

function borraFormaPago(numLinea){
	$("#formaPago_" + numLinea).unbind();
	$("#formaPago_" + numLinea).remove();
	hideFormularioFormaPago();
	if ($("#tbodyCuentasBanco").children().length == 0)
		$("#tablaCuentasBanco").hide();
}

function hideFormularioEmail(){
	$("#nuevo_email").slideUp();
	limpiarEmail();
	$("#addEmail").show();
}

function borraEmail(numLinea){
	$("#email_" + numLinea).unbind();
	$("#email_" + numLinea).remove();
	hideFormularioEmail();
	if ($("#tbodyEmails").children().length == 0)
		$("#tablaEmails").hide();
}

function hideFormularioDireccion(){
	$("#nueva_direccion").slideUp();
	limpiarDirContacto();
	$("#addDirContacto").show();
}

function borraDireccionContacto(numLinea){
	$("#direccionContacto_" + numLinea).unbind();
	$("#direccionContacto_" + numLinea).remove();
	hideFormularioDireccion();
}

function editaDireccionContacto(numLinea){
	$("#dropdown_tiposDire").val($('#tipoContacto_' + numLinea).val());
	$("#" + $("#dropdown_tiposDire").parent().attr("id")).children('span').text(
		$('#dropdown_tiposDire option[value=' + $('#tipoContacto_' + numLinea).val() + ']').text());
	$("#" + $("#dropdown_provincias").parent().attr("id")).children('span').text(
		$('#dropdown_provincias option[value=' + $('#provinciaContacto_' + numLinea).val() + ']').text());
	$("#text_horario").val($('#horarioContacto_' + numLinea).val());
	$("#text_calle").val($('#calleContacto_' + numLinea).val());
	$("#text_localidad").val($('#localidadContacto_' + numLinea).val());
	$("#text_municipio").val($('#municipioContacto_' + numLinea).val());
	$("#text_codigoPostal").val($('#codigoPostalContacto_' + numLinea).val());
	$("#text_eanDireccion").val($('#codigoEanDireccion_' + numLinea).val());
	$("#dropdown_provincias").val($('#provinciaContacto_' + numLinea).val());
	$("#btAddDirCon").hide();
	$("#btEditDirCon").show();
	showFormularioDireccion();
	numLin = numLinea;
}

function editaTelefono(numLinea){
	$("#tipo_tfno").val($('#tipoTfno_' + numLinea).val());
	$("#" + $("#tipo_tfno").parent().attr("id")).children('span').text(
		$('#tipo_tfno option[value=' + $('#tipoTfno_' + numLinea).val() + ']').text());
	$("#text_numero").val($('#numeroTelefono_' + numLinea).val());
	$("#text_notasTfno").val($('#notasTelefono_' + numLinea).val());
	$("#btAddTfno").hide();
	//$("#addTfno").hide();
	$("#btEditTfno").show();
	showFormularioTfno();
	numLin = numLinea;
}

function editaFormaPago(numLinea){
	if ($('#tipoFormaPago_' + numLinea).val() == ""){
		descFormaPago = $('#descFormaPago_' + numLinea).text();
		$("#dropdown_formasPago").val($('#tipoFormaPago_' + numLinea).val());
		$("#" + $("#dropdown_formasPago").parent().attr("id")).children('span').text(descFormaPago);
	}else{
		$("#dropdown_formasPago").val($('#tipoFormaPago_' + numLinea).val());
		$("#" + $("#dropdown_formasPago").parent().attr("id")).children('span').text(
			$('#dropdown_formasPago option[value=' + $('#tipoFormaPago_' + numLinea).val() + ']').text());
	}
	//alert($('#tipoFormaPagoDesde_' + numLinea).val());
	$("#dropdown_formaPagoDesde").val($('#tipoFormaPagoDesde_' + numLinea).val());
		$("#" + $("#dropdown_formaPagoDesde").parent().attr("id")).children('span').text(
			$('#dropdown_formaPagoDesde option[value=' + $('#tipoFormaPagoDesde_' + numLinea).val() + ']').text());
	$("#diaPago").val($('#diaPago_' + numLinea).val());
	$("#text_diasFormaPago").val($('#diasFormaPago_' + numLinea).text().trim());
	$("#cuenta_iban").val($('#cuenta_iban_' + numLinea).text());
	$("#cuenta_banco").val($('#cuenta_banco_' + numLinea).text());
	$("#cuenta_ofici").val($('#cuenta_ofici_' + numLinea).text());
	$("#cuenta_contr").val($('#cuenta_contr_' + numLinea).text());
	$("#cuenta_numer").val($('#cuenta_numer_' + numLinea).text());
	$("#btAddFormaPago").hide();
	$("#btEditFormaPago").show();
	if ($('#cuentaAsociada_' + numLinea).val() == "true" || $('#cuentaAsociada_' + numLinea).val() == true)
		if (!$("#cuentaAsociada").is(':checked')){
			$('#cuentaAsociada').trigger('click');
		}
	showFormularioFormaPago();
	numLin = numLinea;
}

function editaEmail(numLinea){
	$("#dropdown_tipoEmail").val($('#tipoEmail_' + numLinea).val());
	$("#" + $("#dropdown_tipoEmail").parent().attr("id")).children('span').text(
		$('#dropdown_tipoEmail option[value=' + $('#tipoEmail_' + numLinea).val() + ']').text());
	$("#text_mail").val($('#direccionEmail_' + numLinea).val());
	$("#text_notasEmail").val($('#notasEmail_' + numLinea).val());
	$("#btAddEmail").hide();
	$("#addEmail").hide();
	$("#btEditEmail").show();
	showFormularioEmail();
	numLin = numLinea;
}

function editDirContacto(){
	if ($("#dropdown_tiposDire").val() == 0)
		$.msg("Debe seleccionar el tipo de la direccion");
	else{
		$('#tipoContacto_' +numLin).parent().html('<input type="hidden" value="' + $("#dropdown_tiposDire").val() + '" id="tipoContacto_' + numLin + '" name="tipoContacto_' + numLin + '"/>'+$("#dropdown_tiposDire :selected").text());
		$('#horarioContacto_' +numLin).parent().html('<input type="hidden" value="' + $("#text_horario").val() + '" id="horarioContacto_' + numLin + '" name="horarioContacto_' + numLin + '"/>' + $("#text_horario").val());
		$('#calleContacto_' +numLin).parent().html('<input type="hidden" value="' + $("#text_calle").val() + '" id="calleContacto_' + numLin + '" name="calleContacto_' + numLin + '"/>' + $("#text_calle").val());
		$('#localidadContacto_' +numLin).parent().html('<input type="hidden" value="' + $("#text_localidad").val() + '" id="localidadContacto_' + numLin + '" name="localidadContacto_' + numLin + '"/>' + $("#text_localidad").val());
		$('#municipioContacto_' +numLin).parent().html('<input type="hidden" value="' + $("#text_municipio").val() + '" id="municipioContacto_' + numLin + '" name="municipioContacto_' + numLin + '"/>' + $("#text_municipio").val());
		$('#codigoPostalContacto_' +numLin).parent().html('<input type="hidden" value="' + $("#text_codigoPostal").val() + '" id="codigoPostalContacto_' + numLin + '" name="codigoPostalContacto_' + numLin + '"/>' + $("#text_codigoPostal").val());
		$('#codigoEanDireccion_' +numLin).parent().html('<input type="hidden" value="' + $("#text_eanDireccion").val() + '" id="codigoEanDireccion_' + numLin + '" name="codigoEanDireccion_' + numLin + '"/>' + $("#text_eanDireccion").val());
		$('#provinciaContacto_' +numLin).parent().html('<input type="hidden" value="' + $("#dropdown_provincias").val() + '" id="provinciaContacto_' + numLin + '" name="provinciaContacto_' + numLin + '"/>' + $("#dropdown_provincias :selected").text());
		hideFormularioDireccion();
	}
}

function editaTfno(){
	$('#tipoTfno_' + numLin).parent().html('<input type="hidden" value="' + $("#tipo_tfno").val() + '" id="tipoTfno_' + numLin + '" name="tipoTfno_' + numLin + '"/>'+$("#tipo_tfno :selected").text());
	$('#numeroTelefono_' +numLin).parent().html('<input type="hidden" value="' + $("#text_numero").val() + '" id="numeroTelefono_' + numLin + '" name="numeroTelefono_' + numLin + '"/>' + $("#text_numero").val());
	$('#notasTelefono_' +numLin).parent().html('<input type="hidden" value="' + $("#text_notasTfno").val() + '" id="notasTelefono_' + numLin + '" name="notasTelefono_' + numLin + '"/>' + $("#text_notasTfno").val());
	hideFormularioTfno();
}

function editarEmail(){
	$('#tipoEmail_' + numLin).parent().html('<input type="hidden" value="' + $("#dropdown_tipoEmail").val() + '" id="tipoEmail_' + numLin + '" name="tipoEmail_' + numLin + '"/>'+$("#dropdown_tipoEmail :selected").text());
	$('#direccionEmail_' +numLin).parent().html('<input type="hidden" value="' + $("#text_mail").val() + '" id="direccionEmail_' + numLin + '" name="direccionEmail_' + numLin + '"/>' + $("#text_mail").val());
	$('#notasEmail_' +numLin).parent().html('<input type="hidden" value="' + $("#text_notasEmail").val() + '" id="notasEmail_' + numLin + '" name="notasEmail_' + numLin + '"/>' + $("#text_notasEmail").val());
	hideFormularioEmail();
}

function registraEntidad() {
	flag = false;
	rolesEntidad = "";
	urlTiposProveedor = "";
	if ($("#bot_proveedor").hasClass('btActivado') == true && $("#bot_cliente").hasClass('btActivado') == true){
		rolesEntidad = 'PC';
		if ($("#dropdown_sectores").val() <= 0){
			$.msg("Seleccione el sector de la entidad como cliente");
		}
		if ($("#tbodyTiposProveedor").children().length == 0){
			$.msg("Seleccione al menos un tipo para la entidad como proveedor");
		}
		if ($("#dropdown_sectores").val() > 0 && $("#tbodyTiposProveedor").children().length > 0){
			flag = true;
			tipos = $(".tiposProveedor");
			for (i = 0; i < tipos.length; i++){
				id = tipos[i].id;
				frag = id.split("_");
				idTipo = frag[1];
				urlTiposProveedor += "&tipoProveedor_" + idTipo + "=" + idTipo;
			}
		}
	}else{
		if ($("#bot_proveedor").hasClass('btActivado') == true){
			rolesEntidad = 'P';
			if ($("#tbodyTiposProveedor").children().length == 0){
				$.msg("Seleccione al menos un tipo para la entidad como proveedor");
			}else{
				flag = true;
				tipos = $(".tiposProveedor");
				for (i = 0; i < tipos.length; i++){
					id = tipos[i].id;
					frag = id.split("_");
					idTipo = frag[1];
					urlTiposProveedor += "&tipoProveedor_" + idTipo + "=" + idTipo;
				}
			}
		}else{
			if ($("#bot_cliente").hasClass('btActivado') == true){
				rolesEntidad = 'C';
				if ($("#dropdown_sectores").val() <= 0){
					$.msg("Seleccione el sector de la entidad como cliente");
				}else
					flag = true;
			}
		}
	}
	if (flag){
		var nombre = document.getElementById("text_nombre").value;
		if (($("#bot_autonomo").hasClass('btActivado') == false) && ($("#bot_empresa").hasClass('btActivado') == false)){
			$.msg("Debe seleccionar si se trata de un autonomo o de una empresa");
		}else{
			$.confirm("&#191Es correcta la nueva entidad?",
				function(){
					//1. Ajax que guarda la entidad
					urlDatosGenerales = "?nombre=" + encodeURIComponent($("#text_nombre").val()) + "&apellidos=" + encodeURIComponent($("#text_apellidos").val()) + "&idSector=" + $("#dropdown_sectores").val() + "&nif=" + $("#text_DNI").val() + "&ean=" + $("#text_EAN").val() + "&web=" + encodeURIComponent($("#text_web").val());
					//1.01 Preparar la URL con las direcciones asociadas al cliente (lineas con clase = dirsContacto)
					direcciones = $(".dirsContacto");
					urlDirecciones = "&";
					//$.msg("Direcciones: " + direcciones.length);
					for (i = 0; i < direcciones.length; i++){
						id = direcciones.get(i).id;
						//$.msg(id);
						frag = id.split("_");
						indice = frag[1];
						urlDireccion = "lineaContacto_" + indice + "=0&provinciaContacto_" + indice + "=" + $("#provinciaContacto_" + indice).val() +
							"&localidadContacto_" + indice + "=" + $("#localidadContacto_" + indice).val() + "&codigoEanDireccion_" + indice + "=" + 
							$("#codigoEanDireccion_" + indice).val() + "&codigoPostalContacto_" + indice + "=" + $("#codigoPostalContacto_" + indice).val() + "&calleContacto_" + indice + "=" + $("#calleContacto_" + indice).val() + "&municipioContacto_" + indice + "=" + $("#municipioContacto_" + indice).val() + "&horarioContacto_" + indice + "=" + $("#horarioContacto_" + indice).val() + "&tipoContacto_" + indice + "=" + $("#tipoContacto_" + indice).val();
						//$.msg(urlDireccion);
						if (i > 0)
							urlDirecciones += "&";
						urlDirecciones += urlDireccion;
					}
					//$.msg(urlDirecciones);
					//1.02 Preparar la URL con los telefonos asociados al cliente (lineas con clase = tfnosContacto)
					telefonos = $(".tfnosContacto");
					urlTelefonos = "&";
					//$.msg("Direcciones: " + direcciones.length);
					for (i = 0; i < telefonos.length; i++){
						id = telefonos.get(i).id;
						//$.msg(id);
						frag = id.split("_");
						indice = frag[1];
						urlTelefono = "lineaTfno_" + indice + "=0&numeroTelefono_" + indice + "=" + $("#numeroTelefono_" + indice).val() +
							"&tipoTfno_" + indice + "=" + $("#tipoTfno_" + indice).val() + "&notasTelefono_" + indice + "=" +
							$("#notasTelefono_" + indice).val();
						//idTelefono????
						//$.msg(urlTelefono);
						if (i > 0)
							urlTelefonos += "&";
						urlTelefonos += urlTelefono;
					}
					//$.msg(urlTelefonos);
					//1.03 Preparar la URL con los emails asociados al cliente (lineas con clase = emailsContacto)
					emails = $(".emailsContacto");
					urlEmails = "&";
					//$.msg("Direcciones: " + direcciones.length);
					for (i = 0; i < emails.length; i++){
						id = emails.get(i).id;
						//$.msg(id);
						frag = id.split("_");
						indice = frag[1];
						urlEmail = "lineaEmail_" + indice + "=0&direccionEmail_" + indice + "=" + $("#direccionEmail_" + indice).val() +
							"&tipoEmail_" + indice + "=" + $("#tipoEmail_" + indice).val() + "&notasEmail_" + indice + "=" +
							$("#notasEmail_" + indice).val();
						//idTelefono????
						//$.msg(urlEmail);
						if (i > 0)
							urlEmails += "&";
						urlEmails += urlEmail;
					}
					//$.msg(urlEmails);
					//1.04 Preparar la URL con las formas de pago asociadas a la entidad (lineas con clase = formaPagoEntidad)
					formasDePago = $(".formaPagoEntidad");
					urlFormasPago = "&";
					for (i = 0; i < formasDePago.length; i++){
						id = formasDePago.get(i).id;
						//$.msg(id);
						frag = id.split("_");
						indice = frag[1];
						urlFormaDePago = "lineaFormaPago_" + indice + "=0&formaPago_" + indice + "=" + $("#formaPago_" + indice).val() +
							"&idFormaPagoDesde_" + indice + "=" + $("#tipoFormaPagoDesde_" + indice).val() +
							"&diasFormaPago_" + indice + "=" + $("#diasFormaPago_" + indice).text() +
							"&cuenta_iban_" + indice + "=" + $("#cuenta_iban_" + indice).text() +
							"&cuenta_banco_" + indice + "=" + $("#cuenta_banco_" + indice).text() +
							"&cuenta_ofici_" + indice + "=" + $("#cuenta_ofici_" + indice).text() +
							"&cuenta_contr_" + indice + "=" + $("#cuenta_contr_" + indice).text() +
							"&cuenta_numer_" + indice + "=" + $("#cuenta_numer_" + indice).text() +
							"&diaPago_" + indice + "=" + $("#diaPago_" + indice).val();
						//$.msg(urlFormaDePago);
						if (i > 0)
							urlFormasPago += "&";
						urlFormasPago += urlFormaDePago;
					}
					//1.04 Otros
					exportable = 'X';
					if ($('#checkExportableAutoventa').is(':checked')){
						exportable = 'S';
					}else{
						exportable = 'N';
					}
					//Observaciones
					var observaciones = tinyMCE.get('txt_altaEntidad_observaciones').getContent();
					//alert(observaciones);
					observaciones = encodeURIComponent(observaciones);
					urlOtros = "&limiteCredito=" + $("#text_limiteCredito").val() +	"&dsctoMercancia=" + $("#text_dsctoMercancia").val() +
						"&dsctoValor=" + $("#text_dsctoValor").val() + "&recargoEquivalencia=" + $("#text_recargoEqui").val() +
						"&exportableAutoventa=" + exportable + "&observaciones=" + observaciones;
					//$.msg(urlDatosBancarios);
					urlFINAL = urlDatosGenerales;
					if (urlDirecciones.length > 1)
						urlFINAL += urlDirecciones;
					if (urlTelefonos.length > 1)
						urlFINAL += urlTelefonos;
					if (urlFormasPago.length > 1)
						urlFINAL += urlFormasPago;
					if (urlEmails.length > 1)
						urlFINAL += urlEmails;
					//urlFINAL += urlDatosBancarios;
					urlFINAL += "&rolesDeLaEntidad=" + rolesEntidad;
					urlFINAL += "&sectorEntidad=" + $("#dropdown_sectores").val();
					urlFINAL += urlTiposProveedor;
					urlFINAL += urlOtros;
					if ($("#bot_autonomo").hasClass('btActivado') == true)
						urlFINAL += "&autonomoEmpresa=A";
					else
						if ($("#bot_empresa").hasClass('btActivado') == true)
							urlFINAL += "&autonomoEmpresa=E";
					//$.msg(urlFINAL);
					urlFINAL += '&idRuta=' + $('#dropdown_rutas').val() + '&idComercial=' + $('#dropdown_comerciales').val();
					var $url = "IngresaEntidad.action" + urlFINAL;
					//alert($url);
					$.ajax({
						type: 'POST',
						url: $url,
						cache: false,
						async:false,
						success: function(html){
							$.msg("Cliente insertado correctamente");
						}
					});
					//2. Consultamos los clientes/proveedores
					if ($("#btMenuClientes").hasClass("active") == true)
						$url = "ConsultaClientes.action";
					else
						if ($("#btMenuProveedores").hasClass("active") == true)
							$url = "ConsultaProveedores.action";
					$.ajax({
						url: $url,
						cache: false,
						async:false,
						success: function(html){
							if ($("#btMenuClientes").hasClass("active") == true){
								$("#widget_consCli").empty();
								$("#widget_consCli").append(html);
								$.ajax({
									type: "POST",
									url: "cliente/consultaClientes.js",
									dataType: "script"
								});
							}else{
								if ($("#btMenuProveedores").hasClass("active") == true){
									$("#widget_consProv").empty();
									$("#widget_consProv").append(html);
									$.ajax({
										type: "POST",
										url: "proveedor/consultaProveedores.js",
										dataType: "script"
									});
								}
							}
							$.ajax({
								type: "POST",
								url: "js/script.js",
								dataType: "script"
							});
						}
					});
				},
				function(){
					$.msg("Cancelado");
				}
			);
		}
	}
}

function numerico(valor){
	cad = valor.toString();
	for (var i=0; i<cad.length; i++) {
		var caracter = cad.charAt(i);
		if (caracter<"0" || caracter>"9")
			return false;
	}
	return true;
}

function obtenerDigito(valor){
	valores = new Array(1, 2, 4, 8, 5, 10, 9, 7, 3, 6);
	control = 0;
	for (i=0; i<=9; i++)
		control += parseInt(valor.charAt(i)) * valores[i];
	control = 11 - (control % 11);
	if (control == 11) control = 0;
	else if (control == 10) control = 1;
	return control;
}

function validar(f) {
	if($('#cuenta_iban').val().length != 4 || $('#cuenta_banco').val().length != 4 || $('#cuenta_ofici').val().length != 4 || $('#cuenta_contr').val().length != 2 || $('#cuenta_numer').val().length != 10 ){
		//podria autocompletar con ceros a la izquierda
		$.msg("NO VALIDADO: Por favor, complete el numero de cuenta completando con ceros a la izquierda si fuera necesario, hasta tener los 20 digitos.");
		return false;
	}else {
		if(!numerico($('#cuenta_banco').val()) || !numerico($('#cuenta_ofici').val()) || !numerico($('#cuenta_contr').val()) || !numerico($('#cuenta_numer').val()) ){
			$.msg("NO VALIDADO: Existen caracteres no numericos en el numero de la cuenta.");
			return false;
		}else {
			if (!(obtenerDigito("00" + $('#cuenta_banco').val() + $('#cuenta_ofici').val()) == parseInt($('#cuenta_contr').val().charAt(0))) || !(obtenerDigito($('#cuenta_numer').val()) == parseInt($('#cuenta_contr').val().charAt(1)))){
				$.msg("NO VALIDA: Los digitos de control indican que el numero de cuenta es incorrecto. Deberia ser: " + obtenerDigito("00" + $('#cuenta_banco').val() + $('#cuenta_ofici').val()) + obtenerDigito($('#cuenta_numer').val()) );
				return false;
			}else{
				$.msg ("CCC correcto");
				return true;
			}
		}
	}
}

function muestraBanco(){
	banco = "";
	idBanco = $('#cuenta_banco').val();
	//$.msg(idBanco);
	idBanco = parseFloat(idBanco);
	banco = $("#dropdown_banco option[value='" + idBanco + "']").text();
	$("#spanBanco").text(banco);
}

function autoSaltaCampos(){
	$('#cliente_cuenta_iban').keyup(function() {
		if($('#cliente_cuenta_iban').val().length>3) {
			$('#cliente_cuenta_banco').focus();
		}
	});
	$('#cliente_cuenta_banco').keyup(function() {
		if($('#cliente_cuenta_banco').val().length>3) {
			$('#cliente_cuenta_ofici').focus();
			muestraBanco();
		}
	});
	$('#cliente_cuenta_ofici').keyup(function() {
		if($('#cliente_cuenta_ofici').val().length>3) {
			$('#cliente_cuenta_contr').focus();	
		}
	});
	$('#cliente_cuenta_contr').keyup(function() {
		if($('#cliente_cuenta_contr').val().length>1) {
			$('#cliente_cuenta_numer').focus();	
		}
	});
	$('#cliente_cuenta_numer').keyup(function() {
		if($('#cliente_cuenta_numer').val().length>9) {	
			$('#required_cuenta_bancox').focus();
			validar();
		}
	});
}

function entidadCliente(){
	//$.msg("Vas a insertar un cliente");
	if ($("#bot_cliente").hasClass('btActivado') == true){
		$("#bot_cliente").removeClass("btActivado");
		if ($("#bot_proveedor").hasClass('btActivado') == true){
			$("#breadcrumb_entidad").text("Proveedor");
			$("#sectionTipoProveedor").show();
			$("#sectionSectorCliente").hide();
		}else{
			$("#breadcrumb_entidad").text("Entidad");
			$("#divAutonomoEmpresa").hide();
			/*$("#bot_empresa").removeClass("btActivado");
			$("#bot_autonomo").removeClass("btActivado");*/
			$("#sectionTipoProveedor").hide();
			$("#sectionSectorCliente").hide();
			seleccionTipoEntidad();
		}
	}else{
		//$("#divAutonomoEmpresa").show();
		$("#bot_cliente").addClass("btActivado");
		if ($("#bot_proveedor").hasClass('btActivado') == true){
			$("#breadcrumb_entidad").text("Cliente/proveedor");
			$("#sectionTipoProveedor").show();
			$("#sectionSectorCliente").show();
		}else{
			$("#breadcrumb_entidad").text("Cliente");
			$("#sectionTipoProveedor").hide();
			$("#sectionSectorCliente").show();
		}
	}
}

function entidadProveedor(){
	//$.msg("Vas a insertar un proveedor");
	if ($("#bot_proveedor").hasClass('btActivado') == true){
		$("#bot_proveedor").removeClass("btActivado");
		if ($("#bot_cliente").hasClass('btActivado') == true){
			$("#breadcrumb_entidad").text("Cliente");
			$("#sectionTipoProveedor").hide();
			$("#sectionSectorCliente").show();
		}else{
			$("#breadcrumb_entidad").text("Entidad");
			$("#divAutonomoEmpresa").hide();
			/*$("#bot_empresa").removeClass("btActivado");
			$("#bot_autonomo").removeClass("btActivado");*/
			$("#sectionTipoProveedor").hide();
			$("#sectionSectorCliente").hide();
			seleccionTipoEntidad();
		}
	}else{
		//$("#divAutonomoEmpresa").show();
		$("#bot_proveedor").addClass("btActivado");
		if ($("#bot_cliente").hasClass('btActivado') == true){
			$("#breadcrumb_entidad").text("Cliente/proveedor");
			$("#sectionTipoProveedor").show();
			$("#sectionSectorCliente").show();
		}else{
			$("#breadcrumb_entidad").text("Proveedor");
			$("#sectionTipoProveedor").show();
			$("#sectionSectorCliente").hide();
		}
	}
}

function entidadAutonomo(){
	//$.msg("Vas a insertar un autónomo");
	$("#bot_autonomo").addClass("btActivado");
	$("#bot_empresa").removeClass("btActivado");
	seleccionTipoEntidad();
}

function entidadEmpresa(){
	//$.msg("Vas a insertar una empresa");
	$("#bot_empresa").addClass("btActivado");
	$("#bot_autonomo").removeClass("btActivado");
	seleccionTipoEntidad();
}

function seleccionTipoEntidad(){
	flag = false;
	if ($("#bot_autonomo").hasClass('btActivado') == true){
		/*$("#labelNombre").text("Nombre y apellidos");
		$("#text_nombre").attr("title", "Introduzca el nombre y los apellidos del cliente");
		$("#spanNombre").text("Introduzca el nombre y los apellidos del cliente");
		$("#labelApellidos").text("Nombre comercial");
		$("#spanApellidos").text("Introduzca el nombre comercial del cliente");
		$("#text_apellidos").attr("title", "Introduzca el nombre comercial del cliente");*/
		
		$("#labelApellidos").text("Nombre y apellidos");
		$("#text_apellidos").attr("title", "Introduzca el nombre y los apellidos del cliente");
		$("#spanApellidos").text("Introduzca el nombre y los apellidos del cliente");
		$("#labelNombre").text("Nombre comercial");
		$("#spanNombre").text("Introduzca el nombre comercial del cliente");
		$("#text_nombre").attr("title", "Introduzca el nombre comercial del cliente");
		
		$("#sectionRecarEqui").show();
		flag = true;
	}else
		if ($("#bot_empresa").hasClass('btActivado') == true){
			$("#labelNombre").text("Nombre comercial");
			$("#spanNombre").text("Introduzca el nombre comercial del cliente");
			$("#text_nombre").attr("title", "Introduzca el nombre comercial del cliente");
			$("#labelApellidos").text("Razon social");
			$("#spanApellidos").text("Introduzca la razon social del cliente");
			$("#text_apellidos").attr("title", "Introduzca la razon social del cliente");
			$("#sectionRecarEqui").hide();
			flag = true;
		}else{
			$("#labelNombre").text("Seleccione autonomo o empresa");
			$("#spanNombre").text("Seleccione autonomo o empresa");
			$("#text_nombre").attr("title", "Seleccione autonomo o empresa");
			$("#labelApellidos").text("Seleccione autonomo o empresa");
			$("#spanApellidos").text("Seleccione autonomo o empresa");
			$("#text_apellidos").attr("title", "Seleccione autonomo o empresa");
			$("#sectionRecarEqui").hide();
			flag = false;
		}
	if (flag){
		$("#labelNombre").attr("style" , "");
		$("#labelApellidos").attr("style" , "");
		$("#text_apellidos").removeAttr("readonly");
		$("#text_nombre").removeAttr("readonly");
		$("#spanApellidos").attr("style" , "");
		$("#spanNombre").attr("style" , "");
	}else{
		$("#labelNombre").attr("style" , "color:red;");
		$("#labelApellidos").attr("style" , "color:red;");
		$("#text_apellidos").removeAttr("readonly");
		$("#text_nombre").removeAttr("readonly");
		$("#spanApellidos").attr("style" , "color:red;");
		$("#spanNombre").attr("style" , "color:red;");
	}
}

/*############################ FUNCIONES DE ANTERIOR/SIGUIENTE ############################*/

function nextPrimerBreadcrumb(){
	/* Comprobaciones para pasar del primer breadcrumb*/
	/* Si divClienteProveedor esta visible, comprobamos que ha seleccionado cliente o proveedor o ambos */
	if ($("#divClienteProveedor").is(":visible")){
		if ($("#bot_proveedor").hasClass('btActivado') == true || $("#bot_cliente").hasClass('btActivado') == true){
			$("#divAutonomoEmpresa").show();
			$("#btPrev1").show();
			$("#divClienteProveedor").hide();
		}else
			$.msg("Debe seleccionar si la nueva entidad es un cliente, un proveedor o ambos para poder continuar");
	}else
		if ($("#divAutonomoEmpresa").is(":visible")){
			if ($("#bot_autonomo").hasClass('btActivado') == true || $("#bot_empresa").hasClass('btActivado') == true){
				$("#liDatosGenerales").show();
				$('#next1').trigger('click');
			}else
				$.msg("Debe seleccionar si la nueva entidad es un autonomo o una empresa");
		}
}

function prevPrimerBreadcrumb(){
	$("#divAutonomoEmpresa").hide();
	$("#btPrev1").hide();
	$("#divClienteProveedor").show();
}

function nextSegundoBreadcrumb(){
	$('#next2').trigger('click');
	$("#liContactos").show();
}

function prevSegundoBreadcrumb(){
	$('#prev2').trigger('click');
	$("#divAutonomoEmpresa").show();
	$("#divClienteProveedor").hide();
}

function nextTercerBreadcrumb(){
	hideFormularioTfno();
	hideFormularioEmail();
	$('#next3').trigger('click');
	$("#liDirecciones").show();
}

function prevTercerBreadcrumb(){
	$('#prev3').trigger('click');
}

function nextCuartoBreadcrumb(){
	hideFormularioDireccion();
	$('#next4').trigger('click');
	$("#liDatosBancarios").show();
}

function prevCuartoBreadcrumb(){
	$('#prev4').trigger('click');
}

function nextQuintoBreadcrumb(){
	$('#next5').trigger('click');
	$("#liOtros").show();
	$("#bot_insertar").show();
}

function prevQuintoBreadcrumb(){
	$('#prev5').trigger('click');
}

function prevSextoBreadcrumb(){
	$('#prev6').trigger('click');
}

/*############################ FIN FUNCIONES DE ANTERIOR/SIGUIENTE ############################*/

function seleccionBreadcrumbEntidad(){
	//ocultarBreadcrumbs();
	ocultarTablas();
	$("#divAutonomoEmpresa").hide();
	$("#divClienteProveedor").show();
}

function proveedorSeleccionado(){
	if ($("#dropdown_tipos").val() > 0){
		if ($("#tipoProve_" + $("#dropdown_tipos").val()).length == 0){
			var nuevaFila = document.createElement("tr");
				nuevaFila.setAttribute("id", "tipoProve_" + $("#dropdown_tipos").val());
				nuevaFila.setAttribute("class", "tiposProveedor");
				// Crear la fila
				nuevaFila.innerHTML =
					'<td>' +
						$("#dropdown_tipos :selected").text() +
					'</td>' +
					'<td>' +
						'<a href="javascript:quitarTipo(' + $("#dropdown_tipos").val() +');" title="Quitar tipo"><img src="img/disabled.gif" alt="Quitar tipo" title="Quitar tipo"/></a>' +
					'</td>';
				document.getElementById('tbodyTiposProveedor').appendChild(nuevaFila);
		}else{
			$.msg("Tipo de proveedor ya seleccionado");
		}
	}
}

function quitarTipo(idTipo){
	$("#tipoProve_" + idTipo).unbind();
	$("#tipoProve_" + idTipo).remove();
}