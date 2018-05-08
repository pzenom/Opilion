package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.MateriaPrima;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class FiltroIngredientesAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private MateriaPrima entry= new MateriaPrima();

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute() throws Exception {
		System.out.println("Filtro ingredientes");
		long idGrupo = 0;
		if (request.getParameter("idGrupo") != null && request.getParameter("idGrupo").compareTo("") != 0)
			idGrupo = Long.valueOf(request.getParameter("idGrupo"));
		request.getSession().setAttribute("listaingredientes",
				(new GestionProductosHelper()).getMateriasPrimasBasicas(idGrupo));
		request.getSession().setAttribute("listatipos",
				(new GestionProductosHelper()).getGrupoProducto());
		//request.getSession().setAttribute("idGrupo", 2);
		return (SUCCESS);
	}

	//@Override
	public Object getModel() {
		return entry;
	}
}