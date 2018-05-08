package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.negocio.RegistroSalidaDataHelper;


public class EntregarAlbaranAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Albaran albaran = new Albaran();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	//@Override
	public Object getModel() {
		return albaran;
	}

	public String execute() throws Exception {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		albaran.setUsuarioResponsable(us.getLogin());
		System.out.println("EntregarAlbaranAction...");
		new RegistroSalidaDataHelper().entregarAlbaran(albaran.getCodigoAlbaran(), us.getLogin());
		return SUCCESS;
	}
}