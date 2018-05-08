package es.induserco.opilion.presentacion.cliente;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class DeshabilitarEntidadAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private Entidad clienteFind = new Entidad();
	private Entidad clienteActualiza = new Entidad();	

	//@Override
	public Object getModel() {	
		return clienteActualiza;
	}

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	public String execute() throws Exception {	
		
		clienteFind.setIdUsuario((Long)request.getSession().getAttribute("idUsuario"));
		System.out.println("PRESENTACION: Actualizar Cliente");
		System.out.println("El nombre es:" + clienteFind.getIdUsuario());
		System.out.println("El nombre es:" + clienteActualiza.getNombre());
		//new GestionEntidadesHelper().updateCliente(clienteFind, clienteActualiza);		
		request.getSession().setAttribute("listaregistros",(new GestionEntidadesHelper()).deshabilitaEntidad(clienteFind));
		return SUCCESS;
	}
}