package es.induserco.opilion.presentacion.envase;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.presentacion.GestionEnvasesHelper;

public class FiltroEnvaseAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Envase entry = new Envase();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel() {
		return entry;
	}

	public String execute() throws Exception {	
		//carga los listados para los filtros de los combos
		request.getSession().setAttribute("listamateriales",(new GestionEnvasesHelper()).getMateriales());
		request.getSession().setAttribute("listaenvases",(new GestionEnvasesHelper()).getEnvases(entry));
		return (SUCCESS);
	}
}