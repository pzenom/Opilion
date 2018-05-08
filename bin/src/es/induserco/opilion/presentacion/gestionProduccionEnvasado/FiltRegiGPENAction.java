package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class FiltRegiGPENAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private GestionProduccion gprod= new GestionProduccion();

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	public Object getModel() {
		System.out.println("procesando el execute de Consulta!");
		return gprod;
	}

	public String execute() throws Exception {
		//Colocamos la lista de entidades en la request.
		request.getSession().setAttribute("listaproductos",(new GestionEnvasadoHelper()).getProductos());
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}
}