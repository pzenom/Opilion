package es.induserco.opilion.presentacion.cliente;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperCompileManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class ConsCLJRAction extends ActionSupport
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Entidad entidad= new Entidad();
	private Vector<Entidad> consulta = new Vector<Entidad>();

	public Vector<Entidad> getConsulta() {
		return consulta;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	public String execute() throws Exception {
		consulta = new GestionEntidadesHelper().getClientes(entidad);
		try {
			//ActionContext ac= ActionContext.getContext();
			//ServletContext sc = (ServletContext)ac.get(StrutsStatics.SERVLET_CONTEXT);
			//sc.getRealPath("/reportes");
            JasperCompileManager.compileReportToFile(
            		"C:\\reportes\\ConsCLJR.jrxml",
            		"C:\\reportes\\ConsCLJR.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
		return SUCCESS;
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de informe de clientes!");
		return entidad;
	}
}