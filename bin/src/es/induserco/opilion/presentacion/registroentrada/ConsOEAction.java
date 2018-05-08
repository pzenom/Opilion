package es.induserco.opilion.presentacion.registroentrada;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroOrden;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class ConsOEAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private RegistroOrden orden = new RegistroOrden();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("ConsOEAction");
		return orden;
	}

	public String execute() throws Exception {
		String filtroString = (String)request.getParameter("filtro");
		int filtro = 1;
		if (filtroString != null){
			filtro = Integer.parseInt(filtroString);
		}
		//Colocamos la lista de entradaes en la request.
		request.getSession().setAttribute("codigoEntrada", (orden.getCodigoOrden()));
		//Verifica detalle del albaran para mostrar bot�n de generar reporte.
		Vector rordenes = new GestionRegistroEntradaHelper().getOrdenes(orden, filtro);
		request.getSession().setAttribute("listaproveedores", (new GestionRegistroEntradaHelper()).getProveedores());
		request.getSession().setAttribute("listaordenes",rordenes);
		if(!rordenes.isEmpty())
			request.getSession().setAttribute("listaoeestado","si");
		else
			request.getSession().setAttribute("listaoeestado","no");     		
		return (SUCCESS);
	}
}