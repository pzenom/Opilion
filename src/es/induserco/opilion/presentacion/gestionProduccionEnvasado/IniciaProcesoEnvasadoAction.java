/*************************************************************
 *  VISUALIZACION DE UN PROCESO DE ENVASADO
 * @version 0.1 
 * @author Administrador - Induserco
**************************************************************/	
package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class IniciaProcesoEnvasadoAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = -9013888892313309236L;
	private HttpServletRequest request;
	private GestionProduccion gprod = new GestionProduccion();

	//@Override
	public void setServletRequest(HttpServletRequest request){
		this.request=request;
	}

	//@Override
	public Object getModel() {
		System.out.println("GetModel IniciaProcesoEnvasado");
		return gprod;
	}

	public String execute() throws Exception {
		System.out.println("Execute IniciaProcesoEnvasado");

		//1. Damos valor a orden
		Map <String, String[]> parametros = request.getParameterMap();
		String nombreParametro = "";
		String orden = "";
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			if(nombreParametro.compareTo("orden") == 0){
				orden = parametros.get(e.getKey())[0];
				break;
			}
		}
		request.getSession().setAttribute("orden", orden);
		GestionProduccion info = new GestionEnvasadoHelper().getInfoProcesosEnv(orden, "");
		boolean ean13 = true;
		if (info.getTipoEan() == 14)
			ean13 = false;
		String estado = "Iniciado";
		request.getSession().setAttribute("estado", estado);
		//2. Modificaciones en BD
		String operario = ((Usuario) request.getSession().getAttribute("usuario")).getLogin();
		boolean iniciado = new GestionEnvasadoHelper().iniciaProcesoEnvasado(orden, operario);
		if (iniciado)
			if (!ean13)
				return "EAN14";
			else
				return "EAN13";
		else
			return ERROR;
	}
}