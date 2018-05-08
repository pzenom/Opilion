package es.induserco.opilion.presentacion.merma;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.ProductoMerma;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class IngresarControlMermaAction.
 */
public class IngresarControlMermaAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The entry. */
	private ProductoMerma entry = new ProductoMerma();
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {	
		System.out.println("Agregar proveedor!: "+entry.getIdUbicacion());		
		return entry;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception 
	{	
		//Recupera el usuario
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		entry.setResponsable(usuario.getLogin());			
		
		System.out.println("Registro de ubicacion...");
		new GestionProductosHelper().addProductoMerma(entry);		
		return SUCCESS;
	}

	/**
	 * Gets the id ubicacion.
	 *
	 * @return the id ubicacion
	 */
	public Long getIdUbicacion() {return entry.getIdUbicacion();}
	
	/**
	 * Sets the id ubicacion.
	 *
	 * @param idUbicacion the new id ubicacion
	 */
	public void setIdUbicacion(Long idUbicacion) {request.setAttribute("idUbicacion", entry.getIdUbicacion());}


	
}