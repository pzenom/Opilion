package es.induserco.opilion.presentacion.mantenimiento;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Mantenimiento;
import es.induserco.opilion.data.entidades.Maquina;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class FiltMTMaquinaAction extends ActionSupport
	implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private Maquina maquina = new Maquina();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta!");
		return maquina;
	}

	public String execute() throws Exception {
		request.getSession().setAttribute("maquina",(new GestionRegistroEntradaHelper()).getMaquinas(0, maquina.getIdMaquina(), ""));
		request.getSession().setAttribute("listaMantenimientos",(new GestionRegistroEntradaHelper()).getMantenimientos(0,0, maquina.getIdMaquina()));
		return (SUCCESS);
	}
}