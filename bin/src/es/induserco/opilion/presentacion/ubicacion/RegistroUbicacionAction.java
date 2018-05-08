package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class RegistroUbicacionAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	
	private Ubicacion entry = new Ubicacion();

	public String execute() throws Exception {	
		request.getSession().setAttribute("listatipoubicacion",(new GestionRegistroEntradaHelper()).getTipoUbicaciones());
		request.getSession().setAttribute("listaubicacionzonas",(new GestionUbicacionesHelper()).getDireccionesUbicacion());
		request.getSession().setAttribute("listapasillos",(new GestionUbicacionesHelper()).getPasillosUbicacion());
		request.getSession().setAttribute("listaestanterias",(new GestionUbicacionesHelper()).getEstanteriasUbicacion());
		request.getSession().setAttribute("listaalturas",(new GestionUbicacionesHelper()).getAlturasUbicacion());		
		return SUCCESS;
	}
	
	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	//@Override
	public Object getModel() {	
		return entry;
	}	
}