Ext.ns('opilion');
Ext.onReady(function(){
	
	Ext.QuickTips.init();

	var detailEl;
	
	/*************************************************/
	/*            PANEL DE PESTAÑAS CENTRAL          */
	/*************************************************/
	var contenidoPanel = {
		id: 'contenido-panel',
		region: 'center',
		layout: 'card',
		margins: '2 5 5 0',
		activeItem: 0,
		border: false,
		items: [
			//from base.js
			inicio//, gestion
		]
	};
	
	var tabPanel = new Ext.TabPanel({
		//renderTo: Ext.getBody(),
		//renderTo: tabs,
		region: 'center',
		deferredRender:false,
		autoScroll: true, 
		margins:'0 4 4 0',
		activeTab: 0,
		items: [
			inicio
		]
	});
    
	/*************************************************/
	/*               PANEL DE AYUDA                  */
	/*************************************************/
	var detailsPanel = {
		id: 'details-panel',
        title: 'Ayuda',
		iconCls: 'ayu',
        region: 'center',
		margins: '0 0 5 0',
        bodyStyle: 'padding-bottom:15px;background:#eee;',
		autoScroll: true,
		html: '<p class="details-info">La ayuda es autom&aacute;tica cuando se selecciona una opci&oacute;n del panel principal</p>'
    };
	
	/*************************************************/
	/*             PANEL DE ALERTAS                  */
	/*************************************************/
	var alertsPanel = {
		collapsible:true,
		id: 'alerts-panel',
        title: 'Panel de Avisos',
		iconCls: 'avi',
        region: 'south',
        bodyStyle: 'padding: 5px;background:#eee;',
		autoScroll: true,
		html: '<p class="details-info">Guacala, aqu&iacute; deber&iacute;an de ir los avisos</p>'
    };
	
	/*************************************************/
	/*       DEFINICION DE PANELES DE AREAS          */
	/*************************************************/
	/* GESTION */
	var treePanelGestion = new Ext.tree.TreePanel({
    	id: 'gestion-panel',
    	title: 'Gestion',
		iconCls: 'ges',
        region:'north',
        split: true,
        //height: 300,
       // minSize: 150,
        autoScroll: true,
        
        // tree-specific configs:
        rootVisible: false,
        lines: false,
        singleExpand: true,
        useArrows: true,
        
        loader: new Ext.tree.TreeLoader({
            dataUrl:'js/dashboard/tree-data-gestion.json'
        }),
        
        root: new Ext.tree.AsyncTreeNode()
    });
	
	function addTab(tabTitle, targetUrl){
        tabPanel.add({
			title: tabTitle,
			iconCls: 'tabs',
			autoLoad: {url: targetUrl, callback: this.initSearch, scope: this},
			closable:true
		}).show();
    };
	
	treePanelGestion.on('click', function(n){
		/*******************************************************************/
		//añado una pestana nueva
		var sn = this.selModel.selNode || {};//nodo seleccionado previamente, la primera vez es null
		if(n.leaf && (n.id != sn.id )) {
			//addTab(n.id, n.text, n.id+'/'+n.id+'.jsp');
			
			//addTab(n.id, n.text, 'FiltroEnvases.action');			
			/*if (n.id == 'clientes') {
				Ext.Msg.$.msg('Click:', n.id+'/Filtro'+n.text+'.action');
				//addTab(n.id, n.text, 'FiltroEnvases.action');
			}*/
			addTab(n.id, n.text, 'Filtro'+n.text+'.action');			
		}

		/*******************************************************************/
   /* 	var sn = this.selModel.selNode || {}; // selNode is null on initial selection
    	if(n.leaf && n.id != sn.id){  // ignore clicks on folders and currently selected node 
    		Ext.getCmp('contenido-panel').layout.setActiveItem(n.id + '-panel');
    		if(!detailEl){
    			var bd = Ext.getCmp('details-panel').body;
    			bd.update('').setStyle('background','#fff');
    			detailEl = bd.createChild(); //create default empty div
				Ext.Msg.$.msg('Click:', 'Has hecho un click en '+n+ ' y que no es donde estabas' );
    		}
    		detailEl.hide().update(Ext.getDom(n.id+'-details').innerHTML).slideIn('l', {stopFx:true,duration:.2});
    	}
			/*******************************************************************/
			//Ext.Msg.$.msg('Click:', 'Has hecho un click en '+n.id+' | '+sn.id );
			//addTab(n.text,'hola.html');
			/*******************************************************************/
    });
	
	/* PRODUCCION */
	var treePanelProduccion = new Ext.tree.TreePanel({
    	id: 'produccion-panel',
    	title: 'Produccion',
		iconCls: 'pro',
        region:'north',
        split: true,
        //height: 300,
        //minSize: 150,
        autoScroll: true,
        
        // tree-specific configs:
        rootVisible: false,
        lines: false,
        singleExpand: true,
        useArrows: true,
        
        loader: new Ext.tree.TreeLoader({
            dataUrl:'js/dashboard/tree-data-produccion.json'
        }),
        
        root: new Ext.tree.AsyncTreeNode()
    });
	
	treePanelProduccion.on('click', function(n){
    	var sn = this.selModel.selNode || {}; // selNode is null on initial selection
    	if(n.leaf && n.id != sn.id){  // ignore clicks on folders and currently selected node 
    		Ext.getCmp('contenido-panel').layout.setActiveItem(n.id + '-panel');
    		if(!detailEl){
    			var bd = Ext.getCmp('details-panel').body;
    			bd.update('').setStyle('background','#fff');
    			detailEl = bd.createChild(); //create default empty div
    		}
    		detailEl.hide().update(Ext.getDom(n.id+'-details').innerHTML).slideIn('l', {stopFx:true,duration:.2});
    	}		
    });
	
	/*  ALBARANES */
	var treePanelAlbaranes = new Ext.tree.TreePanel({
    	id: 'albaranes-panel',
    	title: 'Albaranes',
		iconCls: 'alb',
        region:'north',		
        split: true,
        //height: 300,
        //minSize: 150,
        autoScroll: true,
        
        // tree-specific configs:
        rootVisible: false,
        lines: false,
        singleExpand: true,
        useArrows: true,
        
        loader: new Ext.tree.TreeLoader({
            dataUrl:'js/dashboard/tree-data-albaranes.json'
        }),
        
        root: new Ext.tree.AsyncTreeNode()
    });

	treePanelAlbaranes.on('click', function(n){
    	var sn = this.selModel.selNode || {}; // selNode is null on initial selection
    	if(n.leaf && n.id != sn.id){  // ignore clicks on folders and currently selected node 
    		Ext.getCmp('contenido-panel').layout.setActiveItem(n.id + '-panel');
    		if(!detailEl){
    			var bd = Ext.getCmp('details-panel').body;
    			bd.update('').setStyle('background','#fff');
    			detailEl = bd.createChild(); //create default empty div
    		}
    		detailEl.hide().update(Ext.getDom(n.id+'-details').innerHTML).slideIn('l', {stopFx:true,duration:.2});
    	}		
    });
	
	/* FACTURACION */
	var treePanelFacturacion = new Ext.tree.TreePanel({
    	id: 'facturacion-panel',
    	title: 'Facturacion',
		iconCls: 'fac',
        region:'north',
        split: true,
        //height: 300,
        //minSize: 150,
        autoScroll: true,
        
        // tree-specific configs:
        rootVisible: false,
        lines: false,
        singleExpand: true,
        useArrows: true,
        
        loader: new Ext.tree.TreeLoader({
            dataUrl:'js/dashboard/tree-data-facturacion.json'
        }),
        
        root: new Ext.tree.AsyncTreeNode()
    });
	
	treePanelFacturacion.on('click', function(n){
    	var sn = this.selModel.selNode || {}; // selNode is null on initial selection
    	if(n.leaf && n.id != sn.id){  // ignore clicks on folders and currently selected node 
    		Ext.getCmp('contenido-panel').layout.setActiveItem(n.id + '-panel');
    		if(!detailEl){
    			var bd = Ext.getCmp('details-panel').body;
    			bd.update('').setStyle('background','#fff');
    			detailEl = bd.createChild(); //create default empty div
    		}
    		detailEl.hide().update(Ext.getDom(n.id+'-details').innerHTML).slideIn('l', {stopFx:true,duration:.2});
    	}		
    });
	
	/* DOCUMENTACION */	
	var treePanelDocumentacion = new Ext.tree.TreePanel({
    	id: 'documentacion-panel',
    	title: 'Documentacion',
		iconCls: 'doc',
        region:'north',
        split: true,
        //height: 300,
        //minSize: 150,
        autoScroll: true,
        
        // tree-specific configs:
        rootVisible: false,
        lines: false,
        singleExpand: true,
        useArrows: true,
        
        loader: new Ext.tree.TreeLoader({
            dataUrl:'js/dashboard/tree-data-documentacion.json'
        }),
        
        root: new Ext.tree.AsyncTreeNode()
    });
	
	treePanelDocumentacion.on('click', function(n){
    	var sn = this.selModel.selNode || {}; // selNode is null on initial selection
    	if(n.leaf && n.id != sn.id){  // ignore clicks on folders and currently selected node 
    		Ext.getCmp('contenido-panel').layout.setActiveItem(n.id + '-panel');
    		if(!detailEl){
    			var bd = Ext.getCmp('details-panel').body;
    			bd.update('').setStyle('background','#fff');
    			detailEl = bd.createChild(); //create default empty div
    		}
    		detailEl.hide().update(Ext.getDom(n.id+'-details').innerHTML).slideIn('l', {stopFx:true,duration:.2});
    	}		
    });	

	/*************************************************/
	/*       NAVEGACION DE PANELES DE AREAS          */
	/*************************************************/
	var navegacion2 = {
		title: 'Menu Navegacion Principal',
		iconCls: 'nav2',
		//collapsible:true, //esto permite que se colapse
	    id: 'layout-browser2',
	    region:'north',
	    border: false,
	    split:true,
		margins: '0 0 0 0',
	    //width: 250,
		height: 300,
		//minHeight: 200,
	    //minSize: 100,
	    //maxSize: 500,
		layout: {
			type: 'accordion',
			animate: true
		},
		items: [ 
			treePanelGestion,
			treePanelProduccion,
			treePanelAlbaranes,
			treePanelFacturacion,
			treePanelDocumentacion
			]
	};
		
	/*************************************************/
	/*             NAVEGACION PRINCIPAL              */
	/*************************************************/
	var navegacion = {
		layout: 'border',
		title: 'Menu Opilion',
		iconCls: 'nav',
		collapsible:true, //esto permite que se colapse
	    id: 'layout-browser',
	    region:'west',
	    border: false,
	    split:true,
		margins: '2 0 5 3',
	    width: 250,
	    minSize: 100,
	    maxSize: 500,
		/*layout: {
			type: 'accordion',
			animate: true
		},*/
		items: [
			navegacion2, 
			detailsPanel, 
			alertsPanel
			]
	};
	
	/*************************************************/
	/*               VENTANA  PRINCIPAL              */
	/*************************************************/
	new Ext.Viewport({
		layout: 'border',
		title: 'ERP - Tierrina Vaqueira',
		items: [{
			//encabezado
			xtype: 'box',
			region: 'north',
			applyTo: 'header',
			height: 31
		},
			navegacion, //barra de la izquierda
			tabPanel,
			//contenidoPanel // panel central de datos
		],
        renderTo: Ext.getBody()
    });
	/***************************************************/
	function addTab(id, tabTitle, targetUrl){
		tabPanel.add({
			id: id,
			title: tabTitle,
			autoScroll: true,
			//layout: 'border',
			//iconCls: 'tabs',
			/*items: [{				
                id:'clientesx',
                baseCls:'x-plain',
                renderTo: Ext.getBody(),
                layout:'table',
                layoutConfig: {columns:3},
                // applied to child components
                defaults: {frame:true, width:200, height: 200},
                items:[{
                    title:'Item 1'
                },{
                    title:'Item 2'
                },{
                    title:'Item 3'
                },{
                    title:'Item 4',
                    width:410,
                    colspan:2
                },{
                    title:'Item 5'
                },{
                    title:'Item 6'
                },{
                    title:'Item 7',
                    width:410,
                    colspan:2
                },{
                    title:'Item 8'
                }]
            }],*/
			layout: 'fit', 
			//autoLoad: {url: targetUrl, callback: this.initSearch, scope: this},
			//autoLoad: {url: targetUrl, scripts: true},//, callback: this.initSearch, scope: this},
			//autoLoad: {url: 'FiltroEnvases.action', scripts: true},
			autoLoad: {url: targetUrl, scripts: true},
			closable:true
		}).show();
    };
	/***************************************************/
	/* FIN */
});
