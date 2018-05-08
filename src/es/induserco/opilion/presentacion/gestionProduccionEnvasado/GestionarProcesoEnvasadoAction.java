package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class GestionarProcesoEnvasadoAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = -9013888892313309236L;
	private HttpServletRequest request;
	private GestionProduccion gprod = new GestionProduccion();

	//@Override
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}

	//@Override
	public Object getModel() {
		System.out.println("GetModel GestionarProcesoEnvasado");
		return gprod;
	}

	public String execute() throws Exception {
		System.out.println("Execute GestionarProcesoEnvasado");
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
		double cantidadTotal = 0, cantidadElaborada = 0, cantidadElaboradaAgrupacion = 0;
		GestionProduccion info = new GestionEnvasadoHelper().getInfoProcesosEnv(orden, "");
		gprod.setLoteAsignado(info.getLoteAsignado());
		gprod.setEstadoProceso(info.getEstadoProceso());
		gprod.setIdProducto(info.getIdProducto());
		cantidadTotal = info.getCantidadProducto();
		cantidadElaborada = info.getCantidadElaborada();
		request.getSession().setAttribute("envasar", cantidadTotal);
		request.getSession().setAttribute("envasado", cantidadElaborada);
		request.getSession().setAttribute("editar", editar);
		if (gprod.getIdProducto() == 0)
			gprod.setIdProducto((Long) request.getSession().getAttribute("idProducto"));
		request.getSession().setAttribute("idProducto", gprod.getIdProducto());
		request.getSession().setAttribute("nombreProducto",
				new GestionProductosHelper().getProducto(gprod.getIdProducto()).getNombre());
		boolean ean13 = true;
		if (info.getTipoEan() == 14)
			ean13 = false;
		if (ean13){//1. Envasando items
			Vector<LineaProducto> materias =
				(new GestionEnvasadoHelper().getEnvasesMateriasEnvasado(orden, "", 1));
			int contador = 0;
			long idAnterior = 0;
			contador = materias.size();
			for (int i = 1; i < contador; i++){
				LineaProducto materia = (LineaProducto) materias.elementAt(i - 1);
				idAnterior = materia.getIdLinea();
				materias.elementAt(i).setIdAnterior(idAnterior);
			}
			Vector<LineaProducto> envasesAsociados =
				(new GestionEnvasadoHelper().getEnvasesMateriasEnvasado(orden, "", 2));
			contador = envasesAsociados.size();
			for (int i = 1; i < contador; i++){
				LineaProducto envase = (LineaProducto) envasesAsociados.elementAt(i - 1);
				idAnterior = envase.getIdLinea();
				envasesAsociados.elementAt(i).setIdAnterior(idAnterior);
			}
			Vector<LineaProducto> items =
				(new GestionEnvasadoHelper().getEnvasesMateriasEnvasado(orden, "", 3));
			contador = 0;
			idAnterior = 0;
			contador = items.size();
			for (int i = 1; i < contador; i++){
				LineaProducto producto = (LineaProducto) items.elementAt(i - 1);
				idAnterior = producto.getIdLinea();
				items.elementAt(i).setIdAnterior(idAnterior);
			}
			materias.addAll(items);
			//Vamos a ver si se van a agrupar los items generados
			request.getSession().setAttribute("ingredientes", materias);
			request.getSession().setAttribute("envases", envasesAsociados);
			boolean agrupar = false;
			if (info.isAgrupar()){
				agrupar = true;
				cantidadElaboradaAgrupacion = info.getCantidadElaboradaAgrupacion();
			}
			request.getSession().setAttribute("agrupacionesEnvasadas", cantidadElaboradaAgrupacion);
			request.getSession().setAttribute("agrupar", agrupar);
		}else{//2. Envasando agrupados
			Vector<LineaProducto> items =
				(new GestionEnvasadoHelper().getEnvasesMateriasEnvasado(orden, "", 3));
			int contador = 0;
			long idAnterior = 0;
			contador = items.size();
			for (int i = 1; i < contador; i++){
				LineaProducto producto = (LineaProducto) items.elementAt(i - 1);
				idAnterior = producto.getIdLinea();
				items.elementAt(i).setIdAnterior(idAnterior);
			}
			request.getSession().setAttribute("items", items);
			Vector<LineaProducto> envases =
				(new GestionEnvasadoHelper().getEnvasesMateriasEnvasado(orden, "", 2));
			contador = envases.size();
			for (int i = 1; i < contador; i++){
				LineaProducto envase = (LineaProducto) envases.elementAt(i - 1);
				idAnterior = envase.getIdLinea();
				envases.elementAt(i).setIdAnterior(idAnterior);
			}
			request.getSession().setAttribute("envases", envases);
		}
		request.getSession().setAttribute("orden", orden);
		request.getSession().setAttribute("lote", gprod.getLoteAsignado());
		if (gprod.getLoteAsignado() == null || gprod.getLoteAsignado().compareTo("") == 0)
			gprod.setLoteAsignado((String) request.getAttribute("lote"));
		observaciones = (new GestionEnvasadoHelper().getObservacionesEnvasado(orden));
		request.getSession().setAttribute("observaciones", observaciones);
		
		String estado = gprod.getEstadoProceso();
		if (estado == null || estado.compareTo("") == 0)
			estado = (String) request.getAttribute("estado");
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
}