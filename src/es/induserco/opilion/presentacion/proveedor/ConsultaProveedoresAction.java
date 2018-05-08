package es.induserco.opilion.presentacion.proveedor;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class ConsultaProveedoresAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>
{
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
		request.getSession().setAttribute("idTipo", entidad.getIdTipo());
		entidad.setIdTipo((Long)request.getSession().getAttribute("idTipo"));
		request.getSession().setAttribute("listatipos",(new GestionEntidadesHelper()).getListaTiposProveedores());
		consulta = new GestionEntidadesHelper().getProveedores(entidad);
		request.getSession().setAttribute("listaproveedores",consulta);
		request.getSession().setAttribute("idTipo", entidad.getIdTipo());
		return (SUCCESS);
	}

	//@Override
	public Object getModel(){
		System.out.println("procesando el getModel de ConsultaProveedores!");
		return entidad;
	}
}