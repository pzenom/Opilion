package es.induserco.opilion.presentacion.devoluciones;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class ListaDevolucionesAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private RegistroEntrada entrada= new RegistroEntrada();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}
	
	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta Registro Entrada!");
		return entrada;
	}
	
	public String execute() throws Exception {
		String fecha = entrada.getStrFecha();
		if (fecha == null)
			fecha = "";
		if (fecha != null && fecha.compareTo("") != 0){
			//Formateamos la fecha que llega de la forma: 07/09/2011
			//Necesitamos la fecha de la forma: 2011-09-07
			String[] fragmentosFecha = fecha.split("/");
			String fechaBuena = fragmentosFecha[2] + "-" + fragmentosFecha[1] + "-" + fragmentosFecha[0];
			fecha = fechaBuena;
		}
		request.getSession().setAttribute("listaDevoluciones", (new GestionRegistroEntradaHelper()).getDevoluciones(fecha));   		
		return (SUCCESS);
	}
}