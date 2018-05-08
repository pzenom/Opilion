package es.induserco.opilion.presentacion.producto;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.data.entidades.MateriaPrima;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionEnvasesHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class MostrarProductoAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	//lo que se tiene aqui se tiene que recuperar aqui nada más
	private HttpServletRequest request;
	private Producto entry = new Producto();
	
	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		System.out.println("CargarProducto Action...");
		this.request = httpServletRequest;			
	}
	
	//@Override
	public Object getModel() {
		return entry;
	}
	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		//System.out.println("Cargar Action - id entrada update "+request.getAttribute("idProducto"));
		long idProducto = Long.parseLong(request.getAttribute("idProducto").toString());
		System.out.println("idProducto: " + idProducto);
		Producto producto = (new GestionProductosHelper()).getProducto(idProducto);
		request.getSession().setAttribute("idProducto", idProducto);
		
		entry.setIdProducto((Long)request.getAttribute("idProducto"));
		request.getSession().setAttribute("fecharegistro",(new GestionEntidadesHelper()).getFechaRegistro());
		request.getSession().setAttribute("listacategorias",(new GestionProductosHelper()).getCategorias());
		request.getSession().setAttribute("listaestados",(new GestionProductosHelper()).getEstados());
		request.getSession().setAttribute("listatipos",(new GestionProductosHelper()).getTipoProducto());
		request.getSession().setAttribute("listagrupos",(new GestionProductosHelper()).getGrupoProducto());
		request.getSession().setAttribute("listafamilias",(new GestionProductosHelper()).getFamilias());
		request.getSession().setAttribute("listaimpuestos",(new GestionProductosHelper()).getImpuestos());
		request.getSession().setAttribute("listaCategorizaciones", new GestionProductosHelper().getGrupoProducto());
		//Cargamos la imagen del producto
		request.getSession().setAttribute("userImageFileName", "img/productos/" + producto.getFoto());
		
		request.getSession().setAttribute("listamateriaprima",(new GestionProductosHelper()).getMateriasPrimas(0));
		request.getSession().setAttribute("listaenvases", (new GestionEnvasesHelper()).getEnvases(-1L));		
		request.getSession().setAttribute("productosCompuesto", (new GestionProductosHelper().getProductos(null, false)));
		
		if (new GestionProductosHelper().getProductos(null, false).size() > 0){
			//El producto es un EAN 14
			request.getSession().setAttribute("tipoProdu", "EAN14");
		}else
			request.getSession().setAttribute("tipoProdu", "EAN13");
			
		Vector<Producto> composicion = (Vector<Producto>)(new GestionProductosHelper().getProductosComponen(idProducto, false));
		Vector<MateriaPrima> materias = (new GestionProductosHelper()).getMateriasPrimasProducto(idProducto, "");
		Vector<Producto> EANs13 = (new GestionProductosHelper()).getEANs13Producto(idProducto, "");
		Vector<Envase> envases = (new GestionProductosHelper()).getEnvasesProducto(idProducto, "");
		request.getSession().setAttribute("productosCompuesto_Asociado", composicion);
		request.getSession().setAttribute("listamateriaprima_Asociado", materias);
		request.getSession().setAttribute("listaEANs13_Asociado", EANs13);
		request.getSession().setAttribute("listaenvases_Asociado", envases);

		request.getSession().setAttribute("listaregistros",(new GestionProductosHelper()).loadProducto(entry));
		
		Vector<Producto> productos = new Vector<Producto>();
		productos.add(producto);
		request.getSession().setAttribute("producto", productos);
		
		return (SUCCESS);
	}
}