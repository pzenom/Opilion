package es.induserco.opilion.presentacion.ubicacion;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;

public class MuestraHuecosSeleccionadosAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion ubicacion = new Ubicacion();

	public String execute() throws Exception{
		System.out.println("Execute MuestraHuecosSeleccionadosAction");
		Vector<Ubicacion> ubicaciones = new Vector<Ubicacion>();
		//Obtengo el mapa de parametros
		Map <String, String[]> parametros = request.getParameterMap();
		String nombreParametro = "", hueco = "", refHueco;
		long idHueco = 0;
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			if((nombreParametro.indexOf("seleccionado_") == 0)){
				Ubicacion u = new Ubicacion();
				hueco = parametros.get(e.getKey())[0];
				String aux[] = hueco.split("__");
				idHueco = Integer.parseInt(aux[0]);
				refHueco = aux[1];
				String registro = aux[2];
				u.setIdHueco(idHueco);
				u.setRegistro(registro);
				u.setNombreHueco(refHueco);
				ubicaciones.add(u);
			}
		}
		request.getSession().setAttribute("ubicaciones", ubicaciones);
		return SUCCESS;
	}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) { this.request = httpServletRequest; }
	public Object getModel(){ return ubicacion; }
}