package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class FiltroAlbaranesAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Albaran albaran = new Albaran();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("Filtrando albaranes");
		return albaran;
	}

	public String execute() throws Exception {
		int filtro = Integer.parseInt((String)request.getParameter("filtro"));
		if (request.getParameter("idUsuario") != null){
			long idCliente = Long.parseLong((String)request.getParameter("idUsuario"));
			Entidad e = new Entidad();
			e.setIdUsuario(idCliente);
			albaran.setCliente(e);
		}
		//Lista de clientes
		request.getSession().setAttribute("listaclientes",(new GestionRegistroSalidaHelper()).getClientes());
		//Lista de albaranes recuperados
		request.getSession().setAttribute("listaalbaranes",
				(new GestionRegistroSalidaHelper()).getAlbaranes(albaran, filtro));
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}
}