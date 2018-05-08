package es.induserco.opilion.presentacion.ubicacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class SeleccionUbicacionesEnvasadoAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = -9013888892313309236L;
	private HttpServletRequest request;
	private GestionProduccion gprod = new GestionProduccion();

	public Object getModel() {
		System.out.println("GetModel SeleccionUbicacionesEnvasadoAction");
		gprod.setOrden(request.getParameter("orden"));
		gprod.setEstadoProceso(request.getParameter("estadoProceso"));
		gprod.setIdProducto(Long.parseLong(request.getParameter("idProducto")));
		//gprod.setIdGestion(Long.parseLong(request.getParameter("idEnvasado")));
		gprod.setLoteAsignado(request.getParameter("loteAsignado"));
		gprod.setTipoEan(Integer.parseInt(request.getParameter("tipoEan")));
		return gprod;
	}

	public String execute() throws Exception {
		System.out.println("Execute SeleccionUbicacionesEnvasadoAction");
		//obtengo el mapa de parametros
		Map <String, String[]> parametros = request.getParameterMap();
		String nombreParametro = "", editar = "";
		String orden = gprod.getOrden(), observaciones = "";
		//long idEnvasado = 0;
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			if(nombreParametro.compareTo("editar") == 0)
				editar = parametros.get(e.getKey())[0];
		}
		double cantidadTotal = 0, cantidadElaborada = 0;
		GestionProduccion info = (new GestionEnvasadoHelper().getInfoProcesosEnv(orden, ""));
		cantidadTotal = info.getCantidadProducto();
		cantidadElaborada = info.getCantidadElaborada();
		request.getSession().setAttribute("envasar", cantidadTotal);
		request.getSession().setAttribute("envasado", cantidadElaborada);
		request.getSession().setAttribute("editar", editar);
		request.getSession().setAttribute("nombreProducto", new GestionProductosHelper().getProducto(gprod.getIdProducto()).getNombre());
		boolean ean13 = true;
		if (info.getTipoEan() == 14)
			ean13 = false;
		if (ean13){//1. ESTAMOS ENVASANDO ITEMS
			System.out.println("materias cargadando");
			Vector<LineaProducto> materias = (new GestionEnvasadoHelper().getLineaProducto(orden, "", "", 1));
			System.out.println("materias cargadas");
			int contador = 0;
			long idAnterior = 0;
			contador = materias.size();
			for (int i = 1; i < contador; i++){
				LineaProducto materia = (LineaProducto) materias.elementAt(i - 1);
				idAnterior = materia.getIdLinea();
				materias.elementAt(i).setIdAnterior(idAnterior);
			}
			System.out.println("envases cargando");
			Vector<LineaProducto> envasesAsociados = (new GestionEnvasadoHelper().getLineaProducto(orden, "", "", 2));
			System.out.println("envases cargados");
			contador = envasesAsociados.size();
			for (int i = 1; i < contador; i++){
				LineaProducto envase = (LineaProducto) envasesAsociados.elementAt(i - 1);
				idAnterior = envase.getIdLinea();
				envasesAsociados.elementAt(i).setIdAnterior(idAnterior);
			}
			for (int i = 0; i < materias.size(); i++){
				String entrada = materias.get(i).getRegistroEntrada();
				System.out.println("ubicaciones materias cargando");
				ArrayList<Ubicacion> ubicaciones = new GestionUbicacionesHelper().getUbicacionesEntrada(entrada, true);
				System.out.println("ubicaciones materias cargadas");
				materias.get(i).setUbicaciones(ubicaciones);
			}
			for (int i = 0; i < envasesAsociados.size(); i++){
				String entrada = envasesAsociados.get(i).getRegistroEntrada();
				System.out.println("ubicaciones envases cargando");
				ArrayList<Ubicacion> ubicaciones = new GestionUbicacionesHelper().getUbicacionesEntrada(entrada, true);
				System.out.println("ubicaciones envases cargadas");
				envasesAsociados.get(i).setUbicaciones(ubicaciones);
			}
			
			System.out.println("items cargando");
			Vector<LineaProducto> items =
				(new GestionEnvasadoHelper().getLineaProducto(orden, "", "", 3));
			contador = 0;
			idAnterior = 0;
			contador = items.size();
			for (int i = 1; i < contador; i++){
				LineaProducto producto = (LineaProducto) items.elementAt(i - 1);
				idAnterior = producto.getIdLinea();
				items.elementAt(i).setIdAnterior(idAnterior);
			}
			for (int i = 0; i < contador; i++){
				String entrada = items.get(i).getRegistroEntrada();
				ArrayList<Ubicacion> ubicaciones = new GestionUbicacionesHelper().getUbicacionesEntrada(entrada, false);
				items.get(i).setUbicaciones(ubicaciones);
			}
			System.out.println("items cargadas");
			
			materias.addAll(items);
			request.getSession().setAttribute("ingredientes", materias);
			request.getSession().setAttribute("envases", envasesAsociados);
		}else{//2. ESTAMOS ENVASANDO AGRUPADOS
			Vector<LineaProducto> items =
				(new GestionEnvasadoHelper().getLineaProducto(orden, "", "", 3));
			int contador = 0;
			long idAnterior = 0;
			contador = items.size();
			for (int i = 1; i < contador; i++){
				LineaProducto producto = (LineaProducto) items.elementAt(i - 1);
				idAnterior = producto.getIdLinea();
				items.elementAt(i).setIdAnterior(idAnterior);
			}
			for (int i = 0; i < contador; i++){
				String entrada = items.get(i).getRegistroEntrada();
				ArrayList<Ubicacion> ubicaciones = new GestionUbicacionesHelper().getUbicacionesEntrada(entrada, false);
				items.get(i).setUbicaciones(ubicaciones);
			}
			request.getSession().setAttribute("items", items);
			Vector<LineaProducto> envases =
				(new GestionEnvasadoHelper().getLineaProducto(orden, "", "", 2));
			contador = 0;
			idAnterior = 0;
			contador = envases.size();
			for (int i = 1; i < contador; i++){
				LineaProducto producto = (LineaProducto) envases.elementAt(i - 1);
				idAnterior = producto.getIdLinea();
				envases.elementAt(i).setIdAnterior(idAnterior);
			}
			for (int i = 0; i < contador; i++){
				String entrada = envases.get(i).getRegistroEntrada();
				ArrayList<Ubicacion> ubicaciones =
					new GestionUbicacionesHelper().getUbicacionesEntrada(entrada, false);
				envases.get(i).setUbicaciones(ubicaciones);
			}
			request.getSession().setAttribute("envases", envases);
		}
		request.getSession().setAttribute("orden", orden);
		request.getSession().setAttribute("lote", gprod.getLoteAsignado());
		observaciones = new GestionEnvasadoHelper().getObservacionesEnvasado(orden);
		request.getSession().setAttribute("observaciones", observaciones);
		String estado = gprod.getEstadoProceso();
		if (estado.compareToIgnoreCase("p") == 0)
			estado = "Pendiente";
		else
			if (estado.compareToIgnoreCase("i") == 0)
				estado = "Iniciado";
			else
				if (estado.compareToIgnoreCase("s") == 0)
					estado = "Parado";
				else
					if (estado.compareToIgnoreCase("f") == 0)
						estado = "Finalizado";
					else
						if (estado.compareToIgnoreCase("b") == 0)
							estado = "Bloqueado";
						else
							if (estado.compareToIgnoreCase("a") == 0)
								estado = "Anulado";
		request.getSession().setAttribute("estado", estado);
		if (!ean13)
			return "EAN14";
		else
			return "EAN13";
	}
	
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}
}