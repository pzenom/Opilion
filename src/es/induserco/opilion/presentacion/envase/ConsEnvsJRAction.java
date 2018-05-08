package es.induserco.opilion.presentacion.envase;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperCompileManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.presentacion.GestionEnvasesHelper;

public class ConsEnvsJRAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Envase entry= new Envase();
	private Vector<Envase> consulta = new Vector<Envase>();
	
	public Vector<Envase> getConsulta() {
		return consulta;
	}	

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	public String execute() throws Exception {
		entry.setIdMaterial((Long)request.getSession().getAttribute("idMaterial"));			
		consulta=new GestionEnvasesHelper().getEnvases(entry);
		try {
			//ActionContext ac= ActionContext.getContext();
			//ServletContext sc = (ServletContext)ac.get(StrutsStatics.SERVLET_CONTEXT);
			//sc.getRealPath("/reportes");
            JasperCompileManager.compileReportToFile(
            		"C:\\reportes\\ConsEnvsJR.jrxml",
            		"C:\\reportes\\ConsEnvsJR.jasper");
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