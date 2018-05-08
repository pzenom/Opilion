package es.induserco.opilion.presentacion.registroentrada;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class NuevaOEAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private RegistroEntrada registroEntrada = new RegistroEntrada();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Registro Orden Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return registroEntrada;
	}

	public String execute() throws Exception {
		try {
			Usuario us = (Usuario) request.getSession().getAttribute("usuario");
			// Borrar las tablas temporales (ordenentrada, registroentrada, registroentrada_estado
			// y registroentrada_incidencia)
			new GestionRegistroEntradaHelper().deleteTemporales(us.getLogin());	
			request.getSession().setAttribute("fecharegistro",(new GestionRegistroEntradaHelper()).getFechaRegistro());
			request.getSession().setAttribute("fechacaducidad",(new GestionRegistroEntradaHelper()).getFechaCaducidad());		
			request.getSession().setAttribute("codigoorden",(new GestionRegistroEntradaHelper()).getCodigoOrden());		
			request.getSession().setAttribute("listaproveedores",(new GestionRegistroEntradaHelper()).getProveedores());
			request.getSession().setAttribute("listatransportistas",(new GestionRegistroEntradaHelper()).getTransportistas());
			request.getSession().setAttribute("listavehiculos",(new GestionUbicacionesHelper()).getVehiculos());
			request.getSession().setAttribute("codigoOrden",new GestionRegistroEntradaHelper().getCodigoOrden());
			Vector <RegistroEntrada>listareorden = new GestionRegistroEntradaHelper().getRegistrosEntradaOrdenTmp((String)request.getSession().getAttribute("codigoorden"));
			request.getSession().setAttribute("listareorden", listareorden);
			//Levantamos el evento success para especificar que todo esta bien
			return (SUCCESS);
		}
		catch (Exception e) {
			return (ERROR);
		}
	}
}