package es.induserco.opilion.presentacion.gestionProduccionSeleccion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class ConsRESeleAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private GestionProduccion gprod= new GestionProduccion();

	public String execute() throws Exception {
		if (gprod.getOrden() == null)
			gprod.setOrden("");
		request.getSession().setAttribute("listaregistrosseleccion",
				(new GestionDesgranadoHelper()).getREProceso(gprod.getOrden(),gprod.getIdProducto(),4));
		request.getSession().setAttribute("listaproductos", //new GestionProductosHelper().getProductos(null));
				(new GestionProductosHelper()).getMateriasPrimas(0));
		return (SUCCESS);
	}

	//@Override
	public void setServletRequest(HttpServletRequest request) { this.request=request; }
	//@Override
	public Object getModel() { System.out.println("Consulta RE para Proceso: Seleccion"); return gprod; }	
}