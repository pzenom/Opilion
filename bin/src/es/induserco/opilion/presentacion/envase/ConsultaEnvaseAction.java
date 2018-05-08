package es.induserco.opilion.presentacion.envase;

import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.presentacion.GestionEnvasesHelper;

public class ConsultaEnvaseAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Envase entry = new Envase();

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	public String execute() throws Exception {
		request.getSession().setAttribute("idMaterial", (entry.getIdMaterial()));	
		request.getSession().setAttribute("listaenvases", (new GestionEnvasesHelper()).getEnvases(entry));
		request.getSession().setAttribute("listamateriales",(new GestionEnvasesHelper()).getMateriales());
		return (SUCCESS);
		}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta!");
		return entry;
	}
}