package es.induserco.opilion.presentacion.registroentrada;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class NuevaOETmpAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	private HttpServletRequest request;
	private RegistroEntrada registroEntrada = new RegistroEntrada();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Registro Entrada Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return registroEntrada;
	}

	public String execute() throws Exception {
		try {
			Usuario us = (Usuario) request.getSession().getAttribute("usuario");
			registroEntrada.setIdOperario(us.getLogin());
			registroEntrada.setCodigoOrden(request.getSession().getAttribute("codigoOrden").toString());
			
			System.out.println("Nueva Orden Entrada Temporal " + registroEntrada.getCodigoOrden());
			
			//Colocamos las listas de datos en la request.
			request.getSession().setAttribute("fecharegistro",(new GestionRegistroEntradaHelper()).getFechaRegistro());
			request.getSession().setAttribute("fechacaducidad",(new GestionRegistroEntradaHelper()).getFechaCaducidad());
			request.getSession().setAttribute("listaordenes",(new GestionRegistroEntradaHelper()).getOrdenes());
			request.getSession().setAttribute("listaproveedores",(new GestionRegistroEntradaHelper()).getProveedores());
			request.getSession().setAttribute("listatransportistas",(new GestionRegistroEntradaHelper()).getTransportistas());
			request.getSession().setAttribute("listacosechas",(new GestionRegistroEntradaHelper()).getCosechas());
			request.getSession().setAttribute("listatipoubicacion",(new GestionRegistroEntradaHelper()).getTipoUbicaciones());
			request.getSession().setAttribute("listaproductos",(new GestionRegistroEntradaHelper()).getProductos());
			request.getSession().setAttribute("listaenvases",(new GestionRegistroEntradaHelper()).getEnvases());
			request.getSession().setAttribute("listaProductosFinales",
					(new GestionProductosHelper()).getProductos(new Producto(), false));
			request.getSession().setAttribute("listacategorias",(new GestionRegistroEntradaHelper()).getCategorias(""));
			request.getSession().setAttribute("listavehiculos",(new GestionRegistroEntradaHelper()).getVehiculos());
			request.getSession().setAttribute("listaincidencias",(new GestionRegistroEntradaHelper()).getIncidencias());
			request.getSession().setAttribute("listaformatos",(new GestionRegistroEntradaHelper()).getFormatos());
			request.getSession().setAttribute("listaestadosfabas",(new GestionRegistroEntradaHelper()).getEstadosFabas());
			request.getSession().setAttribute("listaincidenciastmp", new Vector<Object>());
			
			// Guardar el albaran, origen, proveedor y tipo de registro para mostrarlo en Nueva2OE.jsp
			request.getSession().setAttribute("albaran", registroEntrada.getAlbaran());
			request.getSession().setAttribute("origen", registroEntrada.getOrigen());
			request.getSession().setAttribute("idproveedor", registroEntrada.getIdProveedor());
			request.getSession().setAttribute("proveedor",
					(new GestionEntidadesHelper()).getEntidad(registroEntrada.getIdProveedor()).getNombre());
			//Vamos a cargar aqui el tipo de registros que puede generar el proveedor (Los tipos del proveedor)
			request.getSession().setAttribute("tiposProveedor",
					(new GestionEntidadesHelper()).getEntidad(registroEntrada.getIdProveedor()).getTiposProveedor());

			// Guardar el transportista, vehiculo y condiciones TTE
			request.getSession().setAttribute("idtranportista", registroEntrada.getIdTransportista());
			request.getSession().setAttribute("transportista",
					(new GestionEntidadesHelper()).getEntidad(registroEntrada.getIdTransportista()).getNombre());
			request.getSession().setAttribute("descvehiculo", registroEntrada.getDescVehiculo());
			request.getSession().setAttribute("notasvehiculo", registroEntrada.getNotasVehiculo());
			
			System.out.println("Nueva orden entrada temporal " + registroEntrada.getCodigoOrden());
							
			//Lanzamos la grabación a la tabla temporal
			new GestionRegistroEntradaHelper().getOrdenEntradaTemporal(registroEntrada);
			
			//Levantamos el evento success para especificar que todo esta bien
			return (SUCCESS);	
		}
		catch (Exception e) {
			return (ERROR);
		}
	}
}