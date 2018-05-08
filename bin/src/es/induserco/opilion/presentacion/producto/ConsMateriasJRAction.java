package es.induserco.opilion.presentacion.producto;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperCompileManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.MateriaPrima;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class ConsMateriasJRAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private MateriaPrima entry = new MateriaPrima();
	private Vector<MateriaPrima> consulta = new Vector<MateriaPrima>();

	public Vector<MateriaPrima> getConsulta() {
		return consulta;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}
	
	public String execute() throws Exception {
		consulta = new GestionProductosHelper().getMateriasPrimas(0);
		try {
            JasperCompileManager.compileReportToFile(
            		"C:\\reportes\\ConsMateriasJR.jrxml",
            		"C:\\reportes\\ConsMateriasJR.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }			
		return (SUCCESS);
		
	}
	
	//@Override
	public Object getModel() {
		return entry;
	}
}