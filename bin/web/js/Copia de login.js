
 Ext.onReady(function(){
    Ext.QuickTips.init();
		
	// Creamos la variable contenedor	 
    var login = new Ext.FormPanel({ 
        labelWidth:80,
        url:'Login.action',
		//baseCls: 'login_form',		
        frame:true, 
        title:'Validaci&oacute;n de Acceso', 
        defaultType:'textfield',
		monitorValid:true,
		waitMsgTarget: true,
        items:[
			{
                fieldLabel:'Usuario', 
                name:'login', 
                allowBlank:false ,
				blankText: 'El nombre es requerido. Ej: prueba'
            },{ 
                fieldLabel:'Contrase&ntilde;a', 
                name:'password', 
                inputType:'password', 
                allowBlank:false,
				blankText: 'La contrase&ntilde;a es requerida. Ej: prueba'
            }],  
        buttons:[{ 
            text:'Login',
            formBind: true,	 
            handler:function(){ 
                login.getForm().submit({ 
                    method:'POST', 
                    waitTitle: 'Cargando', 
                    waitMsg:'Enviando Datos...',
					//tenemos exito, vamos al dashboard
                    success:function(){ 
						//Ext.Msg.$.msg('Estado', 'Validaci&oacute;n Correcta', function(btn, text){
						//	if (btn == 'ok'){
								var redirect = 'pagEscritorio.jsp'; 
								window.location = redirect;
						//	}
						//});
                    },
 
                    failure:function(form, action){ 
						Ext.MessageBox.show({
							title: 'Error de Validaci&oacute;n',
							msg: 'El usuario o la contrase&ntilde;a no es correcto. ',
							buttons: Ext.MessageBox.OK,
							animEl: 'contenido',
							//fn: showResult,
							//icon: Ext.get('icons').dom.value
							icon: Ext.MessageBox.ERROR
						});
//						Ext.Msg.$.msg('Error de Validaci&oacute;n', 'El usuario o la contrase&ntilde;a no es correcto');
                        /*if(action.failureType == 'server'){ 
                            obj = Ext.util.JSON.decode(action.response.responseText); 
                            Ext.Msg.$.msg('Error de Validaci&oacute;n', obj.errors.reason); 
                        }else{ 
                            Ext.Msg.$.msg('Advertencia', 'No se puede conectar con el Servidor de autenticaci&oacute;n: ' + action.response.responseText); 
                        } */
                        login.getForm().reset(); 
                    } 
                }); 
            } 
        }] 
    });
  
	// Ventana principal
	var win = new Ext.Window({
        layout:'fit',
        width:270,
        height:150,
        closable: false,
        resizable: false,
        plain: true,
        border: false,
        items: [			
			login
		]
	});

	var p = new Ext.Panel({
        title: 'My Panel',
        collapsible:true,
        renderTo: 'contenido',
		bodyStyle: "background-image:url(ext/resources/images/soccer_field.jpg) !important",
        height:768,                    
        width:1024,
        html: Ext.example.bogusMarkup
    });
	
	win.show();
});