package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class RegistroEnvasadoAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private GestionProduccion gprod = new GestionProduccion();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Registro Envasado Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		return gprod;
	}

	public String execute() throws Exception {
		//Colocamos las listas de datos en la request.
		request.getSession().setAttribute("fecharegistro",(new GestionEnvasadoHelper()).getFechaRegistro());
		request.getSession().setAttribute("listaproductos",(new GestionEnvasadoHelper()).getProductos());
		request.getSession().setAttribute("listaenvases",(new GestionEnvasadoHelper()).getEnvases());
		//Levantamos el evento success para especificar que todo esta bien
		return (SUCCESS);
	}
}