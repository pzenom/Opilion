package es.induserco.opilion.presentacion.registros;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.data.entidades.TipoVehiculo;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

/**
 * Inserta un nuevo albar�n en la base de datos
 * @author andres (09/05/2011)
 * @version 0.3
 */
public class InseDetaAlbaOrdenAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Albaran albaran = new Albaran();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Insertar Albaran Action...");
		this.request=request;
	}

	//@Override
	public Object getModel() {
		return albaran;
	}

	public String execute() throws Exception {
		boolean flagDireccionAlbaran = false;
		albaran.setUsuarioResponsable(((Usuario) request.getSession().getAttribute("usuario")).getLogin());
		//Llega un cero de m�s en: cantidadTotal, importeTotal, numeroBultosTotal, pesoNetoTotal
		//Si los cogemos de la request, en lugar de directamente del albaran, tenemos los valores correctos
		albaran.setCantidadTotal(Double.parseDouble(request.getParameter("cantidadTotal")));
		albaran.setImporteTotal(Double.parseDouble(request.getParameter("importeTotal")));
		albaran.setNumeroAgrupacionesTotal(Double.parseDouble(request.getParameter("numeroAgrupacionesTotal")));
		albaran.setPesoNetoTotal(Double.parseDouble(request.getParameter("pesoNetoTotal")));
		
		Map <String, String[]> parametros = request.getParameterMap();
		String nombreParametro = "";
		Vector<String> leidos = new Vector<String>();
		boolean auto = false;
		Long idCliente = (long)0;
		String codigoPedido = "";
		int cuantasLineas = 0;
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			//1. Cargamos la informaci�n general del albar�n
			if(nombreParametro.compareTo("codigoPedido") == 0)
				codigoPedido = parametros.get(e.getKey())[0];
			else
				if(nombreParametro.compareTo("cli") == 0){
					idCliente = Long.parseLong(parametros.get(e.getKey())[0]);
					Entidad en = new Entidad();
					en.setIdUsuario(idCliente);
					albaran.setCliente(en);
				}
				else
					if(nombreParametro.compareTo("auto") == 0)
						auto = Boolean.parseBoolean(parametros.get(e.getKey())[0]);
					else
						if(nombreParametro.compareTo("cnt") == 0)
							cuantasLineas = Integer.parseInt(parametros.get(e.getKey())[0]);
		}
		//Cargamos todos los registros de entrada
		if (auto){
			//2. Para cada una de las lineas del albaran...
			for (int i = 1; i <= cuantasLineas; i++){
				//Creamos un RegistroSalida
				RegistroSalida salida = new RegistroSalida();
				//Con su numero de linea
				salida.setNumLinea((long)i);
				//Leemos la cantidad unitaria pedida para la linea
				salida.setCantidadUnitaria(Double.parseDouble(parametros.get("cantidadUnitaria_" + i)[0]));
				//salida.setPrecioUnitario(Double.parseDouble(parametros.get("brutoUnitario_" + i)[0]));
				salida.setPrecioUnitario(Double.parseDouble(parametros.get("netoUnitario_" + i)[0]));
				salida.setPrecioTotal(Double.parseDouble(parametros.get("netoLinea_" + i)[0]));
				//Leemos la informaci�n de la l�nea 
				int cuantosBultos = 0;
				iterator = parametros.entrySet().iterator();
				while(iterator.hasNext()) {
					Map.Entry e = (Map.Entry) iterator.next();
					nombreParametro = (String) e.getKey();
					if(nombreParametro.indexOf("bultos_") > -1){
						String fragmenta[] = nombreParametro.split("_");
						//Encontramos el numero de bultos para la linea actual
						if (fragmenta[2].compareTo("" + i) == 0){
							cuantosBultos = Integer.parseInt(parametros.get(e.getKey())[0]);
							//Guardamos el ean del producto como el id del producto de la linea actual
							salida.setIdProducto(Long.parseLong(fragmenta[1]));
							break;
						}
					}
				}
				//Una vez que sabemos los bultos que hay para la linea seguimos
				//En cuantosBultos tenemos el numero de bultos para la linea actual
					
				Vector<Bulto> bultos = new Vector<Bulto>();
				String ean = "", idProducto = "";
				//3. Para cada uno de los bultos en la l�nea...
				for (int j = 0; j < cuantosBultos; j++){
					Bulto bulto = new Bulto();
					//Creamos el array de lotes
					Vector<Lote> lotes = new Vector<Lote>();
					//Leemos el destino del bulto, y los lotes que componen el bulto
					iterator = parametros.entrySet().iterator();
					while(iterator.hasNext()) {
						Map.Entry entry = (Map.Entry) iterator.next();
						nombreParametro = (String) entry.getKey();
						boolean flag = true;
						for (int h = 0; h < leidos.size(); h++)
							if (nombreParametro.compareTo(leidos.elementAt(h)) == 0){
								flag = false;
								break;
							}
						if (flag){
							if(nombreParametro.indexOf("lotesLeidos_" + i + "_" + (j + 1)) > -1){//Encontramos un lote leido para nuestro bulto
								//Nos llega de la forma: lotesLeidos_linea_bulto_ean_lote, y en value llega el lote que ha sido leido
								String frags[] = nombreParametro.split("_");
								String noRepite = nombreParametro;
								String lote = parametros.get(entry.getKey())[0];
								idProducto = frags[3];
								Lote loteLeido = new Lote();
								loteLeido.setLote(lote);
								//Buscamos la cantidad del lote
								Iterator ite = parametros.entrySet().iterator();
								String cantidad = "";
								ite = parametros.entrySet().iterator();
								while(ite.hasNext()) {
									Map.Entry entrada = (Map.Entry) ite.next();
									nombreParametro = (String) entrada.getKey();
									//cantidadLoteLeido_1_1_0000000008888_1
									if(nombreParametro.indexOf("cantidadLoteLeido_" + i +
											"_" + (j + 1) + "_" + idProducto + "_" + frags[4]) > -1){//Encontramos la ubicacion para el lote
										cantidad = parametros.get(entrada.getKey())[0];
										loteLeido.setCantidad(Double.parseDouble(cantidad));
										break;
									}
								}
								lotes.add(loteLeido);
								//Buscamos el resto de lotes para el bulto
								ite = parametros.entrySet().iterator();
								while(ite.hasNext()) {
									Map.Entry entrada = (Map.Entry) ite.next();
									nombreParametro = (String) entrada.getKey();
									if(nombreParametro.indexOf("lotesLeidos_" + i + "_" + (j + 1)) > -1){//Encontramos un lote leido para un bulto
										//Nos llega de la forma: lotesLeidos_linea_bulto_ean_lote, y en value llega el lote que ha sido leido
										String f[] = nombreParametro.split("_");
										if (nombreParametro.compareTo(noRepite) != 0){
											leidos.add(nombreParametro);
											leidos.add(noRepite);
											String lo = parametros.get(entrada.getKey())[0];
											Lote l = new Lote();
											//Buscamos la cantidad del lote
											cantidad = "";
											ite = parametros.entrySet().iterator();
											while(ite.hasNext()) {
												entrada = (Map.Entry) ite.next();
												nombreParametro = (String) entrada.getKey();
												//cantidadLoteLeido_1_1_0000000008888_1
												if(nombreParametro.indexOf("cantidadLoteLeido_" + i +
														"_" + (j + 1) + "_" + idProducto + "_" + f[4]) > -1){//Encontramos la ubicacion para el lote
													cantidad = parametros.get(entrada.getKey())[0];
													l.setCantidad(Double.parseDouble(cantidad));
													break;
												}
											}
											l.setLote(lo);
											lotes.add(l);
											break;
										}
									}
								}
							}
						}
					}
					bulto.setLotes(lotes);
					//Buscamos la direccion a la que va el lote
					Iterator iter = parametros.entrySet().iterator();
					while(iter.hasNext()) {
						Map.Entry entra = (Map.Entry) iter.next();
						nombreParametro = (String) entra.getKey();
						if(nombreParametro.indexOf("bultoDireccion_" + i + "_" + (j + 1)) > -1){//Encontramos la direccion a la que va un bulto
							//Nos llega de la forma: bultoDireccion_linea_bulto_eanDireccion
							String separa[] = nombreParametro.split("_");
							bulto.setDireccionEntrega(Integer.parseInt(separa[3]));
							if (!flagDireccionAlbaran){
								albaran.setDestino("" + bulto.getDireccionEntrega());
								flagDireccionAlbaran = true;
							}
							break;
						}
					}
					bultos.add(bulto);
				}
				salida.setBultos(bultos);
				salida.setNumeroBultos((long)bultos.size());
				salida.setAlbaran(albaran);
				Producto aux = new Producto();
				aux.setIdProducto(Long.parseLong(idProducto));
				//aux.setCodigoEan(ean);//(salida.getIdProducto().toString());
				//aux.setIdProducto(salida.getIdProducto());
				Producto pro = (Producto)new GestionProductosHelper().getProductos(aux, false).get(0);
				salida.setPesoNeto(pro.getPeso() * salida.getCantidadUnitaria());
				System.out.println("Ingresar 'salida' en la BD");
				//salida.setCodigoEan(ean);
				salida.setIdProducto(Long.parseLong(idProducto));
				new GestionRegistroSalidaHelper().addRegistroSalida(salida);
			}
		}
		
		/**
		 * Todav�a no se conocen, se modifican en el futuro: comercial y veh�culo
		 * pesoNetoTotal
		 */
		albaran.setComercial(new Entidad());
		albaran.setTipoVehiculo(new TipoVehiculo());
		
		Entidad cliente = new Entidad();
		cliente.setIdUsuario(idCliente);
		cliente.setEan(String.valueOf(idCliente));
		albaran.setCliente(cliente);
		albaran.setCodigoOrden(codigoPedido);
		albaran.setCodigoAlbaran((String)request.getSession().getAttribute("albaran"));
		albaran.setTipoAlbaran("O");
		System.out.println("cliente: "+ albaran.getCliente().getIdUsuario());
		//System.out.println("comercial"+albaran.getComercial().getIdUsuario());
		System.out.println("albaran: "+ albaran.getCodigoAlbaran());
		System.out.println("resp: "+ albaran.getUsuarioResponsable());
		new GestionRegistroSalidaHelper().addAlbaran(albaran);
		String pedido = codigoPedido;
		//Ahora hay que actualizar el estado del pedido a L (Listo)
		new GestionRegistroSalidaHelper().actualizaEstadoPedido(pedido, 'L');
		return SUCCESS;
	}
}