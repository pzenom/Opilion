package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
//import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionCribadoHelper;
import es.induserco.opilion.presentacion.GestionRegistroEnvasesHelper;

public class EditDetaGPENAction extends ActionSupport 
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
		// TODO Auto-generated method stub
		return gprod;
	}

	public String execute() throws Exception {
		//Colocamos las listas necesarias en la request.
		//request.getSession().setAttribute("ordeningrediente",(request.getParameter("orden")));
		//request.getSession().setAttribute("envproductos",(new GestionEnvasadoHelper()).getPresentacionProductos());
		//request.getSession().setAttribute("listaenvases",(new GestionEnvasadoHelper()).getEnvasesMateriaPrima());
		//Levantamos el evento success para especificar que todo ha ido bien
		//return (SUCCESS);
		
		//Colocamos las listas de datos en la request.

		//1. Fecha del registro no editable
		request.getSession().setAttribute("fecharegistro",(new GestionCribadoHelper()).getFechaRegistro());
		
		//2. Código de orden que engloba el proceso y a los subregistros
		//request.getSession().setAttribute("orden",(new GestionDesgranadoHelper()).getCodigoRegistroProceso("Envasado"));
		gprod.setOrden((String)request.getSession().getAttribute("orden"));
		System.out.println("codigote es "+gprod.getOrden());
		
		//3. Hora de inicio del proceso
		request.getSession().setAttribute("horaInicioProceso",(new GestionCribadoHelper()).getHoraInicioProceso(gprod.getOrden()));
		gprod.setHoraInicioProceso((String)request.getSession().getAttribute("horainicio"));		
		
		//4. Listado de Subregistros generados
		request.getSession().setAttribute("subentradasenvasado",(new GestionRegistroEnvasesHelper()).getSubRegistrosEntradaEnvasado(gprod.getOrden()));
		
		return (SUCCESS);		
	}
}