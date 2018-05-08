Ext.ns('opilion');
Ext.onReady(function(){
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
			autoLoad: {url: 'FiltroEnvases.action', scripts: true},//, callback: this.initSearch, scope: this},
			closable:true
		}).show();
    };
/***************************************************/
});