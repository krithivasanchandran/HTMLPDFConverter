package com.billing.paypal.pdf;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.IOException;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfResources;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.text.DocumentException;

public class IndividualMergePDFFunctionality {
	//public static final Map<String,Object> pdfFileCarrier = new HashMap<String,Object>();

	public static void main(String[] args) throws Exception {

		String SRC = "C:/Users/krichandran/Documents/gap_inv_withprint_css.html";
		// String SRC2 = "C:/Users/krichandran/Desktop/pinacle.html.txt";

		String DEST = "C:/Users/krichandran/Documents/aabbccdd.pdf";
		// String DEST2 = "C:/Users/krichandran/Documents/testinvoice2.pdf";
		
		String manipulatedDestination = "C:/Users/krichandran/Documents/manipulated_aabbccdd.pdf";

		String mergeDest = "C:/Users/krichandran/Documents/Merger.pdf";

		byte[] instream;
		byte[] tempstream;
		LinkedList<byte[]> ops = new LinkedList<byte[]>();
		
		try {

			tempstream = Files.readAllBytes(Paths.get(SRC));
			System.out.println("Writing to " + DEST);

			//export(tempstream);
			
			Map<String,Object> pdfFileCarrier = PDFExporter.exportGAPInvoice(tempstream);
			
			PdfDocument pdfout = (PdfDocument) pdfFileCarrier.get("PdfDocument");
			byte[] bytearrstream = (byte[]) pdfFileCarrier.get("ByteArrayStream");
			
			System.out.println("File Output Contents :: " + bytearrstream.length + " contents :: " + new String(bytearrstream,"utf-8"));
			
			FileOutputStream pdf = new FileOutputStream(DEST);
			pdf.write(bytearrstream);
			pdf.close();
			
		//	manipulatePdf(DEST,manipulatedDestination);
			
			ManipulatePdf(DEST,manipulatedDestination);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	 public static void ManipulatePdf(String src, String dest) throws FileNotFoundException, java.io.IOException {
         //Initialize PDF document
         PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
         float margin = 5;
         
         for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
        	 
             PdfPage page = pdfDoc.getPage(i);
             // change page size
             Rectangle mediaBox = page.getMediaBox();
             Rectangle newMediaBox = new Rectangle(mediaBox.getLeft(), mediaBox.getBottom() - (margin * 2), mediaBox
                 .getWidth() + margin * 25, mediaBox.getHeight() - (margin *3));
             //mediaBox.moveLeft(60f);
            
             newMediaBox.moveDown(-23f);
             newMediaBox.moveRight(25.5f);
             newMediaBox.moveUp(-7f);
             page.setMediaBox(newMediaBox);
             
             
             
            // mediaBox.moveRight(50f);
             // add border
//             PdfCanvas over = new PdfCanvas(page);
//             over.setStrokeColor(ColorConstants.GRAY);
//             over.rectangle(mediaBox.getLeft(), mediaBox.getBottom(), mediaBox.getWidth(), mediaBox.getHeight());
//             over.stroke();
             // change rotation of the even pages
//             if (i % 2 == 0) {
//                 page.SetRotation(180);
//             }
         }
         pdfDoc.close();
     }
	 
	 
	 /*
	  * Working Code for - floatingpoint_ margin_GAP_invoice.pdf
	  *
	  *
	  *
	  
	  public static void ManipulatePdf(String src, String dest) throws FileNotFoundException, java.io.IOException {
         //Initialize PDF document
         PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
         float margin = 10;
         
         for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
        	 
             PdfPage page = pdfDoc.getPage(i);
             // change page size
             Rectangle mediaBox = page.getMediaBox();
             Rectangle newMediaBox = new Rectangle(mediaBox.getLeft() + Math.round(margin * 2.5), mediaBox.getBottom() - margin, mediaBox
                 .getWidth() + margin * 13, mediaBox.getHeight() + margin );
             //mediaBox.moveLeft(60f);
            
             //mediaBox.moveUp(50f);
             page.setMediaBox(newMediaBox);
            // mediaBox.moveRight(50f);
             // add border
//             PdfCanvas over = new PdfCanvas(page);
//             over.setStrokeColor(ColorConstants.GRAY);
//             over.rectangle(mediaBox.getLeft(), mediaBox.getBottom(), mediaBox.getWidth(), mediaBox.getHeight());
//             over.stroke();
             // change rotation of the even pages
//             if (i % 2 == 0) {
//                 page.SetRotation(180);
//             }
         }
         pdfDoc.close();
     }*/
	 
	 
	 /*
	  * Working Code for - lesser_margin_top_GAP_invoice.pdf
	  *
	 
	 public static void ManipulatePdf(String src, String dest) throws FileNotFoundException, java.io.IOException {
         //Initialize PDF document
         PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
         float margin = 30;
         for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
             PdfPage page = pdfDoc.getPage(i);
             // change page size
             Rectangle mediaBox = page.getMediaBox();
             Rectangle newMediaBox = new Rectangle(mediaBox.getLeft() + margin, mediaBox.getBottom() - margin, mediaBox
                 .getWidth() + margin * 4, mediaBox.getHeight() + margin );
             //mediaBox.moveLeft(100f);
             mediaBox.moveUp(50f);
             page.setMediaBox(newMediaBox);
             
            
            
             // add border
//             PdfCanvas over = new PdfCanvas(page);
//             over.setStrokeColor(ColorConstants.GRAY);
//             over.rectangle(mediaBox.getLeft(), mediaBox.getBottom(), mediaBox.getWidth(), mediaBox.getHeight());
//             over.stroke();
             // change rotation of the even pages
//             if (i % 2 == 0) {
//                 page.SetRotation(180);
//             }
         }
         pdfDoc.close();
     }
	 */
	 
	 /*
	  * Working Code for - updated_GAP_invoice_.pdf
	  *
	 
	 public static void ManipulatePdf(String src, String dest) throws FileNotFoundException, java.io.IOException {
         //Initialize PDF document
         PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
         float margin = 30;
         for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
             PdfPage page = pdfDoc.getPage(i);
             // change page size
             Rectangle mediaBox = page.getMediaBox();
             Rectangle newMediaBox = new Rectangle(mediaBox.getLeft() + margin, mediaBox.getBottom() - margin, mediaBox
                 .getWidth() + margin * 4, mediaBox.getHeight() + margin * 2);
             //mediaBox.moveLeft(100f);
             mediaBox.moveUp(300f);
             page.setMediaBox(newMediaBox);
            
             // add border
//             PdfCanvas over = new PdfCanvas(page);
//             over.setStrokeColor(ColorConstants.GRAY);
//             over.rectangle(mediaBox.getLeft(), mediaBox.getBottom(), mediaBox.getWidth(), mediaBox.getHeight());
//             over.stroke();
             // change rotation of the even pages
//             if (i % 2 == 0) {
//                 page.SetRotation(180);
//             }
         }
         pdfDoc.close();
     }
	 */

//	protected static void manipulatePdf(String SRC,String DEST) throws Exception {
//		
//        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(DEST));
//        int n = pdfDoc.getNumberOfPages();
//        float percentage = 0.7f;
//        for (int p = 1; p <= n; p++) {
//            float offsetX = (pdfDoc.getPage(p).getPageSize().getWidth() * (1 - percentage)) / 2;
//            float offsetY = (pdfDoc.getPage(p).getPageSize().getHeight() * (1 + percentage)) / 2;
//            
//            //  float offsetY = pdfDoc.getPage(p).getPageSize().getHeight();
//            new PdfCanvas(pdfDoc.getPage(p).newContentStreamBefore(), new PdfResources(), pdfDoc).writeLiteral(
//                    String.format(Locale.ENGLISH, "\nq %s 0 0 %s %s %s cm\nq\n",
//                            percentage, percentage, offsetX, offsetY));
//            new PdfCanvas(pdfDoc.getPage(p).newContentStreamAfter(),
//                    new PdfResources(), pdfDoc).writeLiteral("\nQ\nQ\n");
//        }
//        pdfDoc.close();
//    }
	
//	public static void export(byte[] data) throws IOException, DocumentException, UnsupportedEncodingException, java.io.IOException {
//
//		// Create a buffer to hold the cleaned up HTML
//
//		ByteArrayOutputStream bOutStream = new ByteArrayOutputStream();
//
//		PdfDocument pdfDocument = new PdfDocument(new PdfWriter(bOutStream));
//		
//		// System.out.println(data);
//
//		// Clean up the HTML to be well formed
//		// HtmlCleaner cleaner = new HtmlCleaner();
//
//		ConverterProperties converterProps = new ConverterProperties();
//		converterProps.setCharset("utf-8");
//
//		PageSize pageSize = PageSize.A4;
//		pdfDocument.setDefaultPageSize(pageSize);
//
//		HtmlConverter.convertToPdf(new String(data, "UTF-8").replace("https://www.hyperwallet.com/app/uploads/hyperwallet-logo.svg", "C:/Users/krichandran/workspace/IndividualHTMLtoPDFConverter/hyperwalletlogo/hyperwallet-logo.svg"), pdfDocument, converterProps);
//
//		pdfDocument = new PdfDocument(new PdfReader(new ByteArrayInputStream(bOutStream.toByteArray())));
//
//		pdfFileCarrier.put("PdfDocument", pdfDocument);
//		pdfFileCarrier.put("ByteArrayStream", bOutStream.toByteArray());
//
//	}
	
	/**********************************************************************************
	 * Changes Made - Change Request Date - 6/6/2019. * Merge PDF Inclusions *
	 * iText - 7 Dependent Libraries was used for merging pdf invoices *
	 * 1.html2pdf-2.1.3.jar * 2. Kernel - 7.1.6.jar * 3. IO - 7.1.6.jar *
	 **********************************************************************************/

	private synchronized void mergeServicePDF(PdfDocument pdfdocument, PdfMerger merger) {

		try {

			merger.merge(pdfdocument, 1, pdfdocument.getNumberOfPages());
			

		} catch (Exception exe) {
			System.out.println(exe.getMessage() + "Cause -> " + exe.getCause().getMessage());
		}
	}

}
