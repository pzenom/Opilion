package es.induserco.opilion.presentacion.registroentrada;

import net.sf.jasperreports.engine.JasperCompileManager;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class EtiqREJRAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private RegistroEntrada entrada= new RegistroEntrada();
	private RegistroEntrada re = new RegistroEntrada();
	
	public RegistroEntrada getRe() {
		return re;
	}

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("execute de Etiqueta Registro Entrada!");
		return entrada;
	}

	public String execute() throws Exception {
		//Colocamos la lista de entradades en la request.
		re = new GestionRegistroEntradaHelper().getEtiquetaRE(entrada.getCodigoEntrada());
		//generaci�n del reporte
		try {
            JasperCompileManager.compileReportToFile(
            		"/reportes/entradas/EtiqREJR.jrxml",
            		"/reportes/entradas/EtiqREJR.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
		return SUCCESS;
	}
}