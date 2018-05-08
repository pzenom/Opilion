package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class ActualizarEstadoFacturaAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 8101736591258577235L;
	private HttpServletRequest request;
	private Factura factura = new Factura();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Actualizar ESTADO factura Action...");
		this.request = request;	
	}

	//@Override
	public Object getModel() {		
		return factura;
	}

	public String execute() throws Exception {
		String estado = (String)request.getParameter("estado");
		factura.setEstado(estado);
		new GestionRegistroSalidaHelper().updateEstadoFactura(factura);
		return SUCCESS;
	}
}