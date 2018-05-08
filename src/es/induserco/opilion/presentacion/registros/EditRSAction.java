package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class EditRSAction.
 */
public class EditRSAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The exit. */
	private RegistroSalida exit = new RegistroSalida();
	

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Registro Salida Update Action...");
		this.request=request;	
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {		
		return exit;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		//Colocamos las listas de datos en la request.
		System.out.println("codigo salida para update sacado del request "+request.getAttribute("codigoSalida"));
		request.getSession().setAttribute("codigoSalida",(request.getAttribute("codigoSalida")));
		exit.setCodigoSalida((String)request.getAttribute("codigoSalida"));
		request.getSession().setAttribute("listarsupd",(new GestionRegistroSalidaHelper()).loadRegistroSalida(exit.getCodigoSalida()));
		
		
		return (SUCCESS);
	}
}


