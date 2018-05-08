package es.induserco.opilion.presentacion;

import java.io.File;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Backup;

/**
 * Exporta la base de datos a un fichero
 * @author andres (20/05/2011)
 * @version 0.1
 */
public class PrepararBackupAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	
	//@Override
	public void setServletRequest(HttpServletRequest request) { this.request=request; }

	//@Override
	public Object getModel() {
		System.out.println("Backup");
		return null;
	}
	
	public String execute() throws Exception {
		System.out.println("Execute Preparar backup BACKUP");
    	boolean error = false;
    	Vector<Backup> backups = new Vector<Backup>();
	    try{
	    	String ruta = "./webapps/opilion/backups";
	    	File dir = new File(ruta);
	    	String[] ficheros = dir.list();
	    	if (ficheros == null)
	    		System.out.println("No hay ficheros en el directorio especificado");
	    	else {
	    		for (int x = 0; x < ficheros.length; x++){
	    			System.out.println(ficheros[x]);
	    			if (ficheros[x].compareTo("mysqldump.exe") != 0){
	    				Backup backup = new Backup();
	    				backup.setNombre(ficheros[x]);
	    				backup.setRuta(ruta + "/" + ficheros[x]);
	    				if (ficheros[x].indexOf("backup") >= 0){
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
	    		}
	    		this.request.getSession().setAttribute("historico", backups);
	    	}
	    }
	    catch (Exception ex){
	    	error = true;
            ex.printStackTrace();
	    } finally {
	    	try {
	    	} catch (Exception e2) { e2.printStackTrace(); }
	    }
	    if (!error)
	    	return ("SUCCESS");
	    else
	    	return ("ERROR");
	}
}