package es.induserco.opilion.presentacion.envase;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionEnvasesHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistroEnvaseAction.
 */
public class RegistroEnvaseAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The entry. */
	private Envase entry = new Envase();
	
	//private InputStream inputStream;
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {	
		return entry;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception 
	{	
		//Map responseMap = new HashMap();
		//responseMap.put("success", true);
		
		//Colocamos las listas de datos en la request.
		request.getSession().setAttribute("fecharegistro",(new GestionEntidadesHelper()).getFechaRegistro());
		request.getSession().setAttribute("listamateriales",(new GestionEnvasesHelper()).getMateriales());
		//JSONObject jsonResponse= JSONObject.fromObject(responseMap);
		//inputStream= new ByteArrayInputStream(jsonResponse.toString().getBytes());

		return SUCCESS;
	}

	/**
	 * @return the inputStream
	 */
	//public InputStream getInputStream() throws Exception {
	//	return inputStream;
	//}	
	
}