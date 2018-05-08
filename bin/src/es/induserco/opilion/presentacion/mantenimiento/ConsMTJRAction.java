package es.induserco.opilion.presentacion.mantenimiento;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperCompileManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Mantenimiento;
import es.induserco.opilion.presentacion.GestionCribadoHelper;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;


public class ConsMTJRAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private Mantenimiento mant= new Mantenimiento();
	private Vector<Mantenimiento> consulta = new Vector<Mantenimiento>();

	public Vector<Mantenimiento> getConsulta() {
		return consulta;
	}		

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el action de consulta de mantenimiento!");
		return mant;
	}

	public String execute() throws Exception {
		String fechaConsulta = (String)request.getParameter("fechaConsulta");
		
		//String idTipoMant=(String)request.getSession().getAttribute("idTipoMant");
		//mant.setIdTipoMant((Long) request.getSession().getAttribute("idTipoMant"));
		mant.setIdTipo((Long) request.getSession().getAttribute("idTipoMant"));
		mant.setIdMaquina((Long)request.getSession().getAttribute("idMaquina"));
		//String idMaquina=(String)request.getSession().getAttribute("idMaquina");

		//request.getSession().setAttribute("idTipoMant",(mant.getIdTipoMant()));
		//request.getSession().setAttribute("idMaquina",(mant.getIdMaquina()));
		//consulta=new GestionRegistroEntradaHelper().getRegistrosMT(mant.getIdTipoMant(),mant.getIdMaquina(),fechaConsulta);
		long tipo = 0;// = mant.getIdTipo();
		long idMaquina = 0;// = mant.getIdMaquina();
		if (mant.getIdTipo() == null)
			tipo = 0;
		if (mant.getIdMaquina() == null)
			idMaquina = 0;
		consulta = new GestionRegistroEntradaHelper().
			getMantenimientosProgramados(tipo , idMaquina, fechaConsulta);
		try {
			//ActionContext ac= ActionContext.getContext();
			//ServletContext sc = (ServletContext)ac.get(StrutsStatics.SERVLET_CONTEXT);
			//sc.getRealPath("/reportes");
            JasperCompileManager.compileReportToFile(
            		"C:\\reportes\\ConsMTJR.jrxml",
            		"C:\\reportes\\ConsMTJR.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }			
		return (SUCCESS);
	}
}