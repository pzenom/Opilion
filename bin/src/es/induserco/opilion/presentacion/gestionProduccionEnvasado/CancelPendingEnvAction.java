/*************************************************************
 *  CANCELACION DE ORDEN DE ENVASADO PENDIENTE (23/02/2011)
 * @version 0.1 
 * @author Administrador - Induserco
**************************************************************/	
package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class CancelPendingEnvAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = -9013888892313309236L;
	private HttpServletRequest request;
	private RegistroEnvasado envasado = new RegistroEnvasado();
	
	//@Override
	public void setServletRequest(HttpServletRequest request){
		this.request=request;
	}

	//@Override
	public Object getModel() {
		System.out.println("GetModel CancelPendingEnv");
		return envasado;
	}

	public String execute() throws Exception {
		System.out.println("Execute CancelPendingEnv");
		try {
			//Recoger par�metros: request.getAttribute("id del param (loteAsignado)")
			System.out.println("Valor de visualizar: " + request.getAttribute("visualizar"));
			request.getSession().setAttribute("envasadoPendientes",(new GestionEnvasadoHelper()).delProcesoPendiente(envasado));			
			//sacamos los procesos de envasado pendientes
			request.getSession().setAttribute("envasadoPendientes",(new GestionEnvasadoHelper()).getProcesosPendientes(null, "E"));
			//Colocamos las listas necesarias en la request.
			//url = "gpenvasado/consRegiGPEN.jsp";
			//return "redirecciona";
			
			return SUCCESS;
		}
		catch (Exception e) { return (ERROR); }
	}
}