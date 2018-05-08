package es.induserco.opilion.presentacion.envase;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionEnvasesHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class EliminarEnvaseAction.
 */
public class EliminarEnvaseAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The envase. */
	private Envase envase = new Envase();

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
		return envase;
	}		

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception 
	{	
		request.getSession().setAttribute("idEnvase",(request.getAttribute("idEnvase")));
		envase.setIdEnvase((Long)request.getAttribute("idEnvase"));
		new GestionEnvasesHelper().deshabilitaEnvase(envase);
		System.out.println("Escogido "+envase.getIdEnvase());
		return (SUCCESS);
	}

}