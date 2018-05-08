var canttotal = new Array();

var eltosActivos = new Array();
var cantidadExistente = new Array();
var maximo = document.getElementById("maximo").innerHTML;
//var quedan = parseFloat(maximo);

cantidadexistente = getElementsByClassName( 'cantidadexistente', document.body );

function doCalcAndSubmit(cadena,componente) {
	//obtengo el valor que quiero restar
	var temp = parseFloat(document.formulario.cantidad[componente-1].value);
	var disponemos = parseFloat(cantidadexistente[componente-1].innerHTML);
	//$.msg('disponemos de '+disponemos);
	//si es menor que cero lo pongo a cero
	if (temp < 0 ) {		
		//$.msg("<0 "+temp);
		document.formulario.cantidad[componente-1].value=0;
		document.formulario.resta[componente-1].value=disponemos;
	} else if (temp > disponemos) {
		//$.msg(temp+" > Max : "+disponemos);
		// si el valor excede el rango, le pongo el máximo en stock
		document.formulario.cantidad[componente-1].value=disponemos;
		document.formulario.resta[componente-1].value=0;
	} else { 
		//$.msg("en rango "+temp);
		//si es un valor dentro del rango, lo actualizo
		document.formulario.resta[componente-1].value=disponemos-temp;
	}
	//$.msg('Quedan '+(maximo-sumatorio()));
}

function inserta() {
	if (valida()==true) {
		//$.msg('validado');
		document.formulario.submit();
	} else $.msg('No has indicado cantidad suficiente (son '+maximo+' uds.) Tienes '+sumatorio());
}

function valida() {
	var grupo = document.getElementById("formulario").listaMateriaPrima;
	if (checkvalidate(grupo)) {
		var suma = sumatorio();
		//if((suma>0)&&(suma<=maximo)){
		if(suma==maximo) {
			//$.msg('cantidad correcta??'); 
			return true;
		} else { 
			//$.msg('La cantidad a envasar excede los limites permitidos'); 
			return false; 
		}		
	} else {
		$.msg("Ninguna materia prima está marcada");
		return false;
	} 
}

function sumatorio() {
	var suma = 0;
	//$.msg('sumatorio');
	canttotal = getElementsByClassName( 'cantidad', document.body );
//	$.msg('zzzzz'+eltosActivos.length);
	for (i = 0; i<eltosActivos.length; i++) {
		//Si el formulario esta vacio se llena con 0
		if ((document.formulario.cantidad[i].value=="")||(document.formulario.cantidad[i].value<0)) {
			document.formulario.cantidad[i].value=0;
		}
		//$.msg('ZZZZ'+eltosActivos[i]);
		if (eltosActivos[i]==1) {
			suma+=parseFloat(document.formulario.cantidad[i].value);
			//$.msg('Sumatorio: '+suma);
		}
	}
	//$.msg('MAXIMO: '+);
	//$.msg('SUMATORIO: '+suma);
	return suma;
}
 
function checkvalidate(checks) {
	var retorno = false;
  for (i = 0; lcheck = checks[i]; i++) {
    if (lcheck.checked) {
			eltosActivos[i]=1;
			retorno=true;
    } else eltosActivos[i]=0;
  }
  return retorno;
}

function getElementsByClassName( strClassName, obj ) {
  var ar = arguments[2] || new Array();
  var re = new RegExp("\\b" + strClassName + "\\b", "g");

  if ( re.test(obj.className) ) {
    ar.push( obj );
  }
  for ( var i = 0; i < obj.childNodes.length; i++ )
    getElementsByClassName( strClassName, obj.childNodes[i], ar );  
  return ar;
}

