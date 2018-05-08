package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class IngresarRegistroSalidaAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The exit. */
	private RegistroSalida exit = new RegistroSalida();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Ingresar Registro Salida Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return exit;
	}

	public String execute() throws Exception {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		exit.setIdOperario(us.getLogin());
		new GestionRegistroSalidaHelper().addRegistroSalida(exit); 
		return SUCCESS;
	}
}