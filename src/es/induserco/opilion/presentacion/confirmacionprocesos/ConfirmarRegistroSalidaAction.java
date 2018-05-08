package es.induserco.opilion.presentacion.confirmacionprocesos;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class ConfirmarRegistroSalidaAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private HttpServletRequest request;
	private RegistroSalida exit = new RegistroSalida();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Confirmacion Registro Salida Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return exit;
	}

	public String execute() throws Exception {		
		//Colocamos las listas de datos en la request.
		request.getSession().setAttribute("fecharegistro",(new GestionRegistroSalidaHelper()).getFechaRegistro());
		//Levantamos el evento success para especificar que todo esta bien
		return (SUCCESS);
	}
}