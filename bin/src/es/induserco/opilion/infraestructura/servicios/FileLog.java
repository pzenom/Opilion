package es.induserco.opilion.infraestructura.servicios;

import java.io.FileWriter;
import java.io.PrintWriter;

public class FileLog implements LogService {

	private String codigo;
	private String fichero="SalidaAlertas.txt"; 
	
	//@Override
	public void enviarMensaje(String mensaje){
		try{
			PrintWriter out = new PrintWriter(new FileWriter(fichero, true));	
			out.println("\t\t" + codigo +
					"::\tENVIANDO MENSAJE SERVICIO DE LOG DE NEGOCIO: " + mensaje);
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setCodigo(String codigo) { this.codigo = codigo; }
	public void setFichero(String fichero) { this.fichero = fichero; }
}