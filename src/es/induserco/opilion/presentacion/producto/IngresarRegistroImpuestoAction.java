package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.GrupoProducto;
import es.induserco.opilion.data.comun.contacto.Sector;
import es.induserco.opilion.data.entidades.Categoria;
import es.induserco.opilion.data.entidades.Familia;
import es.induserco.opilion.data.entidades.Impuesto;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class IngresarRegistroImpuestoAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private Impuesto impuesto = new Impuesto();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("IngresarRegistroImpuestoAction...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return impuesto;
	}

	public String execute() throws Exception {
		new GestionProductosHelper().registrarImpuesto(impuesto);
		//Levantamos el evento success para especificar que todo esta bien
		return (SUCCESS);
	}
}