package es.induserco.opilion.presentacion.gestionProduccionFumigado;

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
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;

public class InseGPFumiAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>, SessionAware {
	private HttpServletRequest request;
	private Map session;
	private List listProcSele;
	private GestionProduccion gprodf = new GestionProduccion();
	private GestionProduccion gprodup = new GestionProduccion();
	String proceso="Fumigado";
	private String url;

	public void setListProcSele(List listProcSele) {this.listProcSele = listProcSele;}

	public List getListProcSele() {return listProcSele;}

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Ingresar Registro Envasado Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return gprodup;
	}

	public String execute() throws Exception {
		
		HashMap mapaCantRE=new HashMap();
		List listRECong = new ArrayList();
		HashMap ProcSele = (HashMap) session.get("listProcSele");
		String[] cant = request.getParameterValues("cantidad");
		
		//1) agrega las mermas para cada RE del proceso
		addMermasREProceso(mapaCantRE, ProcSele, cant);
		
		//2) actualiza las cantidades de Merma de los RE
		gprodf.setOrden((String)request.getSession().getAttribute("orden"));
		System.out.println("codigo para update orden es "+gprodf.getOrden());
		new GestionDesgranadoHelper().updateREMermProceso(mapaCantRE,gprodf.getOrden(),"Fumigado");
		
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		gprodup.setIdOperario(us.getLogin());
		
		//sirve para actualizar los datos de confirmaci�n como hora final de proceso
		new GestionDesgranadoHelper().updateRegistroProceso(gprodf, gprodup,proceso);

		//Deber�a limpiar todas las variable de sesi�n usadas en el proceso
		//request.getSession().setAttribute("codigoEntrada",(new GestionDesgranadoHelper().getCodigoEntradaOrden(gprodf.getOrden(),"Cribado")));		
		//System.out.println("codigoEntrada del cribado para envasado es..."+new GestionDesgranadoHelper().getCodigoEntradaOrden(gprodf.getOrden(),"Cribado"));
		
		//new GestionCribadoHelper().addRegistroCribado(gprod); 
		
		setUrl("UbicarProceso.action?reubicar=true&tipoProceso=" + proceso + "&proceso=" + gprodf.getOrden());
		request.getSession().setAttribute("tipoProceso", proceso);
		request.getSession().setAttribute("proceso", gprodf.getOrden());
		request.getSession().setAttribute("reubicar", "true");
		request.setAttribute("tipoProceso", proceso);
		request.setAttribute("proceso", gprodf.getOrden());
		
		return "redirect";
	}

	private void addMermasREProceso(HashMap mapaCantRE, HashMap ProcSele,
			String[] cant) {
		if (ProcSele == null ){
			ProcSele=new HashMap();
			session.put("ProcSele",ProcSele);
		}		
		for (int i=0;i<cant.length;i++){
			Iterator iter = (Iterator) listProcSele.iterator();
			while ( iter.hasNext()){
				String ing = (String)iter.next();
				ing = ing.substring(0, ing.length()-1);
				System.out.println("A�adiendo a la lista: "+ing);
				System.out.println("cantidad a procesar "+cant[i]);
				mapaCantRE.put(ing, cant[i]);
				i++;
			}
		}
		for( Iterator it = mapaCantRE.keySet().iterator(); it.hasNext();) { 
            String s = (String)it.next();
            String s1 = (String)mapaCantRE.get(s);
            System.out.println("Map creado "+ s + " : " + s1);
		}
	}

	//@Override
	public void setSession(Map session) {
		this.session = (Map) session;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}