package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class IngresarRegistroProductoAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Producto entry = new Producto();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		System.out.println("Ingresar Producto Action...");
		this.request = httpServletRequest;			
	}

	//@Override
	public Object getModel() {	
		return entry;
	}

	public String execute() throws Exception{
		Boolean estado = false;
		System.out.println("Registro de producto...");
		System.out.println("dato combo idcategoria action ingresar "+entry.getIdCategoria());
		System.out.println("dato combo idestado action ingresar "+entry.getIdEstado());		
		estado = new GestionProductosHelper().addProducto(entry, false, 0);
			if(estado.equals(Boolean.TRUE))
		return SUCCESS;
			else
		return ERROR;
	}
}