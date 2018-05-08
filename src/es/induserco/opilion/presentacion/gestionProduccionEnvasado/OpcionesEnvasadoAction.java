package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;

/*************************************************************
 *  Visualización de las opciones para los procesos de envasado
 * @version 0.0
 * @author Induserco - Andrés - 08/04/2011
**************************************************************/	
public class OpcionesEnvasadoAction extends ActionSupport implements ModelDriven<Object>{

	private static final long serialVersionUID = -9013888892313309236L;
	private GestionProduccion gprod = new GestionProduccion();

	public Object getModel() {
		System.out.println("GetModel OpcionesEnvasado");
		return gprod;
	}

	public String execute() throws Exception {
		System.out.println("Execute OpcionesEnvasado");
		return SUCCESS;
	}
}