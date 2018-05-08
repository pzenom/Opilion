package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class IngresarUbicacionAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion entry = new Ubicacion();

	//@Override
	public Object getModel() {	
		System.out.println("Agregar proveedor!: "+entry.getIdUbicacion());		
		return entry;
	}

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	public String execute() throws Exception 
	{	
		//Usuario usuario = (Entidad)request.getSession().getAttribute("usuario");
		//System.out.println("Logueado el usuario:"+usuario.getLogin());
		System.out.println("Registro de ubicacion...");
		System.out.println(" El id es:" + entry.getIdUbicacion());
		new GestionUbicacionesHelper().addUbicacion(entry);		
		return SUCCESS;
	}

	public Long getIdUbicacion() {return entry.getIdUbicacion();}
	public void setIdUbicacion(Long idUbicacion) {request.setAttribute("idUbicacion", entry.getIdUbicacion());}
}