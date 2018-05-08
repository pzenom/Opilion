package es.induserco.opilion.presentacion.cliente;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class ConsultaClientesAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Entidad entidad= new Entidad();
	private Vector<Entidad> consulta = new Vector<Entidad>();

	public Vector<Entidad> getConsulta() {
		return consulta;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}

	public String execute() throws Exception {
		//Colocamos la lista de proveedores en la request.
		System.out.println("Consulta cliente!");
		request.getSession().setAttribute("idSector",(entidad.getIdSector()));		
		entidad.setIdSector((Long)request.getSession().getAttribute("idSector"));
		request.getSession().setAttribute("listasectores",(new GestionEntidadesHelper()).getListaSectores());
		consulta = new GestionEntidadesHelper().getClientes(entidad);
		request.getSession().setAttribute("listaclientes", consulta);
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta!");
		return entidad;
	}
}