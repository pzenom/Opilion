package es.induserco.edifact.presentacion.orders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.data.OrdersLin;
import es.induserco.edifact.data.OrdersLoc;
import es.induserco.edifact.data.x12.DTM;
import es.induserco.edifact.data.x12.IMD;
import es.induserco.edifact.negocio.EdifactParserDataHelper;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class UpdateOrderAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object> {
  	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Order pedido = new Order();
	
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("NORMALTOEDI Action...");
		this.request=request;
	}
	
	//@Override
	public Object getModel() {
		System.out.println("procesando el get model de normalToEDI!");
		return pedido;
	}

 	public String execute() throws IOException {
 		System.out.println("execute de normalToEDI!");
 		/**
 		 * Por rellenar:
 		 * unh
 		 * aliCond
 		 * nadMr (Receptor del mensaje)
 		 * nadSu (Proveedor)
 		 * nadPr (Emisor de pago)
 		 * 
 		 * piaNumIn (numero artículo interno del comprador)
 		 * priInf
 		 */
		try {
			Usuario us = (Usuario) request.getSession().getAttribute("usuario");
			Map <String, String[]> parametros = request.getParameterMap();
			String nombreParametro = "", fechaEntrega = "";
			List<DTM> fechas = new ArrayList<DTM>();
			List<OrdersLin> lineas = new ArrayList<OrdersLin>();
			List<OrdersLoc> localizaciones = new ArrayList<OrdersLoc>();
			Iterator iterator = parametros.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry e = (Map.Entry) iterator.next();
				nombreParametro = (String) e.getKey();
				if(nombreParametro.indexOf("linea_") > -1) {
					//Encontramos una nueva direccion
					OrdersLin linea = new OrdersLin();
					String fragmenta[] = nombreParametro.split("_");
					linea.setLinNum(fragmenta[1]);
					linea.setPiaNumSa(parametros.get(e.getKey())[0]);
					lineas.add(linea);
				}
			}
			//Buscamos todas las localizaciones para cada linea
			iterator = parametros.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry e = (Map.Entry) iterator.next();
				nombreParametro = (String) e.getKey();
				if(nombreParametro.indexOf("inputDireccion_") > -1) {
					OrdersLoc loc = new OrdersLoc();
					//Id del pedido
					//loc.setIdOrders(pedido.getIdOrders());
					String fragmenta[] = nombreParametro.split("_");
					//Id de la linea
					loc.setIdLin(fragmenta[1]);
					//Destino
					String dest = parametros.get(e.getKey())[0];
					String idDireccion = dest.split("-")[0];
					//loc.setLoc((new GestionRegistroSalidaHelper()).getDireccionesEAN(idDireccion));
					loc.setLoc(idDireccion);
					//Ahora buscamos la cantidad
					Iterator i = parametros.entrySet().iterator();
					while(i.hasNext() ) {
						Map.Entry entrada = (Map.Entry) i.next();
						nombreParametro = (String) entrada.getKey();
						if(nombreParametro.indexOf("unidades_") > -1) {
							String frag[] = nombreParametro.split("_");
							if ((frag[1].compareTo(loc.getIdLin()) == 0) &&
									(frag[2].compareTo(fragmenta[2]) == 0)){
								loc.setQty(parametros.get(entrada.getKey())[0]);
								break;
							}
						}
					}
					localizaciones.add(loc);
				}
			}
			//A cada una de las lineas en "lineas", añadirle su conjunto de localizaciones
			Iterator it = lineas.listIterator();
			while(it.hasNext() ) {
				OrdersLin l = (OrdersLin) it.next();
				//String linId = l.getIdLin();
				String linId = l.getLinNum();
				List<OrdersLoc> locs = new ArrayList<OrdersLoc>();
				Iterator itera = localizaciones.listIterator();
				//Buscamos todas las localizaciones para la linea
				while(itera.hasNext() ) {
					OrdersLoc loc = (OrdersLoc) itera.next();
					if (loc.getIdLin().compareTo(linId) == 0){
						locs.add(loc);
					}
				}
				//En locs, todos los localizadores para la linea actual
				l.setLoc(locs);
			}
			iterator = parametros.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry e = (Map.Entry) iterator.next();
				nombreParametro = (String) e.getKey();
				String f[];
				String idLinea = "", linNum = "";
				if(nombreParametro.indexOf("unidadesPedidas_") > -1) {
					f = nombreParametro.split("_");
					linNum = f[1];
					Iterator i = lineas.listIterator();
					while(i.hasNext()) {
						OrdersLin l = (OrdersLin) i.next();
						if (l.getLinNum().compareTo(linNum) == 0){
							l.setQty21Cant(parametros.get(e.getKey())[0]);
						}
					}
				}else{
					if(nombreParametro.indexOf("observaciones_") > -1) {
						f = nombreParametro.split("_");
						linNum = f[1];
						Iterator i = lineas.listIterator();
						while(i.hasNext()) {
							OrdersLin l = (OrdersLin) i.next();
							if (l.getLinNum().compareTo(linNum) == 0){
								l.setObservaciones(parametros.get(e.getKey())[0]);
							}
						}
					}else{
						if(nombreParametro.indexOf("pesoLinea_") > -1){
							f = nombreParametro.split("_");
							linNum = f[1];
							Iterator i = lineas.listIterator();
							while(i.hasNext() ) {
								OrdersLin l = (OrdersLin) i.next();
								if (l.getLinNum().compareTo(linNum) == 0)
									l.setPesoLinea(Double.parseDouble(parametros.get(e.getKey())[0]));
							}
						}else{
							if(nombreParametro.indexOf("unidadesAgrupaciones_") > -1) {
								f = nombreParametro.split("_");
								linNum = f[1];
								Iterator i = lineas.listIterator();
								while(i.hasNext() ) {
									OrdersLin l = (OrdersLin) i.next();
									if (l.getLinNum().compareTo(linNum) == 0)
										l.setQty59Cant(parametros.get(e.getKey())[0]);
								}
							} else{
								if(nombreParametro.indexOf("precioUnidad_") > -1) {
									f = nombreParametro.split("_");
									linNum = f[1];
									Iterator i = lineas.listIterator();
									while(i.hasNext() ) {
										OrdersLin l = (OrdersLin) i.next();
										if (l.getLinNum().compareTo(linNum) == 0)
											l.setPriAaa(parametros.get(e.getKey())[0]);
									}
								} else{
									if(nombreParametro.indexOf("precioTotalLinea_") > -1) {
										f = nombreParametro.split("_");
										linNum = f[1];
										Iterator i = lineas.listIterator();
										while(i.hasNext() ) {
											OrdersLin l = (OrdersLin) i.next();
											if (l.getLinNum().compareTo(linNum) == 0)
												l.setMoa203(parametros.get(e.getKey())[0]);
										}
									} else{
										if(nombreParametro.compareTo("fechaEntrega") == 0){
											fechaEntrega = parametros.get(e.getKey())[0];
											if (fechaEntrega.compareTo("") != 0){
												String frag[] = fechaEntrega.split("/");
												String fechaFormato = frag[2] + frag[1] + frag[0];
												DTM dtm = new DTM();
												dtm.setDtmFech(fechaFormato);
												dtm.setDtmForm("102");
												dtm.setDtmFunc("2");
												fechas.add(dtm);
											}
										} else{
											if(nombreParametro.compareTo("fechaPedido") == 0){
												fechaEntrega = parametros.get(e.getKey())[0];
												if (fechaEntrega.compareTo("") != 0){							
													String frag[] = fechaEntrega.split("/");
													String fechaFormato = frag[2] + frag[1] + frag[0];
													DTM dtm = new DTM();
													dtm.setDtmFech(fechaFormato);
													dtm.setDtmForm("102");
													dtm.setDtmFunc("137");
													fechas.add(dtm);
												}
											} else{
												if(nombreParametro.indexOf("codEan_") > -1) {
													f = nombreParametro.split("_");
													linNum = f[1];
													Iterator i = lineas.listIterator();
													while(i.hasNext() ) {
														OrdersLin l = (OrdersLin) i.next();
														if (l.getLinNum().compareTo(linNum) == 0){
															String eanIdProducto = parametros.get(e.getKey())[0];
															String frag[] = eanIdProducto.split("__");
															String ean = frag[0];
															String idProducto = frag[1];
															long idProd = Long.parseLong(idProducto);
															IMD imd = new IMD();
															if (ean.compareTo("") != 0){
																l.setIdLin(ean);
																l.setIdProducto(idProd);
																imd.setIdLinea(Long.valueOf(ean));
																imd.setIdProducto(idProd);
																//A partir del EAN, sacar la info del producto (Descripcion)
																imd.setImdDesc((new GestionRegistroSalidaHelper()).getInfoProducto(ean));
															}
															imd.setImdCara("DSC");
															imd.setImdTipo("F");
															l.setImd(imd);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			//Tipo de pedido: Pedido
			pedido.setBgmTipo("220");
			//Funcion del mensaje: Propuesta
			pedido.setBgmFunc("16");
			//Usuario responsable
			pedido.setNadMr("" + us.getIdUsuario());
			pedido.setDtm(fechas);
			pedido.setLin(lineas);
			new EdifactParserDataHelper().updateOrder(pedido);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}		
		return SUCCESS;
 	}
}