package es.induserco.opilion.presentacion.consultaGestionProduccion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

/**
 * Filtra en la lista de procesos de envasado activos
 * @author Induserco, Andrés, 08/04/2011
 * @version 0.0
 */
public class FiltraListarEnvasarAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private GestionProduccion gprod= new GestionProduccion();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("Procesando el execute de Filtra Lista Envasar!");
		return gprod;
	}
	
	public String execute() throws Exception {
		//Colocamos la lista de gprodes en la request.
		String fechaConsulta = (String)request.getParameter("fechaConsulta");
		System.out.println("Fecha es: " + fechaConsulta);
		//request.getSession().setAttribute("orden",(gprod.getOrden()));
		String estados[] = {gprod.getEstadoProceso()};
		estados = null;
		request.getSession().setAttribute("listaregienvasados",
				(new GestionEnvasadoHelper()).getProcesosEnvasado(gprod.getOrden(),
						fechaConsulta,gprod.getLoteAsignado(),gprod.getIdProducto(),estados ,'S'));
		request.getSession().setAttribute("listaproductos",(new GestionEnvasadoHelper()).getPresentacionProductos(false));
		//Levantamos el evento success para especificar que todo ha ido bien
		return (SUCCESS);
	}
}