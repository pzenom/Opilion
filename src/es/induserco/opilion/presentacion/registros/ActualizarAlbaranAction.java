package es.induserco.opilion.presentacion.registros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class ActualizarAlbaranAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven {

	private static final long serialVersionUID = 422840826723506717L;
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

 	public String execute() throws IOException {
 		System.out.println("Actualizar AlbaranAction Action...");
		try {
			Map <String, String[]> parametros = request.getParameterMap();
			String nombreParametro = "";
			List<RegistroSalida> lineas = new ArrayList<RegistroSalida>();
			Iterator iterator = parametros.entrySet().iterator();
			String descripcion = "";
			while(iterator.hasNext()) {
				descripcion = "";
				Map.Entry e = (Map.Entry) iterator.next();
				nombreParametro = (String) e.getKey();
				if (nombreParametro.indexOf("descripcion_") > -1){
					RegistroSalida linea = new RegistroSalida();
					descripcion = parametros.get(e.getKey())[0];
					String frag[] = nombreParametro.split("_");
					String numeroLinea = frag[1];
					String idBultoString = frag[2];
					Iterator iterator2 = parametros.entrySet().iterator();
					while(iterator2.hasNext()){
						Map.Entry e2 = (Map.Entry) iterator2.next();
						String nombreParametro2 = (String) e2.getKey();
						if (nombreParametro2.equals("precio_" + numeroLinea + "_" + idBultoString)){
							linea.setNumLinea(Long.parseLong(numeroLinea));
							linea.setIdBulto(Long.parseLong(idBultoString));
							linea.setDescripcion(descripcion);
							linea.setPrecioKilo(Double.parseDouble(parametros.get(e2.getKey())[0]));
							lineas.add(linea);
							break;
						}
					}
				}
			}
			albaran.setImporteTotal(Double.parseDouble(request.getParameter("total")));
			//TODO: graneles vs items
			//albaran.setRegistrosSalida(lineas);
			//Actualizamos el albarán
			Usuario us = (Usuario) request.getSession().getAttribute("usuario");
			albaran.setUsuarioResponsable(us.getLogin());
			new GestionRegistroSalidaHelper().actualizarAlbaran(albaran);
			return SUCCESS;
		} catch (Exception e) { e.printStackTrace(); }
		return ERROR;	 
	 }
}