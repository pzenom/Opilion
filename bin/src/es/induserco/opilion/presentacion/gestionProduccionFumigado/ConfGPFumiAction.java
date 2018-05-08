package es.induserco.opilion.presentacion.gestionProduccionFumigado;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
//import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionCribadoHelper;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;
import es.induserco.opilion.presentacion.GestionEnvasesHelper;
import es.induserco.opilion.presentacion.GestionRegistroEnvasesHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfGPFumiAction.
 */
public class ConfGPFumiAction extends ActionSupport 
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

		//1. Fecha del registro no editable
		request.getSession().setAttribute("fecharegistro",(new GestionCribadoHelper()).getFechaRegistro());
		
		//2. C�digo de orden que engloba el proceso y a los subregistros
		gprod.setOrden((String)request.getSession().getAttribute("orden"));
		System.out.println("codigo de orden fumigado es "+gprod.getOrden());
		
		//3. Hora de inicio del proceso
		request.getSession().setAttribute("horaInicioProceso",(new GestionCribadoHelper()).getHoraInicioProceso(gprod.getOrden()));
		gprod.setHoraInicioProceso((String)request.getSession().getAttribute("horainicio"));		
		
		//4. Listado de Subregistros generados
		request.getSession().setAttribute("subentradasproceso",(new GestionDesgranadoHelper()).getREProceso(gprod.getOrden(),"Fumigado"));
		
		return (SUCCESS);		
	}

}





