package es.induserco.opilion.presentacion.gestionProduccionFumigado;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class ConsREFumiAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private GestionProduccion gprod= new GestionProduccion();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("Consulta RE para Proceso: Fumigados");
		return gprod;
	}

	public String execute() throws Exception {
		//request.getSession().setAttribute("listaregistrosfumigados",(new GestionCongeladoHelper()).getRegistroEntradaCongelado(gprod.getOrden(),gprod.getIdProducto()));
		if (gprod.getOrden() == null)
			gprod.setOrden("");
		request.getSession().setAttribute("listaregistrosfumigados",(new GestionDesgranadoHelper()).getREProceso(gprod.getOrden(),gprod.getIdProducto(),3));		
		//Levantamos el evento success para especificar que todo ha ido bien
		request.getSession().setAttribute("listaproductos",(new GestionProductosHelper()).getMateriasPrimas(0));// new GestionProductosHelper().getProductos(null));
		return (SUCCESS);
	}
}