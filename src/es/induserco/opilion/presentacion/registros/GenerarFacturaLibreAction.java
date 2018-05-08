package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class GenerarFacturaLibreAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Albaran albaran = new Albaran();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Albaran Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return albaran;
	}

	public String execute() throws Exception {
		request.getSession().setAttribute("listaclientes", (new GestionRegistroSalidaHelper()).getClientes());
		Factura nuevaFactura = new GestionRegistroSalidaHelper().getNuevaFactura();
		request.getSession().setAttribute("numeroFactura", nuevaFactura.getIdFactura());
		request.getSession().setAttribute("codigoFactura", nuevaFactura.getCodigoFactura());
		return (SUCCESS);
	}
}