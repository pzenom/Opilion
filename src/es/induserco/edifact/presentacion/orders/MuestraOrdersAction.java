package es.induserco.edifact.presentacion.orders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import es.induserco.edifact.data.Order;
import es.induserco.edifact.data.OrdersLin;
import es.induserco.edifact.data.x12.DTM;
import es.induserco.edifact.presentacion.EdifactParserHelper;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.LineaAlbaran;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class MuestraOrdersAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Struts Muestra Order Action...");
		this.request = request;
	} 
	
 	public String execute() throws IOException {
		try {
			boolean editable =
				Boolean.parseBoolean(request.getParameter("editable"));
			request.getSession().setAttribute("editable", editable);
			String codigo = request.getParameter("codigoSalida");
			System.out.println("MuestraOrdersAction: "+codigo);
			Vector resultado = (new EdifactParserHelper()).orderVisualizaService(codigo, true);
			if(resultado.get(0).getClass().toString().endsWith("String")){
				System.out.println("Hay un error");
				request.getSession().setAttribute("mensaje", resultado);
				return ERROR;
			}
			Order pedido = (Order)resultado.get(0);
			request.getSession().setAttribute("codigoPedido", pedido.getBgmNum());
			request.getSession().setAttribute("divisa", pedido.getCux());
			long idFormaDePago = pedido.getIdFormaPago();
			String formaDePago = new GestionEntidadesHelper().getNombreFormaPago(idFormaDePago);
			request.getSession().setAttribute("formaDePago", formaDePago);
			String eanCliente = pedido.getNadBy();
			Entidad cliente = new Entidad();
			cliente.setIdUsuario(Long.parseLong(pedido.getNadBy()));
			List<Entidad> clientes = (List<Entidad>)new GestionEntidadesHelper().getClientes(cliente);
			if (clientes.size() > 0){
				cliente = clientes.get(0);
				//request.getSession().setAttribute("cliente", clientes);
				request.getSession().setAttribute("nombreCliente", cliente.getNombre());
				request.getSession().setAttribute("nifCliente", cliente.getNif());
			}
			//Leer las fechas del pedido
			List<DTM> fechas = pedido.getDtm();
			Iterator iterator = fechas.iterator();
			while (iterator.hasNext()){
				DTM fecha = (DTM) iterator.next();
				if (fecha.getDtmFunc().compareTo("137") == 0){
					request.getSession().setAttribute("fechaPedido", fecha.getDtmFech());
				}
				else
					if (fecha.getDtmFunc().compareTo("2") == 0){
						String f = fecha.getDtmFech();
						String[] frag = f.split("/");
						if (frag.length == 1){//fecha EDI -> 201005281900
							//2010 05 28
							int a = Integer.parseInt(f.substring(2, 4));
							int m = Integer.parseInt(f.substring(4, 6));
							int d = Integer.parseInt(f.substring(6, 8));
							String diaString = "" + d;
							String mesString = "" + m;
							if (diaString.length() == 1)
								diaString = "0" + diaString;
							if (mesString.length() == 1)
								mesString = "0" + mesString;
							request.getSession().setAttribute("fechaEntrega",
									diaString + "/" + mesString + "/" + a);
						}else{
							String annio = "20" + frag[2];
							request.getSession().setAttribute("fechaEntrega",
									frag[0] + "/" + frag[1] + "/" + annio);
						}
						
					}
			}
			//Leer las lineas del pedido
			List<OrdersLin> lineas = pedido.getLin();
			iterator = lineas.iterator();
			double bultosTotal = 0;
			double cantidadTotal = 0;
			double pesoTotal = 0;
			while (iterator.hasNext()){
				OrdersLin order = (OrdersLin) iterator.next();
				//Leer
				int comprueba = Integer.parseInt(order.getLinNum());
				if ((comprueba % 2) == 0){
					order.setTipoOrdena("odd");
				} else
					order.setTipoOrdena("even");
				//Ahora leer el nombre del producto final
				Producto pro = new Producto();
				pro.setCodigoEan(order.getIdLin());
				List<Producto> productos =
					(List<Producto>) new GestionProductosHelper().getProductos(pro, false);
				if (productos.size() > 0){
					order.setNombreProducto(productos.get(0).getDescripcion());
					order.setPeso(productos.get(0).getPeso());
				}
				bultosTotal += Double.parseDouble(order.getQty59Cant());
				cantidadTotal += Double.parseDouble(order.getQty21Cant());
				//pesoTotal += qty21 * pesoProducto;
				pesoTotal += (Double.parseDouble(order.getQty21Cant())) * (order.getPeso());
				//pesoTotal += order.getPeso();
		 	}
			request.getSession().setAttribute("lineasPedido", lineas);
			Vector<LineaAlbaran> direcciones = (new EdifactParserHelper().getDireccionesOrder(codigo));
			//request.getSession().setAttribute("listaDirecciones", direcciones);
			List<LineaAlbaran> listaDirs = new ArrayList<LineaAlbaran>(direcciones);
			request.getSession().setAttribute("listaDirecciones", listaDirs);
			//Leer estos de los sumatorios de las lineas cargadas antes
			request.getSession().setAttribute("pesoTotal", pesoTotal);
			request.getSession().setAttribute("bultosTotal", bultosTotal);
			request.getSession().setAttribute("cantidadTotal", cantidadTotal);
			request.getSession().setAttribute("importeTotal", pedido.getMoa79());
			System.out.println("Presentacion -EDIFACT: "+resultado.toString());
			request.getSession().setAttribute("pedido",resultado);
			return SUCCESS;
		} catch (Exception e) {	e.printStackTrace(); }
		return ERROR;
	 }
}