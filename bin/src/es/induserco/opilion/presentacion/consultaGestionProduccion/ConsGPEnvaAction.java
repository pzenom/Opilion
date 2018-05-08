package es.induserco.opilion.presentacion.consultaGestionProduccion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class ConsGPEnvaAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private GestionProduccion gprod = new GestionProduccion();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta Registro Envasado!");
		return gprod;
	}

	public String execute() throws Exception {
		try{
			//Colocamos la lista de gprodes en la request.
			request.getSession().setAttribute("listaregienvasados", new GestionEnvasadoHelper().getRegistroEnvasados(gprod.getOrden(), gprod.getIdProducto(), 1));
			//Levantamos el evento success para especificar que todo ha ido bien
			request.getSession().setAttribute("listaproductos", new GestionProductosHelper().getProductos(null, false));
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
}