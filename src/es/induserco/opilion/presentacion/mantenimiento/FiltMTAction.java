package es.induserco.opilion.presentacion.mantenimiento;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Mantenimiento;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class FiltMTAction.
 */
public class FiltMTAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private Mantenimiento mant= new Mantenimiento();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta!");
		return mant;
	}

	public String execute() throws Exception {
		request.getSession().setAttribute("listaTiposMaquinas",(new GestionRegistroEntradaHelper()).getTiposMaquinas());
		request.getSession().setAttribute("listaMantenimientos",(new GestionRegistroEntradaHelper()).getMantenimientos(0,0, 0));
		return (SUCCESS);
	}
}