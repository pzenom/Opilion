package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class InseDetaAlbaAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private Albaran albaran = new Albaran();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Insertar Albaran Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		return albaran;
	}

	public String execute() throws Exception {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		albaran.setUsuarioResponsable(us.getLogin());
		albaran.setCodigoAlbaran((String)request.getSession().getAttribute("albaran"));
		//Tipo de Albaran Simple
		albaran.setTipoAlbaran("S");
		String radFact = (String)request.getParameter("radFact");
		System.out.println("cliente" + albaran.getCliente().getIdUsuario());
		System.out.println("comercial" + albaran.getComercial().getIdUsuario());
		System.out.println("albaran" + albaran.getCodigoAlbaran());
		System.out.println("resp" + albaran.getUsuarioResponsable());
		new GestionRegistroSalidaHelper().addAlbaran(albaran);
		//new GestionRegistroSalidaHelper().addRegistroSalida(null);
		
		if (radFact.equals("N"))
			return "redirect";
		else
			return SUCCESS;
	}
}