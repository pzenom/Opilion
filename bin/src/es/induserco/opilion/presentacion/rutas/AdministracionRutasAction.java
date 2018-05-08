package es.induserco.opilion.presentacion.rutas;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.contacto.Ruta;
import es.induserco.opilion.presentacion.GestionUsuariosHelper;

public class AdministracionRutasAction extends ActionSupport implements
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
		Vector<Ruta> rutas = new GestionUsuariosHelper().getRutas();
		Vector<Usuario> comerciales = new GestionUsuariosHelper().getComerciales();
		request.getSession().setAttribute("rutas", rutas);
		request.getSession().setAttribute("comerciales", comerciales);
		return SUCCESS;
	}
}