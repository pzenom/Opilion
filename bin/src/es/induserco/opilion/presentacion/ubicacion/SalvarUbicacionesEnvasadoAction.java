package es.induserco.opilion.presentacion.ubicacion;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class SalvarUbicacionesEnvasadoAction extends ActionSupport implements
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
		System.out.println("Execute SalvarUbicacionesEnvasadoAction");
		Vector<Ubicacion> ubicaciones = new Vector<Ubicacion>();
		//Obtengo el mapa de parametros
		Map <String, String[]> parametros = request.getParameterMap();
		String nombreParametro = "", hueco = "", refHueco;
		long idUbicacion = 0;
		long idHueco = 0;
		double cantidad = 0;
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			if((nombreParametro.indexOf("sacar_") > -1)){
				Ubicacion u = new Ubicacion();
				String aux[] = nombreParametro.split("_");
				cantidad = Double.parseDouble((String)parametros.get(e.getKey())[0]);
				if (cantidad > 0){
					String registro = "";
					String idPalet = "";
					idHueco = Integer.parseInt(aux[1]);
					idUbicacion = Integer.parseInt(aux[2]);
					registro = aux[3];
					if (aux.length == 6){
						idPalet = aux[4];
						u.setIdPalet(Long.parseLong(idPalet));
					}
					u.setIdHueco(idHueco);
					u.setIdUbicacion(idUbicacion);
					u.setCantidad(cantidad);
					u.setRegistro(registro);
					ubicaciones.add(u);
				}
			}
		}
		new GestionUbicacionesHelper().sacarUbicaciones(ubicaciones, false, false, true);
		if (new GestionEnvasadoHelper().setUbicado(request.getParameter("orden"), ubicaciones, true))
			return SUCCESS;
		else
			return ERROR;
	}
}