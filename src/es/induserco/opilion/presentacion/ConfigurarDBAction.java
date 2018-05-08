package es.induserco.opilion.presentacion;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.ConfiguracionDB;

/**
 * Exporta la base de datos a un fichero
 * @author andres (20/05/2011)
 * @version 0.1
 */
public class ConfigurarDBAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String user;
	private String password;

	private int BUFFER = 10485760;  
	//Para guardar en memmoria
    private StringBuffer temp = null;
    //Para guardar el archivo SQL
    private FileWriter  fichero = null;
    private PrintWriter pw = null;
	
	//@Override
	public void setServletRequest(HttpServletRequest request) { this.request=request; }
	public void setPassword(String password){ this.password = password; }
	public void setUser(String user){ this.user = user; }

	//@Override
	public Object getModel() {
		System.out.println("ConfigurarDBAction");
		return null;
	}
	
	public String execute() throws Exception {
		System.out.println("Execute ConfigurarDBAction:");// " + password);
    	boolean error = false;
	    try{
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			String path = loader.getResource("configuracion.xml").toURI().getPath();
			Document doc = dBuilder.parse(new File(path));
			doc.getDocumentElement().normalize();
			//System.out.println("El elemento raiz es: " + doc.getDocumentElement().getNodeName());
			NodeList listaNodosHijos = doc.getElementsByTagName("dbBackUp");       
			//System.out.println("Existen "+ listaNodosHijos.getLength() +" nodos hijo de tipo baseDatos");
			Node configBD = listaNodosHijos.item(0);
			Element elemento = (Element) configBD;
			String host = getTagValue("host", elemento);
			String db = getTagValue("db", elemento);
			String puerto = getTagValue("puerto", elemento);
			String filePath = getTagValue("filePath", elemento);
			String rutaProceso = getTagValue("rutaProceso", elemento);
			String rutaBackups = getTagValue("rutaBackups", elemento);
			this.user = getTagValue("user", elemento);
			this.password = getTagValue("pass", elemento);
			ConfiguracionDB conf = new ConfiguracionDB();
			conf.setHost(host);
			conf.setDb(db);
			conf.setPuerto(puerto);
			conf.setFilePath(filePath);
			conf.setRutaProceso(rutaProceso);
			conf.setRutaBackups(rutaBackups);
			conf.setUser(user);
			conf.setPass(password);
			request.getSession().setAttribute("config", conf);
	    }
	    catch (Exception ex){
	    	error = true;
            ex.printStackTrace();
	    } finally {
	    	try {
	    		if (null != fichero)
	    			fichero.close();
	    	} catch (Exception e2) {
	    		e2.printStackTrace();
	    	}
	    }
	    if (!error)
	    	return ("SUCCESS");
	    else
	    	return ("ERROR");
	}

	private static String getTagValue(String sTag, Element eElement){
		NodeList nlList= eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
}