package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionEnvasesHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class RegistroProductoAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven {

	private HttpServletRequest request;
	private Producto entry = new Producto();

	//@Override
	public Object getModel() {	
		System.out.println("Agregar producto!: "+entry.getNombre());		
		return entry;
	}

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	public String execute() throws Exception {	
		//Colocamos las listas de datos en la request.
		request.getSession().setAttribute("fecharegistro",(new GestionEntidadesHelper()).getFechaRegistro());
		request.getSession().setAttribute("listacategorias",(new GestionProductosHelper()).getCategorias());
		request.getSession().setAttribute("listaestados",(new GestionProductosHelper()).getEstados());
		request.getSession().setAttribute("listatipos",(new GestionProductosHelper()).getTipoProducto());
		request.getSession().setAttribute("listagrupos",(new GestionProductosHelper()).getGrupoProducto());
		request.getSession().setAttribute("listamateriaprima",(new GestionProductosHelper()).getMateriasPrimas(0));
		request.getSession().setAttribute("listafamilias",(new GestionProductosHelper()).getFamilias());		

		request.getSession().setAttribute("listabolsas",(new GestionEnvasesHelper()).getEnvases(1L));
		request.getSession().setAttribute("listasacos",(new GestionEnvasesHelper()).getEnvases(2L));		
		request.getSession().setAttribute("listacarton",(new GestionEnvasesHelper()).getEnvases(3L));
		request.getSession().setAttribute("listasaquetes",(new GestionEnvasesHelper()).getEnvases(4L));		
		request.getSession().setAttribute("listamadera",(new GestionEnvasesHelper()).getEnvases(5L));
		request.getSession().setAttribute("listaotros",(new GestionEnvasesHelper()).getEnvases(6L));		

		return SUCCESS;
	}

	public String getNombre() {return entry.getNombre();}
	public void setNombre(String nombre) {request.setAttribute("nombre", entry.getNombre());}
}