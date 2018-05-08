package es.induserco.opilion.presentacion.ubicacion;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class MuestraAlmacenadosAjaxAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion ubicacion = new Ubicacion();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel(){
		return ubicacion;
	}

	public String execute() throws Exception{
		long idHueco = Long.parseLong((String)request.getParameter("idHuecoMuestra"));
		ubicacion.setIdHueco(idHueco);
		Ubicacion u = new GestionUbicacionesHelper().getUbicacion(ubicacion);
		Vector<Ubicacion> registrosAlmacenados = new GestionUbicacionesHelper().getRegistrosAlmacenados(idHueco);
		request.getSession().setAttribute("almacenados", registrosAlmacenados);
		request.getSession().setAttribute("idHueco", idHueco);
		request.getSession().setAttribute("descripcionHueco", u.getNombreHueco());
		return SUCCESS;
	}
}