package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class DividirFacturaCuotasAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = -622643281402760772L;
	private HttpServletRequest request;
	private Factura factura = new Factura();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("DividirFacturaCuotasAction Action...");
		this.request = request;	
	}

	//@Override
	public Object getModel() {		
		return factura;
	}

	public String execute() throws Exception {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		factura.setUsuarioResponsable(us.getLogin());
		//Inserta la factura
		new GestionRegistroSalidaHelper().dividirFacturaCuotas(factura);
		return SUCCESS;
	}
}