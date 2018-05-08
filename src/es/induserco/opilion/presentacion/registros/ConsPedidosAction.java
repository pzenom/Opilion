package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

// TODO: Auto-generated Javadoc
/**
 * Action que ejecuta la consulta de acuerdo a los parametros de
 * FiltAlbaAction.
 */
public class ConsPedidosAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	/** The request. */
	private HttpServletRequest request;
	
	/** The albaran. */
	private Albaran albaran= new Albaran();
	
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
		System.out.println("Consulta Pedidos");
		// TODO Auto-generated method stub
		return albaran;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {

		String fechaInicio = (String)request.getParameter("fechaInicio");
		String fechaFin = (String)request.getParameter("fechaFin");
		System.out.println("Fecha usable para busqueda "+ fechaInicio + "y " + fechaFin);

		//Lista de clientes
		//request.getSession().setAttribute("listaclientes",(new GestionRegistroSalidaHelper()).getClientes());
		//Lista de albaranes recuperados
		//request.getSession().setAttribute("listapedidos",(new GestionRegistroSalidaHelper()).getPedidos(fechaInicio,fechaFin));
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}
}

