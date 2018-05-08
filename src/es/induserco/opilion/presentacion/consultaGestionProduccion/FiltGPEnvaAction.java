package es.induserco.opilion.presentacion.consultaGestionProduccion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class FiltGPEnvaAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private GestionProduccion gprod= new GestionProduccion();
	
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
		int filtro = Integer.parseInt((String)request.getParameter("filtro"));
		request.getSession().setAttribute("listaregienvasados", (new GestionEnvasadoHelper()).getRegistroEnvasados(gprod.getOrden(), gprod.getIdProducto(), filtro));
		request.getSession().setAttribute("listaproductos", (new GestionEnvasadoHelper()).getPresentacionProductos(false));
		return (SUCCESS);
	}
}