package es.induserco.opilion.presentacion.registroentrada;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class UpdaREAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private RegistroEntrada regEntradaFind = new RegistroEntrada();
	private RegistroEntrada regEntradaActualiza = new RegistroEntrada();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Actualizar Registro Entrada Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return regEntradaActualiza;
	}

	public String execute() throws Exception {
		try {
			System.out.println("codigo entrada a enviar al update "+request.getSession().getAttribute("codigoEntrada"));
			regEntradaFind.setCodigoEntrada((String)request.getSession().getAttribute("codigoEntrada"));
			Usuario us = (Usuario) request.getSession().getAttribute("usuario");
			regEntradaFind.setIdOperario(us.getLogin());
			//A�ade un cero de m�s en temperatura, humedad, cantidad, costoUnitario y costoTotal
			//Ley�ndolos nosotros de la request no se da este problema
			regEntradaActualiza.setCantidad(Double.valueOf(request.getParameter("cantidad")));
			regEntradaActualiza.setCostoUnitario(Double.valueOf(request.getParameter("costoUnitario")));
			regEntradaActualiza.setCostoTotal(Double.valueOf(request.getParameter("costoTotal")));
			regEntradaActualiza.setTemperatura(Double.valueOf(request.getParameter("temperatura")));
			regEntradaActualiza.setHumedad(Double.valueOf(request.getParameter("humedad")));
			List listaEstados = null, listaIncidencias = null;
			String est = request.getParameter("listaEstadoFabas");
			if (est != null){
				listaEstados = obtieneLista(est);
				System.out.println("Estados: " + listaEstados);
			}
			
			String inc = request.getParameter("listaIncidencias");
			if (inc != null){
				listaIncidencias = obtieneLista(inc);
				System.out.println("Incidencias: " + listaIncidencias);
			}
			new GestionRegistroEntradaHelper().updateRegistroEntrada(regEntradaFind, regEntradaActualiza, listaIncidencias, listaEstados);
			regEntradaActualiza = (RegistroEntrada)
				(new GestionRegistroEntradaHelper().getRegistroEntrada((String) request.getSession().getAttribute("codigoEntrada")));
			System.out.println("------------------------->DOS");
			//Carga los RE asociados a ese n�m de Orden
			Vector <RegistroEntrada>listareorden =
				new GestionRegistroEntradaHelper().getRegistrosEntradaOrden(regEntradaActualiza.getCodigoOrden());
			//Vector <RegistroEntrada>listareorden=new GestionRegistroEntradaHelper().getRegistrosEntradaOrden((String)request.getSession().getAttribute("codigoorden"));
			System.out.println("------------------------->TRES");
			request.getSession().setAttribute("listareorden", listareorden);
			request.getSession().setAttribute("codigoOrden", regEntradaActualiza.getCodigoOrden());
			//Actualizamos stocks
			/*new GestionProductosHelper().updateStockMateriasPrimas();
			new GestionProductosHelper().updateStockProductos();
			new GestionProductosHelper().updateStockEnvases();*/
			return SUCCESS;
		}
		catch (Exception e) {
			e.printStackTrace();
			return (ERROR);
		}
	}
	
	// Funci�n que, dado un String, lo convierte en List
	/**
	 * Obtiene lista.
	 *
	 * @param l the l
	 * @return the list
	 */
	private List obtieneLista (String l){
		List lista = new ArrayList();
		String elto;
		StringTokenizer tokenizer = new StringTokenizer(l, ",");
		while (tokenizer.hasMoreTokens()) {
			elto = tokenizer.nextToken();
			lista.add(elto);
		}
		return lista;
	}
}