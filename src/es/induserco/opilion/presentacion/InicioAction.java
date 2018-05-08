package es.induserco.opilion.presentacion;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.data.entidades.GestionRoles;

public class InicioAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	
	public String execute() throws Exception {
		System.out.println("Execute INICIO");
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		//Cargamos las facturas para mostrar en el calendario
		Factura f = new Factura();
		Vector<Factura> facturas = new GestionRegistroSalidaHelper().getFacturas(f, null, null, us);
		request.getSession().setAttribute("listaclientes",(new GestionRegistroSalidaHelper()).getClientes());
		request.getSession().setAttribute("listaestados",(new GestionRegistroSalidaHelper()).getEstadosFacturas());
		request.getSession().setAttribute("listaregienvasados", new GestionEnvasadoHelper().getProcesosPendientes(null, "S"));
		request.getSession().setAttribute("facturas", facturas);
		request.getSession().setAttribute("listaproductos",
				(new GestionEnvasadoHelper()).getPresentacionProductos(false));
		GestionRoles gestionRoles = new GestionEntidadesHelper().getRoles();
		request.getSession().setAttribute("gestionRoles", gestionRoles);
		request.getSession().setAttribute("listavehiculos", (new GestionUbicacionesHelper()).getVehiculos());
		request.getSession().setAttribute("comerciales", (new GestionUsuariosHelper()).getComerciales());
		request.getSession().setAttribute("rutas", (new GestionUsuariosHelper()).getRutas());
	    return SUCCESS;
	}

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}
}