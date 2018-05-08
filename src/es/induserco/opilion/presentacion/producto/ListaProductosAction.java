package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class ListaProductosAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{	

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Producto entry = new Producto();
	
	public String execute() throws Exception {	
		//carga los listados para los filtros de los combos
		request.getSession().setAttribute("listacategorias",(new GestionProductosHelper()).getCategorias());
		request.getSession().setAttribute("listaestados",(new GestionProductosHelper()).getEstados());
		request.getSession().setAttribute("listafamilias",(new GestionProductosHelper()).getFamilias());		
		request.getSession().setAttribute("listatipos",(new GestionProductosHelper()).getTipoProducto());
		request.getSession().setAttribute("listagrupos",(new GestionProductosHelper()).getGrupoProducto());
		//para el caso hipotético de volver sin asegurarse 
		entry.setIdCategoria(new Long(0));
		entry.setIdEstado(new Long(0));
		entry.setIdFamilia(new Long(0));
		entry.setIdTipo(new Long(0));
		entry.setIdGrupo(new Long(0));
		//si muestro esto, estoy devolviendo productos de mano
		request.getSession().setAttribute("listaproductos", (new GestionProductosHelper()).getProductos(entry, false));
		return (SUCCESS);
	}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) { this.request = httpServletRequest; }
	public Object getModel() { return entry; }	
}