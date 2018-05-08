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
 * @author andres (31/05/2011)
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class ConsultaTrazaProductoAction extends ActionSupport 
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
		if (gestionesProduccion.size() == 1){
			GestionProduccion envasado = gestionesProduccion.get(0);
			request.getSession().setAttribute("envasado", envasado);
			//Tenemos la orden de envasado y el id del producto envasado
			String ordenEnvasado = envasado.getOrden();
			long idProducto = envasado.getIdProducto();
			//A. Con esta orden, sacamos todas las lineas del proceso de envasado
			//En cada una de las lineas leemos el codigo de entrada. Con este codigo leemos la informacion de la entrada en registroentrada.
			//En ordenentrada leemos el transportista...
			Vector<RegistroEntrada> detallesEnvasado =
				(Vector<RegistroEntrada>) new GestionEnvasadoHelper().getDetallesEnvasado(ordenEnvasado);
			request.getSession().setAttribute("MP_EN_envasadoEntrada", detallesEnvasado);
			//B. Con el id del producto sacamos toda la información sobre el producto: nombre, categoría...
			Producto producto = new GestionProductosHelper().getProducto(idProducto);
			request.getSession().setAttribute("producto", producto);
			System.out.println("PASÓ LOTE!");
		}else{
			//No se envasó, miramos si se trata de un producto venta
			/*RegistroEntrada re = new RegistroEntradaDataHelper().getRegistroEntrada(producto.getLote());
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
			}else{*/
				String error = "El lote introducido no existe en la base de datos";
				request.getSession().setAttribute("error", error);
				return "NO_EXISTE";
			//}
		}
		//Levantamos el evento success para especificar que todo ha ido bien
		return SUCCESS;
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de ConsTrazaProducto!");
		return producto;
	}
}