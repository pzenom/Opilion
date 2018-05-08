//animate the opening of the branch (span.grower jQueryElement)
function openBranch(jQueryElement, noAnimation) {
		jQueryElement.addClass('OPEN').removeClass('CLOSE');
		if(noAnimation)
			jQueryElement.parent().find('ul:first').show();
		else
			jQueryElement.parent().find('ul:first').slideDown();
}
//animate the closing of the branch (span.grower jQueryElement)
function closeBranch(jQueryElement, noAnimation) {
	jQueryElement.addClass('CLOSE').removeClass('OPEN');
	if(noAnimation)
		jQueryElement.parent().find('ul:first').hide();
	else
		jQueryElement.parent().find('ul:first').slideUp();
}

//animate the closing or opening of the branch (ul jQueryElement)
function toggleBranch(jQueryElement, noAnimation) {
	if(jQueryElement.hasClass('OPEN'))
		closeBranch(jQueryElement, noAnimation);
	else
		openBranch(jQueryElement, noAnimation);
}

//when the page is loaded...
$(document).ready(function () {
	//to do not execute this script as much as it's called...
	//if(!$('ul.tree.dhtml').hasClass('dynamized'))
	if($('#nav').hasClass('dynamized')){
		//$.msg("hola");
		//add growers to each ul.tree elements
		$('nav ul li ul').prev().before("<span class='grower OPEN'> </span>");
		
		//dynamically add the '.last' class on each last item of a branch
		$('ul li ul li:last-child, ul li:last-child').addClass('last');
		
		//collapse every expanded branch
		$('ul li span.grower.OPEN').addClass('CLOSE').removeClass('OPEN').parent().find('ul:first').hide();
		//$('ul').show();
		
		//open the tree for the selected branch
			$('ul .selected').parents().each( function() {
				if ($(this).is('ul'))
					toggleBranch($(this).prev().prev(), true);
			});
			toggleBranch( $('ul .selected').prev(), true);
		
		//add a fonction on clicks on growers
		$('ul span.grower').click(function(){
			toggleBranch($(this));
		});
		//mark this 'ul.tree' elements as already 'dynamized'
		$('ul').addClass('dynamized');

		$('ul').removeClass('dhtml');
	} else $.msg("no lo leo");
	
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