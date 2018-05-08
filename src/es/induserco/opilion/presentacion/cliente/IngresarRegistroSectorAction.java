package es.induserco.opilion.presentacion.cliente;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.contacto.Sector;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class IngresarRegistroSectorAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private Sector sector = new Sector();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("IngresarRegistroSectorAction...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return sector;
	}

	public String execute() throws Exception {
		new GestionEntidadesHelper().registrarSector(sector);
		//Levantamos el evento success para especificar que todo esta bien
		return (SUCCESS);
	}
}