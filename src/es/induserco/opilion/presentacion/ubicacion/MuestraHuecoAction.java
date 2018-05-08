package es.induserco.opilion.presentacion.ubicacion;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;

public class MuestraHuecoAction extends ActionSupport implements ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private Ubicacion ubicacion = new Ubicacion();

	//@Override
	public Object getModel(){
		return ubicacion;
	}

	public String execute() throws Exception{
		System.out.println("Execute MuestraHuecoAction");
		return SUCCESS;
	}
}