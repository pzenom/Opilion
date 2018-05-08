package es.induserco.opilion.presentacion.consultaGestionProduccion;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperCompileManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.presentacion.GestionCribadoHelper;

public class ConsGPCribJRAction extends ActionSupport 
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
		System.out.println("procesando el execute de Consulta Registro Cribado!");
		return gprod;
	}

	public String execute() throws Exception {
		//Colocamos la lista de gprodes en la request.
		String fechaConsulta = (String)request.getParameter("fechaConsulta");
		System.out.println("fecha es"+fechaConsulta);		
		request.getSession().setAttribute("orden",(gprod.getOrden()));
		//request.getSession().setAttribute("listacribados",(new GestionCribadoHelper()).getRegistroCribados(gprod.getOrden(),fechaConsulta,gprod.getIdProducto()));

		consulta=new GestionCribadoHelper().getRegistroCribados(gprod.getOrden(),fechaConsulta,gprod.getIdProducto());
		try {
			//ActionContext ac= ActionContext.getContext();
			//ServletContext sc = (ServletContext)ac.get(StrutsStatics.SERVLET_CONTEXT);
			//sc.getRealPath("/reportes");
            JasperCompileManager.compileReportToFile(
            		"C:\\reportes\\ConsGPCribJR.jrxml",
            		"C:\\reportes\\ConsGPCribJR.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
		return (SUCCESS);
	}
}