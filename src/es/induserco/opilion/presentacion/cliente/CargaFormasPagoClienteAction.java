package es.induserco.opilion.presentacion.cliente;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class CargaFormasPagoClienteAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private Entidad cliente = new Entidad();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("CargaFormasPagoCliente Action...");
		this.request = request;	
	}

	//@Override
	public Object getModel() {		
		return cliente;
	}

	public String execute() throws Exception {
		request.getSession().setAttribute("formasPagoCliente",
				(new GestionEntidadesHelper()).getFormasPagoEntidad(cliente));
		return (SUCCESS);
	}
}