package es.induserco.opilion.presentacion.registroentrada;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.RegistroOrden;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class VerOEAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private RegistroOrden entry = new RegistroOrden();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Ver Orden de Entrada Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return entry;
	}

	public String execute() throws Exception {
		try {
			//Obtenemos la orden de entrada
			entry = (new GestionRegistroEntradaHelper()).getOrdenEntrada((String)request.getParameter("codigoOrden"));
			
			//Colocamos las listas de datos en la request.
			request.getSession().setAttribute("codigoorden",entry.getCodigoOrden());		
			request.getSession().setAttribute("fecharegistro",entry.getFecha());
			request.getSession().setAttribute("responsable",entry.getIdOperario());
			request.getSession().setAttribute("albaran",entry.getAlbaran());
			request.getSession().setAttribute("origen",entry.getOrigen());
			request.getSession().setAttribute("idProveedor",
					((Entidad)(new GestionEntidadesHelper()).getEntidades("2 AND idUsuario ='" +
					entry.getIdProveedor() + "'").firstElement()).getIdUsuario());
			request.getSession().setAttribute("proveedor",
					((Entidad)(new GestionEntidadesHelper()).getEntidades("2 AND idUsuario ='" +
					entry.getIdProveedor() + "'").firstElement()));
			request.getSession().setAttribute("tiporegistro",entry.getIdTipoRegistro());
			//Cargamos los proveedores que correspondan
			if (entry.getIdTipoRegistro().compareTo("E") == 0){
				request.getSession().setAttribute("listaProveedores",
						((new GestionEntidadesHelper()).getEntidades("2 AND idTipo=4")));
			}else
				if (entry.getIdTipoRegistro().compareTo("M") == 0){
					request.getSession().setAttribute("listaProveedores",
							((new GestionEntidadesHelper()).getEntidades("2 AND idTipo=1")));
				}
			request.getSession().setAttribute("transportista",
					((Entidad)(new GestionEntidadesHelper()).getEntidades("2 AND idUsuario ='" +
					entry.getIdTransportista() + "'").firstElement()).getNombre());
			request.getSession().setAttribute("idTransportista",
					((Entidad)(new GestionEntidadesHelper()).getEntidades("2 AND idUsuario ='" +
					entry.getIdTransportista() + "'").firstElement()).getIdUsuario());
			request.getSession().setAttribute("descvehiculo", entry.getDescVehiculo());
			request.getSession().setAttribute("notasvehiculo", entry.getNotasVehiculo());
			request.getSession().setAttribute("listatransportistas",
					(new GestionRegistroEntradaHelper()).getTransportistas());
			request.getSession().setAttribute("listavehiculos",(new GestionUbicacionesHelper()).getVehiculos());
			request.getSession().setAttribute("listaregistros",
					(new GestionRegistroEntradaHelper()).getRegistrosEntradaOrden(entry.getCodigoOrden()));

			//Levantamos el evento success para especificar que todo esta bien
			return (SUCCESS);	
		}
		catch (Exception e) {
			e.printStackTrace();
			return (ERROR);
		}
	}
}