package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistroMovVehAction.
 */
public class RegistroMovVehAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The entry. */
	private RegistroEntrada entry = new RegistroEntrada();

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Registro Movimiento Vehículo Action...");
		this.request=request;	
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
	public String execute() throws Exception {
		//Colocamos las listas de datos en la request.
		request.getSession().setAttribute("fecharegistro",(new GestionRegistroEntradaHelper()).getFechaRegistro());
		request.getSession().setAttribute("fechacaducidad",(new GestionRegistroEntradaHelper()).getFechaCaducidad());		
		request.getSession().setAttribute("listatransportistas",(new GestionRegistroEntradaHelper()).getTransportistas());
		request.getSession().setAttribute("listavehiculos",(new GestionRegistroEntradaHelper()).getVehiculos());
		//Levantamos el evento success para especificar que todo esta bien
		return (SUCCESS);
	}
}
