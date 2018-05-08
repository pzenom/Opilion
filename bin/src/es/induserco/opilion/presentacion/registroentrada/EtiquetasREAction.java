package es.induserco.opilion.presentacion.registroentrada;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroOrden;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class EtiquetasREAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private RegistroOrden entry = new RegistroOrden();

	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Registro Casi listo Orden Action...");
		this.request = request;	
	}

	public Object getModel() {
		return entry;
	}

	public String execute() throws Exception {
		try {
			String codigoOrden = entry.getCodigoOrden();
			if (codigoOrden == null)
				codigoOrden = (String)request.getSession().getAttribute("codigoOrden");
			if (codigoOrden == null || codigoOrden.compareTo("") == 0)
				codigoOrden = request.getParameter("codigoOrden");
			System.out.println("codigo de orden es :" + codigoOrden);
			request.getSession().setAttribute("codigoorden", codigoOrden);
			//Obtenemos la orden de entrada
			entry = (new GestionRegistroEntradaHelper()).getOrdenEntrada(codigoOrden);
			request.getSession().setAttribute("listatransportistas",
					(new GestionRegistroEntradaHelper()).getTransportistas());
			//Carga los RE asociados a ese n�m de Orden
			Vector<RegistroEntrada> listareorden = new GestionRegistroEntradaHelper().getRegistrosEntradaOrden(codigoOrden);
			request.getSession().setAttribute("listareorden",listareorden);
			//Colocamos las listas de datos en la request.
			request.getSession().setAttribute("codigoorden", entry.getCodigoOrden());
			request.getSession().setAttribute("fecharegistro", entry.getFecha());
			request.getSession().setAttribute("responsable", entry.getIdOperario());
			request.getSession().setAttribute("albaran", entry.getAlbaran());
			request.getSession().setAttribute("origen", entry.getOrigen());
			request.getSession().setAttribute("idProveedor",
					((Entidad)(new GestionEntidadesHelper()).getEntidades("2 AND e.idUsuario ='" +
					entry.getIdProveedor() + "'").firstElement()).getIdUsuario());
			request.getSession().setAttribute("proveedor",
					((Entidad)(new GestionEntidadesHelper()).getEntidades("2 AND e.idUsuario ='" +
					entry.getIdProveedor() + "'").firstElement()).getNombre());
			request.getSession().setAttribute("tiporegistro",entry.getIdTipoRegistro());
			request.getSession().setAttribute("transportista",
					((Entidad)(new GestionEntidadesHelper()).getEntidades("2 AND e.idUsuario ='" +
					entry.getIdTransportista() + "'").firstElement()).getNombre());
			request.getSession().setAttribute("idTransportista",
					((Entidad)(new GestionEntidadesHelper()).getEntidades("2 AND e.idUsuario ='" +
					entry.getIdTransportista() + "'").firstElement()).getIdUsuario());
			request.getSession().setAttribute("descripcionVehiculo", entry.getDescVehiculo());
			request.getSession().setAttribute("notasVehiculo", entry.getNotasVehiculo());
			//Verifica detalle del RE asociados a ese n�m de Orden
			if(!listareorden.isEmpty())
				request.getSession().setAttribute("listareordenestado","si");
			else
				request.getSession().setAttribute("listareordenestado","no");
			//Levantamos el evento success para especificar que todo esta bien
			request.getSession().setAttribute("previa", "SI");
			boolean editable = Boolean.parseBoolean(request.getParameter("editable"));
			request.getSession().setAttribute("editable", editable);
			return SUCCESS;
		}
		catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}