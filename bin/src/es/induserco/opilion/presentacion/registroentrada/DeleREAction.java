package es.induserco.opilion.presentacion.registroentrada;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class DeleREAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private RegistroEntrada regEntradaElimina = new RegistroEntrada();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Eliminar Registro Entrada Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return regEntradaElimina;
	}
	
	public String execute() throws Exception {
		try {
			System.out.println("C�digo entrada a enviar al update " + regEntradaElimina.getCodigoEntrada());
			// Borrar el RE, los estados y las incidencias
			RegistroEntrada re = new GestionRegistroEntradaHelper().getRegistroEntrada(regEntradaElimina.getCodigoEntrada());
			new GestionRegistroEntradaHelper().deleteRegistroEntrada(regEntradaElimina);
			Vector<RegistroEntrada> listareorden =
				(Vector<RegistroEntrada>)new GestionRegistroEntradaHelper().getRegistrosEntradaOrden(re.getCodigoOrden());
			if(listareorden.isEmpty()) {
				// Si no existen RE se desactiva la orden de entrada
				new GestionRegistroEntradaHelper().deleteOrdenEntrada(regEntradaElimina.getCodigoEntrada());
				//return "ordenEliminada";
			}
			return SUCCESS;
		}
		catch (Exception e) {
			e.printStackTrace();
			return (ERROR);
		}
	}
}