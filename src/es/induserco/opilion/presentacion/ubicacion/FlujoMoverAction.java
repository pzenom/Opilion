package es.induserco.opilion.presentacion.ubicacion;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;

public class FlujoMoverAction extends ActionSupport implements ModelDriven<Object> {

	private static final long serialVersionUID = 1L;
	private Ubicacion entry = new Ubicacion();

	//@Override
	public Object getModel() {	
		return entry;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}
}