package es.induserco.opilion.presentacion.producto;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.MateriaPrima;
import es.induserco.opilion.presentacion.GestionEnvasesHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class CargarMPAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{
	private static final long serialVersionUID = 1L;
	//lo que se tiene aqui se tiene que recuperar aqui nada más
	private HttpServletRequest request;
	private MateriaPrima entry = new MateriaPrima();
	
	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		System.out.println("Cargar MP Action...");
		this.request = httpServletRequest;			
	}

	//@Override
	public Object getModel() {	
		//System.out.println("Cargar actualizar!: "+entidad.getNombre());		
		return entry;
	}		

	public String execute() throws Exception {
		//System.out.println("Cargar Action - id entrada update "+request.getAttribute("idProducto"));		
		//request.getSession().setAttribute("idMateriaPrima",(request.getAttribute("idMateriaPrima")));
		//entry.setIdMateriaPrima((Long)request.getAttribute("idMateriaPrima"));
		request.getSession().setAttribute("idMateriaPrima", entry.getIdMateriaPrima());
		request.getSession().setAttribute("listamateriales",(new GestionEnvasesHelper()).getMateriales());
		Vector<MateriaPrima> materia = (new GestionProductosHelper()).loadMateriaPrima(entry);
		request.getSession().setAttribute("listaregistros", materia);
		request.getSession().setAttribute("listaGrupos", new GestionProductosHelper().getGrupoProducto());
		request.getSession().setAttribute("categorias" ,
				new GestionProductosHelper().getCategoriasMateria(entry.getIdMateriaPrima()));
		request.getSession().setAttribute("listacategorias",(new GestionRegistroEntradaHelper()).getCategorias(""));
		request.getSession().setAttribute("listaCategorizaciones", new GestionProductosHelper().getGrupoProducto());
		return (SUCCESS);
	}
}