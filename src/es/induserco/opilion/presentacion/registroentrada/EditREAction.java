package es.induserco.opilion.presentacion.registroentrada;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class EditREAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private RegistroEntrada entry = new RegistroEntrada();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Registro Entrada Update Action...");
		this.request = request;	
	}

	//@Override
	public Object getModel() {		
		return entry;
	}

	public String execute() throws Exception {
		try {
			//Colocamos las listas de datos en la request.
			request.getSession().setAttribute("codigoEntrada",(request.getAttribute("codigoEntrada")));
			entry.setCodigoEntrada((String)request.getAttribute("codigoEntrada"));
			entry = new GestionRegistroEntradaHelper().getRegistroEntrada(entry.getCodigoEntrada());
			entry.setCodigoOrden((String)request.getAttribute("codigoOrden"));
			request.getSession().setAttribute("codigoOrden",(request.getAttribute("codigoOrden")));
			String fechareg =
				(new GestionRegistroEntradaHelper()).getFechaRegistro((String)request.getAttribute("codigoEntrada"));
			request.getSession().setAttribute("fecharegistro",
					(new GestionRegistroEntradaHelper()).getFechaRegistro((String)request.getAttribute("codigoEntrada")));
			System.out.println("ZZZZ: " + fechareg);
			request.getSession().setAttribute("fechacaducidad",
					(new GestionRegistroEntradaHelper()).getFechaCaducidad((String)request.getAttribute("codigoEntrada")));		
			request.getSession().setAttribute("listaproveedores",
					(new GestionRegistroEntradaHelper()).getProveedores());
			request.getSession().setAttribute("listatransportistas",
					(new GestionRegistroEntradaHelper()).getTransportistas());
			request.getSession().setAttribute("listacosechas",
					(new GestionRegistroEntradaHelper()).getCosechas());
			request.getSession().setAttribute("listatipoubicacion",
					(new GestionRegistroEntradaHelper()).getTipoUbicaciones());
			request.getSession().setAttribute("listaproductos",
					(new GestionRegistroEntradaHelper()).getProductos());
			request.getSession().setAttribute("listaProductosFinales",
					(new GestionProductosHelper()).getProductos(new Producto(), false));
			request.getSession().setAttribute("listaenvases",
					(new GestionRegistroEntradaHelper()).getEnvases());
			request.getSession().setAttribute("listacategorias",
					(new GestionRegistroEntradaHelper()).getCategorias(""));
			request.getSession().setAttribute("listavehiculos",
					(new GestionRegistroEntradaHelper()).getVehiculos());
			request.getSession().setAttribute("listaformatos",
					(new GestionRegistroEntradaHelper()).getFormatos());
			request.getSession().setAttribute("listaestadosfabas",
					(new GestionRegistroEntradaHelper()).getEstadosFabas());
			request.getSession().setAttribute("listaincidencias",
					(new GestionRegistroEntradaHelper()).getIncidencias());
			request.getSession().setAttribute("tiporegistro",
					entry.getIdTipoRegistro());
			// Se guarda en la request los datos del RE y sus incidencias
			request.getSession().setAttribute("listaregistros",
					(new GestionRegistroEntradaHelper()).loadRegistroEntrada(entry));
			request.getSession().setAttribute("listaestadostmp",
					(new GestionRegistroEntradaHelper()).loadEstadoFabas(entry));
			request.getSession().setAttribute("listaincidenciastmp",
					(new GestionRegistroEntradaHelper()).loadIncidencias(entry));
			//Levantamos el evento success para especificar que todo esta bien
			return (SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			return (ERROR);
		}
	}
}