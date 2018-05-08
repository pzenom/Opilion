package es.induserco.opilion.presentacion.gestionProduccionFumigado;

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
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;
import es.induserco.opilion.presentacion.GestionRegistroIngredientesHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class InseREFumiAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>, SessionAware{
	
	/**
	 * Recoger
	 * 1. idProducto codigo Producto Presentacion
	 * 2. lista de RE de FUMIGADOS seleccionados
	 * */
	private HttpServletRequest request;
	private Map session;
	private List listProcSele;
	private GestionProduccion gpro = new GestionProduccion();
	private GestionProduccion gprod = new GestionProduccion();
	
	public List getListProcSele() {	return listProcSele;}
	public void setListProcSele(List listProcSele) {this.listProcSele = listProcSele;}	
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Insertar RE que entran al proceso de fumigado...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		return gprod;
	}

	public String execute() throws Exception {
		List listaprocsele = new ArrayList();
		HashMap mapProcSele = (HashMap) session.get("listProcSele");
		addListaProcSele(listaprocsele, mapProcSele);
		//Asigna el idOperario
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		gprod.setIdOperario(us.getLogin());	
		System.out.println("asigna el login del operario ");
	    //Crea un nuevo Registro de Proceso ok
	    new GestionDesgranadoHelper().addRegistroProceso(gprod,"Fumigado");
	    //Agrega los RE seleccionados a la tabla de detalle del Proceso
	    new GestionRegistroIngredientesHelper().addComponentesProceso(listaprocsele,gprod.getOrden(),"Fumigado");	    
	    System.out.println("Agrega las mp a la tabla temporal ");	 
	    request.getSession().setAttribute("listadofumi",
	    		(new GestionDesgranadoHelper().getREProceso(gprod.getOrden(),"Fumigado")));	    
	    System.out.println("Recupera el listado de congelados ");	 	    
	    //request.getSession().setAttribute("listCongSele",listacongsele);
	    //Sirve para mostrar los RE de EN relacionados con ese PP
	    System.out.println("Orden de RE es : "+gprod.getOrden());
	    request.getSession().setAttribute("orden",gprod.getOrden());
	    /** SACAR UBICACIONES **/
		Vector<LineaProducto> materias =
			(new GestionDesgranadoHelper().getInfoMateriasProceso(gprod.getOrden(), "Fumigado"));
		for (int i = 0; i < materias.size(); i++){
			String entrada = materias.get(i).getRegistroEntrada();
			ArrayList<Ubicacion> ubicaciones =
				new GestionUbicacionesHelper().getUbicacionesEntrada(entrada, true);
			materias.get(0).setUbicaciones(ubicaciones);
		}
		request.getSession().setAttribute("ingredientes", materias);
	    return SUCCESS;
	}

	/**
	 * Función que agrega un checklist a la lista desplegada.
	 *
	 * @param listaprocsele the listaprocsele
	 * @param mapProcSele the map proc sele
	 */
	private void addListaProcSele(List listaprocsele,HashMap mapProcSele) {
		if (mapProcSele == null ){
			mapProcSele=new HashMap();
			session.put("Envases",mapProcSele);
		}		
		//Agrega RE escogidos al listado...
		Iterator iter = (Iterator) listProcSele.iterator();
		while ( iter.hasNext()){
			String ing = (String)iter.next();
			Integer numero;
			if ( (numero= (Integer) mapProcSele.get(ing)) != null ){	
				numero++;
				mapProcSele.put(ing, numero );
			}
			else{
				mapProcSele.put(ing, 1);
			}
			
			System.out.println("Agregando a la lista del proceso: "+ing);
			listaprocsele.add(ing);
		}
	}

	//@Override
	public void setSession(Map session) {
		this.session = (Map) session;
	}
}