package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class EditDetaFactAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Albaran albaran = new Albaran();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Editar detalles factura Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return albaran;
	}

	public String execute() throws Exception {
		request.getSession().setAttribute("factura", new GestionRegistroSalidaHelper().getNuevaFactura());
		Albaran a = new GestionRegistroSalidaHelper().getAlbaran(albaran.getCodigoAlbaran(), true);
		request.getSession().setAttribute("albaran", a);
		request.getSession().setAttribute("listaivas", new GestionProductosHelper().getImpuestos());
		Entidad e = new Entidad();
		e.setIdUsuario(a.getIdCliente());
		request.getSession().setAttribute("listaDireccionesFacturacion", new GestionEntidadesHelper().getDireccionesEntidad(e, "F"));
		Entidad entidad = new GestionEntidadesHelper().loadEntidad(e);
		request.getSession().setAttribute("formasDePago", entidad.getFormasPagoEntidad());
		request.getSession().setAttribute("telefonos", entidad.getTelefonos());
		return SUCCESS;
	}
}