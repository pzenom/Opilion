package es.induserco.opilion.presentacion.cliente;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.banca.Banco;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class IngresarRegistroBancoAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private Banco banco = new Banco();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("IngresarRegistroBancoAction...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return banco;
	}

	public String execute() throws Exception {
		new GestionEntidadesHelper().registrarBanco(banco);
		//Levantamos el evento success para especificar que todo esta bien
		return (SUCCESS);
	}
}