package es.induserco.opilion.presentacion.ubicacion;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.TipoUbicacion;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class MostrarAlmacenAction extends ActionSupport implements
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

	@SuppressWarnings("unchecked")
	public String execute() throws Exception{
		System.out.println("Execute MostrarAlmacenAction");
		Vector<TipoUbicacion> almacenes = new GestionRegistroEntradaHelper().getTipoUbicaciones();
		request.getSession().setAttribute("almacenes", almacenes);
		request.getSession().setAttribute("mover", "false");
		request.getSession().setAttribute("meter", "false");
		request.getSession().setAttribute("reubicar", "false");
		request.getSession().setAttribute("ubicar", "false");
		request.getSession().setAttribute("incidencia", "false");
		request.getSession().setAttribute("sacar", "false");
		request.setAttribute("seleccion", "false");
		String registro = (String)request.getSession().getAttribute("registro");
		request.getSession().setAttribute("codigoEntrada", registro);
		return SUCCESS;
	}
}