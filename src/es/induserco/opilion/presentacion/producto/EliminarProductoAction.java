package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class EliminarProductoAction.
 */
public class EliminarProductoAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	//lo que se tiene aqui se tiene que recuperar aqui nada más
	/** The request. */
	private HttpServletRequest request;
	
	/** The producto. */
	private Producto producto = new Producto();


	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {	
		System.out.println("Por aqui anda!! ");		
		return producto;
	}		

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception
	{	
		request.getSession().setAttribute("idProducto",(request.getAttribute("idProducto")));
		producto.setIdProducto((Long)request.getAttribute("idProducto"));
		new GestionProductosHelper().deshabilitaProducto(producto);
		System.out.println("Escogido " + producto.getIdProducto());
		return (SUCCESS);
	}
}