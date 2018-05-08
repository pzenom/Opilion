package es.induserco.opilion.presentacion.usuario;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.presentacion.GestionUsuariosHelper;

public class ListaComercialesAjaxAction extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;

	public void setServletRequest(HttpServletRequest request) { this.request = request; }

	public String execute() throws Exception {
		Vector<Usuario> comerciales = new GestionUsuariosHelper().getComerciales();
		request.getSession().setAttribute("vectorComerciales", comerciales);
		return (SUCCESS);
	}
}