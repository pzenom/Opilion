package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class ConsAlbaAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Albaran albaran = new Albaran();

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}

	public Object getModel() {
		System.out.println("Consulta Albaranes");
		return albaran;
	}

	public String execute() throws Exception {
		String fechaInicio = (String)request.getParameter("fechaInicio");
		String fechaFin = (String)request.getParameter("fechaFin");
		System.out.println("Fecha usable para busqueda " + fechaInicio + " y " + fechaFin);
		if (request.getParameter("idUsuario") != null){
			long idCliente = Long.parseLong((String)request.getParameter("idUsuario"));
			Entidad e = new Entidad();
			e.setIdUsuario(idCliente);
			albaran.setCliente(e);
		}
		//Lista de clientes
		request.getSession().setAttribute("listaclientes", new GestionRegistroSalidaHelper().getClientes());
		//Lista de albaranes recuperados
		request.getSession().setAttribute("listaalbaranes",
				(new GestionRegistroSalidaHelper()).getAlbaranes(albaran, 1));//1: ultimos 20
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}
}