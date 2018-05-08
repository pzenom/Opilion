package es.induserco.opilion.presentacion.mantenimiento;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ciclo;
import es.induserco.opilion.data.entidades.Mantenimiento;
import es.induserco.opilion.data.entidades.Maquina;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

/**
 * @author andres (26/05/2011)
 * @version 1.0
 */
public class RegistrarMTAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	private HttpServletRequest request;
	private Mantenimiento entry = new Mantenimiento();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("execute...Programar MT Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return entry;
	}

	public String execute() throws Exception {
		request.getSession().setAttribute("maquina",(new GestionRegistroEntradaHelper()).getMaquinas(0, entry.getIdMaquina(), ""));
		request.getSession().setAttribute("mantenimiento",
				(new GestionRegistroEntradaHelper()).getMantenimientos(entry.getIdMantenimientoProgramacion(), 0, 0));
		return SUCCESS;
		//request.getSession().setAttribute("listatm",(new GestionRegistroEntradaHelper()).getTipoMant());
		//request.getSession().setAttribute("listaTiposMaquinas", (new GestionRegistroEntradaHelper()).getTiposMaquinas());
	}
}