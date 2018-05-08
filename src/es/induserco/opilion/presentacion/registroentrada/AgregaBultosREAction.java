package es.induserco.opilion.presentacion.registroentrada;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class AgregaBultosREAction extends ActionSupport
	implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven {

	private HttpServletRequest request;
	private Bulto bulto = new Bulto();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Agrega Bultos RE Action");
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		return bulto;
	}

	public String execute() throws Exception {
		try {
			System.out.println("Estamos en el execute de AgregaBultos");
			bulto.setCodigoEntrada((String)request.getParameter("codigoEntrada"));
			bulto.setPeso(Double.parseDouble(bulto.getResponsable()));
			//para que el dao sepa que se trata de inicializar/actualizacion masiva
			new GestionRegistroEntradaHelper().inicializaBultosRE(bulto, 0);
			//como vamos a cargar la misma página hay que volver a cargar los datos						
			RegistroEntrada entry = new RegistroEntrada();
			entry.setCodigoEntrada(bulto.getCodigoEntrada());
			//obtienes el registro en cuestión
			request.getSession().setAttribute("listaregistros",(new GestionRegistroEntradaHelper()).loadRegistroEntrada(entry));
			//miramos los bultos creados
			request.getSession().setAttribute("listabultos",(new GestionRegistroEntradaHelper()).getBultosRegistroEntrada(entry));
			return SUCCESS;
		}
		catch (Exception e) {
			return (ERROR);
		}
	}
}