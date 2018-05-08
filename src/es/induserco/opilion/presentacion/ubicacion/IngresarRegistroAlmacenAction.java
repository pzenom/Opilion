package es.induserco.opilion.presentacion.ubicacion;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class IngresarRegistroAlmacenAction extends ActionSupport implements ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private Ubicacion ubicacion = new Ubicacion();

	//@Override
	public Object getModel() {		
		return ubicacion;
	}

	public String execute() throws Exception {
		new GestionUbicacionesHelper().registrarAlmacen(ubicacion);
		return (SUCCESS);
	}
}