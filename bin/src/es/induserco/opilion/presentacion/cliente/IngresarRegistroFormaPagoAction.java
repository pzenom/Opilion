package es.induserco.opilion.presentacion.cliente;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.banca.FormaPago;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class IngresarRegistroFormaPagoAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private FormaPago formaPago = new FormaPago();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("IngresarRegistroFormaPagoAction...");
		this.setRequest(request);	
	}

	//@Override
	public Object getModel() { return formaPago; }

	public String execute() throws Exception {
		new GestionEntidadesHelper().registrarFormaPago(formaPago);
		return SUCCESS;
	}

	public void setRequest(HttpServletRequest request) { this.request = request; }
	
	public HttpServletRequest getRequest() { return request; }
}