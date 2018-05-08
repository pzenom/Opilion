package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroEnvasesHelper;
import es.induserco.opilion.presentacion.GestionRegistroIngredientesHelper;

public class ListREMPAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private GestionProduccion gprod = new GestionProduccion();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}

	//@Override
	public Object getModel() {
		gprod.setIdProducto(Long.parseLong(request.getParameter("idProd")));
		return gprod;
	}

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		System.out.println("ListREMPAction");
		Boolean agrupar = Boolean.parseBoolean(request.getParameter("agrupar"));
		
		String tipoEan = request.getParameter("tipoEan");
		System.out.println("Tipo de EAN seleccionado: " + tipoEan);
		request.getSession().setAttribute("tipoEan", tipoEan);
		request.getSession().setAttribute("idProductoSelect", gprod.getIdProducto() + "_" + tipoEan);
		request.getSession().setAttribute("vacia", new Vector<Producto>());
		//Cargamos los envases asociados al producto
		//System.out.println("A leer envases asociados al producto");
		Vector<LineaProducto> envases =
			(Vector<LineaProducto>)(new GestionRegistroEnvasesHelper()).getEnvasesProducto(gprod.getIdProducto(), " AND re.saldo > 0 ");
		int contador = 0;
		long idAnterior = 0, idAnteriorAgrupar = 0;
		contador = envases.size();
		//Para poder mostrar los lotes de envases agrupados para cada envase
		for (int i = 1; i < contador; i++){
			LineaProducto envase = (LineaProducto) envases.elementAt(i - 1);
			idAnterior = envase.getIdLinea();
			idAnteriorAgrupar = envase.getIdLinea();
			envases.elementAt(i).setIdAnterior(idAnterior);
		}
		Vector<LineaProducto> envasesPosibleAgrupar = new Vector<LineaProducto>(),
			agrupacionesPosibles = new Vector<LineaProducto>();
		if (agrupar){
			//System.out.println("Los items se van a ir agrupando");
			//Cargamos las posibles agrupaciones que se pueden formar a partir del EAN13 generado
			agrupacionesPosibles =
				(Vector<LineaProducto>)(new GestionProductosHelper()).getAgrupacionesEAN13(gprod.getIdProducto());
			//Cargamos los envases asociados al producto
			envasesPosibleAgrupar =
				(Vector<LineaProducto>)(new GestionProductosHelper()).getEnvasesAgruparProducto(gprod.getIdProducto(), " AND re.saldo > 0 ");
			contador = 0;
			idAnterior = 0;
			idAnteriorAgrupar = 0;
			contador = envasesPosibleAgrupar.size();
			//Para poder mostrar los lotes de envases agrupados para cada envase
			for (int i = 1; i < contador; i++){
				LineaProducto envase = (LineaProducto) envasesPosibleAgrupar.elementAt(i - 1);
				idAnterior = envase.getIdLinea();
				idAnteriorAgrupar = envase.getIdProducto();
				envasesPosibleAgrupar.elementAt(i).setIdAnterior(idAnterior);
				envasesPosibleAgrupar.elementAt(i).setIdAnteriorAgrupar(idAnteriorAgrupar);
			}
			//request.getSession().setAttribute("listaEnvasesPosibleAgrupar", envasesPosibleAgrupar);
			envases.addAll(envasesPosibleAgrupar);
			request.getSession().setAttribute("agrupacionesPosibles", agrupacionesPosibles);
		}
		request.getSession().setAttribute("stockEnvaseAgrupar", envasesPosibleAgrupar.size());
		//System.out.println("A leer envases");
		request.getSession().setAttribute("listaregistroenvases", envases);
		if (tipoEan.compareTo("13") == 0){
			//System.out.println("A leer ingredientes");
			//Cargamos las materias primas asociadas al producto
			Vector<LineaProducto> materias =
				(Vector<LineaProducto>)(new GestionRegistroIngredientesHelper()).getIngredientesProducto(gprod.getIdProducto(), "");
			contador = 0;
			idAnterior = 0;
			contador = materias.size();
			for (int i = 1; i < contador; i++){
				LineaProducto materia = (LineaProducto) materias.elementAt(i - 1);
				idAnterior = materia.getIdLinea();
				materias.elementAt(i).setIdAnterior(idAnterior);
			}
			request.getSession().setAttribute("listaregistroingredientes", materias);
			//System.out.println("A leer eans13");
			Vector<LineaProducto> eans13 = (new GestionProductosHelper().getEANs13Producto((long)gprod.getIdProducto()));
			contador = 0;
			idAnterior = 0;
			contador = eans13.size();
			for (int i = 1; i < contador; i++){
				LineaProducto materia = (LineaProducto) eans13.elementAt(i - 1);
				idAnterior = materia.getIdLinea();
				eans13.elementAt(i).setIdAnterior(idAnterior);
			}
			request.getSession().setAttribute("listaeans13", eans13);
			return "EAN13";
		}else{
			if (tipoEan.compareTo("14") == 0){
				//System.out.println("A leer composición");
				//System.out.println("composicion leida");
				Vector<LineaProducto> composicion =
					(new GestionProductosHelper().getProductosComponen(gprod.getIdProducto(), true));
				contador = 0;
				idAnterior = 0;
				contador = composicion.size();
				//System.out.println("Seguimos con la composición");
				for (int i = 1; i < contador; i++){
					LineaProducto materia = (LineaProducto) composicion.elementAt(i - 1);
					idAnterior = materia.getIdLinea();
					composicion.elementAt(i).setIdAnterior(idAnterior);
				}
				//System.out.println("Acabamos la composición");
				request.getSession().setAttribute("listaItemsComponen", composicion);
				return "EAN14";
			}
		}
		return ERROR;
	}
}