package es.induserco.opilion.presentacion.gestionProduccionCongelado;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class ConsRECongAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private GestionProduccion gprod= new GestionProduccion();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("Consulta RE para Proceso: Congelado");
		return gprod;
	}

	public String execute() throws Exception {
		//request.getSession().setAttribute("listaregistroscongelados",(new GestionCongeladoHelper()).getRegistroEntradaCongelado(gprod.getOrden(),gprod.getIdProducto()));
		if (gprod.getOrden() == null)
			gprod.setOrden("");
		request.getSession().setAttribute("listaregistroscongelados",(new GestionDesgranadoHelper()).getREProceso(gprod.getOrden(),gprod.getIdProducto(),2));
		request.getSession().setAttribute("listaproductos",(new GestionProductosHelper()).getMateriasPrimas(0));// new GestionProductosHelper().getProductos(null));
		return (SUCCESS);
	}
}