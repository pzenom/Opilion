package es.induserco.opilion.presentacion.usuario;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.presentacion.GestionUsuariosHelper;

public class InsertarUsuarioAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
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
	}

	//@Override
	public String execute() throws Exception {
		new GestionUsuariosHelper().addUsuario(usuario);
		return SUCCESS;
	}
}