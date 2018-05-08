package es.induserco.opilion.presentacion.registroentrada;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class NewRSEAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	private HttpServletRequest request;
	private RegistroEntrada entry = new RegistroEntrada();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Registro Entrada Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return entry;
	}

	public String execute() throws Exception {
		//Colocamos las listas de datos en la request.
		request.getSession().setAttribute("codigoEntrada",(request.getAttribute("codigoEntrada")));
		entry.setCodigoEntrada((String)request.getAttribute("codigoEntrada"));
		
		request.getSession().setAttribute("fecharegistro",(new GestionRegistroEntradaHelper()).getFechaRegistro());
		request.getSession().setAttribute("fechacaducidad",(new GestionRegistroEntradaHelper()).getFechaCaducidad());
		request.getSession().setAttribute("listacategorias",(new GestionRegistroEntradaHelper()).getCategorias(""));
		request.getSession().setAttribute("listaincidencias",(new GestionRegistroEntradaHelper()).getIncidencias());
		request.getSession().setAttribute("listaformatos",(new GestionRegistroEntradaHelper()).getFormatos());
		request.getSession().setAttribute("listaestadosfabas",(new GestionRegistroEntradaHelper()).getEstadosFabas());		
		request.getSession().setAttribute("listaproveedores",(new GestionRegistroEntradaHelper()).getProveedores());
		request.getSession().setAttribute("listacosechas",(new GestionRegistroEntradaHelper()).getCosechas());		
		request.getSession().setAttribute("listaproductos",(new GestionRegistroEntradaHelper()).getProductos());
		request.getSession().setAttribute("listavehiculos",(new GestionRegistroEntradaHelper()).getVehiculos());
		request.getSession().setAttribute("listaincidencias",(new GestionRegistroEntradaHelper()).getIncidenciasFiltradas(1));
		
		request.getSession().setAttribute("listaregistrosupd",(new GestionRegistroEntradaHelper()).getRegistroEntradas(entry.getCodigoEntrada(),entry.getFecha(),entry.getIdProducto()));
		
		//Levantamos el evento success para especificar que todo esta bien
		return (SUCCESS);
	}
}