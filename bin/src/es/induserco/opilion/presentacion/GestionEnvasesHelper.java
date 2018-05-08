package es.induserco.opilion.presentacion;

import java.util.Vector;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService;

import es.induserco.opilion.data.entidades.Envase;

public class GestionEnvasesHelper {
	
	public Boolean addEnvase(Envase entry) throws Exception{
		return ((IGestionEnvasesService)(new ServiceLocator()).getService("IGestionEnvasesService")).addEnvase(entry);
	}

	public Vector<Envase> getEnvases(Envase entry) throws Exception{
		return ((IGestionEnvasesService)(new ServiceLocator()).getService("IGestionEnvasesService")).getEnvases(entry);
	}

	public Vector getEnvases(Long idMaterial) throws Exception{
		return ((IGestionEnvasesService)(new ServiceLocator()).getService("IGestionEnvasesService")).getEnvases(idMaterial);
	}

	public Boolean updateEnvase(Envase envaseFind, Envase envaseUpdate)throws Exception {
		return ((IGestionEnvasesService)(new ServiceLocator()).getService("IGestionEnvasesService")).updateEnvase(envaseFind, envaseUpdate);
	}

	public Vector loadEnvase(Envase entry) throws Exception{
		return ((IGestionEnvasesService)(new ServiceLocator()).getService("IGestionEnvasesService")).loadEnvase(entry);
	}
	
	public Vector getMateriales() throws Exception{
		return ((IGestionEnvasesService)(new ServiceLocator()).getService("IGestionEnvasesService")).getMateriales();
	}

	public Boolean deshabilitaEnvase(Envase entry) throws Exception {
		return ((IGestionEnvasesService)(new ServiceLocator()).getService("IGestionEnvasesService")).deshabilitaEnvase(entry);
	}
}