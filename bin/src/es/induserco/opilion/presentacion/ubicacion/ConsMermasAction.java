package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class ConsMermasAction extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute() throws Exception {
		System.out.println("procesando el execute de ConsMermas!");
		//Colocamos la lista de incidencias en la request.
		request.getSession().setAttribute("listaIncidencias",
				(new GestionUbicacionesHelper().getIncidencias()));
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}
}