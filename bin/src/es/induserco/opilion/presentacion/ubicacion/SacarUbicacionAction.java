package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;

public class SacarUbicacionAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion ubicacion = new Ubicacion();
	private String url;

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel(){
		return ubicacion;
	}

	public String execute() throws Exception{
		System.out.println("Execute SacarUbicacionAction");
		String idTipoUbicacion = request.getParameter("idTipoUbicacion");
		switch (Integer.parseInt(idTipoUbicacion)){
		case 1:
			setUrl("SacarUbicacionBarcia?codigoEntrada=" + ubicacion.getRegistro() +
				"&idPalet=" + ubicacion.getIdPalet());
			return "redirect";
			//return "barcia";
		case 2:
			//return "naveantigua";
		case 3:
			//return "operadorlogico";
		case 4:
			//return "autoventa";
		}
		return ERROR;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}