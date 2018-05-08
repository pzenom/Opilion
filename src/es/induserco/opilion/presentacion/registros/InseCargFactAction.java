package es.induserco.opilion.presentacion.registros;

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

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.datos.salida.EscribirFichero;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class InseCargFactAction.
 */
public class InseCargFactAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The albaran. */
	private Albaran albaran = new Albaran();
	
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Insertar Cargos Factura Action...");
		this.request=request;	
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {		
		return albaran;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		String totalCargoTran =(String)request.getParameter("totalCargoTran");
		String totalCargoBanc =(String)request.getParameter("totalCargoBanc");
		String totalCargoDevo =(String)request.getParameter("totalCargoDevo");		
		request.getSession().setAttribute("totalCargoTran",totalCargoTran);
		request.getSession().setAttribute("totalCargoBanc",totalCargoBanc);
		request.getSession().setAttribute("totalCargoDevo",totalCargoDevo);
		
		//Recoge valores Totales
		String cargosTotal =(String)request.getParameter("cargosTotal");
		System.out.println("Cargos totales :"+cargosTotal);
		request.getSession().setAttribute("cargostotal",cargosTotal);
		return SUCCESS;
	}
}

