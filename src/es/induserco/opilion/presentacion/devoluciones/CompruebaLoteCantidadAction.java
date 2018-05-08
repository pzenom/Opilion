package es.induserco.opilion.presentacion.devoluciones;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

/**
 * Comprueba si existe el lote, y si para ese lote tenemos la cantidad seleccionada
 * @author andres (23/08/2012)
 * @version 1.0
 */
public class CompruebaLoteCantidadAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Lote lote = new Lote();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	//@Override
	public Object getModel() {
		return lote;
	}

	public String execute() throws Exception {
		System.out.println("CompruebaLoteCantidadAction...");
		System.out.println("Comprobar el lote " + lote.getLote() + ", cantidad: " + lote.getCantidad());
		char resultado = ' ';//O = OK; N = No existe el lote; C = No hay tanta Cantidad para ese lote;
		resultado = new GestionEnvasadoHelper().compruebaLoteCantidad(lote);
		request.getSession().setAttribute("comprobacion", resultado);
		return SUCCESS;
	}
}