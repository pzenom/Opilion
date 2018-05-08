package es.induserco.opilion.presentacion.mantenimiento;

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
import es.induserco.opilion.data.entidades.Maquina;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class MantenimientoAjaxAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>, SessionAware
{
	private HttpServletRequest request;
	private Maquina entry = new Maquina();
	/*private List listaTM;*/
	private Map session;

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("execute...Inse Maquina Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		return entry;
	}

	public String execute() throws Exception {
        /*Procesos de mantenimiento*/
		/*List listmt = new ArrayList();
		HashMap procesos = (HashMap) session.get("listaTM");
		Iterator iter = (Iterator) listaTM.iterator();
		List listMant = new ArrayList();
		listMant = addListProcesos(listmt, procesos, iter);		
		entry.setListMant(listMant);*/
		
		//String fechaConsulta = (String)request.getParameter("fechaConsulta");
		//entry.setFechaCadena(fechaConsulta);
		//new GestionRegistroEntradaHelper().addMQ(entry);
		Maquina maquina = (Maquina)new GestionRegistroEntradaHelper().getMaquinas(0, entry.getIdMaquina(), "").get(0);
		request.getSession().setAttribute("maquina", maquina);
		return SUCCESS;
	}

	/**
	 * Adds the list procesos.
	 *
	 * @param listindic the listindic
	 * @param procesos the procesos
	 * @param iter the iter
	 * @return the list
	 */
	/*private List addListProcesos(List listindic, HashMap procesos, Iterator iter) {
		if (procesos == null )
		{
			procesos=new HashMap();
			session.put("procesos",procesos);
		}
		//Añadimos las incidencias al listado...
		while ( iter.hasNext())
		{
			String inc = (String)iter.next();
			Integer numero;
			if ( (numero= (Integer) procesos.get(inc)) != null )
			{	
				numero++;
				procesos.put(inc, numero );
			}
			else
			{
				procesos.put(inc, 1);
			}
			
			System.out.println("Añadiendo procesos: "+inc);
			listindic.add(inc);
		}
		return listindic;
	}*/

	public void setSession(Map session) {
		this.session = (Map) session;
	}	
	/*
	public void setListaTM(List listaTM) {
		this.listaTM = listaTM;
	}

	public List getListaTM() {
		return listaTM;
	}*/
}