package es.induserco.opilion.presentacion.registros;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperCompileManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsCicloJR.
 */
public class ConsCicloJR extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	/** The request. */
	private HttpServletRequest request;
	
	/** The salida. */
	private RegistroSalida salida= new RegistroSalida();
	
	/** The re. */
	private Vector re = new Vector();
	
	/**
	 * Gets the re.
	 *
	 * @return the re
	 */
	public Vector getRe() {
		return re;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta Registro Entrada!");
		// TODO Auto-generated method stub
		return salida;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		//Colocamos la lista de entradades en la request.
		request.getSession().setAttribute("orden",(salida.getCodigoSalida()));
		request.getSession().setAttribute("listaentradas",(new GestionRegistroEntradaHelper()).getRegistroEntradas(salida.getCodigoSalida(),salida.getFecha(),salida.getIdProducto()));
		request.getSession().setAttribute("listaenvases",(new GestionRegistroEntradaHelper()).getEnvases());
		//Levantamos el evento success para especificar que todo ha ido bien
		
		re= new GestionRegistroEntradaHelper().getRegistroEntradas(null,null,null);
		
		//generaci�n del reporte
		try {
            JasperCompileManager.compileReportToFile(
            		"D:\\entornoMIW\\tomcat\\webapps\\opiliondb\\reportes\\TrazabilidadRS.jrxml",
            		"D:\\entornoMIW\\tomcat\\webapps\\opiliondb\\reportes\\TrazabilidadRS.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
		return SUCCESS;
	}


}
