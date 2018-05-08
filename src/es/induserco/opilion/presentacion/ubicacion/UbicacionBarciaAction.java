package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;

public class UbicacionBarciaAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion entry = new Ubicacion();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	//@Override
	public Object getModel() {	
		return entry;
	}

	public String execute() throws Exception 
	{
		request.getSession().setAttribute("idAlmacen", 1);
		request.getSession().setAttribute("cantidad", request.getParameter("cantidad"));
		request.getSession().setAttribute("registro", request.getParameter("codigoEntrada"));
		int idPalet = Integer.parseInt((String)request.getParameter("idPalet"));
		request.getSession().setAttribute("idPalet", idPalet);
		//Vector<Bulto> bultos = new Vector<Bulto>();
		//String reubicar = request.getParameter("reubicar");
		return SUCCESS;
	}
}