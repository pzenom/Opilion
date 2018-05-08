package es.induserco.opilion.presentacion.gestionProduccionCongelado;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
//import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionCribadoHelper;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;

public class ConfGPCongAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private GestionProduccion gprod= new GestionProduccion();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}
	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta!");
		return gprod;
	}

	public String execute() throws Exception {
		//1. Fecha del registro no editable
		request.getSession().setAttribute("fecharegistro",(new GestionCribadoHelper()).getFechaRegistro());
		
		//2. C�digo de orden que engloba el proceso y a los subregistros
		//request.getSession().setAttribute("orden",(new GestionDesgranadoHelper()).getCodigoRegistroProceso("Envasado"));
		gprod.setOrden((String)request.getSession().getAttribute("orden"));
		System.out.println("codigo de orden congelado es "+gprod.getOrden());
		
		//3. Hora de inicio del proceso
		request.getSession().setAttribute("horaInicioProceso",
				(new GestionCribadoHelper()).getHoraInicioProceso(gprod.getOrden()));
		gprod.setHoraInicioProceso((String)request.getSession().getAttribute("horainicio"));		
		
		//4. Listado de Subregistros generados
		request.getSession().setAttribute("subentradasproceso",
				(new GestionDesgranadoHelper()).getREProceso(gprod.getOrden(),"Congelado"));
		return (SUCCESS);		
	}
}