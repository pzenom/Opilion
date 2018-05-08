package es.induserco.opilion.presentacion.registroentrada;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class ConsREAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private RegistroEntrada entrada= new RegistroEntrada();

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}

	public Object getModel() {
		System.out.println("procesando el execute de Consulta Registro Entrada!");
		return entrada;
	}

	public String execute() throws Exception {
		int filtro = 1;
		if ((String)request.getParameter("filtro") != null && !((String)request.getParameter("filtro")).equals(""))
			filtro = Integer.parseInt((String)request.getParameter("filtro"));
		request.getSession().setAttribute("codigoEntrada", entrada.getCodigoEntrada());
		Vector<RegistroEntrada> re = new GestionRegistroEntradaHelper().getRegistroEntradas(entrada, filtro);
		request.getSession().setAttribute("listaentradas", re);
		request.getSession().setAttribute("listaenvases",(new GestionRegistroEntradaHelper()).getEnvases());
		request.getSession().setAttribute("listaproductos",(new GestionRegistroEntradaHelper()).getProductos());
		if(!re.isEmpty())
			request.getSession().setAttribute("listareestado","si");
		else
			request.getSession().setAttribute("listareestado","no");
		return (SUCCESS);
	}
}