package es.induserco.opilion.datos.envase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.data.entidades.Material;
import es.induserco.opilion.infraestructura.DatabaseConfig;

/**
 * @author Induserco, andres (12/04/2011)
 * @version 0.2
 */
public class EnvaseDAO extends DatabaseConfig implements IEnvaseDataService {
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection con = null;	

	//@Override
	public Boolean addEnvase(Envase envase) throws Exception {
		// System.out.println("DAO addEnvase");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement stmt;
		
		Connection con = null;
		Boolean resultado = false;
		Date fecha = null;		
		int res=0;
		
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idEnvase) as idMaxEnvase FROM envase");
			rs = ps.executeQuery();
			long idEnvaseMax =0 ;
			Envase envaseMaximo = new Envase();
			if(rs.next()){
				envaseMaximo.setIdEnvase(rs.getLong("idMaxEnvase"));
				idEnvaseMax= envaseMaximo.getIdEnvase()+1;
			}
			else 
				idEnvaseMax=1;

			// System.out.println("el id de envase es... "+ idEnvaseMax);

			//obtener fecha sistema
			ps = con.prepareStatement(" SELECT CURDATE() as fecha ");
			rs = ps.executeQuery();
			
			while(rs.next()){
				fecha = rs.getDate("fecha");
	        }
			
			//SQL de insersion
			String insertString= 
				"insert into envase(idEnvase,idMaterial,nombre,descripcion,dimensiones,stock,peso) values(" +
				idEnvaseMax + ","
				+ envase.getIdMaterial()+",'"
				+ envase.getNombre() +"','"	
				+ envase.getDescripcion() +"','"					
				+ envase.getDimensiones() +"',"
				+ envase.getStock() +","				
				+ envase.getPeso() +")";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);

			if(res==1)
				{
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
			} catch (Exception e) {}
		}
	}

	//@Override
	public Vector<Envase> getEnvases(Envase envase) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Envase>  resultado = new Vector<Envase>() ;
		String Qry="";
		try {
			//1. Antes de mostrar los envases, actualizamos su stock
			updateStockEnvases();
			con = bddConexion();
			if (envase.getIdMaterial() != null){
				if (envase.getIdMaterial()!= 0)
					Qry="SELECT * FROM envase WHERE "
						+ " idMaterial = " + envase.getIdMaterial() +"";	
				else
					Qry="SELECT * FROM envase";
			}else
				Qry="SELECT * FROM envase";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();

			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Envase env = new Envase();
				env.setIdEnvase(rs.getLong("idEnvase"));
				env.setIdMaterial(rs.getLong("idMaterial"));
				env.setNombre(rs.getString("nombre"));
				env.setDescripcion(rs.getString("descripcion"));
				env.setDimensiones(rs.getString("dimensiones"));
				env.setPeso(rs.getDouble("peso"));
				env.setStock(rs.getDouble("stock"));
				
				//La añadimos al Vector de resultado
				resultado.add(env);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * Actualiza el stock de todos los envases, a partir de los stocks registrados en la tabla registroentrada
	 * @author Induserco, Andrés (12/04/2011)
	 * @throws Exception Si falla la actualización
	 * @since 0.2
	 */
	private void updateStockEnvases() throws Exception {
		// System.out.println("Actualiza el stock de los envases");
		PreparedStatement prepared = null;
		ResultSet result = null;
		Statement s;
		long idEnvase = 0;
		double stock = 0;
		boolean flag = false;
		try {
			con = bddConexion();
			//1. Leemos el id de todos los envases
			String Qry = "SELECT idEnvase FROM envase";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			//Mientras haya envases...
			while (rs.next()) {
				//... buscamos registros de entrada para cada envase, y actualizamos su stock total en la tabla envase
				idEnvase = rs.getLong("idEnvase");
				stock = 0;
				Qry = "SELECT * FROM  registroentrada WHERE idTipoRegistro='E' AND idEnvase = " + idEnvase;
				// System.out.println(Qry);
				prepared = con.prepareStatement(Qry);
				result = prepared.executeQuery();
				//Mientras haya registros de entrada para el envase...
				while (result.next()) {
					//... actualizamos el stock
					stock += result.getDouble("saldo");
					flag = true;
				}
				if (flag){
					String updateString = 
						"UPDATE envase SET stock = " + stock + " WHERE idEnvase = " + idEnvase;
					// System.out.println(updateString);
					s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					s.executeUpdate(updateString);
					flag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {}
		}
	}

	//@Override
	public Vector<Envase> getEnvases(Long idMaterial) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Envase> resultado = new Vector<Envase>();
		try {
			con = bddConexion();
			String Qry = "";
			if (idMaterial != -1L)
				Qry="SELECT * FROM envase WHERE idMaterial = " + idMaterial +"";
			else
				Qry="SELECT * FROM  envase ORDER BY nombre";		
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Envase env = new Envase();
				env.setIdEnvase(rs.getLong("idEnvase"));
				env.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(env);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Material> getMateriales() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Material> resultado = new Vector<Material>();
		try {
			con = bddConexion();
			String Qry="SELECT * FROM  material";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Material mat = new Material();
				mat.setIdMaterial(rs.getLong("idMaterial"));
				mat.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(mat);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}	

	//@Override
	public Boolean updateEnvase(Envase envaseFind, Envase envaseUpdate) throws Exception {
		// System.out.println("DAO updateEnvase");
		Statement stmt;
		Connection con = null;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			//SQL de insercion
			String updateString= 
				"UPDATE envase SET " 
				+ " idMaterial = " 		+ envaseUpdate.getIdMaterial() + ","
				+ " nombre = '" 		+ envaseUpdate.getNombre() + "',"	
				+ " descripcion = '" 	+ envaseUpdate.getDescripcion() + "',"	
				+ " dimensiones = '" 	+ envaseUpdate.getDimensiones() + "',"						
				+ " peso = " 			+ envaseUpdate.getPeso()+ ","					
				+ " stock = " 			+ envaseUpdate.getStock()+ " "
				+ " WHERE idEnvase =" 	+ envaseFind.getIdEnvase();
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if(res == 1){
				// System.out.println("Envase ACTUALIZADO");
				resultado=true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {}
		}
	}

	//@Override
	public Vector<Envase> loadEnvase(Envase entry) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Envase> resultado = new Vector<Envase>();
		// System.out.println("DAO loadEnvase");
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM  envase WHERE idEnvase = "
				+ entry.getIdEnvase();		
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// System.out.println("devuelve envase");
				Envase env = new Envase();
				env.setIdEnvase(rs.getLong("idEnvase"));
				env.setIdMaterial(rs.getLong("idMaterial"));
				env.setNombre(rs.getString("nombre"));
				env.setDescripcion(rs.getString("descripcion"));
				env.setDimensiones(rs.getString("dimensiones"));
				env.setPeso(rs.getDouble("peso"));
				env.setStock(rs.getDouble("stock"));
				//La añadimos al Vector de resultado
				resultado.add(env);
				// System.out.println("DAO nombre del envase a consultar: "+env.getNombre());
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
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Boolean deshabilitaEnvase(Envase envase) throws Exception {
		// Borra el envase no lo deshabilita!
		// System.out.println("DAO deleteEnvase");
		Statement stmt;
		Connection con = null;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			//SQL de insersion
			String updateString= "DELETE envase WHERE idEnvase="
				+ envase.getIdEnvase();
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if(res == 1){
				// System.out.println("envase borrado!");
				resultado=true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {}
		}
	}
}
