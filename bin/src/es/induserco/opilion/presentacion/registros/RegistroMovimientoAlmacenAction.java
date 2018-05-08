package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistroMovimientoAlmacenAction.
 */
public class RegistroMovimientoAlmacenAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The usuario. */
	private Usuario usuario = new Usuario();

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub	
		System.out.println("Registro Movimiento Almacen Action...");
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {		
		return usuario;
	}
}