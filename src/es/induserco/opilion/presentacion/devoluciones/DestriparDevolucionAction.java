package es.induserco.opilion.presentacion.devoluciones;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.data.entidades.RegistroOrden;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class DestriparDevolucionAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private RegistroOrden entry = new RegistroOrden();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("DestriparDevolucionAction...");
		this.request = request;	
	}

	//@Override
	public Object getModel() {
		return entry;
	}

	public String execute() throws Exception {
		String lote = entry.getLote();
		RegistroEnvasado re = new RegistroEnvasado();
		re.setLote(lote);
		//En productos tenemos todos los productos (materias primas, envases, eans13...) que se utilizaron en el envasado
		Vector<LineaProducto> productos = (Vector<LineaProducto>) (new GestionEnvasadoHelper().getProductosComponenLote(lote));
		//TODO: Ordeno alfabéticamente, por el nombre de los productos
		//Ahora tenemos que cargarlos, ya que son los lotes que se pueden reutilizar
		request.getSession().setAttribute("productos", productos);
		return SUCCESS;
	}
}