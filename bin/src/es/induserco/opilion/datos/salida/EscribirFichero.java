package es.induserco.opilion.datos.salida;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import es.induserco.opilion.infraestructura.DatabaseConfig;

import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.data.entidades.ItemFactura;
import es.induserco.opilion.data.entidades.Producto;

public class EscribirFichero extends DatabaseConfig{
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection con = null;

	public Factura getFactura(Long idFactura) throws Exception {
		Factura fact = new Factura();
		try {
			con = bddConexion();
			String Qry =
				" SELECT f.codigoFactura,f.fecha,f.pedido,f.albaran" +
				" FROM factura f"+
				" WHERE f.idFactura = " + idFactura;
			System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Albaran alb = new Albaran();
				alb.setCodigoAlbaran(rs.getString("albaran"));
				fact.setCodigoFactura(rs.getString("codigoFactura"));
				fact.setFecha(rs.getString("fecha"));
				fact.setPedido(rs.getString("pedido"));
				fact.setAlbaran(alb);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return fact;
	}

	public Entidad getDatosComercial(Long idEntidad) throws Exception {
		//Inicializamos el Vector de retorno.
		Entidad entidad = new Entidad();
		try {
			con = bddConexion();
			String Qry =
				" SELECT e.nombre,e.nif,e.ean,e.registroMercantil,d.nombreCalle,d.localidad,p.nombre as nombreProvincia,d.codigoPostal" +
				" FROM entidad e, provincia p,direccion d"+
				" WHERE e.idProvincia=p.idProvincia "+
					" AND d.empresa_idUsuario=e.idUsuario"+
					" AND e.idUsuario="+idEntidad;
			System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				entidad.setNombre(rs.getString("nombre"));
				entidad.setNif(rs.getString("nif"));
				entidad.setEan(rs.getString("ean"));
				//entidad.setRegistroMercantil(rs.getString("registroMercantil"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return entidad;
	}

	public  List getDetalleFactura(Long idFactura) throws Exception {
		//Inicializamos el Vector de retorno.
		ItemFactura item = new ItemFactura();
		List<String> datosdetafact = new ArrayList<String>();
		int contItems=0;
		try {
			con = bddConexion();
			//SELECT de datos de cada item
			String Qry="SELECT f.idFactura,f.codigoFactura,i.idItem,i.codigoItem, i.cantidad, i.precioUnitBruto, i.precioUnitNeto, i.precioTotal, i.iva, p.idProducto,p.nombre,p.codigoProducto,DATE_FORMAT(f.fecha,'%Y%m%d') as fecha,f.pedido,f.albaran" +
					   " FROM factura f, factura_item i, producto p"+
					   " WHERE f.idFactura=i.idFactura "+
					   " AND p.idProducto=i.idProducto"+
					   " AND f.idFactura="+idFactura;
			System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//item.setNombre(rs.getString("nombre"));
				item.setFactura(new Factura(rs.getLong("idFactura"),rs.getString("codigoFactura"),rs.getString("fecha"),rs.getString("pedido"),null));
				item.setProducto(new Producto(rs.getLong("idProducto"),rs.getString("codigoProducto"),rs.getString("nombre"), (Long)rs.getLong("idImpuesto")));
				item.setIdItem(rs.getLong("idItem"));
				item.setCodigoItem(rs.getString("codigoItem"));
				item.setCantidad(rs.getDouble("iva"));	
				item.setCantidad(rs.getDouble("cantidad"));
				item.setPrecioUnitBruto(rs.getDouble("precioUnitBruto"));
				item.setPrecioUnitNeto(rs.getDouble("precioUnitNeto"));	
				item.setPrecioTotal(rs.getDouble("precioTotal"));	
				item.setIva(rs.getDouble("iva"));	
				contItems++;			
				//arma el detalle por cada l�nea (�tem)
				String linId="LIN+"+item.getIdItem()+"++"+item.getCodigoItem()+":EN"+"'";
				String imd="IMD+F+M+:::"+item.getProducto().getNombre()+"'";
				String qty47="QTY+47:"+item.getCantidad()+":PCE"+"'";
				String moa66="MOA+66:"+item.getPrecioTotal()+"'";
				String priaab="PRIAAB:"+item.getPrecioUnitBruto()+"'";
				String priaaa="PRIAAB:"+item.getPrecioUnitNeto()+"'";
				String tax7="TAX+7+VAT+++:::"+item.getIva()+"'";
				String unss="UNS+S'";
				System.out.println(linId);
				System.out.println(imd);
				System.out.println(qty47);
				System.out.println(moa66);
				System.out.println(priaab);
				System.out.println(priaaa);		
				System.out.println(tax7);	
				System.out.println(unss);
				System.out.println("---");
				datosdetafact.add(linId);
				datosdetafact.add(imd);
				datosdetafact.add(qty47);
				datosdetafact.add(moa66);
				datosdetafact.add(priaab);
				datosdetafact.add(priaaa);
				datosdetafact.add(tax7);
				datosdetafact.add(unss);
			}
			//System.out.println("contador es " +contItems);
			String cnt2="CNT+2:"+contItems+"'";
			datosdetafact.add(cnt2);
			System.out.println(cnt2);
			//ahora un SELECT para sumar los totales
			String Qry2="SELECT i.iva as iva, sum(i.precioUnitBruto) as total_precio_bruto," +
			"sum(i.precioUnitNeto) as total_precio_neto, "+
			"sum(i.precioTotal) as base_imponible_total, " +
			"TRUNCATE((sum(i.precioTotal)*i.iva)/100,2) as importe_total_impuestos, "+
			"TRUNCATE(sum(i.precioTotal)+((sum(i.precioTotal)*i.iva)/100),2) as importe_total_pagar "+
			" FROM factura f, factura_item i"+
			" WHERE f.idFactura=i.idFactura "+
			" AND f.idFactura="+idFactura+
			" group by i.iva ";
			System.out.println(Qry2);
			ps = con.prepareStatement(Qry2);
			rs = ps.executeQuery();
				while (rs.next()) {			
					String moa79="MOA+79:"+rs.getString("base_imponible_total");
					String moa125="MOA+125:"+rs.getString("base_imponible_total");
					String moa98="MOA+98:"+rs.getString("base_imponible_total");
					String moa176="MOA+176:"+rs.getString("importe_total_impuestos");
					String moa139="MOA+139:"+rs.getString("importe_total_pagar");
					String taxtotal="TAX+7+VAT+++:::"+rs.getString("iva");
					String unt="UNT+36"+"[nummensaje]";
					System.out.println(moa79);	
					System.out.println(moa125);	
					System.out.println(moa98);	
					System.out.println(moa176);	
					System.out.println(moa139);	
					System.out.println(taxtotal);	
					System.out.println(moa176);
					System.out.println(moa125);	
					System.out.println(unt);
					datosdetafact.add(moa79);
					datosdetafact.add(moa125);	
					datosdetafact.add(moa98);	
					datosdetafact.add(moa176);	
					datosdetafact.add(moa139);	
					datosdetafact.add(taxtotal);	
					datosdetafact.add(moa176);
					datosdetafact.add(moa125);	
					datosdetafact.add(unt);
				}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return datosdetafact;
	}

	public  List generarDatosFichero(Long idVendedor, Long idComprador, Long idFactura) throws Exception{
		//Entidad entVend= new Entidad();
		Factura factura = new Factura();
		List<String> datos = new ArrayList<String>();
		//datos vendedor
		//entVend = getDatosComercial(idVendedor);
		//datos factura
		factura=getFactura(idFactura);
		//Armar la cabecera
		String cab="UNH+"+"[nummensaje]"+"+INVOIC:D:93A:UN:EAN007";
		String bgm="BGM+380+"+factura.getCodigoFactura();
		String dtm="DTM+137:+"+factura.getFecha()+":102";
		String refon="RFF+ON:"+factura.getPedido();
		String refdq="RFF+DQ:"+factura.getAlbaran();
		//info vendedor comprador
		//String nadii="NAD+II+:"+entVend.getNif()+"::9+++"+entVend.getDireccionComercial().getNombreCalle()+"-"+entVend.getDireccionComercial().getLocalidad()+"-"+entVend.getDireccionComercial().getProvinciaDireccion().getNombre()+"++"+entVend.getDireccionComercial().getCodigoPostal();
		//String nadsco="NAD+SCO+"+entVend.getEan()+"::9++"+entVend.getNombre()+"::"+entVend.getRegistroMercantil()+"+"+entVend.getDireccionComercial().getNombreCalle()+"+"+entVend.getDireccionComercial().getLocalidad()+"-"+entVend.getDireccionComercial().getProvinciaDireccion().getNombre()+"+"+entVend.getDireccionComercial().getCodigoPostal();
		//String refva="RFF+VA:"+entVend.getNif();
		//String nadiv="NAD+IV:"+entVend.getNif();
		//String nadbco="NAD+BCO+++"+entComp.getEan()+"::9++"+entComp.getNombre()+"::"+entComp.getRegistroMercantil()+"+"+entComp.getDireccionComercial().getNombreCalle()+"+"+entComp.getDireccionComercial().getLocalidad()+"-"+entComp.getDireccionComercial().getProvinciaDireccion().getNombre()+"+"+entComp.getDireccionComercial().getCodigoPostal();
		//String nadsu="NAD+SU+"+entVend.getNif();
		String nadby="NAD+BY+";
		String naddp="NAD+DP+";
		//String nadpr="NAD+PR+"+entVend.getNif();	
		String cux2="CUX+2:EUR:4+";	
		String pat35="DTM+13:0:102";
		datos.add(cab);
		datos.add(bgm);
		datos.add(dtm);
		datos.add(refon);
		datos.add(refdq);
		//datos.add(nadii);
		//datos.add(nadsco);
		//datos.add(refva);
		//datos.add(nadiv);
		//datos.add(nadbco);
		//datos.add(nadsu);
		datos.add(nadby);
		datos.add(naddp);
		//datos.add(nadpr);
		datos.add(cux2);
		datos.add(pat35);
		System.out.println(cab);
		System.out.println(bgm);
		System.out.println(dtm);
		System.out.println(refon);
		System.out.println(refdq);
		//System.out.println(nadii);
		//System.out.println(nadsco);
		//System.out.println(refva);
		//System.out.println(nadiv);
		//System.out.println(nadbco);
		//System.out.println(nadsu);
		System.out.println(nadby);
		System.out.println(naddp);
		//System.out.println(nadpr);
		System.out.println(cux2);
		System.out.println(pat35);
		//datos detalle de factura
		getDetalleFactura(1L);
		return datos;
	}

	public Boolean generarEDI(List<String> datos,List<String> datodetafact){
		String sFichero = "ficheroedi.txt";
		File fichero = new File(sFichero);
		if (fichero.exists()){
	          System.out.println("El fichero " + sFichero + " ya existe");
				return false;
		}
		else {
			try{
			  BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
				for(int i=0;i<datos.size();i++ ){
					bw.write(datos.get(i)+"'");
					bw.newLine();				
				}
				for(int i=0;i<datodetafact.size();i++ ){
					bw.write(datodetafact.get(i)+"'");
					bw.newLine();				
				}				
			  // Hay que cerrar el fichero
			  bw.close();
			} catch (IOException ioe){
				ioe.printStackTrace();
				return false;
			}
		}
		return true;
	}
}