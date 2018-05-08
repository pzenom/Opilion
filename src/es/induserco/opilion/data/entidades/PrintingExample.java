package es.induserco.opilion.data.entidades;


import java.awt.print.*;
import java.io.File;

import net.sourceforge.barbecue.*; 

// TODO: Auto-generated Javadoc
/**
 * Print a barcode using Java's print API.
 *
 * @author Sean C. Sullivan
 */
public class PrintingExample
{

    /**
     * Outputting barcode as png.
     *
     * @throws BarcodeException the barcode exception
     */
    public static void outputtingBarcodeAsPNG() throws BarcodeException {
        // get a Barcode FROM the BarcodeFactory
		Barcode barcode = BarcodeFactory.createCode128B("My Barcode");

        try {
            File f = new File("mybarcode.png");

            // Let the barcode image handler do the hard work
            BarcodeImageHandler.savePNG(barcode, f);
        } catch (Exception e) {
            // Error handling here
        }
    }	
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		try
		{
			Barcode b = BarcodeFactory.createCode128("E100831-326");
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintable(b);
			job.setJobName("Codigo RE");
			job.setCopies(2);
			

			if (job.printDialog())
			{
				job.print();
			}
			
			outputtingBarcodeAsPNG();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
