package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class EliminarRegistroEnvasadoAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private HttpServletRequest request;
	private GestionProduccion regEnvasadoFind = new GestionProduccion();
	private GestionProduccion regEnvasadoElimina = new GestionProduccion();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Eliminar Registro Envasado Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return regEnvasadoElimina;
	}

	public String execute() throws Exception {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		regEnvasadoElimina.setIdOperario(us.getLogin());
		regEnvasadoFind.setOrden((String)request.getSession().getAttribute("orden"));
		new GestionEnvasadoHelper().deleteRegistroEnvasado(regEnvasadoFind, regEnvasadoElimina);
		//request.getSession().setAttribute("listaregistrosdel",(new GestionEnvasadoHelper()).getRegistroEnvasados(regEnvasadoFind.getOrden(),regEnvasadoFind.getFecha(),regEnvasadoFind.getLoteAsignado(),regEnvasadoFind.getIdProducto()));
		return SUCCESS;
	}
}