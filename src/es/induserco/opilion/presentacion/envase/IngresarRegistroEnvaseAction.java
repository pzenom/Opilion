package es.induserco.opilion.presentacion.envase;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.presentacion.GestionEnvasesHelper;

public class IngresarRegistroEnvaseAction extends ActionSupport implements
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
		request.getSession().setAttribute("resultado",new GestionEnvasesHelper().addEnvase(entry));
		if(request.getSession().getAttribute("resultado").equals(true)){
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
}