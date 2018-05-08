package es.induserco.opilion.presentacion;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
public class SalvarCambiosDBAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private ConfiguracionDB config = new ConfiguracionDB();
	private String url;
	
	//@Override
	public void setServletRequest(HttpServletRequest request) { this.request=request; }

	//@Override
	public Object getModel() {
		System.out.println("SalvarCambiosDBAction");
		return config;
	}
	
	public String execute() throws Exception {
		System.out.println("Execute SalvarCambiosDBAction");// " + password);
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
			
			setTagValue("host", elemento, config.getHost());
			setTagValue("db", elemento, config.getDb());
			setTagValue("puerto", elemento, config.getPuerto());
			setTagValue("filePath", elemento, config.getFilePath());
			setTagValue("rutaProceso", elemento, config.getRutaProceso());
			setTagValue("rutaBackups", elemento, config.getRutaBackups());
			setTagValue("user", elemento, config.getUser());
			setTagValue("pass", elemento, config.getPass());
			
			//Escribimos los cambios en el fichero xml
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result =  new StreamResult(new File(path));
			transformer.transform(source, result);
			
			//Volvemos a cargar el xml para mostrarlo
			doc = dBuilder.parse(new File(path));
			doc.getDocumentElement().normalize();
			listaNodosHijos = doc.getElementsByTagName("dbBackUp");
			configBD = listaNodosHijos.item(0);
			elemento = (Element) configBD;
							
			String host = getTagValue("host", elemento);
			String db = getTagValue("db", elemento);
			String puerto = getTagValue("puerto", elemento);
			String filePath = getTagValue("filePath", elemento);
			String rutaProceso = getTagValue("rutaProceso", elemento);
			String rutaBackups = getTagValue("rutaBackups", elemento);
			String user = getTagValue("user", elemento);
			String password = getTagValue("pass", elemento);
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
	    	} catch (Exception e2) {
	    		e2.printStackTrace();
	    	}
	    }
	    if (!error){
	    	setUrl("ConfigurarDB");
	    }
	    else{
	    	setUrl("ConfigurarDBDefecto");
	    }
	    return ("redirect");
	}

	private static String setTagValue(String sTag, Element eElement, String value){
		NodeList nlList= eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		nValue.setNodeValue(value);
		eElement.getElementsByTagName(sTag).item(0).getChildNodes().item(0).setNodeValue(value);
		return nValue.getNodeValue();
	}
	
	private static String getTagValue(String sTag, Element eElement){
		NodeList nlList= eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}

	public void setUrl(String url) { this.url = url; }
	public String getUrl() { return url; }
}