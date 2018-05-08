package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class EditFactAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Factura factura = new Factura();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Editar Albaran Action...");
		this.request = request;	
	}

	//@Override
	public Object getModel() {		
		return factura;
	}

	public String execute() throws Exception {
		Factura f = new GestionRegistroSalidaHelper().getFactura(factura.getCodigoFactura());
		Entidad e = new Entidad();
		e.setIdUsuario(f.getIdCliente());
		request.getSession().setAttribute("listaDireccionesFacturacion", new GestionEntidadesHelper().getDireccionesEntidad(e, "F"));
		Entidad entidad = new GestionEntidadesHelper().loadEntidad(e);
		request.getSession().setAttribute("formasDePago", entidad.getFormasPagoEntidad());
		request.getSession().setAttribute("telefonos", entidad.getTelefonos());
		request.getSession().setAttribute("factura", f);
		request.getSession().setAttribute("listaivas", new GestionProductosHelper().getImpuestos());
		return SUCCESS;
	}
}