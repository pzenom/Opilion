package es.induserco.opilion.presentacion.cliente;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;

public class GestionClienteAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Usuario usuario = new Usuario();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel() {		
		return usuario;
		//return null;
	}

	public String execute() throws Exception {	
		System.out.println("PRESENTACION: procesando el execute de GestionProveedorAction");
		
		return SUCCESS;
	}
}