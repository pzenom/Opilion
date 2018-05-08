package es.induserco.opilion.datos.producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import es.induserco.opilion.data.comun.EstadoProducto;
import es.induserco.opilion.data.comun.GrupoProducto;
import es.induserco.opilion.data.comun.TipoProducto;
import es.induserco.opilion.data.entidades.Categoria;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.data.entidades.Familia;
import es.induserco.opilion.data.entidades.Impuesto;
import es.induserco.opilion.data.entidades.MateriaPrima;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.ProductoMerma;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.datos.entrada.RegistroEntradaDAO;
import es.induserco.opilion.infraestructura.DatabaseConfig;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

/**
 * Producto DAO
 * @author Induserco, Andrés (29/04/2011)
 * @version 0.3
 */
public class ProductoDAO extends DatabaseConfig implements IProductoDataService {
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection con = null;

	//@Override
	public Vector<Producto> getProductos(Producto producto, boolean categorizacion) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Producto> resultado = new Vector<Producto>();
		try {
			con = bddConexion();
			String Qry =
				" SELECT pc.nombre as nombreGrupo,pc.idCategorizacion, p.peso, p.precio, " +
					" p.idImpuesto, p.foto, p.stock, p.familia_idFamilia, p.idProducto, " +
					" p.idCategoria,p.idEstado,p.idGrupo, TRIM(p.nombre) AS nombre,p.descripcion,p.stockMinimo, " +
					" p.codigoEan,p.precioCoste,p.stockAgrupado, p.mostrar, p.caducidad " +
				" FROM producto p " +
					" INNER JOIN producto_categorizacion pc ON pc.idCategorizacion = p.idGrupo " +
				" WHERE p.habilitado = 'S' ";
			if (producto != null){
				String Qry2 = " ";
				//filtros
				if((producto.getIdCategoria() != null) && (producto.getIdCategoria() != 0)){
					   Qry2 = Qry2 + " AND idCategoria = " + producto.getIdCategoria() + "";
				}
				if((producto.getIdEstado() != null) && (producto.getIdEstado() != 0)){
					   Qry2 = Qry2 + " AND idEstado = " + producto.getIdEstado() + "";
				}
				if((producto.getCodigoEan() != null) && (producto.getCodigoEan().compareTo("") != 0)){
					   Qry2 = Qry2 + " AND codigoEan = '" + producto.getCodigoEan() + "'";
				}
				if((producto.getIdProducto() != null) && (producto.getIdProducto() != 0)){
					   Qry2 = Qry2 + " AND idProducto = '" + producto.getIdProducto() + "'";
				}				
				if((producto.getIdGrupo() != null) && (producto.getIdGrupo() != 0)){
					   Qry2 = Qry2 + " AND idGrupo = " + producto.getIdGrupo() + "";
				}
				if((producto.getIdFamilia() != null) && (producto.getIdFamilia() != 0)){
					   Qry2 = Qry2 + " AND familia_idFamilia = " + producto.getIdFamilia() + "";
				}	
				Qry = Qry + Qry2;
			}
			if (categorizacion)
				Qry += " ORDER BY idCategorizacion, LOWER(TRIM(p.nombre)) ";
			else
				Qry += " ORDER BY LOWER(TRIM(p.nombre))";
			System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos  proveedor en el registro
				Producto prod = new Producto();
				prod.setIdProducto(rs.getLong("idProducto"));
				prod.setIdCategoria(rs.getLong("idCategoria"));
				prod.setIdEstado(rs.getLong("idEstado"));
				//prod.setIdGrupo(rs.getLong("idCategorizacion"));
				prod.setIdGrupo(rs.getLong("idGrupo"));
				prod.setNombre(rs.getString("nombre"));
				prod.setDescripcion(rs.getString("descripcion"));
				prod.setCodigoEan(rs.getString("codigoEan"));
				prod.setStockMinimo(rs.getDouble("stockMinimo"));
				prod.setStock(rs.getDouble("stock"));
				prod.setStockAgrupado(rs.getDouble("stockAgrupado"));
				prod.setIdFamilia(rs.getLong("familia_idFamilia"));
				prod.setFoto(rs.getString("foto"));
				prod.setCaducidad(rs.getInt("caducidad"));
				prod.setIdImpuesto((Long)rs.getLong("idImpuesto"));
				prod.setPrecioCoste(rs.getDouble("precioCoste"));
				prod.setPrecio(rs.getDouble("precio"));
				prod.setPeso(rs.getDouble("peso"));
				prod.setMostrar(rs.getInt("mostrar"));
				String qry2 =
					" SELECT pc.cantidad " +
					" FROM producto p " +
					" INNER JOIN producto_compuesto pc ON p.idProducto = pc.idProducto " +
						" WHERE p.idProducto = " + prod.getIdProducto();
				PreparedStatement ps2 = con.prepareStatement(qry2);
				ResultSet rs2 = ps2.executeQuery();
				int unidades = 1;
				if (rs2.next()) {
					unidades = rs2.getInt("cantidad");
				}
				prod.setEANs13(unidades);
				//La añadimos al Vector de resultado
				resultado.add(prod);
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
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Producto getProducto(long id) throws Exception {
		Producto prod = new Producto();
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM  producto WHERE idProducto = " + id;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos  proveedor en el registro
				prod.setIdProducto(rs.getLong("idProducto"));
				prod.setIdCategoria(rs.getLong("idCategoria"));
				prod.setIdEstado(rs.getLong("idEstado"));
				prod.setIdTipo(rs.getLong("idTipo"));
				prod.setIdGrupo(rs.getLong("idGrupo"));
				prod.setNombre(rs.getString("nombre"));
				prod.setDescripcion(rs.getString("descripcion"));
				prod.setCodigoEan(rs.getString("codigoEan"));
				prod.setStock(rs.getLong("stock"));
				prod.setStockAgrupado(rs.getLong("stockAgrupado"));
				prod.setStockMinimo(rs.getLong("stockMinimo"));
				prod.setIdFamilia(rs.getLong("familia_idFamilia"));
				prod.setFoto(rs.getString("foto"));
				prod.setCaducidad(rs.getInt("caducidad"));
				prod.setIdImpuesto((Long)rs.getLong("idImpuesto"));
				prod.setFecha(rs.getDate("fecha"));
				prod.setPrecio(rs.getDouble("precio"));
				prod.setPeso(rs.getDouble("peso"));
				prod.setMostrar(rs.getInt("mostrar"));
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
		return prod;
	}

	//@Override
	public Boolean addProducto(Producto producto, boolean idEspecifico, long id) throws Exception {
		// System.out.println("DAO addProducto");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement stmt;
		con = bddConexion();
		Boolean resultado = false;
		int res = 0;
		try {
			long idProductoMax = 0;
			//Si le especificamos el identificador, no hacemos calculos
			if (idEspecifico)
				idProductoMax = id;
			else{
				//obtener el nuevo Id
				ps = con.prepareStatement("SELECT MAX(idProducto) AS idMaxProducto FROM producto");
				rs = ps.executeQuery();
				Producto productoMaximo = new Producto();
				if(rs.next()){
					productoMaximo.setIdProducto(rs.getLong("idMaxProducto"));
					idProductoMax = productoMaximo.getIdProducto() + 1;
				}
				else
					idProductoMax = 1;
			}
			// System.out.println("El id de producto es... " + idProductoMax);
			NumberFormat nf = new DecimalFormat("0000000000000");
			String EAN = "";
			if (producto.getCodigoEan().compareTo("") == 0){
				String EANAux = "" + idProductoMax;
				//EAN = "0000000000000";
				EAN = nf.format(Long.parseLong(EANAux));
			}else
				EAN = nf.format(Long.parseLong(producto.getCodigoEan()));
			//SQL de insersion
			String insertString = 
				"INSERT INTO producto(idProducto,idCategoria,idEstado,codigoEan,fecha," +
					"nombre,descripcion,stockMinimo,idGrupo,idMateriaPrima,familia_idFamilia," +
					"idImpuesto,stock,idBolsa,idSaco,idCarton,idSaquete,idMadera,idOtro,precio, peso, mostrar, caducidad) VALUES(" +
				idProductoMax + ","
				+ producto.getIdCategoria() + ","
				+ producto.getIdEstado() + ",'"	
				+ EAN + "'," 
				+ " CURDATE(),'"
				+ producto.getNombre() + "','"	
				+ producto.getDescripcion() + "',"
				+ producto.getStockMinimo() + ","
				+ producto.getIdGrupo() + ","
				+ producto.getIdMateriaPrima() + ","
				+ producto.getIdFamilia() + ","
				+ producto.getIdImpuesto() + ","
				+ producto.getStock() + ","
				+ producto.getIdBolsa() + ","
				+ producto.getIdSaco() + ","
				+ producto.getIdCarton() + ","
				+ producto.getIdSaquete() + ","
				+ producto.getIdMadera() + ","
				+ producto.getIdOtro() + ","
				+ producto.getPrecio() + ","
				+ producto.getPeso() + ", "
				+ producto.getMostrar() + ", "
				+ producto.getCaducidad() + ")";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(res == 1){
				// System.out.println("REGISTRO INSERTADO");
				resultado = true;
			}
			/*
			//Si insertamos un producto con el id específico, es que lo estamos actualizando
			if (idEspecifico){
				//Actualizamos el histórico del producto
			}*/
			return resultado;	
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public boolean setMateriasPrimasProducto(long idProducto, Vector<double[]> materias) throws Exception{
		boolean resultado = false;
		try{
			int res = 0;
			Statement stmt;
			con = bddConexion();
			int materia;
			double cantidad;
			for (int i = 0; i < materias.size() ; i++){
				//Cada posición  vector recibido es una dupla: idMateria, cantidadMateria
				//Object objeto = materias.elementAt(i);
				double[] dupla = materias.elementAt(i);
				materia = (int)dupla[0];
				cantidad = dupla[1];
				String insertString = 
					" INSERT INTO producto_materiaprima (idProducto, idMateriaPrima, cantidad) " +
					" VALUES (" + idProducto + ", " + materia + ", " + cantidad +")";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				if (res == 1)
					resultado = true;
				//Insertamos tambien en el histórico del producto
				insertString = 
					" INSERT INTO producto_materiaprima_historico (idProducto, idMateriaPrima, cantidad) " +
					" VALUES (" + idProducto + ", " + materia + ", " + cantidad +")";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				if (res == 1)
					resultado = true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}
	
	//@Override
	public boolean setEANs13Producto(long idProducto, Vector<double[]> EANs13) throws Exception{
		boolean resultado = false;
		try{
			int res = 0;
			Statement stmt;
			con = bddConexion();
			int materia;
			double cantidad;
			for (int i = 0; i < EANs13.size() ; i++){
				//Cada posición  vector recibido es una dupla: idMateria, cantidadMateria
				//Object objeto = materias.elementAt(i);
				double[] dupla = EANs13.elementAt(i);
				materia = (int)dupla[0];
				cantidad = dupla[1];
				String insertString = 
					" INSERT INTO producto_ean13 (idProducto, idEAN13, cantidad) " +
					" VALUES (" + idProducto + ", " + materia + ", " + cantidad + "); ";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				if (res == 1)
					resultado = true;
				//Insertamos tambien en el histórico del producto
				insertString = 
					" INSERT INTO producto_ean13_historico (idProducto, idEAN13, cantidad) " +
					" VALUES (" + idProducto + ", " + materia + ", " + cantidad +"); ";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				if (res == 1)
					resultado = true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public long getIdProductoLote(String lote) throws Exception{
		// System.out.println("DAO getIdProductoLote");
		Connection con = null;
		long idProducto = -1;
		try {
			con = bddConexion();
			ps = con.prepareStatement(" SELECT idProducto FROM gp_envasado WHERE lote = '" + lote + "'; ");
			rs = ps.executeQuery();
			if (rs.next()){
				idProducto =  rs.getLong("idProducto");
			}else{
				//No es el lote de un envasado. Miramos si era un registro de entrada
				ps = con.prepareStatement(" SELECT idProducto FROM registroentrada WHERE codigoEntrada = '" + lote + "'; ");
				rs = ps.executeQuery();
				if (rs.next()){
					idProducto =  rs.getLong("idProducto");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return idProducto;
	}
	
	//@Override
	public boolean setProductoCompuesto(long idProducto, Vector<double[]> componentes) throws Exception{
		boolean resultado = false;
		try{
			int res = 0;
			Statement stmt;
			con = bddConexion();
			int compuesto;
			double cantidad;
			for (int i = 0; i < componentes.size() ; i++){
				//Cada posición  vector recibido es una dupla: idMateria, cantidadMateria
				//Object objeto = materias.elementAt(i);
				double[] dupla = componentes.elementAt(i);
				compuesto = (int)dupla[0];
				cantidad = dupla[1];
				String insertString = 
					"INSERT INTO producto_compuesto (idProducto,idCompuestoDe,cantidad) " +
					"VALUES (" + idProducto + ", " + compuesto + ", " + cantidad + "); ";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				if (res == 1)
					resultado = true;
				//Insertamos tambien en el histórico del producto
				insertString = 
					"INSERT INTO producto_compuesto_historico (idProducto, idCompuestoDe, cantidad) " +
					"VALUES (" + idProducto + ", " + compuesto + ", " + cantidad + "); ";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				if (res == 1)
					resultado = true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public boolean setEnvasesProducto(long idProducto, Vector<double[]> envases) throws Exception{
		boolean resultado = false;
		try{
			int res = 0;
			Statement stmt;
			con = bddConexion();
			int envase;
			double cantidad;
			for (int i = 0; i < envases.size() ; i++){
				//Cada posición  vector recibido es una dupla: idMateria, cantidadMateria
				double[] dupla = envases.elementAt(i);
				envase = (int)dupla[0];
				cantidad = dupla[1];
				String insertString = 
					" INSERT INTO producto_envase (idProducto,idEnvase,cantidad) " +
					" VALUES (" + idProducto + ", " + envase + ", " + cantidad + "); ";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				if (res == 1)
					resultado = true;
				//Insertamos tambien en el histórico del producto
				insertString = 
					" INSERT INTO producto_envase_historico (idProducto, idEnvase, cantidad) " +
					" VALUES (" + idProducto + ", " + envase + ", " + cantidad + "); ";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				if (res == 1)
					resultado = true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public long addProductoGetId(Producto producto) throws Exception {
		// System.out.println("DAO addProducto");
		Statement stmt;
		Connection con = null;
		long resultado = -1;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT MAX(idProducto) AS idMaxProducto FROM producto");
			rs = ps.executeQuery();
			long idProductoMax =0 ;
			Producto productoMaximo = new Producto();
			if(rs.next()){
				productoMaximo.setIdProducto(rs.getLong("idMaxProducto"));
				idProductoMax = productoMaximo.getIdProducto()+1;
			}
			else 
				idProductoMax = 1;
			// System.out.println("el id de producto es... " + idProductoMax);
			NumberFormat nf = new DecimalFormat("0000000000000");
			String EAN = "";
			if (producto.getCodigoEan().compareTo("") == 0){
				String EANAux = "" + idProductoMax;
				//EAN = "0000000000000";
				EAN = nf.format(Long.parseLong(EANAux));
			}else
				EAN = nf.format(Long.parseLong(producto.getCodigoEan()));
			//SQL de insersion
			String insertString= 
				"INSERT INTO producto(idProducto,peso,precio,idCategoria,idEstado,codigoEan," +
					"fecha,nombre,descripcion,stockMinimo,idGrupo,idMateriaPrima,familia_idFamilia," +
					"idImpuesto,idBolsa,idSaco,idCarton,idSaquete,idMadera,idOtro, mostrar, caducidad) " +
					" VALUES(" +
				idProductoMax + ","
				+ producto.getPeso() + ","
				+ producto.getPrecio() + ","
				+ producto.getIdCategoria() + ","
				+ producto.getIdEstado() + ",'"	
				+ EAN + "'," 
				+ " CURDATE(),'"
				+ producto.getNombre() + "','"	
				+ producto.getDescripcion() + "',"
				+ producto.getStockMinimo() + ","
				+ producto.getIdGrupo() + ","
				+ producto.getIdMateriaPrima() + ","
				+ producto.getIdFamilia() + ","
				+ producto.getIdImpuesto() + ","
				+ producto.getIdBolsa() + ","
				+ producto.getIdSaco() + ","
				+ producto.getIdCarton() + ","
				+ producto.getIdSaquete() + ","
				+ producto.getIdMadera() + ","
				+ producto.getIdOtro() + ", "
				+ producto.getMostrar() + ", "
				+ producto.getCaducidad() + ")";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(res == 1){
				// System.out.println("REGISTRO INSERTADO");
				resultado = idProductoMax;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public Vector<Categoria> getCategorias() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Categoria> resultado = new Vector<Categoria>();
		try {
			con = bddConexion();
			String Qry="SELECT * FROM categoria";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos  proveedor en el registro
				Categoria cat = new Categoria();
				cat.setIdCategoria(rs.getLong("idCategoria"));
				cat.setNombreCategoria(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(cat);
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
		//Retornamos el vector de resultado.
		return resultado;
	}	

	public boolean updateMateriaPrima(MateriaPrima materiaFind, MateriaPrima materiaUpdate) throws Exception {
		// System.out.println("DAO updateMP");
		PreparedStatement ps = null;
		Statement stmt;
		Connection con = null;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			//SQL de insersion
			String updateString= 
				" UPDATE materiaprima SET " +
				" idGrupo = " + materiaUpdate.getIdGrupo() + ", " +
				" nombre = '" + materiaUpdate.getNombre() + "' " +
				" WHERE idProducto =" + materiaFind.getIdMateriaPrima();
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if(res == 1){
				// System.out.println("MP ACTUALIZADA");
				resultado = true;
			}
			String Qry =
				" SELECT idCategoria, idMateriaCategoria " +
				" FROM materiaprima_categoria " +
				" WHERE idMateriaPrima = " + materiaUpdate.getIdMateriaPrima();
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()){
				long idCategoria = rs.getLong("idCategoria");
				boolean incluida = false;
				ArrayList<Long> categorias = materiaUpdate.getCategorias();
				for (int i = 0; i < categorias.size(); i++){
					if (categorias.get(i) == idCategoria){
						incluida = true;
						break;
					}
				}
				if (!incluida){
					String borra = 
						" UPDATE materiaprima_categoria " +
						" SET habilitado = 'N' " +
						" WHERE idMateriaCategoria = " + rs.getLong("idMateriaCategoria");
					// System.out.println(borra);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(borra);
				}
			}
			//AHORA
			//Para cada una de las categorias asociadas a la materia prima que aun no esten asociadas,
				//creamos una nueva fila en materiaprima_categoria
			ArrayList<Long> categorias = materiaUpdate.getCategorias();
			for (int i = 0; i < categorias.size(); i++){
				long cati = categorias.get(i);
				con = bddConexion();
				Qry =
					" SELECT * " +
					" FROM materiaprima_categoria " +
					" WHERE idMateriaPrima = '" + materiaUpdate.getIdMateriaPrima() + "' " +
						" AND idCategoria = " + cati;
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				boolean existe = false;
				String habilitado = "-";
				while (rs.next()) {
					existe = true;
					habilitado = rs.getString("habilitado");
				}
				String insert = "";
				boolean inserta = false;
				if (!existe){
					inserta = true;
					insert = 
						" INSERT INTO materiaprima_categoria(idMateriaPrima, idCategoria, stock) " +
						" VALUES('" + materiaUpdate.getIdMateriaPrima() + "','" + cati + "', 0); ";
				} else
					if (existe && habilitado.compareToIgnoreCase("N") == 0){
						inserta = true;
						insert = 
							" UPDATE materiaprima_categoria " +
							" SET habilitado = 'S' " +
							" WHERE idMateriaPrima = " + materiaUpdate.getIdMateriaPrima() + " " +
								" AND idCategoria = " + cati;
					}
				if (inserta){
					// System.out.println(insert);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					res = stmt.executeUpdate(insert);
					if(res == 1){
						// System.out.println("REGISTRO INSERTADO");
					}
				}
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public Vector<Producto> loadProducto(Producto entry) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Producto> resultado = new Vector<Producto>();
		// System.out.println("DAO loadProducto");
		try {
			con = bddConexion();
			String Qry = " SELECT * FROM producto WHERE idProducto = " + entry.getIdProducto();
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// System.out.println("devuelve la producto correspondiente");
				//Completamos los datos  Libro en la producto
				Producto productoConsulta = new Producto();
				productoConsulta.setIdProducto(rs.getLong("idProducto"));
				productoConsulta.setIdCategoria(rs.getLong("idCategoria"));
				productoConsulta.setIdEstado(rs.getLong("idEstado"));
				productoConsulta.setCodigoEan(rs.getString("codigoEan"));
				productoConsulta.setIdTipo(rs.getLong("idTipo"));
				productoConsulta.setIdGrupo(rs.getLong("idGrupo"));
				productoConsulta.setIdBolsa(rs.getLong("idBolsa"));
				productoConsulta.setIdSaco(rs.getLong("idSaco"));
				productoConsulta.setIdCarton(rs.getLong("idCarton"));
				productoConsulta.setIdSaquete(rs.getLong("idSaquete"));
				productoConsulta.setIdMadera(rs.getLong("idMadera"));
				productoConsulta.setIdOtro(rs.getLong("idOtro"));
				productoConsulta.setIdMateriaPrima(rs.getLong("idMateriaPrima"));
				productoConsulta.setFecha(rs.getDate("fecha"));
				productoConsulta.setNombre(rs.getString("nombre"));
				productoConsulta.setDescripcion(rs.getString("descripcion"));
				productoConsulta.setStock(rs.getLong("stock"));
				productoConsulta.setStockAgrupado(rs.getLong("stockAgrupado"));
				productoConsulta.setStockMinimo(rs.getLong("stockMinimo"));
				productoConsulta.setIdFamilia(rs.getLong("familia_idFamilia"));
				productoConsulta.setPrecio(rs.getDouble("precio"));
				productoConsulta.setPeso(rs.getDouble("peso"));
				productoConsulta.setCaducidad(rs.getInt("caducidad"));
				//La añadimos al Vector de resultado
				resultado.add(productoConsulta);
				//falta los otros sets
				// System.out.println("DAO nombre  producto a consultar: " + productoConsulta.getNombre());
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
		//Retornamos el vector de resultado.
		return resultado;
	}

	public Vector<MateriaPrima> loadMateriaPrima(MateriaPrima entry) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<MateriaPrima> resultado = new Vector<MateriaPrima>();
		// System.out.println("DAO loadProducto");
		try {
			con = bddConexion();
			String Qry = " SELECT * FROM materiaprima WHERE idProducto = " + entry.getIdMateriaPrima();
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// System.out.println("devuelve el producto correspondiente");
				//Completamos los datos  Libro en la producto
				MateriaPrima materia = new MateriaPrima();
				materia.setIdMateriaPrima(rs.getLong("idProducto"));
				materia.setIdGrupo(rs.getLong("idGrupo"));
				materia.setNombre(rs.getString("nombre"));
				//materia.setIdEstado(rs.getLong("idEstado"));
				//La añadimos al Vector de resultado
				resultado.add(materia);
				//falta los otros sets
				// System.out.println("DAO nombre  producto a consultar: "+ materia.getNombre());
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
		//Retornamos el vector de resultado.
		return resultado;
	}

	public Vector<Impuesto> getImpuestos() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Impuesto> resultado = new Vector<Impuesto>();
		try {
			con = bddConexion();
			String Qry = " SELECT * FROM impuestos; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos  proveedor en el registro
				Impuesto impuesto = new Impuesto();
				impuesto.setIdImpuesto((Long)rs.getLong("idImpuesto"));
				impuesto.setNombreImpuesto(rs.getString("nombre"));
				impuesto.setPorcentaje(rs.getDouble("porcentaje"));
				//La añadimos al Vector de resultado
				resultado.add(impuesto);
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
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Familia> getFamilias() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Familia> resultado = new Vector<Familia>();
		try {
			con = bddConexion();
			String Qry="SELECT * FROM familia";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos  proveedor en el registro
				Familia familia = new Familia();
				familia.setIdFamilia(rs.getLong("idFamilia"));
				familia.setDescripcionFamilia(rs.getString("descripcion"));
				familia.setStock(rs.getLong("stock"));
				familia.setStockMinimo(rs.getLong("stockMinimo"));
				
				//La añadimos al Vector de resultado
				resultado.add(familia);
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
		//Retornamos el vector de resultado.
		return resultado;
	}

	public Vector<EstadoProducto> getEstados() throws Exception{
		//Inicializamos el Vector de retorno.
		Vector<EstadoProducto> resultado = new Vector<EstadoProducto>();
		try {
			con = bddConexion();
			String Qry="SELECT * FROM estadoproducto";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos  proveedor en el registro
				EstadoProducto estado = new EstadoProducto();
				estado.setIdEstado(rs.getLong("idEstado"));
				estado.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(estado);
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
		//Retornamos el vector de resultado.
		return resultado;
	}

	public Vector<GrupoProducto> getGrupoProducto() throws Exception{
		//Inicializamos el Vector de retorno.
		Vector<GrupoProducto> resultado = new Vector<GrupoProducto>();
		try {
			con = bddConexion();
			//String Qry="SELECT * FROM  grupo_producto";
			String Qry="SELECT * FROM  producto_categorizacion WHERE habilitado='S' ORDER BY nombre";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos  proveedor en el registro
				GrupoProducto grupoProd = new GrupoProducto();
				grupoProd.setIdGrupo(rs.getLong("idCategorizacion"));
				grupoProd.setIdPadre(rs.getLong("idPadre"));
				//grupoProd.setIdGrupo(rs.getLong("idGrupoProducto"));
				grupoProd.setNombreGrupo(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(grupoProd);
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
		//Retornamos el vector de resultado.
		return resultado;
	}

	public Vector<TipoProducto> getTipoProducto() throws Exception{
		//Inicializamos el Vector de retorno.
		Vector<TipoProducto> resultado = new Vector<TipoProducto>();
		try {
			con = bddConexion();
			String Qry="SELECT * FROM tipo_producto";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos  proveedor en el registro
				TipoProducto tipoProducto = new TipoProducto();
				tipoProducto.setIdTipo(rs.getLong("idTipoProducto"));
				tipoProducto.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(tipoProducto);
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
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public String getFechaRegistro() throws Exception {
	   String mes = ""; 
	   Calendar fecha = java.util.Calendar.getInstance();
	   switch(fecha.get(java.util.Calendar.MONTH)){
		   case 0: mes = "01"; break;
		   case 1: mes = "02"; break;
		   case 2: mes = "03"; break;
		   case 3: mes = "04"; break;
		   case 4: mes = "05"; break;
		   case 5: mes = "06"; break;
		   case 6: mes = "07"; break;
		   case 7: mes = "08"; break;
		   case 8: mes = "09"; break;
		   case 9: mes = "10"; break;
		   case 10: mes = "11"; break;
		   case 11: mes = "12"; break;
	   }
	   String fecharetorno = fecha.get(java.util.Calendar.DATE) + "/"
	   		+ mes + "/"
		    + fecha.get(java.util.Calendar.YEAR);
	   return fecharetorno;
	}

	//@Override
	public Boolean addProductoMerma(ProductoMerma entry) throws Exception {
		// System.out.println("DAO addMermaProducto");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement stmt;
		String strFechaRegistro = null;
		Connection con = null;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idMerma) as idMaxProducto FROM producto_merma");
			rs = ps.executeQuery();
			long idProductoMax =0 ;
			Producto productoMaximo = new Producto();
			if(rs.next()){
				productoMaximo.setIdProducto(rs.getLong("idMaxProducto"));
				idProductoMax= productoMaximo.getIdProducto()+1;
			}
			else 
				idProductoMax=1;
			// System.out.println("el id de producto es... "+ idProductoMax);
			strFechaRegistro= getDate(entry.getFechaRegistro());
			//SQL de insersion
			String insertString= 
				"insert into producto_merma(idMerma,idProducto,idUbicacion,fecha,cantidad,responsable,motivo) values(" +
				  idProductoMax + ","
				+ entry.getIdProducto()+ ","
				+ entry.getIdUbicacion() + ",'"
				+ strFechaRegistro + "',"
				+ entry.getCantidad() + ",'"	
				+ entry.getResponsable() + "','"	
				+ entry.getMotivo()+"')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(res==1){
				// System.out.println("REGISTRO INSERTADO");
				resultado=true;
			}
			return resultado;			
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public Boolean deshabilitaProducto(Producto producto) throws Exception {
		// System.out.println("DAO deshabilitar producto");
		Statement stmt;
		con = bddConexion();
		Boolean resultado = false;
		int res = 0;
		try {
			//SQL de insersion
			String updateString = 
				" UPDATE producto SET habilitado = 'N' " +
				" WHERE idProducto = " + producto.getIdProducto();
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if(res == 1){
				// System.out.println("Producto DESHABILITADO");
				resultado=true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	/**
	 * @author Induserco, Andrés (13/04/2011)
	 * @param idGrupo Identificador del grupo sobre el que vamos a filtrar
	 * @return Vector Las materias primas
	 * @throws Exception
	 * @since 0.2
	 */
	//@Override
	public Vector<MateriaPrima> getMateriasPrimas(long idGrupo) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<MateriaPrima> resultado = new Vector<MateriaPrima>();
		try {
			updateStockMateriasPrimas();
			con = bddConexion();
			/*String Qry = "SELECT * FROM  materiaprima m";
			if (idGrupo != 0)
				Qry += " WHERE m.idGrupo = '" + idGrupo + "'";*/
			String Qry =
				" SELECT mc.idMateriaCategoria, m.idProducto, m.nombre, c.nombre as nombreCategoria, mc.stock, c.idCategoria " +
				" FROM materiaprima m, materiaprima_categoria mc, categoria c " +
				" WHERE " +
					" m.idProducto = mc.idMateriaPrima " +
					" AND mc.idCategoria = c.idCategoria " +
					" AND mc.habilitado = 'S' ";
			if (idGrupo != 0)
				Qry += " AND m.idGrupo = '" + idGrupo + "'";
			Qry += " ORDER BY nombre, nombreCategoria, idProducto; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos  proveedor en el registro
				MateriaPrima mp = new MateriaPrima();
				mp.setIdMateriaPrima(rs.getLong("idProducto"));
				mp.setNombre(rs.getString("nombre"));
				//mp.setStock(rs.getDouble("stock"));
				mp.setCantidad(rs.getDouble("stock"));
				mp.setNombreCategoria(rs.getString("nombreCategoria"));
				mp.setIdCategoria(rs.getLong("idCategoria"));
				mp.setIdMateriaCategoria(rs.getLong("idMateriaCategoria"));
				//La añadimos al Vector de resultado
				resultado.add(mp);
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
		//Retornamos el vector de resultado.
		return resultado;
	}
	
	public Vector<MateriaPrima> getMateriasPrimasBasicas(long idGrupo) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<MateriaPrima> resultado = new Vector<MateriaPrima>();
		try {
			updateStockMateriasPrimas();
			con = bddConexion();
			String Qry = "SELECT m.idProducto, m.nombre, m.stock FROM materiaprima m";
			if (idGrupo != 0)
				Qry += " WHERE m.idGrupo = '" + idGrupo + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos  proveedor en el registro
				MateriaPrima mp = new MateriaPrima();
				mp.setIdMateriaPrima(rs.getLong("idProducto"));
				mp.setNombre(rs.getString("nombre"));
				mp.setStock(rs.getDouble("stock"));
				//mp.setCantidad(rs.getDouble("stock"));
				//mp.setNombreCategoria(rs.getString("nombreCategoria"));
				//mp.setIdCategoria(rs.getLong("idCategoria"));
				//La añadimos al Vector de resultado
				resultado.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }}
		//Retornamos el vector de resultado.
		return resultado;
	}
	
	/**
	 * @author Induserco, Andrés (11/05/2011)
	 * @param idGrupo Identificador de la materia prima sobre la que vamos a filtrar
	 * @return Vector Las materias primas
	 * @throws Exception
	 * @since 0.2
	 */
	//@Override
	public Vector<MateriaPrima> getMateriasPrimasCategorias(long idMateriaPrima) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<MateriaPrima> resultado = new Vector<MateriaPrima>();
		try {
			updateStockMateriasPrimas();
			con = bddConexion();
			String Qry = "SELECT m.nombre as nombreMateria, cate.nombre as nombreCate, mc.stock, cate.idCategoria " +
					" FROM materiaprima m, materiaprima_categoria mc, categoria cate " +
				" WHERE mc.habilitado='S' AND m.idProducto = mc.idMateriaPrima " +
				" AND mc.idCategoria = cate.idCategoria " +
				//" AND mc.stock > 0" +
				" AND m.idProducto = " + idMateriaPrima;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos de las materias primas categorizadas
				MateriaPrima mp = new MateriaPrima();
				mp.setNombreCategoria(rs.getString("nombreCate"));
				mp.setIdMateriaPrima(idMateriaPrima);
				mp.setNombre(rs.getString("nombreMateria"));
				mp.setStock(rs.getDouble("stock"));
				mp.setIdCategoria(rs.getLong("idCategoria"));
				//La añadimos al Vector de resultado
				resultado.add(mp);
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
		//Retornamos el vector de resultado.
		return resultado;
	}
	
	/**
	 * Actualiza el stock de todas las materias primas, a partir de los stocks registrados en la tabla registroentrada
	 * @author Induserco, Andrés (12/04/2011)
	 * @throws Exception Si falla la actualización
	 * @since 0.2
	 */
	//@Override
	public void updateStockMateriasPrimas() throws Exception {
		// System.out.println("Actualiza el stock de las materias primas");
		PreparedStatement prepared = null;
		ResultSet result = null, result2 = null;
		Statement s;
		long idMateriaPrima = 0, idCategoria = 0;
		double stock = 0;
		try {
			con = bddConexion();
			//0. Todos los stocs de materiaprima_categoria a cero
			String updateString = 
				" UPDATE materiaprima_categoria SET stock = 0 ";
			// System.out.println(updateString);
			s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			s.executeUpdate(updateString);
			//1. Leemos el id de todas las materias
			String Qry = "SELECT idProducto FROM materiaprima";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			//Mientras haya materias primas...
			while (rs.next()) {
				//... buscamos registros de entrada para cada envase, y actualizamos su stock total en la tabla envase
				idMateriaPrima = rs.getLong("idProducto");
				stock = 0;
				Qry =
					" SELECT SUM(saldo) AS saldo " +
					" FROM registroentrada re " +
					" WHERE re.habilitado = 'S' " +
						" AND (idTipoRegistro = 'M' OR idTipoRegistro = 'S') " +
						" AND idProducto = " + idMateriaPrima;
				// System.out.println(Qry);
				prepared = con.prepareStatement(Qry);
				result = prepared.executeQuery();
				//Mientras haya registros de entrada para el envase...
				if (result.next()) {
					//... actualizamos el stock
					stock = result.getDouble("saldo");
					// System.out.println(" ACTUALIZAMOS STOCK EN LA TABLA materiaprima ");
					//1. Update materiaprima
					updateString = 
						"UPDATE materiaprima SET stock = " + stock + " WHERE idProducto = " + idMateriaPrima;
					// System.out.println(updateString);
					s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					s.executeUpdate(updateString);
				}
			}
			//2. Ahora hay que hacer un update a materiaprima_categoria
			// System.out.println("vamos a actualizar el STOCK EN LA TABLA materiaprima_categoria");
			Qry = "SELECT idProducto FROM materiaprima";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()){
				//Para cada materia prima, miramos las categorias que tiene asociadas
				idMateriaPrima = rs.getLong("idProducto");
				stock = 0;
				Qry = "SELECT idCategoria FROM materiaprima_categoria mc WHERE mc.idMateriaPrima = " + idMateriaPrima;
				// System.out.println(Qry);
				prepared = con.prepareStatement(Qry);
				result = prepared.executeQuery();
				while (result.next()) {
					stock = 0;
					idCategoria = result.getLong("idCategoria");
					Qry =
						" SELECT SUM(saldo) AS saldo " +
						" FROM registroentrada re " +
						" WHERE (idTipoRegistro = 'M' OR idTipoRegistro = 'S') " +
							" AND re.habilitado = 'S' " +
							" AND idProducto = " + idMateriaPrima +
							" AND idCategoria = " + idCategoria;
					// System.out.println(Qry);
					prepared = con.prepareStatement(Qry);
					result2 = prepared.executeQuery();
					//Mientras haya registros de entrada para la materia prima categorizada...
					if (result2.next()) {
						stock = result2.getDouble("saldo");
						// System.out.println("ACTUALIZAMOS STOCK EN LA TABLA materiaprima_categoria");
						//1. Update materiaprima
						updateString = 
							"UPDATE materiaprima_categoria SET stock = " + stock + " WHERE idMateriaPrima = " + idMateriaPrima +
								" AND idCategoria = " + idCategoria;
						// System.out.println(updateString);
						s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						s.executeUpdate(updateString);
					}
				}
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
	}
	
	//@Override
	public void updateStockProductos() throws Exception {
		//El stock viene o de envasados, o de registro de entrada de tipo P
		//Falta el stock que viene de los envasados!! Cuidado si es un EAN13 o un EAN14
		// System.out.println("Actualiza el stock de los productos");
		PreparedStatement prepared = null;
		ResultSet result = null;
		Statement s;
		long idProducto = 0;
		double stock = 0;
		try {
			con = bddConexion();
			//0. Todos los stocs de materiaprima_categoria a cero
			String updateString = "UPDATE producto SET stock = 0";
			// System.out.println(updateString);
			s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			s.executeUpdate(updateString);
			//1. Leemos el id de todas las materias
			String Qry = "SELECT idProducto FROM producto";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			//Mientras haya materias primas...
			while (rs.next()) {
				//... buscamos registros de entrada para cada producto, y actualizamos su stock total en la tabla envase
				idProducto = rs.getLong("idProducto");
				stock = 0;
				Qry =
					" SELECT SUM(saldo) AS saldo " +
					" FROM registroentrada re " +
					" WHERE re.habilitado = 'S' " +
						" AND idTipoRegistro = 'P' " +
						" AND idProducto = " + idProducto;
				// System.out.println(Qry);
				prepared = con.prepareStatement(Qry);
				result = prepared.executeQuery();
				//Mientras haya registros de entrada para el envase...
				if (result.next()) {
					//... actualizamos el stock
					stock = result.getDouble("saldo");
					// System.out.println("ACTUALIZAMOS STOCK EN LA TABLA producto");
					//1. Update materiaprima
					updateString = 
						"UPDATE producto SET stock = " + stock + " WHERE idProducto = " + idProducto;
					// System.out.println(updateString);
					s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					s.executeUpdate(updateString);
				}
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
	}
	
	//@Override
	public void updateStockEnvases() throws Exception {
		// System.out.println("Actualiza el stock de los envases");
		PreparedStatement prepared = null;
		ResultSet result = null;
		Statement s;
		long idEnvase = 0;
		double stock = 0;
		try {
			con = bddConexion();
			//0. Todos los stocs de materiaprima_categoria a cero
			String updateString = "UPDATE envase SET stock = 0";
			// System.out.println(updateString);
			s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			s.executeUpdate(updateString);
			//1. Leemos el id de todas las materias
			String Qry = "SELECT idEnvase FROM envase";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			//Mientras haya materias primas...
			while (rs.next()) {
				//... buscamos registros de entrada para cada envase, y actualizamos su stock total en la tabla envase
				idEnvase = rs.getLong("idEnvase");
				stock = 0;
				Qry =
					" SELECT SUM(saldo) AS saldo " +
					" FROM registroentrada re " +
					" WHERE re.habilitado = 'S' " +
						" AND idTipoRegistro = 'E' " +
						" AND idEnvase = " + idEnvase;
				// System.out.println(Qry);
				prepared = con.prepareStatement(Qry);
				result = prepared.executeQuery();
				//Mientras haya registros de entrada para el envase...
				if (result.next()) {
					//... actualizamos el stock
					stock = result.getDouble("saldo");
					// System.out.println("ACTUALIZAMOS STOCK EN LA TABLA envase");
					//1. Update materiaprima
					updateString = 
						"UPDATE envase SET stock = " + stock + " WHERE idEnvase = " + idEnvase;
					// System.out.println(updateString);
					s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					s.executeUpdate(updateString);
				}
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
	}

	//Obtiene las fechas formateadas
	@SuppressWarnings("deprecation")
	private String getDate(Date fc){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
    	int anno = fc.getYear()+1900;
    	int mes = fc.getMonth()+1;
    	int dia = fc.getDate();
    	Date d = null;
    	String dd = null;
		Calendar fecha = Calendar.getInstance();
		fecha.set(anno,mes,dia);
		String strFecha =fecha.get(java.util.Calendar.YEAR) + "-"+ mes + "-"+ fecha.get(java.util.Calendar.DATE); 
		try {
		    d = format.parse(strFecha);
		    dd = format.format(d);
		} catch (ParseException ex) {
		   ex.printStackTrace();
		}
		return dd;
	}

    /*
     * Obtenemos las materias primas asociadas a un producto
     */
    public Vector<MateriaPrima> getMateriasPrimasProducto(long id, String ean) throws Exception {
		Vector<MateriaPrima> retorno = new Vector<MateriaPrima>();
		try {
			con = bddConexion();
			String Qry = "";
			if (id != 0)
				Qry = "SELECT materia.nombre, mc.idMateriaCategoria, c.nombre as nombreCategoria, producto.idProducto, cantidad, mc.stock " +
					" FROM (producto_materiaprima producto, materiaprima materia, categoria c, materiaprima_categoria mc) " +
					" WHERE producto.idProducto = " + id +
						" AND producto.idMateriaPrima = mc.idMateriaCategoria " +
						" AND mc.idCategoria = c.idCategoria " +
						" AND materia.idProducto=mc.idMateriaPrima";
			else
				if (ean.compareTo("") != 0)
					Qry = "SELECT materia.nombre, mc.idMateriaCategoria, c.nombre as nombreCategoria, producto.idProducto, cantidad, mc.stock " +
					" FROM (producto_materiaprima producto, materiaprima materia, producto p, categoria c, materiaprima_categoria mc) " +
					" WHERE p.codigoEan = " + ean +
						" AND producto.idMateriaPrima = mc.idMateriaCategoria " +
						" AND mc.idCategoria = c.idCategoria " +
						" AND materia.idProducto=mc.idMateriaPrima " +
						" AND p.idProducto=producto.idProducto";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				MateriaPrima MP = new MateriaPrima();
				MP.setIdMateriaCategoria(rs.getLong("idMateriaCategoria"));
				MP.setNombre(rs.getString("nombre"));
				MP.setCantidad(rs.getDouble("cantidad"));
				MP.setStock(rs.getDouble("stock"));
				MP.setNombreCategoria(rs.getString("nombreCategoria"));
				//La añadimos al Vector de resultado
				retorno.add(MP);
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
		return retorno;
	}
    
    /*
     * Obtenemos las materias primas asociadas a un producto
     */
    //@Override
    public Vector<Producto> getEANs13Producto(long id, String ean) throws Exception {
		Vector<Producto> retorno = new Vector<Producto>();
		try {
			con = bddConexion();
			String Qry = "";
			if (id != 0)
				Qry = "SELECT p.nombre, p.idProducto, pe.cantidad, p.stock " +
					" FROM (producto_ean13 pe, producto p) " +
					" WHERE pe.idProducto = " + id +
						" AND pe.idEAN13 = p.idProducto ; ";
			else
				if (ean.compareTo("") != 0)
					Qry = "SELECT p.nombre, p.idProducto, pe.cantidad, p.stock " +
					" FROM (producto_ean13 pe, producto p) " +
					" WHERE p.codigoEan = " + ean +
						" AND pe.idEAN13 = p.idProducto ; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Producto p = new Producto();
				p.setIdProducto(rs.getLong("idProducto"));
				p.setNombre(rs.getString("nombre"));
				p.setCantidad(rs.getDouble("cantidad"));
				p.setStock(rs.getDouble("stock"));
				//La añadimos al Vector de resultado
				retorno.add(p);
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
		return retorno;
	}	
    
    /*
     * Obtenemos los productos q forman una agrupación
     */
    //@Override
    public Vector<LineaProducto> getEANs13Producto(long id) throws Exception {
		Vector<LineaProducto> retorno = new Vector<LineaProducto>();
		try {
			con = bddConexion();
			String Qry = "";
				Qry =
					" SELECT gp.idEnvasado, gp.lote, p.idProducto, u.idUbicacion, u.registro, pe.idEAN13, " +
						" p.stock, gp.fecha, gp.fechaCaducidad, p.nombre, u.cantidad AS cantidad, pe.cantidad as cantidadCompuesto " +
					" FROM producto_ean13 pe, producto p, gp_envasado gp, ubicacion u " +
					" WHERE pe.idProducto = " + id + 
						" AND pe.idEAN13 = gp.idProducto " +
					    " AND u.registro = gp.lote " +
					    " AND p.idProducto = gp.idProducto; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				LineaProducto P = new LineaProducto();
				P.setIdLinea(rs.getLong("idEAN13"));
				P.setIdUbicacion(rs.getLong("idUbicacion"));
				P.setLote(rs.getString("lote"));
				P.setFecha(rs.getDate("fecha"));
				P.setFechaCaducidad(rs.getDate("fechaCaducidad"));
				P.setCantidadUbicacion(rs.getLong("cantidadCompuesto"));
				double cantidad = rs.getDouble("cantidad");
				P.setDisponible(cantidad);
				P.setIdProducto(rs.getLong("idEAN13"));
				P.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				if (cantidad > 0)
					retorno.add(P);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return retorno;
	}
    
    /*
     * Obtenemos los productos q forman una agrupación
     */
    public Vector<LineaProducto> getProductosComponen(long id, boolean cantidades) throws Exception {
		Vector<LineaProducto> retorno = new Vector<LineaProducto>();
		try {
			con = bddConexion();
			String Qry = "";
			if (cantidades)
				Qry =
					" SELECT gp.idEnvasado, gp.lote, p.idProducto, u.idUbicacion, " +
						" u.registro, c.idCompuestoDe, p.stock, gp.fecha, gp.fechaCaducidad, " +
						" p.nombre, u.cantidad AS cantidad, c.cantidad as cantidadCompuesto " +
					" FROM producto_compuesto c, producto p, gp_envasado gp, ubicacion u " +
					" WHERE c.idProducto = " + id +
						" AND c.idCompuestoDe = gp.idProducto " +
						" AND u.registro = gp.lote " +
						" AND p.idProducto = gp.idProducto";
			else
				Qry = "SELECT DISTINCT c.idCompuestoDe, c.idProducto, p.nombre, c.cantidad " +
						" FROM producto_compuesto c, producto p " +
						" WHERE c.idProducto = " + id +
							" AND p.idProducto = c.idCompuestoDe";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				LineaProducto P = new LineaProducto();
				P.setIdLinea(rs.getLong("idCompuestoDe"));
				if (cantidades){
					P.setIdUbicacion(rs.getLong("idUbicacion"));
					P.setLote(rs.getString("lote"));
					P.setFecha(rs.getDate("fecha"));
					P.setFechaCaducidad(rs.getDate("fechaCaducidad"));
					P.setCantidadUbicacion(rs.getLong("cantidadCompuesto"));
				}
				double cantidad = rs.getDouble("cantidad");
				P.setDisponible(cantidad);
				P.setIdProducto(rs.getLong("idCompuestoDe"));
				P.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				if (cantidades && cantidad > 0)
					retorno.add(P);
				else
					if (!cantidades)
						retorno.add(P);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return retorno;
	}
	
	/**
	 * Devuelve los envases asociados a un producto. Se pueden buscar tanto a partir de su codigo id como a partir de su codigo ean
	 * Se llama a este método cuando mostramos un producto para mostrar/editar, y cargamos los envases asociados al producto
	 * @param id Id del producto
	 * @param ean Código ean del producto
	 * @return Vector que contiene todos los envases asociados al producto
	 * @author andres (29/04/2011)
	 * @since 0.1
	 */
    public Vector<Envase> getEnvasesProducto(long id, String ean) throws Exception {
    	// System.out.println("Get envases producto: " + id);
		Vector<Envase> retorno = new Vector<Envase>();
		try {
			con = bddConexion();
			String Qry = "";
			if (id != 0)
				Qry =
					" SELECT e.nombre, e.idEnvase, cantidad, e.stock " +
					" FROM (producto_envase producto, envase e) " +
					" WHERE producto.idProducto = " + id +
						" AND producto.idEnvase = e.idEnvase; ";
			else
				if (ean.compareTo("") != 0)
					Qry = "SELECT e.nombre, e.idEnvase, cantidad, e.stock " +
					"FROM (producto_envase producto, envase e, producto p) " +
					"WHERE p.codigoEan = " + ean +
					" AND producto.idEnvase = e.idEnvase" +
					" AND p.idProducto = producto.idProducto";
				else
					return null;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Envase EN = new Envase();
				EN.setIdEnvase(rs.getLong("idEnvase"));
				EN.setNombre(rs.getString("nombre"));
				EN.setCantidad(rs.getDouble("cantidad"));
				EN.setStock(rs.getDouble("stock"));
				//La añadimos al Vector de resultado
				retorno.add(EN);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
			}
		}
		return retorno;
	}

	/**
	 * Eliminar envases producto.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	private boolean eliminarEnvasesProducto(long id) throws Exception{
		Statement stmt;
		try{
			con = bddConexion();
			String deleteString =
				"DELETE FROM producto_envase WHERE idProducto = " + id;
			// System.out.println(deleteString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(deleteString);
			// System.out.println("producto_envase BORRADO");
			con.close();
			return true;
		} catch (Exception e) {
			con.close();
			e.printStackTrace();
			throw (e);
		}
	}
	
	/**
	 * Eliminar materias producto.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	private boolean eliminarMateriasProducto(long id) throws Exception{
		Statement stmt;
		try{
			con = bddConexion();
			String deleteString =
				"DELETE FROM producto_materiaprima WHERE idProducto = " + id;
			// System.out.println(deleteString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(deleteString);
			// System.out.println("producto_materiaprima BORRADO");
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			con.close();
			throw (e);
		}
	}
	
	/**
	 * Eliminar eans13 producto.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	private boolean eliminarEANs13Producto(long id) throws Exception{
		Statement stmt;
		try{
			con = bddConexion();
			String deleteString =
				"DELETE FROM producto_ean13 WHERE idProducto = " + id;
			// System.out.println(deleteString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(deleteString);
			// System.out.println("producto_ean13 BORRADO");
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			con.close();
			throw (e);
		}
	}
	
	/**
	 * Eliminar compuestos producto.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	private boolean eliminarCompuestosProducto(long id) throws Exception{
		Statement stmt;
		try{
			con = bddConexion();
			String deleteString = 
				"DELETE FROM producto_compuesto WHERE idProducto = " + id;
			// System.out.println(deleteString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(deleteString);
			// System.out.println("producto_compuesto BORRADO");
			con.close();
			return true;
		} catch (Exception e) {
			con.close();
			e.printStackTrace();
			throw (e);
		}
	}

	public boolean eliminarProducto(long id) throws Exception {
		//Desde aquí, eliminar el producto de las tablas: producto, pro_compuesto, pro_materiaprima, pro_envase
		boolean eliminado = false;
		Statement stmt;
		try{
			con = bddConexion();
			int res = -1;
			String deleteString = 
				"DELETE FROM producto WHERE idProducto = " + id;
			// System.out.println(deleteString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(deleteString);
			if((res == 1) &&
				(eliminarEnvasesProducto(id) && eliminarMateriasProducto(id) &&
						eliminarEANs13Producto(id) && eliminarCompuestosProducto(id))){
				// System.out.println("Producto BORRADO");
				eliminado=true;
			}
			con.close();
			return eliminado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	public boolean setCampoProducto(long idProducto, String columna, String valor) throws Exception{
		boolean actualizado = false;
		// System.out.println("ProductoDAO setCampoProducto");
		Statement stmt;
		int res = 0;
		try {
			con = bddConexion();
			//SQL de actualizacion
			String updateString =
				"UPDATE producto SET " + columna + " = '" + valor + "'" +
				" WHERE idProducto = " + idProducto;
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if(res == 1){
				// System.out.println("Producto DESHABILITADO");
				actualizado=true;
				}
			return actualizado;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}
	
	public long addMateriaPrima(MateriaPrima materiaPrima) throws Exception {
		// System.out.println("DAO addMateriaPrima");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement stmt;
		Connection con = null;	
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT MAX(idProducto) as idMaxMateria FROM materiaprima");
			rs = ps.executeQuery();
			long idMateriaMax = 0 ;
			MateriaPrima materiaMaxima = new MateriaPrima();
			if(rs.next()){
				materiaMaxima.setIdMateriaPrima(rs.getLong("idMaxMateria"));
				idMateriaMax = materiaMaxima.getIdMateriaPrima() + 1;
			}
			else 
				idMateriaMax = 1;
			// System.out.println("el id de la materia es... " + idMateriaMax);
			//SQL de insersion
			String insertString= 
				"INSERT INTO materiaprima(idProducto,idGrupo,nombre,tipo,stock, habilitado) " +
				" VALUES(" +
				idMateriaMax + ","
				+ materiaPrima.getIdGrupo() + ",'"
				+ materiaPrima.getNombre() + "',"
				+ "'M',"
				+ materiaPrima.getStock() + ","
				+ "'S')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);

			if(res==1)
			{
				// System.out.println("REGISTRO INSERTADO");
				//resultado=true;
			}else
				idMateriaMax = -1;
			
			//AHORA
			//Para cada una de las categorias asociadas a la materia prima, creamos una nueva fila en materiaprima_categoria
			ArrayList<Long> categorias = materiaPrima.getCategorias();
			for (int i = 0; i < categorias.size(); i++){
				long cati = categorias.get(i);
				con = bddConexion();
				String insert = 
					"INSERT INTO materiaprima_categoria(idMateriaPrima,idCategoria,stock) " +
					" VALUES('"+ idMateriaMax + "','" + cati + "',0)";
				// System.out.println(insert);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insert);
				if(res == 1){
					// System.out.println("REGISTRO INSERTADO");
					//resultado=true;
				}else
					idMateriaMax = -1;
			}
			return idMateriaMax;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}
	
	public Vector<Categoria> getCategoriasMateria(Long idMateriaPrima) throws Exception{
		// System.out.println("Get categorias de la materia prima: " + idMateriaPrima);
		Vector<Categoria> retorno = new Vector<Categoria>();
		try {
			con = bddConexion();
			String Qry = "";
			Qry = "SELECT DISTINCT c.nombre, c.idCategoria,mc.stock " +
					" FROM categoria c, materiaprima_categoria mc " +
					" WHERE mc.habilitado='S' AND mc.idCategoria=c.idCategoria AND " +
					" mc.idMateriaPrima = '" + idMateriaPrima + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Categoria cate = new Categoria();
				cate.setIdCategoria(rs.getLong("idCategoria"));
				cate.setNombreCategoria(rs.getString("nombre"));
				cate.setStock(rs.getDouble("stock"));
				retorno.add(cate);
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
		return retorno;
	}
	
	//@Override
	public void addStockProducto(String lote, double cantidad) throws Exception{
		// System.out.println("Añadimos stock al producto");
		try {
			con = bddConexion();
			long idProducto = 0;
			ResultSet rs = null;
			Statement stmt;
			String Qry =
				" SELECT idProducto " +
				" FROM gp_envasado enva " +
				" WHERE enva.lote = '" + lote + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				idProducto = rs.getLong("idProducto");
			}
			//Tenemos el id y el stock del producto
			String updateString =
				"UPDATE producto SET stock = stock + " + cantidad +
				" WHERE idProducto = " + idProducto;
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			int res = stmt.executeUpdate(updateString);
			if(res == 1)
			{
				// System.out.println("STOCK del producto ACTUALIZADO");
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}
	
	//@Override
	public void registrarGrupoProducto(GrupoProducto grupo) throws Exception{
		// System.out.println("DAO registrarGrupoProducto");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT MAX(idGrupoProducto) AS idMaxGrupo FROM grupo_producto");
			rs = ps.executeQuery();
			long idMax = 0;
			if(rs.next()){
				idMax = rs.getLong("idMaxGrupo") + 1;
			}else
				idMax = 1;
			// System.out.println("el id del grupo es... "+ idMax);
			Statement stmt;
			//SQL de insercion
			String insertSql = 
				"INSERT INTO grupo_producto(idGrupoProducto,nombre) VALUES(" +
				  idMax + ",'" + grupo.getNombreGrupo() + "')";
			// System.out.println(insertSql);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertSql);
			if(res == 1){
				// System.out.println("GRUPO PRODUCTO INSERTADO");
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
	}

	//@Override
	public void registrarFamilia(Familia familia) throws Exception{
		// System.out.println("DAO registrarFamilia");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT MAX(idFamilia) AS idMaxFamilia FROM familia");
			rs = ps.executeQuery();
			long idMax = 0;
			if(rs.next()){
				idMax = rs.getLong("idMaxFamilia") + 1;
			}else
				idMax = 1;
			// System.out.println("el id de la familia es... "+ idMax);
			Statement stmt;
			//SQL de insercion
			String insertSql = 
				"INSERT INTO familia(idFamilia,descripcion) VALUES(" +
				  idMax + ",'" + familia.getDescripcionFamilia() + "')";
			// System.out.println(insertSql);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertSql);
			if(res == 1){
				// System.out.println("FAMILIA INSERTADA");
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
	}

	//@Override
	public void registrarCategoria(Categoria categoria) throws Exception{
		// System.out.println("DAO registrarCategoria");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT MAX(idCategoria) AS idMaxCategoria FROM categoria");
			rs = ps.executeQuery();
			long idMax = 0;
			if(rs.next()){
				idMax = rs.getLong("idMaxCategoria") + 1;
			}else
				idMax = 1;
			// System.out.println("el id de la categoria es... "+ idMax);
			Statement stmt;
			//SQL de insercion
			String insertSql = 
				"INSERT INTO categoria(idCategoria,nombre) VALUES(" +
				  idMax + ",'" + categoria.getNombreCategoria() + "')";
			// System.out.println(insertSql);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertSql);
			if(res == 1){
				// System.out.println("CATEGORIA INSERTADA");
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
	}

	//@Override
	public void registrarImpuesto(Impuesto impuesto) throws Exception{
		// System.out.println("DAO registrarImpuesto");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		int res = 0;	
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT MAX(idImpuesto) AS idMaxImpuesto FROM impuestos");
			rs = ps.executeQuery();
			long idMax = 0;
			if(rs.next()){
				idMax = rs.getLong("idMaxImpuesto") + 1;
			}else
				idMax = 1;
			// System.out.println("el id del impuesto es... " + idMax);
			Statement stmt;
			//SQL de insercion
			String insertSql = 
				"INSERT INTO impuestos(idImpuesto,nombre,porcentaje) VALUES(" +
				  idMax + ",'" + impuesto.getNombreImpuesto() + "','" + impuesto.getPorcentaje() + "')";
			// System.out.println(insertSql);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertSql);
			if(res == 1){
				// System.out.println("IMPUESTO INSERTADO");
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
	}
	
	//@Override
	public int devolver(String lote, double cantidad, String destino, Entidad e) throws Exception{
		int retorno = 1;
		RegistroEntrada entry = new RegistroEntrada();
		entry.setResponsable(e);
		entry.setCantidad(cantidad);
		entry.setLote(lote);
		//A. Mirar si existe el lote en la base de datos (tabla gp_envasado)
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		long idHueco = 0;	
		try {
			con = bddConexion();
			ps = con.prepareStatement("SELECT lote FROM gp_envasado WHERE lote='" + lote + "'");
			rs = ps.executeQuery();
			if(rs.next()){}
			else
				return 0;
			//B. Si va a reubicar lo devuelto, mirar que el hueco de destino existe
			if (destino != null && destino.compareTo("") != 0){
				ps = con.prepareStatement("SELECT idHueco FROM ubicacion_hueco WHERE descripcion='" + destino + "'");
				rs = ps.executeQuery();
				if(rs.next()){ idHueco = rs.getLong("idHueco"); }
				else
					return 2;
			}
			//1. Marcar devuelto
			new RegistroEntradaDAO().devolucion(entry);
			if (destino != null && destino.compareTo("") != 0){
				//2. Ubicar en idHueco
				Vector<Ubicacion> ubicaciones = new Vector<Ubicacion>();
				Ubicacion u = new Ubicacion();
				u.setIdHueco(idHueco);
				u.setCantidad(cantidad);
				u.setRegistro(lote);
				ubicaciones.add(u);
				new GestionUbicacionesHelper().salvarUbicaciones(ubicaciones, false);
			}
		} catch (Exception ex) {
				ex.printStackTrace();
				throw (ex);
			} finally {
				try {
					ps.close();
					con.close();
				} catch (Exception ex) { ex.printStackTrace(); }
			}
		return retorno;
	}
	
	//@Override
	public Vector<Producto> getProductosMalDefinidos() throws Exception{
		//Inicializamos el Vector de retorno.
		Vector<Producto> resultado = new Vector<Producto>();
		try {
			con = bddConexion();
			String Qry = "SELECT p.idProducto, p.nombre, p.descripcion, p.codigoEan " +
				"FROM producto p WHERE habilitado = 'S' AND p.peso <= 0";
			Qry += " ORDER BY p.nombre";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos  proveedor en el registro
				Producto prod = new Producto();
				prod.setIdProducto(rs.getLong("idProducto"));
				prod.setNombre(rs.getString("nombre"));
				prod.setDescripcion(rs.getString("descripcion"));
				prod.setCodigoEan(rs.getString("codigoEan"));
				//La añadimos al Vector de resultado
				resultado.add(prod);
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
		//Retornamos el vector de resultado.
		return resultado;
	}
	
	/**
	 * A partir de un idProducto, obtiene todos los envases donde el producto podría ser agrupado
	 * @param idProducto Identificador del producto que consultamos
	 * @return
	 * @throws Exception
	 */
	//@Override
	public Vector<LineaProducto> getEnvasesAgruparProducto(long idProducto, String filtro) throws Exception {
		Vector<LineaProducto> resultado = new Vector<LineaProducto>();
		try {
			con = bddConexion();
			String Qry =
				"SELECT * " +
				" FROM producto_compuesto b " +
				" WHERE b.idCompuestoDe = " + idProducto;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()){
				long idAgrupacion = rs.getLong("idProducto");
				//Para esta agrupacion, miramos si hay mas productos que la compongan
				Qry =
					"SELECT * " +
					" FROM producto_compuesto b " +
					" WHERE b.idProducto = " + idAgrupacion;
				// System.out.println(Qry);
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				ps2 = con.prepareStatement(Qry);
				rs2 = ps2.executeQuery();
				int contador = 0;
				while (rs2.next()){
					contador++;
				}
				if (contador > 1){
					//La agrupacion esta compuesta por mas de un producto.
					//No nos sirve para este caso (Estamos envasando un item EAN13).
					//En este caso, solo nos sirven agrupaciones compuestas unicamente por nuestro EAN13
				}else{
					Qry =
						" SELECT p.idProducto as idProductoAgrupacion, " +
							" p.nombre as nombreProductoAgrupacion, pe.cantidad, " +
							" e.idEnvase, e.nombre, re.codigoEntrada, re.codigoOrden orden, " +
							" re.saldo, re.fecha, re.fechaCaducidad, re.habilitado, " +
							" (SELECT lote FROM ordenentrada oe WHERE oe.codigoOrden = re.codigoOrden) lote " +
						" FROM registroentrada re " +
							" INNER JOIN envase e ON re.idEnvase = e.idEnvase " +
							" INNER JOIN producto_envase pe ON pe.idEnvase = e.idEnvase " +
							" INNER JOIN producto_compuesto pc ON pe.idProducto = pc.idProducto " +
							" INNER JOIN producto p ON p.idProducto = pc.idProducto " +
						" WHERE pc.idProducto = " + idAgrupacion +
							" AND pc.idCompuestoDe = " + idProducto + " " + filtro +
						" ORDER BY idEnvase, idProductoAgrupacion";
					// System.out.println(Qry);
					ps2 = con.prepareStatement(Qry);
					rs2 = ps2.executeQuery();
					while (rs2.next()){
						LineaProducto linea = new LineaProducto();
						String orden = rs2.getString("orden");
						String consulta =
							" SELECT en.nombre " +
							" FROM ordenentrada oe " +
								" INNER JOIN entidad en ON en.idUsuario = oe.idProveedor " +
							" WHERE oe.codigoOrden = '" + orden + "'; ";
						// System.out.println("SQL: " + consulta);
						PreparedStatement pre = con.prepareStatement(consulta);
						ResultSet res = pre.executeQuery();
						if (res.next())
							linea.setProveedor(res.getString("nombre"));
						consulta =
							" SELECT pc.cantidad " +
							" FROM producto_compuesto pc " +
							" WHERE pc.idProducto = " + idAgrupacion;
						// System.out.println("SQL: " + consulta);
						pre = con.prepareStatement(consulta);
						res = pre.executeQuery();
						double cantidad = 0;
						if (res.next())
							cantidad = res.getDouble("cantidad");
						linea.setTeorica(rs2.getDouble("cantidad") / cantidad);
						linea.setIdLinea(rs2.getLong("idEnvase"));
						linea.setNombre(rs2.getString("nombre") + " AGRUPACION: " + rs2.getString("nombreProductoAgrupacion"));
						linea.setHabilitado(rs2.getString("habilitado"));
						linea.setEntrada(rs2.getString("codigoEntrada"));// + "_" + rs2.getLong("idProductoAgrupacion"));
						linea.setFecha(rs2.getDate("fecha"));
						linea.setFechaCaducidad(rs2.getDate("fechaCaducidad"));
						linea.setDisponible(rs2.getDouble("saldo"));
						linea.setLote(rs2.getString("lote"));
						linea.setIdProducto(rs2.getLong("idProductoAgrupacion"));//linea.getIdLinea());
						//La añadimos al Vector de resultado
						resultado.add(linea);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return resultado;
	}
	
	/**
	 * A partir de un idProducto, id de un EAN13, obtiene todas las agrupaciones que se pueden hacer con él
	 * (Solo las agrupaciones que se forman unicamente con ese EAN13) 
	 */
	//@Override
	public Vector<LineaProducto> getAgrupacionesEAN13(long idProducto) throws Exception {
		Vector<LineaProducto> resultado = new Vector<LineaProducto>();
		try {
			con = bddConexion();
			String Qry =
				" SELECT p.idProducto, p.nombre " +
				" FROM producto_compuesto pc, producto p " +
				" WHERE pc.idCompuestoDe = " + idProducto +
					" AND p.idProducto = pc.idProducto";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()){
				long idAgrupacion = rs.getLong("idProducto");
				//Para esta agrupacion, miramos si hay mas productos que la compongan
				Qry =
					" SELECT COUNT(p.idProducto) AS cuantosProductos " +
					" FROM producto_compuesto p " +
					" WHERE p.idProducto = " + idAgrupacion;
				// System.out.println(Qry);
				PreparedStatement ps2 = con.prepareStatement(Qry);
				ResultSet rs2 = ps2.executeQuery();
				int cuantosProductos = 0;
				while (rs2.next()){
					cuantosProductos = rs2.getInt("cuantosProductos");
				}
				if (cuantosProductos == 1){//solo nos sirven agrupaciones compuestas unicamente por nuestro EAN13
					LineaProducto linea = new LineaProducto();
					linea.setNombre(rs.getString("nombre"));
					linea.setIdProducto(rs.getLong("idProducto"));
					resultado.add(linea);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return resultado;
	}
	
	public Producto listaProductoLote(String lote) throws Exception{
		Producto producto = new Producto();
		try {
			con = bddConexion();
			String qryProducto = "";
			//Miramos si es un registro de entrada (producto venta) o lote envasado por nosotros
			if (lote.charAt(0) == '0'){
				long idProducto = 0;
				qryProducto =
					" SELECT IF (gpa.idAgrupacion > -1, gpa.idAgrupacion, gp.idProducto) AS idProducto " +
					" FROM producto p " +
						" INNER JOIN gp_envasado gp ON gp.idProducto = p.idProducto " +
						" INNER JOIN gp_envasado_agrupacion gpa ON gpa.ordenEnvasado = gp.orden " +
					" WHERE gp.lote = '" + lote + "'; ";
				//Modificar el stock del producto
				ps = con.prepareStatement(qryProducto);
				rs = ps.executeQuery();
				if (rs.next()){
					idProducto = rs.getLong("idProducto");
				}
				qryProducto =
					" SELECT p.nombre, p.stock " +
					" FROM producto p " +
					" WHERE p.idProducto = '" + idProducto + "'; ";
				//Modificar el stock del producto
				ps = con.prepareStatement(qryProducto);
				rs = ps.executeQuery();
				if (rs.next()){
					producto.setNombre(rs.getString("nombre"));
					producto.setStock(rs.getDouble("stock"));
				}
				ps.close();
				rs.close();
			}else{
				if (lote.charAt(0) == 'E'){
					long idProducto = 0, idEnvase = 0;//idCategoria = 0
					qryProducto =
						" SELECT re.idProducto, re.idTipoRegistro, re.idCategoria, re.idEnvase " +
						" FROM registroentrada re " +
						" WHERE re.codigoEntrada = '" + lote + "'; ";
					//Si es un registro de entrada, modificar el saldo del registro
					//Modificar el stock del producto
					ps = con.prepareStatement(qryProducto);
					rs = ps.executeQuery();
					if (rs.next()){
						String tipoRegistro = rs.getString("idTipoRegistro");
						idProducto = rs.getLong("idProducto");
						//idCategoria = rs.getLong("idCategoria");
						idEnvase = rs.getLong("idEnvase");
						if (tipoRegistro!= null && tipoRegistro.equals("P")){
							qryProducto =
								" SELECT p.nombre, p.stock " +
								" FROM producto p " +
								" WHERE p.idProducto = '" + idProducto + "'; ";
							ps = con.prepareStatement(qryProducto);
							rs = ps.executeQuery();
							if (rs.next()){
								producto.setNombre(rs.getString("nombre"));
								producto.setStock(rs.getDouble("stock"));
							}
						}else{
							if (tipoRegistro!= null && tipoRegistro.equals("M")){								
								qryProducto =
									" SELECT m.nombre, m.stock " +
									" FROM materiaprima m " +
									" WHERE m.idProducto = '" + idProducto + "'; ";
								ps = con.prepareStatement(qryProducto);
								rs = ps.executeQuery();
								if (rs.next()){
									producto.setNombre(rs.getString("nombre"));
									producto.setStock(rs.getDouble("stock"));
								}
							}else{
								if (tipoRegistro!= null && tipoRegistro.equals("E")){
									qryProducto =
										" SELECT e.nombre, e.stock " +
										" FROM envase e " +
										" WHERE e.idEnvase = '" + idEnvase + "'; ";
									//Modificar el stock del producto
									ps = con.prepareStatement(qryProducto);
									rs = ps.executeQuery();
									if (rs.next()){
										producto.setNombre(rs.getString("nombre"));
										producto.setStock(rs.getDouble("stock"));
									}
								}else{
									throw new Exception("Tipo de registro desconocido");
								}
							}
						}
					}
					ps.close();
					rs.close();
				}else{
					//throw new Exception();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return producto;
	}
	
	public void modificarStockRegistro(String lote, double cantidad, long idHueco, String usuarioResponsable, String causa) throws Exception{
		//Comprobar si ya existe el lote en la ubicacion. Si no, insertamos
		try {
			con = bddConexion();
			String Qry =
				" SELECT u.idUbicacion " +
				" FROM ubicacion u " +
				" WHERE u.registro = '" + lote + "' " +
					" AND u.idHueco = '" + idHueco + "'; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			if (rs.next()){
				long idUbicacion = rs.getLong("idUbicacion");
				Qry =
					" UPDATE ubicacion " +
					" SET cantidad = cantidad + (" + cantidad + ") " +
					" WHERE idUbicacion = '" + idUbicacion + "'; ";
			}else{
				//insertar en ubicacion
				Qry =
					" INSERT INTO ubicacion (idHueco, registro, cantidad) " +
					" VALUES('" + idHueco + "', '" + lote + "', '" + cantidad + "'); ";
			}
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(Qry);
			
			String qryProducto = "";
			//Miramos si es un registro de entrada (producto venta) o lote envasado por nosotros
			if (lote.charAt(0) == '0'){
				long idProducto = 0;
				qryProducto =
					" SELECT IF (gpa.idAgrupacion > -1, gpa.idAgrupacion, gp.idProducto) AS idProducto " +
					" FROM producto p " +
						" INNER JOIN gp_envasado gp ON gp.idProducto = p.idProducto " +
						" INNER JOIN gp_envasado_agrupacion gpa ON gpa.ordenEnvasado = gp.orden " +
					" WHERE gp.lote = '" + lote + "'; ";
				//Modificar el stock del producto
				ps = con.prepareStatement(qryProducto);
				rs = ps.executeQuery();
				if (rs.next()){
					idProducto = rs.getLong("idProducto");
				}
				ps.close();
				rs.close();
				Qry =
					" UPDATE producto SET stock = stock + " + cantidad + " WHERE idProducto = " + idProducto;
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(Qry);
			}else{
				if (lote.charAt(0) == 'E'){
					long idProducto = 0, idCategoria = 0, idEnvase = 0;
					qryProducto =
						" SELECT re.idProducto, re.idTipoRegistro, re.idCategoria, re.idEnvase " +
						" FROM registroentrada re " +
						" WHERE re.codigoEntrada = '" + lote + "'; ";
					//Si es un registro de entrada, modificar el saldo del registro
					Qry =
						" UPDATE registroentrada SET saldo = saldo + " + cantidad + " WHERE codigoEntrada = '" + lote + "'; ";
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(Qry);
					//Modificar el stock del producto
					ps = con.prepareStatement(qryProducto);
					rs = ps.executeQuery();
					if (rs.next()){
						String tipoRegistro = rs.getString("idTipoRegistro");
						idProducto = rs.getLong("idProducto");
						idCategoria = rs.getLong("idCategoria");
						idEnvase = rs.getLong("idEnvase");
						if (tipoRegistro!= null && tipoRegistro.equals("P")){
							Qry =
								" UPDATE producto SET stock = stock + " + cantidad + " WHERE idProducto = " + idProducto;
							stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
							stmt.executeUpdate(Qry);
						}else{
							if (tipoRegistro!= null && tipoRegistro.equals("M")){
								//1. materiaprima_categoria
								Qry =
									" UPDATE materiaprima_categoria SET stock = stock + " + cantidad + " WHERE idMateriaPrima = " + idProducto + " AND idCategoria = " + idCategoria + "; ";
								stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
								stmt.executeUpdate(Qry);
								//2. materiaprima
								Qry =
									" UPDATE materiaprima SET stock = stock + " + cantidad + " WHERE idProducto = " + idProducto;
								stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
								stmt.executeUpdate(Qry);
							}else{
								if (tipoRegistro!= null && tipoRegistro.equals("E")){
									Qry =
										" UPDATE envase SET stock = stock + " + cantidad + " WHERE idEnvase = " + idEnvase;
									stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
									stmt.executeUpdate(Qry);
								}else{
									throw new Exception("Tipo de registro desconocido");
								}
							}
						}
					}
					ps.close();
					rs.close();
				}else{
					throw new Exception();
				}
			}
			//insertar en log stock modificado
			Qry =
				" INSERT INTO log_stock_modificado (registro, cantidad, idHueco, usuario, causa) VALUES ('" + lote + "', '" + cantidad + "', '" + idHueco + "', '" + usuarioResponsable + "', '" + causa + "'); ";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(Qry);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}
	
	public String validarLote(String lote) throws Exception{
		String validacion = "O";//ok
		try {
			con = bddConexion();
			//1. Empieza por 0 (cero) o por E?
			if (lote.charAt(0) == '0'){
				//Comprobar si existe en el histórico de envasados
				String Qry =
					" SELECT gpe.idEnvasado " +
					" FROM gp_envasado gpe " +
					" WHERE gpe.lote = '" + lote + "'; ";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				if (rs.next()){
				}else{
					validacion = "NL";//no existe el lote
				}
			}else{
				if (lote.charAt(0) == 'E'){
					//Comprobar si existe en el histórico de entradas
					String Qry =
						" SELECT re.idEntrada " +
						" FROM registroentrada re " +
						" WHERE re.codigoEntrada = '" + lote + "'; ";
					// System.out.println(Qry);
					ps = con.prepareStatement(Qry);
					rs = ps.executeQuery();
					if (rs.next()){
					}else{
						validacion = "NR";//no existe el lote
					}
				}else{
					validacion = "M";//lote mal formado, no empieza ni por E ni por cero
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return validacion;
	}
}