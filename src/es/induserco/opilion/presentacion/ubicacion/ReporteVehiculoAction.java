package es.induserco.opilion.presentacion.ubicacion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Vehiculo;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class ReporteVehiculoAction extends ActionSupport implements ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private Vehiculo vehiculo = new Vehiculo();
	private String url;
	private final String FORMATO_IMAGEN = "png";
	private final String RUTA_IMAGEN = "/reportes_generados/vehiculo/", RUTA_PDF = "/reportes_generados/vehiculo/";
	private final int tamanio = 500;
	private static Logger logger = Logger.getLogger(ReporteVehiculoAction.class);

	public String execute() throws Exception {
		logger.info("ReporteVehiculoAction!!");
		logger.info("Imprimir reporte de vehiculo: " + vehiculo.getIdVehiculo());
		//Obtenemos el vehículo con la carga que vamos a mostrar
		vehiculo = new GestionUbicacionesHelper().getVehiculoCompleto(vehiculo.getIdVehiculo());
		
		//Timestampt actual
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		//Ruta del pdf generado
		ActionContext ac = ActionContext.getContext();
		ServletContext sc = (ServletContext)ac.get(StrutsStatics.SERVLET_CONTEXT);
		String rutaPDF = sc.getRealPath(this.RUTA_PDF) + "/" + timeStamp;
		//Obtenemos las urls de los códigoso QR para el vehículo
		Vector<String> urls = vehiculo.getUrlCodigosQR();
		for (int i = 0; i < urls.size(); i++){
			String datos = urls.get(i);
			//Matriz de datos a dibujar
			BitMatrix bm;
			//Writer que nos ayudará a darle formato a los datos
			Writer writer = new QRCodeWriter();
			//Generaremos la matríz codificando los datos que establecimos previamente dándoles el formato que necesitamos (Código QR)
			bm = writer.encode(datos, BarcodeFormat.QR_CODE, tamanio, tamanio);
			//Ahora necesitaremos un buffer a través del cual escribir la imagen al disco duro
			BufferedImage image = new BufferedImage(tamanio, tamanio, BufferedImage.TYPE_INT_RGB);
			//Escribir la matriz de datos con dos ciclos anidados, iterando a través de lo ancho y alto de la imagen
			for (int y = 0; y < tamanio; y++) {
				for (int x = 0; x < tamanio; x++) {
					int grayValue = (bm.get(x, y) ? 1 : 0) & 0xff;
			        image.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
			    }
			}
			//Con el fin de corregir la imagen (colores invertidos)
			image = invertirColores(image);
			
			//El nombre de la imagen será el timestamp actual, mas el identificador de la página (i + 1)
			String nombreImagenQR = timeStamp + "_" + (i + 1) + "." + FORMATO_IMAGEN;
			//Preparamos la ruta donde almacenaremos la imagen
			String rutaImagen = sc.getRealPath(this.RUTA_IMAGEN) + "/" + nombreImagenQR;
			
			//Escribimos la imagen en el disco duro:
			FileOutputStream qrCode = new FileOutputStream(rutaImagen);
			ImageIO.write(image, FORMATO_IMAGEN, qrCode);
			//cerramos el flujo de datos que utilizamos:
			qrCode.close();
		}
		
		/*
		VIEJO
		//Obtenemos la url del codigo QR para el vehículo
		String datos = vehiculo.getUrlCodigoQR();
		//Matriz de datos a dibujar
		BitMatrix bm;
		//Writer que nos ayudará a darle formato a los datos
		Writer writer = new QRCodeWriter();
		//Generaremos la matríz codificando los datos que establecimos previamente dándoles el formato que necesitamos (Código QR)
		bm = writer.encode(datos, BarcodeFormat.QR_CODE, tamanio, tamanio);
		//Ahora necesitaremos un buffer a través del cual escribir la imagen al disco duro
		BufferedImage image = new BufferedImage(tamanio, tamanio, BufferedImage.TYPE_INT_RGB);
		//Escribir la matriz de datos con dos ciclos anidados, iterando a través de lo ancho y alto de la imagen
		for (int y = 0; y < tamanio; y++) {
			for (int x = 0; x < tamanio; x++) {
				int grayValue = (bm.get(x, y) ? 1 : 0) & 0xff;
		        image.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
		    }
		}
		//Con el fin de corregir la imagen (colores invertidos)
		image = invertirColores(image);
		
		//El nombre de la imagen será el timestamp actual
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String nombreImagenQR = timeStamp + "." + FORMATO_IMAGEN;
		//Preparamos la ruta donde almacenaremos la imagen y el pdf
		ActionContext ac = ActionContext.getContext();
		ServletContext sc = (ServletContext)ac.get(StrutsStatics.SERVLET_CONTEXT);
		String rutaImagen = sc.getRealPath(this.RUTA_IMAGEN) + "/" + nombreImagenQR;
		String rutaPDF = sc.getRealPath(this.RUTA_PDF) + "/" + timeStamp;
		
		//Escribimos la imagen en el disco duro:
		FileOutputStream qrCode = new FileOutputStream(rutaImagen);
		ImageIO.write(image, FORMATO_IMAGEN, qrCode);
		//cerramos el flujo de datos que utilizamos:
		qrCode.close();*/
		
		//Tenemos ya la imagen generada en el disco duro, generamos el reporte en el que incrustaremos esta imagen
		JasperReport reporte = null;
		vehiculo.setNombreImagenQR(timeStamp);
		List<Object> lista = new ArrayList<Object>();
		lista.add(vehiculo);
		reporte = (JasperReport) JRLoader.loadObjectFromFile("/reportes/ubicaciones/reporteVehiculo.jasper");
		setUrl("/reportes/ubicaciones/reporteVehiculo.jasper");		
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
		
		//Guardamos el pdf
		OutputStream output = new FileOutputStream(new File(rutaPDF + ".pdf")); 
        JasperExportManager.exportReportToPdfStream(jasperPrint, output); 
        output.flush();
        output.close();
		
		return SUCCESS;
	}

	/**
	 * Debido a un bug, las imágenes se crean con los colores invertidos (el negro por el blanco y viceversa)
	 * por lo cual es necesario invertir los colores de la imagen,
	 * @param imagen
	 * @return
	 */
	private BufferedImage invertirColores(BufferedImage imagen) {
        for (int x = 0; x < tamanio; x++) {
            for (int y = 0; y < tamanio; y++) {
                int rgb = imagen.getRGB(x, y);
                if (rgb == -16777216) {
                    imagen.setRGB(x, y, -1);
                } else {
                    imagen.setRGB(x, y, -16777216);
                }
            }
        }
        return imagen;
    }

	public void setUrl(String url) { this.url = url; }
	public String getUrl() { return url; }

	//@Override
	public Object getModel() {
		return vehiculo;
	}

	public Vehiculo getVehiculo(){
		return vehiculo;
	}
}