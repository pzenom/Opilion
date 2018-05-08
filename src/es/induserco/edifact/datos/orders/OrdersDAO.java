package es.induserco.edifact.datos.orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.data.OrdersLin;
import es.induserco.edifact.data.OrdersLoc;
import es.induserco.edifact.data.x12.DTM;
import es.induserco.edifact.data.x12.IMD;
import es.induserco.opilion.data.entidades.DireccionEntrega;
import es.induserco.opilion.data.entidades.EstadoPedido;
import es.induserco.opilion.data.entidades.LineaAlbaran;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.infraestructura.DatabaseConfig;

/**
 * @author Induserco, Andres (05/04/2011)
 * @version 1.2
 */
public class OrdersDAO extends DatabaseConfig implements IOrdersDataService {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	//@Override
	/**
	 * @param filtro 0: todos; 1: ultimos 20; 2: ultimos 50; 3: ultima semana; 4: ultimo mes; 5: ultimo año; 
	 */
	public Vector<Order> orderLista(Order order, int filtro) throws Exception {
		System.out.println("OrdersDAO: orderLista");
		Vector<Order> resultado = new Vector<Order>();
		try {
			PreparedStatement ps2, ps3;
			ResultSet rs2, rs3;
			con = bddConexion();
			String Qry =
				" SELECT *, forma.descripcion as descripcionFormaPago, " +
					" IF(cli.apellidos='', cli.nombre, CONCAT(cli.nombre, ' - ', cli.apellidos)) AS nombreCliente " +
				" FROM fac_orders f " +
					" LEFT JOIN fac_orders_estados estados ON f.estado = estados.idEstado " +
					" LEFT JOIN referenciabancaria ref ON ref.idDatoBancario = f.idFormaPago " +
					" LEFT JOIN formapago forma ON forma.idFormaPago = ref.idFormaPago " +
					" LEFT JOIN entidad cli ON cli.idUsuario = f.nadBy " +
				" WHERE f.estado != 'X' " +
					" AND bgmTipo = '220' ";
			if (order != null && (order.getEstado() == 'R' || order.getEstado() == 'L'
					|| order.getEstado() == 'N' || order.getEstado() == 'E'
					|| order.getEstado() == 'A'))
				Qry += " AND estado='" + order.getEstado() + "' ";
			if (order != null && order.getNadBy() != null && !order.getNadBy().equals(""))
				Qry += " AND f.nadBy = '" + order.getNadBy() + "' ";
			String limit = "", Qry2 = "";
			if (filtro > 0){
				if (filtro == 3 || filtro == 4 || filtro == 5){
					String fechaInicio = "", fechaFin = "";
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					fechaFin = dateFormat.format(cal.getTime());
					switch (filtro) {
						case 3://ultima semana
							cal.add(Calendar.DATE, -7);
							fechaInicio = dateFormat.format(cal.getTime());
							break;
						case 4://ultimo mes
							cal.add(Calendar.MONTH, -1);
							fechaInicio = dateFormat.format(cal.getTime());
							break;
						case 5://ultimo año
							cal.add(Calendar.YEAR, -1);
							fechaInicio = dateFormat.format(cal.getTime());
							break;
						default:
							break;
					}
					Qry2 = Qry2 + " AND f.fechaRegistro BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "' ";
				}else{
					switch (filtro){
						case 1:
							limit = " LIMIT 20; ";
							break;
						case 2:
							limit = " LIMIT 50; ";
							break;
						default:
							break;
					}
				}
			}
			Qry2 += " ORDER BY f.fechaRegistro DESC ";
			Qry = Qry.concat(Qry2).concat(limit);
			System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Order pedido = new Order();
				pedido.setIdOrders(rs.getLong("idOrders"));
				pedido.setUnh(rs.getString("unh"));
				pedido.setBgmFunc(rs.getString("bgmFunc"));
				pedido.setBgmNum(rs.getString("bgmNum"));
				pedido.setBgmTipo(rs.getString("bgmTipo"));
				pedido.setAliCond(rs.getString("aliCond"));
				pedido.setFtxCali(rs.getString("ftxCali"));
				pedido.setNadMs(rs.getString("nadMs"));
				pedido.setRffMs(rs.getString("rffMs"));
				pedido.setNadMr(rs.getString("nadMr"));
				pedido.setRffMr(rs.getString("rffMr"));
				pedido.setNadSu(rs.getString("nadSu"));
				pedido.setRffSu(rs.getString("rffSu"));
				pedido.setNadBy(rs.getString("nadBy"));
				pedido.setNombreCliente(rs.getString("nombreCliente"));
				pedido.setRffBy(rs.getString("rffBy"));
				pedido.setNadDp(rs.getString("nadDp"));
				pedido.setRffDp(rs.getString("rffDp"));
				pedido.setNadIv(rs.getString("nadIv"));
				pedido.setRffIv(rs.getString("rffIv"));
				pedido.setNadPr(rs.getString("nadPr"));
				pedido.setRffPr(rs.getString("rffPr"));
				pedido.setCux(rs.getString("cux"));
				pedido.setMoa79(rs.getString("moa79"));
				pedido.setCnt(rs.getString("cnt"));
				pedido.setUnt(rs.getString("unt"));
				pedido.setEstado(rs.getString("estado").charAt(0));
				pedido.setDescripcionEstado(rs.getString("nombre"));
				String descripcionFormaPago = rs.getString("descripcionFormaPago") + " a " + rs.getLong("diasFormaPago") + " dias";
				String numeroCuenta = rs.getString("numCuenta");
				if (!numeroCuenta.equals("000000000000000000000000"))
					descripcionFormaPago += ". Numero cuenta: " + numeroCuenta;
				pedido.setDescripcionFormaPago(descripcionFormaPago);
				pedido.setIdFormaPago(rs.getLong("idFormaPago"));
				//Buscamos el nombre del usuario responsable (nadMs)
				String consultaResponsable = "SELECT login FROM usuario u WHERE u.idUsuario = '" + pedido.getNadMr() + "'";
				//System.out.println(consultaResponsable);
				ps2 = con.prepareStatement(consultaResponsable);
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					pedido.setNombreResponsable(rs2.getString("login"));
				}
				//System.out.println("Vamos a por la fecha");
				// Carga los tipos de fecha
				ps2 = con.prepareStatement("SELECT * FROM fac_orders_dtm WHERE idOrders='" + pedido.getIdOrders() + "'");
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					DTM dtm = new DTM();
					String dtmFech = rs2.getString("dtmFech");//20111026
					dtm.setDtmForm(rs2.getString("dtmForm"));
					if (dtm.getDtmForm().compareTo("102") == 0){
						//20111026
						int a = 0, m = 0, d = 0;
						a = Integer.parseInt(dtmFech.substring(2, 4));
						m = Integer.parseInt(dtmFech.substring(4, 6));
						d = Integer.parseInt(dtmFech.substring(6));
						String diaString = "" + d;
						String mesString = "" + m;
						if (diaString.length() == 1)
							diaString = "0" + diaString;
						if (mesString.length() == 1)
							mesString = "0" + mesString;
						String dtmFechFormateada = diaString + "/" + mesString + "/" + a;
						dtm.setDtmFech(dtmFechFormateada);
					}else
						dtm.setDtmFech(dtmFech);
					dtm.setDtmFunc(rs2.getString("dtmFunc"));
					pedido.setDtm(dtm);
				}
				//System.out.println("Vamos a por los productos");
				// Carga los artículos
				/*ps2 = con.prepareStatement("SELECT * FROM fac_orders_lin WHERE idOrders='" + pedido.getIdOrders() + "' ORDER BY (linNum + 0)");
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					OrdersLin lin = new OrdersLin();
					lin.setIdLin(rs2.getString("idLin"));
					lin.setLinNum(rs2.getString("linNum"));
					lin.setPiaNumSa(rs2.getString("piaNumSA"));
					lin.setPiaNumIn(rs2.getString("piaNumIN"));
					lin.setQty21Cant(rs2.getString("qty21Cant"));
					//lin.setKilos(rs2.getDouble("kilos"));
					lin.setQty59Cant(rs2.getString("qty59Cant"));
					lin.setMoa203(rs2.getString("moa203"));
					lin.setPriAaa(rs2.getString("priAaa"));
					lin.setPriAab(rs2.getString("priAab"));
					lin.setPriInf(rs2.getString("PriInf"));
					// Carga las direcciones para la linea
					ps3 = con.prepareStatement("SELECT nombreCalle, d.localidad, d.codigoPostal," +
							"f.idOrders, f.idLin, f.idLoc, f.qty " +
							" FROM fac_orders_loc f, direccion d " +
							" WHERE idOrders='"
							+ pedido.getIdOrders() + "' AND f.idLoc = d.idDireccion");
					rs3 = ps3.executeQuery();
					List<OrdersLoc> dirs = new ArrayList<OrdersLoc>();
					while (rs3.next()) {
						OrdersLoc loc = new OrdersLoc();
						loc.setIdLin(rs3.getString("idLin"));
						loc.setIdOrders(rs3.getLong("idOrders"));
						loc.setLoc(rs3.getString("idLoc"));
						loc.setQty(rs3.getString("qty"));
						loc.setDescripcion(rs3.getString("nombreCalle"));
						dirs.add(loc);
					}
					lin.setLoc(dirs);
					pedido.setLin(lin);
				}*/
				String consulta =
					" SELECT COUNT(DISTINCT folo.idLoc) as direccionesDiferentes " +
					" FROM fac_orders fo, fac_orders_lin foli, fac_orders_loc folo " +
					" WHERE fo.idOrders = '" + pedido.getIdOrders() + "' " +
					" AND foli.idOrders = fo.idOrders " +
					" AND folo.idOrders = fo.idOrders " +
					" AND folo.idLin = foli.linNum";
				ps3 = con.prepareStatement(consulta);
				rs3 = ps3.executeQuery();
				while (rs3.next()) {
					pedido.setNumeroDireccionesDistintas(rs3.getLong("direccionesDiferentes"));
				}
				resultado.add(pedido);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("OrdersDAO-FIN");
		return resultado;
	}

	//@Override
	public Boolean orderExists(Order pedido) throws Exception {
		System.out.println("OrdersDAO: orderExists");
		try {
			con = bddConexion();
			String Qry =
				" SELECT * " +
				" FROM fac_orders " +
				" WHERE unh = '" + pedido.getUnh() + "' " +
					" AND nadMs ='" + pedido.getNadMs() + "' " +
					" AND bgmNum ='" + pedido.getBgmNum() + "'";
			System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Habemus Papam: El fichero ya ha existe");
				return true;
			} else
				System.out.println("Non Habemus Papam: El fichero no existe");
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		System.out.println("OrdersDAO-FIN");
		return false;
	}

	/**
	 * @author Induserco, Andres (25/04/2011)
	 * @since 1.1
	 */
	public long getProximoId() throws Exception {
		long id = 0;
		try {
			con = bddConexion();
			// obtengo el identificador
			ps = con.prepareStatement("SELECT max(idOrders) AS idOrders FROM fac_orders");
			rs = ps.executeQuery();
			rs.next();
			id = (rs.getLong("idOrders")) + 1;
			con.close();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	/**
	 * Devuelve todas las direcciones de entrega asociadas a un pedido
	 * 
	 * @param idOrder
	 * @return
	 * @throws Exception
	 */
	public Vector<LineaAlbaran> getDireccionesOrder(String pedido)
			throws Exception {
		System.out.println("OrdersDAO: getDireccionesOrder");
		Vector<LineaAlbaran> lineas = new Vector<LineaAlbaran>();
		con = null;
		try {
			con = bddConexion();
			// 1. Obtenemos todas las lineas del pedido
			String Qry = "SELECT DISTINCT orders.idOrders, lineas.linNum as linea, lineas.idLin"
					+ " FROM fac_orders_lin lineas, fac_orders orders"
					+ " WHERE orders.bgmNum = '"
					+ pedido
					+ "'"
					+ " AND orders.idOrders = lineas.idOrders"
					+ " ORDER BY linea";
			System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				LineaAlbaran linea = new LineaAlbaran();
				linea.setIdPedido(rs.getString("idOrders"));
				linea.setNumeroLinea(rs.getLong("linea"));
				linea.setEanProducto(rs.getString("idLin"));
				lineas.add(linea);
				// 2. Para cada linea, guardamos sus direcciones
				for (int i = 0; i < lineas.size(); i++) {
					Vector<DireccionEntrega> direcciones = new Vector<DireccionEntrega>();
					LineaAlbaran l = lineas.elementAt(i);
					String query =
						" SELECT DISTINCT orders.idOrders, lineas.linNum as linea, lineas.idLin,"
							+ " dirs.ean, dirs.nombreCalle, dirs.idDireccion, dirs.localidad, locs.qty"
						+ " FROM fac_orders_lin lineas, fac_orders_loc locs, fac_orders orders, direccion dirs"
						+ " WHERE lineas.idOrders = locs.idOrders"
							+ " AND locs.idOrders = orders.idOrders"
							+ " AND locs.idLoc = dirs.idDireccion"
							+ " AND orders.idOrders = '" + l.getIdPedido() + "'"
							+ " AND lineas.linNum = locs.idLin"
							+ " AND lineas.linNum = '" + (i + 1) + "'"
							+ " AND locs.localizacionProcesada = 'N'"
						+ " ORDER BY linea";
					System.out.println(query);
					PreparedStatement pst = null;
					ResultSet res = null;
					pst = con.prepareStatement(query);
					res = pst.executeQuery();
					while (res.next()) {
						DireccionEntrega direccion = new DireccionEntrega();
						String calle = res.getString("nombreCalle");
						String localidad = res.getString("localidad");
						String texto = calle + ", " + localidad;
						/*if (texto.length() > 40)
							texto = calle.substring(0, 25) + "..., " + localidad;*/
						if (texto.length() > 40)
							//texto = calle.substring(0, 25) + "..., " + localidad;
							texto = texto.substring(0, 37) + "...";
						direccion.setCalle(texto);
						direccion.setIdDireccion(res.getString("idDireccion"));
						//direccion.setLocalidad(res.getString("localidad"));
						direccion.setNumeroBultos(res.getDouble("qty"));
						direcciones.add(direccion);
					}
					linea.setDirecciones(direcciones);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("getDireccionesOrder-FIN");
		return lineas;
	}

	//@Override
	public Boolean addOrder(Order pedido) throws Exception {
		System.out.println("OrdersDAO");
		try {
			con = bddConexion();
			Statement stmt;
			ps = null;
			rs = null;
			//idFormaPago es el idDatoBancario en referenciabancaria
			String insertString =
				" INSERT INTO fac_orders(unh,bgmTipo,bgmNum,bgmFunc,aliCond,ftxCali," +
					" nadMs,rffMs,nadMr,rffMr,nadSu,rffSu,nadBy,rffBy,nadDp,rffDp,nadIv,rffIv,nadPr," +
					" rffPr,cux,moa79,cnt,unt,observaciones, idFormaPago, fechaRegistro, responsable) " +
				" VALUES("
					+ "'"
					+ pedido.getUnh()
					+ "','"
					+ pedido.getBgmTipo()
					+ "','"
					+ pedido.getBgmNum()
					+ "','"
					+ pedido.getBgmFunc()
					+ "','"
					+ pedido.getAliCond()
					+ "','"
					+ pedido.getFtxCali()
					+ "','"
					+ pedido.getNadMs()
					+ "','"
					+ pedido.getRffMs()
					+ "','"
					+ pedido.getNadMr()
					+ "','"
					+ pedido.getRffMr()
					+ "','"
					+ pedido.getNadSu()
					+ "','"
					+ pedido.getRffSu()
					+ "','"
					+ pedido.getNadBy()
					+ "','"
					+ pedido.getRffBy()
					+ "','"
					+ pedido.getNadDp()
					+ "','"
					+ pedido.getRffDp()
					+ "','"
					+ pedido.getNadIv()
					+ "','"
					+ pedido.getRffIv()
					+ "','"
					+ pedido.getNadPr()
					+ "','"
					+ pedido.getRffPr()
					+ "','"
					+ pedido.getCux()
					+ "','"
					+ pedido.getMoa79()
					+ "','"
					+ pedido.getCnt()
					+ "','"
					+ pedido.getUnt()
					+ "','"
					+ pedido.getObservacionesPedido()
					+ "','"
					+ pedido.getIdFormaPago() + "'" + ", CURRENT_TIMESTAMP, '" + pedido.getResponsable() + "')";
			System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
			// obtengo el identificador
			ps = con.prepareStatement("SELECT max(idOrders) AS idOrders FROM fac_orders");
			rs = ps.executeQuery();
			if (rs.next()) {
				pedido.setIdOrders(rs.getLong("idOrders"));
				// idUsuarioMax= usuarioMaximo.getIdUsuario()+1;
			} else
				pedido.setIdOrders((long) 1);
			// insertamos los DTM (fechas)
			List lista = pedido.getDtm();
			Iterator iterador = lista.listIterator();
			Iterator iteradorInterno;
			DTM dtm;
			while (iterador.hasNext()) {
				dtm = (DTM) iterador.next();
				System.out.println("Insertamos una fecha");
				insertString =
					" INSERT INTO fac_orders_dtm (idOrders,dtmFech,dtmForm,dtmFunc) VALUES('"
						+ pedido.getIdOrders()
						+ "','"
						+ dtm.getDtmFech()
						+ "','"
						+ dtm.getDtmForm()
						+ "','"
						+ dtm.getDtmFunc()
						+ "')";
				System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString);
			}
			// insertamos las lineas de producto
			lista = pedido.getLin();
			List localizaciones;
			List descriptoresDeArticulo;
			iterador = null;
			iterador = lista.listIterator();
			OrdersLin lin;
			while (iterador.hasNext()) {
				lin = (OrdersLin) iterador.next();
				System.out.println("Insertamos un artículo");
				insertString =
				"INSERT INTO fac_orders_lin (idOrders,idLin,linNum,piaNumIN,piaNumSA," +
					"qty21Cant,kilos,qty59Cant,moa203,priAaa,priAab,priInf,observaciones) VALUES('"
						+ pedido.getIdOrders()
						+ "','"
						+ lin.getIdProducto()
						+ "','"
						+ lin.getLinNum()
						+ "','"
						+ lin.getPiaNumIn()
						+ "','"
						+ lin.getPiaNumSa()
						+ "','"
						+ lin.getQty21Cant()
						+ "','"
						+ lin.getPesoLinea()
						+ "','"
						+ lin.getQty59Cant()
						+ "','"
						+ lin.getMoa203()
						+ "','"
						+ lin.getPriAaa()
						+ "','"
						+ lin.getPriAab()
						+ "','"
						+ lin.getPriInf()
						+ "','"
						+ lin.getObservaciones()
						+ "')";
				System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString);
				// insertamos las descripciones
				descriptoresDeArticulo = lin.getImd();
				iteradorInterno = descriptoresDeArticulo.listIterator();
				IMD imd;
				while (iteradorInterno.hasNext()) {
					imd = (IMD) iteradorInterno.next();
					System.out.println("Insertamos una descripción para un artículo dado");
					String imdDesc = imd.getImdDesc();
					if (imdDesc.length() >= 50)
						imdDesc = imd.getImdDesc().substring(0, 50);
					insertString = "INSERT INTO fac_orders_imd (idOrders,idLin,imdTipo,imdCara,imdDesc,imdDescCod) " +
							" VALUES('"
							+ pedido.getIdOrders()
							+ "','"
							+ imd.getIdLinea()
							+ "','"
							+ imd.getImdTipo()
							+ "','"
							+ imd.getImdCara()
							+ "','"
							+ imdDesc
							+ "','"
							+ imd.getImdCodi() + "')";
					System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
				}
				// insertamos las localizaciones
				localizaciones = lin.getLoc();
				iteradorInterno = localizaciones.listIterator();
				OrdersLoc loc;
				while (iteradorInterno.hasNext()) {
					loc = (OrdersLoc) iteradorInterno.next();
					System.out
							.println("Insertamos una localización para un artículo dado");
					insertString = "INSERT INTO fac_orders_loc (idOrders,idLin,idLoc,qty,localizacionProcesada) VALUES('"
							+ pedido.getIdOrders()
							+ "','"
							+ loc.getIdLin()
							+ "','"
							+ loc.getLoc()
							+ "','"
							+ loc.getQty()
							+ "','N')";
					System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("OrdersDAO-FIN");
		return true;
	}

	//@Override
	public Vector<Order> orderLista(String codigo, boolean todasLocalizaciones) throws Exception {
		System.out.println("OrdersDAO: orderLista " + codigo);
		Vector<Order> resultado = new Vector<Order>();
		con= null;
		try {
			PreparedStatement ps2, ps3;
			ResultSet rs2, rs3;
			con = bddConexion();
			String Qry =
				"SELECT *, forma.descripcion as descripcionFormaPago " +
				" FROM fac_orders f, fac_orders_estados estados, referenciabancaria ref, formapago forma " +
				" WHERE f.bgmNum = '" + codigo + "' " +
					" AND f.estado = estados.idEstado " +
					//" AND f.bgmTipo = '220' " +
					" AND ref.idDatoBancario = f.idFormaPago" +
					" AND forma.idFormaPago = ref.idFormaPago";
			System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			if(rs.next()){
				Order pedido = new Order();
				pedido.setIdOrders(rs.getLong("idOrders"));
				pedido.setUnh(rs.getString("unh"));
				pedido.setBgmFunc(rs.getString("bgmFunc"));
				pedido.setBgmNum(rs.getString("bgmNum"));
				pedido.setBgmTipo(rs.getString("bgmTipo"));
				pedido.setAliCond(rs.getString("aliCond"));
				pedido.setFtxCali(rs.getString("ftxCali"));
				pedido.setNadMs(rs.getString("nadMs"));
				pedido.setRffMs(rs.getString("rffMs"));				
				pedido.setNadMr(rs.getString("nadMr"));
				pedido.setRffMr(rs.getString("rffMr"));
				pedido.setNadSu(rs.getString("nadSu"));
				pedido.setRffSu(rs.getString("rffSu"));
				pedido.setNadBy(rs.getString("nadBy"));
				pedido.setRffBy(rs.getString("rffBy"));
				pedido.setNadDp(rs.getString("nadDp"));
				pedido.setRffDp(rs.getString("rffDp"));
				pedido.setNadIv(rs.getString("nadIv"));
				pedido.setRffIv(rs.getString("rffIv"));
				pedido.setNadPr(rs.getString("nadPr"));
				pedido.setObservacionesPedido(rs.getString("observaciones"));
				pedido.setRffPr(rs.getString("rffPr"));
				pedido.setCux(rs.getString("cux"));
				pedido.setMoa79(rs.getString("moa79"));
				pedido.setCnt(rs.getString("cnt"));
				pedido.setUnt(rs.getString("unt"));	
				pedido.setIdFormaPago(rs.getLong("idFormaPago"));
				pedido.setEstado(rs.getString("estado").charAt(0));
				pedido.setDescripcionEstado(rs.getString("nombre"));
				String descripcionFormaPago = rs.getString("descripcionFormaPago") + " a " + rs.getLong("diasFormaPago") + " dias";
				String numeroCuenta = rs.getString("numCuenta");
				if (!numeroCuenta.equals("000000000000000000000000"))
					descripcionFormaPago += ". Numero cuenta: " + numeroCuenta;
				pedido.setDescripcionFormaPago(descripcionFormaPago);
				pedido.setIdFormaPago(rs.getLong("idFormaPago"));
				//System.out.println("Vamos a por la fecha");
				// Carga los tipos de fecha
				ps2 = con.prepareStatement("SELECT * FROM  fac_orders_dtm WHERE idOrders='" + pedido.getIdOrders() + "'");
				rs2 = ps2.executeQuery();
				while(rs2.next()){
					DTM dtm = new DTM();
					//dtm.setDtmFech(rs2.getString("dtmFech"));
					String dtmFech = rs2.getString("dtmFech");//20111026
					dtm.setDtmForm(rs2.getString("dtmForm"));
					if (dtm.getDtmForm().compareTo("102") == 0){
						//20111026
						int a = 0, m = 0, d = 0;
						a = Integer.parseInt(dtmFech.substring(2, 4));
						m = Integer.parseInt(dtmFech.substring(4, 6));
						d = Integer.parseInt(dtmFech.substring(6));
						String diaString = "" + d;
						String mesString = "" + m;
						if (diaString.length() == 1)
							diaString = "0" + diaString;
						if (mesString.length() == 1)
							mesString = "0" + mesString;
						String dtmFechFormateada = diaString + "/" + mesString + "/" + a;
						dtm.setDtmFech(dtmFechFormateada);
					}else
						dtm.setDtmFech(dtmFech);
					dtm.setDtmFunc(rs2.getString("dtmFunc"));
					pedido.setDtm(dtm);
				}
				//System.out.println("Vamos a por los productos");
				//Carga los artículos
				String consulta =
					"SELECT * " +
					" FROM fac_orders_lin fol, producto p " +
					" WHERE idOrders = '" + pedido.getIdOrders() + "' " +
						" AND fol.piaNumSA = p.idProducto " +
					" ORDER BY (linNum + 0)";
				System.out.println(consulta);
				ps2 = con.prepareStatement(consulta);
				rs2 = ps2.executeQuery();
				while(rs2.next()){
					OrdersLin lin = new OrdersLin();
					lin.setIdLin(rs2.getString("codigoEan"));
					lin.setIdProducto(rs2.getLong("idProducto"));
					lin.setLinNum(rs2.getString("linNum"));
					lin.setPiaNumSa(rs2.getString("piaNumSA"));
					lin.setObservaciones(rs2.getString("observaciones"));
					lin.setPiaNumIn(rs2.getString("piaNumIN"));
					//lin.setQty21Cant(rs2.getString("qty21Cant"));
					String qty59 = rs2.getString("qty59Cant");
					if (qty59.contains(".")){
						double aux = Double.parseDouble(qty59);
						int qtyAux = (int) aux;
						qty59 = String.valueOf(qtyAux);
					}
					lin.setQty59Cant(qty59);
					lin.setNombreProducto(rs2.getString("nombre"));
					lin.setPeso(rs2.getDouble("peso"));
					lin.setPesoLinea(rs2.getDouble("kilos"));
					/*lin.setNumeroAgrupaciones(Integer.parseInt(rs2.getString("qty21Cant"))
							/ Integer.parseInt(rs2.getString("qty59Cant")));*/
					//lin.setMoa203(rs2.getString("moa203"));
					lin.setPriAaa(rs2.getString("priAaa"));
					lin.setPriAab(rs2.getString("priAab"));
					lin.setPriInf(rs2.getString("PriInf"));
					lin.setLineaProcesada(rs2.getString("lineaProcesada"));
					// Carga las direcciones para la linea
					if (todasLocalizaciones)
						consulta =
							"SELECT nombreCalle, d.localidad, d.codigoPostal," +
								"f.idOrders, f.idLin, f.idLoc, f.qty, f.localizacionProcesada " +
							" FROM fac_orders_loc f, direccion d " +
							" WHERE idOrders='"
								+ pedido.getIdOrders() + "' AND f.idLoc = d.idDireccion " +
								" AND f.idLin = '" + lin.getLinNum() + "'";
					else
						consulta =
							"SELECT nombreCalle, d.localidad, d.codigoPostal," +
								"f.idOrders, f.idLin, f.idLoc, f.qty, f.localizacionProcesada " +
							" FROM fac_orders_loc f, direccion d " +
							" WHERE idOrders='"
								+ pedido.getIdOrders() + "' AND f.idLoc = d.idDireccion " +
								" AND f.idLin = '" + lin.getLinNum() + "' " +
								" AND f.localizacionProcesada = 'N' ";
					ps3 = con.prepareStatement(consulta);
					System.out.println(consulta);
					rs3 = ps3.executeQuery();
					List<OrdersLoc> dirs = new ArrayList<OrdersLoc>();
					int numeroAgrupaciones = 0;
					double qty21 = 0, moa203 = 0;
					qty21 = rs2.getDouble("qty21Cant");
					moa203 = rs2.getDouble("moa203");
					String qry2 =
						" SELECT pc.cantidad " +
						" FROM producto p, producto_compuesto pc " +
						" WHERE p.idProducto = pc.idProducto " +
							" AND p.idProducto = " + lin.getIdProducto();
					PreparedStatement psAux = con.prepareStatement(qry2);
					ResultSet rsAux = psAux.executeQuery();
					int unidades = 1;
					if (rsAux.next()) {
						unidades = rsAux.getInt("cantidad");
					}
					lin.setEANs13(unidades);
					while (rs3.next()) {
						OrdersLoc loc = new OrdersLoc();
						loc.setIdLin(rs3.getString("idLin"));
						loc.setIdOrders(rs3.getLong("idOrders"));
						loc.setLoc(rs3.getString("idLoc"));
						String qty = rs3.getString("qty");
						if (qty.contains(".")){
							double aux = Double.parseDouble(qty);
							int qtyAux = (int) aux;
							qty = String.valueOf(qtyAux);
						}else{
							if (qty.equals("null") || qty.equals(""))
								qty = "1";
						}
						loc.setQty(qty);
						//qty21 += Double.parseDouble(loc.getQty());
						numeroAgrupaciones += Integer.parseInt(loc.getQty());
						loc.setLocalizacionProcesada(rs3.getString("localizacionProcesada"));
						String calle = rs3.getString("nombreCalle");
						String localidad = rs3.getString("localidad");
						String texto = calle + ", " + localidad;
						if (texto.length() > 40)
							//texto = calle.substring(0, 25) + "..., " + localidad;
							texto = texto.substring(0, 37) + "...";
						loc.setDescripcion(texto);
						//loc.setDescripcion(rs3.getString("nombreCalle"));
						dirs.add(loc);
					}
					/*qty21 = numeroAgrupaciones * Integer.parseInt(lin.getQty59Cant());
					kilos = qty21 * lin.getPeso();
					moa203 = qty21 * Double.parseDouble(lin.getPriAaa());*/
					lin.setNumeroAgrupaciones(numeroAgrupaciones);
					lin.setQty21Cant("" + qty21);
					//lin.setKilos(kilos);
					lin.setMoa203("" + moa203);
					lin.setLoc(dirs);
					if (dirs.size() > 0){
						pedido.setLin(lin);
					}
				}
				resultado.add(pedido);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		System.out.println("OrdersDAO-FIN");
		return resultado;
	}
	
	/**
	 * Elimina un pedido: No borramos el pedido de la base de datos. Acualizamos su estado a E (Eliminado)
	 */
	//@Override
	public boolean eliminaOrder(String codigo) throws Exception{
		System.out.println("Eliminamos el pedido: " + codigo);
		boolean eliminado = false;
		/* Tablas afectadas: fac_orders */
		try {
			con = bddConexion();
			String Qry = "SELECT idOrders, estado FROM fac_orders WHERE bgmNum = '" + codigo + "'";
			System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			long id = 0;
			char estado = ' ';
			while (rs.next()) {
				id = rs.getLong("idOrders");
				estado = rs.getString("estado").charAt(0);
			}
			if (estado == 'R' || estado == 'N' || estado == 'A'){
				Statement stmt;
				String updateString = "UPDATE fac_orders SET estado='X' WHERE idOrders = '" + id + "'";
				System.out.println(updateString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				if (stmt.executeUpdate(updateString) == 1)
					eliminado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} 
		try {
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eliminado;
		
	}
	
	//@Override
	public boolean updateOrder(Order pedido) throws Exception {
		System.out.println("updateOrder");
		try {
			con = bddConexion();
			Statement stmt;
			ps = null;
			rs = null;
			//idFormaPago es el idDatoBancario en referenciabancaria
			String insertString = "UPDATE fac_orders " +
				" SET unh = '" + pedido.getUnh() + "'" +
					" ,bgmTipo = '" + pedido.getBgmTipo() + "'" +
					" ,bgmFunc = '" + pedido.getBgmFunc() + "'" +
					" ,aliCond = '" + pedido.getAliCond() + "'" +
					" ,ftxCali = '" + pedido.getFtxCali() + "'" +
					" ,nadMs = '" + pedido.getNadMs() + "'" +
					" ,rffMs = '" + pedido.getRffMs() + "'" +
					" ,nadMr = '" + pedido.getNadMr() + "'" +
					" ,rffMr = '" + pedido.getRffMr() + "'" +
					" ,nadSu = '" + pedido.getNadSu() + "'" +
					" ,rffSu = '" + pedido.getRffSu() + "'" +
					" ,nadBy = '" + pedido.getNadBy() + "'" +
					" ,rffBy = '" + pedido.getRffBy() + "'" +
					" ,nadDp = '" + pedido.getNadDp() + "'" +
					" ,rffDp = '" + pedido.getRffDp() + "'" +
					" ,nadIv = '" + pedido.getNadIv() + "'" +
					" ,rffIv = '" + pedido.getRffIv() + "'" +
					" ,nadPr = '" + pedido.getNadPr() + "'" +
					" ,rffPr = '" + pedido.getRffPr() + "'" +
					" ,cux = '" + pedido.getCux() + "'" +
					" ,moa79 = '" + pedido.getMoa79() + "'" +
					" ,cnt = '" + pedido.getCnt() + "'" +
					" ,unt = '" + pedido.getUnt() + "'" +
					" ,idFormaPago = '" + pedido.getIdFormaPago() + "'" +
					" ,observaciones = '" + pedido.getObservacionesPedido() + "'" +
					" WHERE bgmNum='" + pedido.getBgmNum() + "'";
			System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
			// obtengo el identificador
			ps = con.prepareStatement("SELECT idOrders FROM fac_orders WHERE bgmNum='" + pedido.getBgmNum() + "'");
			rs = ps.executeQuery();
			if (rs.next()) {
				pedido.setIdOrders(rs.getLong("idOrders"));
			}
			//Borramos la informacion actual del pedido...
			String qryBorrar = "DELETE FROM fac_orders_loc WHERE idOrders='" + pedido.getIdOrders() + "'";
			System.out.println(qryBorrar);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(qryBorrar);
			qryBorrar = "DELETE FROM fac_orders_lin WHERE idOrders='" + pedido.getIdOrders() + "'";
			System.out.println(qryBorrar);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(qryBorrar);
			qryBorrar = "DELETE FROM fac_orders_imd WHERE idOrders='" + pedido.getIdOrders() + "'";
			System.out.println(qryBorrar);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(qryBorrar);
			//... para luego insertarla
			// actualizamos los DTM (fechas)
			List lista = pedido.getDtm();
			Iterator iterador = lista.listIterator();
			Iterator iteradorInterno;
			DTM dtm;
			while (iterador.hasNext()) {
				dtm = (DTM) iterador.next();
				System.out.println("Insertamos una fecha");
				insertString = "UPDATE fac_orders_dtm SET dtmFech='" + dtm.getDtmFech() + "'" +
							" WHERE idOrders='" + pedido.getIdOrders() +
								"' AND dtmFunc='" + dtm.getDtmFunc() + "'";
				System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString);
			}
			// insertamos las lineas de producto
			lista = pedido.getLin();
			List localizaciones;
			List descriptoresDeArticulo;
			iterador = null;
			iterador = lista.listIterator();
			OrdersLin lin;
			while (iterador.hasNext()) {
				lin = (OrdersLin) iterador.next();
				System.out.println("Insertamos un artículo");
				insertString =
				"INSERT INTO fac_orders_lin (idOrders,idLin,linNum,piaNumIN,piaNumSA," +
					"qty21Cant,kilos,qty59Cant,moa203,priAaa,priAab,priInf,observaciones) VALUES('"
						+ pedido.getIdOrders()
						+ "','"
						+ lin.getIdProducto()
						+ "','"
						+ lin.getLinNum()
						+ "','"
						+ lin.getPiaNumIn()
						+ "','"
						+ lin.getPiaNumSa()
						+ "','"
						+ lin.getQty21Cant()
						+ "','"
						+ lin.getPesoLinea()
						+ "','"
						+ lin.getQty59Cant()
						+ "','"
						+ lin.getMoa203()
						+ "','"
						+ lin.getPriAaa()
						+ "','"
						+ lin.getPriAab()
						+ "','"
						+ lin.getPriInf()
						+ "','"
						+ lin.getObservaciones()
						+ "')";
				System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString);
				// insertamos las descripciones
				descriptoresDeArticulo = lin.getImd();
				iteradorInterno = descriptoresDeArticulo.listIterator();
				IMD imd;
				while (iteradorInterno.hasNext()) {
					imd = (IMD) iteradorInterno.next();
					System.out
							.println("Insertamos una descripción para un artículo dado");
					String imdDesc = imd.getImdDesc();
					if (imdDesc.length() >= 50)
						imdDesc = imd.getImdDesc().substring(0, 50);
					insertString = "INSERT INTO fac_orders_imd (idOrders,idLin,imdTipo,imdCara,imdDesc,imdDescCod) " +
							" VALUES('"
							+ pedido.getIdOrders()
							+ "','"
							+ imd.getIdLinea()
							+ "','"
							+ imd.getImdTipo()
							+ "','"
							+ imd.getImdCara()
							+ "','"
							+ imdDesc
							+ "','"
							+ imd.getImdCodi() + "')";
					System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
				}
				// insertamos las localizaciones
				localizaciones = lin.getLoc();
				iteradorInterno = localizaciones.listIterator();
				OrdersLoc loc;
				while (iteradorInterno.hasNext()) {
					loc = (OrdersLoc) iteradorInterno.next();
					System.out
							.println("Insertamos una localización para un artículo dado");
					insertString = "INSERT INTO fac_orders_loc (idOrders,idLin,idLoc,qty,localizacionProcesada) VALUES('"
							+ pedido.getIdOrders()
							+ "','"
							+ loc.getIdLin()
							+ "','"
							+ loc.getLoc()
							+ "','"
							+ loc.getQty()
							+ "','N')";
					System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("OrdersDAO-FIN");
		return true;
	}
	
	//@Override
	public Vector<Producto> getProdutosPedido(Order order) throws Exception{
		Vector<Producto> productos = new Vector<Producto>();
		String idPedido = order.getBgmNum();
		String Qry = "SELECT p.nombre, lin.linNum, lin.kilos " +
				" FROM fac_orders fac, fac_orders_lin lin, producto p " +
				" WHERE fac.bgmNum = '" + idPedido + "' " +
					" AND fac.idOrders = lin.idOrders " +
					" AND lin.idLin = p.idProducto " +
				" ORDER BY (linNum + 0)";
		System.out.println(Qry);
		try {
			con = bddConexion();
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Producto p = new Producto();
				p.setNombre(rs.getString("nombre"));
				p.setPeso(rs.getDouble("kilos"));
				productos.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return productos;
	}
	
	//@Override
	public Vector<EstadoPedido> getInformacionEstado(String estado) throws Exception{
		Vector<EstadoPedido> estados = new Vector<EstadoPedido>();
		String Qry = "SELECT e.* " +
				" FROM fac_orders_estados e " +
				" WHERE e.idEstado = '" + estado + "' ";
		System.out.println(Qry);
		try {
			con = bddConexion();
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				EstadoPedido e = new EstadoPedido();
				e.setNombre(rs.getString("nombre"));
				e.setDescripcion(rs.getString("descripcion"));
				e.setIdEstadoPedido(rs.getString("idEstado").charAt(0));
				estados.add(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return estados;
	}
}