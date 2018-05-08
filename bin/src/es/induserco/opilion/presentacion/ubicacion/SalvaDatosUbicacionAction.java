package es.induserco.opilion.presentacion.ubicacion;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class SalvaDatosUbicacionAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object>{
	
	public String execute() throws Exception{
		String usuarioResponsable = ((Usuario) request.getSession().getAttribute("usuario")).getLogin();
		System.out.println("Execute SalvaDatosUbicacionAction");
		Vector<Ubicacion> ubicaciones = new Vector<Ubicacion>();
		//Obtengo el mapa de parametros
		Map <String, String[]> parametros = request.getParameterMap();
		String nombreParametro = "", hueco = "", refHueco = "";
		long idHueco = 0;
		boolean movimiento = false;//Define si estamos en un movimiento entre almacenes o no
		boolean gestionBultos = Boolean.parseBoolean(request.getParameter("gestionBultos"));
		boolean reubicar = Boolean.parseBoolean(request.getParameter("reubicar"));
		boolean devolucion = Boolean.parseBoolean(request.getParameter("devolucion"));
		boolean ubicar = Boolean.parseBoolean(request.getParameter("ubicar"));
		boolean seleccion = Boolean.parseBoolean(request.getParameter("seleccion"));
		boolean esVehiculo = Boolean.parseBoolean((String) request.getParameter("ubicacionVehiculo"));
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			if (esVehiculo ||
					Boolean.parseBoolean((String) request.getParameter("ubicacionBigBag")) == true){
				ubicacion.setEsVehiculo(true);
				//System.out.println("El almacen es un veh�culo");
				if((nombreParametro.indexOf("seleccionado") == 0)){
					String registro = ubicacion.getRegistro();
					long idPalet = ubicacion.getIdPalet();
					idHueco = ubicacion.getIdHueco();
					if (gestionBultos){
						setUrl("BultosRE?codigoEntrada=" + registro);
						movimiento = true;
					}else
						if (reubicar){
							char estadoChar = 'X';
							String estado = request.getParameter("estado");
							if (estado.compareTo("Finalizado") == 0 || estado.compareTo("F") == 0)
								estadoChar = 'F';
							setUrl("VisualizaProcesoEnvasado?idEnvasado=" + request.getParameter("idEnvasado") +
								"&orden=" + request.getParameter("orden") + "&estado=" + estadoChar +
								"&lote=" + request.getParameter("lote"));
							new GestionUbicacionesHelper().reubicar(request.getParameter("orden"), registro,
									request.getParameter("idUbicacion"), "" + idHueco,
									Long.parseLong(request.getParameter("idPalet")), 0);
							request.getSession().setAttribute("idEnvasado", request.getParameter("idEnvasado"));
						}else
							if (seleccion){
								setUrl("UbicarProceso?proceso=" + request.getParameter("procesoSeleccion"));
								request.getSession().setAttribute("proceso",
										request.getParameter("procesoSeleccion"));
								request.getSession().setAttribute("tipoProceso",
										request.getParameter("tipoProceso"));
								if (request.getParameter("tipoProceso").compareTo("Congelado") == 0 ||
										request.getParameter("tipoProceso").compareTo("Fumigado") == 0){
									if (request.getParameter("tipoProceso").compareTo("Congelado") == 0)
										ubicacion.setCongelado("C");
									if (request.getParameter("tipoProceso").compareTo("Fumigado") == 0)
										ubicacion.setCongelado("F");
									ubicacion.setOrden(request.getParameter("procesoSeleccion"));
									new GestionUbicacionesHelper().ubicarCongelado(request.getParameter("procesoSeleccion"),
											request.getParameter("tipoProceso"), ubicacion);
								}else{
									new GestionUbicacionesHelper().ubicarSeleccion(registro, "" + idHueco,(long)0,
											ubicacion.getCantidad());
									ubicacion.setEstaUbicado(true);
								}
								request.getSession().setAttribute("idEnvasado", request.getParameter("idEnvasado"));
							}else
								if (ubicar){
									char estadoChar = 'X';
									String estado = request.getParameter("estado");
									if (estado.compareTo("Finalizado") == 0 || estado.compareTo("F") == 0)
										estadoChar = 'F';
									setUrl("VisualizaProcesoEnvasado?idEnvasado=" + request.getParameter("idEnvasado") +
										"&orden=" + request.getParameter("orden") + "&estado=" + estadoChar +
										"&lote=" + request.getParameter("lote"));
									new GestionUbicacionesHelper().reubicar(request.getParameter("orden"), registro,
											"" + idHueco, "", Long.parseLong(request.getParameter("idPalet")),
											ubicacion.getCantidad());
									request.getSession().setAttribute("idEnvasado", request.getParameter("idEnvasado"));
								} else{
									if (devolucion){
										setUrl("ListaDevoluciones.action");
									}else{
										setUrl("Inicio.action");
										movimiento = true;
									}
								}
					ubicacion.setNombreHueco(refHueco);
					if (!seleccion)
						ubicacion.setIdPalet(ubicacion.getIdPalet());
					Ubicacion u = new Ubicacion();
					copiarUbicaciones(ubicacion, u);
					u.setIdPalet(idPalet);
					u.setUsuarioResponsable(usuarioResponsable);
					ubicaciones.add(u);
				}				
			}else{
				if((nombreParametro.indexOf("seleccionado") == 0)){
					Ubicacion u = new Ubicacion();
					copiarUbicaciones(ubicacion, u);
					hueco = parametros.get(e.getKey())[0];
					String aux[] = hueco.split("__");
					idHueco = Integer.parseInt(aux[0]);
					refHueco = aux[1];
					String registro = aux[2];
					u.setIdHueco(idHueco);
					u.setRegistro(registro);
					if (gestionBultos){
						setUrl("BultosRE?codigoEntrada=" + registro);
						movimiento = true;
					}else
						if (reubicar){
							char estadoChar = 'X';
							String estado = request.getParameter("estado");
							if (estado.compareTo("Finalizado") == 0 || estado.compareTo("F") == 0)
								estadoChar = 'F';
							setUrl("VisualizaProcesoEnvasado?idEnvasado=" + request.getParameter("idEnvasado") +
								"&orden=" + request.getParameter("orden") + "&estado=" + estadoChar +
								"&lote=" + request.getParameter("lote"));
							new GestionUbicacionesHelper().reubicar(request.getParameter("orden"), u.getRegistro(),
									request.getParameter("idUbicacion"), "" + u.getIdHueco(),
									Long.parseLong(request.getParameter("idPalet")), 0);
							request.getSession().setAttribute("idEnvasado", request.getParameter("idEnvasado"));
						}else
							if (seleccion){
								setUrl("UbicarProceso?proceso=" + request.getParameter("procesoSeleccion"));
								u.setIdPalet(0);
								request.getSession().setAttribute("proceso",
										request.getParameter("procesoSeleccion"));
								request.getSession().setAttribute("tipoProceso",
										request.getParameter("tipoProceso"));
								if (request.getParameter("tipoProceso").compareTo("Congelado") == 0 ||
										request.getParameter("tipoProceso").compareTo("Fumigado") == 0){
									if (request.getParameter("tipoProceso").compareTo("Congelado") == 0)
										u.setCongelado("C");
									if (request.getParameter("tipoProceso").compareTo("Fumigado") == 0)
										u.setCongelado("F");
									u.setOrden(request.getParameter("procesoSeleccion"));
									new GestionUbicacionesHelper().ubicarCongelado(request.getParameter("procesoSeleccion"),
											request.getParameter("tipoProceso"), ubicacion);
								}else{
									new GestionUbicacionesHelper().ubicarSeleccion(registro, "" + u.getIdHueco(),(long)0,
											ubicacion.getCantidad());
									u.setEstaUbicado(true);
								}
								request.getSession().setAttribute("idEnvasado", request.getParameter("idEnvasado"));
							}else
								if (ubicar){
									char estadoChar = 'X';
									String estado = request.getParameter("estado");
									if (estado.compareTo("Finalizado") == 0 || estado.compareTo("F") == 0)
										estadoChar = 'F';
									setUrl("VisualizaProcesoEnvasado?idEnvasado=" + request.getParameter("idEnvasado") +
										"&orden=" + request.getParameter("orden") + "&estado=" + estadoChar +
										"&lote=" + request.getParameter("lote"));
									new GestionUbicacionesHelper().reubicar(request.getParameter("orden"), u.getRegistro(),
											"" + u.getIdHueco(), "", Long.parseLong(request.getParameter("idPalet")),
											ubicacion.getCantidad());
									request.getSession().setAttribute("idEnvasado", request.getParameter("idEnvasado"));
								} else{
									if (devolucion){
										setUrl("ListaDevoluciones.action");
									}else{
										setUrl("Inicio.action");
										movimiento = true;
									}
								}
					u.setNombreHueco(refHueco);
					if (!seleccion)
						u.setIdPalet(ubicacion.getIdPalet());
					u.setCantidad(ubicacion.getCantidad());
					u.setOrden(ubicacion.getOrden());
					u.setUsuarioResponsable(usuarioResponsable);
					ubicaciones.add(u);
				}
			}
		}
		new GestionUbicacionesHelper().salvarUbicaciones(ubicaciones, movimiento);
		return "redirect";
	}
	
	private void copiarUbicaciones(Ubicacion ubicacion, Ubicacion u) {
		u.setCantidad(ubicacion.getCantidad());
		u.setEsVehiculo(ubicacion.isEsVehiculo());
		u.setIdHueco(ubicacion.getIdHueco());
		u.setIdUbicacion(ubicacion.getIdUbicacion());
		u.setOrden(ubicacion.getOrden());
		u.setRegistro(ubicacion.getRegistro());
		u.setNumeroBultos(ubicacion.getNumeroBultos());
		u.setCongelado(ubicacion.getCongelado());
		u.setIdPalet(ubicacion.getIdPalet());
	}

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion ubicacion = new Ubicacion();
	private String url;
	
	public void setUrl(String url) { this.url = url; }
	public String getUrl() { return this.url; }
	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) { this.request = httpServletRequest; }
	//@Override
	public Object getModel(){ return ubicacion; }
}