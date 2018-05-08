package es.induserco.edifact.presentacion.orders;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class DireccionesClienteAction extends ActionSupport implements
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
		System.out.println("DireccionesClienteAction");
		Vector<Direccion> direcciones = new GestionEntidadesHelper().getDireccionesEntidad(cliente, "");
		request.getSession().setAttribute("direccionesCliente", direcciones);
		return SUCCESS;
	}
}