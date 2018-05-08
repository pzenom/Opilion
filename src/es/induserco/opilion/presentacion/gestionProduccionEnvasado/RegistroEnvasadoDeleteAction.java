package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistroEnvasadoDeleteAction.
 */
public class RegistroEnvasadoDeleteAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The gprod. */
	private GestionProduccion gprod = new GestionProduccion();
	

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Registro Entrada Delete Action...");
		this.request=request;	
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {		
		return gprod;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		//Colocamos las listas de datos en la request.
		request.getSession().setAttribute("orden",(request.getAttribute("orden")));
		gprod.setOrden((String)request.getAttribute("orden"));
		request.getSession().setAttribute("fecharegistro",(new GestionEnvasadoHelper()).getFechaRegistro());
				request.getSession().setAttribute("listaproductos",(new GestionEnvasadoHelper()).getProductos());
		request.getSession().setAttribute("listaenvases",(new GestionEnvasadoHelper()).getEnvases());
		request.getSession().setAttribute("listaoperarios",(new GestionEnvasadoHelper()).getOperarios());
		//request.getSession().setAttribute("listaregistrosdel",(new GestionEnvasadoHelper()).getRegistroEnvasados(gprod.getOrden(),gprod.getFecha(),gprod.getLoteAsignado(),gprod.getIdProducto()));
		//Levantamos el evento success para especificar que todo esta bien
		return (SUCCESS);
	}
}



