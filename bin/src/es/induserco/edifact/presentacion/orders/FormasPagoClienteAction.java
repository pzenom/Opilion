package es.induserco.edifact.presentacion.orders;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.banca.DatoBancario;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class FormasPagoClienteAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Entidad cliente = new Entidad();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel(){
		return cliente;
	}

	public String execute() throws Exception{
		System.out.println("FormasPagoClienteAction");
		Vector<DatoBancario> formasPago = new GestionEntidadesHelper().getFormasPagoEntidad(cliente);
		request.getSession().setAttribute("formasPagoCliente", formasPago);
		return SUCCESS;
	}
}