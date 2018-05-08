package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class ConsultaProductoAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Producto entry= new Producto();
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	public String execute() throws Exception {
		//Colocamos la lista de proveedores en la request.
		request.getSession().setAttribute("idCategoria",(entry.getIdCategoria()));	
		request.getSession().setAttribute("idEstado",(entry.getIdEstado()));
		request.getSession().setAttribute("idFamilia",(entry.getIdFamilia()));
		//Sobra tipo en producto?
		//request.getSession().setAttribute("idTipo",(entry.getIdTipo()));
		request.getSession().setAttribute("idGrupo",(entry.getIdGrupo()));
		
		request.getSession().setAttribute("listaproductos",(new GestionProductosHelper()).getProductos(entry, false));
		//request.getSession().setAttribute("listamateriaprima",(new GestionProductosHelper()).getProductos(entry));
		
		request.getSession().setAttribute("listacategorias",(new GestionProductosHelper()).getCategorias());
		request.getSession().setAttribute("listaestados",(new GestionProductosHelper()).getEstados());
		request.getSession().setAttribute("listafamilias",(new GestionProductosHelper()).getFamilias());
		request.getSession().setAttribute("listagrupos",(new GestionProductosHelper()).getGrupoProducto());
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta!");
		// TODO Auto-generated method stub
		return entry;
	}
}