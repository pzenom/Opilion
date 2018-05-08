package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class ListaUbicacionesRegistroAjaxAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{	

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Producto producto = new Producto();
	
	public String execute() throws Exception {	
		//si muestro esto, estoy devolviendo productos de mano
		request.getSession().setAttribute("listaUbicaciones", new GestionUbicacionesHelper().getUbicacionesRegistro(producto.getLote()));
		return (SUCCESS);
	}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) { this.request = httpServletRequest; }
	public Object getModel() { return producto; }	
}