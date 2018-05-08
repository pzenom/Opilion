package es.induserco.opilion.presentacion.registroentrada;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.RegistroOrden;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class UpdateOEAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private String url;
	public String getUrl() { return url; }
	private RegistroOrden entry = new RegistroOrden();
	
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Actualizar Registro Orden Action...");
		this.request = request;	
	}

	//@Override
	public Object getModel() {
		return entry;
	}

	public String execute() throws Exception {
        try {
        	Usuario us = (Usuario) request.getSession().getAttribute("usuario");
        	//entry.setIdOperario(us.getLogin());
        	//entry.setCodigoOrden((String)request.getSession().getAttribute("codigoorden"));
        	//System.out.println("CODIGO A PASAR ES :"+entry.getCodigoOrden());
        	//entry.setIdProveedor((Long)request.getSession().getAttribute("idproveedor"));
        	//entry.setIdTransportista((Long)request.getSession().getAttribute("idtransportista"));
        	//System.out.println("El transportista es: "+entry.getIdTransportista());
        	//entry.setAlbaran((String)request.getSession().getAttribute("albaran"));
        	//entry.setOrigen((String)request.getSession().getAttribute("origen"));
        	//entry.setIdTipoRegistro((String)request.getSession().getAttribute("idtiporegistro"));
        	entry.setDescVehiculo(request.getParameter("descVehiculo"));
        	new GestionRegistroEntradaHelper().updateRegistroOrden(entry);
        	return SUCCESS;
        }
		catch (Exception e) { return (ERROR); }
	}
}