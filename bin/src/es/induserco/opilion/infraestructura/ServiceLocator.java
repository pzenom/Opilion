package es.induserco.opilion.infraestructura;

import java.util.HashMap;

public class ServiceLocator {
	
	private HashMap<String, String> configuration;

	public ServiceLocator(){
		
		configuration = new HashMap<String, String>();
		configuration.put("IGestionEntidadesService","es.induserco.opilion.negocio.gestionentidades.GestionEntidadesService");
		configuration.put("IEntidadDataService", "es.induserco.opilion.datos.entidad.EntidadDataService");
		
		configuration.put("IGestionProductosService","es.induserco.opilion.negocio.gestionproductos.GestionProductosService");
		configuration.put("IProductoDataService", "es.induserco.opilion.datos.producto.ProductoDataService");

		configuration.put("IGestionEnvasesService","es.induserco.opilion.negocio.gestionenvases.GestionEnvasesService");
		configuration.put("IEnvaseDataService", "es.induserco.opilion.datos.envase.EnvaseDataService");
		
		configuration.put("IGestionesEspecialesService","es.induserco.opilion.negocio.gestionesespeciales.GestionesEspecialesService");
		configuration.put("IGestionesEspecialesDataService", "es.induserco.opilion.datos.especial.GestionesEspecialesDataService");

		configuration.put("IGestionUsuariosService","es.induserco.opilion.negocio.gestionusuarios.GestionUsuariosService");
		configuration.put("IUsuarioDataService", "es.induserco.opilion.datos.usuario.UsuarioDataService");

		configuration.put("IGestionUbicacionesService","es.induserco.opilion.negocio.gestionubicaciones.GestionUbicacionesService");
		configuration.put("IUbicacionDataService", "es.induserco.opilion.datos.ubicacion.UbicacionDataService");
		
		configuration.put("IGestionRegistroEntradaService","es.induserco.opilion.negocio.gestionregistroentrada.GestionRegistroEntradaService");
		configuration.put("IRegistroEntradaDataService", "es.induserco.opilion.datos.entrada.RegistroEntradaDataService");
		
		configuration.put("IGestionRegistroSalidaService","es.induserco.opilion.negocio.gestionregistrosalida.GestionRegistroSalidaService");
		configuration.put("IRegistroSalidaDataService", "es.induserco.opilion.datos.salida.RegistroSalidaDataService");

		configuration.put("IGestionRegistroEnvasadoService","es.induserco.opilion.negocio.gestionproduccion.GestionRegistroEnvasadoService");
		configuration.put("IGestionProduccionDataService", "es.induserco.opilion.datos.produccion.GestionProduccionDataServices");
		
		configuration.put("IGestionMermasProduccionService","es.induserco.opilion.negocio.gestionproduccion.GestionMermasProduccionService");
		configuration.put("IGestionProduccionDataService", "es.induserco.opilion.datos.produccion.GestionProduccionDataServices");
		
		configuration.put("IGestionRegistroFiltradoService","es.induserco.opilion.negocio.gestionproduccion.GestionRegistroFiltradoService");
		configuration.put("IGestionProduccionDataService", "es.induserco.opilion.datos.produccion.GestionProduccionDataServices");
		
		configuration.put("IGestionRegistroCribadoService","es.induserco.opilion.negocio.gestionproduccion.GestionRegistroCribadoService");
		configuration.put("IGestionProduccionDataService", "es.induserco.opilion.datos.produccion.GestionProduccionDataServices");
		
		configuration.put("IGestionRegistroDesgranadoService","es.induserco.opilion.negocio.gestionproduccion.GestionRegistroDesgranadoService");
		configuration.put("IGestionProduccionDataService", "es.induserco.opilion.datos.produccion.GestionProduccionDataServices");
		
		configuration.put("IGestionRegistroCongeladoService","es.induserco.opilion.negocio.gestionproduccion.GestionRegistroCongeladoService");
		configuration.put("IGestionProduccionDataService", "es.induserco.opilion.datos.produccion.GestionProduccionDataServices");
		
		configuration.put("IGestionRegistroFumigadoService","es.induserco.opilion.negocio.gestionproduccion.GestionRegistroFumigadoService");
		configuration.put("IGestionProduccionDataService", "es.induserco.opilion.datos.produccion.GestionProduccionDataServices");

		configuration.put("IGestionRegistroIngredientesService","es.induserco.opilion.negocio.gestionproduccion.GestionRegistroIngredientesService");
		configuration.put("IGestionProduccionDataService", "es.induserco.opilion.datos.produccion.GestionProduccionDataServices");
		
		configuration.put("IGestionRegistroEnvasesService","es.induserco.opilion.negocio.gestionproduccion.GestionRegistroEnvasesService");
		configuration.put("IGestionProduccionDataService", "es.induserco.opilion.datos.produccion.GestionProduccionDataServices");
		
		configuration.put("IMensajeDataService", "es.induserco.opilion.datos.mensaje.MensajeDataService");
		
		configuration.put("IOrdersParserService", "es.induserco.edifact.negocio.orders.OrdersParserService");
		configuration.put("IOrdersParserDataService", "es.induserco.edifact.datos.orders.OrdersDataService");
		configuration.put("IOrdersVisualizaService", "es.induserco.edifact.negocio.orders.OrdersVisualizaService");
	}

	public Object getService(String service) throws Exception{
		return Class.forName((String) configuration.get(service)).newInstance();
	}
}