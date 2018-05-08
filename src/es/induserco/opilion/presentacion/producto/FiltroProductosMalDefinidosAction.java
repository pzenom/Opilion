package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class FiltroProductosMalDefinidosAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Producto entry = new Producto();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	//@Override
	public Object getModel() {
		return entry;
	}

	public String execute() throws Exception {
		System.out.println("FiltroProductosMalDefinidosAction execute...");
		request.getSession().setAttribute("listaproductosMalDefinidos",
				(new GestionProductosHelper()).getProductosMalDefinidos());
		return (SUCCESS);
	}
}