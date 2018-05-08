package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class UpdaRSAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private RegistroSalida rsFind = new RegistroSalida();
	private RegistroSalida rsUpda = new RegistroSalida();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Actualizar Registro Salida Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return rsUpda;
	}

	public String execute() throws Exception {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		rsUpda.setIdOperario(us.getLogin());		
		rsFind.setCodigoSalida((String)request.getSession().getAttribute("codigoSalida"));
		new GestionRegistroSalidaHelper().updateRegistroSalida(rsFind, rsUpda);
		//request.getSession().setAttribute("listaregistrosupd",(new GestionRegistroSalidaHelper()).getRegistroSalidas(rsFind.getCodigoSalida(),rsFind.getFecha(),rsFind.getIdProducto()));
		return SUCCESS;
	}
}