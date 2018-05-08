package es.induserco.opilion.presentacion.ubicacion;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class SacarSeleccionLineaAjaxAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object> {
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion ubicacion = new Ubicacion();
	private String url;
	
	public String getUrl(){ return this.url; }

	public String execute() throws Exception{
		//En ubicacion tenemos separadas idLinea de idLineaZona
		Ubicacion u = new GestionUbicacionesHelper().getUbicacion(ubicacion);
		Vector<Ubicacion> ocupados =
			new GestionUbicacionesHelper().getHuecosOcupados(ubicacion);
		u.setRegistro(ubicacion.getRegistro());
		if (u.getIdLinea() > 0){
			request.getSession().setAttribute("ubicacion", u);
			request.getSession().setAttribute("ocupados", ocupados);
			url = u.getUrlPlanoLinea();
			return SUCCESS;
		}else
			return ERROR;
	}
	
	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel(){
		return ubicacion;
	}
}