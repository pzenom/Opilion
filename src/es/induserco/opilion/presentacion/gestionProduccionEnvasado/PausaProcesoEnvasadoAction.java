/*************************************************************
 *  VISUALIZACION DE UN PROCESO DE ENVASADO
 * @version 0.1 
 * @author Administrador - Induserco
**************************************************************/	
package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class PausaProcesoEnvasadoAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = -9013888892313309236L;
	private HttpServletRequest request;
	private RegistroEnvasado envasado = new RegistroEnvasado();

	//@Override
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}

	//@Override
	public Object getModel() {
		System.out.println("GetModel IniciaProcesoEnvasado");
		return envasado;
	}

	public String execute() throws Exception {
		System.out.println("Execute IniciaProcesoEnvasado");
		//1. Damos valor a orden
		Map <String, String[]> parametros = request.getParameterMap();
		String nombreParametro = "";
		String orden = "", observaciones = "";
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			if(nombreParametro.compareTo("orden") == 0)
				orden = parametros.get(e.getKey())[0];
		}
		request.getSession().setAttribute("orden", orden);
		request.getSession().setAttribute("estado", "Pausado");
		String operario = ((Usuario) request.getSession().getAttribute("usuario")).getLogin();
		boolean iniciado = new GestionEnvasadoHelper().pausaProcesoEnvasado(orden, operario, envasado.getElaborado(),
				request.getParameter("elaboradoAgrupar"), envasado.getObservaciones());
		GestionProduccion info = new GestionEnvasadoHelper().getInfoProcesosEnv(orden, "");
		request.getSession().setAttribute("envasado", info.getCantidadElaborada());
		request.getSession().setAttribute("agrupacionesEnvasadas", info.getCantidadElaboradaAgrupacion());
		boolean ean13 = true;
		if (info.getTipoEan() == 14)
			ean13 = false;
		if (iniciado)
			if (!ean13)
				return "EAN14";
			else
				return "EAN13";
		else
			return ERROR;
	}
}