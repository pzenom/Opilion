package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.flujos.FlujoDevolucion;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionProductosHelper;

/**
 * @author andres (07.09.2011)
 * @version 1.0
 */
public class RealizarFlujoDevolucionAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private FlujoDevolucion flujo = new FlujoDevolucion();
	private String url;

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	//@Override
	public Object getModel() {	
		return flujo;
	}

	public String execute() throws Exception {
		request.getSession().setAttribute("flujo", flujo);
		if (flujo.getCodigoEscape() != null){
			if (flujo.getCodigoEscape().compareTo("X0000004") == 0){//CANCELAR
				//Salir (al escritorio) sin mover nada
				setUrl("Inicio");
				return "redirect";
			}else
				if (flujo.getCodigoEscape().compareTo("X0000005") == 0){//FIN
					//Mover los datos introducidos y salir
					return devolucion(true);
				}else
					if (flujo.getCodigoEscape().compareTo("X0000002") == 0){//INTRO
						//Mover los datos introducidos
						return devolucion(false);
					}
		}else
			request.getSession().setAttribute("msg", "");
		return SUCCESS;
	}

	private String devolucion(boolean fin) throws Exception{
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		Entidad e = new Entidad();
		e.setNombre(us.getLogin());
		e.setIdUsuario(us.getIdUsuario());
		int devuelto =
			new GestionProductosHelper().devolver(flujo.getLote(), flujo.getCantidad(), flujo.getDestino(), e);
		switch (devuelto){
		case 1://OK
			if (fin)
				setUrl("Inicio.action");
			else{
				request.getSession().setAttribute("codigo", "X0000009");
				setUrl("ComenzarFlujo.action");
			}
			return "redirect";
		case 0://No existe el lote
			request.getSession().setAttribute("msg", "No existe el lote introducido");
			break;
		case 2://No existe el destino
			request.getSession().setAttribute("msg", "No existe el hueco de destino");
			break;
		}
		return SUCCESS;
	}
	
	public void setUrl(String url) { this.url = url; }
	public String getUrl() { return url; }
}