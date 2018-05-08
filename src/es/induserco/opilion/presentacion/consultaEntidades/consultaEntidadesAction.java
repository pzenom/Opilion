package es.induserco.opilion.presentacion.consultaEntidades;

import javax.servlet.http.HttpServletRequest;

import es.induserco.opilion.data.entidades.*;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class consultaEntidadesAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{
	
	private HttpServletRequest request;
	private Entidad entidad= new Entidad();

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	public String execute() throws Exception {
		//Colocamos la lista de entidades en la request.
		String filtro="";
		request.getSession().setAttribute("listaentidades",(new GestionEntidadesHelper()).getEntidades(filtro));

		System.out.println("paso supuestamente!");
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta!");
		// TODO Auto-generated method stub
		return entidad;
	}
}