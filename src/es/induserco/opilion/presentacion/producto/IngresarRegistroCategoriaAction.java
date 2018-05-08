package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.GrupoProducto;
import es.induserco.opilion.data.comun.contacto.Sector;
import es.induserco.opilion.data.entidades.Categoria;
import es.induserco.opilion.data.entidades.Familia;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class IngresarRegistroCategoriaAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private Categoria categoria = new Categoria();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("IngresarRegistroCategoriaAction...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return categoria;
	}

	public String execute() throws Exception {
		new GestionProductosHelper().registrarCategoria(categoria);
		//Levantamos el evento success para especificar que todo esta bien
		return (SUCCESS);
	}
}