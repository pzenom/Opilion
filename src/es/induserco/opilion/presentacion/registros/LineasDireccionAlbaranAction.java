package es.induserco.opilion.presentacion.registros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.data.OrdersLin;
import es.induserco.edifact.data.x12.DTM;
import es.induserco.edifact.presentacion.EdifactParserHelper;
import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.LineaAlbaran;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class LineasDireccionAlbaranAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven {

	private static final long serialVersionUID = 422840826723506717L;
	private HttpServletRequest request;
	private Direccion direccion = new Direccion();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("LineasDireccionAlbaranAction Action...");
		this.request=request;	
	} 

	//@Override
	public Object getModel() {		
		return direccion;
	}

 	public String execute() throws IOException {
		try {
			//1. Leemos los identificadores de los pedidos que vamos a unir en el albarán
			Map <String, String[]> parametros = request.getParameterMap();
			String nombreParametro = "";
			List<String> codigosLineasDireccion = new ArrayList<String>();
			Iterator iterator = parametros.entrySet().iterator();
			String cod = "";
			DTM dtm = new DTM();
			while(iterator.hasNext()) {
				cod = "";
				Map.Entry e = (Map.Entry) iterator.next();
				nombreParametro = (String) e.getKey();
				if (nombreParametro.indexOf("codigoPedido_") > -1){
					cod = parametros.get(e.getKey())[0];
					codigosLineasDireccion.add(cod);
				}else{
					if (nombreParametro.indexOf("fechaEntrega") > -1){
						//1.1 Leemos la nueva fecha de entrega para el albarán
						String fechaEntrega = parametros.get(e.getKey())[0];
						if (fechaEntrega != null){
							String frag[] = fechaEntrega.split("/");
							String fechaFormato = frag[2] + frag[1] + frag[0];
							dtm.setDtmFech(fechaFormato);
						}
					}
				}
			}
			
			//2. Creamos el nuevo pedido oculto
			String codigo = new GestionRegistroSalidaHelper().
				unirLineasPedidosDireccion(codigosLineasDireccion, dtm, direccion.getIdDireccion());//Nuevo bgmNum
			request.getSession().setAttribute("codigoPedido", codigo);
			
			//3. Generamos el albaran para el pedido con id=codigo
			//genera un nuevo codigo Albaran
			String codigoAlbaran = new GestionRegistroSalidaHelper().getCodigoAlbaran();
			request.getSession().setAttribute("albaran", codigoAlbaran);
			//Datos del encabezado
			request.getSession().setAttribute("fecharegistro",
					(new GestionRegistroSalidaHelper()).getFechaRegistro());
			request.getSession().setAttribute("listaclientes",
					(new GestionRegistroSalidaHelper()).getClientes());
			request.getSession().setAttribute("listacomerciales",
					(new GestionRegistroSalidaHelper()).getComerciales());
			
			//String codigo = pedido.getBgmNum();
			System.out.println("PedidosAlbaranAction: " + codigo);

			Vector<LineaAlbaran> direcciones = (new EdifactParserHelper().getDireccionesOrder(codigo));
			//request.getSession().setAttribute("listaDirecciones", direcciones);
			List<LineaAlbaran> listaDirs = new ArrayList<LineaAlbaran>(direcciones);
			request.getSession().setAttribute("listaDirecciones", listaDirs);
			//Collections.copy(listaDirs, direcciones);
			Vector resultado = new EdifactParserHelper().orderVisualizaService(codigo, false);
			if(resultado.size() > 0 && resultado.get(0).getClass().toString().endsWith("String")){
				System.out.println("Hay un error");
				request.getSession().setAttribute("mensaje", resultado);
				return ERROR;
			}
			System.out.println("Presentacion -EDIFACT: " + resultado.toString());
			request.getSession().setAttribute("pedido", resultado);
			//1. Obtiene los datos del encabezado del albaran a pàrtir de una orden
			Order orden1 = (Order) resultado.elementAt(0);
			Albaran albaranEncabezado =
				new GestionRegistroSalidaHelper().getEncabezadoAlbaranOrden(orden1);
			request.getSession().setAttribute("albaranEncabezado", albaranEncabezado);
			request.getSession().setAttribute("codigoPedido", orden1.getBgmNum());
			//carga listado de transportes
			request.getSession().setAttribute("listatipovehiculos",
					(new GestionRegistroSalidaHelper()).getTipoVehiculos());
			//2. Si existen RS asociados a cada línea los muestro
			getRSAlbaran(codigoAlbaran, resultado, orden1);
			//3. recupero la fila de totales:
			Albaran albaranTotales = new GestionRegistroSalidaHelper().getTotalesOrden(orden1.getBgmNum());
			request.getSession().setAttribute("albaranTotales", albaranTotales);
			//Cliente. Mostramos EAN + nombre
			request.getSession().setAttribute("cliente", orden1.getNadBy());
			Entidad e = new Entidad();
			//e.setIdUsuario(orden1.getNadBy());
			e.setIdUsuario(Long.parseLong(orden1.getNadBy()));
			Entidad cliente = (Entidad) new GestionEntidadesHelper().getClientes(e).get(0);
			request.getSession().setAttribute("nombreCliente", cliente.getNombre());
			Date fechaNull = null;
			//Sacamos el id del albaran a partir del codigo del albaran
			request.getSession().setAttribute("idAlbaran", Integer.parseInt(codigoAlbaran.split("-")[1]));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;	 
	 }
 	
	 /**
 	 * Método que recupera los registos de salida asociados a cada línea de pedido del albarán.
 	 *
 	 * @param codigoAlbaran the codigo albaran
 	 * @param resultado the resultado
 	 * @return lista de RS de cada línea de la orden
 	 * @throws Exception the exception
 	 * @autor MCCB
 	 */
	private void getRSAlbaran(String codigoAlbaran, Vector resultado, Order pedido) throws Exception {
		Order orden = (Order) resultado.elementAt(0);
		List listlin = orden.getLin();
		Iterator it = listlin.iterator();
		while(it.hasNext()){
			OrdersLin ol = (OrdersLin)it.next();
			System.out.println("num linea es " + ol.getLinNum());
			// 1. Recupero los rs de cada
			Vector rs=new GestionRegistroSalidaHelper().getRSLineaAlbaran(codigoAlbaran,Long.parseLong(ol.getLinNum()));
			request.getSession().setAttribute("listarsdetalle_"+ol.getLinNum(),rs);
			//2. Recupero los totales de RS de cada línea
			Vector rstotal=new GestionRegistroSalidaHelper().getRSLineaAlbaranTotal(codigoAlbaran, Long.parseLong(ol.getLinNum()));
			request.getSession().setAttribute("listarstotales_"+ol.getLinNum(),rstotal);
			//3. Recuperamos las direcciones y la cantidad que va a cada direccion
			Vector direcciones =
				new GestionRegistroSalidaHelper().getDireccionesCantidades(pedido.getIdOrders(), ol.getLinNum());
			request.getSession().setAttribute("direcciones_" + ol.getLinNum(),direcciones);
		}
	}
}