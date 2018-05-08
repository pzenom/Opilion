package es.induserco.opilion.presentacion.devoluciones;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroOrden;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class DevolucionAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private RegistroOrden entry = new RegistroOrden();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Ver Orden de Entrada Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return entry;
	}

	public String execute() throws Exception {
		request.getSession().setAttribute("listaestadosfabas",(new GestionRegistroEntradaHelper()).getEstadosFabas());
		request.getSession().setAttribute("listaclientes",(new GestionRegistroSalidaHelper()).getClientes());
		return SUCCESS;
	}
}