package es.induserco.opilion.presentacion.cliente;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class EliminarEntidadAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	//lo que se tiene aqui se tiene que recuperar aqui nada más
	private HttpServletRequest request;
	private Entidad entidadRecuperada = new Entidad();
	private String url;

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	//@Override
	public Object getModel() {	
		System.out.println("Por aqui anda!! ");		
		return entidadRecuperada;
	}		

	public String execute() throws Exception {
		request.getSession().setAttribute("idUsuario",(request.getParameter("idUsuarioEliminar")));
		entidadRecuperada.setIdUsuario(Long.parseLong(request.getParameter("idUsuarioEliminar")));
		new GestionEntidadesHelper().deshabilitaEntidad(entidadRecuperada);
		System.out.println("Escogido " + entidadRecuperada.getNombre());
		String tipoEntidad = request.getParameter("tipoUsuario");
		if (tipoEntidad.compareTo("C") == 0)
			setUrl("ConsultaClientes");
		else
			if (tipoEntidad.compareTo("P") == 0)
				setUrl("ConsultaProveedores");
		return ("redirect");
	}

	public void setUrl(String url) { this.url = url; }
	public String getUrl() { return url; }
}