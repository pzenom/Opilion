package es.induserco.opilion.presentacion.registroentrada;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class LoadREAction.
 */
public class LoadREAction extends ActionSupport implements
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
		System.out.println("Registro Entrada Load Action...");
		this.request=request;	
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {		
		return entry;
	}
	
	/*public String execute() throws Exception {
		try {
			//Colocamos las listas de datos en la request.
			request.getSession().setAttribute("codigoEntrada",(request.getAttribute("codigoEntrada")));
			entry.setCodigoEntrada((String)request.getAttribute("codigoEntrada"));
		
			request.getSession().setAttribute("fecharegistro",(new GestionRegistroEntradaHelper()).getFechaRegistro());
			request.getSession().setAttribute("fechacaducidad",(new GestionRegistroEntradaHelper()).getFechaCaducidad());		
			request.getSession().setAttribute("listaproveedores",(new GestionRegistroEntradaHelper()).getProveedores());
			request.getSession().setAttribute("listaproductos",(new GestionRegistroEntradaHelper()).getProductos());
			request.getSession().setAttribute("listacategorias",(new GestionRegistroEntradaHelper()).getCategorias());
			request.getSession().setAttribute("listavehiculos",(new GestionRegistroEntradaHelper()).getVehiculos());
			request.getSession().setAttribute("listaincidencias",(new GestionRegistroEntradaHelper()).getIncidencias());
			request.getSession().setAttribute("listaformatos",(new GestionRegistroEntradaHelper()).getFormatos());
			request.getSession().setAttribute("listaestadosfabas",(new GestionRegistroEntradaHelper()).getEstadosFabas());		
			request.getSession().setAttribute("listacosechas",(new GestionRegistroEntradaHelper()).getCosechas());
			request.getSession().setAttribute("listaenvases",(new GestionRegistroEntradaHelper()).getEnvases());
		
			request.getSession().setAttribute("listaregistrosdel",(new GestionRegistroEntradaHelper()).loadRegistroEntrada(entry));		
			//Levantamos el evento success para especificar que todo esta bien
			return (SUCCESS);	 
		}
		
		catch (Exception e) {
			return (ERROR);
		}
	}*/
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		try {
			//Colocamos las listas de datos en la request.
			System.out.println("El codigo entrada es: " + request.getAttribute("codigoEntrada"));
			request.getSession().setAttribute("codigoEntrada",(request.getAttribute("codigoEntrada")));
			entry.setCodigoEntrada((String)request.getAttribute("codigoEntrada"));
							
			request.getSession().setAttribute("fecharegistro",(new GestionRegistroEntradaHelper()).getFechaRegistro((String) request.getAttribute("codigoEntrada")));
			request.getSession().setAttribute("fechacaducidad",(new GestionRegistroEntradaHelper()).getFechaCaducidad((String) request.getAttribute("codigoEntrada")));		
					
			// Se guarda en la request los datos del RE y sus incidencias
			request.getSession().setAttribute("listaregistros",(new GestionRegistroEntradaHelper()).loadRegistroEntrada(entry));
			request.getSession().setAttribute("listaestadostmp", (new GestionRegistroEntradaHelper()).loadEstadoFabas(entry));
			request.getSession().setAttribute("listaincidenciastmp", (new GestionRegistroEntradaHelper()).loadIncidencias(entry));
					
			//Levantamos el evento success para especificar que todo esta bien
			return (SUCCESS);	 
		}
		
		catch (Exception e) {
			return (ERROR);
		}
	}
}


