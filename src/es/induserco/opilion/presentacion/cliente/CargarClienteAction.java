package es.induserco.opilion.presentacion.cliente;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class CargarClienteAction extends ActionSupport implements
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
		request.getSession().setAttribute("listaprovincias",(new GestionEntidadesHelper()).getProvincias());
		
		//request.getSession().setAttribute("listadiasentrega",(new GestionEntidadesHelper()).getListaDiasEntregaCliente(entidadRecuperada));
		request.getSession().setAttribute("listarutas",(new GestionEntidadesHelper()).getListaRutas());
		request.getSession().setAttribute("listasectores",(new GestionEntidadesHelper()).getListaSectores());
		request.getSession().setAttribute("listabancos",(new GestionEntidadesHelper()).getListaBancos());
		/*Entidad cliente = new GestionEntidadesHelper().loadCliente(entidadRecuperada);
		request.getSession().setAttribute("listaregistros", cliente);
		request.getSession().setAttribute("listaDireccionesEntrega", cliente.getDirecciones());//getDireccionesEntrega
		Vector<Direccion> dire = cliente.getDirecciones();
		request.getSession().setAttribute("listaDireccionesContacto", dire);
		
		Vector<Telefono> telefonos = cliente.getTelefonos();
		request.getSession().setAttribute("telefonos", telefonos);
		
		Vector<Email> emails = cliente.getEmails();
		request.getSession().setAttribute("emails", emails);
		*/
		request.getSession().setAttribute("envio", "E");
		request.getSession().setAttribute("facturacion", "F");
		
		/*DatoBancario ref = cliente.getDatosBancarios();
		request.getSession().setAttribute("banco", ref);*/
		
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