package es.induserco.opilion.presentacion.registroentrada;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class InseRSEAction.
 */
public class InseRSEAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>, SessionAware
{
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The reg entrada find. */
	private RegistroEntrada regEntradaFind = new RegistroEntrada();
	
	/** The reg entrada actualiza. */
	private RegistroEntrada regEntradaActualiza = new RegistroEntrada();
	
	/** The lista incidencias. */
	private List listaIncidencias;
	
	/** The lista estado fabas. */
	private List listaEstadoFabas;
	
	/** The session. */
	private Map session;
	

	
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Actualizar Registro Entrada Action...");
		this.request=request;	
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {		
		return regEntradaActualiza;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		System.out.println("codigo entrada a enviar al update "+request.getSession().getAttribute("codigoEntrada"));
		regEntradaFind.setCodigoEntrada((String)request.getSession().getAttribute("codigoEntrada"));
		
		//checkboxlist de incidencias
		List listindic = new ArrayList();
		HashMap incidencias = (HashMap) session.get("listaIncidencias");
		Iterator iter = (Iterator) listaIncidencias.iterator();
		addListIncidencias(listindic, incidencias, iter);

		//checkbox estados
		List listestados = new ArrayList();
		HashMap estados = (HashMap) session.get("listaEstadoFabas");
		Iterator iterEstados = (Iterator) listaEstadoFabas.iterator();
		addListIncidencias(listestados, estados, iterEstados);
		
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		regEntradaActualiza.setIdOperario(us.getLogin());
		
		new GestionRegistroEntradaHelper().addRegistroSubEntrada(regEntradaFind,regEntradaActualiza,listindic,listestados); 		
		
		
		//new GestionRegistroEntradaHelper().updateRegistroEntrada(regEntradaFind, regEntradaActualiza);
		request.getSession().setAttribute("listaregistrosupd",(new GestionRegistroEntradaHelper()).getRegistroEntradas(regEntradaFind.getCodigoEntrada(),regEntradaFind.getFecha(),regEntradaFind.getIdProducto()));
		return SUCCESS;
	}

	/**
	 * Adds the list incidencias.
	 *
	 * @param listindic the listindic
	 * @param incidencias the incidencias
	 * @param iter the iter
	 */
	private void addListIncidencias(List listindic, HashMap incidencias,
			Iterator iter) {
		if (incidencias == null )
		{
			incidencias=new HashMap();
			session.put("incidencias",incidencias);
			
		}		

		//Añadimos las incidencias al listado...
		
		while ( iter.hasNext())
		{
			String inc = (String)iter.next();
			Integer numero;
			if ( (numero= (Integer) incidencias.get(inc)) != null )
			{	
				numero++;
				incidencias.put(inc, numero );
			}
			else
			{
				incidencias.put(inc, 1);
			}
			
			System.out.println("Añadiendo al carrito: "+inc);
			listindic.add(inc);
		}
	}	
	
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	//@Override
	public void setSession(Map session) {
		this.session = (Map) session;
	}

	/**
	 * Sets the lista incidencias.
	 *
	 * @param listaIncidencias the new lista incidencias
	 */
	public void setListaIncidencias(List listaIncidencias) {
		this.listaIncidencias = listaIncidencias;
	}

	/**
	 * Gets the lista incidencias.
	 *
	 * @return the lista incidencias
	 */
	public List getListaIncidencias() {
		return listaIncidencias;
	}	
	
	/**
	 * Sets the lista estado fabas.
	 *
	 * @param listaEstadoFabas the new lista estado fabas
	 */
	public void setListaEstadoFabas(List listaEstadoFabas) {
		this.listaEstadoFabas = listaEstadoFabas;
	}

	/**
	 * Gets the lista estado fabas.
	 *
	 * @return the lista estado fabas
	 */
	public List getListaEstadoFabas() {
		return listaEstadoFabas;
	}



}
