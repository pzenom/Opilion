package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import es.induserco.opilion.data.entidades.Entidad;

public class RegistroCategoriaAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private Entidad entry = new Entidad();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("RegistroCategoriaAction...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return entry;
	}

	public String execute() throws Exception {
		//Colocamos las listas de datos en la request.
		//Levantamos el evento success para especificar que todo esta bien
		return (SUCCESS);
	}
}