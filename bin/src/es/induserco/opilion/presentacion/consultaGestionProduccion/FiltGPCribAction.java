package es.induserco.opilion.presentacion.consultaGestionProduccion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionCribadoHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class FiltGPCribAction.
 */
public class FiltGPCribAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	/** The request. */
	private HttpServletRequest request;
	
	/** The gprod. */
	private GestionProduccion gprod= new GestionProduccion();
	
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta!");
		// TODO Auto-generated method stub
		return gprod;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		//Colocamos la lista de entidades en la request.
		request.getSession().setAttribute("listaproductos",(new GestionCribadoHelper()).getProductos());
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}

}



