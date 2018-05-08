package es.induserco.opilion.presentacion.gestionProduccionSeleccion;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class RegiGPSeleAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private HttpServletRequest request;
	private RegistroEntrada entry = new RegistroEntrada();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Registro Entrada Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {		
		return entry;
	}

	public String execute() throws Exception {
		//Colocamos las listas de datos en la request.
		request.getSession().setAttribute("codigoEntrada",(request.getAttribute("codigoEntrada")));
		entry.setCodigoEntrada((String)request.getAttribute("codigoEntrada"));
		
		Double saldoregistro = new GestionRegistroEntradaHelper().getSaldoRegistro(entry.getCodigoEntrada());
		String saldo = Double.toString(saldoregistro);
		request.getSession().setAttribute("saldoregistro",saldo);
		
		request.getSession().setAttribute("cantidadusar",(request.getAttribute("cantidadusar")));
		entry.setCantidad((Double)request.getAttribute("cantidadusar"));
		System.out.println("Cantidad a usar es "+entry.getSaldo());

		request.getSession().setAttribute("fecharegistro",(new GestionRegistroEntradaHelper()).getFechaRegistro());
		request.getSession().setAttribute("fechacaducidad",(new GestionRegistroEntradaHelper()).getFechaCaducidad());
		request.getSession().setAttribute("listacategorias",(new GestionRegistroEntradaHelper()).getCategorias(""));
		request.getSession().setAttribute("listacategoriasalida",(new GestionRegistroEntradaHelper()).getCategoriasFiltradas(1));
		request.getSession().setAttribute("listaformatos",(new GestionRegistroEntradaHelper()).getFormatos());
		request.getSession().setAttribute("listaestadosfabas",(new GestionRegistroEntradaHelper()).getEstadosFabas());
		request.getSession().setAttribute("listaproveedores",(new GestionRegistroEntradaHelper()).getProveedores());
		request.getSession().setAttribute("listacosechas",(new GestionRegistroEntradaHelper()).getCosechas());
		request.getSession().setAttribute("listaproductos",(new GestionRegistroEntradaHelper()).getProductos());
		request.getSession().setAttribute("listavehiculos",(new GestionRegistroEntradaHelper()).getVehiculos());
		request.getSession().setAttribute("listaincidencias",(new GestionRegistroEntradaHelper()).getIncidenciasFiltradas(1));
		
		request.getSession().setAttribute("listaregistrosupd",
				(new GestionRegistroEntradaHelper()).getRegistroEntradas(entry.getCodigoEntrada(),
						entry.getFecha(),entry.getIdProducto()));
		
		//Categorias Resultantes
		request.getSession().setAttribute("listacategoriasseleccion",
				(new GestionRegistroEntradaHelper()).getCategorias(entry.getCodigoEntrada()));
		
		/** SACAR UBICACIONES **/
		Vector<LineaProducto> materias =
			(new GestionDesgranadoHelper().getInfoMateria(entry.getCodigoEntrada()));
		ArrayList<Ubicacion> ubicaciones =
			new GestionUbicacionesHelper().getUbicacionesEntrada(entry.getCodigoEntrada(), true);
			materias.get(0).setUbicaciones(ubicaciones);
		request.getSession().setAttribute("ingredientes", materias);
		
		return (SUCCESS);
	}
}