package es.induserco.opilion.data.entidades;

import javax.swing.*;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;

public class Example {

	/**
	 * Using barbecue as swing component.
	 */
	public void usingBarbecueAsSwingComponent() {
		JPanel panel = new JPanel();
		// Always get a Barcode FROM the BarcodeFactory
        Barcode barcode = null;
        try {
            barcode = BarcodeFactory.createCode128B("My Barcode");
        } catch (BarcodeException e) {
            // Error handling
        }

        /* Because Barcode extends Component, you can use it just like any other
		 * Swing component. In this case, we can add it straight into a panel
		 * AND it will be drawn AND layed out according to the layout of the panel.
		 */
		panel.add(barcode);
	}

	/**
	 * Drawing barcode direct to graphics.
	 *
	 * @throws BarcodeException the barcode exception
	 * @throws OutputException the output exception
	 */
	public void drawingBarcodeDirectToGraphics() throws BarcodeException, OutputException {
		// Always get a Barcode FROM the BarcodeFactory
		Barcode barcode = BarcodeFactory.createCode128B("My Barcode");

		// We are created an image FROM scratch here, but for printing in Java, your
		// print renderer should have a Graphics internally anyway
		BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_BYTE_GRAY);
		// We need to cast the Graphics FROM the Image to a Graphics2D - this is OK
		Graphics2D g = (Graphics2D) image.getGraphics();

		// Barcode supports a direct draw method to Graphics2D that lets you position the
		// barcode on the canvas
		barcode.draw(g, 10, 56);
	}

    /**
     * Outputting barcode as png.
     *
     * @throws BarcodeException the barcode exception
     */
    public void outputtingBarcodeAsPNG() throws BarcodeException {
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
}