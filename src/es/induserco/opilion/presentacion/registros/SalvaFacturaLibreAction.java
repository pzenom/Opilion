package es.induserco.opilion.presentacion.registros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Cargo;
import es.induserco.opilion.data.entidades.CuotaFactura;
import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.data.entidades.ItemFactura;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class SalvaFacturaLibreAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven {

	private static final long serialVersionUID = 422840826723506717L;
	private HttpServletRequest request;
	private Factura factura = new Factura();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	} 

	//@Override
	public Object getModel() {
		return factura;
	}

 	public String execute() throws IOException {
 		System.out.println("SalvaFacturaLibreAction Action...");
		try {
			Map <String, String[]> parametros = request.getParameterMap();
			String nombreParametro = "";
			List<ItemFactura> items = new ArrayList<ItemFactura>();
			List<CuotaFactura> cuotas = new ArrayList<CuotaFactura>();
			Iterator iterator = parametros.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry e = (Map.Entry) iterator.next();
				nombreParametro = (String) e.getKey();
				if (nombreParametro.indexOf("unidadesLinea_") > -1){
					ItemFactura item = new ItemFactura();
					String frag[] = nombreParametro.split("_");
					String numeroLinea = frag[1];
					item.setNumLinea(Long.parseLong(numeroLinea));
					item.setCantidad(Double.parseDouble(parametros.get(e.getKey())[0]));
					Iterator iterator2 = parametros.entrySet().iterator();
					while(iterator2.hasNext()){
						Map.Entry e2 = (Map.Entry) iterator2.next();
						String nombreParametro2 = (String) e2.getKey();
						if (nombreParametro2.equals("eanLinea_" + numeroLinea)){
							item.setCodigoItem(parametros.get(e2.getKey())[0]);
						}else{
							if (nombreParametro2.equals("descripcionLinea_" + numeroLinea)){
								item.setDescripcion(parametros.get(e2.getKey())[0]);
							}else{
								if (nombreParametro2.equals("loteLinea_" + numeroLinea)){
									item.setLote(parametros.get(e2.getKey())[0]);
								}else{
									if (nombreParametro2.equals("kilosLinea_" + numeroLinea)){
										item.setPeso(Double.parseDouble(parametros.get(e2.getKey())[0]));
									}else{
										if (nombreParametro2.equals("precioKiloLinea_" + numeroLinea)){
											item.setPrecioKilo(Double.parseDouble(parametros.get(e2.getKey())[0]));
										}else{
											if (nombreParametro2.equals("importeLinea_" + numeroLinea)){
												item.setPrecioTotal(Double.parseDouble(parametros.get(e2.getKey())[0]));
											}
										}
									}
								}
							}
						}
					}
					items.add(item);
				}else{
					if (nombreParametro.indexOf("lineaCuota_") > -1){
						CuotaFactura cuota = new CuotaFactura();
						long numeroCuota = Long.parseLong(parametros.get(e.getKey())[0]);
						cuota.setNumeroCuota(numeroCuota);
						Iterator iterator2 = parametros.entrySet().iterator();
						while(iterator2.hasNext()){
							Map.Entry e2 = (Map.Entry) iterator2.next();
							String nombreParametro2 = (String) e2.getKey();
							if (nombreParametro2.equals("importeCuota_" + numeroCuota)){
								cuota.setImporte(Double.parseDouble(parametros.get(e2.getKey())[0]));
							}else{
								if (nombreParametro2.equals("porcentajeCuota_" + numeroCuota)){
									cuota.setPorcentaje(Double.parseDouble(parametros.get(e2.getKey())[0]));
								}else{
									if (nombreParametro2.equals("fechaCuota_" + numeroCuota)){
										String fecha = parametros.get(e2.getKey())[0];//dd/mm/aaaa
										String[] frag = fecha.split("/");
										cuota.setFecha(frag[2] + "-" + frag[1] + "-" + frag[0]);
									}else{
										if (nombreParametro2.equals("observacionesCuota_" + numeroCuota)){
											cuota.setObservaciones(parametros.get(e2.getKey())[0]);
										}
									}
								}
							}
						}
						cuotas.add(cuota);
					}
				}
			}
			
			String cargostotal = (String)request.getParameter("cargostotal");
			String dscto = (String)request.getParameter("descuento");
			String valorDescuento = (String)request.getParameter("valorDescuento");
			String subtotal = (String)request.getParameter("subtotal");
			String fechavenc = (String)request.getParameter("fechavencimiento");
			String total = (String)request.getParameter("total");
			String iva = (String)request.getParameter("iva");
			double cargoTran = 0, ivaCargoTran = 0, totalCargoTran = 0, cargoBanc = 0,
				ivaCargoBanc = 0, totalCargoBanc = 0, cargoDevo = 0, ivaCargoDevo = 0, totalCargoDevo = 0;
			cargoTran = Double.parseDouble((String)request.getParameter("cargoTran"));
			ivaCargoTran = Double.parseDouble((String)request.getParameter("ivaCargoTran"));
			totalCargoTran = Double.parseDouble((String)request.getParameter("totalCargoTran"));
			cargoBanc = Double.parseDouble((String)request.getParameter("cargoBanc"));
			ivaCargoBanc = Double.parseDouble((String)request.getParameter("ivaCargoBanc"));
			totalCargoBanc = Double.parseDouble((String)request.getParameter("totalCargoBanc"));
			cargoDevo = Double.parseDouble((String)request.getParameter("cargoDevo"));
			ivaCargoDevo = Double.parseDouble((String)request.getParameter("ivaCargoDevo"));
			totalCargoDevo = Double.parseDouble((String)request.getParameter("totalCargoDevo"));

			List<Cargo> cargos = new ArrayList<Cargo>();
			Cargo cargoT = new Cargo("T", cargoTran);
			cargoT.setTotalCargo(totalCargoTran);
			cargoT.setIvaCargo(ivaCargoTran);
			cargos.add(cargoT);
			Cargo cargoB = new Cargo("B", cargoBanc);
			cargoB.setTotalCargo(totalCargoBanc);
			cargoB.setIvaCargo(ivaCargoBanc);
			cargos.add(cargoB);
			Cargo cargoD = new Cargo("D", cargoDevo);
			cargoD.setTotalCargo(totalCargoDevo);
			cargoD.setIvaCargo(ivaCargoDevo);
			cargos.add(cargoD);
			
			//Crea una nueva factura con encabezado y detalle
			Factura fact = new Factura();
			fact.setIdFactura(factura.getIdFactura());
			fact.setCodigoFactura(factura.getCodigoFactura());
			fact.setCargos(cargos);
			fact.setCuotas(cuotas);
			
			fact.setIvaAplicable(Double.parseDouble(this.request.getParameter("ivaAplicable")));
			fact.setDescripcionDestino((this.request.getParameter("descripcionDestino")));
			fact.setDescripcionFormaPago((this.request.getParameter("descripcionFormaPago")));
			fact.setNombreCliente((this.request.getParameter("nombreCliente")));
			fact.setTelefonoCliente((this.request.getParameter("telefonoCliente")));
			fact.setIdCliente(Long.parseLong((this.request.getParameter("idCliente"))));
			fact.setNifCliente((this.request.getParameter("nifCliente")));
			fact.setObservaciones((this.request.getParameter("observaciones")));
			
			fact.setItems(items);
			fact.setImporteTotal(Double.parseDouble(total));
			fact.setDescuento(Double.parseDouble(dscto));
			fact.setValorDescuento(Double.parseDouble(valorDescuento));
			fact.setValorIva(Double.parseDouble(iva));
			fact.setSubtotal(Double.parseDouble(subtotal));
			if (cargostotal.compareTo("") != 0)
				fact.setCargosTotal(Double.parseDouble(cargostotal));
			fact.setTotal(Double.parseDouble(total));
			String[] frags = fechavenc.split("/");
			fechavenc = frags[2] + "-" + frags[1] + "-" + frags[0];
			fact.setFechaVencimiento(fechavenc);
			
			//Insertar factura
			new GestionRegistroSalidaHelper().insertarFacturaLibre(fact);
			return SUCCESS;
		} catch (Exception e) { e.printStackTrace(); }
		return ERROR;	 
	 }
}