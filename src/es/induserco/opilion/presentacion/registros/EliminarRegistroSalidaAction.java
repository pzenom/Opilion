package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class EliminarRegistroSalidaAction.
 */
public class EliminarRegistroSalidaAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The rs find. */
	private RegistroSalida rsFind = new RegistroSalida();
	
	/** The rs dele. */
	private RegistroSalida rsDele = new RegistroSalida();
	
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Eliminar Registro Salida Action...");
		this.request=request;	
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {		
		return rsDele;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		rsDele.setIdOperario(us.getLogin());
		rsFind.setCodigoSalida((String)request.getSession().getAttribute("codigoSalida"));
		new GestionRegistroSalidaHelper().deleteRegistroSalida(rsFind, rsDele);
		return SUCCESS;
	}
}

