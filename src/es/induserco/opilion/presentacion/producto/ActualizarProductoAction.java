package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class ActualizarProductoAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Producto productoFind = new Producto();
	private Producto productoActualiza = new Producto();	
	
	//@Override
	public Object getModel() {	
		return productoActualiza;
	}

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	public String execute() throws Exception 
	{
		System.out.println("PRESENTACION: Actualizar Producto");
		productoFind.setIdProducto((Long)request.getSession().getAttribute("idProducto"));
		System.out.println(" El nombre es: " + productoFind.getIdProducto());
		System.out.println(" El nombre es: " + productoActualiza.getNombre());
		//new GestionProductosHelper().updateProducto(productoFind, productoActualiza);	
		request.getSession().setAttribute("listaregistros",(new GestionProductosHelper()).loadProducto(productoFind));		
		return SUCCESS;
	}	
}