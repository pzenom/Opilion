package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.UbicacionZona;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class ConsultaUbicacionAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private UbicacionZona ubicacionZona= new UbicacionZona();

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	public String execute() throws Exception {
		System.out.println("procesando el execute de ConsultaUbicacion!");
		//Colocamos la lista de proveedores en la request.
		request.getSession().setAttribute("idZona",(ubicacionZona.getIdZona()));		
		request.getSession().setAttribute("listaubicaciones", (new GestionUbicacionesHelper().getUbicaciones(ubicacionZona.getIdZona())));
		//Levantamos el evento success para especificar que todo ha ido bien

		return (SUCCESS);
		}

	//@Override
	public Object getModel() { return ubicacionZona; }
}