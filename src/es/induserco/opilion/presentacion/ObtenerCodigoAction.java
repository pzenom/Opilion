package es.induserco.opilion.presentacion;

import net.sf.jasperreports.engine.JasperCompileManager;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class ObtenerCodigoAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private String codigo;

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("Obtener código de barras!");
		return codigo;
	}

	public String execute() throws Exception {
		//generación del reporte
		try {
            JasperCompileManager.compileReportToFile(
            		"C:\\reportes\\codigoBarras.jrxml",
            		"C:\\reportes\\codigoBarras.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
		return SUCCESS;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
}