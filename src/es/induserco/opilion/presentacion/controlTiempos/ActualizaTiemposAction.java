package es.induserco.opilion.presentacion.controlTiempos;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.comun.RegistroActividad;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class ActualizaTiemposAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private GestionProduccion gpro = new GestionProduccion();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("ActualizaTiemposAction...");
		this.request = request;	
	}

	//@Override
	public Object getModel(){ return gpro; }

	public String execute() throws Exception {
		System.out.println("ActualizaTiemposAction");
		String proceso = request.getParameter("proceso");
		String caducidad = request.getParameter("fechaCaducidad");
		System.out.println(proceso + " - " + caducidad);
		try{
			String operario = ((Usuario) request.getSession().getAttribute("usuario")).getLogin();
			new GestionEnvasadoHelper().actualizaCaducidadEnvasado(proceso, caducidad, operario);
		}catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
}