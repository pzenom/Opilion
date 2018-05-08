package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.GrupoProducto;
import es.induserco.opilion.data.comun.contacto.Sector;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class IngresarRegistroGrupoProductoAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private GrupoProducto grupo = new GrupoProducto();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("IngresarRegistroGrupoProductoAction...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return grupo;
	}

	public String execute() throws Exception {
		new GestionProductosHelper().registrarGrupoProducto(grupo);
		//Levantamos el evento success para especificar que todo esta bien
		return (SUCCESS);
	}
}