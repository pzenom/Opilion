package es.induserco.opilion.presentacion.registros;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.edifact.data.OrdersLin;
import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class MuestraDireccionesAlbaranAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven {

	private static final long serialVersionUID = 422840826723506717L;
	private HttpServletRequest request;
	private Albaran albaran = new Albaran();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("ListaLineasPedidoDireccionAction Action...");
		this.request = request;	
	} 

	//@Override
	public Object getModel() {		
		return albaran;
	}

 	public String execute() throws IOException {
		try {			
			Vector<Direccion> direcciones = new GestionRegistroSalidaHelper().getDireccionesAlbaran(albaran);
			request.getSession().setAttribute("direcciones", direcciones);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;	 
	 }
}