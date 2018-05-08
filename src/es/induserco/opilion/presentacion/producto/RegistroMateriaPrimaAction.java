package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.MateriaPrima;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

/**
 * @author andres (12/05/2011) (17/05/2011)
 * @version 0.2
 */
public class RegistroMateriaPrimaAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private MateriaPrima entry = new MateriaPrima();
	
	//@Override
	public Object getModel() {
		return entry;
	}

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}
	
	/**
	 * @since 0.2
	 */
	public String execute() throws Exception{
		request.getSession().setAttribute("listaGrupos", new GestionProductosHelper().getGrupoProducto());
		request.getSession().setAttribute("fecharegistro",(new GestionEntidadesHelper()).getFechaRegistro());
		request.getSession().setAttribute("listacategorias",(new GestionRegistroEntradaHelper()).getCategorias(""));
		request.getSession().setAttribute("listaCategorizaciones", new GestionProductosHelper().getGrupoProducto());
		return SUCCESS;
	}
}