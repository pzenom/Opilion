package es.induserco.opilion.presentacion.consultaGestionProduccion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class ConsGPSeleAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private GestionProduccion gprod = new GestionProduccion();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta Registro Seleccion!");
		return gprod;
	}

	public String execute() throws Exception {
		//Colocamos la lista de gprodes en la request.
		String fechaConsulta = (String)request.getParameter("fechaConsulta");
		//Llega de la forma 08/11/2011, y debe compararse con la forma 2011-11-02
		String[] frag = null;
		if (fechaConsulta != null)
			frag = fechaConsulta.split("/");
		if (frag != null && frag.length == 3)
			fechaConsulta = frag[2] + "-" + frag[1] + "-" + frag[0];
		System.out.println("fecha es"+fechaConsulta);
		request.getSession().setAttribute("orden",
				(gprod.getOrden()));
		request.getSession().setAttribute("listaseleccion",
				(new GestionDesgranadoHelper()).getRegistroSeleccion(gprod.getOrden(),fechaConsulta));
		//Levantamos el evento success para especificar que todo ha ido bien
		request.getSession().setAttribute("listaproductos", new GestionProductosHelper().getProductos(null, false));
		return (SUCCESS);
	}
}