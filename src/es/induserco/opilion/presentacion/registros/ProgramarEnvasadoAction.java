package es.induserco.opilion.presentacion.registros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.data.entidades.MateriaPrima;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroEnvasesHelper;
import es.induserco.opilion.presentacion.GestionRegistroIngredientesHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

/**
 * @author Induserco, andres (28/04/2011)
 * @version 0.2
 */
public class ProgramarEnvasadoAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven {
	
	private HttpServletRequest request;
	private Albaran albaran = new Albaran();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("ProgramarEnvasadoAction Action...");
		this.request=request;	
	} 

	//@Override
	public Object getModel() {		
		return albaran;
	}
	
	/**
	 * @author andres (28/04/2011)
	 * @since 0.2
	 */
 	public String execute() throws IOException {
		System.out.println("Execute ProgramarEnvasadoAction");	
 		try{
 			String codigo = request.getParameter("codigoPedido");
 			List<Producto> productosAux = new ArrayList<Producto>();
 			String nombreParametro = "";
 			//Vectores que contienen los envases y las materias que se necesitan
 			Vector<Envase> envasesFaltan = new Vector<Envase>();
 			Vector<MateriaPrima> materiasPrimasFaltan = new Vector<MateriaPrima>();
 			Map <String, String[]> parametros = request.getParameterMap();
 			// paso por todos los parametros
 			Iterator iterator = parametros.entrySet().iterator();
 			while(iterator.hasNext()){
 				Map.Entry e = (Map.Entry) iterator.next();
 				nombreParametro=(String) e.getKey();
 				if(nombreParametro.indexOf("ean_") > -1) {
 					//Obtenemos el codigo ean del producto que hay que envasar
 					String ean = ((String[])parametros.get(e.getKey()))[0];
 					//A partir del ean, obtenemos la cantidad a envasar, que la leemos del parametro envasar_'ean'
 					Iterator itera = parametros.entrySet().iterator();
 					double cantidad = 0;
 		 			while(itera.hasNext()) {
 		 				Map.Entry entry = (Map.Entry) itera.next();
 		 				nombreParametro = (String) entry.getKey();
 		 				if(nombreParametro.compareTo("envasar_" + ean) == 0) {
 		 					//Cantidad a envasar
 		 					cantidad = Double.parseDouble(((String[])parametros.get(entry.getKey()))[0]);
 		 					break;
 		 				}
 		 			}
 		 			//Tenemos el ean y la cantidad
 		 			Producto a = new Producto();
 		 			a.setCodigoEan(ean);
 		 			a.setCantidad(cantidad);
 		 			productosAux.add(a);
 		 			//Lista auxiliar que nos creamos, para saber en todo momento cuanta cantidad hay que envasar de cada producto
 		 			Producto p = new Producto();
 		 			p.setCodigoEan(ean);
 		 			p = (Producto) new GestionProductosHelper().getProductos(p, false).get(0);
 		 			//Obtenemos las materias primas y los envases asociados a este ean
 		 			Vector<LineaProducto> envases =
 		 				(Vector<LineaProducto>)(new GestionRegistroEnvasesHelper()).
 		 				getEnvasesProducto(p.getIdProducto(), "");
 		 			int contador = 0;
 		 			long idAnterior = 0;
 		 			contador = envases.size();
 		 			//Para poder mostrar los lotes de envases agrupados para cada envase
 		 			for (int i = 1; i < contador; i++){
 		 				LineaProducto envase = (LineaProducto) envases.elementAt(i - 1);
 		 				idAnterior = envase.getIdLinea();
 		 				envases.elementAt(i).setIdAnterior(idAnterior);
 		 			}
 		 			request.getSession().setAttribute("listaregistroenvases", envases);
 		 			
 		 			//Cargamos las materias primas asociadas al producto
 		 			Vector<LineaProducto> materias =
 		 				(Vector<LineaProducto>)(new GestionRegistroIngredientesHelper()).
 		 				getIngredientesProducto(p.getIdProducto(), "");
 		 			contador = 0;
 		 			idAnterior = 0;
 		 			contador = materias.size();
 		 			for (int i = 1; i < contador; i++){
 		 				LineaProducto materia = (LineaProducto) materias.elementAt(i - 1);
 		 				idAnterior = materia.getIdLinea();
 		 				materias.elementAt(i).setIdAnterior(idAnterior);
 		 			}
 		 			request.getSession().setAttribute("listaregistroingredientes", materias);
 		 			//return (SUCCESS);
 				}
 			}
 			
 			request.getSession().setAttribute("listaEanCantidad", productosAux);
 			//Aquí tenemos ya TODOS los envases y las materias primas que faltan
 			//Si falta alguna materia prima y/o envase, salimos del action a una pagina que muestra lo que falta
 			//Si no, podemos programar el proceso de envasado
 			if (envasesFaltan.size() > 0 || materiasPrimasFaltan.size() > 0){
 				/*List<MateriaPrima> m = new ArrayList<MateriaPrima>(materiasPrimasFaltan.size());
 				List<Envase> e = new ArrayList<Envase>(envasesFaltan.size());*/
 				List<MateriaPrima> m = new ArrayList<MateriaPrima>(materiasPrimasFaltan);
 				List<Envase> e = new ArrayList<Envase>(envasesFaltan);
 				/*Collections.copy(m, materiasPrimasFaltan);
 				Collections.copy(e, envasesFaltan);*/
 				request.getSession().setAttribute("listamaterias", m);
				request.getSession().setAttribute("listaenvases", e);
				//Ahora hay que actualizar el estado del pedido a N (Rechazado)
				//new GestionRegistroSalidaHelper().actualizaEstadoPedido(codigo, 'N');
 				return "faltaStock";
 			}
			else{
				//Ahora hay que actualizar el estado del pedido a E (Envasando)
				new GestionRegistroSalidaHelper().actualizaEstadoPedido(codigo, 'E');
				return SUCCESS;
			}
 		} catch (Exception e){ e.printStackTrace(); }
		return ERROR;
	}	
}