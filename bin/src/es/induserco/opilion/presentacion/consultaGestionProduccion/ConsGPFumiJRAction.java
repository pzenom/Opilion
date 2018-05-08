package es.induserco.opilion.presentacion.consultaGestionProduccion;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperCompileManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionFumigadoHelper;

public class ConsGPFumiJRAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private GestionProduccion gprod= new GestionProduccion();
	private Vector<GestionProduccion> consulta = new Vector<GestionProduccion>();
	
	public Vector<GestionProduccion> getConsulta() {
		return consulta;
	}			

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta Registro Fumigado!");
		// TODO Auto-generated method stub
		return gprod;
	}

	public String execute() throws Exception {
		//Colocamos la lista de gprodes en la request.
		String fechaConsulta = (String)request.getParameter("fechaConsulta");
		//Llega de la forma 08/11/2011, y debe compararse con la forma 2011-11-02
		String[] frag = null;
		if (fechaConsulta != null)
			frag = fechaConsulta.split("/");
		if (frag != null && frag.length == 3)
			fechaConsulta = frag[2] + "-" + frag[1] + "-" + frag[0];
		System.out.println("fecha es"+fechaConsulta);		
		request.getSession().setAttribute("orden",(gprod.getOrden()));
		//request.getSession().setAttribute("listafumigados",(new GestionFumigadoHelper()).getRegistroFumigados(gprod.getOrden(),fechaConsulta));
		consulta=new GestionFumigadoHelper().getRegistroFumigados(gprod.getOrden(),fechaConsulta);
		try {
			//ActionContext ac= ActionContext.getContext();
			//ServletContext sc = (ServletContext)ac.get(StrutsStatics.SERVLET_CONTEXT);
			//sc.getRealPath("/reportes");
            JasperCompileManager.compileReportToFile(
            		"C:\\reportes\\ConsGPFumiJR.jrxml",
            		"C:\\reportes\\ConsGPFumiJR.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }				
		return (SUCCESS);
	}
}