package es.induserco.opilion.presentacion.sincronizacion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.presentacion.GestionesEspecialesHelper;

/**
 * Exporta la base de datos a un fichero
 * @author andres (20/05/2011)
 * @version 0.1
 */
public class ExportarDatosAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	
	//@Override
	public void setServletRequest(HttpServletRequest request) { this.request = request; }

	//@Override
	public Object getModel() {
		System.out.println("Exportar datos");
		return null;
	}
	
	public String execute() throws Exception {
		System.out.println("Execute exportar datos");
		long idVehiculo = Long.parseLong(request.getParameter("vehiculo"));
		long idComercial = Long.parseLong(request.getParameter("idComercial"));
		//TODO: idRuta, lo recogemos aquí, pero de momento no hacemos nada con ello
		long idRuta = Long.parseLong(request.getParameter("idRuta"));
		new GestionesEspecialesHelper().exportarDatos(idVehiculo, idComercial);
	    return ("SUCCESS");
	}
}