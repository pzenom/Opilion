package es.induserco.opilion.presentacion.producto;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.MateriaPrima;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class VerLotesIngredienteAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private MateriaPrima mp = new MateriaPrima();

	public String execute() throws Exception {
		System.out.println("Execute MateriasPrimasAjaxAction");
		long idMateriaPrima = mp.getIdMateriaPrima();
		Vector<RegistroEntrada> materias = new GestionEnvasadoHelper().getLotesIngrediente(idMateriaPrima);
		request.getSession().setAttribute("lotes", materias);
		return SUCCESS;
	}

	public void setServletRequest(HttpServletRequest request) { this.request = request;	}
	public Object getModel() { return mp; }
}