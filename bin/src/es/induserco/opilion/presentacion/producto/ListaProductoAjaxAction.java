package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class ListaProductoAjaxAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{	

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Producto producto = new Producto();
	
	public String execute() throws Exception {	
		//para el caso hipot�tico de volver sin asegurarse 
		producto.setIdCategoria(new Long(0));
		producto.setIdEstado(new Long(0));
		producto.setIdFamilia(new Long(0));
		producto.setIdTipo(new Long(0));
		producto.setIdGrupo(new Long(0));
		request.getSession().setAttribute("producto", (new GestionProductosHelper()).listaProductoLote(producto.getLote()));
		return SUCCESS;
	}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) { this.request = httpServletRequest; }
	public Object getModel() { return producto; }	
}