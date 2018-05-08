package es.induserco.opilion.presentacion.ubicacion;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.TipoUbicacion;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class ReubicarAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion ubicacion = new Ubicacion();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel(){
		return ubicacion;
	}

	/* Reubicar sobras envasados */
	@SuppressWarnings("unchecked")
	public String execute() throws Exception{
		System.out.println("Execute SeleccionAlmacenAction");
		Vector<TipoUbicacion> almacenes = new GestionRegistroEntradaHelper().getTipoUbicaciones();
		boolean reubicar = Boolean.parseBoolean((String) request.getParameter("reubicar"));
		request.setAttribute("reubicar", reubicar);
		boolean seleccion = Boolean.parseBoolean((String) request.getParameter("seleccion"));
		request.setAttribute("seleccion", seleccion);
		request.getSession().setAttribute("seleccion", seleccion);
		request.getSession().setAttribute("mover", "false");
		request.getSession().setAttribute("meter", "true");
		request.getSession().setAttribute("gestionBultos", false);
		request.getSession().setAttribute("registro", ubicacion.getRegistro());
		request.getSession().setAttribute("cantidad", ubicacion.getCantidad());
		if (ubicacion.getCantidad() == 0)
			request.getSession().setAttribute("cantidad", request.getParameter("cantidad"));
		request.getSession().setAttribute("lote", request.getParameter("lote"));
		request.getSession().setAttribute("orden", request.getParameter("orden"));
		request.getSession().setAttribute("procesoSeleccion", request.getParameter("procesoSeleccion"));
		request.getSession().setAttribute("estado", request.getParameter("estado"));
		request.getSession().setAttribute("idEnvasado", request.getParameter("idEnvasado"));
		request.getSession().setAttribute("idPalet", request.getParameter("idPalet"));
		if (reubicar){
			request.getSession().setAttribute("almacenes", almacenes);
			//String cadenaMover = (String)request.getParameter("mover");
			//request.getSession().setAttribute("reubicar", "true");
			request.getSession().setAttribute("idUbicacion", request.getParameter("idUbicacion"));
		}else{
			boolean ubicar = Boolean.parseBoolean((String) request.getParameter("ubicar"));
			request.setAttribute("ubicar", ubicar);
		}
		return "redirect";
	}
}