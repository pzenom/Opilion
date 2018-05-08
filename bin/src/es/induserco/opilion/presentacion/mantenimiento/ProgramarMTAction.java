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
public class ProgramarMTAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	private HttpServletRequest request;
	private Maquina entry = new Maquina();

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
		if (entry.getIdMaquina() != null){
			Maquina maquina = new GestionRegistroEntradaHelper().getMaquinas(0,entry.getIdMaquina(), "").get(0);
			request.getSession().setAttribute("maquina", maquina);
			Vector<Mantenimiento> mantenimientos = new GestionRegistroEntradaHelper().getMantenimientos(0, maquina.getIdTipo(), 0);
			request.getSession().setAttribute("listaMantenimientos", mantenimientos);
			Vector<Ciclo> ciclos = new GestionRegistroEntradaHelper().getCiclos();
			request.getSession().setAttribute("listaCiclos", ciclos);
			return ("PROGRAMAR_MAQUINA");
		} else
			if (entry.getIdTipo() != 0){
				request.getSession().setAttribute("listaMaquinas",new GestionRegistroEntradaHelper().getMaquinas(entry.getIdTipo(),0,""));
				return (SUCCESS);
			}
		return (SUCCESS);
		//request.getSession().setAttribute("listatm",(new GestionRegistroEntradaHelper()).getTipoMant());
		//request.getSession().setAttribute("listaTiposMaquinas", (new GestionRegistroEntradaHelper()).getTiposMaquinas());
	}
}