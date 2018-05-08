package es.induserco.opilion.presentacion.gestionProduccionCongelado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionCongeladoHelper;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class UpdaRECongCantAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>, SessionAware{
	
	private HttpServletRequest request;
	private Map session;
	private List listProcSele;
	private GestionProduccion gpro = new GestionProduccion();
	private GestionProduccion gprod = new GestionProduccion();
	public void setListProcSele(List listProcSele) {this.listProcSele = listProcSele;}
	public List getListProcSele() { return listProcSele; }
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("UpdaRECongCantAction Action...");
		this.request=request;	
	}
	//@Override
	public Object getModel() {
		return gprod;
	}

	public String execute() throws Exception {
		
		HashMap mapaCantRE=new HashMap();
		List listRECong = new ArrayList();
		HashMap Envases = (HashMap) session.get("listProcSele");
		String[] cant = request.getParameterValues("cantidad");
		if (Envases == null ){
			Envases=new HashMap();
			session.put("Envases",Envases);
		}
		for (int i=0;i<cant.length;i++){
			Iterator iter = (Iterator) listProcSele.iterator();
			while ( iter.hasNext()){
				String ing = (String)iter.next();
				ing = ing.substring(0, ing.length());
				System.out.println("A�adiendo a la lista: " + ing);
				System.out.println("cantidad a procesar " + cant[i]);
				mapaCantRE.put(ing, cant[i]);
				i++;
			}
		}
		for( Iterator it = mapaCantRE.keySet().iterator(); it.hasNext();) { 
            String s = (String)it.next();
            String s1 = (String)mapaCantRE.get(s);
            System.out.println("Map creado "+ s + " : " + s1);
		}
		//Actualiza los datos de los RE de envases
		gprod.setOrden((String)request.getSession().getAttribute("orden"));
		request.getSession().setAttribute("envases",
				(new GestionDesgranadoHelper().updateRECantProceso(mapaCantRE,gprod.getOrden(),"Congelado")));
		
		/** SACAMOS DE LAS UBICACIONES  **/
		Vector<Ubicacion> ubicaciones = new Vector<Ubicacion>();
		//Obtengo el mapa de parametros
		Map <String, String[]> parametros = request.getParameterMap();
		String nombreParametro = "", hueco = "", refHueco;
		long idUbicacion = 0;
		double cantidad = 0;
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			if((nombreParametro.indexOf("sacar_") > -1)){
				Ubicacion u = new Ubicacion();
				String aux[] = nombreParametro.split("_");
				idUbicacion = Integer.parseInt(aux[1]);
				cantidad = Double.parseDouble((String)parametros.get(e.getKey())[0]);
				String registro = aux[2];
				if (cantidad > 0){
					u.setIdUbicacion(idUbicacion);
					u.setCantidad(cantidad);
					u.setRegistro(registro);
					ubicaciones.add(u);
				}
			}
		}
		new GestionUbicacionesHelper().sacarUbicaciones(ubicaciones, false, false, true);
		new GestionCongeladoHelper().guardarUbicacionesCongelado(ubicaciones, gprod.getOrden(), "Congelado");
		
		return SUCCESS;
	}

	//@Override
	public void setSession(Map session) {
		this.session = (Map) session;
	}
}