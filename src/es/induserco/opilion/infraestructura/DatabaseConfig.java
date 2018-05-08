package es.induserco.opilion.infraestructura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DatabaseConfig {
	
	protected String SQL_DRV;// = "org.gjt.mm.mysql.Driver";
	protected String SQL_URL;// = "jdbc:mysql://192.168.1.12:3306/bdd_trazabilidad";
	protected String SQL_USR;// = "opilion_app";
	protected String SQL_PAS;// = "opilion_app";
	protected String conection;// = "prueba";
	
	public Connection bddConexion() throws ClassNotFoundException,SQLException {
		Connection con = null;
		try {
			//System.out.println("SQL");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			String path = loader.getResource("configuracion.xml").toURI().getPath();
			//System.out.println("Path del fichero de configuración es: " + path);
			
			Document doc = dBuilder.parse(new File(path));
			doc.getDocumentElement().normalize();
			
			//System.out.println("El elemento raiz es: " + doc.getDocumentElement().getNodeName());
			NodeList listaNodosHijos = doc.getElementsByTagName("baseDatos");       
			//System.out.println("Existen "+ listaNodosHijos.getLength() +" nodos hijo de tipo baseDatos");
			Node configBD = listaNodosHijos.item(0);
			Element elemento = (Element) configBD;
			SQL_DRV = getTagValue("SQL_DRV", elemento);
			SQL_URL = getTagValue("SQL_URL", elemento);
			SQL_USR = getTagValue("SQL_USR", elemento);
			SQL_PAS = getTagValue("SQL_PAS", elemento);
			conection = getTagValue("conection", elemento);
			
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, SQL_USR, SQL_PAS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private static String getTagValue(String sTag, Element eElement){
		NodeList nlList= eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
}