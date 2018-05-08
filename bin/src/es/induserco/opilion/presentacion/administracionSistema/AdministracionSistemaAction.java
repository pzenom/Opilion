package es.induserco.opilion.presentacion.administracionSistema;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Empresa;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.GestionRoles;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionUsuariosHelper;

public class AdministracionSistemaAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = -622643281402760772L;
	private HttpServletRequest request;
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("AdministracionSistemaAction Action...");
		this.request = request;	
	}

	//@Override
	public Object getModel(){
		System.out.println("ADMINISTRACION");
		return null;
	}

	public String execute() throws Exception {
		Empresa empresa = new GestionEntidadesHelper().getEmpresa((long)1);
		GestionRoles gestionRoles = new GestionEntidadesHelper().getRoles();
		request.getSession().setAttribute("empresa", empresa);
		request.getSession().setAttribute("gestionRoles", gestionRoles);
		Vector<Usuario> usuarios = new GestionUsuariosHelper().getUsuarios();
		request.getSession().setAttribute("usuarios", usuarios);
		return SUCCESS;
	}
}