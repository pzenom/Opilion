package es.induserco.opilion.presentacion.envase;

import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.presentacion.GestionEnvasesHelper;

public class ActualizarEnvaseAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Envase envaseFind = new Envase();
	private Envase envaseActualiza = new Envase();

	//@Override
	public Object getModel() {	
		return envaseActualiza;
	}

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	public String execute() throws Exception {	

		System.out.println("PRESENTACION: Actualizar Envase");
		envaseFind.setIdEnvase((Long)request.getSession().getAttribute("idEnvase"));
		new GestionEnvasesHelper().updateEnvase(envaseFind, envaseActualiza);	
		request.getSession().setAttribute("listaregistros",(new GestionEnvasesHelper()).loadEnvase(envaseFind));		
		return SUCCESS;
	}
}