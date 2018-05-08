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

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionRegistroEnvasesHelper;

public class InseCantREMPAction extends ActionSupport implements
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
		System.out.println("Ingresar Registro Envases Action...");
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

		for (int i=0;i<cant.length;i++){
			Iterator iter = (Iterator) listaMateriaPrima.iterator();
			while ( iter.hasNext()){
				String ing = (String)iter.next();
				ing = ing.substring(0, ing.length()-1);
				System.out.println("A�adiendo a la lista: "+ing);
				System.out.println("cantidad a envasar "+cant[i]);
				mapaCantEnvases.put(ing, cant[i]);
				i++;
			}
		}
		for( Iterator it = mapaCantEnvases.keySet().iterator(); it.hasNext();) { 
            String s = (String)it.next();
            String s1 = (String)mapaCantEnvases.get(s);
            System.out.println("Map creado "+ s + " : " + s1);
		}
		
		//Actualiza los datos de los RE de envases
		gprod.setOrden((String)request.getSession().getAttribute("orden"));
		request.getSession().setAttribute("envases",(new GestionRegistroEnvasesHelper().updateTmpRegistroIngredientesEnvases(mapaCantEnvases,gprod.getOrden())));
		
		return SUCCESS;
	}

	//@Override
	public void setSession(Map session) {
		this.session = (Map) session;
	}
}