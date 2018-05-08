package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.negocio.RegistroSalidaDataHelper;


/**
 * Inserta un nuevo albarán en la base de datos
 * @author andres (09/05/2011)
 * @version 0.3
 */
public class CompruebaLotesLeidosAlbaranAction extends ActionSupport implements
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
		System.out.println("CompruebaLotesLeidosAlbaranAction...");
		System.out.println("Comprobar el lote " + lote.getLote() +
				", cantidad: " + lote.getCantidad() +
				", codigoEan: " + lote.getIdProducto());
		char resultado = ' ';//O = OK; N = No existe el lote; C = el lote no corresponde con el producto; S = Stock insuficiente
		resultado = new RegistroSalidaDataHelper().comprobarLoteLeido(lote);
		request.getSession().setAttribute("comprobacion", resultado);
		return SUCCESS;
	}
}