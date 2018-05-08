package es.induserco.opilion.presentacion.registroentrada;

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
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class InseREAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>, SessionAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private List listaIncidencias;
	private List listaEstadoFabas;
	private List listaCategoriasE;
	private List listaCategoriasS;
	private Map session;
	private RegistroEntrada entry = new RegistroEntrada();
	
	public String execute() throws Exception {
		try{
			//Incidencias
			List listindic = new ArrayList();
			//HashMap incidencias = (HashMap) session.get("listaIncidencias");
			HashMap incidencias = new HashMap();// = (HashMap) session.get("listaIncidencias");
			if (listaIncidencias != null){
				Iterator iter = (Iterator) listaIncidencias.iterator();
				addListIncidencias(listindic, incidencias, iter);
			}
			//Estados
			List listestados = new ArrayList();
			HashMap estados = new HashMap();// (HashMap) session.get("listaEstadoFabas");
			if (listaEstadoFabas != null){
				Iterator iterEstados = (Iterator) listaEstadoFabas.iterator();
				addListIncidencias(listestados, estados, iterEstados);
			}
			entry.setCostoUnitario(Double.parseDouble(request.getParameter("costoUnitario")));
			entry.setCostoTotal(Double.parseDouble(request.getParameter("costoTotal")));
			entry.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			Usuario us = (Usuario) request.getSession().getAttribute("usuario");
			entry.setIdOperario(us.getLogin());
			entry.setCodigoOrden((String)request.getSession().getAttribute("codigoorden"));
			System.out.println("CODIGO A PASAR ES " + entry.getCodigoOrden());
			boolean listoDistribuir = Boolean.parseBoolean(request.getParameter("listoDistribuir"));
			entry.setListoDistribuir(listoDistribuir);
			new GestionRegistroEntradaHelper().addRegistroEntradaTmp(entry, listindic, listestados);
			//Recargo la variable que contiene el listado de RE asociados a esa orden
			//System.out.println("recargo la var de sesion :"+entry.getCodigoOrden());
			request.getSession().setAttribute("listaregistros",
					(new GestionRegistroEntradaHelper()).getRegistrosEntradaOrden(entry.getCodigoOrden()));
			//request.getSession().setAttribute("codigoEntrada",entry.getCodigoEntrada());
			if (entry.getIdTipoUbicacion() == 1){
				//System.out.println("ubicacion es "+entry.getIdTipoUbicacion());
				//envia registro para la parte del registro de ubicación
				//System.out.println("cod entrada es "+entry.getCodigoEntrada());
				request.getSession().setAttribute("listaregistrosubicacion",
						(new GestionRegistroEntradaHelper()).getRegistroEntradas(entry.getCodigoEntrada(),
								entry.getFecha(),entry.getIdProducto()));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private void addListIncidencias(List listindic, HashMap incidencias, Iterator iter) {
		if (incidencias == null){
			incidencias = new HashMap();
			session.put("incidencias", incidencias);
		}
		String inc = "";
		//Añadimos las incidencias al listado...
		while (iter.hasNext()){
			inc = (String)iter.next();
		}
		String[] frag = inc.split(",");
		for (int i = 0; i < frag.length; i++){
			incidencias.put(frag[i], i);
			listindic.add(frag[i]);
		}
	}

	//@Override
	public void setSession(Map session){ this.session = (Map) session; }
	public void setListaEstadoFabas(List listaEstadoFabas){ this.listaEstadoFabas = listaEstadoFabas; }
	public List getListaEstadoFabas(){ return listaEstadoFabas; }
	public void setListaIncidencias(List listaIncidencias){ this.listaIncidencias = listaIncidencias; }
	public List getListaIncidencias(){ return listaIncidencias; }
	public void setListaCategoriasE(List listaCategoriasE){ this.listaCategoriasE = listaCategoriasE; }
	public List getListaCategoriasE(){ return listaCategoriasE; }
	public void setListaCategoriasS(List listaCategoriasS){ this.listaCategoriasS = listaCategoriasS; }
	public List getListaCategoriasS(){ return listaCategoriasS; }

	public void setServletRequest(HttpServletRequest request) { this.request = request;	}
	public Object getModel() { return entry; }
}