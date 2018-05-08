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
import es.induserco.opilion.presentacion.GestionRegistroEnvasesHelper;

public class EditCantREMPAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>, SessionAware{
	
	private HttpServletRequest request;
	private Map session;
	private List listaMateriaPrima;
	private GestionProduccion gpro = new GestionProduccion();
	private GestionProduccion gprod = new GestionProduccion();

	public void setlistaMateriaPrima(List listaMateriaPrima) {
		this.listaMateriaPrima = listaMateriaPrima;
	}

	public List getlistaMateriaPrima() {
		return listaMateriaPrima;
	}

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Ingresar Registro MP Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		return gprod;
	}

	public String execute() throws Exception {
		
		HashMap mapaCantEnvases=new HashMap();
		List listenvases = new ArrayList();
		HashMap Envases = (HashMap) session.get("listaMateriaPrima");
		String[] cant = request.getParameterValues("cantidad");
		if (Envases == null ){
			Envases=new HashMap();
			session.put("Envases",Envases);
			
		}		
		Iterator iter = (Iterator) listaMateriaPrima.iterator();
		while ( iter.hasNext()){
			String ing = (String)iter.next();
			ing = ing.substring(0, ing.length()-1);
			System.out.println("A�adiendo al carrito: "+ing);
			mapaCantEnvases.put(ing, "0");
		}
		for( Iterator it = mapaCantEnvases.keySet().iterator(); it.hasNext();) { 
            String s = (String)it.next();
            String s1 = (String)mapaCantEnvases.get(s);
            System.out.println("Map creado "+ s + " : " + s1);
		}
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		System.out.println("paso por registrar cantidades");
		//muestro las MP y los ENV seleccionados
		gprod.setOrden((String)request.getSession().getAttribute("orden"));
	    request.getSession().setAttribute("selenvases",(new GestionRegistroEnvasesHelper().getRegistroEnvases(listenvases)));
	    request.getSession().setAttribute("envases",(new GestionRegistroEnvasesHelper().addTmpRegistroIngredientesEnvases(mapaCantEnvases,gprod.getOrden())));
	    request.getSession().setAttribute("datosenv", (new GestionRegistroEnvasesHelper().getTmpRegistroIngredientesEnvases(gprod.getOrden())));
	    return SUCCESS;
	}

	//@Override
	public void setSession(Map session) {
		this.session = (Map) session;
	}
}