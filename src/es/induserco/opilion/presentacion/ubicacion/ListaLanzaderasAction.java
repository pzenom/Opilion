package es.induserco.opilion.presentacion.ubicacion;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Lanzadera;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class ListaLanzaderasAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Lanzadera lanzadera = new Lanzadera();
	private static Logger logger = Logger.getLogger(ListaLanzaderasAction.class);

	public String execute() throws Exception{
		logger.info("MostrarAlmacenAction");
		Vector<Lanzadera> lanzaderas = new GestionUbicacionesHelper().getLanzaderas(lanzadera);
		request.getSession().setAttribute("vectorLanzaderas", lanzaderas);
		return SUCCESS;
	}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) { this.request = httpServletRequest; }
	public Object getModel(){ return lanzadera; }
}