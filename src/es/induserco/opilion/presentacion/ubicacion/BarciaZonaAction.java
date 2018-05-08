package es.induserco.opilion.presentacion.ubicacion;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.Ubicacion;

public class BarciaZonaAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion entry = new Ubicacion();

	public String execute() throws Exception {
		request.getSession().setAttribute("idAlmacen", 1);
		request.getSession().setAttribute("registro", request.getParameter("codigoEntrada"));
		int numeroPalets = Integer.parseInt((String)request.getParameter("numeroPalets"));
		request.getSession().setAttribute("numeroPalets", numeroPalets);
		Vector<Bulto> bultos = new Vector<Bulto>();
		for (int i = 0; i < numeroPalets; i++)
		{
			bultos.add(new Bulto());
		}
		request.getSession().setAttribute("bultos", bultos);
		return SUCCESS;
	}
	
	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	//@Override
	public Object getModel() {	
		return entry;
	}
}