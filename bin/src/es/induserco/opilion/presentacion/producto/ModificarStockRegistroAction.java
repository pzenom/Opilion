package es.induserco.opilion.presentacion.producto;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class ModificarStockRegistroAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object> {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion ubicacion = new Ubicacion();

	public String execute() throws Exception{
		String lote = ((String) request.getParameter("lote"));
		double cantidad = Double.parseDouble(request.getParameter("cantidad"));
		long idHueco = Long.parseLong(request.getParameter("idHueco"));
		String causas = ((String) request.getParameter("causas"));
		String usuarioResponsable = ((Usuario) request.getSession().getAttribute("usuario")).getLogin();
		new GestionProductosHelper().modificarStockRegistro(lote, cantidad, idHueco, usuarioResponsable, causas);
		return SUCCESS;
	}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) { this.request = httpServletRequest; }
	public Object getModel(){ return ubicacion; }
}