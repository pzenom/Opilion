package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;

public class UbicarPaletAction extends ActionSupport implements
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
		System.out.println("Execute UbicarPaletAction");
		String idTipoUbicacion = request.getParameter("idTipoUbicacion");
		boolean sacar = Boolean.parseBoolean(request.getParameter("sacar"));
		request.getSession().setAttribute("sacar", sacar);
		boolean mover = Boolean.parseBoolean(request.getParameter("mover"));
		request.getSession().setAttribute("mover", mover);
		boolean meter = Boolean.parseBoolean(request.getParameter("meter"));
		request.getSession().setAttribute("meter", meter);
		boolean gestionBultos = Boolean.parseBoolean(request.getParameter("gestionBultos"));
		request.getSession().setAttribute("gestionBultos", gestionBultos);
		int almacen = Integer.parseInt(idTipoUbicacion);
		//Primero comprobamos si se trata de un almacén que tengamos definido (con plazo, zonas, etc)
		if (almacen == 1){
			setUrl("UbicacionBarcia?codigoEntrada=" + ubicacion.getRegistro() +
					"&idPalet=" + ubicacion.getIdPalet() + "&cantidad=" + ubicacion.getCantidad());
			return "redirect";
		}else{
			//Si no, lo tratamos como si fuera un vehículo, aunque sea un almacen
			if (almacen > 0){
				setUrl("UbicacionVehiculo?codigoEntrada=" + ubicacion.getRegistro() +
						"&idPalet=" + ubicacion.getIdPalet() + "&cantidad=" + ubicacion.getCantidad() + "&idVehiculo=" + almacen);
				return "redirect";
			}
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