package es.induserco.opilion.presentacion.registroentrada;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class AgregaEstadoRETmpAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private RegistroEntrada entry = new RegistroEntrada();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Agrega Estado RETmp Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return entry;
	}

	public String execute() throws Exception {
		try {
			System.out.println("AGREGA");
			// Obtener el idEstado
			
			// Obtener los datos del Registro Entrada
			entry.setLote(request.getParameter("lote"));
			
			entry.setCodigoOrden((String)request.getSession().getAttribute("codigoOrden"));
			entry.setCantidad(Double.valueOf(request.getParameter("cant")).doubleValue());
			entry.setCostoUnitario(Double.valueOf(request.getParameter("cUni")).doubleValue());
			entry.setCostoTotal(Double.valueOf(request.getParameter("cTotal")).doubleValue());
			entry.setIdTipoRegistro(request.getParameter("tipoReg"));
			//entry.setFechaLlegada(request.getParameter("fLlegada"));
			//entry.setFechaCaducidad(request.getParameter("fCaduc"));
			entry.setIdProducto(Long.valueOf(request.getParameter("idPro")));
			entry.setIdEnvase(Long.valueOf(request.getParameter("idEnv")));
			entry.setIdCategoriaEntrada(Long.valueOf(request.getParameter("idCatEntr")));
			entry.setIdCategoria(Long.valueOf(request.getParameter("idCat")));
			entry.setIdCosecha(Long.valueOf(request.getParameter("idCosecha")));
			entry.setIdFormatoEntrega(Long.valueOf(request.getParameter("idFormEntr")));
			entry.setNumeroBultos(Long.valueOf(request.getParameter("numBultos")));
			entry.setNumeroPallets(Long.valueOf(request.getParameter("numPallets")));
			entry.setTemperatura(Double.valueOf(request.getParameter("temp")).doubleValue());
			entry.setHumedad(Double.valueOf(request.getParameter("hum")).doubleValue());
			entry.setIdTipoUbicacion(Long.valueOf(request.getParameter("idTipUbic")));
			
			System.out.println("ESTADOS: " + request.getParameter("estados"));
			System.out.println("INCIDENCIAS: " + request.getParameter("incidencias"));
			
			// Insertar en la tabla registroentrada_estado_tmp
			// Cargar los estados asociados a ese codigoEntrada/idEstado para el grid
			// Obtener el nombre de los estados
			//Vector <RegistroEntrada>listaestados = new GestionRegistroEntradaHelper().getRegistrosEntradaOrdenTmp(codigoOrden);
			//request.getSession().setAttribute("listaestados",listaestados);
			
			// Si el registro de entrada no está insertado, se inserta
			//Usuario us = (Usuario) request.getSession().getAttribute("usuario");
			//entry.setIdOperario(us.getLogin());
			//entry.setCodigoOrden((String)request.getSession().getAttribute("codigoorden"));
			//System.out.println("CODIGO ORDEN: " + entry.getCodigoOrden());
			
			System.out.println("ID: " + request.getParameter("idEstado"));
			//System.out.println(request.getSession().getAttribute("codigoEstado"));
			//request.getSession().setAttribute("codigoEstado",(request.getAttribute("codigoEstado")));
			//System.out.println(request.getSession().getAttribute("codigoEstado"));

			//Colocamos las listas de datos en la request.
			//request.getSession().setAttribute("codigoEntrada",(request.getAttribute("codigoEntrada")));
			//entry.setCodigoEntrada((String)request.getAttribute("codigoEntrada"));
			
			//System.out.println("CODIGO ENTRADA: " + entry.getCodigoEntrada());
			
			/*request.getSession().setAttribute("fecharegistro",(new GestionRegistroEntradaHelper()).getFechaRegistro());
			request.getSession().setAttribute("fechacaducidad",(new GestionRegistroEntradaHelper()).getFechaCaducidad());		
			//request.getSession().setAttribute("listaproveedores",(new GestionRegistroEntradaHelper()).getProveedores());
			//request.getSession().setAttribute("listaproductos",(new GestionRegistroEntradaHelper()).getProductos());
			//request.getSession().setAttribute("listacategorias",(new GestionRegistroEntradaHelper()).getCategorias());
			//request.getSession().setAttribute("listavehiculos",(new GestionRegistroEntradaHelper()).getVehiculos());
			request.getSession().setAttribute("listaincidencias",(new GestionRegistroEntradaHelper()).getIncidencias());
			//request.getSession().setAttribute("listaformatos",(new GestionRegistroEntradaHelper()).getFormatos());
			request.getSession().setAttribute("listaestadosfabas",(new GestionRegistroEntradaHelper()).getEstadosFabas());		
			//request.getSession().setAttribute("listacosechas",(new GestionRegistroEntradaHelper()).getCosechas());
			//request.getSession().setAttribute("listaenvases",(new GestionRegistroEntradaHelper()).getEnvases());
		
			// Se guarda en la request los datos de la orden
			request.getSession().setAttribute("listaregistrosdel",(new GestionRegistroEntradaHelper()).loadRegistroEntradaTmp(entry));*/			
			
			//Levantamos el evento success para especificar que todo esta bien
			return (SUCCESS);	 
		}
		catch (Exception e) {
			return (ERROR);
		}
	}
}