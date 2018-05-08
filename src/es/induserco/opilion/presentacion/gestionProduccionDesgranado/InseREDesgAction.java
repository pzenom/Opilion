package es.induserco.opilion.presentacion.gestionProduccionDesgranado;

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

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class InseREDesgAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>, SessionAware{
	
	private HttpServletRequest request;
	private Map session;
	private List listCateDesg; 
	private RegistroEntrada entry = new RegistroEntrada();
	private GestionProduccion gprod = new GestionProduccion();
	private String url;
	public void setListCateDesg(List listCateDesg) {this.listCateDesg = listCateDesg;}
	public List getListCateDesg() {return listCateDesg;}

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("InseGPDesgAction Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		return gprod;
	}

	/**
	 * 1. Indica la cantidad a seleccionar
	 * 2. Se genera la nueva orden (registro GP)
	 * 3. Con las categorias generadas y las cantidades se
	 * generan nuevos Subregistros de Entrada
	 * 4. Se generan los registros de detalle SRE de la GP
	 */
	public String execute() throws Exception {
		String proceso = "Desgranado";
		HashMap mapaCantRE = new HashMap();
		List listRECong = new ArrayList();
		//HashMap categorias = (HashMap) session.get("listCateDesg");
		String[] categorias = request.getParameterValues("listCateDesg");
		String[] cantity = request.getParameterValues("cantidad");
		//Agrega las cantidades a las categorías generadas
		addCategoriasCantidades(mapaCantRE, categorias, cantity);
		//Asigna el idOperario
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		entry.setIdOperario(us.getLogin());	
		System.out.println("El responsable es :" + entry.getIdOperario());
		//Recupera el código del RE Padre
		entry.setCodigoEntrada((String)request.getSession().getAttribute("codigoEntrada"));		
		System.out.println("El cod de entrada es " + entry.getCodigoEntrada());
		request.getSession().setAttribute("registro", entry.getCodigoEntrada());
		//Cantidad a seleccionar
		String saldos=request.getParameter("saldoregistro");
		System.out.println("El saldo es "+saldos);		
		entry.setSaldo(Double.parseDouble(saldos));
		/** SACAMOS DE LAS UBICACIONES  **/
		Vector<Ubicacion> ubicaciones = new Vector<Ubicacion>();
		/*String destrioString = request.getParameter("destrio");
		double destrio = Double.parseDouble(destrioString);*/
		//entry.setSaldo(destrio);
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
		//Crea los nuevos SRE basados en las categorías y cantidades guardadas en el map
		String orden =
			new GestionRegistroEntradaHelper().addRegistrosSubEntradaProceso(entry, mapaCantRE, proceso, entry.getSaldo());
		setUrl("UbicarProceso.action?tipoProceso=Desgranado&proceso=" + orden);
		request.getSession().setAttribute("tipoProceso", "Desgranado");
		request.getSession().setAttribute("proceso", orden);
		request.setAttribute("tipoProceso", "Desgranado");
		request.setAttribute("proceso", orden);
		return "redirect";
	}
	
	private void addCategoriasCantidades(HashMap mapaCantRE, String[] categorias, String[] cantity) {
		int cont = 0;
		//System.out.println("cant es... "+cant.toString());
		for(int j=0;j<cantity.length;j++){
			if (!cantity[j].equals("0")){
				cont++;
			}
		}
		//Cont = numero de clases seleccionadas
		String[] cant=new String[cont];
		int siguienteInserta = 0;
		for(int j=0;j<cantity.length;j++){
			if (!cantity[j].equals("0")){
					System.out.println(cantity[j]);
					cant[siguienteInserta++]=cantity[j];
			}
		}		
		if (categorias == null ){
			//categorias = new HashMap();
			session.put("Categorias",categorias);
		}		
		for (int i=0;i<cant.length;i++){
			Iterator iter = (Iterator) listCateDesg.iterator();
			while ( iter.hasNext()){
				String ing = (String)iter.next();
				ing = ing.substring(0, ing.length()-1);
				System.out.println("categoria añadida a la lista: "+ing);
				System.out.println("cantidad a procesar "+cant[i]);
				System.out.println("Voy a agregar "+ing+" con "+cant[i]);
				mapaCantRE.put(ing, cant[i]);
				i++;
			}
		}
		for (Iterator it = mapaCantRE.keySet().iterator(); it.hasNext();) { 
            String s = (String)it.next();
            String s1 = (String)mapaCantRE.get(s);
            System.out.println("Map creado "+ s + " : " + s1);
		}
	}

	//@Override
	public void setSession(Map session) { this.session = (Map) session; }
	public void setUrl(String url) { this.url = url; }
	public String getUrl() { return url; }
}