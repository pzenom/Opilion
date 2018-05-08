colorea();
function colorea() {
	var nodos = mydocumentgetElementsByClassName("colorea");
	//$.msg(nodos.length);
	for (var i=0, l=nodos.length; i<l; i++) {
		nodos[i].className ="hola";
		//$.msg(nodos[i].innerHTML);
		var valor = parseFloat(nodos[i].innerHTML);
		if (valor >= 13.5) {
			//$.msg('Mala Calidad');
			nodos[i].className = "tabrojo";
		} else if (valor < 13.5 && valor >= 3.5) {
			//$.msg('Calidad aceptable');
			nodos[i].className = "tabamarillo";
		} else if (valor < 3.5 && valor >= -6.5) {
			//$.msg('Muy Buena Calidad');
			nodos[i].className = "tabverde";
		} else if (valor < -6.5 && valor >= -16.5) {
			//$.msg('Excelente Calidad');
			nodos[i].className = "tabazul";
		} else $.msg('Valor en rango no definido');
		//$.msg(valor);
	}
}

function mydocumentgetElementsByClassName(what) {
	var all=document.getElementsByTagName('*')
	var expression=new RegExp('\\b'+what+'\\b','i')
	var result=[]
	for (var i=0,l=all.length;i<l;i++) if (expression.test(all[i].className)) result.push(all[i])
	return result
}

function detector() {
	var f = document.getElementById('formulario');
	var error=0;
	for(i=0;i<f.varIG.length;i++) {
		if( f.varIG[i].checked) {
			error++;
			//$.msg('Has seleccionado IG: '+ i);
		}
		if( f.varSP[i].checked) {
			//$.msg('Has seleccionado SP:'+ i);
			error++;
		}
		if( f.varDP[i].checked) {
			//$.msg('Has seleccionado DP:'+ i);
			error++;
		}
		if( f.varDA[i].checked) {
			//$.msg('Has seleccionado DA:'+ i);
			error++;
		}
		if( f.varM[i].checked) {
			//$.msg('Has seleccionado M:'+ i);
			error++;
		}
	}
	if(error==5) {
		//$.msg('Todo en orden');
		if (confirm('¿Son las opciones correctas?')) {
			f.submit();
		}
	} else $.msg('No has seleccionado todas las opciones necesarias para calcular');
	//$.msg('Están seleccionados '+error);
}

function validaradio(formulario,grupo){
	for(i=0;i<formulario.grupo.length;i++) {
		if( formulario.grupo[i].checked) {
			//$.msg('Has seleccionado '+ i);
			break;
		}
	}
}
function submitform() { 
	document.forms["formulario"].submit();
}