package es.induserco.opilion.presentacion.mantenimiento;

import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import es.induserco.opilion.data.entidades.Mantenimiento;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class ConsMTAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private Mantenimiento mant = new Mantenimiento();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute consulta de mantenimientos!");
		return mant;
	}
	
	public String execute() throws Exception {
		//String fechaConsulta = (String)request.getParameter("fechaConsulta");
		//request.getSession().setAttribute("idTipoMant", mant.getIdTipo());//(mant.getIdTipoMant()));
		//request.getSession().setAttribute("idMaquina",(mant.getIdMaquina()));
		//request.getSession().setAttribute("listamaqmant",new GestionRegistroEntradaHelper().getRegistrosMT(mant.getIdTipoMant(),mant.getIdMaquina(),fechaConsulta));
		request.getSession().setAttribute("listaMantenimientos",new GestionRegistroEntradaHelper().getRegistrosMT(mant.getIdTipo(),0,mant.getFecha()));
		return (SUCCESS);
	}
}