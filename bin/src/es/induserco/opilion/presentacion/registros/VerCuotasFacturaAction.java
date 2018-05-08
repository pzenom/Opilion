package es.induserco.opilion.presentacion.registros;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.data.entidades.CuotaFactura;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class VerCuotasFacturaAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = -622643281402760772L;
	private HttpServletRequest request;
	private Factura factura = new Factura();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("VerCuotasFacturaAction Action...");
		this.request = request;	
	}

	//@Override
	public Object getModel() {		
		return factura;
	}

	public String execute() throws Exception {
		List<CuotaFactura> cuotas =
			(List<CuotaFactura>) new GestionRegistroSalidaHelper().getFactura(factura.getCodigoFactura()).getCuotas();
		request.getSession().setAttribute("cuotas", cuotas);
		request.getSession().setAttribute("codigoFactura", factura.getCodigoFactura());
		return SUCCESS;
	}
}