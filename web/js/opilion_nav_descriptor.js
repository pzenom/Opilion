$(document).ready(function () {
	//preparamos el menu extraño
	$('ul.botonera_descrita li ul').css({'position' : 'absolute', 'width' : '180px'})
	//al estar sobre un boton
	$('ul.botonera_descrita li button').mouseenter(function() {
		//limpiamos los botones
		$('ul.botonera_descrita li button').removeClass("corporate");
		$('ul.botonera_descrita li ul').slideUp();
		//$(this).css({'background-color' : '#00A9A9'});
		//pintamos el color y desplegamos si coincide
		$(this).addClass("corporate");
		if($(this).next().is('ul')) {
			$(this).next().slideDown();			
		}		
	});/*.mouseleave(function() {
		if($(this).next().is('ul')) {
			$(this).next().slideUp();			
		};
	});*/
	//si salimos de la lista, limpiamos todo
	$('ul.botonera_descrita').mouseleave(function() {
		$('ul.botonera_descrita li button').removeClass("corporate");
		$('ul.botonera_descrita li ul').slideUp();
	});
	//pintamos un poco las li on hover
	/*$('ul.botonera_descrita li ul li').hover( function(){
		$(this).css({'background':'#BADA55 !important','border':'solid 2px red'});
		//$.msg("aqui");
	});*/
});