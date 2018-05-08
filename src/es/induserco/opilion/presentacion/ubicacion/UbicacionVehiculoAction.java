package es.induserco.opilion.presentacion.ubicacion;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class UbicacionVehiculoAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object>{

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

	public String execute() throws Exception {
		System.out.println("UbicacionVehiculoAction");
		int idVehiculo = Integer.parseInt(request.getParameter("idVehiculo"));
		request.getSession().setAttribute("idAlmacen", idVehiculo);
		request.getSession().setAttribute("cantidad", request.getParameter("cantidad"));
		request.getSession().setAttribute("registro", request.getParameter("codigoEntrada"));
		int idPalet = Integer.parseInt((String)request.getParameter("idPalet"));
		request.getSession().setAttribute("idPalet", idPalet);
		
		//boolean sacar = Boolean.parseBoolean((String) request.getSession().getAttribute("sacar"));
		request.getSession().setAttribute("sacar", request.getSession().getAttribute("sacar"));
		
		//boolean sacar = );

		//1. Cargar los registros guardados para el idVehiculo (Registro, cantidad...)
		
		//UbicacionDAO: public Vector<Ubicacion> getRegistrosAlmacenados(long idHueco)
		//Vehiculo I: idAlmacen 2 -> idHueco 200
		Vector<Ubicacion> ubicaciones = null;
		Ubicacion ubicacion = new Ubicacion();
		
		long idHueco = new GestionUbicacionesHelper().getIdHuecoVehiculo(idVehiculo);
		ubicaciones = new GestionUbicacionesHelper().getRegistrosAlmacenados(idHueco);
		ubicacion.setIdHueco(idHueco);
		/*switch (idVehiculo){
		case 2:
			ubicaciones = new GestionUbicacionesHelper().getRegistrosAlmacenados(200);
			ubicacion.setIdHueco(200);
			break;
		case 3:
			ubicaciones = new GestionUbicacionesHelper().getRegistrosAlmacenados(201);
			ubicacion.setIdHueco(201);
			break;
		case 4:
			ubicaciones = new GestionUbicacionesHelper().getRegistrosAlmacenados(202);
			ubicacion.setIdHueco(202);
			break;
		}*/
		request.getSession().setAttribute("ubicaciones", ubicaciones);
		ubicacion = new GestionUbicacionesHelper().getUbicacion(ubicacion);
		request.getSession().setAttribute("ubicacion", ubicacion);
		request.getSession().setAttribute("ubicacionVehiculo", true);
		
		//2. Desde js, mirar si es para mover, incidencia, etc, y habilitar/deshabilitar
		
		return SUCCESS;
	}
}