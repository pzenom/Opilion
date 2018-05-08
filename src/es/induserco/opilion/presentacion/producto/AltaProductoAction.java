package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionEnvasesHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class AltaProductoAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{
	private static final long serialVersionUID = 1L;
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

	public String execute() throws Exception{
		request.getSession().setAttribute("listacategorias", (new GestionProductosHelper()).getCategorias());
		request.getSession().setAttribute("listafamilias",(new GestionProductosHelper()).getFamilias());		
		request.getSession().setAttribute("listatipos",(new GestionProductosHelper()).getTipoProducto());
		request.getSession().setAttribute("listagrupos",(new GestionProductosHelper()).getGrupoProducto());
		request.getSession().setAttribute("listaimpuestos", (new GestionProductosHelper()).getImpuestos());

		request.getSession().setAttribute("listaCategorizaciones", new GestionProductosHelper().getGrupoProducto());
		request.getSession().setAttribute("listamateriaprima",(new GestionProductosHelper()).getMateriasPrimas(0));
		request.getSession().setAttribute("listaenvases",(new GestionEnvasesHelper()).getEnvases(-1L));		
		
		request.getSession().setAttribute("productosCompuesto", (new GestionProductosHelper().getProductos(null, false)));

		return SUCCESS;
	}

	public String getNombre() {return entry.getNombre();}
	public void setNombre(String nombre) {request.setAttribute("nombre", entry.getNombre());}
}