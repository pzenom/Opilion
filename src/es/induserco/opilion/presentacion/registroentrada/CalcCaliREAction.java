package es.induserco.opilion.presentacion.registroentrada;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.RegistroCalidad;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class CalcCaliREAction.
 */
public class CalcCaliREAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The calidad. */
	private RegistroCalidad calidad = new RegistroCalidad();
	
	/** The entry. */
	private RegistroEntrada entry = new RegistroEntrada();
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {				              
		System.out.println("Agregar calidad:");	
		//return entry;
		return calidad;
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
		calidad.setCodigoEntrada((String)request.getSession().getAttribute("codigoEntrada"));
		//insertamos el nuevo calculo
		new GestionRegistroEntradaHelper().addAnalisisCalidadRegistro(calidad);
		
		//obtenemos el histórico de calculos en base al código de entrada
		//Vector <RegistroCalidad> historico = new GestionRegistroEntradaHelper().getRegistrosCalidad(calidad.getCodigoEntrada());
		Vector <RegistroCalidad> historico = new GestionRegistroEntradaHelper().getRegistrosCalidad((String)request.getSession().getAttribute("codigoEntrada"));
		request.getSession().setAttribute("historico",historico);	
		

		return SUCCESS;
	}
	
}