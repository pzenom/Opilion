package es.induserco.opilion.presentacion.producto;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.MateriaPrima;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class MateriasPrimasAjaxAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private MateriaPrima mp = new MateriaPrima();

	public void setServletRequest(HttpServletRequest request) { this.request=request; }
	public Object getModel() { return mp; }

	public String execute() throws Exception {
		System.out.println("Execute MateriasPrimasAjaxAction");
		long idMateriaPrima = mp.getIdMateriaPrima();
		Vector<MateriaPrima> materias = new GestionProductosHelper().getMateriasPrimasCategorias(idMateriaPrima);
		request.getSession().setAttribute("materiaCategorias", materias);
		return SUCCESS;
	}
}