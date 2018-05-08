package es.induserco.opilion.presentacion.registroentrada;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroUbicacion;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class InseRUEAction.
 */
public class InseRUEAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
	{
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The entry. */
	private RegistroUbicacion entry = new RegistroUbicacion();
	
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Ingresar Registro Entrada Action...");
		this.request=request;	
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {
		return entry;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
	
		//Recoge el usuario
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		entry.setResponsable(us.getLogin());
		
		//Recoge la zona
		String zonita= (String)request.getParameter("zona");
		System.out.println("zonaa correcta por el get parameter es "+zonita);
		Integer zonaInt = Integer.parseInt(zonita);
		entry.setIdUbicacion(zonaInt);
		
		//Recoge el codigo de entrada
		request.getSession().setAttribute("codigoEntrada",(request.getAttribute("codigoEntrada")));
		entry.setCodigoEntrada((String)request.getAttribute("codigoEntrada"));
		
		System.out.println("zona es "+entry.getIdUbicacion());
		System.out.println("codigo entrada es :"+entry.getCodigoEntrada());
		new GestionUbicacionesHelper().addRegistroUbicacion(entry); 
		return SUCCESS;
	}

}
