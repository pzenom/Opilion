package es.induserco.opilion.presentacion.rutas;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.contacto.Ruta;
import es.induserco.opilion.presentacion.GestionUsuariosHelper;

public class CargarRutaAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ruta ruta = new Ruta();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel() {	
		return ruta;
	}

	public String execute() throws Exception {
		Ruta rutaCargar = new GestionUsuariosHelper().getRuta(ruta);
		request.getSession().setAttribute("rutaCargada", rutaCargar);
		Vector<Usuario> comerciales = new GestionUsuariosHelper().getComerciales();
		request.getSession().setAttribute("comerciales", comerciales);
		return SUCCESS;
	}
}