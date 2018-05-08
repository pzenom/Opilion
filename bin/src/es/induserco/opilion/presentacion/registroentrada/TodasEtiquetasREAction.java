package es.induserco.opilion.presentacion.registroentrada;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.lowagie.text.pdf.PdfContentByte;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.negocio.RegistroEntradaDataHelper;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class TodasEtiquetasREAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private RegistroEntrada re = new RegistroEntrada();

	public RegistroEntrada getRe() {
		return re;
	}

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de reporte registro de envasado!");
		return re;
	}

	public String execute() throws Exception {
		Vector<Bulto> bultos = new RegistroEntradaDataHelper().getBultosRegistroEntrada(re);
		for (int i = 0; i < bultos.size(); i++){
			//Para cada bulto, crear su etiqueta
			Bulto bulto = bultos.get(i);
			//bulto.setCodigoEntrada((String)(request.getParameter("idEntrada")));
			//System.out.println("ID: " + bulto.getCodigoEntrada());
			//bulto.setPBruto(bulto.getPBruto() / 10);
			//bulto.setPNeto(bulto.getPNeto() / 10);
			//bulto.setPBruto(Double.parseDouble(request.getParameter("pBruto")));
			//bulto.setPNeto(Double.parseDouble(request.getParameter("pNeto")));
			//Colocamos la lista de entradades en la request.
			re = new GestionRegistroEntradaHelper().getEtiquetaRE(bulto.getCodigoEntrada());
			if (re.getDescCategoria() == null){
				re.setOrigen("");
			}
			else
				if(re.getDescCategoria().equals("Extra")){
					re.setOrigen("E");
				} else if(re.getDescCategoria().equals("Extra B")){
					re.setOrigen("B");
				} else if(re.getDescCategoria().equals("Primera")){
					re.setOrigen("P");
				} else if(re.getDescCategoria().equals("Segunda")){
					re.setOrigen("S");
				}
			re.setHumedad(bulto.getPBruto());
			re.setCostoUnitario(bulto.getPNeto());
			//re.setDescVehiculo(Integer.toString(bulto.getNumBultosPalet()));
			re.setAlbaran(String.valueOf(bulto.getNumBulto()));
			JasperReport reporte = (JasperReport) JRLoader.loadObject("/reportes/EtiqREB2JR.jasper");
			//Colocamos la lista de entradas en la request.
			List lista = new ArrayList(); 
	        lista.add(re);
			JasperFillManager.fillReportToFile(reporte, "hola.pdf", null, new JRBeanCollectionDataSource(lista));
		}
		//Juntamos todas las etiquetas
		/*Document document = new Document();
	    try {
	      List<InputStream> pdfs = streamOfPDFFiles;
	      List<PdfReader> readers = new ArrayList<PdfReader>();
	      int totalPages = 0;
	      Iterator<InputStream> iteratorPDFs = pdfs.iterator();

	      // Create Readers for the pdfs.
	      while (iteratorPDFs.hasNext()) {
	        InputStream pdf = iteratorPDFs.next();
	        PdfReader pdfReader = new PdfReader(pdf);
	        readers.add(pdfReader);
	        totalPages += pdfReader.getNumberOfPages();
	      }
	      // Create a writer for the outputstream
	      PdfWriter writer = PdfWriter.getInstance(document, outputStream);

	      document.open();
	      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	      PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
	      // data

	      PdfImportedPage page;
	      int currentPageNumber = 0;
	      int pageOfCurrentReaderPDF = 0;
	      Iterator<PdfReader> iteratorPDFReader = readers.iterator();

	      // Loop through the PDF files AND add to the output.
	      while (iteratorPDFReader.hasNext()) {
	        PdfReader pdfReader = iteratorPDFReader.next();

	        // Create a new page in the target for each source page.
	        while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
	          document.newPage();
	          pageOfCurrentReaderPDF++;
	          currentPageNumber++;
	          page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
	          cb.addTemplate(page, 0, 0);

	          // Code for pagination.
	          if (paginate) {
	            cb.beginText();
	            cb.setFontAndSize(bf, 9);
	            cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " of " + totalPages, 520, 5, 0);
	            cb.endText();
	          }
	        }
	        pageOfCurrentReaderPDF = 0;
	      }
	      outputStream.flush();
	      document.close();
	      outputStream.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      if (document.isOpen())
	        document.close();
	      try {
	        if (outputStream != null)
	          outputStream.close();
	      } catch (IOException ioe) {
	        ioe.printStackTrace();
	      }
	    }*/
		return SUCCESS;
	}
}