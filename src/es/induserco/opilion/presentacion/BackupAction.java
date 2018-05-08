package es.induserco.opilion.presentacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Backup;

/**
 * Exporta la base de datos a un fichero
 * @author andres (20/05/2011)
 * @version 0.1
 */
public class BackupAction extends ActionSupport 
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
		System.out.println("Backup");
		return null;
	}
	
	public String execute() throws Exception {
		System.out.println("Execute BACKUP:");// " + password);
    	boolean error = false;
    	String mensaje = "";
    	Vector<Backup> backups = new Vector<Backup>();
	    try{
	    	String ruta = "";
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
			String fileName = getTagValue("fileName", elemento);
			String rutaProceso = getTagValue("rutaProceso", elemento);
			String rutaBackups = getTagValue("rutaBackups", elemento);
			this.user = getTagValue("user", elemento);
			this.password = getTagValue("pass", elemento);
			
			//Preparamos el nombre del fichero de salida
			//Calendar calendario = Calendar.getInstance();
			Calendar calendario = new GregorianCalendar();
			int hora, minutos, segundos, dia, mes, annio, ms;
			hora = calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			ms = calendario.get(Calendar.MILLISECOND);
			dia = calendario.get(Calendar.DAY_OF_MONTH);
			mes = calendario.get(Calendar.MONTH) + 1;
			annio = calendario.get(Calendar.YEAR);
			fileName += "_" + dia + "_" + mes + "_" + annio + "_" + hora + "_" + minutos + "_" + segundos + "_" + ms + ".sql";
			
	    	//Sentencia para crear el BackUp
	    	String user = this.user, password = this.password;
	    	//String ruta = "C:\\xampp\\mysql\\bin\\mysqldump " +
	        ruta = rutaProceso + 
	    			" --host=" + host + " --port=" + puerto +
			        " --user=" + user + " --password=" + password +
			        " --compact --complete-insert --extended-insert --skip-quote-names" +
			        " --skip-comments --skip-triggers " + db;
	    	System.out.println(ruta);
	    	Process run = Runtime.getRuntime().exec(ruta);
	    	//1. Leemos los errores
	    	InputStream er = run.getErrorStream();
	    	BufferedReader brEr = new BufferedReader(new InputStreamReader(er));
		    temp = new StringBuffer();
		    int count = 0;
		    char[] cbuf = new char[BUFFER];
		    if (brEr.ready())
		    	while ((count = brEr.read(cbuf, 0, BUFFER)) != -1)
		    		temp.append(cbuf, 0, count);
	        brEr.close();
	        er.close();
	        System.out.println(temp.toString());
	        //mysqldump: Got error: 1044: Access denied for user 'opilion_app'@'%' to database 'bdd_trazabilidad' when using LOCK TABLES
	        //mysqldump: Got error: 1045: Access denied for user 'andres'@'INDUPC05' (using password: YES) when trying to connect
	        
        	if (temp.toString().indexOf("Got error: 1044:") > -1){
	        	mensaje = "Usuario sin permisos suficientes";
	        	System.out.println(mensaje);
	        	request.getSession().setAttribute("mensajeError", mensaje);
	        	error = true;
	        } else
	        	if (temp.toString().indexOf("Got error: 1045:") > -1){
	        		mensaje = "Acceso denegado para el usuario " + user + ".";
		        	System.out.println(mensaje);
		        	request.getSession().setAttribute("mensajeError", mensaje);
		        	error = true;
		        }
        	if (!error){
		    	//2. Leemos la salida normal de la ejecucion
		    	InputStream in = run.getInputStream();
			    BufferedReader br = new BufferedReader(new InputStreamReader(in));
			    temp = new StringBuffer();
			    count = 0;
			    cbuf = new char[BUFFER];
		        while ((count = br.read(cbuf, 0, BUFFER)) != -1)
		            temp.append(cbuf, 0, count);
		        br.close();
		        in.close();
		        fichero = new FileWriter(filePath + rutaBackups + fileName);
		        pw = new PrintWriter(fichero);
		        pw.println(temp.toString());
	        	request.getSession().setAttribute("path", rutaBackups + fileName);
	        	
	        	//ruta = "./webapps/opilionEntrada/backups";
	        	ruta = filePath + rutaBackups;
		    	File dir = new File(ruta);
		    	String[] ficheros = dir.list();
		    	int x = 0;
		    	if (ficheros == null)
		    		System.out.println("No hay ficheros en el directorio especificado");
		    	else {
		    		for (x = 0; x < ficheros.length; x++){
		    			System.out.println(ficheros[x]);
		    			//if (ficheros[x].compareTo("mysqldump.exe") != 0){
		    			if (ficheros[x].indexOf("backup_") == 0){
		    				Backup backup = new Backup();
		    				backup.setNombre(ficheros[x]);
		    				backup.setRuta(ruta + "/" + ficheros[x]);
		    				String[] frag = ficheros[x].split("_");
		    				String d = frag[1];
		    				if (d.length() == 1)
		    					d = "0" + d;
		    				String m = frag[2];
		    				if (m.length() == 1)
		    					m = "0" + m;
		    				String a = frag[3];
		    				backup.setFecha(d + "/" + m + "/" + a);
		    				backups.add(backup);
		    			}
		    		}
		    		this.request.getSession().setAttribute("historico", backups);
		    	}
		    	//Registramos en la base de datos
		    	new GestionesEspecialesHelper().registrarBackup(backups.lastElement());
        	}
	    }
	    catch (Exception ex){
	    	error = true;
	    	mensaje = "Error realizando la copia de seguridad";
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