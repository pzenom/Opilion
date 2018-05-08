/*!
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.onReady(function(){

    Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';

    var bd = Ext.getBody();

    /*
     * ================  Form 3  =======================
     */
    bd.createChild({tag: 'h2', html: 'Form 3 - A little more complex'});


    var top = new Ext.FormPanel({
        labelAlign: 'top',
        frame:true,
        title: 'Resgistro de Envase',
        bodyStyle:'padding:5px 5px 0',
        width: 600,
        items: [{
            layout:'column',
            items:[{
                columnWidth:.5,
                layout: 'form',
                items: [{
                    xtype:'textfield',
                    fieldLabel: 'idMaterial',
                    name: 'material',
                    anchor:'95%'
                }, {
                    xtype:'textfield',
                    fieldLabel: 'Stock',
                    name: 'stock',
                    anchor:'95%'
                }, {
				    xtype:'textfield',
                    fieldLabel: 'Nombre',
                    name: 'nombre',
                    anchor:'95%'
				}]
            },{
                columnWidth:.5,
                layout: 'form',
                items: [{
                    xtype:'textfield',
                    fieldLabel: 'Peso',
                    name: 'peso',
                    anchor:'95%'
                },{
                    xtype:'textfield',
                    fieldLabel: 'Dimensiones [Ej: 16 x 2 x 15]',
                    name: 'dimensiones',
                    anchor:'95%'
                }]
            }]
        },{
            xtype:'htmleditor',
            id:'descripcion',
            fieldLabel:'Descripci&oacute;n',
            height:200,
            anchor:'98%'
        }],

        buttons: [{
            text: 'Guardar'
        },{
            text: 'Cancelar'
        }]
    });

    top.render(document.body);


});