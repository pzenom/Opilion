package es.induserco.opilion.presentacion.consultaGestionProduccion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class ListarEnvasarAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private GestionProduccion gprod = new GestionProduccion();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta!");
		return gprod;
	}

	public String execute() throws Exception {
		//Colocamos la lista de entidades en la request.
		request.getSession().setAttribute("listaregienvasados",
				(new GestionEnvasadoHelper()).getProcesosPendientes(gprod, "S"));
		request.getSession().setAttribute("listaproductos",
				(new GestionEnvasadoHelper()).getPresentacionProductos(false));
		//request.getSession().setAttribute("listaproductos",(new GestionEnvasadoHelper()).getProcesosPendientes("E"));
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}
}