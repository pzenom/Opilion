package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class FiltroUbicacionesAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object> {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Entidad entidad= new Entidad();

	public String execute() throws Exception {
		request.getSession().setAttribute("listaubicacionzonas",(new GestionUbicacionesHelper()).getDireccionesUbicacion());
		return (SUCCESS);
	}

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de FiltroUbicacionesAction!");
		return entidad;
	}
}