package es.induserco.opilion.presentacion.ubicacion;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.TipoUbicacion;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class SeleccionAlmacenAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object> {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion ubicacion = new Ubicacion();

	@SuppressWarnings("unchecked")
	public String execute() throws Exception{
		System.out.println("Execute SeleccionAlmacenAction.");
		Vector<TipoUbicacion> almacenes = new GestionRegistroEntradaHelper().getTipoUbicaciones();
		request.getSession().setAttribute("almacenes", almacenes);
		String cadenaMover = (String)request.getParameter("mover");
		boolean reubicar = Boolean.parseBoolean((String)request.getParameter("reubicar"));
		if (!reubicar){
			boolean mover = Boolean.parseBoolean(cadenaMover);
			request.getSession().setAttribute("mover", mover);
			boolean meter = Boolean.parseBoolean((String)request.getParameter("meter"));
			request.getSession().setAttribute("meter", meter);
			request.getSession().setAttribute("reubicar", "false");
			boolean ubicar = Boolean.parseBoolean((String) request.getParameter("ubicar"));
			//String ubicarSession = (String)request.getSession().getAttribute("ubicar");
			if (ubicar == false && request.getSession().getAttribute("ubicar") != null)
				ubicar = (Boolean)request.getSession().getAttribute("ubicar");
				//ubicar = Boolean.parseBoolean(ubicarSession);
			request.getSession().setAttribute("ubicar", ubicar);
			boolean incidencia = Boolean.parseBoolean((String) request.getParameter("incidencia"));
			request.getSession().setAttribute("incidencia", incidencia);
			if (ubicar)
				request.getSession().setAttribute("sacar", "false");
		}else{
			request.getSession().setAttribute("mover", false);
			request.getSession().setAttribute("meter", true);
			boolean seleccion = Boolean.parseBoolean((String) request.getParameter("seleccion"));
			request.setAttribute("seleccion", seleccion);
			request.getSession().setAttribute("reubicar", reubicar);
		}
		String registro = "";
		String bultos = request.getParameter("bultos");
		if (bultos != null && bultos.compareTo("true") == 0){
			registro = request.getParameter("registro");
			request.getSession().setAttribute("gestionBultos", true);
			request.getSession().setAttribute("cantidad", ubicacion.getCantidad());
			
			if (ubicacion.getNumeroBultos() > 0)
				request.getSession().setAttribute("numeroBultos", ubicacion.getNumeroBultos());
			else
				request.getSession().setAttribute("numeroBultos",
					request.getAttribute("numeroBultos"));
			request.getSession().setAttribute("orden",
					request.getAttribute("orden"));
			
			request.getSession().setAttribute("registro", ubicacion.getRegistro());
			request.getSession().setAttribute("idPalet", request.getParameter("idNuevoPalet"));
			//ACTUALIZAMOS EL HUECO DEL QUE SE VA A MOVER
			//Se saca el registro ubicacion.getRegistro y la cantidad ubicacion.getCantidad, con idPalet = request.getParameter("idNuevoPalet")
			//Del hueco request.getParameter("idHuecoViejo")
			Vector<Ubicacion> ubicaciones = new Vector<Ubicacion>();
			Ubicacion u = new Ubicacion();
			u.setCantidad(ubicacion.getCantidad());
			u.setIdPalet(Long.parseLong(request.getParameter("idNuevoPalet")));
			u.setIdHueco(Long.parseLong(request.getParameter("idHuecoViejo")));
			u.setRegistro(ubicacion.getRegistro());
			ubicaciones.add(ubicacion);
			new GestionUbicacionesHelper().sacarUbicaciones(ubicaciones, false, false, false);
		}else{
			registro = (String)request.getSession().getAttribute("registro");
			request.getSession().setAttribute("gestionBultos", request.getSession().getAttribute("gestionBultos"));
			if (ubicacion.getNumeroBultos() > 0)
				request.getSession().setAttribute("numeroBultos", ubicacion.getNumeroBultos());
			else
				request.getSession().setAttribute("numeroBultos",
					request.getAttribute("numeroBultos"));
			request.getSession().setAttribute("orden",
					request.getAttribute("orden"));
		}
		request.getSession().setAttribute("registro", registro);
		return SUCCESS;
	}
	
	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel(){
		return ubicacion;
	}
}