package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class EditDetaAlbaAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

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
		Albaran alba = new GestionRegistroSalidaHelper().getAlbaran(albaran.getCodigoAlbaran(), true);
		request.getSession().setAttribute("albaran", alba);
		Entidad e = new Entidad();
		e.setIdUsuario(alba.getIdCliente());
		Entidad entidad = new GestionEntidadesHelper().loadEntidad(e);
		request.getSession().setAttribute("direcciones", entidad.getDirecciones());
		request.getSession().setAttribute("direccionesEntrega", entidad.getDireccionesEntrega());
		request.getSession().setAttribute("direccionesFacturacion", entidad.getDireccionesFacturacion());
		request.getSession().setAttribute("listaTemperaturas", new GestionRegistroSalidaHelper().getTemperaturasTransporte());
		request.getSession().setAttribute("listaPortes", new GestionRegistroSalidaHelper().getPortesTransporte());
		request.getSession().setAttribute("telefonos", entidad.getTelefonos());
		request.getSession().setAttribute("formasDePago", entidad.getFormasPagoEntidad());
		request.getSession().setAttribute("listatransportistas", new GestionRegistroEntradaHelper().getTransportistas());
		return (SUCCESS);
	}
}