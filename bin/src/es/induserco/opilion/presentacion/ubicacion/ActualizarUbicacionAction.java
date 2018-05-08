package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class ActualizarUbicacionAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion entryFind = new Ubicacion();
	private Ubicacion entryActualiza = new Ubicacion();	

	//@Override
	public Object getModel() {	
		return entryActualiza;
	}

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	public String execute() throws Exception 
	{	
		entryFind.setIdUbicacion((Long)request.getSession().getAttribute("idUbicacion"));
		new GestionUbicacionesHelper().updateUbicacion(entryFind, entryActualiza);		
		request.getSession().setAttribute("listaregistros",(new GestionUbicacionesHelper()).loadUbicacion(entryFind));
		return SUCCESS;
	}
}