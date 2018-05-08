package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class ValidarLoteRegistroAjaxAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{	

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Producto producto = new Producto();
	
	public String execute() throws Exception {
		String validacion = "";//O: OK; N: No existe
		validacion = new GestionProductosHelper().validarLote(producto.getLote());
		request.getSession().setAttribute("validacion", validacion);
		return SUCCESS;
	}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) { this.request = httpServletRequest; }
	public Object getModel() { return producto; }	
}