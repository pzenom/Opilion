package es.induserco.opilion.presentacion.registroentrada;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class BultosREAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private RegistroEntrada entry = new RegistroEntrada();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Bultos Registro Entrada Action...");
		this.request = request;
	}

	//@Override
	public Object getModel() {		
		return entry;
	}

	public String execute() throws Exception {
		try {
			request.getSession().setAttribute("tipoRegistroEnvase", "E");
			//request.getSession().setAttribute("tipoRegistroMateria", "M");
			request.getSession().setAttribute("codigoEntrada", (String)request.getParameter("codigoEntrada"));
			//System.out.println((String)request.getParameter("codigoEntrada"));
			System.out.println((String)request.getAttribute("codigoEntrada"));
			//entry.setCodigoEntrada((String)request.getAttribute("codigoEntrada"));
			//obtienes el registro en cuestión
			request.getSession().setAttribute("listaregistros",
					(new GestionRegistroEntradaHelper()).loadRegistroEntrada(entry));
			//miramos los bultos creados
			Vector<Bulto> bultos = (Vector<Bulto>)(new GestionRegistroEntradaHelper()).getBultosRegistroEntrada(entry);
			request.getSession().setAttribute("listabultos", bultos);
			if (bultos != null && bultos.size() > 0)
				request.getSession().setAttribute("paletUno", bultos.get(0));
			else
				request.getSession().setAttribute("paletUno", new Bulto());
			request.getSession().setAttribute("updateBulto", false);
			return SUCCESS;
		}
		catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}