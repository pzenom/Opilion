package es.induserco.opilion.presentacion.proveedor;

import java.io.UnsupportedEncodingException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.comun.contacto.Email;
import es.induserco.opilion.data.comun.contacto.Telefono;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class CargarProveedorAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	//lo que se tiene aqui se tiene que recuperar aqui nada m�s
	private HttpServletRequest request;
	private Entidad entidadRecuperada = new Entidad();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
		try{
			request.setCharacterEncoding("UTF-8");
		}
		catch (UnsupportedEncodingException ex) {}
	}
	
	//@Override
	public Object getModel() {	
		//System.out.println("Cargar actualizar!: "+entidad.getNombre());		
		return entidadRecuperada;
	}

	public String execute() throws Exception {	
		request.getSession().setAttribute("idUsuario",(request.getAttribute("idUsuario")));
		entidadRecuperada.setIdUsuario((Long)request.getAttribute("idUsuario"));
		request.getSession().setAttribute("fecharegistro",(new GestionEntidadesHelper()).getFechaRegistro());
		request.getSession().setAttribute("listaformaspago",(new GestionEntidadesHelper()).getFormasPago());
		request.getSession().setAttribute("listaprovincias",(new GestionEntidadesHelper()).getProvincias());
		request.getSession().setAttribute("listatipos",(new GestionEntidadesHelper()).getListaTiposProveedores());		
		request.getSession().setAttribute("listabancos",(new GestionEntidadesHelper()).getListaBancos());
		request.getSession().setAttribute("listareqhomologa",(new GestionEntidadesHelper()).getListaReqHomologa());
		//request.getSession().setAttribute("criterios",(new GestionEntidadesHelper()).getListaReqHomologa());
		request.getSession().setAttribute("listacriterios",(new GestionEntidadesHelper()).getListaReqHomologa(entidadRecuperada));
		
		Entidad proveedor = new GestionEntidadesHelper().loadEntidad(entidadRecuperada);
		request.getSession().setAttribute("listaregistros", proveedor);
		request.getSession().setAttribute("listaDireccionesEntrega", proveedor.getDirecciones());//getDireccionesEntrega());
		
		Vector<Direccion> dire = proveedor.getDirecciones();
		request.getSession().setAttribute("listaDireccionesContacto", dire);
		
		Vector<Telefono> telefonos = proveedor.getTelefonos();
		request.getSession().setAttribute("telefonos", telefonos);
		
		Vector<Email> emails = proveedor.getEmails();
		request.getSession().setAttribute("emails", emails);
		
		request.getSession().setAttribute("envio", "E");
		request.getSession().setAttribute("facturacion", "F");
		
		System.out.println("Escogido "+entidadRecuperada.getIdUsuario());
		return (SUCCESS);
	}
}