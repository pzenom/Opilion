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

import es.induserco.opilion.data.entidades.GestionProduccion;

public class BloqueaProcesoEnvasadoAction extends ActionSupport 
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
			if(nombreParametro.compareTo("orden") == 0)
			{
				orden = parametros.get(e.getKey())[0];
				break;
			}
		}
		request.getSession().setAttribute("orden", orden);
		
		//2. Modificaciones en BD
		return SUCCESS;
	}
}