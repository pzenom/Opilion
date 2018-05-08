package es.induserco.opilion.presentacion.envase;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.presentacion.GestionEnvasesHelper;

public class CargarEnvaseAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	//lo que se tiene aqui se tiene que recuperar aqui nada más
	private HttpServletRequest request;
	private Envase entry = new Envase();
	
	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		System.out.println("CargarProducto Action...");
		this.request = httpServletRequest;			
	}

	//@Override
	public Object getModel() {	
		//System.out.println("Cargar actualizar!: "+entidad.getNombre());		
		return entry;
	}		

	public String execute() throws Exception {	
		//System.out.println("Cargar Action - id entrada update "+request.getAttribute("idProducto"));		
		request.getSession().setAttribute("idEnvase",(request.getAttribute("idEnvase")));
		entry.setIdEnvase((Long)request.getAttribute("idEnvase"));
		request.getSession().setAttribute("listamateriales",(new GestionEnvasesHelper()).getMateriales());
		request.getSession().setAttribute("listaregistros",(new GestionEnvasesHelper()).loadEnvase(entry));
		return (SUCCESS);
	}
}