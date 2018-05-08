package es.induserco.opilion.presentacion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.data.entidades.GestionProduccion;

public class ActualizarFechaEventoAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = -622643281402760772L;
	private HttpServletRequest request;
	private Factura factura = new Factura();
	
	public void setServletRequest(HttpServletRequest request) { this.request = request;	}
	public Object getModel() { return factura; }

	public String execute() throws Exception {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		String diasDesplazados = (String)request.getParameter("diasDesplazamiento");
		String codigoEvento = (String)request.getParameter("codigoEvento");
		if (codigoEvento.charAt(0) == 'F'){
			//Factura
			//Inserta la factura
			factura.setCodigoFactura(codigoEvento);
			factura.setFechaVencimiento(diasDesplazados);
			factura.setUsuarioResponsable(us.getLogin());
			new GestionRegistroSalidaHelper().updateFechaVencimientoFactura(factura);
		}else{
			if (codigoEvento.charAt(0) == 'E' && codigoEvento.charAt(1) == 'N'){
				//Envasado
				GestionProduccion gpro = new GestionProduccion();
				gpro.setOrden(codigoEvento);
				gpro.setFechaProgramada(diasDesplazados);
				gpro.setUsuarioResponsable(us.getLogin());
				new GestionEnvasadoHelper().updateFechaProgramada(gpro);
			}
		}
		return SUCCESS;
	}
}