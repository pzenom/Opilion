package es.induserco.opilion.presentacion.entidad;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.banca.DatoBancario;
import es.induserco.opilion.data.comun.contacto.Email;
import es.induserco.opilion.data.comun.contacto.Ruta;
import es.induserco.opilion.data.comun.contacto.Telefono;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionUsuariosHelper;

public class CargarEntidadAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Entidad entidadRecuperada = new Entidad();
	private String [] listadiasentregaselected;

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel() {
		System.out.println("Por aqui anda!! ");
		return entidadRecuperada;
	}

	public String execute() throws Exception {	
		request.getSession().setAttribute("idUsuario", entidadRecuperada.getIdUsuario());
		request.getSession().setAttribute("fecharegistro",(new GestionEntidadesHelper()).getFechaRegistro());
		request.getSession().setAttribute("listaformaspago",(new GestionEntidadesHelper()).getFormasPago());
		request.getSession().setAttribute("listaformaspagodesde",(new GestionEntidadesHelper()).getFormasPagoDesde());
		request.getSession().setAttribute("listaprovincias",(new GestionEntidadesHelper()).getProvincias());
		request.getSession().setAttribute("listasectores",(new GestionEntidadesHelper()).getListaSectores());
		request.getSession().setAttribute("listatipos",(new GestionEntidadesHelper()).getListaTiposProveedores());
		request.getSession().setAttribute("listabancos",(new GestionEntidadesHelper()).getListaBancos());
		request.getSession().setAttribute("listareqhomologa",(new GestionEntidadesHelper()).getListaReqHomologa());
		request.getSession().setAttribute("listacriterios",(new GestionEntidadesHelper()).getListaReqHomologa(entidadRecuperada));
		
		Entidad entidad = new GestionEntidadesHelper().loadEntidad(entidadRecuperada);
		request.getSession().setAttribute("entidadCargada", entidad);
		request.getSession().setAttribute("direcciones", entidad.getDirecciones());
		Vector<Telefono> telefonos = entidad.getTelefonos();
		request.getSession().setAttribute("telefonos", telefonos);
		Vector<Email> emails = entidad.getEmails();
		request.getSession().setAttribute("emails", emails);
		Vector<DatoBancario> formasPago = entidad.getFormasPagoEntidad();
		request.getSession().setAttribute("formasPago", formasPago);
		
		request.getSession().setAttribute("envio", "E");
		request.getSession().setAttribute("facturacion", "F");
		request.getSession().setAttribute("ambas", "A");

		Vector<Ruta> rutas = new GestionUsuariosHelper().getRutas();
		Vector<Usuario> comerciales = new GestionUsuariosHelper().getComerciales();
		request.getSession().setAttribute("rutas", rutas);
		request.getSession().setAttribute("comerciales", comerciales);
		
		System.out.println("Escogido " + entidadRecuperada.getNombre());
		return (SUCCESS);
	}

	public void setListadiasentregaselected(String [] listadiasentregaselected) {
		this.listadiasentregaselected = listadiasentregaselected;
	}

	public String [] getListadiasentregaselected() {
		return listadiasentregaselected;
	}
}