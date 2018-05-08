package es.induserco.opilion.presentacion.consultaGestionProduccion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;
import es.induserco.opilion.presentacion.GestionMermasProduccionHelper;

public class ConsultaMermasProduccionAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private GestionProduccion gprod= new GestionProduccion();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta Mermas Produccion!");
		return gprod;
	}

	public String execute() throws Exception {
		//Colocamos la lista de gprodes en la request.
		request.getSession().setAttribute("listamermas",
				(new GestionMermasProduccionHelper()).getMermasProduccion(gprod.getFecha(), gprod.getIdProducto()));
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}
}