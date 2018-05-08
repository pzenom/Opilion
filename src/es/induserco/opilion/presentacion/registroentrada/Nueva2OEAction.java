package es.induserco.opilion.presentacion.registroentrada;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroOrden;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class Nueva2OEAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private RegistroOrden entry = new RegistroOrden();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Registro Casi listo Orden Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return entry;
	}

	public String execute() throws Exception {
		try {
			//Compruebo el usuario a esa seccion
			Usuario us = (Usuario) request.getSession().getAttribute("usuario");	
			//Colocamos las listas de datos en la request.
			request.getSession().setAttribute("fecharegistro",(new GestionRegistroEntradaHelper()).getFechaRegistro());
			request.getSession().setAttribute("fechacaducidad",(new GestionRegistroEntradaHelper()).getFechaCaducidad());		
			request.getSession().setAttribute("codigoorden",(new GestionRegistroEntradaHelper()).getCodigoOrden());		
			request.getSession().setAttribute("listaproveedores",(new GestionRegistroEntradaHelper()).getProveedores());
			request.getSession().setAttribute("listatransportistas",(new GestionRegistroEntradaHelper()).getTransportistas());
			request.getSession().setAttribute("listavehiculos",(new GestionRegistroEntradaHelper()).getVehiculos());
			String codigoOrden = new GestionRegistroEntradaHelper().getCodigoOrden();
			System.out.println("codigo de orden es :" + codigoOrden);
			//Carga los RE asociados a ese núm de Orden
			Vector<RegistroEntrada> listareorden = new GestionRegistroEntradaHelper().getRegistrosEntradaOrdenTmp(codigoOrden);
			request.getSession().setAttribute("listareorden",listareorden);
			//Verifica detalle del RE asociados a ese núm de Orden
			if(!listareorden.isEmpty())
				request.getSession().setAttribute("listareordenestado","si");
			else
				request.getSession().setAttribute("listareordenestado","no");
			//Levantamos el evento success para especificar que todo esta bien
			return (SUCCESS);	
		}
		catch (Exception e) {
			e.printStackTrace();
			return (ERROR);
		}
	}
}