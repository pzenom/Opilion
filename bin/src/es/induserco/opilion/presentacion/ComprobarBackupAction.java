package es.induserco.opilion.presentacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
public class ComprobarBackupAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
    private FileWriter  fichero = null;
    private PrintWriter pw = null;

	//@Override
	public Object getModel() {
		System.out.println("INICIO");
		return null;
	}
	
	public String execute() throws Exception {
		System.out.println("Execute ComprobarBackupAction");
		comprobarBackup();
	    return SUCCESS;
	}

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}
	
	private void comprobarBackup() throws Exception {
		new GestionesEspecialesHelper().registrarBackup(new Backup());
		new GestionesEspecialesHelper().registroBackupAuto();
    	
		boolean error = false;
		Backup ultimo = new GestionesEspecialesHelper().getUltimoBackupAuto();
		String fechaUltimo = ultimo.getFecha();
		int annio = 0, mes = 0, dia = 0;
		long diferencia = 0;
		if (fechaUltimo != null){
			//String hoy = dateFormat.format(calendar.getTime());
			//Comparar fechas
			//Si el numero de dias es menor que 7, hacer copia de seguridad y actualizar la base de datos
			final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al d�a 
			java.util.Date hoy = new Date(); //Fecha de hoy
			String frag[] = fechaUltimo.split("-");
			annio = Integer.parseInt(frag[0]);
			mes = Integer.parseInt(frag[1]);
			dia = Integer.parseInt(frag[2]);
			Calendar programada = new GregorianCalendar(annio, mes - 1, dia); 
			java.sql.Date fechaProgramada = new java.sql.Date(programada.getTimeInMillis());
			System.out.println("Milisegundos hoy: " + hoy.getTime());
			System.out.println("Milisegundos programada: " + fechaProgramada.getTime());
			diferencia = (hoy.getTime() - fechaProgramada.getTime() ) / MILLSECS_PER_DAY;
			System.out.println("Diferencia en dias entre " + fechaUltimo
					+ " y " + hoy + " es de " + diferencia + " dias");
		}
		if (diferencia > 6 || fechaUltimo == null){//HACER COPIA DE SEGURIDAD y actualizar la base de datos
			int BUFFER = 10485760;
			//Para guardar en memmoria
		    StringBuffer temp = null;
		    //Para guardar el archivo SQL
		    FileWriter  fichero = null;
	    	String mensaje = "";
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
				String fileName = getTagValue("fileName", elemento);
				String rutaProceso = getTagValue("rutaProceso", elemento);
				String user = getTagValue("user", elemento);
				String password = getTagValue("pass", elemento);
				String filePath = getTagValue("filePath", elemento);
				String rutaBackups = getTagValue("rutaBackups", elemento);
				
				//Preparamos el nombre del fichero de salida
				//Calendar calendario = Calendar.getInstance();
				Calendar calendario = new GregorianCalendar();
				int hora, minutos, segundos, ms;
				hora = calendario.get(Calendar.HOUR_OF_DAY);
				minutos = calendario.get(Calendar.MINUTE);
				segundos = calendario.get(Calendar.SECOND);
				ms = calendario.get(Calendar.MILLISECOND);
				dia = calendario.get(Calendar.DAY_OF_MONTH);
				mes = calendario.get(Calendar.MONTH) + 1;
				annio = calendario.get(Calendar.YEAR);
				fileName += "_" + dia + "_" + mes + "_" + annio + "_" + hora +
					"_" + minutos + "_" + segundos + "_" + ms + ".sql";
		    	//Sentencia para crear el BackUp
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
			        }if (!error){
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
			        	System.out.println("Copia de seguridad realizada con �xito. " +
			        			"Actualizando la base de datos...");
			        	new GestionesEspecialesHelper().registroBackupAuto();
			        	System.out.println("Base de datos actualizada con �xito!");
				    	}
			        }
		    catch (Exception ex){
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
		}
	}
	
	private static String getTagValue(String sTag, Element eElement){
		NodeList nlList= eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
}