package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class FiltroFacturasAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Factura factura = new Factura();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta Registro Salida!");
		return factura;
	}

	public String execute() throws Exception {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		String fechaInicio = (String)request.getParameter("fechaInicio");
		String fechaFin = (String)request.getParameter("fechaFin");
		System.out.println("Fecha usable para busqueda "+ fechaInicio + "y " + fechaFin);
		if (request.getParameter("idUsuario") != null &&
			request.getParameter("idUsuario").compareTo("") != 0){
			long idCliente = Long.parseLong((String)request.getParameter("idUsuario"));
			factura.setIdCliente(idCliente);
		}
		request.getSession().setAttribute("listaclientes",(new GestionRegistroSalidaHelper()).getClientes());
		request.getSession().setAttribute("listasalidas",
				(new GestionRegistroSalidaHelper()).getFacturas(factura, fechaInicio, fechaFin, us));
		return (SUCCESS);
	}
}