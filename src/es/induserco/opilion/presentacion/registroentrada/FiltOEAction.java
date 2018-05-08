package es.induserco.opilion.presentacion.registroentrada;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;


public class FiltOEAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private Entidad entidad= new Entidad();
	
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	public Object getModel() {
		System.out.println("FiltOEAction");
		return entidad;
	}

	public String execute() throws Exception {
		//Colocamos la lista de los filtros para la consulta
		Vector vacio = new Vector();
		request.getSession().setAttribute("listaproveedores",(new GestionRegistroEntradaHelper()).getProveedores());
		request.getSession().setAttribute("listaordenes", vacio);
		//request.getSession().setAttribute("listaordenes",(new GestionRegistroEntradaHelper()).getOrdenes());
		return (SUCCESS);
	}
}