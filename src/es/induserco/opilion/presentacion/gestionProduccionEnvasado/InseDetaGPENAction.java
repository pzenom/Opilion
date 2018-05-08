package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;

public class InseDetaGPENAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private GestionProduccion gprodf = new GestionProduccion();
	private GestionProduccion gprodup = new GestionProduccion();
	String proceso="Envasado";

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("InseDetaGPENAction...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return gprodup;
	}

	public String execute() throws Exception {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		gprodup.setIdOperario(us.getLogin());

		gprodf.setOrden((String)request.getSession().getAttribute("orden"));
		System.out.println("codigo para update orden es "+gprodf.getOrden());		

		//sirve para actualizar los datos de confirmación como hora final de proceso
		//new GestionDesgranadoHelper().updateRegistroProceso(gprodf, gprodup,proceso);
		//tiene que copiar los datos de la tabla temporal e iinsertarlos en la fija,
		//
		new GestionDesgranadoHelper().insertRegistroEnvasado(gprodf, gprodup, proceso);
		
		//Debería limpiar todas las variable de sesión usadas en el proceso
		//request.getSession().setAttribute("codigoEntrada",(new GestionDesgranadoHelper().getCodigoEntradaOrden(gprodf.getOrden(),"Cribado")));		
		//System.out.println("codigoEntrada del cribado para envasado es..."+new GestionDesgranadoHelper().getCodigoEntradaOrden(gprodf.getOrden(),"Cribado"));
		
		//new GestionCribadoHelper().addRegistroCribado(gprod); 
		return SUCCESS;
	}
}