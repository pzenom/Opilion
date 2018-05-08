package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class ListaUbicacionRegistroAjaxAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{	

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Producto producto = new Producto();
	
	public String execute() throws Exception {
		long idHueco = Long.parseLong(request.getParameter("idHueco"));
		request.getSession().setAttribute("ubicacion", new GestionUbicacionesHelper().getUbicacionRegistro(producto.getLote(), idHueco));
		return (SUCCESS);
	}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) { this.request = httpServletRequest; }
	public Object getModel() { return producto; }	
}