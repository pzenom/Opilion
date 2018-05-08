package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class EditCantENAction.
 */
public class EditCantENAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The entry. */
	private GestionProduccion entry = new GestionProduccion();

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("EditCantEN Action...");
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
		//Recupero el cod de entrada enviado en el link de c�digo
		request.getSession().setAttribute("orden",(request.getAttribute("orden")));
		entry.setOrden((String)request.getAttribute("orden"));		

		//recupera n�mero de albar�n y tipo de albaran O si es de orden 
		String albaran=(String)request.getParameter("albaran");		
		String tipo=(String)request.getParameter("tipo");				
		
		//sube a la request
		request.getSession().setAttribute("albaran",albaran);		
		request.getSession().setAttribute("tipo",tipo);
		
		//recupera de la request
		request.getSession().getAttribute("linNum");
		request.getSession().getAttribute("qty21Cant");		
		
		System.out.println("la orden es "+entry.getOrden());
		System.out.println("el albaran2 es "+albaran);		
		System.out.println("el tipo de albaran es "+tipo);
		System.out.println("el numero de linea en editcant es "+(String)request.getSession().getAttribute("linNum"));
		System.out.println("la cantidad a despachar es "+(String)request.getSession().getAttribute("qty21Cant"));		
		
		//recupera el detalle de la orden de envasado
		request.getSession().setAttribute("registroorden",(new GestionRegistroEntradaHelper()).getREENDetalle(entry.getOrden()));		
		return (SUCCESS);
	}
}
