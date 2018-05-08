package es.induserco.opilion.presentacion.consultaRegistros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsultaRegSalidaAction.
 */
public class ConsultaRegSalidaAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	/** The request. */
	private HttpServletRequest request;
	
	/** The entidad. */
	private Entidad entidad= new Entidad();
	
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
		return entidad;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		//Colocamos la lista de entidades en la request.
		request.getSession().setAttribute("listaproductos",(new GestionRegistroSalidaHelper()).getProductos());
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}

}
