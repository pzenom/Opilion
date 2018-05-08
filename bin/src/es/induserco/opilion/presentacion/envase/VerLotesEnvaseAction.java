package es.induserco.opilion.presentacion.envase;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class VerLotesEnvaseAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private Envase e = new Envase();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("Execute VerLotesEnvaseAction");
		return e;
	}

	//@Override
	public String execute() throws Exception {
		System.out.println("Execute VerLotesEnvaseAction");
		long idEnvase = e.getIdEnvase();
		Vector<RegistroEntrada> materias = new GestionEnvasadoHelper().getLotesEnvase(idEnvase);
		request.getSession().setAttribute("lotes", materias);
		return SUCCESS;
	}
}