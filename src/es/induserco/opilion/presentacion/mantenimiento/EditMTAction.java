package es.induserco.opilion.presentacion.mantenimiento;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Maquina;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class EditMTAction.
 */
public class EditMTAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	private HttpServletRequest request;
	private Maquina entry = new Maquina();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("execute...Edit Mant Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return entry;
	}

	public String execute() throws Exception {
		//request.getSession().setAttribute("listatm",(new GestionRegistroEntradaHelper()).getTipoMant());
		request.getSession().setAttribute("listaTiposMaquinas", (new GestionRegistroEntradaHelper()).getTiposMaquinas());
		return (SUCCESS);
	}
}
