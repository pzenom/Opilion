package es.induserco.opilion.presentacion.mantenimiento;

import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Mantenimiento;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

/**
 * @author andres (26/05/2011)
 * @version 1.0
 */
public class InseRegMTAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	private HttpServletRequest request;
	private Mantenimiento entry = new Mantenimiento();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("execute...Inse Mantenimiento Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		return entry;
	}

	/**
	 * @author andres (26/05/2011)
	 * @since 1.0
	 */
	public String execute() throws Exception {
		System.out.println("Execute InseMTAction: Insertar Mantenimiento nuevo!");
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		entry.setResponsable(us.getLogin());
		String fecha = entry.getFecha();
		entry.setFechaProgramada(fecha);
		//String fecha = (String)request.getParameter("fechaConsulta");
		//System.out.println("fecha es"+fecha);		
		//entry.setFecha(fecha);
		new GestionRegistroEntradaHelper().inseRegMT(entry); 
		return SUCCESS;
	}
}