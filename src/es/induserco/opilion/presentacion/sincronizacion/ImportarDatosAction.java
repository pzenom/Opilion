package es.induserco.opilion.presentacion.sincronizacion;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.negocio.GestionesEspecialesDataHelper;
import es.induserco.opilion.presentacion.GestionesEspecialesHelper;

/**
 * Exporta la base de datos a un fichero
 * @author andres (20/05/2011)
 * @version 0.1
 */
public class ImportarDatosAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	
	//@Override
	public void setServletRequest(HttpServletRequest request) { this.setRequest(request); }

	//@Override
	public Object getModel() {
		System.out.println("Importar datos");
		return null;
	}
	
	public String execute() throws Exception {
		System.out.println("Execute importar datos");
		Vector<String> ficherosXML = new GestionesEspecialesHelper().getFicherosXML();
		String MY_XML = ficherosXML.get(0), MY_SCHEMA = ficherosXML.get(1);
		boolean ficheroValidado = new GestionesEspecialesHelper().validarXML(MY_XML, MY_SCHEMA);
		if (ficheroValidado){
			new GestionesEspecialesHelper().importarClientes(MY_XML);
			new GestionesEspecialesHelper().importarPedidos(MY_XML);
			new GestionesEspecialesHelper().importarAlbaranes(MY_XML);
			new GestionesEspecialesHelper().importarDevoluciones(MY_XML);
		}
		return ("SUCCESS");
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
}