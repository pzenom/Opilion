package es.induserco.opilion.presentacion.devoluciones;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class ReaprovecharDevolucionAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private LineaProducto entry = new LineaProducto();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("ReaprovecharDevolucionAction...");
		this.request = request;	
	}

	//@Override
	public Object getModel() {
		return entry;
	}

	public String execute() throws Exception {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		entry.setUsuarioResponsable(us.getLogin());
		//String lote = entry.getLote();
		//entry.setTeorica(10.0);
		//A partir del lote, sacar el tipo de registro, y generar una orden de entrada y un registro de entrada
		new GestionRegistroEntradaHelper().reaprovecharDevolucion(entry);
		return SUCCESS;
	}
}