package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;
import es.induserco.opilion.presentacion.GestionRegistroEnvasesHelper;
import es.induserco.opilion.presentacion.GestionRegistroIngredientesHelper;

public class InseREMPAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>, SessionAware
{
	/**
	 * Recoger
	 * 1. idProducto codigo Producto Presentacion
	 * 2. lista de RE de MP seleccionados
	 * 3. cantidad a envasar
	 * */
	private HttpServletRequest request;
	private Map session;
	private List listaMateriaPrima;
	private GestionProduccion gpro = new GestionProduccion();
	private GestionProduccion gprod = new GestionProduccion();

	public void setListaMateriaPrima(List listaMateriaPrima) {
		this.listaMateriaPrima = listaMateriaPrima;
	}

	public List getListaMateriaPrima() {
		return listaMateriaPrima;
	}

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Insertar RE MP Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		return gprod;
	}

	public String execute() throws Exception {
		List listamateriaprima = new ArrayList();
		HashMap materiasPrimas = (HashMap) session.get("listaMateriaPrima");
		
		addListaMateriasPrimas(listamateriaprima, materiasPrimas);

		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		gprod.setIdOperario(us.getLogin());	
		
		//Recupera el codigo del producto presentacion seleccionado
		gprod.setIdProducto((Long)request.getSession().getAttribute("idproductopresentacion"));	   
		System.out.println("Id PP es : "+gprod.getIdProducto());

		//Recupera la cantidad A ENVASAR
		String qty=request.getParameter("qty");
		request.getSession().setAttribute("qtytotal",qty);	 
		System.out.println("Cantidad a envasar es "+qty);
		
	    //Agrega el Registro temporal del Envasado
	    new GestionEnvasadoHelper().addRegistroEnvasado(gprod);

	    System.out.println("Agrega las MP a la tabla temporal ");
	    new GestionRegistroIngredientesHelper().addComponentesEnvasado(listamateriaprima,gprod.getOrden(),"M");	    
	    System.out.println("Recupera MP asociada a esa orden");
	    request.getSession().setAttribute("listadomp", (new GestionRegistroEnvasesHelper().getTmpRegistroIngredientesEnvases(gprod.getOrden())));	    
	    //request.getSession().setAttribute("listaMateriaPrima",listamateriaprima);
	    //Sirve para mostrar los RE de EN relacionados con ese PP
	    
	    System.out.println("muestra RE de envases relacionados con ese Id de PP ");
	    request.getSession().setAttribute("listaregistroenvases",(new GestionRegistroEnvasesHelper()).getRegistroEnvases(gprod.getIdProducto(), ""));
	    //request.getSession().setAttribute("envases",(new GestionRegistroEnvasesHelper().addTmpRegistroIngredientesEnvases(gprod,gpro)));
	    System.out.println("Orden de RE es : "+gprod.getOrden());
	    request.getSession().setAttribute("orden",gprod.getOrden());
	    return SUCCESS;
	}

	/**
	 * Funci�n que agrega un checklist a la lista desplegada.
	 *
	 * @param listamateriaprima the listamateriaprima
	 * @param materiasPrimas the materias primas
	 */
	private void addListaMateriasPrimas(List listamateriaprima,
			HashMap materiasPrimas) {
		if (materiasPrimas == null ){
			materiasPrimas=new HashMap();
			session.put("Envases",materiasPrimas);
		}		

		//A�adimos las incidencias al listado...
		Iterator iter = (Iterator) listaMateriaPrima.iterator();
		while ( iter.hasNext()){
			String ing = (String)iter.next();
			Integer numero;
			if ( (numero= (Integer) materiasPrimas.get(ing)) != null ){	
				numero++;
				materiasPrimas.put(ing, numero );
			}
			else{
				materiasPrimas.put(ing, 1);
			}
			System.out.println("A�adiendo a la lista: "+ing);
			listamateriaprima.add(ing);
		}
	}

	//@Override
	public void setSession(Map session) {
		this.session = (Map) session;
	}
}