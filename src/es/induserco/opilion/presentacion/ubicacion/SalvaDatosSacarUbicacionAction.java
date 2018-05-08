package es.induserco.opilion.presentacion.ubicacion;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class SalvaDatosSacarUbicacionAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion ubicacion = new Ubicacion();
	private String url;

	public String execute() throws Exception{
		String usuarioResponsable = ((Usuario) request.getSession().getAttribute("usuario")).getLogin();
		System.out.println("Execute SalvaDatosSacarUbicacionAction");
		Vector<Ubicacion> ubicaciones = new Vector<Ubicacion>();
		//Obtengo el mapa de parametros
		Map <String, String[]> parametros = request.getParameterMap();
		String nombreParametro = "";
		String moverString = request.getParameter("mover");
		boolean mover = Boolean.parseBoolean(moverString);
		String gestionBultosString = request.getParameter("gestionBultos");
		boolean bultos = Boolean.parseBoolean(gestionBultosString);
		boolean reubicar = Boolean.parseBoolean(request.getParameter("reubicar"));
		String incidenciaString = request.getParameter("incidencia");
		boolean incidencia = Boolean.parseBoolean(incidenciaString);
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			//if((nombreParametro.indexOf("seleccionado_") == 0)){
			if (!incidencia){
				if((nombreParametro.indexOf("sacarRegistro_") == 0)){
					String cantidades[] = parametros.get(e.getKey());
					int cuanto = cantidades.length;
					for (int i = 0; i < cuanto; i++){
						if (Double.parseDouble(cantidades[i]) > 0){
							Ubicacion u = new Ubicacion();
							u.setIdHueco(ubicacion.getIdHueco());//ID HUECO
							String aux[] = nombreParametro.split("_");
							String registro = aux[1];
							u.setRegistro(registro);
							//u.setIdPalet(ubicacion.getIdPalet());
							u.setCantidad(Double.parseDouble(cantidades[i]));
							aux = nombreParametro.split("__");
							u.setIdUbicacion(Long.parseLong(aux[1]));
							u.setIdPalet(Long.parseLong(aux[2]));
							u.setUsuarioResponsable(usuarioResponsable);
							ubicaciones.add(u);
							//boolean mover = Boolean.parseBoolean((String) request.getParameter("mover"));
							request.getSession().setAttribute("cantidad", u.getCantidad());
							request.getSession().setAttribute("idPalet", u.getIdPalet());
							request.getSession().setAttribute("registro", u.getRegistro());
							if (mover){
								if (reubicar){
									setUrl("SeleccionAlmacen.action");
									//setUrl("SeleccionAlmacen?reubicar=true&mover=false&meter=true&gestionBultos=" + bultos);
								}else{
									setUrl("SeleccionAlmacen.action");
									//setUrl("SeleccionAlmacen?reubicar=false&mover=false&meter=true&gestionBultos=" + bultos);
								}
								request.getSession().setAttribute("mover", false);
								request.getSession().setAttribute("meter", true);
								request.getSession().setAttribute("gestionBultos", bultos);
							}else
								setUrl("ConsOE");
						}
					}
				}
			}else{
				if (incidencia){
					if((nombreParametro.indexOf("mermaRegistro_") == 0)){
						String cantidades[] = parametros.get(e.getKey());
						int cuanto = cantidades.length;
						for (int i = 0; i < cuanto; i++){
							if (Double.parseDouble(cantidades[i]) > 0){
								Ubicacion u = new Ubicacion();
								u.setIdHueco(ubicacion.getIdHueco());//ID HUECO
								String aux[] = nombreParametro.split("_");
								String registro = aux[1];
								u.setRegistro(registro);
								u.setIdPalet(ubicacion.getIdPalet());
								u.setCantidad(Double.parseDouble(cantidades[i]));
								aux = nombreParametro.split("__");
								u.setIdUbicacion(Long.parseLong(aux[1]));
								u.setIdPalet(Long.parseLong(aux[2]));
								u.setDescripcion(request.getParameter("observacionIncidencia"));
								u.setUsuarioResponsable(usuarioResponsable);
								ubicaciones.add(u);
								//boolean mover = Boolean.parseBoolean((String) request.getParameter("mover"));
								request.getSession().setAttribute("cantidad", u.getCantidad());
								request.getSession().setAttribute("idPalet", u.getIdPalet());
								setUrl("ConsMermas");
							}
						}
					}
				}
			}
		}
		new GestionUbicacionesHelper().sacarUbicaciones(ubicaciones, incidencia, false, false);
		return "redirect";
	}
	public void setUrl(String url) { this.url = url; }
	public String getUrl() { return url; }
	
	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel(){
		return ubicacion;
	}
}