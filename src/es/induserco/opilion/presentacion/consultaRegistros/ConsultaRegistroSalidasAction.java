package es.induserco.opilion.presentacion.consultaRegistros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsultaRegistroSalidasAction.
 */
public class ConsultaRegistroSalidasAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	/** The request. */
	private HttpServletRequest request;
	
	/** The exit. */
	private RegistroSalida exit= new RegistroSalida();
	
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta Registro Salida!");
		// TODO Auto-generated method stub
		return exit;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		System.out.println("Fecha usable para busqueda "+ exit.getFecha());
		//Colocamos la lista de entradaes en la request.
		request.getSession().setAttribute("listasalidas",(new GestionRegistroSalidaHelper()).getRegistroSalidas("", exit.getCodigoSalida(),exit.getFecha(),exit.getIdProducto()));
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}

}

