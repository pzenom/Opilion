package es.induserco.opilion.presentacion.devoluciones;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.TipoUbicacion;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class DevolverLoteAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private HttpServletRequest request;
	private RegistroEntrada entry = new RegistroEntrada();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Ver orden de entrada action...");
		this.request = request;	
	}

	//@Override
	public Object getModel() {		
		return entry;
	}

	public String execute() throws Exception {
		System.out.println("Devolver el lote: " + entry.getLote());
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		Entidad e = new Entidad();
		e.setNombre(us.getLogin());
		e.setIdUsuario(us.getIdUsuario());
		entry.setResponsable(e);
		String devolver = request.getParameter("devolver");
		boolean devuelve = Boolean.parseBoolean(devolver);
		String reaprovechar = request.getParameter("reaprovechar");
		boolean reaprovecha = Boolean.parseBoolean(reaprovechar);
		entry.setDevolverStock(devuelve);
		entry.setReaprovechar(reaprovecha);
		new GestionRegistroEntradaHelper().devolucion(entry);
		if (devuelve){
			new GestionProductosHelper().addStockProducto(entry.getLote(), entry.getCantidad());
			request.getSession().setAttribute("meter", true);
			request.getSession().setAttribute("registro", entry.getLote());
			request.getSession().setAttribute("codigoEntrada", entry.getLote());
			request.getSession().setAttribute("cantidad", entry.getCantidad());
			Vector<TipoUbicacion> almacenes = new GestionRegistroEntradaHelper().getTipoUbicaciones();
			request.getSession().setAttribute("almacenes", almacenes);
			return "UBICA_DEVOLUCION";
		}
		return SUCCESS;
	}
}