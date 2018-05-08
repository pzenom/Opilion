$(document).ready(function() {
	
	//for Caching
	var $contenido = $('#contenido');
	
		/*----------------------------------------------------------------------*/
		/* preload images
		/*----------------------------------------------------------------------*/
		
		//$.preload();
		
		/*----------------------------------------------------------------------*/
		/* Widgets
		/*----------------------------------------------------------------------*/
		
		$contenido.find('div.widgets').wl_Widget();
		//$.msg($contenido.find('div.widgets').length);
		
		/*----------------------------------------------------------------------*/
		/* All Form Plugins
		/*----------------------------------------------------------------------*/
		
		//Integers and decimals
		$contenido.find('input[type=number].integer').wl_Number();
		$contenido.find('input[type=number].decimal').wl_Number({decimals:2,step:0.5});
		
		//Date and Time fields
		$contenido.find('input.date, div.date').wl_Date();
		//$contenido.find('input.time').wl_Time();
		
		//Autocompletes (source is required)
		/*$contenido.find('input.autocomplete').wl_Autocomplete({
			source: ["ActionScript","AppleScript","Asp","BASIC","C","C++","Clojure","COBOL","ColdFusion","Erlang","Fortran","Groovy","Haskell","Java","JavaScript","Lisp","Perl","PHP","Python","Ruby","Scala","Scheme"]
		});*/
		
		//Elastic textareas (autogrow)
		$contenido.find('textarea[data-autogrow]').elastic();
		//WYSIWYG Editor
		//$contenido.find('textarea.html').wl_Editor();
		
		//Validation
		$contenido.find('input[data-regex]').wl_Valid();
		$contenido.find('input[type=email]').wl_Mail();
		$contenido.find('input[type=url]').wl_URL();

		//File Upload
		$contenido.find('input[type=file]').wl_File();

		//Password and Color
		//$contenido.find('input[type=password]').wl_Password();
		//$contenido.find('input.color').wl_Color();
		
		//Sliders
		$contenido.find('div.slider').wl_Slider();
		
		//Multiselects
		//$contenido.find('select[multiple]').wl_Multiselect();
		
		//The Form itself
		$contenido.find('form').wl_Form();
		
		/*----------------------------------------------------------------------*/
		/* Alert boxes
		/*----------------------------------------------------------------------*/
		
		$contenido.find('div.alert').wl_Alert();
		
		/*----------------------------------------------------------------------*/
		/* Breadcrumb
		/*----------------------------------------------------------------------*/
		
		$contenido.find('ul.breadcrumb').wl_Breadcrumb();
		
		/*----------------------------------------------------------------------*/
		/* datatable plugin
		/*----------------------------------------------------------------------*/
		
		$contenido.find("table.datatable").dataTable();
		
		/*----------------------------------------------------------------------*/
		/* uniform plugin
		/*----------------------------------------------------------------------*/
		
		$("select, textarea, input").not('input[type=submit], textarea.html, select[multiple]').uniform();
		
		/*----------------------------------------------------------------------*/
		/* Charts
		/*----------------------------------------------------------------------*/

		/*$contenido.find('table.chart').wl_Chart({
			onClick:function(value, legend, label, id){
				$.msg("value is "+value+" from "+legend+" at "+label+" ("+id+")",{header:'Custom Callback'});
			}
		});*/
		
		/*----------------------------------------------------------------------*/
		/* Fileexplorer
		/*----------------------------------------------------------------------*/

		//$contenido.find('div.fileexplorer').wl_Fileexplorer();
		
		
		/*----------------------------------------------------------------------*/
		/* Calendar (read http://arshaw.com/fullcalendar/docs/ for more info!)
		/*----------------------------------------------------------------------*/
		
		
		
		$contenido.find('div.calendar').wl_Calendar({
			eventSources: [
					{
						url: 'http://www.google.com/calendar/feeds/usa__en%40holiday.calendar.google.com/public/basic'
					},{
						events: [ // put the array in the `events` property
							{
								title  : 'Fixed Event',
								start  : '2011-08-01'
							},
							{
								title  : 'long fixed Event',
								start  : '2011-08-06',
								end    : '2011-08-14'
							}
						],
						color: '#f0a8a8',     // an option!
						textColor: '#ffffff' // an option!
					},{
						events: [ // put the array in the `events` property
							{
								title  : 'Editable',
								start  : '2011-08-09 12:30:00'
							}
						],
						editable:true,
						color: '#a2e8a2',     // an option!
						textColor: '#ffffff' // an option!
					}		
					// any other event sources...
			
				]
			});
		
		/*----------------------------------------------------------------------*/
		/* Gallery
		/*----------------------------------------------------------------------*/
		
		//$contenido.find('ul.gallery').wl_Gallery();
		
		
		/*----------------------------------------------------------------------*/
		/* Tipsy Tooltip
		/*----------------------------------------------------------------------*/
		
		
		$contenido.find('input[title]').tipsy({
			gravity: function(){return ($(this).data('tooltip-gravity') || config.tooltip.gravity); },
			fade: config.tooltip.fade,
			opacity: config.tooltip.opacity,
			color: config.tooltip.color,
			offset: config.tooltip.offset
		});
		
		
		/*----------------------------------------------------------------------*/
		/* Accordions
		/*----------------------------------------------------------------------*/
		
		$contenido.find('div.accordion').accordion({
				collapsible:true,
				autoHeight:false
		});
		
		/*----------------------------------------------------------------------*/
		/* Tabs
		/*----------------------------------------------------------------------*/
		
		$contenido.find('div.tab').tabs({
				fx: {
					opacity: 'toggle',
					duration: 'fast'
				}
		});

		/*----------------------------------------------------------------------*/
		/* Navigation Stuff
		/*----------------------------------------------------------------------*/
		
		//Top Pageoptions
		$('#wl_config').click(function(){
			var $pageoptions = $('#pageoptions');
			if($pageoptions.height() < 200){
				$("#mensajeResultadoConfiguracion").hide();
				$pageoptions.animate({'height':200});
				$(this).addClass('active');
			}else{
				$pageoptions.animate({'height':20});
				$(this).removeClass('active');
			}
			return false;
		});
		
		//Header navigation for smaller screens
		var $headernav = $('ul#headernav');
		
		$headernav.bind('click',function(){
			//if(window.innerWidth > 800) return false;
			var ul = $headernav.find('ul').eq(0);
			(ul.is(':hidden')) ? ul.addClass('shown') : ul.removeClass('shown');
		});
		
		$headernav.find('ul > li').bind('click',function(event){
			event.stopPropagation();
			var children = $(this).children('ul');
			
			if(children.length){
				(children.is(':hidden')) ? children.addClass('shown') : children.removeClass('shown');
			}
		});
		
		//Search Field Stuff
		var $searchform = $('#searchform'),
			$searchfield = $('#search');
		
		$searchfield
			.bind('focus.wl',function(){
		   		$searchfield.parent().animate({width: '150px'},100).select();
			})
			.bind('blur.wl',function(){
	   			$searchfield.parent().animate({width: '90px'},100);
			});
			
		$searchform
			.bind('submit.wl',function(){
				//do something on submit
				var query = $searchfield.val();
			});
			
		//Main Navigation
		var $nav = $('#nav');
			
		/* SCRIPT DEL MENU MODIFICADO */
		$nav.delegate('li','click.wl', function(event){
			var _this = $(this),
				_parent = _this.parent(),
				a = _parent.find('a');
			//_parent.find('ul').slideUp('fast');
			a.removeClass('active');
			//_this.find('ul:hidden').slideDown('fast');
			_this.find('a').eq(0).addClass('active');
			event.stopPropagation();
		});
		
		/* messages */
		/*$('#message_delay').click(function(){
			$.msg("This stays exactly 10 seconds",{live:10000});
		});*/
		
		/*----------------------------------------------------------------------*/
		/* Helpers
		/*----------------------------------------------------------------------*/
		
		//placholder in inputs is not implemented well in all browsers, so we need to trick this
		$("[placeholder]").bind('focus.placeholder',function() {
			var el = $(this);
			if (el.val() == el.attr("placeholder") && !el.data('uservalue')) {
				el.val("");
				el.removeClass("placeholder");
			}
		}).bind('blur.placeholder',function() {
			var el = $(this);
			if (el.val() == "" || el.val() == el.attr("placeholder") && !el.data('uservalue')) {
				el.addClass("placeholder");
				el.val(el.attr("placeholder"));
				el.data("uservalue",false);
			}else{

			}
		}).bind('keyup.placeholder',function() {
			var el = $(this);
			if (el.val() == "") {
				el.data("uservalue",false);
			}else{
				el.data("uservalue",true);
			}
		}).trigger('blur.placeholder');
		
		/*----------------------------------------------------------------------*/
		/* Helps to make current section active in the Mainbar
		/*----------------------------------------------------------------------*/
		/*$.msg("antes...");
			var loc = location.pathname.replace(/\/([^.]+)\//g,'');
			var current = $nav.find('a[href="'+loc+'"]');
			if(current.parent().parent().is('#nav')){
				current.addClass('active');
			}else{
				current.parent().parent().parent().find('a').eq(0).addClass('active').next().show();
				current.addClass('active');
			}
		$.msg("despues");*/
		/*----------------------------------------------------------------------*/
			/*----------------------------------------------------------------------*/
		/* Callback for Slider
		/*----------------------------------------------------------------------*/
			$contenido.find('div.slider#slider_callback').wl_Slider({
				//$.msg("hola");
				onSlide:function(value){
					//$.msg("moviendo");
					$('#slider_callback_bar').width(value+'%').text(value);
				}													  
			});
	selects = $('.selector').get();
	//alert(selects.length);
	for (i = 0; i < selects.length; i++){
		select = selects[i];
		select.setAttribute('id', 'selector_' + i);
		hijo = $('#selector_' + i).children().get(1);
		hijo.setAttribute('onchange', hijo.getAttribute('onchange') + 'javascript:ajustarSelect("' + i + '");');
	}
});

function ajustarSelect(i){
	hijo = $('#selector_' + i).children().get(0);
	hijo.setAttribute('id', 'textoSelect_' + i);
	texto = $('#textoSelect_' + i).text().trim();
	select = $('#selector_' + i).children().get(1);
	select.setAttribute('title', texto);
}