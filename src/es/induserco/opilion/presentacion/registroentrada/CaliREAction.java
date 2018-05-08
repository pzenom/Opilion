package es.induserco.opilion.presentacion.registroentrada;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.RegistroCalidad;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;

// TODO: Auto-generated Javadoc
/**
 * The Class CaliREAction.
 */
public class CaliREAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The entry. */
	private RegistroEntrada entry = new RegistroEntrada();
	

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Registro Entrada Update Action...");
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
		//Colocamos las listas de datos en la request.
		request.getSession().setAttribute("codigoEntrada",(request.getAttribute("codigoEntrada")));
		entry.setCodigoEntrada((String)request.getAttribute("codigoEntrada"));
		//Levantamos el evento success para especificar que todo esta bien
		System.out.println("CodigoEntrada..."+entry.getCodigoEntrada());
		
		Vector <RegistroCalidad> historico =new GestionRegistroEntradaHelper().getRegistrosCalidad(entry.getCodigoEntrada());
		request.getSession().setAttribute("historico",historico);	
		
		return (SUCCESS);
	}
}
