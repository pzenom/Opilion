package es.induserco.opilion.presentacion.registroentrada;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class AgregaBultoREAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven {

	private HttpServletRequest request;
	private RegistroEntrada entry = new RegistroEntrada();
	private int nombre;
	private double peso;
	private Bulto bulto = new Bulto();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Bultos Registro Entrada Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return entry;
	}
	
	public String execute() throws Exception {
		try {
			//entry.setIdEntrada(Long.valueOf(request.getParameter("idEntrada")));
			entry.setCodigoEntrada((String)(request.getParameter("idEntrada")));
			nombre = Integer.parseInt(request.getParameter("nombre"));
			peso = Double.valueOf(request.getParameter("peso")).doubleValue();
			//new GestionRegistroEntradaHelper().addBultoRE(entry.getIdEntrada(),nombre,peso);
			new GestionRegistroEntradaHelper().addBultoRE(entry.getCodigoEntrada(),nombre,peso);
			bulto.setCodigoEntrada((String)(request.getParameter("idEntrada")));
			bulto.setNumBulto(Integer.parseInt(request.getParameter("nombre")));
			bulto.setNumBulto(Integer.parseInt(request.getParameter("nombre")));
			bulto.setPeso(Double.valueOf(request.getParameter("peso")).doubleValue());
			System.out.println("ID: "+bulto.getCodigoEntrada());
			System.out.println("N�: "+bulto.getNumBulto());
			System.out.println("Kg: "+bulto.getPeso());
			new GestionRegistroEntradaHelper().addBultoRE(bulto);
			return SUCCESS;
		}
		catch (Exception e) {
			return (ERROR);
		}
	}
}