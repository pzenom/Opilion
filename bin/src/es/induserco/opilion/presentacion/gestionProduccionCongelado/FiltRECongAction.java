package es.induserco.opilion.presentacion.gestionProduccionCongelado;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionCribadoHelper;

public class FiltRECongAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private GestionProduccion gprod= new GestionProduccion();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta!");
		return gprod;
	}
	
	public String execute() throws Exception {
		//Muestra el listado de Materia prima para seleccionar los RE.
		request.getSession().setAttribute("listaproductos",(new GestionCribadoHelper()).getProductos());
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}
}