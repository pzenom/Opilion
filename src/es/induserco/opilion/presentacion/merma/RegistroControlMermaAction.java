package es.induserco.opilion.presentacion.merma;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistroControlMermaAction.
 */
public class RegistroControlMermaAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	//lo que se tiene aqui se tiene que recuperar aqui nada más
	/** The request. */
	private HttpServletRequest request;
	
	/** The entry. */
	private Ubicacion entry = new Ubicacion();
	
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
		return entry;
	}		
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception 
	{	
		request.getSession().setAttribute("listaproductos",(new GestionRegistroEntradaHelper()).getProductos());
		request.getSession().setAttribute("listaubicaciones",(new GestionUbicacionesHelper()).getUbicaciones(7L));
		return SUCCESS;
	}
}