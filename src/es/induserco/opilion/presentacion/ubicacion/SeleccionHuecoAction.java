package es.induserco.opilion.presentacion.ubicacion;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class SeleccionHuecoAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion ubicacion = new Ubicacion();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel(){
		return ubicacion;
	}

	public String execute() throws Exception{
		/*long idZona = ubicacion.getIdZona();
		long idAlmacen = ubicacion.getIdAlmacen();
		long idLinea = ubicacion.getIdLinea();
		long idEstanteria = ubicacion.getIdEstanteria();
		long idPiso = ubicacion.getIdPiso();
		long idHueco = ubicacion.getIdHueco();*/
		Ubicacion u = new GestionUbicacionesHelper().getUbicacion(ubicacion);
		//request.getSession().setAttribute("idZona", idZona);
		//request.getSession().setAttribute("idAlmacen", idAlmacen);
		u.setRegistro(ubicacion.getRegistro());
		request.getSession().setAttribute("ubicacion", u);
		Vector<Ubicacion> ubicaciones =
			new GestionUbicacionesHelper().getRegistrosAlmacenados(ubicacion.getIdHueco());
		request.getSession().setAttribute("ubicaciones", ubicaciones);
		return SUCCESS;
	}
}