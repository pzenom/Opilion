package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Vehiculo;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class ComprobarLanzaderaVaciaAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private static final long serialVersionUID = 1L;
	private Vehiculo vehiculo = new Vehiculo();
	private static Logger logger = Logger.getLogger(ComprobarLanzaderaVaciaAction.class);

	public String execute() throws Exception {
		logger.info("ComprobarLanzaderaVaciaAction!!");
		int numeroProductosLanzadera = new GestionUbicacionesHelper().getNumeroProductosNuevaLanzadera(vehiculo.getIdVehiculo());
		request.getSession().setAttribute("numeroProductosLanzadera", numeroProductosLanzadera);
		return SUCCESS;
	}

	public Object getModel() { return vehiculo; }
	public void setServletRequest(HttpServletRequest request) { this.request = request;	}
}