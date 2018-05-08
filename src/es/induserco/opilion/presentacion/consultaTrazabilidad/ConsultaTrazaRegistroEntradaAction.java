package es.induserco.opilion.presentacion.consultaTrazabilidad;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.MovimientoAlmacen;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.negocio.RegistroEntradaDataHelper;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

/**
 * @author andres (06/03/2014)
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class ConsultaTrazaRegistroEntradaAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Producto producto = new Producto();

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	
	public String execute() throws Exception {
		Vector<RegistroSalida> salidas = (Vector<RegistroSalida>)
			new GestionRegistroSalidaHelper().getRegistroSalidas(producto.getLote(), "", null, 0L);
		request.getSession().setAttribute("salidas", salidas);
		request.getSession().setAttribute("lote", producto.getLote());
		Vector<MovimientoAlmacen> movimientos = new GestionUbicacionesHelper().getMovimientosLote(producto.getLote());
		request.getSession().setAttribute("movimientos", movimientos);
		Vector<MovimientoAlmacen> modificacionesStock = new GestionUbicacionesHelper().getModificacionesStock(producto.getLote());
		request.getSession().setAttribute("modificacionesStock", modificacionesStock);
		Vector<RegistroEntrada> devoluciones = (Vector<RegistroEntrada>)
			new GestionRegistroEntradaHelper().getDevolucionesLote(producto.getLote());
		request.getSession().setAttribute("devoluciones", devoluciones);
		String estados[] = null;
		Vector<GestionProduccion> gestionesProduccion = (Vector<GestionProduccion>)
				new GestionEnvasadoHelper().getProcesosEnvasado("", "", producto.getLote(), 0, estados, 'S');
		//Distinguir registro de entrada normal q se usa como materia prima de registro de entrada producto venta
		RegistroEntrada re = new RegistroEntradaDataHelper().getRegistroEntrada(producto.getLote());
		if (re != null && re.getCodigoEntrada() != null && re.getCodigoEntrada().equals(producto.getLote()) && re.getIdTipoRegistro().equals("P")){
			//Sí, se trata de un producto venta
			//1. No pasó por procesos de envasado, enviamos null
			request.getSession().setAttribute("envasado", null);
			//2. Enviamos la información del producto
			Producto producto = new GestionProductosHelper().getProducto(re.getIdProducto());
			request.getSession().setAttribute("producto", producto);
			//3. La información de la entrada
			request.getSession().setAttribute("MP_EN_envasadoEntrada", re);
			System.out.println("PASÓ PRODUCTO VENTA!");
		}else{
			Vector<Producto> lotes = (Vector<Producto>) new GestionEnvasadoHelper().getTrazabilidadRegistroEntrada(producto.getLote());
			request.getSession().setAttribute("lotes", lotes);
		}
		return SUCCESS;
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de ConsTrazaProducto!");
		return producto;
	}
}