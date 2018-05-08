/*!
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.onReady(function(){

    // NOTE: This is an example showing simple state management. During development,
    // it is generally best to disable state management as dynamically-generated ids
    // can change across page loads, leading to unpredictable results.  The developer
    // should ensure that stable state ids are set for stateful components in real apps.    
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

    // sample static data for the store
	//holas
    var myData = registros;
	

    /**
     * Custom function used for column renderer
     * @param {Object} val
     */
    function change(val){
        if(val > 0){
            return '<span style="color:green;">' + val + '</span>';
        }else if(val < 0){
            return '<span style="color:red;">' + val + '</span>';
        }
        return val;
    }

    /**
     * Custom function used for column renderer
     * @param {Object} val
     */
    function pctChange(val){
        if(val > 0){
            return '<span style="color:green;">' + val + '%</span>';
        }else if(val < 0){
            return '<span style="color:red;">' + val + '%</span>';
        }
        return val;
    }

    // create the data store
    var store = new Ext.data.ArrayStore({
        fields: [
           {name: 'codigoEntrada'},
           //{name: 'fecha', type: 'date', dateFormat: 'n/j h:ia'},
           //{name: 'fecha', type: 'date', dateFormat: 'm-d-Y'},
		   {name: 'fecha', },
           {name: 'descripcion'},
           {name: 'unidades', type: 'float'},
           {name: 'operaciones'}

        ]
    });

    // manually load local data
    store.loadData(myData);

    // create the Grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
            {id:'codigoEntrada',header: 'Codigo Entrada', width: 20, sortable: true, dataIndex: 'codigoEntrada'},
			{header: 'Fecha Registro', width: 85, sortable: true, renderer: Ext.util.Format.dateRenderer('d/m/Y'), dataIndex: 'fecha'},
            {header: 'Descripci&oacute;n', width: 160, sortable: true, dataIndex: 'descripcion'},
			{header: 'Unidades', width: 75, sortable: true, renderer: change, dataIndex: 'unidades'},
			{header: 'Operaciones', width: 75, sortable: false, renderer: pctChange, dataIndex: 'operaciones'}
			//{header: 'Descripci&oacute;n', width: 75, sortable: true, renderer: 'usMoney', dataIndex: 'price'},
            //{header: 'Change', width: 75, sortable: true, renderer: change, dataIndex: 'change'},
            //{header: '% Change', width: 75, sortable: true, renderer: pctChange, dataIndex: 'pctChange'}

        ],
        stripeRows: true,
        autoExpandColumn: 'codigoEntrada',
        height: 350,
        width: 600,
        title: 'Array Grid',
        // config options for stateful behavior
        stateful: true,
        stateId: 'grid'        
    });
    
    // render the grid to the specified div in the page
    grid.render('grid-example');
});