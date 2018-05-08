package es.induserco.opilion.presentacion.registroentrada;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import groovyjarjarantlr.collections.List;

// TODO: Auto-generated Javadoc
/**
 * The Class LoadRETmpAction.
 */
public class LoadRETmpAction extends ActionSupport implements
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
		System.out.println("Registro Entrada Load Tmp Action...");
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
		try {
			//Colocamos las listas de datos en la request.
			System.out.println("El codigo entrada es: " + request.getAttribute("codigoEntrada"));
			request.getSession().setAttribute("codigoEntrada",(request.getAttribute("codigoEntrada")));
			entry=(new GestionRegistroEntradaHelper()).getOrdenRegistroTmp((String)request.getAttribute("codigoEntrada"));
			request.getSession().setAttribute("fecharegistro",entry.getFechaLlegada());
			request.getSession().setAttribute("fechacaducidad",entry.getFechaCaducidad());
			System.out.println("LOADRETMPACTION FCaducidad:" +entry.getFechaCaducidad());
			System.out.println("LOADRETMPACTION FLlegada:" +entry.getFechaLlegada());
			System.out.println("LOADRETMPACTION FRegistro:" +entry.getFecha());
			/*entry.setCodigoEntrada((String)request.getAttribute("codigoEntrada"));
			//request.getSession().setAttribute("fecharegistro",(new GestionRegistroEntradaHelper()).getFechaRegistro());
			request.getSession().setAttribute("fecharegistro",(new GestionRegistroEntradaHelper()).getFechaRegistroTmp(entry.getCodigoEntrada()));
			System.out.println("LOADRETMPACTION FRegistroXXX:" +entry.getFecha());
			request.getSession().setAttribute("fechacaducidad",(new GestionRegistroEntradaHelper()).getFechaCaducidadTmp());	
			//request.getSession().setAttribute("fechacaducidad",(new GestionRegistroEntradaHelper()).getFechaCaducidad());		
			*/	
			// Se guarda en la request los datos del RE y sus incidencias
			request.getSession().setAttribute("listaregistros",(new GestionRegistroEntradaHelper()).loadRegistroEntradaTmp(entry));
			request.getSession().setAttribute("listaestadostmp", (new GestionRegistroEntradaHelper()).loadEstadoFabasTmp(entry));
			request.getSession().setAttribute("listaincidenciastmp", (new GestionRegistroEntradaHelper()).loadIncidenciasTmp(entry));
					
			//Levantamos el evento success para especificar que todo esta bien
			return (SUCCESS);	 
		}
		
		catch (Exception e) {
			return (ERROR);
		}
	}
}